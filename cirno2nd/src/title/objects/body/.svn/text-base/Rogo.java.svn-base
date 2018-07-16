package title.objects.body;

import java.awt.geom.Point2D;

import main.constant.FR;

public class Rogo extends Base_First {

	public Rogo() {
		super("title.dat");
	}

	@Override
	public Point2D rotate() {
		return NO;
	}

	@Override
	public int x() {
		return FR.SCREEN_WIDTH / 2 - w() / 2;
	}

	@Override
	public int y() {
		return 2 + (int) (Math.sin(Math.PI * 2 * count_360 / 360 * 4) * 5);
	}

}
