package dangeon.model.object.creature.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import connection.sv_cl.SocketHolder;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.menu.view.result.Scene_Result_Info;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.scene.action.message.MessageLock;
import dangeon.model.condition.CONDITION;
import dangeon.model.condition.復活;
import dangeon.model.config.Config;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.StairScene;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.救出大作戦;
import dangeon.model.map.field.random.逆ヶ島;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.鉄の矢;
import dangeon.model.object.artifact.item.bullet.御柱;
import dangeon.model.object.artifact.item.bullet.目からビーム;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印剣;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印回復;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印山;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印水;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印病;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印空腹;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印罠師;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印超不幸;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印連;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印邪;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印鎌;
import dangeon.model.object.artifact.item.pot.送信の瓶;
import dangeon.model.object.artifact.item.scrool.幻想郷縁起;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.八意永琳のカード;
import dangeon.model.object.artifact.item.spellcard.八雲藍のカード;
import dangeon.model.object.artifact.item.spellcard.蓬莱山輝夜のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.enemy.剣;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.npc.賽銭箱;
import dangeon.model.object.creature.npc.second.Takarabako;
import dangeon.model.object.creature.player.Battle.Battle;
import dangeon.model.object.creature.player.class_job.BaseClassJob;
import dangeon.model.object.creature.player.class_job.ClassDefault;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;
import dangeon.model.object.creature.player.shop.Shop;
import dangeon.model.result.Result;
import dangeon.util.Damage;
import dangeon.util.Etc;
import dangeon.util.MapInSelect;
import dangeon.util.ObjectPoint;
import dangeon.util.R;
import dangeon.view.anime.Base_Anime;
import dangeon.view.anime.CardAnime;
import dangeon.view.anime.DoronEffect;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.MiniMap;
import dangeon.view.detail.View_Sider;
import dangeon.view.util.StringFilter;
import main.constant.FR;
import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_Artifact;
import main.res.Image_Player;
import main.res.SE;
import main.thread.MainThread;
import main.util.BeautifulView;
import main.util.DIRECTION;
import main.util.Show;
import main.util.半角全角コンバーター;

public class Player extends Base_Creature {
	protected enum STATUS {
		DEF, HP, STR
	}

	public static void load(Player p) {
		me = p;
		// transient
		me.list_attacking_stand = new ArrayList<Stand>();
		me.load();
		if (me.hash_restriction == null) {
			me.hash_restriction = new HashMap<Enchant, Boolean>();
		}
		Message.clearRecord();
	}

	private BaseClassJob class_job = new ClassDefault();

	private long playing_time = 0;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public transient 賽銭箱 saisen = null;

	public transient Shop shop = null;

	/**
	 * 満腹度 d
	 */
	private int SATIETY = 100, MAX_SATIETY = 100;

	public static final int satiety_first = 20;

	public static final int satiety_second = 10;;

	private int self_healing = 150;

	private int self_healing_rest = 0;

	private int player_exp = 0;

	public static final String name = "チルノ";

	public static Player me = new Player();

	public int turn_satiety = 100;

	private int turn_satiety_rest = 0;

	private boolean flag_throwing = false, flag_wand = false;

	private static Point back_point = new Point(0, 0);

	private boolean flag_stand_attacking = false;

	private int exp_cash_no_message = 0;

	private int exp_cash = 0;

	private Base_Artifact throw_item = null;

	private transient ArrayList<Stand> list_attacking_stand = new ArrayList<Stand>();
	private Base_Artifact atk, def;

	private int additional_attack_que, additional_attack_que_youmu,
			additional_throw_que;

	private boolean flag_additional = false;

	public Base_Creature last_attack = null;
	public boolean flag_game_over_TurnOver;
	public String cause_of_death = null;
	public Point attack_point = null;
	private final String color = Color.YELLOW.toString();
	public static boolean flag_clear = false;
	public static boolean flag_daichan_return;
	private int books, books_count;
	public HashMap<Enchant, Boolean> hash_restriction = new HashMap<Enchant, Boolean>();

	private int dying_frame = 0;

	private boolean flag_next_monster_house = false;
	public boolean flag_no_item = false, flag_no_item_daichan = false;

	public Base_Creature Nedayashi;

	private int submition_size = 0;

	private Player() {
		super(new Point(0, 0), name, 1);
		IM = CHARA_IMAGE.チルノ;
		Message.clearRecord();
		// 乱数や縛りフラグが解消されるので呼んじゃだめ
		// resetAll();
	}

	/**
	 * 状態異常説明ウィンドウ
	 * 
	 * @param c
	 */
	public Player(CONDITION c) {
		super(new Point(0, 0), name, 1, c);
		IM = CHARA_IMAGE.チルノ;
	}

	public void addBooks(int pages) {
		// if (pages > 0) {
		// SE.SYSTEM_PICKUP.play();
		// }
		books += pages;
		if (books > 999) {
			books -= 999;
			SE.FANFARE2.play();
			Message.set("幻想郷縁起が完成した！");
			Medal.幻想郷緑起を手に入れた.addCount();
			ItemFall.itemFall(new 幻想郷縁起(mass_point.getLocation()));
		} else if (books < 0) {
			books = 0;
		}
	}

	public void additionalAttack() {
		additional_attack_que++;
	}

	public void additionalAttackYoumu() {
		additional_attack_que_youmu++;
	}

	public void additionalThrow() {
		additional_throw_que++;
	}

	/**
	 * アイテム等でレベル変動する場合のみ
	 */
	@Override
	public void addLV(int goal) {
		if (Player.me.getLV() == 99 && goal > 0) {
			Message.set("これ以上", Player.me.getColoredName(), "のレベルは上がらなかった");
			return;
		} else if (Player.me.getLV() + goal < 1) {
			Message.set("これ以上", Player.me.getColoredName(), "のレベルは下がらない！！");
			return;
		} else if (Player.me.getLV() + goal > 99) {
			Message.set(Player.me.getColoredName(), "のレベルが上がった");
			goal = 99 - Player.me.getLV();
		} else {
			if (goal > 0) {
				Message.set(Player.me.getColoredName(), "のレベルが上がった");
			} else if (goal < 0) {
				Message.set(Player.me.getColoredName(), "のレベルが下がった");
			}
		}
		LevelUp(goal);
		EXP.table.setExp();
	}

	@Override
	public void addMAX_HP(int delt) {
		if (BonusConductor.ゆっくり_最大HP常時１０()) {
			return;
		}
		if (BonusConductor.ゆっくり_最大HPボーナス２()) {
			if (delt < 0)
				delt = -2;
			else if (delt > 0)
				delt = 2;
		}
		if (BonusConductor.人形使い_HP上昇制限()) {
			delt = delt * 3 / 4;
		}
		super.addMAX_HP(delt);
		Medal.最高HP.save_the_more(getMAX_HP());
	}

	public void addPlayingMilliTime(int time_milli_second) {
		this.playing_time += time_milli_second;
	}

	public void allItemUnFreeze() {
		for (Base_Artifact a : Belongings.getListItems()) {
			a.freezeCountReset();
		}
	}

