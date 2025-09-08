package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.object.artifact.item.scrool.明かりの書;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.大妖精のカード;
import dangeon.model.object.artifact.item.staff.一時しのぎの杖;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;
import main.res.CHARA_IMAGE;

public class Class冒険家 extends BaseClassJob {
	@Override
	public Medal getMedal_ミラクルクエスト() { return Medal.Class冒険家; }
	@Override
	public Medal getMedal_七曜クエスト() { return Medal.Class冒険家_七曜; }

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 一時しのぎの杖(p).createStaffRest(4)));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 明かりの書(p)));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 大妖精のカード(p)));
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
