package cirno_question.maptool;

import java.awt.Point;
import java.util.ArrayList;

import cirno_question.SetOnMapObject;

public class MapMass {
	public MassCategory MC;
	public SetOnMapObject EM = null;
	public SetOnMapObject A = null;
	private final int X;
	private final int Y;

	public MapMass(MassCategory mc, int x, int y) {
		MC = mc;
		X = x;
		Y = y;
	}

	public ArrayList<SetOnMapObject> getListMapObject() {
		ArrayList<SetOnMapObject> list = new ArrayList<SetOnMapObject>();
		if (EM != null) {
			list.add(EM);
		}
		if (A != null) {
			list.add(A);
		}
		return list;
	}

	public MassCategory getMassCategory() {
		return MC;
	}

	public Point getMassPoint() {
		return new Point(X, Y);
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public boolean isEnemy() {
		if (EM == null) {
			return false;
		}
		return EM.getEnemy() != null;
	}

	public boolean isEnter() {
		if (A == null) {
			return false;
		}
		return A.enter;
	}

	public boolean isExit() {
		if (A == null) {
			return false;
		}
		return A.exit;
	}

	public boolean isItem() {
		if (A == null) {
			return false;
		}
		return A.getItem() != null;
	}

	public boolean isTrap() {
		if (A == null) {
			return false;
		}
		return A.getTrap() != null;
	}

	public void massClear() {
		MC = MassCategory.WALKABLE;
		EM = null;
		A = null;
	}

	public void removeEnemy() {
		if (EM == null) {
			return;
		}
		if (EM.getEnemy() != null) {
			EM = null;
		}
	}

	public void removeItem() {
		if (A == null) {
			return;
		}
		if (A.getItem() != null) {
			A = null;
		}
	}

	public void removeObject() {
		removeItem();
		removeTrap();
		removeEnemy();
	}

	public void removeTrap() {
		if (A == null) {
			return;
		}
		if (A.getTrap() != null) {
			A = null;
		}
	}

	public void setMassCategory(MassCategory mc) {
		MC = mc;
	}

	// public SetOnMapObject getMapObject() {
	// return map_object;
	// }

	public void setMassObject(SetOnMapObject mo) {
		System.out.println("set");
		if (mo.getEnemy() != null) {
			System.out.println("getEnemy");
			EM = mo;
			System.out.println(EM.getName());
			if (A != null) {
				System.out.println(A.getName());
			}
		} else if (mo.getArtifact() != null) {
			A = mo;
		} else if (mo.enter) {
			for (int i = 0; i < 40; i++) {
				for (int j = 0; j < 30; j++) {
					if (MainMap.ME.getMass(i, j).isEnter()) {
						MainMap.ME.getMass(i, j).A = null;
					}
				}
			}
			A = mo;
		} else if (mo.exit) {
			for (int i = 0; i < 40; i++) {
				for (int j = 0; j < 30; j++) {
					if (MainMap.ME.getMass(i, j).isExit()) {
						MainMap.ME.getMass(i, j).A = null;
					}
				}
			}
			A = mo;
		}

	}
}
