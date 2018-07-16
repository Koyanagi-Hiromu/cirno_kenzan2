package dangeon.latest.scene.action.otog.main.panel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.lang.reflect.Constructor;

import main.constant.FR;
import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.util.DIRECTION;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.enemy.永江衣玖;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.RotateDraw;
import dangeon.view.util.StringFilter;

public class Panel_Sub2 extends Base_Panel {

	public Image_LargeCharacter IMLC;

	private int frame = 0;

	private BGM b;
	public Base_Enemy CHARA;

	public Panel_Sub2(Panel_Main p, Panel_Sub s, int margine) {
		super(s.X + s.W + margine, s.Y, FR.SCREEN_WIDTH
				- (s.X + s.W + margine * 2), s.H - 40 - margine, p);
	}

	@Override
	public void drawME(Graphics2D g) {
		BGM bgm = P.VH.bgm;
		if (bgm == null)
			return;
		if (bgm.CLASS == null)
			return;
		if (bgm != b)
			frame = 0;
		b = bgm;
		g.setColor(Color.WHITE);
		g.setClip(0, 1, W, H - 1);

		if (IMLC == null) {
			try {
				Point p = Player.me.getMassPoint().getLocation();
				p.translate(1000, -1000);
				Constructor<?> con = bgm.CLASS.getConstructor(Point.class);
				SpellCard sc = (SpellCard) con.newInstance(new Point(0, 0));
				IMLC = Image_LargeCharacter.get(sc);
				Constructor<? extends Base_Enemy> con2 = sc.getStand()
						.getConstructor(Point.class, int.class);
				CHARA = con2.newInstance(p, 0);
				if (CHARA.getCharacterImage().WALK == null
						|| CHARA instanceof CardAttackEffect) {
					CHARA = new 永江衣玖(p, 0);
				}
				if (CHARA != null) {
					CHARA.setMute(true);
					CHARA.setDirection(DIRECTION.DOWN_LEFT);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// String card;
			// card = bgm.CLASS.getSimpleName();
			// card = card.substring(0, card.lastIndexOf("のカード"));
			// IMLC = Image_LargeCharacter.get(card);
			// IMLC.CHARA = EnemyTable.returnBaseEnemySetPoint(card, 0, p);
		}
		IMLC.draw(g, W * 9 / 10, H * 2 / 5);
		int w = g.getFontMetrics().stringWidth(bgm.TITLE) + 20;
		frame++;
		RotateDraw.draw(g, bgm.TITLE, -2, 0 - frame, 90);
		RotateDraw.draw(g, bgm.TITLE, -2, 0 - frame + w, 90);
		RotateDraw.draw(g, bgm.TITLE, -2, 0 - frame + w * 2, 90);
		if (frame >= w)
			frame -= w;
		String str;
		str = P.VH.getDifficulty().trim();
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		StringFilter.drawString(
				g,
				str,
				W
						- 1
						- g.getFontMetrics().stringWidth(
								StringFilter.getPlainString(str)), H - 3);
		g.setClip(null);
	}
}
