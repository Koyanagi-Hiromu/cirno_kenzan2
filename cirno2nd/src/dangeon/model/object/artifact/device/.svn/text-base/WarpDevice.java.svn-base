package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;

public class WarpDevice extends Base_Device {
	/**
	 *
	 */
	private static final long serialVersionUID = 7102978615813695843L;

	public WarpDevice(Point p) {
		super(p, "ワープ装置", 1, 0, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.trap1_8;
		sim = ENCHANT_SIMBOL.バネ;
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "踏んだり使用したりするとどこか別の部屋へワープする",
				"どんな手段でも破壊することも移動させることもできない" };
	}

	@Override
	public boolean walkOnAction() {
		Player.me.setMassPoint_Jump(MassCreater.getWarpPoint(null));
		return false;
	}
}
