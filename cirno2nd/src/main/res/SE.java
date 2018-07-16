/**
 *
 */
package main.res;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import load.Loading;
import main.thread.MainThread;
import main.util.FileReadSupporter;
import main.util.Show;
import main.util.Sleep;
import dangeon.model.config.Config;

public enum SE {
	SYSTEM_PICKUP("puu76"), SYSTEM_ALARM("beep12"), SYSTEM_STAIR_STEP("step03"), SYSTEM_DAMAGED_NORMAL(
			"hit76_d"), SYSTEM_DAMEGED_ANIME("tm2_hit002"), SYSTEM_MONSTER_HOUSE(
			"alarm01"), LIGHTNING("bom00"), MASTERSPARK("tm2_lightning000"), SYSTEM_ENTER(
			"clock02"), SYSTEM_ARRANGEMENT("kucha04"), SYSTEM_CURSOR("clock03"), SYSTEM_CANCEL(
			"on09"), MISS("fm015_c"), SYSTEM_CURSE("coin00"), SYSTEM_ENCHANT(
			"open56"), SYSTEM_ENCHANT_OFF("weapon00"), THROW("swing12"), CHANGE_ITEM(
			"metal26_b"), TIME_STOP("eco03"), FIRST_OURA("tm2_slidedoor000"), YOUMU_SP2(
			"freeze04"), MISTHIA_VOISE("power04"), THROW_HEAVY("swing40_d"), KYOUKA(
			"power10"), KOGASA_SPELL("puu17"), DAMAGED_CRITICAL("hit81"), BURN(
			"burst00"), YUYUKO_SPELL("tm2_death000"), YUYUKO_SPELL2(
			"tm2_death001"), YUKARI_SPELL("puu81"), MIRACLE_ONIGIRI("power12"), BOMB(
			"tm2_bom002"), KEINE_SP("voice023_a"), DIGG("biri02"), LEVEL_DOWN(
			"pyoro12"), LEVEL_UP("lvup"), ATTACK_WAVE("fm000"), ATTACK_YUKKURI(
			"ゆっくり"), ATTACK_SWING("swing02"), SYSTEM_EAT("eat02"), SYSTEM_SCROLL(
			"saku10"), SYSTEM_MAGIC("ta_fa_maho06"), SYSTEM_USING_SPELLCARD(
			"power36"), SYSTEM_USING_RING("power11"), SYSTEM_USING_DISC(
			"cursor35"), ATTACK_HEAVY("swing03"), ATTACK_HANDS("swing40_a"), ATTACK_SWORD(
			"hit_s06"), ATTACK_EAT("lip01"), THROW_BOMB("don07"), ATTACK_ROOLING(
			"byoro06"), ATTACK_SPEAR("hit17"), HEAL_GREATER("power09"), HEAL_SMALL(
			"power21"), ATTACK_WATER("hit_s13"), ATTACK_AURA("fire00"), ATTACK_SMALL_OBJECT(
			"arrow01"), ATTACK_SCRATCH("hit_s02"), ATTACK_UNKNOWN("jya00"), POWER_UP(
			"push19"), WARP("puu09"), WARP_INSTANT("warp02"), APPEAR("smoke02"), ONAKASUITA(
			"open51"), SYSTEM_MENU("on03"), DECURSE("tm2_mind000"), STATUS_SIBIBI(
			"kachi15"), STATUS_SLOW("push24"), STATUS_SEAL("tm2_coin000"), STATUS_PIYOPIYO(
			"pyoro41"), STATUS_DODODO("hit_p09"), ICE("freeze06"), SYSTEM_TRAP_ON(
			"on13_a"), STATUS_SLEEP("mizu01_e"), STATUS_POWER_UP("power03"), STATUS_SPEEDY(
			"power35"), SUMMON("tm2_quiz001good"), FALL("fall05"), ATTACK_FIRE(
			"tm2_bom004"), LUCKEY("coin04"), GOGOGO("goro03"), CREASY_WAVE(
			"fm006"), FANFARE1("fan1"), FANFARE2("fan2"), POIZON("mizu05"), ATTACK_SPECIAL_BODY(
			"push35_c"), ATTACK_SHOOT_ICY("cursor25"), LIGHT_ON("cursor09"), STATUS_GOOD(
			"power32"), STATUS_MEGUSURI("power22"), ZAKUZAKU("gara03"), STATUS_SHADOW(
			"metal36_b"), REIMU_BARRIER("metal34_b"), CHECK("puu27"), PITFALL_OPEN(
			"mecha33"), MEKKI("metal38"), RAIN("water00"), PAPER("paper00"), ISHUKUSHO(
			"puu01"), IKAKUTYO("puu74"), ATTACK_SHOOT("swing17"), BROKEN(
			"coin08"), BREAKINTOONEROOM("bom19_b"), THUNDER("noise12"), WAOON(
			"voice031"), AMANOJACK("amanojack_pyoro28"), BIGGER("fm010");

