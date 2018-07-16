package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

/**
 * 胃拡張の種
 * 
 * @author Administrator
 * 
 */
public class 胃拡張の種 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "胃拡張の種";
	public static final String item_name_sound = "いかくちょうのたね";
	private static final int item_use_level1 = 10;
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.大;

	public 胃拡張の種(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		if (c instanceof Player)
			grassUse();
		else if (c instanceof Base_Enemy) {
			if (((Base_Enemy) c).getLargement() < 2) {
				SE.BIGGER.play();
				SE.ISHUKUSHO.play();
				((Base_Enemy) c).changeSize(1);
				Message.set(c.getColoredName(), "は大きくなった");
			}
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "最大満腹度が高いとおにぎりを胃に収めておけるのでその分腐る心配が減ったり他のアイテムが持てたりと良いことづくめ。ちなみに梅干しの味がする。";
	}

	@Override
	public boolean grassUse() {
		MainMap.addEffect(new GoodBadEffect(true));
		if (Player.me.getMAX_SATIETY() >= 200) {
			Message.set(new String[] { "なにも起こらなかった" });
			if (isEnchantedNow())
				Enchant.forceToRemove(this);
			Belongings.remove(this);
			return true;
		}
		SE.IKAKUTYO.play();
		int i = item_use_level1;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.医)) {
			i += 5;
		}
		if (Player.me.getMAX_SATIETY() + i >= 200) {
			i = 200 - Player.me.getMAX_SATIETY();
		}
		Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + i);
		Message.set(new String[] { "チルノの最大満腹度が", 半角全角コンバーター.半角To全角数字(i), "増えた" });
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}

}