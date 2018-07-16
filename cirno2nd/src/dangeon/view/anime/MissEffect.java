package dangeon.view.anime;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

import main.constant.FR;
import main.util.BeautifulView;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;

public class MissEffect extends HitEffect {

	public MissEffect(Base_Creature c, boolean graze_miss) {
		super(c, graze_miss ? "GRAZE" : "MISS");
	}

	@Override
	protected void addFrameImage() {
	}

	@Override
	public boolean draw(Graphics2D g) {
		Point p = ObjectPoint.getScreenPointRelPlayer(SCREEN_POINT);
		p.translate(0, -MAP.TILE_SIZE + MAP.Y_OFFSET);
		float rate = new Float(Math.cos((double) time / TIME_MAX)) / 2;
		BeautifulView.setAlphaOnImg(g, rate);
		g.drawImage(DAMAGE, p.x + DELT.x, p.y + DELT.y, WIDTH, HEIGHT, null);
		BeautifulView.setAlphaOnImg(g, 1F);
		return upDatePopNumber();
	}

	@Override
	protected void SE(int damage) {
		main.res.SE.MISS.play();
	}

	@Override
	protected void setColor(Graphics2D g, Base_Creature c, int height) {
		Color COLOR1 = Color.CYAN, COLOR2 = Color.BLUE;
		g.setPaint(new GradientPaint(0, 0, COLOR1, 0, height, COLOR2));
	}

	@Override
	protected boolean upDatePopNumber() {
		time += FR.THREAD_SLEEP;
		if (time > TIME_MAX) {
			return false;
		}
		// 速度の成分が最終的に2/3になる
		double converter = (3.0 * TIME_MAX - time) / (3.0 * TIME_MAX);
		double v_x = FIRST_VELOCITY.x * converter / 10;
		double v_y = FIRST_VELOCITY.y * converter / 20;
		Point velocity = new Point((int) v_x, (int) v_y);
		// int a = (int) (ACCERARETION * converter);
		double t = time / 1000.0;
		double delt_x = velocity.x * t;
		double delt_y = velocity.y * t;
		DELT.move((int) delt_x + CENTERING, (int) delt_y + MIDDLING);
		return true;
	}
}
