package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.きもけーね;
import dangeon.model.object.creature.enemy.上白沢慧音;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.util.MapInSelect;

public class きもけーねのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "きもけーねのカード";
	public static final String item_name_sound = "きもけーねのかーと";
	private static final int composition = 6;
	private static final int item_str = 11;
	private static final int item_def = 11;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "1つ前の階に戻るぞ" };

	public きもけーねのカード(Point p) {
		super(p, item_name, 1, composition, 上白沢慧音.class);
		sim = ENCHANT_SIMBOL.月;
	}

	@Override
	public String getCharacterShortName() {
		return "慧音";
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
		return きもけーね.class;
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
		SE.KEINE_SP.play();
		SE.LEVEL_DOWN.play();
		// Message.set("「どれどれ先生に任せなさい」");
		for (Base_Creature c : MapInSelect.getListRoomInEnemy()) {
			if (c instanceof Base_NPC)
				continue;
			c.chengeLv(1);
		}
		return true;
	}
}
