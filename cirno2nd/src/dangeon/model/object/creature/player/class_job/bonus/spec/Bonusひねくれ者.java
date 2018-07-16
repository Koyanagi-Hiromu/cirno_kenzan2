package dangeon.model.object.creature.player.class_job.bonus.spec;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.Classひねくれ者;
import dangeon.model.object.creature.player.class_job.bonus.BonusSpecialAction;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class Bonusひねくれ者 extends BonusSpecialAction {
	private static final long serialVersionUID = 1L;

	public Bonusひねくれ者(Classひねくれ者 classひねくれ者) {
		super(classひねくれ者, 1, 10, "天邪状態にする");
	}

	@Override
	public ActionCheck excuteSpecAction() {
		boolean flagA = perform(Player.me.getDirection());
		boolean flagB;
		if (BonusConductor.ひねくれ者_特技拡張()) {
			flagB = perform(Player.me.getDirection().getReverse());
		} else {
			flagB = false;
		}
		if (flagA || flagB) {
			return ActionCheck.SUCCESS;
		} else {
			Message.set("誰もいないね！ ざんねーん！@");
			return ActionCheck.FAILURE;
		}

	}

	@Override
	public String getEffectExn() {
		return "特技：" + super.getEffectExn() + "(消費HP：Lv％)";
	}

	@Override
	protected int getHP_USE_PERCENT() {
		return Player.me.getLV();
	}

	private boolean perform(DIRECTION direction) {
		Point p = Player.me.getMassPoint().getLocation();
		direction.getFrontPoint(p);
		Base_Creature creature = MapList.getCreature(p);
		if (creature != null && (creature instanceof Base_Enemy)) {
			creature.setCondition(CONDITION.天邪鬼, 5);
			SE.AMANOJACK.play();
			MainMap.addEffect(new DoronEffect(creature.getMassPoint(), null,
					true, false), false);
			return true;
		} else {
			return false;
		}
	}
}
