package dangeon.view.detail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import main.res.CHARA_IMAGE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.menu.first.infomation.contents.Simbols;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Enchant;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.StringFilter;

public class View_Simbols {

	private static int y = 0;

	private static void add(Base_Artifact a,
			HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> hash,
			Enchant[] enchant, int j) {
		if (a != null && a.isStaticCheked() && a.sim != null) {
			if (a.sim.getShow() == j + 1 || a.sim.getShow() == 4) {
				add(a.sim, hash, enchant[j]);
			}
		}
	}

	private static void add(ENCHANT_SIMBOL sim,
			HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> hash, Enchant enchant) {
		if (!hash.get(enchant).contains(sim)) {
			hash.get(enchant).add(sim);
		}
	}

	private static void addBar(ArrayList<String> final_list, Enchant enchant) {
		String name;
		if (enchant == Enchant.ANY1) {
			name = "とくしゅ";
		} else if (enchant == Enchant.ANY2) {
			return;
		} else {
			name = enchant.toString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(enchant.getColor().toString());
		sb.append("――");
		sb.append(name);
		sb.append("――――――――――――――――");
		final_list.add(sb.toString());
	}

	private static void addSimbolString(ENCHANT_SIMBOL sim,
			ArrayList<String> list, Enchant enchant) {
		String name;
		if (enchant == Enchant.ANY2) {
			name = Enchant.ANY2.getColor().toString()
					.concat(sim.getSimbolName()).concat(Color.WHITE.toString());
		} else {
			name = sim.getName();
		}
		list.add(name.concat(":").concat(sim.getEXPLAIN(enchant)));
	}

	public static void arrowkeyPressed(DIRECTION direction) {
		y += direction.Y;
		if (y < 0) {
			y = 0;
		}
	}

	public static void draw(Graphics2D g) {
		g.drawImage(Records.WINDOW, SCREEN.X, SCREEN.Y, null);
		ArrayList<String> list = toString(getSimbols());
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		if (y >= list.size()) {
			y = list.size() - 1;
		}
		int i = y;
		for (int j = 0; i < list.size() && j < 18; i++, j++) {
			StringFilter
					.drawString(
							g,
							list.get(i),
							SCREEN.X + SCREEN.Y_MARGINE,
							SCREEN.Y
									+ SCREEN.Y_MARGINE
									+ (NormalFont.SMALL_HEIGHT + NormalFont.SMALL_LEADING)
									* (j + 1));
		}
		g.setFont(NormalFont.NORMALFONT);
		int x = Records.WINDOW.getWidth(null) - 20;
		int _y = Records.WINDOW.getHeight(null) - 30;
		if (y > 0) {
			g.drawImage(CHARA_IMAGE.arrow.getWalkImage(1, DIRECTION.UP, 0), x,
					_y, null);
		}
		if (y < list.size() - 1) {
			g.drawImage(CHARA_IMAGE.arrow.getWalkImage(1, DIRECTION.DOWN, 0),
					x, _y + 12, null);
		}
	}

	public static HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> getSimbols() {
		return Simbols.getSimbols();
		// HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> hash = new
		// HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>>(
		// 4);
		// boolean flag_enc_dungeon = PresentField.get().isAllEnchantDungeon();
		// hash.put(Enchant.ATK, new ArrayList<ENCHANT_SIMBOL>());
		// hash.put(Enchant.DEF, new ArrayList<ENCHANT_SIMBOL>());
		// hash.put(Enchant.ANY1, new ArrayList<ENCHANT_SIMBOL>());
		// hash.put(Enchant.ANY2, new ArrayList<ENCHANT_SIMBOL>());
		// Enchant[] enchant = { Enchant.ATK, Enchant.DEF };
		// Base_Artifact a;
		// for (int j = 0; j < enchant.length; j++) {
		// // 正規装備チェック
		// a = enchant[j].getEnchant();
		// if (a != null) {
		// // デフォルトチェック
		// if (a.sim != null && a.sim.getShow() == j + 1) {
		// add(a.sim, hash, enchant[j]);
		// }
		// // 合成チェック
		// for (ENCHANT_SIMBOL sim : a.getListComposition()) {
		// if (sim.getShow() == j + 1 || sim.getShow() == 4) {
		// // add(a.sim, list, enchant[j]);
		// add(sim, hash, enchant[j]);
		// }
		// }
		// }
		// if (flag_enc_dungeon) {
		// // 何でも装備ダンジョンチェック
		// for (Base_Artifact _A : Belongings.getListItems()) {
		// add(_A, hash, enchant, j);
		// }
		// } else {
		// // 何でも装備チェック
		// for (Enchant en : Enchant.getANYS()) {
		// add(en.getEnchant(), hash, enchant, j);
		// }
		// }
		// }
		// for (Enchant en : Enchant.getALL()) {
		// a = en.getEnchant();
		// if (a == null || !a.isStaticCheked()) {
		// continue;
		// }
		// if (a.sim != null) {
		// if (a.sim.getShow() == 3) {
		// add(a.sim, hash, Enchant.ANY1);
		// }
		// }
		// if (en.equals(Enchant.ATK) || en.equals(Enchant.DEF)) {
		// for (ENCHANT_SIMBOL sim : a.getListComposition()) {
		// if (sim.getShow() == 3) {
		// add(sim, hash, Enchant.ANY1);
		// }
		// }
		// }
		// }
		// for (ENCHANT_SIMBOL sim : ENCHANT_SIMBOL.values_ring()) {
		// if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, sim)) {
		// if (flag_enc_dungeon
		// || EnchantSpecial
		// .enchantSimbolRingCheck_View_Simbol(sim)) {
		// add(sim, hash, Enchant.ANY2);
		// }
		// }
		// }
		// return hash;
	}

	private static ArrayList<String> toString(
			HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> hash) {
		ArrayList<Enchant> enchants = new ArrayList<Enchant>(4);
		enchants.add(Enchant.ATK);
		enchants.add(Enchant.DEF);
		enchants.add(Enchant.ANY1);
		if (!hash.get(Enchant.ANY2).isEmpty()) {
			enchants.add(Enchant.ANY2);
		}
		ArrayList<String> final_list = new ArrayList<String>();
		for (Enchant enchant : enchants) {
			addBar(final_list, enchant);
			for (ENCHANT_SIMBOL sim : hash.get(enchant)) {
				addSimbolString(sim, final_list, enchant);
			}
		}
		return final_list;
	}

}
