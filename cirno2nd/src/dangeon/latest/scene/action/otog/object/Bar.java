package dangeon.latest.scene.action.otog.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.latest.scene.action.otog.value.ValueHolder;
import dangeon.model.config.Config;

public class Bar extends TargetA {
	public Bar(ValueHolder vH, long milli_frame) {
		super((byte) (Config.isOtogArrowRight() ? 4 : 0), vH, milli_frame);
		y += 25;
	}

	@Override
	protected void demo(ValueHolder vh) {
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		int y = this.y + del_y;
		g.drawLine(0, y, P.W, y);
	}

	@Override
	public void hitSet(ValueHolder vh, TEXT tex) {
	}

	@Override
	protected boolean killMySelf(Iterator<?> iterator) {
		// if (y + del_y > P.VH.accs[0].y + P.VH.accs[0].SIZE / 2) {
		if (y + del_y > P.H) {
			iterator.remove();
		}
		return false;
	}

	@Override
	public void upDate(ValueHolder vh, long frame, Iterator<?> iterator) {
		del_y = (int) ((frame * 1000 - JUST_MP3_MILLI_FRAME) * vh.SPEED + 500) / 1000;
		killMySelf(iterator);
	}
}
