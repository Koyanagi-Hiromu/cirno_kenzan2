package dangeon.latest.scene.action.otog.value;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import main.Listener.ACTION;
import main.res.BGM;
import main.thread.MainThread;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.main.panel.Panel_Main;
import dangeon.latest.scene.action.otog.main.panel.Panel_Sub3;
import dangeon.latest.scene.action.otog.main.panel.Panel_West;
import dangeon.latest.scene.action.otog.object.Accepter;
import dangeon.latest.scene.action.otog.object.AccepterD;
import dangeon.latest.scene.action.otog.object.Base_Otog_Object;
import dangeon.latest.scene.action.otog.object.TargetA;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Content_Selection;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.model.config.Config;
import dangeon.view.util.StringFilter;

/**
 * filed のみ記述
 * 
 * @author weray
 * 
 */
public class ValueHolder {
	public final long ADJ = Config.getOtogAdjust();
	public long LIFE_100;
	public long score_milli = 0;
	public long node_sum;
	public int flag_random = Config.getOtogRandom();
	public final ArrayList<Long> SCORE_PROCESS = new ArrayList<Long>(),
			LIFE_PROCESS = new ArrayList<Long>();
	// public long CHO_SEI = -5000;
	public long CHO_SEI;
	public int SPEED;
	public BGM pre_bgm;

	public int DELAY_ACCEPT = 3;

	public final ArrayList<TargetA> list = new ArrayList<TargetA>();

	public Accepter accs[];
	public BGM bgm;
	public Hash_ACCEPTER_S_INDEX_ACTION_X HASH;
	public HitChecker HIT_CHECKER;
	public Panel_Main MAIN_PANEL;
	/**
	 * 最初の１小節のマイナス mp3frame
	 */
	public long first_frame = -100;
	public final ArrayList<Base_Otog_Object> objects = new ArrayList<Base_Otog_Object>();
	public final ArrayList<Base_Otog_Object> bars = new ArrayList<Base_Otog_Object>();
	public Panel_West west;
	public Panel_Sub3 sub3;
	public boolean demo;
	public int end_mp3_frame;
	public int MAX_COMBO = 0;
	/**
	 * 2: Easy 3: Normal 4: Hard 5: Lunatic 6: Eternal
	 */
	public int difficulty = 3;
	public long HEAL_MILLI_RATE;

	public HashMap<TEXT, Integer> hash_save_tex_count = new HashMap<HitChecker.TEXT, Integer>(
			TEXT.values().length);

	public ArrayList<Otog_Select_Content_Selection> SCORE_SELECTIONS = new ArrayList<Otog_Select_Content_Selection>();

	public int select_y;

	public int select_sort;

	/**
	 * 0:F 0<br/>
	 * 1:E 30000<br/>
	 * 2:D 45000<br/>
	 * 3:C 60000<br/>
	 * 4:B 75000<br/>
	 * 5:A 85000<br/>
	 * 6:AA 90000<br/>
	 * 7:AAA 95000<br/>
	 * 8:S 100000
	 */
	public int RANK = 0;

	public Otog_Select_Content_Selection CONTENT;

	public ValueHolder() {
		pre_bgm = BGM.get();
		SPEED = Config.getOtogSpeed();
		if (SPEED == -1)
			SPEED = 5;
	}

	public void addDifficulty(int del) {
		difficulty += del;
		if (difficulty < 2)
			difficulty += 6;
		if (difficulty > 7)
			difficulty -= 6;
	}

	public void addSpeed(int del) {
		SPEED += del;
		if (SPEED > 15)
			SPEED = 2;
		Config.saveOtogSpeed(SPEED);
	}

	public void changeRandom() {
		flag_random = Config.addOtogRandom();
	}

	public String getDifficulty() {
		return getDifficultyColors(difficulty).toString().concat(
				getDifficultyCharcters());
	}

	private String getDifficultyCharcters() {
		switch (difficulty) {
		case 2:
			return "EASY";
		case 3:
			return "NOMA";
		case 4:
			return "HARD";
		case 5:
			return "LUNA";
		case 6:
			return "ULTM";
		case 7:
			return "demo";
		}
		return null;
	}

