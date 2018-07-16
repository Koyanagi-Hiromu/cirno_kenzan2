package dangeon.latest.scene.event.staffroll;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.model.object.artifact.item.spellcard.Exルーミアのカード;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.きもけーねのカード;
import dangeon.model.object.artifact.item.spellcard.みすちーのカード;
import dangeon.model.object.artifact.item.spellcard.ゆっくりのカード;
import dangeon.model.object.artifact.item.spellcard.わかさぎ姫のカード;
import dangeon.model.object.artifact.item.spellcard.アリスのカード;
import dangeon.model.object.artifact.item.spellcard.イナバのカード;
import dangeon.model.object.artifact.item.spellcard.キスメのカード;
import dangeon.model.object.artifact.item.spellcard.チルノのカード;
import dangeon.model.object.artifact.item.spellcard.ナズーリンのカード;
import dangeon.model.object.artifact.item.spellcard.パチュリーのカード;
import dangeon.model.object.artifact.item.spellcard.パルスィのカード;
import dangeon.model.object.artifact.item.spellcard.フランドールのカード;
import dangeon.model.object.artifact.item.spellcard.プリズムリバー三姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.ミスティアのカード;
import dangeon.model.object.artifact.item.spellcard.メディスンメランコリーのカード;
import dangeon.model.object.artifact.item.spellcard.リグルのカード;
import dangeon.model.object.artifact.item.spellcard.リリーホワイトのカード;
import dangeon.model.object.artifact.item.spellcard.ルーミアのカード;
import dangeon.model.object.artifact.item.spellcard.レティのカード;
import dangeon.model.object.artifact.item.spellcard.レミリアのカード;
import dangeon.model.object.artifact.item.spellcard.一輪雲山のカード;
import dangeon.model.object.artifact.item.spellcard.上白沢慧音のカード;
import dangeon.model.object.artifact.item.spellcard.九十九姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.二ッ岩マミゾウのカード;
import dangeon.model.object.artifact.item.spellcard.今泉影狼のカード;
import dangeon.model.object.artifact.item.spellcard.伊吹萃香のカード;
import dangeon.model.object.artifact.item.spellcard.八坂神奈子のカード;
import dangeon.model.object.artifact.item.spellcard.八意永琳のカード;
import dangeon.model.object.artifact.item.spellcard.八雲紫のカード;
import dangeon.model.object.artifact.item.spellcard.八雲藍のカード;
import dangeon.model.object.artifact.item.spellcard.十六夜咲夜のカード;
import dangeon.model.object.artifact.item.spellcard.博麗霊夢のカード;
import dangeon.model.object.artifact.item.spellcard.古明地こいしのカード;
import dangeon.model.object.artifact.item.spellcard.古明地さとりのカード;
import dangeon.model.object.artifact.item.spellcard.四季映姫・ヤマザナドゥのカード;
import dangeon.model.object.artifact.item.spellcard.因幡てゐのカード;
import dangeon.model.object.artifact.item.spellcard.堀川雷鼓のカード;
import dangeon.model.object.artifact.item.spellcard.多々良小傘のカード;
import dangeon.model.object.artifact.item.spellcard.大妖精のカード;
import dangeon.model.object.artifact.item.spellcard.姫海棠はたてのカード;
import dangeon.model.object.artifact.item.spellcard.宮古芳香のカード;
import dangeon.model.object.artifact.item.spellcard.寅丸星のカード;
import dangeon.model.object.artifact.item.spellcard.封獣ぬえのカード;
import dangeon.model.object.artifact.item.spellcard.射命丸文のカード;
import dangeon.model.object.artifact.item.spellcard.小悪魔のカード;
import dangeon.model.object.artifact.item.spellcard.小野塚小町のカード;
import dangeon.model.object.artifact.item.spellcard.少名針妙丸のカード;
import dangeon.model.object.artifact.item.spellcard.幽谷響子のカード;
import dangeon.model.object.artifact.item.spellcard.星熊勇儀のカード;
import dangeon.model.object.artifact.item.spellcard.朱鷺子のカード;
import dangeon.model.object.artifact.item.spellcard.村紗水蜜のカード;
import dangeon.model.object.artifact.item.spellcard.東風谷早苗のカード;
import dangeon.model.object.artifact.item.spellcard.橙のカード;
import dangeon.model.object.artifact.item.spellcard.比那名居天子のカード;
import dangeon.model.object.artifact.item.spellcard.永江衣玖のカード;
import dangeon.model.object.artifact.item.spellcard.河城にとりのカード;
import dangeon.model.object.artifact.item.spellcard.洩矢諏訪子のカード;
import dangeon.model.object.artifact.item.spellcard.火焔猫燐のカード;
import dangeon.model.object.artifact.item.spellcard.物部布都のカード;
import dangeon.model.object.artifact.item.spellcard.犬走椛のカード;
import dangeon.model.object.artifact.item.spellcard.秋姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.秦こころのカード;
import dangeon.model.object.artifact.item.spellcard.紅美鈴のカード;
import dangeon.model.object.artifact.item.spellcard.聖白蓮のカード;
import dangeon.model.object.artifact.item.spellcard.蓬莱山輝夜のカード;
import dangeon.model.object.artifact.item.spellcard.藤原妹紅のカード;
import dangeon.model.object.artifact.item.spellcard.蘇我屠自古のカード;
import dangeon.model.object.artifact.item.spellcard.西行寺幽々子のカード;
import dangeon.model.object.artifact.item.spellcard.豊聡耳神子のカード;
import dangeon.model.object.artifact.item.spellcard.赤蛮奇のカード;
import dangeon.model.object.artifact.item.spellcard.鍵山雛のカード;
import dangeon.model.object.artifact.item.spellcard.霊烏路空のカード;
import dangeon.model.object.artifact.item.spellcard.霍青娥のカード;
import dangeon.model.object.artifact.item.spellcard.霧雨魔理沙のカード;
import dangeon.model.object.artifact.item.spellcard.風見幽香のカード;
import dangeon.model.object.artifact.item.spellcard.鬼人正邪のカード;
import dangeon.model.object.artifact.item.spellcard.魂魄妖夢のカード;
import dangeon.model.object.artifact.item.spellcard.魅魔のカード;
import dangeon.model.object.artifact.item.spellcard.黒谷ヤマメのカード;

