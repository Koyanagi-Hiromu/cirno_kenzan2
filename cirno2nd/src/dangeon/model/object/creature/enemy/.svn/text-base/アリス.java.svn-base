package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.util.UtilMessage;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.detail.View_Sider;

public class アリス extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public アリス(Point p, int Lv) {
		super(p, Lv);
	}

	private void effect() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.護)) {
			UtilMessage.effectDefMsg_Hina();
			return;
		} else if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.祓)) {
			UtilMessage.effectDefMsg();
			return;
		}
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : Belongings.getListItems()) {
			if (!a.isCurse() && a.isAbleToCurse()
					&& !a.getListComposition().contains(ENCHANT_SIMBOL.金))
				list.add(a);
		}
		if (list.isEmpty()) {
			Message.set("しかし何も呪うことが出来なかった");
			return;
		}
		SE.SYSTEM_CURSE.play();
		for (int i = 0; i < LV; i++) {
			int select = new R().nextInt(list.size());
			Base_Artifact a = list.get(select);
			Message.set(a.getColoredName(), "が呪われた");
			View_Sider.setInformation(a.getColoredName(), "が呪われました");
			a.setCurse(true);
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		MapInSelect.aroundInEnemyChengeCondition(CONDITION.麻痺, 0);
		Message.set("周囲を呪った");
		return true;
	}

	@Override
	protected Point getAttackPoint() {
		return new Point(screen_point.x + attack_delt_point.x / 2,
				screen_point.y + attack_delt_point.y / 2);
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible()) {
			return false;
		}
		Message.set(getColoredName(), "は呪言を唱えた");
		direction = converDirection(Player.me.getMassPoint());
		setAnimation(new Special_Wait_FrameByFrame(this, SE.FIRST_OURA, 1,
				new Task() {
					/**
			 * 
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						effect();
					}
				}, 0, 0, 0, 1, 1, 1, 2, 2, 2));
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!attack_possible()) {
			return false;
		}
		if (!isSpecialParcent()) {
			return false;
		}
		return true;
	}
}
