package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.紅美鈴;
import dangeon.model.object.creature.player.Player;

public class 紅美鈴のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "紅美鈴のカード";
	public static final String item_name_sound = "ほんめいりんのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 8;
	private static final boolean passing_of_spell = false;
	private final ENCHANT_SIMBOL SIM = ENCHANT_SIMBOL.龍;

	private static final String[] EXPLAN = new String[] { "ＨＰが150回復する程度の能力" };

	int heal = 150;

	public 紅美鈴のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = SIM;
	}

	@Override
	public String getCharacterShortName() {
		return "美鈴";
	}

	@Override
	public String getDoter() {
		return "aozane";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=86531";
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
		return "驚くほど門番";
	}

	@Override
	public String getIllustlator() {
		return "かまた";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2807601";
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
		return 紅美鈴.class;
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
		Player.me.chengeHP(null, null, heal);
		// Message.set("HPが", String.valueOf(heal), "回復した");
		return true;
	}

}
