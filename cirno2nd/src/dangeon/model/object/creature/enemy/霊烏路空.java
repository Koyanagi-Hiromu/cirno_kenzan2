package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.大砲の弾;
import dangeon.model.object.artifact.item.scrool.八咫烏の書;
import dangeon.model.object.artifact.item.scrool.自爆の書;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class 霊烏路空 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 霊烏路空(Point p, int Lv) {
		super(p, Lv);
	}

	/**
	 * 
	 * @param c
	 *            投げたキャラクター
	 */
	public boolean effect() {
		DIRECTION dir = root();
		if (dir == null) {
			return false;
		} else {
			this.setDirection(dir);
		}
		final 霊烏路空 C = this;
		Message.set(getColoredName().concat("は").concat("核弾を発射した"));
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
						if (frame == 6) {
							Base_Artifact a = new 大砲の弾(getMassPoint(), false);
							a.itemThrow(C);
						}
					}
				}, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 4));

		return true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Base_Artifact a = new 大砲の弾(getMassPoint(), false);
		a.itemThrow(Player.me);
		return false;
	}

	@Override
	protected Base_Artifact getDropItem() {
		int r = new R().nextInt(100);
		if (r < 70) {
			return new 大砲の弾(mass_point, true);
		} else if (r < 95) {
			return new 自爆の書(mass_point);
		} else {
			return new 八咫烏の書(mass_point);
		}
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, getAnimation().getComa());
		}
		return super.getImage();
	}

	@Override
	public int getItemDropParcent() {
		return super.getItemDropParcent() * 2;
	}

	@Override
	protected void init1() {
		setHalfWalk();
	}

	@Override
	protected void init2() {
	}

	// public Image getImage() {
	// return main.res.IMAGE.幽香.getImage(direction, MainThread.getKoma());
	// }

	@Override
	protected void init3() {
		set2timesWalk();
	}

	@Override
	protected void init4() {
		set2timesWalk();
		setCondition(CONDITION.炎上, 0);
	}

	private boolean railgun() {

		return true;
	}

	private DIRECTION root() {
		for (DIRECTION dir : DIRECTION.values()) {
			if (dir.NUM == 5) {
				continue;
			}
			List<Base_Creature> l = MapInSelect.getListStraightHitCreature(dir,
					getMassPoint(), 10, true);
			for (Base_Creature creature : l) {
				if (!MassCreater.is1InTheSightFrom2(getMassPoint().x,
						getMassPoint().y, creature.getMassPoint().x,
						creature.getMassPoint().y, 1)) {
					continue;
				}
				if (creature instanceof Player) {
					return dir;
				}
			}
		}
		return null;
	}

	@Override
	protected boolean specialAttack() {
		if (special_ok) {
			if (!player_is_in_sight) {
				return false;
			}
			return effect();
		}
		return false;
	}

	// protected boolean specialAction() {
	// if (railgun()) {
	// return true;
	// }
	// return false;
	// }

	@Override
	protected boolean specialCheck() {
		// return false;
		if (!player_is_in_sight) {
			return false;
		}
		if (!isSpecialParcent()) {
			return false;
		}
		return root() != null;
	}

}
