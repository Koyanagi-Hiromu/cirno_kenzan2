package dangeon.model.config;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.人形;
import dangeon.model.object.creature.enemy.博麗霊夢;
import dangeon.util.Switch;
import dangeon.view.constant.NormalFont;
import main.Listener;
import main.Listener.ACTION;
import main.constant.PropertySupporter;
import main.pad.ListenerAdapter;
import main.res.Image_Dungeon_Name;
import main.util.DIRECTION;

public class Config {
	public static final PropertySupporter PR = new PropertySupporter(-1);
	public static PropertySupporter PR_INDIV, PR_INDIV_PAD;
	/**
	 * ロード画面に使用
	 */
	public static PropertySupporter[] PR_INDIV_PRE = new PropertySupporter[4];

	static {
		PR_INDIV_PRE[3] = new PropertySupporter(4, "data");
	}

	private final static int save_index = 4;

	private static boolean key_ex, mass_off, no_random_bgm;

	private static int story, question, otog_adj;

	private static boolean mini_map_on;
	private static boolean test;
	private static boolean bgm_fix;
	private static boolean lap_on, mouse_on, hack_on, direct_name,
			no_shadow_on, bias_name_on, no_smooth, otog_arrow_right,
			show_hours;
	private static boolean high_speed_msg, font_style, coin_1, light;
	private static int otog_random, otog_fps, difficulty, skip_rate = 1;
	private static int gacha;
	public static final String SHIBARI[] = { "shibari_saikyo", "shibari_keine",
			"shibari_hakurei", "shibari_inseki" };

	public static void addOtogAdjust() {
		otog_adj++;
		if (otog_adj > 5)
			otog_adj = -5;
		PR_INDIV_PAD.saveProperty("otog_adj", otog_adj);

	}

	public static int addOtogRandom() {
		if (++otog_random > 2) {
			otog_random = 0;
		}
		PR_INDIV_PAD.saveProperty("otog_random", otog_random);
		return otog_random;
	}

	public static void addQuestion() {
		question++;
		PR_INDIV.saveProperty("question", question);
	}

	public static void addTimes() {
		PR_INDIV_PRE[save_index - 1].saveProperty_add("times", 1);
	}

	public static void changeGachaPoints(int delt) {
		gacha += delt;
		PR_INDIV_PAD.saveProperty("gacha", gacha);
	}

	public static boolean changeHighSpeedMessage() {
		high_speed_msg = !high_speed_msg;
		PR_INDIV.saveProperty("high_speed_msg", high_speed_msg);
		return high_speed_msg;
	}

	public static void changeOtogFPS() {
		if (otog_fps == 30)
			otog_fps = 60;
		else
			otog_fps = 30;
		PR_INDIV_PAD.saveProperty("otog_fps", otog_fps);
	}

	public static int decRetryNumbers() {
		PropertySupporter ps = PR_INDIV_PRE[save_index - 1];
		int numbers = ps.getProperty("numbers_for_retry");
		ps.saveProperty("numbers_for_retry", --numbers);
		return numbers;
	}

	public static boolean doesShowHours() {
		return show_hours;
	}

	public static boolean ExLumiaCheck() {
		String data = Config.getItemData(0);
		for (String element : data.split(",")) {
			if ("Exルーミアのカード".matches(element)) {
				return true;
			}
		}
		return false;
	}

	public static int getBGMVol() {
		return PR.getProperty("bgm_vol");
	}

	public static String getEnemyDataKey(Base_Enemy e, int lv) {
		return e.getOriginalName().concat(String.valueOf(lv));
	}

	public static int getFate() {
		return difficulty;
	}

	public static int getFloor(int index) {
		return PR_INDIV_PRE[index].getProperty("floor");
	}

	public static int getGachaPoints() {
		return gacha;
	}

	public static String getItemData(int category) {
		PropertySupporter pr = PR_INDIV_PRE[getSaveIndex() - 1];
		Object str = pr.getProperty_Nature(getItemDataKey(category));
		if (str == null)
			return "";
		else
			return str.toString();
	}