	private Clip CLIP;
	private static int vol = Config.getSEVol();
	public static final int VOL_MAX = 100;

	/**
	 * While you can obtain any number of Clip instances, only 32 can be open at
	 * the same time. This is a hard limitation of the engine; it can only mix
	 * 32 channels.
	 */
	private static final int MAX = 20;

	private static ArrayList<SE> list_opening_clip = new ArrayList<SE>(MAX);

	public static void mute(boolean b) {
		flag_mute = b;
	}

	public static void setVol(int se_vol) {
		vol = se_vol;
	}

	private FloatControl ctrl_vol;

	private final String FILE_NAME;

	private int start_frame = -1;

	private static boolean flag_mute = false;

	private static boolean flag_limmit = Config.isSELimmite();

	public static SE get(String name) {
		for (SE se : SE.values()) {
			if (se.name().matches(name))
				return se;
		}
		Show.showErrorMessageDialog("the name of SE is not found : " + name);
		return ATTACK_AURA;
	}

	private boolean flag_closing = false;

	private SE(String file_name) {
		Loading.setStr(this);
		FILE_NAME = file_name;
		if (!Config.isSELimmite()) {
			init();
		}
	}

	private void close() {
		new Thread() {
			@Override
			public void run() {
				flag_closing = true;
				CLIP.close();
				CLIP = null;
				ctrl_vol = null;
				start_frame = -1;
				flag_closing = false;
			}
		}.start();
	}

	private void init() {
		long time = System.nanoTime();
		while (flag_closing) {
			Sleep.done();
		}
		AudioInputStream audio = null;
		try {
			audio = AudioSystem.getAudioInputStream(FileReadSupporter
					.readURL("res/se/".concat(FILE_NAME).concat(".wav")));
			DataLine.Info info = new DataLine.Info(Clip.class,
					audio.getFormat());
			CLIP = (Clip) AudioSystem.getLine(info);
			CLIP.open(audio);
			audio.close();
			ctrl_vol = (FloatControl) CLIP
					.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (LineUnavailableException e) {
			Config.PR.saveProperty("SE_Limmit", true);
			Show.showErrorMessageDialog("お使いのＰＣは効果音の読み込み数に限界があります\n設定を変更しましたので次のメッセージのあと再起動して下さい");
			Show.showCriticalErrorMessageDialog(e);
			flag_limmit = true;
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(name() + "_load：" + (System.nanoTime() - time)
				/ 1000000);
	}

	private boolean isLimmit() {
		return flag_limmit;
	}

	private void open() {
		if (list_opening_clip.contains(this)) {
			if (CLIP == null || ctrl_vol == null) {
				init();
			}
			list_opening_clip.remove(this);
			list_opening_clip.add(this);
		} else {
			init();
			if (list_opening_clip.size() == MAX) {
				list_opening_clip.remove(0).close();
			}
			list_opening_clip.add(this);
		}
	}

	public void play() {
		if (vol == 0 || flag_mute) {
			return;
		}
		if (Config.isSELimmite()) {
			open();
		}
		ctrl_vol.setValue(new Float(Math.log10((double) vol / VOL_MAX * 2)) * 20);
		int frame = MainThread.getFrame();
		int del = frame - start_frame;
		if (del >= 0 && del <= 2) {
			// 呼び出しフレームから２フレームまで呼び出し無効
			return;
		}
		if (CLIP.isRunning()) {
			new Thread() {
				@Override
				public void run() {
					while (CLIP.isRunning()) {
						CLIP.stop();
						Sleep.done(1);
					}
					CLIP.setFramePosition(0);
					CLIP.start();
				};
			}.start();
		} else {
			new Thread() {
				@Override
				public void run() {
					CLIP.setFramePosition(0);
					CLIP.start();
				};
			}.start();
		}
		start_frame = frame;
	}

}