package dangeon.view.anime;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.Base_Creature;

public class Special_Wait_FrameByFrame extends Special_Wait {
	private final int[] ANIME;

	/**
	 * 
	 * @param c
	 * @param se
	 * @param se_frame
	 * @param task
	 * @param anime
	 */
	public Special_Wait_FrameByFrame(Base_Creature c, SE se, int se_frame,
			Task task, int... anime) {
		super(c, anime.length, se, se_frame, task);
		ANIME = anime;
	}

	@Override
	public int getComa() {
		return ANIME[getFrame()];
	}
}
