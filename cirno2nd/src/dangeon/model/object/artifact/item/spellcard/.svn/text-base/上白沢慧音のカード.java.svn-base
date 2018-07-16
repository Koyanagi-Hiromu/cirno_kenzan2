package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.上白沢慧音;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 上白沢慧音のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "上白沢慧音のカード";
	public static final String item_name_sound = "かみしらさわけいねのかーと";
	private static final int composition = 5;
	private static final int item_str = 7;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "ターンを消費せず、周囲の敵のレベルを下げるぞ。" };

	public 上白沢慧音のカード(Point p) {
		super(p, item_name, 1, composition, 上白沢慧音.class);
		sim = ENCHANT_SIMBOL.教;
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
		return "獲得経験値が上がるぞ";
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
		return 上白沢慧音.class;
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
		for (Base_Creature c : MapInSelect.getListAroundInCreature(Player.me
				.getMassPoint())) {
			if (c instanceof Base_NPC)
				continue;
			c.chengeLv(1);
		}
		return true;
	}
}
