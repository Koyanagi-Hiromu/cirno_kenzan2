package dangeon.latest.scene.action.menu.first.item;

import java.util.ArrayList;

import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.Base_Scene_Menu_First____View;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.scene.action.menu.first.item.contents.Scene_Menu_First_Item_Fire;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List___Foot;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.device.FakeStairs;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.select.SelectBox_Stair;

public class Scene_Menu_First_Item extends Scene_Menu_First___ {
	public Scene_Menu_First_Item(KeyHolder kh, Base_View bv) {
		super(2, kh, new Base_Scene_Menu_First____View(bv));
	}

	private void contentsKAISHU() {
		if (MapList.isCheckedItem()) {
			setContents("回収", "フロアで踏んだアイテムを足元に集めます", new Book() {
				@Override
				protected void work() {
					MapList.gatherCheckedItems();
					setNextScene(Scene_Action.getMe());
				}
			});
		} else {
			setDeprecatedContents("回収", "フロアで踏んだアイテムを足元に集めます", new Book() {
				@Override
				protected void work() {
					setNextScene(Scene_Action.getMe());
					Message.set("踏まれたアイテムが落ちていません");
				}
			});
		}
	}

	private void init() {
		setContents("一覧", "アイテムリストを表示します", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Item_List(KH, CURRENT_VIEW));
			}
		});
		setContents("射撃", "射撃キーで発射するものを決めます", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Item_Fire(KH, CURRENT_VIEW));
			}
		});
		setFoot();
		contentsKAISHU();
		// setDeprecatedContents("？？", "（ここに表示するもの募集中）", new Book() {
		// @Override
		// protected void work() {
		// }
		// });
	}

	private void init_holding() {
		setDeprecatedContents("一覧", "アイテムリストを表示します", new Book() {
			@Override
			protected void work() {
				Message.set("敵を装備している間はアイテムを確認できません");
				setNextScene(Scene_Action.getMe());
			}
		});
		setContents("射撃", "射撃キーで発射するものを決めます", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Item_Fire(KH, CURRENT_VIEW));
			}
		});
		setFoot_holding();
		contentsKAISHU();

		// setDeprecatedContents("？？", "（ここに表示するもの募集中）", new Book() {
		// @Override
		// protected void work() {
		// }
		// });
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		init();
	}

	private void setFoot() {
		final Base_Artifact a = Belongings.getFoot();
		if (a != null) {
			if (a instanceof Stairs && !(a instanceof FakeStairs)) {
				setContents("階段", "次のフロアに進めます", new Book() {
					@Override
					protected void work() {
						setNextScene(Scene_Action.getMe());
						new SelectBox_Stair((Stairs) a);
					}
				});
				return;
			} else if (!(a instanceof Base_Device)) {
				setContents("足元", "足元のアイテムを表示します", new Book() {
					@Override
					protected void work() {
						setNextScene(new Scene_Menu_First_Item_List___Foot(KH,
								CURRENT_VIEW, true));
					}
				});
				return;
			} else {
				setContents("装置", "足元の装置を使います", new Book() {
					@Override
					protected void work() {
						if (a.walkOnAction())
							TurnSystemController.callMeToStartEnemyTurn();
						setNextScene(Scene_Action.getMe());
					}
				});
				return;
			}
		}
		setDeprecatedContents("足元", "足元のアイテムを表示します", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Item_List___Foot(KH,
						CURRENT_VIEW, true));
			}
		});
	}

	private void setFoot_holding() {
		final Base_Artifact a = Belongings.getFoot();
		if (a != null && a instanceof Stairs) {
			setContents("階段", "次のフロアに進めます", new Book() {
				@Override
				protected void work() {
					setNextScene(Scene_Action.getMe());
					new SelectBox_Stair((Stairs) a);
				}
			});
		} else {
			setDeprecatedContents("足元", "足元のアイテムを表示します", new Book() {
				@Override
				protected void work() {
					Message.set("敵を装備している間は足元を確認できません");
					setNextScene(Scene_Action.getMe());
				}
			});
		}
	}

}