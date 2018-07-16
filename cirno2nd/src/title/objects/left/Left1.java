package title.objects.left;

import java.awt.geom.Point2D;

import title.objects.Base_Title_Object_Rorate360;

public class Left1 extends Base_Title_Object_Rorate360 {
	public int count;

	public Left1() {
		super("left_1");
	}

	public Left1(String f) {
		super(f);
	}

	@Override
	public double getSpeedRate() {
		return 3.0 + Math.sin(Math.PI * 2 * count_360 / 360);
	}

	@Override
	protected boolean isConnetedBody() {
		return true;
	}

	@Override
	public Point2D rotate() {
		return new Point2D.Double(0.5, 0.5);
	}

	@Override
	public int x_final() {
		return 53;
	}

	@Override
	public int y_final() {
		return 304 - h() - 25;
	}

}
