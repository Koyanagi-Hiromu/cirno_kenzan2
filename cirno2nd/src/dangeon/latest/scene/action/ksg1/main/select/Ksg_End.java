package dangeon.latest.scene.action.ksg1.main.select;

import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.ksg1.main.Ksg_Main;
import dangeon.latest.scene.action.otog.ready.select.Otog_Select_Content;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Content_Selection;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;

public class Ksg_End extends Base_Scene {
	public final ArrayList<Otog_Select_Content> content = new ArrayList<Otog_Select_Content>();
	public int y;

	public final long COUNT, HIGH_SCORE;

	public boolean FLAG_NEW_RECORD;

	public Ksg_End(KeyHolder kh, Base_View bv, long count) {
		super(kh, new Ksg_End_View(bv));
		COUNT = count;
		FLAG_NEW_RECORD = Config.saveKsg1Score(COUNT);
		HIGH_SCORE = Config.getKsg1Score();
		content.add(new Otog_Select_Content("RETRY", new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				for (Otog_Select_Content c : content)
					c.init();
				setNextScene(((Ksg_Main) getPreviousScene()).init());
			}
		}));
		content.add(new Otog_Select_Content("END", new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				for (Otog_Select_Content c : content)
					c.init();
				setNextScene(((Ksg_Main) getPreviousScene()).prev);
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
