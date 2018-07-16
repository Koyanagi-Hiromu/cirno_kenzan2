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
public class オオイカリの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "オオイカリの杖";
	public static final String item_name_sound = "いかりのつえ";

	private boolean flag_worst = false;

	public オオイカリの杖(Point p) {
		this(p, false);
	}

	public オオイカリの杖(Point p, boolean b) {
		super(p, item_name, 1);
		flag_worst = b;
	}

	@Override
	protected void effect(Base_Creature c) {
		int t = 0;

		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			t = -1;
		}
		if (Player.me.equals(c) && flag_worst) {
			if (c.conditionCheck(CONDITION.邪法)) {
				c.setCondition(CONDITION.死, t);
				SE.YUYUKO_SPELL.play();
			} else {
				c.setCondition(CONDITION.邪法, t);
				SE.SYSTEM_CURSE.play();
			}
		} else {
			SE.STATUS_SPEEDY.play();
			c.setCondition(CONDITION.倍速, t);
			if (!Player.me.equals(c)) {
				SE.STATUS_DODODO.play();
				c.setCondition(CONDITION.イカリ, t);
			}
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}