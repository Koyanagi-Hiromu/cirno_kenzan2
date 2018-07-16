package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.model.object.artifact.item.bullet.アンカー;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.村紗水蜜;
import dangeon.model.object.creature.player.Player;

public class 村紗水蜜のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "村紗水蜜のカード";
	public static final String item_name_sound = "むらさむなみつのかーと";
	private static final int composition = 5;
	private static final int item_str = 3;
	private static final int item_def = 7;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "ぶっとびアンカーを投げる程度の能力" };

	public 村紗水蜜のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.イカリ;
	}

	@Override
	public String getCharacterShortName() {
		return "村紗";
	}

	@Override
	public String getDoter() {
		return "シュウ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1724270";
	}

	@Override
	String getExplanToEnchant() {
		return "吹き飛ばなくなるぞ";
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
		return "BFA";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=425724";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "アンカーは当たればぶっ飛ばした上でおよそレベルの１０倍のダメージを与える";
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 村紗水蜜.class;
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
		SE.THROW_HEAVY.play();
		new アンカー(Player.me).itemThrow(Player.me, HowToThrow.BREAK);
		return true;
	}

}
