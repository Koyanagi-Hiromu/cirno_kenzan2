package dangeon.model.object.artifact.item;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.grass.超不幸の草;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.R;
import dangeon.view.detail.View_Sider;
import dangeon.view.util.StringFilter;
import main.res.Image_Artifact;
import main.res.Image_Player;
import main.res.SE;

public abstract class Base_Item extends Base_Artifact {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static HashMap<Class<? extends Base_Item>, Boolean> map = new HashMap<Class<? extends Base_Item>, Boolean>();

	protected int staff_use_count = 0;
	protected final int base_merchant_value;

	public static final String CL_PUNISH = new Color(225, 225, 255).toString(),
			CL_NOT_USABLE = new Color(190, 230, 220).toString(),
			CL_CUSTOMIZED = new Color(255, 255, 180).toString(),
			CL_NAMED = new Color(0, 225, 70).toString(), CL_CURSED = new Color(
					200, 50, 150).toString(), CL_NORMAL = new Color(255, 195,
					175).toString(), CL_LIMITTED = new Color(255, 255, 255)
					.toString();

	/**
	 * 色をつける
	 */
	public boolean flag_gousei = false;

	/**
	 * SpellCardにのみ使用される変数
	 */
	protected boolean flag_open_cursed = false;

	/**
	 * SpellCardと杖にのみ使用される変数
	 */
	protected boolean flag_open_numbers = false;

	protected Base_Artifact A;

	// private void mapInWhiteShowItem() {
	// if (checkedCheck(getClass())) {
	// if (this instanceof Grass || this instanceof Scrool
	// || this instanceof Food || this instanceof Arrow) {
	// checked = true;
	// }
	// }
	// }

	public final int MAX_STAFF_REST = 99;

	public boolean flag_pick_checked;

	protected Base_Item(Point p, String item_name, int composition_number,
			ITEM_CASE item_case) {
		super(p, item_name, composition_number == 0 ? 1 : composition_number,
				item_case, true);
		visible = true;
		base_merchant_value = ItemTable.getMerchantValue(this);
	}

	protected void action() {
	}

	/**
	 * アイテムテーブルによる自動生成時および「使用」時に回数を変更する<br/>
	 * 慧音の問題などで回数を指定して生成する場合はcreaterStaffRestを使用すること
	 * 
	 * @param staffRest
	 * @return
	 */
	public final Base_Item addStaffRest(int delt) {
		if (staff_rest + delt >= MAX_STAFF_REST) {
			staff_rest = MAX_STAFF_REST;
		} else {
			this.staff_rest += delt;
		}

		return this;
	}

	protected void before_walkOnAction() {
		flag_pick_checked = true;
	}

