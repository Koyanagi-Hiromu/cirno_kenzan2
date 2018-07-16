package event.stafroll.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import main.constant.FR;
import main.util.BeautifulView;
import event.Event_Drawen_Object;

public class Stafroll_images {
	private final ArrayList<Event_Drawen_Object> ObjectListDeep = new ArrayList<Event_Drawen_Object>(),
			ObjectListMiddle = new ArrayList<Event_Drawen_Object>(),
			ObjectListShallow = new ArrayList<Event_Drawen_Object>();

	public Stafroll_images() {
		ObjectListDeep.add(new Event_Drawen_Object("ending_haikei", 0, 0));
		ObjectListShallow.add(new Event_Drawen_Object("chiruno_daichan2",
				FR.SCREEN_WIDTH + 15, 70) {
			private int frame = 0;

			@Override
			public boolean drawImage(Graphics2D g) {
				BeautifulView.setAlphaOnImg(g, 0.5f);
				frame++;
				g.drawImage(BI, X - frame / 20, Y, W, H, null);
				BeautifulView.setAlphaOnImg(g, 1f);
				return false;
			}
		});
	}

	public void draw(Graphics2D g) {
		for (Iterator<Event_Drawen_Object> i = ObjectListDeep.iterator(); i
				.hasNext();) {
			Event_Drawen_Object o = i.next();
			if (o.drawImage(g)) {
				i.remove();
			}
		}
		for (Iterator<Event_Drawen_Object> i = ObjectListMiddle.iterator(); i
				.hasNext();) {
			Event_Drawen_Object o = i.next();
			if (o.drawImage(g)) {
				i.remove();
			}
		}
		for (Iterator<Event_Drawen_Object> i = ObjectListShallow.iterator(); i
				.hasNext();) {
			Event_Drawen_Object o = i.next();
			if (o.drawImage(g)) {
				i.remove();
			}
		}
	}

}
