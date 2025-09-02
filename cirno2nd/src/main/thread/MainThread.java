package main.thread;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dangeon.model.config.Config;
import dangeon.view.util.StringFilter;
import main.Scene;
import main.Second_Firster;
import main.constant.FR;
import main.pad.ListenerAdapter;
import main.util.BeautifulView;
import main.util.BlackOut;
import main.util.FrameShaker;
import main.util.Show;
import main.util.Sleep;

public class MainThread extends Thread {
	// public final boolean test = new File("../dangeon").exists();
	public final boolean test = false;
	public static final MainThread ME = new MainThread();

	private long before_time;

	private int over_slept_time = 0;

	private static int koma_frame_counter = 0;

	private static int framerate;
	private static int framecount;
	private static long basetime;

	public static int getFrame() {
		return koma_frame_counter;
	}

	public static int getFramerate() {
		return framerate;
	}

	private static void setFramerate(int framerate) {
		MainThread.framerate = framerate;
	}

	private final int bufferSize = 3;

	private BufferStrategy bufferStrategy;

	private boolean flag_sys_draw = false;

	private BufferedImage[] bis = new BufferedImage[5];

	private boolean[] flag_switched = new boolean[bis.length];

	private static boolean task_size = false, task_fullscreen = false;

	// private void drawTest(Graphics2D g2) {
	// double rate = 0.6;
	// BufferedImage im = new BufferedImage((int) (Frame.SCREEN_WIDTH / rate),
	// (int) (Frame.SCREEN_HEIGHT / rate), BufferedImage.TYPE_INT_RGB);
	// Graphics2D g = im.createGraphics();
	// int x0 = (int) (Frame.SCREEN_WIDTH * (1 / rate - 1) / 2);
	// int y0 = (int) (Frame.SCREEN_HEIGHT * (1 / rate - 1) / 2);
	// g.translate(x0, y0);
	// g2.translate(Starter.ME.insets.left + FrameShaker.shake(),
	// Starter.ME.insets.top);
	// View.draw(g);
	// g2.drawImage(im, 0, 0, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT, null);
	// g2.setColor(Color.BLUE);
	// int w = (int) (Frame.SCREEN_WIDTH * rate);
	// int h = (int) (Frame.SCREEN_HEIGHT * rate);
	// g2.drawRect((Frame.SCREEN_WIDTH - w) / 2,
	// (Frame.SCREEN_HEIGHT - h) / 2, w, h);
	// g2.drawLine(0, 0, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT);
	// g2.drawLine(Frame.SCREEN_WIDTH, 0, 0, Frame.SCREEN_HEIGHT);
	// }

	private static boolean flag_TimeStop;

	public static boolean isTask_size() {
		return task_size || task_fullscreen;
	}

	public static void setSwitchFullScreenTask() {
		task_fullscreen = true;
	}

	public static void setSwitchSizeTask() {
		task_size = true;
	}

	public static void switchWB(boolean b) {
		flag_TimeStop = b;
	}

	private MainThread() {
		this.setPriority(NORM_PRIORITY + 1);
		this.setName(this.getClass().toString());
		basetime = System.nanoTime();
	}

	private void calculateFPS() {
		framecount++;
		long now = System.nanoTime();
		double delt = now - basetime;
		if (delt >= 1000000000L) {
			setFramerate((int) Math.round(framecount * 1000000000.0 / delt));
			basetime = now;
			framecount = 0;
		}
	}

