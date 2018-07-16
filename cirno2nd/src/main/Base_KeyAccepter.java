package main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public abstract class Base_KeyAccepter {

	public void draw(Graphics2D g) {
	}

	abstract public void keyPressed(KeyEvent e);

	abstract public void keyReleased(KeyEvent e);

	public void up_date() {
	}
}
