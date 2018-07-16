package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class 焼き芋のリボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "静葉の焼き芋リボン";
	public static final String item_name_sound = "やきいもりほん";

	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.芋;

	public 焼き芋のリボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "春ですので、元気になります。";
	}

}
