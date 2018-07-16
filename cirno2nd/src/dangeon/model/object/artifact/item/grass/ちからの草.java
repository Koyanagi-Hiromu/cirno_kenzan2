package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.anime.OuraEffect;
import dangeon.view.detail.MainMap;

public class ちからの草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ちからの草";
	public static final String item_name_sound = "ちからのくさ";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.力１;

	public ちからの草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "ちからが減っているときはちからが１回復する。ちからが最大のときは最大値と共にちからが上昇する。ちなみに鏡開きが遅れたもちの味がする。";
	}

	@Override
	public boolean grassUse() {
		Player.me.setAnimation(new OuraEffect(Player.me, new Task() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Player.me.setCondition(CONDITION.超会心, 50);
				MainMap.addEffect(new GoodBadEffect(true));
				SE.POWER_UP.play();
				if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
						ENCHANT_SIMBOL.医)) {
					Player.me.setSTR(Player.me.getMAX_STR());
					Player.me.changeMAX_STR(1);
					Message.set(new String[] { "チルノのちからが全回復して限界値が１上がった" });
				} else {
					if (Player.me.getMAX_STR() == Player.me.getSTR()) {
						Message.set(new String[] { "チルノのちからの限界値が１上がった" });
						Player.me.changeMAX_STR(1);
					} else {
						Message.set(new String[] { "チルノのちからが回復した" });
						Player.me.setSTR(Player.me.getSTR() + 1);
					}
				}
			}
		}, true));

		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}

	@Override
	protected boolean isAbleToBeHittedChecked(Base_Creature c) {
		return false;
	}

}