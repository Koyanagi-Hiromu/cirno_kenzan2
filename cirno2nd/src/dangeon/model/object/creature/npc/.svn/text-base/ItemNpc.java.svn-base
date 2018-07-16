package dangeon.model.object.creature.npc;

import java.awt.Point;
import java.util.List;

import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.device.TEST;
import dangeon.model.object.artifact.item.arrow.ミニ八卦炉;
import dangeon.model.object.artifact.item.arrow.大砲の弾;
import dangeon.model.object.artifact.item.arrow.毒ナイフ;
import dangeon.model.object.artifact.item.arrow.鉄の矢;
import dangeon.model.object.artifact.item.disc.DiscA;
import dangeon.model.object.artifact.item.disc.DiscA_A;
import dangeon.model.object.artifact.item.grass.くちなし草;
import dangeon.model.object.artifact.item.grass.しあわせ草;
import dangeon.model.object.artifact.item.grass.すばやさ草;
import dangeon.model.object.artifact.item.grass.ちからの草;
import dangeon.model.object.artifact.item.grass.アイスの種;
import dangeon.model.object.artifact.item.grass.ドラゴン草;
import dangeon.model.object.artifact.item.grass.不幸の草;
import dangeon.model.object.artifact.item.grass.命の草;
import dangeon.model.object.artifact.item.grass.天使の草;
import dangeon.model.object.artifact.item.grass.妹切草;
import dangeon.model.object.artifact.item.grass.姉切草;
import dangeon.model.object.artifact.item.grass.感電草;
import dangeon.model.object.artifact.item.grass.毒消し草;
import dangeon.model.object.artifact.item.grass.毒草;
import dangeon.model.object.artifact.item.grass.氷結草;
import dangeon.model.object.artifact.item.grass.混乱草;
import dangeon.model.object.artifact.item.grass.灯草;
import dangeon.model.object.artifact.item.grass.睡眠草;
import dangeon.model.object.artifact.item.grass.胃拡張の種;
import dangeon.model.object.artifact.item.grass.胃縮小の種;
import dangeon.model.object.artifact.item.grass.苦無草;
import dangeon.model.object.artifact.item.grass.超不幸の草;
import dangeon.model.object.artifact.item.grass.金縛り草;
import dangeon.model.object.artifact.item.grass.高飛び草;
import dangeon.model.object.artifact.item.grass.鳳凰の種;
import dangeon.model.object.artifact.item.pot.お祓いの瓶;
import dangeon.model.object.artifact.item.pot.かき氷器;
import dangeon.model.object.artifact.item.pot.フランドールの瓶;
import dangeon.model.object.artifact.item.pot.優勝者の瓶;
import dangeon.model.object.artifact.item.pot.分裂の瓶;
import dangeon.model.object.artifact.item.pot.印の瓶;
import dangeon.model.object.artifact.item.pot.合成の瓶;
import dangeon.model.object.artifact.item.pot.変化の瓶;
import dangeon.model.object.artifact.item.pot.妹紅の瓶;
import dangeon.model.object.artifact.item.pot.小鈴の瓶;
import dangeon.model.object.artifact.item.pot.水瓶;
import dangeon.model.object.artifact.item.pot.空の瓶;
import dangeon.model.object.artifact.item.pot.聖瓶;
import dangeon.model.object.artifact.item.pot.蛍瓶;
import dangeon.model.object.artifact.item.pot.識別の瓶;
import dangeon.model.object.artifact.item.pot.針名丸の瓶;
import dangeon.model.object.artifact.item.pot.雛瓶;
import dangeon.model.object.artifact.item.pot.香霖の瓶;
import dangeon.model.object.artifact.item.ring.こいしのお忍びリボン;
import dangeon.model.object.artifact.item.ring.しあわせのリボン;
import dangeon.model.object.artifact.item.ring.フランの掘削リボン;
import dangeon.model.object.artifact.item.ring.メディスンの衰退リボン;
import dangeon.model.object.artifact.item.ring.リリーのホワイトリボン;
import dangeon.model.object.artifact.item.ring.ルーミアのリボン;
import dangeon.model.object.artifact.item.ring.レミリアの超回復リボン;
import dangeon.model.object.artifact.item.ring.吸収のリボン;
import dangeon.model.object.artifact.item.ring.大遠投のリボン;
import dangeon.model.object.artifact.item.ring.復活のリボン;
import dangeon.model.object.artifact.item.ring.慧音の識別リボン;
import dangeon.model.object.artifact.item.ring.探知のリボン;
import dangeon.model.object.artifact.item.ring.橙のぶっとびリボン;
import dangeon.model.object.artifact.item.ring.流行病のリボン;
import dangeon.model.object.artifact.item.ring.焼き芋のリボン;
import dangeon.model.object.artifact.item.ring.空の融合リボン;
import dangeon.model.object.artifact.item.ring.罠避けのリボン;
import dangeon.model.object.artifact.item.ring.萃香の頑強リボン;
import dangeon.model.object.artifact.item.ring.透視のリボン;
import dangeon.model.object.artifact.item.ring.閻魔の改心リボン;
import dangeon.model.object.artifact.item.ring.雛の御守リボン;
import dangeon.model.object.artifact.item.scrool.いかづちの書;
import dangeon.model.object.artifact.item.scrool.おにぎりの書;
import dangeon.model.object.artifact.item.scrool.おはらいの書;
import dangeon.model.object.artifact.item.scrool.グリモワール;
import dangeon.model.object.artifact.item.scrool.ダンジョン攻略本;
import dangeon.model.object.artifact.item.scrool.バクスイの書;
import dangeon.model.object.artifact.item.scrool.パワーアップの書;
import dangeon.model.object.artifact.item.scrool.メッキの書;
import dangeon.model.object.artifact.item.scrool.ワイルドカード;
import dangeon.model.object.artifact.item.scrool.人を殺せる書;
import dangeon.model.object.artifact.item.scrool.八咫烏の書;
import dangeon.model.object.artifact.item.scrool.切れ端;
import dangeon.model.object.artifact.item.scrool.大部屋の書;
import dangeon.model.object.artifact.item.scrool.天邪鬼の書;
import dangeon.model.object.artifact.item.scrool.封書モンスターハウス;
import dangeon.model.object.artifact.item.scrool.幻想郷縁起;
import dangeon.model.object.artifact.item.scrool.慧音の歴史書;
import dangeon.model.object.artifact.item.scrool.撒き餌の書;
import dangeon.model.object.artifact.item.scrool.敵倍速の書;
import dangeon.model.object.artifact.item.scrool.明かりの書;
import dangeon.model.object.artifact.item.scrool.氷の書;
import dangeon.model.object.artifact.item.scrool.混乱の書;
import dangeon.model.object.artifact.item.scrool.破裂の書;
import dangeon.model.object.artifact.item.scrool.罠師の書;
import dangeon.model.object.artifact.item.scrool.自爆の書;
import dangeon.model.object.artifact.item.scrool.解凍の書;
import dangeon.model.object.artifact.item.scrool.識別の書;
import dangeon.model.object.artifact.item.scrool.電光石火の書;
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
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.artifact.item.staff.いかづちの杖;
import dangeon.model.object.artifact.item.staff.しあわせの杖;
import dangeon.model.object.artifact.item.staff.イカリの杖;
import dangeon.model.object.artifact.item.staff.ハイテンションの杖;
import dangeon.model.object.artifact.item.staff.モノカの杖;
import dangeon.model.object.artifact.item.staff.一時しのぎの杖;
import dangeon.model.object.artifact.item.staff.不幸の杖;
import dangeon.model.object.artifact.item.staff.倍速の杖;
import dangeon.model.object.artifact.item.staff.吹き飛ばしの杖;
import dangeon.model.object.artifact.item.staff.回復の杖;
import dangeon.model.object.artifact.item.staff.場所替えの杖;
import dangeon.model.object.artifact.item.staff.封印の杖;
import dangeon.model.object.artifact.item.staff.引き寄せの杖;
import dangeon.model.object.artifact.item.staff.影縛りの杖;
import dangeon.model.object.artifact.item.staff.悪魔の杖;
import dangeon.model.object.artifact.item.staff.氷の杖;
import dangeon.model.object.artifact.item.staff.無意識の杖;
import dangeon.model.object.artifact.item.staff.邪法の杖;
import dangeon.model.object.artifact.item.staff.金縛りの杖;
import dangeon.model.object.artifact.item.staff.鈍足の杖;
import dangeon.model.object.artifact.trap.いかづちの罠;
import dangeon.model.object.artifact.trap.ねむりの罠;
import dangeon.model.object.artifact.trap.デロデロの罠;
import dangeon.model.object.artifact.trap.ハラヘリの罠;
import dangeon.model.object.artifact.trap.バネの罠;
import dangeon.model.object.artifact.trap.ワナの罠;
import dangeon.model.object.artifact.trap.丸太の罠;
import dangeon.model.object.artifact.trap.召喚の罠;
import dangeon.model.object.artifact.trap.呪いの罠;
import dangeon.model.object.artifact.trap.回転盤の罠;
import dangeon.model.object.artifact.trap.地雷の罠;
import dangeon.model.object.artifact.trap.小石の罠;
import dangeon.model.object.artifact.trap.影縫いの罠;
import dangeon.model.object.artifact.trap.毒の矢の罠;
import dangeon.model.object.artifact.trap.氷の罠;
import dangeon.model.object.artifact.trap.落とし穴の罠;
import dangeon.model.object.artifact.trap.装備外しの罠;
import dangeon.model.object.artifact.trap.警報機の罠;
import dangeon.model.object.artifact.trap.鈍足の罠;
import dangeon.model.object.artifact.trap.錆の罠;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class ItemNpc extends Base_NPC {
	private Point p = Player.me.getMassPoint().getLocation();
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String name = "ItemNpc";
	private final int index;

	public ItemNpc(Point p, int i) {
		super(p, name, false);
		index = i;
	}

	private void item0(List<Base_Artifact> items) {
		Message.set("=>【スペルカードチェック用】");
		items.add(new チルノのカード(p));
		items.add(new 因幡てゐのカード(p));
		items.add(new 射命丸文のカード(p));
		items.add(new 博麗霊夢のカード(p));
		items.add(new 霧雨魔理沙のカード(p));
		items.add(new 比那名居天子のカード(p));
		items.add(new プリズムリバー三姉妹のカード(p));
		items.add(new レミリアのカード(p));
		items.add(new 犬走椛のカード(p));
		items.add(new 今泉影狼のカード(p));
		items.add(new 八雲紫のカード(p));
		items.add(new 八雲藍のカード(p));
		items.add(new 寅丸星のカード(p));
		items.add(new 魂魄妖夢のカード(p));
		items.add(new 朱鷺子のカード(p));
		items.add(new 東風谷早苗のカード(p));
		items.add(new 秦こころのカード(p));
		items.add(new 黒谷ヤマメのカード(p));
		items.add(new 小野塚小町のカード(p));
		items.add(new リグルのカード(p));
		items.add(new ルーミアのカード(p));
		items.add(new 一輪雲山のカード(p));
		items.add(new 河城にとりのカード(p));
		items.add(new 古明地こいしのカード(p));
		items.add(new 古明地さとりのカード(p));
		items.add(new 四季映姫・ヤマザナドゥのカード(p));
		items.add(new 上白沢慧音のカード(p));
		items.add(new 星熊勇儀のカード(p));
		items.add(new 聖白蓮のカード(p));
		items.add(new 堀川雷鼓のカード(p));
		items.add(new 風見幽香のカード(p));
		items.add(new 永江衣玖のカード(p));
		items.add(new 少名針妙丸のカード(p));
		items.add(new 伊吹萃香のカード(p));
		items.add(new 紅美鈴のカード(p));
		items.add(new 姫海棠はたてのカード(p));
		items.add(new 鍵山雛のカード(p));
		items.add(new アリスのカード(p));
		items.add(new イナバのカード(p));
		items.add(new キスメのカード(p));
		items.add(new わかさぎ姫のカード(p));
		items.add(new 蘇我屠自古のカード(p));
		items.add(new 八坂神奈子のカード(p));
		items.add(new 蓬莱山輝夜のカード(p));
		items.add(new 物部布都のカード(p));
		items.add(new 火焔猫燐のカード(p));
		items.add(new パチュリーのカード(p));
		items.add(new フランドールのカード(p));
		items.add(new 十六夜咲夜のカード(p));
		items.add(new 西行寺幽々子のカード(p));
		items.add(new 藤原妹紅のカード(p));
		items.add(new 二ッ岩マミゾウのカード(p));
		items.add(new 八意永琳のカード(p));
		items.add(new 封獣ぬえのカード(p));
		items.add(new パルスィのカード(p));
		items.add(new 九十九姉妹のカード(p));
		items.add(new 橙のカード(p));
		items.add(new 赤蛮奇のカード(p));
		items.add(new 洩矢諏訪子のカード(p));
		items.add(new 鬼人正邪のカード(p));
		items.add(new ミスティアのカード(p));
		items.add(new レティのカード(p));
		items.add(new ナズーリンのカード(p));
		items.add(new 宮古芳香のカード(p));
		items.add(new 秋姉妹のカード(p));
		items.add(new 豊聡耳神子のカード(p));
		items.add(new 幽谷響子のカード(p));
		items.add(new 霊烏路空のカード(p));
		items.add(new 霍青娥のカード(p));
		items.add(new メディスンメランコリーのカード(p));
		items.add(new リリーホワイトのカード(p));
		items.add(new 小悪魔のカード(p));
		items.add(new 村紗水蜜のカード(p));
		items.add(new 多々良小傘のカード(p));
		items.add(new きもけーねのカード(p));
		items.add(new みすちーのカード(p));
		items.add(new ゆっくりのカード(p));
		items.add(new 大妖精のカード(p));
		items.add(new Exルーミアのカード(p));
		items.add(new 魅魔のカード(p));

	}

	private void item1(List<Base_Artifact> items) {
		Message.set("=>【草】");
		items.add(new Stairs(p));
		items.add(new 超不幸の草(p));
		items.add(new 鳳凰の種(p));
		items.add(new 天使の草(p));
		items.add(new しあわせ草(p));
		items.add(new 金縛り草(p));
		items.add(new 混乱草(p));
		items.add(new 睡眠草(p));
		items.add(new 灯草(p));
		items.add(new 毒草(p));
		items.add(new 姉切草(p));
		items.add(new アイスの種(p));
		items.add(new 高飛び草(p));
		items.add(new すばやさ草(p));
		items.add(new ちからの草(p));
		items.add(new ドラゴン草(p));
		items.add(new 胃拡張の種(p));
		items.add(new 胃縮小の種(p));
		items.add(new 感電草(p));
		items.add(new 妹切草(p));
		items.add(new 毒消し草(p));
		items.add(new 命の草(p));
		items.add(new 不幸の草(p));
		items.add(new くちなし草(p));
		items.add(new 苦無草(p));
		items.add(new 氷結草(p));
		items.add(new 破裂の書(p));
		items.add(new 破裂の書(p));
		items.add(new 破裂の書(p));
	}

	private void item2(List<Base_Artifact> items) {
		Message.set("=>【消費アイテムチェック用（未完）】");
		items.add(new Stairs(p));
		items.add(new しあわせの杖(p));
		items.add(new 封印の杖(p));
		items.add(new 引き寄せの杖(p));
		items.add(new いかづちの杖(p));
		items.add(new 氷の杖(p));
		items.add(new 無意識の杖(p));
		items.add(new 不幸の杖(p));
		items.add(new 金縛りの杖(p));
		items.add(new 倍速の杖(p));
		items.add(new イカリの杖(p));
		items.add(new 悪魔の杖(p));
		items.add(new モノカの杖(p));
		items.add(new 邪法の杖(p));
		items.add(new 一時しのぎの杖(p));
		items.add(new 影縛りの杖(p));
		items.add(new 回復の杖(p));
		items.add(new 場所替えの杖(p));
		items.add(new 吹き飛ばしの杖(p));
		items.add(new 鈍足の杖(p));
		items.add(new ハイテンションの杖(p));
		items.add(new 破裂の書(p));
		items.add(new 破裂の書(p));
		items.add(new 破裂の書(p));
	}

	private void item3(List<Base_Artifact> items) {
		Message.set("=>【書】");
		items.add(new TEST());
		items.add(new 慧音の歴史書(p));
		items.add(new ワイルドカード(p));
		items.add(new 封書モンスターハウス(p));
		items.add(new 八咫烏の書(p));
		items.add(new ダンジョン攻略本(p));
		items.add(new バクスイの書(p));
		items.add(new グリモワール(p));
		items.add(new いかづちの書(p));
		items.add(new 電光石火の書(p));
		items.add(new 氷の書(p));
		items.add(new 解凍の書(p));
		items.add(new 混乱の書(p));
		items.add(new メッキの書(p));
		items.add(new 天邪鬼の書(p));
		items.add(new おにぎりの書(p));
		items.add(new おはらいの書(p));
		items.add(new パワーアップの書(p));
		items.add(new 識別の書(p));
		items.add(new 撒き餌の書(p));
		items.add(new 自爆の書(p));
		items.add(new 大部屋の書(p));
		items.add(new 敵倍速の書(p));
		items.add(new 破裂の書(p));
		items.add(new 明かりの書(p));
		items.add(new 罠師の書(p));
		items.add(new 人を殺せる書(p));
		items.add(new 幻想郷縁起(p));
		items.add(new 切れ端(p));

	}

	private void item4(List<Base_Artifact> items) {
		Message.set("=>【マップチェック用】");
		items.add(new 透視のリボン(p));
		items.add(new フランの掘削リボン(p));
		items.add(new 慧音の識別リボン(p));
		items.add(new 焼き芋のリボン(p));
		items.add(new 復活のリボン(p));
		items.add(new 橙のぶっとびリボン(p));
		items.add(new こいしのお忍びリボン(p));
		items.add(new 流行病のリボン(p));
		items.add(new レミリアの超回復リボン(p));
		items.add(new ルーミアのリボン(p));
		items.add(new しあわせのリボン(p));
		items.add(new 空の融合リボン(p));
		items.add(new 雛の御守リボン(p));
		items.add(new 閻魔の改心リボン(p));
		items.add(new メディスンの衰退リボン(p));
		items.add(new 大遠投のリボン(p));
		items.add(new リリーのホワイトリボン(p));
		items.add(new 吸収のリボン(p));
		items.add(new 探知のリボン(p));
		items.add(new 萃香の頑強リボン(p));
		items.add(new 罠避けのリボン(p));
	}

	private void item5(List<Base_Artifact> items) {
		Message.set("=>【射撃チェック用】");
		items.add(new 鉄の矢(p, true));
		items.add(new 毒ナイフ(p, true));
		items.add(new ミニ八卦炉(p, true));
		items.add(new 大砲の弾(p, true));
		items.add(new 鉄の矢(p, true).setArrowRest(99));
		items.add(new 毒ナイフ(p, true).setArrowRest(99));
		items.add(new ミニ八卦炉(p, true).setArrowRest(99));
		items.add(new 大砲の弾(p, true).setArrowRest(99));
		items.add(new メッキの書(p));
		items.add(new 合成の瓶(p));
		items.add(new 合成の瓶(p));
	}

	private void item6(List<Base_Artifact> items) {
		Message.set("=>【罠用】");
		items.add(new Stairs(p));
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new いかづちの罠(p).setJapanese());
		items.add(new デロデロの罠(p).setJapanese());
		items.add(new ねむりの罠(p).setJapanese());
		items.add(new バネの罠(p).setJapanese());
		items.add(new ハラヘリの罠(p).setJapanese());
		items.add(new ワナの罠(p).setJapanese());
		items.add(new 影縫いの罠(p).setJapanese());
		items.add(new 回転盤の罠(p).setJapanese());
		items.add(new 丸太の罠(p).setJapanese());
		items.add(new 警報機の罠(p).setJapanese());
		items.add(new 錆の罠(p).setJapanese());
		items.add(new 呪いの罠(p).setJapanese());
		items.add(new 召喚の罠(p).setJapanese());
		items.add(new 小石の罠(p).setJapanese());
		items.add(new 装備外しの罠(p).setJapanese());
		items.add(new 地雷の罠(p).setJapanese());
		items.add(new 毒の矢の罠(p).setJapanese());
		items.add(new 鈍足の罠(p).setJapanese());
		items.add(new 氷の罠(p).setJapanese());
		items.add(new 落とし穴の罠(p).setJapanese());
		items.add(new TEST());
	}

	private void item7(List<Base_Artifact> items) {
		Message.set("=>【瓶チェック用】");
		items.add(new 印の瓶(p));
		items.add(new 合成の瓶(p));
		items.add(new 変化の瓶(p));
		items.add(new お祓いの瓶(p));
		items.add(new 識別の瓶(p));
		items.add(new かき氷器(p));
		items.add(new 水瓶(p));
		items.add(new 空の瓶(p));
		items.add(new 蛍瓶(p));
		items.add(new 針名丸の瓶(p));
		items.add(new 聖瓶(p));
		items.add(new フランドールの瓶(p));
		items.add(new 雛瓶(p));
		items.add(new 妹紅の瓶(p));
		items.add(new 小鈴の瓶(p));
		items.add(new 香霖の瓶(p));
		items.add(new 分裂の瓶(p));
		items.add(new 優勝者の瓶(p));

		items.add(new DiscA(p));
		items.add(new DiscA(p));
		items.add(new DiscA(p));
		items.add(new DiscA(p));
		items.add(new DiscA_A(p));
		items.add(new DiscA_A(p));
		items.add(new DiscA_A(p));
		items.add(new DiscA_A(p));
	}

	@Override
	public void message() {
		Belongings.getListItems().add(new TEST());
		Belongings.getListItems().clear();
		Enchant.allRemove();
		Message.set("手持ちアイテムを変更します");
		new Base_SelectBox("カード", "草", "書", "リボン", "杖", "射撃", "罠", "瓶&DISC",
				"杖＆リボン", "やめる") {
			@Override
			public void pushEnter(int y) {
				switch (y) {
				case 0:
					item0(Belongings.getListItems());
					end();
					break;
				case 1:
					item1(Belongings.getListItems());
					end();
					break;
				case 2:
					item3(Belongings.getListItems());
					end();
					break;
				case 3:
					item4(Belongings.getListItems());
					end();
					break;
				case 4:
					item2(Belongings.getListItems());
					end();
					break;
				case 5:
					item5(Belongings.getListItems());
					end();
					break;
				case 6:
					item6(Belongings.getListItems());
					end();
					break;
				case 7:
					item7(Belongings.getListItems());
					end();
					break;
				case 8:
					Belongings.getListItems().add(new パルスィのカード(p));
					item2(Belongings.getListItems());
					item4(Belongings.getListItems());
					end();
					break;
				default:
					end();
					break;
				}
				for (Base_Artifact a : Belongings.getListItems()) {
					if (a instanceof SpellCard) {
						a.setForgeValue(90);
						a.check();
					} else if (a instanceof Staff) {
						((Staff) a).addStaffRest(90);
					}
				}
			}
		};

		// if (index == 0) {
		// item0(Belongings.getListItems());
		// } else if (index == 1) {
		// item1(Belongings.getListItems());
		// } else if (index == 2) {
		// item2(Belongings.getListItems());
		// } else if (index == 3) {
		// item3(Belongings.getListItems());
		// } else if (index == 4) {
		// item4(Belongings.getListItems());
		// } else if (index == 5) {
		// item5(Belongings.getListItems());
		// } else if (index == 6) {
		// item6(Belongings.getListItems());
		// }
	}

}
