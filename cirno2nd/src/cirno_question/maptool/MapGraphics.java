package cirno_question.maptool;

import java.awt.Graphics;

public class MapGraphics {
	public static int start_point_x = 0;
	public static int start_point_y = 0;
	public static int end_point_x = 0;
	public static int end_point_y = 0;
	public static MapGraphics ME = new MapGraphics();

	public void paint(Graphics g) {
		// g.drawLine(start_point_x, start_point_y, end_point_x, end_point_y);
		g.drawLine(10, 10, 50, 50);
	}
}
