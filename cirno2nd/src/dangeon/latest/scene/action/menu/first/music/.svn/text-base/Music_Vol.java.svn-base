package dangeon.latest.scene.action.menu.first.music;

import java.util.ArrayList;

import main.res.BGM;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;

public class Music_Vol extends Base_Scene_Menu {
	;

	public final int FIRST_VALUE, INDEX;

	public Music_Vol(KeyHolder kh, Base_View bv, int x, int y, int index) {
		super(BGM.VOL_MAX, kh, new Music_Vol_View(bv, x, y));
		INDEX = index;
		FIRST_VALUE = BGM.getVol();
		this.y = getNumberOfCols()
				- ((INDEX == 0) ? Config.getBGMVol() : Config.getSEVol());
	}

	@Override
	protected void action_cancel() {
		setVol(FIRST_VALUE);
		super.action_cancel();
	}

	@Override
	protected void action_else() {
	}

	@Override
	protected void action_enter() {
		if (INDEX == 0)
			Config.setBGMVol(getNumberOfCols() - y);
		else
			Config.setSEVol(getNumberOfCols() - y);
		super.action_enter();
	}

	@Override
	public boolean arrow(DIRECTION d) {
		y += d.Y;
		if (y < 0) {
			y = 0;
		} else if (y >= getNumberOfCols()) {
			y = getNumberOfCols();
		} else {
			SE.SYSTEM_CURSOR.play();
		}
		setVol(getNumberOfCols() - y);
		if (((Music_Vol_View) CURRENT_VIEW).BAR != null)
			((Music_Vol_View) CURRENT_VIEW).BAR.setY(y);

		return END;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return new Scene_Menu_First_Music(KH,
				CURRENT_VIEW.PREVIOUSE_VIEW.PREVIOUSE_VIEW, INDEX);
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
	}

	@Override
	protected void initialX_Y() {
	}

	public void setVol(int value) {
		if (INDEX == 0)
			BGM.setVol(value);
		else
			SE.setVol(value);
	}

}