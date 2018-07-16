package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 金縛りの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "金縛りの杖";
	public static final String item_name_sound = "かなしはりのつえ";

	public 金縛りの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_SIBIBI.play();
		int t;
		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			t = -1;
		} else {
			t = 0;
		}
		c.setCondition(CONDITION.麻痺, t);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "金縛りはしばらくすると解けてしまう。魔法弾は必ず当たるのでここぞという時はまず杖を頼ろう。ピンチなのに何も考えず物を投げたり攻撃しちゃう君はまだ初級者のようだね。";
	}

}