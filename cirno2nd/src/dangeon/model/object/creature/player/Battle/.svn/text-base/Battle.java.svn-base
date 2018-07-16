package dangeon.model.object.creature.player.Battle;

import main.res.SE;
import main.util.FrameShaker;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印会心;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印嫉妬;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印密;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印必中;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印面;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印鼠;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.MissEffect;
import dangeon.view.detail.MainMap;

public class Battle {
	private static int player_attack_miss = 5;
	private static boolean flag_critical = false;
	private static boolean flag_miss = false;

	public static void attackCritical() {
		Message.set("クリティカル");
		FrameShaker.doneSoftly();
		SE.DAMAGED_CRITICAL.play();
	}

	public static void attackMiss(Base_Creature c) {
		MainMap.addEffect(new MissEffect(c, false));
		Message.set(new String[] { "攻撃が外れた", "" });
		印嫉妬.miss();
		unsetCriticalFlag();
	}

	public static boolean checkCritical(Base_Creature c) {
		boolean b = false;
		if (印嫉妬.isEffect() || 印鼠.effect() || 印面.effect(c)
				|| Player.me.getConditionList().contains(CONDITION.超会心)
				|| 印会心.effect() || 印嫉妬.combo(c) || flag_critical) {
			印密.effect();
			b = true;
		}
		flag_critical = false;
		return b;
	}

	private static int getAttackMissParcent() {
		return PresentField.get().isHaraheru() ? player_attack_miss : 0;
	}

	public static boolean isCritical() {
		return flag_critical;
	}

	public static boolean isHit(Base_Creature c) {
		if (印鼠.effect() || 印面.effect(c) || 印必中.effect() || 印嫉妬.combo(c)
				|| !new R().is(getAttackMissParcent())) {
			flag_miss = false;
			return true;
		}
		return EnchantSpecial
				.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.必中);
	}

	/**
	 * 通常攻撃前に呼び出せばクリティカル攻撃となる
	 */
	public static void setCriticalFlag() {
		flag_critical = true;
	}

	public static void setHitFlag() {
		flag_miss = true;
	}

	public static void unsetCriticalFlag() {
		flag_critical = false;
	}

}
