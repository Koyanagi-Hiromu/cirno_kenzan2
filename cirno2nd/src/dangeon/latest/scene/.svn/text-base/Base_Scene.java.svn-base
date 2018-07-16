package dangeon.latest.scene;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.Listener.ACTION;
import main.util.BeautifulView;
import main.util.DIRECTION;
import dangeon.latest.system.KeyHolder;

public abstract class Base_Scene {
	protected final boolean END = true, AUTO_REPEAT = false;
	public final KeyHolder KH;
	public final Base_View CURRENT_VIEW;
	private boolean flag_first_draw;

	public Base_Scene(KeyHolder kh, Base_View bv) {
		KH = kh;
		CURRENT_VIEW = bv;
		bv.setParentScene(this);
	}

	/**
	 * You must return "REPEAT_OFF" or "REPEAT_ON"
	 */
	public abstract boolean action(ACTION a);

	public boolean action_pre(ACTION a) {
		if (!flag_first_draw)
			return AUTO_REPEAT;
		return action(a);
	}

	public abstract boolean arrow(DIRECTION d);

	public final void draw(Graphics2D g) {
		BeautifulView.setAlphaOnImg(g, 1f);
		flag_first_draw = true;
		CURRENT_VIEW.beginToDraw(g, true);
	}

	public Base_Scene getPreviousScene() {
		return CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE;
	}

	protected boolean isContraining(ACTION a) {
		return KH.isContraining(a);
	}

	protected boolean isThisCurrentScene() {
		return KH.isThisCurrentScene(this);
	}

	public void keyReleased(ACTION a, DIRECTION d) {
		KH.removeTaskKeyAction(a);
		KH.removeTaskKeyDirection(d);
	}

	protected void setNextScene(Base_Scene base_scene) {
		KH.setKeyAccepter(base_scene);
	}

	public void upDate() {
	}

	/**
	 * Key設定用
	 * 
	 * @param ev
	 */
	public void upDate(KeyEvent ev) {
	}
}
