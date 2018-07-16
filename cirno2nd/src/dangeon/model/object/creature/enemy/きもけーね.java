package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait;

public class きもけーね extends Base_Enemy {
	private static final long serialVersionUID = 1L;

	private SpellCard item = null;

	public きもけーね(Point p, int Lv) {
		super(p, Lv);
	}

	private void addEnchantSimbol(Base_Artifact a) {

		ENCHANT_SIMBOL simbol = prepareSimbol();
		if (simbol != null) {
			if (a.compositionSpace()) {
				if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.竹)) {
					Message.set("しかし永遠の前では新たな歴史を作れなかった");
				} else if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.護)
						|| a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
					Message.set("しかし神の加護があって印を追加できなかった");
				} else {
					a.addListComposition(simbol);
					Message.set(getColoredName(), "は", a.getColoredName(),
							"に【", simbol.getName(false), "】を加えた");
				}
			} else {
				Message.set(getColoredName(), "は", a.getColoredName(), "を奪った");
			}
		} else if (LV == 4 && (a instanceof SpellCard)) {
			Message.set(getColoredName(), "は", a.getColoredName(), "に色を加えた");
			((SpellCard) a).addStandLv();
		} else {
			Message.set(getColoredName(), "は", a.getColoredName(), "を奪った");
		}

	}

	private void animation() {
		ArrayList<SpellCard> list = prepareSpellCardList();
		final SpellCard a = list.get(new R().nextInt(list.size()));
		missMessage(a);
		setAnimation(new Special_Wait(this, 9, 3, SE.KEINE_SP, 1, new Task() {

			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				effect(a);
			}

			@Override
			protected void work(int frame) {
				if (frame == 4) {
					if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
							ENCHANT_SIMBOL.融合)) {
					} else {
						Belongings.remove(a);
						ItemFall.itemFall(Player.me.getMassPoint(),
								mass_point.getLocation(), a);
					}
				}
			}
		}));

	}

	private boolean checkSkillCondition() {
		return attack_possible() && !prepareSpellCardList().isEmpty();
	}

	private void effect(Base_Artifact a) {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.融合)) {
			Message.set("しかし", Player.me.getColoredName(), "と融合していて奪えなかった");
		} else {
			addEnchantSimbol(a);
		}
		if (LV == 3)
			attack();
	}

	@Override
	protected void enemyBreakAction() {
		if (item != null) {
			ItemFall.itemFall(getMassPoint(), item);
		}
	}

	@Override
	protected void init1() {
		super.init1();
	}

	@Override
	protected void init2() {
		super.init2();
	}

	@Override
	protected void init3() {
		super.init3();
	}

	@Override
	protected void init4() {
		super.init4();
	}

	@Override
	public boolean itemHitEffect(Base_Artifact a, boolean ento) {
		if (ento || item != null || !(a instanceof SpellCard)) {
			return true;
		}

		SE.CHANGE_ITEM.play();
		item = (SpellCard) a;
		if (!a.getListComposition().isEmpty()) {
			ENCHANT_SIMBOL simbol = a.getListComposition().remove(
					a.getListComposition().size() - 1);
			SE.KEINE_SP.play();
			setAnimation(new Special_Wait(this, 9, 3));
			Message.set(getColoredName(), "は【", simbol.getName(),
					"】を食べてパワーアップした");
			powerUP();
		} else {
			setAnimation(new Special_Wait(this, 9, 2));
			Message.set(getColoredName(), "は", a.getColoredName(), "を受け取った");
		}
		return false;
	}

	private void missMessage(SpellCard a) {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.融合)) {
			Message.set(getColoredName(), "は", a.getColoredName(), "を奪おうとした");
		}
	}

	private ENCHANT_SIMBOL prepareSimbol() {
		switch (LV) {
		case 1:
			ENCHANT_SIMBOL[] simbols = { ENCHANT_SIMBOL.電, ENCHANT_SIMBOL.火,
					ENCHANT_SIMBOL.天使, ENCHANT_SIMBOL.力１, ENCHANT_SIMBOL.薬,
					ENCHANT_SIMBOL.妹, ENCHANT_SIMBOL.倍速, ENCHANT_SIMBOL.幸せ草,
					ENCHANT_SIMBOL.不幸, ENCHANT_SIMBOL.痺, ENCHANT_SIMBOL.飛,
					ENCHANT_SIMBOL.鈍足, ENCHANT_SIMBOL.ハート, ENCHANT_SIMBOL.消,
					ENCHANT_SIMBOL.混, ENCHANT_SIMBOL.睡, ENCHANT_SIMBOL.封,
					ENCHANT_SIMBOL.大, ENCHANT_SIMBOL.冴 };
			return simbols[new R().nextInt(simbols.length)];
		case 2:
			ENCHANT_SIMBOL[] simbols2 = { ENCHANT_SIMBOL.喘息,
					ENCHANT_SIMBOL.ハラヘリ, ENCHANT_SIMBOL.裁, ENCHANT_SIMBOL.倍速,
					ENCHANT_SIMBOL.絶 };
			return simbols2[new R().nextInt(simbols2.length)];
		case 3:
		case 4:
			return null;
			// ENCHANT_SIMBOL[] simbols3 = { ENCHANT_SIMBOL.封印,
			// ENCHANT_SIMBOL.寒,
			// ENCHANT_SIMBOL.復活, ENCHANT_SIMBOL.忍, ENCHANT_SIMBOL.芋,
			// ENCHANT_SIMBOL.掘, ENCHANT_SIMBOL.高, ENCHANT_SIMBOL.融合,
			// ENCHANT_SIMBOL.護, ENCHANT_SIMBOL.春, ENCHANT_SIMBOL.衰,
			// ENCHANT_SIMBOL.会心, ENCHANT_SIMBOL.幸せ, ENCHANT_SIMBOL.遠投,
			// ENCHANT_SIMBOL.回復, ENCHANT_SIMBOL.識別, ENCHANT_SIMBOL.透視,
			// ENCHANT_SIMBOL.頑 };
			// return simbols3[new R().nextInt(simbols3.length)];
		}
		return null;

	}

	private ArrayList<SpellCard> prepareSpellCardList() {
		ArrayList<SpellCard> list = new ArrayList<SpellCard>();
		for (Base_Artifact base_Artifact : Enchant.getEnchantList()) {
			if (base_Artifact instanceof SpellCard)
				list.add((SpellCard) base_Artifact);
		}
		return list;
	}

	@Override
	protected boolean specialAttack() {
		if (checkSkillCondition()) {
			animation();
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected boolean specialCheck() {
		return isSpecialParcent() && checkSkillCondition();
	}

}
