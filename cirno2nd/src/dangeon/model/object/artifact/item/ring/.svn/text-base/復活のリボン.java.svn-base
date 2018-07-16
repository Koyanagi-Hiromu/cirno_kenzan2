package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class 復活のリボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "妹紅の復活リボン";
	public static final String item_name_sound = "ふっかつのりほん";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.復活;

	public 復活のリボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "毎ターン経験値が１ずつ増える。これさえあれば君のレベルがマッハなのは確定的に明らか。「幸」印ではなく「福」印なのは幸せ草に礼儀正しい大人の対応をしたからだ。";
	}

}
