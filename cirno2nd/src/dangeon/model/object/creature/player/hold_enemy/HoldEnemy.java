package dangeon.model.object.creature.player.hold_enemy;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

import main.res.SE;
import main.util.BeautifulView;
import main.util.DIRECTION;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.EnemyFall;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.賽銭箱;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class HoldEnemy {
	public static HoldEnemy ME = new HoldEnemy();

	private static void draw(Graphics2D g, String s1, String s2, int x, int y) {
		String name = s1.concat(s2);
		y += g.getFontMetrics().getHeight() + 11;
		int w = g.getFontMetrics().stringWidth(
				StringFilter.getPlainString(name));
		int h = g.getFontMetrics().getHeight();
		g.setPaint(new GradientPaint(x, y - h, new Color(0, 0, 255, 100), x, y,
				new Color(0, 0, 100, 100)));
		g.fillRect(x, y - h + 2, w, h);
		g.setColor(Color.WHITE);
		g.drawRect(x, y - h + 2, w, h);
		StringFilter.drawString(g, name, x, y);
	}

	private Base_Enemy E = null;

	private int throw_max = 5;

	private int timer = 0, INTERVAL = 100;

	public int draw(Graphics2D g, int x, int y) {
		if (E != null) {
			Image im = E.getCharacterImage().getWalkImage(E.getLV(),
					DIRECTION.DOWN, 0f);
			int rect_size = 50 * 2 / 3;
			int percent = im.getWidth(null) * 2 / 3;
			int _x = x - (percent - rect_size) / 2;
			int _y = y - (percent - rect_size) / 2;
			Shape s = g.getClip();
			g.setClip(x, y, rect_size, rect_size);
			g.drawImage(im, _x, _y, percent, percent, Color.WHITE, null);
			g.setClip(s);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, rect_size, rect_size);
			g.setColor(Color.WHITE);
			y += 20;
			StringFilter.drawEnchant(g, x, y);
			g.setFont(NormalFont.NORMALFONT.deriveFont(10f));
			// draw(g, "装備：　", E.getExplanHoldEffect(), x, y);
			// y += g.getFontMetrics().getHeight();
			if (timer > 0) {
				timer--;
				BeautifulView.setAlphaOnImg(g, new Float((double) timer
						/ INTERVAL));
				draw(g, "使用：　", E.getExplanHoldAction(), x, y);
				BeautifulView.setAlphaOnImg(g, 1f);
			}
			return rect_size + 2;
		}
		return 0;
	}

	public Base_Enemy get() {
		return E;
	}

	private int getThrowMax() {
		return throw_max;
	}

	public boolean hold() {
		if (E == null) {
			final Base_Enemy em = MapInSelect
					.getFrontEnemyFromCreature(Player.me);
			if (em != null) {
				if (em instanceof 賽銭箱) {
					SE.SYSTEM_MENU.play();
					Message.set("目の前に敵はいないぞ");
				} else {
					timer = INTERVAL;
					SE.WARP_INSTANT.play();
					TurnSystemController.callMeToStartEnemyTurn();
					em.setMassPoint_ParabolaJump_Smalling(
							Player.me.getMassPoint(), new Task() {
								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								@Override
								public void work() {
									SE.SYSTEM_ENCHANT.play();
									Message.set(em.getColoredName(), "を装備した");
									em.setMassPoint(new Point(0, 0));
									MapList.removeEnemy(em);
									// TaskOnMapObject.addEnemyRemoveTask(em);
									E = em;
								}
							});
					return true;
				}
			} else {
				SE.SYSTEM_MENU.play();
				Message.set("目の前に敵はいないぞ");
			}
		} else {
			Message.set("装備できるエネミーは1体までだぞ");
		}
		return false;
	}

	public boolean isHold() {
		return E != null;
	}

	public void reset() {
		E = null;
	}

	public boolean specialAction() {
		if (E == null) {
			Message.set("敵を装備していないので能力は使えない");
			return false;
		}
		if (!E.enchantEnemySpecialAction()) {
			Player.me.normalAttack();
		}
		TurnSystemController.callMeToStartEnemyTurn();
		return true;
	}

	/**
	 * 投げられる場合は、trueを返す
	 * 
	 * @return
	 */
	public boolean throwEnemy() {
		if (E == null) {
			return false;
		}
		Point p = Player.me.getMassPoint();
		DIRECTION d = Player.me.getDirection();
		int _x = d.X;
		int _y = d.Y;
		ArrayList<Point> list = new ArrayList<Point>();
		for (int i = 1; i <= getThrowMax(); i++) {
			if (i != 1) {
				_x += d.X;
				_y += d.Y;
			}
			Point _p = new Point(p.x + _x, p.y + _y);
			if (!MassCreater.getMass(_p).WALKABLE) {
				break;
			}
			if (MassCreater.getMass(_p).WATER) {
				continue;
			}
			list.add(_p);
		}
		if (list.isEmpty()) {
			SE.SYSTEM_MENU.play();
			Message.set("目標点が埋まっていて投げられない");
			return false;
		}
		SE.THROW_HEAVY.play();
		throwing(list.get(list.size() - 1));
		TurnSystemController.callMeToStartEnemyTurn();
		return true;
	}

	private void throwing(Point goal) {
		MapList.addEnemy(E);
		E.setMassPoint(Player.me.getMassPoint());
		E.setMassPoint_ParabolaJump_Undo(EnemyFall.getPoint(goal), new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
			}
		});
		E = null;
	}

}
