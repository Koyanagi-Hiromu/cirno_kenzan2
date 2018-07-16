package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.scrool.識別の書;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.view.anime.Special_Wait;

public class 上白沢慧音 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 上白沢慧音(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		for (Base_Creature c : MapInSelect.getListRoomInEnemy()) {
			c.addLV(-1);
		}
		return true;
	}

	@Override
	protected Base_Artifact getDropItem() {
		return new 識別の書(mass_point);
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getSPImage(LV, getAnimation().getComa());
		}
		return super.getImage();
	}

	@Override
	public int isKamin() {
		return 100;
	}

	@Override
	protected int itemDropParcent() {
		return super.itemDropParcent() * 8;
	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			direction = converDirection(Player.me.getMassPoint());

			Player.me.startDamaging();
			setAnimation(new Special_Wait(this, 8, 3, SE.KEINE_SP, 1,
					new Task() {
						/**
				 *
				 */
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
									ENCHANT_SIMBOL.竹)) {
								Message.set(Player.me.getColoredName(),
										"の歴史は悠久でうまく喰べれなかった");
							} else if (EnchantSpecial.enchantSimbolAllCheck(
									CASE.RING, ENCHANT_SIMBOL.衰)) {
								Message.set(getColoredName(), "は",
										Player.me.getColoredName(),
										"の歴史をうまく喰べれなかった");
							} else {
								Message.set(getColoredName(), "は",
										Player.me.getColoredName(), "の歴史を喰べた");
								Player.me.addLV(-1);
								SE.LEVEL_DOWN.play();
								// Message.set(Player.me.getColoredName().concat(
								// "のレベルが１下がった"));
							}
						}
					}));
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (Player.me.getLV() == 1) {
			return false;
		}
		if (attack_possible()) {
			if (isSpecialParcent()) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		attack_No = attaking_frame / 3;
		if (attack_No > 4)
			attack_No = 4;
	}

}
