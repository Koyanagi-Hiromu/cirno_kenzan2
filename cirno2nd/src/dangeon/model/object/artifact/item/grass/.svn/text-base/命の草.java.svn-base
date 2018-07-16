package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

/**
 * 命の草
 * 
 * @author Administrator
 * 
 */
public class 命の草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.GRASS;
	public static final String item_name = "命の草";
	public static final String item_name_sound = "いのちのくさ";
	public static final int composition = 0;
	public static final int item_use_level1 = 5;

	// public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.命;

	public 命の草(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.ハート;
	}

	@Override
	protected void effect(Base_Creature c) {
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "最大ＨＰが増えた分のＨＰは回復しない。最大ＨＰが増えると自然回復量も上がるので満腹度も減りにくくなる。ちなみに牛乳の味がする。";
	}

	@Override
	public boolean grassUse() {
		SE.HEAL_SMALL.play();
		int max_heal = item_use_level1;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.医)) {
			max_heal = max_heal + 1;
		}
		MainMap.addEffect(new GoodBadEffect(true));
		Message.set(new String[] { "最大HPが", 半角全角コンバーター.半角To全角数字(max_heal),
				"ポイント上がった" });
		Player.me.addMAX_HP(max_heal);
		return true;
	}

	@Override
	protected boolean isAbleToBeHittedChecked(Base_Creature c) {
		return false;
	}

	@Override
	public boolean waterAction() {
		if (isCurse()) {
			return super.waterAction();
		} else {
			Medal.命の草から蓮の花を咲かせた.addCount();
			staticCheck();
			Message.set(getColoredName(), "が水に落ちて蓮の花が咲いた");
			Mass m = MassCreater.getMass(mass_point);
			m.setWaterLeefOn(false);
			m.FLOWER = true;
			MapList.removeArtifact(this);
			return false;
		}
	}
}