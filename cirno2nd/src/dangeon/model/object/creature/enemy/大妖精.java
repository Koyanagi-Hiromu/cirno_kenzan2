package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.List;

import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.NextFloor;
import dangeon.model.map.PresentField;
import dangeon.model.map.StairScene;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import main.res.SE;
import main.util.DIRECTION;

public class 大妖精 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 大妖精(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		NextFloor.next(PresentField.get());
		return true;
	}

	private Task getTask(final Base_Creature c, final Point p) {
		if (p == null)
			return new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					if (c == Player.me) {
						StairScene.addTask(new Task() {
							private static final long serialVersionUID = 1L;

							@Override
							public void work() {
							}
						});
					} else {
						c.setMassPoint(Mass.nullpo.getPoint());
					}
				}
			};
		else
			return new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					c.setMassPoint(p);
				}
			};
	}

	@Override
	protected void init4() {
		changeSize(1);
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible())
			return false;
		Point p = null;
		Base_Enemy e = null;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.イカリ)) {
			Message.set(getColoredName(), "は", Player.me.getColoredName(),
					"が重くてうまくテレポートできなかった");
			return true;
		}
		if (LV == 1 && new R().is(10)) {
			telepote(null, null);
			TurnSystemController.setTurnFinish();
			Message.set(getColoredName(), "は", Player.me.getColoredName(),
					"を次の階へ連れ去った");
			NextFloor.next(PresentField.get());
			return true;
		} else if (LV > 2) {
			int try_number = 3;
			int i = 0;
			for (; i < try_number; i++) {
				e = MapList.getListEnemy().get(
						new R().nextInt(MapList.getListEnemy().size()));
				if (MassCreater.getRoom(e.getMassPoint()) == null)
					continue;
				if (MassCreater.isPlayerInTheSameRoom(e.getMassPoint()))
					continue;
				List<Point> list = MapInSelect.aroundEmptyCheck(e
						.getMassPoint());
				for (Point point : list) {
					if (MassCreater.getRoom(point) != null) {
						p = point;
						break;
					}
				}
				if (p != null)
					break;
			}
			if (i == try_number)
				return false;
		} else {
			p = MassCreater.getWarpPoint(null);
		}
		List<Point> list = MapInSelect.aroundEmptyCheck(p, false);
		Point _P = null;
		for (Point point : list) {
			if (!p.equals(point)) {
				_P = point;
				break;
			}
		}
		if (_P == null) {
			return false;
		}
		telepote(_P, p);
		Player.me.setDirection(DIRECTION.getDirection(Player.me.getMassPoint(),
				getMassPoint()));
		Message.set(getColoredName(), "は", Player.me.getColoredName(),
				"を連れてワープした");
		final DIRECTION _D = DIRECTION.getDirection(p, _P);
		Player.me.setDirection(_D);
		//
		// Player.me.setMassPoint_Jump(p, new Task() {
		//
		// /**
		// *
		// */
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void work() {
		// }
		// });
		if (e != null)
			CONDITION.wakeUp(e);
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible()) {
			if (isSpecialParcent()) {
				return true;
			}
		}
		return false;
	}

	private void telepote(Point p, Point _p) {
		SE.KYOUKA.play();
		this.setTelepoteAnimation(false, getTask(this, p));
		Player.me.setTelepoteAnimation(false, getTask(Player.me, _p));
	}
}
