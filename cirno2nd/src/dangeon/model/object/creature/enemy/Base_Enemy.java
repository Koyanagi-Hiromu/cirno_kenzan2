package dangeon.model.object.creature.enemy;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.Image_LargeCharacter;
import main.res.SE;
import main.util.CSVLoadSupporter;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.condition.Confusion;
import dangeon.model.config.Config;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.view.anime.OuraEffect;
import dangeon.view.constant.MAP;

public abstract class Base_Enemy extends Base_Creature {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int largement;

	/**
	 * 部屋内の通路の数を保持、および決定に使う
	 */
	protected List<Point> list_room_in_root = new ArrayList<Point>();

	/**
	 * 移動待ちのエネミーの許可
	 */
	public static boolean enemy_move_wait = false;

	/**
	 * 行動前、行動後を設定する
	 */
	public boolean enemy_actioned = false;

	public boolean enemy_condition_actioned = false;

	public boolean player_is_in_sight = false;

	// Mass[][] mass = MassCreater.getMass();
	Mass[][] mass;

	protected int count_sight = 0;

	private Point player_back_point = Mass.nullpo.null_point;

	/**
	 * 特殊行動をチェックするのは一ターンに一回
	 */
	protected boolean special_possible_check = false;

	/**
	 * 特殊行動を発動予約
	 */
	public boolean special_ok = false;
	public boolean flag_move = false;
	public boolean flag_2nd_move, flag_3rd_move;

	private Point back_point = new Point(0, 0);
	private Point root = new Point();
	Point kaguya = new Point(-10, -10);
	private int move_limit = 0;
	/**
	 * クリアするタイミングは通路に入った瞬間 ワープした瞬間
	 */
	private List<Point> list_road = new ArrayList<Point>();
	public int turn_count = 0;
	private int count_max = 3;

	private boolean random;

	private boolean flag_skill_checking_now;

	private static String color_name[] = { Color.PINK.toString(),
			Color.PINK.toString(), new Color(255, 100, 100).toString(),
			Color.RED.toString() }, color_white = Color.WHITE.toString();

	private String explain_hold_action;
	private SE attack_se;

	private String tati_e;

	private int SIZE_HP, SIZE_STR;

	public Base_Enemy(Point p, int LV) {
		super(p, "_NO_DATA_", LV);
		massCheck();
	}

	public Base_Enemy(Point p, String name, int LV) {
		super(p, name, LV);
		massCheck();
	}

	public void action() {
		if (special_ok) {
			return;
		}
		playerSearch();
		if (!special_possible_check && isSkillActive()
				&& !Player.me.getConditionList().contains(CONDITION.やりすごし)
				&& !Player.me.getConditionList().contains(CONDITION.暗闇)
				&& !Player.me.getConditionList().contains(CONDITION.透明)) {
			special_possible_check = true;
			if (specialCheck()) {
				special_ok = true;
				return;
			}
		}
		enemyMovePossible();
	}

	public final void attack() {
		normalAttack(false);
	}

	/**
	 * 攻撃可能ならば、trueを返す
	 * 
	 * @return
	 */
	protected final boolean attack_possible() {
		if (CONDITION.enemyDontMove(this)) {
			return false;
		} else if (isPlayerSide()) {
			return false;
		} else if (isAbleToAttackWhileNeglectingWall()) {
			return playerIsInNeiver();
		} else {
			return attack_possible_Normally();
		}
	}

	private boolean attack_possible_Normally() {
		Point p = Player.me.getMassPoint();
		if (playerIsInNeiver()) {
			if (!MassCreater.getMass(p).WALKABLE) {
				return false;
			}
			if (getMassPoint().x < p.x && getMassPoint().y < p.y) {
				if (!mass[getMassPoint().x + 1][getMassPoint().y].WALKABLE
						|| !mass[getMassPoint().x][getMassPoint().y + 1].WALKABLE) {
					return false;
				}
			} else if (getMassPoint().x < p.x && getMassPoint().y > p.y) {
				if (!mass[getMassPoint().x + 1][getMassPoint().y].WALKABLE
						|| !mass[getMassPoint().x][getMassPoint().y - 1].WALKABLE) {
					return false;
				}
			} else if (getMassPoint().x > p.x && getMassPoint().y < p.y) {
				if (!mass[getMassPoint().x - 1][getMassPoint().y].WALKABLE
						|| !mass[getMassPoint().x][getMassPoint().y + 1].WALKABLE) {
					return false;
				}
			} else if (getMassPoint().x > p.x && getMassPoint().y > p.y) {
				if (!mass[getMassPoint().x - 1][getMassPoint().y].WALKABLE
						|| !mass[getMassPoint().x][getMassPoint().y - 1].WALKABLE) {
					return false;
				}
			}

			return true;
		}
		return false;
	}

