package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.メディスン;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.Poison;

public class メディスンメランコリーのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "メディスンのカード";
	public static final String item_name_sound = "めてぃすんのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 5;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "フロア内の敵の力を半分にする程度の能力" };

	public メディスンメランコリーのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.毒;
	}

	public boolean effect() {
		Point p = Player.me.getMassPoint().getLocation();
		boolean flag = false;
		if (MassCreater.getMass(p).ROOM) {
			Message.set("部屋内に毒をまき散らした");
			for (Base_Enemy em : MapList.getListEnemy()) {
				if (MassCreater.isPointInTheSameRoomInEntrance(p,
						em.getMassPoint())) {
					flag = true;
					Poison.effect(em, 1, true, false);
				}
			}
		} else {
			Message.set("隣接している敵に毒をまいた");
			for (Base_Creature em : MapList.getListAroundCreature(p)) {
				flag = true;
				Poison.effect(em, 1, true, false);
			}
		}
		return true;
	}

	@Override
	public String getCharacterShortName() {
		return "メディスン";
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
		return "力が下げられなくなり、毒で回復するようになるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("まきまき");
	}

	@Override
	public String getIllustlator() {
		return "めろん22";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2963688";
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
		return メディスン.class;
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
		SE.KYOUKA.play();
		effect();
		return true;
	}

}
