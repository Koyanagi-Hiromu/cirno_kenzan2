package dangeon.model.object.creature.player.class_job;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.物部布都のカード;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusSimbol;
import dangeon.model.object.creature.player.class_job.bonus.BonusStatus;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;
import dangeon.model.object.creature.player.class_job.bonus.spec.Bonus風水師;

public class Class風水師 extends BaseClassJob {
	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.水));
		list.add(new BonusSwitch(this, 3, "水路の出現率３倍"));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusStatus(this, CONDITION.炎上));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 4, "浮島の出現率４倍"));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 0, "攻防にフロア数に応じたマイナス補正"));
		list.add(new BonusSwitch(this, 1, "雷ダメージでも倒れる"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 0, "水氷炎が隣にあると強い"));
		list.add(new Bonus風水師(this));
		list.add(new BonusSimbol(ENCHANT_SIMBOL.舟));
		list.add(new BonusSimbol(ENCHANT_SIMBOL.龍));
	}

	@Override
	public String getExn() {
		return Color.ORANGE + "特技の使用は【特殊キー】" + Color.ORANGE + "を押そう！"
				+ Color.CYAN + "（キャンセル＋あいダッシュでもOK！）";
	}

	@Override
	public SpellCard getRep() {
		return new 物部布都のカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "水の上に乗ってワープしたことがある";
	}
}
