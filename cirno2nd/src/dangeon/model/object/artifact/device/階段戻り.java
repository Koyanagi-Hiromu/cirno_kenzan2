package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.menu.view.result.Scene_Result_Info;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.creature.player.Player;

public class 階段戻り extends Stairs {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public final String RESULT;
	public final Base_Map_Random BMR;

	public 階段戻り(Point p, String result, Base_Map_Random bmr) {
		super(p, "戻り階段", 1, 0, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.STAIRS;
		RESULT = result;
		BMR = bmr;
	}

	private void addStory() {
		if (Config.isHack_playing()) {
			return;
		}
		StoryManager flag = BMR.getStoryManager_ClearFlag();
		if (flag != null)
			flag.saveThisFinished();
		//
		// for (Enchant enc : Enchant.values()) {
		// if (Boolean.TRUE.equals(Player.me.hash_restriction.get(enc))) {
		// // 1つでも縛れてたら
		// if (BMR instanceof 最強への道) {
		// Config.setValue(Config.SHIBARI[0], true);
		// } else if (BMR instanceof 慧音の最終問題) {
		// Config.setValue(Config.SHIBARI[1], true);
		// } else if (BMR instanceof 博麗中結界) {
		// Config.setValue(Config.SHIBARI[2], true);
		// } else if (BMR instanceof 隕石異変) {
		// Config.setValue(Config.SHIBARI[3], true);
		// }
		// break;
		// }
		// }
		// if (BMR instanceof 慧音の最終問題 && Config.getStory() < 10000) {
		// Config.setStory(10000);
		// } else if (BMR instanceof 最強への道 && Config.getStory() < 99999) {
		// Config.setStory(99999);
		// } else if (BMR instanceof 博麗中結界 && Config.getReimuEvent() < 10000) {
		// Config.setReimuEvent(10000);
		// }
	}

	@Override
	protected String[] getExplan() {
		return null;
	}

	@Override
	public String[] getSlection() {
		return new String[] { "ダンジョンから帰還する", "やめる" };
	}

	@Override
	public void move() {
		addStory();
		Player.flag_clear = true;
		Player.me.cause_of_death = RESULT;
		new Scene_Result_Info(BMR);
	}

}
