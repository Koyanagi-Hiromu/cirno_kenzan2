package dangeon.latest.scene.action.menu.first.config.keys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Listener;
import main.Listener.ACTION;
import main.pad.ListenerAdapter;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.latest.util.view_window.WindowContent;
import dangeon.model.config.Config;
import dangeon.view.constant.NormalFont;

public class KeyConfig extends Base_Scene_Menu {

	private class MyWindow extends UnderMenuWindow {
		public MyWindow() {
			super(4, Message.Y, NormalFont.NORMALFONT.deriveFont(11f),
					new WindowContent[0]);
		}

		@Override
		protected void drawString(Graphics2D g, int X, int Y) {
			g.setFont(FONT);
			for (int i = 0; i < explain.length; i++) {
				drawString(g, explain[i], i + 1, x, 0);
			}
		}
	}

	public KeyConfig(KeyHolder kh, Base_View bv) {
		super(12, 3, kh, new KeyConfig_View(bv));
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

	public String getDirection(int x, int y) {
		if (x == 1) {
			return "→";
		} else if (x == -1) {
			return "←";
		} else if (y == 1) {
			return "↓";
		} else if (y == -1) {
			return "↑";
		} else {
			return "";
		}
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		setDeprecatedPerfetedlyContents();
		setDeprecatedContentsHorizon();
		for (ACTION a : ACTION.getMainCommands()) {
			setContents(a);
		}
		setDeprecatedContentsHorizon();
		setContents(Color.ORANGE.toString() + "END", "設定を完了します", new Book() {
			@Override
			protected void work() {
				setNextScene(getPreviousScene());
			}
		});
		setDeprecatedPerfetedlyContents();
		setDeprecatedPerfetedlyContents();
		for (ACTION a : ACTION.getSubCommands()) {
			setContents(a);
		}
		setDeprecatedPerfetedlyContents();
		setDeprecatedPerfetedlyContents();
		setContents(Color.RED.toString() + "RESET", "設定を初期状態に戻します", new Book() {
			@Override
			protected void work() {
				SE.SYSTEM_ARRANGEMENT.play();
				Listener.ME.reset();
			}
		});
		setDeprecatedPerfetedlyContents();
		setDeprecatedPerfetedlyContents();
		for (DIRECTION d : DIRECTION.values_onlyBasic4())
			setDirectionContents(d);
		for (DIRECTION d : DIRECTION.values_onlyNANAME4())
			setDirectionContents(d);
		setDeprecatedPerfetedlyContents();
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return new MyWindow();
	}

	@Override
	protected void initialX_Y() {
		super.initialX_Y();
		y = COL - 1;
	}

	private void setContents(ACTION a) {
		String[] s = a.EXP;
		if (a.IS_MAIN) {
			s = new String[s.length + 1];
			s[0] = Color.YELLOW + "メインキーです　Keyを必ず設定してください" + Color.WHITE;
			for (int i = 0; i < a.EXP.length; i++) {
				s[i + 1] = a.EXP[i];
			}
		}
		setContents(a.name(), s, new Book() {
			@Override
			protected void work() {
			}
		}, a);
	}

	private void setDirectionContents(DIRECTION d) {
		String[] s = new String[4];
		if (d.isBias()) {
			s[0] = "ななめキーです";
			s[1] = (d.X > 0 ? "右" : "左") + (d.Y > 0 ? "下" : "上") + "です";
			s[2] = "";
			s[3] = "パッドを当てることはできません";
		} else {
			s[0] = "基本十字キーです";
			s[1] = "アローキーは常にこれに割り振られます";
			s[2] = "";
			s[3] = "パッドを当てることはできません";
		}
		setContents(getDirection(d.X, 0).concat(getDirection(0, d.Y)), s,
				new Book() {
					@Override
					protected void work() {
					}
				}, d);
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
		int code = ev.getKeyCode();
		if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_UP
				|| code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_LEFT)
			return;
		if (getSelectedContent().OBJECT != null) {
			if (getSelectedContent().OBJECT instanceof DIRECTION) {
				DIRECTION d = (DIRECTION) getSelectedContent().OBJECT;
				SE.SYSTEM_ENTER.play();
				Config.saveKey(code, d);
			} else if (getSelectedContent().OBJECT instanceof ACTION) {
				ACTION a = (ACTION) getSelectedContent().OBJECT;
				if (Listener.getAllow_map().get(code) == null) {
					int pad = ListenerAdapter.getPrevInt();
					if (pad != -1) {
						SE.SYSTEM_ENTER.play();
						Config.setPadKey(pad, a.KEY_NUM);
					} else {
						SE.SYSTEM_ENTER.play();
						Config.saveKey(code, a);
					}
				}
			}
		}
	}

	@Override
	protected boolean vertical_sort() {
		return true;
	}

}