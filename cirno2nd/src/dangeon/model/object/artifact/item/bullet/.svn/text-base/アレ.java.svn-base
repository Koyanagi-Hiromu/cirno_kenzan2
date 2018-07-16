package dangeon.model.object.artifact.item.bullet;

import main.res.BulletImage;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public abstract class アレ extends Base_Bullet {
	private static final long serialVersionUID = 1L;

	public アレ(Base_Creature c, String name) {
		super(c, name);
	}

	public アレ(String name) {
		super(Player.me, name);
	}

	@Override
	public BulletImage getBulletImage() {
		return BulletImage.アンカー;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		MapInSelect.吹き飛ばし(c2, "飛んできたアンカーによって倒れた", c, direction, 10,
				c2.getLV() * 10 + 5);
	}
}
