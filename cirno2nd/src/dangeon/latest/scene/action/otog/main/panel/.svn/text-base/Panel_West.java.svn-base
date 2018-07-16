package dangeon.latest.scene.action.otog.main.panel;

import java.awt.Graphics2D;

import main.res.BGM;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.Fader;
import dangeon.view.detail.Header;

public class Panel_West extends Base_Panel {
	public final Panel_Main P;
	public DIRECTION d = DIRECTION.NEUTRAL;
	private int FIRST_MUTE, LENGTH;

	private int y, _y;

	private int posing_frame;

	private final int POSING_FRAME = 16;
	private int end_frame;
	private boolean first = true;
	private boolean flag_end;
	private boolean save_flags[] = new boolean[100];

	public Panel_West(Panel_Main p, int margine) {
		super(margine, p.Y, 40, p.H, p);
		P = p;
		init();
		p.VH.west = this;
	}

	@Override
	public void drawME(Graphics2D g) {
		int size = 100;
		// g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(0, 0, W, H);
		if (P != null && P.VH.LIFE_100 < 2000)
			Header.drawIMLC_GURUGURU(g, 0, H + 15 - size, size);
		else
			Header.drawIMLC_DOYA(g, 0, H + 15 - size, size);
		g.drawImage(P.VH.HASH.getImage(d, posing_frame), -5, y, null);
	}

	private void frame() {
		if (posing_frame > 0) {
			posing_frame--;
		} else {
			y = _y;
			d = DIRECTION.NEUTRAL;
		}
	}

	public void init() {
		for (int i = 0; i < save_flags.length; i++) {
			save_flags[i] = false;
		}
		end_frame = 0;
		P.VH.LIFE_PROCESS.clear();
		P.VH.SCORE_PROCESS.clear();
	}

	private void save(long p) {
		for (int i = 0; i < save_flags.length; i++) {
			if (!save_flags[i]) {
				if (p > i * 10) {
					P.VH.LIFE_PROCESS.add(P.VH.LIFE_100);
					P.VH.SCORE_PROCESS.add(P.VH.score_milli);
					save_flags[i] = true;
				}
				return;
			}
		}
	}

	public void set(DIRECTION d) {
		this.d = d;
		posing_frame = POSING_FRAME;
		y = _y;
	}

	public void upDate(long pre_ave) {
		frame();
		if (first) {
			first = false;
			FIRST_MUTE = (int) (P.VH.bgm.getMP3MilliFrame(4000) / 1000) - 5;
			LENGTH = P.VH.end_mp3_frame - FIRST_MUTE;
		}
		// int end = BGM.end_frame
		long p = (pre_ave - FIRST_MUTE) * 1000 / LENGTH;
		if (!flag_end && !P.black_oouting) {
			if ((p > 10 && BGM.isBGMStatusStopped())
					|| (p >= 1000 && end_frame++ > 15)) {
				flag_end = true;
				for (int i = 0; i < save_flags.length; i++) {
					if (!save_flags[i]) {
						P.VH.LIFE_PROCESS.add(P.VH.LIFE_100);
						P.VH.SCORE_PROCESS.add(P.VH.score_milli);
						save_flags[i] = true;
					}
				}
				new Fader(P.VH.MAIN_PANEL.PARENT);
				return;
			}
		}
		if (p >= 0) {
			save(p);
		}
		if (p > 950) {
			p = 950;
		}
		_y = (int) ((p * (H - 40) + 500) / 1000);
	}

}
