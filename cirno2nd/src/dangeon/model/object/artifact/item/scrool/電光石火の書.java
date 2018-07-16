package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.staff.Staff;

public class 電光石火の書 extends Scrool implements SelectItem {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "疾風の書";
	public static final String item_name_sound = "はやてのしょ";

	public 電光石火の書(Point p) {
		super(p, item_name);
		sim = null;
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		if (isStaticCheked()) {
			for (Iterator<Base_Artifact> i = list.iterator(); i.hasNext();) {
				Base_Artifact a = i.next();
				if (!(a instanceof SelectItem))
					if (!a.isCurse_And_isItViewed())
						i.remove();
			}
		}
		return list;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "アイテムを１つ完全識別する。杖に使えば残り回数も分かるので優先して使うといい。たまに持っているアイテムを全て識別する。足元のアイテムにも使える。";
	}

	@Override
	public void scroolUse() {
		if (A instanceof SelectItem) {
			SE.SYSTEM_ALARM.play();
			Message.set(getColoredName(), "は選択式のアイテムには使用できません");
			if (A instanceof Base_Item)
				Checker.select(A);
		} else if (A.isCurse()) {
			Message.set(A.getColoredName(), "は呪われていて使えなかった");
		} else {
			Message.set(A.getColoredName(), "を一瞬で使用した");
			if (!(A instanceof Staff)) {
				if (A.staticCheck()) {
					A.check();
				}
			}
			A.itemUse();
		}
	}

}