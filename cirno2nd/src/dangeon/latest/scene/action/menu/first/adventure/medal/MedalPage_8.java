package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

public class MedalPage_8 extends Base_MedalPage {

	@Override
	public void addMedals() {
		add(new ClassMedalWrapper(Medal.Class冒険家));
		add(new ClassMedalWrapper(Medal.Class守矢神));
		add(new ClassMedalWrapper(Medal.Classナイフマスター));
		add(new ClassMedalWrapper(Medal.Class人工太陽));
		add(new ClassMedalWrapper(Medal.Class風水師));
		add(new ClassMedalWrapper(Medal.Class人形使い));
		add(new ClassMedalWrapper(Medal.Classゆっくり));
		add(new ClassMedalWrapper(Medal.Classフラワーマスター));
		add(new ClassMedalWrapper(Medal.Class宵闇妖怪));
		add(new ClassMedalWrapper(Medal.Classひねくれ者));
		add(new ClassMedalWrapper(Medal.Class半人半霊));
		add(new ClassMedalWrapper(Medal.Class気分屋));
		add(new ClassMedalWrapper(Medal.Class蓬莱人形));
	}

	@Override
	public void getExp(ArrayList<String> l) {
		l.add("ミラクルクエストの冒険の記録");
	}

	@Override
	public String getShortTitle() {
		return "ミラクエ";
	}
}