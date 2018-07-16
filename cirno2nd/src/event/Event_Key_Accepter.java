package event;

import java.awt.event.KeyEvent;

import main.Base_KeyAccepter;
import main.Listener;
import main.Listener.ACTION;
import main.util.DIRECTION;

public class Event_Key_Accepter extends Base_KeyAccepter {

	@Override
	public void keyPressed(KeyEvent e) {
		ACTION a = Listener.getKey().get(e.getKeyCode());
		DIRECTION d = Listener.getAllow_map().get(e.getKeyCode());
		if (a != null) {
			Event_Scene.UPDATER.keyPressed(a);
		}
		if (d != null) {
			Event_Scene.UPDATER.keyPressed(d);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
