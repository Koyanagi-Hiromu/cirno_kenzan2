package dangeon.view.anime;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import main.constant.FR;
import main.util.BeautifulView;
import dangeon.view.constant.MAP;

public class ThunderEffect extends Base_Effect {

	private final boolean flag_room;

	private Point delt_p;

	public ThunderEffect(boolean room) {
		this(room, new Point());
	}

	public ThunderEffect(boolean room, Point delt_p) {
		frame_image = new ArrayList<Image>(1);
		frame_image.add(loadImage("anime/biriri.png"));
		flag_room = room;
		this.delt_p = delt_p;
	}

	@Override
	protected boolean draw(Graphics2D g) {
		int max = 20;
		if (getFrame() == max) {
			return false;
		}
		int size_max = (flag_room) ? FR.SCREEN_WIDTH * 3 / 2
				: MAP.TILE_SIZE * 2;
		int size = (getFrame() > max / 3) ? size_max : size_max * getFrame()
				* 3 / max;
		double theta = (getFrame() > max * 2 / 3) ? max * 2 / 3 : getFrame();
		theta *= 0.1;
		AffineTransform firts_at = g.getTransform();
		AffineTransform at = new AffineTransform(firts_at);
		at.translate(FR.SCREEN_WIDTH / 2 + delt_p.x, FR.SCREEN_HEIGHT / 2
				+ delt_p.y);
		g.setTransform(at);
		BeautifulView.setAlphaOnImg(g,
				new Float(0.2 * Math.sin(Math.PI * getFrame() / max)));
		g.setColor(Color.YELLOW);
		if (flag_room) {
			g.fillRect(-size_max / 2, -size_max / 2, size_max, size_max);
		} else {
			g.fillOval(-size_max / 2, -size_max / 2, size_max, size_max);
			BeautifulView.setAlphaOnImg(g, 1F);
			g.setTransform(firts_at);
			return true;
		}
		BeautifulView.setAlphaOnImg(g, 0.6F);
		g.drawImage(frame_image.get(0), -size / 2, -size / 2, size, size, null);
		at.rotate(theta);
		g.setTransform(at);
		BeautifulView.setAlphaOnImg(g, 0.1F);
		g.drawImage(frame_image.get(0), -size / 2, -size / 2, size, size, null);
		BeautifulView.setAlphaOnImg(g, 1F);
		g.setTransform(firts_at);
		return true;
	}

}
