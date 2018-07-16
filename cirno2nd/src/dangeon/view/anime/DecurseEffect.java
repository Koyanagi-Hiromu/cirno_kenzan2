package dangeon.view.anime;

import java.awt.Graphics2D;
import java.awt.Point;

import main.res.Image_Anime;
import main.res.SE;
import main.util.BeautifulView;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.player.Player;

public class DecurseEffect extends Effect_Image_Anime {

	public DecurseEffect() {
		this(null);
	}

	public DecurseEffect(Point p, Task t) {
		super(p, 1, Image_Anime.decurse, SE.DECURSE, 1, t, 0, 1, 2, 3, 4, 4, 4,
				4, 4, 3, 2, 1, 0);
	}

	public DecurseEffect(Task t) {
		this(Player.me.getMassPoint(), t);
		Player.me.setDirection(DIRECTION.DOWN);
	}

	@Override
	public boolean draw(Graphics2D g) {
		BeautifulView.setAlphaOnImg(g, 0.5f);
		boolean flag = super.draw(g);
		BeautifulView.setAlphaOnImg(g, 1f);
		return flag;
	}

	@Override
	public int getComa() {
		return super.getComa();
		// int c = getFrame() * 2 / getMaxComa();
		// int max = Image_Anime.decurse.getLength() - 1;
		// if (c > max) {
		// c = max * 2 - c + 1;
		// }
		// System.out.println(c);
		// return c;
	}
}