	public void attackOrSpecial() {
		if (!CONDITION.isMovable(this)) {
			enemy_actioned = true;
			return;
		}
		if (conditionChecker()) {
			enemy_actioned = true;
			return;
		}
		if (enemy_condition_actioned) {
			enemy_actioned = true;
			return;
		}
		if (special_ok) {
			if (isSkillActive()) {
				boolean flag_ok = false;
				flag_skill_checking_now = true;
				playerSearch();
				if (specialCheck()) {
					flag_ok = true;
				}
				flag_skill_checking_now = false;
				if (flag_ok && specialAttack()) {
					enemy_actioned = true;
					return;
				}
			}
		}
		if (attack_possible()) {
			attack();
		}
		enemy_actioned = true;
	}

	/**
	 * 
	 * @param base_Creature
	 *            対象
	 * @return
	 */
	public boolean attackPossibleFromEnemy(Base_Creature base_Creature) {
		Point p = base_Creature.getMassPoint();
		if (getMassPoint().x < p.x && getMassPoint().y < p.y) {
			if (!mass[getMassPoint().x + 1][getMassPoint().y].WALKABLE
					|| !mass[getMassPoint().x][getMassPoint().y + 1].WALKABLE) {
				return false;
			}
		} else if (getMassPoint().x < p.x && getMassPoint().y > p.y) {
			if (!mass[getMassPoint().x + 1][getMassPoint().y].WALKABLE
					|| !mass[getMassPoint().x][getMassPoint().y - 1].WALKABLE) {
				return false;
			}
		} else if (getMassPoint().x > p.x && getMassPoint().y < p.y) {
			if (!mass[getMassPoint().x - 1][getMassPoint().y].WALKABLE
					|| !mass[getMassPoint().x][getMassPoint().y + 1].WALKABLE) {
				return false;
			}
		} else if (getMassPoint().x > p.x && getMassPoint().y > p.y) {
			if (!mass[getMassPoint().x - 1][getMassPoint().y].WALKABLE
					|| !mass[getMassPoint().x][getMassPoint().y - 1].WALKABLE) {
				return false;
			}
		} else {
			return true;
		}
		return false;
	}

	// バトル時のカウンターアクション
	// @overrideしてキャラ
	public void battleCounterAction() {
	}

	/**
	 * 分岐路
	 */
	private void branch() {
		Point point = getMassPoint();
		List<Point> list = new ArrayList<Point>();
		if (rootIsAround()) {
			if (movePoint(player_back_point, false, false)) {
				if (point.equals(player_back_point)) {
					setPlayerBackPoint(Mass.nullpo.null_point);
				}
				return;
			} else {
				moveStopCount();
				return;
			}
		} else {
			for (Point p : list_road) {
				if (p.x == back_point.x && p.y == back_point.y) {
					continue;
				}
				list.add(p);
			}
		}
		int select = new R().nextInt(list.size());
		System.out.println(list);
		Point p = list.get(select);
		if (MapList.getCreature(p) == null && movable_water_check(p)) {
			enemy_move(p.x - point.x, p.y - point.y, converDirection(p));
			return;
		}
		list.remove(p);
		int select_second = new R().nextInt(list.size());
		p = list.get(select_second);
		if (MapList.getCreature(p) == null && !MassCreater.getMass(p).WATER) {
			enemy_move(p.x - point.x, p.y - point.y, converDirection(p));
			return;
		}
		list.remove(p);
		if (list.isEmpty()) {
			// TaskOnMapObject.addEnemyWaitMoveTask(this);
			moveStopCount();
			return;
		}
		Point fp = list.get(0);
		if (MapList.getCreature(fp) == null && !MassCreater.getMass(fp).WATER) {
			enemy_move(fp.x - point.x, fp.y - point.y, converDirection(fp));
			return;
		}
		if (!enemy_move_wait) {
			// TaskOnMapObject.addEnemyWaitMoveTask(this);
			return;
		}
		moveStopCount();
	}

	public void changeSize(int delt) {
		delt = delt + largement;
		if (delt > 1) {
			setHuge();
		} else if (delt == 1) {
			setBig();
		} else if (delt == 0) {
			setNeutral();
		} else {
			setSmall();
		}
		if (MAX_STR <= 0) {
			MAX_STR = 1;
		}
		if (getMAX_HP() <= 0) {
			setMAX_HP(1);
		}
		if (HP <= 0) {
			HP = 1;
		}
		if (STR <= 0) {
			STR = 1;
		}
	}

	private boolean conditionChecker() {
		if (this.getConditionList().contains(CONDITION.混乱)) {
			if (attack_possible())
				Confusion.randomAttack(this);
			return true;
		}
		// if (CONDITION.conditionEffectCase(this, CONDITION.怯え))
		// return true;
		return false;
	}

