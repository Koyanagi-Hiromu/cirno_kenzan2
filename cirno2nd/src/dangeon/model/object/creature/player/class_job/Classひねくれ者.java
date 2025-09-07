package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.鬼人正邪のカード;
import dangeon.model.object.artifact.item.staff.一時しのぎの杖;
import dangeon.model.object.artifact.item.staff.倍速の杖;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;

public class Classひねくれ者 extends BaseClassJob {
	@Override
	public Medal getMedal_ミラクルクエスト() { return Medal.Classひねくれ者; }
	@Override
	public Medal getMedal_七曜クエスト() { return Medal.Classひねくれ者_七曜; }

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 0, "魔法弾や投擲物が自分に向かう"));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 一時しのぎの杖(p).createStaffRest(2)));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 倍速の杖(p).createStaffRest(2)));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
//		list.add(new BonusSwitch(this, 6, "凍ったアイテムが装備できない"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		// list.add(new Bonusひねくれ者(this));
		list.add(new BonusSwitch(this, 3, "通常攻撃が壁から自分に向かう"));
	}

	@Override
	public SpellCard getRep() {
		return new 鬼人正邪のカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "冒険家クラスを選択した";
	}
}
