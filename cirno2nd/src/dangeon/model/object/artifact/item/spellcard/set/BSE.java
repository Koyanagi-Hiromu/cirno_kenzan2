package dangeon.model.object.artifact.item.spellcard.set;

/**
 * 共鳴
 * 
 * @author rottiti
 * 
 */
public enum BSE {
	HP(100, SetEnchantCard.レイマリ), STR(20, SetEnchantCard.マリアリ), DEF(20,
			SetEnchantCard.メイサク);

	private final SetEnchantCard[] SC;
	private final int value;
	private final int value2;
	private final int value3;

	BSE(int i, int j, int k, SetEnchantCard... sc) {
		SC = sc;
		value = i;
		value2 = j;
		value3 = k;
	}

	BSE(int i, int j, SetEnchantCard... sc) {
		this(i, j, 0, sc);
	}

	BSE(int i, SetEnchantCard... sc) {
		this(i, 0, 0, sc);
	}

	BSE(SetEnchantCard... sc) {
		this(0, 0, 0, sc);
	}

	public int getValue() {
		return value;
	}

	public int getValue2() {
		return value2;
	}

	public int getValue3() {
		return value3;
	}

	public boolean isCheck() {
		for (SetEnchantCard sc : SC) {
			if (sc.isSetCard()) {
				return true;
			}
		}
		return false;
	}
}