	public static String getItemDataKey(int category) {
		switch (category) {
		case 0:
			return "カード";
		case 1:
			return "草";
		case 2:
			return "リボン";
		case 3:
			return "書";
		case 4:
			return "杖";
		case 5:
			return "食べ物";
		case 6:
			return "射撃";
		case 7:
			return "魔法瓶";
		default:
			return "その他";
		}
	}

	public static long getKONumber(Base_Enemy e, int lv) {
		PropertySupporter pr = PR_INDIV_PRE[getSaveIndex() - 1];
		Object str = pr.getProperty_Nature(getEnemyDataKey(e, lv));
		if (str != null)
			return Long.valueOf(str.toString().split(",")[0]);
		else
			return 0;
	}

	public static long getKsg1Score() {
		Object o = PR_INDIV.getProperty_Nature("ksg1_score");
		if (o == null)
			return 0;
		return Long.valueOf(o.toString());
	}

	public static int getMap(int index) {
		return PR_INDIV_PRE[index].getProperty("map");
	}

	public static int getOtogAdjust() {
		return otog_adj;
	}

	public static int getOtogFPS() {
		return otog_fps;
	}

	public static ACTION getOtogKey(int id) {
		Object o = PR_INDIV_PAD.getProperty_Nature("otog" + String.valueOf(id));
		String s = null;
		if (o != null)
			s = o.toString();
		if (o == null || s.isEmpty()) {
			switch (id) {
			case 1:
				return ACTION.ENTER;
			case 2:
				return ACTION.FIRE;
			case 3:
				return ACTION.GATHER;
			case 4:
				return ACTION.TURN;
			}
			return ACTION.OWATTA;
		} else {
			return ACTION.valueOf(s);
		}
	}

	public static int getOtogRandom() {
		return otog_random;
	}

	public static int getOtogSpeed() {
		return PR_INDIV_PAD.getProperty("otog_speed");
	}

	public static int getPadKey(int i) {
		return PR_INDIV_PAD.getProperty("pad".concat(String.valueOf(i)));
	}

	public static int getQuestion() {
		return question;
	}

	public static long getRandomSeed() {
		PropertySupporter ps = PR_INDIV_PRE[save_index - 1];
		return ps.getProperty("random_seed");
	}

	public static int getReimuEvent() {
		return PR_INDIV.getProperty("reimu_ev");
	}

	public static int getRetryNumber() {
		PropertySupporter ps = PR_INDIV_PRE[save_index - 1];
		int number = ps.getProperty("numbers_for_retry");
		return number;
	}

	public static long getRetrySeed() {
		return PR_INDIV_PRE[save_index - 1]
				.getProperty("random_seed_for_retry");
	}

	public static int getSaveIndex() {
		return save_index;
	}

	public static int getSeconds(int index) {
		return PR_INDIV_PRE[index].getProperty("seconds");
	}

	public static int getSEVol() {
		return PR.getProperty("se_vol");
	}

	public static int getSkipRate() {
		return skip_rate;
	}

	public static int getStory() {
		return story;
	}

	public static int getTimes(int index) {
		return PR_INDIV_PRE[index].getProperty("times");
	}

	public static Object getValue(String key) {
		return PR_INDIV.getProperty_Nature(key);
	}

	public static void initializer(int index) {
		PR_INDIV_PRE[index] = new PropertySupporter(index + 1, "data");
		PropertySupporter ps = PR_INDIV_PRE[index] = new PropertySupporter(
				index + 1, "data");
		ps.saveProperty("seconds", 0);
		ps.saveProperty("floor", 0);
		ps.saveProperty("times", 0);
		ps.saveProperty("map", Image_Dungeon_Name.shucchou_teragoya.NO);
		ps = new PropertySupporter(index + 1);
		ps.saveProperty("story", 0);
		ps.saveProperty("question", 0);
		ps.saveProperty("test", false);
		ps.saveProperty("key_ex", true);
		ps.saveProperty("bgm", false);
		ps.saveProperty("mini_map_on", true);
		ps.saveProperty("reimu", false);
		ps.saveProperty("reimu_ev", 0);
		ps.saveProperty("cutin", true);
		for (int i = 1; i < 10; i++) {
			ps.saveProperty("reimu".concat(String.valueOf(i)), false);
		}
	}

