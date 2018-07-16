package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class すばやさ草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "すばやさ草";
	public static final String item_name_sound = "すはやさそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.倍速;

	public すばやさ草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_SPEEDY.play();
		c.setCondition(CONDITION.倍速, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 決定稿か不明
		return "飲んだあともう１回行動できるのでピンチの時はとりあえず飲んでよい。敵がいつ行動するか意識して動こう。ちなみにハッカキャンディの味がする。";
	}

	@Override
	public boolean grassUse() {
		SE.STATUS_SPEEDY.play();
		Player.me.setCondition(CONDITION.倍速, 0);
		Message.set(new String[] { "素早くなった" });
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return false;
	}

}
