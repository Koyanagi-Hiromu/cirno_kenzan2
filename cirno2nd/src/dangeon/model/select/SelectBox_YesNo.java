package dangeon.model.select;

import dangeon.latest.util.Base_SelectBox;

public abstract class SelectBox_YesNo extends Base_SelectBox {

	public SelectBox_YesNo() {
		super("はい", "いいえ");
	}

	public void no() {
	}

	@Override
	public void pushEnter(int y) {
		if (y == 0) {
			yes();
		} else {
			no();
		}
		end();
	}

	public abstract void yes();

}
