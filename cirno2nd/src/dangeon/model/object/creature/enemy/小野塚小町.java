package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 小野塚小町 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 小野塚小町(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (!player_is_in_sight) {
			return false;
		}
		// if (playerInSearch()) {
		// return enemySearch();
		// }
		return playerInSearch(false);
	}

	private void effect(DIRECTION d) {
		direction = converDirection(Player.me.getMassPoint());
		SE.WARP_INSTANT.play();
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.イカリ)) {
			Point p = Player.me.getMassPoint().getLocation();
			p.translate(-d.X, -d.Y);
			Player.me.setMassPoint_WalkLike(p, 3);
			if (mass_point.x != p.x - d.X || mass_point.y != p.y - d.Y) {
				direction = converDirection(Player.me.getMassPoint());
				startAttack(null);
				return;
			}
		} else {
			Point p = getMassPoint().getLocation();
			p.translate(d.X, d.Y);
			Player.me.setMassPoint_WalkLike(p, 3);
		}
		new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			private boolean flag = false;

			@Override
			public boolean isDemandToContinue() {
				return flag;
			}

			@Override
			public void work() {
				flag = Player.me.isAnimating();
				if (!flag) {
					Message.set(getColoredName().concat("は")
							.concat(Player.me.getColoredName())
							.concat("を目の前に引き寄せ攻撃をした"));
					attack();
				}
			}
		}.work_appointment();
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		// FIXME
		Message.set("未実装");
		return true;
	}

	@Override
	public int isKamin() {
		if (LV == 4)
			return 0;
		return super.isKamin();
	}

	private boolean playerInSearch(boolean effect) {
		if (playerNeiber()) {
			return false;
		}
		for (DIRECTION dir : DIRECTION.values_exceptNeatral()) {
			Base_Creature e = MapInSelect.getStraightHitCreature(dir,
					getMassPoint(), 0);
			if (e != null && e instanceof Player) {
				if (MapList.getEnemy(getMassPoint().x + dir.X, getMassPoint().y
						+ dir.Y) == null) {
					if (effect) {
						effect(dir);
						return true;
					} else {
						return true;
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private boolean playerNeiber() {
		Point p = Player.me.getMassPoint();
		Point p2 = getMassPoint();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (p2.x + i == p.x && p2.y + j == p.y) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		if (!player_is_in_sight) {
			return false;
		}
		return playerInSearch(true);
	}

	@Override
	protected boolean specialCheck() {
		if (!isSpecialParcent()) {
			return false;
		}
		if (check()) {
			return true;
		}
		return false;
	}

}
