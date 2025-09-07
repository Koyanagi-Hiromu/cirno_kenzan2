package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

public class MedalPage_9 extends Base_MedalPage {

	@Override
	public void addMedals() {
		add(new ClassMedalWrapper(Medal.Class冒険家_七曜));
		add(new ClassMedalWrapper(Medal.Class守矢神_七曜));
		add(new ClassMedalWrapper(Medal.Classナイフマスター_七曜));
		add(new ClassMedalWrapper(Medal.Class人工太陽_七曜));
		add(new ClassMedalWrapper(Medal.Class風水師_七曜));
		add(new ClassMedalWrapper(Medal.Class人形使い_七曜));
		add(new ClassMedalWrapper(Medal.Classゆっくり_七曜));
		add(new ClassMedalWrapper(Medal.Classフラワーマスター_七曜));
		add(new ClassMedalWrapper(Medal.Classパチュリー_七曜));
		add(new ClassMedalWrapper(Medal.Classひねくれ者_七曜));
		add(new ClassMedalWrapper(Medal.Class半人半霊_七曜));
		add(new ClassMedalWrapper(Medal.Class気分屋_七曜));
		add(new ClassMedalWrapper(Medal.Class蓬莱人形_七曜));
	}

	@Override
	public void getExp(ArrayList<String> l) {
		l.add("七曜クエストの冒険の記録");
	}

	@Override
	public String getShortTitle() {
		return "七曜クエ";
	}
}