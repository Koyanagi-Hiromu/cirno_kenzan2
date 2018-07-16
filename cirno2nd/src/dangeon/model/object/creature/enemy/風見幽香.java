package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.ItemDetail;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印魔;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.MasterSpark;

public class 風見幽香 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 風見幽香(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (LV == 1 && !player_is_in_sight) {
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
		return "マスタースパークによって消し炭になった";
	}

	private void effect() {
		direction = DIRECTION.getDirectionToPlayer(this);
		setAnimation(new MasterSpark(this, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				effect(getDam());
			}
		}));
	}

	private void effect(int effect_damage) {
		Message.set(getColoredName(), "はマスタースパークを放った");
		Point p = getMassPoint().getLocation();
		DIRECTION d = getDirection();
		while (MassCreater.getMass(p) != Mass.nullpo) {
			p.translate(d.X, d.Y);
			Mass mass = MassCreater.getMass(p);
			if (mass.DIGGABLE) {
				mass.setDigged(true);
			}
			Base_Creature c = MapList.getCreature(p);
			if (c != null) {
				effectDamage(c, effect_damage);
			}
		}
		MassCreater.retakeMassSet();
	}

	private void effectDamage(Base_Creature c, int effect_damage) {
		int damage = effect_damage;
		if (c == Player.me) {
			damage = 印魔.effect(damage);
		}
		Damage.damage(this, null, damageMessage(), this, c, damage);
	}

	// @Override
	// public int getFootY() {
	// int h = 50;
	// if (getAnimation() != null) {
	// h *= 2;
	// }
	// return -h + MAP.Y_OFFSET;
	// }

	@Override
	public boolean enchantEnemySpecialAction() {
		Player.me.setSecondAnimation(new MasterSpark(Player.me, new Task() {
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
					if (!MassCreater.getMass(p).WALKABLE) {
						MassCreater.dig(p, true);
					}
					if (MapList.getEnemy(p) != null) {
						Damage.damage(null, null, null, Player.me,
								MapList.getEnemy(p), 50);
					}
				}
				MassCreater.retakeMassSet();
			}
		}));
		return true;
	}

	private int getDam() {
		switch (LV) {
		case 1:
			return 30;
		case 2:
			return 40;
		}
		return 50;
	}

	@Override
	protected Base_Artifact getDropItem() {
		return ItemTable.itemReturn(mass_point, ItemDetail.GRASS);
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, 0);
		}
		return super.getImage();
	}

	@Override
	public int getItemDropParcent() {
		return super.getItemDropParcent() * 2;
	}

	@Override
	public int isKamin() {
		return LV == 2 ? 100 : super.isKamin();
	}

	private boolean railgun() {
		if (check()) {
			effect();
			return true;
		} else {
			return false;
		}
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
