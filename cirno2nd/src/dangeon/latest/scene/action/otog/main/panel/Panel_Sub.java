package dangeon.latest.scene.action.otog.main.panel;

import java.awt.Color;
import java.awt.Graphics2D;

import main.constant.FR;
import main.res.Image_Artifact;

public class Panel_Sub extends Base_Panel {

	protected Panel_Sub(int x, Panel_Main p, int margine) {
		super(x, FR.SCREEN_HEIGHT - margine - 209, 31, 209, p);
	}

	public Panel_Sub(Panel_Main p, int margine) {
		this(p.X + p.W + margine, p, margine);
	}

	@Override
	public void drawME(Graphics2D g) {
		int h = 20;
		int life = (int) (P.VH.LIFE_100 / 100);
		g.setColor(Color.RED);
		g.drawLine(3, (200 + 2) + 4, W - 3, (200 + 2) + 4);
		g.drawLine(3, (200 - life * 2 - 1) + 4, W - 3, (200 - life * 2 - 1) + 4);
		int rainbow = (int) ((P.pre_ave % 200 - 100) * 3);
		int max = life * 2;
		int sup = (int) ((0.9 + Math.sin(P.pre_ave / 3.0) / 4.0) * max);
		if (P.pre_ave > P.VH.end_mp3_frame) {
			max = life * 2;
			rainbow = -1;
		}
		for (int i = 0; i < max; i++) {
			if (i >= 160)
				g.setColor(Color.CYAN);
			else if (i >= 100)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.BLUE.darker());
			if (i > sup) {
				Color c = g.getColor();
				double rate = (i - sup) / 15.0;
				if (rate < 1)
					rate = 1;
				int rgb[] = new int[3];
				rgb[0] = (int) (c.getRed() / rate);
				rgb[1] = (int) (c.getGreen() / rate);
				rgb[2] = (int) (c.getBlue() / rate);
				for (int j = 0; j < rgb.length; j++) {
					if (rgb[j] < 0)
						rgb[j] = 0;
					else if (rgb[j] > 255)
						rgb[j] = 255;
				}
				c = new Color(rgb[0], rgb[1], rgb[2]);
				g.setColor(c);
			}
			if (i > rainbow - 50 && i <= rainbow) {
				int del = rainbow - i;
				Color c = g.getColor();
				Color c2 = new Color(255 * (50 - del) / 50, 0, 0);
				c = new Color(c2.getRed(), c.getGreen(), c.getBlue());
				g.setColor(c);
			}
			g.drawLine(3, (200 - i) + 4, W - 3, (200 - i) + 4);
		}
		life /= 10;
		for (int i = 0; i < 10; i++) {
			if (life == i)
				return;
			int index = 0;
			if (i > 7)
				index = 2;
			else if (i > 4)
				index = 1;
			g.drawImage(Image_Artifact.FOOD.getImage(index), -8, -8 + h
					* (9 - i), null);
		}
	}

	@Override
	protected int getBGColorB() {
		return 0;
	}

	@Override
	protected int getBGColorG() {
		return 0;
	}

	@Override
	protected int getBGColorR() {
		return 0;
	}
}
