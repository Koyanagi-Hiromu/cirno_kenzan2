package cirno_question;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cirno_question.ObjectNames.Category;
import cirno_question.frame.MainFrame;
import cirno_question.maptool.MapFrame;

public class MainListener implements ActionListener {
	public static MainListener ME = new MainListener();

	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel jp = whichPanel(e.getActionCommand());
		if (jp == null) {
			QS.map_clip_object = QS.select_clip_object.getSetOnMapObject();
			QS.map_clip_category = null;
			MapFrame.ME.clip_board.setText(QS.map_clip_object.getName());
			return;
		}
		if (jp.equals(MainFrame.ME.object_p)) {
			String button_text = ((JButton) e.getSource()).getText();
			MainFrame.ME.setExplanText(Category.getObjectExplain(button_text));
			Category.setObjectClip(button_text);
			QS.map_clip_object = QS.select_clip_object.getSetOnMapObject();
			QS.map_clip_category = null;
			MapFrame.ME.clip_board.setText(QS.map_clip_object.getName());
		} else if (jp.equals(MainFrame.ME.category_p)) {
			String button_text = ((JButton) e.getSource()).getText();
			MainFrame.ME.detail_p.removeAll();
			MainFrame.ME.detail_p.repaint();
			MainFrame.ME.check_p.removeAll();
			MainFrame.ME.check_p.repaint();
			MainFrame.ME.object_p.removeAll();
			MainFrame.ME.object_p.repaint();
			QS.category_position = button_text;
			MainFrame.ME.setObjectPanel();
			MainFrame.ME.setObjectDetail();
			MainFrame.ME.object_p.revalidate();
			MainFrame.ME.detail_p.revalidate();
			MainFrame.ME.check_p.revalidate();
		}
	}

	private JPanel whichPanel(String str) {
		if (str.matches("category")) {
			return MainFrame.ME.category_p;
		} else if (str.matches("object")) {
			return MainFrame.ME.object_p;
		} else {
			return null;
		}
	}
}
