package dangeon.model.object.creature.enemy;

import java.awt.Graphics2D;
import java.awt.Point;

import main.res.SE;
import main.util.FrameShaker;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.view.anime.OldReimuSeal;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.constant.MAP;
import dangeon.view.detail.MainMap;

public class 先代巫女 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 先代巫女(Point p, int Lv) {
		super(p, Lv);
	}

	protected void done() {
		int i = Damage.enemyCriticalAttack(this);
		SE.DAMAGED_CRITICAL.play();
		if (i > 0) {
			Player.me.setCondition(CONDITION.封印, getSealTime());
			FrameShaker.doneNormaly();
			MapInSelect.吹き飛ばし(this, null, Player.me, this.direction, 10, 5);
		} else {
			SE.MISS.play();
		}
	}

	@Override
	protected void drawCreature(Graphics2D g, int x, int y) {
		if (getAnimation() != null) {
			int coma = getAnimation().getComa();
			if (coma == 4) {
				// x += direction.X * MAP.TILE_SIZE / 3 * 1 / 3;
				// y += direction.Y * MAP.TILE_SIZE / 3 * 1 / 3;
			} else if (coma == 5) {
				x += direction.X * MAP.TILE_SIZE / 3 * 1 / 3;
				y += direction.Y * MAP.TILE_SIZE / 3 * 1 / 3;
			} else if (coma == 6) {
				x += direction.X * MAP.TILE_SIZE / 3;
				y += direction.Y * MAP.TILE_SIZE / 3;

			}
		}
		super.drawCreature(g, x, y);
	}

	private void effect() {
		MainMap.addEffect(new OldReimuSeal(this, Player.me));
		setAnimation(new Special_Wait_FrameByFrame(this, SE.SYSTEM_USING_RING,
				1, new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					};

					@Override
					public void work(int frame) {
						if (frame == 9) {
							done();

							// a.itemThrow(C);
						} else if (frame == 3) {
							Message.set("「歯ぁ食いしばりなぁ！！」");
						}
					}
				}, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 6, 6, 6, 6));
	}

	private int getSealTime() {
		if (LV == 1)
			return 2;
		else if (LV == 2)
			return 5;
		else if (LV == 3)
			return 5;
		else
			return 255;
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible())
			return false;
		effect();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		return attack_possible() && isSpecialParcent();
	}

}
