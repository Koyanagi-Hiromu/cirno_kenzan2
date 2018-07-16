package dangeon.latest.scene.action.menu.first.infomation;

import java.util.ArrayList;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.Base_Scene_Menu_First____View;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.scene.action.menu.first.infomation.contents.Record;
import dangeon.latest.scene.action.menu.first.infomation.contents.RecordInfomation;
import dangeon.latest.scene.action.menu.first.infomation.contents.Simbols;
import dangeon.latest.scene.action.menu.first.infomation.contents.WhatIsInTheDungeon;
import dangeon.latest.scene.action.menu.first.infomation.contents.checked.CheckedList;
import dangeon.latest.scene.action.menu.first.infomation.contents.checked.CheckedList_Command;
import dangeon.latest.scene.action.menu.first.infomation.contents.conditions.Conditions;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;

public class Scene_Menu_First_Info extends Scene_Menu_First___ {

	public Scene_Menu_First_Info(KeyHolder kh, Base_View bv) {
		super(kh, new Base_Scene_Menu_First____View(bv));
	}

	private void initCheckedList(Base_Map_Random rdm) {
		if (rdm != null && rdm.UN_CHECKED_LEVEL > 0)
			setContents("識別状況リスト", "識別したアイテムを確認します", new Book() {
				@Override
				protected void work() {
					setNextScene(new CheckedList_Command(new CheckedList(KH,
							CURRENT_VIEW, 0)));
				}
			});
		else
			setDeprecatedContents("識別状況リスト", "識別したアイテムを確認します", new Book() {
				@Override
				protected void work() {
					Message.set("ここに未識別のアイテムは存在しません");
					setNextScene(Scene_Action.getMe());
				}
			});
	}

	private void initCondition() {
		setContents("状態異常の詳細", "状態異常の種類と効果を確認します", new Book() {
			@Override
			protected void work() {
				setNextScene(new Conditions(KH,
						Scene_Action.getMe().CURRENT_VIEW));
			}
		});
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		initMsg();
		initInfo();
		Base_Map_Random rdm = PresentField.get().getRandomMap();
		initWhatIsInDungeon(rdm);
		initCondition();
		initCheckedList(rdm);
		initSimbols();
	}

	private void initInfo() {
		setContents("おしらせの履歴", "画面右上に流れたおしらせを確認します", new Book() {
			@Override
			protected void work() {
				setNextScene(new RecordInfomation(KH,
						Scene_Action.getMe().CURRENT_VIEW));
			}
		});
	}

	private void initMsg() {
		setContents("メッセージの履歴", "画面下に流れたメッセージを確認します", new Book() {
			@Override
			protected void work() {
				setNextScene(new Record(KH, Scene_Action.getMe().CURRENT_VIEW));
			}
		});
	}

	private void initSimbols() {
		setContents("効果中の印一覧", "現在効果を発揮している印を確認します", new Book() {
			@Override
			protected void work() {
				setNextScene(new Simbols(KH, Scene_Action.getMe().CURRENT_VIEW));
			}
		});
	}

	private void initWhatIsInDungeon(final Base_Map_Random rdm) {
		if (rdm != null)
			setContents("何階に何がある？", "ダンジョンの概要を確認します", new Book() {
				@Override
				protected void work() {
					setNextScene(new WhatIsInTheDungeon(KH, Scene_Action
							.getMe().CURRENT_VIEW, rdm));
				}
			});
		else
			setDeprecatedContents("何階に何がある？", "ダンジョンの概要を確認します", new Book() {
				@Override
				protected void work() {
					Message.set("ここはダンジョンではありません");
					setNextScene(Scene_Action.getMe());
				}
			});
	}
}