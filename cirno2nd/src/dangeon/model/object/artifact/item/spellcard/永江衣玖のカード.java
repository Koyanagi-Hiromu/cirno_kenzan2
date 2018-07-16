package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.永江衣玖;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ThunderDamage;

public class 永江衣玖のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "永江衣玖のカード";
	public static final String item_name_sound = "なかえいくのかーと";
	private static final int composition = 5;
	private static final int item_str = 6;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵に雷を落とす程度の能力" };

	public 永江衣玖のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.衣;
	}

	@Override
	public String getCharacterShortName() {
		return "衣玖";
	}

	@Override
	public String getDoter() {
		return "AJIA";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=709125";
	}

	@Override
	String getExplanToEnchant() {
		return "雷を受けると回復するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("カリスマ！！");
	}

	@Override
	public String getIllustlator() {
		return "よー";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=630924";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "部屋内に雷を落とすぞ";
	}

	@Override
	protected String[] getSecondExplan() {
		return new String[] { "", "" };
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 永江衣玖.class;
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
		ThunderDamage.thunderDamage(Player.me, Player.me, Player.me, 75);
		return true;
	}

}
