package dangeon.view.detail;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import dangeon.controller.MainSystem;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.StairScene;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.bullet.Base_Bullet;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印罠師;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.わかさぎ姫;
import dangeon.model.object.creature.enemy.洩矢諏訪子;
import dangeon.model.object.creature.enemy.風見幽香;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.Stand;
import dangeon.util.MergeSort;
import dangeon.util.ObjectPoint;
import dangeon.view.anime.Base_Anime;
import dangeon.view.constant.MAP;
import dangeon.view.constant.NormalFont;
import dangeon.view.detail.name.MainMap_Name;
import dangeon.view.util.StringFilter;
import dangeon.view.util.WithinOutofScreen;
import main.constant.FR;
import main.res.CHARA_IMAGE;
import main.res.Image_Effect;
import main.res.Image_MapTip;
import main.thread.MainThread;
import main.util.BeautifulView;
import main.util.DIRECTION;
import main.util.EX_DIRECTION;
import main.util.半角全角コンバーター;

public class MainMap {

	private static final List<Base_Anime> list_effect = new ArrayList<Base_Anime>();
	private static ArrayList<Base_Creature> animating_creature_list = new ArrayList<Base_Creature>();

	public final static HashMap<EX_DIRECTION, Integer> wall_map = new HashMap<EX_DIRECTION, Integer>();

	static {
		wall_map.put(EX_DIRECTION.UP_LEFT, 0);
		wall_map.put(EX_DIRECTION.LEFT, 1);
		wall_map.put(EX_DIRECTION.DOWN_LEFT, 2);
		wall_map.put(EX_DIRECTION.UP, 3);
		wall_map.put(EX_DIRECTION.NEUTRAL, 4);
		wall_map.put(EX_DIRECTION.DOWN, 5);
		wall_map.put(EX_DIRECTION.UP_RIGHT, 6);
		wall_map.put(EX_DIRECTION.RIGHT, 7);
		wall_map.put(EX_DIRECTION.DOWN_RIGHT, 8);
		wall_map.put(EX_DIRECTION.ELSE, 9);
	}

	public static Color shadow[] = { new Color(0, 0, 0),
			new Color(100, 0, 100), new Color(100, 100, 0),
			new Color(100, 0, 0) };

	private static double grid_time = 0;
	private static BufferedImage base_image;

	private static final Area SCREEN = new Area(new Rectangle2D.Double(
			-MAP.TILE_SIZE, -MAP.TILE_SIZE,
			FR.SCREEN_WIDTH + MAP.TILE_SIZE * 2, FR.SCREEN_HEIGHT
					+ MAP.TILE_SIZE * 2));

	/**
	 * synchronized => draw中は遮断
	 * 
	 * @param e
	 */
	public synchronized static void addEffect(Base_Anime e) {
		list_effect.add(e);
	}

	/**
	 * synchronized => draw中は遮断
	 * 
	 * @param e
	 * @param b
	 *            true => 描画終了まで、キー入力無効＆ターン進行不可
	 */
	public synchronized static void addEffect(Base_Anime e, boolean b) {
		if (b) {
			MainSystem.addDemandForWaitUntilRemoved(e, list_effect);
		}
		addEffect(e);
	}

	public static void draw(Graphics2D g) {
		// IntervalChecker.strart();
		// 2ms
		drawMap(g);
		drawGrid(g);
		drawMapObject(g);
		drawAnimatingCreature(g);
		// 4-5ms
		drawSpot(g);
		drawEffect(g);
		// IntervalChecker.end();
	}

	private static void drawAnimatingCreature(Graphics2D g) {
		for (Iterator<Base_Creature> iterator = animating_creature_list
				.iterator(); iterator.hasNext();) {
			Base_Creature c = iterator.next();
			if (c.getAnimation() == null) {
				iterator.remove();
			} else {
				Base_Anime anime = c.getAnimation();
				boolean animating = c.getAnimation().drawAnime(g);
				if (anime != c.getAnimation()) {
					// アニメ中に他のアニメに切り替わった場合iteratorをremoveしない
					continue;
				}
				if (!animating) {
					c.finshAnimation();
					iterator.remove();
				}
			}
		}
	}

