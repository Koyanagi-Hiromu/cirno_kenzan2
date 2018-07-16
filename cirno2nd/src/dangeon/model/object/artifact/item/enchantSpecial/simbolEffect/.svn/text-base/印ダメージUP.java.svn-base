package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.config.Config;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.上白沢慧音のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.STAGE;
import dangeon.view.anime.HitEffect;

public class 印ダメージUP extends EnchantSpecial {
	public static int damage15(int i) {
		i += 4;
		return i * 15 / 10;
	}

	public static int damage20(int i) {
		i += 4;
		return i * 2;
	}

	/**
	 * ダメージアップ（特効） 30%UP
	 */
	public static int effect(int damage, Base_Creature c) {
		HitEffect.setLargeDamaged(true);
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.月)) {
			if (!Player.me.getListAllCondition().isEmpty()) {
				return SetEnchantCard.isSetCard(上白沢慧音のカード.class) ? 印ダメージUP
						.damage20(damage) : 印ダメージUP.damage15(damage);
			}
		}
		if (印華.effectCheck()) {
			return 印華.effect(damage);
		}
		if (c instanceof Base_Enemy) {
			Base_Enemy e = (Base_Enemy) c;
			if (e.getLargement() > 0) {
				if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.大)) {
					return damage15(damage);
				}
			}
			if (Config.isAccessableToDetail(e, e.getLV())) {
				if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.縁)) {
					return damage15(damage);
				}
			}
		}
		if (stageMatch(c)) {
			return damage15(damage);
		}
		if (印邪.checkLargeDamage()) {
			return damage15(damage);
		}
		HitEffect.setLargeDamaged(false);
		return damage;
	}

	public static int fixDamage(int damage) {
		// int tyouzin = SetEnchantCard.isSetCard(聖白蓮のカード.class) ? 10 : 5;
		// if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.超人)) {
		// damage += tyouzin;
		// }
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.槌)) {
			damage += 2;
		}
		return damage;
	}

	public static boolean stageMatch(Base_Creature c) {
		for (STAGE category : c.getCategory()) {
			if (enchantSimbolAllCheck(CASE.ALL, category.EC)) {
				return true;
			}
		}
		return false;
	}
}
