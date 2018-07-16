package dangeon.latest.scene.action.message;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.res.Image_LargeCharacter;
import main.res.SE;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Conversation {
	public static final String SLOW_REGEX = "#slow#";
	public final String[] TEXT;
	private final StringBuilder[] SB;
	public final Boolean flag_player_saying_this;
	public ConvEvent EVENT;
	public static Image_LargeCharacter previous_player, previous_opponent;
	public final Image_LargeCharacter IMLC;
	private boolean flag_first = true;
	private boolean flag_slow = false;

	private static Image_LargeCharacter previous;

	public static void end() {
		previous = previous_player = previous_opponent = null;
	}

	public static boolean isConvEmpty() {
		return previous_player == null && previous_opponent == null;
	}

	private int moji, col, delay;

	Conversation(ConvEvent CnE, String... strings) {
		this(null, null, CnE, strings);
	}

	private Conversation(Image_LargeCharacter imlc, Boolean flag,
			ConvEvent CnE, String... strings) {
		if (isConvEmpty())
			Image_LargeCharacter.slide_init();
		if (flag != null)
			if (flag)
				previous_player = imlc;
			else
				previous_opponent = imlc;
		IMLC = imlc;
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (String s : strings) {
			if (s.matches(SLOW_REGEX)) {
				setSlow();
			} else {
				sb.append(s);
			}
		}
		Color prev_color = null;
		for (String s : StringFilter.getBreakSpacingStrings(
				NormalFont.NORMALFONT, sb.toString(), Message.W)) {
			list.add((prev_color != null) ? prev_color.toString().concat(s) : s);
			prev_color = StringFilter.getColor(null, s);
		}
		TEXT = list.toArray(new String[0]);
		SB = new StringBuilder[TEXT.length];
		for (int i = 0; i < SB.length; i++)
			SB[i] = new StringBuilder();
		flag_player_saying_this = flag;
		EVENT = CnE;
		if (Message.ME.addConv(this)) {
			Message.end();
			slideIN();
		}
		if (TEXT.length > Message.COLS) {
			sb = new StringBuilder();
			for (int i = Message.COLS; i < TEXT.length; i++) {
				sb.append(TEXT[i]);
			}
			new Conversation(IMLC, sb.toString());
		}
	}

	/**
	 * 
	 * @param imlc
	 *            is of Opponent(=not player)
	 * @param strings
	 */
	public Conversation(Image_LargeCharacter imlc, ConvEvent CnE,
			String... strings) {
		this(imlc, false, CnE, strings);
	}

	/**
	 * 
	 * @param imlc
	 *            is of Opponent(=not player)
	 * @param strings
	 */
	public Conversation(Image_LargeCharacter imlc, String... strings) {
		this(imlc, false, null, strings);
	}

	/**
	 * 
	 * @param imlc
	 *            is of Opponent(=not player)
	 * @param strings
	 */
	public Conversation(Image_LargeCharacter imlc, String string, ConvEvent CnE) {
		this(imlc, false, CnE, string);
	}

	/**
	 * 
	 * @param strings
	 *            is of Cirno(player)
	 */
	public Conversation(String... strings) {
		this(previous_player == null ? Image_LargeCharacter.チルノ
				: previous_player, true, null, strings);
	}

	/**
	 * 
	 * @param strings
	 *            is of Cirno(player)
	 */
	public Conversation(String cirno_msg, ConvEvent CnE) {
		this(previous_player == null ? Image_LargeCharacter.チルノ
				: previous_player, true, CnE, cirno_msg);
	}

	public void drawName(Graphics2D g) {
		if (IMLC != null && IMLC != Image_LargeCharacter.ANY) {
			String name = IMLC.name();
			if (name.startsWith("Ex")) {
				name = name.substring(2);
			}
			int x = 20;
			int y = 240;
			int h = 12;
			int w = g.getFontMetrics().stringWidth(name) + 10;
			g.setColor(new Color(0, 0, 255, 200));
			g.fillRoundRect(x - 5, y - h + 7, w, h, 10, 10);
			g.setColor(Color.WHITE);

			StringFilter.drawString(g, name, x, y);
		}
	}

	public String[] getTEXT() {
		if (!isWriting())
			return TEXT;
		int num = 2;
		for (int j = 0; j < num; j++) {
			if (col == TEXT.length)
				return TEXT;
			String s = StringFilter.getStringAt(TEXT[col], moji);
			if (flag_slow) {
				if (++delay == 2) {
					delay = 0;
					moji++;
				} else {
					continue;
				}
			} else {
				moji++;
			}
			if (s != null) {
				SB[col].append(s);
			} else {
				col++;
				moji = 0;
				j--;
				continue;
			}
		}
		String[] texts = new String[TEXT.length];
		for (int i = 0; i < TEXT.length; i++)
			texts[i] = SB[i].toString();
		return texts;
	}

	public boolean isSelection() {
		return EVENT != null && EVENT.isSelection();
	}

	public boolean isWriting() {
		return IMLC != null && col != TEXT.length;
	}

	public void setSlow() {
		flag_slow = true;
	}

	public void slideIN() {
		SE.SYSTEM_ENTER.play();
		if (IMLC != null)
			if (IMLC.isConv()) {
				if (previous != IMLC) {
					IMLC.slide();
				}
			} else
				IMLC.convSlideIN(!flag_player_saying_this);
		if (flag_first && EVENT != null) {
			EVENT.workAtTheSameTime();
		}
		flag_first = false;
		previous = IMLC;
	}
}
