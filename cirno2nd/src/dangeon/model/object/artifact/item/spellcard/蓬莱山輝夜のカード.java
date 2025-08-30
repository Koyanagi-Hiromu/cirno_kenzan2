package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.蓬莱山輝夜;
import dangeon.model.object.creature.player.Player;
import main.res.SE;
import main.util.半角全角コンバーター;

public class 蓬莱山輝夜のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "蓬莱山輝夜のカード";
	public static final String item_name_sound = "ほうらいさんかくやのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 10;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "あらゆる飛来物を反射するようになる程度の能力" };

	public 蓬莱山輝夜のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.竹;
	}

	@Override
	public String getCharacterShortName() {
		return "輝夜";
	}

	@Override
	public String getDoter() {
		return "ポリバケツ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=26447";
	}

	@Override
	String getExplanToEnchant() {
		return "命や時を止められないぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("働きたくないでござる");
	}

	@Override
	public String getIllustlator() {
		return "いかあし";
	}

	@Override
	public String getIllustlatorURL() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
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
		return 蓬莱山輝夜.class;
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
		SE.STATUS_GOOD.play();
		Player.me.setCondition(CONDITION.反射, 0);
		TurnSystemController.turnSkip();
		Message.set("夜明けまであと",
				半角全角コンバーター.半角To全角数字(1+(TurnSystemController.getRestTurn() / 10)),
				"行動");
		return true;
	}
}
