package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.awt.Color;

import dangeon.model.map.field.random.Base_Map_Random.Difficulty;

public class ClassMedalWrapper extends MedalWrapper
{
	public ClassMedalWrapper(Medal m) {
		super(m);
	}

	@Override
	public String toString() {
		StringBuilder flags = new StringBuilder();
		append(flags, Difficulty.Easy, medal.isLevel(1));
		append(flags, Difficulty.Normal, medal.isLevel(2));
		append(flags, Difficulty.Hard, medal.isLevel(3));
		flags.append(" ");
		flags.append(medal);
		return flags.toString();
	}
	
	void append(StringBuilder sb, Difficulty diff, boolean cleared)
	{
//			sb.append("[");
		if (cleared)
		{
			sb.append(diff.COLOR);
			sb.append("■");
		}
		else
		{	
			sb.append(Color.gray);
			sb.append("□");
		}
//			sb.append(diff.name().substring(0, 1));
		sb.append(Color.WHITE);
//			sb.append("]");
	}
}