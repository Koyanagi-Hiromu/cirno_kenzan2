package dangeon.model.object.creature.action;

import java.awt.Point;
import java.util.ArrayList;

import main.res.Image_Player;
import main.util.DIRECTION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;

/**
 * 転び
 * 
 */
public class Tumble {

	private static Point drop(Point p, DIRECTION d, int delt) {
		Point _p = p.getLocation();
		if (d.X == 0 || d.Y == 0) {
			if (d.X == 0) {
				_p.translate(delt, 0);
			} else {
				_p.translate(0, delt);
			}
		} else {
			if (delt < 0) {
				if (d.X > 0) {
					_p.translate(-delt, 0);
				} else {
					_p.translate(delt, 0);
				}
			} else if (delt > 0) {
				if (d.Y > 0) {
					_p.translate(0, delt);
				} else {
					_p.translate(0, -delt);
				}
			}
		}
		return _p;
	}

	public static void enemy(Base_Creature c, DIRECTION d, int length) {
		if (!c.isItemDrop()) {
			return;
		}
		c.setOnDropItem();
	}

	private static void move(Base_Creature c, DIRECTION d, int length) {
		c.direction = d;
		if (length == 0) {
			return;
		}
		Point p = new Point(c.getMassPoint().x + d.X * length,
				c.getMassPoint().y + d.Y * length);
		c.setMassPoint_WalkLike(p, 3);
	}

	public static boolean player(DIRECTION d, int length, int MAX) {
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.融合)) {
			return true;
		}
		Point p = Player.me.getMassPoint().getLocation();
		if (!MassCreater.getMass(p, d).WALKABLE)
			return false;
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a.isEnchantedNow()) {
				continue;
			}
			list.add(a);
		}
		boolean flag = false;
		for (int i = MAX; i > 0; i--) {
			p.translate(d.X, d.Y);
			for (int j = -i / 2; j <= i / 2; j++) {
				if (list.size() == 0) {
					return flag;
				}
				Base_Artifact a = list.get((int) (new R().nextDouble() * list
						.size()));
				Point _p = drop(p, d, j);
				if (!MapInSelect.isEmptyArtifactPoint(_p)) {
					continue;
				}
				flag = true;
				a.setMassPoint(Player.me.getMassPoint());
				MapList.addArtifact(a);
				a.setMassPoint_ParabolaJump_NoAttack_PotBreak(_p);
				list.remove(a);
				Belongings.remove(a);
			}
		}
		return flag;
		// for (int i = 0; i < MAX; i++) {
		// p.translate(d.X, d.Y);
		// for (int j = -i; j <= i; j++) {
		// Base_Artifact a = list.get((int) (new A().nextDouble() *
		// list.size()));
		// Point _p = drop(p, d, j);
		//
		// list.remove(a);
		// Belongings.remove(a);
		// if (list.size() == 0) {
		// return;
		// }
		// }
		// }
	}

	public static void tumble(Base_Creature c, DIRECTION d, int length) {
		tumble(c, d, length, 2);
	}

	public static void tumble(Base_Creature c, DIRECTION d, int length, int max) {
		// move(c, d, length);
		if (c instanceof Player) {
			if (player(d, length, max))
				Image_Player.down.set(3);
		} else {
			enemy(c, d, length);
		}
	}
}
