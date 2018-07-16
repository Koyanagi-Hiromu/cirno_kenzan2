package event.stafroll.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.constant.FR;
import main.res.BGM;
import main.util.BeautifulView;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.view.util.StringFilter;

public class Stafroll_Staffs {
	private int frame = 0;
	private int delt;
	private int speed = 5;
	private final SpellCard SC;

	private boolean flag_end = false;

	public Stafroll_Staffs(SpellCard sc) {
		this.SC = sc;
		delt = FR.SCREEN_WIDTH - SC.IMLC.W + 50 - 10;
	}

	public boolean draw(Graphics2D g) {
		Font font = g.getFont();
		g.setFont(font.deriveFont(20f));
		int x = 30;
		int y = 200;
		int intend = 20;
		delt -= speed * 3;
		int max = FR.SCREEN_WIDTH - SC.IMLC.W - 10;
		if (delt < max) {
			delt = max;
			frame++;
		}
		if (frame == 0) {
			BeautifulView.setAlphaOnImg(g, 1f * (FR.SCREEN_WIDTH - delt)
					/ (FR.SCREEN_WIDTH - max));
			drawIMLC(g, x, y, delt);
		} else {
			String[] arr = { "ドット制作", SC.getDoter(), "イラスト制作",
					SC.getIllustlator(), "BGMアレンジ", BGM.getAutor(SC) };
			int h = 20;
			int move_frame = 5;
			int wait_frame = move_frame * arr.length * 2;
			int result_wait_frame = move_frame * arr.length + wait_frame;
			if (frame > result_wait_frame + move_frame * arr.length) {
				int fr = frame - (result_wait_frame + move_frame * arr.length);
				BeautifulView.setAlphaOnImg(g, 1f * (move_frame - fr)
						/ move_frame);
				if (fr >= move_frame) {
					flag_end = true;
				}
				drawIMLC(g, x, y, delt);
			} else {
				BeautifulView.setAlphaOnImg(g, 1f);
				drawIMLC(g, x, y, delt);
			}
			if (frame > result_wait_frame) {
				int fr = frame - result_wait_frame;
				for (int i = 0; i < arr.length; i++) {
					String str = arr[i];
					int _x;
					if (fr <= move_frame * i) {
						_x = x;
						BeautifulView.setAlphaOnImg(g, 1f);
					} else if (fr > move_frame * (i + 1)) {
						continue;
					} else {
						_x = x + (move_frame * (i) - fr) * speed;
						BeautifulView.setAlphaOnImg(g, 1f
								* (move_frame * (i + 1) - fr) / move_frame);
					}
					g.drawString(str, (i % 2 == 0) ? _x : _x + intend, y + h
							* i);
				}
			} else {
				for (int i = 0; i < arr.length; i++) {
					String str = arr[i];
					int _x;
					if (frame <= move_frame * i) {
						continue;
					} else if (frame > move_frame * (i + 1)) {
						BeautifulView.setAlphaOnImg(g, 1f);
						_x = x;
					} else {
						_x = x + (move_frame * (i + 1) - frame) * speed;
						BeautifulView.setAlphaOnImg(g, 1f - 1f
								* (move_frame * (i + 1) - frame) / move_frame);
					}
					g.drawString(str, (i % 2 == 0) ? _x : _x + intend, y + h
							* i);
				}
			}
		}
		g.setFont(font);
		return flag_end;
	}

	private void drawIMLC(Graphics2D g, int x, int y, int delt) {
		SC.IMLC.draw(g, delt);
		g.setColor(Color.WHITE);
		StringFilter.drawEdgedString_plain(g, SC.getCharacterName(), x, 165);
		BeautifulView.setAlphaOnImg(g, 1f);
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRoundRect(25, y - 30, 220, 150, 10, 10);
		g.setColor(Color.WHITE);
	}
}
