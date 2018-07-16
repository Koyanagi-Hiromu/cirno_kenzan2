package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ゆっくり霊夢;
import dangeon.model.object.creature.player.Player;

public class ゆっくりのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ゆっくりのカード";
	public static final String item_name_sound = "ゆっくりのかーと";
	private static final int composition = 6;
	private static final int item_str = 1;
	private static final int item_def = 1;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "全てのキャラクターを鈍足にする程度の能力" };

	public ゆっくりのカード(Point p) {
		super(p, item_name, 1, composition);
	}

	@Override
	public String getCharacterShortName() {
		return "ゆっくり";
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
		return "";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ゆっくりしていってね！");
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
		return ゆっくり霊夢.class;
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
		Message.set("ゆっくりしていってね！");
		SE.CREASY_WAVE.play();
		for (Base_Creature c : MapList.getListCreature()) {
			c.setCondition(CONDITION.鈍足, 0);
		}
		Player.me.setCondition(CONDITION.鈍足, 0);
		return true;
	}

}
