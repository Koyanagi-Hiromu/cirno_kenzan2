package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.レティ;
import dangeon.model.object.creature.player.Player;

public class レティのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "レティのカード";
	public static final String item_name_sound = "れてぃのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "毎ターンＨＰが回復するようになる程度の能力" };

	public レティのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.冷;
	}

	@Override
	public String getCharacterShortName() {
		return "レティ";
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
		return "凍っているアイテムが多いほど、防御力が上がるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("あたいが最強ね");
	}

	@Override
	public String getIllustlator() {
		return "猫に地球";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member_illust.php?id=366396";
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
		return レティ.class;
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
		SE.ICE.play();
		Player.me.setCondition(CONDITION.回復, 0);
		return true;
	}

}
