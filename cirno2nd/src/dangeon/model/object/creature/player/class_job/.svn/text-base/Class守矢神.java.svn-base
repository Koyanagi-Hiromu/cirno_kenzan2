package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.八坂神奈子のカード;
import dangeon.model.object.artifact.item.spellcard.東風谷早苗のカード;
import dangeon.model.object.artifact.item.spellcard.洩矢諏訪子のカード;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusSimbol;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;

public class Class守矢神 extends BaseClassJob {

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.オンバシラ));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.バトル));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.祝));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, "口を使うとチルノの身に奇跡が起こる"));
		// list.add(new BonusSwitch(this, "全てのおにぎりが奇跡のおにぎりになる"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		SpellCard[] a = { new 東風谷早苗のカード(p), new 八坂神奈子のカード(p), new 洩矢諏訪子のカード(p) };
		for (SpellCard s : a) {
			list.add(new BonusItem(s, 6));
		}
	}

	@Override
	public SpellCard getRep() {
		return new 東風谷早苗のカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "（初めから出現している）";
	}
}
