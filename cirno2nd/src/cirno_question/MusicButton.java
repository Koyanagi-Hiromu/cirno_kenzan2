package cirno_question;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.res.BGM;
import cirno_question.maptool.MapFrame;

public class MusicButton implements ActionListener {

	public static final ActionListener ME = new MusicButton();

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().matches("再生")) {
			MapToolMusic.ME.play(MapFrame.ME.music_combo.getSelectedItem()
					.toString());
		} else if (e.getActionCommand().matches("停止")) {
			BGM.stop();
		} else if (e.getActionCommand().matches("アップ")) {
			BGM.ascVol();
		} else {
			BGM.dceVol();
		}
	}

}
