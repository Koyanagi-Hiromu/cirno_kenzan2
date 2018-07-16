package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.黒谷ヤマメ;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 黒谷ヤマメのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "黒谷ヤマメのカード";
	public static final String item_name_sound = "くろたにやまめのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "８方向に蜘蛛の糸を飛ばして鈍足にする程度の能力" };

	public 黒谷ヤマメのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.病;
	}

	@Override
	public String getCharacterShortName() {
		return "ヤマメ";
	}

	@Override
	public String getDoter() {
		return "まるせん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1733063";
	}

	@Override
	String getExplanToEnchant() {
		return "状態異常が早く直るぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ぷっしゃー！");
	}

	@Override
	public String getIllustlator() {
		return "ヒスイ";
	}

	@Override
	public String getIllustlatorURL() {
		return null;
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
		return 黒谷ヤマメ.class;
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
		for (Base_Creature c : MapInSelect.getListStraightHitCreature(
				Player.me.getDirection(), Player.me.getMassPoint(), 0)) {
			SE.STATUS_SLOW.play();
			c.setCondition(CONDITION.鈍足, 0);
		}
		return true;
	}

}