	public Base_Enemy createEnemySet仮眠(boolean b) {
		if (b) {
			setConditionList(CONDITION.特殊仮眠, 0);
		} else {
			CONDITION.conditionRecovery(this, CONDITION.特殊仮眠);
		}
		return this;
	}

	public void directionPointRoomInRoot(DIRECTION d) {
		Point p = getMassPoint();
		int x = d.X;
		int y = d.Y;
		while (true) {
			x += d.X;
			y += d.Y;
			if (!MassCreater.getMass(p.x + x, p.y + y).WALKABLE) {
				break;
			}
			if (MassCreater.getMass(p.x + x, p.y + y).ROOM) {
				setRoot(new Point(p.x + x, p.y + y));
				break;
			}
		}
	}

	/**
	 * エネミー装備時、特殊行動
	 * 
	 * @return falseで無し
	 */
	public boolean enchantEnemySpecialAction() {
		return false;
	}

	public final void enemy_move(int x, int y, DIRECTION dir) {
		// TODO
		if (getMassPoint().x == player_back_point.x
				&& getMassPoint().y == player_back_point.y) {
			setPlayerBackPoint(Mass.nullpo.null_point);
		}
		back_point = getMassPoint().getLocation();
		delt.x = -x * MAP.TILE_SIZE;
		delt.y = -y * MAP.TILE_SIZE;
		mass_point.x += x;
		mass_point.y += y;
		direction = dir;
		turn_count = 0;
		enemy_actioned = true;
		flag_move = true;
		if (TurnSystemController.isAbleToMoveAdditionally(this)) {
			enemy_actioned = false;
			action();
			if (TaskOnMapObject.getEnemyMoveTask().remove(this)) {
				moving();
			} else {
				if (flag_3rd_move)
					flag_3rd_move = false;
				else
					flag_2nd_move = false;
			}
			enemy_actioned = true;
		}
		if (!MassCreater.getMass(getMassPoint()).WALKABLE) {
			// System.out.println("壁だよ");
			// Show.writeErrorText(new Exception("壁だよ！　" + x + ":" + y));
			// Show.showConfirmDialog("壁だよ！" + x + ":" + y);
		}
	}

	public void enemyMovePossible() {
		boolean move_possible = false;
		if (getConditionList().contains(CONDITION.混乱)) {
			if (!Confusion.movePossible(this)) {
				return;
			}
		}
		if (isWall_walk()) {
			if (specialMoveCheck()) {
				move_possible = true;
			}
		} else if (isRandom_walk()) {
			if (specialMoveCheck()) {
				move_possible = true;
			}
		} else {
			if (!player_is_in_sight) {
				move_possible = true;
			} else if (!attack_possible()) {
				move_possible = true;
			}
		}
		if (move_possible) {
			TaskOnMapObject.addEnemyMoveTask(this);
			return;
		}
	}

	protected boolean enemyOrWalkable(boolean Walkable_through, Point p) {
		if (MassCreater.isPointOutOfDangeon(p)) {
			return false;
		}
		return movePoint(p, MassCreater.getMass(this.getMassPoint()).ROOM,
				Walkable_through);
	}

	@Override
	public SE getAttackSE() {
		return attack_se;
	}

	@Override
	public String getColoredName() {
		return color_name[largement + 1].concat(super.getColoredName()).concat(
				color_white);
	}

	public int getDeConvertedLV(int lv) {
		return lv;
	}

	@Override
	public int getDEF() {
		if (getConditionList().contains(CONDITION.パワーアップ)) {
			return super.getDEF() * 15 / 10;
		}
		return super.getDEF();
	}

	@Override
	public DIRECTION getDirection() {
		return direction;
	}

