package cirno_question.maptool.map_button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import cirno_question.file_system.FileExport;
import cirno_question.maptool.MainMap;
import cirno_question.maptool.MapFrame;

public class ButtonExport implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// FileExport.mapExport();
		if (!MainMap.ME.isEnterExit()) {
			JOptionPane.showMessageDialog(MapFrame.ME, "入口と出口を設置して下さい");
			return;
		}

		JFileChooser filechooser = new JFileChooser(new File("")
				.getAbsolutePath().concat("/question"));
		FileFilter filter_qes = new FileNameExtensionFilter("QESファイル", "qes");
		String file_name = "";
		boolean not_override = true;
		filechooser.addChoosableFileFilter(filter_qes);

		filechooser.setDialogTitle("問題マップを名前を付けて保存する");
		int selected = filechooser.showSaveDialog(MapFrame.ME);
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = filechooser.getSelectedFile();
			try {
				FileReader filereader = new FileReader(file.getAbsolutePath());
				int option = JOptionPane.showConfirmDialog(MapFrame.ME,
						"上書きしますか？", "上書き確認", JOptionPane.OK_CANCEL_OPTION);
				if (option != 0) {
					return;
				}
				System.out.println(filereader.read());
				not_override = false;
			} catch (FileNotFoundException e2) {
				System.out.println(file);
				file_name = "1.qes";
			} catch (IOException ee) {
			}
			// try {
			// file.createNewFile();
			// } catch (IOException e1) {
			// e1.printStackTrace();
			// }
			BufferedWriter filewriter;
			try {
				File f = new File(file.getAbsolutePath().concat(file_name));
				filewriter = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(f), "UTF8"));
				FileExport.mapExport(filewriter);
				filewriter.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (selected == JFileChooser.CANCEL_OPTION) {
			System.out.println("キャンセル");
		} else if (selected == JFileChooser.ERROR_OPTION) {
			System.out.println("エラー");
		}
	}
}
