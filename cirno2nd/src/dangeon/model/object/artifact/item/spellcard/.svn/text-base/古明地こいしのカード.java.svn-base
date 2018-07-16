package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.古明地こいし;
import dangeon.model.object.creature.player.Player;

public class 古明地こいしのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "古明地こいしのカード";
	public static final String item_name_sound = "こめいしこいしのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "１ターン透明になる程度の能力" };

	public 古明地こいしのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.無;
	}

	@Override
	public String getCharacterShortName() {
		return "こいし";
	}

	@Override
	public String getDoter() {
		return "ポポタ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1599527";
	}

	@Override
	String getExplanToEnchant() {
		return "時々３ターン透明になるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("");
	}

	@Override
	public String getIllustlator() {
		return "ぬぐぬ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=32724";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String[] getSecondExplan() {
		return new String[] { "", "" };
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 古明地こいし.class;
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
		SE.STATUS_GOOD.play();
		Player.me.setCondition(CONDITION.透明, 5);
		return true;
	}

}
