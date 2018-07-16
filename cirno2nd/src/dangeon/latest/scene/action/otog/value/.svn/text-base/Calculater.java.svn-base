package dangeon.latest.scene.action.otog.value;

import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;

public class Calculater {

	private void add(ValueHolder vh, TEXT tex) {
		vh.hash_save_tex_count.put(tex, vh.hash_save_tex_count.get(tex) + 1);
	}

	public void calc(final ValueHolder vh, TEXT tex) {
		if (tex != null) {
			add(vh, tex);
			long n = (999999999999l + 1000) / vh.node_sum;
			vh.score_milli += n * getScoreRate(tex) / 100;
			if (vh.score_milli > 999999999999l)
				vh.score_milli = 999999999999l;
			vh.LIFE_100 += getHeal(vh, tex);
			if (vh.LIFE_100 < 0) {
				vh.LIFE_100 = 0;
				vh.MAIN_PANEL.PARENT.interrput();
				// for (TEXT t : TEXT.values()) {
				// if (t == TEXT.MISS)
				// continue;
				// if (vh.hash_save_tex_count.get(t) > 0) {
				// new Fader(vh.MAIN_PANEL.PARENT);
				// return;
				// }
				// }
				// new Fader(vh.MAIN_PANEL.PARENT,
				// vh.MAIN_PANEL.PARENT.getNextSelectScene()) {
				// @Override
				// protected void taskWork() {
				// vh.MAIN_PANEL.PARENT.pre_bgm.play();
				// }
				// };
				return;
			} else if (vh.LIFE_100 > 10000) {
				vh.LIFE_100 = 10000;
			}
		}
	}

	private long getError(ValueHolder vh, TEXT tex) {
		switch (vh.difficulty) {
		case 2:
			return 160;
		case 3:
			return 300;
		case 4:
			return 500;
		case 5:
			return 1000;
		case 6:
			if (tex == TEXT.NOODLE)
				return 1000;
			else
				return 10000;
		}
		return 0;
	}

	private long getHeal(ValueHolder vh, TEXT tex) {
		switch (tex) {
		case PERFECT:
		case COLD:
			return 100 * vh.HEAL_MILLI_RATE / 1000;
		case COOL:
			return 20 * vh.HEAL_MILLI_RATE / 1000;
		case CHILLY:
		case NOODLE:
			return -getError(vh, tex) / 2;
		case MISS:
		case ERROR:
			return -getMiss(vh) / 2;
		}
		return 0;
	}

	private int getMiss(ValueHolder vh) {
		switch (vh.difficulty) {
		case 2:
			return 250;
		case 3:
			return 750;
		case 4:
			return 900;
		case 5:
			return 1800;
		case 6:
			return 10000;
		}
		return 0;
	}

	private long getScoreRate(TEXT tex) {
		switch (tex) {
		case PERFECT:
			return 100;
		case COLD:
			return 50;
		case COOL:
			return 10;
		case CHILLY:
		case NOODLE:
		case ERROR:
		case MISS:
		}
		return 0;
	}
}
