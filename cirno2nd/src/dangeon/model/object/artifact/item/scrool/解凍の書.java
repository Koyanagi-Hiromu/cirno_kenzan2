package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;

public class 解凍の書 extends Scrool implements SelectItem {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "解凍の書";
	public static final String item_name_sound = "かいとうのしょ";

	public 解凍の書(Point p) {
		super(p, item_name);
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		return null;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "凍ったアイテムは何でも装備することができない。なので改めて装備したい場合はこの書に頼ることになるだろう。ちなみにこの書も凍る。";
	}

	@Override
	protected boolean isParmitToOpen() {
		return false;
	}

	@Override
	public void scroolUse() {
		SE.BURN.play();
		Message.set(A.getColoredName(), "を解凍した");
		A.freezeCountReset();
	}

}
