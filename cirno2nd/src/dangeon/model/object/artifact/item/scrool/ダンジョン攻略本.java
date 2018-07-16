package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DecurseEffect;

/**
 * 天の恵み
 * 
 * @author Administrator
 * 
 */
public class ダンジョン攻略本 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ダンジョン攻略本";
	public static final String item_name_sound = "たんしょんこうりゃくほん";

	public ダンジョン攻略本(Point p) {
		super(p, item_name);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "この書は次のあなたを１度に全部お助けします。体力が減っている／具合が悪い／敵に囲まれている／ハラペコだ／装備品が呪われている";
	}

	@Override
	protected boolean isParmitToOpen() {
		return false;
	}

	public void itemEffect() {
		if (Player.me.isBadCondition()) {
			for (CONDITION condition : Player.me.getBadConditionList()) {
				CONDITION.conditionRecovery(Player.me, condition);
			}
			for (CONDITION con : Player.me.getConditionRemoveTask()) {
				Player.me.getConditionList().remove(con);
			}
			Player.me.getConditionRemoveTask().clear();
			Message.set("悪い状態を回復した");
		}
		if (Player.me.getSATIETY() <= 0) {
			Player.me.chengeSatiety(Player.me.getMAX_SATIETY());
			Message.set(new String[] { ("満腹になった"), "" });
		}
		if (Player.me.getHP() < Player.me.getMAX_HP()) {
			Player.me.chengeHP(null, null, Player.me.getMAX_HP());
			// Message.set(new String[] { ("HPが回復した"), "" });
		}
		ArrayList<Base_Creature> list = MapList.getSurroundedCreature(Player.me
				.getMassPoint());
		if (list.size() >= 2) {
			for (Base_Creature c : list) {
				c.setCondition(CONDITION.麻痺, 0);
			}
			SE.STATUS_SIBIBI.play();
			Message.set("周りの敵を麻痺させた");
		}
		for (Base_Artifact a : Enchant.getEnchantList()) {
			if (a.isCurse() == true) {
				Player.me.setAnimation(new DecurseEffect());
				a.setCurse(false);
				Message.set(new String[] {
						a.getColoredName().concat("の呪いを解いた"), "" });
			}
		}
	}

	@Override
	public void scroolUse() {
		itemEffect();
	}

}
