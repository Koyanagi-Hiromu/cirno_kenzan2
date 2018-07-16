package dangeon.model.object.creature.player.class_job.bonus;

import java.util.ArrayList;

import main.res.SE;
import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.BaseClassJob;

public abstract class BonusSpecialAction extends BonusSwitch {
	protected enum ActionCheck {
		SUCCESS, FAILURE;
	}

	private static final long serialVersionUID = 1L;

	private final int HP_USE_PERCENT;

	public BonusSpecialAction(BaseClassJob class_job, int c, int hp_use,
			String exn) {
		super(class_job, c, exn);
		HP_USE_PERCENT = hp_use;
	}

	public void excute() {
		int hp_percent = Player.me.getHP() * 100 / Player.me.getMAX_HP();
		if (hp_percent < getHP_USE_PERCENT()) {
			SE.SYSTEM_CURSE.play();
			Message.set("ＨＰが足りなくて特技が使用できない");
		} else {
			if (excuteSpecAction() == ActionCheck.SUCCESS) {
				int damage = Player.me.getMAX_HP() * getHP_USE_PERCENT() / 100;
				if (damage >= Player.me.getHP())
					damage = Player.me.getHP() - 1;
				Player.me.chengeHP_NoEffect(-damage);
				TurnSystemController.callMeToStartEnemyTurn();
			}
		}
	}

	@Override
	public void excute(ArrayList<Base_Artifact> list1,
			ArrayList<ENCHANT_SIMBOL> list2) {
		parent.setSwichON(this);
	}

	public abstract ActionCheck excuteSpecAction();

	@Override
	public String getEffectExn() {
		if (getHP_USE_PERCENT() > 0) {
			return "特技：" + super.getEffectExn() + "(消費HP" + getHP_USE_PERCENT()
					+ "％)";
		} else {
			return "特技：" + super.getEffectExn();
		}
	}

	protected int getHP_USE_PERCENT() {
		return HP_USE_PERCENT;
	}

}
