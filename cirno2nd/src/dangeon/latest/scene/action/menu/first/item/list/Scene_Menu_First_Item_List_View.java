package dangeon.latest.scene.action.menu.first.item.list;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import main.Listener;
import main.Listener.ACTION;
import main.constant.FR;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.itemlist.Item_List_View;
import dangeon.latest.scene.action.menu.first.infomation.contents.Simbols;
import dangeon.latest.util.view_window.ItemSideWindow;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Enchant;
import dangeon.view.constant.NormalFont;
import dangeon.view.detail.View_Simbols;
import dangeon.view.util.StringFilter;

public class Scene_Menu_First_Item_List_View extends Item_List_View {
	private StringOnlyWindow TOP_SIDE_WINDOW;
	private StringOnlyWindow SIDE_WINDOW1;
	public static final int MAIN_W = 270, MARGINE = 19,
			MINI_W = FR.SCREEN_WIDTH - MAIN_W - 30 - MARGINE, Y = 40, X = 6;

	private boolean into_red = false;

	public Scene_Menu_First_Item_List_View(Base_View bv) {
		super(bv, true);
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
		int first_line = (enchant == Enchant.ATK || enchant == Enchant.DEF) ? 3
				: 6;
		int second_line = 5;
		// ATK DEF
		// list.add("○○○");
		// list.add("○○○○○○");
		// ANY RING
		// list.add("○○○○○○");
		// list.add("○○○○○○");
		for (ENCHANT_SIMBOL sim : arrayList) {
			if (count == first_line + second_line) {
				sb.append(enchant.getColor().toString());
				sb.append("…");
				list.add(sb.toString());
				return;
			}
			String name;
			if (enchant == Enchant.ANY2) {
				name = Enchant.ANY2.getColor().toString()
						.concat(sim.getSimbolName())
						.concat(Color.WHITE.toString());
			} else {
				name = sim.getName();
			}
			sb.append(name);
			sb.append("　");
			count++;
			if (count == first_line) {
				list.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		list.add(sb.toString());
		if (count < first_line) {
			list.add("");
		}
	}

	@Override
	protected MenuWindow createMenuWindow() {
		int x = X + MAIN_W + MARGINE;
		int y = Y;
		createTopSide(x, 2);
		SIDE_WINDOW1 = new ItemSideWindow(x, y, MINI_W, Simbols.getFont(),
				getItems());
		return super.createMenuWindow();
	}

	private void createTopSide(int x, int y) {
		boolean foot = PARENT_SCENE instanceof Scene_Menu_First_Item_List___Foot;
		TOP_SIDE_WINDOW = null;
		if (!foot) {
			for (int keys : Listener.getKey().keySet()) {
				if (ACTION.FIRE == Listener.getKey().get(keys)) {
					String text = KeyEvent.getKeyText(keys);
					if (text.length() > 2) {
						text = text.substring(0, 2);
					} else {
						text = 半角全角コンバーター.半角To全角英字(text);
					}
					createTopSide(x, y, Color.WHITE + "複数選択:"
							+ StringFilter.NUMBERS + text);
					break;
				}
			}
			//
			// if (Belongings.isOver30()) {
			// createTopSide(x, y, "over".concat(半角全角コンバーター
			// .半角To全角数字(Belongings.getSize() - 30)));
			// }
		}
	}

	private void createTopSide(int x, int y, String title) {
		TOP_SIDE_WINDOW = new StringOnlyWindow(x, y,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE), title)
		// {
		// @Override
		// protected int getBG() {
		// return BG_RED;
		// }
		// }
		;
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		super.draw(g, current);
		if (into_red) {
			((Scene_Menu_First_Item_List) PARENT_SCENE).change(true);
			into_red = false;
		}
		if (current)
			SIDE_WINDOW1.drawWindow(g);
		if (TOP_SIDE_WINDOW != null)
			TOP_SIDE_WINDOW.drawWindow(g);
	}

	private String[] getItems() {
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
			if (smfil.getPage() < 3) {
				sb.append("持ち物 (");
				sb.append(smfil.getPage() + 1);
				sb.append(Color.WHITE);
				sb.append("/");
				if (smfil.getMaxPage() > 3)
					sb.append(3);
				else
					sb.append(smfil.getMaxPage());
				sb.append(Color.WHITE);
				sb.append(")");
			} else {
				sb.append("ポケット");
			}
		}
		return sb.toString();
	}

	void setRed() {
		into_red = true;
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
