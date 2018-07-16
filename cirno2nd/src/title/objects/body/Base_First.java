package title.objects.body;

import java.awt.geom.Point2D;

import title.objects.Base_Title_Object;

public abstract class Base_First extends Base_Title_Object {

	protected boolean flag_appearing = true;

	public Base_First(String s) {
		super(s, true);
	}

	@Override
	protected float getAlpha() {
		if (flag_appearing) {
			int invisible_time = 45;
			if (count_360 < invisible_time)
				return 0f;
			else
				return ((count_360 - invisible_time) / (90f - invisible_time));
		} else
			return 1f;
	}

	public double getSin(int i) {
		return Math.sin(Math.PI * 2 * count_360 / 90 / i);
	}

	@Override
	public double rate() {
		// TODO 自動生成されたメソッド・スタブ
		return 0.23;
	}

	@Override
	public Point2D rotate() {
		return NO;
	}

	@Override
	protected void upDate_AfterSetted() {
		if (count_360 == 90)
			flag_appearing = false;
	}

}
