package dangeon.view.detail;

import java.awt.Graphics2D;
import java.awt.Image;

import main.constant.FR;
import dangeon.controller.listener.menu.RECORDS;
import dangeon.latest.scene.action.message.Message;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.StringFilter;
import dangeon.view.util.Window;

public class Records {
	public static final Image WINDOW;
	public static final int MAX;
	static {
		WINDOW = Window.get(FR.SCREEN_WIDTH - SCREEN.X * 2, FR.SCREEN_HEIGHT
				- SCREEN.Y * 2);
		MAX = WINDOW.getHeight(null) / (NormalFont.HEIGHT + NormalFont.LEADING);
	}

	public static void draw(Graphics2D g) {
		g.drawImage(WINDOW, SCREEN.X, SCREEN.Y, null);
		int y = RECORDS.getY();
		int max = Math.min(Message.getRecordSize() - y, MAX);
		StringFilter.setOnlyColor(g,
				Message.getRecord(Message.getRecordSize() - 1 - (max - 2) - y));
		for (int i = max - 1; i >= 0; i--) {
			StringFilter.drawString(g,
					Message.getRecord(Message.getRecordSize() - 1 - i - y),
					SCREEN.X + SCREEN.Y_MARGINE, FR.SCREEN_HEIGHT - SCREEN.Y
							- SCREEN.Y_MARGINE
							- (NormalFont.HEIGHT + NormalFont.LEADING) * i);
		}
	}

	// public static void draw(Graphics2D g) {
	//
	// int x = SCREEN.X + SCREEN.X_MARGINE;
	// int w = Frame.SCREEN_WIDTH - x * 2;
	// int h = (NormalFont.HEIGHT + SCREEN.Y_MARGINE) * 2 - NormalFont.LEADING;
	// int y = Frame.SCREEN_HEIGHT - h - SCREEN.Y_MARGINE;
	// int[] xPoints = { x + w / 2 - 10, x + w / 2, x + w / 2 + 10 };
	// int[] yPoints = { y + 2 - SCREEN.Y_MARGINE, y - 2,
	// y + 2 - SCREEN.Y_MARGINE };
	// for (int i = 0; i < 7; i++) {
	// Window.draw(g, SCREEN.X, y, w, h);
	// int index = 2 * (i + RECORDS.y);
	// if(index+1<MessageRecord.getMessageRecord().size()){
	// StringFilter.drawString(g,
	// MessageRecord.getMessageRecord().get(index+1),
	// x,y+SCREEN.Y_MARGINE-NormalFont.LEADING+NormalFont.HEIGHT);
	// StringFilter.drawString(g,
	// MessageRecord.getMessageRecord().get(index),
	// x,y+SCREEN.Y_MARGINE-NormalFont.LEADING+NormalFont.HEIGHT*2);
	// }
	// y -= h + SCREEN.Y_MARGINE;
	// g.fillPolygon(xPoints, yPoints, 3);
	// for (int j = 0; j < yPoints.length; j++)
	// yPoints[j] -= h + SCREEN.Y_MARGINE;
	// }
	// }

}
