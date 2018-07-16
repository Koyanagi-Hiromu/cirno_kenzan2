package dangeon.model.object.creature.npc;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import main.util.FileReadSupporter;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.慧音の最終問題;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;

public class FirstNpc extends Base_NPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FirstNpc(Point p) {
		super(p, "firstNpc", false);
	}

	// public Image getImage() {
	// return IMAGE.ANYBODY.getImage();
	// }

	private String[] getMsg(int i) {
		StringBuilder msg = new StringBuilder();

		try {
			BufferedReader bf = new BufferedReader(
					FileReadSupporter.readUTF8("res/enemyTable/慧音の最終問題.txt"));
			for (int c = 0; c < 5 * (i - 1); c++) {
				bf.readLine();
			}
			String s;
			for (int c = 0; c < 5; c++) {
				int floor = 5 * (i - 1) + 1 + c;
				msg.append(floor);
				msg.append("F：");
				s = bf.readLine().split("\t")[0];
				msg.append(s.substring(0, s.indexOf(",")));
				msg.append("$");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		msg.append("戻る$");
		msg.append("次へ");
		return msg.toString().split("\\$");
	}

	@Override
	public void message() {
		select(1);
	}

	private void select(final int i) {
		new Base_SelectBox(false, getMsg(i)) {
			@Override
			public void pushEnter(int y) {
				end();
				if (y == 5) {
					if (i > 1) {
						select(i - 1);
					} else {
						select(20);
					}
				} else if (y == 6) {
					if (i < 20) {
						select(i + 1);
					} else {
						select(1);
					}
				} else {
					PresentField.setPresentField(慧音の最終問題.ME);
					MapList.setFloor((i - 1) * 5 + y);
					EnchantSpecial.removeAlwaysEnchantSpecial();
					CONDITION.conditionAllClear(Player.me);
					Player.me.itemFreezeCount();
					EnemyTable.resetEnemyTurn();
					TaskOnMapObject.setNewMap();
				}
			}
		};
	}
}
