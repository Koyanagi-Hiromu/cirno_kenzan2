package dangeon.latest.scene.action.ksg1.main.panel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import dangeon.latest.scene.action.otog.main.panel.Base_Panel;
import dangeon.latest.system.KeyHolder;
import dangeon.util.R;
import dangeon.view.util.StringFilter;
import main.Listener.ACTION;
import main.constant.FR;
import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_Artifact;
import main.res.Image_Player;
import main.res.SE;
import main.util.DIRECTION;

public class Ksg_Panel_Main extends Base_Panel {
	public class Accepter {
		public final int INDEX;
		public final boolean LEFT;
		public boolean pushing;

		public Accepter(boolean b, int i) {
			INDEX = i;
			LEFT = b;
		}

		public void draw(Graphics2D g) {
			if (INDEX == 0) {
				if (flag_missed)
					g.setColor(Color.RED);
				else if (pushing)
					g.setColor(Color.YELLOW);
				else
					g.setColor(Color.WHITE);
			} else if (INDEX == 1) {
//				g.setColor(Color.CYAN);
				g.setColor(Color.BLUE);
			} else if (INDEX == 2) {
				g.setColor(Color.BLUE);
			}
			int s = getSize();
			g.drawRect(getX(LEFT, s), getY(INDEX, s), s, s);
		}

		public void setPushing(boolean b) {
			pushing = b;
		}

	}

	public class Target {
		public int INDEX;
		public final boolean LEFT, FOOD;
		public boolean ice;
		public final Image_Artifact IM;
		public int dy;
		private int dx;

		public Target(boolean b, int i, int seed) {
			INDEX = i;
			LEFT = b;
			IM = getImage(seed);
			ice = seed % 4 == 0;
			FOOD = seed < 60;
			decIndex();
			INDEX++;
		}

		public void ascX() {
			dx += 10 * ((LEFT) ? -1 : 1);
		}

		public void changeIce() {
			ice = !ice;
		}

		public void decIndex() {
			INDEX--;
			dy -= getSize() * 2;
		}

		public void draw(Graphics2D g) {
			int s = getSize();
			g.drawImage(IM.getImage(ice ? 1 : 0), getX(LEFT, s) + dx,
					getY(INDEX, s) + dy, null);
		}

		private Image_Artifact getImage(int seed) {
			if (seed < 40) {
				return Image_Artifact.FOOD;
			} else if (seed < 50) {
				return Image_Artifact.GOLDEN_FOOD;
			} else if (seed < 60) {
				return Image_Artifact.GLASS;
			} else if (seed < 65) {
				return Image_Artifact.BIN;
			} else if (seed < 70) {
				return Image_Artifact.BOOK;
			} else if (seed < 75) {
				return Image_Artifact.CARD;
			} else if (seed < 80) {
				return Image_Artifact.DISC;
			} else if (seed < 85) {
				return Image_Artifact.RIBBON;
			} else if (seed < 88) {
				return Image_Artifact.HAKKE;
			} else if (seed < 91) {
				return Image_Artifact.KNIFE;
			} else if (seed < 94) {
				return Image_Artifact.P_KNIFE;
			} else {
				return Image_Artifact.STAFF;
			}
		}

		public void upDate() {
			dy /= 2;
		}
	}

	public final int NORMAL = 5, LEFT = 4, RIGHT = 6, UP_DOWN = 8, DEATH = -1;

	public int cirno_state = NORMAL;

	public final ArrayList<Accepter> list_a = new ArrayList<Accepter>();

	public final ArrayList<Target> list_hit = new ArrayList<Target>();

	public final Target[] targets_left = new Target[3],
			targets_right = new Target[3];

	public KeyHolder KH;
	public int frame, limit;
	public final int LIMIT = 100;
	private boolean flag_missed;

	public long count;

	public Ksg_Panel_Main(int x, int w, int margine, KeyHolder kh) {
		super(x, margine, w, FR.SCREEN_HEIGHT - margine * 2, null);
		for (int i = 0; i < 3; i++) {
			list_a.add(new Accepter(true, i));
			list_a.add(new Accepter(false, i));
		}
		init();
		KH = kh;
	}

	public boolean action(ACTION a) {
		frame = 5;
		boolean success = false;
		boolean b1 = targets_left[0].FOOD && !targets_left[0].ice;
		boolean b2 = targets_right[0].FOOD && !targets_right[0].ice;
		cirno_state = UP_DOWN;
		success = !b1 && !b2;
		if (success) {
			success(SE.SYSTEM_ARRANGEMENT);
			return true;
		} else {
			death();
			return false;
		}
	}

	public synchronized boolean arrow(DIRECTION d) {
		frame = 5;
		boolean success = false;
		SE se = SE.YOUMU_SP2;
		boolean b1 = targets_left[0].FOOD && !targets_left[0].ice;
		boolean b2 = targets_right[0].FOOD && !targets_right[0].ice;
		switch (d) {
		case LEFT:
			cirno_state = LEFT;
			success = b1;
			list_hit.add(targets_left[0]);
			if (b2)
				list_hit.add(targets_right[0]);
			break;
		case RIGHT:
			cirno_state = RIGHT;
			success = b2;
			list_hit.add(targets_right[0]);
			if (b1)
				list_hit.add(targets_left[0]);
			break;
		case UP:
		case DOWN:
			cirno_state = UP_DOWN;
			success = !b1 && !b2;
			se = SE.SYSTEM_ARRANGEMENT;
			break;
		}
		if (success) {
			success(se);
			return true;
		} else {
			death();
			return false;
		}
	}

