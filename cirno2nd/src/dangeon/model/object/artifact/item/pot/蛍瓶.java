package dangeon.model.object.artifact.item.pot;

import java.awt.Point;
import java.util.Iterator;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.bullet.アレ;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.view.detail.MiniMap;
import dangeon.view.detail.View_Sider;

public class 蛍瓶 extends Base_Pot {
	private class ひかり extends アレ {
		private static final long serialVersionUID = 1L;

		public ひかり() {
			super(Player.me, "ひかり");
		}
	}

	private static final long serialVersionUID = 1L;

	public 蛍瓶(Point p) {
		this(p, new R().nextInt(3) + 1);
	}

	public 蛍瓶(Point p, int size) {
		super(p, "", size, STAGE.永夜抄);
	}

	@Override
	public boolean broken() {
		SE.BROKEN.play();
		for (Iterator<Base_Artifact> iterator = LIST.iterator(); iterator
				.hasNext();) {
			Base_Artifact a = iterator.next();
			if (a instanceof アレ)
				continue;
			ItemFall.itemFall(mass_point, a);
		}
		staticCheck();
		Message.set("光が広がった");
		SE.LIGHT_ON.play();
		Player.me.setCondition(CONDITION.蛍, 25);
		return true;
	}

	@Override
	public void endPlayerTurn() {
		if (MAX > LIST.size() && isEnchantedNow()
				&& MassCreater.isPlayerInRoom()) {
			if (new R().is(1)) {
				staticCheck();
				View_Sider.setInformation(getColoredName(), "にひかりが溜まった");
				Message.set(getColoredName(), "にひかりが溜まった");
				SE.LIGHT_ON.play();
				LIST.add(new ひかり());
			}
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	public boolean itemUse() {
		if (LIST.isEmpty()) {
			return emptyAction();
		} else if (MAX == 0) {
			Message.set("中身はもうからっぽだ");
			return false;
		} else {
			Message.set("光が広がった");
			SE.LIGHT_ON.play();
			Player.me.setCondition(CONDITION.蛍, 25);
			MiniMap.openTiles();
			LIST.remove(0);
			setMax(MAX - 1);
			return true;
		}
	}

}