	public static boolean isAccessableToDetail(Base_Enemy e, int lv) {
		if (Switch.test && Switch.switch_player_no_death) {
			return true;
		}
		PropertySupporter pr = PR_INDIV_PRE[getSaveIndex() - 1];
		Object str = pr.getProperty_Nature(getEnemyDataKey(e, lv));
		return str != null && str.toString().split(",")[1].matches("t");
	}

	public static boolean isBiasName() {
		return bias_name_on;
	}

	public static boolean isCoinOnly1() {
		return coin_1;
	}

	public static boolean isCutIN() {
		return PR_INDIV.isGotPropertyTrue("cutin");
	}

	public static boolean isDirectName() {
		return direct_name;
	}

	public static boolean isEnchantedOnigiriFirst() {
		return true;
	}

	public static boolean isExist(Base_Enemy e, int lv) {
		if (Switch.test && Switch.switch_player_no_death) {
			return true;
		}
		PropertySupporter pr = PR_INDIV_PRE[getSaveIndex() - 1];
		Object str = pr.getProperty_Nature(getEnemyDataKey(e, lv));
		return str != null;
	}

	public static boolean isExperienceMode() {
		return false;
	}

	public static boolean isFirst() {
		String s = "flag_over_twice";
		int i = PR.getProperty(s);
		PR.saveProperty(s, 0);
		return i == -1;
	}

	public static boolean isFixBGM() {
		return bgm_fix;
	}

	public static boolean isFontPlain() {
		return font_style;
	}

	public static boolean isHack_playing() {
		return hack_on;
	}

	public static boolean isHighSpeedMessage() {
		return high_speed_msg;
	}

	public static boolean isKey_ex() {
		return key_ex;
	}

	public static boolean isLapON() {
		return lap_on;
	}

	public static boolean isLightVer() {
		return light;
	}

	public static boolean isMass_OFF() {
		return mass_off;
	}

	public static boolean isMini_map_on() {
		return mini_map_on;
	}

	public static boolean isMouseON() {
		return mouse_on;
	}

	public static boolean isNoShadow() {
		return no_shadow_on;
	}

	public static boolean isOtogArrowRight() {
		return otog_arrow_right;
	}

	public static boolean isPassWardTrue(String string) {
		return PR_INDIV.isGotPropertyTrue(string) || Switch.test;
	}

	public static boolean isRandom_BGM() {
		return !no_random_bgm;
	}

	public static boolean isReimuChecked() {
		return PR_INDIV.isGotPropertyTrue("reimu");
	}

	public static boolean isReimuChecked(int i) {
		return PR_INDIV.isGotPropertyTrue("reimu".concat(String.valueOf(i)));
	}

	public static boolean isSaveIndex(int i) {
		return i == save_index;
	}

	public static boolean isSELimmite() {
		return PR.isGotPropertyTrue("SE_Limmit");
	}

	public static boolean isSmooth() {
		return no_smooth;
	}

	public static boolean isStory(int i) {
		return story == i;
	}

	/**
	 * 
	 * @param i
	 *            　以上
	 * @param j
	 *            　以下
	 * @return　ならtrue
	 */
	public static boolean isStoryRange(int i, int j) {
		return story >= i && story <= j;
	}

	public static boolean isTest() {
		return test;
	}

	public static void release() {
		PropertySupporter ps = PR_INDIV_PRE[save_index - 1];
		ps.saveProperty("floor", 0);
		ps.saveProperty("map", 0);

	}

