package main.res;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.util.HashMap;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import main.util.CSVLoadSupporter;
import main.util.FileReadSupporter;
import main.util.Show;
import main.util.Sleep;
import dangeon.model.config.Config;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.util.R;
import dangeon.view.detail.Footer;

/**
 * UTILは便利な定数です BasicPlayerListenerを受け持ちます。 また.play()を行った場合ランダムで曲が流れます
 */
public enum CopyOfBGM implements BasicPlayerListener {
	UTIL(true),
	//
	DevilBadGhost, CloudsGirl, smallsmallwisegirl, Strength, pf, hypertech, Rock_on_dream, pache, chirno, remi, merin, lunadial, rumya, un2, youkai, old, serene, chen, imperi, udonge, lek, tenko, Flash, Jiang_Shi_Dance, Mizuchi, akisiimai, momiji, recess, Vaisravana, the_uncertainty_principle, come_into_bloom, blind, to_hana_poi2, to_kou_chiruno, to_hana_kon5, to_hisob2, to_shin6b, Cheer_up_r, the_unknown, The_boundary_of_the_world_wob, Another_world_fly_over_the_boundary, cilno_kenzan, yukkurishiteittene, mou_uta_sura_kikoenai, sun, SAKADuKi, kanpyo_ch_ayakashi, kanpyo_ch_eiyanoten, kanpyo_ch_sprite, kanpyo_ch_bbcc, kanpyo_ch_plume, kanpyo_ch2_bbcc, kanpyo_ch2_fairy, kanpyo_ch2_fairy_starsapphire, BYGONEBATTLEFIELD, suwakeroBOMBER, MischievousConfessionofTEWI, mofmof, mami, wa, doukutsu, VenomReflection, Mannequin, RampageGhost, PetalChaser, TwoMinutes, RedSpiderLily, BladeRunner, ASHtoASH, hunter_game, TakeAChance, dorobo, THEPOWER, Time_av_Ox, Feel_A_Goddess, black, fillInBlack, StayWithinTheWall, kagura_satori, kagura_insect, kagura_umbrella,

	kagerou, iku, Mermaidia, kanpyo_ch2_hexa, Dingy_rip

	, hallucination, Complete_Darkness, frozen_night, Good_Morningood_Day, hyougen_s, hyougenyakou, rebirth_comp, Shake_it_Banging__, speranza, tsukumo, team_tsunagi, izanagi, bousitu, raiko, NamelessBird,

	;

	private class LoadThread extends Thread {
		private class Interrupt extends Exception {
			private static final long serialVersionUID = 1L;
		}

		private CopyOfBGM B;

		private boolean flag_intterruput;

		public LoadThread(CopyOfBGM b) {
			B = b;
		}

		private void finish() throws Exception {
			interruptCheck();
			playing_holder = playingBGM = B;
			loadingBGM = null;
			playing_holder.flag_first = true;
			// Footer.set(playingBGM);
			player.play();
			interruptCheck();
			UTIL.load_thread = null;
		}

		private synchronized void first() {
			// opening_map.clear();
			// opening_map.put(B, true);
		}

		private synchronized void interruptCheck() throws Interrupt {
			if (flag_intterruput || !opening_map.containsKey(B)) {
				flag_intterruput = false;
				throw new Interrupt();
			}
		}

		private synchronized boolean isOpening() throws Interrupt {
			interruptCheck();
			return opening_map.get(B);
		}

		private void open() throws Exception {
			File file = FileReadSupporter.readURL(B.NAME);
			// if (!file.exists()) {
			// throw new FileNotFoundException();
			// }
			player.open(file);
			long sum_ms = getDefaultSeek() + sleep_ms;
			if (SEEK != -1) {
				sum_ms += B.SEEK;
			}
			while (isOpening())
				sum_ms -= sleep();
			if (roop && sum_ms > 0)
				player.seek(sum_ms);
			while (player.getStatus() == BasicPlayer.SEEKING)
				sleep();
		}

		public void reset(CopyOfBGM bgm) {
			B = bgm;
			flag_intterruput = true;
		}

