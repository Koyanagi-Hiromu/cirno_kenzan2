package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.grass.しあわせ草;
import dangeon.model.object.artifact.item.grass.ちからの草;
import dangeon.model.object.artifact.item.grass.命の草;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.util.ThunderDamage;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.anime.ThunderEffect;
import dangeon.view.detail.MainMap;

public class 永江衣玖 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 永江衣玖(Point p, int Lv) {
		super(p, Lv);
	}

	public boolean effect() {
		if (playerIsInNeiver()) {
			final Base_Creature C = this;
			final DIRECTION d = direction;

			setAnimation(new Special_Wait_FrameByFrame(this, SE.LUCKEY, 1,
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
							if (frame < 8) {
								C.direction = DIRECTION
										.getDirectionFromRotate(frame + 3);
							} else {
								C.direction = d;
							}
							if (frame == 6) {
								Message.set(getColoredName(), "はポーズを決めた");
								int dam;
								if (LV == 1)
									dam = 20;
								else if (LV == 2)
									dam = 30;
								else if (LV == 3)
									dam = 30;
								else
									dam = 30;
								ThunderDamage.thunderDamage(C, C, C, dam);
							}
						}
					}, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 1, 1, 2, 2, 2, 2,
					2, 2, 2, 2, 1, 1, 0, 0));
		}

		return true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		MainMap.addEffect(new ThunderEffect(MassCreater.isPlayerInRoom()));
		for (Base_Enemy em : MapInSelect.getListRoomOrRoadInEnemy()) {
			ThunderDamage.thunderDamage(this, this, em, 30);
		}
		return true;
	}

	@Override
	protected void enemyBreakAction() {
		if (LV == 4) {
			SE.FANFARE2.play();
			for (int i = 0; i < 4; i++) {
				ItemFall.itemFall(getMassPoint(), specialDrop());
			}
		}
		super.enemyBreakAction();
	}

	@Override
	protected Base_Artifact getDropItem() {
		return specialDrop();
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			if (getAnimation().getComa() == -1)
				return IM.getWalkImage(LV, direction, 0);
			else
				return IM.getSPImage(LV, direction, getAnimation().getComa());
		} else
			return super.getImage();
	}

	@Override
	protected void init1() {
	}

	@Override
	protected void init2() {
	}

	@Override
	protected void init3() {
		setCondition(CONDITION.倍速, 0);
	}

	@Override
	protected void init4() {
		setCondition(CONDITION.三倍速, 0);
	}

	@Override
	protected int itemDropParcent() {
		return 10000;
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

	@Override
	protected boolean specialCheck() {
		if (playerIsInNeiver()) {
			if (isSpecialParcent())
				return true;
		}
		return false;
	}

	private Base_Artifact specialDrop() {
		switch (R.ran(4)) {
		case 0:
			return new しあわせ草(getMassPoint());
		case 1:
			return new ちからの草(getMassPoint());
		case 2:
			return new 命の草(getMassPoint());
		default:
			return ItemTable.itemReturn(getMassPoint(), null);
		}
	}

}
