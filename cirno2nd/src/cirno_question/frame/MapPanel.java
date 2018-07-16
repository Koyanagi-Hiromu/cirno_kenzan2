package cirno_question.frame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import cirno_question.maptool.MainMap;
import cirno_question.maptool.MapFrame;
import cirno_question.maptool.MapMass;
import cirno_question.maptool.MassCategory;

public class MapPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -9007671475796050945L;

	@Override
	public void paintComponent(Graphics g) {
		paintStrings(g);
		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i <= MapFrame.MAX_Y; i += MapFrame.fill_max) {
			g.drawLine(0, i, MapFrame.MAX_X, i);
		}
		for (int i = 0; i <= MapFrame.MAX_X; i += MapFrame.fill_max) {
			g.drawLine(i, 0, i, MapFrame.MAX_Y);
		}

	}

	// private static final Point FIRST_POINT = new Point(2, 12);

	public void paintFillMassCategory(Graphics g, MapMass mm) {
		if (MainMap.ME.isOutMassLine(mm.getMassPoint())) {
			return;
		}
		if (mm.getMassCategory() == MassCategory.WALKABLE) {
			g.setColor(Color.black);
		} else if (mm.getMassCategory() == MassCategory.ROAD) {
			g.setColor(Color.LIGHT_GRAY);
		} else if (mm.getMassCategory() == MassCategory.ROOM) {
			g.setColor(Color.WHITE);
		} else if (mm.getMassCategory() == MassCategory.WATER) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.DARK_GRAY);
		}
		g.fillRect(MapFrame.fill_max * mm.getX(),
				MapFrame.fill_max * mm.getY(), 15, 15);
	}

	public void paintStringMassObject(Graphics g, MapMass mm) {
		if (mm.getListMapObject().isEmpty()) {
			return;
		}
		if (mm.isEnemy()) {
			g.setColor(Color.red);
			if (mm.isItem() || mm.isTrap() || mm.isEnter() || mm.isExit()) {
				g.fillRect(MapFrame.fill_max * mm.getX() + 3, MapFrame.fill_max
						* mm.getY() + 3, 10, 5);
			} else {
				g.fillRect(MapFrame.fill_max * mm.getX() + 3, MapFrame.fill_max
						* mm.getY() + 3, 10, 10);
			}
			if (mm.EM.sleep) {
				g.setColor(Color.cyan);
				g.fillRect(MapFrame.fill_max * mm.getX() + 4, MapFrame.fill_max
						* mm.getY() + 4, 4, 4);
			}
			if (mm.EM.angry) {
				g.setColor(Color.BLUE);
				g.fillRect(MapFrame.fill_max * mm.getX() + 8, MapFrame.fill_max
						* mm.getY() + 4, 4, 4);
			}
		}
		if (mm.isItem() || mm.isTrap() || mm.isEnter() || mm.isExit()) {
			if (mm.isItem()) {
				g.setColor(Color.green);
			} else if (mm.isTrap()) {
				g.setColor(Color.yellow);
			} else if (mm.isEnter()) {
				g.setColor(Color.ORANGE);
			} else {
				g.setColor(Color.PINK);
			}
			if (mm.isEnemy()) {
				g.fillRect(MapFrame.fill_max * mm.getX() + 3, MapFrame.fill_max
						* mm.getY() + 8, 10, 5);
			} else {
				g.fillRect(MapFrame.fill_max * mm.getX() + 3, MapFrame.fill_max
						* mm.getY() + 3, 10, 10);
			}
			g.setColor(Color.blue);
		}
		// g.setColor(Color.BLACK);
		// g.drawString("物", MapFrame.fill_max * mm.getX() + FIRST_POINT.x,
		// MapFrame.fill_max * mm.getY() + FIRST_POINT.y);
	}

	// public void rep() {
	// Graphics g = getGraphics();
	// repaint();
	// g.dispose();
	// }

	public void paintStrings(Graphics g) {
		MapMass mm;
		for (int i = 0; i < MainMap.ME.MAX_MASS_X; i++) {
			for (int j = 0; j < MainMap.ME.MAX_MASS_Y; j++) {
				mm = MainMap.ME.getMass(i, j);
				paintFillMassCategory(g, mm);
				paintStringMassObject(g, mm);
			}
		}
	}
}
