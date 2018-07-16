package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.十六夜咲夜;

public class 十六夜咲夜のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "十六夜咲夜のカード";
	public static final String item_name_sound = "いさよいさくやのかーと";
	private static final int composition = 5;
	private static final int item_str = 7;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "時を三秒間止める程度の能力" };

	public 十六夜咲夜のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.弾;
	}

	@Override
	public String getCharacterShortName() {
		return "咲夜";
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
		return "防御力が上がるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ドドドドドドド");
	}

	@Override
	public String getIllustlator() {
		return "みな☆とるてぃあ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=1766413";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "使用すると１ターンだけ追加で動くことが出来る";
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		// TODO 自動生成されたメソッド・スタブ
		return 十六夜咲夜.class;
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
		Message.set("３秒間だけ時間を止めたわ");
		TurnSystemController.setTimeStop(3);
		return true;
	}
}