	private void attackEnd() {
		additional_attack_que = additional_attack_que_youmu = 0;
		if (flag_additional) {
			flag_additional = false;
			Enchant.ATK.setEnchant(atk);
			Enchant.DEF.setEnchant(def);
		}
	}

	public boolean attackWalkableCheck(Point p, DIRECTION dir) {
		if (EnchantSpecial.attackPhaseWalkableThrough()) {
			return true;
		}
		if (!MassCreater.getMass(p.x + dir.X, p.y + dir.Y).WALKABLE) {
			return EnchantSpecial.enchantSimbolAllCheck(CASE.ATK,
					ENCHANT_SIMBOL.必中);
		}
		if (dir.NUM % 2 == 0) {
			return true;
		}
		DIRECTION[] d = dir.getNeiboringDirections3();
		for (int i = 0; i < 3; i++) {
			if (!MassCreater.getMass(new Point(p.x + d[i].X, p.y + d[i].Y)).WALKABLE) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void changeMAX_STR(int value) {
		MAX_STR += value;
		STR += value;
		if (MAX_STR > 99) {
			MAX_STR = 99;
		} else if (MAX_STR < 1) {
			MAX_STR = 1;
		}
		if (STR > 99) {
			STR = 99;
		} else if (STR < 1) {
			STR = 1;
		}
		Medal.最高ちから.save_the_more(MAX_STR);
	}

	private boolean checkTrap(int i, int j) {
		MassCreater.getMass(i, j).setUnWarning();
		if (MapList.getArtiface(i, j) instanceof Base_Trap) {
			if (additional_attack_que == 0) {
				final Base_Artifact a = MapList.getArtiface(i, j);
				if (!a.isVisible()) {
					if (!conditionCheck(CONDITION.目薬) && !印罠師.effect()) {
						attackEnd();
						MainMap.addEffect(new DoronEffect(a.getMassPoint(),
								new Task() {
									/**
							 *
							 */
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
									}

									@Override
									protected void work(int frame) {
										if (frame == 4) {
											a.setVisible(true);
										}
									}
								}), true);
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean chengeHP(Object obj, String str, int delt) {
		if (delt > 0) {
			// あらゆる回復に対して
			if (印邪.isHalfGhost()) {
				delt = 1;
			}
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.医)) {
				delt = SetEnchantCard.isSetCard(八意永琳のカード.class) ? delt * 2
						: delt * 15 / 10;
			}
		} else {
			// あらゆるダメージに対して
			if (BonusConductor.ゆっくり＿被ダメージ量１()) {
				delt = -1;
			} else if (BonusConductor.ゆっくり＿被ダメージ量半減()) {
				delt /= 2;
			}
			Scene_Action.getMe().set_I_Dushing_request_arrow_key(true);
		}
		super.chengeHP(obj, str, delt);
		if (!playerLifeCheck(obj)) {
			if (delt < 0) {
				EnchantSpecial.deffenceDamageCounter();
			}
		} else {
			EnchantSpecial.setFlagNeglectHeal(false);
			if (Player.me.isFalling()) {
				Player.me.death_sudden(obj, str);
			} else {
				Player.me.death(obj, str);
			}
		}
		EnchantSpecial.setFlagNeglectHeal(false);
		return false;
	}

	public void chengeMaxSatiety(int delt) {
		if (MAX_SATIETY + delt > 200) {
			MAX_SATIETY = 200;
		} else if (MAX_SATIETY + delt <= 0) {
			MAX_SATIETY = 0;
		} else {
			MAX_SATIETY += delt;
		}
	}

	public void chengeSatiety(int delt) {
		if (delt > 0) {
			turn_satiety_rest = 0;
		} else if (delt < 0) {
			if (SATIETY + delt == 20) {
				SE.ONAKASUITA.play();
				View_Sider.setInformation("お腹が空いてきました");
				Message.set("お腹が空いてきた");
			} else if (SATIETY + delt == 10) {
				SE.ONAKASUITA.play();
				View_Sider.setInformation("お腹がペコペコです");
				Message.set("お腹がかなり空いてきた…");
			} else if (SATIETY + delt == 0) {
				SE.ONAKASUITA.play();
				View_Sider.setInformation(Color.RED.toString(),
						"お腹がすきすぎてＨＰが減っています！");
				Message.set("だめだ…はやく何か食べないと……$倒れてしまう……！");
			}
		}
		if (SATIETY + delt >= MAX_SATIETY) {
			setSATIETY(MAX_SATIETY);
		} else if (SATIETY + delt < 0) {
			setSATIETY(0);
		} else
			setSATIETY(SATIETY + delt);
		if (delt < 0 && SATIETY > 0) {
			印回復.selfHeal();
		}
	}

	public void death(Object cause_of_death, String str) {
		if (isDying()) {
			return;
		}
		if (!Config.isTest()) {
			dying_frame = 1;
			this.cause_of_death = Result.getMessage(cause_of_death, str);
			Medal.saveDeath(this.cause_of_death);
			BGM.waitUntilFadeOut_Thread();
			TurnSystemController.setTurnFinish();
			if (flag_game_over_TurnOver) {
				dying_frame = 0;
				death_at_dying_finished();
			}
		}
	}
	
	private void recover(BGM 復活時のBGM, boolean flanCoin)
	{
		dying_frame = 0;
		復活時のBGM.play();
		chengeHP_NoEffect(getMAX_HP());
		chengeSatiety(getMAX_SATIETY());
		CONDITION.conditionAllClear(Player.me, true);
		
		if (flanCoin)
		{
			setTelepoteAnimation(true, null);
			Config.decRetryNumbers();
			setCondition(CONDITION.炎上, 0);
			setCondition(CONDITION.反射, 0);
			MapInSelect.explosion(getMassPoint());
		}
		else
		{
			Medal.復活回数.addCount();
			if (BonusConductor.蓬莱人形_復活時炎上()) {
				Player.me.setCondition(CONDITION.炎上, 0);
			}
			if (BonusConductor.蓬莱人形_復活時自爆()) {
				MapInSelect.explosion(Player.me.getMassPoint());
			}
		}
	}
	
	private void confirmFlanCoin(int zanki)
	{
		new ConvEvent("残機を使って復活しますか？$(爆発して復活します)$", Scene_Result_Info.getZanki(zanki)) {
			@Override
			protected Book getContent1() {
				return new Book("再挑戦") {
					@Override
					protected void work() {
						recover(PresentField.get().getBGM(), true);
					}
				};
			}

			@Override
			protected int pushCancelAction() {
				return -1;
			}
		};
	}

	private void death_at_dying_finished() {
		Message.setConcatFlag(false);
		new MessageLock(Player.me.getColoredName(),
				flag_game_over_TurnOver ? "は楽しくなりすぎた" : "は倒れた@") {

			@Override
			public void second() {
				if (flag_game_over_TurnOver) {
					flag_game_over_TurnOver = false;
					new Scene_Result_Info();
				}
				else if (cause_of_death == "冒険をあきらめた") {
					new Scene_Result_Info();
				}
				else {
					BGM 復活時のBGM = 復活.tryRecovery(); 
					if (復活時のBGM != null) {
						recover(復活時のBGM, false);
						return;
					}

					if (!Config.isCoinOnly1()) {
						int zanki = Config.getRetryNumber() - 1;
						if (zanki > 0)
						{
							confirmFlanCoin(zanki);
							return;
						}
					}
					
					new Scene_Result_Info();
				}
			}
		};
	}

	public void death_sudden(Object cause_of_death, String str) {
		death(cause_of_death, str);
		dying_frame = 100;
	}

	private void drawArrow(Graphics2D g, DIRECTION d) {
		Point p = ObjectPoint.getScreenPointRelPlayer(this);
		int dif = d.isBias() ? 24 : 23;
		int delt_x = 0, delt_y = 0;
		switch (d) {
		case DOWN:
			delt_y = -4;
			break;
		case LEFT:
		case RIGHT:
			delt_y = -2;
			break;
		case UP:
			delt_y = -10;
			break;
		case UP_LEFT:
		case UP_RIGHT:
			delt_y = -6;
			break;
		case DOWN_LEFT:
		case DOWN_RIGHT:
			break;
		}
		p.translate(d.X * dif + delt_x, d.Y * dif + delt_y);

		g.drawImage(CHARA_IMAGE.arrow.getWalkImage(1, d, 0.5f), p.x, p.y, null);
	}

	@Override
	protected void drawCreature(Graphics2D g, int x, int y) {
		// if (Scene_Action.getMe().isDirecting())
		// drawArrow(g, direction);
		if (isDying()) {
			BeautifulView.setAlphaOnImg(g, getDyingAlpha());
			g.drawImage(getImage(), x + getFootX(), y + getFootY(), null);
			BeautifulView.setAlphaOnImg(g, 1f);
		} else {
			if (!conditionCheck(CONDITION.透明) && StairScene.isTasksEmpty()) {
				// g.drawImage(getImage(), x + getFootX(), y + getFootY(),
				// null);
				super.drawCreature(g, x, y);
			} else {
				skipDrawFrame();
			}
			if (Scene_Action.getMe().is_I_Dushing()) {
				g.setColor(new Color(150, 255, 255));
				String tit = "＼あたいにお任せダッシュ／";
				StringFilter.drawString(g, tit, x + getFootX() - 100, y
						+ getFootY());
				// g.drawString(I_Dush.auto ? "(ﾟДﾟ)ﾉ ｧｨ" : "(  ﾟДﾟ)? ",
				// x + c.getFootX() + 50, y + c.getFootY() + 35);
			}
		}
	}

	private boolean enemyDamage() {
		CONDITION.conditionEffectCase(this, CONDITION.混乱);
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.驚)) {
			DIRECTION d = Player.me.getDirection();
			Point p = Player.me.getMassPoint().getLocation();
			Base_Creature e;
			if (EnchantSpecial.attackPhaseWalkableThrough()) {
				e = MapInSelect.getStraightHitCreature(d, p, 0);
			} else {
				e = MapInSelect.getStraightMoveCheck(d, p, 0);
			}
			if (e != null) {
				final Point f_p = e.getMassPoint().getLocation();
				f_p.translate(-d.X, -d.Y);
				if (!p.equals(f_p)) {
					if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK,
							ENCHANT_SIMBOL.間)
							|| EnchantSpecial.enchantSimbolAllCheck(CASE.ATK,
									ENCHANT_SIMBOL.式)) {
						f_p.translate(-d.X, -d.Y);
					}
					if (!p.equals(f_p)) {
						SE.WARP_INSTANT.play();
						Player.me.setMassPoint_WalkLike(f_p, 5, new Task() {
							/**
							 *
							 */
							private static final long serialVersionUID = 1L;

							@Override
							public void work() {
								setMassPoint(f_p);
								印鎌.effect(f_p.getLocation());
								enemyDamageOne(direction);
							}
						});
						return true;
					}
				}
			}
		}
		return enemyDamageOne(direction);
	}

