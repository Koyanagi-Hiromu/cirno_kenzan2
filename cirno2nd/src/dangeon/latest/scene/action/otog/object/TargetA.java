package dangeon.latest.scene.action.otog.object;

import java.awt.Graphics2D;
import java.util.Iterator;

import main.Listener.ACTION;
import main.res.Image_Artifact;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.latest.scene.action.otog.value.ValueHolder;

public class TargetA extends Base_Otog_Object {
	public final long JUST_MP3_MILLI_FRAME;
	final int w = 50;
	public final ACTION A;
	public final boolean EVEN;
	public int del_y;

	public TargetA(byte b, ValueHolder vH, long milli_frame) {
		super(vH.MAIN_PANEL, vH.HASH.getX(b), vH.HASH.getY(b));
		A = vH.HASH.getA(b);
		JUST_MP3_MILLI_FRAME = milli_frame + vH.CHO_SEI;
		upDate(vH, vH.first_frame, null);
		EVEN = false;
	}

	public TargetA(int i, ValueHolder vH, long milli_frame) {
		super(vH.MAIN_PANEL, vH.HASH.getX(i) - 12, vH.HASH.getY(i) - 13);
		A = vH.HASH.getA(i);
		JUST_MP3_MILLI_FRAME = milli_frame + vH.CHO_SEI;
		upDate(vH, vH.first_frame, null);
		EVEN = i % 2 == 0;
	}

	protected void demo(ValueHolder vh) {
		for (Accepter ac : vh.accs) {
			ac.push(A, null);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage((EVEN ? Image_Artifact.GOLDEN_FOOD : Image_Artifact.FOOD)
				.getImage(0), x, y + del_y, null);
	}

	public boolean hit(DIRECTION d) {
		return false;
	}

	public void hitSet(ValueHolder vh, TEXT tex) {
		vh.MAIN_PANEL.set(tex);
		if (tex != TEXT.NOODLE) {
			vh.objects.add(new FallFood(vh, tex, vh.HASH.getIndex(A), this));
			vh.objects.add(new HitObject(this));
		}
	}

	@Override
	protected boolean killMySelf(Iterator<?> iterator) {
		if (y + del_y > P.H) {
			P.set(TEXT.MISS);
			return super.killMySelf(iterator);
		}
		return false;
	}

	@Override
	public void upDate(ValueHolder vh, long frame, Iterator<?> iterator) {
		del_y = (int) ((frame * 1000 - JUST_MP3_MILLI_FRAME) * vh.SPEED + 500) / 1000;
		if (del_y > 0) {
			del_y -= vh.DELAY_ACCEPT * vh.SPEED;
			if (del_y < 0)
				del_y = 0;
			if (vh.demo) {
				demo(vh);
				hitSet(vh, TEXT.PERFECT);
				super.killMySelf(iterator);
				return;
			}
		}
		killMySelf(iterator);
	}

}
