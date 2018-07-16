package dangeon.view.anime;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.Second_Firster;
import main.constant.FR;
import main.res.Image_LargeCharacter;
import main.util.BeautifulView;
import dangeon.controller.task.Task;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class CardAnime extends Base_Anime {
	private final Font FONT = NormalFont.NORMALFONT.deriveFont(20f);
	private final int FRAME = 40;

	private final SpellCard SC;

	private final String NAME;
	private final int FINAL_X;
	private final int SPEED = 50;
	private final Image_LargeCharacter IM[] = new Image_LargeCharacter[1];
	private final int he = 150;
	private final int alpha = 200;
	private final Color C = new Color(0, 0, 0, alpha);
	private final int STRING_X;
	private boolean use_item = false;

	public CardAnime(SpellCard sc) {
		super(Player.me);
		IM[0] = sc.IMLC.init();
		for (int i = 0; i < IM.length; i++) {
			IM[i] = IM[0];
		}
		FINAL_X = FR.SCREEN_WIDTH - IM[0].W;
		NAME = sc.getFuName();
		STRING_X = -10 + FR.SCREEN_WIDTH
				- Second_Firster.ME.getFontMetrics(FONT).stringWidth(NAME);
		SC = sc;
	}

	@Override
	protected boolean draw(Graphics2D g) {
		if (getFrame() == FRAME * 4 / 5) {
			new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					SC.itemUse();
				}
			}.work_appointment();
		}
		if (getFrame() > FRAME) {
			return false;
		}
		drawBlack(g);
		coma = getFrame() / 3 % 2;
		for (int i = 0; i < IM.length; i++) {
			BeautifulView.setAlphaOnImg(g, 1F / (i + 1));
			int x = FR.SCREEN_WIDTH - getFrame() * SPEED + i * 15;
			if (x > FINAL_X) {
				IM[i].draw(g, x);
			} else {
				if (i == 0) {
					IM[i].draw(g, FINAL_X + (x - FINAL_X) / 200);
				}
			}

		}
		BeautifulView.setAlphaOnImg(g, 1F);
		int x = getFrame() * 30;
		if (x > STRING_X) {

			x = STRING_X + (x - STRING_X) / 200;
		}
		g.setColor(Color.WHITE);
		g.setFont(FONT);
		BeautifulView.setAntiAliasing(g, true);
		StringFilter.drawEdgedString_plain(g, NAME, x,
				(FR.SCREEN_HEIGHT + he) / 2 - 5);
		g.setFont(NormalFont.NORMALFONT);
		return true;
	}

	private void drawBlack(Graphics2D g) {
		int a = (int) (Math.cos(Math.PI / 2 * getFrame() / FRAME) * alpha);
		System.out.println(a);
		g.setColor(new Color(0, 0, 0, a));
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
		g.setColor(C);
		g.fillRect(0, (FR.SCREEN_HEIGHT - he) / 2, FR.SCREEN_WIDTH, he);
	}
}
