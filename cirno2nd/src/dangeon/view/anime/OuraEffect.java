package dangeon.view.anime;

import java.awt.Graphics2D;

import main.res.Image_Anime;
import main.res.SE;
import main.util.BeautifulView;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.Base_Creature;

public class OuraEffect extends Effect_Image_Anime {

	public OuraEffect(Base_Creature c) {
		this(c, null);
	}

	public OuraEffect(Base_Creature c, boolean red) {
		this(c, null, red);
	}

	public OuraEffect(Base_Creature c, Task t) {
		this(c, t, false);
	}

	public OuraEffect(Base_Creature c, Task t, boolean red) {
		super(c.getMassPoint(), 2, red ? Image_Anime.oura_red
				: Image_Anime.oura, SE.STATUS_POWER_UP, 3, t);
	}

	@Override
	public boolean draw(Graphics2D g) {
		BeautifulView.setAlphaOnImg(g, 0.5f);
		boolean flag = super.draw(g);
		BeautifulView.setAlphaOnImg(g, 1f);
		return flag;
	}

}
