package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;

public class 合成説明NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final boolean sukima;

	public 合成説明NPC(int x, int y, boolean sukima) {
		super(new Point(x, y), "アリス", CHARA_IMAGE.アリス, false);
		this.sukima = sukima;
	}

	@Override
	public void message() {
		ConvEvent CnE = new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("合成の仕方") {
					@Override
					protected void work() {
						if (sukima) {
							say("そこのにとりに頼むか魔理沙の魔法瓶を使うと合成ができるわ");
						} else {
							say("魔理沙の魔法瓶を使うと合成ができるわ");
						}
						say("２つのアイテムから１つの強いアイテムを作れるの");
						setRep(Image_LargeCharacter.パチュリー);
						rep("１つ目のアイテムに２つ目のアイテムを合体させるイメージね");
						say("１つ目に選んだアイテムをベース、２つ目のアイテムを素材と言うわ");
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("杖の合成") {
					@Override
					protected void work() {
						say("杖の合成は簡単よ");
						say("同じ種類の杖を２つ選ぶと新しい１つの杖が出来るわ");
						setRep(Image_LargeCharacter.パチュリー);
						rep("例えば金縛りの杖[3]と金縛りの杖[5]を選ぶと金縛りの杖[8]が出来上がるわ");
						say("数字が合体して１つになるってわけね");
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("カードの合成") {
					@Override
					protected void work() {
						say("カードの合成は大きく２通りあるわ");
						say("カード同士を合成する場合と、カードに別のものを合成する場合ね");
						setRep(Image_LargeCharacter.パチュリー);
						rep("１つ目に選んだものがベースとなって２つ目に選んだ素材が合成されるわ");
						rep("素材、つまり２つ目に選んだものは無くなってしまうから注意することね");
						say("そもそも合成は取り返しが効かないから慎重に行うことよ");
					}
				};
			}

			@Override
			protected Book getContent4() {
				return new Book("カードにカード以外を合成する") {
					@Override
					protected void work() {
						say("ベースに選んだカードに素材が持っていた印が足されるわ");
						say("でもベースの器がいっぱいだと印は足されないから注意ね");
						say("逆に言えば器の数だけ印を足すことができるわ");
						setRep(Image_LargeCharacter.パチュリー);
						rep("同じ名前の印は重複できないことも伝えておくわ");
						rep("妹切草を合成して（妹）印を持ったカードがあるとするでしょう？");
						rep("そのカードにもう１度妹切草を合成しても無駄ってことよ");
						say("（妹）（妹）にならず（妹）だけのカードになるわ");
					}
				};
			}

			@Override
			protected Book getContent5() {
				return new Book("カードにカードを合成する") {
					@Override
					protected void work() {
						say("ベースには３つのものが足されるわ");
						say("印と修正値とボムよ");
						say("まずベースに選んだカードに素材が持っている印が足されるわ");
						setRep(Image_LargeCharacter.パチュリー);
						rep("補足説明すると素材の印は左から順番にすべて足されるわ");
						rep("ベースに器が少ない場合は印が入らないことがあるわ");
						say("次に修正値だけど、これは杖と同じように単純に数字が足されるわ");
						say("修正値が高くなると攻撃力、防御力が高くなるのよ");
						rep("最後にボムだけど、ボムは素材のボムの量に関わりなく１増えるわ");
						rep("素材はボムを使用してから合成した方が得・・・　なのかしらね");
					}
				};
			}

			@Override
			protected Book getContent6() {
				return new Book("カードにリボンを合成する") {
					@Override
					protected void work() {
						say("リボンねぇ…");
						say("リボンの合成はあまりオススメしないわ");
						setRep(Image_LargeCharacter.パチュリー);
						rep("そうかしら？　私は好きよ");
						rep("リボンを合成するとリボンを装備した時の効果を恒久的に得られるわ");
						rep("ただし厳しい条件があるわ");
						say("攻撃カードと防御カード両方にリボンの印が必要なの");
						say("つまり素材に同じリボンが２つ必要よ");
						say("そもそも同じリボンを２つ集めるのも難しいけど…");
						say("それよりも器を合わせて２つも消費しちゃうのが私は嫌いだわ");
						setRep(Image_LargeCharacter.パチュリー);
						rep("それならサブ武器とサブ防具を作ればいいじゃない");
						say("私はメイン１つに修正値を集めるのが好きなの");
					}
				};
			}

			@Override
			protected Book getContent7() {
				return new Book("カードが変化した？") {
					@Override
					protected void work() {
						say("幻想郷の賢者の話によると「特殊合成」というものがあるらしいわ");
						say("とあるカードにと特定の印をいくつか入れるとカードが変化する…");
						say("という噂よ");
						setRep(Image_LargeCharacter.パチュリー);
						rep("本当かしらねー");
					}
				};
			}

		};
		new Conversation(IMLC, "合成のルールを教えてあげるわ", CnE);
	}
}
