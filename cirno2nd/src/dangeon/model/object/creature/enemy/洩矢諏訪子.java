package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.RainEffect;

public class 洩矢諏訪子 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 洩矢諏訪子(Point p, int Lv) {
		super(p, Lv);
	}

	private void effect() {
		if (LV < 3) {
			setAnimation(RainEffect.work(-LV));
		} else {
			setAnimation(RainEffect.work_fuck());
		}
	}

	private void effetct_pratica() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.衰)) {
			Message.set("しかし何も起こらなかった");
			return;
		}
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		Base_Artifact a;
		a = Enchant.ATK.getEnchant();
		if (a != null) {
			list.add(a);
		}
		a = Enchant.DEF.getEnchant();
		if (a != null) {
			list.add(a);
		}
		if (list.isEmpty()) {
			return;
		}
		int select = new R().nextInt(list.size());
		a = list.get(select);
		if (EnchantSpecial.simbolCheckOne(a, ENCHANT_SIMBOL.金)) {
			if (LV == 3) {
				a.getListComposition().remove(ENCHANT_SIMBOL.金);
				Message.set("神の加護が剥がれた");
			} else {
				Message.set("しかし神の加護があって大丈夫だった");
			}
		} else {
			int i = level();
			Message.set(a.getColoredName(), "の修正値が",
					半角全角コンバーター.半角To全角数字(Math.abs(i)), "下がった");
			a.setForgeValue(i);
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		for (Base_Creature c : MapInSelect.getListRoomInEnemy()) {
			c.setDEF(0);
		}
		Message.set("雨を降らして、防御力を無くした");
		return true;
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, getAnimation().getComa() % 3);
		} else {
			return super.getImage();

		}
	}

	private int level() {
		if (LV == 1) {
			return -1;
		} else if (LV == 2) {
			return -2;
		} else {
			return -3;
		}
	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			direction = converDirection(Player.me.getMassPoint());

			effect();
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible()) {
			if (isSpecialParcent()) {
				return true;
			}
		}
		return false;
	}

}
