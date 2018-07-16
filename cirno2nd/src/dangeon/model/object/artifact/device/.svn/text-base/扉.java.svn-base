package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.controller.TaskOnMapObject;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.Base_Map;

public class 扉 extends Base_Device {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int composition = 0;
	public boolean sight = true;
	public static String name = "移動";
	private final Base_Map BFM;
	private final Point PLAYER;

	public 扉(Point p, Base_Map bfm, Point player) {
		super(p, name, 1, composition, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.STAIRS;
		BFM = bfm;
		PLAYER = player;
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "装備:", "使用:", "" };
	}

	@Override
	public boolean itemUse() {
		walkOnAction();
		return false;
	}

	@Override
	public void itemUseThis() {
		walkOnAction();
	}

	@Override
	public boolean walkOnAction() {
		TaskOnMapObject.setNewMap(new MassCreater(BFM, PLAYER, false));
		return true;
	}

}
