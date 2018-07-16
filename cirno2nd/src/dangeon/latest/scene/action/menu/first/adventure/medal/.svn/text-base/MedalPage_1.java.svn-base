package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

import dangeon.model.object.creature.player.EXP;

public class MedalPage_1 extends Base_MedalPage {

	@Override
	public void addMedals() {
		add(new MedalWrapper(Medal.経験値) {
			@Override
			public String toString() {
				int exp = (int) medal.getCount();
				int lv = EXP.table.level(exp);
				return "最大Ｌｖ：" + lv + "　（最大経験値：" + exp + "）";
			}
		});
		add(new MedalWrapper(Medal.最高HP) {
			@Override
			public String toString() {
				return "最大ＨＰ：" + medal.getCount();
			}
		});
		add(new MedalWrapper(Medal.最高ちから) {
			@Override
			public String toString() {
				return "最大ちから：" + medal.getCount();
			}
		});
		add(new MedalWrapper(Medal.攻撃力) {
			@Override
			public String toString() {
				return "最大攻撃力：" + medal.getCount();
			}
		});
		add(new MedalWrapper(Medal.防御力) {
			@Override
			public String toString() {
				return "最大防御力：" + medal.getCount();
			}
		});
		add(new MedalWrapper(Medal.今までで合計XX体の敵をやっつけた) {
			@Override
			public String toString() {
				return "累計" + medal.getCount() + "体の敵をやっつけた";
			}
		});
		add(new MedalWrapper(Medal.一度に与えた最高ダメージXX) {
			@Override
			public String toString() {
				return "一度に与えた攻撃ダメージの最大値：" + medal.getCount();
			}
		});
		add(new MedalWrapper(Medal.一度に受けた最高ダメージXX) {
			@Override
			public String toString() {
				return "一度に受けた攻撃ダメージの最大値：" + medal.getCount();
			}
		});
		add(new MedalWrapper(Medal.一度にXXコの状態異常アイコンを出した) {
			@Override
			public String toString() {
				return "一度に状態異常アイコンを" + medal.getCount() + "コ出した";
			}
		});
		// add(new MedalWrapper(){
		// @Override
		// public String toString() {
		// return ;
		// }
		// });
	}

	@Override
	public void getExp(ArrayList<String> l) {
		l.add("過去の冒険で一番大きな記録");
	}

	@Override
	public String getShortTitle() {
		return "最大値";
	}
}