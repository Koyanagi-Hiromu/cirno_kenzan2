package dangeon.view.anime;

import java.awt.Graphics2D;

import main.res.Image_Anime;
import main.util.BeautifulView;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class GoodBadEffect extends Effect_Image_Anime {

	public GoodBadEffect(Base_Creature c, boolean isGood) {
		super(c.getMassPoint(), 1, (isGood) ? Image_Anime.good_up
				: Image_Anime.bad_down, null, 0, null);
	}

	public GoodBadEffect(boolean isGood) {
		this(Player.me, isGood);
	}

	@Override
	public boolean draw(Graphics2D g) {
		BeautifulView.setAlphaOnImg(g, 0.5f);
		boolean flag = super.draw(g);
		BeautifulView.setAlphaOnImg(g, 1f);
		return flag;
	}

}
