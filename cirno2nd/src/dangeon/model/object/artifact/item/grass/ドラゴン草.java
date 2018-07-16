package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.bullet.ドラゴンブレス;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印炎;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.FireEffect;
import dangeon.view.detail.MainMap;

/**
 * ドラゴン草
 * 
 * @author Administrator
 * 
 */
public class ドラゴン草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ドラゴン草";
	public static final String item_name_sound = "とらこんそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.火;

	public ドラゴン草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	private boolean checkStraight() {
		Point p = Player.me.getMassPoint().getLocation();
		DIRECTION d = Player.me.getDirection();
		p.translate(d.X, d.Y);
		while (MassCreater.getMass(p).WALKABLE) {
			Base_Enemy e = MapList.getEnemy(p);
			if (e != null && !(e instanceof Base_NPC)) {
				return true;
			}
			p.translate(d.X, d.Y);
		}
		return false;
	}

	@Override
	protected void effect(final Base_Creature c) {
		final ドラゴン草 ME = this;
		MainMap.addEffect(new FireEffect(c, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				int dam = 70;
				if (c instanceof Player) {
					dam = 印炎.effect(dam);
				}
				dam = c.damagedWithFire(dam);
				if (c.equals(Player.me)) {
					Damage.damage(ME, null, ME.getName() + "によって燃え尽きた",
							Player.me, c, 印炎.effect(dam));
				} else {
					Damage.damage(ME, null, "燃え尽きた", Player.me, c, dam);
				}
			}
		}), true);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "飛距離無限大の必中する威力７０の炎を吐く。美鈴はこのアイテムを見て炎を出す練習をしたとかしないとか。ちなみにトウガラシの味がする。";
	}

	@Override
	public boolean grassUse() {
		if (checkStraight()) {
			Message.set("炎を吹いた");
			new ドラゴンブレス(Player.me, 70).itemThrow(Player.me, HowToThrow.MAGIC,
					Math.max(MassCreater.WIDTH, MassCreater.HEIGHT));
		} else {
			MainMap.addEffect(new FireEffect(Player.me, new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					Player.me.setCondition(CONDITION.炎上, 20);
					Message.set("体が炎に包まれた");
				}
			}), true);
		}
		return true;
	}

}