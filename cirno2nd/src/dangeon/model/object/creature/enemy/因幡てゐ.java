package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.TrapTable;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.artifact.trap.召喚の罠;
import dangeon.model.object.artifact.trap.地雷の罠;
import dangeon.model.object.artifact.trap.落とし穴の罠;
import dangeon.model.object.artifact.trap.錆の罠;

public class 因幡てゐ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 因幡てゐ(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Message.set("フロアのどこかにワナを仕掛けた");
		TrapTable.addMapList(3 * LV, null);
		return true;
	}

	@Override
	protected void enemyBreakAction() {
		if (LV == 4) {
			SE.ZAKUZAKU.play();
			for (int i = 0; i <= 3; i++) {
				TaskOnMapObject
						.addTrapSetTask(new 地雷の罠(MassCreater.getTrapIP()));
			}
			Message.set("てゐはフロアのどこかに地雷のワナを３コ残した");
			return;
		}
		Boolean b = null;
		if (MapList.getArtifact(getMassPoint()) != null)
			return;
		if (MassCreater.getRoom(getMassPoint()) != null) {
			for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
				if (MassCreater.getMass(getMassPoint(), d).ROAD) {
					b = false;
					break;
				}
			}
			if (b == null)
				return;
		} else {
			b = true;
		}
		SE.SYSTEM_TRAP_ON.play();
		Base_Trap trap = trap();
		trap.setVisible(true);
		MapList.setTrapOnMap(getMassPoint(), trap);
		if (b)
			Message.set("てゐは通路に", trap.getColoredName(), "を残した");
		else
			Message.set("てゐは通路のそばに", trap.getColoredName(), "を残した");
		if (LV == 1) {
			for (DIRECTION d : DIRECTION.values()) {
				Mass m = MassCreater.getMass(d.getFrontPoint(mass_point
						.getLocation()));
				if (m.isHoly() || m.isShop()) {
					Medal.落としてゐを敷地近くで倒した.addCount();
					break;
				}
			}
		}
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

	private Base_Trap trap() {
		switch (LV) {
		case 1:
			return new 落とし穴の罠(getMassPoint());
		case 2:
			return new 地雷の罠(getMassPoint());
		case 3:
			return new 召喚の罠(getMassPoint());
		default:
			return new 錆の罠(getMassPoint(), true);
		}
	}

}
