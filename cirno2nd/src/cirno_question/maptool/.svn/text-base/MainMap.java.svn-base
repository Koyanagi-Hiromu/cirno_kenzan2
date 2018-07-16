package cirno_question.maptool;

import java.awt.Point;
import java.awt.event.MouseEvent;

import cirno_question.QS;
import cirno_question.maptool.map_button.ButtonClear;

public class MainMap {
	public static MainMap ME = new MainMap();

	public final int MAX_MASS_X = 40;
	public final int MAX_MASS_Y = 30;
	public final int MASS_DIAMETER = 15;
	private MapMass[][] mass = new MapMass[MAX_MASS_X][MAX_MASS_Y];

	public void allClear() {
		for (int i = 0; i < MAX_MASS_X; i++) {
			for (int j = 0; j < MAX_MASS_Y; j++) {
				mass[i][j].massClear();
			}
		}

	}

	public Point checkMousePointIntoMassPoint(Point p) {
		return new Point(p.x / 15, p.y / 15);
	}

	public MapMass[][] getAllMass() {
		return mass;
	}

	public MapMass getMass(int x, int y) {
		if (x >= MAX_MASS_X || y >= MAX_MASS_Y) {
			return null;
		} else if (x < 0 || y < 0) {
			return null;
		}
		return mass[x][y];
	}

	public MapMass getMass(Point point) {
		Point p = checkMousePointIntoMassPoint(point);
		if (p.x >= MAX_MASS_X || p.y >= MAX_MASS_Y) {
			return null;
		} else if (p.x < 0 || p.y < 0) {
			return null;
		}
		return mass[p.x][p.y];
	}

	public boolean isEnterExit() {
		boolean enter = false;
		boolean exit = false;
		for (int i = 0; i < MAX_MASS_X; i++) {
			for (int j = 0; j < MAX_MASS_Y; j++) {
				if (mass[i][j].isEnter()) {
					enter = true;
				} else if (mass[i][j].isExit()) {
					exit = true;
				}
				if (enter && exit) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isOutMassLine(Point p) {
		if (p.x <= 0 || p.y <= 0 || p.x >= 39 || p.y >= 29) {
			return true;
		}
		return false;
	}

	public void setFirstMass() {
		for (int i = 0; i < MAX_MASS_X; i++) {
			for (int j = 0; j < MAX_MASS_Y; j++) {
				mass[i][j] = new MapMass(MassCategory.WALKABLE, i, j);
			}
		}
	}

	public void setMassCategory(MouseEvent e) {
		if (isOutMassLine(checkMousePointIntoMassPoint(e.getPoint()))) {
			return;
		}
		if (e.getPoint().x >= MapFrame.MAX_X
				|| e.getPoint().y >= MapFrame.MAX_Y) {
			return;
		}
		MapMass mm = getMass(e.getPoint());
		for (int i = 0; i <= MapFrame.ME.bounds_box.getSelectedIndex(); i++) {
			for (int j = 0; j <= MapFrame.ME.bounds_box.getSelectedIndex(); j++) {
				MapMass _m = getMass(mm.getX() + i, mm.getY() + j);
				if (_m == null) {
					continue;
				}
				if (QS.map_clip_category == null && QS.map_clip_object == null) {
					ButtonClear.massClear(_m);
				} else if (QS.map_clip_category == null) {
					_m.setMassObject(QS.map_clip_object);
				} else {
					_m.setMassCategory(QS.map_clip_category);
				}
			}
		}
	}
}
