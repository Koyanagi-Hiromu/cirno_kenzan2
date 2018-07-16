package dangeon.latest.util.view_window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.util.BeautifulView;
import dangeon.latest.scene.action.menu.first.infomation.contents.Simbols;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Enchant;
import dangeon.view.util.StringFilter;

public class ItemSideWindow extends StringOnlyWindow {
	public ItemSideWindow(int x, int y, int w, Font font, String... contents) {
		super(x, y, w, font, contents);
	}

	@Override
	protected void drawString(Graphics2D g, int X, int Y) {
		for (int j = 0; j < 2; j++) {
			int h = getContentHeight() * 3;
			g.setColor(Color.WHITE);
			SpellCard sc = Enchant.values()[j].getEnchant_Casted_SpellCard();
			if (sc != null) {
				sc.IMLC.draw(g, X + 10, Y + 10 + h * +j, 78, h - 5);
			}
		}
		for (int i = 0; i < CONTENTS.length; i++) {
			BeautifulView.setAlphaOnImg(g, 1f);
			if (i % 3 != 0) {
				int size = 16;
				int diff = 12;
				int x = getViewX() + INSETS.left + MARGINE - 2;
				int y = getViewY() + INSETS.top + getContentHeight() * i + 4;
				String line = CONTENTS[i].replaceAll("　", "");
				while (!line.isEmpty()) {
					Color c = StringFilter.getFirstColor(g, line);
					if (Color.WHITE.equals(c))
						break;
					String sim = StringFilter.getPlainString(line).substring(0,
							1);
					if (sim.matches("…")) {
						sim = "...";
						line = "";
						g.setColor(c);
					} else {
						g.drawImage(Simbols.getJewel(c).getImage(), x, y, size,
								size, null);
						g.setColor(Color.WHITE);
						line = line
								.substring(line.indexOf(sim
										.concat("java.awt.Color[r=255,g=255,b=255]")) + 34);
					}
					StringFilter.drawEdgedString_plain(g, sim, x
							+ g.getFontMetrics().stringWidth(sim) / 2 - 3, y
							+ getContentHeight() - 4, c.darker().darker()
							.darker());
					x += diff;
				}
			} else {
				drawEdgedString(g, CONTENTS[i], i + 1);
			}
		}
		BeautifulView.setAlphaOnImg(g, 1f);
	}
}
