package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.射命丸文;
import dangeon.model.object.creature.player.Player;

public class 射命丸文のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "射命丸文のカード";
	public static final String item_name_sound = "しゃめいまるあやのかーと";
	private static final int composition = 5;
	private static final int item_str = 5;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "倍速状態になる程度の能力" };

	public 射命丸文のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.身躱し;
	}

	@Override
	public String getCharacterShortName() {
		return "射命丸";
	}

	@Override
	public String getDoter() {
		return "鮫妻";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=360177";
	}

	@Override
	String getExplanToEnchant() {
		return "攻撃を回避しやすくなるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("おぉ……");
	}

	@Override
	public String getIllustlator() {
		return "金編モロ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=403167";
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
		return 射命丸文.class;
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
		SE.STATUS_SPEEDY.play();
		Player.me.setCondition(CONDITION.倍速, 0);
		return true;
	}

}
