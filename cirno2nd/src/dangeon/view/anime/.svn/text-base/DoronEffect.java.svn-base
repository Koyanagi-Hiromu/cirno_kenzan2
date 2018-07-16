package dangeon.view.anime;

import java.awt.Point;

import main.res.Image_Anime;
import main.res.SE;
import dangeon.controller.task.Task;

public class DoronEffect extends Effect_Image_Anime {

	public DoronEffect(Point p, Task t) {
		this(p, t, false);
	}

	public DoronEffect(Point p, Task t, boolean large) {
		this(p, t, large, true);
	}

	public DoronEffect(Point p, Task t, boolean large, boolean se) {
		super(p, 2, large ? Image_Anime.Doron_Large : Image_Anime.Doron,
				se ? SE.APPEAR : null, 1, t);
	}

}
