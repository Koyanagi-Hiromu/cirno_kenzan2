package dangeon.model.object.creature.resistant;

import java.util.Arrays;
import java.util.List;

import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.ミスティア;
import dangeon.model.object.creature.enemy.メディスン;
import dangeon.model.object.creature.enemy.宮古芳香;
import dangeon.model.object.creature.enemy.黒谷ヤマメ;

/**
 * 
 * 耐性or弱点
 * 
 */
public enum ResistWeakPoint {
	毒吸収(メディスン.class), 毒耐性(ミスティア.class, 黒谷ヤマメ.class, 宮古芳香.class), NULL, 炎耐性, 雷耐性;
	private final List<Class<?>> list;

	private int damage = 2;

	private ResistWeakPoint(Class<?>... names) {
		list = Arrays.asList(names);
	}

	public boolean isResist(Base_Creature c) {
		return list.contains(c.getClass());
	}

	public boolean isResistAction(Base_Creature c, int i) {
		if (list.contains(c.getClass())) {
			if (c.resistOrWeakAction(this, i)) {
				return true;
			}
		}
		return false;
	}

	public boolean isWeakPointAction(Base_Creature c, int i) {
		if (list.contains(c.getClass())) {
			return true;
		}
		return false;
	}

	public int weakPointDamage(Base_Creature c, int i) {
		if (list.contains(c.getClass())) {
			return i * damage;
		}
		return i;
	}

}
