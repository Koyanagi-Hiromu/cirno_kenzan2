package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.剣;
import dangeon.model.object.creature.player.Player;

public class Exルーミアのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "Exルーミアのカード";
	public static final String item_name_sound = "いーえっくするーみあのかーと";
	private static final int composition = 5;
	private static final int item_str = 14;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;
	private static final String[] EXPLAN = new String[] { "部屋内の敵を暗闇状態にする程度の能力" };

	public Exルーミアのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.剣;
	}

	@Override
	public String getCharacterShortName() {
		return "ルーミア";
	}

	@Override
	public String getDoter() {
		return "かみねんど";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=648642";
	}

	@Override
	String getExplanToEnchant() {
		return "時々投擲物を跳ね返すぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
		// return new String[] { "周囲８マスに人形を作り出す程度の能力" };
	}

	@Override
	protected String getFlavor() {
		return new String("私の名前はアリス・マーガトロイド");
	}

	@Override
	public String getIllustlator() {
		return "五目みつき";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=38755";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "部屋内の敵を暗闇状態にする";
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 剣.class;
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
		if (MassCreater.isSpotValid()) {
			Message.set("暗闇を吸収した");
			SE.STATUS_GOOD.play();
			Player.me.setCondition(CONDITION.やりすごし, 15);
			SE.LIGHT_ON.play();
			MassCreater.setSpot(false);
		} else {
			Message.set("光を吸収した");
			Player.me.chengeHP(null, null, Player.me.getMAX_HP());
			MassCreater.setSpot(true);
		}
		return true;
	}
}
