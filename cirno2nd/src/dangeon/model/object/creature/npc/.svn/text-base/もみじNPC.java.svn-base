package dangeon.model.object.creature.npc;

import java.awt.Point;
import java.io.File;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import dangeon.controller.listener.menu.Menu_Result;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.config.Config;

public class もみじNPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public もみじNPC(Point p) {
		super(p, "犬走椛", CHARA_IMAGE.犬走椛, false);
	}

	private String get() {
		if (Config.isBiasName()) {
			if (Config.isDirectName()) {
				return "方向キーと斜め制限キーを押した時にも表示され";
			} else {
				return "斜め制限キーを押した時にも表示され";
			}
		} else {
			if (Config.isDirectName()) {
				return "方向キーを押した時にも表示され";
			} else {
				return "Nameキーを押した時のみ表示され";
			}
		}
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.犬走椛);
		Message.set("何かご用でしょうか？@");
		new Base_SelectBox("直前の挑戦結果が見たい", "冒険の設定を変えたい", "用事は特にない", "諸注意を聞く") {
			@Override
			public void pushEnter(int y) {
				end();
				if (y == 0) {
					if (new File("save/0.save").exists()) {
						Menu_Result.init(".", 0);
					} else {
						Message.setImageLargeCharacter(Image_LargeCharacter.犬走椛);
						Message.set("すみません配属されたばかりで記事がありません@");
						Message.set("１度冒険に行ってください@");
					}

				} else if (y == 1) {
					Message.setImageLargeCharacter(Image_LargeCharacter.犬走椛);
					Message.set("見た目や演出の変更ができます$", "どれも機能をONにするとゲームが少し重くなります@");
					Message.removeILC();
					new Base_SelectBox(false, "敵の名前の確認キーの設定", "落ちているアイテムの影の設定") {

						private void name() {
							Message.set("現在敵の名前は", get(), "ます", "@");
							new Base_SelectBox(false, "方向キーを押した時でも表示する",
									"斜め制限キーを押した時でも表示する", "どちらの時でも表示する",
									"Nameキーを押した時のみ表示する") {

								@Override
								public void pushEnter(int y) {
									end();
									Config.switchBiasName(y == 1 || y == 2);
									Config.switchDirectName(y == 0 || y == 2);
									Message.setImageLargeCharacter(Image_LargeCharacter.犬走椛);
									Message.set(get(), "るように設定しました！@");
									if (y != 3) {
										Message.set("動作が重くなるようでしたら解除して下さいね@");
									}
									Message.removeILC();
								}
							};
						}

						@Override
						public void pushEnter(int y) {
							end();
							Message.setImageLargeCharacter(Image_LargeCharacter.犬走椛);
							if (y == 0) {
								name();
							} else if (y == 1) {
								shadow();
							}
							Message.removeILC();
						}

						private void shadow() {
							String s = (Config.isNoShadow()) ? "せん" : "す";
							Message.set("現在アイテムに影がついていま", s, "@");
							new Base_SelectBox(false, "影をつける", "影をつけない") {

								@Override
								public void pushEnter(int y) {
									end();
									Config.switchDropShadow(y == 1);
									String s = (Config.isNoShadow()) ? "ない"
											: "る";
									Message.setImageLargeCharacter(Image_LargeCharacter.犬走椛);
									Message.set("影をつけ", s, "ように設定しました！@");
									if (y == 0) {
										Message.set("動作が重くなるようでしたら解除して下さいね@");
									}
									Message.removeILC();
								}
							};
						}

					};

				} else if (y == 3) {
					Message.setImageLargeCharacter(Image_LargeCharacter.犬走椛);
					Message.set("文様とは異なり私はハックダンジョンの記録もしております@");
					Message.set("記録したものはsaveフォルダ直下に書き出していますが…@");
					Message.set("注意して欲しいのですがファイルは%１０コまでしか保存できないです@");
					Message.set("必要でしたら他のフォルダに移してください@");
					Message.removeILC();
				}
			}
		};
		Message.removeILC();
	}

}
