package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * 
 * @author Administrator
 * 
 */
public class 透視のリボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "はたての透視リボン";
	public static final String item_name_sound = "とうしのりほん";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.透視;

	public 透視のリボン(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "装備すると敵とアイテムの位置が分かる。最強のアイテムとして名高いアイテムで大変なレアアイテムなので大切にしよう。";
	}

}
