package dangeon.model.object.creature.npc;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import main.util.FileReadSupporter;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.TEST;

public class CopyOfFirstNpc extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CopyOfFirstNpc(Point p) {
		super(p, "TESTNpc", false);
	}

	// public Image getImage() {
	// return IMAGE.ANYBODY.getImage();
	// }

	private String[] getMsg(int i) {
		StringBuilder msg = new StringBuilder();

		try {
			BufferedReader bf = new BufferedReader(
					FileReadSupporter.readUTF8("res/enemyTable/TESTダンジョン.txt"));
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
					new MassCreater(TEST.ME, null, true).createFirstMap(0);
					System.out.println(i);
					MapList.setFloor((i - 1) * 5 + y);
				}
			}
		};
	}
}
