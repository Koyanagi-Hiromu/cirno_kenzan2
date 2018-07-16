package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.constant.FR;
import main.constant.PropertySupporter;
import main.pad.ListenerAdapter;
import main.res.BGM;
import main.thread.MainThread;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.util.Switch;
import dangeon.view.constant.MAP;
import debugMode.WindowDebug;

public class Second_Firster extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Second_Firster ME;

	private static void BGMwriter() {

		// BGM.black.play();
		// if (true)
		// return;
		int i = 0;
		BGM bs[] = { BGM.kagerou, BGM.iku, BGM.Mermaidia, BGM.kanpyo_ch2_hexa,
				BGM.Dingy_rip };
		for (BGM b : bs) {
			if (b != BGM.UTIL) {
				b.play();
				BGM.waitUntilFadeOut();
				System.out.println(++i);
			}
		}
		System.out.print("name");
		System.out.print("\t");
		System.out.print("AUTHOR");
		System.out.print("\t");
		System.out.print("Hz_44100");
		System.out.print("\t");
		System.out.print("NAME");
		System.out.print("\t");
		System.out.print("seek");
		System.out.print("\t");
		System.out.print("TITLE");
		System.out.print("\t");
		System.out.print("end_frame");
		System.out.println();
		for (BGM b : bs) {
			if (b != BGM.UTIL) {
				System.out.print(b.name());
				System.out.print("\t");
				// System.out.print(b.hz_44100);
				// System.out.print("\t");
				// System.out.print(b.CLASS);
				System.out.println();
			}
		}
	}

	public static void start() {
		ME = new Second_Firster();
		new WindowDebug();
		MainThread.ME.start();
		ME.requestFocus();
	}

	public final Insets insets;

	private GraphicsDevice device;

	private boolean fullscreen;

	private int width, height;

	private float scale_w = 1f, scale_h = 1f;

	private boolean flag_screen_doubled;
	private final PropertySupporter PR;

	private boolean flag_supportedfullscreen;

	private float x_offset, y_offset;

	private Second_Firster() {
		PR = Config.PR;
		setIconImage(new ImageIcon("./res/image/artifact/stairs.dat")
				.getImage());
		getContentPane().setBackground(Color.BLACK);
		setBackground(Color.BLACK);
		setTitle(Switch.getMainTitle());
		setSize(main.constant.FR.SCREEN_WIDTH, main.constant.FR.SCREEN_HEIGHT);
		setResizable(MainThread.ME.test);
		setLocationRelativeTo(null);
		initFullScreen();
		setVisible(true);
		insets = getInsets();
		width = main.constant.FR.SCREEN_WIDTH + insets.left + insets.right;
		height = main.constant.FR.SCREEN_HEIGHT + insets.top + insets.bottom;
		if (MainThread.ME.test) {
			width += MAP.TILE_SIZE * 3;
			height += MAP.TILE_SIZE * 2;
		}
		setSize(width, height);
		setIgnoreRepaint(true);
		flag_screen_doubled = !PR.isGotPropertyTrue("flag_screen_doubled");
		switchSize();
		setLocationRelativeTo(null);
		addKeyListener(Listener.ME);
		ListenerAdapter.setListener(Listener.ME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fullscreen = PR.isGotPropertyTrue("fullscreen");
		if (fullscreen) {
			setFullScreen();
		}
	}

	public void drawBlack(Graphics2D g) {
		g.scale(1, 1);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getX_offset(), getBounds().height);
		g.fillRect(getBounds().width - getX_offset(), 0, getX_offset(),
				getBounds().height);
	}

	public float getRateH() {
		return scale_h;
	}

	public float getRateW() {
		return scale_w;
	}

	public int getX_offset() {

		return Math.round(x_offset);
	}

	public int getY_offset() {
		return Math.round(y_offset);
	}

	/**
	 * フルスクリーンモードの初期化
	 * 
	 */
	private void initFullScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		device = ge.getDefaultScreenDevice();

		flag_supportedfullscreen = device.isFullScreenSupported();

	}

	public boolean isDoubledScreen() {
		return flag_screen_doubled;
	}

	public boolean isFullScreen() {
		return fullscreen;
	}

	public int PCScreenH() {
		return getBounds().height;
	}

	public int PCScreenW() {
		return getBounds().width;
	}

	private void restoreFullScreen() {
		dispose();
		setCursor(null);
		setUndecorated(false);
		fullscreen = false;
		device.setFullScreenWindow(null);
		if (flag_screen_doubled) {
			scale_w = 2f;
			scale_h = 2f;
		} else {
			scale_w = 1f;
			scale_h = 1f;
		}
		PR.saveProperty("fullscreen", fullscreen);
		setVisible(true);
	}

	/**
	 * ディスプレイモードを設定
	 * 
	 * @param width
	 * @param height
	 * @param bitDepth
	 */
	private void setDisplayMode(int width, int height, int bitDepth) {
		if (!device.isDisplayChangeSupported()) {
			System.out.println("ディスプレイモードの変更はサポートされていません。");
			return;
		}

		DisplayMode dm = new DisplayMode(width, height, bitDepth,
				DisplayMode.REFRESH_RATE_UNKNOWN);
		device.setDisplayMode(dm);
	}

	private void setFullScreen() {
		dispose();
		// setVisible(false);
		// addNotify();
		setUndecorated(true);
		// 必要ならマウスカーソルを消す
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE),
				new Point(), "");
		setCursor(cursor);
		if (!flag_supportedfullscreen) {
			Message.set("フルスクリーンモードがサポートされていません。");
			fullscreen = false;
			PR.saveProperty("fullscreen", fullscreen);
			return;
		}
		fullscreen = true;
		PR.saveProperty("fullscreen", fullscreen);
		device.setFullScreenWindow(this);
		scale_h = scale_w = Math.min((float) getBounds().width
				/ FR.SCREEN_WIDTH, (float) getBounds().height
				/ FR.SCREEN_HEIGHT);
		x_offset = y_offset = 0;
		if (getBounds().width > getBounds().height) {
			x_offset = getBounds().width - FR.SCREEN_WIDTH * scale_w;
			x_offset /= 2;
		} else {
			y_offset = getBounds().height - getBounds().width;
			y_offset /= 2;
		}
		setVisible(true);
	}

	/**
	 * 現在のディスプレイモードを表示
	 * 
	 */
	private void showCurrentMode() {
		DisplayMode dm = device.getDisplayMode();
		System.out.println("現在のディスプレイモード");
		System.out.println("Width: " + dm.getWidth());
		System.out.println("Height: " + dm.getHeight());
		System.out.println("Bit Depth: " + dm.getBitDepth());
		System.out.println("Refresh Rate: " + dm.getRefreshRate());
	}

	public void switchFullScreen() {
		if (fullscreen) {
			restoreFullScreen();
		} else {
			setFullScreen();
		}
	}

	public void switchSize() {
		if (fullscreen) {
			restoreFullScreen();
		}
		flag_screen_doubled = !flag_screen_doubled;
		width = main.constant.FR.SCREEN_WIDTH + insets.left + insets.right;
		height = main.constant.FR.SCREEN_HEIGHT + insets.top + insets.bottom;
		scale_w = 1f;
		scale_h = 1f;
		if (flag_screen_doubled) {
			width += main.constant.FR.SCREEN_WIDTH;
			height += main.constant.FR.SCREEN_HEIGHT;
			scale_w = 2f;
			scale_h = 2f;
		}
		PR.saveProperty("flag_screen_doubled", flag_screen_doubled);
		setSize(width, height);
	}

}
