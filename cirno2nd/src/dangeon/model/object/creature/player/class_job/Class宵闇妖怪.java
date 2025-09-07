package dangeon.model.object.creature.player.class_job;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.ring.ルーミアのリボン;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.ルーミアのカード;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusSimbol;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;

public class Class宵闇妖怪 extends BaseClassJob {
	@Override
	public Medal getMedal_ミラクルクエスト() { return Medal.Class宵闇妖怪; }
	@Override
	public Medal getMedal_七曜クエスト() { return Medal.Class宵闇妖怪_七曜; }
	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 1, "階段を降りると：宵闇状態15"));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 2, "階段を降りると：宵闇状態50"));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new ルーミアのリボン(p)));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, "おにぎりを拾えない"));
		list.add(new BonusSimbol(ENCHANT_SIMBOL.ハラヘリ));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new ルーミアのカード(p), 6, Enchant.DEF));
		list.add(new BonusSimbol(ENCHANT_SIMBOL.飯));
	}

	@Override
	public String getExn() {
		return Color.ORANGE + "特技の使用は【特殊キー】" + Color.ORANGE + "を押そう！"
				+ Color.CYAN + "（キャンセル＋あいダッシュでもOK！）";
	}

	@Override
	public SpellCard getRep() {
		return new ルーミアのカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "ルーミアにおにぎりを投げつけた";
	}

	@Override
	public void newMapAction() {
		if (checkSwicth(1))
			Player.me.setCondition(CONDITION.やりすごし, 15);
		else if (checkSwicth(2))
			Player.me.setCondition(CONDITION.やりすごし, 50);

		super.newMapAction();
	}
}
