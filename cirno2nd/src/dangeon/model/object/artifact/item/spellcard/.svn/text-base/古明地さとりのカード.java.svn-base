package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.古明地さとり;
import dangeon.model.object.creature.player.Player;

public class 古明地さとりのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "古明地さとりのカード";
	public static final String item_name_sound = "こめいしさとりのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "攻撃を避け易くなる程度の能力" };

	public 古明地さとりのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.心;
	}

	@Override
	public String getCharacterShortName() {
		return "さとり";
	}

	@Override
	public String getDoter() {
		return "ポポタ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1599527";
	}

	@Override
	String getExplanToEnchant() {
		return "時々回避状態になるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("二人は……");
	}

	@Override
	public String getIllustlator() {
		return "ラムタイズム";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2380894";
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
		return 古明地さとり.class;
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
		Player.me.setCondition(CONDITION.絶対回避, 10);
		Message.set("「さあトラウマを与えに行きましょう」");
		return true;
	}

}
