package dangeon.view.anime;

import java.awt.Graphics2D;

import main.res.Image_Anime;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.Base_Creature;

public class EyeEffect extends Effect_Image_Anime {
	private final Base_Creature C;

	public EyeEffect(Base_Creature c, Task t, int lv) {
		super(c.getMassPoint(), 3, Image_Anime.eye, SE.CREASY_WAVE, 1, t);
		IM_ANIME.setLv(lv);
		C = c;
	}

	@Override
	public boolean draw(Graphics2D g) {
		C.startDamaging();
		// double min = 0.3f, max = 1.0f;
		// double a = (1.0 - (0.0 + IM_ANIME.getLength() - getFrame())
		// / IM_ANIME.getLength())
		// * (max - min) + min;
		// System.out.println(getFrame());
		// BeautifulView.setAlphaOnImg(g, new Float(a));
		boolean b = super.draw(g);
		// BeautifulView.setAlphaOnImg(g, 1f);
		return b;
	}

}
