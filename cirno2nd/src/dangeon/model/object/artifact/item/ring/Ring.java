package dangeon.model.object.artifact.item.ring;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.util.Damage;
import dangeon.util.R;

public abstract class Ring extends Base_Item {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Ring(Point p, String item_name) {
		super(p, item_name, 0, ITEM_CASE.RING);
		IM = Image_Artifact.RIBBON;
	}

	@Override
	protected void action() {
	}

	protected void enchantAttack() {
	}

	protected int enchantAttack(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	@Override
	protected int enchantDefence(boolean normal_attack, Base_Creature c,
			int damage) {
		return damage;
	}

	@Override
	public void firstMsgAtUsingThis() {
		Message.set(getColoredName(), "を掲げた");
	}

	@Override
	public String getClassName() {
		return "リボン：";
	}

	@Override
	public String getLastPackage() {
		return "ring";
	}

	@Override
	protected String getSecondExplain_ByCategory() {
		return "何でも装備すれば問題なく効果を発揮するが合成する場合は攻撃カードと防御カードの両方に印をつけなくてはならない。使用することでそのフロアにいる間リボンの恩恵を得ることができる。";
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
		Message.set(new String[] { c.getColoredName().concat("に"),
				damage + ("ポイントを与えた") });
		Damage.damage(this, null, null, c2, c, damage);
	}

	@Override
	public final boolean itemUse() {
		if (sim == null) {
			Message.set("このアイテムは没アイテムだ");
			return false;
		}
		EnchantSpecial.addAlways_enchant_special(sim);
		if (isEnchantedNow())
			Enchant.forceToRemove(this);
		Belongings.remove(this);
		Message.setConcatFlag(false);
		Message.set(Enchant.ANY2.getColor().toString(), sim.getSimbolName(),
				Color.WHITE.toString(), "が一時的に付与された。");
		return true;
	}

}
