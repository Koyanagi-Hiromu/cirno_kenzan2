package dangeon.latest.scene.action.otog.ready.select.select;

import java.util.ArrayList;
import java.util.Collections;

import main.Listener.ACTION;
import main.res.BGM;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.otog.Fader;
import dangeon.latest.scene.action.otog.Scene_Otog;
import dangeon.latest.scene.action.otog.value.ValueHolder;
import dangeon.latest.system.KeyHolder;

public class Otog_Select extends Base_Scene {
	public final ValueHolder VH;
	public final ArrayList<Otog_Select_Content_Selection> content;
	public final ArrayList<Otog_Select_Content_Left> left = new ArrayList<Otog_Select_Content_Left>();
	public Select_Content_Gauge gauge;

	public final BGM DEFAULT_BGM = BGM.iku;

	private int bgm_wait_frame = 0;

	private final int BGM_WAIT = 50;

	private BGM selected_bgm;

	private boolean phase_main = true;

	private int sub_y = 1;

	public Otog_Select(KeyHolder kh, Base_View bv) {
		this(kh, bv, null);
		initContents();
		for (int i = 0; i < 3; i++) {
			sort(2 - i);
		}
	}

	public Otog_Select(KeyHolder kh, Base_View bv, ValueHolder vh) {
		super(kh, new Otog_Select_View(bv));
		if (vh != null)
			VH = vh;
		else
			VH = new ValueHolder();
		content = VH.SCORE_SELECTIONS;
		gauge = new Select_Content_Gauge();
		if (!content.isEmpty()) {
			sort(VH.select_sort);
		}
	}

	@Override
	public boolean action(ACTION a) {
		if (phase_main)
			action_normal(a);
		else
			action_sub(a);
		return END;
	}

	private void action_normal(ACTION a) {
		if (a == ACTION.ENTER) {
			SE.SYSTEM_MAGIC.play();
			VH.CONTENT = getContent(0);
			final Otog_Select ME = this;
			final Scene_Otog S = new Scene_Otog(this);
			new Fader(this, S) {
				@Override
				protected void taskWork() {
					S.init(ME, VH.difficulty == 7);
				}
			};
		} else if (a == ACTION.CANCEL || a == ACTION.MENU) {
			SE.SYSTEM_CANCEL.play();
			setNextScene(CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE);
			if (VH.pre_bgm == null) {
				BGM.waitUntilFadeOut_Thread();
			} else {
				VH.pre_bgm.play();
			}
		} else {
		}
	}

	private void action_sub(ACTION a) {
		if (a == ACTION.ENTER) {
			SE.SYSTEM_ARRANGEMENT.play();
			if (sub_y == 0) {
				SE.SYSTEM_ARRANGEMENT.play();
				VH.select_sort += 1;
				if (VH.select_sort < 0)
					VH.select_sort += 3;
				if (VH.select_sort > 2)
					VH.select_sort -= 3;
				sort(VH.select_sort);
			} else if (sub_y == 1) {
				VH.addDifficulty(1);
			} else if (sub_y == 2) {
				VH.addSpeed(1);
			} else if (sub_y == 3) {
				VH.changeRandom();
			}
		} else if (a == ACTION.CANCEL || a == ACTION.MENU) {
			SE.SYSTEM_CANCEL.play();
			phase_main = true;
			getContent(0).frame = 0;
			gauge.set(-1);
			sub_y = 0;
		} else {
		}
	}

	@Override
	public boolean arrow(DIRECTION d) {
		SE.SYSTEM_CURSOR.play();
		if (phase_main)
			arrow_main(d);
		else
			arrow_sub(d);
		if (d.Y == 0 && d.X != 0) {
			sub_y = 1;
			phase_main = !phase_main;
			if (phase_main) {
				getContent(0).frame = 0;
				gauge.set(-1);
			} else {
				getContent(0).frame = -1;
				gauge.set(sub_y);
			}
		}
		return END;
	}

	private void arrow_main(DIRECTION d) {
		if (d.Y != 0) {
			getContent(0).dx -= 25;
			VH.select_y += d.Y;
			if (VH.select_y < 0)
				VH.select_y += content.size();
			if (VH.select_y >= content.size())
				VH.select_y -= content.size();
			for (Otog_Select_Content_Selection c : content) {
				c.dy += d.Y * c.h;
			}
			getContent(0).dx += 100;
			setLeft();
		}
	}

	private void arrow_sub(DIRECTION d) {
		sub_y += d.Y;
		if (sub_y < 0)
			sub_y = 3;
		else if (sub_y > 3)
			sub_y = 0;
		gauge.set(sub_y);
	}

	public Otog_Select_Content_Selection getContent(int i) {
		i += VH.select_y;
		while (i < 0)
			i += content.size();
		while (i >= content.size())
			i -= content.size();
		return content.get(i);
	}

	private void initContents() {
		new Otog_Select_Initializer().initContents(content, VH);
	}

	private void setBgm() {
		if (!DEFAULT_BGM.isPlayingBGM()) {
			DEFAULT_BGM.play();
		}
		bgm_wait_frame = BGM_WAIT;
		selected_bgm = getContent(0).BGM;
	}

	public void setLeft() {
		for (Otog_Select_Content_Selection c : content) {
			c.frame = -1;
		}
		setBgm();
		getContent(0).frame = 0;
		int max_ver = 4;
		ArrayList<Otog_Select_Content_Selection> temp = new ArrayList<Otog_Select_Content_Selection>(
				max_ver);
		Otog_Select_Content_Selection sel = getContent(0);
		for (Otog_Select_Content_Selection s : content) {
			if (sel.AUTHOR.equals(s.AUTHOR) && sel.BGM.equals(s.BGM))
				temp.add(s);
		}
		left.clear();
		for (int i = 1; i <= max_ver; i++) {
			boolean flag = false;
			for (Otog_Select_Content_Selection s : temp) {
				if (i == s.VER) {
					flag = true;
					if (s != sel)
						left.add(new Otog_Select_Content_Left(s, false));
					break;
				}
			}
			if (!flag && i != 4)
				left.add(new Otog_Select_Content_Left(i));
		}
		left.add(new Otog_Select_Content_Left(sel, true));
	}

	private void sort(int select_sort) {
		int pre = VH.select_sort;
		VH.select_sort = select_sort;
		Collections.sort(content);
		VH.select_sort = pre;
		setLeft();
	}

	@Override
	public void upDate() {
		if (bgm_wait_frame > 0) {
			if (--bgm_wait_frame == 0) {
				selected_bgm.play();
			}
		}
		for (Otog_Select_Content_Selection c : content) {
			c.upDate();
		}
		for (Otog_Select_Content_Left l : left) {
			l.upDate();
		}
		gauge.upDate();
	}

}
