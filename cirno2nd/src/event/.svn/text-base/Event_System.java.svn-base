package event;

import java.awt.Graphics2D;

import main.Base_System;

public class Event_System extends Base_System {
	public Event_System() {
		super(new Event_Key_Accepter());
	}

	@Override
	public void draw(Graphics2D g) {
		Event_Scene.get();
		Event_Scene.DRWER.draw(g);
	}

	@Override
	public void upDate() {
		Event_Scene.get();
		Event_Scene.UPDATER.upDate();
	}
}
