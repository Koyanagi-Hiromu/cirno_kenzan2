package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.西行寺幽々子;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 西行寺幽々子のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "西行寺幽々子のカード";
	public static final String item_name_sound = "さいきょうしゆゆこのかーと";
	private static final int composition = 5;
	private static final int item_str = 7;
	private static final int item_def = 14;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "部屋内の敵を死にいざなう程度の能力" };

	public 西行寺幽々子のカード(Point p) {
		super(p, item_name, 1, composition, 西行寺幽々子.class);
		sim = ENCHANT_SIMBOL.ハラヘリ;
	}

	private void effect() {
		MapInSelect.roomInChengeConditionAtPoint(Player.me.getMassPoint(),
				CONDITION.死, 13);
		// MapInSelect.roomInCreatureChengeCondition(Player.me, CONDITION.死,
		// 13);
	}

	@Override
	public String getCharacterShortName() {
		return "幽々子";
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
		return "お腹が減りやすいぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("お腹が減ったでござる");
	}

	@Override
	public String getIllustlator() {
		return "み～や";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.miyasuke.net/";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		// TODO 自動生成されたメソッド・スタブ
		return 西行寺幽々子.class;
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
		SE.YUYUKO_SPELL.play();
		effect();
		return true;
	}
}
