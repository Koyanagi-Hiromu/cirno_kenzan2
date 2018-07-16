package dangeon.latest.scene.action.otog.object;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.Iterator;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.latest.scene.action.otog.value.ValueHolder;

public class TargetA_Long extends TargetA {
	public int del_y2;
	public final int LENGTH;
	public final Color START, FINAL_A = new Color(0, 255, 0, 100),
			FINAL_B = new Color(0, 0, 0, 0);
	public TEXT pro_text;
	private Boolean flag_pushing_me = false;

	public TargetA_Long(long length, int row, ValueHolder vH, long mp3Frame) {
		super(row, vH, mp3Frame);
		// LENGTH = (int) (length / 1000);
		LENGTH = (int) (length * vH.SPEED + 500) / 1000;
		START = EVEN ? new Color(255, 255, 255, 200) : new Color(255, 255, 0,
				200);
	}

	@Override
	public void draw(Graphics2D g) {
		drawLines(g);
		if (isFirst())
			g.drawImage((EVEN ? Image_Artifact.GOLDEN_FOOD
					: Image_Artifact.FOOD).getImage(0), this.x, this.y + del_y,
					null);
	}

	private void drawLines(Graphics2D g) {
		int SIZE = 23;
		int x = this.x + 13;
		int w = SIZE;
		int top = getTop();
		int max = P.VH.HASH.getY(1) + 12;
		int h = LENGTH;
		if (top + h > max)
			h = max - top;
		if (isMissed())
			g.setPaint(new GradientPaint(x, top, FINAL_B, x, top + LENGTH,
					Color.BLACK));
		else
			g.setPaint(new GradientPaint(x, top, FINAL_A, x, top + LENGTH,
					START));

		g.fillRect(x, top, w, h);
		g.setColor(Color.GREEN);
		g.drawRect(x, top, w, h);
	}

	public int getTop() {
		return y + del_y2 + 25 + 1 - LENGTH;
	}

	@Override
	public void hitSet(ValueHolder vh, TEXT tex) {
		if (isPushing())
			return;
		if (tex.isNoGood() && tex != TEXT.CHILLY)
			return;
		pro_text = tex;
		vh.objects.add(new FallFood(vh, tex, vh.HASH.getIndex(A), this));
		vh.objects.add(new HitObject(this));
		flag_pushing_me = true;
	}

	public boolean isFirst() {
		return flag_pushing_me != null && !flag_pushing_me;
	}

	public boolean isMissed() {
		return flag_pushing_me == null;
	}

	public boolean isPushing() {
		return flag_pushing_me != null && flag_pushing_me;
	}

	@Override
	protected boolean killMySelf(Iterator<?> iterator) {
		if (getTop() > P.VH.HASH.getY(1) + 12) {
			if (pro_text == null)
				P.set(TEXT.MISS);
			else
				P.set(pro_text);
			iterator.remove();
			return true;
		}
		return false;
	}

	@Override
	public void upDate(ValueHolder vh, long frame, Iterator<?> iterator) {
		del_y2 = del_y = (int) ((frame * 1000 - JUST_MP3_MILLI_FRAME)
				* vh.SPEED + 500) / 1000;
		if (del_y > 0) {
			del_y -= vh.DELAY_ACCEPT * vh.SPEED;
			if (del_y < 0)
				del_y = 0;
			else if (isFirst())
				flag_pushing_me = null;
			if (vh.demo && !isPushing()) {
				hitSet(vh, TEXT.PERFECT);
			}
		}
		if (killMySelf(iterator))
			return;
		if (isPushing()) {
			if (!vh.demo && !vh.MAIN_PANEL.KH.isContraining(A)) {
				flag_pushing_me = null;
				pro_text = TEXT.CHILLY;
			}
		}
	}
}
