package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 薬草 装備時特殊効果 HP+ 使用時 HP回復
 * 
 * @author Administrator
 * 
 */
public class 妹切草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "妹切草";
	public static final String item_name_sound = "いもきりそう";
	public static final int item_use_power1 = 150;
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.妹;

	public 妹切草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		c.heal(Player.me, item_use_power1);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "いもぎりそうと読む。希少価値が高く争いまで起こったがあまりに大切にされすぎて誰からも忘れられた草。ちなみにどこか切ない味がする。";
	}

	@Override
	public boolean grassUse() {
		int heal = item_use_power1;
		int max_heal = 4;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.医)) {
			// heal = Player.me.getMAX_HP();
			max_heal = max_heal + 2;
		}
		if (Player.me.getHP() < Player.me.getMAX_HP()) {
			Player.me.chengeHP(null, null, heal);
			// Message.set(new String[] { "HPが",
			// 半角全角コンバーター.半角To全角数字(heal) + "ポイント回復した" });
		} else {
			Message.set(new String[] { "最大HPが",
					半角全角コンバーター.半角To全角数字(max_heal) + "ポイント増えた" });
			Player.me.addMAX_HP(max_heal);
			Player.me.chengeHP(null, null, max_heal);
		}
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}
}
