package dangeon.latest.scene.action.ksg1;

import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.SE;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.ksg1.help.Ksg_Help;
import dangeon.latest.scene.action.ksg1.main.Ksg_Main;
import dangeon.latest.scene.action.otog.ready.select.Otog_Select_Content;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Content_Selection;
import dangeon.latest.system.KeyHolder;
import dangeon.model.map.PresentField;

public class Ksg_First extends Base_Scene {
	public final ArrayList<Otog_Select_Content> content = new ArrayList<Otog_Select_Content>();
	public int y;

	public final Base_Scene PREV;

	public Ksg_First(KeyHolder kh, Base_View bv, Base_Scene prev) {
		super(kh, new Ksg_First_View(bv));
		PREV = prev;
		content.add(new Otog_Select_Content("START GAME", new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				for (Otog_Select_Content c : content)
					c.init();
				new Ksg_Main(KH, CURRENT_VIEW);
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
				setNextScene(new Ksg_Help(KH, CURRENT_VIEW));
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
