package dangeon.latest.scene.action.otog.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Iterator;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.latest.scene.action.otog.value.ValueHolder;

public class FallFood extends Base_Otog_Object {
	private int cool;
	private int speed = 0;
	private final int FRAME = 10;
	private int frame = 0;
	public final boolean EVEN;

	public FallFood(ValueHolder vh, TEXT tex, int index, TargetA targetA) {
		// x = vh.HASH.getX(index) - 12;
		// y = vh.HASH.getY(index) - 13;
		x = targetA.x;
		y = targetA.y + targetA.del_y;
		EVEN = targetA.EVEN;
		switch (tex) {
		case PERFECT:
			// cool = 3;
			// break;
		case COLD:
			cool = 2;
			break;
		case COOL:
			cool = 1;
			break;
		case CHILLY:
		case NOODLE:
		case MISS:
		default:
			cool = 0;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		Image im;
		// if (cool == 3)
		// im = Image_Artifact.FOOD.getImage(2);
		// im = Image_Artifact.trap1_9.getImage(0);
		// im = Image_Object.ice_block.getImage();
		// else
		im = ((EVEN) ? Image_Artifact.GOLDEN_FOOD : Image_Artifact.FOOD)
				.getImage(cool);
		int rate = 100 - frame * 100 / FRAME;
		int s = 50 * rate / 100;
		g.drawImage(im, x, y, s, s, null);
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