	public Color getDifficultyColors(int difficulty) {
		switch (difficulty) {
		case 0:
			return Color.BLACK;
		case 1:
			return Color.DARK_GRAY;
		case 2:
			return StringFilter.NUMBERS;
		case 3:
			return Color.ORANGE;
		case 4:
			return Color.MAGENTA;
		case 5:
			return Color.RED;
		case 6:
			int frame = MainThread.getFrame();
			if (frame % 9 % 3 == 0)
				return Color.WHITE;
			else if (frame % 9 / 3 == 0)
				return Color.MAGENTA;
			else if (frame % 9 / 3 == 1)
				return Color.CYAN;
			else if (frame % 9 / 3 == 2)
				return Color.YELLOW;
		case 7:
			return Color.LIGHT_GRAY;
		}
		return null;
	}

	public String getRank(Graphics2D g, int RANK) {
		switch (RANK) {
		case 0:
			g.setColor(Color.GRAY);
			return "F";
		case 1:
			g.setColor(Color.BLUE);
			return "E";
		case 2:
			g.setColor(Color.GREEN.darker());
			return "D";
		case 3:
			g.setColor(Color.WHITE);
			return "C";
		case 4:
			g.setColor(Color.YELLOW);
			return "B";
		case 5:
			g.setColor(Color.RED);
			return "A";
		case 6:
			g.setColor(Color.RED);
			return "AA";
		case 7:
			g.setColor(Color.RED);
			return "AAA";
		default:
			g.setColor(TEXT.PERFECT.getColor(MainThread.getFrame() / 2));
			return "S";
		}
	}

	public void init(Panel_Main p) {
		MAIN_PANEL = p;
		accs = new Accepter[5];
		if (Config.isOtogArrowRight()) {
			for (int i = 0; i < 4; i++) {
				accs[i] = new Accepter(p, Config.getOtogKey(i + 1), i, accs);
			}
			accs[4] = new AccepterD(p, 4, accs);
		} else {
			accs[0] = new AccepterD(p, 0, accs);
			for (int i = 1; i < 5; i++) {
				accs[i] = new Accepter(p, Config.getOtogKey(i), i, accs);
			}
		}
		HASH = new Hash_ACCEPTER_S_INDEX_ACTION_X(accs);
		SCORE_PROCESS.clear();
		score_milli = 0;
		LIFE_PROCESS.clear();
		MAX_COMBO = 0;
		LIFE_100 = 5000;
		hash_save_tex_count.clear();
		for (TEXT tex : TEXT.values()) {
			hash_save_tex_count.put(tex, 0);
		}
		list.clear();
		bars.clear();
		objects.clear();
		HIT_CHECKER = new HitChecker();
	}

	public boolean isFailed() {
		return LIFE_100 < 5000;
	}

	public void pushed(ACTION a) {
		HIT_CHECKER.pushed(this, a);
	}

	public void pushed(DIRECTION d) {
		HIT_CHECKER.pushed(this, d);
	}

	public void setBgm(BGM bgm) {
		this.bgm = bgm;
	}

	public void setOffset(long offset) {
		CHO_SEI = 7500 + offset;
	}

	public void setRank() {
		long rank = LIFE_100 + score_milli / 10000000;
		int hosei = 0;
		for (int i = 0; i < 8; i++) {
			if (i >= 4)
				hosei++;
			if (i >= 5)
				hosei++;
			if (rank > 100000 - 5000 * (i + hosei)) {
				// A~S
				RANK = 8 - i;
				return;
			}
		}
		RANK = 0;
	}

	public void upDate(long frame) {
		for (Accepter a : accs)
			a.upDate(this, frame, null);
		for (Iterator<TargetA> iterator = list.iterator(); iterator.hasNext();) {
			iterator.next().upDate(this, frame, iterator);
		}
		for (Iterator<Base_Otog_Object> iterator = bars.iterator(); iterator
				.hasNext();) {
			iterator.next().upDate(this, frame, iterator);
		}
		for (Iterator<Base_Otog_Object> iterator = objects.iterator(); iterator
				.hasNext();) {
			iterator.next().upDate(this, frame, iterator);
		}
	}
}
