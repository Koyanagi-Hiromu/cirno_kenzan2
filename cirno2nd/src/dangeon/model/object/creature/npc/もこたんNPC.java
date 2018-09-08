package dangeon.model.object.creature.npc;

import java.awt.Color;
import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;

public class もこたんNPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public もこたんNPC(int x, int y) {
		super(new Point(x, y), "藤原妹紅", CHARA_IMAGE.藤原妹紅, false);
	}

	@Override
	public void message() {
		new Conversation(IMLC, "アイテムの凍結について教えよう", new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("アイテムが青い？") {

					@Override
					protected void work() {
						say("青いアイコンは冷えたことを表している@", "階段を下りるたびに手持ちのアイテムが冷えていくよ@",
								"冷えたアイテムを更に持ち続けると……@", "最後には凍りきってしまうんだ@");
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("凍るとどうなるの？") {

					@Override
					protected void work() {
						say("凍りきったアイテムは", Color.RED.toString(), "何でも装備だけ",
								Color.WHITE.toString(), "ができなくなる@");
						say("凍っていようが使用も可能だし@");
						say("カードなら攻撃や防御装備もできる@");
						say("一部のアイテムは凍らせることで恩恵を得られるらしいな@");
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("どうやって溶かすの？") {

					@Override
					protected void work() {
						say("ダンジョン内で溶かす手段は限られている@");
						say("専用の書を使用するか、私を模した敵の特殊能力を受けるかしかないな@");
					}
				};
			}

		});
	}

	@Override
	protected void say(String... strings) {
		StringBuilder sb = new StringBuilder();
		for (String string : strings) {
			sb.append(string);
			if (string.contains("＠") || string.contains("@")) {
				sb.deleteCharAt(sb.length() - 1);
				say(sb.toString());
				sb = new StringBuilder();
			}
		}
		if (sb.length() > 0)
			super.say(sb.toString());
	}
}
