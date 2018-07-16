package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

/**
 * 
 * @author Administrator
 * 
 */
public class 苦無草 extends Base_Grass {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "めぐすり草";
	public static final String item_name_sound = "めくすりそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.影縫い;

	public 苦無草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_SHADOW.play();
		c.setCondition(CONDITION.影縫い, 0);
		int h = 150;
		c.heal(Player.me, h);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "罠や透明の敵を見ることができるよく見え状態になる。良い状態も１つの状態異常として扱われる。ちなみにイチゴシロップの味がする。";
	}

	@Override
	public boolean grassUse() {
		if (Player.me.getHP() < Player.me.getMAX_HP()) {
			int h = 150;
			// Message.set("HPが", 半角全角コンバーター.半角To全角数字(h), "ポイント回復した");
			Player.me.chengeHP(this, null, h);
		} else {
			int max_heal = 4;
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.医)) {
				max_heal = max_heal + 2;
			}
			Message.set(new String[] { "最大HPが", max_heal + "ポイント増えた" });
			Player.me.addMAX_HP(max_heal);
			Player.me.chengeHP(null, null, max_heal);
		}
		MainMap.addEffect(new GoodBadEffect(false));
		SE.STATUS_SHADOW.play();
		Player.me.setCondition(CONDITION.影縫い, 0);
		return true;
	}
}