package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.food.ミシャグジおにぎり;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

/**
 * 
 * @author Administrator
 * 
 */
public class おにぎりの書 extends Scrool implements SelectItem {
	private static final long serialVersionUID = 1L;
	public static final String item_name = "おにぎりの書";
	public static final String item_name_sound = "おにきりのしょ";

	public おにぎりの書(Point p) {
		super(p, item_name);
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		return null;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "奇跡";
	}

	@Override
	public void scroolUse() {
		SE.MIRACLE_ONIGIRI.play();
		int i = Belongings.getListItems().indexOf(A);
		boolean flag_curs = A.isCurse();
		Belongings.remove(A);
		Base_Artifact a = flag_curs ? new ミシャグジおにぎり(Player.me.getMassPoint())
				: new 奇跡のおにぎり(Player.me.getMassPoint());
		if (i != -1 && i < Belongings.getMax()) {
			Belongings.setItems(a, i);
			if (Belongings.getSize() > Belongings.getMax()) {
				Belongings.remove(a);
				ItemFall.itemFall(Player.me.getMassPoint(), a);
			}
		} else {
			ItemFall.itemFall(Player.me.getMassPoint(), a);
		}
		Message.set(A.getColoredName(), "は", a.getColoredName(), "になった");
	}

}