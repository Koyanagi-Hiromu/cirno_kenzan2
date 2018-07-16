package dangeon.latest.util.view_window;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import main.Second_Firster;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class MenuWindow_SelfAdjusting extends MenuWindow {
	protected int[] CONTENTS_WIDTH;

	protected int left_padding;

	public MenuWindow_SelfAdjusting(Base_Scene_Menu_View view) {
		this(view, NormalFont.NORMALFONT);
	}

	public MenuWindow_SelfAdjusting(Base_Scene_Menu_View view, Font font) {
		super(view, font);
	}

	public MenuWindow_SelfAdjusting(Base_Scene_Menu_View view, int w, Font font) {
		super(view, w, font);
	}

	@Override
	public void drawLine(Graphics2D g, int cols, boolean middle_under) {
		// TODO 自動生成されたメソッド・スタブ
		super.drawLine(g, cols, middle_under);
	}

	@Override
	protected void drawString(Graphics2D g, int x, int y) {
		super.drawString(g, x, y);
	}

	@Override
	public int getW(int index) {
		int max = 0;
		for (int i : CONTENTS_WIDTH) {
			max = i > max ? i : max;
		}
		return max;
	}

	@Override
	protected int getW_Inside() {
		CONTENTS_WIDTH = new int[SCENE.ROW];
		for (int i = 0; i < CONTENTS_WIDTH.length; i++) {
			CONTENTS_WIDTH[i] = 0;
		}
		FontMetrics fm = Second_Firster.ME.getFontMetrics(FONT);
		for (int i = 0; i < SCENE.getContentSize(); i++) {
			String content = SCENE.getContentName(i);
			int content_w = fm
					.stringWidth(StringFilter.getPlainString(content))
					+ MARGINE;
			int row = i % SCENE.ROW;
			CONTENTS_WIDTH[row] = Math.max(content_w, CONTENTS_WIDTH[row]);
		}
		int w_inside = 0;
		for (int i : CONTENTS_WIDTH) {
			w_inside += i;
		}
		if (W != 0) {
			int del_W = W - w_inside;
			del_W /= (CONTENTS_WIDTH.length + 1);
			for (int i = 0; i < CONTENTS_WIDTH.length; i++) {
				CONTENTS_WIDTH[i] += del_W;
			}
			left_padding = del_W;
			return W;
		} else {
			return w_inside;
		}
	}

	@Override
	protected int getX(int x, int index) {
		int _x = super.getX(x, index);
		int row = index % SCENE.ROW;
		for (int i = 0; i < row; i++) {
			_x += CONTENTS_WIDTH[i];
		}
		return _x + left_padding;
	}

}
