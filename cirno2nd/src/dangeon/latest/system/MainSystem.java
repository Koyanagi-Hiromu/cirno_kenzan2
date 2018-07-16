package dangeon.latest.system;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import main.Base_System;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;

/**
 * this class is not used...
 * 
 * @author weray
 * 
 */
public class MainSystem extends Base_System {
	private final TurnController TC;

	public MainSystem() {
		super(new SceneHolder_KeyAccepter());
		TC = new TurnController(this);
	}

	private void creatureUpDate() {
		int player = 1;
		List<Base_Enemy> enemies = MapList.getListEnemy();
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>(player
				+ enemies.size());
		list.add(Player.me);
		for (Base_Enemy enemy : enemies) {
			list.add(enemy);
		}
		for (Base_Creature creature : list) {
			creature.upDate();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		KEY.draw(g);
	}

	@Override
	public void upDate() {
		KEY.up_date();
		creatureUpDate();
		TC.upDate();
	}
}
