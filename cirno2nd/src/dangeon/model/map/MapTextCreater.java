package dangeon.model.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.util.Show;
import dangeon.util.R;

class MapTextCreater implements MouseListener, KeyListener {
	class Division {
		int devided;
		boolean enter = false, exit = false;
		List<Division> neibor = new ArrayList<Division>();
		boolean room = false;
		boolean to_top = false, to_bottom = false, to_left = false,
				to_right = false;
		int wanna_cut = 0;
		int WIDTH, HEIGHT, X, Y;
		/**
		 * for room
		 */
		int x, y, w, h;

		Division(int width, int height, int x, int y, int cutted) {
			this.WIDTH = width;
			this.HEIGHT = height;
			this.X = x;
			this.Y = y;
			this.devided = cutted;
		}

		void createRoom() {
			int try_count = 0;
			while (try_count < 30) {
				room = true;
				int max_w = WIDTH;
				int max_h = HEIGHT;
				if (!to_top)
					max_h -= min_MARGIN;
				if (!to_bottom)
					max_h -= min_MARGIN;
				if (!to_right)
					max_w -= min_MARGIN;
				if (!to_left)
					max_w -= min_MARGIN;
				int min_w = min_W;
				int min_h = min_H;
				int hosei = 6; // 何マスごとに最小値を大きくするか
				int add_min;
				if (max_w > max_h) {
					add_min = (max_h - min_H) / hosei;
				} else {
					add_min = (max_w - min_W) / hosei;
				}
				min_w += add_min;
				min_h += add_min;
				w = getRandom(max_w, min_w);
				h = getRandom(max_h, min_h);
				x = new R().nextInt(max_w - w + 1);
				if (!to_left)
					x += min_MARGIN;
				y = new R().nextInt(max_h - h + 1);
				if (!to_top)
					y += min_MARGIN;
				double ratio = (double) h / w;
				double mini_ratio = (double) mass[0].length / (mass.length * 2);
				double max_ratio = mass.length * 4.5 / mass[0].length;
				if (mini_ratio <= ratio && ratio <= max_ratio) {
					return;
				}
				try_count++;
				if (try_count == 30) {
					Show.showCriticalErrorMessageDialog("Error@MapCreater.createRoom");
				}
			}
		}

		public Division devide() {
			int weight = 3;
			if (cutted_vertivaly) {
				return (new R().nextInt(weight) == 0) ? devide_width()
						: devide_height();
			}
			return (new R().nextInt(weight) == 0) ? devide_height()
					: devide_width();
		}

		private Division devide_height() {
			cutted_vertivaly = false;
			boolean old_to_bottom = new R().nextBoolean();
			boolean new_to_top = !old_to_bottom;
			int min = min_H;
			{
				if (!old_to_bottom)
					min += min_MARGIN;
				if (!to_top)
					min += min_MARGIN;
			}
			int new_min = min_H;
			{
				if (!false)
					new_min += min_MARGIN;
				if (!new_to_top)
					new_min += min_MARGIN;
			}
			int h = getRandom(HEIGHT - new_min, min);
			devided++;
			Division new_d = new Division(WIDTH, HEIGHT - h, X, Y + h, devided);
			HEIGHT = h;
			{
				new_d.to_top = new_to_top;
				new_d.to_left = to_left;
				new_d.to_bottom = to_bottom;
				new_d.to_right = to_right;
			}
			to_bottom = old_to_bottom;
			return new_d;
		}

		private Division devide_width() {
			cutted_vertivaly = true;
			boolean old_to_right = new R().nextBoolean();
			boolean new_to_left = !old_to_right;
			int min = min_W;
			{
				if (!old_to_right)
					min += min_MARGIN;
				if (!to_left)
					min += min_MARGIN;
			}
			int new_min = min_W;
			{
				if (!false)
					new_min += min_MARGIN;
				if (!new_to_left)
					new_min += min_MARGIN;
			}
			int w = getRandom(WIDTH - new_min, min);
			devided++;
			Division new_d = new Division(WIDTH - w, HEIGHT, X + w, Y, devided);
			WIDTH = w;
			{
				new_d.to_left = new_to_left;
				new_d.to_bottom = to_bottom;
				new_d.to_right = to_right;
				new_d.to_top = to_top;
			}
			to_right = old_to_right;
			return new_d;
		}

