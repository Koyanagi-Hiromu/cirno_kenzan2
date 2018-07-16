package title.objects.left;

import java.awt.geom.Point2D;

import main.constant.FR;
import title.objects.Base_Title_Object;

public class Left extends Base_Title_Object {
	public int count;

	public Left() {
		super("left_0.dat");
	}

	public Left(String f) {
		super(f);
	}

	public void calc() {
		count++;
		if (count >= 360)
			count = 0;
		double A = (Math.PI * 2 * 6 / 360) * Math.pow(0.90, count / 12);
		theta = -(A * Math.sin(Math.PI * 20 * count / 360)) * signal();
	}

	@Override
	protected boolean isConnetedBody() {
		return true;
	}

	@Override
	public double rate() {
		if (flag_setted)
			return super.rate();
		else
			return super.rate()
					* Math.sin(Math.PI / 2 * first_frame / FIRST_FRAME);
	}

	@Override
	public Point2D rotate() {
		return new Point2D.Double(0.96, 0.8);
	}

	public int signal() {
		return 1;
	}

	@Override
	public void upDate_AfterSetted() {
		calc();
	}

	@Override
	protected void upDate_First_Indiv() {
		if (first_frame > FIRST_FRAME / 3)
			calc();
	}

	@Override
	public int x() {
		return FR.SCREEN_WIDTH / 2 - w();
	}

	@Override
	public int y() {
		return 304 - h() - (FIRST_FRAME - first_frame) * 1;
	}
}
