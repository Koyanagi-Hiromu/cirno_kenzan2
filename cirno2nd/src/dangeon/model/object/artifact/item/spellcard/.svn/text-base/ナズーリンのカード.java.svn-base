package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ナズーリン;

public class ナズーリンのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ナズーリンのカード";
	public static final String item_name_sound = "なすーりんのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 3;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "フロアのマップが分かるようになる程度の能力" };

	public ナズーリンのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.鼠;
	}

	@Override
	public String getCharacterShortName() {
		return "ナズーリン";
	}

	@Override
	public String getDoter() {
		return "柊木冬";
	}

	@Override
	public String getDoterURL() {
		return null;
	}

	@Override
	String getExplanToEnchant() {
		return "ピンチになると必中会心になるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("くいっ");
	}

	@Override
	public String getIllustlator() {
		return "たいがー";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=157043";
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
		return ナズーリン.class;
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
		MassCreater.setAllMassOnMiniMap();
		return true;
	}

}