		private char[][] getSymbol() {
			char[][] mass = new char[WIDTH][HEIGHT];
			for (int i = 0; i < mass.length; i++)
				for (int j = 0; j < mass[i].length; j++) {
					mass[i][j] = '壁';
					if (i == 0 && !to_left) {
						mass[i][j] = '左';
					}
					if (i == WIDTH - 1 && !to_right) {
						mass[i][j] = '右';
					}
					if (j == 0 && !to_top) {
						mass[i][j] = '上';
					}
					if (j == HEIGHT - 1 && !to_bottom) {
						mass[i][j] = '下';
					}
				}
			if (room) {
				for (int i = 0; i < w; i++)
					for (int j = 0; j < h; j++)
						mass[x + i][y + j] = ROOM;
			}
			return mass;
		}

		void setMass() {
			if (!room)
				Show.showCriticalErrorMessageDialog("部屋か通路を生成してください");
			char[][] symbol = getSymbol();
			for (int i = 0; i < symbol.length; i++) {
				for (int j = 0; j < symbol[i].length; j++) {
					if (mass[i + X][j + Y] != '＃')
						Show.showCriticalErrorMessageDialog("Error@MapCreater.setMass");
					mass[i + X][j + Y] = symbol[i][j];
				}
			}
		}
	}

	public static char[][] test;
	static JFrame jf = new JFrame();
	static final char PATH = '★', ROOM = '．';

