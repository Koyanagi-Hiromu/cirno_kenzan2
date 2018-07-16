package dangeon.latest.util.view_window;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;

import main.constant.FR;
import main.res.Image_Object;
import main.res.Image_Window_Frame;
import main.util.BeautifulView;
import main.util.DIRECTION;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class MenuWindow extends Base_Window {
	protected final Color COLOR_MAIN = new Color(0, 150, 255, 50),
			COLOR_SUB = new Color(100, 200, 100, 50),
			COLOR_MAIN_LINE = new Color(0, 200, 255),
			COLOR_MAIN_LINE_CLEAR = new Color(COLOR_MAIN_LINE.getRed(),
					COLOR_MAIN_LINE.getGreen(), COLOR_MAIN_LINE.getBlue(), 0),
			COLOR_SUB_LINE = new Color(100, 255, 100),
			COLOR_SUB_LINE_CLEAR = new Color(COLOR_SUB_LINE.getRed(),
					COLOR_SUB_LINE.getGreen(), COLOR_SUB_LINE.getBlue(), 0);
	protected final Base_Scene_Menu SCENE;
	protected final Base_Scene_Menu_View VIEW;
	protected final int W;

	private boolean red = false;

	protected MenuWindow(Base_Scene_Menu_View view) {
		this(view, NormalFont.NORMALFONT);
	}

	protected MenuWindow(Base_Scene_Menu_View view, Font font) {
		this(view, 0, font);
	}

	public MenuWindow(Base_Scene_Menu_View view, int w) {
		this(view, w, NormalFont.NORMALFONT);
	}

	public MenuWindow(Base_Scene_Menu_View view, int w, Font font) {
		super(view.X, view.Y, font);
		VIEW = view;
		W = w;
		SCENE = (Base_Scene_Menu) view.PARENT_SCENE;
		WINDOW = createWindow(getWidth(getW_Inside()), getHeight(SCENE.COL));
	}

	public void change(int cols, boolean red) {
		this.red = red;
		WINDOW = createWindow(getWidth(getW_Inside()), getHeight(cols));
	}

	private void drawBackLight(Graphics2D g, boolean current) {
		drawBackLight(g, current, SCENE.getIndex());
	}

	protected void drawBackLight(Graphics2D g, boolean current, int index) {
		FontMetrics fm = g.getFontMetrics(FONT);
		int w = getW(index) * 70 / 100;
		int h = fm.getHeight();
		int x = getX(getViewX(), index);
		int y = getY(g, getViewY(), index);
		y += fm.getDescent();
		y -= h;
		Color c = g.getColor();
		Paint p = g.getPaint();
		if (current) {
			g.setPaint(new GradientPaint(x, y, COLOR_MAIN_LINE, x + w, y,
					COLOR_MAIN_LINE_CLEAR));
		} else {
			g.setPaint(new GradientPaint(x, y, COLOR_SUB_LINE, x + w, y,
					COLOR_SUB_LINE_CLEAR));
		}
		g.fillRect(x, y, w, h);
		g.setPaint(p);
		g.setColor(c);
	}

	protected void drawGlowString(Graphics2D g, int x, int y, boolean current) {
		int index = SCENE.getIndex();
		if (SCENE.isContentDeprecated(index)) {
			return;
		}
		Color origine = g.getColor();
		g.setColor(current ? COLOR_MAIN : COLOR_SUB);
		String plain = StringFilter.getPlainString(SCENE.getContentName(index));
		for (DIRECTION direction : DIRECTION.values_exceptNeatral()) {
			drawGlowString(g, x, y, direction, index, plain);
		}
		g.setColor(Color.WHITE);
		g.drawString(plain, getX(x, index), getY(g, y, index));
		g.setColor(origine);
	}

	protected void drawGlowString(Graphics2D g, int x, int y,
			DIRECTION direction, int index, String plain) {
		int range = 2;
		int _x = getX(x, index) + direction.X * range;
		int _y = getY(g, y, index) + direction.Y * range;
		g.drawString(plain, _x, _y);
	}

	@Override
	protected void drawString(Graphics2D g, int x, int y) {
		g.setFont(FONT);
		int selected = SCENE.getIndex();
		for (int i = 0; i < SCENE.getContentSize(); i++) {
			Color c = g.getColor();
			if (isSelectedGlow()) {
				if (!SCENE.isContentDeprecated(i) && i == selected) {
					continue;
				}
			}
			if (SCENE.isContentDeprecated(i)) {
				g.setColor(Color.GRAY);
			}
			drawStringFilter(g, i, x, y);
			g.setColor(c);
		}
	}

	protected void drawStringFilter(Graphics2D g, int i, int x, int y) {
		String s = SCENE.getContentName(i);
		if (s.matches(WindowFrame.HORIZON))
			drawLine(g, i - 1, true);
		else {
			StringFilter.drawString(g, s, getX(x, i), getY(g, y, i));
		}
	}

	protected void drawSuikaIcon(Graphics2D g, boolean current) {
		Image im = Image_Object.suika.getImage();
		int hosei = 2;
		if (!current)
			BeautifulView.setAlphaOnImg(g, 0.6f);
		g.drawImage(im, getX(getViewX(), SCENE.getIndex()) - im.getWidth(null)
				+ hosei,
				getY(g, getViewY(), SCENE.getIndex()) - im.getHeight(null),
				null);
		BeautifulView.setAlphaOnImg(g, 1f);
	}

	public void drawWindow(Graphics2D g, boolean current) {
		if (isSelectedLine()) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(current ? Color.WHITE : Color.GRAY);
		}
		g.drawImage(WINDOW, getViewX(), getViewY(), null);
		if (isSelectedLine()) {
			drawBackLight(g, current);
		}
		drawString(g, getViewX(), getViewY());
		drawSuikaIcon(g, current);
		if (isSelectedGlow())
			drawGlowString(g, getViewX(), getViewY(), current);
	}

	@Override
	protected int getBG() {
		return getBG(red);
	}

	@Override
	public int getViewX() {
		Integer X = VIEW.X;
		if (X == null)
			return (FR.SCREEN_WIDTH - WINDOW.getWidth()) / 2;
		else if (X < 0)
			return FR.SCREEN_WIDTH + X - getWidth();
		else
			return X;
	}

	@Override
	protected int getViewY() {
		Integer Y = VIEW.Y;
		if (Y == null)
			return (FR.SCREEN_HEIGHT - WINDOW.getHeight()) / 2;
		else
			return Y;
	}

	public int getW(int index) {
		return W;
	}

	protected int getW_Inside() {
		return W;
	}

	protected int getX(int x, int index) {
		return x + INSETS.left + MARGINE;
	}

	@Override
	protected int getY(Graphics2D g, int y, int index) {
		int dy = 0;
		if (isSelectedLine()) {
			if (!SCENE.isContentDeprecated(index) && index == SCENE.getIndex()) {
				dy += 1;
			}
		}
		int col = index / SCENE.ROW;
		return y + INSETS.top + getContentHeight() * (col + 1) + dy;
	}

	protected boolean isSelectedGlow() {
		return VIEW.TYPE.equals(Image_Window_Frame.GLOW);
	}

	protected boolean isSelectedLine() {
		return VIEW.TYPE.equals(Image_Window_Frame.LINE);
	}
}
