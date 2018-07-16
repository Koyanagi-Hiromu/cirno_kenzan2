package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;

public class 撒き餌の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "撒き餌の書";
	public static final String item_name_sound = "まきえのしょ";

	public 撒き餌の書(Point p) {
		super(p, item_name);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "";
	}

	@Override
	public void scroolUse() {
		if (!(PresentField.get() instanceof Base_Map_Random)) {
			Message.set("ふしぎなちからによってかき消された");
			return;
		}
		SE.LIGHTNING.play();
		ArrayList<Base_Artifact> _list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (a instanceof Base_Device)
				continue;
			if (MassCreater.isPlayerInTheSameRoom(a.getMassPoint())) {
				_list.add(a);
			}
		}
		for (Base_Artifact a : _list) {
			TaskOnMapObject.addItemRemoveTask(a);
			EnemyTable.setOnMapPointAndLevel(a.getMassPoint(), 0, false);
		}
	}
}
