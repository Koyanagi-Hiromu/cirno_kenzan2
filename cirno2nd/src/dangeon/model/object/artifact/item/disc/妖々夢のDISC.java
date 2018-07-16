package dangeon.model.object.artifact.item.disc;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

public class 妖々夢のDISC extends Base_Disc {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected 妖々夢のDISC(Point p, String item_name) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.妖々夢;
	}

}
