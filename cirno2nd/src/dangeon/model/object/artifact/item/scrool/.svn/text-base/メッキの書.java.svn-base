package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

/**
 * メッキ
 * 
 * @author Administrator
 * 
 */
public class メッキの書 extends Scrool implements SelectItem {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ピカピカの書";
	public static final String item_name_sound = "ピカピカのしょ";

	public メッキの書(Point p) {
		super(p, item_name);
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		return null;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "カードかおにぎりにメッキを施す。メッキが施されたカードは錆びなくなりおにぎりは腐らなくなる。おにぎりは食感がボソボソになるが、うん、まぁ、食べられる。";
	}

	@Override
	public boolean isAbleToCurse() {
		return false;
	}

	@Override
	protected boolean isParmitToOpen() {
		return false;
	}

	@Override
	public void scroolUse() {
		SE.MEKKI.play();
		if (A.compositionSpace()) {
			Message.set(A.getColoredName(), "に加護を施した");
			A.addListComposition(ENCHANT_SIMBOL.金);
			A.changeToGolden();
		} else {
			Message.set("うまく", A.getColoredName(), "%に加護を施せなかった");
		}
		if (A.isCurse()) {
			A.setCurse(false);
			Message.set(A.getColoredName(), "の呪いが解けた");
		}
	}
}