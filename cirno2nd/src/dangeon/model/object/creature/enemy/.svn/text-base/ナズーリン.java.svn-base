package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.scrool.人を殺せる書;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.Battle.Battle;
import dangeon.util.R;

public class ナズーリン extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private int rolling_attack_task;
	private boolean flag_once;

	private int pre_lv;

	public ナズーリン(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		if (Player.me.getMAX_HP() / 2 > Player.me.getHP()) {
			Battle.setCriticalFlag();
			for (int i = 0; i < LV; i++) {
				Player.me.normalAttack();
			}
			return true;
		}
		return false;
	}

	private int getAttackTask() {
		int devide;
		if (LV == 1)
			devide = 4;
		else if (LV == 2)
			devide = 2;
		else
			devide = 1;
		if (getMAX_HP() / devide >= getHP()) {
			jaho();
		}
		if (conditionCheck(CONDITION.邪法)) {
			return 3;
		} else {
			return 1;
		}
	}

	@Override
	protected Base_Artifact getDropItem() {
		int parcent;
		if (LV == 1)
			parcent = 1;
		else if (LV == 2)
			parcent = 10;
		else if (LV == 3)
			parcent = 10;
		else
			parcent = 100;
		return new R().is(parcent) ? new 人を殺せる書(getMassPoint().getLocation())
				: super.getDropItem();
	}

	@Override
	protected int itemDropParcent() {
		return 500;
	}

	private void jaho() {
		if (!flag_once && !conditionCheck(CONDITION.邪法)) {
			flag_once = true;
			Message.set(getColoredName(), "は邪法に手を染めた");
			SE.SYSTEM_CURSE.play();
			setCondition(CONDITION.邪法, 5 * LV);
		}
	}

	private boolean reCheck() {
		if (pre_lv == LV) {
			if (Player.me.getHP() > 0 && isSkillActive()) {
				return attack_possible();
			}
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible()) {
			if (LV == 3) {
				jaho();
				return true;
			}
			return false;
		}
		direction = converDirection(Player.me.getMassPoint());
		rolling_attack_task = getAttackTask();
		pre_lv = this.LV;
		attack();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (LV == 3 && player_is_in_sight) {
			jaho();
		}
		if (!attack_possible()) {
			return false;
		}
		return true;
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		if (attaking_frame == -1) {
			rolling_attack_task--;
			if (rolling_attack_task > 0) {
				Player.me.endDamaging();
				if (reCheck())
					attack();
			}
		}
	}
}
