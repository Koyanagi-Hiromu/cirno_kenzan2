package dangeon.model.object.creature.resistant;

import main.res.SE;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印毒消し;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

public class Poison {
	/**
	 * 
	 * @param c
	 *            クリーチャー
	 * @param i
	 *            力 / i + 1
	 * @param max_str
	 *            力の最大値可否
	 * @param slow
	 */
	public static void effect(Base_Creature c, int i, boolean max_str,
			boolean slow) {
		if (c.getHP() <= 0) {
			return;
		}
		if (c instanceof Player) {
			if (印毒消し.effect()) {
				return;
			}
			if (!max_str) {
				SE.POIZON.play();
				if (Player.me.getNoEnchantStr() == 1) {
					Message.set(c.getColoredName(), "のちからはもう下がらない");
				} else {
					Player.me.addStr(-i);
					if (Player.me.getNoEnchantMaxStr() == 1) {
						Message.set(c.getColoredName(), "のちからが１になった");
					} else {
						Message.set(c.getColoredName(), "のちからが",
								半角全角コンバーター.半角To全角数字(i), "下がった");
					}
				}
			} else {
				SE.POIZON.play();
				Player.me.changeMAX_STR(-i);
				Message.set(c.getColoredName(), "のちからの最大値が",
						半角全角コンバーター.半角To全角数字(i), "下がった");
			}
		} else {
			if (resist(c)) {
				return;
			}
			SE.POIZON.play();
			for (int j = 0; j < i; j++) {
				c.setSTR(c.getSTR() * 3 / 4);
			}
			Message.set(c.getColoredName(), "のちからが下がった");
		}
		if (slow) {
			SE.STATUS_SLOW.play();
			c.setCondition(CONDITION.鈍足, 0);
		}
	}

	public static void effect(Base_Creature c, int i, boolean max_str,
			boolean slow, boolean hp) {
		if (!hp || !(c instanceof Player)) {
			effect(c, i, max_str, slow);
		} else {
			if (印毒消し.effect()) {
				return;
			}
			SE.POIZON.play();
			Player.me.addMAX_HP(-i);
			Message.set(c.getColoredName(), "のＨＰの最大値が", 半角全角コンバーター.半角To全角数字(i),
					"下がった");
			if (slow) {
				SE.STATUS_SLOW.play();
				c.setCondition(CONDITION.鈍足, 0);
			}
		}
	}

	public static boolean resist(Base_Creature c) {
		if (ResistWeakPoint.毒耐性.isResist(c)) {
			Message.set(c.getColoredName(), "に毒は効かなかった");
			return true;
		} else if (ResistWeakPoint.毒吸収.isResist(c)) {
			c.addLV(1);
			return true;
		} else
			return false;
	}

	public static void strHeal(Base_Creature c) {
		if (c instanceof Player) {
			MainMap.addEffect(new GoodBadEffect(true));
			if (Player.me.getNoEnchantStr() != Player.me.getNoEnchantMaxStr()) {
				Message.set("ちからが回復した");
				Player.me.setSTR(Player.me.getNoEnchantMaxStr());
			}
		} else {
			if (ResistWeakPoint.毒吸収.isResist(c)) {
				Damage.damage(null, null, null, Player.me, c, 999);
			} else if (c.getSTR() < c.getMAX_STR()) {
				Message.set("ちからが回復した");
				c.setSTR(c.getMAX_STR());
			}
		}
	}

}
