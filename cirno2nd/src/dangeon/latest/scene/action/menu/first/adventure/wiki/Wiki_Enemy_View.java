package dangeon.latest.scene.action.menu.first.adventure.wiki;

import java.awt.Graphics2D;
import java.awt.Point;

import main.constant.FR;
import dangeon.latest.scene.Base_View;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class Wiki_Enemy_View extends Base_View {

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g, 200);
		((Wiki_Enemy) PARENT_SCENE).WINDOW.drawWindow(g);
		// Image im = CHARA_IMAGE.多々良小傘.getWalkImage(1, DIRECTION.DOWN, 1);
		Base_Enemy en = ((Wiki_Enemy) PARENT_SCENE).ENEMY;
		int x = FR.SCREEN_WIDTH / 2;
		int y = 60;
		Point p = en.getAttackDeltPoint().getLocation();
		// x += p.x;
		// y += p.y;
		p.translate(x - 25, y);
		// x = 320;
		// y = FR.SCREEN_HEIGHT - 10;
		en.drawCreature(g, p);
		// g.drawImage(im, x - w / 2, y - h / 2, null);
	}
}