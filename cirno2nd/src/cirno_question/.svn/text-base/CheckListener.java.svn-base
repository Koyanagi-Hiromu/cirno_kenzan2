package cirno_question;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cirno_question.ObjectNames.Category;
import cirno_question.frame.MainFrame;

public class CheckListener implements ActionListener {
	public static CheckListener ME = new CheckListener();

	@Override
	public void actionPerformed(ActionEvent e) {
		Category.setListEnemyCheckStage();
		Category.setListCardCheckStage();
		MainFrame.ME.object_p.removeAll();
		MainFrame.ME.object_p.repaint();
		MainFrame.ME.setObjectPanel();
		MainFrame.ME.object_p.revalidate();
	}

}
