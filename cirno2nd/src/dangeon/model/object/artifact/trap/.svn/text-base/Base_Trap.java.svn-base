package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.constant.FR;
import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.scene.action.message.MessageLock;
import dangeon.latest.scene.action.message.MsgBlock;
import dangeon.latest.system.KeyHolder;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印罠師;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public abstract class Base_Trap extends Base_Artifact {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private boolean jap = false;

	protected int durability;

	public static final int trap_list_value = 14;
	protected boolean flag_broken = false;
	private int demand_wait_min_frame;
	protected boolean flag_demand_waiting = false;

	protected Base_Trap(Point p, String name, int parcentage, int durability) {
		super(p, name, 0, ITEM_CASE.TRAP, false);
		this.durability = durability;
		visible = MassCreater.getMass(p).isTrapChecked();
	}

	protected Base_Artifact action(Base_Artifact a) {
		return a;
	}

	protected abstract void action(Base_Creature c);

	private void breakingCheck() {
		int ran2 = new R().nextInt(100);
		if (ran2 < durability) {
			if (getListComposition().contains(ENCHANT_SIMBOL.金)) {
				getListComposition().remove(ENCHANT_SIMBOL.金);
				Message.set(getColoredName(), "の神の加護が剥がれた@");
			} else {
				flag_broken = true;
				Message.set(getColoredName().concat("は壊れた"));
				TaskOnMapObject.addTrapRemoveTask(this);
			}
		}
	}

	/**
	 * とりあえず無視する
	 */

	@Override
	public void check() {
	}

	protected int enchantAttack(boolean normal, Base_Creature c, int damage) {
		return 0;
	}

	@Override
	protected int enchantDefence(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "踏むと様々な効果が発生するぞ", "普段は目に見えないぞ" };
	}

	@Override
	protected String getSecondExplain_ByCategory() {
		return "踏むと何かしら効果が発生する。踏むまで地面に隠れていて見えないが罠がある場所で素振りをすることで発見することができる。なお何回か踏むと壊れてしまう。";
	}

	/**
	 * 呪われるか否か
	 */
	@Override
	public boolean isAbleToCurse() {
		return false;
	}

	public boolean isAbleToWork() {
		return MapList.getArtiface_exceptME(getMassPoint(), this) == null;
	}

	private void isActive() {
		int ran = new R().nextInt(100) + 1;
		int active;
		if (印罠師.effect()) {
			active = 0;
		} else if (jap) {
			active = 200;
		} else if (visible || Player.me.conditionCheck(CONDITION.目薬)) {
			if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.戯)) {
				active = 0;
			} else {
				active = 70;
			}
		} else {
			active = 90;
		}

		// if (EnchantSpecial.enchantSimbolAllCheck(EnchantSpecial.CASE.ALL,
		// ENCHANT_SIMBOL.裁)) {
		// // if(ran)
		// }
		if (ran <= active) {
			trapEffect();
		} else {
			Message.set("しかし罠は発動しなかった。");
			Message.setConcatFlag(false);
			flag_demand_waiting = false;
		}
	}

	@Override
	public boolean isDemandWaiting() {
		if (demand_wait_min_frame > 0) {
			demand_wait_min_frame -= FR.THREAD_SLEEP;
			return true;
		} else {
			return flag_demand_waiting;
		}
	}

	@Override
	public boolean isMobile() {
		// if (印罠師.effect())
		// return true;
		return false;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	public void itemHit() {
	}

	@Override
	public void itemHit(final Base_Creature c, Base_Creature c2) {
		final Base_Trap ME = this;
		new Task() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Message.set(ME.getColoredName(), "が反応した");
				trapEffect(c);
				if (!flag_broken) {
					ItemFall.itemFall(ME);
				}
			}
		}.work_appointment();
	}

	@Override
	public boolean itemUse() {
		return false;
	}

	@Override
	public void itemUseThis() {
		trapEffect();
		TurnSystemController.callMeToStartEnemyTurn();
	}

	@Override
	public void itemUseThis_fromMenu(KeyHolder KH, Base_View view) {
		super.itemUseThis_fromMenu(KH, view);
		if (Belongings.get(-1) != this) {
			if (isEnchantedNow())
				Enchant.forceToRemove(this);
			Belongings.remove(this);
		}
	}

	protected void setDemandReleaseTask() {
		Message.setTask_AfterReleaseDemandToPushEnter(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				flag_demand_waiting = false;
			}
		});
	}

	public Base_Trap setJapanese() {
		jap = true;
		durability = 0;
		return this;
	}

	public void trapEffect() {
		trapEffect(Player.me);
	}

	public Base_Artifact trapEffect(Base_Artifact a) {
		visible = true;
		SE.SYSTEM_TRAP_ON.play();
		Base_Artifact A = action(a);
		if (A != null) {
			MapList.removeArtifact(a);
			ItemFall.itemFall(A);
		}
		breakingCheck();
		return a;
	}

	public void trapEffect(Base_Creature c) {
		visible = true;
		action(c);
		breakingCheck();
		Message.setConcatFlag(false);
	}

	@Override
	public boolean walkOnAction() {
		if (印罠師.effect()) {
			Message.set(getColoredName(), "の上に乗った");
			return false;
		}
		if (Player.me.isFlying()) {
			return false;
		}
		flag_demand_waiting = true;
		demand_wait_min_frame = MsgBlock.BLOCK_DURATION;
		SE.SYSTEM_TRAP_ON.play();
		Message.setConcatFlag(false);
		if (印罠師.effect() || visible || Player.me.conditionCheck(CONDITION.目薬)) {
			visible = true;
			Message.set(getColoredName(), "を踏んだ。");
			isActive();
		} else {
			new MessageLock(getColoredName(), "を踏んだ。") {

				@Override
				public void second() {
					isActive();
				}
			};
			visible = true;
		}
		return this instanceof 落とし穴の罠;
	}

	@Override
	public boolean walkOnAction(boolean b) {
		if (印罠師.effect()) {
			Message.set(getColoredName(), "の上に乗った");
			return false;
		}
		return walkOnAction();
	}

	/**
	 * エネミーが罠を踏んだときのアクション
	 * 
	 * @param em
	 */
	public void walkOnActionByEnemy(Base_Enemy em) {
		if (!印罠師.effect()) {
			return;
		}
		if (em.isFlying()) {
			return;
		}
		flag_demand_waiting = true;
		demand_wait_min_frame = MsgBlock.BLOCK_DURATION;
		SE.SYSTEM_TRAP_ON.play();
		Message.setConcatFlag(false);
		Message.set(em.getName(), "は", getColoredName(), "を踏んだ。");
		action(em);
		breakingCheck();
		Message.setConcatFlag(false);
	}

}
