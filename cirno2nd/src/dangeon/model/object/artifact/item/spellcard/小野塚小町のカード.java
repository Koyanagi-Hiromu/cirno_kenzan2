package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.小野塚小町;
import dangeon.model.object.creature.player.Player;

public class 小野塚小町のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "小野塚小町のカード";
	public static final String item_name_sound = "おのつかこまちのかーと";
	private static final int composition = 4;
	private static final int item_str = 7;
	private static final int item_def = 7;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "距離を詰める程度の能力" };

	public 小野塚小町のカード(Point p) {
		super(p, item_name, 1, composition, 小野塚小町.class);
		sim = ENCHANT_SIMBOL.間;
	}

	private void effect() {
		Point p = Player.me.getMassPoint().getLocation();
		DIRECTION dir = Player.me.getDirection();
		p.x += dir.X;
		p.y += dir.Y;
		while (true) {
			if (!MassCreater.getMass(p).WALKABLE) {
				break;
			}
			if (MapList.getCreature(p) != null) {
				break;
			}
			Player.me.setMassPoint_WalkLike(p, 3);
			p.x += dir.X;
			p.y += dir.Y;
		}
	}

	@Override
	public String getCharacterShortName() {
		return "小町";
	}

	@Override
	public String getDoter() {
		return "パピジ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=2942558";
	}

	@Override
	String getExplanToEnchant() {
		return "攻撃を避けると距離をとるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("小野妹子");
	}

	@Override
	public String getIllustlator() {
		return "ふいゆ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2189510";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "使用すると何かに当たるまで前進する";
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		// TODO 自動生成されたメソッド・スタブ
		return 小野塚小町.class;
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
		SE.WARP_INSTANT.play();
		effect();
		return true;
	}

}
