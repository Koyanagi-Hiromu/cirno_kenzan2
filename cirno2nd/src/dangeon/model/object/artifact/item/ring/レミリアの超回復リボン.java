package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class レミリアの超回復リボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "レミリアの超回復リボン";
	public static final String item_name_sound = "れみりあのちょうかいふくりほん";

	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.回復;

	public レミリアの超回復リボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "代謝が高ければ、お腹も減りやすい";
	}

}
