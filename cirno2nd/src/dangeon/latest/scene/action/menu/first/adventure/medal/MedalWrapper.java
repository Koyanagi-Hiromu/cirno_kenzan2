package dangeon.latest.scene.action.menu.first.adventure.medal;

public class MedalWrapper {
	public final Medal medal;

	public MedalWrapper(Medal m) {
		medal = m;
	}

	@Override
	public String toString() {
		return medal.toString();
	}
}
