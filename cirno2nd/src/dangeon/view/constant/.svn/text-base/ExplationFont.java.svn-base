package dangeon.view.constant;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.io.File;
import java.io.IOException;

import main.Second_Firster;

public class ExplationFont {
	private static final float SIZE = 25F;
	static {
		Font _font = null;
		try {
			_font = Font.createFont(Font.TRUETYPE_FONT,
					new File("res/font/Konatu.ttf")).deriveFont(SIZE);
		} catch (FontFormatException e) {
			main.util.Show
					.showCriticalErrorMessageDialog("FontFormatError@View.font_loading");
			e.printStackTrace();
		} catch (IOException e) {
			main.util.Show
					.showCriticalErrorMessageDialog("IOError@View.font_loading");
			e.printStackTrace();
		}
		EXP_FONT = _font;
		FontMetrics fo = Second_Firster.ME.getFontMetrics(_font);
		HEIGHT = fo.getHeight();
		ASCENT = fo.getAscent();
		DECENT = fo.getDescent();
		LEADING = fo.getLeading();
	}

	public static final Font EXP_FONT;
	public static final int ASCENT;
	public static final int DECENT;
	public static final int HEIGHT;

	/**
	 * 文字と文字の間のスペースの値。
	 */
	public static final int LEADING;
}
