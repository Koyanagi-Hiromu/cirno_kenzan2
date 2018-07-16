package dangeon.model.map.field.random.second;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.config.table.ItemTable;
import dangeon.model.config.table.TrapTable;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class てゐの試験ダンジョン extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final てゐの試験ダンジョン ME = new てゐの試験ダンジョン();

	public static void createTable() {
		StringBuffer sb = new StringBuffer();
		sb.append(EnemyTable.randomTableCreate(10));
		sb.append(ItemTable.randomTableCreate());
		sb.append(TrapTable.randomTableCreate());
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(
							"res/table/てゐの試験ダンジョン.csv"), "UTF-8")));
			pw.print(sb.toString());
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected てゐの試験ダンジョン() {
		super(Difficulty.Lunatic, 0, null);
	}

	@Override
	public void createFirstMap() {
		てゐの試験ダンジョン.createTable();
		super.createFirstMap();
	}

	@Override
	public int defaultItemNumber() {
		return R.ran(5) + 6;
	}

	@Override
	public ArrayList<Base_Artifact> firstItems() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		list.add(new 大きなおにぎり(Player.me.getMassPoint()));
		return list;
	}

	@Override
	public BGM getBGM() {
		return BGM.kanpyo_ch_bbcc;
	}

	@Override
	public String getClassName() {
		return "てゐの試験ダンジョン";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("特殊ダンジョン");
		list.add("潜る度に全てのテーブルがランダムに決定されるぞ");
		list.add("100回でも、1000回でも遊べるダンジョンだ");
		list.add("倒れても挫けないこと");
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] {};
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.keine_no_saishuumondai;
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f < 9) {
			return Image_MapTip.木造校舎;
		} else if (f < 19) {
			return Image_MapTip.紅魔館;
		} else if (f < 31) {
			return Image_MapTip.マヨヒガ;
		} else if (f < 41) {
			return Image_MapTip.木造校舎;
		} else if (f < 58) {
			return Image_MapTip.永遠亭;
		} else if (f < 70) {
			return Image_MapTip.命蓮寺;
		} else if (f < 87) {
			return Image_MapTip.地霊殿_水;
		} else {
			return Image_MapTip.地霊殿;
		}
	}

	@Override
	public int getMaxFloor() {
		return 10;
	}

	@Override
	public int getSaisenParcent() {
		return 20;
	}

	@Override
	public int getTrapDefaultValue() {
		return getTrapDefaultValue(MapList.getFloor());
	}

	@Override
	public boolean isItemDrop() {
		return false;
	}

}
