package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.enemy.赤蛮奇_頭;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 頭印 extends EnchantSpecial {
	static ENCHANT_SIMBOL sim = ENCHANT_SIMBOL.頭;

	public static boolean effect() {
		if (!parcentCheck(10, sim)) {
			return false;
		}
		if (!enchantSimbolAllCheck(sim)) {
			return false;
		}
		if (!setHead()) {
			return false;
		}
		return true;
	}

	private static boolean setHead() {
		if (MapList.isEnemyMax())
			return false;
		Room r = MassCreater.getPlayerRoom();
		if (r == null) {
			return false;
		}
		final Point set = MassCreater.getMonsterPoint(MassCreater
				.getRoom(Player.me.getMassPoint()));
		if (set == Mass.nullpo.null_point) {
			return false;
		}
		Message.set("衝撃によって頭が取れた");
		MainMap.addEffect(new DoronEffect(set, new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
			}

			@Override
			protected void work(int frame) {
				if (frame == 4) {
					赤蛮奇_頭 hEAD = new 赤蛮奇_頭(set, 1, false);
					MapList.addEnemy(hEAD);
				}
			}
		}), true);

		return true;
	}

}
