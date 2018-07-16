package title.objects.body;

import java.awt.geom.Point2D;

import main.constant.FR;

public class PushEnter extends Base_First {

	public PushEnter() {
		super("PushEnter.dat");
	}

	@Override
	protected float getAlpha() {
		if (flag_appearing)
			return super.getAlpha();
		else
			return (float) (Math.abs(getSin(2)));
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
		return FR.SCREEN_HEIGHT - h();
	}

}
