package dangeon.view.anime;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;

public abstract class Base_Effect_Effect extends Special_Wait_Effect {
	protected final Point FIRST_MASS_POINT;

	public Base_Effect_Effect(Point first_point, int anime, int max_coma,
			SE se, int se_frame, Task task) {
		super(anime, max_coma, se, se_frame, task);
		FIRST_MASS_POINT = first_point;
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (!super.draw(g)) {
			return false;
		}
		Point p = ObjectPoint.getScreenPointRelPlayer(ObjectPoint
				.getDangeonScreenPoint(FIRST_MASS_POINT));
		Image image = getImage(getComa());
		int x = p.x + MAP.TILE_SIZE / 2 - image.getWidth(null) / 2;
		int y = p.y + MAP.Y_OFFSET - MAP.TILE_SIZE / 2 - image.getHeight(null)
				/ 2;
		g.drawImage(image, x + getDelX(), y + getDelY(), null);
		return true;
	}

	/**
	 * オーバーライド用
	 * 
	 * @return
	 */
	protected int getDelX() {
		return 0;
	}

	/**
	 * オーバーライド用
	 * 
	 * @return
	 */
	protected int getDelY() {
		return 0;
	}

	protected abstract Image getImage(int coma);
}