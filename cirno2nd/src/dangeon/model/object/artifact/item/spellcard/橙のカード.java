package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.橙;
import dangeon.model.object.creature.player.Player;

public class 橙のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "橙のカード";
	public static final String item_name_sound = "ちぇんのかーと";
	private static final int composition = 2;
	private static final int item_str = 4;
	private static final int item_def = 3;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "倍速状態になり部屋内のどこかにジャンプする程度の能力" };

	public 橙のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.双;
	}

	@Override
	public String getCharacterShortName() {
		return "橙";
	}

	@Override
	public String getDoter() {
		return "めそうさん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=293174";
	}

	@Override
	String getExplanToEnchant() {
		return "前後に攻撃するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ちぇんちぇん");
	}

	@Override
	public String getIllustlator() {
		return "ryosios";
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
	protected String getSecondExplain_ByEach_Annotation() {
		return "通路で使用しても効果はない";
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 橙.class;
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
		DIRECTION d = Player.me.direction;
		for (int i = 0; i < 2; i++) {
			p.translate(d.X, d.Y);
			Mass mass = MassCreater.getMass(p);
			if (!mass.WALKABLE) {
				Message.set("「壁に激突しちゃうよ」");
				return false;
			}
		}
		// Room room = MassCreater.getRoom(Player.me.getMassPoint().x,
		// Player.me.getMassPoint().y);
		// if (room == null) {
		// Message.set("通路ではジャンプできない");
		// return false;
		// }
		// ArrayList<Point> list = room.getListNoCreatureMassPointInRoom();
		// if (list.isEmpty()) {
		// Message.set("ワープ出来るマスが無かった");
		// return false;
		// }
		SE.WARP.play();
		Player.me.setMassPoint_ParabolaJumpAttack(p);
		Message.set("「飛び越えちゃうよー？」");
		return true;
	}

}
