package dangeon.latest.scene.action.otog.ready.select;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.res.CHARA_IMAGE;
import main.res.Image_Anime;
import main.res.Image_Artifact;
import main.res.Image_LargeCharacter;
import main.res.Image_Player;
import main.thread.MainThread;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import dangeon.latest.scene.Base_View;
import dangeon.model.config.Config;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.RotateDraw;
import dangeon.view.util.StringFilter;

public class Otog_Select_View extends Base_View {

	private BufferedImage IM = null;

	private BufferedImage im, im2, im3;

	public Otog_Select_View(Base_View bv) {
		super(bv);
		im = FileReadSupporter.readImage("res/image/".concat("anime/ice.png"));
		im2 = FileReadSupporter.readImage("res/image/"
				.concat("anime/biriri.png"));
		int margine = 0;
		int x = margine, y = margine;
		int width = FR.SCREEN_WIDTH - x * 2;
		int height = FR.SCREEN_HEIGHT - y * 2;
		im3 = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		createBG(im3.createGraphics(), width, height);
	}

	private void createBG(Graphics2D g, int width, int height) {
		g.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, height, new Color(
				100, 200, 255)));
		g.fillRect(0, 0, width, height);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBG(g, current);
		if (current) {
			Otog_Select_First s = (Otog_Select_First) PARENT_SCENE;
			g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
			for (int i = 0; i < s.content.size(); i++) {
				s.content.get(i).drawMe(g, FR.SCREEN_WIDTH / 4, i + 2);
			}
		}
	}

	private void drawBG(Graphics2D g, boolean current) {
		if (!current) {
			if (IM == null) {
				BufferedImage im = new BufferedImage(FR.SCREEN_WIDTH,
						FR.SCREEN_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR_PRE);
				Graphics2D off = im.createGraphics();
				drawBG(off, true);
				off.dispose();
				IM = im;
			}
			g.drawImage(IM, 0, 0, null);
			return;
		} else {
			IM = null;
		}
		int margine = 0;
		int x = margine, y = margine;
		int width = FR.SCREEN_WIDTH - x * 2;
		int height = FR.SCREEN_HEIGHT - y * 2;
		g.drawImage(im3, x, y, null);
		g.setColor(Color.WHITE);
		int _y;
		int s = 100;
		_y = y + height - s - 5;
		int xs[] = { x, FR.SCREEN_WIDTH - x - s };
		g.setClip(x, y, width, height);
		int _s;
		_s = s * 7;
		g.drawImage(im2,
				xs[Config.isOtogArrowRight() ? 0 : 1] - _s / 2 + s / 2, _y - _s
						/ 2 + s / 2, _s, _s, null);
		g.drawImage(im, xs[Config.isOtogArrowRight() ? 1 : 0] - _s / 2 + s / 2,
				_y - _s / 2 + s / 2, _s, _s, null);
		int diff = 40;
		Image_LargeCharacter.チルノ.draw(g, !Config.isOtogArrowRight(), diff);
		Image_LargeCharacter.永江衣玖.draw(g, Config.isOtogArrowRight(), diff);
		// _s = s * 3 / 2;
		// g.drawImage(Image_Anime.thunder.getImage(4),
		// xs[Config.isOtogArrowRight() ? 0 : 1] - _s / 2 + s / 2, _y - _s
		// * 3 + s, _s, _s * 3, null);
		// g.drawImage(Image_Anime.thunder.getImage(3),
		// xs[Config.isOtogArrowRight() ? 1 : 0] - _s / 2 + s / 2, _y - _s
		// * 3 + s, _s, _s * 3, null);
		// RotateDraw.draw(g, Image_Object.ice_block.getImage(),
		// xs[Config.isOtogArrowRight() ? 1 : 0] - _s / 2 + s / 2, _y - _s
		// / 2 + s / 2, _s, _s, 8);
		g.setColor(new Color(0, 0, 0, 50));
		g.fillRect(x, y, width, height);
		g.drawImage(
				Image_Anime.sp_ice.getImage(MainThread.getFrame() / 8 % 19),
				xs[Config.isOtogArrowRight() ? 1 : 0], _y, s, s, null);
		g.drawImage(Image_Player.rising_hand.IM,
				xs[Config.isOtogArrowRight() ? 1 : 0], _y, s, s, null);
		g.drawImage(
				Image_Anime.oura.getImage(MainThread.getFrame() / 8 % 9 + 3),
				xs[Config.isOtogArrowRight() ? 0 : 1] - s / 2, _y - s / 2,
				s * 2, s * 2, null);
		g.drawImage(CHARA_IMAGE.永江衣玖.getSPImage(0, 2),
				xs[Config.isOtogArrowRight() ? 0 : 1], _y, s, s, null);
		for (int i = 0; i < 4; i++) {
			int _x = xs[Config.isOtogArrowRight() ? 0 : 1]
					+ (xs[Config.isOtogArrowRight() ? 1 : 0] - xs[Config
							.isOtogArrowRight() ? 0 : 1]) * (i + 2) / 7;
			int _i = i == 0 ? 0 : i - 1;
			g.drawImage(Image_Artifact.FOOD.getImage(_i), _x, _y, s, s, null);
		}
		int size = 50;
		g.setFont(NormalFont.NORMALFONT.deriveFont((float) size));
		_y = y + size;
		RotateDraw.draw(g, CHARA_IMAGE.チルノ.getWalkImage(0, DIRECTION.DOWN, 0),
				x, _y - 15, 50, 50, -1.0);
		RotateDraw.draw(g, CHARA_IMAGE.チルノ.getATKImage(0, DIRECTION.LEFT, 2), x
				+ width / 2 + 20, _y - size, 50, 50, 0.3);
		RotateDraw.draw(g, CHARA_IMAGE.チルノ.getATKImage(0, DIRECTION.RIGHT, 2),
				x + width / 2 - 25, _y, 50, 50, 0.1);
		g.drawImage(Image_Player.otiru1.IM, width - 45, _y, null);
		g.setColor(Color.ORANGE);
		StringFilter.drawEdgedString(g, "DANCING ", x + 40, _y);
		g.setColor(Color.BLUE);
		StringFilter.drawEdgedString(g, "        CIRNO", x - 5, _y + size);
		g.setFont(NormalFont.NORMALFONT);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width - 1, height - 1);
	}

}
