package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.リグルナイトバグ;
import dangeon.model.object.creature.player.Player;

public class リグルのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "リグルのカード";
	public static final String item_name_sound = "りくるのかーと";
	private static final int composition = 6;
	private static final int item_str = 5;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "常に明るくなる程度の能力" };

	public リグルのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.蛍;
	}

	@Override
	public String getCharacterShortName() {
		return "リグル";
	}

	@Override
	public String getDoter() {
		return "柊木冬";
	}

	@Override
	public String getDoterURL() {
		// TODO
		return null;
	}

	@Override
	String getExplanToEnchant() {
		return "時々明るくなるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("可愛い！");
	}

	@Override
	public String getIllustlator() {
		return "Ｄ＠ｉ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=9179";
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
		return リグルナイトバグ.class;
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
		SE.LIGHT_ON.play();
		if (Player.me.getConditionList().contains(CONDITION.蛍)) {
			CONDITION.quickRemoveCondition(CONDITION.蛍, Player.me);
		}
		Player.me.setCondition(CONDITION.蛍, 0);
		return true;
	}

}
