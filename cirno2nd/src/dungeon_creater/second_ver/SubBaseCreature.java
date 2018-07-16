package dungeon_creater.second_ver;

import dangeon.model.object.creature.Base_Creature;

public class SubBaseCreature {
	public final Base_Creature C;
	public int parcent;

	public SubBaseCreature(Base_Creature c, int parcent) {
		C = c;
		this.parcent = parcent;
	}

	public SubBaseCreature(SubBaseCreature c) {
		C = c.C;
		this.parcent = c.parcent;
	}
}
