package dangeon.util;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.ミニ八卦炉;
import dangeon.model.object.artifact.item.arrow.大砲の弾;
import dangeon.model.object.artifact.item.arrow.鉄の矢;
import dangeon.model.object.artifact.item.disc.Disc;
import dangeon.model.object.artifact.item.grass.すばやさ草;
import dangeon.model.object.artifact.item.grass.ドラゴン草;
import dangeon.model.object.artifact.item.grass.妹切草;
import dangeon.model.object.artifact.item.grass.姉切草;
import dangeon.model.object.artifact.item.grass.混乱草;
import dangeon.model.object.artifact.item.grass.高飛び草;
import dangeon.model.object.artifact.item.grass.鳳凰の種;
import dangeon.model.object.artifact.item.scrool.いかづちの書;
import dangeon.model.object.artifact.item.scrool.おにぎりの書;
import dangeon.model.object.artifact.item.scrool.グリモワール;
import dangeon.model.object.artifact.item.scrool.ダンジョン攻略本;
import dangeon.model.object.artifact.item.scrool.バクスイの書;
import dangeon.model.object.artifact.item.scrool.大部屋の書;
import dangeon.model.object.artifact.item.scrool.天邪鬼の書;
import dangeon.model.object.artifact.item.scrool.撒き餌の書;
import dangeon.model.object.artifact.item.scrool.明かりの書;
import dangeon.model.object.artifact.item.scrool.混乱の書;
import dangeon.model.object.artifact.item.scrool.自爆の書;
import dangeon.model.object.artifact.item.spellcard.アリスのカード;
import dangeon.model.object.artifact.item.spellcard.イナバのカード;
import dangeon.model.object.artifact.item.spellcard.チルノのカード;
import dangeon.model.object.artifact.item.spellcard.パチュリーのカード;
import dangeon.model.object.artifact.item.spellcard.パルスィのカード;
import dangeon.model.object.artifact.item.spellcard.フランドールのカード;
import dangeon.model.object.artifact.item.spellcard.プリズムリバー三姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.ミスティアのカード;
import dangeon.model.object.artifact.item.spellcard.リグルのカード;
import dangeon.model.object.artifact.item.spellcard.ルーミアのカード;
import dangeon.model.object.artifact.item.spellcard.レティのカード;
import dangeon.model.object.artifact.item.spellcard.レミリアのカード;
import dangeon.model.object.artifact.item.spellcard.一輪雲山のカード;
import dangeon.model.object.artifact.item.spellcard.上白沢慧音のカード;
import dangeon.model.object.artifact.item.spellcard.八意永琳のカード;
import dangeon.model.object.artifact.item.spellcard.八雲紫のカード;
import dangeon.model.object.artifact.item.spellcard.八雲藍のカード;
import dangeon.model.object.artifact.item.spellcard.十六夜咲夜のカード;
import dangeon.model.object.artifact.item.spellcard.古明地さとりのカード;
import dangeon.model.object.artifact.item.spellcard.因幡てゐのカード;
import dangeon.model.object.artifact.item.spellcard.多々良小傘のカード;
import dangeon.model.object.artifact.item.spellcard.宮古芳香のカード;
import dangeon.model.object.artifact.item.spellcard.射命丸文のカード;
import dangeon.model.object.artifact.item.spellcard.幽谷響子のカード;
import dangeon.model.object.artifact.item.spellcard.星熊勇儀のカード;
import dangeon.model.object.artifact.item.spellcard.村紗水蜜のカード;
import dangeon.model.object.artifact.item.spellcard.橙のカード;
import dangeon.model.object.artifact.item.spellcard.河城にとりのカード;
import dangeon.model.object.artifact.item.spellcard.秋姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.紅美鈴のカード;
import dangeon.model.object.artifact.item.spellcard.蓬莱山輝夜のカード;
import dangeon.model.object.artifact.item.spellcard.藤原妹紅のカード;
import dangeon.model.object.artifact.item.spellcard.西行寺幽々子のカード;
import dangeon.model.object.artifact.item.spellcard.鍵山雛のカード;
import dangeon.model.object.artifact.item.spellcard.霍青娥のカード;
import dangeon.model.object.artifact.item.spellcard.霧雨魔理沙のカード;
import dangeon.model.object.artifact.item.spellcard.魂魄妖夢のカード;
import dangeon.model.object.artifact.item.spellcard.黒谷ヤマメのカード;
import dangeon.model.object.artifact.item.staff.一時しのぎの杖;
import dangeon.model.object.artifact.item.staff.金縛りの杖;
import dangeon.model.object.artifact.item.staff.鈍足の杖;

