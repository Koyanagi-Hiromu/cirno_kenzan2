package dangeon.view.detail;

import java.awt.Color;
import java.awt.Graphics2D;

import dangeon.controller.DangeonScene;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.StairScene;
import dangeon.model.map.field.random.second.七曜クエスト;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.BGMFONT;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;
import main.constant.FR;
import main.util.BeautifulView;

public class View_StairScene {
	public static void draw(Graphics2D g) {
		StairScene ss = (StairScene) DangeonScene.STAIRS.getTask();
		if (ss != null) {
			ss.work();
			double rate = Math.sin(Math.PI * ss.getFrame() / ss.FRAME);
			BeautifulView.setAlphaOnImg(g, (float) (1f * rate));
			g.setColor(Color.BLACK);
			int s = 30;
			g.fillRect(-s, -s, FR.SCREEN_WIDTH + s * 2, FR.SCREEN_HEIGHT + s
					* 2);
			drawFloor(g);
			g.setColor(Color.WHITE);
		}
	}

	private static void drawDangeonMap(Graphics2D g) {
		// && Config.isHack_playing()
		// String s = MassCreater.getHackName();
		// s = "[" + s.substring(0, 1) + "] " + s.substring(1);
		// g.drawString(s, FR.SCREEN_WIDTH / 2
		// - g.getFontMetrics().stringWidth(s) / 2,
		// FR.SCREEN_HEIGHT / 2 - 50);
		boolean isQuest = PresentField.get() instanceof 七曜クエスト; 
		if (isQuest)
			g.setFont(NormalFont.NORMALFONT);
		else
			g.setFont(BGMFONT.FONT);
		StringBuilder s = new StringBuilder();
		s.append(PresentField.get().getMapName());
		drawMap(g, s.toString());
		if (isQuest)
			g.setFont(BGMFONT.FONT);

		s = new StringBuilder();
		s.append(" -");
		s.append(PresentField.get().getRandomMap().getDIFFICULTY().name());
		s.append("- ");
		s.append(MapList.getFloor());
		s.append("Ｆ");
		g.drawString(s.toString(), 100, FR.SCREEN_HEIGHT / 2);
		g.setFont(NormalFont.NORMALFONT);
		if (Config.isLapON()) {
			StringBuilder sb = new StringBuilder();
			long t = Player.me.getPlayingMilliTime();
			boolean h = false;
			t /= 1000;
			if (t > 3600) {
				sb.append(Color.ORANGE);
				sb.append(t / 3600);
				t -= t / 3600 * 3600;
				sb.append("時 ");
				h = true;
			}
			sb.append(Color.RED);
			if (t > 60) {
				if (h && t / 60 < 10) {
					sb.append("0");
				}
				sb.append(t / 60);
				t -= t / 60 * 60;
				sb.append("分 ");
			}
			sb.append(Color.YELLOW);
			if (t < 10) {
				sb.append("0");
			}
			sb.append(t);
			sb.append("秒 ");
			if (!h) {
				sb.append(Color.lightGray);
				t = Player.me.getPlayingMilliTime();
				t = t - t / 1000 * 1000;
				if (t < 10) {
					sb.append("00");
				} else if (t < 100) {
					sb.append("0");
				}
				sb.append(t);
			}
			StringFilter.drawString(g, sb.toString(), 200,
					FR.SCREEN_HEIGHT / 2 + 50);
		}
	}

	private static void drawFloor(Graphics2D g) {
		g.setColor(Color.WHITE);
		if (PresentField.get().isHaraheru()) {
			drawDangeonMap(g);
		} else {
			drawTwonMap(g);
		}
	}

	private static void drawMap(Graphics2D g, String s) {
		g.drawString(s, FR.SCREEN_WIDTH / 2 - g.getFontMetrics().stringWidth(s)
				/ 2, FR.SCREEN_HEIGHT / 2 - 50);
	}

	private static void drawTwonMap(Graphics2D g) {
		g.setFont(NormalFont.NORMALFONT);
		drawMap(g, PresentField.get().getMapName());
	}
}
