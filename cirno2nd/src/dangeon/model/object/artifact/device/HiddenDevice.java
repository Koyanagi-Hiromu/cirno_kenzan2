package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;

public class HiddenDevice extends Base_Device {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final int composition = 0;
	public static String name = "error";

	public HiddenDevice(Point p) {
		super(p, name, 1, composition, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.NULL;
		visible = false;
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "error", "" };
	}

	@Override
	public boolean isHidden() {
		return true;
	}

	@Override
	public boolean walkOnAction() {
		return false;
	}
}