public class ItemTemplate {
	private static Point P = new Point(0, 0);
	public ArrayList<ListArtifact> LIST = new ArrayList<ListArtifact>();
	public static ItemTemplate ENKAI = new ItemTemplate(0, 1, 2, 3);
	public static ItemTemplate SYUUKAI = new ItemTemplate(0, 4, 5, 6);
	public final int[] number;

	public ItemTemplate(int... i) {
		number = i;
		LIST.add(list1());
		LIST.add(list2());
		LIST.add(list3());
		LIST.add(list4());
		LIST.add(list6());
		LIST.add(list7());
		LIST.add(list8());
	}

	/**
	 * 
	 * @param dungeon
	 * @return
	 */
	public ArrayList<Base_Artifact> getSelect(int dungeon) {
		return select(number[dungeon]);
	}

	private ListArtifact list1() {
		ListArtifact list = new ListArtifact();
		list.add(new 鉄の矢(P, false).createArrowRest(49));
		list.add(new おにぎりの書(P));
		list.add(new おにぎりの書(P));
		list.add(new いかづちの書(P));
		list.add(new いかづちの書(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new 混乱の書(P));
		list.add(new 混乱の書(P));
		list.add(new 撒き餌の書(P));
		list.add(new 撒き餌の書(P));
		list.add(new 天邪鬼の書(P));
		list.add(new 天邪鬼の書(P));
		list.add(new 大部屋の書(P));
		list.add(new 大部屋の書(P));
		list.add(new 自爆の書(P));
		list.add(new 自爆の書(P));
		// list.add(new 複製の書(P));
		// list.add(new 複製の書(P));
		// list.add(new 複製の書(P));
		// list.add(new 複製の書(P));
		// list.add(new 複製の書(P));
		// list.add(new 複製の書(P));
		list.add(new バクスイの書(P));
		list.add(new バクスイの書(P));
		list.add(new 明かりの書(P));
		list.add(new 明かりの書(P));
		list.add(new 明かりの書(P));
		list.add(new 明かりの書(P));
		return list;
	}

	private ListArtifact list2() {
		ListArtifact list = new ListArtifact();
		list.add(new 鉄の矢(P, false).createArrowRest(29));
		list.add(new 西行寺幽々子のカード(P).createSpellCard(false, 10));
		list.add(new 八雲紫のカード(P).createSpellCard(false, 10));
		list.add(new 八雲藍のカード(P).createSpellCard(false, 0));
		list.add(new 魂魄妖夢のカード(P).createSpellCard(false, 0));
		list.add(new 橙のカード(P).createSpellCard(false, 0));
		list.add(new アリスのカード(P).createSpellCard(false, 0));
		list.add(new チルノのカード(P).createSpellCard(false, 0));
		list.add(new レティのカード(P).createSpellCard(false, 0));
		list.add(new Disc(P, "妖々夢", "妖々夢"));
		list.add(new Disc(P, "妖々夢", "妖々夢"));
		list.add(new Disc(P, "妖々夢", "妖々夢"));
		list.add(new Disc(P, "妖々夢", "妖々夢"));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new 妹切草(P));
		list.add(new 妹切草(P));
		list.add(new 姉切草(P));
		list.add(new 姉切草(P));
		list.add(new ドラゴン草(P));
		list.add(new 姉切草(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new 混乱の書(P));
		list.add(new バクスイの書(P));
		list.add(new 明かりの書(P));
		list.add(new 一時しのぎの杖(P));
		list.add(new 金縛りの杖(P));
		return list;
	}

	private ListArtifact list3() {
		ListArtifact list = new ListArtifact();
		list.add(new ミニ八卦炉(P, false).createArrowRest(29));
		list.add(new レミリアのカード(P).createSpellCard(false, 10));
		list.add(new 紅美鈴のカード(P).createSpellCard(false, 10));
		list.add(new 十六夜咲夜のカード(P).createSpellCard(false, 0));
		list.add(new フランドールのカード(P).createSpellCard(false, 0));
		list.add(new パチュリーのカード(P).createSpellCard(false, 0));
		list.add(new ルーミアのカード(P).createSpellCard(false, 0));
		list.add(new チルノのカード(P).createSpellCard(false, 0));
		list.add(new Disc(P, "紅魔郷", "紅魔郷"));
		list.add(new Disc(P, "紅魔郷", "紅魔郷"));
		list.add(new Disc(P, "紅魔郷", "紅魔郷"));
		list.add(new Disc(P, "紅魔郷", "紅魔郷"));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new 妹切草(P));
		list.add(new 姉切草(P));
		list.add(new ドラゴン草(P));
		list.add(new ドラゴン草(P));
		list.add(new 高飛び草(P));
		list.add(new 高飛び草(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new 混乱の書(P));
		list.add(new バクスイの書(P));
		list.add(new 明かりの書(P));
		list.add(new 明かりの書(P));
		list.add(new 一時しのぎの杖(P));
		list.add(new 金縛りの杖(P));
		return list;
	}

	private ListArtifact list4() {
		ListArtifact list = new ListArtifact();
		list.add(new 大砲の弾(P, false).createArrowRest(19));
		list.add(new レティのカード(P).createSpellCard(false, 10));
		list.add(new ルーミアのカード(P).createSpellCard(false, 10));
		list.add(new リグルのカード(P).createSpellCard(false, 2));
		list.add(new 秋姉妹のカード(P).createSpellCard(false, 2));
		list.add(new 黒谷ヤマメのカード(P).createSpellCard(false, 2));
		list.add(new 西行寺幽々子のカード(P).createSpellCard(false, 2));
		list.add(new ミスティアのカード(P).createSpellCard(false, 2));
		list.add(new Disc(P, "紅魔郷", "妖々夢"));
		list.add(new Disc(P, "永夜抄", "風神録"));
		list.add(new Disc(P, "地霊殿", "星蓮船"));
		list.add(new Disc(P, "神霊廟", "花映塚"));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new 妹切草(P));
		list.add(new 姉切草(P));
		list.add(new 混乱草(P));
		list.add(new ドラゴン草(P));
		list.add(new ドラゴン草(P));
		list.add(new 高飛び草(P));
		list.add(new 高飛び草(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new 混乱の書(P));
		list.add(new バクスイの書(P));
		list.add(new 明かりの書(P));
		list.add(new 一時しのぎの杖(P));
		list.add(new 金縛りの杖(P));
		return list;
	}

	private ListArtifact list5() {
		ListArtifact list = new ListArtifact();
		list.add(new 鉄の矢(P, false).createArrowRest(29));
		list.add(new 蓬莱山輝夜のカード(P).createSpellCard(false, 10));
		list.add(new 八意永琳のカード(P).createSpellCard(false, 10));
		list.add(new 因幡てゐのカード(P).createSpellCard(false, 10));
		list.add(new 藤原妹紅のカード(P).createSpellCard(false, 0));
		list.add(new 上白沢慧音のカード(P).createSpellCard(false, 0));
		list.add(new イナバのカード(P).createSpellCard(false, 0));
		list.add(new リグルのカード(P).createSpellCard(false, 0));
		list.add(new ミスティアのカード(P).createSpellCard(false, 0));
		list.add(new Disc(P, "永夜抄", "永夜抄"));
		list.add(new Disc(P, "永夜抄", "永夜抄"));
		list.add(new Disc(P, "永夜抄", "永夜抄"));
		list.add(new Disc(P, "永夜抄", "永夜抄"));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new 妹切草(P));
		list.add(new 姉切草(P));
		list.add(new 混乱草(P));
		list.add(new ドラゴン草(P));
		list.add(new ドラゴン草(P));
		list.add(new 高飛び草(P));
		list.add(new 高飛び草(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new 混乱の書(P));
		list.add(new バクスイの書(P));
		list.add(new 明かりの書(P));
		list.add(new 一時しのぎの杖(P));
		list.add(new 金縛りの杖(P));
		return list;
	}

	/**
	 * ２ボス
	 * 
	 * @return
	 */
	private ListArtifact list6() {
		ListArtifact list = new ListArtifact();
		list.add(new 鉄の矢(P, false).createArrowRest(29));
		list.add(new 橙のカード(P).createSpellCard(false, 10));
		list.add(new 多々良小傘のカード(P).createSpellCard(false, 10));
		list.add(new チルノのカード(P).createSpellCard(false, 2));
		list.add(new ミスティアのカード(P).createSpellCard(false, 2));
		list.add(new 鍵山雛のカード(P).createSpellCard(false, 2));
		list.add(new パルスィのカード(P).createSpellCard(false, 2));
		list.add(new 幽谷響子のカード(P).createSpellCard(false, 2));
		list.add(new Disc(P, "紅魔郷", "妖々夢"));
		list.add(new Disc(P, "永夜抄", "風神録"));
		list.add(new Disc(P, "地霊殿", "星蓮船"));
		list.add(new Disc(P, "神霊廟", "花映塚"));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new 妹切草(P));
		list.add(new 姉切草(P));
		list.add(new すばやさ草(P));
		list.add(new ドラゴン草(P));
		list.add(new 鳳凰の種(P));
		list.add(new 高飛び草(P));
		list.add(new 高飛び草(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new 混乱の書(P));
		list.add(new バクスイの書(P));
		list.add(new 鈍足の杖(P).addStaffRest(5));
		list.add(new 一時しのぎの杖(P).addStaffRest(5));
		list.add(new 金縛りの杖(P).addStaffRest(5));
		return list;
	}

	/**
	 * 3ボス
	 * 
	 * @return
	 */
	private ListArtifact list7() {
		ListArtifact list = new ListArtifact();
		list.add(new 鉄の矢(P, false).createArrowRest(29));
		list.add(new 紅美鈴のカード(P).createSpellCard(false, 10));
		list.add(new アリスのカード(P).createSpellCard(false, 10));
		list.add(new 上白沢慧音のカード(P).createSpellCard(false, 2));
		list.add(new 河城にとりのカード(P).createSpellCard(false, 2));
		list.add(new 星熊勇儀のカード(P).createSpellCard(false, 2));
		list.add(new 一輪雲山のカード(P).createSpellCard(false, 2));
		list.add(new 宮古芳香のカード(P).createSpellCard(false, 2));
		list.add(new Disc(P, "紅魔郷", "妖々夢"));
		list.add(new Disc(P, "永夜抄", "風神録"));
		list.add(new Disc(P, "地霊殿", "星蓮船"));
		list.add(new Disc(P, "神霊廟", "花映塚"));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new 妹切草(P));
		list.add(new 姉切草(P));
		list.add(new すばやさ草(P));
		list.add(new ドラゴン草(P));
		list.add(new 鳳凰の種(P));
		list.add(new 高飛び草(P));
		list.add(new 高飛び草(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new 混乱の書(P));
		list.add(new バクスイの書(P));
		list.add(new 鈍足の杖(P).addStaffRest(5));
		list.add(new 一時しのぎの杖(P).addStaffRest(5));
		list.add(new 金縛りの杖(P).addStaffRest(5));
		return list;
	}

	/**
	 * 4ボス
	 * 
	 * @return
	 */
	private ListArtifact list8() {
		ListArtifact list = new ListArtifact();
		list.add(new 鉄の矢(P, false).createArrowRest(29));
		list.add(new パチュリーのカード(P).createSpellCard(false, 10));
		list.add(new 霧雨魔理沙のカード(P).createSpellCard(false, 10));
		list.add(new 射命丸文のカード(P).createSpellCard(false, 2));
		list.add(new 古明地さとりのカード(P).createSpellCard(false, 2));
		list.add(new 村紗水蜜のカード(P).createSpellCard(false, 2));
		list.add(new プリズムリバー三姉妹のカード(P).createSpellCard(false, 2));
		list.add(new 霍青娥のカード(P).createSpellCard(false, 2));
		list.add(new Disc(P, "紅魔郷", "妖々夢"));
		list.add(new Disc(P, "永夜抄", "風神録"));
		list.add(new Disc(P, "地霊殿", "星蓮船"));
		list.add(new Disc(P, "神霊廟", "花映塚"));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new グリモワール(P));
		list.add(new 妹切草(P));
		list.add(new 姉切草(P));
		list.add(new すばやさ草(P));
		list.add(new ドラゴン草(P));
		list.add(new 鳳凰の種(P));
		list.add(new 高飛び草(P));
		list.add(new 高飛び草(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new ダンジョン攻略本(P));
		list.add(new 混乱の書(P));
		list.add(new バクスイの書(P));
		list.add(new 鈍足の杖(P).addStaffRest(5));
		list.add(new 一時しのぎの杖(P).addStaffRest(5));
		list.add(new 金縛りの杖(P).addStaffRest(5));
		return list;
	}

	public void reset() {
		LIST.clear();
		LIST.add(list1());
		LIST.add(list2());
		LIST.add(list3());
		LIST.add(list4());
		LIST.add(list6());
		LIST.add(list7());
		LIST.add(list8());
	}

	public ArrayList<Base_Artifact> select(int i) {
		reset();
		return LIST.get(i).getList();
	}
}
