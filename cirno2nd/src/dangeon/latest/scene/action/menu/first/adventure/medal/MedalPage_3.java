package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

public class MedalPage_3 extends Base_MedalPage {

	@Override
	public void addMedals() {
		add(Medal.クリア間近で倒れた);
		add(Medal.ゆっくりに倒された);
		add(Medal.ナイフを反射されて倒れた);
		add(Medal.復活系のアイテムを残して倒れた);
		add(Medal.おにぎりを食べて倒れた);
		add(Medal.アチチ草によって燃え尽きた);
		add(Medal.眠っている間に倒れた);
		add(Medal.咳をして倒れた);
		add(Medal.空中から敵にぶつかって倒れた);
		add(Medal.奇跡が起こって倒れた);
		add(Medal.泥棒していたら冒険の目的を忘れた);
		add(Medal.鳳凰の種が呪われていて復活できなかった);
	}

	@Override
	public void getExp(ArrayList<String> l) {
		l.add("悔しかった記録");
	}

	@Override
	public String getShortTitle() {
		return "倒れた";
	}
}