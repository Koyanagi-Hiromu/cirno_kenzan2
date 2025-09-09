package dangeon.latest.scene.action.menu.first.adventure.wiki_item;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.util.Switch;
import main.util.半角全角コンバーター;

public class ItemWiki extends Item_List {
	private static Base_Artifact getFrom(ArrayList<Base_Artifact> LIST,
			Class<?> clazz) {
		for (Base_Artifact a : LIST) {
			if (a.getClass().equals(clazz))
				return a;
		}
		return null;
	}

	private static ArrayList<Base_Artifact> initList(int i) {
		final ArrayList<Base_Artifact> LIST = new ArrayList<Base_Artifact>();
		String data = Config.getItemData(i);
		String category;
		switch (i) {
		case 0:
			category = "spellcard";
			break;
		case 1:
			category = "grass";
			break;
		case 2:
			category = "ring";
			break;
		case 3:
			category = "scrool";
			break;
		case 4:
			category = "staff";
			break;
		case 5:
			category = "food";
			break;
		case 6:
			category = "arrow";
			break;
		case 7:
			category = "pot";
			break;
		default:
			category = "";
			break;
		}
		Point p = new Point();
		category = "item.".concat(category).concat(".");
		for (String s : new ItemWiki_AllItem().get(i)) {
			if (!s.isEmpty()) {
				Base_Artifact a = ItemTable.returnBaseArtifactSetPoint(
						category.concat(s), p);
				boolean not_used_yet;
				if (Switch.test && Switch.switch_player_no_death)
					not_used_yet = false;
				else
					not_used_yet = true;
				a.setSampleItem(not_used_yet);
				LIST.add(a);
			}
		}
		if (data.isEmpty())
			return LIST;
		for (String s : data.split(",")) {
			if (!s.isEmpty()) {
				Base_Artifact a = getFrom(LIST,
						ItemTable.createClazz(category.concat(s)));
				if (a != null) {
					// 使ったことがあるので unknow = false
					a.setSampleItem(false);
				} else {
					a = ItemTable.returnBaseArtifactSetPoint(
							category.concat(s), p);
					if (a == null)
						continue;
					a.setSampleItem(false);
					a.setFreezeCount(99);
					LIST.add(a);
				}
			}
		}
		Collections.sort(LIST, new Comparator<Base_Artifact>() {
			@Override
			public int compare(Base_Artifact o1, Base_Artifact o2) {
				return o1.getTrueName().compareTo(o2.getTrueName());
			}
		});
		Collections.sort(LIST, new Comparator<Base_Artifact>() {
			@Override
			public int compare(Base_Artifact o1, Base_Artifact o2) {
				return ItemTable.getRankForSort(o1) - ItemTable.getRankForSort(o2);
			}
		});
		return LIST;
	}

	public boolean flag_selected;

	public final Base_View BACK;

	public final int INDEX;

	public ItemWiki(KeyHolder kh, Base_View bv) {
		this(kh, bv, 0);
	}

	//
	// private static String getPercent(int i) {
	// String data = Config.getItemData(i);
	// int now = data.split(",").length - 1;
	// int max = new ItemWiki_AllItem().get(i).size();
	// // switch (i) {
	// // case 0:
	// // // category = "spellcard";
	// // max = 77;
	// // break;
	// // case 1:
	// // // category = "grass";
	// // max = 10;
	// // break;
	// // case 2:
	// // // category = "ring";
	// // max = 10;
	// // break;
	// // case 3:
	// // // category = "scrool";
	// // max = 10;
	// // break;
	// // case 4:
	// // // category = "staff";
	// // max = 10;
	// // break;
	// // case 5:
	// // // category = "food";
	// // max = 10;
	// // break;
	// // case 6:
	// // // category = "arrow";
	// // max = 10;
	// // break;
	// // case 7:
	// // // category = "pot";
	// // max = 10;
	// // break;
	// // default:
	// // // category = "";
	// // max = 1;
	// // break;
	// // }
	// StringBuilder sb = new StringBuilder();
	// percent(sb, now, max);
	// return sb.toString();
	// }

	public ItemWiki(KeyHolder kh, Base_View bv, int i) {
		// super("", null, initList(i));
		super("", null, initList(i));
		BACK = bv;
		INDEX = i;
	}

	@Override
	protected void action_enter(int index) {
		action_cancel();
	}

	@Override
	public Base_Scene getPreviousScene() {
		return new ItemWiki_Command(this);
	}

	@Override
	public String getTop() {
		StringBuilder sb = new StringBuilder();
		int max = LIST.size();
		int count = 0;
		for (Base_Artifact a : LIST) {
			if (a.isCold())
				max--;
			if (!a.flag_unknown && !a.isCold())
				count++;
		}
		percent(sb, count, max);
		setPages(sb);
		return sb.toString();
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		super.initializeContents(list);
		//
		// if (LIST.isEmpty()) {
		// setDeprecatedContents("（何も使用していない）", new Book() {
		// @Override
		// protected void work() {
		// setNextScene(Scene_Action.getMe());
		// }
		// });
		// } else {
		// for (Base_Artifact a : LIST) {
		// setContents(
		// a.getTrueName(),
		// a.getListItemExplan(new ArrayList<String>()).toArray(
		// new String[0]), new Book() {
		// @Override
		// protected void work() {
		// setNextScene(Scene_Action.getMe());
		// }
		// });
		// }
		// }
	}

	private void percent(StringBuilder sb, int now, int max) {
		int perc;
		if (max == 0)
			perc = 0;
		else
			perc = now * 100 / max;
		if (perc < 10) {
			sb.append(Color.PINK);
		} else if (perc < 40) {
			sb.append(Color.ORANGE);
		} else if (perc < 70) {
			sb.append(Color.GREEN);
		} else if (perc < 80) {
			sb.append(Color.BLUE);
		} else if (perc < 90) {
			sb.append(Color.CYAN);
		} else if (perc < 100) {
			sb.append(Color.YELLOW);
		} else {
			sb.append(Color.RED);
		}
		sb.append("【");
		if (perc < 100) {
			sb.append("　");
		}
		if (perc < 10) {
			sb.append("　");
		}
		sb.append(半角全角コンバーター.半角To全角数字(perc));
		sb.append("％】");
		sb.append(Color.WHITE);
	}

}