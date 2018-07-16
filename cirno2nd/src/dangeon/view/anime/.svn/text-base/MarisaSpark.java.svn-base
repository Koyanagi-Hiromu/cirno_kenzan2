package dangeon.view.anime;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.res.SE;
import main.util.BeautifulView;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;

public class MarisaSpark extends Base_Anime {
	private int width = 0, height = 0;

	private final double THETA;
	private boolean flag = false;

	private int masp_frame = 0;

	private final int HEAD_WIDTH;

	private final BufferedImage[] BODY, FOOT_HEAD;

	private Point START_POINT;

	public MarisaSpark(final Base_Creature c, Task task) {
		super(c, task);
		int coma = 10;
		String base = "anime/";
		BufferedImage bi, bi2;
		bi = loadImage(base.concat("マリサスパーク2.png"));
		bi2 = loadImage(base.concat("マリサスパーク1.png"));
		int x = 0;
		int w = bi.getWidth();
		int h = bi.getHeight() / coma;
		BODY = new BufferedImage[coma];
		FOOT_HEAD = new BufferedImage[coma];
		for (int i = 0; i < coma; i++) {
			BODY[i] = bi.getSubimage(x, h * i, w, h);
			FOOT_HEAD[i] = bi2.getSubimage(x, h * i, w, h);
		}
		THETA = Math.PI / 4 * C.direction.ROTATE;
		HEAD_WIDTH = w;
	}

	@Override
	public boolean draw(Graphics2D g) {
		coma = getFrame() / 2;
		if (coma > 3) {
			coma = 3;
		} else if (getFrame() == 4) {
			SE.MASTERSPARK.play();
		}
		if (!flag) {
			if (!Player.me.isAnimeComletelyEnded()) {
				return true;
			} else if (coma != 3) {
				return true;
			} else {
				flag = true;
				START_POINT = getStartPoint();
			}
		}
		boolean flag = draw_mian(g);
		masp_frame++;
		return flag;
	}

	private boolean draw_mian(Graphics2D g) {
		AffineTransform firts_at = g.getTransform();
		AffineTransform at = new AffineTransform(firts_at);
		Point p = START_POINT.getLocation();
		at.translate(p.x, p.y);
		at.rotate(THETA);
		BeautifulView.setAlphaOnImg(g, new Float((double) height
				/ MAP.TILE_SIZE));
		g.setTransform(at);
		g.translate(42.0, 0.0);
		boolean animating = drawRight(g);
		g.setTransform(firts_at);
		g.setColor(new Color(240, 240, 255, 50));
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
		BeautifulView.setAlphaOnImg(g, 1F);
		return animating;
	}

	private boolean drawRight(Graphics2D g) {
		if (width > FR.SCREEN_WIDTH) {
			height -= 5;
			if (height <= 0) {
				return false;
			}
		} else {
			if (height < MAP.TILE_SIZE) {
				height += 20;
				if (height > MAP.TILE_SIZE) {
					height = MAP.TILE_SIZE;
				}
			}
		}
		int y;
		y = -height / 2;
		System.out.println(masp_frame);
		int x = -20;
		int i = masp_frame / 2;
		// FOOT BODY HEAD
		g.drawImage(FOOT_HEAD[i], x, y, HEAD_WIDTH, height, null);
		g.drawImage(BODY[i], x + HEAD_WIDTH, y, width, height, null);
		// g.drawImage(FOOT_HEAD[i], HEAD_WIDTH * 2 + width, y, -HEAD_WIDTH,
		// height,
		// null);
		width += MAP.MoveHighSpeed;
		return true;
	}

	private Point getStartPoint() {
		Point p;
		if (ObjectPoint.getLengthBetweenPlayer(C.getMassPoint()) > MAP.TILE_HORIZON_INNER_NUMBER / 2) {
			int x = Player.me.getMassPoint().x, y = Player.me.getMassPoint().y;
			x += (MAP.TILE_HORIZON_INNER_NUMBER / 2 + 2) * -C.direction.X;
			y += (MAP.TILE_HORIZON_INNER_NUMBER / 2 + 2) * -C.direction.Y;
			p = ObjectPoint.getScreenPointRelPlayer(ObjectPoint
					.getDangeonScreenPoint(x, y));
		} else {
			p = ObjectPoint.getScreenPointRelPlayer(C);
		}
		p.translate(MAP.TILE_SIZE / 2, 11 + 13);
		return p;
	}

}
