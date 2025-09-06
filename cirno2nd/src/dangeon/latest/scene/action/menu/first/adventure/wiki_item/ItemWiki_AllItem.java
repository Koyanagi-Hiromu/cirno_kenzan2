package dangeon.latest.scene.action.menu.first.adventure.wiki_item;

import java.util.ArrayList;

public class ItemWiki_AllItem {
	public ItemWiki_AllItem() {
	}

	private void arrow(ArrayList<String> list) {
		list.add("ミニ八卦炉");
		list.add("大砲の弾");
		list.add("鉄の矢");
		list.add("毒ナイフ");
	}

	private void food(ArrayList<String> list) {
		list.add("スモールおにぎり");
		list.add("ナツメおにぎり");
		list.add("ミシャグジおにぎり");
		list.add("奇跡のおにぎり");
		list.add("巨大なおにぎり");
		list.add("小さなおにぎり");
		list.add("大きなおにぎり");
		list.add("腐ったおにぎり");
	}

	public ArrayList<String> get(int category) {
		ArrayList<String> list = new ArrayList<String>();
		switch (category) {
		case 0:
			spellcard(list);
			break;
		case 1:
			grass(list);
			break;
		case 2:
			ring(list);
			break;
		case 3:
			scrool(list);
			break;
		case 4:
			staff(list);
			break;
		case 5:
			food(list);
			break;
		case 6:
			arrow(list);
			break;
		case 7:
			pot(list);
			break;
		default:
			break;
		}
		// Collections.sort(list);
		return list;
	}

	private void grass(ArrayList<String> l) {
		l.add("アイスの種");
		l.add("くちなし草");
		l.add("しあわせ草");
		l.add("すばやさ草");
		l.add("ちからの草");
		l.add("ドラゴン草");
		l.add("胃拡張の種");
		l.add("胃縮小の種");
		l.add("感電草");
		l.add("金縛り草");
		l.add("苦無草");
		l.add("高飛び草");
		l.add("混乱草");
		l.add("姉切草");
		l.add("睡眠草");
		l.add("超不幸の草");
		l.add("天使の草");
		l.add("灯草");
		l.add("毒消し草");
		l.add("毒草");
		l.add("氷結草");
		l.add("不幸の草");
		l.add("鳳凰の種");
		l.add("妹切草");
		l.add("命の草");
	}

	private void pot(ArrayList<String> list) {
		list.add("お祓いの瓶");
		list.add("かき氷器");
		list.add("グリモワールの瓶");
		list.add("フランドールの瓶");
		list.add("印の瓶");
		list.add("空の瓶");
		list.add("蛍瓶");
		list.add("香霖の瓶");
		list.add("合成の瓶");
		list.add("識別の瓶");
		list.add("小鈴の瓶");
		list.add("針名丸の瓶");
		list.add("水瓶");
		list.add("雛瓶");
		list.add("聖瓶");
		list.add("変化の瓶");
		list.add("妹紅の瓶");
		// list.add("送信の瓶");
	}

	private void ring(ArrayList<String> list) {
		list.add("こいしのお忍びリボン");
		list.add("しあわせのリボン");
		list.add("フランの掘削リボン");
		list.add("メディスンの衰退リボン");
		list.add("リリーのホワイトリボン");
		list.add("ルーミアのリボン");
		list.add("レミリアの超回復リボン");
		list.add("空の融合リボン");
		list.add("慧音の識別リボン");
		list.add("焼き芋のリボン");
		list.add("雛の御守リボン");
		list.add("大遠投のリボン");
		list.add("透視のリボン");
		list.add("復活のリボン");
		list.add("流行病のリボン");
		list.add("橙のぶっとびリボン");
		list.add("萃香の頑強リボン");
		list.add("閻魔の改心リボン");
	}

	private void scrool(ArrayList<String> list) {
//		list.add("あとがき");
		list.add("いかづちの書");
		list.add("おにぎりの書");
		list.add("おはらいの書");
		list.add("グリモワール");
		list.add("グリモワール2");
		list.add("ダンジョン攻略本");
		list.add("バクスイの書");
		list.add("パワーアップの書");
		list.add("メッキの書");
		list.add("ワイルドカード");
		list.add("解凍の書");
		list.add("慧音の歴史書");
		list.add("幻想郷縁起");
		list.add("混乱の書");
		list.add("撒き餌の書");
		list.add("自爆の書");
		list.add("識別の書");
		list.add("人を殺せる書");
		list.add("切れ端");
		list.add("大部屋の書");
		list.add("敵倍速の書");
		list.add("天邪鬼の書");
		list.add("電光石火の書");
		list.add("破裂の書");
		list.add("八咫烏の書");
		list.add("氷の書");
		list.add("封書モンスターハウス");
		list.add("明かりの書");
		list.add("自由人の狂想曲");	
	}

