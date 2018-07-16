package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.霍青娥;

public class 霍青娥のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "霍青娥のカード";
	public static final String item_name_sound = "かくせいかのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 10;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "フロア中の敵を邪法状態にする程度の能力" };

	public 霍青娥のカード(Point p) {
		super(p, item_name, 1, composition, 霍青娥.class);
		sim = ENCHANT_SIMBOL.邪;
	}

	@Override
	public String getCharacterShortName() {
		return "青娥";
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
		return "アイテムが呪われている程、防御力が高くなるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("呪");
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
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 霍青娥.class;
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
		for (Base_Creature c : MapList.getListEnemy()) {
			c.setCondition(CONDITION.邪法, 0);
		}
		return true;
	}
}
