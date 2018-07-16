package dangeon.view.anime;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import main.res.Image_Anime;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;

public class ExplosionEffect extends Effect_Image_Anime {

	private final ArrayList<Base_Creature> LIST;

	public ExplosionEffect(boolean b, Point p, Task t) {
		super(p, 1, Image_Anime.explo, SE.BOMB, 1, t);
		if (t == null) {
			LIST = null;
		} else {
			LIST = MapList.getSurroundedCreature_IncludingMyself(p);
		}
	}

	public ExplosionEffect(Point p, Task t) {
		super(p, 2, Image_Anime.explo, SE.BOMB, 1, t);
		if (t == null) {
			LIST = null;
		} else {
			LIST = MapList.getSurroundedCreature_IncludingMyself(p);
		}
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (LIST != null) {
			for (Base_Creature c : LIST) {
				if (!c.isFire()) {
					c.startDamaging();
				}
			}
		}
		return super.draw(g);
	}

	@Override
	protected int getDelX() {
		return 5;
	}
}