	public void death() {
		SE.SYSTEM_CURSE.play();
		flag_missed = true;
		BGM.stop();

	}

	private int decLimit() {
		if (count > 1040)
			return 10;
		else if (count > 840)
			return 9;
		else if (count > 660)
			return 8;
		else if (count > 500)
			return 7;
		else if (count > 360)
			return 6;
		else if (count > 240)
			return 5;
		else if (count > 140)
			return 4;
		else if (count > 60)
			return 3;
		else if (count > 20)
			return 2;
		else if (count > 0)
			return 1;
		else
			return 0;
	}

	@Override
	public synchronized void drawME(Graphics2D g) {
		Image im = null;
		CHARA_IMAGE c = CHARA_IMAGE.チルノ;
		switch (cirno_state) {
		case NORMAL:
			im = c.getWalkImage(0, DIRECTION.DOWN, 0);
			break;
		case LEFT:
			im = c.getATKImage(0, DIRECTION.LEFT, 2);
			break;
		case RIGHT:
			im = c.getATKImage(0, DIRECTION.RIGHT, 2);
			break;
		case UP_DOWN:
			im = Image_Player.disc3.IM;
			break;
		}
		g.drawImage(im, W / 2 - 25, H - 75, null);
		int s = getSize();
		int x = W / 2 - s / 2;
		int y = H - s;
		int angle = (int) (360.0 * limit / LIMIT);
		StringBuilder sb = new StringBuilder();
		int dec = decLimit();
		if (dec > 0)
		{
			sb.append("x");
			if (dec == 10) {
				sb.append("9.9");
			} else {
				sb.append(decLimit());
				sb.append(".0");
			}
		}
		for (int i = 0; i < 2; i++) {
			y -= s * 2;
			getLimitColor(g);
			g.fillArc(x, y, s, s, 90, angle);
			g.setColor(Color.BLUE);
			g.drawOval(x, y, s, s);
			g.setColor(Color.LIGHT_GRAY);
			StringFilter.drawString(g, sb.toString(), x + s / 2 - 10, y + s / 2
					+ 4);
		}
		for (Accepter a : list_a)
			a.draw(g);
		for (Target t : targets_left)
			t.draw(g);
		for (Target t : targets_right)
			t.draw(g);
		for (Target t : list_hit)
			t.draw(g);
	}

	private void getLimitColor(Graphics2D g) {
		if (limit * 4 < LIMIT)
			g.setColor(Color.RED);
		else if (limit * 2 < LIMIT)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.GREEN);
	}

	private int getSize() {
		return 50;
	}

	public int getX(boolean left, int s) {
		int x = 20;
		if (!left) {
			x = W - x - s;
		}
		return x;
	}

	private int getY(int INDEX, int s) {
		return H - s * 2 - s * INDEX * 2;
	}

	public void init() {
		int r = R.ran(20);
		if (r == 0) {
			BGM.PetalChaser.play();
		} else if (r == 1) {
			BGM.RampageGhost.play();
		} else if (r == 2) {
			BGM.Rock_on_dream.play();
		} else if (r == 3) {
			BGM.Jiang_Shi_Dance.play();
		} else {
			BGM.BladeRunner.play();
		}

		for (int i = 0; i < 3; i++) {
			targets_left[i] = new Target(true, i, R.ran(100));
			targets_right[i] = new Target(false, i, R.ran(100));
		}
		list_hit.clear();
		flag_missed = false;
		limit = LIMIT;
		count = 0;
	}

	private void success(SE se) {
		int speed = decLimit();
		count++;
		if (speed != decLimit()) {
			SE.STATUS_SPEEDY.play();
		}
		limit = LIMIT;
		if (se != null)
			se.play();
		int max = targets_left.length - 1;
		for (int i = 0; i < max; i++) {
			targets_left[i] = targets_left[i + 1];
			targets_right[i] = targets_right[i + 1];
			targets_left[i].decIndex();
			targets_right[i].decIndex();
		}
		targets_left[max] = new Target(true, max, R.ran(100));
		targets_right[max] = new Target(false, max, R.ran(100));
	}

	@Override
	public void upDate() {
		if (frame > 0) {
			if (--frame == 0) {
				cirno_state = NORMAL;
			}
		}
		for (Target t : targets_left)
			t.upDate();
		for (Target t : targets_right)
			t.upDate();
		for (Target t : list_hit)
			t.ascX();
		list_a.get(0).setPushing(false);
		list_a.get(1).setPushing(false);
		for (DIRECTION d : KH.getDirections()) {
			if (d == DIRECTION.LEFT) {
				list_a.get(1).setPushing(true);
			} else if (d == DIRECTION.RIGHT) {
				list_a.get(0).setPushing(true);
			}
		}
	}

	public boolean upDate_limit() {
		if (limit <= 0) {
			death();
			return false;
		}
		limit -= decLimit();
		if (limit <= 0) {
			limit = 0;
		}
		return true;
	}
}
