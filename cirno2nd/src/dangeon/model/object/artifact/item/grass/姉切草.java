package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

/**
 * 薬草 装備時特殊効果 HP+ 使用時 HP回復
 * 
 * @author Administrator
 * 
 */
public class 姉切草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "姉切草";
	public static final String item_name_sound = "あねきりそう";
	public static final int item_use_power1 = 100;
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.薬;

	public 姉切草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		if (c.isBadCondition()) {
			for (CONDITION condition : c.getBadConditionList()) {
				CONDITION.conditionRecovery(c, condition);
			}
			for (CONDITION con : c.getConditionRemoveTask()) {
				c.getConditionList().remove(con);
			}
			c.getConditionRemoveTask().clear();
		}
		c.heal(Player.me, item_use_power1);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "ＨＰが全回復しているときに飲むと最大ＨＰが２上昇する。なので未識別の草を飲む時は一応ＨＰを全回復させてから飲むとよい。ちなみにホウレンソウの味がする。";
	}

	@Override
	public boolean grassUse() {
		MainMap.addEffect(new GoodBadEffect(true));
		if (Player.me.isBadCondition()) {
			for (CONDITION condition : Player.me.getBadConditionList()) {
				CONDITION.conditionRecovery(Player.me, condition);
			}
			for (CONDITION con : Player.me.getConditionRemoveTask()) {
				Player.me.getConditionList().remove(con);
			}
			Player.me.getConditionRemoveTask().clear();
		}
		int heal = item_use_power1;
		int max_heal = 2;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.医)) {
			max_heal = max_heal + 1;
		}
		if (Player.me.getHP() < Player.me.getMAX_HP()) {
			Player.me.chengeHP(null, null, heal);
			// Message.set(new String[] { "HPが",
			// 半角全角コンバーター.半角To全角数字(heal) + "ポイント回復した" });
		} else {
			Message.set(new String[] { "最大HPが", max_heal + "ポイント増えた" });
			Player.me.addMAX_HP(max_heal);
			Player.me.chengeHP(null, null, max_heal);
		}
		if (Player.me.getMAX_STR() > Player.me.getSTR()) {
			Message.set(new String[] { "チルノのちからが１回復した" });
			Player.me.setSTR(Player.me.getSTR() + 1);
		}
		if (isEnchantedNow())
			Enchant.forceToRemove(this);
		Belongings.remove(this);
		return true;
	}
}
