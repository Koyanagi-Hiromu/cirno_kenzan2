package debugMode.ListenerDebug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Switch;
import debugMode.WindowDebug;

public class MainListenerDebug implements ActionListener, MouseListener {
	public static MainListenerDebug ME = new MainListenerDebug();

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action");
		if (e.getActionCommand().matches("デス")) {
			Switch.switch_player_no_death = !Switch.switch_player_no_death;
			setLabel(WindowDebug.death_l, Switch.switch_player_no_death);
		} else if (e.getActionCommand().matches("予備")) {
			Belongings.setItems(new Stairs(Player.me.getMassPoint()
					.getLocation()));
			// R.next();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1) {
			WindowDebug.items.show(e.getComponent(), 100, 30);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	private void setLabel(JLabel lable, boolean on_off) {
		if (on_off) {
			lable.setText("ON");
		} else {
			lable.setText("OFF");
		}
	}
}
