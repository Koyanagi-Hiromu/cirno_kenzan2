package dangeon.model.config.table;

import dangeon.model.object.creature.Base_Creature;

public class EnemyDetail {
	public static EnemyDetail matches(String str, int lv) {
		for (EnemyDetail e : EnemyTable.list_enemy_table) {
			if (e.CLASS.equals(str) && e.LV == lv) {
				return e;
			}
		}
		return null;
	}

	public final int LV;
	public final int PARCENT;

	public final String CLASS;

	public EnemyDetail(int lv, int parcent, String class_name) {

		// System.out.println(lv + parcent + class_name);
		LV = lv;
		PARCENT = parcent;
		CLASS = class_name;
	}

	public Base_Creature getBaseCreature() {
		return EnemyTable.returnBaseEnemy(CLASS, LV);
	}
}
