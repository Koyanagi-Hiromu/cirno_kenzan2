package dangeon.model.object.creature.player.class_job;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.アリスのカード;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;
import dangeon.model.object.creature.player.class_job.bonus.spec.Bonus人形使い;

public class Class人形使い extends BaseClassJob {
	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 3, "既にある人形を呼び出す特技追加"));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 4, "人形が前進駆動する"));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 2, "爆発威力が増加　壁も壊れる"));
		list.add(new BonusSwitch(this, 5, "人形の寿命が低下"));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 0, "最大HPの増加度が２５％減"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new Bonus人形使い(this));
	}

	@Override
	public String getExn() {
		return Color.ORANGE + "特技の使用は【特殊キー】" + Color.ORANGE + "を押そう！"
				+ Color.CYAN + "（キャンセル＋あいダッシュでもOK！）";
	}

	@Override
	public SpellCard getRep() {
		return new アリスのカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "Anotherの人形を図鑑に登録した";
	}
}
