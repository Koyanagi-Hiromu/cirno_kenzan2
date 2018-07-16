package dangeon.model.map.field.random.bossmap;

import java.awt.Point;

import main.res.BGM;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.ItemFall;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.pot.合成の瓶;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.少名針妙丸;

public abstract class BossMap_Daisakusen extends BossMap {

	private static final long serialVersionUID = 1L;

	public BossMap_Daisakusen(Base_Map_Random bmr) {
		super(bmr, 0, new Point(19, 24));
	}

	@Override
	public void appearStair() {
		int size = 4 - BMR.getDIFFICULTY().index;
		if (size < 2)
			size = 2;
		合成の瓶 jar = new 合成の瓶(boss.getMassPoint(), size);
		Checker.checkStatic(jar);
		ItemFall.itemFall(jar);
		super.appearStair();
	}

	@Override
	public BGM getBGM() {
		return BGM.speranza;
	}

	@Override
	protected Base_Enemy getBoss() {
		return new 少名針妙丸(new Point(19, 9), 4)
				.setConditionList(CONDITION.イカリ, 3);
	}

	@Override
	public int getTrapDefaultValue() {
		return 0;
	}

	@Override
	protected void message() {
		Message.set("わー　やられたー@");
	}

}
