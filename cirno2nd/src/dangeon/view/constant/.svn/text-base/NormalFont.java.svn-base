package dangeon.view.constant;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.io.IOException;

import main.Second_Firster;
import main.util.FileReadSupporter;

public class NormalFont {
	public static final float SIZE = 18F;
	public static final float SMALL_SIZE = 14F;
	static {
		Font _font = null;
		try {
			_font = Font.createFont(Font.TRUETYPE_FONT,

			// FileReadSupporter.readURL("res/font/APJapanesefont.ttf"))
					FileReadSupporter.readURL("res/font/kiloji_b.ttf"))
			// FileReadSupporter.readURL("res/font/azukiLP.ttf"))
			// FileReadSupporter.readURL("res/font/uzura.ttf"))
			// FileReadSupporter.readURL("res/font/れいこフォント.TTF"))
			// FileReadSupporter.readURL("res/font/sazanami-gothic.ttf"))
			// FileReadSupporter.readURL("res/font/ume-pgs5.ttf"))
			// FileReadSupporter.readURL("res/font/みかちゃん-P.ttf"))
			// FileReadSupporter.readURL("res/font/みかちゃん-PB.ttf"))
			// FileReadSupporter.readURL("res/font/KonatuTohaba.ttf"))
					.deriveFont(Font.BOLD, SIZE);
		} catch (FontFormatException e) {
			main.util.Show
					.showCriticalErrorMessageDialog("FontFormatError@View.font_loading");
			e.printStackTrace();
		} catch (IOException e) {
			main.util.Show
					.showCriticalErrorMessageDialog("IOError@View.font_loading");
			e.printStackTrace();
		}
		NORMALFONT = _font;
		FontMetrics fo = Second_Firster.ME.getFontMetrics(_font);
		HEIGHT = fo.getHeight();
		ASCENT = fo.getAscent();
		DECENT = fo.getDescent();
		LEADING = fo.getLeading();
		WIDTH2 = Second_Firster.ME.getFontMetrics(_font).stringWidth("　　");
		fo = Second_Firster.ME.getFontMetrics(_font.deriveFont(SMALL_SIZE));
		SMALL_HEIGHT = fo.getHeight();
		SMALL_ASCENT = fo.getAscent();
		SMALL_DECENT = fo.getDescent();
		SMALL_LEADING = fo.getLeading();
	}

	public static Font NORMALFONT;

	public static final int ASCENT;
	public static final int DECENT;
	public static final int HEIGHT;
	public static final int SMALL_HEIGHT;
	public static final int SMALL_ASCENT;
	public static final int SMALL_DECENT;
	public static final int SMALL_LEADING;
	/**
	 * the width of 2 letters (2 full-width white spaces).
	 */
	public static final int WIDTH2;

	/**
	 * 文字と文字の間のスペースの値。
	 */
	public static final int LEADING;

	public static void setBold(boolean bold) {
		NORMALFONT = NORMALFONT.deriveFont(bold ? Font.BOLD : Font.PLAIN);
	}
}
