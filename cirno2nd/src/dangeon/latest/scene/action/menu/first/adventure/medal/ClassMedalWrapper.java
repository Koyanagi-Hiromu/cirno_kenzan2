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
		append(flags, Difficulty.Easy, medal.getDightNumber(1));
		append(flags, Difficulty.Normal, medal.getDightNumber(2));
		append(flags, Difficulty.Hard, medal.getDightNumber(3));
		flags.append(" ");
		flags.append(medal);
		return flags.toString();
	}
	
	void append(StringBuilder sb, Difficulty diff, int number)
	{
//			sb.append("[");
		if (number == 9)
		{
			sb.append(diff.COLOR);
			sb.append("★");
		}
		else if (number == 8)
		{
			sb.append(diff.COLOR);
			sb.append("■");
		}
		else if (number != 0)
		{
			sb.append(Color.lightGray);
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