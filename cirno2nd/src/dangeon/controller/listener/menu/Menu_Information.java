package dangeon.controller.listener.menu;

import dangeon.controller.DangeonScene;
import dangeon.controller.listener.menu.Conducter.PHASE;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.special.map.SukimaMap;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.save.SaveLoad;

public class Menu_Information extends Base_SelectBox {
	private static String[] getMsg() {
		StringBuilder sb = new StringBuilder();
		sb.append("メッセージ履歴");
		sb.append("@");
		sb.append("識別済み装備印一覧");
		sb.append("@");
		sb.append("設定を変更する");
		sb.append("@");
		if (PresentField.get().isDungeon()
				&& !(PresentField.get() instanceof SukimaMap)) {
			sb.append("冒険をあきらめる");
		} else {
			sb.append("セーブしてタイトルに戻る");
		}
		return sb.toString().split("@");
	}

	boolean flag_return_menu = false;

	public Menu_Information() {
		super(false, getMsg());
	}

	@Override
	protected DangeonScene endScene() {
		if (flag_return_menu) {
			return DangeonScene.MENU;
		} else {
			return DangeonScene.DANGEON;
		}
	}

	@Override
	public void pushCancel() {
		flag_return_menu = true;
		super.pushCancel();
	}

	@Override
	public void pushEnter(int y) {
		if (y == 0) {
			flag_return_menu = true;
			Conducter.phase = PHASE.履歴;
			end();
		} else if (y == 1) {
			flag_return_menu = true;
			Conducter.phase = PHASE.印;
			end();
		} else if (y == 2) {
			end();
			new Menu_Config();
		} else {
			end();
			if (PresentField.get().isDungeon()
					&& !(PresentField.get() instanceof SukimaMap)) {
				Message.set("本当にあきらめますか？@");
				new Base_SelectBox("いいえ", "はい") {
					@Override
					public void pushEnter(int y) {
						end();
						if (y == 0) {
							new Menu_Information();
						} else {
							Player.me.death_sudden(null, "冒険をあきらめた");
						}
					}
				};

			} else if ((PresentField.get() instanceof SukimaMap)) {
				Message.set("倉庫内では終了できません");
				new Menu_Information();
			} else {
				Message.set("アイテムを保持したまま終了します$本当によろしいですか？@");
				new Base_SelectBox("いいえ", "はい") {
					@Override
					public void pushEnter(int y) {
						end();
						if (y == 0) {
							new Menu_Information();
						} else {
							new SaveLoad(new MassCreater()).saveEnd();
						}
					}
				};
			}

		}
	}
}