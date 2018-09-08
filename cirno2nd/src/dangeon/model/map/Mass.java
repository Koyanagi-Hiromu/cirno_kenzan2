package dangeon.model.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

import main.res.SE;
import main.util.DIRECTION;
import main.util.EX_DIRECTION;
import main.util.Show;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印罠師;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.守矢賽銭箱;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.MiniMap;

public class Mass implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible = false, digged, holy, shop;
	private int warning;
	public final boolean HAP;
	public boolean ROOM, WALKABLE, ROAD, DIGGABLE, WATER;
	/**
	 * 水路の属性
	 */
	public boolean WATER_NOT_FROZEN, WATER_LEEF, WATER_FROZEN;
	public boolean FLOWER;
	public final int X, Y;
	public Point null_point;
	/**
	 * X = -10; Y = -10; WALKABLE = false; ROOM = false; ROAD = false;
	 * 
	 */
	public static Mass nullpo = new Mass();
	private EX_DIRECTION u_l = null, u_r = null, d_l = null, d_r = null;

	private Base_MapObject map_object_in_wall = null;

	private boolean trap_checked;

	private int restore_water_leef_count;

	private boolean flag_island = false;

	Mass() {
		X = -10;
		Y = -10;
		null_point = new Point(X, Y);
		WALKABLE = false;
		ROOM = false;
		ROAD = false;
		DIGGABLE = false;
		HAP = false;
	}

	Mass(int x, int y, char symbol) {
		this.X = x;
		this.Y = y;
		if (x == 0 || x >= MassCreater.WIDTH - 1 || y == 0
				|| y >= MassCreater.HEIGHT - 1) {
			WALKABLE = false;
			DIGGABLE = false;
			ROOM = false;
			ROAD = false;
			WATER = false;
		} else {
			WALKABLE = true;
			DIGGABLE = true;
			ROOM = false;
			ROAD = false;
			WATER = false;
			switch (symbol) {
			case '壁':
				WALKABLE = false;
				DIGGABLE = (PresentField.get().isDiggable());
				break;
			case '■':
				WALKABLE = false;
				DIGGABLE = false;
				break;
			case '　':
				ROOM = true;
				break;
			case '☆':
				ROAD = true;
				break;
			case '水':
				WATER = true;
				ROAD = true;
				break;
			default:
				Show.showCriticalErrorMessageDialog("SyntacError : mapファイルは半角のアルファベットと一部記号（\\.*%+#）のみを許可しています。 => "
						.concat(Character.toString(symbol)));
			}

		}
		HAP = Math.floor((new R().nextDouble() * 20)) % 20 == 0;
		trap_checked = 印罠師.effect();
	}

	public void drawGrid(Graphics2D g, int x, int y, int size) {
		if (WATER) {
			g.setColor(new Color(100, 150, 200));
			g.drawRoundRect(x + 2, y + 2, size - 4, size - 4, size / 8,
					size / 8);
		} else
			drawGrid_WALKABLE(g, x, y, size);
	}

	private void drawGrid_WALKABLE(Graphics2D g, int x, int y, int size) {
		g.setColor(new Color(160, 170, 130));
		g.drawRect(x + 2, y + 2, size - 4, size - 4);

	}

	/**
	 * call MassCreater.retakeMassSet();
	 * 
	 * @param on
	 */
	public void forceToWater(boolean on) {
		WATER = on;
		if (on) {
			setUnWarning();
			MapList.water(MapList.getArtiface(X, Y));
		} else {
			WATER_FROZEN = false;
			WATER_LEEF = false;
			WATER_NOT_FROZEN = false;
		}
		WALKABLE = false;
		setDigged(true);
	}

	public Mass get(DIRECTION d) {
		return MassCreater.getMass(X + d.X, Y + d.Y);
	}

	public Point getPoint() {
		return new Point(X, Y);
	}

	public String getView() {
		String mass = "";
		if (!visible)
			return mass;
		if (WALKABLE) {
			mass = "■";
		}
		return mass;
	}

	public EX_DIRECTION getWallDIRECION(int index) {
		switch (index) {
		case 0:
			return u_l;
		case 1:
			return d_l;
		case 2:
			return u_r;
		case 3:
			return d_r;
		}
		return null;
	}

	public boolean isAbleToExistArtifact() {
		return WALKABLE && !WATER;
	}

	private boolean isAnyWater_Leef_Frozen() {
		return WATER || WATER_LEEF || WATER_FROZEN;
	}

	public boolean isDiggable() {
		return DIGGABLE;
	}

	public boolean isDigged() {
		return digged;
	}

	public boolean isHoly() {
		return holy;
	}

	public boolean isIsLand() {
		return flag_island;
	}

	public boolean isNotPermittedToExistTrapAppearently() {
		if (!this.WALKABLE || this.WATER || this.WATER_LEEF)
			return true;
		for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
			if (MassCreater.getMass(X + d.X, Y + d.Y).ROAD) {
				return true;
			}
		}
		return false;
	}

	public boolean isObjectInWall() {
		return map_object_in_wall != null;
	}

	public boolean isRoom() {
		return ROOM;
	}

	public boolean isShop() {
		return shop;
	}

	public boolean isTrapChecked() {
		return trap_checked;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isWalkable() {
		return WALKABLE;
	}

	public boolean isWarning() {
		return warning != 0;
	}

	public boolean isWarningLarge(boolean main) {
		if (main)
			return warning == 2;
		else
			return warning > 0;
	}

	public boolean isWarningSmall(boolean main) {
		if (main)
			return warning == -2;
		else
			return warning < 0;
	}

	public boolean isWater_or_WaterLeef() {
		return WATER || WATER_LEEF;
	}

	public void setChecked(boolean b) {
		trap_checked = b;
	}

	public void setDigged(boolean f) {
		if (f) {
			if (WALKABLE) {
				return;
			} else if (DIGGABLE) {
				tellBroken();
				Room room = MassCreater.getRoom(X, Y);
				if (room == null) {
					ROOM = false;
					ROAD = true;
				} else {
					ROOM = true;
					ROAD = false;
				}
				WALKABLE = true;
				e: for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (MassCreater.getMass(X + i, Y + j).isVisible()) {
							visible = true;
							break e;
						}
					}
				}
			} else {
				return;
			}
		}
		digged = f;
	}

	private EX_DIRECTION setDOWN_LEFT(boolean RIGHT, boolean LEFT,
			boolean DOWN, boolean UP, boolean ANGLE) {
		if (DOWN && LEFT)
			return EX_DIRECTION.DOWN_LEFT;
		if (LEFT) {
			if (UP)
				return EX_DIRECTION.UP_LEFT;
			return EX_DIRECTION.LEFT;
		}
		if (DOWN) {
			if (RIGHT)
				return EX_DIRECTION.DOWN_RIGHT;
			return EX_DIRECTION.DOWN;
		}
		if (ANGLE)
			return EX_DIRECTION.ELSE;
		if (UP && RIGHT)
			return EX_DIRECTION.UP_RIGHT;
		if (RIGHT)
			return EX_DIRECTION.RIGHT;
		if (UP)
			return EX_DIRECTION.UP;
		return EX_DIRECTION.NEUTRAL;
	}

	private EX_DIRECTION setDOWN_RIGHT(boolean LEFT, boolean RIGHT, boolean UP,
			boolean DOWN, boolean ANGLE) {
		if (DOWN && RIGHT)
			return EX_DIRECTION.DOWN_RIGHT;
		if (RIGHT) {
			if (UP)
				return EX_DIRECTION.UP_RIGHT;
			return EX_DIRECTION.RIGHT;
		}
		if (DOWN) {
			if (LEFT)
				return EX_DIRECTION.DOWN_LEFT;
			return EX_DIRECTION.DOWN;
		}
		if (ANGLE)
			return EX_DIRECTION.ELSE;
		if (UP && LEFT)
			return EX_DIRECTION.UP_LEFT;
		if (LEFT)
			return EX_DIRECTION.LEFT;
		if (UP)
			return EX_DIRECTION.UP;
		return EX_DIRECTION.NEUTRAL;
	}

	/**
	 * 
	 * @param b
	 * <br/>
	 *            true　→　水路を凍らす<br/>
	 *            false　→　水路を溶かす
	 */
	public void setFrozen(boolean b) {
		// if (WATER_NOT_FROZEN)
		// return;
		if (b == isWater_or_WaterLeef()) {
			setWater(!b);
			WATER_FROZEN = b;
			WATER_LEEF = false;
			
			if (WATER_FROZEN && EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.冴)) {
				SE.LIGHT_ON.play();
				Scene_Action.getMe().tellRestStop();
				Player.me.setCondition(CONDITION.目薬, 0);
			}
		}
	}

	public void setFrozen_includingNeibors() {
		setFrozen(true);
		for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
			Mass m = MassCreater.getMass(getPoint(), d);
			if (m.isWater_or_WaterLeef())
				m.setFrozen_includingNeibors();
		}
	}

	public void setHoly(boolean f) {
		holy = f;
	}

	public void setIsLand(boolean b) {
		flag_island = b;
	}

	public void setLeef() {
		if (new R().is(10) && !MapList.isCreature(getPoint())) {
			WATER = false;
			WATER_LEEF = true;
			if (new R().is(10)) {
				FLOWER = true;
			}
		} else {
			WATER_LEEF = false;
		}
	}

	public void setNonFrozenWater() {
		WATER_NOT_FROZEN = true;
		setWater(true);
	}

	public void setObjectInWall(Base_MapObject mapObject) {
		if (WALKABLE) {
			// Show.showErrorMessageDialog("ここは壁ではありません");
		} else if (isObjectInWall()) {
			// Show.showErrorMessageDialog("既にオブジェクトが埋まっています");
		} else {
			map_object_in_wall = mapObject;
		}
	}

	public void setShop(boolean b) {
		shop = b;
	}

	public void setUnWarning() {
		if (isWarning() && !isWarningLarge(true) && !isWarningSmall(true)) {
			warning = 0;
			MassCreater.retakeMassSet();
		}
	}

	private EX_DIRECTION setUP_LEFT(boolean RIGHT, boolean LEFT, boolean UP,
			boolean DOWN, boolean ANGLE) {
		if (UP && LEFT)
			return EX_DIRECTION.UP_LEFT;
		if (LEFT) {
			if (DOWN)
				return EX_DIRECTION.DOWN_LEFT;
			return EX_DIRECTION.LEFT;
		}
		if (UP) {
			if (RIGHT)
				return EX_DIRECTION.UP_RIGHT;
			return EX_DIRECTION.UP;
		}
		if (ANGLE)
			return EX_DIRECTION.ELSE;
		if (DOWN && RIGHT)
			return EX_DIRECTION.DOWN_RIGHT;
		if (RIGHT)
			return EX_DIRECTION.RIGHT;
		if (DOWN)
			return EX_DIRECTION.DOWN;
		return EX_DIRECTION.NEUTRAL;
	}

	private EX_DIRECTION setUP_RIGHT(boolean LEFT, boolean RIGHT, boolean UP,
			boolean DOWN, boolean ANGLE) {
		if (UP && RIGHT)
			return EX_DIRECTION.UP_RIGHT;
		if (RIGHT) {
			if (DOWN)
				return EX_DIRECTION.DOWN_RIGHT;
			return EX_DIRECTION.RIGHT;
		}
		if (UP) {
			if (LEFT)
				return EX_DIRECTION.UP_LEFT;
			return EX_DIRECTION.UP;
		}
		if (ANGLE)
			return EX_DIRECTION.ELSE;
		if (DOWN && LEFT)
			return EX_DIRECTION.DOWN_LEFT;
		if (LEFT)
			return EX_DIRECTION.LEFT;
		if (DOWN)
			return EX_DIRECTION.DOWN;
		return EX_DIRECTION.NEUTRAL;
	}

	public void setVisible(boolean f) {
		visible = f;
	}

	private void setWall() {
		Mass[][] mass = MassCreater.getMass();
		boolean LEFT = false, RIGHT = false, UP = false, DOWN = false;
		try {
			LEFT = mass[X - 1][Y].WALKABLE;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			RIGHT = mass[X + 1][Y].WALKABLE;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			UP = mass[X][Y - 1].WALKABLE;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			DOWN = mass[X][Y + 1].WALKABLE;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			u_r = setUP_RIGHT(LEFT, RIGHT, UP, DOWN,
					mass[X + 1][Y - 1].WALKABLE);
		} catch (ArrayIndexOutOfBoundsException e) {
			u_r = setUP_RIGHT(LEFT, RIGHT, UP, DOWN, false);
		}
		try {
			u_l = setUP_LEFT(RIGHT, LEFT, UP, DOWN, mass[X - 1][Y - 1].WALKABLE);
		} catch (ArrayIndexOutOfBoundsException e) {
			u_l = setUP_LEFT(RIGHT, LEFT, UP, DOWN, false);
		}
		try {
			d_r = setDOWN_RIGHT(LEFT, RIGHT, UP, DOWN,
					mass[X + 1][Y + 1].WALKABLE);
		} catch (ArrayIndexOutOfBoundsException e) {
			d_r = setDOWN_RIGHT(LEFT, RIGHT, UP, DOWN, false);
		}
		try {
			d_l = setDOWN_LEFT(RIGHT, LEFT, DOWN, UP,
					mass[X - 1][Y + 1].WALKABLE);
		} catch (ArrayIndexOutOfBoundsException e) {
			d_l = setDOWN_LEFT(RIGHT, LEFT, DOWN, UP, false);
		}
	}

	void setWallTileNo() {
		// TODO DELETE WATER_FROZEN
		if (WATER || WATER_FROZEN || WATER_LEEF) {
			setWater();
		} else if (!WALKABLE) {
			setWall();
		}
	}

	public void setWarningLarge(boolean b) {
		if (b) {
			if (!isWarningLarge(false))
				warning = 2;
		} else if (!isNotPermittedToExistTrapAppearently()) {
			warning = 1;
		}
	}

	public void setWarningSmall(boolean b) {
		if (b) {
			if (!isWarningSmall(false))
				warning = -2;
		} else if (!isNotPermittedToExistTrapAppearently()) {
			warning = -1;
		}
	}

	private void setWater() {
		ROOM = MassCreater.getRoom(X, Y) != null;
		Mass[][] mass = MassCreater.getMass();
		boolean LEFT = false, RIGHT = false, UP = false, DOWN = false;
		try {
			LEFT = !mass[X - 1][Y].isAnyWater_Leef_Frozen();
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			RIGHT = !mass[X + 1][Y].isAnyWater_Leef_Frozen();
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			UP = !mass[X][Y - 1].isAnyWater_Leef_Frozen();
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			DOWN = !mass[X][Y + 1].isAnyWater_Leef_Frozen();
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			u_r = setUP_RIGHT(LEFT, RIGHT, UP, DOWN,
					!mass[X + 1][Y - 1].isAnyWater_Leef_Frozen());
		} catch (ArrayIndexOutOfBoundsException e) {
			u_r = setUP_RIGHT(LEFT, RIGHT, UP, DOWN, false);
		}
		try {
			u_l = setUP_LEFT(RIGHT, LEFT, UP, DOWN,
					!mass[X - 1][Y - 1].isAnyWater_Leef_Frozen());
		} catch (ArrayIndexOutOfBoundsException e) {
			u_l = setUP_LEFT(RIGHT, LEFT, UP, DOWN, false);
		}
		try {
			d_r = setDOWN_RIGHT(LEFT, RIGHT, UP, DOWN,
					!mass[X + 1][Y + 1].isAnyWater_Leef_Frozen());
		} catch (ArrayIndexOutOfBoundsException e) {
			d_r = setDOWN_RIGHT(LEFT, RIGHT, UP, DOWN, false);
		}
		try {
			d_l = setDOWN_LEFT(RIGHT, LEFT, DOWN, UP,
					!mass[X - 1][Y + 1].isAnyWater_Leef_Frozen());
		} catch (ArrayIndexOutOfBoundsException e) {
			d_l = setDOWN_LEFT(RIGHT, LEFT, DOWN, UP, false);
		}
	}

	/**
	 * call MassCreater.retakeMassSet();
	 * 
	 * @param on
	 */
	public void setWater(boolean on) {
		if (!DIGGABLE)
			return;
		WATER = on;
		if (on) {
			setUnWarning();
			MapList.water(MapList.getArtiface(X, Y));
		}
		if (on && !WATER_NOT_FROZEN) {
			setLeef();
		}
		WALKABLE = false;
		setDigged(true);
	}

	public void setWaterLeefOn(boolean walk_off) {
		forceToWater(walk_off);
		WATER_LEEF = !walk_off;
		if (walk_off) {
			int x = new R().nextInt(20) + 2;
			restore_water_leef_count = (int) (Math.pow(10, -1.0 / x) * 10) - 1;
			Base_Artifact a = MapList.getArtifact(getPoint());
			if (a != null)
				a.waterAction();
		} else {
			restore_water_leef_count = 0;
			FLOWER = false;
		}
		MiniMap.reset();
	}

	public void setWaterWithAnimation(final boolean on, final boolean last) {
		if (WATER != on) {
			SE.ATTACK_WATER.play();
			MainMap.addEffect(new DoronEffect(new Point(X, Y), null, true,
					false), true);
			forceToWater(on);
			if (last)
				MassCreater.retakeMassSet();
		}
	}

	public void stepEnd() {
		if (restore_water_leef_count > 0 && --restore_water_leef_count == 0) {
			if (WATER)
				setWaterLeefOn(false);
		}
	}

	public void tellBroken() {
		if (isObjectInWall()) {
			if (map_object_in_wall instanceof Base_Artifact) {
				MapList.addArtifact((Base_Artifact) map_object_in_wall);
			} else if (map_object_in_wall instanceof Base_Enemy) {
				if (map_object_in_wall instanceof 守矢賽銭箱) {
					SE.FANFARE1.play();
					Medal.壁の中から賽銭箱を発見した.addCount();
				}
				MapList.addEnemy((Base_Enemy) map_object_in_wall);
			}
		}
	}
}
