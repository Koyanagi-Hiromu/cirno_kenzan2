package cirno_question.maptool.map_button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cirno_question.QS;
import cirno_question.maptool.MapFrame;

public class ButtonObject implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// QuestionStatus.map_clip_object = null;
		QS.map_clip_object = QS.select_clip_object.getSetOnMapObject();
		QS.map_clip_category = null;
		MapFrame.ME.clip_board.setText(QS.map_clip_object.getName());
		// System.out.println(QuestionStatus.select_clip_object
		// .getSetOnMapObject().getName());
	}

}
