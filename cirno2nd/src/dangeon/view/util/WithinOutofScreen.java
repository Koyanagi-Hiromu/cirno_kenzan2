package dangeon.view.util;

import java.awt.Image;
import java.awt.Point;

import dangeon.model.object.Base_MapObject;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;
import main.constant.FR;

public class WithinOutofScreen {
	public static boolean isOutside(Base_MapObject o) {
		Point p = ObjectPoint.getScreenPointRelPlayer(o);
		try {
			Image image = o.getImage();
			return isOutside(p.x, p.y, image.getWidth(null), image.getHeight(null));
		} catch (Exception e) {
			System.out.println(o);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 精確さは欠ける <br />
	 * Use =>isOutside(Base_MapObject o)
	 * 
	 * @param o
	 * @param mass_point
	 * @return
	 */
	public static boolean isOutside(Base_MapObject o, Point mass_point) {
		Point p = ObjectPoint.getScreenPointRelPlayer(mass_point);
		try {
			return isOutside(p.x, p.y, o.getImage().getWidth(null), o
					.getImage().getHeight(null));
		} catch (Exception e) {
			System.out.println(o);
			e.printStackTrace();
			return false;
		}

	}

	public static boolean isOutside(int x, int y, int w, int h) {
		if (x + w < -MAP.TILE_SIZE / 2)
			return true;
		if (x > FR.SCREEN_WIDTH + MAP.TILE_SIZE / 2)
			return true;
		if (y + h < -MAP.TILE_SIZE / 2)
			return true;
		if (y > FR.SCREEN_HEIGHT + MAP.TILE_SIZE / 2)
			return true;
		return false;
	}
}
