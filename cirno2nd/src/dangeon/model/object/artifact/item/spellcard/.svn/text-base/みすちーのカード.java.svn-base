package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ミスティア;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.STAGE;

public class みすちーのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "みすちーのカード";
	public static final String item_name_sound = "みすちーのかーと";
	private static final int composition = 7;
	private static final int item_str = 3;
	private static final int item_def = 2;
	private static final boolean passing_of_spell = false;

	// private ENCHANT_SIMBOL SIM = ENCHANT_SIMBOL.鰻;

	private static final String[] EXPLAN = new String[] { "部屋内の敵を混乱させる程度の能力" };

	public みすちーのカード(Point p) {
		super(p, item_name, 1, composition);
		// sim = SIM;
		list_stage.add(STAGE.妖々夢);

	}

	@Override
	public String getCharacterShortName() {
		return "ミスティア";
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
		return "特に効果が無いぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("TINTINTIN");
	}

	@Override
	public String getIllustlator() {
		return "ペケ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://anime.geocities.jp/heberekedou06/";
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
		return ミスティア.class;
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
		SE.MISTHIA_VOISE.play();
		SE.STATUS_PIYOPIYO.play();
		MapInSelect.roomOrRoadInEnemyChengeCondition(Player.me.getMassPoint(),
				CONDITION.混乱, 0);
		return true;
	}

}
