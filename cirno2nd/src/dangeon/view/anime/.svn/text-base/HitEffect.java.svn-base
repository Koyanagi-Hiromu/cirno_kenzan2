package dangeon.view.anime;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Second_Firster;
import main.constant.FR;
import main.res.Image_Effect;
import main.res.SE;
import main.util.BeautifulView;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ObjectPoint;
import dangeon.util.R;
import dangeon.view.constant.MAP;
import dangeon.view.constant.NormalFont;

public class HitEffect extends Base_Effect {

	protected static final int first_speed = 500, ACCERARETION = 400,
			TIME_MAX = 1000;
	protected final Point FIRST_VELOCITY;
	protected final Point DELT;
	protected int time;

	protected final int CENTERING, MIDDLING;

	protected int frame = 5;
	protected final Point SCREEN_POINT;
	protected final Image DAMAGE;
	protected final int ANGLE, MAX = 45;
	protected static int pre_angle = 90;
	protected final Base_Creature C;

	private final Color COLOR_P1 = new Color(255, 100, 0),
			COLOR_P2 = new Color(255, 200, 255);

	protected final int WIDTH, HEIGHT;
	private static boolean flag_largement;

	public static void setLargeDamaged(boolean b) {
		flag_largement = b;
	}

	public HitEffect(Base_Creature c, int damage) {
		this(c, damage, 半角全角コンバーター.半角To全角数字(damage));
	}

	public HitEffect(Base_Creature c, int damage, String str) {
		C = c;
		SE(damage);
		Font font = NormalFont.NORMALFONT.deriveFont(25f);
		FontMetrics fo = Second_Firster.ME.getFontMetrics(font);
		WIDTH = fo.stringWidth(str);
		HEIGHT = fo.getHeight();
		BufferedImage im = new BufferedImage(WIDTH * 2 + 3, HEIGHT * 2 + 5,
				BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D) im.getGraphics();
		g.scale(2, 2);
		g.setFont(font);
		// g.setColor(Color.BLACK);
		// for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
		// g.drawString(str, d.X * 3, d.Y * 3 + HEIGHT);
		// }
		g.setColor(Color.BLACK);
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			g.drawString(str, d.X * 2, d.Y * 2 + HEIGHT);
		}
		// g.setColor(Color.BLACK);
		// g.drawString(str, 0, HEIGHT);
		g.setColor(Color.WHITE);
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			g.drawString(str, d.X, d.Y + HEIGHT);
		}
		setColor(g, c, HEIGHT);
		g.drawString(str, 0, HEIGHT);
		// StringFilter.drawEdgedString_plain(g, str, 0, HEIGHT);
		g.dispose();
		DAMAGE = im;
		int angle;
		do {
			angle = new R().nextInt(MAX) - MAX / 2 + 90;
		} while (Math.abs(angle - pre_angle) < MAX / 5);
		pre_angle = ANGLE = angle;
		FIRST_VELOCITY = new Point((int) (first_speed * Math.cos(2 * Math.PI
				* ANGLE / 360.0)), (int) (-first_speed * Math.sin(2 * Math.PI
				* ANGLE / 360.0)));
		CENTERING = (MAP.TILE_SIZE - WIDTH) / 2;
		MIDDLING = -HEIGHT / 2;
		addFrameImage();
		SCREEN_POINT = c.getScreenPoint().getLocation();
		DELT = new Point(CENTERING, MIDDLING);
	}

	public HitEffect(Base_Creature c, String str) {
		this(c, 0, str);
	}

	protected void addFrameImage() {
		frame_image = new ArrayList<Image>(frame);
		Image_Effect IE;
		switch ((int) Math.floor(new R().nextDouble() * 3)) {
		case 0:
			IE = Image_Effect.hit_00;
			break;
		default:
			IE = Image_Effect.HitA_;
			break;
		}
		for (int i = IE.BI.length - 1; i >= 0; i--) {
			frame_image.add(IE.BI[i]);
			frame_image.add(frame_image.get(frame_image.size() - 1));
		}
	}

	@Override
	public boolean draw(Graphics2D g) {
		Point p = ObjectPoint.getScreenPointRelPlayer(SCREEN_POINT);
		p.translate(0, -MAP.TILE_SIZE + MAP.Y_OFFSET);
		if (frame > 0) {
			if (frame == 5) {
				C.startDamaging();
			}
			frame--;
			System.out.println(0.8f + frame * 0.05f);
			BeautifulView.setAlphaOnImg(g, 0.8f + frame * 0.05f);
			g.drawImage(frame_image.get(frame), p.x, p.y, null);
			BeautifulView.setAlphaOnImg(g, 1f);
		}
		float rate = new Float(Math.cos((double) time / TIME_MAX));
		BeautifulView.setAlphaOnImg(g, rate);
		g.drawImage(DAMAGE, p.x + DELT.x, p.y + DELT.y, WIDTH, HEIGHT, null);
		BeautifulView.setAlphaOnImg(g, 1F);
		return upDatePopNumber();
	}

	protected void SE(int damage) {
		SE.SYSTEM_DAMEGED_ANIME.play();
	}

	protected void setColor(Graphics2D g, Base_Creature c, int height) {
		Color COLOR1, COLOR2;
		if (c instanceof Player) {
			COLOR1 = Color.RED;
			COLOR2 = Color.MAGENTA;
		} else {
			if (flag_largement) {
				flag_largement = false;
				COLOR1 = Color.BLACK;
				COLOR2 = Color.RED;
			} else {
				COLOR1 = Color.ORANGE;
				COLOR2 = Color.YELLOW;
			}
		}
		g.setPaint(new GradientPaint(0, 0, COLOR1, 0, height, COLOR2));
	}

	protected boolean upDatePopNumber() {
		time += FR.THREAD_SLEEP;
		if (time > TIME_MAX) {
			return false;
		}
		// 速度の成分が最終的に2/3になる
		double converter = (3.0 * TIME_MAX - time) / (3.0 * TIME_MAX);
		double v_x = FIRST_VELOCITY.x * converter;
		double v_y = FIRST_VELOCITY.y * converter;
		Point velocity = new Point((int) v_x, (int) v_y);
		int a = (int) (ACCERARETION * converter);
		double t = time / 1000.0;
		double delt_x = velocity.x * t;
		double delt_y = velocity.y * t + a * t * t;
		DELT.move((int) delt_x + CENTERING, (int) delt_y + MIDDLING);
		return true;
	}
}
