package dangeon.latest.scene.action.otog.ready.select.gacha;

import java.awt.Color;
import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.otog.ready.select.Otog_Select_Content;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Content_Selection;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;

public class Otog_GACHA extends Base_Scene {
	public final ArrayList<Otog_Select_Content> content = new ArrayList<Otog_Select_Content>();
	public final Select_Content_Point POINT = new Select_Content_Point();
	public int y;

	public Otog_GACHA(KeyHolder kh, Base_View bv) {
		super(kh, new Otog_GACHA_View(bv));
		y = 0;
		construct_init();
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

	public void construct_init() {
		content.clear();
		init(1);
		init(3);
		init(5);
		content.add(new Otog_Select_Content("BACK", new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				for (Otog_Select_Content c : content)
					c.init();
				setNextScene(CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE);
			}
		}));
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
		POINT.frame = -1;
		POINT.setTitle(Config.getGachaPoints());
		getContent(0).frame = 0;
	}

	private void init(final int i) {
		if (Config.getGachaPoints() < 9 * i) {
			content.add(new Otog_Select_Content(Color.LIGHT_GRAY.toString() + i
					+ " GACHA (point " + (9 * i) + ")", new Task() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					SE.SYSTEM_CURSE.play();
				}
			}));
		} else {
			content.add(new Otog_Select_Content(i + " GACHA (point " + (9 * i)
					+ ")", new Task() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					for (Otog_Select_Content c : content)
						c.init();
					setNextScene(new Otog_GACHAing(i, KH, CURRENT_VIEW));
				}
			}));
		}
	}

	@Override
	public void upDate() {
		for (Otog_Select_Content c : content) {
			c.upDate();
		}
		POINT.upDate();
	}

}
