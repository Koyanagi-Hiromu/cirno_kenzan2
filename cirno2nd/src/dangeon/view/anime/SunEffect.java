package dangeon.view.anime;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import main.constant.FR;
import main.util.BeautifulView;

public class SunEffect extends Base_Effect {

	public SunEffect(boolean room) {
		frame_image = new ArrayList<Image>(1);
		frame_image.add(loadImage("anime/sun.png"));
	}

	@Override
	protected boolean draw(Graphics2D g) {
		int max = 20;
		if (getFrame() == max) {
			return false;
		}
		int w = frame_image.get(0).getWidth(null);
		int h = frame_image.get(0).getHeight(null);

		double rate = 0.8;
		g.setColor(Color.RED);
		BeautifulView.setAlphaOnImg(g,
				new Float(0.8 * Math.sin(Math.PI * getFrame() / max)));
		w = (int) (w * rate);
		h = (int) (h * rate);
		g.drawImage(frame_image.get(0), (FR.SCREEN_WIDTH - w) / 2,
				(FR.SCREEN_HEIGHT - h) / 2, w, h, null);
		BeautifulView.setAlphaOnImg(g, 1F);
		return true;
	}

}
