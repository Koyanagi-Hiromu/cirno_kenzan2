package dangeon.model.object.artifact.item.bullet;

import main.res.BulletImage;
import main.res.SE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.MapInSelect;

public class アンカー extends Base_Bullet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public アンカー(Base_Creature c) {
		super(c, "アンカー");
		SE.THROW_HEAVY.play();
	}

	@Override
	public BulletImage getBulletImage() {
		return BulletImage.アンカー;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		MapInSelect.吹き飛ばし(c2, "飛んできたアンカーによって倒れた", c, direction, 10,
				c2.getLV() * 10 + 5);
		// effect(c2, c);
	}

	// private void move(int i, int j, Base_Creature source, Base_Creature
	// target) {
	// int x = target.getMassPoint().x;
	// int y = target.getMassPoint().y;
	// int count = 0;
	// while (true) {
	// count++;
	// x += i;
	// y += j;
	// if (MapList.getCreature(x + i, y + j) != null) {
	// target.setMassPoint(x, y);
	// damage(source, target);
	// damage(source, MapList.getCreature(x + i, y + j));
	// return;
	// } else if (!MassCreater.getMass(x + i, y + j).WALKABLE) {
	// target.setMassPoint(x, y);
	// damage(source, target);
	// return;
	// } else if (count > 10) {
	// target.setMassPoint(x, y);
	// damage(source, target);
	// return;
	// } else {
	// continue;
	// }
	// }
	// }
	//
	// private void damage(Base_Creature source, Base_Creature target) {
	// Damage.damage(source, null, source, target, 10);
	// }
	//
	// protected void effect(Base_Creature source, Base_Creature target) {
	// move(source.getDirection().X, source.getDirection().Y, source, target);
	// }
}
