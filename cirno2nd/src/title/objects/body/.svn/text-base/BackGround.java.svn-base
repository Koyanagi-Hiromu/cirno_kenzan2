package title.objects.body;

import java.awt.geom.Point2D;

import main.constant.FR;
import title.objects.Base_Title_Object_Rorate360;

public class BackGround extends Base_Title_Object_Rorate360 {

	public BackGround() {
		super("crystal.dat", true);
	}

	@Override
	public double getSpeedRate() {
		return -1.0;
	}

	@Override
	public Point2D rotate() {
		return CENTER;
	}

	@Override
	public int x_final() {
		return FR.SCREEN_WIDTH / 2 - w() / 2;
	}

	@Override
	public int y_final() {
		return FR.SCREEN_HEIGHT / 2 - h() / 2;
	}

}
