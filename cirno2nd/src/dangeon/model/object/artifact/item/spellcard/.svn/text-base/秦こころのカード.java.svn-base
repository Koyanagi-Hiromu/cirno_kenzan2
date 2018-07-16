package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.秦こころ;
import dangeon.view.anime.DecurseEffect;
import dangeon.view.detail.MainMap;

public class 秦こころのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "秦こころのカード";
	public static final String item_name_sound = "はたこころのかーと";
	private static final int composition = 5;
	private static final int item_str = 8;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "フロア内の敵の状態異常を解除する程度の能力" };

	public 秦こころのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.面;
	}

	@Override
	public String getCharacterShortName() {
		return "秦こころ";
	}

	@Override
	public String getDoter() {
		return "";
	}

	@Override
	public String getDoterURL() {
		return "";
	}

	@Override
	String getExplanToEnchant() {
		return "攻撃した敵の状態異常を解除するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ええんじゃないか！");
	}

	@Override
	public String getIllustlator() {
		return "";
	}

	@Override
	public String getIllustlatorURL() {
		return "";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "敵を影縫いにするぞ！";
	}

	@Override
	protected String[] getSecondExplan() {
		return new String[] { "", "" };
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 秦こころ.class;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		if (status == STATUS.STR) {
			return item_str;
		} else {
			return item_def;
		}
	}

	@Override
	protected boolean spellUse() {
		SE.GOGOGO.play();
		Message.set("「・・・・・・」");
		for (Base_Creature _c : MapList.getListEnemy()) {
			MainMap.addEffect(new DecurseEffect(
					_c.getMassPoint().getLocation(), null), false);
			CONDITION.conditionAllClear(_c);
		}
		return true;
	}

}