		@Override
		public void run() {
			while (true) {
				try {
					first();
					waitFadeOut();
					open();
					sleep();
					finish();
					break;
				} catch (Interrupt e) {
					// 読込中に他のファイル読み込みの命令が来た
				} catch (FileNotFoundException e) {
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private long sleep() throws Exception {
			interruptCheck();
			return Sleep.done(10l);
		}

		private void waitFadeOut() throws Exception {
			while (player.getStatus() == BasicPlayer.PLAYING)
				sleep();
		}
	}

	private static void fadeOutVol(double rate) {
		if (rate < 0) {
			rate = 0;
		} else if (rate > 1) {
			rate = 1;
		}
		try {
			System.out.println(rate * vol / VOL_MAX);
			player.setGain(rate * vol / VOL_MAX);
			// player.setPan(0.0);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// 再生していない
		}
	}

	public static CopyOfBGM get() {
		return loadingBGM != null ? loadingBGM : playingBGM;
	}

	public static CopyOfBGM get(Class<? extends SpellCard> c) {
		for (CopyOfBGM bgm : values()) {
			if (c.equals(bgm.CLASS)) {
				return bgm;
			}
		}
		return null;
	}

	@Deprecated
	public static CopyOfBGM get(Image_LargeCharacter imlc) {
		for (CopyOfBGM bgm : values()) {
			String s = bgm.CLASS.getSimpleName();
			s = s.substring(0, s.indexOf("のカード"));
			if (imlc.name().equals(s)) {
				return bgm;
			}
		}
		return UTIL;
	}

	public static CopyOfBGM get2(String str) {
		for (CopyOfBGM bgm : values()) {
			if (bgm.CLASS == null)
				continue;
			String s = bgm.CLASS.getSimpleName();
			s = s.substring(0, s.indexOf("のカード"));
			if (str.equals(s)) {
				return bgm;
			}
		}
		return UTIL;
	}

	public static long getFrame() {
		// if (frame < 0)
		// {
		// System.out.println("☆" + frame);
		// if (sleep_ms > 0) {
		// System.out.println("◆◆◆◆" + sleep_ms + "□□□□"
		// + (-(getMP3MilliFrame(sleep_ms) + 500) / 1000));
		// return -(getMP3MilliFrame(sleep_ms) + 500) / 1000;
		// }
		// } else
		// System.out.println("●" + frame);

		return frame;
	}

	private static boolean play_random(boolean flag_continus_replay) {
		try {
			if (Config.isRandom_BGM()) {
				if (spell.BGM_LIST.size() > 1) {
					CopyOfBGM bgm;
					while (playingBGM == (bgm = spell.BGM_LIST
							.toArray(new CopyOfBGM[0])[R.ran(spell.BGM_LIST
							.size())]))
						;
					bgm.play(0, flag_continus_replay);
				} else {
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			// 楽曲なし？
		}
		return false;
	}

	public static void setVol() {
		try {
			player.setGain((double) vol / VOL_MAX);
			// player.setPan(0.0);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// 再生していない
		}
	}

	/**
	 * 
	 * @param v
	 *            0.0~1.0にすること
	 */
	public static void setVol(double v) {
		setVol((int) (v * VOL_MAX));
	}

	public static void setVol(int v) {
		vol = v;
		if (vol > VOL_MAX)
			vol = VOL_MAX;
		if (vol < 0)
			vol = 0;
		setVol();
	}

	public static void stop() {
		try {
			if (player != null) {
				player.stop();
			}
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		playingBGM = null;
	}

	public static void waitUntilFadeOut() {
		if (flag_fadeouting) {
			// 他のスレッドから呼び出されていることがある
			do {
				Sleep.done(10l);
			} while (flag_fadeouting);
		} else {
			if (playingBGM != null) {
				flag_fadeouting = true;
				int count = 0;
				double rate = 0.99;
				do {
					fadeOutVol(Math.sin(rate * Math.PI / 2));
					rate -= 0.1;
					// fadeOutVol(rate);
					Sleep.done(3);
				} while (++count < 10);
				stop();
				flag_fadeouting = false;
			} else {
				stop();
			}
		}
	}

	public static void waitUntilFadeOut_Thread() {
		waitUntilFadeOut_Thread(false);
	}

	public static void waitUntilFadeOut_Thread(boolean flag_continus_replay) {
		if (flag_fadeouting)
			return;
		Footer.unSet();
		if (!flag_continus_replay) {
			new Thread() {
				@Override
				public void run() {
					waitUntilFadeOut();
				};
			}.start();
		}
	}

	public final Class<?> CLASS;

	/**
	 * 音楽の音量設定　setterで設定可能
	 */
	private static int vol = Config.getBGMVol();

	public static final int VOL_MAX = 100;

	public final static BasicPlayer player;

	static {
		player = new BasicPlayer();
		player.addBasicPlayerListener(UTIL);
		while (player == null) {
			main.util.Sleep.done(1);
		}
	}

	/**
	 * お気に入り曲再生時用変数
	 */
	public static int loaded = -1;

	private static SpellCard spell;

	private static boolean flag_fadeouting;

	public static void ascVol() {
		vol += 10;
		if (vol >= VOL_MAX)
			vol = VOL_MAX;
		setVol(vol);
	}

	public static void dceVol() {
		vol -= 10;
		if (vol <= 0)
			vol = 0;
		setVol(vol);
	}

	public static String getAutor(SpellCard sc) {
		Class<? extends SpellCard> c = sc.getClass();
		for (CopyOfBGM bgm : values()) {
			if (c.equals(bgm.CLASS)) {
				return bgm.AUTHOR;
			}
		}
		return "(該当なし)";
	}

	/**
	 * @return
	 */
	public static int getVol() {
		return vol;
	}

	public static boolean isBGMStatusStopped() {
		return playingBGM == null || player.getStatus() == BasicPlayer.STOPPED;
	}

	public static synchronized boolean isLoading() {
		for (CopyOfBGM b : opening_map.keySet()) {
			if (opening_map.get(b))
				return true;
		}
		return false;
	}

	public static boolean isSpecialBGMplaying() {
		CopyOfBGM b = loadingBGM != null ? loadingBGM : playingBGM;
		if (b == null)
			return false;
		else
			return b.SEEK >= 0;
	}

	public static void play(SpellCard s) {
		loaded = -1;
		if (s == null || s.BGM_LIST.isEmpty())
			UTIL.play();
		spell = s;
		start();
	}

	public static void start() {
		try {
			if (!play_random(false))
				get(spell.getClass()).play();
		} catch (Exception e) {
			// 楽曲なし？
		}
	}

	public final String NAME;
	public final String TITLE, AUTHOR;
	public final long OFFSET;
	public final int END_FRAME;

	private static CopyOfBGM playingBGM = null, loadingBGM = null;

	public final long SEEK;

	public final boolean Hz_44100;

	private LoadThread load_thread;

	private static HashMap<CopyOfBGM, Boolean> opening_map = new HashMap<CopyOfBGM, Boolean>(
			1);

	private static boolean roop = true;

	private long sleep_ms = 0;

	// public Boolean hz_44100;

	private boolean flag_first;

	public static long frame;

	private static CopyOfBGM playing_holder;

	public static CopyOfBGM get(String bgm) {
		for (CopyOfBGM b : CopyOfBGM.values()) {
			if (b.name().matches(bgm)) {
				return b;
			}
		}
		Show.showCriticalErrorMessageDialog("ファイル", bgm, "に一致するファイルは存在しません");
		return null;
	}

	private static String getSaveURL(int index) {
		StringBuilder sb = new StringBuilder();
		sb.append("save/");
		sb.append(Config.getSaveIndex());
		sb.append("/ms");
		sb.append(index);
		sb.append(".save");
		return sb.toString();
	}

	public static boolean isFlag_fadeouting() {
		return flag_fadeouting;
	}

	public static String save(int index) {
		try {
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(
					new FileOutputStream(getSaveURL(index)));
			String s;
			if (spell != null) {
				oos.writeObject(spell);
				s = spell.getColoredName();
			} else {
				oos.writeObject(playingBGM);
				s = playingBGM.getColoredTitle();
			}
			oos.close();
			return s;
		} catch (Exception e) {
			Show.showErrorMessageDialog(e);
		}
		return null;
	}

	public static Object staticLoad(int index) {
		String url = getSaveURL(index);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(url));
			Object obj = ois.readObject();
			if (obj instanceof SpellCard) {
				play((SpellCard) obj);
			} else {
				((CopyOfBGM) obj).load_start(false);
			}
			ois.close();
			return obj;
		} catch (EOFException e) {
			return "にはまだ何も保存されていません";
		} catch (FileNotFoundException e) {
			return "にはまだ何も保存されていません";
		} catch (InvalidClassException e) {
			// serialVersionUID の不一致
			return "は保存形式が誤っていて再生出来ません";
		} catch (WriteAbortedException e) {
			// serialVersionUID の書き損じ？
			return "は保存形式が誤っていて再生出来ません";
		} catch (Exception e) {
			e.printStackTrace();
			Show.showCriticalErrorMessageDialog(e);
		}
		return "";
	}

	private CopyOfBGM() {
		CSVLoadSupporter<String> list = CSVLoadSupporter.loadCSV(name(),
				CopyOfBGM.class, "\t");
		AUTHOR = list.get();
		TITLE = list.get();
		Class<?> clazz = null;
		try {
			String str = list.get();
			if (!str.matches("null"))
				clazz = Class.forName(str);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		CLASS = clazz;
		Hz_44100 = list.isTrue();
		NAME = list.get()
		// .replaceFirst("bgm", "bgm_raw")
		// .replaceFirst("\\.dat", ".mp3")
		;
		END_FRAME = list.toI();
		SEEK = list.toI();
		System.out.println("bgm: seek\t" + SEEK + "\t" + this);
		OFFSET = list.toL();
	}

	private CopyOfBGM(boolean b) {
		CLASS = null;
		NAME = "";
		TITLE = "";
		AUTHOR = "";
		Hz_44100 = false;
		SEEK = 0;
		OFFSET = 0;
		END_FRAME = 0;
	}

	public String getColoredTitle() {
		return Base_Item.CL_PUNISH + "「" + TITLE + "」";
	}

	private long getDefaultSeek() {
		// return 64000l;
		// return 224000l * 4;
		// return 224000l * 4 / 10;

		// return 2500000l;
		// return 0;
		// long seek = SEEK;
		// if (seek == -1) {
		// seek = 0;
		// }
		return 90000l;
	}

	// long nano;

	public long getMP3MilliFrame(long ms) {
		int HZ = Hz_44100 ? 44100 : 48000;
		return (ms * HZ / 1152);
	}

	public boolean isPlayingBGM() {
		return this.equals(loadingBGM) || this.equals(playingBGM);
	}

	private void load_start(boolean flag_continus_replay) {
		loaded = -1;
		waitUntilFadeOut_Thread(flag_continus_replay);
		opening_map.clear();
		opening_map.put(this, true);
		if (UTIL.load_thread == null) {
			loadingBGM = this;
			UTIL.load_thread = UTIL.new LoadThread(this);
			UTIL.load_thread.start();
		} else {
			UTIL.load_thread.reset(this);
		}
	}

	private synchronized void opened() {
		for (CopyOfBGM b : opening_map.keySet())
			opening_map.put(b, false);

	}

	/**
	 * 再生する準備が整ったら受け取る
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void opened(Object arg0, Map properties) {
		// System.out.println("opened");
		// System.out.println(properties);
		opened();
		// b.hz_44100 = null;
		// b.hz_44100 = properties.get("mp3.frequency.hz").toString()
		// .matches("44100")
		// || properties.get("audio.samplerate.hz").toString()
		// .matches("44100.0");
	}

	public void play() {
		play(0);
	}

	public void play(boolean otog) {
		play(otog ? -1 : 0);
	}

	public void play(long sleep) {
		play(sleep, false);
	}

	public void play(long sleep, boolean flag_continus_replay) {
		if (sleep < 0) {
			sleep_ms = 0;
			roop = false;
		} else {
			sleep_ms = sleep;
			roop = true;
		}
		frame = -1;
		if (playingBGM == this) {
			return;
		} else {
			if (equals(UTIL)) {
				int rnd = new R().nextInt(values().length);
				values()[rnd].play();
			} else {
				load_start(flag_continus_replay);
			}
		}
	}

	/**
	 * 進捗状況を受け取る
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void progress(int bytesread, long microseconds, byte[] pcmdata,
			Map properties) {
		if (playing_holder.flag_first) {
			setVol();
			playing_holder.flag_first = false;
		}
		frame = (Long) properties.get("mp3.frame");
		// System.out.println("▶" + frame + " / " + playing_holder.END_FRAME);
		if (frame >= playing_holder.END_FRAME) {
			// System.out.println("▶ EOM");
		}
		// nano = System.nanoTime();
	}

	private void replay() {
		if (roop && !play_random(true)) {
			try {
				player.seek(getDefaultSeek());
				player.play();
				setVol();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * プレーヤーにコントローラを設定する
	 */
	@Override
	public void setController(BasicController arg0) {
	}

	/**
	 * プレーヤーの状態を受け取る
	 */
	@Override
	public void stateUpdated(BasicPlayerEvent event) {
		if (event.getCode() == BasicPlayerEvent.OPENED) {
		} else if (event.getCode() == BasicPlayerEvent.STOPPED) {
		} else if (event.getCode() == BasicPlayerEvent.PLAYING) {
		} else if (event.getCode() == BasicPlayerEvent.EOM) {
			// System.out.println("End Of Music.");
			new Thread() {
				@Override
				public void run() {
					while (true) {
						if (player.getStatus() == BasicPlayer.STOPPED) {
							replay();
							Sleep.done(1l);
							break;
						}
					}
				};
			}.start();
		}
	}

}