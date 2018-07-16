package dangeon.latest.scene.action.menu.first.infomation.contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;

import main.res.Image_Object;
import main.util.BeautifulView;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.scrool.Scrool;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.ScroolWindow;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Simbols extends Scrool {
	private static int[] bars = new int[4];

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
		int index = -1;
		String name;
		if (enchant == Enchant.ANY1) {
			index = 2;
			name = "特殊";
		} else if (enchant == Enchant.ANY2) {
			index = 3;
			name = "リボン";
		} else if (enchant == Enchant.ATK) {
			index = 0;
			name = "攻撃";
		} else if (enchant == Enchant.DEF) {
			index = 1;
			name = "防御";
		} else {
			name = enchant.toString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(enchant.getColor().toString());
		sb.append(name);
		final_list.add(sb.toString());
		bars[index] = final_list.size() - 1;
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
		list.add(name.concat("：").concat(sim.getEXPLAIN(enchant)));
	}

	public static Font getFont() {
		return NormalFont.NORMALFONT.deriveFont(10f);
	}

	public static Image_Object getJewel(Color c) {
		if (Enchant.ATK.getColor().equals(c)) {
			return Image_Object.jewel_red;
		} else if (Enchant.DEF.getColor().equals(c)) {
			return Image_Object.jewel_yellow;
		} else if (Enchant.ANY1.getColor().equals(c)) {
			return Image_Object.jewel_perple;
		} else if (Enchant.ANY2.getColor().equals(c)) {
			return Image_Object.jewel_aqua;
		}
		return Image_Object.jewel_green;
	}

	public static HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> getSimbols() {
		HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> hash = new HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>>(
				4);
		boolean flag_enc_dungeon = PresentField.get().isAllEnchantDungeon();
		hash.put(Enchant.ATK, new ArrayList<ENCHANT_SIMBOL>());
		hash.put(Enchant.DEF, new ArrayList<ENCHANT_SIMBOL>());
		hash.put(Enchant.ANY1, new ArrayList<ENCHANT_SIMBOL>());
		hash.put(Enchant.ANY2, new ArrayList<ENCHANT_SIMBOL>());
		for (ENCHANT_SIMBOL s : Player.me.getClassJob().getSimbols()) {
			if (s.ring)
				add(s, hash, Enchant.ANY2);
			else if (s.getShow() == 1)
				add(s, hash, Enchant.ATK);
			else if (s.getShow() == 2)
				add(s, hash, Enchant.DEF);
			else if (s.getShow() == 3)
				add(s, hash, Enchant.ANY1);
			else {
				add(s, hash, Enchant.ATK);
				add(s, hash, Enchant.DEF);
			}
		}
		Enchant[] enchant = { Enchant.ATK, Enchant.DEF };
		Base_Artifact a;
		for (int j = 0; j < enchant.length; j++) {

			// 正規装備チェック
			a = enchant[j].getEnchant();
			if (a != null) {
				// デフォルトチェック
				if (a.sim != null && a.sim.getShow() == j + 1) {
					add(a.sim, hash, enchant[j]);
				}
				// 合成チェック
				for (ENCHANT_SIMBOL sim : a.getListComposition()) {
					if (sim.getShow() == j + 1 || sim.getShow() == 4) {
						// add(a.sim, list, enchant[j]);
						add(sim, hash, enchant[j]);
					}
				}
			}
			if (flag_enc_dungeon) {
				// 何でも装備ダンジョンチェック
				for (Base_Artifact _A : Belongings.getListItems()) {
					// if (_A.isFrozen())
					// continue;
					add(_A, hash, enchant, j);
					if (_A.sim != null && _A.sim.getShow() == 3) {
						add(_A.sim, hash, Enchant.ANY1);
					}
				}
			} else {
				// 何でも装備チェック
				for (Enchant en : Enchant.getANYS()) {
					add(en.getEnchant(), hash, enchant, j);
				}
			}
			if (EnchantSpecial.bake(j)) {
				// 謎 狸印チェック
				for (ENCHANT_SIMBOL sim : EnchantSpecial.bakeList(j)) {
					add(sim, hash, enchant[j]);
				}
			}
		}
		for (Enchant en : Enchant.getALL()) {
			a = en.getEnchant();
			if (a == null || !a.isStaticCheked()) {
				continue;
			}
			if (a.sim != null) {
				if (a.sim.getShow() == 3) {
					add(a.sim, hash, Enchant.ANY1);
				}
			}
			if (en.equals(Enchant.ATK) || en.equals(Enchant.DEF)) {
				for (ENCHANT_SIMBOL sim : a.getListComposition()) {
					if (sim.getShow() == 3) {
						add(sim, hash, Enchant.ANY1);
					}
				}
			}
		}
		for (ENCHANT_SIMBOL sim : ENCHANT_SIMBOL.values_ring()) {
			if (EnchantSpecial.enchantSimbolAllCheck__no_Check(sim)) {
				if (flag_enc_dungeon
						|| EnchantSpecial
								.enchantSimbolRingCheck_View_Simbol(sim)) {
					add(sim, hash, Enchant.ANY2);
				}
			}
		}
		return hash;
	}

	private static ArrayList<String> toString(
			HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> hash) {
		ArrayList<Enchant> enchants = new ArrayList<Enchant>(4);
		enchants.add(Enchant.ATK);
		enchants.add(Enchant.DEF);
		enchants.add(Enchant.ANY1);
		enchants.add(Enchant.ANY2);
		ArrayList<String> final_list = new ArrayList<String>();
		for (Enchant enchant : enchants) {
			addBar(final_list, enchant);
			for (ENCHANT_SIMBOL sim : hash.get(enchant)) {
				addSimbolString(sim, final_list, enchant);
			}
		}
		final_list.add("");
		return final_list;
	}

	public Simbols(KeyHolder kh, Base_View bv) {
		super(16, kh, bv, toString(getSimbols()));
	}

	@Override
	protected ScroolWindow createScroolWindow(String... contents) {
		return new ScroolWindow(this, getFont(), contents) {
			@Override
			protected void drawString(Graphics2D g, int X, int Y) {
				Shape s = g.getClip();
				int l = getViewX() + 8, t = getViewY() + 8;
				int h = 0;
				g.setClip(l, t, getWidth() - 15, getHeight() - 15);
				{
					for (int j = 0; j < 2; j++) {
						int margine = 10;
						int _h = getContentHeight() * (bars[j + 1] - bars[j]);
						int _y = Y + margine + h - getContentHeight() * y;
						h = _h;
						SpellCard sc = Enchant.values()[j]
								.getEnchant_Casted_SpellCard();
						if (sc != null) {
							sc.IMLC.draw(g, X + margine, _y, getWidth()
									- margine * 2, h - 2);
						}
					}
				}
				super.drawString(g, X, Y);
				for (int i = 0; i < CONTENTS.length; i++) {
					BeautifulView.setAlphaOnImg(g, 1f);
					boolean flag = true;
					for (int index : bars) {
						if (i == 0 || index == i - 1 + y) {
							flag = false;
							break;
						}
					}
					if (flag) {
						int size = 16;
						int x = getViewX() + INSETS.left + MARGINE - 2;
						int y = getViewY() + INSETS.top + getContentHeight()
								* (i - 1) + 4;
						String line = CONTENTS[i];
						if (line.isEmpty())
							continue;
						Color c = StringFilter.getFirstColor(g, line);
						String sim = StringFilter.getPlainString(line)
								.substring(0, 1);
						g.drawImage(getJewel(c).getImage(), x, y, size, size,
								null);
						g.setColor(Color.WHITE);
						StringFilter.drawEdgedString_plain(g, sim, x
								+ g.getFontMetrics().stringWidth(sim) / 2 - 3,
								y + getContentHeight() - 4, c.darker().darker()
										.darker());
					}
				}
				BeautifulView.setAlphaOnImg(g, 1f);
				g.setClip(s);
			}

		};
	}

}