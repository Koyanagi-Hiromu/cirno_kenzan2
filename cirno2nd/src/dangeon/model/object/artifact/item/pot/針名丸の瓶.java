package dangeon.model.object.artifact.item.pot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.EnemyFall;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.bullet.アレ;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 針名丸の瓶 extends Base_Pot {
	private class エネミー extends アレ {
		private static final long serialVersionUID = 1L;
		public final Base_Enemy e;

		public エネミー(Base_Enemy e) {
			super(Player.me, e.getColoredName());
			this.e = e;
		}
	}

	private static final long serialVersionUID = 1L;

	boolean flag_non_break;

	public 針名丸の瓶(Point p) {
		this(p, new R().nextInt(3) + 2);
	}

	public 針名丸の瓶(Point p, int size) {
		super(p, "", size, STAGE.輝針城);
	}

	@Override
	public boolean broken() {
		SE.BROKEN.play();
		ArrayList<Point> list = new ArrayList<Point>();
		DIRECTION ds[] = new DIRECTION[9];
		int i = 0;
		ds[0] = DIRECTION.NEUTRAL;
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			ds[++i] = d;
		}
		for (Iterator<Base_Artifact> iterator = LIST.iterator(); iterator
				.hasNext();) {
			Base_Artifact a = iterator.next();
			if (a instanceof エネミー) {
				Base_Enemy e = ((エネミー) a).e;
				Point p = EnemyFall.getPoint(mass_point, list);
				list.add(p);
				e.setMassPoint(mass_point);
				setEnemy(e, p);
			} else {
				ItemFall.itemFall(mass_point, a);
			}
		}
		return true;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	public boolean isNoWasteWithThrowing() {
		return flag_non_break;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		flag_non_break = false;
		if (c instanceof Base_Enemy && !c.isPlayerSide()
				&& !(c instanceof Base_NPC)) {
			Base_Enemy e = ((Base_Enemy) c);
			if (getMaxSize() - LIST.size() <= 0) {
				SE.BIGGER.play();
				Message.set(e.getColoredName(), "は小さくなった");
				e.changeSize(-1);
			} else {
				SE.BIGGER.play();
				e.changeSize(-1);
				if (e.getLargement() < 0) {
					flag_non_break = true;
					staticCheck();
					Message.set(e.getColoredName(), "を捕まえた");
					LIST.add(new エネミー(e));
					MapList.removeEnemy(e);
					return;
				} else {
					Message.set(e.getColoredName(), "は大きくて捕まらなかった");
				}
			}
		}
		super.itemHit(c, c2);
	}

	@Override
	public boolean itemUse() {
		if (LIST.isEmpty()) {
			return emptyAction();
		} else if (MAX == 0) {
			Message.set("中身はもうからっぽだ");
			return false;
		} else {
			Base_Artifact a = LIST.remove(0);
			if (a instanceof エネミー) {
				setMax(MAX - 1);
				Base_Enemy e = ((エネミー) a).e;
				Message.set(e.getColoredName(), "を取り出した");
				SE.SYSTEM_PICKUP.play();
				Point p = EnemyFall.getPoint(Player.me.getDirection()
						.getFrontPoint(Player.me.getMassPoint().getLocation()));
				e.setMassPoint(Player.me.getMassPoint());
				setEnemy(e, p);
			}
			return true;
		}
	}

	private void setEnemy(Base_Enemy e, Point p) {
		MapList.addEnemy(e);
		e.setMassPoint_ParabolaJumpAttack(p);
		e.setCondition(CONDITION.麻痺, 0);
		e.setCondition(CONDITION.イカリ, 0);
		e.load();
	}

}
