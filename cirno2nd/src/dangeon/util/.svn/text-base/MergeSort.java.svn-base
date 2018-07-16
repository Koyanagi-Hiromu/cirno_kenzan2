package dangeon.util;

import java.util.ArrayList;
import java.util.List;

import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.Base_Creature;

public class MergeSort {

	public static ArrayList<Base_Artifact> creatureY(
			ArrayList<Base_Artifact> list) {
		Base_MapObject[] arr = mergeSort(list.toArray(new Base_MapObject[0]));
		list.clear();
		for (Base_MapObject o : arr)
			list.add((Base_Artifact) o);
		return list;
	}

	public static List<Base_Creature> creatureY(List<Base_Creature> list) {
		Base_MapObject[] arr = mergeSort(list.toArray(new Base_MapObject[0]));
		list.clear();
		for (Base_MapObject o : arr)
			list.add((Base_Creature) o);
		return list;
	}

	/*
	 * マージ 2つの配列a1[]とa2[]を併合してa[]を作ります。
	 */
	private static void merge(Base_MapObject[] a1, Base_MapObject[] a2,
			Base_MapObject[] a) {
		int i = 0, j = 0;
		while (i < a1.length || j < a2.length) {
			if (j >= a2.length
					|| (i < a1.length && a1[i].getMassPoint().y < a2[j]
							.getMassPoint().y)) {
				a[i + j] = a1[i];
				i++;
			} else {
				a[i + j] = a2[j];
				j++;
			}
		}
	}

	private static Base_MapObject[] mergeSort(Base_MapObject[] a) {
		if (a.length > 1) {
			int m = a.length / 2;
			int n = a.length - m;
			Base_MapObject[] a1 = new Base_MapObject[m];
			Base_MapObject[] a2 = new Base_MapObject[n];
			for (int i = 0; i < m; i++)
				a1[i] = a[i];
			for (int i = 0; i < n; i++)
				a2[i] = a[m + i];
			mergeSort(a1);
			mergeSort(a2);
			merge(a1, a2, a);
		}
		return a;
	}

}
