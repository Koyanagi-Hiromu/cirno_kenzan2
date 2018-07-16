package dangeon.controller.listener.menu;

import java.awt.Graphics2D;

import main.Listener.ACTION;
import main.util.DIRECTION;

public abstract class Base_Menu {
	protected int x, y;
	protected final int X_LENGTH, Y_LENGTH;

	protected Base_Menu(int x_length, int y_length) {
		this(0, 0, x_length, y_length);
	}

	protected Base_Menu(int ini_x, int ini_y, int x_length, int y_length) {
		X_LENGTH = x_length;
		Y_LENGTH = y_length;
		x = ini_x;
		y = ini_y;
	}

	public abstract void actionKeyPressed(ACTION action);

	public void arrowKeyPressed(DIRECTION direction) {
		switch (direction) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case LEFT:
			if (X_LENGTH == 0) {
				return;
			}
			x--;
			break;
		case RIGHT:
			if (X_LENGTH == 0) {
				return;
			}
			x++;
			break;
		}
		if (x < 0)
			x = 0;
		if (x > X_LENGTH - 1)
			x = X_LENGTH - 1;
		if (y < 0)
			y = 0;
		if (y > Y_LENGTH - 1)
			y = Y_LENGTH - 1;
	}

	public abstract void draw(Graphics2D g);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
