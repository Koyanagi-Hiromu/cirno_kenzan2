package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class アイスの種 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "アイスの種";
	public static final String item_name_sound = "あいすのたね";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.冴;

	public アイスの種(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "凍っている状態によって様々な効果が発揮される";
	}

	@Override
	public boolean grassUse() {
		iceEffect(Player.me);
		return true;
	}

	private void iceEffect(Base_Creature c) {
		if (this.isFrozen()) {
			SE.POWER_UP.play();
			if (c.getMAX_STR() == c.getSTR()) {
				Message.set(new String[] { c.getColoredName(), "のちからの限界値が２上がった" });
				c.changeMAX_STR(2);
			} else if (c.getMAX_STR() == c.getSTR() + 1) {
				Message.set(new String[] { c.getColoredName(), "のちからの限界値が１上がった" });
				c.setSTR(c.getSTR() + 1);
				c.changeMAX_STR(1);
			} else {
				Message.set(new String[] { c.getColoredName(), "のちからが２回復した" });
				c.setSTR(c.getSTR() + 2);
			}
		} else if (this.isCold()) {
			SE.HEAL_SMALL.play();
			Message.set(new String[] { "最大HPが７ポイント上がった" });
			c.addMAX_HP(7);
		} else {
			if (c instanceof Player) {
				if (Player.me.getMAX_SATIETY() >= 200) {
					Message.set(new String[] { "しかしなにも起こらなかった" });
					if (isEnchantedNow())
						Enchant.forceToRemove(this);
					Belongings.remove(this);
					return;
				}
				SE.IKAKUTYO.play();
				Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + 10);
				Message.set(new String[] { "チルノの最大満腹度が１０増えた" });
			}
		}
	}

	@Override
	protected boolean isAbleToBeHittedChecked(Base_Creature c) {
		return false;
	}

}
