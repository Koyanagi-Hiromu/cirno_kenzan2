package dangeon.controller;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.arrow.大砲の弾;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印食;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.pot.Base_Pot_Selective;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.姫海棠はたて;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.MissEffect;
import dangeon.view.constant.MAP;
import dangeon.view.detail.MainMap;
import main.res.SE;
import main.util.DIRECTION;

public class ThrowingItem {
	public enum HowToThrow {
		NORMAL(MAP.MoveBulletSpeed), SLOW(MAP.MoveSpeed), MAGIC(
				MAP.MoveBulletSpeed), BREAK(MAP.MoveBulletSpeed), PARABOLA(0), PARABOLA_NAKUNARANAI(
				0);
		private final int SPEED;

		private HowToThrow(int speed) {
			SPEED = speed;
		}
	}

	private final int PLAYER_MISS = 10;
	private final int ENEMY_MISS = 20;

	private int first_sleep = 3;
	public final Base_Artifact A;
	public final Base_Creature C;
	public boolean ENTO;
	public boolean DIGG;
	public final HowToThrow HOW;
	private static ThrowingItem me;

	private boolean flag_reflect = false;

	public boolean flag_continue_throwing = false;

	private boolean flag_ento_launch = false;

	private static int THROW_MAX;

	public static boolean launch(Base_Artifact a, boolean ento) {
		return launch(a, ento, 10);
	}

	public static boolean launch(Base_Artifact a, boolean ento, int throw_max) {
		int x = a.getDirection().X;
		int y = a.getDirection().Y;
		if (ento) {
			THROW_MAX = Math.max(MassCreater.WIDTH, MassCreater.HEIGHT);
		} else {
			THROW_MAX = throw_max;
		}
		boolean out = false;
		Point mass_point = a.getMassPoint();
		int i = 0;
		for (; i < THROW_MAX; i++) {
			mass_point.translate(x, y);
			if (MassCreater.getMass(mass_point).X < 0) {
				mass_point.translate(-x, -y);
				out = true;
			} else {
				if (a.isHitToItem()) {
					Base_Artifact A = MapList.getArtifact(mass_point);
					if (A != null) {
						if (A instanceof Base_Device) {
							if (A instanceof Stairs)
								break;
						} else {
							break;
						}
					}
				}
				Base_Creature c = MapList.getCreature(mass_point);
				if (c != null) {
					if (!(c.isSkillActive() && c.isThroughItem(a))) {
						break;
					}
				}
				if (!ento) {
					if (!MassCreater.getMass(mass_point).WALKABLE
							&& !a.isNeglectiveForWall()) {
						break;
					}
				}
			}
		}
		if (i > 0) {
			Point back = a.getDirection().getReverse()
					.getFrontPoint(a.getMassPoint().getLocation());
			if (姫海棠はたて.isShotSpoit(back, a)) {
				a.getMassPoint().setLocation(back);
				a.setFateToBeDeleted();
			}
		}
		// if (!MassCreater.getMass(mass_point).WALKABLE) {
		// mass_point.translate(-x, -y);
		// }
		a.setMoveAnimating(true);
		// a.jumped_away_y = -10;
		return out;
	}

	private ThrowingItem(Base_Artifact a, Base_Creature c) {
		this(a, c, false, HowToThrow.PARABOLA_NAKUNARANAI);
		first_sleep = 0;
	}

	public ThrowingItem(Base_Artifact a, Base_Creature c, boolean ento) {
		this(a, c, ento, HowToThrow.NORMAL);
	}

	public ThrowingItem(Base_Artifact a, Base_Creature c, boolean ento,
			HowToThrow how) {
		A = a;
		C = c;
		ENTO = ento;
		HOW = how;
		me = this;
		if (how == HowToThrow.NORMAL || how == HowToThrow.SLOW)
			A.initScreenPoint();
	}

	private void bounce(Point _p) {
		Point p = getBounceLocation(_p);
		if (p == null) {
			fall(_p);
		} else {
			flag_reflect = true;
			SE.TIME_STOP.play();
			_p.translate(A.getDirection().X, A.getDirection().Y);
			A.direction = DIRECTION.getDirection(_p, p);
			A.getMassPoint().setLocation(p);
			A.setMassPoint_ParabolaJumpAttack(p);
			ThrowingItem th;
			if (HOW == HowToThrow.NORMAL) {
				th = new ThrowingItem(A, C);
			} else {
				th = new ThrowingItem(A, C, false, HowToThrow.PARABOLA);
			}
			first_sleep = 0;
			TaskOnMapObject.setThrow(th);
			th.flag_reflect = true;
			A.upDate();
			A.upDate();
			flag_continue_throwing = true;
		}
	}

