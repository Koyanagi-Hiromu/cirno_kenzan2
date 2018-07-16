package title.objects.body;

import java.awt.geom.Point2D;

import main.constant.FR;
import title.objects.Base_Title_Object;

public class Onigiri extends Base_Title_Object {
	public int count;

	public Onigiri() {
		super("onigiri");
	}

	public Onigiri(String f) {
		super(f);
	}

	@Override
	protected float getAlpha() {
		if (flag_setted)
			return 1f;
		else {
			return (float) Math.pow((double) first_frame / FIRST_FRAME, 3);
		}
	}

	@Override
	public Point2D rotate() {
		return NO;
	}

	@Override
	protected void upDate_AfterSetted() {
	}

	@Override
	protected void upDate_First_Indiv() {
	}

	@Override
	public final int x() {
		if (flag_setted)
			return x_final();
		else
			return x_first() + (x_final() - x_first()) * (first_frame + 1)
					/ FIRST_FRAME;
	}

	private int x_final() {
		return FR.SCREEN_WIDTH / 2 - w() / 2 - 1;
	}

	private final int x_first() {
		return (FR.SCREEN_WIDTH - w()) / 2;
	}

	@Override
	public final int y() {
		if (flag_setted)
			return y_final();
		else
			return y_first()
					+ (y_final() - y_first())
					* first_frame
					/ FIRST_FRAME
					- (int) (Math.pow((FIRST_FRAME / 2), 2) - Math.pow(
							(FIRST_FRAME / 2 - first_frame), 2)) * 6 / 10;
	}

	private int y_final() {
		return 220 + (int) (Math.sin(Math.PI * 2
				* ((double) count_360 / 360 * 3 + 0.75)) * 4);
	}

	private int y_first() {
		return 270;
	}

}
