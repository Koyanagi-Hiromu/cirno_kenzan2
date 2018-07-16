package dangeon.model.object.artifact.item.enchantSpecial;

import java.awt.Color;
import java.util.ArrayList;

import main.util.CSVLoadSupporter;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.player.Enchant;

public enum ENCHANT_SIMBOL {
	小, 天, 鼓, 面, 狼, 剣, 驚, 酔, 嫉妬, 緋, 雷, 鰻, 華, 連, 龍, 潜, 追, 双, 鬼, 必中, 血液, かまいたち, 月, つるはし, オンバシラ, 核爆発, 鼠, 超人, 謎, 祝, 間, 弾, 寅, 式, 水, 殺,
	// スペルカード印防具
	頭, 心, 食, やまびこ, 死, 衣, 炎, 狸, 招, 邪, 闇, 身躱し, イカリ, 冷, 友, 無, 狭, 瞳, 見切り, バトル, 蛍, 毒, 皮, 病, 厄, 魔, 地雷,
	// スペルカード印なんでも
	鴇, 時, 宝, 戯, 教, 写, 和, 舟, 兎, 裁, 医, チルノ, 喘息, ハラヘリ, 竹, 温, 音,
	// 草印
	電, 火, 天使, 絶, 力１, 薬, 妹, 倍速, 幸せ草, 不幸, 痺, 飛, 鈍足, ハート, 消, 冴, 大,
	// 書印
	祓, 幻, 槌, 氷, 混, 睡, 封, 自爆, 識, 書, 縁,
	// リボン印
	封印, 寒, 復活, 探知, 忍, 芋, 掘, 高, 融合, 護, 春, 衰, 栄光, 覚醒, 会心, 幸せ, 遠投, 回復, 識別, 力３, 透視, 錆, ワナ, 頑,
	// 混乱(3, "混", "混乱を防ぐぞ"), 睡眠(3, "睡", "睡眠を防ぐぞ"), ドレイン(3, "ド",
	// "ステータスを下げられなくなるぞ"),呪い(3, "呪",
	// "呪いから身を守るぞ"),
	// DISC印
	外伝, 妖々夢, 風神録, 地霊殿, 星蓮船, 永夜抄, 花映塚, 紅魔郷, 神霊廟, 輝針城,
	// 特殊印
	飯, 杖, 罠,
	// 罠印
	腐, ねむり, バネ, ハラ, 影縫い, 回転, 丸太, 錆の罠, 呪罠, 召還, 装備, 地雷の罠, 毒の矢, 鈍足の罠, 氷の罠, 落とし穴,
	// 没
	機械特効, トド, 金, 使い捨て, 拾, 地;

	private static String init(int type, int i, String exp) {
		if (i == 4) {
			if (type == 1) {
				return exp;
			}
		} else if (i == type) {
			return exp;
		}
		return Color.WHITE.toString().concat("特に効果はないぞ");
	}

	public static ArrayList<ENCHANT_SIMBOL> values(int simbol_number) {
		ArrayList<ENCHANT_SIMBOL> list = new ArrayList<ENCHANT_SIMBOL>();
		for (ENCHANT_SIMBOL sim : values()) {
			if (sim.number == simbol_number) {
				list.add(sim);
			}
		}
		return list;
	}

	public static ArrayList<ENCHANT_SIMBOL> values_ring() {
		ArrayList<ENCHANT_SIMBOL> list = new ArrayList<ENCHANT_SIMBOL>();
		for (ENCHANT_SIMBOL sim : values()) {
			if (sim.ring) {
				list.add(sim);
			}
		}
		return list;
	}

	public final boolean ring, grass_book;

	/**
	 * 装備識別用
	 */
	public final boolean tokidoki;

	private final String simbol_name;

	public final int number;

	public final String EXPLAIN_ATK, EXPLAIN_DEF, EXPLAIN_SP;

	public static int SIMBOL_NUMBER_ATK = 1, SIMBOL_NUMBER_DEF = 2,
			SIMBOL_NUMBER_ALL = 3, SIMBOL_NUMBER_RING = 4;

	public static ArrayList<ENCHANT_SIMBOL> getListSimbol(int simbol_number) {
		ArrayList<ENCHANT_SIMBOL> list = new ArrayList<ENCHANT_SIMBOL>();
		for (ENCHANT_SIMBOL sim : values()) {
			if (sim.number == simbol_number) {
				list.add(sim);
			}
		}
		return list;
	}

	private ENCHANT_SIMBOL() {
		CSVLoadSupporter<String> list = CSVLoadSupporter.loadCSV(name(),
				ENCHANT_SIMBOL.class, "\t");
		System.out.println(name());
		number = list.toI();
		simbol_name = list.get();
		EXPLAIN_ATK = list.get();
		EXPLAIN_DEF = list.get();
		EXPLAIN_SP = list.get();
		ring = list.isTrue();
		grass_book = list.isTrue();
		String[] arr = { EXPLAIN_ATK, EXPLAIN_DEF, EXPLAIN_SP };
		boolean b = simbol_name.matches("杖") || simbol_name.matches("会");
		if (!b)
			for (String string : arr) {
				if (string.contains("時々") || string.contains("まれ")) {
					b = true;
					break;
				}
			}
		tokidoki = b;
	}

	public CASE getEnchantCase() {
		switch (number) {
		case 1:
			return CASE.ATK;
		case 2:
			return CASE.DEF;
		case 3:
			return CASE.ALL;
		default:
			return CASE.RING;
		}
	}

	public String getEXPLAIN(Enchant enchant) {
		if (enchant == null) {
			return EXPLAIN_SP;
		}
		switch (enchant) {
		case ATK:
			return EXPLAIN_ATK;
		case DEF:
			return EXPLAIN_DEF;
		default:
			return EXPLAIN_SP;
		}
	}

	public String getName() {
		String str;
		str = getSimbolName();
		switch (getShow()) {
		case 1:
			return Enchant.ATK.getColor().toString().concat(str)
					.concat(Color.white.toString());
		case 2:
			return Enchant.DEF.getColor().toString().concat(str)
					.concat(Color.white.toString());
		case 3:
			return Enchant.ANY1.getColor().toString().concat(str)
					.concat(Color.white.toString());
		case 4:
			return Enchant.ANY3.getColor().toString().concat(str)
					.concat(Color.white.toString());
		default:
			return null;
		}
	}

	public String getName(boolean ring_color) {
		if (ring_color && ring) {
			return Enchant.ANY2.getColor().toString().concat(getSimbolName())
					.concat(Color.white.toString());
		} else {
			return getName();
		}
	}

	public String getQuestionExplan() {
		String str = new String();
		if (EXPLAIN_ATK.length() != 0) {
			str = str.concat("攻撃時 :").concat(EXPLAIN_ATK);
		}
		if (EXPLAIN_DEF.length() != 0) {
			str = str.concat("  防御時 :").concat(EXPLAIN_DEF);
		}
		if (EXPLAIN_SP.length() != 0) {
			str = str.concat("  常時 :").concat(EXPLAIN_SP);
		}
		return str;
	}

	/**
	 * 装備した時に効果を発揮する場所 1:武器 2:防具 3:特殊 4:同異
	 * 
	 * @return
	 */
	public int getShow() {
		return number;
	}

	public String getSimbolName() {
		return simbol_name;
	}

}
