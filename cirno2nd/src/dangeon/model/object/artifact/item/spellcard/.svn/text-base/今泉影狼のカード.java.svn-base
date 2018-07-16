package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import main.util.FrameShaker;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.今泉影狼;

public class 今泉影狼のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "今泉影狼のカード";
	public static final String item_name_sound = "いまいすみかけろうのかーと";
	private static final int composition = 5;
	private static final int item_str = 8;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵を影縫い状態にする程度の能力" };

	public 今泉影狼のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.狼;
	}

	@Override
	public String getCharacterShortName() {
		return "今泉影狼";
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
		return "攻撃すると影縫い状態にするぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("わおーん！");
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
		return 今泉影狼.class;
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
		FrameShaker.doneSoftly();
		Message.set("「私はおたけびをあげた！」");
		SE.WAOON.play();
		SE.STATUS_SHADOW.play();
		for (Base_Enemy em : MapList.getListEnemy()) {
			em.setCondition(CONDITION.影縫い, 0);
		}
		return true;
	}

}
