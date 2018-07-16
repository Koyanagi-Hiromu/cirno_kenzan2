package dangeon.latest.scene.action.otog.main.panel;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Listener.ACTION;
import main.constant.FR;
import main.res.BGM;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.Fader;
import dangeon.latest.scene.action.otog.Scene_Otog;
import dangeon.latest.scene.action.otog.main.Initializer;
import dangeon.latest.scene.action.otog.object.Accepter;
import dangeon.latest.scene.action.otog.object.Base_Otog_Object;
import dangeon.latest.scene.action.otog.result.Scene_Otog_Result;
import dangeon.latest.scene.action.otog.value.Calculater;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.latest.scene.action.otog.value.ValueHolder;
import dangeon.latest.system.KeyHolder;
import dangeon.view.util.StringFilter;

public class Panel_Main extends Base_Panel {

	public ValueHolder VH;

	public final KeyHolder KH;

	public final Scene_Otog PARENT;
	private Calculater CALC;
	public long[] prev_frams = new long[16];
	public long pre_ave;

	private int combo = 0;

	private TEXT tex;

	public boolean black_oouting;

	public Panel_Main(int margine, Scene_Otog scene_Otog, ValueHolder vh) {
		super(margine * 2 + 40, margine, 180, FR.SCREEN_HEIGHT - margine * 2,
				null);
		PARENT = scene_Otog;
		VH = vh;
		VH.init(this);
		KH = scene_Otog.KH;
		CALC = new Calculater();
	}

	public void action(ACTION a) {
		KH.setRequestReleasePushingKey(a);
		for (Accepter ac : VH.accs) {
			if (ac.push(a, null))
				VH.pushed(a);
		}
	}

	public void arrow(DIRECTION d) {
		KH.setRequestReleasePushingKey(d);
		for (Accepter a : VH.accs) {
			if (a.push(null, d))
				VH.pushed(d);
		}
	}

	private long ave(long frame) {
		long sum = 0;
		for (int i = 0; i < prev_frams.length - 1; i++) {
			sum += prev_frams[i] = prev_frams[i + 1];
			// System.out.print(prev_frams[i]);
			// System.out.print(",");
		}
		sum += prev_frams[prev_frams.length - 1] = frame;
		// System.out.println(frame);
		return (sum + prev_frams.length - 1) / prev_frams.length;
	}

	@Override
	protected void drawBG_protected(Graphics2D g) {
		g.setColor(new Color(0, 255, 255, 100));
		g.fillRect(0, 0, W, H);
		for (Accepter a : VH.accs)
			a.draw_oku(g);
		for (Base_Otog_Object o : VH.bars)
			o.draw(g);
	}

	@Override
	public void drawME(Graphics2D g) {
		for (Accepter a : VH.accs)
			a.draw(g);
		if (tex != null) {
			String n = tex.name();
			if (tex == TEXT.COLD) {
				n = n.concat("!! ");
			}
			if (combo > 0) {
				n = n.concat(" ").concat(String.valueOf(combo));
			}
			int w = g.getFontMetrics().stringWidth(n);
			tex.setColor(g, pre_ave);
			StringFilter.drawString(g, n, (W - w) / 2, H / 2);
		}
		for (Base_Otog_Object o : VH.objects)
			o.draw(g);
		for (int i = VH.list.size() - 1; i >= 0; i--) {
			VH.list.get(i).draw(g);
		}
		g.setClip(null);
	}

	public boolean gameStart(BGM bgm, boolean demo) {
		set(null);
		VH.demo = demo;
		VH.setBgm(bgm);
		Initializer ini = new Initializer(VH);
		long milli_sec = ini.game_start();
		if (milli_sec == -1)
			return false;
		init(bgm, milli_sec, ini.first_beats);
		VH.bgm.play(true);
		return true;
	}

	@Override
	protected int getBGColorB() {
		return super.getBGColorB();
	}

	@Override
	protected int getBGColorG() {
		return super.getBGColorG();
	}

	@Override
	protected int getBGColorR() {
		return super.getBGColorR();
	}

	public void init(BGM bgm, long milli_frame, int beats) {
		for (int i = 0; i < prev_frams.length; i++)
			prev_frams[i] = -bgm.getMP3MilliFrame(milli_frame
					* (prev_frams.length - i - 1) / beats) / 1000;
	}

	public void interrupt() {
		new Fader(PARENT, new Scene_Otog_Result(PARENT)) {
			@Override
			protected void taskWork() {
				for (int i = 0; i < VH.list.size(); i++) {
					CALC.calc(VH, TEXT.MISS);
				}
			}
		};
	}

	public void resetVH() {
		VH.init(this);
	}

	public void set(TEXT tex) {
		if (black_oouting)
			return;
		this.tex = tex;
		if (tex != null && tex.isCombo()) {
			if (++combo > VH.MAX_COMBO)
				VH.MAX_COMBO = combo;
		} else
			combo = 0;
		CALC.calc(VH, tex);
		VH.sub3.set(tex);
	}

	@Override
	public void upDate() {
		// if (!BGM.isFlag_fadeouting()) {
		if (!BGM.isFlag_fadeouting() && !BGM.isBGMStatusStopped()) {
			VH.upDate(pre_ave = ave(BGM.getFrame()));
		}
		if (!black_oouting) {
			VH.west.upDate(pre_ave);
			VH.sub3.upDate();
			;
		}
	}

}
