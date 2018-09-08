package dangeon.model.object.artifact;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.res.Image_Artifact;
import main.res.SE;
import main.util.CSVLoadSupporter;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemDetail;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.arrow.ミニ八卦炉;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.enchantSpecial.Composition;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印邪;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.Damage;
import dangeon.util.ObjectPoint;
import dangeon.util.R;
import dangeon.view.constant.MAP;
import dangeon.view.util.WithinOutofScreen;

public abstract class Base_Artifact extends Base_MapObject implements
		Comparable<Base_Artifact> {
	/**
	 * アイテムの特殊効果タイミング
	 * 
	 * @author Administrator
	 * 
	 */
	public enum ENCHANT_CASE {
		ENCHANT,
	}

	protected enum GROW_RATE {
		早熟, 通常, 晩成
	}

	public enum ITEM_CASE {
		DISC, SPELLCARD, WEAPOM, SHIELD, ETC, FOOD, SCROOL, GRASS, TRAP, RING, STAFF, ARROW
	}

	public enum STATUS {
		STR, DEF, SPECIAL
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static void exchange(Base_Artifact pickUp, Base_Artifact a) {
		exchange(pickUp, Belongings.getListItems().indexOf(a));
	}

	public static void exchange(Base_Artifact pickUp, int setOn) {
		Message.set(pickUp.getColoredName().concat("を拾って"));
		Base_Artifact a = Belongings.get(setOn);
		if (Belongings.pickUpItem(pickUp, setOn)) {
			pickUp.setMassPoint(new Point(-1, -1));
			TaskOnMapObject.addTrapRemoveTask(pickUp);
		}
		a.itemSetOn();
	}

	protected Image_Artifact IM = Image_Artifact.NULL;

	/**
	 * trueなら拾ったり投げたりできる
	 */
	protected boolean mobile;

	/**
	 * finalを外すならcomposition_numbe、compositionの兼ね合いをわすれずに
	 */
	public int composition_number;

	/**
	 * finalを外すならcomposition_numbe、compositionの兼ね合いをわすれずに
	 */
	public final List<Base_Artifact> compositon;

	protected int item_freeze_count = 0;

	protected boolean flag_no_frozen = false;

	public int pick_count = 0;

	/**
	 * (罠の)可視性
	 */
	protected boolean visible = false;

	/**
	 * 修正値
	 */
	protected int forge_value = 0;

	/**
	 * 杖の修正値
	 */
	public int staff_rest = 0;

	private boolean curse = false;

	private final ITEM_CASE ITEM_CASE;

	protected int item_exp = 0;

	Mass[][] mass = MassCreater.getMass();
	public ENCHANT_SIMBOL sim;
	/**
	 * 合成印を持たせるリスト
	 */
	private List<ENCHANT_SIMBOL> list_composition = new ArrayList<ENCHANT_SIMBOL>();
	/**
	 * いわゆる半識別状態かどうか<br/>
	 */
	private boolean checked_static = false;

	protected String[] exn;

	/**
	 * ItemWiki用
	 */
	public boolean flag_sample, flag_unknown;

	protected boolean flag_punishment = false, flag_merchant;

	protected boolean flag_pass_turn;

	private boolean flag_hatate;

	protected Base_Artifact(Point p, String name, int composition_number,
			ITEM_CASE item_case, boolean mobile) {
		super(name, p, 1);
		// name
		this.mobile = mobile;
		this.composition_number = composition_number;
		this.compositon = new ArrayList<Base_Artifact>(composition_number);
		this.ITEM_CASE = item_case;
		init();
	}

	public void addListComposition(ENCHANT_SIMBOL ec) {
		if (list_composition.size() >= composition_number)
			return;
		if (!list_composition.contains(ec) && sim != ec) {
			list_composition.add(ec);
		}
	}

	public void changeToGolden() {
	}

	public void check() {
		Checker.checkStatic(this);
	}

	/**
	 * 引数にそれらしいstringを与え、継承先で分岐させる<br/>
	 * 例えばおはらいの書を読んで装備品の修正値だけ分からせたい時に使う
	 * 
	 * @param s
	 */
	public void check(String s) {
	}

	@Override
	public int compareTo(Base_Artifact o) {
		if (getClassName().equals(o.getClassName()))
			return 0;
		return 1;
	}

	public boolean compositionSpace() {
		if (list_composition.size() >= composition_number) {
			return false;
		}
		return true;
	}

	public Base_Artifact createItemCheck() {
		if (this instanceof Base_Item) {
			((Base_Item) this).check();
		}
		return this;
	}

	/**
	 * これを呼ぶと完全識別されます
	 * 
	 * @param CURSE
	 *            呪いの指定
	 * @param FORGE_VALUE
	 *            強化値の指定
	 * @param SYMBOL
	 *            合成印の指定
	 * @return
	 */
	public Base_Artifact createSpellCard(boolean CURSE, int FORGE_VALUE) {
		this.check();
		curse = CURSE;
		this.forge_value = FORGE_VALUE;
		return this;
	}

	/**
	 * 
	 * @param CURSE
	 *            呪いの指定
	 * @param FORGE_VALUE
	 *            強化値の指定
	 * @param SIMBOL
	 *            合成印の指定
	 * @return
	 */
	public Base_Artifact createSpellCard(boolean CURSE, int FORGE_VALUE,
			ENCHANT_SIMBOL... SIMBOL) {
		curse = CURSE;
		this.forge_value = FORGE_VALUE;
		if (SIMBOL != null) {
			for (ENCHANT_SIMBOL ec : SIMBOL) {
				list_composition.add(ec);
			}
		}
		return this;
	}

	/**
	 * 「生成」用。完全識別される。<br/>
	 * 使用等で回数を上下させる場合はsetterを利用のこと
	 */
	public Base_Artifact createStaffRest(int i) {
		if (this instanceof Staff) {
			this.check();
			this.staff_rest = i;
		}
		return this;
	}

	public Base_Artifact defaultCurse() {
		int select = new R().nextInt(16);
		if (select == 0) {
			curse = isAbleToCurse();
		}
		return this;
	}

	public void doWhenPickuped() {
		this.endJump();
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.識別)) {
			check();
		}
	}

	public void enchant() {
		Checker.enchant(this);
	}

	public void enchantCheck(boolean no_tokidoki_check) {
		if (sim != null && !isStaticCheked()) {
			if (no_tokidoki_check || !sim.tokidoki) {
				String s = getColoredName();
				Checker.checkStatic(this);
				Message.set(s, "は", getColoredName(), "だった");
				SE.CHECK.play();
			}
		}
	}

	/**
	 * return damage;
	 * 
	 * @param b
	 * @param creature
	 * @param damage
	 * @return
	 */
	protected abstract int enchantDefence(boolean b, Base_Creature creature,
			int damage);

	public void endPlayerTurn() {
	}

	/**
	 * 
	 * @return リストに追加する／足元から消す<br/>
	 *         NO／NO => true <br/>
	 *         NO／YES => null <br/>
	 *         YES／YES => false <br/>
	 */
	public Boolean exchange() {
		return false;
	}

	public final String[] firstPageExplain() {
		if (flag_unknown) {
			String c = Color.LIGHT_GRAY.toString();
			return new String[] { c + "まだ登録されていません",
					c + "拾っただけでは登録されず、使用することで登録されます" };
		} else if (!isStaticCheked() && !flag_sample) {
			String c = Color.LIGHT_GRAY.toString();
			if (this instanceof SpellCard) {
				return new String[] { c + "未識別のため何のカードかよく分かりません",
						c + "使用することで識別されます",
						Color.MAGENTA.toString() + "装備するだけでも識別されます",
						Base_Item.CL_CURSED.toString() + "呪われていることがあるので注意して下さい" };
			} else {
				if (!(this instanceof Base_Pot) || ((Base_Pot) this).isEmpty()) {
					return new String[] { c + "未識別のためよく分かりません",
							c + "使用することで識別されます",
							c + "装備して秘められた印の効果が発揮しても識別されます" };
				}
			}
		}
		String[] ITEM_EXPLAN = new String[4];
		if (this instanceof SpellCard) {
			String[] arr = getExplan();
			if (arr.length == 1) {
				ITEM_EXPLAN[0] = arr[0];
				ITEM_EXPLAN[1] = "";
			} else {
				ITEM_EXPLAN[0] = arr[0];
				ITEM_EXPLAN[1] = arr[1];
			}
			ITEM_EXPLAN[2] = ((SpellCard) this).getSpellStatus();
			ITEM_EXPLAN[3] = Composition.composition(this);
		} else {
			String[] arr = getExplan();
			for (int i = 0; i < ITEM_EXPLAN.length; i++) {
				if (arr == null || arr.length <= i) {
					ITEM_EXPLAN[i] = "";
				} else {
					ITEM_EXPLAN[i] = arr[i];
				}
			}
		}
		return ITEM_EXPLAN;
	}

	/**
	 * フレーバーテキスト
	 * 
	 * @return
	 */
	protected String flavorExplain() {
		return "「フレーバーテキスト」";
	}

	public void freezeCountPlus(int count) {
		if (!isParmitThisItemFreeze()) {
			return;
		}
		if (isFrozen()) {
			return;
		}
		count *= EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
				ENCHANT_SIMBOL.温) ? 7 : 10;
		count *= EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
				ENCHANT_SIMBOL.氷) ? 2 : 1;
		setFreezeCount(item_freeze_count + count);
		if (isFrozen()) {
			Enchant.itemFreeze(this);

			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.冴)) {
				SE.LIGHT_ON.play();
				Scene_Action.getMe().tellRestStop();
				Player.me.setCondition(CONDITION.目薬, 0);
			}
		}
	}

	public void freezeCountReset() {
		item_freeze_count = 0;
	}

	public ItemDetail getCategory() {
		return ItemDetail.getCategory(this);
	}

	public String getClassName() {
		return "";
	}

	public String getColoredName(boolean icon) {
		return getColoredName();
	}

	protected String[] getExplan() {
		return exn;
	}

	protected String getFlavor() {
		return null;
	}

	public int getForgeValue() {
		return this.forge_value;
	}

	public int getFreeseStep() {
		int index;
		if (isFrozen()) {
			index = 2;
		} else if (isCold()) {
			index = 1;
		} else {
			index = 0;
		}
		return index;
	}

	public int getFreezeCount() {
		return item_freeze_count;
	}

	@Override
	public final Image getImage() {
		return IM.getImage(getFreeseStep());
	}

	public Image getImage(DIRECTION direction) {
		return getImage();
	}

	public final Image_Artifact getImage_Artifact() {
		return IM;
	}

	public ITEM_CASE getITEM_CASE() {
		return ITEM_CASE;
	}

	public String getLastPackage() {
		return null;
	}

	public List<ENCHANT_SIMBOL> getListComposition() {
		return list_composition;
	}

	public List<ENCHANT_SIMBOL> getListCompositionIncludeEnchant() {
		List<ENCHANT_SIMBOL> list = new ArrayList<ENCHANT_SIMBOL>();
		list.add(this.sim);
		for (ENCHANT_SIMBOL es : getListComposition()) {
			list.add(es);
		}
		return list;
	}

	public ArrayList<String> getListItemExplan(ArrayList<String> list) {
		list.add(getName());
		if (sim != null) {
			list.add("印: ".concat(sim.getSimbolName()));
		}
		String use = new String();
		for (String str : getExplan()) {
			use = use.concat(str);
		}
		list.add(use);
		return list;
	}

	public ArrayList<String> getListTrapExplan(ArrayList<String> list) {
		list.add(getName());
		String use = new String();
		for (String str : getExplan()) {
			use = use.concat(str);
		}
		list.add(use);
		return list;
	}

	public int getMerchantBuyValue() {
		int multi;
		if (this instanceof SpellCard)
			multi = 8;
		else if (this instanceof Staff) {
			multi = 4;
		} else {
			multi = 2;
		}
		int pages = getMerchantSoldValue() * multi;
		if (pages < 1)
			pages = 1;
		if (pages > 800)
			pages = 800;
		return pages;
	}

	public int getMerchantSoldValue() {
		return 1;
	}

	public String getOneLineText() {
		StringBuffer sb = new StringBuffer();

		sb.append(sim != null ? sim.getSimbolName() : " -");
		sb.append(" ");
		if (this instanceof SpellCard) {
			sb.append("攻:");
			sb.append(itemEnchantPower(STATUS.STR));
			sb.append("守:");
			sb.append(itemEnchantPower(STATUS.DEF));
			sb.append("印数:");
			sb.append(composition_number);
			// sb.append(" ");
			// sb.append(((SpellCard) this).getOneLineUseExplan());
		} else {
			for (String str : getExplan()) {
				sb.append(str);
			}
		}
		return sb.toString();
	}

	protected String getSecondExplain_ByCategory() {
		return null;
	}

	protected abstract String getSecondExplain_ByEach();

	public int getShadow() {
		return -1;
	}

	public final String getTrueName() {
		return name;
	}

	protected void init() {
		CSVLoadSupporter<String> list = CSVLoadSupporter.loadCSV(this
				.getClass().getSimpleName(), Base_Artifact.class, "\t");
		if (list != null) {
			name = list.get(0);
			flag_pass_turn = list.isTrue(1);
			exn = list.get(2).split("\\$");
			if (!flag_pass_turn) {
				String e[] = new String[exn.length + 1];
				for (int i = 0; i < exn.length; i++) {
					e[i + 1] = exn[i];
				}
				e[0] = "[ターン経過なし]";
				exn = e;
			}
		} else if (name.matches("名無しのDISC")) {
			flag_pass_turn = true;
		} else {
			System.out
					.println("[@Base_artifact.init()]NosuchArtifact Exception :"
							+ name);
		}
	}

	public void initScreenPoint() {
		if (WithinOutofScreen.isOutside(this)) {
			screen_point = ObjectPoint.getDangeonScreenPoint(mass_point);
			screen_point.translate(-MAP.TILE_SIZE * direction.X * 3,
					-MAP.TILE_SIZE * direction.Y * 3);
		}
	}

	public boolean isAbleToCurse() {
		return true;
	}

	public boolean isCold() {
		return item_freeze_count > 65;
	}

	public boolean isCurse() {
		return curse;
	}

	public boolean isCurse_And_isItViewed() {
		return isCurse();
	}

	public boolean isDemandWaiting() {
		return false;
	}

	public boolean isDevice() {
		return false;
	}

	public boolean isEnchantedAndCursed() {
		return isCurse() && Enchant.isEnchanted(this);
	}

	/**
	 * @return このアーティファクトが装備中かそうでないか
	 */
	public boolean isEnchantedNow() {
		return Enchant.isEnchanted(this);
	}

	public boolean isFateToBeDeleted() {
		return flag_hatate;
	}

	public boolean isFlagPassTurn() {
		return flag_pass_turn;
	}

	public boolean isFrozen() {
		return item_freeze_count >= 91;
	}

	/**
	 * 何が何でもゲーム上で表示されないものならtrue（overrideすること）
	 * 
	 * @return false
	 */
	public boolean isHidden() {
		return false;
	}

	public boolean isHitToItem() {
		return false;
	}

	public boolean isHolyPoint(Base_Creature source, Integer damage) {
		return false;
	}

	public boolean isMerchant() {
		return flag_merchant;
	}

	public boolean isMobile() {
		return mobile;
	}

	public boolean isNeglectiveForWall() {
		return false;
	}

	public boolean isNonFrozen() {
		return flag_no_frozen;
	}

	public boolean isNoWasteWithThrowing() {
		return false;
	}

	// private void 遠投test(Point p, int x, int y) {
	// while (true) {
	// // todo 画面外に出たときのif文でwhile文を抜ける
	// if (MapInSelect.MapMaxPoint(new Point(p.x + x, p.y + y))) {
	// break;
	// }
	// p.x += x;
	// p.y += y;
	// if (MapList.getEnemy(p) != null) {
	// Random rnd = new A();
	// int hit_percentage = rnd.nextInt(100);
	// if (hit_percentage < 20) {
	// MessageRecord.setMessageTask(new String[] { "外した", "" });
	// } else {
	// <<<<<<< .mine
	// setMassPoint(new Point(p));
	// itemHit();
	// =======
	//
	// + getMassPoint() + "p" + p);
	// if (itemHitCheck(MapList.getEnemy(p))) {
	// itemHitSetPoint(p);
	// }
	// >>>>>>> .r62
	// }
	// }
	// }
	// MessageRecord.setMessageTask(new String[] { "どこかへ飛んでいった……" });
	// if (getClass().getSuperclass().getSimpleName().matches("Arrow")) {
	// if (arrow_rest != 0)
	// return;
	// }
	// Enchant.forceToRemove(this);
	// Belongings.remove(this);
	// return;
	// }

	/**
	 * このアイテムが凍るか凍らないか判断 オーバーライドして使ってね
	 * 
	 * @return
	 */
	public boolean isParmitThisItemFreeze() {
		return true && !isFrozen();
	}

	/**
	 * 「完全識別」されたアイテムかどうか<br/>
	 * アイテム名は分かるが（呪い）や[回数]などが分からない状態は「static識別」<br/>
	 * カードと杖以外はstatic識別と等しい値を返す
	 */
	public boolean isPerfectCheked() {
		return isStaticCheked();
	}

	public final boolean isPlayerOn() {
		return getMassPoint().x == Player.me.getMassPoint().x
				&& getMassPoint().y == Player.me.getMassPoint().y;
	}

	/**
	 * 
	 * @return 賽銭箱の中身か売り物ならtrue
	 */
	public boolean isPunishment() {
		return flag_punishment;
	}

	/**
	 * 「static識別」されたアイテムかどうか<br/>
	 * falseなら正しい名称が分からない。
	 */
	public boolean isStaticCheked() {
		if (checked_static) {
			return true;
		} else {
			return checked_static = Checker.isStaticChecked(this);
		}
	}

	public boolean isUsable() {
		if (isFrozen() && BonusConductor.気分屋_氷禁止()) {
			return false;
		} else if (isCurse_And_isItViewed()) {
			return false;
		} else if (isMerchant()) {
			return false;
		} else if (this instanceof SpellCard) {
			SpellCard c = (SpellCard) this;
			return c.isAbleToUse();
		}
		return true;
	}

	public boolean isVisible() {
		return visible;
	}

	public void itemBreak() {
		if (isEnchantedNow())
			Enchant.forceToRemove(this);
		Belongings.remove(this);
	}

	public boolean itemCurseMessage() {
		if (isCurse()) {
			Message.set(new String[] { getColoredName().concat("は"),
					"呪われている為、外す事が出来ない" });
			return true;
		}
		return false;
	}

	/**
	 * 選択したアイテムのSTR,DEFを返す
	 * 
	 * @param status
	 * @return
	 */
	public abstract int itemEnchantPower(STATUS status);

	/**
	 * @param c
	 *            被
	 * @param c2
	 *            与
	 */
	public abstract void itemHit(Base_Creature c, Base_Creature c2);

	protected void itemHit_NoEspeciality(Base_Creature c, Base_Creature c2) {
		Random ran = new R();
		int damage = ran.nextInt(2) + 1;
		Message.set(getColoredName(), "は", c.getColoredName(), "に当たった！");
		if (c2 == null) {
			Damage.damage(this, null, null, null, c, damage);
		} else {
			Damage.damage(c2, null, null, c2, c, damage);
		}
	}

	/**
	 * アイテムヒット
	 * 
	 * @param ento
	 * @param c
	 * @param a
	 */
	public void itemHitCheck(boolean ento, Base_Creature c, Base_Artifact a) {
	}

	// public void itemGrowRate(int level2, int level3, int break_point) {
	// if (item_exp < level2) {
	// return;
	// } else if (item_exp < level3) {
	// if (LV == 1) {
	// LV = 2;
	// }
	// } else if (item_exp < break_point) {
	// if (LV == 2) {
	// LV = 3;
	// }
	// } else if (item_exp >= break_point) {
	// MessageRecord.setMessageTask(new String[] { getName().concat("は"),
	// "限界が来て壊れてしまった" });
	// itemBreak();
	// } else
	// return;
	// }
	/**
	 * 
	 * @param ento
	 * @param subject
	 *            与側
	 */
	public boolean itemHitCheck(boolean ento, Base_Creature subject,
			Base_Creature object) {
		if (object.itemHit(this, ento, subject, object)) {
			if (isCurse() || getListComposition().contains(ENCHANT_SIMBOL.金)) {
				itemHit_NoEspeciality(object, subject);
			} else {
				itemHit(object, subject);
			}
			return true;
		}
		return false;
	}

	/**
	 * アイテムを拾う
	 * 
	 * @return 敵のターンを飛ばす場合はtrue
	 */
	public boolean itemPickUp() {
		// firstPageExplain();
		if (Belongings.pickUpItem(this)) {
			if (this instanceof Scrool) {
				if (((Scrool) this).isOpen()) {
					((Scrool) this).setCrose();
					Message.set(getColoredName(), "を閉じた");
				}
			}
			return false;
		}
		return false;
	}

	public boolean itemSetOn() {
		if (!ItemFall.isAbleToFall_AroundPlayer()) {
			Message.set("これ以上置けない");
			return false;
		}
		ItemFall.itemFall(Player.me.getMassPoint().getLocation(), this);
		Enchant.forceToRemove(this);
		Belongings.remove(this);
		Message.set(getColoredName().concat("を置いた"));
		if (this instanceof Base_Item)
			((Base_Item) this).flag_pick_checked = true;
		return true;
	}

	/**
	 * 効果音鳴る
	 * 
	 * @param c
	 *            投げたキャラクター
	 */
	public void itemThrow(Base_Creature c) {
		SE.THROW.play();
		itemThrow(c, HowToThrow.NORMAL);
	}

	/**
	 * 効果音鳴らない
	 * 
	 * @param c
	 *            投げたキャラクター
	 */
	public void itemThrow(Base_Creature c, HowToThrow how) {
		itemThrow(c, how, false);
	}

	/**
	 * 効果音鳴らない
	 * 
	 * @param c
	 *            投げたキャラクター
	 */
	public void itemThrow(Base_Creature c, HowToThrow how, boolean booted) {
		itemThrow(c, how, booted, 10);
	}

	/**
	 * 
	 * @param c
	 *            投げたキャラクター
	 * @param booted
	 *            対象から１マス離れたところからぶっ飛びスタート
	 * @param throw_max
	 *            -1で遠投
	 */
	public void itemThrow(Base_Creature c, HowToThrow how, boolean booted,
			int throw_max) {
		itemThrow(c, how, booted, throw_max, false);
	}

	/**
	 * 
	 * @param c
	 *            投げたキャラクター
	 * @param booted
	 *            対象から１マス離れたところからぶっ飛びスタート
	 * @param throw_max
	 *            -1で遠投
	 * @param flag_magic
	 *            true その場から投擲　false cから投擲
	 */
	public void itemThrow(Base_Creature c, HowToThrow how, boolean booted,
			int throw_max, boolean flag_magic) {
		boolean ento = false;
		if (how == HowToThrow.NORMAL || how == HowToThrow.SLOW
				|| how == HowToThrow.BREAK) {
			if (this instanceof Base_Device) {
				ento = false;
			} else {
				ento = c instanceof Player
						&& EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
								ENCHANT_SIMBOL.遠投);
				ento = ento || this instanceof ミニ八卦炉;
				ento = ento || throw_max == -1;
			}
		}
		itemThrow(c, how, booted, throw_max, flag_magic, ento);
	}

	/**
	 * 
	 * @param c
	 *            投げたキャラクター
	 * @param booted
	 *            対象から１マス離れたところからぶっ飛びスタート
	 * @param throw_max
	 *            -1で遠投
	 * @param flag_magic
	 *            true その場から投擲　false cから投擲
	 */
	private void itemThrow(Base_Creature c, HowToThrow how, boolean booted,
			int throw_max, boolean flag_magic, boolean ento) {
		Enchant.forceToRemove(this);
		Belongings.remove(this);
		// direction = c instanceof Player ? direction : c.direction;
		if ((c instanceof Player) && BonusConductor.ひねくれ者_受け()) {
			Point p = c.getMassPoint().getLocation();
			Mass m = MassCreater.getMass(p);
			DIRECTION d = c.direction;
			while (m.WALKABLE) {
				p.translate(d.X, d.Y);
				m = MassCreater.getMass(p);
			}
			setMassPoint(p);
			direction = c.direction.getReverse();
		} else {
			if (!flag_magic) {
				direction = c.direction;
				if (booted) {
					setMassPoint(c.getMassPoint().x + c.direction.X,
							c.getMassPoint().y + c.direction.Y);
				} else {
					setMassPoint(c.getMassPoint().getLocation());
				}
			} else {
				setMassPoint(this.getMassPoint().getLocation());
			}
		}
		screen_point.x += direction.X * MAP.TILE_SIZE;
		screen_point.y += direction.Y * MAP.TILE_SIZE;
		ThrowingItem.launch(this, ento, throw_max);
		// MapList.addArtifact(this);
		TaskOnMapObject.setThrow(new ThrowingItem(this, c, ento, how));

	}

	/**
	 * 効果音鳴らない
	 * 
	 * @param c
	 *            投げたキャラクター
	 * @param throw_max
	 *            -1で遠投
	 */
	public void itemThrow(Base_Creature c, HowToThrow how, int throw_max) {
		itemThrow(c, how, false, throw_max);
	}

	public abstract boolean itemUse();

	public abstract void itemUseThis();

	public void itemUseThis_fromMenu(final KeyHolder KH, Base_View view) {
		itemUseThis();
		KH.setKeyAccepter(Scene_Action.getMe());
	}

	public void remove() {
		if (isEnchantedNow()) {
			Enchant.forceToRemove(this);
		}
		Belongings.getListItems().remove(this);
		MapList.removeArtifact(this);
	}

	/**
	 * ２ページ目の説明文表示
	 * 
	 * @return
	 */
	public final ArrayList<String> secondPageExplain() {
		int length = 22;
		ArrayList<String> list = new ArrayList<String>();
		String detail = (isStaticCheked()) ? getSecondExplain_ByEach()
				: "未識別のアイテムだ";
		String arr[] = { getSecondExplain_ByCategory(), detail };
		int index = 0;
		for (String str : arr) {
			index++;
			if (str != null) {
				String caption;
				if (index == 1) {
					caption = "【分類】";
				} else {
					caption = "【詳細】";
				}
				if (!str.contains("@")) {
					list.add(caption);
				}
				for (String string : str.split("@")) {
					int times = string.length() / length;
					for (int i = 0; i < times; i++) {
						list.add(string.substring(length * i, length * (i + 1)));
					}
					list.add(string.substring(length * times));
				}
				list.add("");
			}
		}
		return list;
	}

	public void setCurse(boolean curse) {
		if (curse) {
			if (isAbleToCurse()) {
				this.curse = curse;
			}
		} else {
			this.curse = curse;
		}
		印邪.setInfomation();
	}

	public void setFateToBeDeleted() {
		flag_hatate = true;
	}

	public void setFlagNoFrozen(boolean flag) {
		flag_no_frozen = flag;
	}

	public void setForgeValue(int value) {
		forge_value += value;
		if (forge_value > 99) {
			this.forge_value = 99;
		} else if (forge_value < -99) {
			this.forge_value = -99;
		}
	}

	public void setFreezeCount(int count) {
		item_freeze_count = count;
		if (item_freeze_count < 0)
			item_freeze_count = 0;
		else if (item_freeze_count > 100)
			item_freeze_count = 100;
	}

	/**
	 * 呪いを解く スペルカードなら強化値を０にする
	 * 
	 * @return
	 */
	public Base_Artifact setItemNormalCondition() {
		setCurse(false);
		if (this instanceof SpellCard) {
			forge_value = 0;
		}
		return this;
	}

	/**
	 * 主人公がものを落とす時に呼ぶ
	 */
	@Override
	public void setMassPoint_ParabolaJump_NoAttack(Point p) {
		super.setMassPoint_ParabolaJump_NoAttack(p);
		// jumped_away_y_speed /= 2;
		upDate();
		upDate();
	}

	public void setMassPoint_ParabolaJump_NoAttack(Point p, Task task) {
		setMassPoint_ParabolaJumpAttack(p, task);
		mass_point = p;
		flag_jump_no_attack = true;
		upDate();
		upDate();
	}

	public void setMassPoint_ParabolaJump_NoAttack_PotBreak(Point p) {
		setMassPoint_ParabolaJump_NoAttack(p);
	}

	public void setMerchant(boolean b) {
		flag_merchant = b;
	}

	public void setMoveAnimating(boolean b) {
		flag_move_animating = b;
	}

	public void setPunishment(boolean b) {
		flag_punishment = b;
	}

	public void setSampleItem(boolean unknown) {
		flag_sample = true;
		flag_unknown = unknown;
		setCurse(false);
	}

	public void setVisible(boolean b) {
		visible = b;
	}

	public boolean staticCheck() {
		if (!isStaticCheked()) {
			SE.CHECK.play();
			String str = this.getColoredName();
			Checker.checkStatic(this);
			Message.set(str, "は", this.getName(), "だった");
			return true;
		}
		return false;
	}

	public String test() {
		return getSecondExplain_ByEach();
	}

	/**
	 * 
	 * @return 敵のターンを飛ばす場合true。例えば階段を下ったときなど。
	 */
	public abstract boolean walkOnAction();

	/**
	 * ダッシュして乗ったとき
	 * 
	 * @param b
	 *            　意味なし
	 * @return
	 */
	public boolean walkOnAction(boolean b) {
		return walkOnAction();
	}

	/**
	 * 
	 * @return マップに登録するならtrue
	 */
	public boolean waterAction() {
		return true;
	}

}
