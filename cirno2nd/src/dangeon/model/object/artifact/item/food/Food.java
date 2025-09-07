package dangeon.model.object.artifact.item.food;

import java.awt.Point;
import java.util.Random;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.Damage;
import dangeon.util.R;
import main.res.Image_Artifact;
import main.res.SE;

public abstract class Food extends Base_Item {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Food(Point p, String item_name) {
		super(p, item_name, 1, ITEM_CASE.FOOD);
		IM = Image_Artifact.FOOD;
	}

	@Override
	protected void action() {
	}

	@Override
	public void changeToGolden() {
		IM = Image_Artifact.GOLDEN_FOOD;
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
		Message.set(getColoredName(), "を食べた");
	}

	protected abstract void foodUse();

	@Override
	public String getLastPackage() {
		return "food";
	}

	@Override
	protected String getSecondExplain_ByCategory() {
		return "満腹度は何か行動するたびに少しずつ減っていきゼロになると１ターンごとにＨＰが減っていく。そんな恐ろしい状況にならないためにおにぎりを食べよう。";
	}

	@Override
	public int getShadow() {
		return 6;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		int damage;
		String result;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.祝)) {
			SE.MIRACLE_ONIGIRI.play();
			damage = 999;
			result = "奇跡が起こって倒れた";
		} else {
			Random ran = new R();
			damage = ran.nextInt(2) + 1;
			result = getColoredName() + "がぶつかって倒れた";
		}
		Message.set(new String[] { c.getColoredName().concat("に"),
				getColoredName().concat("をぶつけた") });
		Damage.damage(this, null, result, c2, c, damage);
	}

	@Override
	public boolean itemUse() {
		foodUse();
		if (isEnchantedNow())
			Enchant.forceToRemove(this);
		Belongings.remove(this);
		return true;
	}

	@Override
	public boolean waterAction() {
		Message.set(getColoredName(), "は濡れてまずそうになった");
		Base_Item a = new 腐ったおにぎり(getMassPoint());
		a.flag_pick_checked = flag_pick_checked;
		a.setMerchant(false);
		MapList.addArtifact(a);
		return false;
	}

	@Override
	public final Boolean exchange() {
		if (BonusConductor.宵闇妖怪())
		{
			Message.set("宵闇妖怪は", getColoredName(), "を拾えない");
			return true;
		}
		else
			return super.exchange();
	}

	@Override
	public final boolean isMobile() {
		if (BonusConductor.宵闇妖怪())
			return false;
		else
			return super.isMobile();
	}

	@Override
	public boolean itemPickUp() {
		if (BonusConductor.宵闇妖怪())
		{
			Message.set("宵闇妖怪は", getColoredName(), "を拾えない");
			return false;
		}
		else
			return itemPickUp();
	}
}
