package dangeon.view.detail.name;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import dangeon.model.object.creature.Base_Creature;

public class MainMap_Name {
	public static boolean flag_on;
	public static ArrayList<Base_Creature> temp = new ArrayList<Base_Creature>();
	public static ArrayList<NamePlate> list = new ArrayList<NamePlate>();

	public static void drawName(Base_Creature c) {
		temp.add(c);
	}

	public static void drawName(Graphics2D g) {
		init(g);
		for (NamePlate np : list)
			np.drawLine(g);
		for (NamePlate np : list)
			np.draw(g);
		upDate();
	}

	private static void init(Graphics2D g) {
		for (Iterator<NamePlate> iterator = list.iterator(); iterator.hasNext();) {
			if (!temp.remove(iterator.next().C)) {
				iterator.remove();
			}
		}
		for (Base_Creature c : temp) {
			list.add(new NamePlate(g, c, 11));
		}
		temp.clear();
	}

	public static void set(boolean b) {
		if (!b) {
			list.clear();
		}
		flag_on = b;
	}

	private static void upDate() {
		for (NamePlate passive : list) {
			passive.flag = false;
			int sum_right = 0;
			int sum_top = 0;
			int X = passive.screen.x;
			int Y = passive.screen.y;
			int W = passive.W + 2;
			int H = passive.H + 2;
			for (NamePlate checked : list) {
				if (passive == checked)
					continue;
				int left = checked.screen.x;
				int top = checked.screen.y;
				int right = left + checked.W;
				int bottom = top + checked.H;
				left = Math.max(left, X);
				top = Math.max(top, Y);
				right = Math.min(right, X + W);
				bottom = Math.min(bottom, Y + H);
				if (left < X + W && right > X) {
					if (top < Y + H && bottom > Y) {
						if (top > Y && bottom < Y + H) {
							// ありえないので無視
						} else if (top < Y && bottom < Y + H) {
							sum_top -= bottom - top;
						} else if (top > Y && bottom > Y + H) {
							sum_top += bottom - top;
						}
					}
				}
				if (top < Y + H && bottom > Y) {
					if (left < X + W && right > X) {
						int dif = right - left + 1;
						if (left > X && right < X + W) {
							// ありえないので無視
						} else if (left <= X && right <= X + W) {
							sum_right += dif;
							passive.flag = true;
						} else if (left >= X && right >= X + W) {
							sum_right -= dif;
							passive.flag = true;
						}
					}
				}
			}
			passive.upDate(sum_right, sum_top);
		}
	}

}
