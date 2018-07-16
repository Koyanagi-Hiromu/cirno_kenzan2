package dangeon.latest.scene.action.ksg1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.res.CHARA_IMAGE;
import main.res.Image_Artifact;
import main.res.Image_LargeCharacter;
import main.res.Image_Player;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import dangeon.latest.scene.Base_View;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Ksg_First_View extends Base_View {

	private BufferedImage IM;

	public Ksg_First_View(Base_View bv) {
		super(bv);
		IM = FileReadSupporter.readPNGImage("otog/image/music_bg01.png");
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		g.drawImage(IM, 0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT, null);
		drawBlack(g);
		drawBG(g);
		Ksg_First s = (Ksg_First) PARENT_SCENE;
		if (current) {
			g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
			for (int i = 0; i < s.content.size(); i++) {
				s.content.get(i).drawMe(g, FR.SCREEN_WIDTH / 4, i + 2);
			}
		}
	}

	private void drawBG(Graphics2D g) {
		int margine = 0;
		int x = margine, y = margine;
		int width = FR.SCREEN_WIDTH - x * 2;
		int height = FR.SCREEN_HEIGHT - y * 2;
		g.setColor(Color.WHITE);
		int _y;
		int s = 100;
		_y = y + height - s - 5;
		int xs[] = { x, FR.SCREEN_WIDTH - x - s };
		g.setClip(x, y, width, height);
		int _s;
		_s = s * 7;
		int diff = 40;
		Image_LargeCharacter.チルノ.draw(g, false, diff);
		Image_LargeCharacter.魂魄妖夢.draw(g, true, diff);
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
		g.drawImage(Image_Player.disc3.IM, xs[1], _y, s, s, null);
		g.drawImage(CHARA_IMAGE.魂魄妖夢.getSPImage(0, DIRECTION.DOWN, 3), xs[0]
				- s * 4 / 5, _y - s, s * 5 / 2, s * 5 / 2, null);
		g.drawImage(CHARA_IMAGE.魂魄妖夢.getSPImage(0, DIRECTION.DOWN, 4), xs[0],
				_y - s, s * 5 / 2, s * 5 / 2, null);
		// BeautifulView.setAlphaOnImg(g, 0.3f);
		// g.drawImage(Image_Artifact.FOOD.getImage(0), FR.SCREEN_WIDTH / 3, _y,
		// s, s, null);
		// BeautifulView.setAlphaOnImg(g, 0.6f);
		// g.drawImage(Image_Artifact.FOOD.getImage(0), FR.SCREEN_WIDTH / 3 +
		// 38,
		// _y, s, s, null);
		// BeautifulView.setAlphaOnImg(g, 1f);
		// g.drawImage(Image_Artifact.FOOD.getImage(0), FR.SCREEN_WIDTH / 2, _y,
		// s, s, null);
		// for (int i = 0; i < 4; i++) {
		// int _x = xs[1] + (xs[0] - xs[1]) * (i + 2) / 7;
		// int _i = i == 0 ? 0 : i - 1;
		// }
		int size = 50;
		g.setFont(NormalFont.NORMALFONT.deriveFont((float) size));
		_y = y + size + size / 2;
		int _x = x + 50;
		g.drawImage(Image_Artifact.FOOD.getImage(0), _x - s / 2, _y - size - 3,
				s, s, null);
		g.drawImage(Image_Artifact.GLASS.getImage(0), FR.SCREEN_WIDTH - (_x)
				- s / 2 + 10, _y - size - 3, s, s, null);
		g.setColor(Color.RED);
		StringFilter.drawEdgedString(g, "刹那 ", _x, _y);
		g.setColor(Color.YELLOW);
		StringFilter.drawEdgedString(g, "　　の ", _x, _y);
		g.setColor(Color.LIGHT_GRAY);
		StringFilter.drawEdgedString(g, "　　　仕分け", _x, _y);
		g.setFont(NormalFont.NORMALFONT);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width - 1, height - 1);

	}
}
