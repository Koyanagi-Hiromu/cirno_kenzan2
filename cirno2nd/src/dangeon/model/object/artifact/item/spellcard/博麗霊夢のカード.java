package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.博麗霊夢;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 博麗霊夢のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "博麗霊夢のカード";
	public static final String item_name_sound = "はくれいれいむのかーと";
	private static final int composition = 7;
	private static final int item_str = 7;
	private static final int item_def = 14;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵を封印状態にする程度の能力" };

	public 博麗霊夢のカード(Point p) {
		super(p, item_name, 1, composition, 博麗霊夢.class);
	}

	@Override
	public String getCharacterShortName() {
		return "霊夢";
	}

	@Override
	public String getDoter() {
		return "ささ和え";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=568693";
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
		return "霊夢";
	}

	@Override
	public String getIllustlator() {
		return "レリ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=324298";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "封印状態にするぞ";
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		// TODO 自動生成されたメソッド・スタブ
		return 博麗霊夢.class;
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
		SE.STATUS_SEAL.play();
		MapInSelect.roomInChengeConditionAtPoint(Player.me.getMassPoint(),
				CONDITION.封印, 0);
		return true;
	}

}
