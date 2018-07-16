package dangeon.latest.scene.action.menu.selectbox;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import main.Second_Firster;
import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.latest.util.view_window.WindowFrame;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class SelectBox_View extends Base_Scene_Menu_View {
	private final String[] EXN;
	private StringOnlyWindow SIDE_WINDOW1;

	public SelectBox_View(Base_View bv) {
		this(bv, null);
	}

	public SelectBox_View(Base_View bv, String[] strings) {
		super(5, 45, Image_Window_Frame.GLOW, bv);
		EXN = strings;
	}

	@Override
	protected MenuWindow createMenuWindow() {
		MenuWindow m = new MenuWindow_SelfAdjusting(this) {
			@Override
			protected int getBG() {
				return WindowFrame.BG_NORMAL_UNCLEARLY;
			};
		};
		if (EXN != null) {
			int w = 1;// 250;
			Font font = NormalFont.NORMALFONT.deriveFont(11f);
			FontMetrics fo = Second_Firster.ME.getGraphics().getFontMetrics(
					font);
			for (String s : EXN) {
				w = Math.max(w, fo.stringWidth(StringFilter.getPlainString(s)));
			}
			SIDE_WINDOW1 = new StringOnlyWindow(X + m.getWidth() + 5, Y, w,
					font, EXN) {
				@Override
				protected int getBG() {
					return WindowFrame.BG_NORMAL_UNCLEARLY;
				};
			};
		}
		return m;
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		super.draw(g, current);
		if (SIDE_WINDOW1 != null)
			SIDE_WINDOW1.drawWindow(g);
	}
}
