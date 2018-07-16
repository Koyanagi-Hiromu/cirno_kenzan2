package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 印驚 extends EnchantSpecial {
	public static boolean ef() {
		DIRECTION d = Player.me.getDirection();
		Point p = Player.me.getMassPoint().getLocation();
		Base_Creature e = MapInSelect.getStraightMoveCheck(d, p, 0);
		if (e == null) {
			return false;
		}
		Point _p = e.getMassPoint().getLocation();
		Point f_p = new Point(_p.x - d.X, _p.y - d.Y);
		SE.WARP_INSTANT.play();
		Player.me.setMassPoint_WalkLike(f_p, 10);
		Player.me.attack_point = f_p;
		return true;
	}

	public static boolean effect() {
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.驚)) {
			if (ef()) {
				return true;
			}
		}
		return false;
	}
}
