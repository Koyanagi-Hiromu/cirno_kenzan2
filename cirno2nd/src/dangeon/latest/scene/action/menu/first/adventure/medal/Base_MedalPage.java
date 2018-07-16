package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

public abstract class Base_MedalPage {
	protected ArrayList<MedalWrapper> l;

	protected void add(Medal m) {
		l.add(new MedalWrapper(m));
	}

	protected void add(MedalWrapper m) {
		l.add(m);
	}

	public abstract void addMedals();

	public final void addMedals(ArrayList<MedalWrapper> l) {
		this.l = l;
		addMedals();
		this.l = null;
	}

	public abstract void getExp(ArrayList<String> l);

	public abstract String getShortTitle();
}