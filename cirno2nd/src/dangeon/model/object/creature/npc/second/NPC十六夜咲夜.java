package dangeon.model.object.creature.npc.second;

import java.awt.Color;
import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.config.Config;
import dangeon.model.object.creature.npc.Base_NPC;

public class NPC十六夜咲夜 extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NPC十六夜咲夜(Point p) {
		super(p, "十六夜咲夜", CHARA_IMAGE.十六夜咲夜, false);
	}

	@Override
	public void message() {
		String msg = "設定を切り替えますか？";
		ConvEvent CnE = new ConvEvent() {

			@Override
			protected Book getContent1() {
				return new Book("タイマー表示の切替") {
					@Override
					protected void work() {
						Config.switchLap_ON();
						if (Config.isLapON()) {
							say("機能をＯＮにしました");
							say("ダンジョン内でタイマーが表示されます");
						} else {
							say("機能をＯＦＦにしました");
							say("ダンジョン内でタイマーが表示されなくなりました");
							say("動作がわずかに軽くなったと思います");
						}
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("プレイ時間表示の切替") {
					@Override
					protected void work() {
						Config.switchShowHours();
						if (Config.doesShowHours()) {
							say("機能をＯＮにしました");
							say("タイトル画面にプレイ時間が表示されます");
						} else {
							say("機能をＯＦＦにしました");
							say("タイトル画面にプレイ時間が隠されます");
						}
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("キー表示の切替") {
					@Override
					protected void work() {
						Config.switchKey_ex();
						if (Config.isKey_ex()) {
							say("機能をＯＮにしました");
							say("動作がわずかに重くなります");
						} else {
							say("機能をＯＦＦにしました");
							say("動作がわずかに軽くなります");
						}
					}
				};
			}

			@Override
			protected Book getContent4() {
				return new Book("マス目常時表示の切替") {

					@Override
					protected void work() {
						Config.switchMass_off();
						if (!Config.isMass_OFF()) {
							say("機能をＯＮにしました");
							say("動作がわずかに重くなります");
						} else {
							say("機能をＯＦＦにしました");
							say("動作がわずかに軽くなります");
						}
					}
				};
			}

			@Override
			protected Book getContent5() {
				return new Book("ウィンドウ装飾の切替") {

					@Override
					protected void work() {
						if (Config.isLightVer()) {
							Config.saveLightVer(false);
							say("ウィンドウ装飾をONにしました");
							say("動作が重くなります");
							say("お手数ですがゲームを再起動してください");
						} else {
							Config.saveLightVer(true);
							say("機能をOFFにしました");
							say("動作が軽くなります");
							say("お手数ですがゲームを再起動してください");
						}
					}
				};
			}

			@Override
			protected String[] getExn() {
				String[] arr = new String[5];
				String s = getON_OFF(Config.isLapON());
				arr[0] = "▼現在の設定▼";
				arr[1] = "【" + s + "】タイマー機能";
				s = getON_OFF(Config.doesShowHours());
				arr[2] = "【" + s + "】プレイ時間表示";
				s = getON_OFF(Config.isKey_ex());
				arr[3] = "【" + s + "】キー表示";
				s = getON_OFF(!Config.isMass_OFF());
				arr[4] = "【" + s + "】マス目常時表示";
				s = getON_OFF(!Config.isLightVer());
				arr[4] = "【" + s + "】ウィンドウ装飾";
				return arr;
			}

			private String getON_OFF(boolean flag) {
				return (flag ? Color.ORANGE.toString() + " ＯＮ " : Color.GREEN
						.toString() + "ＯＦＦ")
						+ Color.WHITE.toString();
			}
		};
		new Conversation(IMLC, msg, CnE);
	}
}
