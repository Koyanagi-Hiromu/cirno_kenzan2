package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.封獣ぬえ;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 封獣ぬえのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "封獣ぬえのカード";
	public static final String item_name_sound = "ほうしゅうぬえのかーと";
	private static final int composition = 4;
	private static final int item_str = 10;
	private static final int item_def = 8;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵に状態異常を引き起こす程度の能力" };

	public 封獣ぬえのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.謎;
	}

	@Override
	public String getCharacterShortName() {
		return "ぬえ";
	}

	@Override
	public String getDoter() {
		return "Makoto-K";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=803942";
	}

	@Override
	String getExplanToEnchant() {
		return "攻撃すると時々色々な状態異常を引き起こすぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return "封獣ぬえ";
	}

	@Override
	public String getIllustlator() {
		return "よこはち";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member_illust.php?id=20909";
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
		return 封獣ぬえ.class;
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
		Message.set("「にひひ！鬼が出るか蛇が出るかはお楽しみ！！」");
		SE.TIME_STOP.play();
		MapInSelect.roomOrRoadInEnemyChengeCondition(Player.me.getMassPoint(),
				null, 0);
		MapInSelect.roomOrRoadInEnemyChengeCondition(Player.me.getMassPoint(),
				null, 0);
		return true;
	}

}
