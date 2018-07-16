package dangeon.view.detail;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import dangeon.model.object.creature.Base_Creature;
import dangeon.view.anime.Base_Anime;

public class SecondAnime {
	private static HashMap<Base_Anime, Base_Creature> hash = new HashMap<Base_Anime, Base_Creature>();

	//
	// /**
	// * synchronized => draw中は遮断
	// *
	// * @param e
	// */
	// public synchronized static void addEffect(Base_Effect e) {
	// list_effect.add(e);
	// }
	private static ArrayList<Base_Anime> list = new ArrayList<Base_Anime>();

	public synchronized static void draw(Graphics2D g) {
		for (Iterator<Base_Anime> iterator = hash.keySet().iterator(); iterator
				.hasNext();) {
			Base_Anime anime = iterator.next();
			boolean animating = anime.drawAnime(g);
			if (!animating) {

				list.add(anime);
			}
		}
		for (Base_Anime a : list) {
			Base_Creature c = hash.get(a);
			if (c.getSecondAnimation() == a) {
				c.finshSecondAnimation();
			} else {
				int count = 0;
				for (Base_Creature _c : hash.values()) {
					if (c == _c) {
						if (++count == 2) {
							break;
						}
					}
				}
				if (count < 2) {
					c.finshSecondAnimation();
				}
			}
			hash.remove(a);
		}
		list.clear();
	}

	public static void setAnimation(Base_Creature c) {
		hash.put(c.getSecondAnimation(), c);
	}
}