	private void doFinalCalculation() {
		flag_continue_throwing = false;
		A.upDate(HOW.SPEED * 2);
		Base_Creature object = MapList.getCreature(A.getMassPoint());
		if (reflectCheck(object)) {
			reflect();
		} else {
			if (HOW == HowToThrow.MAGIC) {
				doMagic();
			} else {
				doNormal();
			}
			if (!flag_continue_throwing) {
				TaskOnMapObject.setThrow(null);
				me = null;
			}
		}
	}

	private void doMagic() {
		if (MassCreater.getMass(A.getMassPoint()).WALKABLE
				|| A.isNeglectiveForWall()) {
			Base_Creature object = MapList.getCreature(A.getMassPoint());
			doMagic(object);
		} else {
			// 壁
			if (A instanceof MagicBullet) {
				if (((MagicBullet) A).STAFF.hitWall(this)) {
					Base_Creature object = MapList
							.getCreature(A.getMassPoint());
					doMagic(object);
				}
			}
		}
	}

	private void doMagic(Base_Creature object) {
		if (object != null) {
			A.itemHitCheck(false, C, object);
			entoCheck();
		} else if (A.isHitToItem()) {
			A.staticCheck();
			Base_Artifact a = MapList.getArtifact(A.getMassPoint());
			if (a != null) {
				A.itemHitCheck(false, C, a);
				entoCheck();
			}
		}
	}

	private void doNormal() {
		Point p = A.getMassPoint();
		if (MapList.getCreature(p) != null) {
			reachAtCreature(!ENTO);
			entoCheck();
		} else if (!MassCreater.getMass(p).WALKABLE) {
			if (!ENTO) {
				// 壁に激突
				if (A instanceof 大砲の弾) {
					((大砲の弾) A).explode(p, C);
					return;
				}
				p.translate(-A.getDirection().X, -A.getDirection().Y);
				if (!podBreak()) {
					if (C.equals(Player.me)
							&& !flag_reflect
							&& EnchantSpecial
									.enchantSimbolAllCheck(ENCHANT_SIMBOL.弾)) {
						bounce(p);
					} else
						fall(p);
				}
			} else {
				if (A instanceof Base_Pot_Selective) {
					if (((Base_Pot_Selective) A).isMax()) {
						Medal.中身が詰まった瓶を遠投した.addCount();
					}
				}
				Message.set(A.getColoredName().concat("は"), "消えてしまった･･･");
				// MapList.removeArtifact(A);
			}
		} else {
			if (!ENTO) {
				// 自由落下
				if (A instanceof 大砲の弾) {
					((大砲の弾) A).explode(p, C);
					return;
				} else {
					fall(p);
				}
			} else {
				Message.set(A.getColoredName().concat("は"), "消えてしまった･･･");
				// MapList.removeArtifact(A);
			}
		}
	}

	private void entoCheck() {
		if (ENTO) {
			if (!isAnimationExist()) {
				launch(A, ENTO);
				flag_ento_launch = false;
			} else {
				flag_ento_launch = true;
			}
			flag_continue_throwing = true;
			return;
		}
	}

	private void fall(Point p) {
		if (HOW == HowToThrow.NORMAL || HOW == HowToThrow.PARABOLA_NAKUNARANAI)
			ItemFall.itemFall_TrapCheck(p, A);
		if (A.getClass().equals(Stairs.class)) {
			Mass m = MassCreater.getMass(p);
			if (m.isHoly() || m.isShop())
				Medal.階段を杖で移動させて敷地内に入れた.addCount();
		}
	}

	private Point getBounceLocation(Point p) {
		ArrayList<Point> old = new ArrayList<Point>();
		ArrayList<Point> now = new ArrayList<Point>();
		now.add(p.getLocation());
		int length = 4;
		for (int i = 0; i < length; i++) {
			Point _p = getBouncePoint(now, old);
			if (_p != null)
				return _p;
		}
		return null;
	}

