package title.objects.right;

import title.objects.left.Left1;

public class Right1 extends Left1 {
	public int count;

	public Right1() {
		super("right_1");
	}

	public Right1(String f) {
		super(f);
	}

	@Override
	public double getSpeedRate() {
		return -super.getSpeedRate();
	}

	@Override
	public int x_final() {
		return 303;
	}

	@Override
	public int y_final() {
		return 304 - h() - 20;
	}
}