	public static void save(Base_Enemy e, int lv, boolean KO) {
		PropertySupporter pr = PR_INDIV_PRE[getSaveIndex() - 1];
		Object str = pr.getProperty_Nature(getEnemyDataKey(e, lv));
		long ko_number;
		boolean detail;
		if (str == null) {
			ko_number = 0;
			detail = false;
		} else {
			String s = str.toString();
			ko_number = Long.valueOf(s.split(",")[0]);
			detail = s.split(",")[1].matches("t");
		}
		detail = detail || !KO;
		if (KO)
			ko_number++;
		StringBuffer sb = new StringBuffer();
		sb.append(ko_number);
		sb.append(",");
		sb.append(detail ? "t" : "f");
		pr.saveProperty(e.getOriginalName().concat(String.valueOf(lv)),
				sb.toString());
		if (!KO) {
			if (博麗霊夢.class.isInstance(e)) {
				Medal.博麗霊夢をエネミー図鑑に完全に登録した.addCount();
			}
			if (人形.class.isInstance(e)) {
				Medal.人形使いOK.addCount();
			}

		}

	}

	public static void save(long l) {
		PropertySupporter ps = PR_INDIV_PRE[save_index - 1];
		ps.saveProperty_add("seconds", l);
		Base_Map bm = PresentField.get();
		if (bm.isHaraheru()) {
			ps.saveProperty("floor", MapList.getFloor() + 1);
		} else {
			ps.saveProperty("floor", -1);
		}
		ps.saveProperty("map", bm.getMapNo());
	}

	public static void saveDifficulty(int i) {
		difficulty = i;
		PR_INDIV.saveProperty("difficulty", difficulty);
	}

	public static void saveItemData(Base_Artifact a) {
		PropertySupporter pr = PR_INDIV_PRE[getSaveIndex() - 1];
		int category;
		if (a instanceof SpellCard)
			category = 0;
		else if (a instanceof Base_Grass)
			category = 1;
		else if (a instanceof Ring)
			category = 2;
		else if (a instanceof Scrool)
			category = 3;
		else if (a instanceof Staff)
			category = 4;
		else if (a instanceof Food)
			category = 5;
		else if (a instanceof Arrow)
			category = 6;
		else if (a instanceof Base_Pot)
			category = 7;
		else
			category = 8;
		String child = ",";
		ArrayList<String> list = new ArrayList<String>();
		for (String s : getItemData(category).split(child)) {
			list.add(s);
		}
		String val = a.getClass().getSimpleName();
		if (!list.contains(val)) {
			list.add(val);
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (String string : list) {
			sb.append(string);
			sb.append(child);
		}
		sb.deleteCharAt(sb.length() - 1);
		pr.saveProperty(getItemDataKey(category), sb.toString());
	}

	public static void saveKey(int code, ACTION a) {
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE)
			return;
		HashMap<Integer, ACTION> list = Listener.getKey();
		HashMap<Integer, DIRECTION> list2 = Listener.getAllow_map();
		StringBuilder sb = new StringBuilder();
		if (list.containsKey(code)) {
			ACTION rev = list.remove(code);
			saveKey(-1, rev);
			if (a == rev)
				code = -1;
		}
		if (list2.containsKey(code)) {
			saveKey(-1, list2.remove(code));
		}
		if (code != -1) {
			sb.append(code);
			sb.append(",");
		}
		for (int i : list.keySet()) {
			if (list.get(i) == a) {
				sb.append(i);
				sb.append(",");
			}
		}
		if (code != -1)
			list.put(code, a);
		// ","を消す
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		PR_INDIV_PAD.saveProperty(a.name(), sb.toString());
	}

