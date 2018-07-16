package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.Damage;

public class 人を殺せる書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "幻想郷縁起";
	public static final String item_name_sound = "けんそうきょうえんき";

	public 人を殺せる書(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.殺;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "様々な事が書いてある";
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		int damage = 999;
		staticCheck();
		Message.set(new String[] { c.getColoredName().concat("に"),
				getColoredName().concat("をぶつけた") });
		Damage.damage(this, null, null, c2, c, damage);
	}

	@Override
	public void scroolUse() {
		Message.set("「範囲64、最遅行動」と書かれていた。");
	}
}
