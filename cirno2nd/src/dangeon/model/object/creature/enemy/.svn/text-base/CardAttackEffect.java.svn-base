package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.Image_Anime;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.spellcard.プリズムリバー三姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.一輪雲山のカード;
import dangeon.model.object.artifact.item.spellcard.三月精のカード;
import dangeon.model.object.artifact.item.spellcard.九十九姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.堀川雷鼓のカード;
import dangeon.model.object.artifact.item.spellcard.秋姉妹のカード;
import dangeon.model.object.creature.player.Enchant;

public class CardAttackEffect extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private Base_Artifact a;

	public CardAttackEffect(Point p, int Lv) {
		super(p, Lv);
		a = Enchant.ATK.getEnchant();
	}

	@Override
	public Image getATKImage(int LV, DIRECTION attacking_direction,
			int attack_No) {
		if (a != null) {
			if (a instanceof プリズムリバー三姉妹のカード || a instanceof 九十九姉妹のカード
					|| a instanceof 三月精のカード) {
				return Image_Anime.sound.getImage(attack_No);
			} else if (a instanceof 秋姉妹のカード) {
				return Image_Anime.autom.getImage(attack_No);
			} else if (a instanceof 一輪雲山のカード) {
				return Image_Anime.cloud.getImage(attack_No);
			} else if (a instanceof 堀川雷鼓のカード) {
				return Image_Anime.kanden.getImage(attack_No == 3 ? 2
						: attack_No);
			}
		}
		return Image_Anime.cirno.getImage(attack_No);
	}

	@Override
	protected Point getAttackPoint() {
		return super.getAttackPoint();
	}

	@Override
	public SE getAttackSE() {
		if (a != null) {
			if (a instanceof プリズムリバー三姉妹のカード || a instanceof 九十九姉妹のカード
					|| a instanceof 三月精のカード) {
				return SE.ATTACK_WAVE;
			} else if (a instanceof 秋姉妹のカード) {
				return SE.ATTACK_SWING;
			} else if (a instanceof 一輪雲山のカード) {
				return SE.ATTACK_UNKNOWN;
			} else if (a instanceof 堀川雷鼓のカード) {
				return SE.THUNDER;
			}
		}
		return SE.ICE;
	}

	public void setA(Base_Artifact card) {
		a = card;
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		if (attaking_frame < 4) {
			attack_No = 0;
		} else if (attaking_frame < 7) {
			attack_No = 1;
		} else if (attaking_frame < 10) {
			attack_No = 2;
		} else {
			attack_No = 3;
		}
	}

}
