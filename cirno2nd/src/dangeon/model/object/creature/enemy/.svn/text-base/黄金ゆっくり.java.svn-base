package dangeon.model.object.creature.enemy;

import java.awt.Color;
import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.model.object.creature.player.Player;

public class 黄金ゆっくり extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 黄金ゆっくり(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Player.me.setCondition(CONDITION.倍速, 0);
		return true;
	}

	@Override
	protected void init1() {
		setHalfWalk();
	}

	@Override
	protected void init3() {
		set2timesWalk();
	}

	@Override
	protected void init4() {
		set2timesWalk();
	}

	@Override
	public boolean isSkillActive(boolean active_in_dying) {
		return super.isPassiveSkillActive();
	}

	@Override
	public boolean itemHitEffect(Base_Artifact a, boolean ento) {
		if (a instanceof MagicBullet) {
			SE.REIMU_BARRIER.play();
			Message.set("霊夢の結界は魔法弾の効果をダメージに変えた");
			// if(((MagicBullet)a).C)
			// Damage.d
			chengeHP(null, null, -1);
			return false;
		} else {
			SE.REIMU_BARRIER.play();
			Message.set("霊夢の結界は投擲物の効果をダメージに変えた");
			chengeHP(null, null, -1);
			return false;
		}
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

	@Override
	public boolean staffHitEffect(Base_Artifact a) {
		SE.REIMU_BARRIER.play();
		chengeHP(null, null, -1);
		Message.set(Color.RED.toString(), "霊夢の結界は魔法の効果をダメージに変えた",
				Color.WHITE.toString());
		return false;
	}

}
