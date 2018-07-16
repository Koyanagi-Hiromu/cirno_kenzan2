package dangeon.latest.util;

import dangeon.controller.DangeonScene;
import dangeon.controller.listener.menu.Menu_Select;
import dangeon.latest.scene.action.message.Message;

public abstract class Base_SelectBox {
	private static Base_SelectBox me = null;
	public final String[] SELECT;
	public final boolean[] SELECT_NULL;
	public final DangeonScene PRE_DANGEON_SCENE;

	/**
	 * @param select
	 * @param flag_se
	 *            Menuを開く効果音を鳴らすか否か
	 */
	public Base_SelectBox(boolean flag_se, String... select) {
		PRE_DANGEON_SCENE = DangeonScene.getPresentScene();
		if (me != null) {
			System.out.println("SelectBox表示中に新たなSelectBox作成命令が来ました");
		}
		int count_null = 0;
		for (String s : select) {
			if (s == null || s.matches("null")) {
				count_null++;
			}
		}
		SELECT = new String[select.length - count_null];
		SELECT_NULL = new boolean[select.length];
		for (int i = 0, j = 0; i < select.length; i++) {
			String s = select[i];
			SELECT_NULL[i] = (s == null || s.matches("null"));
			if (SELECT_NULL[i]) {
				continue;
			}
			SELECT[j] = s;
			j++;
		}
		me = this;
		Menu_Select.init(this, flag_se);
	}

	/**
	 * Menuを開く効果音を鳴らす
	 * 
	 * @param select
	 */
	public Base_SelectBox(String... select) {
		this(true, select);
	}

	public final void end() {
		if (DangeonScene.SELECT.isPresentScene()) {
			DangeonScene.setScene(endScene());
		}
		if (Message.isDemandToWaitPushingAnyKey()) {
			Message.releaseLock();
		}
		me = null;
	}

	protected DangeonScene endScene() {
		return PRE_DANGEON_SCENE;
	}

	public String getSelectString(int i) {
		return SELECT[i];
	}

	public int getY(int y) {
		int c = -1;
		for (int i = 0; i < y + 1; i++) {
			while (true) {
				c++;
				if (!SELECT_NULL[c]) {
					break;
				}
			}
		}
		return c;
	}

	public void pushCancel() {
		end();
	}

	public abstract void pushEnter(int y);

	public void setY(int i) {
		Menu_Select.setY(i);
	}
}
