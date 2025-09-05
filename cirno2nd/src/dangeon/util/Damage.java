package dangeon.util;

import java.awt.Color;
import java.util.Random;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.condition.Illusion;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印ダメージUP;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印密;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印殺;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印緋;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印雷;
import dangeon.model.object.artifact.item.spellcard.魂魄妖夢のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.封獣ぬえ;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.Battle.Battle;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;
import dangeon.view.anime.MissEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.util.StringFilter;
import main.res.SE;
import main.util.FrameShaker;
import main.util.半角全角コンバーター;

public class Damage {
	private static int attack_miss = 5;

	public static final Color enemy_color = new Color(200, 100, 200);

	private static int againstMagicDeffence(Base_Creature source, int damage) {
		if (source.isMagicAttack()) {
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.魔)) {
				return damage / 2;
			}
		} else if (source.isFire()) {
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.炎)) {
				damage /= 2;
			} else if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
					ENCHANT_SIMBOL.火)) {
				damage -= 10;
			}
		}
		return damage;
	}

	public static Integer convert_2geki(Base_Creature target, int damage) {
		if (target instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.頑)) {
				if (Player.me.getMAX_HP() == Player.me.getHP()) {
					return 1;
				}
			}
		}
		Base_Map bmr = PresentField.get();
		if (bmr != null && bmr.is2GEKI()) {
			if (target instanceof Player) {
				if (EnchantSpecial.isFlagNeglectHeal()) {
					return damage;
				}
			}
			if (target.getHP() <= 1) {
				damage = 1;
			} else {
				damage = target.getHP() - 1;
			}
			return damage;
		}
		return null;
	}

	private static void counterMonster(int damage, Base_Creature source,
			Base_Creature target) {
		if (target.getHP() <= 0) {
			return;
		}
		if (!target.isSkillActive() || !target.counterAttackEnemy()) {
			return;
		}
		damage = (damage * 2) / 10;
		if (damage <= 0) {
			damage = 1;
		}
		damageNoMessage(target, "反射ダメージによって", target, source, damage);
	}

	public static void counterSetDirection(Base_Creature source) {
		if (!source.playerIsInNeiver()) {
			return;
		}
		if (Player.me.last_attack != null) {
			if (MapList.getListEnemy().contains(Player.me.last_attack)) {
				return;
			}
		}
		if (MapList.getCreature(
				Player.me.getMassPoint().x + Player.me.getDirection().X,
				Player.me.getMassPoint().x + Player.me.getDirection().Y) == null) {
			Player.me.setDirection(Player.me.converDirection(source
					.getMassPoint()));
		}

	}

	/**
	 * プレイヤーまたはエネミーに 引数のダメージを与える ダメージメッセージは出る。
	 * 
	 * @param death_message
	 *            リザルト用 プレイヤーへのダメージを与えたclass ○○によって倒されたの○○の部分
	 * @param msg
	 *            「XXXXのダメージを受けた」の直前に入る文字　例：落下の衝撃によって
	 * @param str
	 *            リザルト用 特別な文章以外はnull
	 * @param damage
	 *            このダメージを与える
	 */
	public static int damage(Object death_message, String msg, String str,
			Base_Creature source, Base_Creature target, int damage) {
		if (source != null && source.getName().matches("_NO_DATA_"))
			source = null;
		damageMessage(source, msg, target, str, damage);
		if (target instanceof Player) {
			Player.me.chengeHP(death_message, str, -damage);
		} else {
			if (source instanceof Player) {
				target.chengeHP(null, null, -damage);
				if (target.getHP() <= 0) {
					Player.me.setExpCash(target.getENEMY_EXP());
				}
			} else if (source instanceof Base_Enemy && source.getLV() != 0) {
				target.chengeHP(null, null, -damage);
				if (target.getHP() <= 0 && target != source) {
					source.addLV(1);
				}
			} else {
				target.chengeHP(null, null, -damage);
			}
			EnchantSpecial.setFlagNeglectHeal(false);
		}
		return damage;
	}

	public static void damageArrowMessage(Base_Creature target, String str,
			int damage) {
		Boolean flag = isFatalDamage(target, -damage);
		if (flag == null) {
			SE.REIMU_BARRIER.play();
			Message.set("霊夢の結界がダメージを１に変換した");
		} else if (flag) {
			Message.set(target.getColoredName(), "に致命的ダメージを与えた");
		} else {
			Message.set(target.getColoredName(), "に", damage, "のダメージを与えた");
		}
	}

	/**
	 * エネミーからエネミーへの引数ダメージを与える。
	 * 
	 * @param em
	 * @param damage
	 */
	public static void damageEnemyToEnemy(Base_Enemy source, Base_Enemy target,
			int damage) {
		target.chengeHP(null, null, -damage);
		if (target.getHP() <= 0 && target != source) {
			source.addLV(1);
		}
	}

	public static void damageMessage(Base_Creature source, String msg,
			Base_Creature target, String str, int damage) {
		if (msg == null)
			msg = "";
		Color c;
		if (source instanceof Player) {
			c = Color.WHITE;
		} else {
			c = enemy_color;
		}
		if (damage < 0) {
			// Message.set(msg, target.getColoredName(), "はHPが",
			// getDight(-damage), "回復した");
		} else {
			Boolean flag = isFatalDamage(target, -damage);
			if (flag == null) {
				SE.REIMU_BARRIER.play();
				Message.set("霊夢の結界がダメージを１に変換した");
				return;
			} else if (flag) {
				if (target.getHP() <= 1) {
					damage = 1;
				} else {
					Message.set(c, msg, target.getColoredName(), c,
							"は致命的ダメージを受けた", Color.WHITE);
					return;
				}
			}
			if (target != null && target instanceof Player) {
				if (BonusConductor.ゆっくり＿被ダメージ量１()) {
					damage = 1;
				} else if (BonusConductor.ゆっくり＿被ダメージ量半減()) {
					damage /= 2;
				}
			}
			if (source == null) {
				// FIXME
				// if (str == null) {
				Message.set(c, msg, target.getColoredName(), c, "は",
						getDight(damage), "のダメージを受けた", Color.WHITE);
				// } else {
				// Message.set(c, msg, str, "によって", target.getColoredName(),
				// c, "は", getDight(damage), "のダメージを受けた", Color.WHITE);
				// }
				return;
			}
			if (target instanceof Player) {
				if (封獣ぬえ.isNue(1)) {
					Message.set(c, msg, target.getColoredName(), c, "は",
							source.getColoredName(), c, "から",
							StringFilter.NUMBERS, "ぬえん", Color.WHITE,
							"のダメージを受けた", Color.WHITE);
				} else {
					Message.set(c, msg, target.getColoredName(), c, "は",
							source.getColoredName(), c, "から", getDight(damage),
							"のダメージを受けた", Color.WHITE);
				}
			} else {
				if (封獣ぬえ.isNue(2)) {
					Message.set(c, msg, source.getColoredName(), c, "は",
							target.getColoredName(), c, "に",
							StringFilter.NUMBERS, "ぬえん", Color.WHITE,
							"のダメージを与えた", Color.WHITE);
				} else {
					Message.set(c, msg, source.getColoredName(), c, "は",
							target.getColoredName(), c, "に",
							getDight(damage),
							"のダメージを与えた", Color.WHITE);
				}
			}
		}
	}

	/**
	 * とりあえず
	 * 
	 * @param obj
	 * @param msg
	 * @param str
	 * @param source
	 * @param target
	 * @param damage
	 * @return
	 */
	public static int damageNoEffect(Object obj, String msg, String str,
			Base_Creature source, Base_Creature target, int damage) {
		if (target instanceof Player) {
			Player.me.chengeHP_NoEffect(-damage);
		} else {
			if (source instanceof Player) {
				target.chengeHP(null, null, -damage);
				if (target.getHP() <= 0) {
					Player.me.setExpCash(target.getENEMY_EXP());
				}
			} else if (source instanceof Base_Enemy && source.getLV() != 0) {
				target.chengeHP(null, null, -damage);
				if (target.getHP() <= 0 && target != source) {
					source.addLV(1);
				}
			} else {
				target.chengeHP(null, null, -damage);
			}
		}
		return damage;
	}

	/**
	 * プレイヤーまたはエネミーに 引数のダメージを与える ダメージメッセージは出ない。
	 * 
	 * @param obj
	 *            リザルト用 プレイヤーへのダメージを与えたclass ○○によって倒されたの○○の部分
	 * @param result
	 *            リザルト用 特別な文章以外はnull
	 * @param damage
	 *            このダメージを与える
	 */
	public static int damageNoMessage(Object obj, String result,
			Base_Creature source, Base_Creature target, int damage) {

		if (target instanceof Player) {
			Player.me.chengeHP(obj, result, -damage);
		} else {
			if (source instanceof Player) {
				target.chengeHP(null, null, -damage);
				if (target.getHP() <= 0) {
					Player.me.setExpCash(target.getENEMY_EXP());
				}
			} else if (source instanceof Base_Enemy && source.getLV() != 0) {
				target.chengeHP(null, null, -damage);
				if (target.getHP() <= 0 && target != source) {
					source.addLV(1);
				}
			} else {
				target.chengeHP(null, null, -damage);
			}
		}
		return damage;
	}

	private static double def(int d) {
		double def = 0.972;
		for (int i = 0; i <= d; i++) {
			def = def * 0.972;
		}
		return def;
	}

	public static int enemyCriticalAttack(Base_Creature source) {
		return enemyCriticalAttack(source, Player.me);
	}

	public static int enemyCriticalAttack(Base_Creature source,
			Base_Creature target) {
		if (target.getHP() <= 0) {
			return 0;
		}
		int str = source.getSTR();
		int critical = str * 15 / 10;
		FrameShaker.doneStrongly();
		Message.setConcatFlag(false);
		Message.setConcatFlag(true);
		source.setSTR(critical);
		SE.DAMAGED_CRITICAL.play();
		Message.set("痛恨の一撃！！");
		int d;
		if (source instanceof Player) {
			d = PtoE_NormalAttack(target);
		} else {
			if (target instanceof Player) {
				d = EtoP_normalAttack(source);
			} else {
				d = EtoE_NormalAttack(source, target);
			}
		}
		source.setSTR(str);
		return d;
	}

	private static void enemyDamageDirection(Base_Creature source,
			Base_Creature target) {
		if (target == source) {
			return;
		}
		target.direction = target.converDirection(source.getMassPoint());
	}

	/**
	 * エネミーからエネミーへの通常ダメージ計算
	 */
	private static int EtoE_DamageValue(Base_Creature source,
			Base_Creature target) {
		System.out.println("checksEnemy");
		enemyDamageDirection(source, target);
		Random random = new R();
		double damage_random = random.nextInt(300) + 850;
		double damage_value = (source.getSTR() + 1) * damage_random / 1000
				- target.getDEF();
		if (damage_value <= 1) {
			int damage_low = random.nextInt(2) + 1;
			damage_value = damage_low;
		}
		return (int) damage_value;
	}

	/**
	 * エネミーからエネミーへの通常攻撃
	 * 
	 * @param source
	 * @param target
	 */
	private static int EtoE_NormalAttack(Base_Creature source,
			Base_Creature target) {
		if (new R().is(attack_miss)) {
			MainMap.addEffect(new MissEffect(target, false));
			Message.set(new String[] { "攻撃は外れた", "" });
			return 0;
		} else {
			SE.SYSTEM_DAMAGED_NORMAL.play();
		}
		int damage = EtoE_DamageValue(source, target);
		// target.chengeHP(null, null, -damage);
		damage(source, null, null, source, target, damage);
		// damageMessage(source, target, null, damage);
		// if (target.getHP() <= 0 && target != source) {
		// source.addLV(1);
		// }
		return damage;
	}

	/**
	 * エネミー→プレイヤーの通常攻撃
	 * 
	 * @param normal
	 */
	private static int EtoP_normalAttack(Base_Creature source) {
		CONDITION.conditionEffectCase(source, CONDITION.やりすごし);
		counterSetDirection(source);
		if (new R().is(isAttackMissInKeine()
				+ EnchantSpecial.deffecePhaseDodge())) {
			MainMap.addEffect(new MissEffect(Player.me, false));
			Message.set(source.getColoredName(), enemy_color, "の攻撃は外れた",
					Color.WHITE);
			return 0;
		}
		if (Illusion.effect(Player.me)) {
			Message.set(source.getColoredName(), "の攻撃を避けてワープした");
			return 0;
		}
		int damage = getPlayerDeffeceDamage(source);
		damage = againstMagicDeffence(source, damage);
		damage = powerUp(source, false, damage);
		damage = EnchantSpecial.deffecePhase友(damage);
		Base_Artifact a = Belongings.get(-1);
		if (a != null && a.isHolyPoint(source, damage)) {
			return 0;
		}
		// damageMessage(source, Player.me, null, damage);
		CONDITION.conditionEffectCase(Player.me, CONDITION.麻痺);
		CONDITION.conditionEffectCase(Player.me, CONDITION.安心);
		// Player.me.chengeHP(source, null, -damage);
		// エネミーからの通常攻撃
		if (BonusConductor.蓬莱人形_被ダメージ量２倍()) {
			damage = damage * 5 / 2;
		}
		if (damage < 1) {
			damage = 1;
		}
		damage(source, null, null, source, Player.me, damage);
		Medal.一度に受けた最高ダメージXX.save_the_more(damage);
		EnchantSpecial.battleCounter(damage, source);
		EnchantSpecial.deffecePhaseCondition(source);
		EnchantSpecial.deffencePhaseFinal(source);
		return damage;
	}
	
	public static String getPredctionDamageText(Base_Creature source, boolean flag_detail_ok)
	{
		if (封獣ぬえ.isNue(1))
		{
			return "??";
		}
		int max = getPredictionDamage(source, (int)(PandE_DamageValue_WithoutRandom(source, 1.15)));
		if (flag_detail_ok)
		{
			int min = getPredictionDamage(source, (int)(PandE_DamageValue_WithoutRandom(source, 0.85)));
			if (min == max)
				return Integer.toString(max);
			else
				return min + "-" + max;
		}
		else
		{
			String min;
			if (max < 10)
				min = "?";
			else if (max < 100)
				min = "?";
			else
				min = "??";
			return min + "-" + max;
		}
	}
	
	static int getPredictionDamage(Base_Creature source, int damage) {
		damage = againstMagicDeffence(source, damage);
		damage = powerUp(source, false, damage);
		if (BonusConductor.蓬莱人形_被ダメージ量２倍()) {
			damage = damage * 5 / 2;
		}
		if (damage < 1) {
			damage = 1;
		}
		return damage;
	}

	public static void EtoPandE_ArrowAttack(Object obj, String str,
			Base_Creature source, Base_Creature target) {
		int damage;
		if (target instanceof Player) {
			damage = PandE_DamageValue(false, source);
			if (BonusConductor.蓬莱人形_被ダメージ量２倍()) {
				damage *= 5 / 2;
			}
			damage(obj, null, str, source, target, damage);
		} else {
			if (!(source instanceof Base_Enemy)) {
				enemyDamageDirection(source, target);
			}
			damage = EtoE_DamageValue(source, target);
			damage(null, null, null, source, target, damage);
		}
	}

	public static int getAttackDamage(boolean p, Base_Creature e) {
		// if (HoldEnemy.ME.isHold()) {
		// return EtoE_DamageValue(HoldEnemy.ME.get(), e);
		// }
		return PandE_DamageValue(p, e);
	}

	private static String getDight(int damage) {
		return 半角全角コンバーター.半角To全角数字(damage);
	}

	private static int getPlayerDeffeceDamage(Base_Creature source) {
		if (HoldEnemy.ME.isHold()) {
			return EtoE_DamageValue(source, HoldEnemy.ME.get());
		} else {
			return PandE_DamageValue(false, source);
		}
	}

	private static int isAttackMissInKeine() {
		return PresentField.get().isHaraheru() ? attack_miss : 0;
	}

	public static Boolean isFatalDamage(Base_Creature target, int damage) {
		if (target instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.頑)) {
				if (Player.me.getMAX_HP() == Player.me.getHP()) {
					if (!EnchantSpecial.isFlagNeglectHeal())
						return null;
				}
			}
		}
		Base_Map bmr = PresentField.get();
		if (bmr != null && bmr.is2GEKI()) {
			if (target instanceof Player) {
				if (EnchantSpecial.isFlagNeglectHeal()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static int normalAttack(Base_Creature source, Base_Creature target) {
		if (target.getHP() <= 0) {
			return 0;
		}
		if (source instanceof Player) {
			return PtoE_NormalAttack(target);
		} else {
			if (target instanceof Player) {
				return EtoP_normalAttack(source);
			} else {
				return EtoE_NormalAttack(source, target);
			}
		}
	}

	/**
	 * プレイヤーからエネミーへのダメージ計算 エネミーからプレイヤーへのダメージ計算
	 */
	private static int PandE_DamageValue(boolean player, Base_Creature cr) {
		Random random = new R();
		double damage_random = random.nextInt(300) + 850;
		if (player) {
			double damage = Player.me.getPower(Base_Artifact.STATUS.STR);
			double damage_value = (damage * damage_random) / 1000
					- (印緋.effect() ? 0 : cr.getDEF());
			if (damage_value <= 1) {
				int damage_low = random.nextInt(2) + 1;
				damage_value = damage_low;
			}
			return (int) damage_value;
		}
		double damage_value = cr.getSTR() * damage_random / 1000
				- Enchant.getSumDEF();
		if (damage_value <= 1) {
			int damage_low = random.nextInt(2) + 1;
			damage_value = damage_low;
		}
		return (int) damage_value;
	}

	/**
	 * プレイヤーからエネミーへのダメージ計算 エネミーからプレイヤーへのダメージ計算
	 */
	public static int PandE_DamageValue_WithoutRandom(Base_Creature cr, double rate) {
		int damage_value = (int)(cr.getSTR() * rate - Enchant.getSumDEF());
		if (damage_value <= 1) {
			int damage_low = 1;
			damage_value = damage_low;
		}
		return damage_value;
	}

	private static int powerUp(Base_Creature c, boolean attack, int damage) {
		if (attack) {
			if (c.getConditionList().contains(CONDITION.パワーアップ)) {
				damage = damage * 125 / 100;
			}
			return damage;
		} else {
			damage = damage * 75 / 100;
			return damage;
		}
	}

	public static void PtoE_ArrowAttack(Base_Artifact a, Base_Creature source,
			Base_Creature target, int arrow_str) {
		enemyDamageDirection(source, target);
		int damage;
		damage = PtoE_ArrowDamageValue(arrow_str, target);
		// <<<<<<< .mine
		// Message.set(target.getColoredName(), "に" + getDight(damage)
		// + "のダメージを与えた");
		// =======
		// >>>>>>> .r104
		if (target instanceof Base_Enemy) {
			target.direction = target.converDirection(source.getMassPoint());
			if (MassCreater.getMass(target.getMassPoint()).ROOM) {
				((Base_Enemy) target).directionPointRoomInRoot(target
						.converDirection(source.getMassPoint()));
			}
		}
		damage(a, null, "反射によって自滅してしまった", Player.me, target, damage);
	}

	private static int PtoE_ArrowDamageValue(int arrow_str, Base_Creature target) {
		Random random = new R();
		double damage_random = random.nextInt(300) + 850;
		double str;
		str = Player.me.playerStr() + Player.me.playerLv();
		if (HoldEnemy.ME.get() != null) {
			str = HoldEnemy.ME.get().getSTR() / 2 + str;
		}
		if (BonusConductor.ナイフマスター_射撃威力増加()) {
			str += Enchant.getSumSTR();
		}
			
		double damage = str + (arrow_str / 1.5);
		double damage_value = damage * damage_random / 1000 - target.getDEF();
		if (damage_value <= 1) {
			int damage_low = random.nextInt(2) + 1;
			damage_value = damage_low;
		}
		return (int) damage_value;
	}

	/**
	 * playerからエネミーへの通常攻撃
	 * 
	 * @param target
	 */
	private static int PtoE_NormalAttack(Base_Creature target) {
		enemyDamageDirection(Player.me, target);
		Player.me.last_attack = target;
		boolean hit = Battle.isHit(target);
		boolean critical = Battle.checkCritical(target);
		if (!hit) {
			Battle.attackMiss(target);
			return 0;
		}
		int damage = getAttackDamage(true, target);
		damage = EnchantSpecial.attackPhaseDamageUp(damage, target);
		if (critical) {
			double critical_damage = damage * 1.5;
			damage = (int) critical_damage;
			Battle.attackCritical();
		} else {
			SE.SYSTEM_DAMAGED_NORMAL.play();
		}
		damage = powerUp(Player.me, true, damage);
		CONDITION.conditionEffectCase(target, CONDITION.麻痺);
		CONDITION.conditionEffectCase(target, CONDITION.安心);
		CONDITION.conditionEffectCase(target, CONDITION.やりすごし);
		damage = 印ダメージUP.fixDamage(damage);
		damage *= BonusConductor.ナイフマスター_デメリット()? (100 - MapList.getFloor()) * 0.01f : 1;
		damage = EnchantSpecial.enchantSimbolAllCheck(CASE.ATK,
				ENCHANT_SIMBOL.連) ? SetEnchantCard.isSetCard(魂魄妖夢のカード.class) ? damage
				: damage * 40 / 100
				: damage;
		if (BonusConductor.ひねくれ者_攻撃受け()) {
			if (target.equals(Player.me)) {
				damage = 0;
				// if (BonusConductor.ひねくれ者_受け半減()) {
				// damage /= 2;
				// }
			}
		}
		if (印殺.attackEffect(target)) {
			if (damage < 999)
				damage = 999;
		} else {
			Medal.一度に与えた最高ダメージXX.save_the_more(damage);
		}
		if (damage < 1) {
			damage = 1;
		}
		// damageMessage(Player.me, target, null, damage);
		// target.chengeHP(null, null, -damage);
		damage(Player.me, null, null, Player.me, target, damage);
		counterMonster(damage, Player.me, target);
		if (target.getHP() <= 0) {
			EnchantSpecial.attackPhaseEnemyBreak(target);
			EnchantSpecial.attackPhaseBloodHeal(damage);
			EnchantSpecial.attackPhaseFinal(target);
		} else {
			印雷.effect(target);
			EnchantSpecial.attackPhaseEffectPlus(target);
			EnchantSpecial.attackPhaseCondition(target);
			EnchantSpecial.attackPhaseBloodHeal(damage);
			EnchantSpecial.attackPhaseFinal(target);
		}
		印密.setFlag(false);
		return damage;
	}

	/**
	 * 引数のダメージを乱数をかけて、ダメージを与える。
	 * 
	 * @param player
	 * @param damage
	 */
	public static void PtoE_RandomDamage(Object obj, String str, int damage,
			Base_Creature c) {
		Random random = new R();
		double damage_random = random.nextInt(300) + 850;
		double d = damage * (damage_random / 1000);
		damage(obj, null, str, Player.me, c, (int) d);
	}

	/**
	 * 乱数ダメージを返す
	 * 
	 * @param damage
	 * @return
	 */
	public static int randomDamageValue(int damage) {
		Random random = new R();
		double damage_random = random.nextInt(300) + 850;
		double d = damage * (damage_random / 1000);
		return (int) d;
	}

}
