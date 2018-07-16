package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class 探知のリボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "探知のリボン";
	public static final String item_name_sound = "たんちのりほん";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.探知;

	public 探知のリボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "貴様、見ているな！";
	}

}
