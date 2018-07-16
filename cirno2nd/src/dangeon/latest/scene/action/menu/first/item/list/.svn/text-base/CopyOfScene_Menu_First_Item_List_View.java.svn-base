package dangeon.latest.scene.action.menu.first.item.list;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import main.constant.FR;
import main.res.Image_Object;
import main.util.BeautifulView;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.itemlist.Item_List_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.view.constant.NormalFont;
import dangeon.view.detail.View_Simbols;
import dangeon.view.util.StringFilter;

public class CopyOfScene_Menu_First_Item_List_View extends Item_List_View {
	private StringOnlyWindow TOP_SIDE_WINDOW;
	private StringOnlyWindow SIDE_WINDOW1;
	public static final int MAIN_W = 270, MARGINE = 19,
			MINI_W = FR.SCREEN_WIDTH - MAIN_W - 30 - MARGINE, Y = 40, X = 6;

	public CopyOfScene_Menu_First_Item_List_View(Base_View bv) {
		super(bv);
	}

	private void addBar(ArrayList<String> final_list, Enchant enchant) {
		String name;
		if (enchant == Enchant.ANY1) {
			name = "特殊";
		} else if (enchant == Enchant.ANY2) {
			name = "リボン";
		} else if (enchant == Enchant.ATK) {
			name = "攻撃";
		} else if (enchant == Enchant.DEF) {
			name = "防御";
		} else {
			name = enchant.toString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(enchant.getColor().toString());
		sb.append(name);
		final_list.add(sb.toString());
	}

	private void addSimbolString(ArrayList<ENCHANT_SIMBOL> arrayList,
			ArrayList<String> list, Enchant enchant) {
		addBar(list, enchant);
		StringBuilder sb = new StringBuilder();
		int count = 0;
		int max_in_line = 6;
		// list.add("○○○○○○");
		// list.add("○○○○○○");
		for (ENCHANT_SIMBOL sim : arrayList) {
			if (count == max_in_line * 2) {
				sb.append(enchant.getColor().toString());
				sb.append("...");
				list.add(sb.toString());
				return;
			}
			String name;
			if (enchant == Enchant.ANY2) {
				name = Enchant.ANY2.getColor().toString()
						.concat(sim.getSimbolName());
			} else {
				name = sim.getName();
			}
			sb.append(name);
			sb.append("　");
			count++;
			if (count == max_in_line) {
				list.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		list.add(sb.toString());
		if (count <= max_in_line) {
			list.add("");
		}
	}

	@Override
	protected MenuWindow createMenuWindow() {
		float size = 10;
		int x = X + MAIN_W + MARGINE;
		int y = Y;
		createTopSide(x, 2);
		SIDE_WINDOW1 = new StringOnlyWindow(x, y, MINI_W,
				NormalFont.NORMALFONT.deriveFont(size), getItems(0)) {
			@Override
			protected void drawString(Graphics2D g, int X, int Y) {
				for (int j = 0; j < 2; j++) {
					int h = getContentHeight() * 3;
					g.setColor(Color.WHITE);
					SpellCard sc = Enchant.values()[j]
							.getEnchant_Casted_SpellCard();
					if (sc != null) {
						// sc.IMLC.draw(g, X + 10, Y + 10 + h * +j, 78, h - 5);
						sc.IMLC.draw(g, X + 10, Y + 10 + h * +j, 78, h - 5);
					}
				}
				for (int i = 0; i < CONTENTS.length; i++) {
					if (i % 3 != 0) {
						int size = 12;
						int x = getViewX() + INSETS.left + MARGINE;
						int y = getViewY() + INSETS.top + getContentHeight()
								* i + 1 + 4;
						for (int j = 0; j < StringFilter
								.getPlainString(CONTENTS[i])
								.replaceAll("　", "").length(); j++) {
							g.setColor(Color.GRAY);
							g.drawImage(Image_Object.jewel_red.getImage(), x,
									y, size, size, null);
							x += 13;
						}
					}
					BeautifulView.setAlphaOnImg(g, 1f);
					drawEdgedString(g, CONTENTS[i], i + 1);
				}
			}
		};
		return super.createMenuWindow();
	}

	private void createTopSide(int x, int y) {
		boolean foot = PARENT_SCENE instanceof Scene_Menu_First_Item_List___Foot;
		TOP_SIDE_WINDOW = null;
		if (!foot) {
			if (Belongings.get(-1) != null) {
				createTopSide(x, y, "足元");
			}
		}
	}

	private void createTopSide(int x, int y, String title) {
		TOP_SIDE_WINDOW = new StringOnlyWindow(x, y,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE), title);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		super.draw(g, current);
		if (current)
			SIDE_WINDOW1.drawWindow(g);
		if (TOP_SIDE_WINDOW != null)
			TOP_SIDE_WINDOW.drawWindow(g);
	}

	private String[] getItems(int page) {
		ArrayList<String> list = toString(View_Simbols.getSimbols());
		return list.toArray(new String[0]);
	}

	@Override
	public String getTop() {
		StringBuilder sb = new StringBuilder();
		if (PARENT_SCENE instanceof Scene_Menu_First_Item_List___Foot) {
			sb.append("足元");
		} else {
			Scene_Menu_First_Item_List smfil = (Scene_Menu_First_Item_List) PARENT_SCENE;
			sb.append("持ち物　　(");
			sb.append(smfil.getPage() + 1);
			sb.append(Color.WHITE);
			sb.append("/");
			sb.append(smfil.getMaxPage());
			sb.append(Color.WHITE);
			sb.append(")");
		}
		return sb.toString();
	}

	private ArrayList<String> toString(
			HashMap<Enchant, ArrayList<ENCHANT_SIMBOL>> hash) {
		ArrayList<Enchant> enchants = new ArrayList<Enchant>(4);
		enchants.add(Enchant.ATK);
		enchants.add(Enchant.DEF);
		enchants.add(Enchant.ANY1);
		enchants.add(Enchant.ANY2);
		ArrayList<String> final_list = new ArrayList<String>();
		for (Enchant enchant : enchants) {
			addSimbolString(hash.get(enchant), final_list, enchant);
		}
		return final_list;
	}
}
