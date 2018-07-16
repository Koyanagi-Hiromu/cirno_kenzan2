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

public class MasterSpark extends Base_Anime {
	private int width = 0, height = 0;

	private final double THETA;
	private boolean flag = false;

	private final int HEAD_WIDTH;

	private final BufferedImage BODY, FOOT_HEAD;

	private Point START_POINT;

	public MasterSpark(Base_Creature c) {
		this(c, null);
	}

	public MasterSpark(Base_Creature c, Task task) {
		super(c, task);
		String base = "anime/";
		BODY = loadImage(base.concat("マスパwid-b.png"));
		FOOT_HEAD = loadImage(base.concat("マスパwid-a.png"));
		THETA = Math.PI / 4 * C.direction.ROTATE;
		HEAD_WIDTH = FOOT_HEAD.getWidth();
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (!flag) {
			if (!Player.me.isAnimeComletelyEnded()) {
				return true;
			} else {
				flag = true;
				SE.MASTERSPARK.play();
				START_POINT = getStartPoint();
			}
		}
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
		// FOOT BODY HEAD
		g.drawImage(FOOT_HEAD, 0, y, HEAD_WIDTH, height, null);
		g.drawImage(BODY, HEAD_WIDTH, y, width, height, null);
		g.drawImage(FOOT_HEAD, HEAD_WIDTH * 2 + width, y, -HEAD_WIDTH, height,
				null);
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
		p.translate(MAP.TILE_SIZE / 2, 11);
		return p;
	}
}
