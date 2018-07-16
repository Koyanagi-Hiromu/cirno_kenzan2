package dangeon.util;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

public enum STAGE {
	紅魔郷(ENCHANT_SIMBOL.紅魔郷, "紅"), 妖々夢(ENCHANT_SIMBOL.妖々夢, "妖"), 永夜抄(
			ENCHANT_SIMBOL.永夜抄, "永"), 花映塚(ENCHANT_SIMBOL.花映塚, "花"), 風神録(
			ENCHANT_SIMBOL.風神録, "風"), 地霊殿(ENCHANT_SIMBOL.地霊殿, "地"), 星蓮船(
			ENCHANT_SIMBOL.星蓮船, "星"), 神霊廟(ENCHANT_SIMBOL.神霊廟, "神"), 輝針城(
			ENCHANT_SIMBOL.輝針城, "輝"), 外伝(ENCHANT_SIMBOL.外伝, "外"),
	// （CSVとの兼ね合いで）制御用の定数は以下に書く
	PLAYER();
	public ENCHANT_SIMBOL EC;
	public String ONE_NAME;

	STAGE() {
		EC = null;
	}

	STAGE(ENCHANT_SIMBOL ec, String s) {
		EC = ec;
		ONE_NAME = s;
	}
}
