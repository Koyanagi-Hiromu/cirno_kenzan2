package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import main.thread.MainThread;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Player;

public class わかさぎ姫 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag_in_water;

	public わかさぎ姫(Point p, int Lv) {
		super(p, Lv);
		// flag_in_water = true;
	}

	private boolean check(Point p) {
		if (MapList.getEnemy(p) != null)
			return false;
		if (LV >= 2 && MassCreater.getMass(p).WATER)
			return true;
		if (LV >= 3 && MassCreater.getMass(p).WALKABLE
				&& MapList.isTrapOrWaterPermitted(p))
			return true;
		return false;
	}

	@Override
	public boolean counterAttackEnemy() {
		return super.counterAttackEnemy();
	}

	private void effect() {
		DIRECTION[] ds = new DIRECTION[8];
		for (int i = 0; i < ds.length; i++) {
			ds[i] = Player.me.getDirection().getNeiboringDirection(
					(i + 1) / 2 * (i % 2 == 0 ? -1 : 1));
		}
		for (DIRECTION d : ds) {
			Point p = Player.me.getMassPoint().getLocation();
			p.translate(d.X, d.Y);
			if (check(p)) {
				SE.ATTACK_WATER.play();
				setMassPoint(p);
				boolean flag = false;
				if (!MassCreater.getMass(p).WATER) {
					flag = true;
					MassCreater.getMass(p).setWater(true);
				}
				if (LV == 4) {
					for (DIRECTION direction : DIRECTION.values_exceptNeatral()) {
						p = Player.me.getMassPoint().getLocation();
						p.translate(direction.X, direction.Y);
						if (MapList.isTrapOrWaterPermitted(p)) {
							flag = true;
							MassCreater.getMass(p).setWater(true);
						}
					}
				}
				if (!flag) {
					Message.set(getColoredName(), "は水場へ飛び移った");
				} else {
					Message.set(getColoredName(), "は水場を作って飛び移った");
					MassCreater.retakeMassSet();
				}
				flag_in_water = true;
				return;
			}
		}
		if (LV >= 3)
			Message.set(getColoredName(), "は様子を見ている");
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		return true;
	}

	@Override
	public void enemyMovePossible() {
		// 動けない
	}

	@Override
	public int getFootDeltY() {
		if (flag_in_water)
			return 22;
		else
			return 10;
	}

	@Override
	public Image getImage() {
		if (flag_in_water) {
			int koma = MainThread.getFrame()
					/ (int) (12 / getMoveAnimationSpeed()) % 2;
			return IM.getSPImage(LV, direction, koma);
		}
		return super.getImage();
	}

	@Override
	public int getShadowSize100() {
		return 0;
	}

	@Override
	public boolean isInValidOnAttack() {
		return flag_in_water;
	}

	@Override
	protected boolean isSkillActiveAnytimeButSealed() {
		return true;
	}

	@Override
	public boolean isThroughItem(Base_Artifact a) {
		return flag_in_water;
	}

	@Override
	public boolean isWatering() {
		return !conditionCheck(CONDITION.封印);
	}

	@Override
	public boolean itemHitEffect(Base_Artifact a, boolean ento) {
		boolean flag = super.itemHitEffect(a, ento);
		flag_in_water = this.getHP() > 0;
		return flag;
	}

	@Override
	public void normalAttack(boolean p_or_e) {
		flag_in_water = false;
		super.normalAttack(p_or_e);
	}

	public void resetFlagInWater(boolean b) {
		flag_in_water = b;
	}

	@Override
	protected boolean specialAttack() {
		effect();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible())
			return false;
		if (MassCreater.isPlayerInTheSameRoom(getMassPoint()))
			return true;
		flag_in_water = true;
		return false;
	}

	@Override
	public boolean staffHitEffect(Base_Artifact a) {
		boolean flag = super.staffHitEffect(a);
		flag_in_water = this.getHP() > 0;
		return flag;
	}
}
