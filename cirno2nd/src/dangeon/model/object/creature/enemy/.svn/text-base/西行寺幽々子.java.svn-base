package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.scrool.敵倍速の書;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class 西行寺幽々子 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private int count = 0;

	public 西行寺幽々子(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		MapInSelect.roomInCreatureChengeCondition(Player.me, CONDITION.死, 0);
		return true;
	}

	@Override
	protected Base_Artifact getDropItem() {
		return new 敵倍速の書(mass_point);
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, getAnimation().getComa());
		}
		return super.getImage();
	}

	@Override
	public int getShadowSize100() {
		if (getAnimation() != null) {
			if (getAnimation().getComa() == 1) {
				return 20;
			} else if (getAnimation().getComa() == 2) {
				return 15;
			}
		}
		return super.getShadowSize100();
	}

	@Override
	protected void init4() {
		count = 0;
		super.init4();
	}

	private int level() {
		switch (LV) {
		case 1:
			return 10;
		case 2:
			return 5;
		case 3:
			return 3;
		default:
			return 1 + count;
		}
	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			count++;
			direction = converDirection(Player.me.getMassPoint());
			Message.set(getColoredName(), "が死を誘って来た");
			if (!EnchantSpecial.enchantSimbolAllCheck(EnchantSpecial.CASE.DEF,
					ENCHANT_SIMBOL.竹)) {
				Player.me.startDamaging();
			}
			final 西行寺幽々子 ME = this;
			setAnimation(new Special_Wait_FrameByFrame(
					this,
					SE.YUYUKO_SPELL,
					1,
					new Task() {
						/**
						 *
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							if (LV == 4) {
								ME.setCondition(CONDITION.死, level());
							}
							if (EnchantSpecial.enchantSimbolAllCheck(
									EnchantSpecial.CASE.DEF, ENCHANT_SIMBOL.竹)) {
								if (LV == 4) {
									Message.set("しかしチルノには効かなかった");
								} else {
									Message.set("しかし永遠の前では無意味だった");
								}
							} else {
								Player.me.setCondition(CONDITION.死, level());
							}
						}
					}, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 0,
					0, 0));
			return true;
		}
		return false;
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
