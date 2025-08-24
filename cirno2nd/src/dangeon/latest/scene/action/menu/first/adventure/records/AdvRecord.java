package dangeon.latest.scene.action.menu.first.adventure.records;

import java.awt.Color;
import java.util.ArrayList;

import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.view.result.Scene_Result_Info;
import dangeon.latest.system.KeyHolder;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.ミラクルクエスト;
import dangeon.model.map.field.random.二撃必殺;
import dangeon.model.map.field.random.救出大作戦;
import dangeon.model.map.field.random.緋想の地下;
import dangeon.model.map.field.random.逆ヶ島;
import dangeon.model.map.field.random.運命のワルツ;
import dangeon.model.map.field.random.風穴旅行;
import dangeon.model.map.field.random.second.七曜クエスト;
import dangeon.model.map.field.random.second.五色の神霊廟;
import dangeon.model.map.field.random.second.賢将裏の洞窟;
import dangeon.model.map.field.random.second.魔理沙のトラップタワー;
import dangeon.model.object.creature.player.save.ResultSaveLoad;
import main.constant.PropertySupporter;
import main.res.SE;

public class AdvRecord extends Base_Scene_Menu {
	public final Base_View BACK;
	public final Base_Scene PREVIOUS_SCENE;
	public final int difficulty, INDEX, X, Y;
	public final Base_Map_Random[] ARR = { new 救出大作戦(), new 逆ヶ島(), new 緋想の地下(),
			new 二撃必殺(), new 五色の神霊廟(), new ミラクルクエスト(), new 風穴旅行(), new 賢将裏の洞窟(),
			new 魔理沙のトラップタワー(), new 運命のワルツ(), new 七曜クエスト()
	// ,new 七曜クエスト()
	};

	public boolean flag_selected;

	public AdvRecord(KeyHolder kh, Base_View bv, Base_Scene base_Scene) {
		this(kh, bv, 0, 0, 0, 0, base_Scene);
	}

	public AdvRecord(KeyHolder kh, Base_View bv, int i, int fate, int x, int y,
			Base_Scene prev) {
		super(10, 3, kh, new AdvRecord_View());
		BACK = bv;
		INDEX = i;
		X = x;
		Y = y;
		difficulty = ARR[i].getDIFFICULTY(fate, true).index;
		PREVIOUS_SCENE = prev;
	}

	@Override
	protected void action_cancel() {
		SE.SYSTEM_CANCEL.play();
		flag_selected = false;
		setNextScene(new AdvRecord_Command(this));
	}

	@Override
	protected void action_else() {
	}

	private String getName(int c, String data) {
		StringBuilder sb = new StringBuilder();
		String[] arr = data.split(",");
		setColor(sb, arr);
		sb.append("【");
		if (c < 10)
			sb.append(" ");
		sb.append(c);
		sb.append(Color.WHITE);
		sb.append("位");
		setColor(sb, arr);
		if (arr[1].matches("1")) {
			sb.append("CLEAR");
		} else {
			int floor = Integer.valueOf(arr[0]);
			if (floor < 100)
				sb.append(" ");
			if (floor < 10)
				sb.append(" ");
			sb.append(arr[0]);
			sb.append("階");
		}
		sb.append("】");
		return sb.toString();
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		final AdvRecord ME = this;
		PropertySupporter ps;
		ps = ResultSaveLoad.getPropertySupporter(ARR[INDEX].getClassName(),
				difficulty);
		for (int j = 0; j < ROW; j++) {
			for (int i = 0; i < COL; i++) {
				int c = j * COL + i + 1;
				Object o = ps.getProperty_Nature("r" + (c));
				String data = null;
				if (o != null)
					data = o.toString();
				if (ps.load_failed || data == null || data.matches("-1")
						|| !data.contains(",")) {
					StringBuilder sb = new StringBuilder();
					sb.append("【");
					if (c < 10)
						sb.append(" ");
					sb.append(c);
					sb.append("位");
					sb.append("---階】");
					setDeprecatedContents(sb.toString(), "<NO_DATA>",
							new Book() {

								@Override
								protected void work() {
								}
							});
				} else {
					final int NUM = j * COL + i + 1;
					setContents(getName(c, data), new Book() {
						@Override
						protected void work() {
							new Scene_Result_Info(ARR[INDEX].getClassName(),
									NUM, difficulty, ME);
						}
					});
				}
			}
		}

	}

	private void setColor(StringBuilder sb, String[] arr) {
		if (!arr[4].matches("2")) {
			sb.append(Color.MAGENTA);
		} else if (arr[2].matches("1") || arr[3].matches("1")) {
			sb.append(Color.CYAN);
		} else {
			sb.append(Color.BLUE);
		}

	}

	@Override
	protected boolean vertical_sort() {
		return true;
	}

}