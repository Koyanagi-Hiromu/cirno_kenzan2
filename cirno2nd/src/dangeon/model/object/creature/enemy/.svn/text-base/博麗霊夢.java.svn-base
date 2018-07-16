package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.util.MapInSelect;
import dangeon.view.anime.MissEffect;
import dangeon.view.detail.MainMap;

public class 博麗霊夢 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 博麗霊夢(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		MapInSelect.aroundInEnemyChengeCondition(CONDITION.封印, 0);
		return true;
	}

	@Override
	public boolean isInValidOnAttack() {
		if (LV == 4 && isPassiveSkillActive() && specialMoveParcent()) {
			setTelepoteAnimation(true, null);
			Message.set(getColoredName(), "は攻撃をグレイズした");
			MainMap.addEffect(new MissEffect(this, true));
			return true;
		}
		return false;
	}

	@Override
	public int isKamin() {
		return 0;
	}

	@Override
	public boolean isThroughItem(Base_Artifact a) {
		setTelepoteAnimation(true, null);
		MainMap.addEffect(new MissEffect(this, true));
		if (a instanceof MagicBullet) {
			Message.set("霊夢は魔法弾をすりぬけた");
		} else {
			Message.set("霊夢は投擲物をすりぬけた");
		}
		return true;
	}

	@Override
	protected boolean resistCondition(CONDITION c) {
		if (isPassiveSkillActive() && !c.equals(CONDITION.特殊仮眠)) {
			SE.REIMU_BARRIER.play();
			Message.set(getColoredName(), "は状態異常をダメージに変換した");
			int d;
			switch (LV) {
			case 1:
				d = 2;
				break;
			case 2:
				d = 4;
				break;
			case 3:
			default:
				d = 8;
				break;
			}
			chengeHP(null, null, -this.getMAX_HP() / d);
			return true;
		}
		return false;
	}

	// @Override
	// public boolean staffHitEffect(Base_Artifact a) {
	// SE.REIMU_BARRIER.play();
	// chengeHP(null, null, -2);
	// Message.set(Color.RED.toString(), "霊夢の結界は魔法の効果を２ポイントのダメージに変えた",
	// Color.WHITE.toString());
	// return false;
	// }

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
