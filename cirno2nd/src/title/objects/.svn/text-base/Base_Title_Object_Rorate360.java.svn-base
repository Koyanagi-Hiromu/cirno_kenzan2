package title.objects;

import main.constant.FR;

public abstract class Base_Title_Object_Rorate360 extends Base_Title_Object {

	public Base_Title_Object_Rorate360(String file_name) {
		super(file_name);
	}

	public Base_Title_Object_Rorate360(String file_name, boolean b) {
		super(file_name, b);
	}

	@Override
	protected float getAlpha() {
		if (flag_setted)
			return 1f;
		else {
			return (float) Math.pow((double) first_frame / FIRST_FRAME, 3);
		}
	}

	public double getSpeedRate() {
		return 2;
	}

	@Override
	protected void upDate_AfterSetted() {
		upDateMe();
	}

	@Override
	protected void upDate_First_Indiv() {
		upDateMe();
	}

	private void upDateMe() {
		theta += getSpeedRate() * Math.PI * 2 / 360;
		if (theta > Math.PI * 2) {
			theta -= Math.PI * 2;
		} else if (theta < 0) {
			theta += Math.PI * 2;
		}
	}

	@Override
	public final int x() {
		if (flag_setted)
			return x_final();
		else
			return x_first() + (x_final() - x_first()) * (first_frame + 1)
					/ FIRST_FRAME;
	}

	public abstract int x_final();

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

	public abstract int y_final();

	private int y_first() {
		return 270;
	}
}
