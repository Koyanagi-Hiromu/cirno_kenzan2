package title.objects.body;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.util.FileReadSupporter;
import title.objects.Base_Title_Object;

public class Body extends Base_Title_Object {
	public final BufferedImage[] IMS;

	private int open_frame;

	public Body() {
		IMS = new BufferedImage[6];
		for (int i = 0; i < IMS.length; i++) {
			IMS[i] = FileReadSupporter.readImage(
					"title/body_0".concat(String.valueOf(i)), "png");
		}
		IM = IMS[0];
		open_frame = IMS.length;
	}

	@Override
	protected int h() {
		return super.h() + shakeBody();
	}

	@Override
	public double rate() {
		return 0.23;
	}

	@Override
	public Point2D rotate() {
		return NO;
	}

	@Override
	protected void upDate_AfterSetted() {
		super.upDate_AfterSetted();
	}

	@Override
	protected void upDate_First_Indiv() {
		if (open_frame > 0) {
			open_frame--;
			IM = IMS[IMS.length - open_frame - 1];
		}
	}

	@Override
	public int x() {
		return (FR.SCREEN_WIDTH - w()) / 2;
	}

	@Override
	public int y() {
		return FR.SCREEN_HEIGHT - h();
	}

}
