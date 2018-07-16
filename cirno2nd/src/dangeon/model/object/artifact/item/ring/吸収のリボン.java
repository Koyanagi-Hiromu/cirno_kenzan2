package dangeon.model.object.artifact.item.ring;

import java.awt.Point;

/**
 * 
 * @author Administrator
 * 
 */
public class 吸収のリボン extends Ring {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "吸収のリボン";
	public static final String item_name_sound = "きゅうしゅうのりほん";

	public 吸収のリボン(Point p) {
		super(p, item_name);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "これを二つ装備していると、敵を吸収出来るようになるぞ";
	}

}
