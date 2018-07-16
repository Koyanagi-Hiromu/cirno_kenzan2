package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.bullet.キスメ弾;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class 黒谷ヤマメ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private static final int length1 = 2;

	private static final int length2 = 5;

	private static final int length3 = 10;
	private キスメ弾 bullet = null;

	public 黒谷ヤマメ(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (LV == 4) {
			return true;
		}
		int length = length();
		Point p = Player.me.getMassPoint().getLocation();
		Point e = getMassPoint().getLocation();
		for (int i = -length; i <= length; i++) {
			for (int j = -length; j <= length; j++) {
				if (p.x == e.x + i && p.y == e.y + j) {
					return true;
				}
			}
		}
		return false;
	}

	private void effect() {
		direction = DIRECTION.getDirectionToPlayer(this);
		final 黒谷ヤマメ C = this;
		setAnimation(new Special_Wait_FrameByFrame(this, SE.THROW_BOMB, 1,
				new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					};

					@Override
					public void work(int frame) {
						if (frame == 1) {
							bullet = new キスメ弾(C, Player.me.getMassPoint());
							TaskOnMapObject.setThrow(new ThrowingItem(bullet,
									C, false, HowToThrow.PARABOLA));
						}
					}
				}, 0, 0, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3));
		Message.set(getColoredName().concat("はキスメを投げた"));

	}

	@Override
	public boolean enchantEnemySpecialAction() {
		final Base_Enemy em = getTargetEnemy();
		if (em == null) {
			return false;
		}
		Player.me.setDirection(DIRECTION.getDirection(Player.me, em));
		setAnimation(new Special_Wait_FrameByFrame(this, SE.THROW_BOMB, 1,
				new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					};

					@Override
					public void work(int frame) {
						if (frame == 1) {
							bullet = new キスメ弾(Player.me, em.getMassPoint());
							TaskOnMapObject.setThrow(new ThrowingItem(bullet,
									Player.me, false, HowToThrow.PARABOLA));
						}
					}
				}, 0, 0, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3));
		Message.set(Player.me.getColoredName().concat("はキスメを投げた"));
		return true;
	}

	@Override
	protected Point getAttackPoint() {
		return screen_point;
	}

	private Base_Enemy getTargetEnemy() {
		int length = length();
		Point p = Player.me.getMassPoint();
		for (int i = -length; i <= length; i++) {
			for (int j = -length; j <= length; j++) {
				Base_Enemy em = MapList.getEnemy(p.x + i, p.y + j);
				if (em != null) {
					return em;
				}
			}
		}
		return null;
	}

	private int length() {
		if (LV == 1) {
			return length1;
		} else if (LV == 2) {
			return length2;
		} else if (LV == 3) {
			return length3;
		} else {
			return 15;
		}
	}

	@Override
	protected boolean specialAttack() {
		if (check()) {
			effect();
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!isSpecialParcent()) {
			return false;
		}
		if (!check()) {
			return false;
		}
		return true;
	}

	@Override
	public void upDate() {
		if (bullet != null) {
			if (!bullet.isAnimating()) {
				bullet = null;
			}
		}
		super.upDate();
	}

}
