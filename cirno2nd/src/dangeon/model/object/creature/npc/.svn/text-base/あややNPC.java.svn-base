package dangeon.model.object.creature.npc;

import java.awt.Point;
import java.io.File;

import main.constant.PropertySupporter;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import dangeon.controller.listener.menu.Menu_Result;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.object.creature.player.save.ResultSaveLoad;

public class あややNPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final int page_max = 10;

	private final String[] ARR = { "隕石異変", "慧音の最終問題", "博麗中結界", "最強への道" };

	public あややNPC(Point p) {
		super(p, "射命丸文", CHARA_IMAGE.射命丸文, false);
	}

	private void createrBoxes(final int x, final String field) {
		new Base_SelectBox(getFiles(x, field)) {
			@Override
			public void pushEnter(int y) {
				System.out.println(y);
				if (y == 10) {
					end();
				} else if (y == 11) {
					end();
					createrBoxes(x - 1, field);
				} else if (y == 12) {
					end();
					createrBoxes(x + 1, field);
				} else {
					Menu_Result.init(field, x * page_max + y + 1);
				}
			}

		};
	}

	private String[] getFiles(int x, String field) {
		PropertySupporter ps = ResultSaveLoad.getPropertySupporter(field, -1);
		String[] arr = new String[13];
		boolean has_next = true;
		for (int i = 0; i < page_max; i++) {
			int c = x * page_max + i + 1;
			int floor = ps.getProperty("r" + (c));
			if (floor != -1) {
				arr[i] = String.valueOf(c) + "位：" + floor + "階";
			} else {
				has_next = false;
				arr[i] = null;
			}
		}
		arr[page_max] = "やめる";
		if (x > 0) {
			arr[page_max + 1] = "戻る";
		} else {
			arr[page_max + 1] = null;
		}
		if (!has_next || (x + 1) * page_max >= ResultSaveLoad.MAX_RECORD) {
			arr[page_max + 2] = null;
		} else {
			arr[page_max + 2] = "次へ";
		}
		return arr;
	}

	private String getRoot(String name) {
		return "save/".concat(name);
	}

	private String[] getSelection() {
		String[] arr = new String[ARR.length + 1];
		for (int i = 0; i < ARR.length; i++) {
			arr[i] = ARR[i];
			if (!new File(getRoot(arr[i])).exists()) {
				arr[i] = null;
			}
		}
		arr[arr.length - 1] = "やめる";
		return arr;
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.射命丸文);
		Message.set("チルノさんの記事を見ますか？@");
		new Base_SelectBox(getSelection()) {
			@Override
			public void pushEnter(int y) {
				end();
				if (y != ARR.length) {
					createrBoxes(0, ARR[y]);
				}
			}
		};
		Message.removeILC();
	}

}