public class StaffRollCard {

	public final ArrayList<SpellCard> LIST;

	public StaffRollCard() {
		LIST = new ArrayList<SpellCard>();
		initLIST();
	}

	private void add(SpellCard e) {
		LIST.add(e);
	}

	public SpellCard get() {
		return LIST.remove(0);
	}

	public void initLIST() {
		Point p = new Point();
		// 紅
		// add(new 魅魔のカード(p));
		add(new ルーミアのカード(p));
		add(new Exルーミアのカード(p));
		add(new 大妖精のカード(p));
		// add(new Ex大妖精のカード(p));
		add(new チルノのカード(p));
		add(new 紅美鈴のカード(p));
		add(new 小悪魔のカード(p));
		add(new パチュリーのカード(p));
		add(new 十六夜咲夜のカード(p));
		add(new レミリアのカード(p));
		add(new フランドールのカード(p));

		add(new レティのカード(p));
		add(new 橙のカード(p));
		add(new アリスのカード(p));
		add(new リリーホワイトのカード(p));
		add(new プリズムリバー三姉妹のカード(p));
		add(new 魂魄妖夢のカード(p));
		add(new 西行寺幽々子のカード(p));
		add(new 八雲藍のカード(p));
		add(new 八雲紫のカード(p));

		add(new リグルのカード(p));
		add(new ミスティアのカード(p));
		add(new みすちーのカード(p));
		add(new 上白沢慧音のカード(p));
		add(new きもけーねのカード(p));
		add(new 霧雨魔理沙のカード(p));
		add(new 博麗霊夢のカード(p));
		add(new 因幡てゐのカード(p));
		add(new イナバのカード(p));
		add(new 八意永琳のカード(p));
		add(new 蓬莱山輝夜のカード(p));
		add(new 藤原妹紅のカード(p));

		add(new メディスンメランコリーのカード(p));
		add(new 射命丸文のカード(p));
		add(new 小野塚小町のカード(p));
		add(new 四季映姫・ヤマザナドゥのカード(p));
		add(new 風見幽香のカード(p));

		add(new 秋姉妹のカード(p));
		add(new 鍵山雛のカード(p));
		add(new 河城にとりのカード(p));
		add(new 犬走椛のカード(p));
		add(new 東風谷早苗のカード(p));
		add(new 八坂神奈子のカード(p));
		add(new 洩矢諏訪子のカード(p));

		add(new キスメのカード(p));
		add(new 黒谷ヤマメのカード(p));
		add(new パルスィのカード(p));
		add(new 星熊勇儀のカード(p));
		add(new 古明地さとりのカード(p));
		add(new 火焔猫燐のカード(p));
		add(new 霊烏路空のカード(p));
		add(new 古明地こいしのカード(p));

		add(new ナズーリンのカード(p));
		add(new 多々良小傘のカード(p));
		add(new 一輪雲山のカード(p));
		add(new 村紗水蜜のカード(p));
		add(new 寅丸星のカード(p));
		add(new 聖白蓮のカード(p));
		add(new 封獣ぬえのカード(p));

		add(new 幽谷響子のカード(p));
		add(new 宮古芳香のカード(p));
		add(new 霍青娥のカード(p));
		add(new 蘇我屠自古のカード(p));
		add(new 物部布都のカード(p));
		add(new 豊聡耳神子のカード(p));
		add(new 二ッ岩マミゾウのカード(p));

		add(new わかさぎ姫のカード(p));
		add(new 赤蛮奇のカード(p));
		add(new 今泉影狼のカード(p));
		add(new 九十九姉妹のカード(p));
		add(new 鬼人正邪のカード(p));
		add(new 少名針妙丸のカード(p));
		add(new 堀川雷鼓のカード(p));

		add(new 姫海棠はたてのカード(p));
		add(new 伊吹萃香のカード(p));
		add(new 永江衣玖のカード(p));
		add(new 比那名居天子のカード(p));
		add(new 秦こころのカード(p));

		// add(new 三月精のカード(p));
		add(new 朱鷺子のカード(p));

		add(new ゆっくりのカード(p));
		add(new 魅魔のカード(p));
	}

	public boolean isEmpty() {
		return LIST.isEmpty();
	}
}
