package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.config.Config;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.grass.姉切草;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

public class 霊夢NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private boolean item_stack_flag;

	/**
	 * ①なにはともあれ心得。一番の根幹 <br/>
	 * ②最初は心得より実用的なものを望むだろうから（ターン制について） <br/>
	 * ③実用的なこと。ただし実行が難しいから②のあと <br/>
	 * ④実用的なこと。ただし実行が難しいから③のあと <br/>
	 * ⑤実用的なこと。ただし実行に時間ががかるから④のあと <br/>
	 * ※時間がかかると１回の冒険の重みが増えて、死ぬのがいやになる<br/>
	 * ⑥概念　杖の強さを知ってほしい<br/>
	 * ⑦より抽象的な概念　アイテムを惜しまないこと<br/>
	 * ⑧より抽象的な概念　どのアイテムだ大事なのか<br/>
	 * ⑨結局①に戻る<br/>
	 */
	private final String[] arr = { "打開", "抱え落ち", "マッサージ", "レベリング", "稼ぎ",
			"プライオリティ", "気配察知", "ヘッジ", "即降り", };

	public 霊夢NPC(Point p) {
		super(p, "霊夢", CHARA_IMAGE.博麗霊夢, false);
	}

	private void dakai() {
		Message.set("打開とは厳しい状況や困難な目標を攻略することを言うわ@");
		Message.set("例えばモンスターハウスを無事生還した時に「打開した！」と言うわ@");
		Message.set("他にはそうねぇ「隕石異変を打開するのを祈っているわ」かしらね？@");
		Message.set("でも間違えないでねチルノ、あなたの目標は３０Ｆに辿り着くことよ@");
		Message.set("もちろん敵を倒すことは悪くないけれ$ど決して敵をすべて倒すことではないハズよ@");
		Message.set("１つでも多くの階段を下ることを目標にしなさい？@");
	}

	private String[] getSelection() {
		StringBuilder sb = new StringBuilder();
		String[] num = { "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨" };
		for (int i = 0; i < 9; i++) {
			sb.append((Config.isReimuChecked(i + 1)) ? "○" : num[i]);
			sb.append(arr[i]);
			sb.append("@");
		}
		return sb.toString().split("@");
	}

	private void hedge() {
		Message.set("リスクヘッジって聞いたことある？@");
		Message.set("乱暴に言ってしまえば最悪の事態を想定することよ@");
		Message.set("こういう状況を考えてみましょう@");
		Message.set("あなたのＨＰは１@");
		Message.set("そして目の前に１体の敵@");
		Message.set("敵のＨＰも残り１@");
		Message.set("どうする？攻撃する？@");
		Message.set("そうここで普通に攻撃しちゃだめなのよ@");
		Message.set("外れたらどうするの？@");
		Message.set("必中の効果のあるものを使いましょう@");
		Message.set("例えば杖の魔法弾は必ず当たるわ@");
		Message.set("カードを使うのでもいいし薬草を飲んでＨＰを回復するのもいいわ@");
		Message.set("多少もったいないなと思っても最悪の事態を回避する選択を取りなさい？@");
		Message.set("つまりＨＰが０になる事態を回避することを念頭に置く@");
		Message.set("それこそがリスクヘッジよ@");
	}

	private void item() {
		if (Belongings.isMax()) {
			item_stack_flag = true;
			Message.set("…ってあなた持ち物が一杯じゃない@");
			Message.set("少しだけ待っててあげるから一旦アイテムを置くなりしなさい？@");
		} else {
			Message.setTask_AfterReleaseDemandToPushEnter(new Task() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					item_stack_flag = false;
					Message.removeILC();
					// ItemTable.testCreate(隕石異変.ME.getClassName());
					Base_Artifact a = ItemTable.itemReturn(
							Player.me.getMassPoint(), null);
					a.createSpellCard(false, 0);
					Belongings.setItems(a);
					SE.SYSTEM_PICKUP.play();
					Message.set(a.getColoredName(), "をもらった");
				}
			});
		}
	}

	private void item(int y) {
		Message.set("以上で「", arr[y], "」のお話は終わりよ@");
		if (Config.isReimuChecked(y + 1)) {
			Message.set("「", arr[y], "」について理解は深まったかしら？@");
		} else {
			Message.set("「", arr[y], "」についてちゃんと理解できたかしら？@");
			Message.set("それなら話を聞いたご褒美よ@");
			if (Belongings.isMax()) {
				item_stack_flag = true;
				Message.set("…ってあなた持ち物が一杯じゃない@");
				Message.set("少しだけ待っててあげるから一旦アイテムを置くなりしなさい？@");
			} else if (y == 2) {
				Message.setTask_AfterReleaseDemandToPushEnter(new Task() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						item_stack_flag = false;
						Message.removeILC();
						Base_Artifact a = new 姉切草(Player.me.getMassPoint());
						a.createSpellCard(false, 0);
						Belongings.setItems(a);
						SE.SYSTEM_PICKUP.play();
						Message.set(a.getColoredName(), "をもらった");
					}
				});
			} else {
				item();
			}

		}
	}

	private void kakaeoti() {
		Message.set("アイテムって大切よね？@");
		Message.set("特にカードを無くすなんてとってももったいないわ@");
		Message.set("だから使用するのをためらってしまう@");
		Message.set("でもその結果倒れてしまったら？@");
		Message.set("結局そのアイテムは無くなってしまうわ@");
		Message.set("それこそもったいないと思わない？@");
		Message.set("そういうのを抱え落ちって言うのよ@");
		Message.set("抱え落ちは恥ずべきことよ@");
		Message.set("そう、アイテムは使うものよ無くしても良いものなのよ@");
		Message.set("一番大事なのは３０Ｆまで辿り着くこと@");
		Message.set("次に大事なのはあなたが成長することよ@");
		Message.set("知識もアイテムも使えるものは全部使って倒れなさい@");
		Message.set("それがあなたの本当の経験値になるわ@");
	}

	private void kasegi() {
		Message.set("簡単に言えば何かを集めることよ@");
		Message.set("経験値のこともあるけど大体はアイテムを集めることを指すわ@");
		Message.set("拾う以外にもアイテムを集める方法があるの@");
		Message.set("例えばにとり@");
		Message.set("にとりにアイテムを投げると別のアイテムに変えてくれるわ@");
		Message.set("だからにとりが出現する階層では満腹$度が許す限りにとりを倒し続けるといいわ@");
		Message.set("出現を待って倒す稼ぎを特に「狩り」って言ったりするの@");
		Message.set("にとり狩りはオススメよ@");
		Message.set("他には咲夜よ@");
		Message.set("実はナイフは１０マスしか飛ばないの知ってた？@");
		Message.set("だから１１マス以上離れていれば……@");
		Message.set("あとは分かるわね？@");
	}

	private void lving() {
		Message.set("敵を倒すと経験値が入るわ@");
		Message.set("そしてレベルが上がるわ@");
		Message.set("強い敵を倒すともっと経験値が入るわ@");
		Message.set("そしてもっとレベルが上がるわ@");
		Message.set("レベリングとはわざと強い敵を作って倒すことよ@");
		Message.set("例えば咲夜がいるでしょう？@");
		Message.set("うまく誘導して敵を同士討ちさせるの@");
		Message.set("すると咲夜が強くなるわ@");
		Message.set("その強くなった咲夜を倒すの@");
		Message.set("毒草を投げたり鈍足の杖をあらかじめ使っておけばね？@");
		Message.set("敵から攻撃を受けずに倒すことが出来るはずよ@");
		Message.set("するとあなたのレベルが跳ね上がるってわけ@");
		Message.set("レベルがぐんと上がれば攻略が楽になるはずよ@");
	}

	private void massaji() {
		Message.set("薬草や妹切草を見たことある？@");
		Message.set("とっても大事なアイテムよ@");
		Message.set("装備して敵からダメージを受けると直後にＨＰが４点回復するわ@");
		Message.set("つまりダメージが３点以下ならＨＰが回復するわね？@");
		Message.set("これをマッサージとか指圧と呼ぶことがあるわ@");
		Message.set("ともかく薬草はダメージが４点減るわけだから強いわ@");
		Message.set("あなたが初心者なら拾ったらとりあえず装備しなさい？@");
		Message.set("中級者ならどうして強いかよく考えるといいわ@");
		Message.set("「あなたが何を目的に冒険しているのか」がヒントよ@");
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.博麗霊夢);
		if (Config.getReimuEvent() == 100) {
			boolean flag = false;
			for (int i = 1; i < 10; i++) {
				if (!Config.isReimuChecked(i)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				Config.setReimuEvent(200);
				Message.set("よく全部の話を聞いてくれたわね？@");
				Message.set("もう教えられることは無いわ@");
				Message.set("でもアイテムはあげるからたまに復習しに来るといいわ@");
				return;
			}
		}
		if (Config.getReimuEvent() == 0) {
			Message.set("はぁ……@");
			Message.set("まったく紫ったらまた面倒なことを私に押し付けて……@");
			Message.set("あ、ううん何でもないのよ？@");
			Message.set("さてチルノ、あなた異変を解決しに行くのよね？@");
			Message.set("私も慧音じゃないけど攻略のヒントを教えてあげるわ@");
			Message.set("ちょっと小難しい話をしてあげる@");
			Message.set("分かりにくいだろうけど話を聞くたびにアイテムを１つあげるわ@");
			Message.set("だからしっかり話を聞いて私の為にも早く異変を解決してよね？@");
			Config.setReimuEvent(100);
		} else {
			if (Config.getReimuEvent() == 100) {
				if (Config.isReimuChecked()) {
					if (item_stack_flag) {
						Message.set("話を聞いたご褒美よ@");
						item();
					} else {
						Message.set("ダンジョンから戻ってきたらまた１つ教えてあげるわ");
					}
				} else {
					Message.set("便利な言葉とテクニックを教えてあげるわ@");
					new Base_SelectBox(getSelection()) {
						@Override
						public void pushEnter(final int y) {
							end();
							if (y != 9) {
								if (Config.isReimuChecked(y + 1)) {
									Message.setImageLargeCharacter(Image_LargeCharacter.博麗霊夢);
									Message.set("それはもう教えたけどまた聞きたいの？@");
									Message.removeILC();
									new Base_SelectBox("いいえ", "はい") {
										@Override
										public void pushEnter(int _y) {
											end();
											if (_y == 1) {
												Message.setImageLargeCharacter(Image_LargeCharacter.博麗霊夢);
												Message.set("分かったわ@");
												message(y);
											}
										}
									};
								} else {
									message(y);
								}
							}
						}
					};
				}
			} else {
				if (Config.isReimuChecked()) {
					Message.set("何について復習したいの？@");
					new Base_SelectBox(getSelection()) {
						@Override
						public void pushEnter(final int y) {
							end();
							if (y != 9) {
								message(y);
							}
						}
					};
				} else {
					Message.set("これをあげるから頑張りなさい？@");
					item();
					if (!item_stack_flag) {
						Config.setReimuChecked(true);
					}
				}
			}

		}
		Message.removeILC();
	}

	protected void message(int y) {
		Message.setImageLargeCharacter(Image_LargeCharacter.博麗霊夢);
		Message.set("「", arr[y], "」ね……@");
		switch (y) {
		case 0:
			dakai();
			break;
		case 1:
			kakaeoti();
			break;
		case 2:
			massaji();
			break;
		case 3:
			lving();
			break;
		case 4:
			kasegi();
			break;
		case 5:
			priority();
			break;
		case 6:
			satti();
			break;
		case 7:
			hedge();
			break;
		case 8:
			sokuori();
			break;
		}
		item(y);
		if (!Config.isReimuChecked(y + 1)) {
			Config.setReimuChecked(true);
		}
		Config.setReimuChecked(y + 1, true);
		Message.removeILC();
	}

	private void priority() {
		Message.set("直訳すれば「価値」よ@");
		Message.set("持てるアイテムの数は限られているでしょ？@");
		Message.set("だから常にどのアイテムが有用かを考えないといけないわ@");
		Message.set("カードって楽しいわよね？@");
		Message.set("でも装備できるカードって何でも装備を除くとたった２枚なの@");
		Message.set("装備しないし使わないものは捨てちゃいましょ？@");
		Message.set("もっとプライオリティの高いものを持ち歩くといいわ@");
		Message.set("じゃあプライオリティの高いものって何でしょうね？@");
		Message.set("それは危険な状況を打開できるアイテムよ@");
		Message.set("例えば敵に囲まれたときを考えて見ましょう@");
		Message.set("いかづちの書を読む？@");
		Message.set("それもいいわ@");
		Message.set("でも敵のＨＰが１００もあったらどうするの？@");
		Message.set("思い出してチルノ？@");
		Message.set("あなたの目的はボロボロでも３０階に辿り着くことでしょ？@");
		Message.set("いかにダメージを受けずに階段を探すのかが大事なはずよ@");
		Message.set("さっきの答えの１つに高飛び草を飲むというものがあるわ@");
		Message.set("だってワープした先に階段があるかもしれないじゃない@");
		Message.set("……そうね@");
		Message.set("もちろん倒せるならそれに越したことは無いわよ？@");
		Message.set("レベルが上がればそれだけ生存しやすくなるから@");
		Message.set("要するにバランスよ@");
		Message.set("プライオリティは階層と手持ちのアイテムによって変化するものなのよ@");
	}

	private void satti() {
		Message.set("ＸとＺを同時に押すとターンをスキップできるわね？@");
		Message.set("その時画面内に敵がいないと座るわ@");
		Message.set("逆に画面内に敵がいると立ったままなの@");
		Message.set("これは見えない所にいる敵でも有効よ@");
		Message.set("ダンジョンは途中から先が見えにくくなるけどね？@");
		Message.set("その時にこのテクニックを思い出すといいわ@");
	}

	private void sokuori() {
		Message.set("階段を見つけたらすぐに降りてしまうことよ@");
		Message.set("序盤はフロアを回ってアイテムを集めるわよね？@");
		Message.set("それはいいことよ@");
		Message.set("でも終盤はそんなこと言ってられないわ@");
		Message.set("敵が強いのなら倒すのは諦めましょう@");
		Message.set("逃げるのは恥ずかしいことではないわ@");
		Message.set("最後は誇りをもって逃げなさい@");
		Message.set("あなたの本当の目的は異変を解決することでしょう？@");
		Message.set("どうすれば３０階下れるのか、それが大事なの@");
		Message.set("序盤に何を集め、中盤に何を守り、終盤に何をするのか@");
		Message.set("あなたの友だちを救いたいならよく考えることよ@");
	}
}
