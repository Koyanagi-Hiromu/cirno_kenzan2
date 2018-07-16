package dangeon.view.anime;

import java.awt.Graphics2D;
import java.awt.Point;

import main.res.Image_Anime;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.Base_Creature;

public class FireEffect extends Effect_Image_Anime {
	private final Base_Creature C;

	public FireEffect(Base_Creature c, Task t) {
		super(c.getMassPoint(), 3, Image_Anime.ef, SE.BURN, 1, t);
		C = c;
	}

	public FireEffect(Point p, Task t) {
		super(p, 2, Image_Anime.ef, SE.BURN, 1, t);
		C = null;
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (C != null) {
			C.startDamaging();
		}
		return super.draw(g);
	}

}
