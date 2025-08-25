package dangeon.model.object.creature;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.condition.復活;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.EnemyFall;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.item.bullet.ドラゴンブレス;
import dangeon.model.object.artifact.item.bullet.目からビーム;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印厄;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印嫉妬;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印宝;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印病;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印邪;
import dangeon.model.object.artifact.item.food.ミシャグジおにぎり;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.model.object.creature.action.SpecialAction;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.わかさぎ姫;
import dangeon.model.object.creature.enemy.封獣ぬえ;
import dangeon.model.object.creature.enemy.洩矢諏訪子;
import dangeon.model.object.creature.enemy.風見幽香;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.ResistWeakPoint;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.ObjectPoint;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.util.Switch;
import dangeon.view.anime.Base_Anime;
import dangeon.view.anime.FireEffect;
import dangeon.view.anime.HealEffect;
import dangeon.view.anime.HitEffect;
import dangeon.view.constant.MAP;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.SecondAnime;
import dangeon.view.detail.View_Sider;
import main.res.CHARA_IMAGE;
import main.res.Image_Artifact;
import main.res.SE;
import main.util.BeautifulView;
import main.util.DIRECTION;

public abstract class Base_Creature extends Base_MapObject {

	protected enum AttackPhase {
		GO, BACK;
	}

	public boolean player_side = false;

	protected boolean floating, boating, Fire, Ice, Thunder, Magic;

	private boolean random_walk;

	private boolean wall_walk;

	protected int Ghost = 1;

	protected boolean flag_warning = false;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected List<STAGE> list_category = new ArrayList<STAGE>();

	protected CHARA_IMAGE IM;

	/**
	 * コンストラクタで指定してください
	 */
	protected int ENEMY_EXP, HP, STR, DEF;

	private int MAX_HP;

	protected int MAX_STR;

	protected int MAX_DEF;
	public boolean quick_one_attack = false;
	private boolean item_drop = true;

	public HashMap<CONDITION, Integer> condition_map = new HashMap<CONDITION, Integer>();

	private ArrayList<CONDITION> list_condition = new ArrayList<CONDITION>();

	private ArrayList<CONDITION> condition_remove_task = new ArrayList<CONDITION>();

	protected DIRECTION attacking_direction = null;

	protected final Point attack_delt_point = new Point(0, 0);

	protected AttackPhase normal_attack_phase = null;

	protected final Point beated_away_point = new Point(0, 0);

	protected int beated_away_angle = 0;

	protected int attaking_frame = 0;

	protected int special_attack_parcent = 0;

	protected boolean flag_damaging = false;

	/**
	 * 攻撃画像アニメの指定コマNo
	 */
	protected int attack_No = 0;

	private transient ArrayList<Task> attack_task = new ArrayList<Task>(8);

	private Base_Anime effect, second_effect;

	private int telepo = -1;

	private Task telepo_task;

	protected int SIZE_MAX = 100;

	protected int size = 100;

	private byte flag_changing_size = 0;

	protected boolean flag_mute;

	protected int power_up_count = 0;

	public Base_Creature(Point p, String name, int level) {
		super(name, p, level);
		setNameAndStatus();
		IM = CHARA_IMAGE.get(this.getClass());
	}

	public Base_Creature(Point p, String name, int level, CONDITION c) {
		super(name, p, level);
		CONDITION.setConditionTime(this, c, 0);
		list_condition.add(c);
	}

	public void addAttackTask(final Base_Creature active,
			final Base_Creature passive) {
		attack_task.add(getAttackTask(active, passive));
	}

	public void addAttackTask(Task t) {
		attack_task.add(t);
	}
	
	public void setLv4() {
		if (this instanceof Base_NPC) {
			return;
		}
		if (getConvertedLV() == 4) {
			Message.set("ANOTHERのレベルは変化しなかった");
			return;
		}

		SE.LEVEL_UP.play();
		Message.set(getColoredName(), "はレベルが上がって");
		LV = 4;
		setNameAndStatus();
		Message.set(getColoredName(), "になった");
	}

	public void addLV(int delt) {
		if (this instanceof Base_NPC) {
			return;
		}
		if (getConvertedLV() == 4) {
			Message.set("ANOTHERのレベルは変化しなかった");
			return;
		}
		int lv = delt + getLV();
		lv = getLV(lv);
		if (equals(Player.me)) {
			LV = lv;
		} else {
			if (LV > lv) {
				if (LV > getMaxLV()) {
					Message.set(getColoredName(), "のレベルは下がらなかった");
					return;
				}
				SE.LEVEL_DOWN.play();
				Message.set(getColoredName(), "はレベルが下がって");
				LV = lv;
				setNameAndStatus();
				Message.set(getColoredName(), "になった");
				View_Sider.setInformation("レベルが下がって", getColoredName(),
						"になりました");
			} else if (LV < lv) {
				SE.LEVEL_UP.play();
				Message.set(getColoredName(), "はレベルが上がって");
				LV = lv;
				setNameAndStatus();
				Message.set(getColoredName(), "になった");
				View_Sider.setInformation("レベルが上がって", getColoredName(),
						"になりました");
			} else {
				if (delt < 0) {
					Message.set("これ以上", getColoredName(), "のレベルは下がらなかった");
				} else if (delt > 0) {
					Message.set("これ以上", getColoredName(), "のレベルは上がらなかった");
				}
			}
		}
	}

	public void addMAX_HP(int delt) {
		if (MAX_HP + delt > 999) {
			MAX_HP = 999;
			return;
		} else if (MAX_HP + delt < 1) {
			MAX_HP = 1;
			return;
		}
		MAX_HP += delt;
	}

	public void addStr(int value) {
		if (STR + value <= 1) {
			STR = 1;
		} else if (STR + value >= MAX_STR) {
			STR = MAX_STR;
		} else {
			STR += value;
		}
	}

