package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.リリーホワイト;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class リリーホワイトのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "リリーホワイトのカード";
	public static final String item_name_sound = "りりーほわいとのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 3;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内のキャラクターの状態を回復する程度の能力" };

	final int heal = 50;

	public リリーホワイトのカード(Point p) {
		super(p, item_name, 1, composition);
	}

	@Override
	public String getCharacterShortName() {
		return "リリー";
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
		return new String("春ですよー！");
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
		return リリーホワイト.class;
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
		for (Base_Creature c : MapInSelect
				.getListRoomOrRoadInCreature(Player.me)) {
			c.heal(Player.me, heal);
			CONDITION.conditionAllClear(c);
		}
		Player.me.chengeHP(null, null, heal);
		CONDITION.conditionAllClear(Player.me);
		return true;
	}

}
