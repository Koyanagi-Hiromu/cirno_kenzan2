package dangeon.latest.scene.action.otog.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Iterator;

import main.res.CHARA_IMAGE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.latest.scene.action.otog.value.ValueHolder;
import dangeon.model.config.Config;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class TargetD extends TargetA {
	private final DIRECTION D;
	private DIRECTION d;

	public TargetD(DIRECTION d, ValueHolder vH, long milli_frame) {
		super((byte) (Config.isOtogArrowRight() ? 4 : 0), vH, milli_frame);
		D = d;
	}

	@Override
	protected void demo(ValueHolder vh) {
		for (Accepter ac : vh.accs) {
			ac.push(null, D);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		int size = 75;
		if (D.Y != 0)
			size = 100;
		g.drawImage(CHARA_IMAGE.arrow.getWalkImage(D.NUM / 2 - 1, D, 0), x + 25
				- size / 2, y + 25 - size / 2 + del_y, size, size, null);
		Base_Enemy e = P.VH.sub3.sub2.CHARA;
		if (e != null) {
			CHARA_IMAGE c = e != null ? e.getCharacterImage()
					: CHARA_IMAGE.arrow;
			Image im = c.getATKImage(D.NUM / 2 - 1, D, 1);
			size = im.getWidth(null) * 75 / 100;
			g.drawImage(im, x + 25 - size / 2, y + 25 - size / 2 + del_y, size,
					size, null);
		}
	}

	@Override
	public boolean hit(DIRECTION d) {
		this.d = d;
		return true;
	}

	@Override
	public void hitSet(ValueHolder vh, TEXT tex) {
		if (!vh.demo && D != d)
			tex = TEXT.ERROR;
		vh.MAIN_PANEL.set(tex);
		// vh.objects.add(new HitObject(this));
		switch (tex) {
		case PERFECT:
		case COLD:
			vh.west.set(D);
			return;
		case COOL:
			vh.west.set(DIRECTION.UP_RIGHT);
			return;
		case CHILLY:
			vh.west.set(DIRECTION.UP_LEFT);
			return;
		case ERROR:
			vh.west.set(DIRECTION.DOWN_LEFT);
			return;
		case NOODLE:
		case MISS:
			return;
		}
	}

	@Override
	protected boolean killMySelf(Iterator<?> iterator) {
		if (super.killMySelf(iterator)) {
			P.VH.west.set(DIRECTION.DOWN_RIGHT);
			return true;
		}
		return false;
	}
}
