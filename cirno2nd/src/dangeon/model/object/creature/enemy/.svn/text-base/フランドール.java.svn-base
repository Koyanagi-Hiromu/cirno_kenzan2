package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.util.WithinOutofScreen;

public class フランドール extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private boolean flag_digg;

	private int spec = -1;

	public フランドール(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		int length = LV == 4 ? -1 : LV;
		return range(length, true);
	}

	protected void ddn(final Point p, int speed, DIRECTION dir,
			final boolean critical) {
		Message.set("デデーン！");
		Player.me.startDamaging();
		Player.me.setMassPoint_WalkLike(p, speed);
		Point _p = new Point(p.x - dir.X, p.y - dir.Y);
		final Base_Enemy e = this;
		setMassPoint_WalkLike(_p, speed, new Task() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				spec = -1;
				Mass mass = MassCreater.getMass(p);
				if (mass.DIGGABLE) {
					SE.DIGG.play();
					MassCreater.dig(p, true);
					MassCreater.retakeMassSet();
				}
				if (critical)
					startAttack(new Task() {
						/**
						 *
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							Damage.enemyCriticalAttack(e);
						}
					});
				else
					attack();
			}
		});
	}

	private void digg() {
		flag_digg = false;
		ArrayList<DIRECTION> list = diggable();
		if (list.isEmpty())
			return;
		DIRECTION d = list.get(new R().nextInt(list.size()));
		final ArrayList<Mass> mass_list = new ArrayList<Mass>(LV);
		Point p = getMassPoint().getLocation();
		int i = 0;
		for (; i < LV; i++) {
			p.translate(d.X, d.Y);
			if (diggable(p)) {
				mass_list.add(MassCreater.getMass(p));
			} else {
				break;
			}
		}
		if (mass_list.isEmpty()) {
			return;
		}
		direction = d;
		if (WithinOutofScreen.isOutside(this)) {
			digg(mass_list, i);
		} else {
			final int I = i;
			startAttack(new Task() {
				/**
			 *
			 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					digg(mass_list, I);
				}
			});
		}
	}

	private void digg(ArrayList<Mass> mass_list, int i) {
		Message.set(getColoredName(), "は壁を", 半角全角コンバーター.半角To全角数字(i), "マス掘った");
		SE.DIGG.play();
		for (Mass mass : mass_list) {
			mass.setDigged(true);
		}
		MassCreater.retakeMassSet();
	}

	private ArrayList<DIRECTION> diggable() {
		ArrayList<DIRECTION> list = new ArrayList<DIRECTION>(4);
		for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
			Point p = d.getFrontPoint(getMassPoint().getLocation());
			if (diggable(p)) {
				list.add(d);
			}
		}
		return list;
	}

	private boolean diggable(Point p) {
		Mass mass = MassCreater.getMass(p);
		return !mass.WALKABLE && mass.isDiggable()
				&& MapList.getCreature(p) == null;
	}

	private void effect(final Point p, final DIRECTION dir) {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.イカリ)) {
			Message.set(Player.me.getColoredName(), "は重くて動かせなかった");
			attack();
			return;
		}
		final int speed = 3;
		spec = 0;
		if (super.attack_possible())
			ddn(p, speed, dir, true);
		else {
			Point _point_ = Player.me.getMassPoint().getLocation();
			_point_.translate(-dir.X, -dir.Y);
			setMassPoint_WalkLike(_point_, speed, new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					ddn(p, speed, dir, true);
				}
			});
		}

	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Base_Creature c = MapInSelect.getFrontEnemyFromCreature(Player.me);
		if (c == null) {
			return false;
		}
		MapInSelect.吹き飛ばし(Player.me, "フランに吹き飛ばされて倒れた", c, Player.me.direction,
				10, Damage.getAttackDamage(true, c));
		return true;
	}

	@Override
	public Image getImage() {
		if (spec != -1) {
			spec++;
			int i = (spec < 4) ? 0 : 1;
			return IM.getSPImage(LV, direction, i);
		} else {
			return super.getImage();
		}
	}

	@Override
	public boolean isAbleToAttackWhileNeglectingWall() {
		if (isSkillActive() && LV > 1) {
			return true;
		}
		return super.isAbleToAttackWhileNeglectingWall();
	}

	@Override
	protected boolean specialAttack() {
		if (flag_digg) {
			digg();
			return true;
		} else if (check()) {
			direction = converDirection(Player.me.getMassPoint());
			if (walkableBreak(false)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean specialCheck() {
		if (flag_digg)
			return true;
		flag_digg = false;
		if (isSpecialParcent()) {
			if (check()) {
				if (walkableBreak(true)) {
					return true;
				}
			}
		}
		if (isSpecialParcent() && MassCreater.getRoom(getMassPoint()) != null) {
			if (!diggable().isEmpty()) {
				flag_digg = true;
				return true;
			}
		}
		return false;
	}

	private boolean walkableBreak(boolean check) {
		Point p = Player.me.getMassPoint().getLocation();
		DIRECTION dir = converDirection(p);
		Base_Creature c = MapInSelect.getStraightHitCreature(dir, p, 0);
		Point goal;
		if (c != null) {
			goal = c.getMassPoint().getLocation();
			goal.translate(-dir.X, -dir.Y);
			if (goal.x == p.x && goal.y == p.y) {
				return false;
			}
			if (!check) {
				effect(goal, dir);
			}
			return true;
		} else {
			goal = MapInSelect.getStraightHitPoint(dir, p, 0);
			if (diggable(goal)) {
				// 上の２個目の条件文はぱこゆかりんのチェック
				DIRECTION ds[] = DIRECTION.values_onlyBasic4();
				for (DIRECTION d : ds) {
					Mass m = MassCreater.getMass(goal.x + d.X, goal.y + d.Y);
					if (m.WALKABLE && !m.WATER) {
						if (!check) {
							effect(goal, dir);
						}
						return true;
					}
				}
			}
			goal.translate(-dir.X, -dir.Y);
			if (goal.x == p.x && goal.y == p.y) {
				return false;
			}
			if (!check) {
				effect(goal, dir);
			}
			return true;
		}
	}

}
