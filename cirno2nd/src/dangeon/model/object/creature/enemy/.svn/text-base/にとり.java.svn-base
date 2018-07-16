package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.ItemDetail;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.bullet.ドラゴンブレス;
import dangeon.model.object.artifact.item.bullet.目からビーム;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait;

public class にとり extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private boolean flag_dorobo = false;

	private Base_Artifact item = null;

	public にとり(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean dorobo() {
		if (item == null && attack_possible()) {
			ArrayList<Base_Artifact> list = Belongings.getListItemNoEnchant();
			if (!list.isEmpty())
				return true;
		}
		return false;
	}

	// public Image getImage() {
	// return IMAGE.にとり.getImage(direction, MainThread.getKoma());
	// }

	@Override
	protected void enemyBreakAction() {
		if (item != null) {
			Base_Artifact a;
			if (LV == 1) {
				a = ItemTable.itemReturn(getMassPoint(), null);
			} else if (LV == 2) {
				a = ItemTable.itemReturn(getMassPoint(),
						ItemDetail.getCategory(item));
			} else {
				if (item instanceof Staff) {
					((Staff) item).addStaffRest(1);
					a = item;
					a.check();
				} else if (item instanceof SpellCard) {
					((SpellCard) item).addBomb(1);
					a = item;
					a.check();
				} else {
					a = ItemTable.itemReturn(getMassPoint(),
							ItemDetail.getCategory(item));
				}
			}
			ItemFall.itemFall(getMassPoint(), a);
		}
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			int coma = getAnimation().getComa() % 2;
			return IM.getSPImage(LV, coma);
		} else {
			return super.getImage();
		}
	}

	@Override
	public boolean itemHitEffect(Base_Artifact a, boolean ento) {
		if (a instanceof MagicBullet || a instanceof 目からビーム) {
			return true;
		}
		if (item != null || ento || a instanceof ドラゴンブレス) {
			return true;
		}
		setAnimation(new Special_Wait(this, 6, 3));
		SE.CHANGE_ITEM.play();
		item = a;
		Medal.にとりにアイテムを変換させた.addCount();
		if (LV == 1) {
			Message.set(getColoredName(), "は別のアイテムに改造した");
		} else if (LV == 2) {
			messageChange(a);
			powerUP();
		} else if (LV == 3) {
			if (a instanceof Staff) {
				Message.set(getColoredName(), "は杖の使用回数を増やしてパワーアップした");
			} else if (a instanceof SpellCard) {
				Message.set(getColoredName(), "はカードのボムを増やしてパワーアップした");
			} else {
				messageChange(a);
			}
			powerUP();
		} else {
			Message.set(getColoredName(), "はアイテムを改造してパワーアップした");
			powerUP();
		}
		return false;
	}

	private void messageChange(Base_Artifact a) {
		if (ItemDetail.getCategory(item) == null) {
			Message.set(getColoredName(), "は別のアイテムに改造してパワーアップした");
		} else {
			Message.set(getColoredName(), "は同種のアイテムに改造してパワーアップした");
		}
	}

	@Override
	protected boolean specialAttack() {
		if (dorobo()) {
			flag_dorobo = true;
			List<Base_Artifact> list = Belongings.getListItemNoEnchant();
			final Base_Artifact a = list.get(new R().nextInt(list.size()));
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.融合)
					|| EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
							ENCHANT_SIMBOL.護)
					|| a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
				Message.set(getColoredName(), "は", Player.me.getColoredName(),
						"が持っていた", a.getColoredName(), "を改造しようとした");
				if (!EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.融合)) {
					Belongings.remove(a);
					ItemFall.itemFall(getMassPoint(), a);
				}
				if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.融合)) {
					Message.set("しかし", Player.me.getColoredName(),
							"と融合していて奪えなかった");
				} else if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.護)
						|| a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
					Message.set("しかし神の加護があって変化できなかった");
				}
			} else {
				Belongings.remove(a);
				item = a;
				Message.set(getColoredName(), "はチルノが持っていた", a.getColoredName(),
						"を奪って改造した");
				powerUP();
				setAnimation(new Special_Wait(this, 6, 3));
				SE.CHANGE_ITEM.play();
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected boolean specialCheck() {
		return LV == 4 && isSpecialParcent() && !flag_dorobo && dorobo();
	}

}
