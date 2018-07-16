package cirno_question.maptool;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MapMouseWheel implements MouseWheelListener {

	public static MapMouseWheel ME = new MapMouseWheel();

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int max = MapFrame.ME.bounds_box.getItemCount();
		int i = e.getWheelRotation();
		int index = MapFrame.ME.bounds_box.getSelectedIndex();
		if (index + i < 0 || index + i >= max) {
			return;
		}
		MapFrame.ME.bounds_box.setSelectedIndex(index + i);
	}
}
