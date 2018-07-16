package dangeon.view.anime;

import java.awt.Graphics2D;
import java.awt.Point;

import main.res.Image_Anime;
import main.res.SE;
import main.util.BeautifulView;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.Base_Creature;

public class IceEffect extends Effect_Image_Anime {
	private final Base_Creature C;

	public IceEffect(Base_Creature c, Task t, int lv) {
		super(c.getMassPoint(), 1, Image_Anime.sp_ice, SE.ICE, 1, t);
		Image_Anime.sp_ice.setLv(lv);
		C = c;
	}

	public IceEffect(Point massPoint, Task t, int lv) {
		super(massPoint, 1, Image_Anime.sp_ice, SE.ICE, 1, t);
		Image_Anime.sp_ice.setLv(lv);
		C = null;
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (C != null)
			C.startDamaging();
		double min = 0.3f, max = 1.0f;
		double a = (1.0 - (0.0 + IM_ANIME.getLength() - getFrame())
				/ IM_ANIME.getLength())
				* (max - min) + min;
		System.out.println(getFrame());
		BeautifulView.setAlphaOnImg(g, new Float(a));
		boolean b = super.draw(g);
		BeautifulView.setAlphaOnImg(g, 1f);
		return b;
	}

}
