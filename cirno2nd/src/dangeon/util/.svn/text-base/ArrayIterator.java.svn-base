package dangeon.util;

public class ArrayIterator {
	private int place_number = 1;

	private final int MAX_VALUE;

	/**
	 * テスト:失敗
	 */
	public ArrayIterator(int length) {
		MAX_VALUE = length;
	}

	/**
	 * 
	 * @return 今の位置を返す
	 */
	public int getPlaceNumber() {
		return place_number;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isNext() {
		if (MAX_VALUE == place_number) {
			System.out.println("いっぱい" + place_number);
			return false;
		}
		System.out.println("次へ" + MAX_VALUE + "と" + place_number);
		place_number++;
		return true;
	}
}
