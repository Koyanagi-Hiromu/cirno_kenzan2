package dangeon.model.condition;

import java.util.ArrayList;
import java.util.List;

import main.res.Image_Condition;
import main.util.CSVLoadSupporter;
import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

/**
 * 浮遊状態は消えました<br />
 * enemytableでflyingをtrueにするか<br />
 * isFlyingをオーバーライドしてください<br />
 * 
 * @author weray
 * 
 */
public enum CONDITION {
	// goodStatus　共通
	倍速, 三倍速, やりすごし, 透明, 反射,
	// goodStatus　チルノonly
	目薬, 透視, パワーアップ, 蛍,
	// BadStatus　共通
	睡眠, 麻痺, 混乱, 鈍足, 封印, 影縫い,
	// BadStatus　幽霊
	死, 邪法,

	// midd　敵only
	仮眠, 特殊仮眠,

	炎上,

	イカリ, ええんじゃないか, 安心,
	// bad　敵only
	天邪鬼,
	// good（*,true,false）　敵only

	// カード
	回復, 超会心, 必中会心, 絶対回避, イリュージョン, 復活,
	// BadStatus　チルノonly
	おにぎり,
	// 没
	絶対必中, 暗闇, 爆弾,

	;

	// 没
	// 目潰し( false, 1, 1, false), 身代わり( false, 1, 1, false), 痛み分け(
	// false, 1, 1, false), 怯え( false, 1, 1, false), ;
	// private final List<CONDITION> good_condition = new ArrayList<CONDITION>()
	// {
	// /**
	// *
	// */
	// private static final long serialVersionUID = 1L;
	// {
	// add(蛍);
	// add(三倍速);
	// add(倍速);
	// add(回復);
	// add(目薬);
	// add(透視);
	// add(パワーアップ);
	// add(復活);
	// add(超会心);
	// add(回避);
	// add(無意識);
	// }
	// };