	public static void saveKey(int code, DIRECTION d) {
		if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_UP
				|| code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_LEFT)
			return;
		HashMap<Integer, ACTION> list = Listener.getKey();
		HashMap<Integer, DIRECTION> list2 = Listener.getAllow_map();
		StringBuilder sb = new StringBuilder();
		if (list.containsKey(code)) {
			saveKey(-1, list.remove(code));
		}
		if (list2.containsKey(code)) {
			DIRECTION rev = list2.remove(code);
			saveKey(-1, rev);
			if (d == rev)
				code = -1;
		}
		if (code != -1) {
			sb.append(code);
			sb.append(",");
		}
		for (int i : list2.keySet()) {
			if (list2.get(i) == d) {
				sb.append(i);
				sb.append(",");
			}
		}
		if (code != -1)
			list2.put(code, d);
		// ","を消す
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		PR_INDIV_PAD.saveProperty(d.name(), sb.toString());
	}

	public static boolean saveKsg1Score(long count) {
		long l = getKsg1Score();
		if (count > l) {
			PR_INDIV.saveProperty("ksg1_score", count);
			return true;
		}
		return false;
	}

	public static void saveLightVer(boolean b) {
		light = b;
		Config.PR_INDIV.saveProperty("light", b);
	}

	public static void saveOtogKey(int id, ACTION a) {
		PR_INDIV_PAD.saveProperty("otog" + String.valueOf(id), a.name());
	}

	public static void saveOtogSpeed(int speed) {
		PR_INDIV_PAD.saveProperty("otog_speed", speed);
	}

	public static void saveRandomSeed(long l) {
		PropertySupporter ps = PR_INDIV_PRE[save_index - 1];
		ps.saveProperty("random_seed", l);
	}

	public static void saveRetrySeed(long seed) {
		PropertySupporter ps = PR_INDIV_PRE[save_index - 1];
		ps.saveProperty("random_seed_for_retry", seed);
		ps.saveProperty("numbers_for_retry", 3);
	}
	
	public static void resetRetry() {
		PropertySupporter ps = PR_INDIV_PRE[save_index - 1];
		ps.saveProperty("numbers_for_retry", 3);
	}

	public static void saveValue(String key, Object value) {
		setValue(key, value);
	}

	public static void setBGMVol(int del) {
		PR.saveProperty("bgm_vol", del);
	}

	public static void setCoinOnly1(boolean on) {
		coin_1 = on;
		PR_INDIV.saveProperty("coin_1", coin_1);
	}

	public static void setMouseTrue() {
		mouse_on = true;
		PR_INDIV.saveProperty("mouse_on", mouse_on);
	}

	public static void setPadKey(int i, int key) {
		key = ListenerAdapter.setKeyConfig(i, key);
		PR_INDIV_PAD.saveProperty("pad".concat(String.valueOf(i)), key);
	}

	public static void setReimuChecked(boolean b) {
		PR_INDIV.saveProperty("reimu", b);
	}

	public static void setReimuChecked(int i, boolean b) {
		PR_INDIV.saveProperty("reimu".concat(String.valueOf(i)), b);
	}

	public static void setReimuEvent(int i) {
		PR_INDIV.saveProperty("reimu_ev", i);
	}

	public static void setSaveindex(int i) {
		// ゲーム開始時にcall
		// save_index = i;
		i = save_index;
		PR_INDIV = new PropertySupporter(i);
		PR_INDIV_PAD = new PropertySupporter(i, "pad");
		mini_map_on = PR_INDIV.isGotPropertyTrue("mini_map_on");
		question = PR_INDIV.getProperty("question");
		test = PR_INDIV.isGotPropertyTrue("test");
		bgm_fix = PR_INDIV.isGotPropertyTrue("bgm");
		key_ex = PR_INDIV.isGotPropertyTrue("key_ex");
		mass_off = PR_INDIV.isGotPropertyTrue("mass_off");
		no_random_bgm = PR_INDIV.isGotPropertyTrue("no_random_bgm");
		story = PR_INDIV.getProperty("story");
		// パッチ設定-見つからなかったらfalse
		lap_on = PR_INDIV.isGotPropertyTrue("lap_on");
		show_hours = PR_INDIV.isGotPropertyTrue("show_hours");
		mouse_on = PR_INDIV.isGotPropertyTrue("mouse_on");
		hack_on = PR_INDIV.isGotPropertyTrue("hack_on");
		direct_name = PR_INDIV.isGotPropertyTrue("direct_name");
		bias_name_on = PR_INDIV.isGotPropertyTrue("bias_name_on");
		no_shadow_on = PR_INDIV.isGotPropertyTrue("no_shadow_on");
		no_smooth = PR_INDIV.isGotPropertyTrue("smooth");
		difficulty = PR_INDIV.getProperty("difficulty");
		if (difficulty == -1)
			difficulty = 0;
		skip_rate = PR_INDIV.getProperty("skip_rate");
		if (skip_rate <= 0)
			skip_rate = 1;
		otog_arrow_right = PR_INDIV_PAD.isGotPropertyTrue("otog_arrow");
		otog_adj = PR_INDIV_PAD.getProperty("otog_adj");
		otog_random = PR_INDIV_PAD.getProperty("otog_random");
		otog_fps = PR_INDIV_PAD.getProperty("otog_fps");
		if (otog_fps == -1)
			otog_fps = 30;
		gacha = PR_INDIV_PAD.getProperty("gacha");
		if (gacha == -1)
			gacha = 0;
		high_speed_msg = PR_INDIV.isGotPropertyTrue("high_speed_msg");
		font_style = PR_INDIV.isGotPropertyTrue("font_style");
		coin_1 = PR_INDIV.isGotPropertyTrue("coin_1");
		light = PR_INDIV.isGotPropertyTrue("light");
		NormalFont.setBold(!font_style);
		for (ACTION a : ACTION.values()) {
			a.setKey(PR_INDIV_PAD.getProperty_Nature(a.name()));
		}
		for (DIRECTION d : DIRECTION.values()) {
			Listener.ME.setKey(d, PR_INDIV_PAD.getProperty_Nature(d.name()));
		}
		Listener.init();
		// @zndmch
		// ゲームパッド用キーコンフィグ
		for (int j = 0; j < 16; j++) {
			ListenerAdapter.setKeyConfig(j, getPadKey(j));
		}
	}

	public static void setSEVol(int del) {
		PR.saveProperty("se_vol", del);
	}

	public static void setSkipRate(int i) {
		skip_rate = i;
		PR_INDIV.saveProperty("skip_rate", skip_rate);
	}

	public static void setStory(int story) {
		Config.story = story;
		PR_INDIV.saveProperty("story", story);
	}

	public static void setValue(String key, Object value) {
		PR_INDIV.saveProperty(key, value);
	}

	public static void switchBgm() {
		bgm_fix = !bgm_fix;
		PR_INDIV.saveProperty("bgm", bgm_fix);
	}

	public static void switchBiasName(boolean b) {
		bias_name_on = b;
		PR_INDIV.saveProperty("bias_name_on", bias_name_on);

	}

	public static void switchCutIN() {
		PR_INDIV.saveProperty("cutin", !isCutIN());
	}

	public static void switchDirectName(boolean b) {
		direct_name = b;
		PR_INDIV.saveProperty("direct_name", direct_name);
	}

	public static void switchDropShadow(boolean b) {
		no_shadow_on = b;
		PR_INDIV.saveProperty("no_shadow_on", no_shadow_on);
	}

	public static void switchFontStyle() {
		font_style = !font_style;
		NormalFont.setBold(!font_style);
		PR_INDIV.saveProperty("font_style", font_style);
	}

	public static void switchHack() {
		hack_on = !hack_on;
		PR_INDIV.saveProperty("hack_on", hack_on);

	}

	public static void switchKey_ex() {
		key_ex = !key_ex;
		PR_INDIV.saveProperty("key_ex", key_ex);
	}

	public static void switchLap_ON() {
		lap_on = !lap_on;
		PR_INDIV.saveProperty("lap_on", lap_on);
	}

	public static void switchMass_off() {
		mass_off = !mass_off;
		PR_INDIV.saveProperty("mass_off", mass_off);
	}

	public static void switchMini_map_on() {
		mini_map_on = !mini_map_on;
		PR_INDIV.saveProperty("mini_map_on", mini_map_on);
	}

	public static void switchNo_Random_BGM() {
		no_random_bgm = !no_random_bgm;
		PR_INDIV.saveProperty("no_random_bgm", no_random_bgm);
	}

	public static void switchOtogArrow() {
		otog_arrow_right = !otog_arrow_right;
		PR_INDIV_PAD.saveProperty("otog_arrow", otog_arrow_right);
	}

	public static void switchShowHours() {
		show_hours = !show_hours;
		PR_INDIV.saveProperty("show_hours", show_hours);
	}

	public static void switchSmooth() {
		no_smooth = !no_smooth;
		// Message.set("滑らか表示を", no_smooth ? "OFF" : "ON", "にしました");
		PR_INDIV.saveProperty("smooth", no_smooth);
	}

}
