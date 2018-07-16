package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.util.DIRECTION;

public class Abstract_NPC extends Base_NPC {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected String[] MSG;
	private DIRECTION FIX_DIRECTION = null;

	private boolean flag_flying, flag_boating;

	public Abstract_NPC(CHARA_IMAGE IM, String name, int x, int y,
			boolean move, boolean true_flying___false_boat, String... str) {
		this(Image_LargeCharacter.get(name), IM, name, x, y, move, str);
		flag_flying = true_flying___false_boat;
		flag_boating = !true_flying___false_boat;
	}

	public Abstract_NPC(CHARA_IMAGE IM, String name, int x, int y,
			boolean move, String... str) {
		this(Image_LargeCharacter.get(name), IM, name, x, y, move, str);
	}

	public Abstract_NPC(CHARA_IMAGE IM, String name, int x, int y, DIRECTION d,
			String... str) {
		this(Image_LargeCharacter.get(name), IM, name, x, y, false, str);
		direction = d;
		FIX_DIRECTION = d;
	}

	public Abstract_NPC(Image_LargeCharacter iml, CHARA_IMAGE IM, String name,
			int x, int y, boolean move, String... str) {
		super(iml, new Point(x, y), name, IM, move);
		MSG = str;
	}

	public Abstract_NPC(Image_LargeCharacter iml, CHARA_IMAGE IM, String name,
			int x, int y, DIRECTION d, String... str) {
		this(iml, IM, name, x, y, false, str);
		direction = d;
		FIX_DIRECTION = d;
	}

	private String convert(boolean b, StringBuilder sb, String s, String at) {
		while (s.contains(at)) {
			sb.append(s.substring(0, s.indexOf(at)));
			if (b)
				say(sb.toString());
			else
				rep(sb.toString());
			sb.delete(0, sb.length());
			s = s.substring(s.indexOf(at) + 1);
		}
		return s;
	}

	@Override
	public int getFootDeltY() {
		return isFlying() ? -6 : super.getFootDeltY();
	}

	@Override
	public int getShadowSize100() {
		return isFlying() ? 14 : super.getShadowSize100();
	}

	@Override
	public boolean isBoating() {
		return flag_boating;
	}

	@Override
	public boolean isFlying() {
		return flag_flying;
	}

	@Override
	public void message() {
		if (FIX_DIRECTION != null) {
			direction = FIX_DIRECTION;
		}
		StringBuilder sb = new StringBuilder();
		String at = "@", cirno = "⑨";
		boolean b = false;
		for (String s : MSG) {
			s = s.replaceAll("＠", at);
			if (s.startsWith(cirno)) {
				if (sb.length() != 0)
					say(b, sb.toString());
				sb.delete(0, sb.length());
				b = !b;
				s = s.substring(1);
			}
			while (s.contains(at)) {
				sb.append(s.substring(0, s.indexOf(at)));
				say(b, sb.toString());
				sb.delete(0, sb.length());
				s = s.substring(s.indexOf(at) + 1);
			}
			sb.append(s);
		}
		if (sb.length() != 0)
			say(b, sb.toString());
		// Message.setImageLargeCharacter(IMLC);
		// Message.set(MSG);
		// Message.removeILC();
	}

	private void say(boolean b, String string) {
		if (b)
			rep(string);
		else
			say(string);
	}
}
