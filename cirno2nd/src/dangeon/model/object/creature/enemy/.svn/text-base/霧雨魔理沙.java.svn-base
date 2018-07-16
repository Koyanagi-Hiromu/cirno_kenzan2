package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印魔;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.MarisaSpark;

public class 霧雨魔理沙 extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private static final int effect_damage = 30;

	public 霧雨魔理沙(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (!player_is_in_sight) {
			return false;
		}
		Point player_point = Player.me.getMassPoint();
		if (getMassPoint().x == player_point.x) {
			return true;
		} else if (getMassPoint().y == player_point.y) {
			return true;
		} else if (Math.abs(getMassPoint().x - player_point.x) == Math
				.abs(getMassPoint().y - player_point.y)) {
			return true;
		} else {
			return false;
		}
	}

	private String damageMessage() {
		return "マスタースパークによって消し炭となった";
	}

	private void effect() {
		Message.set(getColoredName(), "はマスタースパークを放った");
		setAnimation(new MarisaSpark(this, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				effect(LV == 4 ? 150 : effect_damage);
			}
		}));
	}

	private void effect(int effect_damage) {
		Point p = getMassPoint().getLocation();
		DIRECTION d = getDirection();
		while (MassCreater.getMass(p) != Mass.nullpo) {
			p.translate(d.X, d.Y);
			Base_Creature c = MapList.getCreature(p);
			if (c != null) {
				effectDamage(c, effect_damage);
			}
		}
	}

	private void effectDamage(Base_Creature c, int effect_damage) {
		int damage = effect_damage;
		if (c == Player.me) {
			damage = 印魔.effect(damage);
		}
		Damage.damage(this, null, damageMessage(), this, c, damage);
	}

	// public Image getImage() {
	// return main.res.IMAGE.幽香.getImage(direction, MainThread.getKoma());
	// }

	@Override
	public boolean enchantEnemySpecialAction() {
		Player.me.setSecondAnimation(new MarisaSpark(Player.me, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Point p = Player.me.getMassPoint().getLocation();
				DIRECTION d = Player.me.getDirection();
				while (true) {
					p.x += d.X;
					p.y += d.Y;
					if (MassCreater.isPointOutOfDangeon(p)) {
						break;
					}
					if (MapList.getEnemy(p) != null) {
						Damage.damage(null, null, null, Player.me,
								MapList.getEnemy(p), 50);
					}
				}
			}
		}));

		return true;
	}

	@Override
	protected void init1() {
	}

	@Override
	protected void init2() {
		set2timesWalk();
	}

	@Override
	protected void init3() {
		set3timesWalk();
	}

	@Override
	protected void init4() {
	}

	private boolean railgun() {
		if (check()) {
			effect();
			return true;
		} else {
			return false;
		}
	}

	protected boolean specialAction() {
		if (railgun()) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		if (special_ok) {
			if (railgun()) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean specialCheck() {
		if (!check()) {
			return false;
		}
		if (isSpecialParcent()) {
			return true;
		}
		return false;
	}

}
