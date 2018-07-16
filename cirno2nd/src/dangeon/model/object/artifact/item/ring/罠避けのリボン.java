package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class 罠避けのリボン extends Ring {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "罠避けのリボン";
	public static final String item_name_sound = "わなよけのりほん";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.ワナ;

	public 罠避けのリボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
