package dangeon.model.config.table;

import dangeon.model.object.creature.enemy.きもけーね;
import dangeon.model.object.creature.enemy.にとり;
import dangeon.model.object.creature.enemy.ゆっくり霊夢;
import dangeon.model.object.creature.enemy.わかさぎ姫;
import dangeon.model.object.creature.enemy.アリス;
import dangeon.model.object.creature.enemy.チルノ;
import dangeon.model.object.creature.enemy.ナズーリン;
import dangeon.model.object.creature.enemy.パチュリー;
import dangeon.model.object.creature.enemy.パルスィ;
import dangeon.model.object.creature.enemy.フランドール;
import dangeon.model.object.creature.enemy.ミスティア;
import dangeon.model.object.creature.enemy.メディスン;
import dangeon.model.object.creature.enemy.リリーホワイト;
import dangeon.model.object.creature.enemy.レミリア;
import dangeon.model.object.creature.enemy.上白沢慧音;
import dangeon.model.object.creature.enemy.二ッ岩マミゾウ;
import dangeon.model.object.creature.enemy.今泉影狼;
import dangeon.model.object.creature.enemy.伊吹萃香;
import dangeon.model.object.creature.enemy.先代巫女;
import dangeon.model.object.creature.enemy.八坂神奈子;
import dangeon.model.object.creature.enemy.八意永琳;
import dangeon.model.object.creature.enemy.八雲紫;
import dangeon.model.object.creature.enemy.八雲藍;
import dangeon.model.object.creature.enemy.十六夜咲夜;
import dangeon.model.object.creature.enemy.博麗霊夢;
import dangeon.model.object.creature.enemy.古明地こいし;
import dangeon.model.object.creature.enemy.因幡てゐ;
import dangeon.model.object.creature.enemy.堀川雷鼓;
import dangeon.model.object.creature.enemy.多々良小傘;
import dangeon.model.object.creature.enemy.姫海棠はたて;
import dangeon.model.object.creature.enemy.宮古芳香;
import dangeon.model.object.creature.enemy.寅丸星;
import dangeon.model.object.creature.enemy.封獣ぬえ;
import dangeon.model.object.creature.enemy.射命丸文;
import dangeon.model.object.creature.enemy.小悪魔;
import dangeon.model.object.creature.enemy.幽谷響子;
import dangeon.model.object.creature.enemy.村紗水蜜;
import dangeon.model.object.creature.enemy.東風谷早苗;
import dangeon.model.object.creature.enemy.橙;
import dangeon.model.object.creature.enemy.比那名居天子;
import dangeon.model.object.creature.enemy.永江衣玖;
import dangeon.model.object.creature.enemy.洩矢諏訪子;
import dangeon.model.object.creature.enemy.火焔猫燐;
import dangeon.model.object.creature.enemy.物部布都;
import dangeon.model.object.creature.enemy.犬走椛;
import dangeon.model.object.creature.enemy.秦こころ;
import dangeon.model.object.creature.enemy.紅美鈴;
import dangeon.model.object.creature.enemy.聖白蓮;
import dangeon.model.object.creature.enemy.藤原妹紅;
import dangeon.model.object.creature.enemy.蘇我屠自古;
import dangeon.model.object.creature.enemy.西行寺幽々子;
import dangeon.model.object.creature.enemy.鈴仙・優曇華院・イナバ;
import dangeon.model.object.creature.enemy.鍵山雛;
import dangeon.model.object.creature.enemy.霊烏路空;
import dangeon.model.object.creature.enemy.霍青娥;
import dangeon.model.object.creature.enemy.霧雨魔理沙;
import dangeon.model.object.creature.enemy.風見幽香;
import dangeon.model.object.creature.enemy.魂魄妖夢;
import dangeon.model.object.creature.enemy.魅魔;
import dangeon.model.object.creature.enemy.黄金ゆっくり;
import dangeon.model.object.creature.enemy.黒谷ヤマメ;

public enum SpecialMonsterHouse {
	//@formatter:off;
	ふらふら祭り(チルノ.class, リリーホワイト.class,伊吹萃香.class, 八雲紫.class,古明地こいし.class),
	殲滅部隊(フランドール.class, レミリア.class,ナズーリン.class, 比那名居天子.class,八雲藍.class),
	マジックギルド(小悪魔.class,パチュリー.class, 霧雨魔理沙.class, 聖白蓮.class, 風見幽香.class, アリス.class,魅魔.class),
	爆炎地帯(霊烏路空.class,パルスィ.class,藤原妹紅.class,紅美鈴.class),
	ビリビリルーム(永江衣玖.class,パチュリー.class,蘇我屠自古.class,堀川雷鼓.class,魅魔.class),
	投げ物選手権(八意永琳.class, 十六夜咲夜.class, 寅丸星.class, 村紗水蜜.class, 黒谷ヤマメ.class, 八坂神奈子.class),
	ドレインパーティ(メディスン.class, 洩矢諏訪子.class, 上白沢慧音.class, 八意永琳.class,宮古芳香.class),
	ゴーストスポット(魂魄妖夢.class, 西行寺幽々子.class, 村紗水蜜.class, 蘇我屠自古.class,宮古芳香.class,霍青娥.class,魅魔.class),
	ウォーターワールド(にとり.class,鍵山雛.class,洩矢諏訪子.class,わかさぎ姫.class,村紗水蜜.class,物部布都.class),
	八百万の神々( 鍵山雛.class,多々良小傘.class,東風谷早苗.class,洩矢諏訪子.class,八坂神奈子.class,秦こころ.class
			,堀川雷鼓.class),
	天狗の狩場(射命丸文.class,犬走椛.class,姫海棠はたて.class,ミスティア.class,霊烏路空.class),
	ワイルドカーペット(鈴仙・優曇華院・イナバ.class,因幡てゐ.class,橙.class,八雲藍.class,火焔猫燐.class,
			ナズーリン.class,寅丸星.class,封獣ぬえ.class,二ッ岩マミゾウ.class,幽谷響子.class,今泉影狼.class,きもけーね.class),
	素敵な楽園(博麗霊夢.class,ゆっくり霊夢.class,黄金ゆっくり.class,先代巫女.class),
	ちびでかハウス();
	public static int getAllNumber() {
		return values().length;
	}

	//@formatter:on;
	private final String[] NAMES;

	SpecialMonsterHouse(Class<?>... clazz) {
		String[] str = new String[clazz.length];
		for (int i = 0; i < str.length; i++)
			str[i] = clazz[i].getSimpleName();
		NAMES = str;
	}

	public String[] getNames() {
		return NAMES;
	}
}
