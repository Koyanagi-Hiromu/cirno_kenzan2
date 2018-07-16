package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ミスティア;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.Poison;

public class ミスティアのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ミスティアのカード";
	public static final String item_name_sound = "みすてぃあーのかーと";
	private static final int composition = 5;
	private static final int item_str = 5;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;
	private ENCHANT_SIMBOL SIM = ENCHANT_SIMBOL.鰻;

	private static final String[] EXPLAN = new String[] { "フロア中の全てのキャラクターに毒を撒く程度の能力" };

	public ミスティアのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = SIM;

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
		return "敵を倒すと時々ヤツメおにぎりを落とすぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("私の歌をきけー！");
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
		for (Base_Creature c : MapList.getListCreature()) {
			Poison.effect(c, 1, true, false);
		}
		Poison.effect(Player.me, 1, false, false);
		return true;
	}

}
