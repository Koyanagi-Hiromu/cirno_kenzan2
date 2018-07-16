package dangeon.latest.scene.action.otog.ready.select.keys;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Listener;
import main.Listener.ACTION;
import main.res.SE;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.model.config.Config;
import dangeon.view.util.StringFilter;

public class OtogKeyConfig extends Base_Scene_Menu {

	public final String ARROW = "CIRNO", TARGET = "ADJUST", FPS = "FPS";

	public OtogKeyConfig(KeyHolder kh, Base_View bv) {
		super(null, 1, kh, new OtogKeyConfig_View(bv));
	}

	@Override
	public boolean action(ACTION a) {
		if (getSelectedContent().OBJECT == null)
			return super.action(a);
		return END;
	}

	@Override
	protected void action_cancel() {
		SE.SYSTEM_CURSOR.play();
		x = 0;
		y = COL - 1;
		setExplain();
	}

	@Override
	protected void action_else() {
	}

	@Override
	public String getContentName(int index) {
		String s = CONTENTS.get(index).NAME;
		if (s.matches(ARROW)) {
			boolean b = Config.isOtogArrowRight();
			Color l = b ? Color.GRAY : StringFilter.NUMBERS;
			Color r = !b ? Color.GRAY : StringFilter.NUMBERS;
			s = Color.CYAN.toString().concat(s).concat(l.toString())
					.concat("   LEFT").concat(r.toString()).concat("　RIGHT");
		} else if (s.matches(TARGET)) {
			int adj = Config.getOtogAdjust();
			String signal = adj == 0 ? "±" : (adj > 0 ? "+" : "");
			s = Color.CYAN.toString().concat(s).concat("  ")
					.concat(StringFilter.NUMBERS.toString()).concat(signal)
					.concat(String.valueOf(adj));
		} else if (s.matches(FPS)) {
			boolean b = Config.getOtogFPS() == 30;
			Color l = !b ? Color.GRAY : StringFilter.NUMBERS;
			Color r = b ? Color.GRAY : StringFilter.NUMBERS;
			s = Color.CYAN.toString().concat(s).concat(l.toString())
					.concat("     30").concat(r.toString()).concat("　60");
		}
		return s;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		setDeprecatedPerfetedlyContents();
		setDeprecatedContentsHorizon();
		for (int i = 0; i < 4; i++) {
			setContents(i);
		}
		setDeprecatedContentsHorizon();
		setContents(ARROW, "", new Book() {
			@Override
			protected void work() {
				Config.switchOtogArrow();
			}
		});
		// setContents(FPS, "", new Book() {
		// @Override
		// protected void work() {
		// Config.changeOtogFPS();
		// }
		// });
		setContents(TARGET, "", new Book() {
			@Override
			protected void work() {
				Config.addOtogAdjust();
			}
		});
		setDeprecatedContentsHorizon();
		setContents(Color.ORANGE.toString() + "END", "設定を完了します", new Book() {
			@Override
			protected void work() {
				setNextScene(getPreviousScene());
			}
		});
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return null;
	}

	@Override
	protected void initialX_Y() {
		super.initialX_Y();
		y = COL - 1;
	}

	private void setContents(int i) {
		i++;
		setContents(String.valueOf(i), new String[] { "" }, new Book() {
			@Override
			protected void work() {
			}
		}, i);
	}

	@Override
	protected void setExplain() {
		if (SUB_WINDOW != null && getSelectedContent() != null) {
			SUB_WINDOW.setExplain(getSelectedContent().EXPLAIN);
		}
	}

	@Override
	public void upDate() {
	}

	@Override
	public void upDate(KeyEvent ev) {
		if (getSelectedContent().OBJECT != null) {
			int id = (Integer) getSelectedContent().OBJECT;
			int code = ev.getKeyCode();
			if (Listener.getAllow_map().get(code) == null) {
				ACTION a = Listener.getKey().get(code);
				if (a != null) {
					Config.saveOtogKey(id, a);
					SE.SYSTEM_ARRANGEMENT.play();
					y++;
					if (isContentDeprecated(getIndex()))
						y--;
				}
			}
		}
	}

}