	private static void drawArtifacts(Graphics2D g, boolean jumping) {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>(MapList
				.getListArtifact().size());
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (jumping == a.isJumping()
					&& MassCreater.getMass(a.getMassPoint()).WALKABLE)
				list.add(a);
		}
		ArrayList<Base_Artifact> order;
		if (jumping)
			order = MergeSort.creatureY(list);
		else
			order = list;
		for (Base_Artifact a : order) {
			if (jumping != a.isJumping())
				continue;
			Mass m = MassCreater.getMass(a.getMassPoint());
			if (!isVisible(a))
				continue;
			Point p = ObjectPoint.getScreenPointRelPlayer(a);
			int y = p.y;
			boolean flag = false;
			if (!jumping && m.WATER) {
				flag = true;
			}
			int size = 18;
			if (!flag && !Config.isNoShadow() && a.getShadow() > 0) {
				int awa = a.jumped_away_y;
				if (a.isJumping()) {
					size = size + awa / 3;
				}
				drawShadow(g, size, p.x, y + 6 - awa, a.getShadow());
			}
			if (flag) {
				BeautifulView.setAlphaOnImg(g, 0.3f);
			}
			g.drawImage(a.getImage(), p.x, y, null);
			if (flag)
				BeautifulView.setAlphaOnImg(g, 1f);
		}
	}

	//
	// private static void drawCreature(Graphics2D g, Base_Creature c) {
	// Point p = ObjectPoint.getScreenPointRelPlayer(c);
	// int x = p.x;
	// int y = p.y;
	// if (c.isJumping()) {
	// y += c.getJumpDY();
	// }
	// if (c == Player.me) {
	// if (Player.me.isDying()) {
	// BeautifulView.setAlphaOnImg(g, Player.me.getDyingAlpha());
	// g.drawImage(c.getImage(), x + c.getFootX(), y + c.getFootY(),
	// null);
	// BeautifulView.setAlphaOnImg(g, 1f);
	// } else {
	// g.drawImage(c.getImage(), x + c.getFootX(), y + c.getFootY(),
	// null);
	// if (Scene_Action.getMe().is_I_Dushing()) {
	// g.setColor(new Color(150, 255, 255));
	// String tit = "＼あたいにお任せダッシュ／";
	// StringFilter.drawString(g, tit, x + c.getFootX() - 100, y
	// + c.getFootY());
	// // g.drawString(I_Dush.auto ? "(ﾟДﾟ)ﾉ ｧｨ" : "(  ﾟДﾟ)? ",
	// // x + c.getFootX() + 50, y + c.getFootY() + 35);
	// }
	// }
	// } else {
	// g.drawImage(c.getImage(), x + c.getFootX(), y + c.getFootY(), null);
	// }
	// for (CONDITION cnd : c.getConditionList()) {
	// if (cnd.IMA != null) {
	// if (c.equals(Player.me) && c.conditionCheck(CONDITION.睡眠)) {
	// continue;
	// }
	// cnd.IMA.draw(g, x + c.getHeadX(), y, c);
	// }
	// }
	// }

	private static void drawCreatures(Graphics2D g, boolean name) {
		List<Base_Creature> list = new ArrayList<Base_Creature>();
		List<Base_Creature> jumping_list = new ArrayList<Base_Creature>();
		for (Base_Enemy e : MapList.getListEnemy())
			list.add(e);
		
		if (!name)
		{
			list.add(Player.me);
			ignoreCreature(list, jumping_list);
		}
		else
		{
			ignoreCreatureForNameplate(list);
		}

		List<Base_Creature> order = MergeSort.creatureY(list);
		if (name) {
			for (Base_Creature c : order)
				MainMap_Name.drawName(c);
			for (Base_Creature c : jumping_list)
				MainMap_Name.drawName(c);
			MainMap_Name.drawName(g);
		} else {
			for (Base_Creature c : order)
				drawCreatureShadow(g, c);
			for (Base_Creature c : jumping_list)
				drawCreatureShadow(g, c);
			for (Base_Creature c : order)
				c.drawCreature(g);
			if (Player.me.isStandExist()) {
				for (ListIterator<Stand> li = Player.me.getListIterator(); li
						.hasNext();) {
					Stand s = li.next();
					Point p = ObjectPoint.getScreenPointRelPlayer(s.SCREEN_X,
							s.SCREEN_Y);
					int x = p.x;
					int y = p.y;
					if (s.E.getSize() == 100) {
						g.drawImage(s.getImage(g), x + s.getFootX(),
								y + s.getFootY(), null);
					} else {
						int size = s.E.getSize();
						Image bi = s.getImage(g);
						int w = bi.getWidth(null) * size / 100;
						int h = bi.getHeight(null) * size / 100;
						g.drawImage(bi, x + s.getFootX(), y + s.getFootY(), w,
								h, null);
					}
				}
				BeautifulView.setAlphaOnImg(g, 1F);
			}
			for (Base_Creature c : jumping_list)
				c.drawCreature(g);
		}
	}

	private static void drawCreatureShadow(Graphics2D g, Base_Creature c) {
		Point p = ObjectPoint.getScreenPointRelPlayer(c);
		int x = p.x;
		int y = p.y + 2;
		int size = c.getShadowSize();
		if (c.isJumping()) {
			if (c.jumped_away_y < 0)
				size += c.jumped_away_y / 15;
		}
		if (MassCreater.getMass(c.getMassPoint()).WATER && !c.isFlying()) {
			if (c.isBoating()) {
				if (!(c instanceof 洩矢諏訪子) && !(c instanceof 風見幽香)) {
					Image im = CHARA_IMAGE.船.getWalkImage(0, c.getDirection(),
							1f);
					int width = im.getWidth(null), height = im.getHeight(null);
					g.drawImage(im, x + MAP.TILE_SIZE / 2 - width / 2, y
							- height / 2 + MAP.Y_OFFSET, null);
					return;
				}
			} else if (c instanceof わかさぎ姫) {
				return;
			}
		} else if (c == Player.me) {
			if (Player.me.isDying() || StairScene.isTasksEmpty()) {
				BeautifulView.setAlphaOnImg(g, Player.me.getDyingAlpha());
				drawShadow(g, size, x, y, c.getShadowY() + c.jumped_away_y, c);
				BeautifulView.setAlphaOnImg(g, 1f);
				return;
			} else if (Player.me.isFalling()) {
				return;
			}
		}
		if (MassCreater.getMass(c.getMassPoint()).WALKABLE) {
			drawShadow(g, size, x, y, c.getShadowY() + c.jumped_away_y, c);
		}
	}

	private synchronized static void drawEffect(Graphics2D g) {
		for (Iterator<Base_Anime> iterator = list_effect.iterator(); iterator
				.hasNext();) {
			Base_Anime e = iterator.next();
			if (!e.drawAnime(g)) {
				iterator.remove();
			}
		}
	}

	/**
	 * 
	 * @param g
	 */
	private static void drawGrid(Graphics2D g) {
		Point p = Player.me.getPureScreenPoint().getLocation();
		Point _P = Player.me.getMassPoint();
		p.translate(-_P.x * MAP.TILE_SIZE, -_P.y * MAP.TILE_SIZE);
		if (!Config.isMass_OFF())
			drawGrid_Mass(g, p, _P);
		if (!Player.me.isAnimating())
			drawGrid_DIRECTION(g, p, _P);
		// for (int i = 0; i < MAP.TILE_HORIZON_INNER_NUMBER + 1; i++) {
		// int x = MAP.TILE_SIZE * i - MAP.TILE_SIZE / 2 - p.x;
		// g.drawLine(x, 0, x, FR.SCREEN_HEIGHT);
		// x += MAP.TILE_SIZE - 1;
		// g.drawLine(x, 0, x, FR.SCREEN_HEIGHT);
		// }
		// for (int j = 0; j < MAP.TILE_VERTICAL_INNER_NUMBER; j++) {
		// int y = MAP.TILE_SIZE * j - p.y;
		// g.drawLine(0, y, FR.SCREEN_WIDTH, y);
		// y += MAP.TILE_SIZE - 1;
		// g.drawLine(0, y, FR.SCREEN_WIDTH, y);
		// }
	}

	private static void drawGrid_DIRECTION(Graphics2D g, Point p, Point _P) {
		ArrayList<DIRECTION> ds = new ArrayList<DIRECTION>(5);
		if (Scene_Action.getMe().isDirecting()
				|| Scene_Action.getMe().isBiasButtonPushing()) {
			if (Config.isMass_OFF())
				drawGrid_Mass(g, p, _P);
			grid_time += 0.4;
			if (Scene_Action.getMe().isDirecting())
				ds.add(Player.me.getDirection());
			if (Scene_Action.getMe().isBiasButtonPushing())
				for (DIRECTION d : DIRECTION.values_onlyNANAME4())
					ds.add(d);
		} else {
			grid_time = 0;
		}
		for (DIRECTION d : ds) {
			Point point = Player.me.getMassPoint().getLocation();
			ArrayList<Base_Creature> list = new ArrayList<Base_Creature>(4);
			for (int i = 0; i < 4; i++) {
				point.translate(d.X, d.Y);
				Base_Creature c = MapList.getCreature(point);
				if (c != null)
					list.add(c);
			}
			ignoreCreature(list, null);
			point = Player.me.getMassPoint().getLocation();
			for (int i = 0; i < 5; i++) {
				int _x = (point.x - _P.x + MAP.TILE_HORIZON_INNER_NUMBER / 2)
						* MAP.TILE_SIZE - MAP.TILE_SIZE / 2 - p.x;
				int _y = (point.y - _P.y + MAP.TILE_VERTICAL_INNER_NUMBER / 2)
						* MAP.TILE_SIZE - p.y;
				int size = MAP.TILE_SIZE - 1;
				int alpha = (int) (Math.floor(Math.cos(grid_time) * 100)) + 150;
				boolean flag_enemy = false;
				for (Base_Creature c : list) {
					if (c.getMassPoint().equals(point)) {
						flag_enemy = true;
						break;
					}
				}
				if (flag_enemy)
					g.setColor(Color.RED);
				else
					g.setColor(new Color(255, 255, 255, alpha));
				g.drawRect(_x, _y, size, size);
				point.translate(d.X, d.Y);
			}
		}
	}

	private static void drawGrid_Mass(Graphics2D g, Point p, Point _P) {
		for (int i = 0; i < MAP.TILE_HORIZON_INNER_NUMBER + 1; i++) {
			for (int j = 0; j < MAP.TILE_VERTICAL_INNER_NUMBER; j++) {
				int x = _P.x + i - MAP.TILE_HORIZON_INNER_NUMBER / 2;
				int y = _P.y + j - MAP.TILE_VERTICAL_INNER_NUMBER / 2;
				Mass m = MassCreater.getMass(x, y);
				if (m.WALKABLE) {
					int _x = (x - _P.x + MAP.TILE_HORIZON_INNER_NUMBER / 2)
							* MAP.TILE_SIZE - MAP.TILE_SIZE / 2 - p.x;
					int _y = (y - _P.y + MAP.TILE_VERTICAL_INNER_NUMBER / 2)
							* MAP.TILE_SIZE - p.y;
					int size = MAP.TILE_SIZE - 1;
					m.drawGrid(g, _x, _y, size);
				}
				if (MapList.isArtifact(x, y)) {
					int _x = (x - _P.x + MAP.TILE_HORIZON_INNER_NUMBER / 2)
							* MAP.TILE_SIZE - MAP.TILE_SIZE / 2 - p.x + 3;
					int _y = (y - _P.y + MAP.TILE_VERTICAL_INNER_NUMBER / 2)
							* MAP.TILE_SIZE - p.y + 3;
					int size = MAP.TILE_SIZE - 7;
					Base_Artifact a = MapList.getArtiface(x, y);
					if (isVisible(a)) {
						if (a instanceof Base_Item) {
							Color c;
							if (a.isPunishment())
								c = MiniMap.C_GOLDSTAIR;
							else if (((Base_Item) a).isPickCheked())
								c = MiniMap.C_ARTIFACT_CHECKED;
							else
								c = MiniMap.C_ARTIFACT;
							if (MassCreater.getMass(a.getMassPoint()).WATER)
								c = c.darker();
							g.setColor(c);
							g.drawOval(_x, _y, size, size);
						} else if (a instanceof Base_Trap) {
							g.setColor(MiniMap.C_TRAP);
							g.drawLine(_x, _y, _x + size, _y + size);
							g.drawLine(_x + size, _y, _x, _y + size);
						}
					}
				}
			}
		}
	}

	private static void drawGrid_old(Graphics2D g) {

		if (Scene_Action.getMe().isDirecting()) {
			g.setColor(new Color(0, 0, 0));
			for (int i = 0; i < MAP.TILE_HORIZON_INNER_NUMBER + 1; i++) {
				int x = MAP.TILE_SIZE * i - MAP.TILE_SIZE / 2;
				g.drawLine(x, 0, x, FR.SCREEN_HEIGHT);
			}
			for (int j = 0; j < MAP.TILE_VERTICAL_INNER_NUMBER; j++) {
				int y = MAP.TILE_SIZE * j;
				g.drawLine(0, y, FR.SCREEN_WIDTH, y);
			}
		}
		if (Scene_Action.getMe().isDirecting()
				|| Scene_Action.getMe().isBiasButtonPushing()) {
			grid_time += 0.3;
			int alpha = (int) (Math.floor((Math.cos(grid_time) + 1) * 90)) + 50;
			Color col = new Color(200, 200, 255, alpha);
			Color col_red = new Color(255, 0, 0, alpha);
			int x = Player.me.getMassPoint().x;
			int y = Player.me.getMassPoint().y;
			if (Scene_Action.getMe().isBiasButtonPushing()) {
				g.setColor(col);
				for (int i = 0; i < MAP.TILE_HORIZON_INNER_NUMBER + 1; i++) {
					for (int j = 0; j < MAP.TILE_VERTICAL_INNER_NUMBER; j++) {
						if (i != j + 1
								&& MAP.TILE_HORIZON_INNER_NUMBER - i != j + 1) {
							continue;
						}
						boolean flag = !isOutofPlayerSight(MapList.getEnemy(x
								+ i - 4, y + j - 3));
						if (flag) {
							g.setColor(col_red);
						}
						g.drawRect(MAP.TILE_SIZE * i - MAP.TILE_SIZE / 2,
								MAP.TILE_SIZE * j, MAP.TILE_SIZE, MAP.TILE_SIZE);
						if (flag) {
							g.setColor(col);
						}
					}
				}
			} else {
				alpha += 20;
				g.setColor(col);
				DIRECTION d = Player.me.getDirection();
				for (int i = 0; i < 5; i++) {
					boolean flag = !isOutofPlayerSight(MapList.getEnemy(x + i
							* d.X, y + i * d.Y));
					if (flag) {
						g.setColor(col_red);
					}
					g.drawRect(MAP.TILE_SIZE * (4 + i * d.X) - MAP.TILE_SIZE
							/ 2, MAP.TILE_SIZE * (3 + i * d.Y), MAP.TILE_SIZE,
							MAP.TILE_SIZE);
					if (flag) {
						g.setColor(col);
					}
				}
			}
		} else if (Scene_Action.getMe().isBiasButtonPushing()) {
			grid_time += 0.01;
			int alpha = (int) (Math.floor((Math.cos(grid_time) + 1)) * 100);
			g.setColor(new Color(0, 0, 0, alpha));
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 7; j++)
					g.drawRect(MAP.TILE_SIZE
							* (MAP.TILE_HORIZON_INNER_CENTER_ID + 1 + i)
							- MAP.TILE_SIZE / 2, MAP.TILE_SIZE
							* (MAP.TILE_VERTICAL_INNER_MIDDLE_ID + j),
							MAP.TILE_SIZE, MAP.TILE_SIZE);
		} else {
			grid_time = 0;
		}
	}

	private static void drawMap(Graphics2D g) {
		int x, y;
		int out = 0;
		x = Player.me.getPureScreenPoint().x
				+ (MAP.TILE_HORIZON_ONE_OUTSIDE_NUMBER
						- MAP.TILE_HORIZON_INNER_CENTER_ID + out)
				* MAP.TILE_SIZE - MAP.TILE_SIZE / 2;
		y = Player.me.getPureScreenPoint().y
				+ (MAP.TILE_VERTICAL_ONE_OUTSIDE_NUMBER
						- MAP.TILE_VERTICAL_INNER_MIDDLE_ID + out)
				* MAP.TILE_SIZE;
		g.drawImage(
				base_image.getSubimage(x, y, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT),
				0, 0, null);
		drawMap_Waters(g);
	}

	private static void drawMap_Waters(Graphics2D g) {
		Font font = g.getFont();
		g.setFont(font.deriveFont(50f));
		for (Mass[] ms : MassCreater.getMass()) {
			for (Mass m : ms) {
				boolean flag_A = m.WATER_LEEF;
				boolean flag_B = m.isWarning() && !m.isTrapChecked();
				if (!flag_A && !flag_B)
					continue;
				if (WithinOutofScreen.isOutside(m.X, m.Y, 50, 50))
					continue;
				if (isOutofPlayerSight(m.getPoint()))
					continue;
				Point p = ObjectPoint.getDangeonScreenPoint(m.getPoint());
				// TODO
				p = ObjectPoint.getScreenPointRelPlayer(p);
				if (m.isWarning() && flag_B) {
					int s = MAP.TILE_SIZE;
					int a = (int) (75 * Math.sin(MainThread.getFrame() * 4
							* Math.PI / 36)) + 100;
					if (m.isWarningLarge(false)) {
						g.setColor(new Color(255, 255, 0, a));
						if (m.isWarningLarge(true)) {
							// g.drawRect(p.x, p.y, s, s);
							g.drawString("！", p.x, p.y + 43);
						} else {
							g.drawRect(p.x, p.y, s, s);
							int delt = s / 4;
							g.drawRect(p.x + delt, p.y + delt, s - delt * 2, s
									- delt * 2);
							// g.drawRect(p.x + 4, p.y + 4, s - 8, s - 8);
							// g.drawRect(p.x + 1, p.y + 1, s - 2, s - 2);
						}
					} else if (m.isWarningSmall(false)) {
						g.setColor(new Color(255, 0, 0, a));
						if (m.isWarningSmall(true)) {
							// int delt = 4;
							// g.drawRect(p.x + delt / 2, p.y + delt / 2,
							// s - delt, s - delt);
							g.drawString("！", p.x, p.y + 43);
						} else {
							g.drawRect(p.x, p.y, s, s);
							int delt = s / 4;
							g.drawRect(p.x + delt, p.y + delt, s - delt * 2, s
									- delt * 2);
						}
					}
				}
				if (flag_A) {
					g.drawImage(m.FLOWER ? Image_Effect.hasu_.BI[1]
							: Image_Effect.ha_.BI[0], p.x, p.y, null);
				}
				// g.drawImage(Image_Effect.ha_.BI[0], p.x, p.y, null);
				// g.drawImage(Image_Effect.hasu_.BI[0], p.x, p.y, null);
			}
		}
		g.setFont(font);
	}

	private static void drawMapObject(Graphics2D g) {
		drawArtifacts(g, false);
		drawCreatures(g, false);
		drawArtifacts(g, true);
		if (Scene_Action.getMe().isNamePushing()) {
			drawCreatures(g, true);
		} else {
			MainMap_Name.set(false);
		}
		drawThrowing(g);
	}

	private static void drawName(Graphics2D g, Base_Creature c) {
		Point p = ObjectPoint.getScreenPointRelPlayer(c);
		int x = p.x;
		int y = p.y;
		if (c.isJumping()) {
			y += c.getJumpDY();
		}
		int size = 14;
		g.setFont(NormalFont.NORMALFONT.deriveFont((float) size));
		if (c instanceof Base_Enemy && !(c instanceof Base_NPC)) {
			g.setColor(Color.WHITE);
			String lv = c.getLV() == 4 ? "ANOTHER" : "Lv".concat(半角全角コンバーター
					.半角To全角数字(c.getLV()));
			StringFilter.drawEdgedString_plain(g, lv, x, y + c.getFootY()
					+ MAP.TILE_SIZE - (size + 1) / 3);
		}
		g.setColor(Color.WHITE);
		StringFilter.drawEdgedString(g, c.getColoredName(), x, y + c.getFootY()
				+ MAP.TILE_SIZE + size + 1 - (size + 1) / 3);
		g.setFont(NormalFont.NORMALFONT);
	}

	private static void drawShadow(Graphics2D g, int width, int x, int y,
			int y_dif) {
		drawShadow(g, width, x, y, y_dif, false);
	}

	public static void drawShadow(Graphics2D g, int width, int x, int y,
			int y_dif, Base_Creature c) {
		if (c.isBeatedAway())
			return;
		int index;
		if (c.conditionCheck(CONDITION.邪法)
				&& MainThread.getFrame() / 3 % 2 == 0)
			index = 1;
		else if (c.getPowerUp() == 1) {
			Color color = new Color(255, 0, 0);
			if (MainThread.getFrame() / 6 % 2 == 0) {
				color = new Color(150, 0, 0);
			} else {
				color = new Color(255, 0, 0);
			}
			drawShadow(g, width, x, y, y_dif, false, color);
			return;
		} else if (c.getPowerUp() > 1) {
			Color color = new Color(255, 0, 0);
			if (MainThread.getFrame() / 6 % 2 == 0) {
				color = new Color(0, 0, 150);
			} else {
				color = new Color(0, 0, 255);
			}
			drawShadow(g, width, x, y, y_dif, false, color);
			return;
		} else {
			drawShadow(g, width, x, y, y_dif, false, shadow[0]);
			return;
		}

		drawShadow(g, width, x, y, y_dif, false, shadow[index]);
	}

	public static void drawShadow(Graphics2D g, int width, int x, int y,
			int y_dif, boolean flat) {
		if (width <= 0)
			return;
		drawShadow(g, width, x, y, y_dif, flat, shadow[0]);
	}

	public static void drawShadow(Graphics2D g, int width, int x, int y,
			int y_dif, boolean flat, Color c) {
		int height = (flat) ? width : width / 2;
		g.setColor(c);
		g.fillOval(x + MAP.TILE_SIZE / 2 - width / 2, y - height / 2
				+ MAP.Y_OFFSET - y_dif, width, height);
	}

	private static void drawSpot(Graphics2D g) {
		// boolean flag_darkness = Player.me.conditionCheck(CONDITION.やりすごし);
		if (!MassCreater.isSpotValid()) {
			return;
		}
		Area mask = (Area) SCREEN.clone();
		if (MassCreater.isPlayerInRoom()) {
			Room r = MassCreater.getPlayerRoom();
			r.LIGHT.x = (MAP.TILE_HORIZON_INNER_CENTER_ID - 1 - (Player.me
					.getMassPoint().x - r.X))
					* MAP.TILE_SIZE
					+ MAP.TILE_SIZE
					- MAP.TILE_SIZE
					/ 4
					+ ObjectPoint.getDifferenceBetweenMass_Screen(Player.me).x;
			r.LIGHT.y = (MAP.TILE_VERTICAL_INNER_MIDDLE_ID - 1 - (Player.me
					.getMassPoint().y - r.Y))
					* MAP.TILE_SIZE
					+ MAP.TILE_SIZE
					/ 2
					- MAP.TILE_SIZE
					/ 4
					+ ObjectPoint.getDifferenceBetweenMass_Screen(Player.me).y;
			mask.subtract(new Area(r.LIGHT));
		}
		int radius = MAP.TILE_SIZE;
		Ellipse2D.Double spot = new Ellipse2D.Double(FR.SCREEN_WIDTH / 2
				- radius, FR.SCREEN_HEIGHT / 2 - radius, radius * 2, radius * 2);
		mask.subtract(new Area(spot));
		g.setColor(new Color(0, 0, 0, 100));
		g.fill(mask);
	}

	private static void drawThrowing(Graphics2D g) {
		if (!TaskOnMapObject.isThrowTaskEmpty()) {
			if (TaskOnMapObject.getThrow().isFirstSleeping()) {
				return;
			}
			Base_Artifact a = TaskOnMapObject.getThrow().A;
			Point p = ObjectPoint.getScreenPointRelPlayer(a);
			Image im = a.getImage(a.direction);
			int x = p.x - (im.getWidth(null) - MAP.TILE_SIZE) / 2;
			int y = p.y - (im.getHeight(null) - MAP.TILE_SIZE) / 2;
			if (!Config.isNoShadow() && a.getShadow() > 0) {
				int size = 12;
				int awa = a.jumped_away_y;
				if (a.isJumping()) {
					size = size + awa / 3;
				}
				drawShadow(g, size, p.x, y + 5, a.getShadow());
			}
			if (a instanceof Base_Bullet || a instanceof Arrow)
				g.drawImage(im, x, y - 10, null);
			else {
				int w = im.getWidth(null);
				int h = im.getHeight(null);
				double theta = MainThread.getFrame();
				AffineTransform firts_at = g.getTransform();
				AffineTransform at = new AffineTransform(firts_at);
				at.translate(x + w / 2, y - 10 + h / 2);
				at.rotate(Math.PI * theta / 4);
				g.setTransform(at);
				g.drawImage(im, -w / 2, -h / 2, null);
				g.setTransform(firts_at);

			}
			// g.drawImage(a.getImage(a.direction), p.x - a.direction.X
			// * MAP.TILE_SIZE / 2, p.y - a.direction.Y * MAP.TILE_SIZE
			// / 2, null);
		}
	}

	public static void drawWall(Graphics2D g, Image[] wall_img, int draw_x,
			int draw_y) {
		int count = 0;
		for (int k = 0; k < 2; k++) {
			for (int l = 0; l < 2; l++) {
				g.drawImage(wall_img[count], draw_x + (MAP.TILE_SIZE / 2 * k),
						draw_y + (MAP.TILE_SIZE / 2 * l), null);
				count++;
			}
		}
	}

	private static Image[] getDirectionWall(Image_MapTip map_tip, Mass mass) {
		Image[] wall_img = new Image[4];
		int count = 0;
		for (int k = 0; k < 2; k++)
			for (int l = 0; l < 2; l++) {
				wall_img[count] = map_tip.getWall(10 * count
						+ wall_map.get(mass.getWallDIRECION(count)));
				count++;
			}
		return wall_img;
	}

	public static Image[] getPlainWall(Image_MapTip map_tip) {
		Image[] wall_img = new Image[4];
		for (int k = 0; k < wall_img.length; k++)
			wall_img[k] = map_tip.getWall(10 * k
					+ MainMap.wall_map.get(EX_DIRECTION.NEUTRAL));
		return wall_img;
	}

	private static void ignoreCreatureForNameplate(List<Base_Creature> list) {
		// int shigt = Player.me.conditionCheck(CONDITION.蛍) ? 2 : 1;
		for (Iterator<Base_Creature> i = list.iterator(); i.hasNext();) {
			Base_Creature c = i.next();
			Point p = ObjectPoint.getScreenPointRelPlayer(c);
			int x = p.x;
			int y = p.y;
						
			if (WithinOutofScreen.isOutside(x, y, c.getImage().getWidth(null),
					c.getImage().getHeight(null))) {
				i.remove();
				continue;
			}
			if (isOutofPlayerSight(c) && c.getAnimation() == null) {
				i.remove();
				continue;
			}
		}
	}
	
	private static void ignoreCreature(List<Base_Creature> list,
			List<Base_Creature> jumping_list) {
		// int shigt = Player.me.conditionCheck(CONDITION.蛍) ? 2 : 1;
		for (Iterator<Base_Creature> i = list.iterator(); i.hasNext();) {
			Base_Creature c = i.next();
			Point p = ObjectPoint.getScreenPointRelPlayer(c);
			int x = p.x;
			int y = p.y;
			if (c.conditionCheck(CONDITION.透明) && !c.isBeatedAway()) {
				if (!(c instanceof Player)) {
					if (!Player.me.conditionCheck(CONDITION.目薬)
							&& !EnchantSpecial
									.enchantSimbolAllCheck(ENCHANT_SIMBOL.識)) {
						c.skipDrawFrame();
						i.remove();
						continue;
					}
				}
			}
			if (c.isJumping()) {
				if (jumping_list != null)
					jumping_list.add(c);
				i.remove();
				continue;
			}
			if (WithinOutofScreen.isOutside(x, y, c.getImage().getWidth(null),
					c.getImage().getHeight(null))) {
				i.remove();
				c.skipDrawFrame();
				continue;
			}
			if (isOutofPlayerSight(c) && c.getAnimation() == null) {
				i.remove();
				c.skipDrawFrame();
				continue;
			}
		}
	}

	public static boolean isOutofPlayerSight(Base_Creature c) {
		if (c == null) {
			return true;
		}
		return isOutofPlayerSight(c.getMassPoint());
	}

	public static boolean isOutofPlayerSight(Point p) {
		if (MassCreater.isSpotValid() && !EnchantSpecial.toushi()
				&& !Player.me.conditionCheck(CONDITION.蛍)) {
			if (!MassCreater.isThePointInThePlayerSight(p, 1)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isVisible(Base_Artifact a) {
		if (a.isHidden())
			return false;
		Mass m = MassCreater.getMass(a.getMassPoint());
		if (WithinOutofScreen.isOutside(a)) {
			return false;
		}
		if (a.isAnimating())
			return true;
		if (MassCreater.isSpotValid() && !m.isVisible()) {
			return false;
		}
		if (!a.isVisible()) {
			if (!(a instanceof Base_Device)) {
				if (!Player.me.conditionCheck(CONDITION.目薬)
						&& !印罠師.effect()
						|| EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
								ENCHANT_SIMBOL.探知)) {
					// 不可視なアイテムかトラップでかつ目薬状態でないなら見えない
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * called from MassCreater.retakeMassSet
	 */
	public static void requestForUpdate() {
		setBase();
		MiniMap.reset();
	}

	public static void setAnimation(Base_Creature c) {
		animating_creature_list.add(c);
	}

	private static void setBase() {
		int out_hr = MAP.TILE_HORIZON_ONE_OUTSIDE_NUMBER;
		int out_vt = MAP.TILE_VERTICAL_ONE_OUTSIDE_NUMBER;
		int w = (MassCreater.WIDTH + out_hr * 2) * MAP.TILE_SIZE;
		int h = (MassCreater.HEIGHT + out_vt * 2) * MAP.TILE_SIZE;
		base_image = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = base_image.createGraphics();
		g.setColor(Color.RED);
		g.fillRect(0, 0, w, h);
		Image[] wall_img = new Image[4];
		Image_MapTip map_tip = PresentField.get().getMapTip();
		for (int x = 0; x < MassCreater.WIDTH + out_hr * 2; x++) {
			for (int y = 0; y < MassCreater.HEIGHT + out_vt * 2; y++) {
				int draw_x = MAP.TILE_SIZE * x;
				int draw_y = MAP.TILE_SIZE * y;
				if (x < out_hr || x >= MassCreater.WIDTH + out_hr || y < out_vt
						|| y >= MassCreater.WIDTH + out_vt) {
					wall_img = getPlainWall(map_tip);
				} else {
					try {
						Mass mass = MassCreater.getMass()[x - out_hr][y
								- out_vt];
						if (mass.WALKABLE) {
							g.drawImage(
									mass.HAP ? map_tip.getHap() : map_tip
											.getFloor(), draw_x, draw_y, null);
							continue;
						}
						wall_img = getDirectionWall(map_tip, mass);
					} catch (ArrayIndexOutOfBoundsException e) {
						wall_img = getPlainWall(map_tip);
					}
				}
				drawWall(g, wall_img, draw_x, draw_y);
			}
		}

		for (int x = 0; x < MassCreater.WIDTH; x++) {
			for (int y = 0; y < MassCreater.HEIGHT; y++) {
				int draw_x = MAP.TILE_SIZE * (x + out_hr);
				int draw_y = MAP.TILE_SIZE * (y + out_vt);
				try {
					Mass mass = MassCreater.getMass()[x][y];
					if (mass.isWater_or_WaterLeef())
						map_tip = Image_MapTip.water;
					else if (mass.WATER_FROZEN)
						map_tip = Image_MapTip.water_ice;
					else
						continue;
					wall_img = getDirectionWall(map_tip, mass);
					// drawWall(g, wall_img, draw_x, draw_y);
					// if (mass.isWater_or_WaterLeef() && mass.HAP) {
					// g.drawImage(Image_Effect.ha_.BI[0], draw_x, draw_y,
					// null);
					// }
				} catch (ArrayIndexOutOfBoundsException e) {
					wall_img = getPlainWall(map_tip);
				}
				drawWall(g, wall_img, draw_x, draw_y);
			}
		}
		g.dispose();
	}

}
