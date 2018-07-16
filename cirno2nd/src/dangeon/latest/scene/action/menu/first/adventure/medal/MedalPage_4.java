package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

public class MedalPage_4 extends Base_MedalPage {

	@Override
	public void addMedals() {
		add(Medal.ルーミアにおにぎりを投げつけた);
		add(Medal.太陽の弱点を突いた);
		add(Medal.ザ・咲夜に通常攻撃を当てた);
		add(Medal.壁の中の敵を封印した);
		add(Medal.倍速の敵に回復の杖を振った);
		add(Medal.雛に呪いを吸い取ってもらった);
		add(Medal.にとりにアイテムを変換させた);
		add(Medal.敵から透視状態を奪った);
		add(Medal.早苗におにぎりを投げつけた);
		add(Medal.アイテムヤッホーにワナの毒ナイフを当てた);
		// add(Medal.雷鼓のレベルを上げた);
		add(Medal.朱鷺子に書を投げつけた);
	}

	@Override
	public void getExp(ArrayList<String> l) {
		l.add("うまくエネミーの特性を把握できた記録");
	}

	@Override
	public String getShortTitle() {
		return "エネミー";
	}
}