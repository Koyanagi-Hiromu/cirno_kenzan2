package cirno_question;

import java.awt.Point;
import java.util.ArrayList;

import cirno_question.maptool.MassCategory;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class QS {
	public static String category_position = "エネミー";
	public static ArrayList<Base_Enemy> ENEMY_LIST = new ArrayList<Base_Enemy>();
	public static ArrayList<String> ENEMY_CHECK_LIST = new ArrayList<String>();
	public static ArrayList<Base_Artifact> CARD_LIST = new ArrayList<Base_Artifact>();
	public static ArrayList<String> CARD_CHECK_LIST = new ArrayList<String>();
	public static SetOnMapObject map_object = new SetOnMapObject(new 大きなおにぎり(
			new Point(0, 0)), false);
	public static MassCategory map_clip_category = null;
	public static SetOnMapObject map_clip_object = new SetOnMapObject(
			new 大きなおにぎり(new Point(0, 0)), true);
	public static SetOnMapObject select_clip_object = new SetOnMapObject(
			new 大きなおにぎり(new Point(0, 0)), true);
	public static boolean graphic_flag = false;
}
