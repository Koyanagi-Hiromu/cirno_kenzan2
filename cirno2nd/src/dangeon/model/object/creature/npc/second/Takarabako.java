package dangeon.model.object.creature.npc.second;

import java.awt.Image;
import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemDetail;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import main.res.Image_Object;

public class Takarabako extends Base_NPC {

	private static final long serialVersionUID = 1L;

	private Base_Artifact item;

	public Takarabako(Point p) {
		super(p, "宝箱", false);
		IM = null;
	}

	@Override
	protected void enemyBreakAction() {
		ItemFall.itemFall(getMassPoint(), item);
		if (ItemTable.getRank_String(item).equals("Ｓ")) {
			Medal.宝箱から出現度Ｓのカードが出現した.addCount();
		}
	}

	@Override
	protected Task getAttackTask(final Base_Creature active,
			final Base_Creature passive) {
		return new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Damage.damage(null, null, null, active, passive, 1);
			}
		};
	}

	@Override
	public Image getImage() {
		return Image_Object.takara.getImage();
	}

	@Override
	public int getShadowSize100() {
		return 44;
	}

	@Override
	public int getShadowY() {
		return 8;
	}

	@Override
	public void message() {
		Player.me.startAttackWithHand(getAttackTask(Player.me, this));
	}
	
	@Override
	public boolean isSkillActive() {
		return true;
	}

	@Override
	protected boolean resistCondition(CONDITION c) {
		return true;
	}

	@Override
	public void setNameAndStatus() {
		super.setNameAndStatus();
		item = ItemTable.itemReturn(getMassPoint(), ItemDetail.CARD);
		item.setCurse(false);
		item.addListComposition(ENCHANT_SIMBOL.金);
		setMAX_HP(HP = ItemTable.getRank(item) * 2 - 1);
		setENEMY_EXP(0);
	}
}
