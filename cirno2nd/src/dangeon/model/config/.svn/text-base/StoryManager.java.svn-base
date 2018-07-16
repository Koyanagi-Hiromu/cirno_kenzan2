package dangeon.model.config;

import main.constant.PropertySupporter;
import dangeon.model.map.field.random.Base_Map_Random;

public enum StoryManager {
	大妖精救出へ, 救出大作戦挑戦ok, 大妖精救出clear, 逆ヶ島挑戦ok, 逆ヶ島clear,
	//
	二撃必殺挑戦ok, 二撃必殺clear, 緋想の地下挑戦ok, 緋想の地下clear,
	// 1.10
	五色の神霊廟挑戦ok, 五色の神霊廟clear,
	// 1.20
	風穴旅行挑戦ok, 風穴旅行clear, ミラクルクエスト挑戦ok, ミラクルクエストclear,
	//
	おりん車, 日記帳,
	// 2.10
	運命のワルツok, 運命のワルツclear, あとがき, シェアok, 運命のワルツEvent_1, 七曜の魔導ok, 七曜の魔導clear, 賢将裏の洞窟ok, 賢将裏の洞窟clear, トラップタワーok, トラップタワーclear

	;
	public static PropertySupporter property = new PropertySupporter(
			Config.getSaveIndex(), "dungeon");

	public static boolean saveDungeon(Base_Map_Random bmr) {
		return false;
	}

	private String getKeyFinished() {
		return "finished";
	}

	public boolean hasFateFinished(int fate) {
		Object obj = property.getProperty_Nature(this.toString() + "_" + fate);
		return obj != null && obj.equals(getKeyFinished());
	}

	public boolean hasFinished() {
		Object obj = property.getProperty_Nature(this.toString());
		return obj != null && obj.equals(getKeyFinished());
	}

	public void saveThisFinished() {
		property.saveProperty(this.toString(), getKeyFinished());
		property.saveProperty(this.toString() + "_" + Config.getFate(),
				getKeyFinished());
	}
}
