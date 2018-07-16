package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class 敵倍速の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "黒染の書";
	public static final String item_name_sound = "くろそめのしょ";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.倍速;

	public 敵倍速の書(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "使用するとダンジョン内の敵の移動速度が１段階上がる。敵は鈍足なら等速に、等速なら倍速に、倍速なら３倍速になる。印も敵が倍速になるだけなのでマイナスです。";
	}

	@Override
	public void scroolUse() {
		SE.STATUS_SPEEDY.play();
		SE.YUYUKO_SPELL.play();
		for (Base_Creature creature : MapList.getListEnemy()) {
			creature.setCondition(CONDITION.倍速, 0);
			creature.setCondition(CONDITION.死, 44);
		}
	}
}