	protected boolean checkedCheck(Class<? extends Base_Item> c) {
		if (!map.containsKey(c)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void doWhenPickuped() {
		this.flag_pick_checked = false;
		super.doWhenPickuped();
	}

	public void firstMsgAtUsingThis() {
		Message.set(getColoredName(), "を使った");
	}

	@Override
	public final String getColoredName() {
		return getColoredName(true);
	}

	@Override
	public String getColoredName(boolean icon) {
		if (flag_sample) {
			if (flag_unknown) {
				// if (Config.isTest()) {
				// return Color.GRAY.toString() + getTrueName();
				// } else {
				return Color.YELLOW.toString().concat("？？？？");
				// }
			} else
				return getTrueName();
		}
		StringBuilder sb = new StringBuilder();
		if (icon) {
			sb.append(StringFilter.ITEM_WARD);
			sb.append(Integer.toString(Image_Artifact.getIdentifier(this),
					Character.MAX_RADIX));
			sb.append(getFreeseStep());
		}
		if (isPunishment()) {
			sb.append(CL_PUNISH);
		} else if (isForgedMax()) {
			sb.append(CL_LIMITTED);
		} else if (flag_gousei || !getListComposition().isEmpty()) {
			sb.append(CL_CUSTOMIZED);
		} else if (!isPerfectCheked()) {
			if (isCurse() && !(this instanceof SpellCard)) {
				sb.append(CL_CURSED);
			} else {
				sb.append(Color.YELLOW.toString());
			}
		} else if (Enchant.isEnchanted(this)) {
			sb.append(Enchant.getEnchantedPlace(this).getColor().toString());
		} else if (isCurse()) {
			sb.append(CL_CURSED);
		} else if (this instanceof SpellCard
				&& !((SpellCard) this).isAbleToUse()) {
			sb.append(CL_NOT_USABLE);
		} else {
			sb.append(CL_NORMAL);
		}
		sb = getName(sb);
		sb.append(Color.WHITE);
		// sb.append(new Color(200, 255, 200));
		// sb.append(Color.WHITE.toString());
		return sb.toString();
	}

	@Override
	public int getMerchantSoldValue() {
		int alpha;
		if (isStaticCheked()) {
			alpha = 0;
			alpha += getListComposition().size();
			// for (ENCHANT_SIMBOL e : getListComposition()) {
			// alpha++;
			// }
			if (isCold())
				alpha++;
			if (isFrozen())
				alpha++;
			if (isCurse_And_isItViewed())
				alpha--;
		} else {
			return 1;
		}
		int beta;
		if (isPerfectCheked()) {
			beta = 0;
			if (this instanceof Staff) {
				beta = staff_rest;
			} else if (this instanceof SpellCard) {
				beta = ((SpellCard) this).getBomCount() + getForgeValue();
			}
		} else {
			beta = 0;
		}
		boolean flag = (this instanceof Base_Pot)
				|| (this instanceof SpellCard) || (this instanceof Scrool);
		float gumma = flag ? 1.5f : 1;
		int pages = Math.round((base_merchant_value + alpha) * gumma
				* (1 + 0.1f * beta));
		if (pages > 100)
			pages = 100;
		return pages;
	}

	@Override
	public String getName() {
		if (isStaticCheked()) {
			return name;
		} else {
			return Checker.getUnCheckedName(this);
		}
	}

	public StringBuilder getName(StringBuilder sb) {
		sb.append(getName());
		return sb;
	}

	@Override
	public int getShadow() {
		return 6;
	}

	public final int getStaffRest() {
		return staff_rest;
	}

	protected boolean isForgedMax() {
		return staff_rest >= MAX_STAFF_REST;
	}

	protected boolean isItemUseThisAvailable() {
		boolean flag_mouse = isUsingMouse();
		if (Player.me.getConditionList().contains(CONDITION.封印)
				|| Player.me.getConditionList().contains(CONDITION.ええんじゃないか)) {
			if (flag_mouse) {
				SE.STATUS_SEAL.play();
				Message.set("口が使えない");
				return false;
			}
		}
		if (isCurse()) {
			flag_open_cursed = true;
			Message.set("呪われているため使用できない！");
			return false;
		}
		return true;
	}

	public boolean isPickCheked() {
		return flag_pick_checked && !flag_punishment && !flag_merchant;
	}

	public boolean isUsingMouse() {
		return this instanceof Food
		// || this instanceof Scrool
				|| this instanceof Base_Grass || this instanceof SpellCard;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		Random ran = new R();
		int damage = ran.nextInt(2) + 1;
		Message.set(new String[] { c.getColoredName().concat("に"),
				getColoredName().concat("をぶつけた") });
		String deathMsg = getColoredName() + "がぶつかって倒れた";
		ThrowingItem ti = TaskOnMapObject.getThrow();
		if (ti != null && ti.A == this && ti.isReflected()) {
			deathMsg = "跳ね返った" + deathMsg;
		} else {
			deathMsg = "飛んできた" + deathMsg;
		}
		Damage.damage(this, null, deathMsg, c2, c, damage);
	}

	@Override
	public final void itemUseThis() {
		if (isItemUseThisAvailable()) {
			firstMsgAtUsingThis();
			if (!(this instanceof Staff) && !(this instanceof Base_Pot)) {
				if (!isStaticCheked()) {
					String name = getColoredName();
					check();
					Message.set(name, "は", getColoredName(), "だった");
					if (ItemTable.getRank(this) >= 3)
						View_Sider.setInformation("出現度：", ItemTable.getRank_String(this));
					
					if (this instanceof Ring) {
						if (ItemTable.getRank(this) == 5) {
							Medal.未識別リボンを使用したらレアものだった.addCount();

						}
					} else if (超不幸の草.class.isInstance(this)) {
						Medal.未識別の草を飲んだら超不幸の種だった.addCount();
					}
				}
			}
			Config.saveItemData(this);
			Image_Player.set(this);
		}
	}

	@Override
	public void itemUseThis_fromMenu(final KeyHolder KH, Base_View view) {
		if (this instanceof SelectItem)
			selectItem(view);
		else
			super.itemUseThis_fromMenu(KH, view);
	}

	protected void selectItem(final Base_View view) {
		final SelectItem ME = (SelectItem) this;
		ArrayList<Base_Artifact> list = null;
		if (isStaticCheked()) {
			list = Belongings.getDeepCopy(Belongings
					.getListItems_includingFoot());
			list = ME.getEscape(list);
		}
		if (list == null)
			list = new ArrayList<Base_Artifact>();
		list.add(this);
		final Base_Item me = this;
		new Item_List(new Book_Item() {
			@Override
			public void work(Base_Artifact a) {
				A = a;
				itemUseThis();
			}
		}, list, true) {
			@Override
			protected void action_cancel() {
				super.action_cancel();
				Checker.select(me);
			}
		};
	}

	public void setFlagPickChecked(boolean b) {
		flag_pick_checked = b;
	}

	@Override
	public final boolean walkOnAction() {
		if (flag_merchant) {
			check();
			TaskOnMapObject.setTaskStairs(this);
			return false;
		} else {
			before_walkOnAction();
			return itemPickUp();
		}
	}

	/**
	 * ダッシュして乗ったとき
	 * 
	 * @param b
	 *            　意味なし
	 * @return
	 */
	@Override
	public final boolean walkOnAction(boolean b) {
		if (flag_merchant) {
			check();
		} else {
			before_walkOnAction();
		}
		Message.set(getColoredName(), "の上に乗った");
		return false;
	}

}
