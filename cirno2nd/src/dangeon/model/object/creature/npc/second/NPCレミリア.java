package dangeon.model.object.creature.npc.second;

import java.awt.Color;
import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.config.Config;
import dangeon.model.object.creature.npc.Base_NPC;

public class NPCレミリア extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NPCレミリア(Point p) {
		super(p, "レミリア", CHARA_IMAGE.レミリア, false);
	}

	@Override
	public void message() {
		String msg = "寄せ来る運命を変えましょうか？";
		ConvEvent CnE = new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("±０ 通常難易度") {
					@Override
					protected void work() {
						Config.saveDifficulty(0);
						say("元の運命に戻したわ");
						setRep(Image_LargeCharacter.フランドール);
						rep("道中楽しんでね！");
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("＋１ 冒険家向け") {
					@Override
					protected void work() {
						Config.saveDifficulty(1);
						say("厳しい運命にしたわ");
						say("あなたに乗り越えられるかしら");
						setRep(Image_LargeCharacter.フランドール);
						rep("がんばってね！");
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("＋２ 妖怪用") {
					@Override
					protected void work() {
						Config.saveDifficulty(2);
						say("大変な運命にしてあげたわ");
						say("あなたに乗り越えられるかしら");
						setRep(Image_LargeCharacter.フランドール);
						rep("クリアできるかな？");
					}
				};
			}

			@Override
			protected String[] getExn() {
				String s;
				switch (Config.getFate()) {
				case 0:
					s = "±０";
					break;
				case 1:
					s = "＋１";
					break;
				default:
					s = "＋２";
					break;
				}
				return new String[] {
						Color.LIGHT_GRAY + "ゲーム難度を補正します",
						Color.LIGHT_GRAY + "現在は " + Color.WHITE + s
								+ Color.LIGHT_GRAY + " です" };
			}
		};

		new Conversation(IMLC, msg, CnE);
	}
}
