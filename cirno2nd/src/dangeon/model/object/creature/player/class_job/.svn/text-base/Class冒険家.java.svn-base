package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import main.res.CHARA_IMAGE;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;

public class Class冒険家 extends BaseClassJob {

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, "ワナが見えない（！マスがなくなる）"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, "お腹がちょっとだけ減りにくい"));
	}

	@Override
	public CHARA_IMAGE getCharaImage() {
		return CHARA_IMAGE.チルノ;
	}

	@Override
	public SpellCard getRep() {
		return null;
	}

	@Override
	public String getSwitchExp() {
		return "クラスを選ばず階段を降りる";
	}
}
