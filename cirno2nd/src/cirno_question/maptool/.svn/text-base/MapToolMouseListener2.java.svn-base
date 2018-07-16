package cirno_question.maptool;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cirno_question.QS;

public class MapToolMouseListener2 implements MouseListener {
	public static MapToolMouseListener2 ME = new MapToolMouseListener2();

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println(MainMap.ME.getMass(e.getPoint()).getMassCategory());
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
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// QuestionStatus.graphic_flag = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// QuestionStatus.graphic_flag = false;
	}

}