	private void spellcard(ArrayList<String> list) {
		list.add("Exルーミアのカード");
		list.add("アリスのカード");
		list.add("イナバのカード");
		list.add("キスメのカード");
		list.add("きもけーねのカード");
		list.add("チルノのカード");
		list.add("ナズーリンのカード");
		list.add("パチュリーのカード");
		list.add("パルスィのカード");
		list.add("フランドールのカード");
		list.add("プリズムリバー三姉妹のカード");
		list.add("みすちーのカード");
		list.add("ミスティアのカード");
		list.add("メディスンメランコリーのカード");
		list.add("ゆっくりのカード");
		list.add("リグルのカード");
		list.add("リリーホワイトのカード");
		list.add("ルーミアのカード");
		list.add("レティのカード");
		list.add("レミリアのカード");
		list.add("わかさぎ姫のカード");
		list.add("伊吹萃香のカード");
		list.add("一輪雲山のカード");
		list.add("因幡てゐのカード");
		list.add("永江衣玖のカード");
		list.add("洩矢諏訪子のカード");
		list.add("河城にとりのカード");
		list.add("火焔猫燐のカード");
		list.add("鬼人正邪のカード");
		list.add("宮古芳香のカード");
		list.add("九十九姉妹のカード");
		list.add("犬走椛のカード");
		list.add("鍵山雛のカード");
		list.add("古明地こいしのカード");
		list.add("古明地さとりのカード");
		list.add("紅美鈴のカード");
		list.add("黒谷ヤマメのカード");
		list.add("今泉影狼のカード");
		list.add("魂魄妖夢のカード");
		list.add("三月精のカード");
		list.add("四季映姫・ヤマザナドゥのカード");
		list.add("射命丸文のカード");
		list.add("朱鷺子のカード");
		list.add("秋姉妹のカード");
		list.add("十六夜咲夜のカード");
		list.add("小悪魔のカード");
		list.add("小野塚小町のカード");
		list.add("少名針妙丸のカード");
		list.add("上白沢慧音のカード");
		list.add("秦こころのカード");
		list.add("星熊勇儀のカード");
		list.add("聖白蓮のカード");
		list.add("西行寺幽々子のカード");
		list.add("赤蛮奇のカード");
		list.add("蘇我屠自古のカード");
		list.add("村紗水蜜のカード");
		list.add("多々良小傘のカード");
		list.add("大妖精のカード");
		list.add("東風谷早苗のカード");
		list.add("藤原妹紅のカード");
		list.add("寅丸星のカード");
		list.add("二ッ岩マミゾウのカード");
		list.add("博麗霊夢のカード");
		list.add("八意永琳のカード");
		list.add("八雲紫のカード");
		list.add("八雲藍のカード");
		list.add("八坂神奈子のカード");
		list.add("比那名居天子のカード");
		list.add("姫海棠はたてのカード");
		list.add("封獣ぬえのカード");
		list.add("風見幽香のカード");
		list.add("物部布都のカード");
		list.add("蓬莱山輝夜のカード");
		list.add("豊聡耳神子のカード");
		list.add("堀川雷鼓のカード");
		list.add("魅魔のカード");
		list.add("霧雨魔理沙のカード");
		list.add("幽谷響子のカード");
		list.add("霊烏路空のカード");
		list.add("橙のカード");
		list.add("霍青娥のカード");
	}

	private void staff(ArrayList<String> list) {
		list.add("いかづちの杖");
		list.add("イカリの杖");
		// list.add("オオイカリの杖");
		list.add("しあわせの杖");
		list.add("ハイテンションの杖");
		// list.add("パチュリーの杖");
		list.add("モノカの杖");
		list.add("悪魔の杖");
		list.add("一時しのぎの杖");
		list.add("引き寄せの杖");
		list.add("影縛りの杖");
		// list.add("炎の杖");
		list.add("回復の杖");
		list.add("金縛りの杖");
		// list.add("邪法の杖");
		// list.add("小悪魔の杖");
		// list.add("小悪魔秘蔵の杖");
		list.add("場所替えの杖");
		list.add("吹き飛ばしの杖");
		// list.add("大悪魔の杖");
		// list.add("転ばぬ先の杖");
		list.add("鈍足の杖");
		list.add("倍速の杖");
		list.add("氷の杖");
		list.add("不幸の杖");
		list.add("封印の杖");
		list.add("無意識の杖");
	}
}
