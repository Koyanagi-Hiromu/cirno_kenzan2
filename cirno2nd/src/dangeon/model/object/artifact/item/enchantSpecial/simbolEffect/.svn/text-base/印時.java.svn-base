package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.十六夜咲夜のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;

public class 印時 extends EnchantSpecial {
	private static Arrow setArrow(Arrow a) {
		if (a.getArrowRest() < 1) {
			return null;
		}
		if (a.getArrowRest() == 1) {
			return a;
		} else {
			a.addArrowRest(SetEnchantCard.isSetCard(十六夜咲夜のカード.class) ? 0 : -1);
			return a.getOne();
		}
	}

	public static void throwKnife(Arrow a) {
		DIRECTION d = Player.me.getDirection();
		Arrow __a = setArrow(a);
		Player.me.itemThrow(__a, d);
		Message.set("チルノは", __a.getColoredName(), "を投げた");
	}
}
