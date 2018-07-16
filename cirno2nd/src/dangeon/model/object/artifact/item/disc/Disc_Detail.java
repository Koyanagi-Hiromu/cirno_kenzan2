package dangeon.model.object.artifact.item.disc;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.util.R;
import dangeon.util.STAGE;

public enum Disc_Detail {
	妖々夢(ENCHANT_SIMBOL.妖々夢, STAGE.妖々夢), 花映塚(ENCHANT_SIMBOL.花映塚, STAGE.花映塚), 星蓮船(
			ENCHANT_SIMBOL.星蓮船, STAGE.星蓮船), 紅魔郷(ENCHANT_SIMBOL.紅魔郷, STAGE.紅魔郷), 永夜抄(
			ENCHANT_SIMBOL.永夜抄, STAGE.永夜抄), 風神録(ENCHANT_SIMBOL.風神録, STAGE.風神録), 地霊殿(
			ENCHANT_SIMBOL.地霊殿, STAGE.地霊殿), 神霊廟(ENCHANT_SIMBOL.神霊廟, STAGE.神霊廟), 輝針城(
			ENCHANT_SIMBOL.輝針城, STAGE.輝針城), 外伝(ENCHANT_SIMBOL.外伝, STAGE.外伝), ;
	static Disc_Detail RandomDisc() {
		Disc_Detail d[] = Disc_Detail.values();
		return d[new R().nextInt(d.length)];
	}

	static Disc_Detail selectDisc(String s) {
		for (Disc_Detail d : Disc_Detail.values()) {
			if (s.matches(d.name())) {
				return d;
			}
		}
		return null;
	}

	ENCHANT_SIMBOL ES;

	STAGE S;

	private Disc_Detail(ENCHANT_SIMBOL es, STAGE stage) {
		ES = es;
		S = stage;
	}

	ENCHANT_SIMBOL getSimbol() {
		return ES;
	}

	STAGE getStage() {
		return S;
	}
}
