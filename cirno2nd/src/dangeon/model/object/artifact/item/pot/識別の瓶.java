package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.first.adventure.wiki.Wiki_Enemy;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 識別の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 識別の瓶(Point p) {
		this(p, new R().nextInt(3) + 2);
	}

	public 識別の瓶(Point p, int size) {
		super(p, "", size, STAGE.永夜抄);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		if (c instanceof Base_Enemy && !(c instanceof Base_NPC)) {
			staticCheck();
			Base_Enemy e = (Base_Enemy) c;
			Config.save(e, e.getConvertedLV(), false);
			SE.CHECK.play();
			Scene_Action a = Scene_Action.getMe();
			StringBuilder sb = new StringBuilder();
			for (STAGE stage : e.getCategory()) {
				if (sb.length() > 0)
					sb.append("&");
				sb.append(stage);
			}
			a.setNextScene(new Wiki_Enemy(a.KH, a.CURRENT_VIEW, e, -1, -1, sb
					.toString(), e.getConvertedLV()));
		} else {
			super.itemHit(c, c2);
		}
	}

	@Override
	protected void potUse() {
		if (!A.isPerfectCheked()) {
			SE.CHECK.play();
			A.check();
			Message.set("「これは", A.getColoredName(), "だな」");
			this.staticCheck();
		}
	}
}
