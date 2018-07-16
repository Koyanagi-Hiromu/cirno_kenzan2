package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Color;
import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.town.map.KoumaKan;
import dangeon.model.map.field.town.map.KoumaKan.Dark;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.Exルーミアのカード;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.view.detail.View_Sider;

public class 印つるはし extends EnchantSpecial {
	private static int pick_break = 10;

	private static boolean isDirectionUDLR(DIRECTION d) {
		if (d == DIRECTION.DOWN || d == DIRECTION.UP || d == DIRECTION.LEFT
				|| d == DIRECTION.RIGHT) {
			return true;
		}
		return false;
	}

	public static void picks() {
		Base_Artifact a = Enchant.ATK.getEnchant();
		if (a instanceof Exルーミアのカード) {
			// Event用
			if (PresentField.get() instanceof KoumaKan) {
				Point p = new Point(Player.me.getDirection().X
						+ Player.me.getMassPoint().x,
						Player.me.getDirection().Y + Player.me.getMassPoint().y);
				Base_Artifact _a = MapList.getArtifact(p);
				if (_a != null && _a instanceof Dark) {
					MapList.removeArtifact(_a);
				}
			}
		}
		if (enchantSimbolAllCheck(ENCHANT_SIMBOL.掘)) {
			picksEffect(null);
		}
		if (a != null
				&& a.getListCompositionIncludeEnchant().contains(
						ENCHANT_SIMBOL.つるはし)) {
			a = Enchant.ATK.getEnchant();
		} else {
			// なんでも装備
			a = null;
			for (Enchant enchant : Enchant.getANYS()) {
				Base_Artifact _a = enchant.getEnchant();
				if (_a != null && _a.sim == ENCHANT_SIMBOL.つるはし) {
					a = _a;
					break;
				}
			}
		}
		if (a == null) {
			// 謎印
			if (enchantSimbolAllCheck(ENCHANT_SIMBOL.つるはし)) {
				a = Enchant.ATK.getEnchant();
				if (a == null
						|| !a.getListCompositionIncludeEnchant().contains(
								ENCHANT_SIMBOL.謎)) {
					for (Enchant enchant : Enchant.getANYS()) {
						Base_Artifact _a = enchant.getEnchant();
						if (_a != null && _a.sim == ENCHANT_SIMBOL.謎) {
							a = _a;
							break;
						}
					}
				}
			}
		}
		if (a == null) {
			// 全手持ち印追加ダンジョン
			if (PresentField.get().isAllEnchantDungeon()) {
				for (Base_Artifact _a : Belongings.getListItems()) {
					if (_a.sim != null && _a.sim.equals(ENCHANT_SIMBOL.つるはし)) {
						a = _a;
						break;
					}
				}
			}
		}
		if (a != null) {
			picksEffect(a);
		}
	}

	private static boolean picksBreak(Base_Artifact a) {
		if (a.pick_count >= pick_break) {
			return true;
		}
		return false;
	}

	private static void picksEffect(Base_Artifact a) {
		boolean dig = false;
		DIRECTION dir = Player.me.getDirection();
		int dx = dir.X;
		int dy = dir.Y;
		Point p = new Point(dx + Player.me.getMassPoint().x, dy
				+ Player.me.getMassPoint().y);
		if (MapList.getTrap(p) != null) {
			// カウントは進まない
			if (MapList.getTrap(p).isVisible()
					|| Player.me.conditionCheck(CONDITION.目薬) || 印罠師.effect()) {
				SE.DIGG.play();
				Message.set(MapList.getTrap(p).getColoredName(), "を壊した");
				MapList.removeArtifact(MapList.getTrap(p));
			}
		}
		if (isDirectionUDLR(dir)) {
			if (!MassCreater.getMass(p).WALKABLE) {
				dig = MassCreater.dig(p) || dig;
				MassCreater.retakeMassSet();
			}
		}
		if (dig) {
			if (a == null) {
				return;
			}
			a.pick_count++;
			if (!picksBreak(a)) {
				if (a.pick_count >= pick_break - 1) {
					if (a.getForgeValue() > 0) {
						View_Sider.setInformation(a.getColoredName(),
								"はそろそろ劣化しそうです");
					} else {
						View_Sider.setInformation(a.getColoredName(), "はそろそろ",
								Color.RED.toString(), "壊れそう",
								Color.WHITE.toString(), "です");
					}
				} else {
					View_Sider.setInformation(a.getColoredName(), "で壁を掘りました");
				}
				return;
			}
			if (parcentCheck(10, ENCHANT_SIMBOL.つるはし)) {
				SE.BROKEN.play();
				if (a.getForgeValue() > 0) {
					Message.set(a.getColoredName(), "は修正値が下がった");
					View_Sider
							.setInformation(a.getColoredName(), "の修正が１下がりました");
					a.setForgeValue(-1);
					a.pick_count = 0;
				} else {
					Message.set(a.getColoredName(), "はくだけ散った");
					View_Sider.setInformation(a.getColoredName(), "はくだけ散りました");
					Enchant.forceToRemove(a);
					Belongings.remove(a);
				}
			} else {
				if (a.getForgeValue() > 0) {
					View_Sider
							.setInformation(a.getColoredName(), "は一定確率で劣化します");
				} else {
					View_Sider.setInformation(a.getColoredName(), "は一定確率で",
							Color.RED.toString(), "壊れ", Color.WHITE.toString(),
							"ます");
				}
			}
		}
	}
}
