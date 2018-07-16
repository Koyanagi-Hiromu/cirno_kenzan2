package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

public class MedalPage_6 extends Base_MedalPage {

	@Override
	public void addMedals() {
		add(new MedalWrapper(Medal.泥棒成功回数) {
			@Override
			public String toString() {
				return "泥棒成功回数： ".concat(String.valueOf(medal.getCount())
						.concat("回"));
			}
		});
		add(Medal.落としてゐを敷地近くで倒した);
		add(Medal.変化した階段を敷地内で元に戻した);
		add(Medal.階段を杖で移動させて敷地内に入れた);
		// add(Medal.階段の上に場所替えして泥棒した);
		add(Medal.ニ部屋しかないマップで高飛びして泥棒した);
		add(Medal.泥棒した時エネミー数が上限に達していた);

	}

	@Override
	public void getExp(ArrayList<String> l) {
		l.add("泥棒を成功させた記録");
	}

	@Override
	public String getShortTitle() {
		return "泥棒";
	}
}