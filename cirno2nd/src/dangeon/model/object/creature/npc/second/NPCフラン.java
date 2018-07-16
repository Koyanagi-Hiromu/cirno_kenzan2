package dangeon.model.object.creature.npc.second;

import java.awt.Color;
import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.config.Config;
import dangeon.model.object.creature.npc.Base_NPC;

public class NPCフラン extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NPCフラン(Point p) {
		super(p, "フランドール", CHARA_IMAGE.フランドール, false);
	}

	@Override
	public void message() {
		String msg = "コインは何コにしておく？";
		ConvEvent CnE = new ConvEvent() {

			@Override
			protected Book getContent1() {
				return new Book("３コ（ON）") {
					@Override
					protected void work() {
						setContinueSwith(true);
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("１コ（OFF）") {
					@Override
					protected void work() {
						setContinueSwith(false);
					}

				};
			}

			@Override
			protected String[] getExn() {
				String s;
				s = getON_OFF(Config.isCoinOnly1());
				return new String[] {
						Color.LIGHT_GRAY + "コンティニューシステムの",
						Color.LIGHT_GRAY + "ON・OFFを切り替えます",
						Color.LIGHT_GRAY + "現在は " + s + Color.LIGHT_GRAY
								+ " です", Color.LIGHT_GRAY + "ＯＦＦにすると倒れても",
						Color.LIGHT_GRAY + "尋ねられなくなります" };
			}

			private String getON_OFF(boolean flag) {
				return (flag ? Color.ORANGE.toString() + "ＯＦＦ" : Color.GREEN
						.toString() + " ＯＮ ")
						+ Color.WHITE.toString();
			}

			private void setContinueSwith(boolean on) {
				Config.setCoinOnly1(!on);
				if (Config.isCoinOnly1()) {
					say("それじゃあコンティニュー出来ないね！");
					say("倒れた後「やり直す」を選択すると、初期配置を変更して１Ｆからやり直すよ");
				} else {
					say("それじゃあコンティニューは２回までね！");
					say("倒れた後「やり直す」を選択すると完全再現するかしないか選べるよ");
				}
			}
		};
		new Conversation(IMLC, msg, CnE);
	}
}
