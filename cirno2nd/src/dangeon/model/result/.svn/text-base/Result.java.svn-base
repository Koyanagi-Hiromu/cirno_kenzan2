package dangeon.model.result;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.鉄の矢;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.grass.ドラゴン草;
import dangeon.model.object.artifact.item.grass.鳳凰の種;
import dangeon.model.object.artifact.item.ring.復活のリボン;
import dangeon.model.object.artifact.item.spellcard.八意永琳のカード;
import dangeon.model.object.artifact.item.spellcard.藤原妹紅のカード;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ゆっくり霊夢;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

public class Result {
	/**
	 * 仮
	 */
	public static String getMessage(Object obj, String str) {
		medalCheck(obj, str);
		if (obj != null && !(obj instanceof Base_MapObject)) {
			return obj.toString();
		} else if (str != null) {
			return str;
		} else if (obj != null) {
			return message(obj);
		} else {
			return message();
		}
	}

	private static void medalCheck(Object obj, String str) {
		if (str == null)
			str = "";
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a.isUsable()) {
				boolean flag = false;
				flag = flag || 藤原妹紅のカード.class.isInstance(a);
				flag = flag || 八意永琳のカード.class.isInstance(a);
				flag = flag || 復活のリボン.class.isInstance(a);
				flag = flag || 鳳凰の種.class.isInstance(a);
				if (flag) {
					Medal.復活系のアイテムを残して倒れた.addCount();
					break;
				}
			}
		}
		if (Player.me.conditionCheck(CONDITION.睡眠)) {
			Medal.眠っている間に倒れた.addCount();
		}
		if (PresentField.get().getMaxFloor() > 49
				&& PresentField.get().getMaxFloor() - MapList.getFloor() < 4) {
			Medal.クリア間近で倒れた.addCount();
		}
		if (鉄の矢.class.isInstance(obj)) {
			Medal.ナイフを反射されて倒れた.addCount();
		} else if (str.equals("お腹を下して倒れた") || str.equals("神に祟られて倒れた")) {
			Medal.おにぎりを食べて倒れた.addCount();
		} else if (str.equals("空中から敵にぶつかった衝撃で倒れた")) {
			Medal.空中から敵にぶつかって倒れた.addCount();
		} else if (str.equals("奇跡が起こって倒れた")) {
			Medal.奇跡が起こって倒れた.addCount();
		} else if (ドラゴン草.class.isInstance(obj)) {
			Medal.アチチ草によって燃え尽きた.addCount();
		} else if (ゆっくり霊夢.class.isInstance(obj)) {
			if (((Base_Enemy) obj).getLV() == 1)
				Medal.ゆっくりに倒された.addCount();
		} else if (obj != null && obj.equals(ENCHANT_SIMBOL.喘息)) {
			Medal.咳をして倒れた.addCount();
		}
	}

	private static String message() {
		return "原因不明の熱病で倒れた";
	}

	private static String message(Object obj) {
		// if (!(obj instanceof Base_MapObject)) {
		// return obj.toString();
		// }
		if (obj instanceof Base_Artifact) {
			return (((Base_MapObject) obj).getColoredName() + "が反射されて倒れた");
		} else {

			return (((Base_MapObject) obj).getColoredName() + "によって倒された");
		}
	}
}
