package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 自爆の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "自爆の書";
	public static final String item_name_sound = "しはくのしょ";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.自爆;
	Mass[][] mass = MassCreater.getMass();

	public 自爆の書(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "チルノを中心に爆発が起こる。チルノはHPが半分になる。周囲８マスの壁は壊れ、アイテムはなくなり、敵は問答無用でぶっとぶ。実は良い緊急回避手段である。";
	}

	@Override
	public void scroolUse() {
		Message.set("自爆した");
		MapInSelect.explosion(Player.me.getMassPoint().getLocation());
	}
}
