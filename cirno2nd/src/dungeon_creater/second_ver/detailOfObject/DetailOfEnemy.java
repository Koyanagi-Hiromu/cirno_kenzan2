package dungeon_creater.second_ver.detailOfObject;

import java.util.ArrayList;

import dangeon.model.object.creature.Base_Creature;

public class DetailOfEnemy {
	static ArrayList<Base_Creature> enemy_list = new ArrayList<Base_Creature>();

	public static ArrayList<Base_Creature> getList() {
		return enemy_list;
	}
}
