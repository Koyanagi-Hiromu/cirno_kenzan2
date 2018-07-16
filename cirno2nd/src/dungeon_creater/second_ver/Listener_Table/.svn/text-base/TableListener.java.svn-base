package dungeon_creater.second_ver.Listener_Table;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dungeon_creater.second_ver.DungeonLevelControl;
import dungeon_creater.second_ver.EnemyTableGraphicsPanel;
import dungeon_creater.second_ver.SetObject;

public class TableListener implements MouseListener, MouseMotionListener {
	public static TableListener ME = new TableListener();

	private void mainRepaint() {
		EnemyTableGraphicsPanel.ME.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2) {
			SetObject.clip();
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		SetObject.mouse_point = e.getPoint();
		DungeonLevelControl.dragTab(e.getPoint());
		mainRepaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		SetObject.mouse_point = e.getPoint();
		mainRepaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			SetObject.setDragFlag(e.getPoint());
			DungeonLevelControl.isFlagDragMove(e.getPoint());
			SetObject.setCurrentObject(e.getPoint());
			EnemyTableGraphicsPanel.ME.setBaseCreature(e.getPoint());
			DungeonLevelControl.pushLevelButton(e.getPoint());
			SetObject.addParcentButton();
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			SetObject.reset();
		}
		mainRepaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		SetObject.putDragEnemyTable(e.getPoint());
		DungeonLevelControl.flag_drag_move = false;
		mainRepaint();
	}

}
