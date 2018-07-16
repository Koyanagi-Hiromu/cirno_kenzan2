package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

/**
 * マムルの位置づけ
 * 
 * @author Administrator
 * 
 */
public class ゆっくり霊夢 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public ゆっくり霊夢(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Player.me.normalAttack();
		Base_Enemy e = MapInSelect.getFrontEnemyFromCreature(Player.me);
		if (e != null)
			e.setCondition(CONDITION.鈍足, 0);
		return true;
	}

	@Override
	public Image getImage() {
		return super.getImage();
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
