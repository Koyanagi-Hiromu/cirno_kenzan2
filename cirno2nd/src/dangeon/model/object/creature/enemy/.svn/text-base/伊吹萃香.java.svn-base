package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印招;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.Battle.Battle;
import dangeon.util.Damage;
import dangeon.util.R;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 伊吹萃香 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 伊吹萃香(Point p, int Lv) {
		super(p, Lv);
	}

	private ArrayList<DIRECTION> check() {
		ArrayList<DIRECTION> list = new ArrayList<DIRECTION>(8);
		if (LV == 1) {
			return list;
		}
		if (getLargement() >= 0 && player_is_in_sight && !MapList.isEnemyMax()) {
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				Point p = d.getFrontPoint(getMassPoint().getLocation());
				Mass mass = MassCreater.getMass(p);
				if (mass.WALKABLE && !mass.WATER
						&& MapList.getCreature(p) == null)
					list.add(d);
			}
		}
		return list;
	}

	private void effect() {
		final Base_Enemy e = this;
		startAttack(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Damage.enemyCriticalAttack(e);
			}
		});
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Battle.setCriticalFlag();
		Player.me.normalAttack();
		return true;
	}

	@Override
	protected void init4() {
		changeSize(1);
	}

	// @Override
	// protected boolean specialMoveCheck() {
	// return specialMoveParcent();
	// }
	//
	// @Override
	// protected boolean specialMove() {
	// if (!attack_possible()) {
	// if (!specialMoveParcent()) {
	// return false;
	// }
	// }
	// randomMoveDirection(false);
	// return true;
	// }

	@Override
	public int isKamin() {
		return 100;
	}

	private boolean multiplize() {
		boolean flag_summon_block = 印招.effect();
		boolean flag_success = false;
		int try_number, lv;
		if (LV == 2) {
			try_number = 1;
			lv = 2;
		} else {
			try_number = 2;
			lv = LV - 1;
		}
		for (int i = 0; i < try_number; i++) {
			ArrayList<DIRECTION> list = check();
			if (list.isEmpty()) {
				if (flag_success)
					this.changeSize(-1);
				return flag_success;
			}
			if (!flag_success) {
				if (flag_summon_block) {
					SE.SUMMON.play();
					Message.set(getColoredName(), "は分裂に失敗した！");
				} else
					Message.set(getColoredName(), "は小さく分裂した");
				flag_success = true;
			}
			Point p = list.get(new R().nextInt(list.size())).getFrontPoint(
					getMassPoint().getLocation());
			MainMap.addEffect(new DoronEffect(p, null, true));
			if (flag_summon_block) {
				continue;
			}
			伊吹萃香 e = new 伊吹萃香(p, lv);
			e.enemy_actioned = true;
			e.flag_2nd_move = e.flag_3rd_move = true;
			e.changeSize(this.getLargement() - 1);
			MapList.addEnemy(e);
		}
		this.changeSize(-1);
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (!check().isEmpty()) {
			return multiplize();
		} else if (attack_possible()) {
			effect();
			return true;
		} else {
			return true;
		}
	}

	@Override
	protected boolean specialCheck() {
		if (!check().isEmpty()) {
			return true;
		}
		return (attack_possible() && isSpecialParcent());
	}
}
