package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.bullet.目からビーム;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;

public class 赤蛮奇_頭 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	Base_Creature tARGET = null;

	private boolean flag_throwing = false;

	public 赤蛮奇_頭(Point p, int Lv) {
		super(p, Lv);
	}

	public 赤蛮奇_頭(Point p, int Lv, boolean target_player) {
		super(p, Lv);
		setPlayerSide(!target_player);
	}

	@Override
	public void action() {
		if (LV == 4)
			super.action();
		else if (!isSkillActive() || !specialCheck()
				|| Player.me.getConditionList().contains(CONDITION.やりすごし)
				|| Player.me.getConditionList().contains(CONDITION.暗闇)
				|| Player.me.getConditionList().contains(CONDITION.透明))
			enemy_actioned = true;
		else
			super.action();
	}

	private boolean aim() {
		ArrayList<Base_Creature> _list = MapInSelect
				.getListCreatureOnTheStraight8Direction_OnlyFirst(
						getMassPoint(), 0);
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (Base_Creature _c : _list) {
			if (_c.equals(Player.me) || _c.isPlayerSide()) {
				continue;
			}
			if (!MassCreater.isPointInTheSameRoom(getMassPoint(),
					_c.getMassPoint())) {
				continue;
			}
			if (_c instanceof 博麗霊夢 && _c.isPassiveSkillActive()) {
				continue;
			}
			if (_c instanceof わかさぎ姫) {
				if (_c.isThroughItem(null)) {
					continue;
				}
			}
			if (_c.conditionCheck(CONDITION.透明)) {
				continue;
			}
			list.add(_c);
		}
		if (list.isEmpty()) {
			return false;
		}
		tARGET = list.get(R.ran(list.size()));
		return true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		return false;
	}

	@Override
	protected Point getAttackPoint() {
		if (flag_throwing) {
			return screen_point;
		} else {
			return super.getAttackPoint();
		}
	}

	private boolean root() {
		if (isPlayerSide()) {
			// if (!MassCreater.isPlayerInTheSameRoom(mass_point))
			// return false;
			return aim();
		} else {
			if (!player_is_in_sight) {
				return false;
			}
			tARGET = Player.me;
			return MapInSelect.isCreatureOnTheStraightAllDirection(this,
					Player.me, Math.max(MassCreater.HEIGHT, MassCreater.WIDTH));
		}
	}

	public void setThrowing() {
		attacking_direction = direction;
		normal_attack_phase = AttackPhase.GO;
		flag_throwing = true;
	}

	@Override
	protected boolean specialAttack() {
		if (!root()) {
			return true;
		}
		if (!isPlayerSide() && attack_possible())
			return false;
		direction = converDirection(tARGET.getMassPoint());
		setThrowing();
		SE.ATTACK_SHOOT_ICY.play();
		new 目からビーム(this).itemThrow(this, HowToThrow.MAGIC,
				Math.max(MassCreater.HEIGHT, MassCreater.WIDTH));
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (isPlayerSide())
			return true;
		if (LV == 4) {
			return root();
		} else
			return !attack_possible();
	}

	@Override
	public void upDate() {
		if (flag_throwing) {
			super.upDate_NormalAttack();
			flag_throwing = (normal_attack_phase != null);
		} else {
			super.upDate();
		}
	}
}
