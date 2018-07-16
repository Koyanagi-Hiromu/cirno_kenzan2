package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.朱鷺子;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;

public class 朱鷺子のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "朱鷺子のカード";
	public static final String item_name_sound = "ときこのかーと";
	private static final int composition = 5;
	private static final int item_str = 7;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "直線３マスの敵が快楽と共に安らかに吹っ飛ぶ" };

	public 朱鷺子のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.鴇;
	}

	@Override
	public String getCharacterShortName() {
		return "アリス";
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
		return "書を装備していると、攻撃力と防御力が上がるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("禁則事項");
	}

	@Override
	public String getIllustlator() {
		return "fe";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2443628";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "直線３マスの敵が快楽と共に安らかに吹っ飛ぶ";
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 朱鷺子.class;
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
		SE.DAMAGED_CRITICAL.play();
		DIRECTION dir = Player.me.getDirection();
		List<Base_Creature> list = MapInSelect.getListStraightHitCreature(dir,
				Player.me.getMassPoint(), 3);
		for (Iterator<Base_Creature> i = list.iterator(); i.hasNext();) {
			Base_Creature c = i.next();
			Damage.damageNoMessage(this, "うぎゃあ", Player.me, c, 99999);
		}
		return true;
	}

}
