package cirno_question.file_system;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import cirno_question.SetOnMapObject;
import cirno_question.maptool.MainMap;
import cirno_question.maptool.MapMass;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.disc.Base_Disc;
import dangeon.model.object.artifact.item.disc.Disc;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;

public class FileExport {

	public static void mapExport(BufferedWriter filewriter) {
		StringBuffer map = new StringBuffer();
		String enter = "";
		String exit = "";
		ArrayList<String> ENEMY = new ArrayList<String>();
		ArrayList<String> ITEM = new ArrayList<String>();
		ArrayList<String> TRAP = new ArrayList<String>();
		MapMass mm;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {
				mm = MainMap.ME.getMass(j, i);
				map.append(mm.getMassCategory().PANEL_ONE_NAME);
				// if (mm.getMassCategory() == MassCategory.WALKABLE) {
				// map.append("壁");
				// } else if (mm.getMassCategory() == MassCategory.ROAD) {
				// map.append("☆");
				// } else {
				// map.append("　");
				// }
				if (j == 39) {
					map.append("\r\n");
				}
				for (SetOnMapObject mo : mm.getListMapObject()) {
					if (mo.getEnemy() != null) {
						StringBuffer enemy = new StringBuffer();
						enemy.append(mo.getEnemy().getClass().getSimpleName());
						enemy.append(",");
						enemy.append(mm.getX());
						enemy.append(",");
						enemy.append(mm.getY());
						enemy.append(",");
						enemy.append(mo.level);
						enemy.append(",");
						enemy.append(mo.sleep);
						enemy.append(",");
						enemy.append(mo.angry);
						ENEMY.add(enemy.toString());
						// enemy.append("\n");
					}
					if (mo.getItem() != null) {
						StringBuffer item = new StringBuffer();
						if (mo.getItem() instanceof Disc) {
							item.append(mo.getItem().getLastPackage());
							item.append(".Disc");
							item.append(((Base_Disc) mo.getItem()).list_stage
									.get(0).EC.getSimbolName());
							item.append(((Base_Disc) mo.getItem()).list_stage
									.get(1).EC.getSimbolName());
						} else {
							item.append(mo.getItem().getLastPackage());
							item.append(".");
							item.append(mo.getItem().getClass().getSimpleName());
						}
						item.append(",");
						item.append(mm.getX());
						item.append(",");
						item.append(mm.getY());
						item.append(",");
						item.append(mo.curse);
						item.append(",");
						item.append(mo.getItem() instanceof SpellCard ? mo.forge
								: 0);
						item.append(",");
						if (mo.getItem() instanceof Staff
								|| mo.getItem() instanceof Base_Pot) {
							item.append(mo.forge);
						} else {
							item.append(0);
						}
						item.append(",");
						item.append(mo.getItem() instanceof Arrow ? mo.forge
								: 0);
						ITEM.add(item.toString());
					}
					if (mo.getTrap() != null) {
						StringBuffer trap = new StringBuffer();
						trap.append(mo.getTrap().getClass().getSimpleName());
						trap.append(",");
						trap.append(mm.getX());
						trap.append(",");
						trap.append(mm.getY());
						TRAP.add(trap.toString());
					}
					if (mo.enter) {
						enter = String.valueOf(mm.getX()).concat(",")
								.concat(String.valueOf(mm.getY()));
					}
					if (mo.exit) {
						exit = String.valueOf(mm.getX()).concat(",")
								.concat(String.valueOf(mm.getY()));
					}
				}
			}
		}
		try {
			filewriter.write(map.toString());
			filewriter.write("[Entrance x,y]	[Exit x,y]\r\n");
			filewriter.write(enter.concat("	").concat(exit).concat("\r\n"));
			filewriter.write("[Enemy x,y,lv,sleep,angry]\r\n");
			for (String str : ENEMY) {
				filewriter.write(str);
				filewriter.write("\r\n");
			}
			filewriter.write("[Item x,y,curse,forge,staff,arrow]\r\n");
			for (String str : ITEM) {
				filewriter.write(str);
				filewriter.write("\r\n");
			}
			filewriter.write("[Trap x,y]\r\n");
			for (String str : TRAP) {
				filewriter.write(str);
				filewriter.write("\r\n");
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