	public static void main(String[] arg) {
		MapTextCreater m = new MapTextCreater();
		test = m.mass;
		// jf.setLayout(new BorderLayout());
		final int w = m.WIDTH * 14, h = m.HEIGHT * 14;
		jf.setSize(w, h);
		jf.add(new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.fillRect(0, 0, w, h);
				g.setColor(Color.CYAN);
				g.setFont(getFont().deriveFont(14F));
				for (int i = 0; i < test.length; i++)
					for (int j = 0; j < test[i].length; j++) {
						g.drawString(String.valueOf(test[i][j]), i * 12 + 5,
								(j + 1) * 12);
					}
			}
		}, BorderLayout.CENTER);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.repaint();
		jf.addMouseListener(m);
		jf.addKeyListener(m);
	}

	private List<Division> list = new ArrayList<Division>();
	// private final int WIDTH=80,HEIGHT=60;
	private final int WIDTH = 40, HEIGHT = 30;
	boolean cutted_vertivaly;
	char[][] mass = new char[WIDTH][HEIGHT];

	final int min_MARGIN = 3;
	final int min_W = 4, min_H = 3;

	MapTextCreater() {
		init();
	}

	private boolean concatPath() {
		Division d = list.get(new R().nextInt(list.size()));
		Division _d = getUnCheckedNeiborExitRoom(d);
		while (_d != null) {
			if (!concatPath_Neibor(d, _d))
				break;
			d = _d;
			_d = getUnCheckedNeiborExitRoom(d);
		}
		List<Division[]> series = new ArrayList<Division[]>();
		List<Division> company = new ArrayList<Division>();
		int concat_count = 0;
		int count = 0;
		while (true) {
			while (true) {
				if (list.isEmpty())
					break;
				int i = 0;
				company.add(list.get(new R().nextInt(list.size())));
				while (i < company.size()) {
					for (Division div : company.get(i).neibor) {
						if (company.contains(div))
							continue;
						company.add(div);
					}
					i++;
				}
				// System.out.println("company :"+company.size());
				series.add(company.toArray(new Division[0]));
				for (Division div : series.get(series.size() - 1))
					list.remove(div);
				company.clear();
			}
			for (Division[] d2 : series)
				for (Division d1 : d2)
					list.add(d1);
			if (series.size() == 1)
				break;
			Division[] divs = series.get(new R().nextInt(series.size()));
			d = divs[new R().nextInt(divs.length)];
			if (concatPath_Neibor(d, getJustNeiborExitRoom(d))) {
				count = 0;
				concat_count += 10;
			} else
				count++;
			if (count > 10) {
				System.out.println("マップ生成失敗。 => リテイク");
				return false;
			}
			series.clear();
		}
		while (concat_count < 15) {
			if (concatPath_Random())
				concat_count += 10;
			else
				concat_count++;
		}
		return true;
	}

	private boolean concatPath(int start_x, int start_y, int end_x, int end_y,
			boolean[][] flag) {
		MapTextCreater_Path.mass = mass;
		MapTextCreater_Path.W_MAX = WIDTH;
		MapTextCreater_Path.H_MAX = HEIGHT;
		MapTextCreater_Path.end_x = end_x;
		MapTextCreater_Path.end_y = end_y;
		MapTextCreater_Path.checked_path = flag;
		return MapTextCreater_Path.triggerCheck(start_x, start_y);
	}

	private boolean concatPath_Neibor(Division start, Division end) {
		if (start == null || end == null)
			return false;
		final int start_x = start.x + start.X + start.w / 2;
		final int start_y = start.y + start.Y + start.h / 2;
		final int end_x = end.x + end.X + end.w / 2;
		final int end_y = end.y + end.Y + end.h / 2;
		final int delt_x = end_x - start_x, delt_y = end_y - start_y;
		int direction = -1, second_direction = -1;
		final int BOTTOM_TOP = 0, RIGHT_LEFT = 1, TOP_BOTTOM = 2, LEFT_RIGHT = 3;
		int min = 3; // 道の始め分
		int delt_l_r = (start_x - start.w / 2) - (end_x + end.w / 2) - min;
		int delt_r_l = (end_x - end.w / 2) - (start_x + start.w / 2) - min;
		int delt_t_b = (start_y - start.h / 2) - (end_y + end.h / 2) - min;
		int delt_b_t = (end_y - end.h / 2) - (start_y + start.h / 2) - min;
		final int hosei = min * 4; // 折り返し分とか
		if (delt_b_t < 0)
			delt_b_t = -delt_b_t + hosei;
		if (delt_r_l < 0)
			delt_r_l = -delt_r_l + hosei;
		if (delt_t_b < 0)
			delt_t_b = -delt_t_b + hosei;
		if (delt_l_r < 0)
			delt_l_r = -delt_l_r + hosei;
		if (delt_y > 0 && delt_x > 0) {
			if (delt_b_t < delt_r_l) {
				direction = BOTTOM_TOP;
				second_direction = RIGHT_LEFT;
			} else {
				direction = RIGHT_LEFT;
				second_direction = BOTTOM_TOP;
			}
		} else if (delt_y > 0 && delt_x <= 0) {
			if (delt_b_t < delt_l_r) {
				direction = BOTTOM_TOP;
				second_direction = LEFT_RIGHT;
			} else {
				direction = LEFT_RIGHT;
				second_direction = BOTTOM_TOP;
			}
		} else if (delt_y <= 0 && delt_x > 0) {
			if (delt_t_b < delt_r_l) {
				direction = TOP_BOTTOM;
				second_direction = RIGHT_LEFT;
			} else {
				direction = RIGHT_LEFT;
				second_direction = TOP_BOTTOM;
			}
		} else {
			if (delt_t_b < delt_l_r) {
				direction = TOP_BOTTOM;
				second_direction = LEFT_RIGHT;
			} else {
				direction = LEFT_RIGHT;
				second_direction = TOP_BOTTOM;
			}
		}
		for (int i = 0; i < 2; i++) {
			int s_x = start.X, s_y = start.Y, s_x2 = 0, s_y2 = 0;
			int e_x = end.X, e_y = end.Y, e_x2 = 0, e_y2 = 0;
			if (direction == TOP_BOTTOM) {
				// System.out.println("TOP-BOTTOM");
				s_x += getRandom(start.x + start.w - 1 - 1, start.x + 1);
				s_y += start.y - 1;
				s_y2--;
				e_x += getRandom(end.x + end.w - 1 - 1, end.x + 1);
				e_y += end.y + end.h;
				e_y2++;
			} else if (direction == BOTTOM_TOP) {
				// System.out.println("BOTTOM-TOP");
				s_x += getRandom(start.x + start.w - 1 - 1, start.x + 1);
				s_y += start.y + start.h;
				s_y2++;
				e_x += getRandom(end.x + end.w - 1 - 1, end.x + 1);
				e_y += end.y - 1;
				e_y2--;
			} else if (direction == RIGHT_LEFT) {
				// System.out.println("RIGHT-LEFT");
				s_x += start.x + start.w;
				s_y += getRandom(start.y + start.h - 1 - 1, start.y + 1);
				s_x2++;
				e_x += end.x - 1;
				e_y += getRandom(end.y + end.h - 1 - 1, end.y + 1);
				e_x2--;
			} else if (direction == LEFT_RIGHT) {
				// System.out.println("LEFT-RIGHT");
				s_x += start.x - 1;
				s_y += getRandom(start.y + start.h - 1 - 1, start.y + 1);
				s_x2--;
				e_x += end.x + end.w;
				e_y += getRandom(end.y + end.h - 1 - 1, end.y + 1);
				e_x2++;
			}
			boolean flag[][] = new boolean[WIDTH][HEIGHT];
			flag[s_x][s_y] = true;
			flag[e_x][e_y] = true;
			flag[e_x + e_x2][e_y + e_y2] = true;
			boolean b = concatPath(s_x + s_x2, s_y + s_y2, e_x + e_x2, e_y
					+ e_y2, flag);
			if (b)
				break;
			if (direction == second_direction)
				return false;
			direction = second_direction;
			// System.out.print("\t");
		}
		start.enter = true;
		end.exit = true;
		start.neibor.add(end);
		end.neibor.add(start);
		return true;
	}

	private boolean concatPath_Random() {
		Division start = list.get(new R().nextInt(list.size()));
		Division end = list.get(new R().nextInt(list.size()));
		if (start == end)
			return false;
		final int top = 0, right = 1, bottom = 2, left = 3;
		int start_x, start_y, end_x, end_y;
		boolean flag[][] = new boolean[WIDTH][HEIGHT];
		while (true) {
			int x = start.X, y = start.Y, x2 = 0, y2 = 0;
			switch (new R().nextInt(4)) {
			case bottom:
				if (start.to_bottom)
					continue;
				x += getRandom(start.x + start.w - 1 - 1, start.x + 1);
				y += start.y + start.h;
				y2++;
				break;
			case left:
				if (start.to_left)
					continue;
				x += start.x - 1;
				y += getRandom(start.y + start.h - 1 - 1, start.y + 1);
				x2--;
				break;
			case right:
				if (start.to_right)
					continue;
				x += start.x + start.w;
				y += getRandom(start.y + start.h - 1 - 1, start.y + 1);
				x2++;
				break;
			case top:
				if (start.to_top)
					continue;
				x += getRandom(start.x + start.w - 1 - 1, start.x + 1);
				y += start.y - 1;
				y2--;
				break;
			}
			flag[x][y] = true;
			start_x = x + x2;
			start_y = y + y2;
			break;
		}
		while (true) {
			int x = end.X, y = end.Y, x2 = 0, y2 = 0;
			switch (new R().nextInt(4)) {
			case bottom:
				x += getRandom(end.x + end.w - 1 - 1, end.x + 1);
				y += end.y + end.h;
				y2++;
				break;
			case left:
				x += end.x - 1;
				y += getRandom(end.y + end.h - 1 - 1, end.y + 1);
				x2--;
				break;
			case right:
				x += end.x + end.w;
				y += getRandom(end.y + end.h - 1 - 1, end.y + 1);
				x2++;
				break;
			case top:
				x += getRandom(end.x + end.w - 1 - 1, end.x + 1);
				y += end.y - 1;
				y2--;
				break;
			}
			flag[x][y] = true;
			flag[x + x2][y + y2] = true;
			end_x = x + x2;
			end_y = y + y2;
			break;
		}
		return concatPath(start_x, start_y, end_x, end_y, flag);
	}

	private int getDistance(int x, int y) {
		return x * x + y * y;
	}

	private Division getJustNeiborExitRoom(Division div) {
		if (div == null)
			return null;
		Division D = null;
		final int x = div.x + div.X, y = div.y + div.Y;
		int distance_D = getDistance(x - WIDTH, y - HEIGHT), distance_d;
		for (Division d : list) {
			if (d == div)
				continue;
			distance_d = getDistance(x - (d.x + d.X + d.w / 2), y
					- (d.y + d.Y + d.h / 2));
			if (distance_d < distance_D) {
				D = d;
				distance_D = distance_d;
			}
		}
		return D;
	}

	char[][] getMass() {
		for (int i = 0; i < mass.length; i++)
			for (int j = 0; j < mass[i].length; j++) {
				if (mass[i][j] == ROOM)
					mass[i][j] = '*';
				else if (mass[i][j] == PATH)
					mass[i][j] = '.';
				else
					mass[i][j] = '#';
			}
		return mass;
	}

	char[][] getMass2() {
		for (int i = 0; i < mass.length; i++)
			for (int j = 0; j < mass[i].length; j++) {
				if (mass[i][j] == ROOM)
					mass[i][j] = '　';
				else if (mass[i][j] == PATH)
					mass[i][j] = '☆';
				else
					mass[i][j] = '壁';
			}
		return mass;
	}

	/*
	 * private Division getLeftUpEnterRoom() { Division D = null; int distance_D
	 * = getDistance(WIDTH,HEIGHT),distance_d;
	 * System.out.println("D\t"+distance_D); for(Division d : list){ if(d.enter)
	 * continue; distance_d = getDistance(d.x+d.X+d.w/2, d.y+d.Y+d.h/2);
	 * System.out.println("d\t"+distance_d); if(distance_d < distance_D){ D = d;
	 * distance_D = distance_d; } } return D; }
	 */
	private int getRandom(int max, int min) {
		return new R().nextInt(max - min + 1) + min;
	}

	private Division getUnCheckedNeiborExitRoom(Division div) {
		if (div == null)
			return null;
		Division D = null;
		final int x = div.x + div.X, y = div.y + div.Y;
		int distance_D = getDistance(x - WIDTH, y - HEIGHT), distance_d;
		for (Division d : list) {
			if (d == div || d.exit || d.enter)
				continue;
			distance_d = getDistance(x - (d.x + d.X + d.w / 2), y
					- (d.y + d.Y + d.h / 2));
			if (distance_d < distance_D) {
				D = d;
				distance_D = distance_d;
			}
		}
		return D;
	}

	private void init() {
		do {
			for (int i = 0; i < mass.length; i++)
				for (int j = 0; j < mass[i].length; j++)
					mass[i][j] = '＃';
			list = new ArrayList<Division>();
			list.add(new Division(mass.length, mass[0].length, 0, 0, 0));
			list.add(list.get(0).devide_width());

			for (int i = 0; i < 3; i++) {
				Division d = null;
				try {
					while (true) {
						d = list.get(new R().nextInt(list.size()));
						if (d.wanna_cut >= d.devided) {
							list.add(d.devide());
							for (Division div : list)
								div.wanna_cut = 0;
						} else {
							d.wanna_cut++;
						}
					}
				} catch (IllegalArgumentException e) {
					// 割り切れなくなったよ
					d.wanna_cut = 0;
				}
			}
			for (Division d : list)
				d.createRoom();
			for (Division d : list)
				d.setMass();
		} while (!concatPath());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 's') {
			try {
				int index = new File("map").listFiles().length;
				File file = new File("map/".concat(String.valueOf(index))
						.concat(".txt"));
				BufferedWriter filewriter = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(file),
								"UTF8"));
				char[][] masses = getMass2();
				for (int y = 0; y < masses[0].length; y++) {
					for (int x = 0; x < masses.length; x++) {
						filewriter.write(String.valueOf(masses[x][y]));
					}
					filewriter.write("\r\n");
				}
				filewriter.close();
			} catch (IOException e2) {
				System.out.println(e2);
			}
			getMass();
			System.out.println("セーブしました。");
		} else
			System.out.println("sキーでセーブ。");
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		init();
		jf.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
