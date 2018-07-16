package dangeon.latest.scene.action.otog.ready.select;

import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.BGM;
import main.res.SE;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.otog.ready.select.gacha.Otog_GACHA;
import dangeon.latest.scene.action.otog.ready.select.help.Otog_Help;
import dangeon.latest.scene.action.otog.ready.select.keys.OtogKeyConfig;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Content_Selection;
import dangeon.latest.system.KeyHolder;
import dangeon.model.map.PresentField;

public class Otog_Select_First extends Base_Scene {
	public final ArrayList<Otog_Select_Content> content = new ArrayList<Otog_Select_Content>();
	public int y;

	public final BGM PRE;

	public Otog_Select_First(KeyHolder kh, Base_View bv, final Base_Scene PREV) {
		super(kh, new Otog_Select_View(bv));
		PRE = BGM.get();
		BGM.iku.play();
		content.add(new Otog_Select_Content("SELECT MUSIC", new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				for (Otog_Select_Content c : content)
					c.init();
				setNextScene(new Otog_Select(KH, CURRENT_VIEW));
			}
		}));
		content.add(new Otog_Select_Content("GACHA", new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				for (Otog_Select_Content c : content)
					c.init();
				setNextScene(new Otog_GACHA(KH, CURRENT_VIEW));
			}
		}));
		content.add(new Otog_Select_Content("CONFIG", new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				for (Otog_Select_Content c : content)
					c.init();
				setNextScene(new OtogKeyConfig(KH, CURRENT_VIEW));
			}
		}));
		content.add(new Otog_Select_Content("HOW TO PLAY", new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				for (Otog_Select_Content c : content)
					c.init();
				setNextScene(new Otog_Help(KH, CURRENT_VIEW));
			}
		}));
		content.add(new Otog_Select_Content("END", new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				new BlackOut(new Task() {
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						for (Otog_Select_Content c : content)
							c.init();
						PresentField.get().getBGM().play();
						setNextScene(PREV);
					}
				});
			}
		}));
		y = 0;
		init();
	}

	@Override
	public boolean action(ACTION a) {
		if (a == ACTION.ENTER) {
			SE.SYSTEM_ENTER.play();
			getContent(0).enter();
		} else if (a == ACTION.CANCEL || a == ACTION.MENU) {
			SE.SYSTEM_CANCEL.play();
			y = content.size() - 1;
			init();
		}
		return END;
	}

	@Override
	public boolean arrow(DIRECTION d) {
		SE.SYSTEM_CURSOR.play();
		if (d.Y != 0) {
			y += d.Y;
			if (y < 0)
				y += content.size();
			if (y >= content.size())
				y -= content.size();
			init();
		}
		return END;
	}

	public Otog_Select_Content getContent(int i) {
		i += y;
		while (i < 0)
			i += content.size();
		while (i >= content.size())
			i -= content.size();
		return content.get(i);
	}

	public void init() {
		for (Otog_Select_Content_Selection c : content) {
			c.frame = -1;
		}
		getContent(0).frame = 0;
	}

	@Override
	public void upDate() {
		for (Otog_Select_Content c : content) {
			c.upDate();
		}
	}

}
