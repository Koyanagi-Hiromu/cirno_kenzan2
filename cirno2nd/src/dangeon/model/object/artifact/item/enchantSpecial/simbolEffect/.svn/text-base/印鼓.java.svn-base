package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.bullet.雷弾;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;

public class 印鼓 extends EnchantSpecial {
	private static int explosion = 50;
	static ENCHANT_SIMBOL sim = ENCHANT_SIMBOL.鼓;

	public static boolean effect() {
		if (!enchantSimbolAllCheck(sim)) {
			return false;
		}
		if (!parcentCheck(explosion, sim)) {
			return false;
		}
		new 雷弾(Player.me, 25).itemThrow(Player.me, HowToThrow.MAGIC,
				Math.max(MassCreater.WIDTH, MassCreater.HEIGHT));
		return true;
	}
}
