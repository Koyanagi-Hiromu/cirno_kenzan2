/**
 *
 */
package main.res;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import load.Loading;
import main.util.FileReadSupporter;
import main.util.Sleep;

public enum CopyOfSE implements LineListener {
	PICKUP("puu76"), ALARM("beep12"), MOVE("step03"), DAMAGED_NORMAL("hit76_d"), DAMEGED_ANIME(
			"tm2_hit002"), MONSTER_HOUSE("alarm01"), LIGHTNING("bom00"), MASTERSPARK(
			"tm2_lightning000"), HAMMER("hit28"), BATTLE_DOME("hit09"), ENTER(
			"clock02"), ARRANGEMENT("kucha04"), CURSOR("clock03"), CHANCEL(
			"on09"), MISS("fm015_c"), CURSE("coin00"), ENCHANT("open56"), ENCHANT_OFF(
			"weapon00"), THROW("swing12"), NITORI_SP("metal26_b"), MARISA_SP(
			"metal26_b"), REIMU_SP("metal26_b"), MEIRIN_SP("metal26_b"), PACHU_SP(
			"metal26_b"), SAKUYA_SP("metal26_b"), YOUMU_SP("tm2_slidedoor000"), YOUMU_SP2(
			"freeze04"), INABA_SP("metal26_b"), AYA_SPELL("metal26_b"), RETHI(
			"metal26_b"), MISTHIA_SPELL("voice023_a"), EIRIN_SP("metal26_b"), ONIGIRI(
			"metal26_b"), THROW_HEAVY("swing40_d"), KYOUKA("metal26_b"), TEI_SP_ATK(
			"metal26_b"), KOGASA_SPELL("puu17"), DAMAGED_CRITICAL("hit81"), MOKOU(
			"burst00"), YAMAME_SP("metal26_b"), ARISU_SP("metal26_b"), YUYUKO_SPELL(
			"tm2_death000"), YUYUKO_SPELL2("tm2_death001"), YUKARI_SPELL(
			"puu81"), SANAE_SPELL("metal26_b"), BOMB("tm2_bom002"), KOMATI_SPELL(
			"metal26_b"), KEINE_SP("voice023_a"), DIGG("biri02"), LEVEL_DOWN(
			"metal26_b"), LEVEL_UP("tm2_quiz004"), ゆっくり("ゆっくり"), EAT("eat02"), SCROLL(
			"paper01"), MAGIC("ta_fa_maho06"), USING_SPELLCARD("power36"), USING_RING(
			"power11"), USING_DISC("cursor35"), ATTACK_HEAVY("swing03"), ATTACK_HANDS(
			"swing40_a"), ATTACK_SWORD("hit_s06"), ATTACK_EAT("lip01"), UTSUHO(
			"don07"), ATTACK_ROOLING("byoro06"), ATTACK_SPEAR("hit17"), HEAL_GREATER(
			"power09"), HEAL_SMALL("power21"), ATTACK_WATER("hit_s13"), ATTACK_AURA(
			"fire00"), ATTACK_SMALL_OBJECT("arrow01"), ATTACK_SCRATCH("hit_s02"), ATTACK_SUKIMA(
			"jya00"), POWER_UP("power09"), WARP_INSTANT("warp02"), APPEAR(
			"smoke02"), ONAKASUITA("open51"), MENU("on03"), DECURSE(
			"tm2_mind000"), STATUS_SIBIBI("kachi15"), STATUS_SLOW("push24"), STATUS_SEAL(
			"tm2_coin000"), STATUS_PIYOPIYO("pyoro41"), STATUS_DODODO("hit_p09"), ICE(
			"freeze06"), TRAP_ON("on14");

	private Clip CLIP;
	private static int vol = 10;
	private static final int VOL_MAX = 100;

	/**
	 * While you can obtain any number of Clip instances, only 32 can be open at
	 * the same time. This is a hard limitation of the engine; it can only mix
	 * 32 channels.
	 */
	private static final int MAX = 20;

	private static List<CopyOfSE> list_opening_clip = new ArrayList<CopyOfSE>(
			MAX);

	private FloatControl ctrl_vol;

	private int stop_to_play_count = 0;
	private AudioInputStream AUDIO;
	private final String FILE_NAME;

	private static boolean flag_mute = false;

	public static void mute(boolean b) {
		flag_mute = b;
	}

	private CopyOfSE(String file_name) {
		Loading.setStr(this);
		FILE_NAME = file_name;
		init();
	}

	private void close() {
		CLIP.setFramePosition(0);
		CLIP.close();
		new Thread() {
			@Override
			public void run() {
				init();
			};
		}.start();
		list_opening_clip.remove(this);
	}

	private void init() {
		long time = System.nanoTime();
		AudioInputStream audio = null;
		try {
			audio = AudioSystem.getAudioInputStream(FileReadSupporter
					.readURL("res/se/".concat(FILE_NAME).concat(".wav")));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AUDIO = audio;
		// System.out.println(name() + "_load：" + (System.nanoTime() - time)
		// / 1000000);
	}

	private void initClip() {
		try {
			while (AUDIO == null) {
				Sleep.done();
			}
			DataLine.Info info = new DataLine.Info(Clip.class,
					AUDIO.getFormat());
			CLIP = (Clip) AudioSystem.getLine(info);
			CLIP.open(AUDIO);
			ctrl_vol = (FloatControl) CLIP
					.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void open() {
		if (list_opening_clip.contains(this)) {
			list_opening_clip.remove(this);
			list_opening_clip.add(this);
		} else {
			if (list_opening_clip.size() >= MAX) {
				list_opening_clip.get(0).close();
			}
			if (CLIP == null) {
				initClip();
			} else if (!CLIP.isOpen()) {
				try {
					CLIP.open(AUDIO);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
			list_opening_clip.add(this);
			CLIP.addLineListener(this);
			ctrl_vol = (FloatControl) CLIP
					.getControl(FloatControl.Type.MASTER_GAIN);
			ctrl_vol.setValue(new Float(Math.log10((double) vol / VOL_MAX * 2)) * 20);
		}

	}

	public void play() {
		if (vol == 0 || flag_mute) {
			return;
		}
		open();
		if (CLIP.isRunning()) {
			stop_to_play_count++;
			CLIP.stop();
		} else {
			CLIP.setFramePosition(0);
			CLIP.start();
		}
	}

	@Override
	public void update(final LineEvent event) {
		if (stop_to_play_count > 0 && event.getType().equals(Type.STOP)) {
			// new Thread() {
			// @Override
			// public void run() {
			// stop_to_play_count -= 2;
			// CLIP.setFramePosition(0);
			// while (true) {
			// Sleep.done(1);
			// if (CLIP.isRunning()) {
			// break;
			// }
			// CLIP.start();
			// if (stop_to_play_count < 0) {
			// stop_to_play_count = 0;
			// }
			// }
			// System.out.println(name() + "_stop_to_play_count:"
			// + stop_to_play_count);
			// }
			// }.start();
		}
	}
}