package dangeon.view.anime;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import main.constant.FR;
import main.util.BeautifulView;

public class CirnoIceEffect extends Base_Effect {

	public CirnoIceEffect() {
		frame_image = new ArrayList<Image>(1);
		frame_image.add(loadImage("anime/ice.png"));
	}

	@Override
	protected boolean draw(Graphics2D g) {
		int max = 20;
		if (getFrame() == max) {
			return false;
		}
		int size_max = FR.SCREEN_WIDTH * 3 / 2;
		int size = (getFrame() > max / 3) ? size_max : size_max * getFrame()
				* 3 / max;
		AffineTransform firts_at = g.getTransform();
		AffineTransform at = new AffineTransform(firts_at);
		at.translate(FR.SCREEN_WIDTH / 2, FR.SCREEN_HEIGHT / 2);
		g.setTransform(at);
		BeautifulView.setAlphaOnImg(g,
				new Float(0.2 * Math.sin(Math.PI * getFrame() / max)));
		g.setColor(Color.CYAN);
		g.fillRect(-size_max / 2, -size_max / 2, size_max, size_max);
		BeautifulView.setAlphaOnImg(g, 0.6F);
		g.drawImage(frame_image.get(0), -size / 2, -size / 2, size, size, null);
		BeautifulView.setAlphaOnImg(g, 0.1F);
		g.drawImage(frame_image.get(0), -size / 4, -size / 4, size / 2,
				size / 2, null);
		BeautifulView.setAlphaOnImg(g, 1F);
		g.setTransform(firts_at);
		return true;
	}

}
