package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class 橙 extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private boolean flag_effect;

	public 橙(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (flag_effect || !attack_possible()) {
			flag_effect = false;
			return false;
		}
		DIRECTION d = getDirection();
		Point p = getMassPoint();
		int x = p.x + d.X * 2;
		int y = p.y + d.Y * 2;
		// if (MapList.isCreature(x, y))
		// return false;
		return MassCreater.getMass(x, y).WALKABLE;
	}

	private void effect() {
		Message.set(getColoredName(), "は", Player.me.getColoredName(), "を飛び越えた");
		SE.WARP.play();
		Point p = getMassPoint();
		DIRECTION d = getDirection();
		setMassPoint_ParabolaJumpAttack(new Point(p.x + d.X * 2, p.y + d.Y * 2));
		if (LV == 2) {
			if (!conditionCheck(CONDITION.倍速))
				set2timesWalk();
		} else if (LV > 2)
			set3timesWalk();
		flag_effect = true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Room room = MassCreater.getRoom(Player.me.getMassPoint().x,
				Player.me.getMassPoint().y);
		if (room == null) {
			Message.set("通路ではジャンプできない");
			return false;
		}
		ArrayList<Point> list = room.getListNoCreatureMassPointInRoom();
		if (list.isEmpty()) {
			Message.set("ワープ出来るマスが無かった");
			return false;
		}
		SE.WARP.play();
		Player.me.setMassPoint_ParabolaJumpAttack(list.get(new R().nextInt(list
				.size())));
		Message.set("ワープした");
		Player.me.setCondition(CONDITION.倍速, 0);
		return true;
	}

	@Override
	public void normalAttack(boolean p_or_e) {
		flag_effect = true;
		super.normalAttack(p_or_e);
	}

	@Override
	protected boolean specialAttack() {
		if (!check()) {
			return false;
		}
		effect();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!isSpecialParcent()) {
			return false;
		}
		if (check()) {
			return true;
		}
		return false;
	}

}
