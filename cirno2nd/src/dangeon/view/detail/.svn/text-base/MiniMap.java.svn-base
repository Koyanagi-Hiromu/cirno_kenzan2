package dangeon.view.detail;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Second_Firster;
import main.thread.MainThread;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.MainSystem;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.device.扉;
import dangeon.model.object.artifact.device.神階段;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印罠師;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.npc.守矢賽銭箱;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.NormalFont;

public class MiniMap {
	private static int x_min, x_max, y_min, y_max;
	public static final Color C_MAP = new Color(0x11, 0x11, 0xff, 150),
			C_MAP_WARNING = new Color(0x11, 0x11, 0xff, 20),
			C_MAP_CHECKED = new Color(0x00, 0x00, 0x88, 150),
			C_MAP_PATH = new Color(0x99, 0x99, 0xdd, 100), C_HOLY = new Color(
					0xff, 0xff, 0x11, 100);
	public static final Color C_ARTIFACT = new Color(255, 200, 0),
			C_ARTIFACT_CHECKED = new Color(100, 255, 100),
			C_PLAYER = new Color(255, 255, 255),
			C_TRAP = new Color(255, 255, 0), C_DEVICE = new Color(0, 255, 255),
			C_GOLDSTAIR = new Color(255, 255, 100), C_ENEMY = new Color(255, 0,
					0);
	private static final int MINI_MAP_SIZE = 5;
	private static BufferedImage IMAGE;

	private static final int FONT_HEIGHT;
	static {
		FontMetrics fm = Second_Firster.ME.getFontMetrics(NormalFont.NORMALFONT
				.deriveFont((float) MINI_MAP_SIZE));
		FONT_HEIGHT = fm.getHeight() - fm.getDescent();
		reset();
	}
	private static final int WIDTH = MassCreater.WIDTH * MINI_MAP_SIZE,
			HEIGHT = MassCreater.HEIGHT * MINI_MAP_SIZE;

	public static void addPicture(Mass m) {
		if (m.isVisible() || !m.WALKABLE) {
			return;
		}
		forceToAddPicture(m);

	}

	public static void draw(Graphics2D g) {
		if (DangeonScene.isPresentSceneLikeMenu()) {
			return;
		}
		boolean flag_map = Scene_Action.getMe().isMap()
				&& MainSystem.isKeyAccept();
		if (!Config.isMini_map_on() && !flag_map) {
			return;
		}
		g.setFont(NormalFont.NORMALFONT.deriveFont((float) MINI_MAP_SIZE)
				.deriveFont(Font.BOLD));
		g.drawImage(IMAGE, getOrigineX(), getOrigineY(), null);
		drawMainMap_Minimap(g);
		if (flag_map) {
			drawDirection(g);
		}
		g.setFont(NormalFont.NORMALFONT);
	}

	private static void drawDirection(Graphics2D g) {
		// BufferedImage im = new BufferedImage(IMAGE.getWidth(),
		// IMAGE.getHeight(), IMAGE.getType());
		// Graphics2D off = g;
		DIRECTION d = Player.me.getDirection();
		int x = Player.me.getMassPoint().x;
		int y = Player.me.getMassPoint().y;
		Color color = new Color(255, 255, 255, 100);
		g.setColor(color);
		int i = 0;
		int max = Math.max(MassCreater.WIDTH, MassCreater.HEIGHT);
		boolean flag_fall = false;
		while (i++ < max) {
			if (!flag_fall) {
				boolean flag = false;
				if (!MassCreater.getMass(x + d.X, y + d.Y).WALKABLE) {
					flag = true;
				}
				boolean flag_creature = !MainMap.isOutofPlayerSight(MapList
						.getEnemy(x, y));
				if (flag || flag_creature || i == 11
						|| !MassCreater.getMass(x, y).isVisible()) {
					flag_fall = true;
					if (!MassCreater.getMass(x, y).isVisible()) {
						g.setColor(new Color(255, 255, 255, 50));
						int s = MINI_MAP_SIZE;
						int h = s * 3 / 4;
						g.fillOval(getOrigineX() + x * s + h / 4, getOrigineY()
								+ y * s + h / 4, h, h);
					} else if (MainThread.getFrame() % 20 < 10) {
						g.setColor(new Color(255, 255, 255, 200));
						g.drawRect(getOrigineX() + x * MINI_MAP_SIZE,
								getOrigineY() + y * MINI_MAP_SIZE,
								MINI_MAP_SIZE, MINI_MAP_SIZE);
					}
					g.setColor(new Color(255, 255, 255, 50));
					x += d.X;
					y += d.Y;
					continue;
				}
			}
			if (i != 1) {
				int s = MINI_MAP_SIZE;
				int h = s * 3 / 4;
				g.drawString("＊", getOrigineX() + x * s + h / 4, getOrigineY()
						+ y * s + h + 2);
				// g.fillOval(getOrigineX() + x * s + h / 4, getOrigineY() +
				// y
				// * s + h / 4, h, h);
			}
			x += d.X;
			y += d.Y;

		}
		// x = Player.me.getMassPoint().x
		// + d.X*10;
		// y = Player.me.getMassPoint().y
		// + d.Y*10;
		// g.setColor(new Color(0, 255, 255));
		// g.drawRect(getOrigineX() + x * MINI_MAP_SIZE, getOrigineY() + y
		// * MINI_MAP_SIZE, MINI_MAP_SIZE-1, MINI_MAP_SIZE-1);
		// off.dispose();
		// g.drawImage(im, getOrigineX(), getOrigineY(), null);
	}

