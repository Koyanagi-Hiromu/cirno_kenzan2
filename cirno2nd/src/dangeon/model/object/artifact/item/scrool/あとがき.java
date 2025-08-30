package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import dangeon.model.config.StoryManager;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.npc.Abstract_NPC;
import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;


public class あとがき extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "非未来「自由人の狂想曲」";
	public static final String item_name_sound = "ひみらい「じゆうじんのげんそうきょく」";

	private boolean read = false;

	public あとがき(Point p) {
		super(p, "非未来「自由人の狂想曲」");
		sim = ENCHANT_SIMBOL.鴇;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "様々な事が書いてある";
	}

	@Override
	public boolean itemUse() {
		scroolUse();
		return true;
	}

	@Override
	public void scroolUse() {
		new Abstract_NPC(Image_LargeCharacter.プルートJの欠片, CHARA_IMAGE.arrow,
				"ウェレイ", 0, 0, false) {
			private static final long serialVersionUID = 1L;

			@Override
			public void message() {
				say("プレイありがとうございました。今後ともよろしくお願いします");
			}
		}.message();
	}
		
	public void OldUse(){
		// 恥ずかしくて消したやつ
		new Abstract_NPC(Image_LargeCharacter.プルートJの欠片, CHARA_IMAGE.arrow,
				"ウェレイ", 0, 0, false) {
			private static final long serialVersionUID = 1L;

			@Override
			public void message() {
				if (read) {
					say("プレイありがとうございました。今後ともよろしくお願いします");
				} else {
					read = true;
					BGM.yukkurishiteittene.play();
					say("こんばんは、クロスロッジ代表のウェレイです");
					say("時間がないので「プルートJの欠片」表記ですがお許し下さい");
					// say("まずはあれですね・・・");
					// say("僕はニコニコ動画が大好きなので、ここはお祝いの言葉をニコニコ流に・・・");
					// say("８８８８８８８８８８８８８８８８８８８８");
					say("いやあ、クリアおめでとうございます！");
					// say("チルノ見参！　いかがでしたでしょうか？");
					// say("ここまで遊んでくださったということはそこそこお気に召して頂けたのでしょうか");
					// say("それとも元々不思議のダンジョン系のゲームがお好きなのでしょうか");
					// say("どちらでも僕は大満足です");
					// say("プレイありがとうございました！");
					// say("さて・・・");
					say("せっかくなので少し裏話でもしますかね");
					// say("いやあ途中で副代表がいなくなってしまって本当に大変でしたねー");
					// say("僕はサボり魔なもんで中々進まない進まない");
					// say("大学との両立が本当にむずかしい・・・　と言うよりうまくいってませんでしたね～");
					// say("それはそうとエネミーの話ですけど");
					say("個人的に最も気に入っているカードは、多々良小傘ですね");
					say("あの子が全てのカードの基準になっていますし、一番最初に作ったということも思い出深いです");
					say("最も気に入っているアイテムは、アチチ草ですね");
					say("アチチ草を目の前の敵に投げて、事故で倒れたプレイヤーも多いのでは？");
					say("一番プログラム的に大変だったのは、寅丸☆系の能力でしたね");
					say("あまりに大変だったんで名前もはっちゃけたのにしました");
					// say("１つ１つ時間をかけて名前決めたんスよ～");
					// say("わりとネーミングセンスが光ったと思ってるんですけどどうですかね");
					// say("ラブチュッチュさとりとか決めた時は２人で笑ったなあ！");
					// say("「ラブさとり、ラブラブさとりときてどうする？」");
					// say("「ら、らぶちゅっちゅ？」「まじかｗｗｗｗ」みたいな！");
					// say("そうそう、従来の風来のシレンファンの方にも馴染みやすいよう、気を使ったんですよ");
					// say("ウドンゲイズとかね（ゲイズっていう敵がいるんですよ）");
					// say("最後にシステムの話");
					// say("魔王物語物語ってご存じですか？");
					// say("なんでも装備システムっていうのがあってすっごい面白いんですよ");
					// say("これを不思議のダンジョンにもってきたら面白いだろうなー！");
					// say("っとずっと思ってたんですけど、それだけじゃ足りない・・・！");
					// say("もう１つ新システムがほしいって思いましてね");
					// say("ロッチチが思いついたのがスキマ合成システムだったんですよ");
					// say("原案はもうちょっと違ったんですけどね");
					// say("まぁともかくなんでも装備システムとスキマ合成システム");
					// say("この２つがこのゲームの核となっています");
					// say("気に入っては頂けましたか？");
					// say("もし気に入ったのならこのシステム使ったゲームを作っていいですからね？");
					// say("テンポの良さとかバランスとかまでは真似できないでしょうし！（えっへん）");
					// say("我々は不思議のダンジョンスキーだったので、細かい所は気をつけましたね");
					// say("回収システムとか画期的だと思うんですけどどうですかね？");
					say("・・・・");
					// say("さて、あと１０分しかない！");
					say("まだまだ、喋りたいこと伝えたいことあるけれど");
					say("ここらで終わっておきますね");
					// say("えーーーっと・・・");
					// say("あのーーーー・・・");
					// say("スペシャルサンクスってあるじゃないですか");
					// say("and youっていうやつ");
					// say("言われた側はまぁたいつものだぁって思うかもしれないですけど");
					// say("うーんこうして作る側になるとね");
					// say("感謝の１つも言いたくなるんですよ");
					// say("・・・・");
					// say("・・・・");
					// say("じゃあ締めましょうかね");
					// say("・・・・");
					say("Thank you for playing !");
					say("ありがとう！　本当にありがとう！");
					say("どうかまた遊んで下さい！");
					// say("僕も思い出した時に遊びます！");
					say("ではではー！　これからも良きゲームライフを！");
					say("");
					say("");
					say("・・・そうそう");
					say("【運命のワルツ】のFate＋２が遊べるようになっていますよ");
					say("クリアしても何も変わらないので無理はしないでくださいね！");
					say("それでは！");

				}
				StoryManager.あとがき.saveThisFinished();
			}
		}.message();
	}
}
