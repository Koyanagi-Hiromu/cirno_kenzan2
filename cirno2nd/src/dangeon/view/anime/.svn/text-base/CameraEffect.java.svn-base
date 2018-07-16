package dangeon.view.anime;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import main.res.Image_Anime;
import main.res.SE;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;

public class CameraEffect extends Effect_Image_Anime {
	public final Point from, to;

	public CameraEffect(Point from, Point to) {
		super(from, 1, Image_Anime.camera, SE.ATTACK_SHOOT, 4, null, 0, 0, 0,
				0, 1, 1, 1, 2, 2);
		this.from = from;
		this.to = to;
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (getFrame() >= MAX) {
			return false;
		}
		int dx = to.x - from.x;
		int dy = to.y - from.y;
		int move_frame = 3;
		if (getFrame() == move_frame) {
			dx = dy = 0;
			FIRST_MASS_POINT.setLocation(to);
		} else if (getFrame() <= move_frame) {
			dx *= MAP.TILE_SIZE * getFrame();
			dy *= MAP.TILE_SIZE * getFrame();
			dx /= move_frame;
			dy /= move_frame;
		}
		if (getFrame() == 1) {
			SE.ATTACK_SHOOT.play();
		}
		Point p = ObjectPoint.getScreenPointRelPlayer(ObjectPoint
				.getDangeonScreenPoint(FIRST_MASS_POINT));
		p.translate(dx, dy);
		Image image = IM_ANIME.getImage(getComa());
		int x = p.x + MAP.TILE_SIZE / 2 - image.getWidth(null) / 2;
		int y = p.y + MAP.Y_OFFSET - MAP.TILE_SIZE / 2 - image.getHeight(null)
				/ 2;
		g.drawImage(image, x + getDelX(), y + getDelY(), null);
		return true;
	}

}
