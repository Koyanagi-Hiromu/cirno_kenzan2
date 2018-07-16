package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

/**
 * 
 * @author Administrator
 * 
 */
public class 大部屋の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "大部屋の書";
	public static final String item_name_sound = "おおへやのしょ";

	public 大部屋の書(Point p) {
		super(p, item_name);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "壁を崩し大きな１つの部屋にする。いろいろな使い方があるが、フロア中の敵が一直線でチルノに向かってくるので注意。ちなみにダンジョン外では無効です。";
	}

	private void itemEffect() {
		MassCreater.breakIntoLargeRoom();
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (new R().is(50)) {
				continue;
			}
			CONDITION.conditionRecovery(em, CONDITION.仮眠);
			CONDITION.conditionRecovery(em, CONDITION.特殊仮眠);
			CONDITION.conditionRecovery(em, CONDITION.安心);
			for (CONDITION con : em.getConditionRemoveTask()) {
				em.getConditionList().remove(con);
			}
		}
		Player.me.getConditionRemoveTask().clear();
	}

	@Override
	public void scroolUse() {
		if (PresentField.get().isHaraheru()) {
			SE.BREAKINTOONEROOM.play();
			itemEffect();
			Message.set(new String[] { "壁を全て崩した" });
		} else {
			Message.set("この書はダンジョン内で読まないと効果がないようだ");
		}
	}
}