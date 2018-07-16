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
public class 邪法の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "邪法の杖";
	public final int turn;
	public static final String item_name_sound = "しゃほうのつえ";

	public 邪法の杖(Point p) {
		this(p, 0);
	}

	public 邪法の杖(Point p, int turn) {
		super(p, item_name, 1);
		this.turn = turn;
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.SYSTEM_CURSE.play();
		int t = turn;
		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			if (t == 0)
				t = -1;
			else
				t /= 2;
		}
		c.setCondition(CONDITION.邪法, turn);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "金縛りはしばらくすると解けてしまう。魔法弾は必ず当たるのでここぞという時はまず杖を頼ろう。ピンチなのに何も考えず物を投げたり攻撃しちゃう君はまだ初級者のようだね。";
	}

	@Override
	public boolean isNeglectiveForWall_Magic() {
		return true;
	}

}