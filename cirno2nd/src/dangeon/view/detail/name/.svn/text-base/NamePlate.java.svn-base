package dangeon.view.detail.name;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

import main.res.Image_Artifact;
import main.thread.MainThread;
import main.util.BeautifulView;
import main.util.半角全角コンバーター;
import dangeon.latest.util.view_window.WindowFrame;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class NamePlate {
	public final int W, H;
	public final Point FIRST;
	public Point screen;
	private boolean flag_detail_ok;
	public final Base_Creature C;
	public final WindowFrame WINDOW;

	public boolean flag;

	private int sin = 0, sin_x = 0;

	public NamePlate(Graphics2D g, Base_Creature c, int font_size) {
		g.setFont(NormalFont.NORMALFONT.deriveFont((float) font_size * 2));
		String lv;
		String NAME;
		if (c.conditionCheck(CONDITION.やりすごし)) {
			lv = "Lv?";
			NAME = "ルーミア？";
		} else {
			if (c.getLV() == 0) {
				lv = "NPC";
			} else {
				lv = c.getConvertedLV() == 4 ? "ANOTHER" : "Lv"
						.concat(半角全角コンバーター.半角To全角数字(c.getLV()));
				flag_detail_ok = (c instanceof Base_Enemy)
						&& Config.isAccessableToDetail((Base_Enemy) c,
								c.getConvertedLV());
			}
			NAME = c.getName();
		}
		C = c;
		Point p = c.getScreenPoint();
		int x = p.x;
		int y = p.y;
		y += c.getFootY() + MAP.TILE_SIZE - (g.getFont().getSize() + 1) / 3;
		if (c.isJumping()) {
			y += c.getJumpDY();
		}
		x += MAP.TILE_SIZE / 2;
		FIRST = new Point(x, y);
		screen = FIRST.getLocation();
		screen.translate(-MAP.TILE_SIZE / 2, 0);
		int w = Math.max(g.getFontMetrics().stringWidth(NAME) / 2 + 6, 50);
		WINDOW = new WindowFrame(x, y, w * 2, 3);
		H = WINDOW.getHeight() / 2;
		W = WINDOW.getWidth() / 2;
		g = WINDOW.WINDOW.createGraphics();
		g.setFont(NormalFont.NORMALFONT.deriveFont((float) font_size * 2));
		BeautifulView.setAntiAliasing(g, true);
		g.setColor(Color.WHITE);
		StringFilter.drawString(g, lv, 12, H / 2 + font_size);
		if (flag_detail_ok) {
			g.drawImage(Image_Artifact.BOOK.getImage(0), g.getFontMetrics()
					.stringWidth(lv) - 2, -2, null);
		}
		StringFilter.drawString(g, NAME, 12, H + font_size * 2);
		g.dispose();
	}

	public void draw(Graphics2D g) {
		Point p = ObjectPoint.getScreenPointRelPlayer(screen);
		int x = p.x;
		int y = p.y;
		g.drawImage(WINDOW.WINDOW, x, y, W, H, null);
	}

	public void drawLine(Graphics2D g) {
		Point p = ObjectPoint.getScreenPointRelPlayer(screen);
		int x = p.x;
		int y = p.y;
		Point p2 = ObjectPoint.getScreenPointRelPlayer(FIRST);
		int x2 = p2.x - 25;
		int y2 = p2.y - 23;
		Color c = new Color(0, 250, 100);
		g.setColor(c);
		g.drawRect(x2, y2, 3, 3);
		g.setPaint(new GradientPaint(0, y2, c, 0, y, Color.LIGHT_GRAY));
		g.drawLine(x + 1, y + 1, x2, y2);

	}

	public void upDate(int right, int top) {
		int s = (int) (Math.sin(MainThread.getFrame() / 10.0) * 3);
		screen.translate(right / 2, top / 4 - sin + s);
		sin = s;
	}
}
