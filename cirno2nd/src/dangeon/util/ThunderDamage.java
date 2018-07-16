package dangeon.util;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印衣;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.view.anime.LighitningEffect;
import dangeon.view.anime.Lightning_First_Effect;
import dangeon.view.constant.MAP;
import dangeon.view.detail.MainMap;
import dangeon.view.util.WithinOutofScreen;

public class ThunderDamage {
	// /**
	// * 自身も含めてのListを返す
	// *
	// * @param c
	// * @return
	// */
	// public static ArrayList<Base_Creature> thunderDamage(Base_Creature c) {
	// ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
	// ArrayList<Base_Creature> _list = new ArrayList<Base_Creature>();
	// check(c, list, _list);
	// return list;
	// }

	// 重複チェック
	private static void check(Base_Creature c, ArrayList<Base_Creature> list,
			ArrayList<Base_Creature> exception_list) {
		if (!exception_list.contains(c)) {
			list.add(c);
			exception_list.add(c);
		}
		for (Base_Creature c2 : MapInSelect.getListAroundInCreature(c
				.getMassPoint())) {
			if (!exception_list.contains(c2)) {
				list.add(c2);
				exception_list.add(c2);
				check(c2, list, exception_list);
			}
		}
	}

	private static void check(Point p, ArrayList<Point> list, Object obj,
			Base_Creature source, int damage) {
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			thunderDamage(new Point(p.x + d.X, p.y + d.Y), list, obj, source,
					damage);
		}
	}

	private static void damage(ArrayList<Base_Creature> list, final Object obj,
			final Base_Creature source, Base_Creature c, int damage) {
		boolean flag = false;
		for (Base_Creature bc : list) {

		}
		if (flag)
			Message.set("電気が走る！");
	}

	private static void damage(final Point p, final ArrayList<Point> list,
			final Object obj, final Base_Creature source,
			final Base_Creature c, final int damage) {
		if (withinScreen(c.getMassPoint())) {
			MainMap.addEffect(new LighitningEffect(c, new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					nextLightning(p, list, obj, source, c, damage);
				}
			}), true);
		} else {
			SE.THUNDER.play();
			nextLightning(p, list, obj, source, c, damage);
		}
	}

	protected static void nextLightning(Point p, ArrayList<Point> list,
			Object obj, Base_Creature source, Base_Creature c, int damage) {
		check(p, list, obj, source, damage);
		if (c == null)
			return;
		if (c.getHP() <= 0)
			return;
		if (c instanceof Player && 印衣.effect(damage))
			return;
		int dmg = damage;
		if (c instanceof Player) {
			if (c.getHP() <= damage && !BonusConductor.風水師_雷()) {
				dmg = c.getHP() - 1;
			}
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.電)) {
				Enchant.check(ENCHANT_SIMBOL.電, true);
				dmg -= 10;
			}
		}
		if (c.isThunder()) {
			Message.set(c.getColoredName(), "に雷は効かなかった");
			return;
		} else if (dmg == 0) {
			Message.set(c.getColoredName(), "は平気だった");
			return;
		}
		Damage.damage(obj, null, "雷に耐性がなく感電して倒れた", source, c, dmg);
	}

	private static void runWater(final Point p, final ArrayList<Point> list,
			final Object obj, final Base_Creature source, final int damage) {
		if (withinScreen(p)) {
			MainMap.addEffect(new LighitningEffect(p, new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					check(p, list, obj, source, damage);
				}
			}), true);
		} else {
			SE.THUNDER.play();
			check(p, list, obj, source, damage);
		}
	}

	public static void thunderDamage(Object obj, Base_Creature source,
			Base_Creature c, int damage) {
		thunderDamage(obj, source, c.getMassPoint(), damage);
	}

	public static void thunderDamage(final Object obj,
			final Base_Creature source, final Point p, final int damage) {
		// Medal.一本の雷で一度にXX体感電させた.name();

		MainMap.addEffect(new Lightning_First_Effect(p, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				// System.out.println("THUNDER"+appo);
				Message.set(Color.ORANGE.toString(), "電気が走る！",
						Color.WHITE.toString());
				ArrayList<Point> list = new ArrayList<Point>();
				list.add(p);
				nextLightning(p, list, obj, source, MapList.getCreature(p),
						damage);
				// thunderDamage(p, new ArrayList<Point>(), obj, source,
				// damage);
			}
		}), true);
	}

	private static void thunderDamage(Point p, ArrayList<Point> list,
			Object obj, Base_Creature source, int damage) {
		if (list.contains(p))
			return;
		list.add(p);
		Base_Creature c = MapList.getCreature(p);
		if (c != null) {
			damage(p, list, obj, source, c, damage);
		} else if (MassCreater.getMass(p).WATER) {
			runWater(p, list, obj, source, damage);
		}
	}

	private static boolean withinScreen(Point point) {
		point = ObjectPoint.getScreenPointRelPlayer(ObjectPoint
				.getDangeonScreenPoint(point));
		return !WithinOutofScreen.isOutside(point.x, point.y, MAP.TILE_SIZE,
				MAP.TILE_SIZE);
	}
}
