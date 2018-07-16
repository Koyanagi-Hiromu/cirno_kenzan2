package dangeon.view.anime;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

import main.util.BeautifulView;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;

public class HealEffect extends HitEffect {

	public HealEffect(Base_Creature c, int damage) {
		super(c, damage);
		Message.set(c.getColoredName(), "のHPが", 半角全角コンバーター.半角To全角数字(damage),
				"回復した");
	}

	@Override
	protected void addFrameImage() {
	}

	@Override
	public boolean draw(Graphics2D g) {
		Point p = ObjectPoint.getScreenPointRelPlayer(SCREEN_POINT);
		p.translate(0, -MAP.TILE_SIZE + MAP.Y_OFFSET);
		float rate = new Float(Math.cos((double) time / TIME_MAX));
		BeautifulView.setAlphaOnImg(g, rate);
		g.drawImage(DAMAGE, p.x + DELT.x, p.y + DELT.y, WIDTH, HEIGHT, null);
		BeautifulView.setAlphaOnImg(g, 1F);
		return upDatePopNumber();
	}

	@Override
	protected void SE(int damage) {
		if (damage < 100) {
			main.res.SE.HEAL_SMALL.play();
		} else {
			main.res.SE.HEAL_GREATER.play();
		}
	}

	@Override
	protected void setColor(Graphics2D g, Base_Creature c, int height) {
		Color COLOR1 = Color.GREEN, COLOR2 = Color.WHITE;
		g.setPaint(new GradientPaint(0, 0, COLOR1, 0, height, COLOR2));
	}
}
