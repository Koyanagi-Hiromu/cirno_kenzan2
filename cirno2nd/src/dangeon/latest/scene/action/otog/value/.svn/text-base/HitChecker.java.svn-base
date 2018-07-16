package dangeon.latest.scene.action.otog.value;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

import main.Listener.ACTION;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.object.TargetA;
import dangeon.latest.scene.action.otog.object.TargetA_Long;

public class HitChecker {
	public enum TEXT {
		PERFECT(Color.YELLOW, Color.CYAN), COLD(Color.WHITE, Color.CYAN), COOL(
				Color.CYAN), CHILLY(Color.LIGHT_GRAY), NOODLE(Color.ORANGE), MISS(
				Color.MAGENTA), ERROR(Color.MAGENTA, Color.DARK_GRAY);
		private final Color C1, C2;

		private TEXT(Color c) {
			this(c, c);
		}

		private TEXT(Color c1, Color c2) {
			C1 = c1;
			C2 = c2;
		}

		public Color getColor(long pre_ave) {
			return pre_ave % 2 == 0 ? C1 : C2;
		}

		public boolean isCombo() {
			return this == PERFECT || this == COLD || this == COOL;
		}

		public boolean isNoGood() {
			return !isCombo();
		}

		public void setColor(Graphics2D g, long pre_ave) {
			g.setColor(getColor(pre_ave));
		}
	}

	private boolean hitcheck(TargetA t, ValueHolder vh,
			Iterator<TargetA> iterator) {
		if (vh.demo)
			return false;
		TEXT tex = targetCheck(vh, t);
		if (tex != null) {
			t.hitSet(vh, tex);
			if (tex != TEXT.NOODLE && !(t instanceof TargetA_Long)) {
				iterator.remove();
			}
			return true;
		}
		return false;
	}

	public void pushed(ValueHolder vh, ACTION a) {
		for (Iterator<TargetA> iterator = vh.list.iterator(); iterator
				.hasNext();) {
			TargetA t = iterator.next();
			if (t.A == a) {
				if (hitcheck(t, vh, iterator))
					return;
			}
		}
	}

	public void pushed(ValueHolder vh, DIRECTION d) {
		for (Iterator<TargetA> iterator = vh.list.iterator(); iterator
				.hasNext();) {
			TargetA t = iterator.next();
			if (t.hit(d)) {
				if (hitcheck(t, vh, iterator))
					return;
			}
		}
	}

	private TEXT targetCheck(ValueHolder vh, TargetA t) {
		long dif = (t.JUST_MP3_MILLI_FRAME - vh.MAIN_PANEL.pre_ave * 1000 + 500)
				/ 1000 + vh.ADJ;
		if (dif < -4) {
			return null;
		} else if (dif < -3) {
			return TEXT.CHILLY;
		} else if (dif < -2) {
			return TEXT.COOL;
		} else if (dif < -1) {
			return TEXT.COLD;
		} else if (dif <= 0) {
			return TEXT.PERFECT;
		} else if (dif < 2) {
			return TEXT.COLD;
		} else if (dif < 3) {
			return TEXT.COOL;
		} else if (dif < 5) {
			return TEXT.CHILLY;
		} else if (dif < 16) {
			return TEXT.NOODLE;
		}
		return null;
	}
}
