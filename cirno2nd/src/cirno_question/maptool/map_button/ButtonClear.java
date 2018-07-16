package cirno_question.maptool.map_button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cirno_question.QS;
import cirno_question.maptool.MapFrame;
import cirno_question.maptool.MapMass;

public class ButtonClear implements ActionListener {

	public static void massClear(MapMass ms) {
		int i = MapFrame.ME.clear_box.getSelectedIndex();
		switch (i) {
		case 0:
			ms.removeObject();
			break;
		case 1:
			ms.removeEnemy();
			break;
		case 2:
			ms.removeItem();
			break;
		case 3:
			ms.removeTrap();
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		QS.map_clip_object = null;
		QS.map_clip_category = null;
		MapFrame.ME.clip_board.setText("消しゴム");
	}
}
