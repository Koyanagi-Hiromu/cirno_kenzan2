package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.八坂神奈子;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 八坂神奈子のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "八坂神奈子のカード";
	public static final String item_name_sound = "やさかかなこのかーと";
	private static final int composition = 3;
	private static final int item_str = 7;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "３マス先まで移動するぞ。途中に敵がいる場合吹き飛ばすぞ" };

	final int move_max = 3;

	public 八坂神奈子のカード(Point p) {
		super(p, item_name, 1, composition, 八坂神奈子.class);
		sim = ENCHANT_SIMBOL.オンバシラ;
	}

	@Override
	public String getCharacterShortName() {
		return "神奈子";
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
		return "時々追加でオンバシラで殴打するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("あぁっ！御柱がっ！");
	}

	@Override
	public String getIllustlator() {
		return "たにたけし";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member_illust.php?id=193851";
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
		// TODO 自動生成されたメソッド・スタブ
		return 八坂神奈子.class;
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
		Point p = Player.me.getMassPoint().getLocation();
		DIRECTION d = Player.me.getDirection();
		Base_Creature c = MapInSelect.getStraightHitCreature(
				Player.me.getDirection(), p, 3);
		if (c != null) {
			Point _p = new Point(c.getMassPoint().x - d.X, c.getMassPoint().y
					- d.Y);
			MapInSelect.吹き飛ばし(Player.me, "ふきとばした", c, d, 10, 5);
			Player.me.setMassPoint_WalkLike(_p, 2);
		} else {
			int i = 0;
			Point _p = p.getLocation();
			while (true) {
				i++;
				_p.translate(d.X, d.Y);
				System.out.println(_p);
				if (!MassCreater.isStandMass(_p)) {
					Player.me.setMassPoint_WalkLike(p, 10);
					break;
				}
				if (i >= 3) {
					Player.me.setMassPoint_WalkLike(_p, 10);
					break;
				}
				p.translate(d.X, d.Y);
			}
		}
		// Message.set("");
		// Base_Artifact a = Enchant.ATK.getEnchant();
		// if (a != null) {
		// SE.KYOUKA.play();
		// Message.set(a.getColoredName().concat("は強化された"));
		// a.setForgeValue(1);
		// if (a.isCurse()) {
		// a.setCurse(false);
		// Message.set("呪いが解けた");
		// }
		// }
		return true;
	}

}
