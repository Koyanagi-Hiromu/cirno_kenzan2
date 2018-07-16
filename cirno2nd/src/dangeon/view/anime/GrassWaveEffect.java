package dangeon.view.anime;

import main.res.Image_Anime;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.Base_Creature;

public class GrassWaveEffect extends Effect_Image_Anime {
	public GrassWaveEffect(Base_Creature c, Task task) {
		super(c.getMassPoint(), 1, Image_Anime.grass_wave, SE.SYSTEM_SCROLL, 1,
				task);
	}

}
