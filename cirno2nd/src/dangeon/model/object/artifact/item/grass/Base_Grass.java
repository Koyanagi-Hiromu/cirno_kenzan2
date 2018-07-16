package dangeon.model.object.artifact.item.grass;

import java.awt.Point;
import java.util.Random;

import main.res.Image_Artifact;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.check.Checker.KeyWord;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.GrassWaveEffect;
import dangeon.view.detail.MainMap;

public abstract class Base_Grass extends Base_Item {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Base_Grass(Point p, String item_name) {
		super(p, item_name, 0, ITEM_CASE.GRASS);
		IM = Image_Artifact.GLASS;
	}

	@Override
	protected void action() {
	}

	private void aroundHit(Base_Creature c) {
		effect_pre(c);
		for (Base_Creature _c : MapInSelect.getListAroundInCreature(c
				.getMassPoint())) {
			if (_c.isResistantToGrassWave()) {
				if (_c.isSkillActive())
					Message.set(_c.getColoredName(), "は草の波紋効果を無効化した");
				continue;
			}
			effect_pre(_c);
		}
	}

	protected abstract void effect(Base_Creature c);

	private void effect_pre(Base_Creature c) {
		hitCheck(c);
		effect(c);
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
		Message.set(getColoredName().concat("を飲んだ"));
	}

	@Override
	public String getClassName() {
		return "草：";
	}

	@Override
	public String getLastPackage() {
		return "grass";
	}

	@Override
	protected String getSecondExplain_ByCategory() {
		return "使用しても投げても効果が発生する。使用すると飲みこんで効果を得るので満腹度が少しだけ得られる。チルノは凍った草もおにぎりも平気でもぐもぐできます。";
	}

	protected abstract boolean grassUse();

	public void haretsu(Base_Creature c) {
		effect_pre(c);
	}

	private void hitCheck(Base_Creature c) {
		if (isAbleToBeHittedChecked(c)) {
			staticCheck();
		} else {
			Message.set(c.getColoredName(), "には効果がなかった");
			if (!isStaticCheked()) {
				Checker.write(this, KeyWord.投擲);
			}
		}
	}

	protected boolean isAbleToBeHittedChecked(Base_Creature c) {
		return true;
	}

	public void itemEffect(Base_Creature c) {
		effect_pre(c);
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	public void itemHit() {
		Random ran = new R();
		int damage = ran.nextInt(2) + 1;
		Message.set(new String[] {
				MapList.getEnemy(getMassPoint()).getColoredName().concat("に"),
				getColoredName().concat("をぶつけた") });
		Message.set(new String[] {
				MapList.getEnemy(getMassPoint()).getColoredName().concat("に"),
				damage + ("ポイントを与えた") });
		// MapList.getEnemy(getMassPoint()).damage(null, null, false, damage);
	}

	@Override
	public void itemHit(final Base_Creature c, Base_Creature c2) {
		Message.set(getColoredName(), "の効果が広がった");
		MainMap.addEffect(new GrassWaveEffect(c, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				aroundHit(c);
			}
		}), true);
	}

	@Override
	public boolean itemUse() {
		Player.me.chengeSatiety(5);
		if (isEnchantedNow())
			Enchant.forceToRemove(this);
		Belongings.remove(this);
		return grassUse();
	}

}
