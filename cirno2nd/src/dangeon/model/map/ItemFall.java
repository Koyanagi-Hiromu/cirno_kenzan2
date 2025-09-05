package dangeon.model.map;

import java.awt.Point;

import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class ItemFall {
	public static boolean flag_trap_check;

	public static boolean isAbleToFall(Point p) {
		return MassCreater.getMass(p).WALKABLE && !MassCreater.getMass(p).WATER
				&& MapList.getArtifact(p) == null;
	}

	public static boolean isAbleToFall_AroundPlayer() {
		Point _p = Player.me.getMassPoint();
		if (MassCreater.getMass(_p).WATER && MapList.getArtifact(_p) == null)
			return true;
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				Point p = _p.getLocation();
				p.translate(i, j);
				if (isAbleToFall(p)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isArtifact_exceptMe(Point p, Base_Artifact a) {
		if (flag_trap_check)
			return MapList.getArtiface_exceptME_Trap(p, a) != null;
		else
			return MapList.getArtiface_exceptME(p, a) != null;
	}

	public static void itemFall(Base_Artifact a) {
		if (a == null) {
			return;
		}
		itemFall(a.getMassPoint(), a);
	}

	/**
	 * 
	 * @param p
	 * @param a
	 *            マップリストに登録されてないもの。
	 */
	public static void itemFall(Point p, Base_Artifact a) {
		itemFall(p, p, a);
	}

	/**
	 * 
	 * @param to_p
	 * @param a
	 *            マップリストに登録されてないもの。
	 */
	public static void itemFall(Point from_p, Point to_p, Base_Artifact a) {
		if (a == null || to_p == null || from_p == null) {
			return;
		}
		to_p = to_p.getLocation();
		boolean flag_sucess = setItemFallPoint_NoMessage(from_p, to_p, a);
		if (flag_sucess) {
			if (a instanceof Base_Trap) {
				if (MassCreater.getMass(a.getMassPoint()).isTrapChecked())
					a.setVisible(true);
			}
			MapList.addArtifact(a);
		} else {
			Message.set(a.getColoredName().concat("は"), "消えてしまった･･･");
		}
	}

	public static void itemFall_TrapCheck(Point p, Base_Artifact a) {
		flag_trap_check = true;
		itemFall(p, a);
	}

	private static boolean setItemFallPoint(Point p, Base_Artifact a) {
		if (MassCreater.getMass(p).isAbleToExistArtifact()
				&& MapList.getArtiface_exceptME(p, a) == null) {
			a.setMassPoint_ParabolaJump_NoAttack(p);
			return true;
		}
		return false;
	}

	public static boolean setItemFallPoint_NoMessage(Point p, Base_Artifact a) {
		return setItemFallPoint_NoMessage(p, p, a);
	}

	/**
	 * アイテムの位置を変更するだけでマップリストに登録しない。
	 * 
	 * @param p
	 * @param a
	 * @return
	 */
	public static boolean setItemFallPoint_NoMessage(Point from, Point p,
			Base_Artifact a) {
		a.setMassPoint(from);
		if (isArtifact_exceptMe(p, a)) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (setItemFallPoint(new Point(p.x + i, p.y + j), a)) {
						return true;
					}
				}
			}
			for (int i = -2; i <= 2; i++) {
				for (int j = -2; j <= 2; j++) {
					if (setItemFallPoint(new Point(p.x + i, p.y + j), a)) {
						return true;
					}
				}
			}
			return false;
		} else {
			if (flag_trap_check)
				if (trapCheck(p, a))
					return true;
			setItemFallPoint(p, a);
			return true;
		}
	}

	private static boolean trapCheck(Point p, final Base_Artifact a) {
		if (MassCreater.getMass(p).isAbleToExistArtifact()
				&& MapList.getArtiface_exceptME_Trap(p, a) == null) {
			return trapCheck_setMassPoint(p, a);
		}
		return false;
	}

	// private static Point getFallPoint(Point p, Base_Artifact a) {
	// return MassCreater.getMass(p).WALKABLE
	// && MapList.getArtifact(p) == null;
	// }

	private static boolean trapCheck_setMassPoint(final Point p,
			final Base_Artifact a) {
		Base_Artifact _a = MapList.getArtiface_exceptME(p, a);
		if (_a != null && _a instanceof Base_Trap) {
			final Base_Trap trap = (Base_Trap) _a;
			if (!trap.isAbleToWork())
				return false;
			a.setMassPoint_ParabolaJump_NoAttack(p, new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					Message.set(trap.getColoredName(), "が反応した");
					Base_Creature c = MapList.getCreature(p);
					if (c != null) {
						new Task() {
							/**
							 *
							 */
							private static final long serialVersionUID = 1L;
							private boolean flag_end;

							@Override
							public boolean isDemandToContinue() {
								return !flag_end;
							}

							@Override
							public void work() {
								if (TaskOnMapObject.isThrowTaskEmpty()) {
									for (Base_Artifact a : MapList
											.getListArtifact()) {
										if (a.isAnimating()) {
											return;
										}
									}
									flag_end = true;
									MapList.removeArtifact(a);
									ItemFall.flag_trap_check = false;
									ItemFall.itemFall(a);
								}
							}
						}.work_appointment();
						trap.trapEffect(c);
						flag_trap_check = false;
					} else
						trap.trapEffect(a);
				}
			});
		} else {
			a.setMassPoint_ParabolaJump_NoAttack(p);
		}
		flag_trap_check = false;
		return true;
	}

}
