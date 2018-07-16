package dangeon.view.anime;

import java.awt.Graphics2D;

import main.res.Image_Player;
import main.res.Image_Player.AnimeAccordingToItemClass;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.object.artifact.trap.落とし穴の罠;
import dangeon.model.object.creature.player.Player;

public class 落とし穴Wait extends Special_Wait_FrameByFrame {

	private final 落とし穴の罠 TRAP;

	public 落とし穴Wait(落とし穴の罠 trap, Player c, Task t) {
		super(c, SE.PITFALL_OPEN, 1, t, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3);
		TRAP = trap;
	}

	@Override
	public boolean draw(Graphics2D g) {
		boolean b = super.draw(g);
		if (getFrame() == 7) {
			Image_Player.set(AnimeAccordingToItemClass.PITFALL);
		}
		if (b) {
			TRAP.setIM(getComa());
		}
		return b;
	}

}
