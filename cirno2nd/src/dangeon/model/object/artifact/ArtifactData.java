package dangeon.model.object.artifact;

import dangeon.model.config.table.ItemTable;
import dangeon.model.map.Mass;

public enum ArtifactData {
	//@formatter:off
	CARD("spellcard","Exルーミアのカード",
			"アリスのカード",
			"イナバのカード",
			"キスメのカード",
			"きもけーねのカード",
			"チルノのカード",
			"ナズーリンのカード",
			"パチュリーのカード",
			"パルスィのカード",
			"フランドールのカード",
			"プリズムリバー三姉妹のカード",
			"みすちーのカード",
			"ミスティアのカード",
			"メディスンメランコリーのカード",
			"ゆっくりのカード",
			"リグルのカード",
			"リリーホワイトのカード",
			"ルーミアのカード",
			"レティのカード",
			"レミリアのカード",
			"わかさぎ姫のカード",
			"伊吹萃香のカード",
			"一輪雲山のカード",
			"因幡てゐのカード",
			"永江衣玖のカード",
			"洩矢諏訪子のカード",
			"河城にとりのカード",
			"火焔猫燐のカード",
			"宮古芳香のカード",
			"犬走椛のカード",
			"鍵山雛のカード",
			"古明地こいしのカード",
			"古明地さとりのカード",
			"紅美鈴のカード",
			"黒谷ヤマメのカード",
			"今泉影狼のカード",
			"魂魄妖夢のカード",
			"三月精のカード",
			"四季映姫・ヤマザナドゥのカード",
			"射命丸文のカード",
			"朱鷺子のカード",
			"秋姉妹のカード",
			"十六夜咲夜のカード",
			"小悪魔のカード",
			"小野塚小町のカード",
			"秦こころのカード",
			"上白沢慧音のカード",
			"星熊勇儀のカード",
			"聖白蓮のカード",
			"西行寺幽々子のカード",
			"赤蛮奇のカード",
			"蘇我屠自古のカード",
			"村紗水蜜のカード",
			"多々良小傘のカード",
			"大妖精のカード",
			"東風谷早苗のカード",
			"藤原妹紅のカード",
			"寅丸星のカード",
			"二ッ岩マミゾウのカード",
			"博麗霊夢のカード",
			"八意永琳のカード",
			"八雲紫のカード",
			"八雲藍のカード",
			"八坂神奈子のカード",
			"比那名居天子のカード",
			"姫海棠はたてのカード",
			"封獣ぬえのカード",
			"風見幽香のカード",
			"物部布都のカード",
			"蓬莱山輝夜のカード",
			"豊聡耳神子のカード",
			"霧雨魔理沙のカード",
			"幽谷響子のカード",
			"霊烏路空のカード",
			"橙のカード",
			"霍青娥のカード"),
			FOOD("food",
					"巨大なおにぎり",
					"大きなおにぎり",
					"腐ったおにぎり",
					"小さなおにぎり",
					"ナツメおにぎり"),
					ARROW("arrow",
							"大砲の弾",
							"鉄の矢",
							"毒ナイフ",
							"ミニ八卦炉"),
							GRASS("grass",
									"アイスの種",
									"しあわせ草",
									"すばやさ草",
									"ちからの草",
									"ドラゴン草",
									"めぐすり草",
									"胃拡張の種",
									"胃縮小の種",
									"感電草",
									"金縛り草",
									"高飛び草",
									"混乱草",
									"妹切草",
									"姉切草",
									"睡眠草",
									"天使の草",
									"灯草",
									"毒消し草",
									"毒草",
									"命の草",
									"鳳凰の種",
									"不幸の草"),
									RING("ring",
											"ルーミアのリボン",
											"お忍びリボン",
											"しあわせのリボン",
											"はたての念写リボン",
											"フランの掘削リボン",
											"メディスンの衰退リボン",
											"リリーのホワイトリボン",
											"空の融合リボン",
											"慧音の識別リボン",
											"焼き芋のリボン",
											"雛の御守リボン",
											"大遠投のリボン",
											"橙のぶっとびリボン",
											"閻魔の改心リボン",
											"レミリアの超回復リボン"
											),
											STAFF("staff",
													"いかづちの杖",
													"イカリの杖",
													"オオイカリの杖",
													"しあわせの杖",
													"パチュリーの杖",
													"悪魔の杖",
													"一時しのぎの杖",
													"影縛りの杖",
													"回復の杖",
													"金縛りの杖",
													"邪法の杖",
													"小悪魔の杖",
													"小悪魔秘蔵の杖",
													"場所替えの杖",
													"吹き飛ばしの杖",
													"大悪魔の杖",
													"倍速の杖",
													"鈍足の杖",
													"氷の杖",
													"不幸の杖",
													"封印の杖",
													"ハイテンションの杖"),
													SCROLL("scrool",
															"いかづちの書",
															"おにぎりの書",
															"おはらいの書",
															"くちなしの書",
															"グリモワール",
															"バクスイの書",
															"超人の書",
															"メッキの書",
															"ワナの書",
															"解凍の書",
															"幻想郷縁起",
															"困った時の書",
															"混乱の書",
															"撒き餌の書",
															"自爆の書",
															"識別の書",
															"水がれの書",
															"大部屋の書",
															"敵倍速の書",
															"天邪鬼の書",
															"電光石火の書",
															"破裂の書",
															"八咫烏の書",
															"氷の書",
															"封書モンスターハウス",
															"複製の書",
															"明かりの書",
															"慧音の歴史書"),
															POT("pot",
																	"印の壷",
																	"分裂の壷",
																	"合成の壷"
																	),
																	DISC("disc","Disc");
	//@formatter:on

	public static String getCategory(String str) {
		for (ArtifactData ad : values()) {
			for (String _str : ad.items) {
				if (_str.matches(str)) {
					return ad.item_package;
				}
			}
		}
		return "";
	}

	private static String getItemPackage(String str) {
		for (ArtifactData ad : values()) {
			for (String name : ad.items) {
				if (str.matches(name)) {
					return ad.item_package.concat(".");
				}
			}
		}
		return "";
	}

	public static Base_Artifact getObject(String str) {
		String package_package_classname = "item.";
		package_package_classname = package_package_classname.concat(
				getItemPackage(str)).concat(str);
		return ItemTable.returnBaseArtifactSetPoint(package_package_classname,
				Mass.nullpo.null_point);
	}

	public static boolean isCategory(String str, String category) {
		for (ArtifactData ad : values()) {
			for (String _str : ad.items) {
				if (_str.matches(str)) {
					if (ad.item_package.matches(category)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public final String[] items;

	private final String item_package;

	ArtifactData(String pack, String... names) {
		item_package = pack;
		items = names;
	}

	public String getCategory() {
		return item_package;
	}

	public String[] getItems() {
		return items;
	}

}
