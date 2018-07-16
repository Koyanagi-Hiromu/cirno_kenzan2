package dangeon.latest.util.view_window;

public class WindowContent {
	public final String NAME;
	public final int X, COL;

	public WindowContent(int x, int col, String name) {
		NAME = name;
		X = x;
		COL = col;
	}

	public WindowContent(int col, String name) {
		NAME = name;
		X = 0;
		COL = col;
	}
}
