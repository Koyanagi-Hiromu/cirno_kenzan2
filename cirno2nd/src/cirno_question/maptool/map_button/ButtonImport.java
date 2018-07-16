package cirno_question.maptool.map_button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import cirno_question.file_system.FileImport;
import cirno_question.maptool.MapFrame;

public class ButtonImport implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int option1 = JOptionPane.showConfirmDialog(MapFrame.ME,
				"現在のマップが消えますが宜しいですか？", "オープン確認", JOptionPane.OK_CANCEL_OPTION);
		if (option1 != 0) {
			return;
		}
		JFileChooser filechooser = new JFileChooser(new File("")
				.getAbsolutePath().concat("/question"));
		FileFilter filter_qes = new FileNameExtensionFilter("QESファイル", "qes");
		filechooser.addChoosableFileFilter(filter_qes);
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.setDialogTitle("問題マップを開く");
		JLabel label = new JLabel();
		int selected = filechooser.showOpenDialog(MapFrame.ME);
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = filechooser.getSelectedFile();
			FileImport.create(file);
			label.setText(file.getName());
			MapFrame.ME.main_p.repaint();
		} else if (selected == JFileChooser.CANCEL_OPTION) {
			label.setText("キャンセルされました");
		} else if (selected == JFileChooser.ERROR_OPTION) {
			label.setText("エラー又は取消しがありました");
		}
	}
}
