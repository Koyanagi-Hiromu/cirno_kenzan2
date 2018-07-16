package dangeon.model.object.creature.enemy;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import main.util.FrameShaker;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印招;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class 古明地さとり extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private final List<String> list = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("火焔猫燐");
			add("霊烏路空");
		}
	};

	private final List<String> list2 = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("古明地こいし");
		}
	};

	public 古明地さとり(Point p, int Lv) {
		super(p, Lv);
	}

	private void dododo() {
		int r = new R().nextInt(2);
		Base_Enemy e = enemyCreate(r);
		if (e != null) {
			SE.STATUS_DODODO.play();
			int rnd = new R().nextInt(4);
			if (rnd == 0) {
				Message.set("「てめーの敗因は…たった一つだぜ……%", Player.me.getColoredName(),
						"は私を怒らせた」");
			} else if (rnd == 1) {
				Message.set("「我が『心』のささえを奪った復讐には$決着をつけなくてはならないッ！」");
			} else if (rnd == 2) {
				Message.set("「『悪』とはてめー自身のためだけに$弱者を利用しふみつけるやつのことだ！！」");
			} else {
				Message.set("「この私の怒りがッ！%てめぇをブッつぶす！！」");
			}
			Message.set(Color.RED.toString(), e.getColoredName(),
					"（イカリ）がフロアのどこかに出現した", Color.WHITE.toString());
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Base_Enemy e = EnemyTable.returnBaseEnemy(list.get(R.ran(2)), LV)
				.setConditionList(CONDITION.イカリ, 9999);
		MapList.addEnemy(e);
		Message.set(Color.RED.toString(), e.getColoredName(), "がフロアのどこかに出現した",
				Color.WHITE.toString());
		return true;
	}

	@Override
	protected void enemyBreakAction() {
		if (印招.effect()) {
			SE.SUMMON.play();
			Message.set("しかし誰もやってこなかった！");
			return;
		}
		if (new R().is(10))
			zetsubo();
		else
			dododo();
	}

	private Base_Enemy enemyCreate(int rnd) {
		Base_Enemy e = EnemyTable.returnBaseEnemy(list.get(rnd), LV)
				.setConditionList(CONDITION.イカリ, 9999);
		MapList.addEnemy(e);
		return e;
	}

	private Base_Enemy enemyCreate2(int rnd) {
		Base_Enemy e = EnemyTable.returnBaseEnemy(list2.get(rnd), LV)
				.setConditionList(CONDITION.ええんじゃないか, 9999);
		MapList.addEnemy(e);
		return e;
	}

	@Override
	protected boolean specialAttack() {
		return false;
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

	private void zetsubo() {
		Base_Enemy e = enemyCreate2(0);
		if (e != null) {
			FrameShaker.doneNormaly();
			SE.GOGOGO.play();
			int rnd = new R().nextInt(5);
			if (rnd == 0) {
				Message.set("「お姉ちゃん・・・」");
			} else if (rnd == 1) {
				Message.set("「愛ゆえに苦しまないといけないね・・・」");
			} else if (rnd == 2) {
				Message.set("「希望が自由を生むのなら、絶望は・・・？」");
			} else if (rnd == 3) {
				Message.set("「希望と絶望の相転移・・・」");
			} else {
				Message.set("「もうこの世界には…　希望も何もないッ…」");
			}
			Message.set(Color.RED.toString(), e.getColoredName(),
					"（絶望）がフロアのどこかに出現した", Color.WHITE.toString());
		}
	}

}
