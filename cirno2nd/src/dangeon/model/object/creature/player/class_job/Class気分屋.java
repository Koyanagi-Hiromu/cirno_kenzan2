package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.scrool.解凍の書;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.古明地こいしのカード;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusSimbol;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;
import dangeon.util.R;

public class Class気分屋 extends BaseClassJob {
	@Override
	public Medal getMedal_ミラクルクエスト() { return Medal.Class気分屋; }
	@Override
	public Medal getMedal_七曜クエスト() { return Medal.Class気分屋_七曜; }

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.無));
		list.add(new BonusSimbol(ENCHANT_SIMBOL.謎));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 2, "階段を降りると：グッドステータス∞"));
		list.add(new BonusSimbol(ENCHANT_SIMBOL.狸));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 解凍の書(p)));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 0, "凍ったアイテムの使用不可"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 1, "階段を降りると：グッドステータス∞"));
	}

	@Override
	public SpellCard getRep() {
		return new 古明地こいしのカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "２５％で透明状態で出現";
	}

	@Override
	public void newMapAction() {
		CONDITION[] cs = { CONDITION.イリュージョン, CONDITION.パワーアップ, CONDITION.三倍速,
				CONDITION.倍速, CONDITION.反射, CONDITION.回復, CONDITION.復活,
				CONDITION.炎上, CONDITION.目薬, CONDITION.蛍, CONDITION.超会心,
				CONDITION.透明, CONDITION.透視 };
		for (int i = 0; i < 3; i++) {
			if (checkSwicth(i + 1)) {
				CONDITION c = cs[new R().nextInt(cs.length)];
				if (c == CONDITION.倍速) {
					if (Player.me.conditionCheck(CONDITION.三倍速)) {
						break;
					}
				}
				if (c == CONDITION.三倍速) {
					Player.me.getConditionList().remove(CONDITION.倍速);
				}
				Player.me.setCondition(c, -2);
			}
		}
		super.newMapAction();
	}
}
