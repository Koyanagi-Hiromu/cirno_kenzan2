package dangeon.latest.scene.action.otog.ready.select.gacha;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import main.Listener.ACTION;
import main.res.SE;
import main.thread.MainThread;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.otog.ready.select.Otog_Select_Content;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Content_Selection;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Initializer;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;
import dangeon.util.R;
import dangeon.view.util.StringFilter;

public class Otog_GACHAing extends Base_Scene {
	public final ArrayList<Otog_Select_Content> content = new ArrayList<Otog_Select_Content>();
	ArrayList<Otog_Select_Content_Selection> random = new ArrayList<Otog_Select_Content_Selection>();
	public int frame = 0;
	public final int MAX_FRAME = 33;
	public boolean flag_cirno_moving = true;

	public int y;

	public Otog_GACHAing(int times, KeyHolder kh, Base_View bv) {
		super(kh, new Otog_GACHAing_View(bv));
		y = 0;
		ArrayList<Otog_Select_Content_Selection> init = new ArrayList<Otog_Select_Content_Selection>();
		new Otog_Select_Initializer().initContents(init, null);
		ArrayList<Otog_Select_Content_Selection> group = new ArrayList<Otog_Select_Content_Selection>();
		while (!init.isEmpty()) {
			grouping(group, init);
			random_add(random, group);
			group.clear();
		}
		initContents(times);
		init();
	}

	@Override
	public boolean action(ACTION a) {
		if (!flag_cirno_moving)
			if (a == ACTION.ENTER || a == ACTION.CANCEL || a == ACTION.MENU) {
				SE.SYSTEM_CANCEL.play();
				Otog_GACHA ga = (Otog_GACHA) CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE;
				ga.construct_init();
				setNextScene(ga);
			}
		return END;
	}

	@Override
	public boolean arrow(DIRECTION d) {
		return END;
	}

	private void grouping(ArrayList<Otog_Select_Content_Selection> group,
			ArrayList<Otog_Select_Content_Selection> init) {
		Otog_Select_Content_Selection parent = init.remove(0);
		group.add(parent);
		for (Iterator<Otog_Select_Content_Selection> iterator = init.iterator(); iterator
				.hasNext();) {
			Otog_Select_Content_Selection checking = iterator.next();
			if (parent.BGM.equals(checking.BGM)
					&& parent.AUTHOR.equals(checking.AUTHOR)) {
				group.add(checking);
				iterator.remove();
			}
		}
	}

	public void init() {
		for (Otog_Select_Content_Selection c : content) {
			c.frame = 0;
		}
	}

	private void initContents(int times) {
		String intend = "　　";
		for (int i = 0; i < times; i++) {
			if (random.isEmpty()) {
				content.add(new Otog_Select_Content(intend
						+ "(ハ・ズ・レ　9point獲得　あとでまた引いてね)", new Task() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					}
				}));
			} else {
				int index = R.ran(random.size());
				Otog_Select_Content_Selection cs = random.remove(index);
				cs.saveProp(true);
				final int lv = cs.LEVEL;
				String s = intend.concat(cs.VERSION).concat(" ")
						.concat(Color.WHITE.toString()).concat(cs.BGM.TITLE);
				content.add(new Otog_Select_Content(s, new Task() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					}
				}) {

					@Override
					protected boolean drawLevel(Graphics2D g, int x, int y,
							int w, int h) {
						g.setColor(TEXT.PERFECT.getColor(MainThread.getFrame() / 2 % 2));
						StringFilter.drawString(g, "NEW!!", x + 2 - 40, y - 10
								+ g.getFontMetrics().getHeight() / 2 - 1);
						StringBuilder sb = new StringBuilder();
						if (lv < 10)
							sb.append(" ");
						sb.append(lv);
						StringFilter.drawString(g, sb.toString(), x + 2, y
								+ g.getFontMetrics().getHeight() / 2 - 1);
						return true;
					}

					@Override
					protected void drawTitle(Graphics2D g, int x, int y, int w,
							int h) {
						String str = TITLE;
						String plan = StringFilter.getPlainString(str);
						if (frame != -1) {
							double d = MainThread.getFrame() / 16.0;
							g.setPaint(new GradientPaint((float) (w * Math
									.cos(d)) + x + w / 2, (float) (h * 2 * Math
									.sin(d)) + y, Color.WHITE, x + w / 2, y,
									Color.ORANGE, true));
						} else
							g.setColor(Color.BLUE);
						g.drawRect(x, y - h / 2, w, h);
						g.setColor(new Color(0, 0, 0, 100));
						g.fillRect(x, y - h / 2, w, h);
						g.setClip(x, y - h / 2, w, h);
						g.setColor(Color.WHITE);
						if (frame != -1) {
							int str_w = g.getFontMetrics().stringWidth(plan) + 10;
							frame += 1;
							str_w += 10;
							if (frame > str_w)
								frame -= str_w;
							StringFilter.drawString(g, str, x + 2 - frame
									+ str_w, y + g.getFontMetrics().getHeight()
									/ 2 - 1);
						}
						StringFilter.drawString(g, str, x + 2 - frame, y
								+ g.getFontMetrics().getHeight() / 2 - 1);
						g.setClip(null);
					}
				});
				Config.changeGachaPoints(-9);
			}
		}
	}

	private void random_add(ArrayList<Otog_Select_Content_Selection> random,
			ArrayList<Otog_Select_Content_Selection> group) {
		int i = 0;
		while (!group.isEmpty()) {
			for (Iterator<Otog_Select_Content_Selection> iterator = group
					.iterator(); iterator.hasNext();) {
				Otog_Select_Content_Selection cs = iterator.next();
				if (cs.VER == i) {
					if (cs.FLAG_CLEARED == -1) {
						random.add(cs);
						return;
					} else if (cs.FLAG_CLEARED <= 1) {
						// 未クリア
						return;
					}
					iterator.remove();
				}
			}
			i++;
		}
	}

	@Override
	public void upDate() {
		if (!flag_cirno_moving) {
			for (Otog_Select_Content c : content) {
				c.upDate();
			}
		} else {
			for (int i = 0; i < 3; i++) {
				if (frame == i * MAX_FRAME / 3)
					SE.HEAL_SMALL.play();
			}
			if (frame++ > MAX_FRAME) {
				flag_cirno_moving = false;
				SE.SYSTEM_ENCHANT.play();
			}
		}
	}
}
