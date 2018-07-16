package dangeon.model.object.artifact.item.disc;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

public class 地霊殿のDISC extends Base_Disc {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected 地霊殿のDISC(Point p, String item_name) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.地霊殿;
	}

}
