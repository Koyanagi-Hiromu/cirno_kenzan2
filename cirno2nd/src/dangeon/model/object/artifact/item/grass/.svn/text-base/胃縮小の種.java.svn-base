package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

/**
 * 胃拡張の種
 * 
 * @author Administrator
 * 
 */
public class 胃縮小の種 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "胃縮小の種";
	public static final String item_name_sound = "いしゅくしょうのたね";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.大;

	public 胃縮小の種(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		if (c instanceof Player)
			grassUse();
		else if (c instanceof Base_Enemy) {
			if (((Base_Enemy) c).getLargement() >= 0) {
				SE.BIGGER.play();
				SE.ISHUKUSHO.play();
				((Base_Enemy) c).changeSize(-1);
				Message.set(c.getColoredName(), "は小さくなった");
			}
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "投げ当てても何の効果もないマイナスアイテム。栄光のリボンで効果を防げるので装備して飲めばお腹の足しになる。ちなみに麦茶の味がする。";
	}

	@Override
	public boolean grassUse() {
		MainMap.addEffect(new GoodBadEffect(false));
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.衰)) {
			Message.set("しかし", Player.me.getColoredName(), "は平気だった");
			return true;
		}
		if (Player.me.getMAX_SATIETY() <= 0) {
			return true;
		}
		Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() - 10);
		if (Player.me.getMAX_SATIETY() < Player.me.getSATIETY()) {
			Player.me.setSATIETY(Player.me.getMAX_SATIETY());
		}
		SE.ISHUKUSHO.play();
		Message.set(new String[] { Player.me.getColoredName() + "の最大満腹度が減った" });
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}

}