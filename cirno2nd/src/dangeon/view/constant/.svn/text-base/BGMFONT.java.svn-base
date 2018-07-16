package dangeon.view.constant;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.io.IOException;

import main.Second_Firster;
import main.util.FileReadSupporter;

public class BGMFONT {
	public static final float SIZE = 30F;
	static {
		Font _font = null;
		try {
			_font = Font.createFont(Font.TRUETYPE_FONT,
					FileReadSupporter.readURL("res/font/minamoji.ttf"))
					.deriveFont(SIZE);
		} catch (FontFormatException e) {
			main.util.Show
					.showCriticalErrorMessageDialog("FontFormatError@View.font_loading");
			e.printStackTrace();
		} catch (IOException e) {
			main.util.Show
					.showCriticalErrorMessageDialog("IOError@View.font_loading");
			e.printStackTrace();
		}
		FONT = _font;
		FontMetrics fo = Second_Firster.ME.getFontMetrics(_font);
		HEIGHT = fo.getHeight();
		ASCENT = fo.getAscent();
		DECENT = fo.getDescent();
		LEADING = fo.getLeading();
	}

	public static final Font FONT;
	public static final int ASCENT;
	public static final int DECENT;
	public static final int HEIGHT;

	/**
	 * 文字と文字の間のスペースの値。
	 */
	public static final int LEADING;
}
