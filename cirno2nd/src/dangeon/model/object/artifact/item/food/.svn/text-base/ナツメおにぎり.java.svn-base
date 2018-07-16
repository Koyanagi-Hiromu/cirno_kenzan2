package dangeon.model.object.artifact.item.food;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;

/**
 * 大きなおにぎり
 * 
 * @author Administrator
 * 
 */
public class ナツメおにぎり extends Food {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ヤツメおにぎり";

	public ナツメおにぎり(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.飯;
	}

	@Override
	protected void foodUse() {

		if (Player.me.isBadCondition()) {
			for (CONDITION condition : Player.me.getBadConditionList()) {
				CONDITION.conditionRecovery(Player.me, condition);
			}
			for (CONDITION con : Player.me.getConditionRemoveTask()) {
				Player.me.getConditionList().remove(con);
			}
			Player.me.getConditionRemoveTask().clear();
		}
		int heal = 100;
		Player.me.chengeHP(null, null, heal);
		// Message.set(new String[] { "HPが",
		// 半角全角コンバーター.半角To全角数字(heal) + "ポイント回復した" });
		if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY()) {
			SE.IKAKUTYO.play();
			Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + 3);
			Player.me.chengeSatiety(20);
			Message.set("最大満腹度が３上がった");
		} else if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY() + 30) {
			Player.me.chengeSatiety(100);
			Message.set("お腹が一杯になった");
		} else {
			Player.me.chengeSatiety(30);
			Message.set("それなりにお腹が膨れた");
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "おいしいヤツメウナギが具に入ったおにぎり。あまりのおいしさに薬草を食べたときの効果が発生するってこれ間違いなく薬草入ってるよ流石みすちー。";
	}

}
