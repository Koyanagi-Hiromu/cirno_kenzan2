package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.姫海棠はたて;
import dangeon.model.object.creature.player.Belongings;

public class 姫海棠はたてのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "姫海棠はたてのカード";
	public static final String item_name_sound = "ひめかいとうはたてのかーと";
	private static final int composition = 3;
	private static final int item_str = 4;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "持ち物を全て識別する程度の能力" };

	public 姫海棠はたてのカード(Point p) {
		super(p, item_name, 1, composition, 姫海棠はたて.class);
		sim = ENCHANT_SIMBOL.写;
	}

	@Override
	public String getCharacterShortName() {
		return "はたて";
	}

	@Override
	public String getDoter() {
		return "ひろひろ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1225179";
	}

	@Override
	String getExplanToEnchant() {
		return "直後の印の発動確率が倍になるぞ";
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
		return "Curee(AQUASTYLE)";
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
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 姫海棠はたて.class;
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
		SE.CHECK.play();
		SE.LUCKEY.play();
		Message.set("「ピッピッピッと」");
		for (Base_Artifact a : Belongings.getListItems()) {
			a.check();
		}
		return true;
	}
}