	public static boolean check(Base_Creature c, CONDITION con) {
		for (CONDITION condition : c.getConditionList()) {
			if (con == condition) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 状態異常全て回復
	 * 
	 * @param creature
	 */
	public static void conditionAllClear(Base_Creature creature) {
		creature.getConditionList().clear();
		creature.condition_map.clear();
	}

	/**
	 * 状態異常チェック
	 * 
	 * @param condition
	 * @param creature
	 */
	public static void conditionCheck(Base_Creature creature) {
		CONDITION.conditionTurnCount(creature);
		for (CONDITION condition : creature.getConditionRemoveTask()) {
			creature.getConditionList().remove(condition);
		}
		creature.getConditionRemoveTask().clear();
		CONDITION.conditionEffect(creature);
	}

	/**
	 * ターンを飛ばすコンディションチェック
	 * 
	 * @param creature
	 */
	public static boolean conditionEffect(Base_Creature creature) {
		for (CONDITION condition : creature.getConditionList()) {
			switch (condition) {
			case 睡眠:
			case 暗闇:
				// case 安心:
			case 仮眠:
			case 特殊仮眠:
				if (creature == Player.me) {
					TurnSystemController.setPlayerConditionSleep();
				}
			case 麻痺:
				if (creature == Player.me) {
					TurnSystemController.callMeToStartEnemyTurn();
				} else {
					((Base_Enemy) creature).enemy_actioned = true;
				}
				return true;
			case やりすごし:
				if (creature == Player.me
						&& !EnchantSpecial
								.enchantSimbolAllCheck(ENCHANT_SIMBOL.食)) {
					TurnSystemController.setPlayerConditionSleep();
					TurnSystemController.callMeToStartEnemyTurn(true);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 個別に状態異常チェック
	 * 
	 * @param creature
	 * @param condition
	 */
	public static boolean conditionEffectCase(Base_Creature creature,
			CONDITION condition) {
		for (CONDITION con : creature.getConditionList()) {
			if (con == condition) {
				switch (condition) {
				case 睡眠:
					Sleep.conditionEffect(creature);
					return true;
				case 暗闇:
					Sleep.暗闇(creature);
					return true;
				case 混乱:
					Confusion.conditionEffect(creature);
					return true;
					// case 怯え:
					// Panic.conditionEffect(creature);
					// return true;
				case 安心:
				case 麻痺:
				case 仮眠:
				case 特殊仮眠:
					creature.condition_map.remove(con);
					creature.setConditionRemoveTask(con);
					return true;
				case やりすごし:
					creature.condition_map.remove(con);
					creature.getConditionList().remove(con);
					return true;
				}
			} else
				continue;
		}
		for (CONDITION con : creature.getConditionRemoveTask()) {
			creature.getConditionList().remove(con);
		}
		creature.getConditionRemoveTask().clear();
		return false;
	}

	/**
	 * エネミーの状態異常継続チェック
	 */
	public static void conditionEnemyContinue() {
		for (Base_Enemy enemy : MapList.getListEnemy()) {
			CONDITION.conditionCheck(enemy);
		}
	}

	public static void conditionOneEnemyContinue(Base_Creature creature) {
		CONDITION.conditionCheck(creature);
	}

	/**
	 * プレイヤーの状態異常継続チェック
	 */
	public static void conditionPlayerContinue() {
		CONDITION.conditionCheck(Player.me);
	}

	/**
	 * 状態異常を回復する
	 * 
	 * @param creature
	 * @param condition
	 */
	public static void conditionRecovery(Base_Creature creature,
			CONDITION... con) {
		for (CONDITION condition : con) {
			if (!creature.getConditionList().contains(condition)) {
				return;
			}
			creature.condition_map.remove(condition);
			creature.setConditionRemoveTask(condition);
		}
	}

	public static void conditionRemove() {
		for (Base_Enemy enemy : MapList.getListEnemy()) {
			for (CONDITION condition : enemy.getConditionRemoveTask()) {
				enemy.getConditionList().remove(condition);
			}
			enemy.getConditionRemoveTask().clear();
		}
	}

	/**
	 * コンディションをランダムに返す。
	 * 
	 * @param c
	 * @return
	 */
	public static CONDITION conditionSelect(CONDITION c) {
		if (c == null) {
			/**
			 * ぬえ
			 */
			List<CONDITION> list = getBadConditionList();
			int select = new R().nextInt(list.size());
			return list.get(select);
		} else {
			return c;
		}
	}

	/**
	 * 状態異常のターンカウントを進める ０以下になると、状態異常解除
	 * 
	 * @param creature
	 */
	public static void conditionTurnCount(Base_Creature creature) {
		int i = 0;

		for (int j = 0; j <= i; j++) {
			for (CONDITION c : creature.getConditionListNow()) {
				if (c.equals(必中会心)) {
					continue;
				}
				Integer count = creature.condition_map.get(c);
				if (count == null || count <= 0) {
					continue;
				}
				creature.condition_map.put(c, count - 1);
				if (count - 1 <= 0) {
					if (c == CONDITION.死) {
						Death.effect(creature);
					}
					conditionRecovery(creature, c);
					return;
				}
			}
		}
	}

	public static boolean enemyDontMove(Base_Creature creature) {
		for (CONDITION condition : creature.getConditionList()) {
			switch (condition) {
			case 暗闇:
				return true;
			case 睡眠:
				return true;
			case 麻痺:
				Paralyze.conditionEffect(creature);
				return true;
				// case 安心:
			case 仮眠:
			case 特殊仮眠:
				return true;
			}
		}
		return false;
	}

	private static List<CONDITION> getBadConditionList() {
		List<CONDITION> list = new ArrayList<CONDITION>();
		for (CONDITION c : CONDITION.values()) {
			if (c.is_bad)
				list.add(c);
		}
		return list;
	}

	/**
	 * やりすごし, 仮眠, 特殊仮眠, 睡眠, 麻痺
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isMovable(Base_Creature c) {
		CONDITION cs[] = { 仮眠, 特殊仮眠, 睡眠, 麻痺 };
		for (CONDITION condition : cs)
			if (c.conditionCheck(condition))
				return false;
		return true;
	}

	/**
	 * プレイヤー
	 * 
	 * @param p
	 * @param c
	 * @return
	 */
	public static boolean isSetCondition(Player p, CONDITION c) {
		switch (c) {
		case 混乱:
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.春)) {
				Message.set("しかし頭が晴れやかで混乱はすぐ治った");
				return true;
			}
			if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.瞳)) {
				Message.set("しかし波長を操ることで混乱から立ち直った");
				return true;
			}
			break;
		case 睡眠:
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.覚醒)
					|| EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
							ENCHANT_SIMBOL.春)) {
				Message.set("しかし元気いっぱいですぐ起きてしまった");
				return true;
			}
			break;
		case 鈍足:
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.春)) {
				Message.set("しかし元気いっぱいで鈍足はすぐ治った");
				return true;
			}
			break;
		case 麻痺:
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.春)) {
				Message.set("しかし元気いっぱいで金縛りはすぐ治った");
				return true;
			}
		case 封印:
		case 影縫い:
		case イカリ:
		case 死:
		case 邪法:
		case おにぎり:
		default:
			break;
		}
		return false;
	}

	public static int powerUp(Base_Creature creature, int damage, boolean attack) {
		for (CONDITION con : creature.getConditionList()) {
			if (con == パワーアップ) {
				if (attack)
					return damage * 150 / 100;
				else
					return damage * 75 / 100;
			}
		}
		return damage;
	}

	public static void quickRemoveCondition(CONDITION con, Base_Creature c) {
		if (!c.getConditionList().contains(con)) {
			return;
		}
		c.condition_map.remove(con);
		c.getConditionList().remove(con);
	}

	/**
	 * 状態異常の長さを設定する
	 * 
	 * @param creature
	 * @param condition
	 * @param timer
	 */
	public static void setConditionTime(Base_Creature creature,
			CONDITION condition, int timer) {
		if (timer == 0) {
			if (creature instanceof Player) {
				timer = condition.p_time;
			} else {
				timer = condition.e_time;
			}
		} else if (timer == -1) {
			if (creature instanceof Player) {
				timer = condition.p_time / 2;
			} else {
				timer = condition.e_time / 2;
			}
		} else if (timer == -2) {
			timer = 0;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.病)) {
			if (condition.isGood() && timer > 0) {
				timer = timer * 2;
			}
		}
		creature.condition_map.put(condition, timer);
	}

	public static void setEnemyNormalSpeed(Base_Creature creature) {
		for (CONDITION con : creature.getConditionList()) {
			if (con == CONDITION.鈍足)
				conditionRecovery(creature, con);
			if (con == CONDITION.倍速)
				conditionRecovery(creature, con);
			if (con == CONDITION.三倍速)
				conditionRecovery(creature, con);
		}
		for (CONDITION con : creature.getConditionRemoveTask()) {
			creature.getConditionList().remove(con);
		}
		creature.getConditionRemoveTask().clear();
	}

	public static ArrayList<CONDITION> values_bad() {
		ArrayList<CONDITION> list = new ArrayList<CONDITION>();
		for (CONDITION con : values()) {
			if (!con.is_good) {
				list.add(con);
			}
		}
		return list;
	}

	public static void wakeUp(Base_Creature c) {
		conditionRecovery(c, CONDITION.仮眠);
		conditionRecovery(c, CONDITION.特殊仮眠);
		conditionRecovery(c, CONDITION.安心);
	}

	public static void 邪法(Base_Creature c) {
		if (c.getConditionList().contains(CONDITION.邪法)) {
			if (c.getGhost() != 1) {
				return;
			}
			int i = c instanceof Player ? 50 : 30;
			int damage = c.getMAX_HP() / i;
			if (damage < 1) {
				damage = 1;
			}
			if (c instanceof Player) {
				if (c.getHP() < damage) {
					damage = c.getHP() - 1;
				}
				if (damage < 1) {
					return;
				}
			}
			EnchantSpecial.setFlagNeglectHeal(true);
			c.chengeHP(null, "邪法によって蝕まれた", -damage);
		}
	}

	public final int p_time;

	public final int e_time;

	// public static boolean conditionReturn(Base_Creature creature, CONDITION
	// con) {
	// for (CONDITION condition : creature.getConditionList()) {
	// if (condition == con)
	// return true;
	// }
	// return false;
	// }

	public final Image_Condition IMA;

	public final boolean is_good, is_bad;

	public final String NAME, ORIGIN;

	public final String[] TEXTS;

	/**
	 * true なら チルノ false なら 敵 null ならどっちでも
	 */
	public final Boolean ONLY_FOR;

	private CONDITION() {
		CSVLoadSupporter<String> list = CSVLoadSupporter.loadCSV(name(),
				CONDITION.class, "\t");
		NAME = list.get();
		is_good = list.get(1).matches("TRUE");
		is_bad = list.get().matches("FALSE");
		p_time = list.toI();
		e_time = list.toI();
		IMA = Image_Condition.get(list.get());
		ONLY_FOR = list.is();
		ORIGIN = list.get();
		TEXTS = list.get().split("\n");
	}

	public boolean isBad() {
		return is_bad;
	}

	public boolean isGood() {
		return is_good;
	}

}
