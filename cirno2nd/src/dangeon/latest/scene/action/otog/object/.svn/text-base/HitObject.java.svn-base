package dangeon.latest.scene.action.otog.object;

import java.awt.Graphics2D;
import java.util.Iterator;

import main.res.Image_Effect;
import dangeon.latest.scene.action.otog.value.ValueHolder;

public class HitObject extends Base_Otog_Object {
	private final int FRAME = 6;
	private int frame = 0;
	public final boolean EVEN;

	public HitObject(TargetA targetA) {
		x = targetA.x;
		y = targetA.y + targetA.del_y;
		EVEN = targetA.EVEN;
	}

	@Override
	public void draw(Graphics2D g) {
		int s = 40;
		g.drawImage(
				(EVEN ? Image_Effect.hit_00 : Image_Effect.HitA_).BI[frame / 2],
				x + 25 - s / 2, y + 25 - s / 2, s, s, null);
	}

	@Override
	public boolean killMySelf(Iterator<?> iterator) {
		if (frame == FRAME) {
			return super.killMySelf(iterator);
		}
		return false;
	}

	@Override
	public void upDate(ValueHolder vh, long frame, Iterator<?> iterator) {
		// x--;
		// y += 2;
		this.frame++;
		killMySelf(iterator);
	}
}