	private void draw(Graphics2D g) {
		AffineTransform at = g.getTransform();
		if (!Second_Firster.ME.isFullScreen()) {
			g.translate(Second_Firster.ME.insets.left,
					Second_Firster.ME.insets.top);
		} else {
			g.translate(Second_Firster.ME.getX_offset(),
					Second_Firster.ME.getY_offset());
		}
		g.scale(Second_Firster.ME.getRateW(), Second_Firster.ME.getRateH());
		g.setClip(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
		g.translate(FrameShaker.shake(), 0);
		Scene.getPresentScene().SYS.draw(g);
		flag_sys_draw = true;
		if (Second_Firster.ME.isFullScreen()) {
			BeautifulView.setAlphaOnImg(g, 1f);
			AffineTransform _at = g.getTransform();
			g.setTransform(at);
			g.setColor(Color.BLACK);
			if (Second_Firster.ME.getX_offset() != 0) {
				int w = Second_Firster.ME.getX_offset() + 1;
				g.fillRect(0, 0, w, Second_Firster.ME.PCScreenH());
				g.fillRect(Second_Firster.ME.PCScreenW() - w, 0, w,
						Second_Firster.ME.PCScreenH());
			} else {
				int h = Second_Firster.ME.getY_offset() + 1;
				g.fillRect(0, 0, Second_Firster.ME.PCScreenW(), h);
				g.fillRect(0, Second_Firster.ME.PCScreenH() - h,
						Second_Firster.ME.PCScreenW(), h);
			}
			g.setTransform(_at);
		}
	}

	private void drawTS(Graphics2D _g) {
		int w = (int) (FR.SCREEN_WIDTH * Second_Firster.ME.getRateW() + 0.5f);
		int h = (int) (FR.SCREEN_HEIGHT * Second_Firster.ME.getRateH() + 0.5f);
		if (!Second_Firster.ME.isFullScreen()) {
			w += Second_Firster.ME.insets.left;
			h += Second_Firster.ME.insets.top;
		} else {
			w += Second_Firster.ME.getX_offset();
			h += Second_Firster.ME.getY_offset();
		}
		BufferedImage bi;
		bi = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = bi.createGraphics();
		BeautifulView.setAntiAliasing(g, true);
		draw(g);
		BlackOut.staticDraw(g);
		g.dispose();
		setClip(_g);
		_g.scale(1, 1);
		_g.drawImage(bi, 0, 0, null);
	}

	public BufferedImage getScreenShot() {
		BufferedImage bi;
		{
			int w = FR.SCREEN_WIDTH;
			int h = FR.SCREEN_HEIGHT;
			bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		}
		// bis[i] = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = bi.createGraphics();
		BeautifulView.setAntiAliasing(g, true);
		{
			g.setClip(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
			Scene.getPresentScene().SYS.draw(g);
		}
		BlackOut.staticDraw(g);
		g.dispose();
		return bi;
	}

	@Override
	public void run() {
		Second_Firster.ME.createBufferStrategy(bufferSize);
		bufferStrategy = Second_Firster.ME.getBufferStrategy();
		before_time = System.nanoTime();
		ListenerAdapter.init();
		while (true) {
			try {
				taskWork();
				ListenerAdapter.frame();
				Scene.getPresentScene().SYS.upDate();
				view();
				calculateFPS();
				// System.out.println("残りメモリ＝" +
				// Runtime.getRuntime().freeMemory()
				// / 1000000 + "[MB]\t合計メモリ="
				// + Runtime.getRuntime().totalMemory() / 1000000 + "MB");
			} catch (Exception e) {
				Show.showErrorMessageDialog(e);
			} catch (Error e) {
				Show.writeErrorText(e);
				Show.showErrorMessageDialog(e.toString());
			}
		}
	}

	private void setClip(Graphics2D g) {
		int x, y;
		if (!Second_Firster.ME.isFullScreen()) {
			x = Second_Firster.ME.insets.left;
			y = Second_Firster.ME.insets.top;
		} else {
			x = Second_Firster.ME.getX_offset();
			y = Second_Firster.ME.getY_offset();
		}
		double _x = x + FR.SCREEN_WIDTH * Second_Firster.ME.getRateW();
		double _y = y + FR.SCREEN_HEIGHT * Second_Firster.ME.getRateH();
		g.setClip(x, y, (int) (_x + 0.5), (int) (_y + 0.5));
	}

	private void setKoma() {
		koma_frame_counter++;
		if (koma_frame_counter == Integer.MAX_VALUE) {
			koma_frame_counter = 0;
		}
	}

	private void taskWork() {
		if (task_size) {
			Second_Firster.ME.switchSize();
			for (int i = 0; i < flag_switched.length; i++)
				flag_switched[i] = true;
			task_size = false;
		}
		if (task_fullscreen) {
			Second_Firster.ME.switchFullScreen();
			task_fullscreen = false;
		}
	}

	/**
	 * グレースケール変換（NTSC係数による加重平均法）
	 */
	protected int toGray(int col) {
		Color c = new Color(col);
		double d = (2 * c.getRed() + 4 * c.getGreen() + c.getBlue()) / 7d;
		int a = c.getAlpha();
		c = new Color((int) d, (int) d, (int) d, a);
		return c.getRGB();
	}

	private void view() {
		flag_sys_draw = false;
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		try {
			if (!bufferStrategy.contentsLost()) {
				if (flag_TimeStop) {
					drawTS(g);
				} else {
					if (Config.isSmooth())
						view_old(g);
					else
						view_zanzou(g);
				}
				Sleep.done();
				if (getFrame() % Config.getSkipRate() == 0)
					bufferStrategy.show();
				Toolkit.getDefaultToolkit().sync();
				StringFilter.upDate();
			} else {
				Show.showInformationMessageDialog("bufferStrategy.contentsLost()");
			}
		} finally {
			g.dispose();
		}
		
		Sleep.done(0);
		if (!flag_sys_draw) {
			Show.showInformationMessageDialog("flag_sys_draw = false");
		}
		setKoma();
	}

	private void view_old(Graphics2D g) {
		BeautifulView.setAntiAliasing(g, true);
		draw(g);
		BlackOut.staticDraw(g);
	}

	private void view_zanzou(Graphics2D _g) {
		int w = (int) (FR.SCREEN_WIDTH * Second_Firster.ME.getRateW() + 0.5f);
		int h = (int) (FR.SCREEN_HEIGHT * Second_Firster.ME.getRateH() + 0.5f);
		if (!Second_Firster.ME.isFullScreen()) {
			w += Second_Firster.ME.insets.left;
			h += Second_Firster.ME.insets.top;
		} else {
			w += Second_Firster.ME.getX_offset();
			h += Second_Firster.ME.getY_offset();
		}
		int i = getFrame() % bis.length;
		if (bis[i] == null || flag_switched[i]) {
			bis[i] = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
			flag_switched[i] = false;
		}
		// bis[i] = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = bis[i].createGraphics();
		BeautifulView.setAntiAliasing(g, true);
		draw(g);
		BlackOut.staticDraw(g);
		g.dispose();
		setClip(_g);
		_g.scale(1, 1);
		_g.drawImage(bis[i], 0, 0, null);
		i += 4;
		if (i >= bis.length)
			i -= bis.length;
		if (bis[i] != null) {
			BeautifulView.setAlphaOnImg(_g, 0.3f);
			_g.drawImage(bis[i], 0, 0, null);
		}
	}

}