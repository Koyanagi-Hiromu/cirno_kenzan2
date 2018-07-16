package dangeon.latest.scene.action.menu.first.special;

import java.util.ArrayList;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.Base_Scene_Menu_First____View;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.system.KeyHolder;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;

public class Scene_Menu_First_Bring extends Scene_Menu_First___ {
	public Scene_Menu_First_Bring(KeyHolder kh, Base_View bv) {
		super(null, kh, new Base_Scene_Menu_First____View(bv));
	}

	private void init() {
		setContents("装備", "目の前の敵を装備します", new Book() {
			@Override
			protected void work() {
				HoldEnemy.ME.hold();
				setNextScene(Scene_Action.getMe());
			}
		});
	}

	private void init_holding() {
		setContents("能力", "装備中の敵の能力を使用します", new Book() {
			@Override
			protected void work() {
				HoldEnemy.ME.specialAction();
				setNextScene(Scene_Action.getMe());
			}
		});
		setContents("解除", "敵装備を解除して弾き飛ばします", new Book() {
			@Override
			protected void work() {
				HoldEnemy.ME.throwEnemy();
				setNextScene(Scene_Action.getMe());
			}
		});
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		if (HoldEnemy.ME.isHold())
			init_holding();
		else
			init();
	}

}