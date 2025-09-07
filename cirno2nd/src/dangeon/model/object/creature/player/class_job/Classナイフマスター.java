package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.十六夜咲夜のカード;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusSimbol;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;
import dangeon.model.object.creature.player.class_job.bonus.spec.Bonusナイフマスター;

public class Classナイフマスター extends BaseClassJob {
	@Override
	public Medal getMedal_ミラクルクエスト() { return Medal.Classナイフマスター; }
	@Override
	public Medal getMedal_七曜クエスト() { return Medal.Classナイフマスター_七曜; }

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 2, "射撃威力に攻撃カードの攻撃力を追加"));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new Bonusナイフマスター(this));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.弾));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 1, "通常攻撃の威力が階段を降りるたび1%減る"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 0, "敵が目の前にいないならナイフを投げる"));
		list.add(new BonusSwitch(this, 3, "咲夜のナイフの反射ダメージを軽減"));
	}

	@Override
	public SpellCard getRep() {
		return new 十六夜咲夜のカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "ナイフをまとめて投げて敵を倒した";
	}
}