	private boolean enemyDamage_youmuCheck() {
		if (印連.effect())
			additionalAttackYoumu();
		else
			additional_attack_que_youmu = 0;
		return enemyDamage();
	}

	private boolean enemyDamageOne(DIRECTION d) {
		final int x = d.X, y = d.Y;
		final Point p = Player.me.getMassPoint().getLocation();
		if (attackWalkableCheck(p, d)) {
			flag_stand_attacking = false;
			Base_Enemy e2 = MapList.getEnemy(p.x + x, p.y + y);
			if (e2 != null) {
				if (e2.hasMessage()) {
					e2.setDirection(e2.converDirection(Player.me.getMassPoint()));
					if (e2.isSkillActive()) {
						e2.message();
					} else {
						Message.set(e2.getColoredName(), "は喋れる状態でなかった");
					}
					Message.setConcatFlag(false);
					attackEnd();
					return e2 instanceof Takarabako;
				} else {
					if (BonusConductor.ひねくれ者_攻撃受け()) {
						return reverseAttack(p, x, y);
					} else {
						startAttack(getAttackTask(p, x, y), false);
					}
					return true;
				}
			}
			if (BonusConductor.ナイフマスター_通常攻撃()) {

				if (checkTrap(p.x + x, p.y + y)) {
					return true;
				} else { 
					return ナイフマスター_通常攻撃();
				}
			}
			else if (BonusConductor.ひねくれ者_攻撃受け() && reverseAttack(p, x, y)) {
				return true;
			} else if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK,
					ENCHANT_SIMBOL.式)) {
				Point _p = new Point(p.x + x, p.y + y);
				int length = SetEnchantCard.isSetCard(八雲藍のカード.class) ? 3 : 1;
				for (int i = 0; i < length; i++) {
					if (attackWalkableCheck(_p, d)) {
						_p.translate(x, y);
						Base_Creature e = MapList.getEnemy(_p);
						if (e != null && !(e instanceof Base_NPC)) {
							if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK,
									ENCHANT_SIMBOL.間)) {
								Battle.setCriticalFlag();
							}
							startAttack(getAttackTask(p, x * 2 + i, y * 2 + i),
									true);
							return true;
						}
					}
				}
			}
			if (印鎌.effect(Player.me.getMassPoint().getLocation())) {
				attack_point = new Point(getMassPoint());
				startAttack(getAttackTask(attack_point, x, y), false);
				return true;
			}
			if (checkTrap(p.x + x, p.y + y))
				return true;
		}

		if (BonusConductor.ナイフマスター_通常攻撃()) {
			return ナイフマスター_通常攻撃();
		}
		if (BonusConductor.ひねくれ者_攻撃受け()) {
			return reverseAttack(p, x, y);
		}
		boolean flag = false;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.式)) {
			flag = attackWalkableCheck(p, d);
		}
		startAttack(null, flag);
		EnchantSpecial.attackPhaseStart();
		return true;
	}

	private boolean ナイフマスター_通常攻撃() {
		attacking_direction = direction;
		normal_attack_phase = AttackPhase.GO;
		SE.THROW.play();
		new 鉄の矢(getMassPoint(), false).itemThrow(this, HowToThrow.BREAK, 10);
//		direction = direction.getDeNeiboringDirection();
		return true;
	}

	public void expCashReset() {
		exp_cash = 0;
	}

	public Enchant[] getAnys() {
		return new Enchant[] { Enchant.ANY1, Enchant.ANY2 };
	}

	@Override
	protected Point getAttackPoint() {
		return screen_point;
	}

	@Override
	public SE getAttackSE() {
		if (flag_stand_attacking) {
			return null;
		} else {
			return SE.ATTACK_HANDS;
		}
	}

	@Override
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
						&& !passive.isInValidOnAttack()) {
					Damage.normalAttack(active, passive);
				} else {
					Battle.unsetCriticalFlag();
				}
				if (additional_attack_que_youmu > 0) {
					additional_attack_que_youmu--;
					enemyDamage();
				}
			}
		};
	}

	protected Task getAttackTask(final Point p, final int x, final int y) {
		EnchantSpecial.attackPhaseStart();
		return getAttackTask(this, MapList.getEnemy(p.x + x, p.y + y));
	}

	@Override
	public Point getBackPoint() {
		return back_point;
	}

	public int getBooks() {
		if (books_count > books) {
			books_count -= 10;
			if (books_count < books) {
				books_count = books;
			}
		} else if (books_count < books) {
			SE.SYSTEM_PICKUP.play();
			if (books_count + 10 < books) {
				books_count += 10;
			} else {
				books_count++;
			}
		}
		return books_count;
	}

	public BaseClassJob getClassJob() {
		if (class_job == null)
			class_job = new ClassDefault();
		return class_job;
	}

	@Override
	public String getColoredName() {
		return color.concat(super.getColoredName()).concat(
				Color.WHITE.toString());
	}

	public int getDeltX() {
		if (flag_stand_attacking) {
			return beated_away_point.x;
		}
		return attack_delt_point.x + beated_away_point.x;
	}

	public int getDeltY() {
		if (flag_stand_attacking) {
			return beated_away_point.y;
		}
		return attack_delt_point.y + beated_away_point.y;
	}

	public float getDyingAlpha() {
		if (dying_frame == -1) {
			return 0f;
		}
		int rest = 10;
		double max = 30.0;
		float f = (float) ((dying_frame - rest) / max);
		f = 1f - f;
		if (f < 0f) {
			f = 0;
		} else if (f > 1f) {
			f = 1f;
		}
		return f;
	}

	public int getExpCash() {
		return exp_cash;
	}

	public int getExpCashNoMessage() {
		return exp_cash_no_message;
	}

	@Override
	public int getFootDeltY() {
		if (conditionCheck(CONDITION.おにぎり))
			return 17;
		else
			return 0;
	}

	@Override
	public int getGhost() {
		return 印邪.getGhost();
	}

	@Override
	public Image getImage() {
		if (flag_clear) {
			return Image_Player.rising_hand.IM;
		} else if (isDying()) {
			return Image_Player.down.IM;
		} else if (conditionCheck(CONDITION.おにぎり)) {
			return Image_Artifact.FOOD.getImage(1);
		} else if (conditionCheck(CONDITION.睡眠)) {
			if (MainThread.getFrame() / 6 % 2 == 0) {
				return Image_Player.sleep0.IM;
			} else {
				return Image_Player.sleep1.IM;
			}
		} else if (Image_Player.isAnimating()) {
			return Image_Player.getImage();
		} else if (getSecondAnimation() != null) {
			if (getSecondAnimation().getComa() == 0) {
				return Image_Player.card1.IM;
			} else {
				return Image_Player.card0.IM;
			}
		} else if (flag_wand) {
			return Image_Player.getWandImage(attacking_direction, attack_No);
		} else if (flag_stand_attacking && isAttacking()) {
			return IM.getATKImage(0, attacking_direction, 2);
		} else if (flag_throwing || isAttacking()) {
			return IM.getATKImage(0, attacking_direction, attack_No);
		} else if (Scene_Action.getMe().isOwata()) {
			return Image_Player.otiru1.IM;
		} else if (flag_damaging) {
			return IM.getDamImage(0, direction);
		} else if (Scene_Action.getMe().isResting()) {
			if (!MapList.isAnyEnemyInScreen()) {
				return Image_Player.TAIKI[MainThread.getFrame() / 8
						% Image_Player.TAIKI.length].IM;
			}
		}
		return IM.getWalkImage(0, direction, getMoveAnimationSpeed());
	}

	public List<Object> getListAllCondition() {
		List<Object> list = new ArrayList<Object>();
		for (CONDITION con : getConditionList()) {
			list.add(con);
		}
		for (ENCHANT_SIMBOL simbol : EnchantSpecial.getAlways_enchant_special()) {
			list.add(simbol);
		}
		return list;
	}

	public ListIterator<Stand> getListIterator() {
		return list_attacking_stand.listIterator();
	}

	@Override
	public final int getMAX_HP() {
		int max = super.getMAX_HP();
		int max2 = max;
		max += SetEnchantCard.isSetCard(蓬莱山輝夜のカード.class) ? max2 * 30 / 100 : 0;
		max += EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
				ENCHANT_SIMBOL.ハート) ? max2 * 15 / 100 : 0;
		if (max > 999)
			max = 999;
		return max;
	}

	public int getOriginMAX_HP() {
		int max = super.getMAX_HP();
		if (max > 999)
			max = 999;
		return max;
	}
	
	public int getMAX_SATIETY() {
		return MAX_SATIETY;
	}

	@Override
	public int getMAX_STR() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.力１)) {
			return MAX_STR + 5;
		} else {
			return MAX_STR;
		}
	}

	@Override
	public int getMaxLV() {
		return 99;
	}

	public int getNoEnchantMaxStr() {
		return MAX_STR;
	}

	public int getNoEnchantStr() {
		return STR;
	}

	public int getPlayerExp() {
		return player_exp;
	}

	public long getPlayingMilliTime() {
		return playing_time;
	}

	public int getPower(Base_Artifact.STATUS s) {
		Base_Artifact a;
		double status_value = 0;
		double enchant = 0;
		double enchant_status = 0;
		System.out.println("合計");
		if (s == Base_Artifact.STATUS.STR) {
			status_value += playerStr() + playerLv();
			if (HoldEnemy.ME.get() != null) {
				return (int) (HoldEnemy.ME.get().getSTR() + status_value);
			}
			a = Enchant.ATK.getEnchant();
			if (a != null) {
				enchant_status += Enchant.getSumSTR() - a.getForgeValue();
				enchant += enchant_status * 0.75;
			} else {
				enchant_status += Enchant.getSumSTR();
				enchant += enchant_status * 0.75;
			}
		} else {
			if (HoldEnemy.ME.get() != null) {
				return HoldEnemy.ME.get().getDEF();
			}
			a = Enchant.DEF.getEnchant();
			if (a != null) {
				enchant_status += Enchant.getSumDEF() - a.getForgeValue();
				enchant += enchant_status * 0.75;
			} else {
				enchant_status += Enchant.getSumDEF();
				enchant += enchant_status * 0.75;
			}
		}
		double forge_coefficient;
		if (a != null) {
			if (s == Base_Artifact.STATUS.STR) {
				forge_coefficient = enchant_status > 10 ? 0.75 + 0.05 * (enchant_status - 10)
						: 0.075 * enchant_status;
				status_value += a.getForgeValue() * forge_coefficient;
			} else {
				forge_coefficient = 0.075 * enchant_status;
				status_value += a.getForgeValue() * forge_coefficient;
			}
			status_value += enchant;
		} else {
			status_value += enchant;
		}
		return (int) Math.ceil(status_value);
	}

	public Point getPureScreenPoint() {
		return screen_point;
	}

	public int getSATIETY() {
		return SATIETY;
	}

	@Override
	public int getShadowSize() {
		if (isFalling())
			return 0;
		return super.getShadowSize();
	}

	@Override
	public int getShadowSize100() {
		return 21;
	}

	@Override
	public int getSTR() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.力１)) {
			return STR + 5;
		} else {
			return STR;
		}
	}

	public int getSubmitionSize() {
		return submition_size;
	}

	public int getSubmitJarSize() {
		if (SocketHolder.isConnected()) {
			for (Base_Artifact a : Belongings.getListItems()) {
				if (a instanceof 送信の瓶) {
					return ((送信の瓶) a).getListSize();
				}
			}
			return -1;
		}
		return -2;
	}

	public DIRECTION getVisibleObjectExistAtNeiboringMass_direction(
			boolean including_artifact) {
		for (DIRECTION d : Player.me.getDirection().getNeiboringDirections5()) {
			Base_Creature c = MapList.getCreature(mass_point.x + d.X,
					mass_point.y + d.Y);
			if (c != null && !(c instanceof Base_NPC)) {
				return d;
			}
			if (including_artifact) {
				Base_Artifact a = MapList.getArtiface(mass_point.x + d.X,
						mass_point.y + d.Y);
				if (a != null && a.isVisible()) {
					return d;
				}
			}
		}
		return null;
	}

	private void initShareItem() {
		submition_size = SocketHolder.isConnected() ? 4 : 0;
	}

	@Override
	public boolean isAnimating() {
		if (Image_Player.isAnimating()) {
			return true;
		} else if (isDying()) {
			return true;
		}
		return super.isAnimating();
	}

	public boolean isAnimating_withoutMoving() {
		return flag_move_animating == false && isAnimating();
	}

	@Override
	public boolean isBoating() {
		return 印水.effect();
	}

	public boolean isDead() {
		return Player.me.getHP() < 1;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDushing() {
		return Scene_Action.getMe().isDushing();
	}

	public boolean isDying() {
		return dying_frame != 0;
	}

	public boolean isFalling() {
		if (Image_Player.isAnimating()) {
			return Image_Player.getAATIC() == Image_Player.AnimeAccordingToItemClass.PITFALL;
		}
		return false;
	}

	public boolean isFlag_next_monster_house() {
		return flag_next_monster_house;
	}

	public boolean isGray() {
		boolean flag = Player.me.saisen != null && !Player.me.saisen.isWhite();
		flag = flag
				|| (Player.me.shop != null && Player.me.shop.getTenshu()
						.warning());
		return flag;
	}

	@Override
	public boolean isIce() {
		return EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
				ENCHANT_SIMBOL.チルノ);
	}

	public boolean isMovable(DIRECTION direction)
			throws ArrayIndexOutOfBoundsException {
		Mass[][] mass = MassCreater.getMass();
		Point p = Player.me.getMassPoint().getLocation();
		p.x += direction.X;
		p.y += direction.Y;
		if (印邪.isWallWalk()) {
			return !MassCreater.getMass(p).equals(Mass.nullpo)
			// && MassCreater.getMass(p).DIGGABLE
					&& MapList.getCreature(p) == null;
		} else if (DIRECTION.isBias(direction)) {
			if (MassCreater.getMass(p).WALKABLE && movable_water_check(p)
					&& mass[p.x][p.y - direction.Y].WALKABLE
					&& mass[p.x - direction.X][p.y].WALKABLE
					&& MapList.getCreature(p) == null) {
				return true;
			}
		} else {
			if (MassCreater.getMass(p).WALKABLE
					&& MapList.getCreature(p) == null && movable_water_check(p)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isResistantToGrassWave() {
		return EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.医);
	}

	@Override
	public boolean isSkillActive() {
		return HP > 0;
	}

	@Override
	public boolean isSkillActive(boolean active_in_dying) {
		return true;
	}

	public boolean isStandExist() {
		return list_attacking_stand.size() > 0;
	}

	boolean isVisibleObjectExistAtNeiboringMass(boolean including_artifact) {
		return getVisibleObjectExistAtNeiboringMass_direction(including_artifact) != null;
	}

	public void itemFreezeCount() {
		if (BonusConductor.蓬莱人形_氷カウント０()) {
			return;
		}
		for (Base_Artifact a : Belongings.getListItems()) {
			a.freezeCountPlus(1);
		}
	}

	@Override
	protected boolean itemHitEffect(Base_Artifact a, boolean ento) {
		if (BonusConductor.ゆっくり_魔投結界()) {
			if (a instanceof MagicBullet) {
				SE.REIMU_BARRIER.play();
				Message.set("霊夢の結界は魔法弾の効果をダメージに変えた");
				Damage.damage(null, null, "変換したダメージによって倒れた", Player.me, this, 1);
				return false;
			} else {
				SE.REIMU_BARRIER.play();
				Message.set("霊夢の結界は投擲物の効果をダメージに変えた");
				Damage.damage(null, null, "変換したダメージによって倒れた", Player.me, this, 1);
				return false;
			}
		} else {
			return super.itemHitEffect(a, ento);
		}
	}

	public void itemLevelUp(int count) {
		LevelUp(count);
		EXP.table.setExp();
	}

	public void itemThrow(Base_Artifact a, DIRECTION d) {
		setThrowing(a, d);
	}

	public void LevelUp(int level_count) {
		if (level_count < 0) {
			for (int i = 0; i < -level_count; i++) {
				SE.LEVEL_DOWN.play();
				LV--;
				int grow = statusGrow(STATUS.HP, false);
				Player.me.addMAX_HP(grow);
				if (getMAX_HP() < HP) {
					Player.me.setHP(Player.me.getMAX_HP());
				}
			}
		} else {
			for (int i = 0; i < level_count; i++) {
				SE.LEVEL_UP.play();
				LV++;
				int grow = statusGrow(STATUS.HP, true);
				Player.me.addMAX_HP(grow);
				Player.me.setHP(grow + Player.me.getHP());
			}
		}
	}

	public void move(DIRECTION dir) {
		if (dir.equals(DIRECTION.NEUTRAL))
			return;
		if (!CONDITION.isMovable(this)) {
			Scene_Action.getMe().set_I_Dushing(false);
			return;
		}
		if (conditionCheck(CONDITION.影縫い)) {
			this.direction = dir;
			Point p = Player.me.getMassPoint().getLocation();
			if (MapList.getEnemy(p.x + dir.X, p.y + dir.Y) == null) {
				if (TurnSystemController.time_stop) {
					SE.STATUS_SHADOW.play();
					Message.set("時間が停止していてクナイが抜けない！");
				} else {
					if (getConditionTurn(CONDITION.影縫い) == 1) {
						Message.set("やっと抜けた");
					} else {
						SE.STATUS_SHADOW.play();
						Message.set("クナイがまだ抜けない！");
					}
				}
				TurnSystemController.callMeToStartEnemyTurn(true);
			}
		} else {
			boolean flag_moved = false;
			Point p = Player.me.getMassPoint().getLocation();
			Mass before_mass = MassCreater.getMass(p);
			this.direction = dir;
			CONDITION.conditionEffectCase(this, CONDITION.混乱);
			try {
				if (isMovable(direction)) {
					p.x += direction.X;
					p.y += direction.Y;
					mass_point.setLocation(p);
					flag_moved = true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
			flag_move_animating = flag_moved;
			if (flag_moved) {
				Mass after_mass = MassCreater.getMass(p);
				after_mass.setUnWarning();
				MiniMap.openTiles();
				if (Scene_Action.getMe().is_I_Dushing()) {
					Scene_Action.getMe().set_I_Dushing_request_arrow_key(false);
					TaskOnMapObject.setMoved();
				} else if (Scene_Action.getMe().isDushKeyPushing()) {
					TaskOnMapObject.setDashingMoved();
					boolean flag = isVisibleObjectExistAtNeiboringMass(true);
					// 通路から部屋に出たとき
					flag = flag || (!before_mass.ROOM && after_mass.ROOM);
					flag = flag
							|| (MassCreater.getMass(Player.me.getMassPoint()).ROAD && I_Dush
									.isStandInBranch() == DIRECTION.NEUTRAL);
					flag = flag || MapList.getArtifact(mass_point) != null;
					if (!flag) {
						if (before_mass.ROOM && after_mass.ROOM) {
							DIRECTION d = direction.getNeiboringDirection(2);
							for (int i = 0; i < 2; i++) {
								d = d.getReverse();
								if (MassCreater
										.getMass(d.getFrontPoint(mass_point
												.getLocation())).ROAD) {
									flag = true;
									break;
								}
							}
						}
					}
					if (flag) {
						Scene_Action.getMe().setRequestReleasePushingKey();
					}
				} else {
					TaskOnMapObject.setMoved();
				}
				TurnSystemController.callMeToStartEnemyTurn(true);
			}
		}
	}

	@Override
	protected String nameLevel() {
		return name;
	}

	public boolean normalAttack() {
		atk = Enchant.ATK.getEnchant();
		def = Enchant.DEF.getEnchant();
		return enemyDamage_youmuCheck();
	}

	@Override
	public void normalAttack(boolean p_or_e) {
	}

	public void performNewMap() {
		itemFreezeCount();
		yokan();
		class_job.newMapAction();
		shareItem();

	}

	public boolean pinchHp() {
		return getHP() * 3 < getMAX_HP();
	}

	public boolean pinchHyperHp() {
		return getHP() * 4 < getMAX_HP();
	}

	public void playerExpDown() {
		player_exp = EXP.table.levelDownExp();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * reset all conditions and items
	 */
	public void playerLevelUp() {
		int exp = 0;
		if (getExpCash() != 0 && !印超不幸.effect()) {
			exp = EnchantSpecial.happy(getExpCash());
			Message.set(Player.me.getColoredName().concat("は")
					.concat(半角全角コンバーター.半角To全角数字(exp)).concat("ポイントの経験値を得た"));
			expCashReset();
		}
		if (getExpCashNoMessage() != 0) {
			exp += getExpCashNoMessage();
			resetExpCashNoMessage();
		}
		if (exp == 0) {
			return;
		}
		setExp(exp);
		if (Player.me.getLV() >= 99) {
			return;
		}
		int level_count = EXP.table.level(Player.me.player_exp);
		// System.out.println(level_count);
		if (Player.me.getLV() == level_count) {
			return;
		} else if (Player.me.getLV() > level_count) {
			Show.showErrorMessageDialog("エラー　何故かレベルが下がりました");
			return;
		}
		level_count = level_count - Player.me.getLV();
		if (Player.me.getLV() + level_count > 99) {
			level_count -= Player.me.getLV() + level_count - 99;
		}
		for (int i = 1; i <= level_count; i++) {
			setPlayerLv(getLV() + 1);
			int grow = statusGrow(STATUS.HP, true);
			addMAX_HP(grow);
			chengeHP_NoEffect(grow);
		}
		//

		SE.LEVEL_UP.play();
		Message.setConcatFlag(false);
		Message.set(getColoredName(), "は", 半角全角コンバーター.半角To全角数字(getLV()),
				"レベルに上がった。");
	}

	public double playerLv() {
		return Math.pow(Etc.logarithm((double) (LV + 2) / 2, 1.6), 2);
	}

	public double playerStr() {
		double str;
		if (STR >= 8) {
			str = Math.pow(Etc.logarithm((double) (STR - 2) / 2, 1.6), 2);
		} else {
			str = Math.pow(Etc.logarithm(3, 1.6), 2) * STR / 8;
		}
		return str;
	}

	@Override
	public boolean reflection(ThrowingItem ti) {
		if (ti.HOW == HowToThrow.MAGIC && 印山.effect()) {
			SE.ATTACK_SPECIAL_BODY.play();
			if (ti.A instanceof 目からビーム) {
				Message.set("ビームを跳ね返した");
			} else if (ti.A instanceof MagicBullet) {
				Message.set("魔法弾を跳ね返した");
			} else {
				Message.set(ti.A.getColoredName(), "を跳ね返した");
			}
			return true;
		}
		if (印剣.effect()) {
			setStandEffect(剣.class, ti.A.direction.getReverse());
			SE.ATTACK_SWORD.play();
			if (ti.HOW == HowToThrow.MAGIC)
				if (ti.A instanceof 目からビーム) {
					Message.set("ビームを跳ね返した");
				} else {
					Message.set("魔法弾を跳ね返した");
				}
			else
				Message.set("飛来物を跳ね返した");
			return true;
		}
		return super.reflection(ti);
	}

	public void resetAll() {
		Belongings.getListItems().clear();
		resetAll_exceptItems();
	}

	/**
	 * reset all conditions
	 */
	public void resetAll_exceptItems() {
		saisen = null;
		shop = null;
		TurnSystemController.setBoss(null);
		flag_next_monster_house = false;
		TurnSystemController.turnCountReset();
		HoldEnemy.ME.reset();
		MapList.resetHutoChan();
		if (PresentField.get() == null || !PresentField.get().isDungeon()) {
			Checker.init(0);
		}
		resetStatus();
		allItemUnFreeze();
		Enchant.allRemove();
		endDamaging();
		TurnSystemController.setTurnFinish();
		Config.resetRetry();
	}

	public void resetExpCashNoMessage() {
		exp_cash_no_message = 0;
	}

	public void resetPlayingMilliTime() {
		playing_time = 0;
	}

	public void resetStatus() {
		LV = 1;
		setMAX_HP(HP = 15);
		EnemyTable.ねだやしRelease();
		class_job = new ClassDefault();
		MAX_STR = STR = 8;
		Medal.最高ちから.save_the_more(MAX_STR);
		MAX_SATIETY = SATIETY = 100;
		player_exp = 0;
		exp_cash = 0;
		books_count = books = 0;
		dying_frame = 0;
		HoldEnemy.ME.reset();
		CONDITION.conditionAllClear(this);
		EnchantSpecial.removeAlwaysEnchantSpecial();
		setFlag_next_monster_house(false);
		additional_attack_que = 0;
		additional_attack_que_youmu = 0;
		additional_throw_que = 0;
		initShareItem();
	}

	@Override
	protected boolean resistCondition(CONDITION c) {
		return CONDITION.isSetCondition(this, c);
	}

	private boolean reverseAttack(Point p, int x, int y) {
		Point wall = p.getLocation();
		while (MassCreater.getMass(wall.x, wall.y).WALKABLE) {
			wall.translate(x, y);
		}
		Point target = wall.getLocation();
		int count = 0;
		Base_Creature c;
		do {
			count++;
			target.translate(-x, -y);
			c = MapList.getCreature(target);
		} while (c == null);
		boolean flag_self_attack = false;
		if (c.equals(this)) {
			// ワナちぇ
			int i = p.x + x;
			int j = p.y + y;
			Mass m = MassCreater.getMass(i, j);
			boolean flag = m.isWarning()
					&& !(m.isWarningLarge(true) || m.isWarningSmall(true));
			if (checkTrap(i, j))
				return true;
			if (flag) {
				MainMap.addEffect(new DoronEffect(new Point(i, j), null, true),
						true);
				return true;
			}
			flag_self_attack = true;
		} else {
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.間)) {
				Battle.setCriticalFlag();
				EnchantSpecial.attackPhaseStart();
			}
		}
		// 印驚.effect();
		DIRECTION d = direction.getReverse();
		boolean flag = count > 1;
		for (int i = 0; i < (flag ? 2 : 1); i++) {
			target.translate(x, y);
		}
		if (Enchant.ATK.isEnchant()) {
			SpellCard s = ((SpellCard) Enchant.ATK.getEnchant());
			list_attacking_stand.add(new Stand(s.getStand(), d, flag,
					s.stand_lv, target));
		} else {
			list_attacking_stand.add(new Stand(CardAttackEffect.class, d, flag,
					1, target));
		}

		flag_stand_attacking = true;
		// 自分から自分への攻撃無効
		super.startAttack(flag_self_attack ? null : getAttackTask(this, c));
		return true;
	}

	public void selfHealing() {
		if (!MassCreater.getMass(mass_point).WALKABLE) {
			EnchantSpecial.setFlagNeglectHeal(true);
			Player.me.chengeHP(null, "*壁の中にいる*", -(getMAX_HP() + 19) / 20);
			Medal.壁の中に入った.addCount();
			return;
		} else if (getSATIETY() <= 0) {
			if (PresentField.get().isHaraheru()) {
				Player.me.chengeHP_NoEffect(-1);
			}
			return;
		}
		int heal_count = 0;
		int max_hp = PresentField.get().getHealRate();
		// self_healing_rest += max_hp * (1000 + 印回復.selfHeal()) / self_healing;
		self_healing_rest += max_hp * 1000 / self_healing;
		while (true) {
			if (self_healing_rest >= 1000) {
				heal_count++;
				self_healing_rest -= 1000;
			} else
				break;
		}
		if (getConditionList().contains(CONDITION.回復)) {
			heal_count += 5;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.回復)) {
			heal_count += 5;
		}
		if (heal_count == 0)
			return;
		if (HP < getMAX_HP()) {
			Player.me.chengeHP_NoEffect(heal_count);
			if (HP == getMAX_HP() && Scene_Action.getMe().isResting()) {
				MainMap.addEffect(new GoodBadEffect(true));
				SE.STATUS_GOOD.play();
			}
			if (PresentField.get().isSupressHeal()) {
				self_healing_rest = 0;
			}
		} else {
			self_healing_rest = 0;
		}
	}

	@Override
	public void setAnimation(Base_Anime a) {
		super.setAnimation(a);
	}

	public void setAnimation(SpellCard item) {
		setSecondAnimation(new CardAnime(item));
	}

	public void setBackPoint() {
		back_point = Player.me.getMassPoint().getLocation();
	}

	public void setClassJob(BaseClassJob job) {
		class_job = job;
	}

	@Override
	public boolean setCondition(CONDITION c, int timer) {
		return super.setCondition(c, 印病.isEnchant(c, timer));
	}

	public void setDirection(DIRECTION dir) {
		if (dir != null)
			direction = dir;
	}

	private void setExp(int exp) {
		if (player_exp + exp >= 9999999) {
			player_exp = 9999999;

		} else if (player_exp + exp <= 0) {
			player_exp = 0;
		} else {
			player_exp += exp;
		}
		Medal.経験値.save_the_more(player_exp);
	}

	@Override
	public void setExpCash(int enemy_EXP) {
		exp_cash += enemy_EXP;
	}

	public void setExpCashNoMessage(int i) {
		exp_cash_no_message += i;
	}

	public void setFanfare(String... array) {
		Message.set(array);
		flag_clear = true;
		SE.FANFARE2.play();
	}

	public void setFlag_next_monster_house(boolean flag_next_monster_house) {
		this.flag_next_monster_house = flag_next_monster_house;
	}

	@Override
	public void setMassPoint(int x, int y) {
		super.setMassPoint(x, y);
		MiniMap.openTiles();
	}

	@Override
	public void setMassPoint(Point p) {
		super.setMassPoint(p);
		MiniMap.openTiles();
	}

	@Override
	public void setMassPoint_Jump(Point p) {
		if (!PresentField.get().isHaraheru()) {
			return;
		}
		super.setMassPoint_Jump(p);
		MiniMap.openTiles();
	}

	@Override
	public void setMassPoint_Jump(Point p, Task task) {
		super.setMassPoint_Jump(p, task);
		MiniMap.openTiles();
	}

	@Override
	public void setMassPoint_ParabolaJumpAttack(Point p) {
		super.setMassPoint_ParabolaJumpAttack(p);
		MiniMap.openTiles();
	}

	@Override
	public void setMassPoint_ParabolaJumpAttack(Point p, Task task) {
		super.setMassPoint_ParabolaJumpAttack(p, task);
		MiniMap.openTiles();
	}

	@Override
	public void setMassPoint_WalkLike(Point p, int move_speed_level) {
		super.setMassPoint_WalkLike(p, move_speed_level);
		MiniMap.openTiles();
	}

	public void setMAX_SATIETY(int value) {
		if (value > 200) {
			MAX_SATIETY = 200;
		} else {
			MAX_SATIETY = value;
		}
	}

	public void setNoMagic() {
		attacking_direction = direction;
		normal_attack_phase = AttackPhase.GO;
		flag_wand = true;
		SE.ATTACK_HANDS.play();
	}

	public void setPlayerExp(int i) {
		player_exp = i;
	}

	private void setPlayerLv(int lv) {
		LV = lv;
	}

	public void setRestrictionFlags(Base_Map_Random bmr) {
		flag_no_item = Belongings.isEmpty();
		for (Enchant enc : Enchant.values()) {
			hash_restriction.put(enc, true);
		}
		if (bmr instanceof 救出大作戦) {
			flag_no_item_daichan = flag_no_item;
		} else if (bmr instanceof 逆ヶ島) {
		} else {
			flag_no_item_daichan = false;
		}
	}

	public void setSATIETY(int value) {
		SATIETY = value;
		if (SATIETY < 0) {
			SATIETY = 0;
		}
	}

	public void setStand(DIRECTION d, boolean isStandAttacking2MassFurther) {
		if (Enchant.ATK.isEnchant()) {
			SpellCard s = ((SpellCard) Enchant.ATK.getEnchant());
			list_attacking_stand.add(new Stand(s.getStand(), d,
					isStandAttacking2MassFurther, s.stand_lv));
		} else {
			list_attacking_stand.add(new Stand(CardAttackEffect.class, d,
					isStandAttacking2MassFurther, 1));
		}
	}

	public void setStandEffect(Class<? extends Base_Enemy> clazz, DIRECTION d) {
		list_attacking_stand.add(new Stand(clazz, d, false, 0));
	}

	@Override
	public void setSTR(int value) {
		if (value >= MAX_STR) {
			value = MAX_STR;
		} else if (value < 1) {
			value = 1;
		}
		STR = value;
	}

	public void setSubmitionSize(int size) {
		submition_size = size;
	}

	public void setThrowing(Base_Artifact throw_item, DIRECTION d) {
		throw_item.direction = d;
		attacking_direction = direction;
		normal_attack_phase = AttackPhase.GO;
		if (throw_item instanceof MagicBullet) {
			flag_wand = true;
			SE.ATTACK_HANDS.play();
		} else {
			flag_throwing = true;
			this.throw_item = throw_item;
		}
	}

	private void shareItem() {
		if (!SocketHolder.isConnected()) {
			return;
		}
		送信の瓶 jar = null;
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof 送信の瓶) {
				jar = ((送信の瓶) a);
				break;
			}
		}
		boolean flag_new = false;
		if (jar == null) {
			flag_new = true;
			jar = new 送信の瓶(mass_point.getLocation());
		}
		if (flag_new) {
			SE.SYSTEM_PICKUP.play();
			if (Belongings.isMax()) {
				ItemFall.itemFall(jar);
				View_Sider.setInformation(jar.getColoredName(),
						"がアイテム欄に追加されました");
			} else {

				Belongings.setItems(jar);
				View_Sider.setInformation(jar.getColoredName(),
						"がアイテム欄に追加されました");
			}
		}
		if (MapList.getFloor() % 5 == 0) {
			jar.addMax();
			View_Sider.setInformation("容量が１増えました");
		}
	}

	public void standAttack(boolean isStandAttacking2MassFurther) {
		if (Enchant.ATK.isEnchant() || isStandAttacking2MassFurther) {
			setStand(direction, isStandAttacking2MassFurther);
			flag_stand_attacking = true;
		}
	}

	@Override
	public void startAttack(Task t) {
		Show.showConfirmDialog("error@Player.startAttack()\nThis method is not though to be called");
		standAttack(false);
		super.startAttack(t);
	}

	public void startAttack(Task t, boolean isStandAttacking2MassFurther) {
		standAttack(isStandAttacking2MassFurther);
		super.startAttack(t);
	}

	public void startAttackWithHand(Task t) {
		super.startAttack(t);
	}

	private int statusGrow(STATUS status, boolean plus) {
		int grow = 0;
		Random ran = new R();
		switch (status) {
		case STR:
			grow = ran.nextInt(3) + 1;
			break;
		case DEF:
			grow = ran.nextInt(3) + 1;
			break;
		case HP:
			grow = ran.nextInt(5) + 2;
			break;
		}
		if (plus)
			return grow;
		else
			return -grow;
	}

	@Override
	protected List<Integer> statusLevel() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(15);
		list.add(8);
		list.add(0);
		list.add(0);
		return list;
	}

	public void turnSatiety() {
		if (!PresentField.get().isHaraheru()) {
			return;
		}
		if (印空腹.notHungry()) {
			return;
		}
		turn_satiety = 印空腹.hungry();
		turn_satiety_rest += 10;
		if (turn_satiety_rest >= turn_satiety) {
			turn_satiety_rest = 0;
			chengeSatiety(-1);
		}
	}

	@Override
	public void upDate() {
		addPlayingMilliTime(FR.THREAD_SLEEP);
		if (isDying()) {
			addPlayingMilliTime(-FR.THREAD_SLEEP);
			if (dying_frame != -1) {
				dying_frame++;
				float a = getDyingAlpha();
				if (a == 0f) {
					dying_frame = -1;
					death_at_dying_finished();
				}
			}
		} else if (Image_Player.isAnimating()) {
			Image_Player.upDate();
		} else if (flag_throwing) {
			super.upDate_NormalAttack();
			if (throw_item != null && attaking_frame >= 3) {
				throw_item.itemThrow(this);
				throw_item = null;
			}
			flag_throwing = (normal_attack_phase != null);
		} else if (flag_wand) {
			super.upDate_NormalAttack();
			flag_wand = (normal_attack_phase != null);
		} else {
			super.upDate();
		}
		for (ListIterator<Stand> li = list_attacking_stand.listIterator(); li
				.hasNext();) {
			Stand s = li.next();
			s.upDate();
			if (s.end()) {
				li.remove();
			}
		}

	}

	@Override
	public void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		if (normal_attack_phase == null) {
			flag_stand_attacking = false;
			if (additional_throw_que > 0) {
				flag_additional = true;
				additional_throw_que--;
				SE.THROW_HEAVY.play();
				attacking_direction = direction;
				normal_attack_phase = AttackPhase.GO;
				Message.set("エクスパンデッド御柱を投げた");
				new 御柱(this).itemThrow(this, HowToThrow.BREAK, 3);
			} else if (additional_attack_que > 0) {
				flag_additional = true;
				Base_Artifact atk = Enchant.ATK.getEnchant();
				Base_Artifact def = Enchant.DEF.getEnchant();
				Enchant.ATK.setEnchant(def);
				Enchant.DEF.setEnchant(atk);
				Message.set("コンビネーション！");
				additional_attack_que--;
				enemyDamage_youmuCheck();
			} else {
				attackEnd();
			}
		}
	}

	public void updateStatus() {
		if (getMAX_HP() < getHP()) {
			setHP(getMAX_HP());
		}
		if (getMAX_STR() < getSTR()) {
			setSTR(getMAX_STR());
		}
	}

	private void yokan() {
		// if (warning()) {
		// if (new R().is(5)) {
		// SE.GOGOGO.play();
		// FrameShaker.doneSoftly();
		// Message.set("嫌な予感がする");
		// }
		// }
	}

}
