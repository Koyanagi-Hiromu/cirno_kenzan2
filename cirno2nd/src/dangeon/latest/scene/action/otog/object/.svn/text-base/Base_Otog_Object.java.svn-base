package dangeon.latest.scene.action.otog.object;

import java.awt.Graphics2D;
import java.util.Iterator;

import dangeon.latest.scene.action.otog.main.panel.Panel_Main;
import dangeon.latest.scene.action.otog.value.ValueHolder;

public abstract class Base_Otog_Object {
	public final Panel_Main P;
	public int x, y;

	public Base_Otog_Object() {
		this(null, 0, 0);
	}

	public Base_Otog_Object(Panel_Main p) {
		this(p, 0, 0);
	}

	public Base_Otog_Object(Panel_Main p, int x, int y) {
		this.P = p;
		this.x = x;
		this.y = y;
	}

	public abstract void draw(Graphics2D g);

	protected boolean killMySelf(Iterator<?> iterator) {
		iterator.remove();
		return true;
	}

	public abstract void upDate(ValueHolder vh, long frame, Iterator<?> iterator);
}
