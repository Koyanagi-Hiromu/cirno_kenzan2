package title.objects;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import main.util.BeautifulView;
import main.util.FileReadSupporter;

public abstract class Base_Title_Object {
	public BufferedImage IM;

	public final Point2D NO = null, CENTER = new Point2D.Double(0.5, 0.5),
			LEFT_BOTTOM = new Point(0, 1), RIGHT_BOTTOM = new Point(1, 1);

	private int first_wait_frame = 3;

	public double theta;

	public boolean flag_setted;

	public int first_frame = 0;

	public final int FIRST_FRAME = 30;

	private boolean flag_body;

	public int count_360 = 0;

	public Base_Title_Object() {
		flag_body = true;
		IM = null;
	}

	public Base_Title_Object(String file_name) {
		this(file_name, false);
	}

	public Base_Title_Object(String file_name, boolean flag_start) {
		if (file_name.contains(".dat")) {
			file_name = file_name.replaceFirst("\\.dat", "");
		}
		IM = FileReadSupporter.readImage("title/".concat(file_name), "png");
		flag_setted = flag_start;
	}

	public void draw(Graphics2D g) {
		if (first_wait_frame > 0 && !flag_body)
			return;
		int w = w();
		int h = h();
		int dx = 0, dy = 0;
		AffineTransform firts_at = g.getTransform();
		if (rotate() != NO) {
			Point2D p = rotate();
			dx = (int) Math.round(w * p.getX());
			dy = (int) Math.round(h * p.getY());
			AffineTransform at = new AffineTransform(firts_at);
			if (isConnetedBody())
				at.translate(x() + dx, y() + dy - shakeBody());
			else
				at.translate(x() + dx, y() + dy);
			at.rotate(theta);
			drawIM(g, at, dx, dy, w, h);
		} else {
			AffineTransform at = new AffineTransform(firts_at);
			at.translate(x(), y());
			drawIM(g, at, dx, dy, w, h);
		}
		g.setTransform(firts_at);
	}

	private void drawIM(Graphics2D g, AffineTransform at, int dx, int dy,
			int w, int h) {
		g.setTransform(at);
		BeautifulView.setAlphaOnImg(g, getAlpha());
		g.drawImage(IM, -dx, -dy, w, h, null);
		BeautifulView.setAlphaOnImg(g, 1f);
	}

	protected float getAlpha() {
		return 1f;
	}

	protected int h() {
		return (int) Math.round(IM.getHeight() * rate());

	}

	protected boolean isConnetedBody() {
		return false;
	}

	public double rate() {
		return 0.175;
	}

	/**
	 * 
	 * @return NO=0,CENTER=1,LEFT_BOTTOM=2,RIGHT_BOTTOM=3;
	 */
	public abstract Point2D rotate();

	protected void setted() {
	}

	protected final int shakeBody() {
		return -(int) (Math.sin(Math.PI * 2 * count_360 / 40) * 15.0 * Math
				.pow(0.9, count_360 / 20));
	}

	public final void upDate() {
		if (first_wait_frame > 0) {
			first_wait_frame--;
			if (!flag_body)
				return;
		} else {
			count_360++;
			if (count_360 >= 360)
				count_360 = 0;
		}
		if (flag_setted)
			upDate_AfterSetted();
		else
			upDate_First();
	}

	protected void upDate_AfterSetted() {
	}

	protected final void upDate_First() {
		upDate_First_Indiv();
		if (++first_frame == FIRST_FRAME) {
			flag_setted = true;
			setted();
		}
	}

	protected void upDate_First_Indiv() {
	}

	protected int w() {
		return (int) Math.round(IM.getWidth() * rate());
	}

	public abstract int x();

	public abstract int y();

}
