package load;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Base_System;
import main.Scene;
import main.constant.FR;
import main.res.BGM;
import main.res.BulletImage;
import main.res.CHARA_IMAGE;
import main.res.Image_Anime;
import main.res.Image_Artifact;
import main.res.Image_Condition;
import main.res.Image_Effect;
import main.res.Image_Icon;
import main.res.Image_LargeCharacter;
import main.res.Image_MapTip;
import main.res.Image_Object;
import main.res.Image_Player;
import main.res.Image_Window_Frame;
import main.res.SE;
import main.thread.MainThread;
import main.util.BeautifulView;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.util.R;
import dangeon.view.constant.BGMFONT;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Loading extends Base_System {
	private enum Step {
		START, NOW, FADE, END;
	}

	private static Step step = null;
	private static float fade = 1F;

	public static void setStr(Object o) {
	}

	private Image_Player ims[];
	private boolean flag_character_readed;
	private boolean flag_font_loaded;

	public static int rnd = -1;

	@Override
	public void draw(Graphics2D g) {
		BeautifulView.setAntiAliasing(g, true);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
		g.setColor(Color.WHITE);
		if (step == Step.NOW || step == Step.END) {
			int frame = MainThread.getFrame() / 12;
			int y = FR.SCREEN_HEIGHT - 15;
			if (flag_font_loaded) {
				drawString(g, frame, y);
			}
			if (flag_character_readed) {
				Image_Player im = ims[frame % ims.length];
				int x = FR.SCREEN_WIDTH - 80;
				g.drawImage(im.IM, x, y - 70, null);
			}
		}
		if (step == null) {
			step = Step.START;
		} else if (step == Step.NOW) {
			fade -= 0.05F;
			if (fade < 0) {
				fade = 0;
				return;
			}
		} else {
			fade = 0;
		}
		BeautifulView.setAlphaOnImg(g, fade);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
	}

	private void drawString(Graphics2D g, int frame, int y) {
		g.setFont(NormalFont.NORMALFONT);
		g.setColor(Color.CYAN);
		int x = FR.SCREEN_WIDTH - g.getFontMetrics().stringWidth("少女ロードちゅ")
				- 10;
		StringBuilder sb = new StringBuilder();
		if (frame % 8 == 0)
			sb.append("　");
		else
			sb.append("少");
		if (frame % 8 == 1)
			sb.append("　");
		else
			sb.append("女");
		if (frame % 8 == 2)
			sb.append("　");
		else
			sb.append("ロ");
		if (frame % 8 == 3)
			sb.append("　");
		else
			sb.append("ー");
		if (frame % 8 == 4)
			sb.append("　");
		else
			sb.append("ド");
		if (frame % 8 == 5)
			sb.append("　");
		else
			sb.append("ち");
		if (frame % 8 == 6)
			sb.append("　");
		else
			sb.append("ゅ");
		StringFilter.drawString(g, sb.toString(), x, y);
	}

	private void load(CHARA_IMAGE c) {
	}

	private void load(Object o) {
	}

	@Override
	public void upDate() {
		if (step == Step.END) {
		} else if (step == Step.START) {
			int r = R.ran(10);
			if (r < 5) {
				Image_Player ims[] = { Image_Player.munya0, Image_Player.munya1 };
				this.ims = ims;
			} else if (r < 10) {
				Image_Player ims[] = { Image_Player.mogu0, Image_Player.mogu1 };
				this.ims = ims;
			} else {
				Image_Player ims[] = { Image_Player.sleep0, Image_Player.sleep1 };
				this.ims = ims;
			}
			final long time = System.nanoTime();
			System.out.println("LoadingStart");
			step = Step.NOW;
			new Thread() {
				@Override
				public void run() {
					load(CHARA_IMAGE.arrow);
					flag_character_readed = true;
					load(NormalFont.NORMALFONT);
					flag_font_loaded = true;
					load(BGMFONT.FONT);
					load(Image_LargeCharacter.ANY);
					load(Image_Artifact.NULL);
					load(BulletImage.MagicBullet);
					load(Image_Anime.explo);
					load(Image_Artifact.NULL);
					load(Image_MapTip.スキマ);
					load(Image_Effect.HitA_);
					load(Image_Player.book0);
					load(Image_Condition.strange_dark);
					load(Image_Icon.stt_icon_death);
					load(Image_Object.cir_damage);
					load(Image_Window_Frame.menu_1);
					load(CONDITION.ええんじゃないか);
					load(SE.APPEAR);
					Config.setSaveindex(4);

					BGM.kanpyo_ch2_fairy.play();
					// str = "BGM";
					// BGM.The_boundary_of_the_world_wob.play();
					// BGM.alarm00r.play();
					System.out.println("LoadingEnd : "
							+ (System.nanoTime() - time) / 1000000000 + " s");
					Scene.DANGEON.setScene();
					step = Step.END;
				}
			}.start();
		}
	}

}
