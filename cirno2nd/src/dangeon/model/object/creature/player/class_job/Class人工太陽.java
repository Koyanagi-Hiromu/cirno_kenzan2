package dangeon.model.object.creature.player.class_job;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.object.artifact.item.arrow.大砲の弾;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.霊烏路空のカード;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusSimbol;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;
import dangeon.model.object.creature.player.class_job.bonus.spec.Bonus人工太陽;

public class Class人工太陽 extends BaseClassJob {
	@Override
	public Medal getMedal_ミラクルクエスト() { return Medal.Class人工太陽; }
	@Override
	public Medal getMedal_七曜クエスト() { return Medal.Class人工太陽_七曜; }
	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.龍));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.炎));
		list.add(new BonusItem(new 大砲の弾(p, false).setArrowRest(50)));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.火));
		list.add(new BonusItem(new 大砲の弾(p, false).setArrowRest(50)));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.核爆発));
		list.add(new BonusSwitch(this, 0, "爆発のダメージが５０％増加"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 霊烏路空のカード(p), 6, Enchant.ATK));
		list.add(new Bonus人工太陽(this));
	}

	@Override
	public String getExn() {
		return Color.ORANGE + "特技の使用は【特殊キー】" + Color.ORANGE + "を押そう！"
				+ Color.CYAN + "（キャンセル＋あいダッシュでもOK！）";
	}

	@Override
	public SpellCard getRep() {
		return new 霊烏路空のカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "太陽の弱点をついた";
	}
}
