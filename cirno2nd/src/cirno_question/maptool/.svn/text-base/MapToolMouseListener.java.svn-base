package cirno_question.maptool;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import cirno_question.QS;

public class MapToolMouseListener implements MouseMotionListener {
	public static MapToolMouseListener ME = new MapToolMouseListener();

	@Override
	public void mouseDragged(MouseEvent e) {
		// System.out.println("ドラックX" + e.getPoint().x + "Y" + e.getPoint().y);
		// MapGraphics.end_point_x = e.getPoint().x;
		// MapGraphics.end_point_y = e.getPoint().y;
		if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			MapMass mass = MainMap.ME.getMass(e.getPoint());
			if (mass.isEnemy()) {
				QS.map_clip_object = mass.EM;
				QS.map_clip_category = null;
				MapFrame.ME.clip_board.setText(QS.map_clip_object.getName());
			} else if (mass.isItem()) {
				QS.map_clip_object = mass.A;
				QS.map_clip_category = null;
				MapFrame.ME.clip_board.setText(QS.map_clip_object.getName());
			} else if (mass.isTrap()) {
				QS.map_clip_object = mass.A;
				QS.map_clip_category = null;
				MapFrame.ME.clip_board.setText(QS.map_clip_object.getName());
			} else {
				QS.map_clip_category = mass.MC;
				MapFrame.ME.clip_board.setText(QS.map_clip_category
						.getMassOneName());
			}
		} else if ((e.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {
			QS.map_clip_object = null;
			QS.map_clip_category = null;
			MapFrame.ME.clip_board.setText("消しゴム");
		} else {
			MainMap.ME.setMassCategory(e);
			MapFrame.ME.setDetailText(e.getPoint());
			MapFrame.ME.main_p.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		MapFrame.ME.setDetailText(e.getPoint());

		// System.out.println("X" + e.getPoint().x + "Y" + e.getPoint().y);
		// MapGraphics.start_point_x = e.getPoint().x;
		// MapGraphics.start_point_y = e.getPoint().y;

	}

}
