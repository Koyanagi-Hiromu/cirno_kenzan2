package dungeon_creater.second_ver;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class SetObject {
	static SubBaseCreature current_base_creature;
	public static Point mouse_point = new Point(0, 0);
	private static SubBaseCreature[] enemy_table = new SubBaseCreature[10];
	public static ArrayList<SubBaseCreature> enemy_list = new ArrayList<SubBaseCreature>();
	public static ArrayList<SubBaseCreature> base_enemy_list = new ArrayList<SubBaseCreature>();
	private static int max_size = 10;
	private static SubBaseCreature[] clip_enemy_table = new SubBaseCreature[10];
	static boolean flag_clip = false;

	static boolean drag_flag = false;

	static SubBaseCreature drag_creature = null;

	static int one_character_space = EnemyTableGraphicsPanel.ME.one_character_space;

	/**
	 * 数値入力に変数を使っていない場所が有るため、変更時注意
	 */
	static int parcent_button_size = 20;

	static int parcent_start_x = EnemyTableGraphicsPanel.ME.table_x + 300;

	static int parcent_start_y = EnemyTableGraphicsPanel.ME.table_start_y
			+ one_character_space + (one_character_space / 2)
			- (parcent_button_size / 2);

	static int parcent_plus_x = parcent_start_x + 70;

	static int parcent_plus_y = parcent_start_y;

	private static void addParcent(int y, int add) {
		if (enemy_table[y] != null) {
			if (enemy_table[y].parcent + add < 1) {
				return;
			}
			enemy_table[y].parcent += add;
		}
	}

	public static void addParcentButton() {
		int y;
		int yy = 0;
		if (addParcentButtonPoint(new Point(parcent_start_x, parcent_start_y))) {
			y = mouse_point.y - parcent_start_y;
			yy = y / one_character_space;
			addParcent(yy, -1);
		}
		if (addParcentButtonPoint(new Point(parcent_plus_x, parcent_start_y))) {
			y = mouse_point.y - parcent_start_y;
			yy = y / one_character_space;
			addParcent(yy, 1);
		}
	}

	private static boolean addParcentButtonPoint(Point _p) {
		Point p = mouse_point;
		int y;
		int yy;
		if (p.x >= _p.x && p.x <= _p.x + parcent_button_size) {
			y = p.y - _p.y;
			if (y < 0) {
				return false;
			}
			yy = y / one_character_space;

			if (yy >= max_size) {
				return false;
			}
			if (y >= one_character_space * yy
					&& y <= parcent_button_size + (one_character_space * yy)) {
				if (y > 0 || y <= max_size) {
					return true;
				} else {
				}
			} else {
			}
		} else {
		}
		return false;
	}

	public static void clip() {
		if (!flag_clip) {
			setClipEnemyTable();
		} else {
			putClipEnemyTable();
		}

	}

	public static void draw(Graphics2D g) {
		drawImageCurrentBaseCreature(g);
		drawImageTableBaseCreature(g);
		drawExplainTableBaseCreature(g);
		drawExplainBox(g);
		drawParcentButton(g);
	}

	private static void drawExplainBox(Graphics2D g) {
		int start_x = 3;
		int start_y = 13;
		int new_line = 14;
		String lv_name = "";
		Point p = EnemyTableGraphicsPanel.ME.getPointObjectExplainBox();
		SubBaseCreature c = null;
		if (current_base_creature != null) {
			c = current_base_creature;
		} else {
			c = EnemyTableGraphicsPanel.ME.getImageOfPoint(mouse_point);
		}
		if (c == null) {
			return;
		}
		StringBuffer status = new StringBuffer("");
		StringBuffer condition = new StringBuffer("");
		StringBuffer special = new StringBuffer("");
		lv_name = "LV".concat(String.valueOf(c.C.getLV())).concat(" ")
				.concat(c.C.getName());
		status.append("HP:");
		status.append(c.C.getHP());
		status.append(" STR:");
		status.append(c.C.getSTR());
		status.append(" DEF:");
		status.append(c.C.getDEF());
		status.append(" EXP:");
		status.append(c.C.getENEMY_EXP());
		for (CONDITION con : c.C.getConditionList()) {
			condition.append(con.name());
			condition.append(" ");
		}
		special.append(((Base_Enemy) c.C).getExplanHoldAction());
		g.drawString(lv_name, p.x + start_x, p.y + start_y);
		g.drawString(status.toString(), p.x + start_x, p.y + start_y + new_line);
		g.drawString(condition.toString(), p.x + start_x, p.y + start_y
				+ new_line * 2);
		g.drawString(special.toString(), p.x + start_x, p.y + start_y
				+ new_line * 3);
	}

	private static void drawExplainTableBaseCreature(Graphics2D g) {
		int y = 0;
		int start_x = EnemyTableGraphicsPanel.ME.one_character_space + 3;
		int start_y = 13;
		int new_line = 14;
		String lv_name = "";
		for (SubBaseCreature c : enemy_table) {
			y++;
			if (c == null) {
				continue;
			}
			Point p = EnemyTableGraphicsPanel.ME.getPointEnemyTable(y);
			StringBuffer status = new StringBuffer("");
			StringBuffer condition = new StringBuffer("");
			lv_name = "LV".concat(String.valueOf(c.C.getLV())).concat(" ")
					.concat(c.C.getName());
			status.append("HP:");
			status.append(c.C.getHP());
			status.append(" STR:");
			status.append(c.C.getSTR());
			status.append(" DEF:");
			status.append(c.C.getDEF());
			status.append(" EXP:");
			status.append(c.C.getENEMY_EXP());
			for (CONDITION con : c.C.getConditionList()) {
				condition.append(con.name());
				condition.append(" ");
			}
			g.drawString(lv_name, p.x + start_x, p.y + start_y);
			g.drawString(status.toString(), p.x + start_x, p.y + start_y
					+ new_line);
			g.drawString(condition.toString(), p.x + start_x, p.y + start_y
					+ new_line * 2);
		}
	}

	private static void drawImageCurrentBaseCreature(Graphics2D g) {
		BufferedImage bimg = null;
		if (SetObject.getCurrentObject() != null) {
			bimg = EnemyTableGraphicsPanel.ME
					.reduction(EnemyTableGraphicsPanel.ME
							.cutImageMainCharaSize(current_base_creature.C
									.getWalkDot()));
		} else if (SetObject.getDragCreature() != null) {
			bimg = EnemyTableGraphicsPanel.ME
					.reduction(EnemyTableGraphicsPanel.ME
							.cutImageMainCharaSize(drag_creature.C.getWalkDot()));
		}
		if (bimg != null) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.8F));
			g.drawImage(bimg, mouse_point.x, mouse_point.y,
					TableMainFrame.MAIN.main_panel);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					1.0F));
		}
	}

	private static void drawImageTableBaseCreature(Graphics2D g) {
		BufferedImage bimg = null;
		int y = 0;
		for (SubBaseCreature c : enemy_table) {
			y++;
			if (c == null) {
				continue;
			}
			Point p = EnemyTableGraphicsPanel.ME.getPointEnemyTable(y);
			bimg = EnemyTableGraphicsPanel.ME
					.reduction(EnemyTableGraphicsPanel.ME
							.cutImageMainCharaSize(c.C.getWalkDot()));
			g.drawImage(bimg, p.x, p.y, null);
		}
	}

	private static void drawParcentButton(Graphics2D g) {
		int counter = 0;
		String parcent_s = "0";
		for (SubBaseCreature c : enemy_table) {
			parcent_s = "0";
			if (c != null) {
				parcent_s = String.valueOf(c.parcent);
			}
			g.drawString(parcent_s, parcent_start_x + parcent_button_size + 22,
					parcent_start_y
							+ EnemyTableGraphicsPanel.ME.one_character_space
							* counter + 15);
			g.drawRect(parcent_start_x, parcent_start_y
					+ EnemyTableGraphicsPanel.ME.one_character_space * counter,
					parcent_button_size, parcent_button_size);
			g.drawRect(parcent_plus_x, parcent_start_y
					+ EnemyTableGraphicsPanel.ME.one_character_space * counter,
					parcent_button_size, parcent_button_size);
			counter++;
		}
	}

	public static SubBaseCreature getCurrentObject() {
		return current_base_creature;
	}

	private static SubBaseCreature getDragCreature() {
		if (drag_flag) {
			return drag_creature;
		}
		return null;
	}

	public static SubBaseCreature[] getEnemyList() {
		return enemy_table;
	}

	private static void putClipEnemyTable() {
		enemy_table = clip_enemy_table;
		clip_enemy_table = new SubBaseCreature[10];
		flag_clip = false;
		setListMapEnemyTable();
	}

	public static void putDragEnemyTable(Point p) {
		if (!drag_flag) {
			return;
		}
		drag_flag = false;
		int y = EnemyTableGraphicsPanel.ME.getYEnemyTableFromPoint(p);
		if (y != -1) {
			setEnemyTable(drag_creature, y);
		}
		drag_creature = null;
	}

	public static void reset() {
		current_base_creature = null;
	}

	private static void resetEnemyTable() {
		enemy_table = new SubBaseCreature[max_size];
	}

	private static void setClipEnemyTable() {
		clip_enemy_table = enemy_table;
		flag_clip = true;
	}

	public static void setCurrentBaseCreature(SubBaseCreature c) {
		current_base_creature = c;
	}

	public static void setCurrentObject(Point p) {
		SubBaseCreature c = EnemyTableGraphicsPanel.ME.getImageOfPoint(p);
		if (c == null) {
			return;
		}
		switch (TableScene.CURRENT) {
		case ENEMY_TABLE:
			setCurrentBaseCreature(c);
			break;
		}
	}

	public static void setDragFlag(Point p) {
		int y = EnemyTableGraphicsPanel.ME.getYEnemyTableFromPoint(p);
		if (y == -1) {
			return;
		}
		drag_creature = enemy_table[y];
		if (drag_creature == null) {
			return;
		}
		if (current_base_creature != null) {
			return;
		}
		drag_flag = true;
	}

	public static void setEnemyList(int i) {
		setEnemyTable(current_base_creature, i);
	}

	public static void setEnemyListDungeonLevel() {
		ArrayList<SubBaseCreature> _list = FileControl.map
				.get(DungeonLevelControl.dungeon_level);
		resetEnemyTable();
		int count = 0;
		for (SubBaseCreature sc : _list) {
			setEnemyTable(new SubBaseCreature(sc), count);
			count++;
		}
	}

	private static void setEnemyTable(SubBaseCreature c, int y) {
		int count_index = 0;
		boolean flag_remove = false;
		if (c == null) {
			enemy_table[y] = null;
			setListMapEnemyTable();
			return;
		}
		for (SubBaseCreature _c : enemy_table) {
			count_index++;
			if (_c == null) {
				continue;
			}
			if (_c.C.getName().matches(c.C.getName())) {
				flag_remove = true;
				break;
			}
		}
		if (flag_remove) {
			enemy_table[count_index - 1] = null;
		}
		enemy_table[y] = c;
		setListMapEnemyTable();
	}

	/**
	 * 書き込み、読み込み用ファイルに現在のエネミーテーブルを反映する
	 */
	private static void setListMapEnemyTable() {
		ArrayList<SubBaseCreature> _list = new ArrayList<SubBaseCreature>();
		_list.clear();
		for (SubBaseCreature c : enemy_table) {
			if (c != null) {
				_list.add(c);
			}
		}
		FileControl.setMap(DungeonLevelControl.dungeon_level, _list);
	}

}
