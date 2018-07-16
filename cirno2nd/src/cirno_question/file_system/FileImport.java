package cirno_question.file_system;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

import main.util.FileReadSupporter;
import cirno_question.SetOnMapObject;
import cirno_question.maptool.MainMap;
import cirno_question.maptool.MapFrame;
import cirno_question.maptool.MapMass;
import cirno_question.maptool.MassCategory;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class FileImport {
	public static void create(File file) {
		try {
			MapMass[][] mass = MainMap.ME.getAllMass();
			BufferedReader bf = new BufferedReader(
					FileReadSupporter.readUTF8(file.getPath()));
			String str;
			int step = 0;
			int[][] ent_exit_x_y = new int[2][2];
			ArrayList<String> list_map_str = new ArrayList<String>();
			ArrayList<Base_MapObject> list_map_obj = new ArrayList<Base_MapObject>();
			while ((str = bf.readLine()) != null) {
				if (!(str.contains("[") && str.contains("]"))) {
					if (step == 0) {
						list_map_str.add(str);
					} else {
						String[] arr_by_TAB = str.split("\t");
						for (int index_by_TAB = 0; index_by_TAB < arr_by_TAB.length; index_by_TAB++) {
							System.out.println("arr_by_TAB : "
									+ arr_by_TAB[index_by_TAB]);
							String[] arr_by_comma = arr_by_TAB[index_by_TAB]
									.split(",");
							if (step == 1) {
								for (int index_by_dot = 0; index_by_dot < arr_by_comma.length; index_by_dot++) {
									ent_exit_x_y[index_by_TAB][index_by_dot] = Integer
											.valueOf(arr_by_comma[index_by_dot]);
								}
							} else if (step == 2) {
								String enm_name = arr_by_comma[0];
								int x = Integer.valueOf(arr_by_comma[1]);
								int y = Integer.valueOf(arr_by_comma[2]);
								int lv = Integer.valueOf(arr_by_comma[3]);
								boolean sleep = Boolean
										.valueOf(arr_by_comma[4]);
								boolean angry = Boolean
										.valueOf(arr_by_comma[5]);
								Base_Enemy be = EnemyTable
										.returnBaseEnemySetPoint(enm_name, lv,
												new Point(x, y));
								if (sleep) {
									be.setConditionList(CONDITION.特殊仮眠, 99999);
								}
								if (angry) {
									be.setConditionList(CONDITION.イカリ, 99999);
								}
								list_map_obj.add(be);
							} else {
								String item_name = arr_by_comma[0];
								Character stage1 = null, stage2 = null;
								if (item_name.startsWith("disc.Disc")) {
									String s = "disc.Disc";
									stage1 = item_name.charAt(s.length());
									stage2 = item_name.charAt(s.length() + 1);
									item_name = s;
								}
								if (step == 3) {
									item_name = "item.".concat(item_name);
								} else {
									item_name = "trap.".concat(item_name);
								}
								int x = Integer.valueOf(arr_by_comma[1]);
								int y = Integer.valueOf(arr_by_comma[2]);
								Base_Artifact a = ItemTable
										.returnBaseArtifactSetPoint(item_name,
												new Point(x, y));
								if (step == 3) {
									boolean curse = Boolean
											.valueOf(arr_by_comma[3]);
									int forge = Integer
											.valueOf(arr_by_comma[4]);
									int staff = Integer
											.valueOf(arr_by_comma[5]);
									int arrow = Integer
											.valueOf(arr_by_comma[6]);
									a.createSpellCard(curse, forge);
									if (a instanceof Base_Pot) {
										((Base_Pot) a).setMax(staff);
									}
									a.staff_rest = staff;
									if (a instanceof Arrow) {
										if (arrow <= 0) {
											a = null;
										} else {
											((Arrow) a).setArrowRest(arrow);
										}
									}
								} else {
									((Base_Trap) a).setJapanese();
								}
								System.out.println("a == " + a);
								if (a != null) {
									list_map_obj.add(a);
								}
							}
						}
					}
				} else {
					step++;
				}
			}
			int y = 0;
			MainMap.ME.allClear();
			for (String s : list_map_str) {
				System.out.println(s);
				for (int x = 0; x < s.length(); x++) {
					System.out.println(x);
					mass[x][y].setMassCategory(MassCategory
							.getMassCategoryMatchPanelName(s
									.substring(x, x + 1)));
				}
				y++;
			}
			for (Base_MapObject o : list_map_obj) {
				int _x = o.getMassPoint().x;
				int _y = o.getMassPoint().y;
				System.out.println(o.getName() + "" + o.getMassPoint());
				mass[_x][_y].setMassObject(new SetOnMapObject(o));
			}
			for (int i = 0; i <= 1; i++) {
				int _x = ent_exit_x_y[i][0];
				int _y = ent_exit_x_y[i][1];
				if (i == 0) {
					mass[_x][_y].setMassObject(new SetOnMapObject(true, false));
				} else {
					mass[_x][_y].setMassObject(new SetOnMapObject(false, true));
				}
			}
			MapFrame.ME.setMapTitle(file.getName());
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
