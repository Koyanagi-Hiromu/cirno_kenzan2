package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import patch.CopyOfPatchChecker;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.util.Switch;

public class 熱解凍NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 熱解凍NPC(Point p) {
		super(p, "てゐ", CHARA_IMAGE.因幡てゐ, false);
	}

	@Override
	public void message() {
		new ConvEvent("テストスイッチ:", Switch.test ? "true" : "false") {
			@Override
			protected Book getContent1() {
				return new Book("解凍") {
					@Override
					protected void work() {
						for (Base_Artifact a : Belongings.getListItems()) {
							a.freezeCountReset();
							a.setForgeValue(99);
						}
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("冷凍") {
					@Override
					protected void work() {
						for (Base_Artifact a : Belongings.getListItems()) {
							a.freezeCountPlus(10);
						}
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("テストスイッチON") {
					@Override
					protected void work() {
						Switch.test = true;
					}
				};
			}

			@Override
			protected Book getContent4() {
				return new Book("テストスイッチOFF") {
					@Override
					protected void work() {
						Switch.test = false;
					}
				};
			}

			@Override
			protected Book getContent5() {
				return new Book("バージョン確認") {
					@Override
					protected void work() {
						CopyOfPatchChecker.patchCheck();
					}
				};
			}
		};
	}

}
