package dangeon.latest.scene.action.menu.first.infomation.contents.conditions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import main.constant.FR;
import main.res.Image_Icon;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.latest.util.view_window.WindowContent;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.NormalFont;
import dangeon.view.detail.MainMap;
import dangeon.view.util.StringFilter;

public class Conditions extends Base_Scene_Menu {

	// public final Base_Scene PREVIOUS_SCENE;
	private class MyWindow extends UnderMenuWindow {
		private CONDITION CON = null;

		public MyWindow() {
			super(5, Message.Y - 11, NormalFont.NORMALFONT.deriveFont(11f),
					new WindowContent[0]);
		}

		@Override
		protected void drawString(Graphics2D g, int X, int Y) {
			if (CON == null)
				return;
			g.setFont(FONT);
			Player c = new Player(CON);
			int x = FR.SCREEN_WIDTH - X - 70, y = Y + 45;
			MainMap.drawShadow(g, c.getShadowSize100(), x, y + 2,
					c.getShadowY(), c);
			if (CON != CONDITION.透明)
				c.drawCreature(g, new Point(x, y));
			x = X + 14;
			y = Y + 40;
			g.drawImage(Image_Icon.getImage(CON), x, y, 30, 30, null);
			String str;
			if (CON.is_good) {
				str = Color.PINK.toString().concat("GOOD");
				x += 3;
			} else if (CON.is_bad) {
				str = Color.GREEN.toString().concat("BAD");
				x += 6;
			} else {
				str = "";
			}
			StringFilter.drawString(g, str, x, y - 3);
			g.setColor(Color.WHITE);
			x = 50;
			for (int i = 0; i < explain.length; i++) {
				drawString(g, explain[i], i + 1, x, 0);
			}
			if (CON.ONLY_FOR != null) {
				String s = Color.ORANGE + "この状態異常は";
				s = s.concat(CON.ONLY_FOR ? "チルノ" : "敵");
				s = s.concat("にしか起こりません");
				drawString(g, s, 4, x, 0);
			}
			drawString(g,
					Color.CYAN.toString().concat("発生源：").concat(CON.ORIGIN), 5,
					50, 0);

		}

		public void setExplain(CONDITION c) {
			setExplain(c.TEXTS);
			CON = c;
		}

		@Override
		public void setExplain(String[] explain) {
			for (int i = 0; i < explain.length; i++) {
				explain[i] = explain[i].replaceAll("&r", Color.RED.toString());
				explain[i] = explain[i]
						.replaceAll("&w", Color.WHITE.toString());
				explain[i] = explain[i].replaceAll("&o",
						Color.ORANGE.toString());
			}
			super.setExplain(explain);
		}
	}

	public Conditions(KeyHolder kh, Base_View bv) {
		super(9, 4, kh, new Conditions_View(bv));
	}

	@Override
	protected void action_else() {
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		for (CONDITION c : CONDITION.values()) {
			setContents(c);
		}
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return new MyWindow();
	}

	private void setContents(CONDITION c) {
		if (c.equals(CONDITION.目薬)) {
		}
		if (c.equals(CONDITION.おにぎり)) {
			setDeprecatedPerfetedlyContents();
		}
		if (c.equals(CONDITION.イカリ)) {
			// setDeprecatedPerfetedlyContents();
		}
		if (c.equals(CONDITION.天邪鬼)) {
			// setDeprecatedPerfetedlyContents();
		}
		if (c.equals(CONDITION.死)) {
			setDeprecatedPerfetedlyContents();
		}
		if (c.equals(CONDITION.回復)) {
			setDeprecatedPerfetedlyContents(3);
		}
		if (c.equals(CONDITION.絶対必中)) {
			setDeprecatedPerfetedlyContents();
			return;
		}
		if (c.equals(CONDITION.暗闇)) {
			return;
		}
		if (c.equals(CONDITION.安心)) {
			return;
		}
		if (c.equals(CONDITION.爆弾)) {
			return;
		}
		String s = c.NAME;
		if (s.length() < 6)
			s = s.concat("　");
		Color col = null;
		if (Player.me.conditionCheck(c))
			col = Color.PINK;
		if (col != null)
			s = col.toString().concat(s);
		setContents(s, c.TEXTS, new Book() {
			@Override
			protected void work() {
				setNextScene(Scene_Action.getMe());
			}
		}, c);

	}

	@Override
	protected void setExplain() {
		if (SUB_WINDOW != null && getSelectedContent() != null) {
			((MyWindow) SUB_WINDOW)
					.setExplain((CONDITION) getSelectedContent().OBJECT);
		}
	}

	@Override
	protected boolean vertical_sort() {
		return true;
	}

}