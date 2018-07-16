package dangeon.model.object.creature.enemy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.controller.ThrowingItem;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.bullet.目からビーム;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.util.R;
import dangeon.view.anime.OuraEffect;

public class Exルーミア extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private 剣 sword;

	private int pre_strength;

	public Exルーミア(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean counterAttackEnemy() {
		power_up_count++;
		setSecondAnimation(new OuraEffect(this, true));
		SE.STATUS_POWER_UP.play();
		int delt;
		if (LV == 1)
			delt = 20;
		else
			delt = 20;
		changeMAX_STR(getSTR() + delt);
		setSTR(getMAX_STR());
		switch (new R().nextInt(10)) {
		case 0:
			Message.set("「覚えたぞ」");
			break;
		case 1:
			Message.set("「絶～～～対に負けないッ」");
			break;
		case 2:
			Message.set("「見切ったのか」");
			break;
		case 3:
			Message.set("「攻撃力が", 半角全角コンバーター.半角To全角数字(delt), "アップした」");
		default:
			break;
		}
		// Message.set("");
		return super.counterAttackEnemy();
	}

	@Override
	public void drawCreature(Graphics2D g, Point p) {
		super.drawCreature(g, p);
		if (isAttacking()) {
			sword.setDirection(direction);
			sword.drawCreature(g, p);
		}
	}

	@Override
	public Image getATKImage(int LV, DIRECTION attacking_direction,
			int attack_No) {
		if (attack_No == 4)
			return null;
		return super.getATKImage(LV, attacking_direction, attack_No);
		// return IM.getATKImage(0, direction, attack_No + 1);
	}

	@Override
	protected Point getAttackPoint() {
		return screen_point;
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getATKImage(LV, direction, 3);
		} else {
			return super.getImage();
		}
	}

	@Override
	protected CONDITION getInitCondition() {
		if (getMassPoint().x == 0 && getMassPoint().y == 0)
			return null;
		return CONDITION.やりすごし;
	}

	@Override
	protected void init1() {
		super.init1();
		sword = new 剣(mass_point, LV);
		pre_strength = MAX_STR;
	}

	@Override
	protected void init2() {
		super.init2();
		sword = new 剣(mass_point, LV);
		pre_strength = MAX_STR;
	}

	@Override
	protected void init3() {
		super.init3();
		sword = new 剣(mass_point, LV);
		pre_strength = MAX_STR;
	}

	@Override
	protected void init4() {
		super.init4();
		sword = new 剣(mass_point, LV);
		pre_strength = MAX_STR;
	}

	@Override
	public boolean itemHitEffect(final Base_Artifact a, boolean ento) {
		String msg = null;
		if (a instanceof MagicBullet) {
			msg = "魔法弾は闇に飲み込まれて消えてしまった";
		} else if (a instanceof 目からビーム) {
			msg = "ビームは闇に飲み込まれて消えてしまった";
		}
		if (msg != null) {
			Message.set(msg);
			setCondition(CONDITION.やりすごし, 0);
			if (power_up_count > 0) {
				Message.set(getColoredName(), "のちからが元に戻った");
				power_up_count = 0;
				changeMAX_STR(pre_strength);
				setSTR(pre_strength);
			} else {
				setCondition(CONDITION.仮眠, 0);
				setCondition(CONDITION.睡眠, 3);
				Message.set(getColoredName(), "は眠ってしまった");
			}
			return false;
		} else {
			return super.itemHitEffect(a, ento);
		}
	}

	@Override
	public boolean reflection(final ThrowingItem ti) {
		if (ti.A instanceof MagicBullet || !new R().is(40)) {
			return super.reflection(ti);
		}

		SE.YOUMU_SP2.play();
		attaking_frame = 4;
		sword.setAttackingFrame(attaking_frame);
		direction = ti.A.direction.getReverse();
		sword.setDirection(direction);
		final String s = getColoredName();
		startAttack(new Task() {
			/**
						 *
						 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Message.set(s, "は投擲物を跳ね返した");
			}
		});
		return true;
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

	@Override
	public void startAttack(Task t) {
		sword.startAttack(null);
		super.startAttack(t);
	}

	@Override
	protected void upDate_NormalAttack() {
		sword.upDate_NormalAttack();
		super.upDate_NormalAttack();
		int len = 2;
		if (attaking_frame < len) {
			attack_No = 0;
		} else if (attaking_frame < len * 2) {
			attack_No = 1;
		} else if (attaking_frame < len * 3) {
			attack_No = 2;
		} else {
			attack_No = 3;
		}
	}

}
