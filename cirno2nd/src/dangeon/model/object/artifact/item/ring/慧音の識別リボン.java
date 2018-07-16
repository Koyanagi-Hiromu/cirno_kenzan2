package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class 慧音の識別リボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "慧音の識別リボン";
	public static final String item_name_sound = "けいねのしきへつりほん";

	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.識別;

	public 慧音の識別リボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "";
	}

}
