package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.豊聡耳神子;
import dangeon.util.MapInSelect;

public class 豊聡耳神子のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "豊聡耳神子のカード";
	public static final String item_name_sound = "とよさとみみのみこのかーと";
	private static final int composition = 5;
	private static final int item_str = 7;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "周囲の敵を金縛り状態にするぞ" };

	public 豊聡耳神子のカード(Point p) {
		super(p, item_name, 1, composition, 豊聡耳神子.class);
		sim = ENCHANT_SIMBOL.和;
	}

	@Override
	public String getCharacterShortName() {
		return "神子";
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
		return "状態異常の時、強くなるぞ";
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
		return 豊聡耳神子.class;
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
		SE.LUCKEY.play();
		MapInSelect.aroundInEnemyChengeCondition(CONDITION.麻痺, 0);
		return true;
	}
}
