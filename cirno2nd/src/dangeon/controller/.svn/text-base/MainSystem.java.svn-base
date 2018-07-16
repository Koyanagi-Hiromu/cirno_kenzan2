package dangeon.controller;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.HashMap;

import main.Base_System;
import main.util.BlackOut;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.SceneHolder_KeyAccepter;
import dangeon.model.object.creature.player.Player;

public class MainSystem extends Base_System {

	private static final HashMap<Object, Collection<?>> demanded_hashmap_for_wait_until_removed = new HashMap<Object, Collection<?>>();

	public static void addDemandForWaitUntilRemoved(Object o,
			Collection<?> collection) {
		demanded_hashmap_for_wait_until_removed.put(o, collection);
	}

	public static boolean isDemandForWaitExist() {
		for (Object o : demanded_hashmap_for_wait_until_removed.keySet()) {
			if (demanded_hashmap_for_wait_until_removed.get(o).contains(o)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isKeyAccept() {
		if (!BlackOut.isEmpty()) {
			return false;
		}
		if (DangeonScene.DANGEON.isPresentScene()) {
			if (TurnSystemController.isEnemyTurn()) {
				return false;
			} else if (!TaskOnMapObject.isThrowTaskEmpty()) {
				return false;
			} else if (Message.isDemandToWaitAnySystem()) {
				return false;
			} else if (Message.isDemandToWaitPushingAnyKey()) {
				return false;
			} else if (Player.me.isAnimating()) {
				return false;
				// } else if (Player.me.getAnimation() != null) {
				// return false;
				// } else if (Player.me.getSecondAnimation() != null) {
				// return false;
			} else if (isDemandForWaitExist()) {
				return false;
			}
		} else if (DangeonScene.STAIRS.isPresentScene()) {
			return false;
		}
		return true;
	}

	public MainSystem() {
		super(new SceneHolder_KeyAccepter());
	}

	@Override
	public void draw(Graphics2D g) {
		KEY.draw(g);
	}

	@Override
	public void upDate() {
		KEY.up_date();
	}

}
