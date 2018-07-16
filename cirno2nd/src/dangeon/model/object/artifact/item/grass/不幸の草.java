package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

public class 不幸の草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "不幸草";
	public static final String item_name_sound = "ふこうそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.不幸;

	public 不幸の草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		if (c instanceof Player
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.衰)) {
			Message.set(Player.me.getColoredName(), "はリボンの効果でレベルが下がらなかった");
			return;
		}
		c.addLV(-1);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "投げても装備しても敵のレベルが下がるから何かと便利だが敵はレベルが変わるとＨＰが全回復するから注意。ちなみに砂の味がする。";
	}

	@Override
	public boolean grassUse() {
		MainMap.addEffect(new GoodBadEffect(false));
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.衰)) {
			Message.set(Player.me.getColoredName(), "はリボンの効果でレベルが下がらなかった");
			return true;
		}
		if (Player.me.getLV() == 1) {
			Message.set(Player.me.getColoredName().concat("特に効果が無かった"));
			return true;
		}
		Player.me.addLV(-1);
		return true;
	}

}