	private static void drawFrozenWaterLines(Graphics2D g, Mass m, int x_l,
			int y_t, int x_r, int y_b) {
		if (!m.WATER_FROZEN)
			return;
		/*
		 * RIGHT____down_up DOWN____left_right LEFT_____up_down
		 * UP_______right_left
		 */
		int[][][] ints = {
				{ { x_r, y_t + 1, x_r, y_b - 1 }, { x_r, y_b, x_r, y_b },
						{ x_r, y_t, x_r, y_t } },
				{ { x_l + 1, y_b, x_r - 1, y_b }, { x_l, y_b, x_l, y_b },
						{ x_r, y_b, x_r, y_b } },
				{ { x_l, y_t + 1, x_l, y_b - 1 }, { x_l, y_t, x_l, y_t },
						{ x_l, y_b, x_l, y_b } },
				{ { x_l + 1, y_t, x_r - 1, y_t }, { x_r, y_t, x_r, y_t },
						{ x_l, y_t, x_l, y_t } }, };
		int i = -1;
		boolean[] flag_drawn = new boolean[4];
		for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
			i++;
			if (!m.get(d).WATER_FROZEN) {
				setFrozenWaterLinesColor(g);
				drawLine(g, ints[i][0][0], ints[i][0][1], ints[i][0][2],
						ints[i][0][3]);
				for (int j = 0; j < 2; j++) {
					DIRECTION d2 = d.getNeiboringDirection(-2 * (j * 2 - 1));
					int num = DIRECTION.getDirection(d.X + d2.X, d.Y + d2.Y).ROTATE / 2;
					if (flag_drawn[num])
						continue;
					flag_drawn[num] = true;
					if (!m.get(d2).WATER_FROZEN && m.isRoom())
						g.setColor(getC_MAP(m));
					else
						setFrozenWaterLinesColor(g);
					int k = j + 1;
					drawLine(g, ints[i][k][0], ints[i][k][1], ints[i][k][2],
							ints[i][k][3]);
				}
			}
		}
	}

	public static void drawHolyRect(Room r) {
		Graphics2D g = (Graphics2D) IMAGE.getGraphics();
		g.setColor(C_GOLDSTAIR);
		int x = r.X * MINI_MAP_SIZE;
		int y = r.Y * MINI_MAP_SIZE;
		g.drawRect(x, y, r.W * MINI_MAP_SIZE - 1, r.H * MINI_MAP_SIZE - 1);
		g.dispose();
	}

	private static void drawLine(Graphics2D g, int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
		g.setStroke(new BasicStroke());
	}

	private static void drawMainMap_Minimap(Graphics2D g) {
		String artifact;
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (a.isHidden()) {
				continue;
			}
			if (a.isAnimating()) {
				continue;
			}
			if (!MassCreater.getMass(a.getMassPoint()).isVisible()
					&& !EnchantSpecial.toushi()) {
				continue;
			}
			if (!a.isVisible()) {
				if (!(a instanceof Base_Device)) {
					if (!Player.me.conditionCheck(CONDITION.目薬)
							&& !印罠師.effect()
							|| EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
									ENCHANT_SIMBOL.探知)) {
						// 不可視なアイテムかトラップでかつ目薬状態でないなら見えない
						continue;
					}
				} else {
					continue;
				}
			}
			if (a instanceof 神階段) {
				if (!MassCreater.getMass(a.getMassPoint()).isVisible()) {
					continue;
				}
				g.setColor(C_GOLDSTAIR);
				artifact = "□";
			} else if (a instanceof Stairs) {
				if (!MassCreater.getMass(a.getMassPoint()).isVisible()) {
					continue;
				}
				g.setColor(C_DEVICE);
				artifact = "□";
			} else if (a instanceof 扉) {
				if (!MassCreater.getMass(a.getMassPoint()).isVisible()) {
					continue;
				}
				g.setColor(C_DEVICE);
				artifact = "◎";
			} else if (a instanceof Base_Trap) {
				g.setColor(C_TRAP);
				artifact = "×";
			} else if (a instanceof Base_Device) {
				g.setColor(C_DEVICE);
				artifact = "■";
			} else {
				Color c;
				if (a.isPunishment() || a.isMerchant())
					c = C_GOLDSTAIR;
				else if (a instanceof Base_Item
						&& ((Base_Item) a).isPickCheked())
					c = C_ARTIFACT_CHECKED;
				else
					c = C_ARTIFACT;
				if (MassCreater.getMass(a.getMassPoint()).WATER)
					c = c.darker().darker();
				g.setColor(c);
				artifact = "■";
			}
			drawMap(g, artifact, a.getMassPoint().x, a.getMassPoint().y);
		}
		boolean flag_toushi = EnchantSpecial.toushi();
		for (Base_Creature c : MapList.getListCreature()) {
			if (c.isPlayerSide() && (flag_toushi || c.isShownOnMiniMap())) {
				g.setColor(C_PLAYER);
				MiniMap.drawMap(g, "●", c.getMassPoint().x, c.getMassPoint().y);
			} else if (c.isFlagWarning()) {
				if (CONDITION.isMovable(c) || flag_toushi
						|| c.isShownOnMiniMap()) {
					boolean flag_swith = (MainThread.getFrame() % 20 < 10);
					g.setColor(C_ENEMY);
					if (flag_swith)
						g.setColor(C_ENEMY);
					else
						g.setColor(C_ARTIFACT);
					Font f = g.getFont();
					int size = 9;
					g.setFont(f.deriveFont((float) size));

					MiniMap.drawMap(g, flag_swith ? "★" : "☆",
							c.getMassPoint().x, c.getMassPoint().y, -size / 4);
					g.setFont(f);
				}
			} else if (flag_toushi || c.isShownOnMiniMap()) {
				if (c instanceof Base_NPC) {
					if (c instanceof 守矢賽銭箱) {
						g.setColor(C_GOLDSTAIR);
					} else {
						g.setColor(C_PLAYER);
					}
				} else {
					g.setColor(C_ENEMY);
				}
				MiniMap.drawMap(g, "◆", c.getMassPoint().x, c.getMassPoint().y);
			}
		}
		g.setColor(C_PLAYER);
		drawMap(g, (MainThread.getFrame() % 20 < 10) ? "⑨" : "●",
				Player.me.getMassPoint().x, Player.me.getMassPoint().y);
	}

	private static void drawMap(Graphics2D g, String symbol, int x, int y) {
		drawMap(g, symbol, x, y, 0);
	}

	private static void drawMap(Graphics2D g, String symbol, int x, int y,
			int delt) {
		g.drawString(symbol, getOrigineX() + x * MINI_MAP_SIZE + delt,
				getOrigineY() + y * MINI_MAP_SIZE + FONT_HEIGHT);
	}

	private static void drawWallLines(Graphics2D g, Mass m, int x_l, int y_t,
			int x_r, int y_b) {
		Mass m2;
		m2 = m.get(DIRECTION.LEFT);
		if (!m2.WALKABLE) {
			setColor_Gird(g, m, m2);
			drawLine(g, x_l, y_t, x_l, y_b);
		}
		m2 = m.get(DIRECTION.RIGHT);
		if (!m2.WALKABLE) {
			setColor_Gird(g, m, m2);
			drawLine(g, x_r, y_t, x_r, y_b);
		}
		m2 = m.get(DIRECTION.UP);
		if (!m2.WALKABLE) {
			setColor_Gird(g, m, m2);
			drawLine(g, x_l, y_t, x_r, y_t);
		}
		m2 = m.get(DIRECTION.DOWN);
		if (!m2.WALKABLE) {
			setColor_Gird(g, m, m2);
			drawLine(g, x_l, y_b, x_r, y_b);
		}
		// ここから斜め
		m2 = m.get(DIRECTION.UP_LEFT);
		if (!m2.WALKABLE) {
			if (m.get(DIRECTION.LEFT).WALKABLE) {
				if (m.get(DIRECTION.UP).WALKABLE) {
					setColor_Gird(g, m, m2);
					drawLine(g, x_l, y_t, x_l, y_t);
				}
			}
		}
		m2 = m.get(DIRECTION.UP_RIGHT);
		if (!m2.WALKABLE) {
			if (m.get(DIRECTION.RIGHT).WALKABLE) {
				if (m.get(DIRECTION.UP).WALKABLE) {
					setColor_Gird(g, m, m2);
					drawLine(g, x_r, y_t, x_r, y_t);
				}
			}
		}
		m2 = m.get(DIRECTION.DOWN_LEFT);
		if (!m2.WALKABLE) {
			if (m.get(DIRECTION.LEFT).WALKABLE) {
				if (m.get(DIRECTION.DOWN).WALKABLE) {
					setColor_Gird(g, m, m2);
					drawLine(g, x_l, y_b, x_l, y_b);
				}
			}
		}
		m2 = m.get(DIRECTION.DOWN_RIGHT);
		if (!m2.WALKABLE) {
			if (m.get(DIRECTION.RIGHT).WALKABLE) {
				if (m.get(DIRECTION.DOWN).WALKABLE) {
					setColor_Gird(g, m, m2);
					drawLine(g, x_r, y_b, x_r, y_b);
				}
			}
		}
	}

	private static void drawWarnings(Graphics2D g, Mass m, int x, int y) {
		if (m.isTrapChecked()) {
			return;
		}
		boolean yellow, red;
		yellow = m.isWarningLarge(false) && !m.isWarningLarge(true);
		red = m.isWarningSmall(false) && !m.isWarningSmall(true);
		Color c;
		if (yellow && red) {
			c = Color.MAGENTA;
		} else if (yellow) {
			c = Color.YELLOW;
		} else if (red) {
			c = Color.RED;
		} else {
			return;
		}
		int w = 2;
		g.setColor(c.darker().darker());
		g.drawRect(x + 1, y + 1, w, w);
	}

	private static void drawWaterLines(Graphics2D g, Mass m, int x_l, int y_t,
			int x_r, int y_b) {
		if (!m.isWater_or_WaterLeef())
			return;
		/*
		 * RIGHT____down_up DOWN____left_right LEFT_____up_down
		 * UP_______right_left
		 */
		int[][][] ints = {
				{ { x_r, y_t + 1, x_r, y_b - 1 }, { x_r, y_b, x_r, y_b },
						{ x_r, y_t, x_r, y_t } },
				{ { x_l + 1, y_b, x_r - 1, y_b }, { x_l, y_b, x_l, y_b },
						{ x_r, y_b, x_r, y_b } },
				{ { x_l, y_t + 1, x_l, y_b - 1 }, { x_l, y_t, x_l, y_t },
						{ x_l, y_b, x_l, y_b } },
				{ { x_l + 1, y_t, x_r - 1, y_t }, { x_r, y_t, x_r, y_t },
						{ x_l, y_t, x_l, y_t } }, };
		int i = -1;
		boolean[] flag_drawn = new boolean[4];
		for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
			i++;
			if (!m.get(d).isWater_or_WaterLeef()) {
				setWaterLinesColor(g, m);
				drawLine(g, ints[i][0][0], ints[i][0][1], ints[i][0][2],
						ints[i][0][3]);
				for (int j = 0; j < 2; j++) {
					DIRECTION d2 = d.getNeiboringDirection(-2 * (j * 2 - 1));
					int num = DIRECTION.getDirection(d.X + d2.X, d.Y + d2.Y).ROTATE / 2;
					if (flag_drawn[num])
						continue;
					flag_drawn[num] = true;
					if (!m.get(d2).isWater_or_WaterLeef() && m.isRoom())
						g.setColor(getC_MAP(m));
					else
						setWaterLinesColor(g, m);
					int k = j + 1;
					drawLine(g, ints[i][k][0], ints[i][k][1], ints[i][k][2],
							ints[i][k][3]);
				}
			}
		}
	}

	private static void forceToAddPicture(Mass m) {
		m.setVisible(true);
		int x = m.X * MINI_MAP_SIZE;
		int y = m.Y * MINI_MAP_SIZE;
		Graphics2D g = (Graphics2D) IMAGE.getGraphics();
		// if (!m.isWater_or_WaterLeef()) {
		setColor(g, m);
		g.fillRect(x, y, MINI_MAP_SIZE, MINI_MAP_SIZE);
		// } else if () {
		// g.setColor(Color.GREEN);
		// g.fillRect(x + 1, y + 1, MINI_MAP_SIZE - 2, MINI_MAP_SIZE - 2);
		// }
		drawWarnings(g, m, x, y);
		int x_l = x, x_r = x + MINI_MAP_SIZE - 1, y_t = y, y_b = y
				+ MINI_MAP_SIZE - 1;
		drawWaterLines(g, m, x_l, y_t, x_r, y_b);
		drawFrozenWaterLines(g, m, x_l, y_t, x_r, y_b);
		drawWallLines(g, m, x_l, y_t, x_r, y_b);
		g.dispose();
		if (m.X < x_min) {
			x_min = m.X;
		}
		if (m.Y < y_min) {
			y_min = m.Y;
		}
		if (m.X > x_max) {
			x_max = m.X;
		}
		if (m.Y > y_max) {
			y_max = m.Y;
		}
	}

	private static Color getC_MAP(Mass m) {
		if (m.isTrapChecked())
			return C_MAP_CHECKED;
		// else if (m.isWarning())
		// return C_MAP_WARNING;
		else
			return C_MAP;
	}

	private static int getOrigineX() {
		int w = (x_max - x_min + 1) * MINI_MAP_SIZE;
		return 95 - w / 2 - x_min * MINI_MAP_SIZE;
	}

	private static int getOrigineY() {
		int h = (y_max - y_min + 1) * MINI_MAP_SIZE;
		return 150 - h / 2 - y_min * MINI_MAP_SIZE;
	}

	private static void openAroundTile(int x, int y) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				Mass m = MassCreater.getMass(x + i, y + j);
				if (m.isWalkable()) {
					addPicture(m);
				}
			}
		}
	}

	private static void openRoom(int x, int y) {
		MassCreater.getRoom(x, y).draw();
	}

	private static void openRoom_concated_path(int x, int y,
			ArrayList<Mass> list) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				Mass m = MassCreater.getMass(x + i, y + j);
				if (list.contains(m)) {
					continue;
				}
				if (m.isWalkable()) {
					if (m.isRoom()) {
						list.add(m);
						openRoom_concated_path(x + i, y + j, list);
					} else {
						addPicture(m);
					}
				}
			}
		}
	}

	/**
	 * This is called , for example, when character walk.
	 */
	public static void openTiles() {
		Point p = Player.me.getMassPoint();
		openTiles(p.x, p.y);
		if (!MassCreater.isSpotValid()) {
			for (Mass m : MassCreater.getMasses_NotSpotValid(p.x, p.y)) {
				if (m.isWalkable()) {
					addPicture(m);
				}
			}
		}
	}

	private static void openTiles(int x, int y) {
		if (MassCreater.getMass(x, y).isRoom()) {
			if (MassCreater.getRoom(x, y).isDrawed()) {
				return;
			}
			openRoom_concated_path(x, y, new ArrayList<Mass>());
			openRoom(x, y);
		} else {
			openAroundTile(x, y);
		}
	}

	public static void reset() {
		x_max = -1;
		x_min = MassCreater.WIDTH;
		y_max = -1;
		y_min = MassCreater.HEIGHT;
		IMAGE = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB_PRE);
		for (Mass[] m2 : MassCreater.getMass()) {
			for (Mass m : m2) {
				if (m.isVisible()) {
					forceToAddPicture(m);
				}
			}
		}
		openTiles();
	}

	private static void setColor(Graphics2D g, Mass m) {
		if (MassCreater.getRoom(m.X, m.Y) == null) {
			g.setColor(C_MAP_PATH);
		} else {
			g.setColor(getC_MAP(m));
			if (m.isHoly()) {
				boolean flag = false;
				for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
					if (!MassCreater.getMass(m.X + d.X, m.Y + d.Y).isHoly()) {
						flag = true;
						break;
					}
				}
				if (flag) {
					g.setColor(C_HOLY);
					return;
				}
			}
			if (m.isShop()) {
				boolean flag = false;
				for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
					if (!MassCreater.getMass(m.X + d.X, m.Y + d.Y).isShop()) {
						flag = true;
						break;
					}
				}
				if (flag) {
					g.setColor(new Color(0x11, 0xff, 0x11, 100));
					return;
				}
			}
		}

	}

	private static void setColor_Gird(Graphics2D g, Mass m, Mass m2) {
		if (!m2.isDiggable()) {
			g.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 2f));
		}
		if (m.ROOM) {
			g.setColor(new Color(120, 200, 200));
		} else {
			g.setColor(new Color(180, 180, 180));
		}
	}

	private static void setFrozenWaterLinesColor(Graphics2D g) {
		g.setColor(new Color(255, 255, 255));
	}

	private static void setWaterLinesColor(Graphics2D g, Mass m) {
		if (m.WATER)
			g.setColor(new Color(20, 20, 255));
		else
			g.setColor(Color.GREEN);
	}
}
