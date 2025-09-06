package dangeon.model.map.field.town.map;

import java.awt.Image;
import java.awt.Point;
import java.util.AbstractList;

import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.town.Base_TownMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.device.HiddenMovePoint;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.artifact.trap.氷の罠;
import dangeon.model.object.creature.npc.Abstract_NPC;
import dangeon.model.object.creature.npc.もこたんNPC;
import dangeon.model.object.creature.npc.合成説明NPC;
import dangeon.model.object.creature.npc.dungeonNpc.NPC逆ヶ島;
import dangeon.model.object.creature.npc.dungeonNpc.魔理沙のトラップタワーNPC;
import dangeon.model.object.creature.player.Player;
import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.Image_MapTip;
import main.util.DIRECTION;

public class EternalHouse extends Base_TownMap {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EternalHouse() {
		super("eternalMap");
	}

	@Override
	public BGM getBGM() {
		return super.getBGM();
	}

	@Override
	public String getClassName() {
		return "永遠亭";
	}

	@Override
	public Point getEntrancePoint() {
		return new Point(2, 14);
	}

	@Override
	public Image_MapTip getMapTip() {
		return Image_MapTip.永遠亭;
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {
		Player.me.direction = DIRECTION.RIGHT;
		/*
		 * ◆◆◆◆中～上半分（小部屋以外）◆◆◆◆
		 */
		add(new HiddenMovePoint(37, 14, new FairyPlace(), new Point(17, 4),
				DIRECTION.DOWN));
		add(new NPC逆ヶ島(new Point(37, 14)).setDirection(DIRECTION.LEFT));
		if (StoryManager.おりん車.hasFinished()) {
			add(new 魔理沙のトラップタワーNPC(new Point(20, 4)));
		} else {
			add(new Abstract_NPC(CHARA_IMAGE.霧雨魔理沙, "霧雨魔理沙", 20, 4, false,
					"やれやれ人気者はつらいんだぜ@", "ところで知ってるか？@",
					"アイテムをうまく落っことすと罠が起動するんだぜ@", "⑨うまくってどういうこと？",
					"⑨ミニマップキーって分かるか？@", "ミニマップだけを表示させるボタンなんだが…@",
					"ミニマップキーを押すと四角(□)が点滅している所があるだろ？@",
					"四角(□)はアイテムを投げた時に落っこちる場所を表しているんだぜ@", "そこに丁度罠があればオッケーだ@",
					"他にも罠のテクニックは色々あるが・・・@", "罠道を極めるのは大変なんだぜ@", "私も今勉強中さ@",
					"⑨極めた人っているの？", "⑨確か魅魔様が…@", "あ、いやなんでもないぜ", "⑨ふ～ん？"));
		}
		/*
		 * ◆◆◆◆小部屋◆◆◆◆
		 */
		if (Config.getStory() < 1000) {
			// add(new Abstract_NPC(CHARA_IMAGE.アリス, "アリス", 7, 8, false,
			// "はぁ……また魔理沙に逃げられたわ@", "魔理沙はほんと人の説明も聞かないわ……@",
			// "きっと「説明」コマンドも選んだことがないんでしょうね……@",
			// "初めてのアイテムは「説明」を読んだあと何でも装備を試してほしいわ@"));
			// add(new Abstract_NPC(CHARA_IMAGE.にとり, "河城にとり", 6, 9, false,
			// "やあ妖精@", "昨今はにとり狩りというのが流行っているらしいね@", "気が気じゃないよ@"));
			// add(new Abstract_NPC(CHARA_IMAGE.パチュリー, "パチュリー", 8, 9, false,
			// "何でも装備する時も合成の時も注意してほしいんだけど@", "同じ印が重複して効果を発揮することはないわ@"));
		}
		add(new Abstract_NPC(CHARA_IMAGE.蓬莱山輝夜, "蓬莱山輝夜", 18, 9, false,
				"なんだか暇ね～@", "難題でも作ろうかしら@") {
			private static final long serialVersionUID = 1L;

			@Override
			public Image getImage() {
				return IM.getSPImage(LV, direction, 0);
			};
		}.setConditionList(CONDITION.反射, 0));
		add(new Abstract_NPC(CHARA_IMAGE.八意永琳, "八意永琳", 17, 8, false,
				"最新の研究によると、実は草は投げると必ず当たるのよ@",
				"敵が投げた草は外れることがあるから気づきにくいわよね@",
				"それと私のカードだけど、草の波紋効果を受け付けなくなる隠し効果があるのよ@"));
		add(new Abstract_NPC(Image_LargeCharacter.イナバ, CHARA_IMAGE.鈴仙・優曇華院・イナバ,
				"鈴仙・優曇華院・イナバ", 15, 10, false, "瞳印には混乱を防ぐ効果もあるのよ@",
				"それと「ゲイズ」の能力は目が合ってなければ大丈夫よ@",
				"でも通常攻撃が通る位置だと関係なく操られてしまうから気をつけてね")
				.setDirection(DIRECTION.RIGHT));
		Point p = new Point(15, 8);
		MassCreater.getMass(p).setWarningSmall(false);
		final Base_Trap t = new 氷の罠(p);
		add(t);
		add(new 氷の罠(new Point(19, 10)));
		add(new Abstract_NPC(CHARA_IMAGE.因幡てゐ, "因幡てゐ", 15, 9, false,
				"怪しいマスがあったら攻撃してみよう@", "罠が見つかることがあるうさよ@") {
			private static final long serialVersionUID = 1L;

			@Override
			public void message() {
				if (t.isVisible()) {
					MSG = new String[] { "普通は怪しいマス以外に罠はないんだけど@",
							"敵の能力のせいで怪しいマス以外の所に罠が設置されることもあるうさよ@",
							"それと素振りの他に罠を見つけるには…@",
							"ひとつの部屋でしばらく休むと部屋内の罠がすべて見つかるうさ" };
				}
				super.message();
			};
		}.setDirection(DIRECTION.RIGHT));
		/*
		 * ◆◆◆◆下半分◆◆◆◆
		 */
		if (!StoryManager.逆ヶ島clear.hasFinished()) {
			add(new Abstract_NPC(CHARA_IMAGE.パチュリー, "パチュリー", 15, 19, false,
					"魔理沙はどこにいったのかしら？").setDirection(DIRECTION.DOWN));
			add(new 合成説明NPC(16, 19, false).setDirection(DIRECTION.DOWN));
			add(new Abstract_NPC(CHARA_IMAGE.にとり, "河城にとり", 17, 19, false,
					"やあ妖精@", "敵の中には特定のアイテムを落とす敵がいるんだよ@", "天狗草を落とす椛とかね@"));
		}
		add(new Abstract_NPC(CHARA_IMAGE.上白沢慧音, "上白沢慧音", 24, 19, false,
				"私のカードには秘められたちからがあるらしい@", "解放された姿を一度見てみたいものだ@"));
		add(new もこたんNPC(25, 19));
		add(new Abstract_NPC(CHARA_IMAGE.ミスティア, "ミスティア", 26, 19, false,
				"メニューの情報はちゃんと利用できてる？@", "状態異常にかかったら一覧を見るのもいいんじゃないかな？@",
				"混乱状態でもアイテムを投げることが出来るとかお得なことが分かるよ！"));
		return super.getObjectList();
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		return true;
	}

	@Override
	public boolean isLightful() {
		return false;
	}
}
