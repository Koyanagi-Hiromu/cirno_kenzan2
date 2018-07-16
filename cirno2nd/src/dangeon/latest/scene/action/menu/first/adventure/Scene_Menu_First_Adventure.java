package dangeon.latest.scene.action.menu.first.adventure;

import java.util.ArrayList;

import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.Base_Scene_Menu_First____View;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal_Commnad;
import dangeon.latest.scene.action.menu.first.adventure.records.AdvRecord;
import dangeon.latest.scene.action.menu.first.adventure.records.AdvRecord_Command;
import dangeon.latest.scene.action.menu.first.adventure.records.AdvRecord_Command_1st;
import dangeon.latest.scene.action.menu.first.adventure.wiki.Wiki_First_Commnad;
import dangeon.latest.scene.action.menu.first.adventure.wiki.Wiki_SelectionCharacter;
import dangeon.latest.scene.action.menu.first.adventure.wiki_item.ItemWiki;
import dangeon.latest.scene.action.menu.first.adventure.wiki_item.ItemWiki_Command;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.save.SaveLoad;

public class Scene_Menu_First_Adventure extends Scene_Menu_First___ {

	public Scene_Menu_First_Adventure(KeyHolder kh, Base_View bv) {
		super(kh, new Base_Scene_Menu_First____View(bv));
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		final Scene_Menu_First___ ME = this;
		setContents("ランキング", "各ダンジョンの足跡を確認します", new Book() {
			@Override
			protected void work() {
				setNextScene(new AdvRecord_Command_1st(new AdvRecord_Command(
						new AdvRecord(KH, CURRENT_VIEW, ME))));
			}
		});
		setContents("１ターンパス", "１ターンだけ何もしないで過ごします", new Book() {
			@Override
			protected void work() {
				TurnSystemController.callMeToStartEnemyTurn(true);
				setNextScene(Scene_Action.getMe());
			}
		});
		// setDeprecatedContents("メダル図鑑", "今までにもらったメダルを見ます", new Book() {
		// @Override
		// protected void work() {
		// Message.set("未実装です");
		// setNextScene(Scene_Action.getMe());
		// }
		// });
		setContents("アイテム図鑑", "使用したことのあるアイテムを見ます", new Book() {
			@Override
			protected void work() {
				setNextScene(new ItemWiki_Command(new ItemWiki(KH,
						CURRENT_VIEW, 0)));
			}
		});
		setContents("エネミー図鑑", "手に入れたエネミーの情報を見ます", new Book() {
			@Override
			protected void work() {
				setNextScene(new Wiki_First_Commnad(
						new Wiki_SelectionCharacter(KH, CURRENT_VIEW)));
			}
		});
		if (StoryManager.日記帳.hasFinished()) {
			setContents("日記帳", "冒険の足跡を見ます", new Book() {
				@Override
				protected void work() {
					setNextScene(new Medal_Commnad(KH, ME));
					// setNextScene(new Wiki_First_Commnad(
					// new Wiki_SelectionCharacter(KH, CURRENT_VIEW)));
				}
			});
		}
		if (PresentField.get().getParentRandomMap() != null
				|| PresentField.isRandomField())
			quitAdv();
		else
			saveAdv();
	}

	private void quitAdv() {
		setContents("今回の冒険を諦める", "アイテムをすべて捨てて拠点に戻ります", new Book() {

			@Override
			protected void work() {
				new ConvEvent("本当にあきらめますか？") {
					@Override
					public boolean defaultYes() {
						return false;
					}

					@Override
					protected Book getYes() {
						return new Book() {
							@Override
							protected void work() {
								Player.me.death_sudden(null, "冒険をあきらめた");
							}
						};
					}
				};
			}
		});
	}

	private void saveAdv() {
		if (PresentField.get() instanceof FairyPlace) {
			setContents("今日の冒険を終える", "家に帰ってゆっくり休みます", new Book() {

				@Override
				protected void work() {
					new ConvEvent("セーブして終了しますか？$", "アイテムを保持したまま終了します") {
						@Override
						protected Book getYes() {
							return new Book() {
								@Override
								protected void work() {
									new SaveLoad(new MassCreater()).saveEnd();
								}
							};
						}
					};
				}
			});
		} else {
			setDeprecatedContents("今日の冒険を終える", "家に帰ってゆっくり休みます", new Book() {

				@Override
				protected void work() {
					setNextScene(Scene_Action.getMe());
					Message.set("妖精の踊り場からじゃないと家に帰れない@");
				}
			});
		}
	}

}