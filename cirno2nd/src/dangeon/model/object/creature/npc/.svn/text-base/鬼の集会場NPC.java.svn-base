package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.SE;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.てゐの異変;
import dangeon.model.map.field.random.second.鬼の集会場;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ItemTemplate;

public class 鬼の集会場NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 鬼の集会場NPC(Point p) {
		super(p, "伊吹萃香", CHARA_IMAGE.伊吹萃香, false);
	}

	@Override
	public void message() {
		say("やっほー");
		new Conversation(IMLC, "てゐの問題に挑んじゃう？", new ConvEvent() {
			@Override
			protected String getCancel() {
				return "ちぇ～　いい肴のつまみになるかと思ったのになぁ～";
			}

			@Override
			protected String[] getExn() {
				return new String[] { "◆てゐの問題◆", "難易度：Lunatic", "全１０階（BOSSなし）",
						"持ち込み上限：１０コ", "", "チルノ見参１の体験版ダンジョン",
						"存在しないアイテムが抽選されてエ", "ラー落ちしたり", "前作で弱かった美鈴がそのまま序盤",
						"に出てくる" };
			}

			@Override
			protected Book getYes() {
				return new Book() {

					@Override
					protected void work() {
						if (Belongings.getSize() > 10) {
							Message.set("アイテムは１０コまでよ？");
						} else {
							SE.YUKARI_SPELL.play();
							Player.me.resetStatus();
							Enchant.allRemove();
							for (Base_Artifact a : てゐの異変.ME.firstItems()) {
								Belongings.setItems(a);
							}
							new MassCreater(てゐの異変.ME, null, true)
									.createFirstMap(0);
						}
					}
				};
			}
		});
		//
		// // Message.setImageLargeCharacter(Image_LargeCharacter.チルノテス);
		// // if (Config.getReimuEvent() < 10000) {
		// Message.set("鬼の集会場に行く？（全10Ｆ）$", "アイテムが一切落ちてないから注意ね～@");
		// // }
		// new Base_SelectBox("はい", "やめる") {
		// @Override
		// public void pushEnter(int y) {
		// if (y == 0) {
		// end();
		// select();
		// } else {
		// end();
		// }
		// }
		// };
		// Message.removeILC();
		// // new SelectBox_YesNo() {
		// // @Override
		// // public void yes() {
		// // for (Enchant e : Enchant.values()) {
		// // e.forceToRemove();
		// // }
		// // Belongings.getListItems().clear();
		// // Belongings.setItems(new 大きなおにぎり(Player.me.getMassPoint()));
		// // new MassCreater(慧音の最終問題.ME, null, true).createMap();
		// // }
		// // };
	}

	public void select() {

		Message.set("どのセットで行くの？@");
		new Base_SelectBox("書チーム", "２ボスチーム", "３ボスチーム", "４ボスチーム") {
			@Override
			public void pushEnter(int y) {
				start(y);
				end();
			}
		};
	}

	public void start(int i) {
		Message.set("いってらっしゃ～い");
		Belongings.allRemove();
		SE.YUKARI_SPELL.play();
		Player.me.resetStatus();
		Enchant.allRemove();
		Belongings.setListItems(ItemTemplate.SYUUKAI.getSelect(i));
		for (Base_Artifact a : 鬼の集会場.ME.firstItems()) {
			Belongings.setItems(a);
		}
		new MassCreater(鬼の集会場.ME, null, true).createFirstMap(0);
	}
}
