package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.鈴仙・優曇華院・イナバ;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class イナバのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "鈴仙・優曇華院・イナバのカード";
	public static final String item_name_sound = "れいせんうとんけいんいなはのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 8;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "直線上の敵を混乱させる程度の能力" };

	public イナバのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.瞳;
	}

	@Override
	public String getCharacterShortName() {
		return "イナバ";
	}

	@Override
	public String getDoter() {
		return "めそうさん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=293174";
	}

	@Override
	String getExplanToEnchant() {
		return "催眠術にかからなくなるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("イナバェ");
	}

	@Override
	public String getIllustlator() {
		return "電気ウサギ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=33409";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 鈴仙・優曇華院・イナバ.class;
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
		SE.CREASY_WAVE.play();
		for (Base_Creature c : MapInSelect.getListStraightHitCreature(
				Player.me.getDirection(), Player.me.getMassPoint(), 0)) {
			SE.STATUS_PIYOPIYO.play();
			c.setCondition(CONDITION.混乱, 0);
		}
		return true;
	}

}
