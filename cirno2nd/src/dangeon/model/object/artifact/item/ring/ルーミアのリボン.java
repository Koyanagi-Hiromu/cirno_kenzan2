package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class ルーミアのリボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ルーミアの封印リボン";
	public static final String item_name_sound = "ルーミアのふういんリボン";

	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.封印;

	public ルーミアのリボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "春ですので、元気になります。";
	}

}
