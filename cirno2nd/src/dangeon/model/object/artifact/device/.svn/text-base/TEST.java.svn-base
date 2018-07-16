package dangeon.model.object.artifact.device;

import main.res.Image_Artifact;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.creature.player.Player;

public class TEST extends Base_Device {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int composition = 0;
	public boolean sight = true;
	public static String name = "引き返し";

	public TEST() {
		super(Player.me.getMassPoint(), name, 1, composition, ITEM_CASE.TRAP,
				GROW_RATE.早熟, false);
		IM = Image_Artifact.NULL;
	}

	@Override
	protected String[] getExplan() {
		String[] str = { "０階に戻ります", };
		return str;
	}

	@Override
	public boolean itemUse() {
		itemUseThis();
		return false;
	}

	@Override
	public void itemUseThis() {
		new MassCreater(new FairyPlace(), false).createMap();
		MapList.setFloor(0);
		Player.me.resetAll();
	}

	@Override
	public boolean walkOnAction() {
		return false;
	}
}
