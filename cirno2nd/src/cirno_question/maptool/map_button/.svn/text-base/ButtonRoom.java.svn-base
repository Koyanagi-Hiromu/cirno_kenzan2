package cirno_question.maptool.map_button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cirno_question.QS;
import cirno_question.maptool.MapFrame;
import cirno_question.maptool.MassCategory;

public class ButtonRoom implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		QS.map_clip_category = MassCategory.ROOM;
		MapFrame.ME.clip_board.setText(QS.map_clip_category.getMassOneName());
	}
}
