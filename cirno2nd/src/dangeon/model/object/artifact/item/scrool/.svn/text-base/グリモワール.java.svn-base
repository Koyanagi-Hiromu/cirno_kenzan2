package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.util.UtilMessage;

/**
 * 
 * @author Administrator
 * 
 */
public class グリモワール extends Scrool implements SelectItem {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "グリモワール";
	public static final String item_name_sound = "くりもわーる";

	public グリモワール(Point p) {
		super(p, item_name);
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		for (Iterator<Base_Artifact> i = list.iterator(); i.hasNext();) {
			Base_Artifact a = i.next();
			if ((a instanceof SpellCard) || (a instanceof Staff))
				i.remove();
		}
		return list;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "指定したカードのボムの数を増やすぞ。上限値まで溜まっている状態なら？";
	}

	@Override
	public void scroolUse() {
		SE.MEKKI.play();
		if (A instanceof SpellCard) {
			SpellCard sc = (SpellCard) A;
			if (sc.getMaxBombs() == sc.getBombs()) {
				UtilMessage.effectDefMsg();
			} else {
				Message.set(A.getColoredName(), "の%", "ボムの数が増えた");
				sc.addBomb(1);
			}
		} else if (A instanceof Staff) {
			Staff s = (Staff) A;
			if (!s.isPerfectCheked()) {
				s.check();
			}
			if (s.getStaffRest() < s.MAX_STAFF_REST) {
				Message.set(A.getColoredName(), "の%", "使用回数が増えた");
				s.addStaffRest(2);
			} else {
				Message.set(A.getColoredName(), "はこれ以上使用回数は増えなかった");
			}
		} else {
			UtilMessage.effectDefMsg();
		}
	}

}