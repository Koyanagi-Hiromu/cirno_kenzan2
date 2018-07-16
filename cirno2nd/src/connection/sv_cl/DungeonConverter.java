package connection.sv_cl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import main.util.Show;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.ミラクルクエスト;
import dangeon.model.map.field.random.二撃必殺;
import dangeon.model.map.field.random.救出大作戦;
import dangeon.model.map.field.random.緋想の地下;
import dangeon.model.map.field.random.逆ヶ島;
import dangeon.model.map.field.random.運命のワルツ;
import dangeon.model.map.field.random.風穴旅行;
import dangeon.model.map.field.random.second.五色の神霊廟;

public class DungeonConverter {

	public DungeonConverter() {
	}

	public DungeonConverter(String map_name, int fate) {
		Config.saveDifficulty(fate);
		inflate(map_name).createFirstMap();
	}

	private HashMap<Class<? extends Base_Map_Random>, Boolean> createHashMap() {
		HashMap<Class<? extends Base_Map_Random>, Boolean> map = new HashMap<Class<? extends Base_Map_Random>, Boolean>();
		map.put(救出大作戦.class, true);
		if (Config.isExperienceMode()) {
			return map;
		}
		map.put(逆ヶ島.class, StoryManager.救出大作戦挑戦ok.hasFinished());
		map.put(二撃必殺.class, StoryManager.二撃必殺挑戦ok.hasFinished());
		map.put(五色の神霊廟.class, StoryManager.五色の神霊廟挑戦ok.hasFinished());
		map.put(緋想の地下.class, StoryManager.緋想の地下挑戦ok.hasFinished());
		map.put(風穴旅行.class, StoryManager.風穴旅行挑戦ok.hasFinished());
		map.put(ミラクルクエスト.class, StoryManager.ミラクルクエスト挑戦ok.hasFinished());
		map.put(運命のワルツ.class, StoryManager.運命のワルツok.hasFinished());
		return map;
	}

	public String[] getDefaultDungeonName() {
		ArrayList<String> list = new ArrayList<String>();
		HashMap<Class<? extends Base_Map_Random>, Boolean> map = createHashMap();
		for (Class<? extends Base_Map_Random> clazz : map.keySet()) {
			if (map.get(clazz)) {
				list.add(clazz.getSimpleName());
			}
		}
		return list.toArray(new String[0]);
	}

	public Base_Map_Random inflate(String map_name) {
		HashMap<Class<? extends Base_Map_Random>, Boolean> map = createHashMap();
		for (Class<? extends Base_Map_Random> clazz : map.keySet()) {
			if (map_name.matches(clazz.getSimpleName())) {
				Constructor<? extends Base_Map_Random> con;
				try {
					con = clazz.getConstructor();
					return con.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
					Show.showErrorMessageDialog(e);
				}
			}
		}
		return null;
	}
}
