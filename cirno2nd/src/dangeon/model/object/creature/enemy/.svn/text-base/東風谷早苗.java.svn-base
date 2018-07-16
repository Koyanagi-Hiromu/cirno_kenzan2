package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.food.ミシャグジおにぎり;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.EnchantArrow;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.util.UtilMessage;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.anime.WindEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.View_Sider;

public class 東風谷早苗 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 東風谷早苗(Point p, int Lv) {
		super(p, Lv);
	}

	private void effect() {
		if (LV >= 2 && !Player.me.conditionCheck(CONDITION.おにぎり)) {
			Player.me.setCondition(CONDITION.おにぎり, LV == 2 ? 10 : 20);
			Message.set("奇跡が起こって", Player.me.getColoredName(), "はおにぎりになった");
		} else {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.護)) {
				UtilMessage.effectDefMsg_Hina();
				return;
			}
			List<Base_Artifact> list = new ArrayList<Base_Artifact>();
			for (Base_Artifact a : Belongings.getListItems()) {
				if (a.isEnchantedNow() || EnchantArrow.isEnchant(a)
						|| a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
					continue;
				}
				list.add(a);
			}
			if (list.isEmpty()) {
				Message.set("しかし奇跡は起こらなかった");
			} else {
				int select = new R().nextInt(list.size());
				int index = Belongings.getListItems().indexOf(list.get(select));
				boolean flag_cursed = list.get(select).isCurse();
				Belongings.remove(list.get(select));
				Belongings.setItems(
						flag_cursed ? new ミシャグジおにぎり(Player.me.getMassPoint())
								: new 奇跡のおにぎり(Player.me.getMassPoint()), index);
				Base_Artifact a = list.get(select);
				Message.set("奇跡が起こって",
						a.getColoredName().concat("は").concat("おにぎりになった"));
				View_Sider.setInformation(a.getColoredName(), "はおにぎりになりました");
				if (ItemTable.getRank(a) >= 4) {
					Medal.敵に珍しいアイテムを壊された.addCount();
				}

			}
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Point p = new Point(Player.me.getMassPoint().x
				+ Player.me.getDirection().X, Player.me.getMassPoint().y
				+ Player.me.getDirection().Y);
		TaskOnMapObject.addEnemyRemoveTask(MapInSelect.getFrontEnemyFromPoint(
				p.x, p.y));
		ItemFall.itemFall(p, new 大きなおにぎり(p));
		return true;
	}

	//
	@Override
	public boolean itemHitEffect(Base_Artifact a, boolean ento) {
		if (a instanceof Food) {
			SE.MIRACLE_ONIGIRI.play();
			Message.set(a.getColoredName(), "は奇跡を起こした");
			Damage.damage(null, null, null, Player.me, this, 999);
			Medal.早苗におにぎりを投げつけた.addCount();
			return false;
		}
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible()) {

			return false;
		}
		direction = converDirection(Player.me.getMassPoint());

		final boolean swing;
		int anime[];
		swing = false;
		SE.MIRACLE_ONIGIRI.play();
		int a[] = { 13, 13, 14, 14, 15, 15, 16, 16, 17, 17, 18, 18, 19, 19 };
		for (int i = 0; i < a.length; i++) {
			a[i] -= 13;
		}
		anime = a;
		setAnimation(new Special_Wait_FrameByFrame(this, SE.MIRACLE_ONIGIRI, 0,
				new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					private int count = 0;

					@Override
					public void work() {
						effect();
					}

					@Override
					protected void work(int frame) {
						if (swing && frame % 3 == 1 && count != 4) {
							count++;
							SE.ATTACK_SWING.play();
						}
					}
				}, anime));
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!attack_possible()) {
			return false;
		}
		if (!isSpecialParcent()) {
			return false;
		}
		return true;
	}

	@Override
	protected void upDate_NormalAttack() {
		int no = attack_No;
		super.upDate_NormalAttack();
		if (no != attack_No && attack_No == 1) {
			MainMap.addEffect(new WindEffect(direction.getFrontPoint(mass_point
					.getLocation())));
		}
	}

}
