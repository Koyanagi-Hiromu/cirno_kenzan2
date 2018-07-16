package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.Random;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.util.Damage;
import dangeon.util.R;

public abstract class Scrool extends Base_Item {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private boolean open = false;

	protected Scrool(Point p, String item_name) {
		super(p, item_name, 0, ITEM_CASE.SCROOL);
		IM = Image_Artifact.BOOK;
	}

	@Override
	protected void action() {
	}

	protected int enchantAttack(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	@Override
	protected int enchantDefence(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	@Override
	public void firstMsgAtUsingThis() {
		Message.set(getColoredName(), "を掲げた");
	}

	@Override
	public String getClassName() {
		return "書：";
	}

	@Override
	public String getLastPackage() {
		return "scrool";
	}

	@Override
	protected String getSecondExplain_ByCategory() {
		return "使用すると特別な効果が得られる。１度使うとなくなるがその分良くも悪くも効果が強い。投げても特に効果はない。";
	}

	// public static void openUseEffect() {
	// for (Base_Artifact a : MapList.getListArtifact()) {
	// if (a instanceof Scrool) {
	// Scrool sc = (Scrool) a;
	// if (!sc.isOpen()) {
	// continue;
	// }
	// Message.set(a.getColoredName(), "を発動させた");
	// sc.scroolUse();
	// TaskOnMapObject.addItemRemoveTask(a);
	// }
	// }
	// return;
	// }

	@Override
	public int getShadow() {
		return 6;
	}

	public boolean isOpen() {
		return open;
	}

	protected boolean isParmitToOpen() {
		return true;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		Random ran = new R();
		int damage = ran.nextInt(2) + 1;
		Message.set(new String[] { c.getColoredName().concat("に"),
				getColoredName().concat("をぶつけた") });
		Damage.damage(this, null, null, c2, c, damage);
	}

	@Override
	public boolean itemUse() {
		Belongings.remove(this);
		scroolUse();
		if (isEnchantedNow()) {
			Enchant.forceToRemove(this);
		}
		return true;
	}

	protected abstract void scroolUse();

	public void setCrose() {
		open = false;
	}

}
