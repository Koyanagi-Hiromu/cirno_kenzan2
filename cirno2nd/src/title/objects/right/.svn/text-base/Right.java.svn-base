package title.objects.right;

import java.awt.geom.Point2D;

import main.constant.FR;
import title.objects.left.Left;

public class Right extends Left {

	public Right() {
		super("right_0.dat");
	}

	@Override
	public Point2D rotate() {
		Point2D p = super.rotate();
		return new Point2D.Double(1 - p.getX(), p.getY());
	}

	@Override
	public int signal() {
		return -1;
	}

	@Override
	public int x() {
		return FR.SCREEN_WIDTH / 2;
	}

}
