package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.UtilMessage;
import dangeon.view.anime.IceEffect;
import dangeon.view.detail.View_Sider;

public class レティ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public レティ(Point p, int Lv) {
		super(p, Lv);
	}

	private void breakItem() {
		List<Base_Artifact> list_belongings = Belongings.getListItemNoEnchant();
		List<Base_Artifact> list_targets = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : list_belongings) {
			if (!a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
				list_targets.add(a);
			}
		}
		if (list_targets.isEmpty()) {
			return;
		}
		int select = new R().nextInt(list_targets.size());
		Base_Artifact a = list_targets.get(select);
		Belongings.remove(a);
		SE.BROKEN.play();
		Message.set(a.getColoredName(), "は冷気に耐えられず壊れた");
		View_Sider.setInformation(a.getColoredName(), "が壊れました");
		if (ItemTable.getRank(a) >= 4) {
			Medal.敵に珍しいアイテムを壊された.addCount();
		}
	}

	private void effect() {
		List<Base_Artifact> list_belongings = Belongings.getListItems();
		List<Base_Artifact> list_targets = new ArrayList<Base_Artifact>();
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.護)) {
			UtilMessage.effectDefMsg_Hina();
			return;
		}
		boolean first = true;
		SE.ICE.play();
		for (int i = 0; i < LV; i++) {
			for (Base_Artifact a : list_belongings) {
				if (a.isParmitThisItemFreeze()
						&& !a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
					list_targets.add(a);
				}
			}
			if (list_targets.isEmpty()) {
				if (first) {
					breakItem();
				}
				return;
			} else {
				frozenItems(list_targets);
			}
			first = false;
		}
	}

	private void frozenItems(List<Base_Artifact> list_targets) {
		int select = new R().nextInt(list_targets.size());
		list_targets.get(select).freezeCountPlus(10);
		Message.set(list_targets.get(select).getColoredName(), "は冷気に包まれた");
		View_Sider.setInformation(list_targets.get(select).getColoredName(),
				"は凍りました");
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, getAnimation().getComa() % 4);
		} else {
			return super.getImage();
		}
	}

	@Override
	protected boolean specialAttack() {
		direction = converDirection(Player.me.getMassPoint());
		setAnimation(new IceEffect(Player.me, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				effect();
			}
		}, LV));
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!isSpecialParcent()) {
			return false;
		}
		if (attack_possible()) {
			return true;
		}
		return false;
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		attack_No = attaking_frame / 2;
		if (attack_No > 5)
			attack_No = 5;
	}

}
