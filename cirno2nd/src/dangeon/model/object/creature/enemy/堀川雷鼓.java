package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import main.util.DIRECTION;
import main.util.FrameShaker;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.bullet.Base_Bullet;
import dangeon.model.object.artifact.item.bullet.雷弾;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class 堀川雷鼓 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 堀川雷鼓(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Message.set("未実装");
		return true;
	}

	@Override
	public void enemyMovePossible() {
	}

	@Override
	public Image getImage() {
		DIRECTION direction = DIRECTION.UP_LEFT;
		int LV = this.LV;
		if (LV == 4)
			LV = 0;
		int koma = IM.getKoma(getMoveAnimationSpeed());
		if (koma == 2)
			koma = 0;
		if (conditionCheck(CONDITION.おにぎり))
			return Image_Artifact.FOOD.getImage(0);
		else if (isAttacking()) {
			return IM.ATK[LV][0][koma];
		} else if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, getAnimation().getComa());
		} else if (flag_damaging) {
			return IM.getDamImage(LV, direction);
		}
		return IM.WALK[LV][0][koma];
	}

	@Override
	public int isKamin() {
		return 0;
	}

	private boolean levelCheck() {
		switch (LV) {
		case 4:
			return true;
		case 3:
			if (levelCheck(5))
				return true;
		case 2:
			if (MassCreater.isPlayerInTheSameRoom(getMassPoint()))
				return true;
		case 1:
			return player_is_in_sight && levelCheck(2);
		}
		return false;
	}

	private boolean levelCheck(int length) {
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

	@Override
	protected boolean specialAttack() {
		if (isSpecialParcent() && levelCheck()) {
			Base_Creature C = this;
			Base_Bullet bullet = new 雷弾(C, 10 + 10 * LV);
			bullet.direction = DIRECTION.getDirectionToPlayer(this);
			bullet.setMassPoint_ParabolaJumpAttack(Player.me.getMassPoint());
			TaskOnMapObject.setThrow(new ThrowingItem(bullet, C, false,
					HowToThrow.PARABOLA));
			Message.set(getColoredName(), "はドラムを叩いた！");
			FrameShaker.doneSoftly();
			setAnimation(new Special_Wait_FrameByFrame(this,
					SE.BREAKINTOONEROOM, 5, null, 2, 3, 4, 5, 4, 5, 5, 4, 5, 4,
					5, 4, 5, 4, 5, 4, 5, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1, 1, 1, 1,
					1, 0, 0));
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		return true;
	}

}
