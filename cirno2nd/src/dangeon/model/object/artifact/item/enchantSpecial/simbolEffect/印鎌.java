package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.小野塚小町のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.Battle.Battle;

public class 印鎌 extends EnchantSpecial {
	public static boolean effect(Point p) {
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.間)) {
			Point origine = p.getLocation();
			DIRECTION d = Player.me.getDirection();
			Base_Enemy e = MapList.getEnemy(p);
			Base_Artifact a = null;
			if (e != null) {
				return false;
			}
			while (true) {
				p.translate(d.X, d.Y);
				if (!MassCreater.getMass(p).WALKABLE) {
					return false;
				}
				e = MapList.getEnemy(p);
				if (e != null) {
					if (e instanceof Base_NPC) {
						return false;
					}
					SE.WARP_INSTANT.play();
					Point _p = origine.getLocation();
					_p.translate(d.X, d.Y);
					e.setMassPoint_WalkLike(_p, 8);
					if (SetEnchantCard.isSetCard(小野塚小町のカード.class)) {
						Battle.setCriticalFlag();
					}
					Battle.setCriticalFlag();
					return true;
				}
			}
		}
		return false;
	}
}
