package dangeon.model.config.table;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.Base_Map_Random.Difficulty;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.util.R;

public class 黄金の賽銭箱テーブル {

	public static ArrayList<Base_Artifact> getItemList(Point p) {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>(3);
		int num = new R().is(10) ? 1 : 0;
		if (PresentField.isRandomField()
				&& PresentField.get().getBaseMapRandom().getDIFFICULTY()
						.isMoreThan(Difficulty.Normal)) {
			num += 1;
		} else {
			num += 2;
		}
		for (int i = 0; i < num; i++) {
			Base_Artifact a = ItemTable.itemReturn(p, true);
			if (a == null)
				a = new 奇跡のおにぎり(p);
			a.addListComposition(ENCHANT_SIMBOL.金);
			a.changeToGolden();
			list.add(a);
		}
		for (int i = 0; i < 4 - num; i++) {
			Base_Artifact a = new 奇跡のおにぎり(p);
			a.addListComposition(ENCHANT_SIMBOL.金);
			a.changeToGolden();
			list.add(a);
		}
		Collections.shuffle(list, new R());
		return list;
	}

	public static boolean isExist() {
		Base_Map_Random bmr = PresentField.get().getBaseMapRandom();
		if (bmr == null)
			return false;
		int f = MapList.getFloor();
		for (int i : bmr.getGoldFloor()) {
			if (i == f)
				return true;
		}
		return false;
	}

	public static boolean isParcent() {
		return new R().is(PresentField.get().getBaseMapRandom()
				.getGoldSaisenParcent());
	}
}
