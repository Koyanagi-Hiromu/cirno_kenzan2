package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class こいしのお忍びリボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "こいしのお忍びリボン";
	public static final String item_name_sound = "こいしのおしのひりほん";

	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.忍;

	public こいしのお忍びリボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
