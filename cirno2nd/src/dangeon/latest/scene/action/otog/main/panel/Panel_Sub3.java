package dangeon.latest.scene.action.otog.main.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;

import main.constant.FR;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Panel_Sub3 extends Base_Panel {

	public Panel_Sub2 sub2;

	public int end_anime_frame = -1;

	public final int FRAME = 10;

	public Panel_Sub3(Panel_Main p, Panel_Sub2 s, int margine) {
		super(s.X, s.Y + s.H + margine, s.W, FR.SCREEN_HEIGHT
				- (s.Y + s.H + margine * 2), p);
		sub2 = s;
		p.VH.sub3 = this;
	}

	private void drawBack(Graphics2D g) {
		if (end_anime_frame > 0) {
			Color c = sub2.CHARA.isAnimating() ? Color.RED : Color.GREEN;
			int spl = 1;
			int y = (int) (H * (spl - Math.sin(Math.PI * end_anime_frame
					/ FRAME)))
					/ spl;
			g.setPaint(new GradientPaint(0, y, new Color(c.getRed(), c
					.getGreen(), c.getBlue(), 0), 0, H, new Color(c.getRed(), c
					.getGreen(), c.getBlue(), 100)));
			g.fillRect(0, 0, W, H);
		}
	}

	private void drawImage(Graphics2D g) {
		if (sub2.CHARA == null)
			return;
		Image im = sub2.CHARA.getImage();
		g.drawImage(im, 22 - im.getWidth(null) / 2,
				21 - im.getHeight(null) / 2, null);
	}

	@Override
	public void drawME(Graphics2D g) {
		g.setClip(0, 1, W, H - 1);
		drawImage(g);
		drawBack(g);
		drawStrings(g);
		g.setClip(null);
	}

	private void drawStrings(Graphics2D g) {
		Font font = g.getFont();
		g.setFont(font.deriveFont(NormalFont.SMALL_SIZE));
		String str;
		g.setColor(Color.WHITE);
		str = P.VH.CONTENT.AUTHOR;
		StringFilter.drawString(g, str,
				W - 1 - g.getFontMetrics().stringWidth(str), H - 3);
		str = P.VH.CONTENT.VERSION;
		StringFilter.drawString(
				g,
				str,
				W
						- 1
						- g.getFontMetrics().stringWidth(
								StringFilter.getPlainString(str)), H - 18);
		g.setFont(font);
	}

	@Override
	protected int getBGColorB() {
		return 20;
	}

	@Override
	protected int getBGColorG() {
		return 10;
	}

	@Override
	protected int getBGColorR() {
		return 10;
	}

	public void set(TEXT tex) {
		if (sub2.CHARA == null)
			return;
		else if (tex == null || sub2.CHARA.isAnimating() || end_anime_frame > 0)
			return;
		else if (tex.isCombo()) {
			sub2.CHARA.setDamaging(true);
		} else if (tex.isNoGood()) {
			sub2.CHARA.startAttack(null);
		}
		end_anime_frame = FRAME;
	}

	@Override
	public void upDate() {
		if (sub2.CHARA == null)
			return;
		if (sub2.CHARA.isAnimating() || end_anime_frame >= 0) {
			end_anime_frame--;
		} else {
			sub2.CHARA.setDamaging(false);
		}
		sub2.CHARA.upDate();
	}
}
