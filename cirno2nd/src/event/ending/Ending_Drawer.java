package event.ending;

import java.awt.Color;
import java.awt.Graphics2D;

import main.constant.FR;
import main.util.BeautifulView;
import event.Base_Drawer;
import event.Event_Drawen_Object;
import event.Event_Scene;

public class Ending_Drawer extends Base_Drawer {
	private final Event_Drawen_Object[] IM;
	private final Event_Drawen_Object IM_NEXT = new Event_Drawen_Object(
			"ending_haikei", 0, 0);

	public Ending_Drawer() {
		IM = new Event_Drawen_Object[6];
		for (int i = 0; i < IM.length; i++) {
			IM[i] = new Event_Drawen_Object("end" + (i + 1), 0, 0);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		int i = getUpdater().getPage();
		if (getUpdater().isEnd()) {
			IM_NEXT.drawImage(g);
			BeautifulView.setAlphaOnImg(g, getUpdater().getFloat());
			IM[i].drawImage(g);
			BeautifulView.setAlphaOnImg(g, 1f);
		} else {
			IM[i].drawImage(g);
		}
		if (getUpdater().getFrame() < 0) {
			BeautifulView.setAlphaOnImg(g, getUpdater().getFloat());
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
			BeautifulView.setAlphaOnImg(g, 1f);
		}
	}

	private Ending_Updater getUpdater() {
		return (Ending_Updater) Event_Scene.UPDATER;
	}
}
