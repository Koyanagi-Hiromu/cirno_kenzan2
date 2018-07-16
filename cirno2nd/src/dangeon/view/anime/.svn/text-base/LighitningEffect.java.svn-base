package dangeon.view.anime;

import java.awt.Graphics2D;
import java.awt.Point;

import main.res.Image_Anime;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class LighitningEffect extends Effect_Image_Anime {
	private final Base_Creature C;

	public LighitningEffect(Base_Creature c, Task t) {
		super(c.getMassPoint(), 3, Image_Anime.kanden, SE.THUNDER, 1, t);
		C = c;
	}

	public LighitningEffect(Point p, Task t) {
		super(p, 2, Image_Anime.kanden, SE.THUNDER, 1, t);
		C = null;
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (C != null && !C.isThunder()) {
			boolean f = true;
			if (C.equals(Player.me)) {
				if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.衣)
						|| Player.me.getHP() <= 1) {
					f = false;
				}
			}
			if (f)
				C.startDamaging();
		}
		return super.draw(g);
	}

}
