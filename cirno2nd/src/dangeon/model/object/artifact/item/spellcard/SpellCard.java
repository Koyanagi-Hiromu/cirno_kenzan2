package dangeon.model.object.artifact.item.spellcard;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.R;
import dangeon.util.STAGE;
import main.res.BGM;
import main.res.Image_Artifact;
import main.res.Image_LargeCharacter;
import main.res.SE;
import main.util.CSVLoadSupporter;
import main.util.Show;
import main.util.半角全角コンバーター;

public abstract class SpellCard extends Base_Item {
	private String fu_name, range;
	public int stand_lv;

	public static final int BOMB_MAX = 4;

	private int BOMB;

	public final int BOMB_USE;

	public final LinkedHashSet<BGM> BGM_LIST = new LinkedHashSet<BGM>();

	protected ArrayList<STAGE> list_stage;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public final Image_LargeCharacter IMLC;

	/**
	 * 「完全識別」されたアイテムかどうか<br/>
	 * アイテム名は分かるが詳細（呪いと強さ）が分からない状態は「static識別」
	 */
	private boolean checked_perfect = false;

	protected SpellCard(Point p, String item_name, int level,
			int composition_number) {
		super(p, item_name, composition_number, ITEM_CASE.SPELLCARD);
		// setForgeValue(3);
		BOMB_USE = getPassingOfSpellCard() ? 2 : 1;
		addBomb(BOMB_USE);
		defaultForge();
		// if (new R().is(1)) {
		// stand_lv = new R().nextInt(3) + 1;
		// forge_value = stand_lv;
		// } else {
		// stand_lv = 0;
		// }
		IM = Image_Artifact.CARD;
		IMLC = Image_LargeCharacter.get(this);
		BGM_LIST.add(BGM.get(this.getClass()));
		list_stage = new ArrayList<STAGE>();
		try {
			Constructor<? extends Base_Enemy> con = getStand().getConstructor(
					Point.class, int.class);
			Base_Enemy obj = con.newInstance(p, level);
			for (STAGE s : obj.getCategory()) {
				list_stage.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Show.showErrorMessageDialog(e);
		}
	}

	protected SpellCard(Point p, String item_name, int level,
			int composition_number, Class<? extends Base_Enemy> c) {
		this(p, item_name, level, composition_number);
	}

	// 以下消去予定
	@Override
	protected void action() {
	}

	public void addBomb(int delt) {
		BOMB += delt;
		if (BOMB >= BOMB_MAX) {
			BOMB = BOMB_MAX;
		} else if (BOMB < 0) {
			BOMB = 0;
		}
	}

	public void addStandLv() {
		stand_lv = (stand_lv + 1) % 4;
	}

	@Override
	public void check() {
		checked_perfect = true;
		flag_open_cursed = true;
		ENCHANT_SIMBOL e = ENCHANT_SIMBOL.謎;
		if (sim == e || getListComposition().contains(e))
			EnchantSpecial.setInfoBake(0);
		e = ENCHANT_SIMBOL.狸;
		if (sim == e || getListComposition().contains(e))
			EnchantSpecial.setInfoBake(1);
		super.check();
	}

	@Override
	public void check(String s) {
		if (s.matches("forge")) {
			if (isStaticCheked())
				check();
			flag_open_numbers = true;
		}
	}

	private void defaultForge() {
		Random ran = new R();

		int value = ran.nextInt(64) + 1;
		switch (value) {
		case 1:
			forge_value = 3;
			break;
		case 2:
		case 3:
			forge_value = 2;
			break;
		case 4:
		case 5:
		case 6:
		case 7:
			forge_value = 1;
			break;
		default:
			break;
		}
	}

	protected int enchantAttack(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	@Override
	public void enchantCheck(boolean tokidoki_check) {
	}

	@Override
	protected int enchantDefence(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	public int getBombs() {
		return BOMB;
	}

	public int getBomCount() {
		int i = getForgeValue() + BOMB;
		i /= BOMB_USE;
		if (i < 0)
			i = 0;
		return i;
	}

	public String getCharacterName() {
		String s = getName();
		return s.substring(0, s.length() - 4);
	}

	public abstract String getCharacterShortName();

	@Override
	public String getClassName() {
		return "カード：";
	}

	/**
	 * FIXME
	 * 
	 * @return
	 */
	public String getComboMsg() {
		return "-共鳴効果-FIXME";
	}

	public abstract String getDoter();

	public abstract String getDoterURL();

	public String getDoubleExplainToUse() {
		String s = "";
		for (String str : getExplanToUse()) {
			return str;
		}
		return s;
	}

	@Override
	protected String[] getExplan() {
		StringBuilder sb = new StringBuilder();
		sb.append(Color.CYAN);
		sb.append("【使用】");
		sb.append(Color.LIGHT_GRAY);
		sb.append("ボム:");
		sb.append(BOMB_USE);
		sb.append(" ");
		sb.append(Color.PINK);
		sb.append("対象:");
		sb.append(range.isEmpty() ? "？" : range);
		sb.append(" ");
		if (!flag_pass_turn) {
			sb.append(Color.MAGENTA);
			sb.append("ターン経過:なし");
		}
		// sb.append(Enchant.CL_ANY1);
		// sb.append("ターン経過:");
		// sb.append(Enchant.CL_ANY3);
		// sb.append("あり ");
		// sb.append(Enchant.CL_ANY2);
		// sb.append("対象:");
		// sb.append(Enchant.CL_ANY3);
		// sb.append("周囲８マス");
		sb.append(Color.WHITE);
		String[] arr = new String[2];
		arr[0] = sb.toString();
		arr[1] = exn[0];
		return arr;
	}

	public String getExplan__() {
		return getExplanToUse()[0];
	}

	abstract String getExplanToEnchant();

	abstract String[] getExplanToUse();

	@Override
	protected abstract String getFlavor();

	public String getFuName() {
		return fu_name;
	}

	public abstract String getIllustlator();

	public abstract String getIllustlatorURL();

	@Override
	public String getLastPackage() {
		return "spellcard";
	}

	@Override
	public ArrayList<String> getListItemExplan(ArrayList<String> list) {
		list.add(getName());
		if (sim != null) {
			list.add("印: ".concat(sim.getSimbolName()));
		}
		list.add(getSpellStatusQuestion());
		String use = new String();
		for (String str : getExplanToUse()) {
			use = use.concat(str);
		}
		list.add(use);
		if (getExplanToEnchant().length() != 0) {
			list.add(getExplanToEnchant());
		}
		return list;
	}

	public final List<STAGE> getListStage() {
		return list_stage;
	}

	public int getMaxBombs() {
		return BOMB_MAX;
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		sb.append(getName());
		if (stand_lv != 0) {
			sb.append("☆");
		}
		if (isPerfectCheked()) {
			if (forge_value != 0) {
				if (forge_value > 0) {
					sb.append("+");
				}
				sb.append(forge_value);
			}
		} else {
			if (flag_open_numbers) {
				if (forge_value >= 0)
					sb.append("+");
				sb.append(forge_value);
			} else {
				sb.append("+?");
			}
		}
		return sb;
	}

	public String getOneLineUseExplan() {
		StringBuffer sb = new StringBuffer();
		for (String str : getExplanToUse()) {
			sb.append(str);
		}
		return sb.toString();
	}

	abstract boolean getPassingOfSpellCard();

	@Override
	protected String getSecondExplain_ByCategory() {
		return "その人物のちからを借りることが出来る不思議なアイテム。使用すると修正値が下がりその結果マイナスになると消えてしまう。";
	}

	@Override
	protected String getSecondExplain_ByEach() {
		StringBuilder sb = new StringBuilder();
		sb.append("【対応DISC】");
		sb.append("@");
		if (getListStage().isEmpty()) {
			sb.append("（なし）");
			sb.append("@");
		} else {
			for (STAGE s : getListStage()) {
				sb.append("◆");
				sb.append(s);
				sb.append("　");
			}
		}
		sb.append("@");
		sb.append("@");
		sb.append("【立ち絵師／ドッター】");
		sb.append("@");
		sb.append("◆");
		sb.append(getIllustlator());
		sb.append("　");
		sb.append("◆");
		sb.append(getDoter());
		sb.append("@");
		sb.append("@");
		String str = getSecondExplain_ByEach_Annotation();
		if (str != null) {
			sb.append("【一言注釈】");
			sb.append("@");
			sb.append(str);
			sb.append("@");
		}
		return sb.toString();
	}

	protected String getSecondExplain_ByEach_Annotation() {
		return null;
	}

	protected abstract String[] getSecondExplan();

	@Override
	public int getShadow() {
		return 4;
	}

	public String getSpellStatus() {
		StringBuilder sb = new StringBuilder();
		sb.append(Enchant.CL_ANY3);
		sb.append("【装備】");
		sb.append(Enchant.CL_ATK);
		sb.append("攻撃力+");
		sb.append(Color.WHITE);
		if (itemEnchantPower(STATUS.STR) < 10) {
			sb.append(" ");
		}
		sb.append(itemEnchantPower(STATUS.STR));
		sb.append(" ");
		sb.append(Enchant.CL_DEF);
		sb.append("防御力+");
		sb.append(Color.WHITE);
		if (itemEnchantPower(STATUS.DEF) < 10) {
			sb.append(" ");
		}
		sb.append(itemEnchantPower(STATUS.DEF));
		sb.append(Color.PINK);
		sb.append("　器:");
		sb.append(Enchant.CL_ANY3);
		sb.append(半角全角コンバーター.toMaruMoji(composition_number));
		sb.append(Color.GRAY);
		sb.append("　出現度:");
		sb.append(Enchant.CL_ANY3.darker());
		sb.append(ItemTable.getRank_String(this));
		return sb.toString();
	}

	public String getSpellStatusQuestion() {
		return getSpellStatus();
	}

	public abstract Class<? extends Base_Enemy> getStand();

	@Override
	protected void init() {
		CSVLoadSupporter<String> list = CSVLoadSupporter.loadCSV(this
				.getClass().getSimpleName(), Base_Artifact.class, "\t");
		if (list != null) {
			name = list.get();
			flag_pass_turn = list.isTrue();
			exn = list.get().split("\\$");
			fu_name = list.get();
			range = list.get();
		} else {
			System.out
					.println("[@Base_artifact.init()]NosuchArtifact Exception :"
							+ name);
		}
	}

	public boolean isAbleToUse() {
		getBombs();
		int i = getBombs() - BOMB_USE;
		if (i < 0 && getForgeValue() < -i) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCurse_And_isItViewed() {
		return flag_open_cursed && isCurse();
	}

	@Override
	protected boolean isForgedMax() {
		return forge_value >= 99 && getBombs() >= getMaxBombs();
	}

	public boolean isPassing() {
		return getPassingOfSpellCard();
	}

	@Override
	public boolean isPerfectCheked() {
		return checked_perfect;
	}

	public boolean isUdongeSelective() {
		return true;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public boolean itemUse() {
		if (this.isUsingMouse() && BonusConductor.守矢神_口封じ()) {
			SE.MIRACLE_ONIGIRI.play();
			Player.me.setCondition(CONDITION.おにぎり, 5);
		}
		if (BOMB >= BOMB_USE) {
			if (!spellUse()) {
				return true;
			} else {
				System.out.println(BOMB + "か" + BOMB_USE);
				addBomb(-BOMB_USE);
				System.out.println(BOMB + "と" + BOMB_USE);
			}
		} else {
			if (getForgeValue() + BOMB - BOMB_USE >= 0) {
				if (!spellUse()) {
					return true;
				}
				setForgeValue(-BOMB_USE + BOMB);
				BOMB = 0;
			} else {
				Message.set("修正値が足りなくて使用できない");
				return false;
			}
		}
		return true;
	}

	@Override
	public void setCurse(boolean curse) {
		if (curse) {
			flag_open_cursed = true;
		}
		super.setCurse(curse);
	}

	/**
	 * 正しく使用できてパワーを消費したらtrue
	 * 
	 * @return
	 */
	protected abstract boolean spellUse();

	private void spellUsed() {
		if (isEnchantedNow())
			Enchant.forceToRemove(this);
		Belongings.remove(this);
	}

	public void startBGM() {
		BGM.play(this);
	}

	@Override
	public boolean waterAction() {
		if (!getListComposition().contains(ENCHANT_SIMBOL.金) && !isFrozen()) {
			Message.set(getColoredName(), "は濡れて修正値が下がった");
			setForgeValue(-1);
		}
		return true;
	}
}