	public void arrowDamage(Object obj, String str, int damage) {
		damage = 1;
		chengeHP(obj, str, damage);
		Damage.damageArrowMessage(this, str, damage);
		if (enemyLifeCheck()) {
			Player.me.setExpCash(getENEMY_EXP());
		}
	}

	public void changeConditoinTurn(CONDITION condition, int diff) {
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.病)) {
			if (condition.isGood() && diff > 0) {
				diff = diff * 2;
			}
		}
		if (condition_map.containsKey(condition)) {
			int timer = condition_map.get(condition) + diff;
			if (timer <= 0) {
				CONDITION.quickRemoveCondition(condition, this);
			} else {
				condition_map.put(condition, timer);
			}
		} else if (diff > 0) {
			setCondition(condition, diff);
		}
	}

	/**
	 * 最大値とともに現在地も上げる
	 * 
	 * @param value
	 */
	public void changeMAX_STR(int value) {
		int max_str = MAX_STR + value;
		if (max_str >= 999) {
			max_str = 999;
			value = max_str - MAX_STR;
		}
		MAX_STR = value;
		STR += value;
		if (MAX_STR < 1) {
			MAX_STR = 1;
		}
		if (STR < 1) {
			STR = 1;
		}
	}

	public DIRECTION chengeDirection(Base_Creature creature, Point p) {
		if (creature.getMassPoint().x == p.x) {
			if (creature.getMassPoint().y < p.y) {
				return DIRECTION.DOWN;
			} else
				return DIRECTION.UP;
		} else if (creature.getMassPoint().y == p.y) {
			if (creature.getMassPoint().x < p.x) {
				return DIRECTION.RIGHT;
			} else
				return DIRECTION.LEFT;
		}
		return null;
	}

	/**
	 * @param obj
	 *            ダメージを与えた要素
	 * @param str
	 *            倒れた時のメッセージ
	 * @param delt
	 *            ダメージ
	 * @return このcreatureが倒れたらtrue
	 */
	public boolean chengeHP(Object obj, String str, int delt) {
		if (delt < 0 && !EnchantSpecial.isFlagNeglectHeal()) {
			Integer I = Damage.convert_2geki(this, -delt);
			if (I != null) {
				delt = -I;
				EnchantSpecial.setFlagNeglectHeal(true);
			}
		}
		if (this.HP + delt <= 0) {
			this.HP = 0;
		} else if (this.HP + delt > this.getMAX_HP()) {
			this.HP = this.getMAX_HP();
		} else {
			this.HP += delt;
		}
		if (delt < 0) {
			if (this.equals(Player.me) && 封獣ぬえ.isNue(3)
					&& !(obj instanceof 封獣ぬえ)) {
				MainMap.addEffect(new HitEffect(this, "ぬえん"));
			} else {
				MainMap.addEffect(new HitEffect(this, -delt));
			}
		} else if (delt > 0) {
			MainMap.addEffect(new HealEffect(this, delt));
		}
		if (delt <= 0) {
			CONDITION.conditionEffectCase(this, CONDITION.麻痺);
			CONDITION.conditionEffectCase(this, CONDITION.安心);
			CONDITION.conditionEffectCase(this, CONDITION.やりすごし);
		}
		CONDITION.conditionEffectCase(this, CONDITION.仮眠);
		CONDITION.conditionEffectCase(this, CONDITION.特殊仮眠);
		if (this instanceof Player) {
			return false;
		} else {
			boolean flag = enemyLifeCheck();
			EnchantSpecial.setFlagNeglectHeal(false);
			return flag;
		}
	}

	public void chengeHP_NoEffect(int delt) {
		if (this.HP + delt <= 0) {
			this.HP = 0;
			if (this instanceof Player) {
				Player.me.death(null, "お腹が減って倒れた");
			} else {
				enemyLifeCheck();
			}
		} else if (this.HP + delt > this.getMAX_HP()) {
			this.HP = this.getMAX_HP();
		} else
			this.HP += delt;
	}

	public void chengeHpGetExp(int delt) {
		if (this.HP + delt <= 0) {
			this.HP = 0;
		} else if (this.HP + delt > this.getMAX_HP()) {
			this.HP = this.getMAX_HP();
		} else {
			this.HP += delt;
		}
		if (delt < 0) {
			MainMap.addEffect(new HitEffect(this, -delt));
		} else if (delt > 0) {
			MainMap.addEffect(new HealEffect(this, delt));
		}
		CONDITION.conditionEffectCase(this, CONDITION.やりすごし);
		CONDITION.conditionEffectCase(this, CONDITION.麻痺);
		CONDITION.conditionEffectCase(this, CONDITION.安心);
		CONDITION.conditionEffectCase(this, CONDITION.仮眠);
		CONDITION.conditionEffectCase(this, CONDITION.特殊仮眠);
		enemyLifeCheck();
	}

	public void chengeLevelHp(int delt) {
		if (HP + delt < 1) {
			HP = 0;
		} else if (HP + delt > 999) {
			HP = 999;
		} else {
			HP += delt;
		}
	}

	/**
	 * ダンジョンから呼ぶ場合はaddLVを使う
	 * 
	 * @param lv
	 */
	public void chengeLv(int lv) {
		LV = lv;
		setNameAndStatus();
	}

	public boolean conditionCheck(CONDITION condition) {
		return list_condition.contains(condition);
	}

	/**
	 * 引数のポイントと自分のポイントを比較して、方向を返す
	 * 
	 * @param p
	 * @return
	 */
	public DIRECTION converDirection(Point p) {
		DIRECTION d = DIRECTION.getDirection(this.getMassPoint(), p);
		if (d == DIRECTION.NEUTRAL) {
			Player.me.setMassPoint_ParabolaJumpAttack(EnemyFall
					.getPoint(Player.me.getMassPoint()));
			// Player.me.setMassPoint_ParabolaJump_NoAttack(this.getMassPoint());
			// Show.showConfirmDialog("converDirectionにおいて"
			// + this.getName().concat("と主人公が重なっています"));
			// while (true) {
			// Point _p = MassCreater.getCreatureIp();
			// if (MapList.getCreature(_p) == null) {
			// Player.me.setMassPoint(_p);
			// break;
			// }
			// }
			// return converDirection(Player.me.getMassPoint());
			d = getDirection();
			if (d == null || d == DIRECTION.NEUTRAL)
				d = DIRECTION.DOWN;
		}
		return d;
	}

	public Base_Enemy convertEnemyFromCreature(Base_Creature creature) {
		return MapList.getEnemy(creature.getMassPoint());
	}

	public boolean counterAttackEnemy() {
		return false;
	}

	public int damagedWithFire(int damage) {
		if (isFire()) {
			Message.set(getColoredName(), "には効かなかった");
			return 0;
		} else {
			return damage;
		}
	}

	private Point directionValue(boolean plus, Point p) {
		if (plus)
			return new Point(getMassPoint().x + p.x, getMassPoint().y + p.y);
		return new Point(getMassPoint().x - p.x, getMassPoint().y - p.y);
	}

	protected void drain(int dam) {
	}

	public void drawCreature(Graphics2D g) {
		drawCreature(g, ObjectPoint.getScreenPointRelPlayer(this));
	}

	protected void drawCreature(Graphics2D g, int x, int y) {
		BufferedImage bi = (BufferedImage) getImage();
		if (telepo == -1 && size == 100) {
			g.drawImage(bi, x + getFootX(), y + getFootY(), null);
		} else {
			int TELEPO = 16;
			if (telepo > -1) {
				telepo++;
			}
			if (telepo >= TELEPO) {
				telepo = -1;
				drawCreature(g, x, y);
				return;
			} else {
				x += getFootX();
				y += getFootY();
				int w = bi.getWidth(null);
				int h = bi.getHeight(null);
				if (size != 100) {
					w = w * size / 100;
					h = h * size / 100;
				}
				if (telepo == -2) {
					telepo = TELEPO / 2;
				} else if (telepo > 0) {
					if (telepo == TELEPO / 2) {
						if (telepo_task != null) {
							telepo_task.work();
							telepo_task = null;
						}
					}
					double rate = Math.sin(Math.PI * telepo / TELEPO);
					double dw = w * rate;
					double dh = h * rate;
					w -= dw;
					h += dh;
					x += dw / 2;
					y -= dh;
				}
				g.drawImage(bi, x, y, w, h, null);
			}

		}

	}

	public void drawCreature(Graphics2D g, Point p) {
		int x = p.x;
		int y = p.y;
		if (!conditionCheck(CONDITION.やりすごし)) {
			if (conditionCheck(CONDITION.透明)) {
				BeautifulView.setAlphaOnImg(g, 0.5f);
				drawCreature(g, x, y);
				BeautifulView.setAlphaOnImg(g, 1f);
			} else {
				drawCreature(g, x, y);
			}
		} else {
			skipDrawFrame();
		}
		for (CONDITION cnd : getConditionList()) {
			if (cnd.IMA != null) {
				if ((this instanceof Player) && conditionCheck(CONDITION.睡眠)
						&& !conditionCheck(CONDITION.おにぎり)) {
					continue;
				}
				cnd.IMA.draw(g, x + getHeadX(), y, this);
			}
		}
	}

	public void endDamaging() {
		flag_damaging = false;
	}

	@Override
	protected void endParabonalJump() {
		if (flag_changing_size != 0) {
			flag_changing_size = 0;
			if (at_movement_end_task != null) {
				at_movement_end_task.work();
				at_movement_end_task = null;
			}
		} else {
			super.endParabonalJump();
		}
	}

	private void enemyBreak() {
		itemDrop();
		if (isSkillActive(true))
			enemyBreakAction();
	}

	/**
	 * 特殊エネミーにはOverrideして使う
	 */
	protected void enemyBreakAction() {
	}

	/**
	 * エネミーのライフが０以下になったらtrueを返す
	 * 
	 * @return
	 */
	public boolean enemyLifeCheck() {
		if (getHP() <= 0) {
			if (this.getConditionList().contains(CONDITION.復活)) {
				CONDITION.conditionRecovery(this, CONDITION.復活);
				Message.set(getColoredName(), "は不死鳥のごとく復活した");
				chengeHP(null, null, getMAX_HP());
				return false;
			} else if (recoveryGhost()) {
				return false;
			}
			setBeatedAway();
			Message.set(getColoredName(), "をやっつけた");
			enemyBreak();
			return true;
		}
		return false;
	}

	public void finshAnimation() {
		effect = null;
	}

	public void finshSecondAnimation() {
		second_effect = null;
	}

	/**
	 * @param plus
	 *            正面ならtrue背後ならfalse
	 * @return
	 */
	public Point frontOrBackPoint(boolean plus) {
		switch (direction) {
		case UP:
			return directionValue(plus, new Point(0, -1));
		case DOWN:
			return directionValue(plus, new Point(0, 1));
		case LEFT:
			return directionValue(plus, new Point(-1, 0));
		case RIGHT:
			return directionValue(plus, new Point(1, 0));
		case UP_LEFT:
			return directionValue(plus, new Point(-1, -1));
		case UP_RIGHT:
			return directionValue(plus, new Point(1, -1));
		case DOWN_LEFT:
			return directionValue(plus, new Point(-1, 1));
		case DOWN_RIGHT:
			return directionValue(plus, new Point(1, 1));
		default:
			return null;
		}
	}

	public Base_Anime getAnimation() {
		return effect;
	}

	public Image getATKImage(int LV, DIRECTION attacking_direction,
			int attack_No) {
		int max = IM.ATK[0][0].length;
		if (attack_No >= max)
			attack_No = max - 1;
		return IM.getATKImage(LV, attacking_direction, attack_No);
	}

	/**
	 * 
	 * @return attack_delt_point
	 */
	public Point getAttackDeltPoint() {
		return attack_delt_point;
	}

	public int getAttackingFrame() {
		return attaking_frame;
	}

	public int getAttackNo() {
		return attack_No;
	}

	protected Point getAttackPoint() {
		return new Point(screen_point.x + attack_delt_point.x, screen_point.y
				+ attack_delt_point.y);
	}

	public SE getAttackSE() {
		return SE.ATTACK_HANDS;
	}

	protected Task getAttackTask(final Base_Creature active,
			final Base_Creature passive) {
		return new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (active != null && passive != null
						&& !passive.isInValidOnAttack())
					Damage.normalAttack(active, passive);
			}
		};
	}

	public Point getBackPoint() {
		Point p = getMassPoint();
		DIRECTION d = getDirection();
		return new Point(p.x - d.X, p.y - d.Y);
	}

	public ArrayList<CONDITION> getBadConditionList() {
		ArrayList<CONDITION> list = new ArrayList<CONDITION>();
		for (CONDITION con : CONDITION.values_bad()) {
			if (list_condition.contains(con)) {
				list.add(con);
			}
		}
		return list;
	}

	public List<STAGE> getCategory() {
		return list_category;
	}

	public CHARA_IMAGE getCharacterImage() {
		return IM;
	}

	@Override
	public String getColoredName() {
		return super.getColoredName();
	}

	public List<CONDITION> getConditionList() {
		return list_condition;
	}

	public List<CONDITION> getConditionListNow() {
		List<CONDITION> list = new ArrayList<CONDITION>();
		for (CONDITION c : getConditionList()) {
			if (getConditionRemoveTask().contains(c)) {
				continue;
			}
			list.add(c);
		}
		return list;
	}

	public List<CONDITION> getConditionRemoveTask() {
		return condition_remove_task;
	}

	public int getConditionTurn(CONDITION con) {
		Integer i = condition_map.get(con);
		if (i != null) {
			return i;
		} else {
			return -1;
		}
	}

	public int getConvertedLV() {
		return getLV();
	}

	public int getDEF() {
		return DEF / 2;
	}

	/**
	 * 固有アイテムが有ればOverride
	 * 
	 * @return
	 */
	protected Base_Artifact getDropItem() {
		return ItemTable.itemReturn(getMassPoint().getLocation(), null);
	}

	public int getENEMY_EXP() {
		System.out.println(ENEMY_EXP);
		return ENEMY_EXP;
	}

	protected int getFootDeltY() {
		return 0;
	}

	public final int getFootX() {
		return (MAP.TILE_SIZE - getImage().getWidth(null) * size / 100) / 2;
	}

	public final int getFootY() {
		return +(-(getImage().getHeight(null) + MAP.TILE_SIZE) / 2 + getFootDeltY())
				* size / 100 + MAP.Y_OFFSET;
	}

	/**
	 * 
	 * @return 幽霊なら-1　半幽霊（＝妖夢）だけ0　通常なら1
	 */
	public int getGhost() {
		return Ghost;
	}

	public int getHeadX() {
		return 0;
	}

	public int getHeadY() {
		return (-MAP.TILE_SIZE + MAP.Y_OFFSET - 10) * size / 100;
	}

	public final int getHP() {
		return HP;
	}

	@Override
	public Image getImage() {
		if (conditionCheck(CONDITION.おにぎり))
			return Image_Artifact.FOOD.getImage(0);
		else if (isAttacking()) {
			return getATKImage(LV, attacking_direction, attack_No);
		} else if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, getAnimation().getComa());
		} else if (flag_damaging) {
			return IM.getDamImage(LV, direction);
		}
		return IM.getWalkImage(LV, direction, getMoveAnimationSpeed());
	}

	public int getItemDropParcent() {
		return itemDropParcent();
	}

	public final int getLV(int goal) {
		boolean plus = goal > this.getLV();
		boolean minus = goal < this.getLV();
		if (!plus && !minus) {
			return goal;
		} else if (getConvertedLV() == 4) {
			return 4;
		} else if (goal < 1) {
			goal = 1;
		} else if (goal > getMaxLV()) {
			goal = getMaxLV();
		}
		return goal;
	}

	public final int getMAX_DEF() {
		return MAX_DEF;
	}

	public int getMAX_HP() {
		return MAX_HP;
	}

	public int getMAX_STR() {
		return MAX_STR;
	}

	protected float getMoveAnimationSpeed() {
		if (TurnSystemController.time_stop_count != 0) {
			if (equals(Player.me)) {
				return 1f;
			} else {
				return 0f;
			}
		} else if (list_condition.contains(CONDITION.仮眠)) {
			return 0f;
		} else if (list_condition.contains(CONDITION.睡眠)) {
			return 0f;
		} else if (list_condition.contains(CONDITION.暗闇)) {
			return 0f;
			// } else if (list_condition.contains(CONDITION.安心)) {
			// return 0f;
		} else if (list_condition.contains(CONDITION.特殊仮眠)) {
			return 0f;
		} else if (list_condition.contains(CONDITION.麻痺)) {
			return 0f;
		} else if (list_condition.contains(CONDITION.鈍足)) {
			return 0.5f;
		} else if (list_condition.contains(CONDITION.倍速)) {
			return 2f;
		} else if (list_condition.contains(CONDITION.三倍速)) {
			return 3f;
		}
		return 1f;
	}

	public int getPowerUp() {
		return power_up_count;
	}

	@Override
	public final Point getScreenPoint() {
		if (beated_away_angle > 0) {
			return new Point(screen_point.x + beated_away_point.x,
					screen_point.y + beated_away_point.y);
		} else if (normal_attack_phase != null) {
			return getAttackPoint();
		}
		return super.getScreenPoint();
	}

	public Base_Anime getSecondAnimation() {
		return second_effect;
	}

	public int getShadowSize() {
		if (!MassCreater.getMass(getMassPoint()).WALKABLE)
			return 0;
		if (conditionCheck(CONDITION.やりすごし))
			return getShadowSize100();
		else
			return getShadowSize100() * size / 100;
	}

	protected int getShadowSize100() {
		return 25;
	}

	public int getShadowY() {
		return 3;
	}

	public int getSize() {
		return size;
	}

	public int getSTR() {
		return STR;
	}

	public BufferedImage getWalkDot() {
		return IM.getWalkDot(LV - 1);
	}

	/**
	 * 幽霊にダメージ
	 */
	public void heal(Base_Creature source, int value) {
		value = value * getGhost();
		if (chengeHP(null, null, value) && source != null)
			source.setExpCash(getENEMY_EXP());
	}

	protected boolean isAbleToAttack_NowDirecting() {
		if (isAbleToAttackWhileNeglectingWall()) {
			return true;
		}
		Point p = mass_point.getLocation();
		if (direction.isBias()) {
			for (DIRECTION d : direction.getNeiboringDirections3()) {
				if (!MassCreater.getMass(d.getFrontPoint(p.getLocation())).WALKABLE) {
					return false;
				}
			}
			return true;
		} else {
			return MassCreater.getMass(direction.getFrontPoint(p)).WALKABLE;
		}
	}

	public boolean isAbleToAttackWhileNeglectingWall() {
		return isWall_walk();
	}

	public boolean isAbleToFeel() {
		if (EnchantSpecial.toushi())
			return true;
		if (!MassCreater.isSpotValid()) {
			Point p = Player.me.getMassPoint();
			for (Mass m : MassCreater.getMasses_NotSpotValid(p.x, p.y)) {
				if (m.X == getMassPoint().x && m.Y == getMassPoint().y) {
					return true;
				}
			}
		}
		return MassCreater.isThePointInThePlayerSight(getMassPoint(), 1, 1);
	}

	public final boolean isAbleToIgnoreWalls() {
		return isWall_walk();
	}

	@Override
	public boolean isAnimating() {
		if (super.isAnimating()) {
			return true;
		}
		if (telepo != -1) {
			return true;
		}
		if (getAnimation() != null) {
			return true;
		}
		if (getSecondAnimation() != null) {
			return true;
		}
		if (isBeatedAway()) {
			return true;
		}
		if (normal_attack_phase != null) {
			return true;
		}
		return false;
	}

	protected boolean isAttacking() {
		return (normal_attack_phase != null);
	}

	public boolean isAttackPhaseEnd() {
		return normal_attack_phase == null;
	}

	public boolean isBadCondition() {
		for (CONDITION con : CONDITION.values_bad()) {
			if (list_condition.contains(con)) {
				return true;
			}
		}
		return false;
	}

	public boolean isBeatedAway() {
		return beated_away_angle > 0;
	}

	public boolean isBoating() {
		return !conditionCheck(CONDITION.封印) && boating;
	}

	/**
	 * for test
	 * 
	 * @return
	 */
	public boolean isCHARA_IMAGE_ARROW() {
		return IM == CHARA_IMAGE.arrow;
	}

	public boolean isFire() {
		return !conditionCheck(CONDITION.封印)
				&& (Fire || conditionCheck(CONDITION.炎上));
	}

	public boolean isFlagWarning() {
		return flag_warning;
	}

	public boolean isFlying() {
		return floating;
	}

	public boolean isIce() {
		return !conditionCheck(CONDITION.封印) && Ice;
	}

	/**
	 * 
	 * @return 通常攻撃無効
	 */
	public boolean isInValidOnAttack() {
		return false;
	}

	public boolean isItemDrop() {
		return item_drop;
	}

	public boolean isMagicAttack() {
		return !conditionCheck(CONDITION.封印) && Magic;
	}

	public boolean isMaxHp() {
		return getMAX_HP() <= HP;
	}

	public boolean isPassiveSkillActive() {
		return HP > 0 && !conditionCheck(CONDITION.封印)
				&& !conditionCheck(CONDITION.ええんじゃないか)
				// && !conditionCheck(CONDITION.麻痺)
				&& !TurnSystemController.time_stop;
	}

	public boolean isPlayerSide() {
		return player_side;
	}

	protected boolean isRandom_walk() {
		return isPassiveSkillActive() && random_walk;
	}

	public boolean isResistantToGrassWave() {
		return false;
	}

	public boolean isShownOnMiniMap() {
		if (EnchantSpecial.toushi())
			return true;
		if (conditionCheck(CONDITION.透明))
			if (!Player.me.conditionCheck(CONDITION.目薬)
					&& !EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
							ENCHANT_SIMBOL.識))
				return false;
		return MassCreater.isThePointInThePlayerSight(getMassPoint(), 1, 1);
	}

	/**
	 * 
	 * @return 能力の使えない状態異常でなく、かつHPが1以上の敵ならtrue
	 */
	public boolean isSkillActive() {
		return isSkillActive(false);
	}

	/**
	 * @param active_in_dying
	 *            trueならHPの分岐をしない
	 * @return 能力の使えない状態異常でなく、かつHPが1以上の敵ならtrue
	 */
	public boolean isSkillActive(boolean active_in_dying) {
		// FIXME
		// if (!(this instanceof Base_Enemy))
		// return false;
		CONDITION[] cs1 = { CONDITION.仮眠, CONDITION.ええんじゃないか, CONDITION.封印,
				CONDITION.特殊仮眠, CONDITION.麻痺, CONDITION.混乱, CONDITION.睡眠,
				CONDITION.暗闇, CONDITION.天邪鬼 };
		CONDITION[] cs2 = { CONDITION.ええんじゃないか, CONDITION.封印 };
		CONDITION[] cs = isSkillActiveAnytimeButSealed() ? cs2 : cs1;
		for (CONDITION condition : cs) {
			if (conditionCheck(condition))
				return false;
		}
		if (active_in_dying)
			return true;
		return HP > 0 && !TurnSystemController.time_stop;
	}

	protected boolean isSkillActiveAnytimeButSealed() {
		return false;
	}

	/**
	 * 霊夢がすり抜けたりする
	 * 
	 * @param a
	 * 
	 * @return
	 */
	public boolean isThroughItem(Base_Artifact a) {
		return false;
	}

	public boolean isThunder() {
		return !conditionCheck(CONDITION.封印) && Thunder;
	}

	public boolean isVisible() {
		return conditionCheck(CONDITION.透明);
	}

	protected boolean isWall_walk() {
		return isPassiveSkillActive() && wall_walk;
	}

	/**
	 * 
	 * @return isFlying() || isIce() || isBoating();
	 */
	public boolean isWatering() {
		return isFlying() || isBoating() || isIce();
	}

	/**
	 *
	 */
	private void itemDrop() {
		if (!PresentField.get().isItemDrop()) {
			return;
		}
		if (!item_drop) {
			return;
		}
		if ((int) (new R().nextDouble() * 10000) < 印宝.effect(itemDropParcent())) {
			setOnDropItem();
		}
	}

	/**
	 * ドロップ率 overrideして使う
	 * 
	 * @return 100_00=100％
	 */
	protected int itemDropParcent() {
		return 250;
	}

	public final boolean itemHit(Base_Artifact a, boolean ento,
			Base_Creature source, Base_Creature target) {
		CONDITION.conditionEffectCase(this, CONDITION.仮眠);
		CONDITION.conditionEffectCase(this, CONDITION.特殊仮眠);
		CONDITION.conditionEffectCase(this, CONDITION.安心);
		CONDITION.conditionEffectCase(this, CONDITION.やりすごし);
		if (!isSkillActive())
			return true;
		if (a instanceof Base_Device) {
			return true;
		}
		return itemHitEffect(a, ento);
	}

	protected boolean itemHitEffect(Base_Artifact a, boolean ento) {
		if (a instanceof MagicBullet || a instanceof 目からビーム
				|| a instanceof ドラゴンブレス) {
			return true;
		}
		if (!ento && conditionCheck(CONDITION.炎上)) {
			MainMap.addEffect(new FireEffect(mass_point, null), true);
			if (a.isCold()) {
				setConditionRemoveTask(CONDITION.炎上);
			}
			Message.set(a.getColoredName(), "は燃え尽きた");
			return false;
		} else {
			return true;
		}
	}

	public void load() {
		attack_task = new ArrayList<Task>(8);
	}

	protected boolean movable_water_check(Point p) {
		Mass m = MassCreater.getMass(p);
		if (!m.WATER)
			return true;
		return isWatering();
	}

	protected abstract String nameLevel();

	public void nodamageGetExp() {
		Player.me.setExpCash(this.getENEMY_EXP());
	}

	public void normalAttack(boolean p_or_e) {
		final Base_Creature c = this;
		if (conditionCheck(CONDITION.混乱)) {
			direction = DIRECTION.values_exceptNeatral()[new R().nextInt(8)];
		} else {
			direction = converDirection(Player.me.getMassPoint());
			if (getConditionList().contains(CONDITION.天邪鬼)) {
				direction = DIRECTION.backDirection(direction);
			}
		}
		startAttack(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				// if (!c.getConditionList().contains(CONDITION.天邪鬼)) {
				if (!isAbleToAttack_NowDirecting())
					return;
				Base_Creature target = null;
				isAbleToAttackWhileNeglectingWall();
				if (Player.me.getMassPoint().equals(
						c.getDirection().getFrontPoint(
								c.getMassPoint().getLocation()))) {
					target = Player.me;
				} else {
					target = MapInSelect.getFrontEnemyFromCreature(c);
				}
				if (target != null) {
					if (c.conditionCheck(CONDITION.ええんじゃないか)) {
						Damage.enemyCriticalAttack(c, target);
					} else {
						int dam = Damage.normalAttack(c, target);
						if (isSkillActive())
							drain(dam);
					}
				}
				// } else {
				// 天邪鬼.effect(c);
				// }
			}
		});
	}

	public boolean playerIsInNeiver() {
		return false;
	}

	public boolean playerLifeCheck(Object obj) {
		if (Player.me.getHP() <= 0) {
			if (復活.recovery()) {
				Medal.復活回数.addCount();
				return false;
			}
			// EnchantSpecial.sendStrage();
			if (Switch.test && Switch.switch_player_no_death) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public void playerTrapDamage(Object obj, String str, int damage) {
		Player.me.chengeHP(obj, str, -damage);
	}

	private CONDITION quickCondition(CONDITION c) {
		if (c == CONDITION.鈍足) {
			if (list_condition.contains(CONDITION.倍速)) {
				CONDITION.setEnemyNormalSpeed(this);
				return null;
			} else if (list_condition.contains(CONDITION.三倍速)) {
				CONDITION.setEnemyNormalSpeed(this);
				return CONDITION.倍速;
			} else {
				return c;
			}
		} else if (c == CONDITION.倍速) {
			if (list_condition.contains(CONDITION.鈍足)) {
				CONDITION.setEnemyNormalSpeed(this);
				return null;
			} else if (list_condition.contains(CONDITION.倍速)) {
				CONDITION.setEnemyNormalSpeed(this);
				return CONDITION.三倍速;
			} else if (list_condition.contains(CONDITION.三倍速)) {
				return null;
			} else {
				return c;
			}
		} else if (c == CONDITION.三倍速) {
			if (list_condition.contains(CONDITION.鈍足)) {
				CONDITION.setEnemyNormalSpeed(this);
				return CONDITION.倍速;
			} else {
				return c;
			}
		} else {
			return c;
		}
	}

	public boolean recoveryGhost() {
		if (this.getGhost() != 1 && this.conditionCheck(CONDITION.邪法)) {
			SE.SYSTEM_CURSE.play();
			setTelepoteAnimation(true, null);
			CONDITION.conditionRecovery(this, CONDITION.邪法);
			Message.set(getColoredName(), "は邪法の力でよみがえった");
			chengeHP(null, null, this.getGhost() == 0 ? 1 : getMAX_HP() / 2);
			return true;
		}
		return false;
	}

	public boolean reflection(ThrowingItem ti) {
		return conditionCheck(CONDITION.反射);
	}

	protected boolean resistCondition(CONDITION c) {
		return false;
	}

	// 無効以外のアクション
	public boolean resistOrWeakAction(ResistWeakPoint res, int damage) {
		return false;
	}

	protected void setAnimation(Base_Anime a) {
		effect = a;
		MainMap.setAnimation(this);
	}

	protected void setBeatedAway() {
		beated_away_angle = new R().nextInt(30) - 15;
		beated_away_angle += (direction.ROTATE + 4) * 45;
	}

	public void setBombedAway() {
		setBeatedAway();
	}

	public boolean setCondition(CONDITION c, int timer) {
		if (resistCondition(c)) {
			return false;
		}
		this.setConditionList(c, timer);
		return true;
	}

	private Base_Creature setConditionList(CONDITION condition, int timer) {
		if (this instanceof Player) {
			印厄.effect(condition);
			印嫉妬.effect(condition);
			if (印病.effect(condition)) {
				return this;
			}
		} else if (this instanceof Base_Enemy) {
			if (condition == CONDITION.おにぎり) {
				TaskOnMapObject.addEnemyRemoveTask(this);
				ItemFall.itemFall(new R().nextInt(2) == 0 ? new ミシャグジおにぎり(
						mass_point) : new 奇跡のおにぎり(mass_point));
				return this;
			}
		}
		if (condition == CONDITION.倍速 || condition == CONDITION.三倍速
				|| condition == CONDITION.鈍足) {
			condition = quickCondition(condition);
		}
		if (list_condition.contains(condition)) {
			if (condition == CONDITION.死) {
				condition_map.put(condition,
						(condition_map.get(condition) + 1) / 2);
			}
			return this;
		}
		if (condition == CONDITION.封印) {
			CONDITION.conditionAllClear(this);
			if (this instanceof Base_Enemy
					&& !MassCreater.getMass(mass_point).WALKABLE) {
				Medal.壁の中の敵を封印した.addCount();
			}
		}
		if (condition == null) {
			return this;
		}
		CONDITION.setConditionTime(this, condition, timer);
		list_condition.add(condition);
		return this;
	}

	public boolean setConditionNeglectResist(CONDITION c, Integer timer) {
		this.setConditionList(c, timer);
		return true;
	}

	public void setConditionRemoveTask(CONDITION condition) {
		condition_remove_task.add(condition);
	}

	/**
	 * 通常はstartDamagingとendDamagingを呼ぶこと 音ゲーで必要だったので作成<br/>
	 * 
	 * @param flag
	 */
	public void setDamaging(boolean flag) {
		flag_damaging = flag;
	}

	public final void setDEF(int dEF) {
		DEF = dEF;
	}

	public void setENEMY_EXP(int eNEMY_EXP) {
		ENEMY_EXP = eNEMY_EXP;
	}

	protected void setExpCash(int enemy_EXP2) {
		addLV(1);
	}

	/**
	 * 最大HP,STR,DEF、現在のHP,STR,DEFを引数の値にする。
	 */
	public final void setFirstStatus(int hp, int str, int def) {
		setMAX_HP(HP = hp);
		MAX_STR = STR = str;
		MAX_DEF = DEF = def;
	}

	public final void setHP(int hP) {
		if (HP + hP >= getMAX_HP()) {
			HP = getMAX_HP();
		}
		HP = hP;
	}

	public void setItemDrop(boolean b) {
		item_drop = b;
	}

	public void setLV_NoMessage(int goal) {
		if (this instanceof Base_NPC) {
			return;
		} else {
			int lv = LV;
			LV = getLV(goal);
			if (LV != lv) {
				setNameAndStatus();
			}
		}
	}

	@Override
	public void setMassPoint_Jump(Point p, Task task) {
		super.setMassPoint_Jump(p, task);
		setTelepoteAnimation(false, null);
	}

	public void setMassPoint_ParabolaJump_Smalling(Point p, Task task) {
		flag_changing_size = -1;
		super.setMassPoint_ParabolaJumpAttack(p, task);
	}

	public void setMassPoint_ParabolaJump_Undo(Point p, Task task) {
		flag_changing_size = 1;
		super.setMassPoint_ParabolaJumpAttack(p, task);
	}

	public final void setMAX_DEF(int mAX_DEF) {
		MAX_DEF = mAX_DEF;
	}

	public final void setMAX_HP(int value) {
		MAX_HP = value;
	}

	public void setMute(boolean b) {
		flag_mute = b;
	}

	public void setNameAndStatus() {
		List<Integer> list = statusLevel();
		name = nameLevel();
		try {
			setMAX_HP(HP = list.get(0));
			MAX_STR = STR = (list.get(1));
			MAX_DEF = DEF = (list.get(2));
			setENEMY_EXP(list.get(3));
		} catch (IndexOutOfBoundsException e) {
			setMAX_HP(HP = 1);
			MAX_STR = STR = 1;
			MAX_DEF = DEF = 1;
			setENEMY_EXP(1);
		}
	}

	public void setOnDropItem() {
		Base_Artifact item = getDropItem();
		if (item == null) {
			return;
		}
		ItemFall.itemFall(getMassPoint(), item);
		setItemDrop(false);
	}

	public void setPlayerSide(boolean b) {
		player_side = b;
	}

	protected void setRandom_walk(boolean random_walk) {
		this.random_walk = random_walk;
	}

	public void setSecondAnimation(Base_Anime a) {
		second_effect = a;
		SecondAnime.setAnimation(this);
	}

	public void setSTR(int sTR) {
		if (sTR <= 1)
			STR = 1;
		else
			STR = sTR;
	}

	public void setTelepoteAnimation(boolean half, Task task) {
		telepo = half ? -2 : 0;
		telepo_task = task;
	}

	protected void setWall_walk(boolean wall_walk) {
		this.wall_walk = wall_walk;
	}

	public void skipDrawFrame() {
		telepo = -1;
	}

	public boolean staffHitCheck(Base_Artifact a) {
		CONDITION.conditionEffectCase(this, CONDITION.仮眠);
		CONDITION.conditionEffectCase(this, CONDITION.特殊仮眠);
		CONDITION.conditionEffectCase(this, CONDITION.安心);
		return staffHitEffect(a);
	}

	protected boolean staffHitEffect(Base_Artifact a) {
		return true;
	}

	public void startAttack(Task t) {
		SE se = getAttackSE();
		if (!flag_mute && se != null) {
			se.play();
		}
		attacking_direction = direction;
		normal_attack_phase = AttackPhase.GO;
		attack_task.add(t);
	}

	public void startDamaging() {
		TaskOnMapObject.setDamaging(this);
		flag_damaging = true;
	}

	protected abstract List<Integer> statusLevel();

	@Override
	public void upDate() {
		if (beated_away_angle > 0) {
			upDate_BeatedAway();
		} else if (normal_attack_phase != null) {
			upDate_NormalAttack();
		} else {
			super.upDate();
		}
	}

	private void upDate_BeatedAway() {
		int dx = (int) Math.round(Math.cos(2 * Math.PI * beated_away_angle
				/ 360)
				* MAP.MoveSpeed * 4);
		int dy = (int) Math.round(Math.sin(2 * Math.PI * beated_away_angle
				/ 360)
				* MAP.MoveSpeed * 4);
		beated_away_point.translate(dx, dy);
		if (dangeon.view.util.WithinOutofScreen.isOutside(this)) {
			TaskOnMapObject.addEnemyRemoveTask(this);
		}
	}

	@Override
	protected void upDate_Jump() {
		if (flag_changing_size != 0) {
			size += flag_changing_size * 5 * SIZE_MAX / 100;
			if (size > SIZE_MAX)
				size = SIZE_MAX;
			if (size < 0)
				size = 0;
		}
		super.upDate_Jump();
	}

	protected void upDate_NormalAttack() {
		attaking_frame++;
		if (!(this instanceof Player)) {
			if (conditionCheck(CONDITION.三倍速)) {
				attaking_frame++;
				attaking_frame++;
			}
			if (conditionCheck(CONDITION.倍速)
					|| (Player.me.conditionCheck(CONDITION.鈍足))
					&& !isPlayerSide()) {
				attaking_frame++;
			}
		}
		switch (normal_attack_phase) {
		case GO:
			int FR = 3;
			if (attaking_frame < FR) {
				attack_No = 0;
				return;
			} else if (attaking_frame < FR * 2) {
				attack_No = 1;
			} else if (attaking_frame < FR * 3) {
				attack_No = 2;
			} else {
				normal_attack_phase = AttackPhase.BACK;
			}
			if (!(Math.abs(attack_delt_point.x) > MAP.TILE_SIZE / 3 || Math
					.abs(attack_delt_point.y) > MAP.TILE_SIZE / 3)) {
				attack_delt_point.translate(direction.X * MAP.MoveSpeed,
						direction.Y * MAP.MoveSpeed);
			}
			break;
		case BACK:
			attack_delt_point.translate(-direction.X * MAP.MoveSpeed,
					-direction.Y * MAP.MoveSpeed);
			if (attack_delt_point.x * direction.X <= 0
					&& attack_delt_point.y * direction.Y <= 0) {
				attack_delt_point.setLocation(0, 0);
				normal_attack_phase = null;
				attack_No = 0;
				attaking_frame = -1;
			}
			break;
		}
		while (!attack_task.isEmpty()) {
			Task t = attack_task.get(0);
			if (t != null)
				t.work();
			attack_task.remove(0);
		}
	}

	public void waterAction(Mass m) {
		if (isWatering() && !isIce()) {
			if (this instanceof 洩矢諏訪子) {
				m.setWaterLeefOn(false);
			} else if (this instanceof 風見幽香) {
				m.setWaterLeefOn(false);
				m.FLOWER = true;
			} else if (this instanceof わかさぎ姫) {
				m.WATER_LEEF = false;
			}
			return;
		}
		if (this instanceof Player) {
			if (!PresentField.get().isHaraheru() || isIce()) {
				Message.set(getColoredName(), "は水路を凍らせた");
				SE.ICE.play();
				m.setFrozen_includingNeibors();
				MassCreater.retakeMassSet();
			} else if (!印邪.isWallWalk()) {
				SpecialAction.高飛び(this);
				Medal.水路の上に乗ってワープした.addCount();
			}
		} else if (this instanceof Base_Enemy) {
			if (isIce()) {
				Message.set(getColoredName(), "は水路を凍らせた");
				SE.ICE.play();
				m.setFrozen_includingNeibors();
				MassCreater.retakeMassSet();
			} else if (!(this instanceof Base_NPC)) {
				SE.ATTACK_WATER.play();
				Message.set(getColoredName(), "はおぼれてしまった");
				chengeHP_NoEffect(-999);
			}
		}
	}

}
