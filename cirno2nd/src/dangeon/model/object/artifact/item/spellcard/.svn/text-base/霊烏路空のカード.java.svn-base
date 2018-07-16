package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.霊烏路空;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 霊烏路空のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "霊烏路空のカード";
	public static final String item_name_sound = "れいうしうつほのかーと";
	private static final int composition = 3;
	private static final int item_str = 12;
	private static final int item_def = 9;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "自爆する程度の能力" };

	public 霊烏路空のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.核爆発;
	}

	@Override
	public String getCharacterShortName() {
		return "空";
	}

	@Override
	public String getDoter() {
		return "かうざー";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=91937";
	}

	@Override
	String getExplanToEnchant() {
		return "攻撃すると時々爆発するぞ";
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
		return "いわのり";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=288981";
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
		return 霊烏路空.class;
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
		MapInSelect.explosion(Player.me.getMassPoint());
		return true;
	}

}
