package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.ItemDetail;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait;

public class _old朱鷺子 extends Base_Enemy {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Base_Artifact item = null;

	public _old朱鷺子(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	protected void enemyBreakAction() {
		if (item != null) {
			Base_Artifact a;
			a = ItemTable.itemReturn(getMassPoint(),
					ItemDetail.getCategory(item));
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
		if (item != null || !(a instanceof Scrool)) {
			return true;
		}
		setAnimation(new Special_Wait(this, 6, 3));
		SE.CHANGE_ITEM.play();
		item = a;
		if (LV > 2) {
			powerUP();
			Message.set(getColoredName(), "は", a.getColoredName(),
					"を受け取ってパワーアップした");
		} else {
			Message.set(getColoredName(), "は", a.getColoredName(),
					"を嬉しそうに受け取った");
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		if (LV == 1) {
			return false;
		}
		ArrayList<Base_Artifact> _list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof Scrool) {
				_list.add(a);
			}
		}
		if (_list.isEmpty()) {
			return false;
		}
		SE.ATTACK_HANDS.play();
		Base_Artifact chenge_item = _list.get(R.ran(_list.size()));
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.融合)) {
			if (LV == 4) {
				Message.set(getColoredName(), "は書を奪おうとした");
			} else {
				Message.set(getColoredName(), "は書を交換しようとした");
			}
			Message.set("しかし", Player.me.getColoredName(), "にくっついていて取れなかった");
		}
		if (LV == 4) {
			Belongings.remove(chenge_item);
			Message.set(getColoredName(), "は", Player.me.getName(), "の持っている",
					chenge_item.getColoredName(), "を強引に奪った");
			return true;
		} else {
			Base_Artifact tokiko_scroll = ItemTable.itemReturn(
					Player.me.getMassPoint(), ItemDetail.SCROOL);
			int index = Belongings.getListItems().indexOf(chenge_item);
			Belongings.remove(chenge_item);
			Belongings.setItems(tokiko_scroll, index);
			Message.set(getColoredName(), "は", Player.me.getName(), "の持っている",
					chenge_item.getColoredName(), "と",
					tokiko_scroll.getColoredName(), "を交換した");
			return true;
		}
	}

	// public Image getImage() {
	// return IMAGE.にとり.getImage(direction, MainThread.getKoma());
	// }

	@Override
	protected boolean specialCheck() {
		if (LV == 1) {
			return false;
		}
		if (!attack_possible()) {
			return false;
		}
		if (!isSpecialParcent()) {
			return false;
		}
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof Scrool) {
				return true;
			}
		}
		return false;
	}
}
