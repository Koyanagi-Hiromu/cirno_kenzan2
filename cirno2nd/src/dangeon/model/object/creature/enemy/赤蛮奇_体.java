package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印招;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 赤蛮奇_体 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	boolean head_parge = false;

	public 赤蛮奇_体(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		return false;
	}

	@Override
	protected void enemyBreakAction() {
	}

	@Override
	protected boolean isRange() {
		if (!MassCreater.isPlayerInRoom()) {
			return false;
		}
		switch (LV) {
		case 1:
			return super.isRange();
		case 2:
			return MassCreater.isPlayerInTheSameRoom(getMassPoint());
		case 3:
			return true;
		case 4:
			return true;
		default:
			return false;
		}
	}

	private boolean parge() {
		if (head_parge) {
			return false;
		}
		if (MapList.isEnemyMax()) {
			return false;
		}
		SE.STATUS_SEAL.play();
		setCondition(CONDITION.封印, 0);
		if (印招.effect()) {
			SE.SUMMON.play();
			Message.set(getColoredName(), "はうまく頭を分離できなかった！");
			return true;
		}
		Room r = MassCreater.getRoom(Player.me.getMassPoint());
		Point p = MassCreater.getMonsterPoint(r);
		int try_number = 1;
		for (int i = 0; i < try_number; i++) {
			if (p == Mass.nullpo.null_point) {
				return head_parge;
			}
			if (!head_parge) {
				head_parge = true;
				IM = CHARA_IMAGE.赤蛮奇;
				Message.set(getColoredName(), "は頭を分離した");
			}
			final 赤蛮奇_頭 head = new 赤蛮奇_頭(p, LV);
			summonHead(head);
		}
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (!isRange()) {
			return false;
		}
		return parge();
	}

	@Override
	protected boolean specialCheck() {
		if (head_parge) {
			return false;
		}
		if (MapList.isEnemyMax()) {
			return false;
		}
		if (isSpecialParcent()) {
			if (isRange()) {
				return true;
			}
		}
		return false;
	}

	private void summonHead(final 赤蛮奇_頭 head) {
		MapList.addEnemy(head);
		head.flag_2nd_move = head.flag_3rd_move = head.enemy_actioned = true;
		MainMap.addEffect(new DoronEffect(head.getMassPoint(), null, true));
	}

}