	protected DIRECTION getDirectionRandomMove(boolean walkable) {
		List<Point> list = new ArrayList<Point>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				Point p = new Point(getMassPoint().x + i, getMassPoint().y + j);
				if (MassCreater.isPointOutOfDangeon(p)) {
					continue;
				}
				if (walkable) {
					if (MapList.getCreature(p) == null
							&& MassCreater.getMass(p).WALKABLE
							&& !MassCreater.getMass(p).WATER) {
						list.add(p);
					}
				} else if (movePointDirection(p, walkable)) {
					list.add(p);
				}
			}
		}
		if (list.isEmpty()) {
			return DIRECTION.DOWN;
		}
		int select = new R().nextInt(list.size());
		Point p = list.get(select);
		DIRECTION dir = converDirection(p);
		return dir;
	}

	public String getExplanHoldAction() {
		return explain_hold_action;
	}

	public final String getExplanHoldEffect() {
		return "特に無し";
	}

	protected CONDITION getInitCondition() {
		return null;
	}

	public int getLargement() {
		return largement;
	}

	public String getOriginalName() {
		return tati_e;
	}

	public Point getPlayerBackPoint() {
		return player_back_point;
	}

	public boolean getPlayerIsInSight() {
		return player_is_in_sight;
	}

	public Point getRoot() {
		return root;
	}

	public ArrayList<String> getSpecialActionExplain() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("特になし");
		return list;
	}

	public int getSpecialParcent() {
		return special_attack_parcent;
	}

	@Override
	public int getSTR() {
		if (getConditionList().contains(CONDITION.パワーアップ)) {
			return STR * 15 / 10;
		}
		return STR;
	}

	public Image_LargeCharacter getTatiE() {
		return Image_LargeCharacter.get(tati_e);
	}

	public boolean hasMessage() {
		return false;
	}

	private void init() {
		CONDITION c = getInitCondition();
		if (c != null)
			setCondition(c, 0);
	}

	protected void init1() {
	}

	protected void init2() {
	}

	protected void init3() {
	}

	protected void init4() {
	}

	public int isKamin() {
		return 30;
	}

	/**
	 * 特殊攻撃の範囲 初期は通常攻撃範囲
	 * 
	 * @return
	 */
	protected boolean isRange() {
		return attack_possible();
	}

	protected boolean isSpecialParcent() {
		if (!isSkillActive()) {
			return false;
		}
		if (flag_skill_checking_now)
			return true;
		int parcent = getSpecialParcent();
		if (parcent != 0) {
			if (getConditionList().contains(CONDITION.イカリ)) {
				return true;
			}
			if (parcent >= new R().nextInt(100) + 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 投擲を食らったとき
	 * 
	 * @return
	 */
	public boolean itemHit() {
		return true;
	}

	private void loop() {
		if (Player.me.getConditionList().contains(CONDITION.やりすごし)) {
			return;
		}
		if (Player.me.getConditionList().contains(CONDITION.透明)) {
			return;
		}
		if (MassCreater.isPlayerInTheSightFromThePoint(getMassPoint(), 1)) {
			player_is_in_sight = true;
			RoomInRootClear();
			if (MassCreater.getMass(Player.me.getMassPoint()).ROAD) {
				Point p = Player.me.getMassPoint().getLocation();
				setRoot(p);
			}
		}
	}

	public void massCheck() {
		if (mass == null) {
			mass = MassCreater.getMass();
		}
	}

	public boolean massChecker(Point p) {
		if (MassCreater.getMass(p) == null) {
			return false;
		}
		return true;
	}

	public void message() {

	}

	public boolean moveCheck(DIRECTION dir, boolean through) {
		Point p = getMassPoint().getLocation();
		if (!isWall_walk()) {
			if (dir.NUM % 2 != 0) {
				if (!moveToWalkableCheck(dir)) {
					return false;
				}
			} else {
				if (!MassCreater.getMass(new Point(p.x + dir.X, p.y + dir.Y)).WALKABLE) {
					return false;
				}
			}
		}
		p = new Point(p.x + dir.X, p.y + dir.Y);
		if (MapList.getCreature(p) != null || !movable_water_check(p)) {
			return false;
		} else {
			return true;
		}
	}

	protected boolean moveConditionChecker() {
		if (getConditionList().contains(CONDITION.影縫い))
			return true;
		// if (CONDITION.conditionEffectCase(this, CONDITION.怯え))
		// return true;
		if (getConditionList().contains(CONDITION.混乱)) {
			Confusion.enemyRandomMove(this);
			return true;
		}
		return false;
	}

	private void moveLimit() {
		Point p = getMassPoint();
		int x = Math.abs(root.x - p.x);
		int y = Math.abs(root.y - p.y);
		move_limit = x + y + 1;

	}

	public boolean movePoint(Point p, boolean room, boolean through) {
		DIRECTION d = converDirection(p);
		List<DIRECTION> list = DIRECTION.neiberDirection(d);
		if (movePoint1(list.get(0), through)) {
			return true;
		}
		DIRECTION d3 = list.get(3);
		DIRECTION d4 = list.get(4);
		int abs_x = Math.abs(p.x - getMassPoint().x);
		int abs_y = Math.abs(p.y - getMassPoint().y);
		if (abs_x != abs_y && (abs_x != 0 && abs_y != 0)) {
			list.clear();
			if (abs_x > abs_y) {
				list.add(p.x - getMassPoint().x > 0 ? DIRECTION.RIGHT
						: DIRECTION.LEFT);
				list.add(p.y - getMassPoint().y > 0 ? DIRECTION.DOWN
						: DIRECTION.UP);
			} else {
				list.add(p.y - getMassPoint().y > 0 ? DIRECTION.DOWN
						: DIRECTION.UP);
				list.add(p.x - getMassPoint().x > 0 ? DIRECTION.RIGHT
						: DIRECTION.LEFT);
			}
			list.add(d3);
			list.add(d4);
		}
		for (int i = 0; i < list.size(); i++) {
			if (i >= 3) {
				if (!room) {
					return false;
				}
			}
			DIRECTION dir = list.get(i);
			if (movePoint1(dir, through)) {
				return true;
			}
		}
		return false;
	}

	private boolean movePoint1(DIRECTION dir, boolean through) {
		if (moveCheck(dir, through)) {
			enemy_move(dir.X, dir.Y, dir);
			return true;
		}
		return false;
	}

	public boolean movePointDirection(Point p, boolean walkable) {
		boolean room = MassCreater.getMass(p).ROOM;
		List<DIRECTION> list = DIRECTION.neiberDirection(converDirection(p));
		for (int i = 0; i < list.size(); i++) {
			if (i >= 3) {
				if (!room) {
					return false;
				}
			}
			DIRECTION dir = list.get(i);
			if (!moveCheck(dir, walkable)) {
				return false;
			}
		}
		return true;
	}

	private void moveStopCount() {
		if (count_max <= turn_count) {
			turn_count = 0;
			if (movePoint(new Point(mass_point.x - direction.X, mass_point.y
					- direction.Y), false, false)) {
				return;
			} else {
				if (enemy_move_wait) {
					enemy_actioned = true;
				}
			}
		} else {
			turn_count++;
			enemy_actioned = true;
		}
	}

	protected boolean moveToWalkableCheck(DIRECTION d) {
		List<DIRECTION> list = DIRECTION.neiberDirection(d);
		if (!MassCreater.getMass(new Point(getMassPoint().x + list.get(0).X,
				getMassPoint().y + list.get(0).Y)).WALKABLE) {
			return false;
		}
		Point first = getMassPoint();
		Point second = getMassPoint();
		first = new Point(first.x + list.get(1).X, first.y + list.get(1).Y);
		second = new Point(second.x + list.get(2).X, second.y + list.get(2).Y);
		if (MassCreater.getMass(first).WALKABLE
				&& MassCreater.getMass(second).WALKABLE) {
			return true;
		}
		return false;
	}

	public void moving() {
		if (moveConditionChecker()) {
			enemy_actioned = true;
			return;
		}
		if (specialMove()) {
			playerIsInNeiver();
			return;
		}
		if (player_is_in_sight
				&& !Player.me.getConditionList().contains(CONDITION.やりすごし)
				&& !Player.me.getConditionList().contains(CONDITION.透明)) {
			testAproach();
		} else {
			testTroop();
		}
		playerIsInNeiver();
	}

	@Override
	protected final String nameLevel() {
		return name;
	}

	public boolean playerChecked() {
		if (playerIsInNeiver()) {
			player_is_in_sight = true;
			return true;
		} else {
			player_is_in_sight = false;
			return false;
		}
	}

	@Override
	public boolean playerIsInNeiver() {
		if (Player.me.getConditionList().contains(CONDITION.やりすごし)) {
			return false;
		}
		if (Player.me.getConditionList().contains(CONDITION.透明)) {
			return false;
		}
		Point p = Player.me.getMassPoint();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (getMassPoint().x + i == p.x && getMassPoint().y + j == p.y) {
					player_is_in_sight = true;
					return true;
				}
			}
		}
		return false;
	}

	public void playerPointIsEntrancePoint() {
	}

	private void playerSearch() {
		player_is_in_sight = false;
		whereIsPlayer();
	}

	protected void powerUP() {
		if (++power_up_count >= 2) {
			setFlagWarning();
		}
		setSecondAnimation(new OuraEffect(this, true));
		SE.STATUS_POWER_UP.play();
		changeMAX_STR(getSTR() * 15 / 10);
		setSTR(getMAX_STR());

		// flag_warning = vtrue;
	}

	private void preChangeSize() {
		if (SIZE_HP == 0 && SIZE_STR == 0) {
			SIZE_HP = getMAX_HP();
			SIZE_STR = MAX_STR;
		}
		if (largement != 0) {
			// もとに戻す
			if (largement == -1) {
				HP = HP * 2;
				STR = STR * 2;
			} else if (largement == 2) {
				HP = HP / 2;
				STR = STR * 2 / 3;
			} else if (largement == 3) {
				HP = HP / 3;
				STR = STR / 2;
			}
		}
	}

	protected DIRECTION randomMoveDirection(boolean walkable) {
		List<Point> list = new ArrayList<Point>();
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			if (moveCheck(d, walkable)) {
				Point p = new Point(getMassPoint().x + d.X, getMassPoint().y
						+ d.Y);
				list.add(p);
			}
		}
		// for (int i = -1; i <= 1; i++) {
		// for (int j = -1; j <= 1; j++) {
		// if (i == 0 && j == 0) {
		// continue;
		// }
		// Point p = new Point(getMassPoint().x + i, getMassPoint().y + j);
		// if (MassCreater.isPointOutOfDangeon(p)) {
		// continue;
		// }
		// if (walkable) {
		// if (MapList.getCreature(p) == null
		// && MassCreater.getMass(p).WALKABLE
		// && !MassCreater.getMass(p).WATER) {
		// list.add(p);
		// }
		// } else if (movePointDirection(p, walkable)) {
		// list.add(p);
		// }
		// }
		// }
		if (list.isEmpty()) {
			return DIRECTION.DOWN;
		}
		int select = new R().nextInt(list.size());
		Point p = list.get(select);
		DIRECTION dir = converDirection(p);
		enemy_move(dir.X, dir.Y, dir);
		return dir;
	}

	private void randomRoot(List<Point> list) {
		List<Point> l = new ArrayList<Point>();
		Point em = getMassPoint().getLocation();
		if (list.isEmpty()) {
			DIRECTION dir = getDirectionRandomMove(false);
			root = new Point(this.getMassPoint().x + dir.X,
					this.getMassPoint().y + dir.Y);
			return;
		}
		if (list.size() == 1) {
			root = list.get(0);
			return;
		}
		for (Point p : list) {
			Point ep = new Point(em.x - direction.X, em.y - direction.Y);
			if (p.x == ep.x && p.y == ep.y) {
				continue;
			}
			l.add(p);
		}
		int select = new R().nextInt(l.size());
		root = l.get(select);
	}

	private boolean randomWalk() {
		if (!specialMoveCheck()) {
			return false;
		}
		randomMoveDirection(false);
		return true;
	}

	/**
	 * 
	 * @param length
	 *            -1 で無限
	 * @param only_effective_when_player_is_in_sight
	 * @return
	 */
	protected boolean range(int length,
			boolean only_effective_when_player_is_in_sight) {
		if (only_effective_when_player_is_in_sight && !player_is_in_sight)
			return false;
		if (length == -1) {
			length = Math.max(MassCreater.HEIGHT, MassCreater.WIDTH);
		}
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			Point p = getMassPoint().getLocation();
			for (int i = 0; i < length; i++) {
				p.translate(d.X, d.Y);
				if (MapList.getCreature(p) instanceof Player) {
					return true;
				}
			}
		}
		return false;
	}

	private void roomInRoadCheck(Point p) {
		list_road.clear();
		Room r = MassCreater.getRoom(getMassPoint().x, getMassPoint().y);
		for (Point point : r.getListEntrance()) {
			list_road.add(point);
		}
	}

	private boolean roomInRoot() {
		if (list_road.isEmpty()
				|| (root.x == getMassPoint().x && root.y == getMassPoint().y)) {
			return true;
		}
		return false;
	}

	public void RoomInRootClear() {
		list_road.clear();
		move_limit = 0;
	}

	private void rootInRoad() {
		Point p = mass_point.getLocation();

		for (int i = -1; i <= 1; i++) {
			if (i == 0)
				continue;
			if (MassCreater.getMass(new Point(p.x + i, p.y)).ROAD) {
				list_road.add(new Point(p.x + i, p.y));
			}
			if (MassCreater.getMass(new Point(p.x, p.y + i)).ROAD) {
				list_road.add(new Point(p.x, p.y + i));
			}
		}
		if (list_road.size() == 1 || list_road.isEmpty()) {
			if (movePoint(new Point(p.x + direction.X, p.y + direction.Y),
					true, false)) {
				return;
			} else if (count_max <= turn_count) {
				Point dir = new Point(getMassPoint().x - direction.X,
						getMassPoint().y - direction.Y);
				direction = converDirection(dir);
				turn_count = 0;
				enemy_actioned = true;
			} else {
				turn_count++;
			}
		} else if (list_road.size() == 2) {
			Point p2 = new Point(p.x + direction.X, p.y + direction.Y);
			if (!movePoint(p2, true, false)) {
				if (count_max <= turn_count) {
					turn_count = 0;
					Point direct = new Point(getMassPoint().x - direction.X,
							getMassPoint().y - direction.Y);
					direction = converDirection(direct);
					enemy_actioned = true;
				} else {
					turn_count++;
				}
			}
		} else {
			branch();
		}
	}

	public boolean rootIsAround() {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if (getMassPoint().x + i == player_back_point.x
						&& getMassPoint().y + j == player_back_point.y) {
					return true;
				}
			}
		}
		return false;
	}

	protected void saveKO() {
		Config.save(this, getConvertedLV(), true);
		Medal.今までで合計XX体の敵をやっつけた.addCount();
	}

	protected void set2timesWalk() {
		setCondition(CONDITION.倍速, 0);
	}

	protected void set3timesWalk() {
		setCondition(CONDITION.三倍速, 0);
	}

	@Override
	protected void setBeatedAway() {
		saveKO();
		super.setBeatedAway();
	}

	private void setBig() {
		preChangeSize();
		size = SIZE_MAX = 150;
		setMAX_HP(SIZE_HP * 2);
		MAX_STR = SIZE_STR * 3 / 2;
		HP = HP * 2;
		STR = STR * 3 / 2;
		largement = 1;
	}

	public Base_Enemy setConditionList(CONDITION condition, int timer) {
		super.setCondition(condition, timer);
		return this;
	}

	public Base_Enemy setDirection(DIRECTION d) {
		attacking_direction = d;
		direction = d;
		return this;
	}

	public Base_Enemy setFlagWarning() {
		flag_warning = true;
		return this;
	}

	protected void setHalfWalk() {
		setCondition(CONDITION.鈍足, 0);
	}

	private void setHuge() {
		preChangeSize();
		size = SIZE_MAX = 200;
		setMAX_HP(SIZE_HP * 3);
		MAX_STR = SIZE_STR * 2;
		HP = HP * 3;
		STR = STR * 2;
		largement = 2;
	}

	@Override
	public void setNameAndStatus() {
		largement = 0;
		size = SIZE_MAX = 100;
		setStateChengeByLevelUp();
		nameLevel();
		statusLevel();
		if (LV < 1) {
			super.setNameAndStatus();
			return;
		}
		CSVLoadSupporter<String> list = CSVLoadSupporter.loadCSV(LV + "_"
				+ getClass().getSimpleName(), getClass(), "\t");
		if (list == null) {
			super.setNameAndStatus();
			return;
		}
		name = list.get();
		setMAX_HP(HP = list.toI());
		MAX_STR = STR = list.toI();
		MAX_DEF = DEF = list.toI();
		setENEMY_EXP(list.toI());
		setSpecialAttackParcent(list.toI());
		explain_hold_action = list.get();
		tati_e = list.get();
		if (tati_e.isEmpty())
			tati_e = getClass().getSimpleName();
		floating = list.isTrue();
		boating = list.isTrue();
		String g = list.get();
		if (g.matches("true") || g.matches("TRUE"))
			Ghost = -1;
		else if (g.matches("half"))
			Ghost = 0;
		else
			Ghost = 1;
		Fire = list.isTrue();
		Ice = list.isTrue();
		Thunder = list.isTrue();
		Magic = list.isTrue();
		setRandom_walk(list.isTrue());
		setWall_walk(list.isTrue());
		list_category.clear();
		STAGE[] stages = { STAGE.紅魔郷, STAGE.妖々夢, STAGE.永夜抄, STAGE.花映塚,
				STAGE.風神録, STAGE.地霊殿, STAGE.星蓮船, STAGE.神霊廟, STAGE.輝針城, STAGE.外伝 };
		for (STAGE stage : stages) {
			if (list.isTrue())
				list_category.add(stage);
		}
		attack_se = SE.get(list.get());
		init();
		if (LV == 1)
			init1();
		else if (LV == 2)
			init2();
		else if (LV == 3)
			init3();
		else if (LV == 4)
			init4();
	}

	private void setNeutral() {
		preChangeSize();
		size = SIZE_MAX = 100;
		setMAX_HP(SIZE_HP);
		MAX_STR = SIZE_STR;
		largement = 0;
	}

	public void setPlayerBackPoint(Point p) {
		player_back_point = p;
	}

	public void setRoot(Point p) {
		if (p == null) {
			list_road.clear();
			root = getMassPoint().getLocation();
			return;
		}
		list_road.clear();
		list_road.add(p);
		root = p;
		moveLimit();
	}

	public void setSize() {
		int size = PresentField.get().getEnemySize();
		if (PresentField.get().isWarningEnemy()) {

			int r = R.ran(1000);
			flag_warning = true;

			if (PresentField.get().isLvUpedEnemy() && LV != 1 && LV != 4
					&& new R().is(75) && !isPlayerSide() && getMaxLV() < 5) {
				if (r < 30) {
					int lv = LV;
					boolean sleep_shallow = conditionCheck(CONDITION.仮眠);
					boolean sleep_deep = conditionCheck(CONDITION.特殊仮眠);
					CONDITION.conditionAllClear(this);
					chengeLv(lv + 1);
					if (getName().startsWith("*")) {
						CONDITION.conditionAllClear(this);
						chengeLv(lv);
						flag_warning = false;
					}
					if (sleep_shallow)
						setCondition(CONDITION.仮眠, 0);
					if (sleep_deep)
						setCondition(CONDITION.特殊仮眠, 0);
				} else
					flag_warning = false;
			} else {
				if (r < 5)
					size -= 1;
				else if (r < 30)
					size += 1;
				else
					flag_warning = false;
			}
		} else {
			if (size == 0) {
				return;
			}
		}
		// if (largement != 0) {
		// return;
		// }
		// size = size + PresentField.get().getEnemySize();
		changeSize(size);
	}

	private void setSmall() {
		preChangeSize();
		size = SIZE_MAX = 50;
		setMAX_HP(SIZE_HP / 2);
		MAX_STR = SIZE_STR / 2;
		HP = HP / 2;
		STR = STR / 2;
		largement = -1;
	}

	protected void setSpecialAttackParcent(int parcent) {
		special_attack_parcent = parcent;
	}

	protected void setSpecialHuge() {
		preChangeSize();
		size = SIZE_MAX = 250;
		setMAX_HP(SIZE_HP * 3);
		MAX_STR = SIZE_STR * 2;
		HP = HP * 3;
		STR = STR * 2;
		largement = 2;
	}

	/**
	 * レベルアップによる状態以上変更がある場合は@Override
	 */
	protected void setStateChengeByLevelUp() {
		// 通常では特に無し
	}

	protected void special() {

	}

	protected boolean specialAttack() {
		return false;
	}

	/**
	 * 隣接時以外の特殊行動 special行動が可能ならばtrueを返す
	 */
	protected abstract boolean specialCheck();

	protected boolean specialMove() {
		if (isWall_walk()) {
			return wallWalk();
		} else if (isRandom_walk()) {
			return randomWalk();
		} else {
			return false;
		}
	}

	protected final boolean specialMoveCheck() {
		if (isWall_walk()) {
			return wallWalkMoveCheck();
		} else if (isRandom_walk()) {
			return specialMoveParcent();
		}
		return false;
	}

	protected boolean specialMoveParcent() {
		int parcent = (attack_possible()) ? 15 : 50;
		if (new R().is(parcent)) {
			return true;
		}
		return false;
	}

	public void startTurn() {
		special_possible_check = false;
		special_ok = false;
	}

	@Override
	protected final List<Integer> statusLevel() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(8);
		list.add(3);
		list.add(2);
		return list;
	}

	private void testAproach() {
		if (movePoint(Player.me.getMassPoint(), true, false)) {
			return;
		}
		if (enemy_move_wait) {
			enemy_actioned = true;
		}
	}

	protected void testTroop() {
		if (MassCreater.getMass(mass_point).ROOM) {
			if (roomInRoot() || move_limit == 1) {
				roomInRoadCheck(getMassPoint());
				randomRoot(list_road);
				moveLimit();
			}
			troopInRoom();
			return;
		}
		if (MassCreater.getMass(mass_point).ROAD) {
			RoomInRootClear();
			troopInRoad();
			RoomInRootClear();
			return;
		}
	}

	private void troopInRoad() {
		rootInRoad();
	}

	private void troopInRoom() {
		move_limit--;
		movePoint(root, true, false);
	}

	@Override
	public void upDate(int MOVE_SPEED) {
		if (flag_2nd_move && flag_3rd_move)
			MOVE_SPEED *= 3;
		else if (flag_2nd_move || flag_3rd_move)
			MOVE_SPEED *= 2;
		super.upDate(MOVE_SPEED);
	}

	protected void walkableThrough() {
		DIRECTION dir = converDirection(Player.me.getMassPoint());
		movePoint(
				new Point(getMassPoint().x + dir.X, getMassPoint().y + dir.Y),
				false, true);
	}

	protected boolean walkableThroughCheck() {
		return false;
	}

	private boolean wallWalk() {
		if (Player.me.getConditionList().contains(CONDITION.やりすごし)
				|| Player.me.getConditionList().contains(CONDITION.透明)) {
			randomMoveDirection(true);
			return true;
		}
		if (random) {
			randomMoveDirection(true);
			return true;
		}
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (Player.me.getMassPoint().x == getMassPoint().x + i
						&& Player.me.getMassPoint().y == getMassPoint().y + j) {
					randomMoveDirection(true);
				}
			}
		}
		walkableThrough();
		return true;
	}

	private boolean wallWalkMoveCheck() {
		if (Player.me.getConditionList().contains(CONDITION.やりすごし)
				|| Player.me.getConditionList().contains(CONDITION.透明)) {
			random = true;
			return true;
		}
		if (isSkillActive() && isRandom_walk() && specialMoveParcent()) {
			random = true;
			return true;
		}
		if (attack_possible()) {
			return false;
		} else {
			random = false;
			return true;
		}
	}

	public void whereIsPlayer() {
		whereIsPlayer(true);
	}

	public void whereIsPlayer(boolean directing) {
		massCheck();
		try {
			if (mass[getMassPoint().x][getMassPoint().y].ROOM) {
				loop();
			} else if (mass[getMassPoint().x][getMassPoint().y].ROAD) {
				RoomInRootClear();
				if (!playerIsInNeiver()) {
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							if (MassCreater.getMass(new Point(mass_point.x + i,
									mass_point.y + j)).ROOM) {
								loop();
							}
						}
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(this + "" + getMassPoint() + ""
					+ mass[1][1].WALKABLE);
		}
		if (player_is_in_sight && directing) {
			direction = converDirection(Player.me.getMassPoint());
		}
	}
}
