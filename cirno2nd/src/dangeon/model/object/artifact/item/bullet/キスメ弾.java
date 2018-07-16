package dangeon.model.object.artifact.item.bullet;

import java.awt.Image;
import java.awt.Point;

import main.res.BulletImage;
import main.util.DIRECTION;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.creature.Base_Creature;

public class キスメ弾 extends Arrow {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public キスメ弾(Base_Creature c, Point point) {
		super(c.getMassPoint(), "キスメ", false);
		direction = c.getDirection();
		setMassPoint_ParabolaJumpAttack(point);
	}

	@Override
	protected String[] getExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Image getImage(DIRECTION direction) {
		return BulletImage.キスメ.getImage(direction);
	}

	@Override
	public Arrow getOne() {
		return null;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	protected String scale() {
		return null;
	}
}
