package dangeon.model.object.artifact.item.pot;

import java.awt.Point;
import java.util.Iterator;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.bullet.アレ;
import dangeon.model.object.creature.enemy.わかさぎ姫;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 水瓶 extends Base_Pot {
	private class 水鉄砲 extends アレ {
		private static final long serialVersionUID = 1L;

		private final String ice = "こおり";

		public 水鉄砲() {
			super("みず");
		}

		@Override
		public String getName() {
			if (isFrozen())
				return ice;
			else
				return name;
		}

	}

	private static final long serialVersionUID = 1L;

	public 水瓶(Point p) {
		this(p, new R().nextInt(5) / 2 + 2);
	}

	public 水瓶(Point p, int size) {
		super(p, "", size, STAGE.輝針城);
	}

	@Override
	public boolean broken() {
		SE.BROKEN.play();
		for (Iterator<Base_Artifact> iterator = LIST.iterator(); iterator
				.hasNext();) {
			Base_Artifact a = iterator.next();
			if (a instanceof 水鉄砲)
				continue;
			ItemFall.itemFall(mass_point, a);
		}
		return true;
	}

	private boolean createWater() {
		DIRECTION d = Player.me.getDirection();
		Point p = Player.me.getMassPoint().getLocation();
		p.translate(d.X, d.Y);
		Mass m = MassCreater.getMass(p);
		if (!m.WALKABLE) {
			Message.set("そこには作れない");
			return false;
		} else {
			Message.set("目の前に水場を作った");
			Player.me.setStandEffect(わかさぎ姫.class, d);
			MainMap.addEffect(new DoronEffect(p, null));
			m.setWater(true);
			if (MapList.getCreature(p) != null) {
				m.WATER_LEEF = false;
			}
			MassCreater.retakeMassSet();
			return true;
		}
	}

	@Override
	public void freezeCountPlus(int count) {
		super.freezeCountPlus(count);
		iceCheck();
	}

	@Override
	public void freezeCountReset() {
		super.freezeCountReset();
		iceCheck();
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	private void iceCheck() {
		for (Iterator<Base_Artifact> iterator = LIST.iterator(); iterator
				.hasNext();) {
			Base_Artifact a = iterator.next();
			if (a instanceof 水鉄砲) {
				a.setFreezeCount(item_freeze_count);
			}
		}
	}

	@Override
	public boolean itemUse() {
		if (LIST.isEmpty()) {
			return emptyAction();
		} else {
			for (Iterator<Base_Artifact> iterator = LIST.iterator(); iterator
					.hasNext();) {
				Base_Artifact a = iterator.next();
				if (a instanceof 水鉄砲 && !a.isFrozen()) {
					boolean success = createWater();
					if (success) {
						iterator.remove();
					}
					return success;
				}
			}
			Message.set(getColoredName(), "の中にみずが入っていない");
			return false;
		}
	}

	@Override
	public boolean waterAction() {
		if (!isFrozen()) {
			int size = LIST.size();
			for (int i = 0; i < MAX - size; i++) {
				LIST.add(new 水鉄砲());
				staticCheck();
			}
		}
		iceCheck();
		return true;
	}
}