	private Point getBouncePoint(ArrayList<Point> now, ArrayList<Point> old) {
		for (Point p : now) {
			Base_Creature c = MapList.getCreature(p);
			if (c != null && !c.equals(Player.me)) {
				return p;
			}
			old.add(p);
		}
		ArrayList<Point> next = new ArrayList<Point>();
		for (Point p : now) {
			if (!MassCreater.getMass(p).WALKABLE)
				continue;
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				Point point = new Point(p.x + d.X, p.y + d.Y);
				if (!old.contains(point))
					next.add(point);
			}
		}
		now.clear();
		for (Point p : next)
			now.add(p);
		return null;
	}

	private boolean isAnimationExist() {
		if (MainSystem.isDemandForWaitExist()) {
			return true;
		}
		return false;
	}

	public boolean isFirstSleeping() {
		return first_sleep > 0;
	}

	public boolean isReflected() {
		return flag_reflect;
	}

	private int isThrowingMiss(Base_Creature target) {
		if (target instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
					ENCHANT_SIMBOL.身躱し)) {
				return 100;
			}
		}
		if (C instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK,
					ENCHANT_SIMBOL.遠投)) {
				return 0;
			}
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK,
					ENCHANT_SIMBOL.必中)) {
				return 0;
			}
			if (A instanceof Base_Grass)
				return 0;
			
			return PLAYER_MISS;
		}
		return ENEMY_MISS;
	}

	private boolean podBreak() {
		if (A instanceof Base_Pot) {
			return ((Base_Pot) A).broken();
		}
		return false;
	}

	private void reachAtCreature(boolean fall) {
		if (A instanceof Base_Device) {
			ItemFall.itemFall(A);
			return;
		}
		Point p = A.getMassPoint();
		Base_Creature object = MapList.getCreature(A.getMassPoint());
		if (!ENTO && object instanceof Player && (印食.effect(this))) {
			Message.set(object.getColoredName(), "は", A.getColoredName(),
					"を食べて満腹度が10回復した");
			return;
		}
		if (A instanceof 大砲の弾) {
			((大砲の弾) A).explode(p, C);
			return;
		}
		if (HOW == HowToThrow.BREAK) {
			if (object.isThroughItem(A)) {
				return;
			}
		}
		if (new R().is(isThrowingMiss(object))) {
			MainMap.addEffect(new MissEffect(object, true));
			if (this.C instanceof Player) {
				Message.set(A.getName(), "は避けられた");
			} else if (object instanceof Player) {
				Message.set(A.getName(), "を避けた");
			} else {
				Message.set(A.getName(), "は当たらなかった");
			}
			if (fall) {
				fall(p);
			}
		} else {
			// HIT
			if (A.itemHitCheck(!fall, C, object) && fall) {
				if (A.getListComposition().contains(ENCHANT_SIMBOL.金)) {
					A.getListComposition().remove(ENCHANT_SIMBOL.金);
					Message.set(A.getColoredName(), "の雛の加護が剥がれた");
					fall(p);
				} else if (A.isNoWasteWithThrowing()) {
					fall(p);
				} else {
					podBreak();
				}
			}
		}
	}

	private void reflect() {
		flag_reflect = true;
		A.direction = A.direction.getReverse();
		if (HOW == HowToThrow.PARABOLA
				|| HOW == HowToThrow.PARABOLA_NAKUNARANAI) {
			if (C != null) {
				A.setMassPoint_ParabolaJumpAttack(C.getMassPoint());
			} else {
				if (THROW_MAX == 0) {
					THROW_MAX = 10;
				}
			}
		}
		ThrowingItem.launch(A, ENTO, THROW_MAX);
		flag_continue_throwing = true;
	}

	private boolean reflectCheck(Base_Creature object) {
		if (object == null)
			return false;
		if (flag_reflect)
			return false;
		return object.isSkillActive() && object.reflection(this);
	}

	public void setReflected(boolean b) {
		flag_reflect = b;
	}

	public void upDate() {
		if (flag_ento_launch) {
			if (!isAnimationExist()) {
				launch(A, ENTO);
				flag_ento_launch = false;
			}
			TaskOnMapObject.work_task();
			return;
		}
		if (first_sleep > 0) {
			if (--first_sleep > 0) {
				return;
			}
		}
		if (A.isAnimating()) {
			if (HOW.SPEED != 0) {
				A.upDate(HOW.SPEED * 2);
			} else {
				A.upDate();
			}
		} else {
			if (A.isFateToBeDeleted()) {
				A.upDate(HOW.SPEED * 2);
				TaskOnMapObject.setThrow(null);
			} else {
				doFinalCalculation();
			}
		}
	}
}
