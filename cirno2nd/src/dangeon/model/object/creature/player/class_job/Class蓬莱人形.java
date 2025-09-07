package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.grass.妹切草;
import dangeon.model.object.artifact.item.grass.鳳凰の種;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.藤原妹紅のカード;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusItems;
import dangeon.model.object.creature.player.class_job.bonus.BonusStatus;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;

public class Class蓬莱人形 extends BaseClassJob {
	@Override
	public Medal getMedal_ミラクルクエスト() { return Medal.Class蓬莱人形; }
	@Override
	public Medal getMedal_七曜クエスト() { return Medal.Class蓬莱人形_七曜; }

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 3, "復活と同時に自爆"));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItems(鳳凰の種.class, 5));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusStatus(this, CONDITION.復活));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 0, "被ダメージ量が２.５倍"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 藤原妹紅のカード(p), 6));
		list.add(new BonusItem(new 妹切草(p)));
		list.add(new BonusSwitch(this, 2, "復活と同時に炎上状態"));
		list.add(new BonusSwitch(this, 1, "階段を降りてもアイテムが冷えない"));
		list.add(new BonusSwitch(this, 4, "最大ＨＰをフロア数だけ減らして自動復活"));
	}

	@Override
	public SpellCard getRep() {
		return new 藤原妹紅のカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "累計で１０回以上復活したことがある";
	}
}
