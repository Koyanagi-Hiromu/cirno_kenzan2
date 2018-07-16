package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.キスメ;

public class キスメのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "キスメのカード";
	public static final String item_name_sound = "きすめのかーと";
	private static final int composition = 5;
	private static final int item_str = 3;
	private static final int item_def = 7;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "フロア中に沢山の落とし穴を作る程度の能力" };

	public キスメのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.狭;
	}

	@Override
	public String getCharacterShortName() {
		return "キスメ";
	}

	@Override
	public String getDoter() {
		return "まるせん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1733063";
	}

	@Override
	String getExplanToEnchant() {
		return "通路にいると防御力が上がるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("殻に篭る");
	}

	@Override
	public String getIllustlator() {
		return "藤丸あお";
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
		return キスメ.class;
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
		if (PresentField.get().isHaraheru()) {
			SE.ZAKUZAKU.play();
			MassCreater.setFallTrap(10);
			Message.set("（落とし穴１０コ作ったよ）");
			return true;
		} else {
			Message.set("（ダンジョンの中じゃないと作れないよ）");
			return false;
		}
	}

}
