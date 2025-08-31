package dangeon.model.object.creature.npc.second;

import java.awt.Color;
import java.awt.Point;

import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.config.Config;
import dangeon.model.object.creature.npc.Base_NPC;
import main.res.CHARA_IMAGE;

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
				return new Book("３コ（残機２）") {
					@Override
					protected void work() {
						setContinueSwith(true);
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("１コ（残機０）") {
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
						Color.LIGHT_GRAY + "コンティニュー回数",
						Color.LIGHT_GRAY + "を切り替えます",
						Color.LIGHT_GRAY + "現在は " + s + Color.LIGHT_GRAY
								+ " です" };
			}

			private String getON_OFF(boolean flag) {
				return (flag ? Color.ORANGE.toString() + "残機０" : Color.GREEN
						.toString() + " 残機２")
						+ Color.WHITE.toString();
			}

			private void setContinueSwith(boolean on) {
				Config.setCoinOnly1(!on);
				if (Config.isCoinOnly1()) {
					say("それじゃあコンティニュー出来ないね！");
				} else {
					say("それじゃあコンティニューは２回までね！");
				}
			}
		};
		new Conversation(IMLC, msg, CnE);
	}
}
