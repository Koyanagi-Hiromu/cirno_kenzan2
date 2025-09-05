package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.UtilMessage;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.detail.View_Sider;
import main.res.SE;

public class 魂魄妖夢 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 魂魄妖夢(Point p, int Lv) {
		super(p, Lv);
	}

	public boolean effect() {
		final Base_Creature c = this;
		setAnimation(new Special_Wait_FrameByFrame(this, SE.FIRST_OURA, 1,
				new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					}

					@Override
					public void work(int frame) {
						if (frame == 10) {
							if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
									ENCHANT_SIMBOL.融合)) {
								UtilMessage.effectDefMsg_RIBON();
								return;
							}
							Base_Artifact a = itemSelect();
							Player.me.startDamaging();
							if (a == null) {
								Message.set(getColoredName().concat("は空振りした"));
								return;
							}
							if (LV == 1) {
								Message.set(getColoredName().concat("は")
										.concat(a.getColoredName().concat(
												"を弾いた")));
								View_Sider.setInformation(a.getColoredName(),
										"が弾かれました");
							} else {
								Message.set(getColoredName().concat("は")
										.concat(a.getColoredName().concat(
												"を弾き飛ばした")));
								View_Sider.setInformation(a.getColoredName(),
										"が弾き飛ばされました");
							}
							SE.YOUMU_SP2.play();
							Belongings.remove(a);
							Enchant.forceToRemove(a);
							a.setMassPoint(Player.me.getMassPoint()
									.getLocation());
							if (LV == 1) {
								ItemFall.itemFall(direction.getFrontPoint(Player.me.getMassPoint()), a);
							} else {
								a.itemThrow(c, HowToThrow.NORMAL, true);
							}
						}
					}
				}, 0, 0, 1, 2, 1, 2, 1, 2, 1, 2, 3, 3, 4, 4, 4, 5, 5, 5));

		return true;
	}

	;

	@Override
	public boolean enchantEnemySpecialAction() {
		// FIXME
		Message.set("未実装");
		return true;
	}

	@Override
	public Image getImage() {
		// if (getAnimation() != null) {
		// return IM.getSPImage(LV, direction, getAnimation().getComa());
		// }
		return super.getImage();
	}

	private Base_Artifact itemSelect() {
		if (Enchant.getEnchantList().isEmpty()) {
			return null;
		}
		int select = new R().nextInt(Enchant.getEnchantList().size());
		Base_Artifact a = Enchant.getEnchantList().get(select);
		if (LV == 3 || LV == 4) {
			if (a instanceof SpellCard)
				return a;
			select = new R().nextInt(Enchant.getEnchantList().size());
			a = Enchant.getEnchantList().get(select);
		}
		return a;
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible()) {

			return true;
		}
		direction = converDirection(Player.me.getMassPoint());

		effect();
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

}
