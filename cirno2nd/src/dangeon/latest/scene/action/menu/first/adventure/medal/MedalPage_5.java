package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

public class MedalPage_5 extends Base_MedalPage {

	@Override
	public void addMedals() {
		add(Medal.アイテムをまとめて置いた);
		add(Medal.水路の上に乗ってワープした);
		add(Medal.アイテムを投げて毒ナイフを起動させた);
		add(Medal.壁の中に入った);
		add(Medal.ナイフをまとめて投げて敵を倒した);
		add(Medal.百鬼夜行抄を使用してボスを消した);
	}

	@Override
	public void getExp(ArrayList<String> l) {
		l.add("うまくアイテムやアクションを使えた記録");
	}

	@Override
	public String getShortTitle() {
		return "行動";
	}
}