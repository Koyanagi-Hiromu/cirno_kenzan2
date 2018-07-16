package title.objects.left;

public class Left2 extends Left1 {
	public int count;

	public Left2() {
		super("left_2");
	}

	public Left2(String f) {
		super(f);
	}

	@Override
	public int x_final() {
		return 73;
	}

	@Override
	public int y_final() {
		return 304 - h() - 10;
	}

}
