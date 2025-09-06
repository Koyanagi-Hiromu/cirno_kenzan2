package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.魅魔のカード;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.UtilMessage;
import main.res.Image_Artifact;
import main.res.SE;

public class グリモワール2 extends Scrool implements SelectItem {
	private static final long serialVersionUID = 1L;

	public グリモワール2(Point p) {
		super(p, "封魔録「グリモワールオブミマ」");
		sim = null;
		IM = Image_Artifact.BOOK2;
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		for (Iterator<Base_Artifact> i = list.iterator(); i.hasNext();) {
			Base_Artifact a = i.next();
			if ((a instanceof SpellCard) && a.composition_number < 7)
				i.remove();
			if ((a instanceof Staff))
				i.remove();
			if ((a instanceof ワイルドカード))
				i.remove();
		}
		return list;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "白紙だけども白紙じゃないぞ";
	}

	@Override
	public void scroolUse() {
		SE.MEKKI.play();
		if (A instanceof SpellCard) {
			SpellCard sc = (SpellCard) A;
			A.composition_number = 7;
			Message.set(A.getColoredName(), "の%", "器の数が増えた");
		} else if (A instanceof Staff) {
			Staff s = (Staff) A;
			if (!s.isPerfectCheked()) {
				s.check();
			}
			if (s.getStaffRest() < s.MAX_STAFF_REST) {
				Message.set(A.getColoredName(), "の%", "使用回数が増えた");
				s.addStaffRest(7);
			} else {
				Message.set(A.getColoredName(), "はこれ以上使用回数は増えなかった");
			}
		} else if (A instanceof ワイルドカード) {
			int i = Belongings.getListItems().indexOf(A);
			Message.set(A.getColoredName(), "が輝いた");
			Belongings.remove(A);
			魅魔のカード c = new 魅魔のカード(Player.me.getMassPoint());
			c.check();
			Belongings.setItems(c, i);
		} else {
			UtilMessage.effectDefMsg();
		}
	}
}
