package dangeon.latest.scene.action.menu.view.result;

import java.awt.Graphics2D;
import java.awt.Image;

import main.res.CHARA_IMAGE;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.scrool.Scrool;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.ScroolWindow;

public class ResultRecord extends Scrool {
	private final Scene_Result_Info INFO;

	public ResultRecord(KeyHolder kh, Base_View bv, Scene_Result_Info info) {
		super(kh, bv, Scene_Result_Info.result.RECORD);
		INFO = info;
	}

	@Override
	protected void action_cancel() {
		SE.SYSTEM_CURSOR.play();
		setNextScene(INFO);
	}

	@Override
	protected void action_enter() {
		setNextScene(INFO);
	}

	@Override
	protected ScroolWindow createScroolWindow(String... contents) {
		return new ScroolWindow(this, contents) {
			@Override
			protected void drawHorizon(Graphics2D g, int i) {
				super.drawHorizon(g, i);
				Image im = CHARA_IMAGE.arrow.getWalkImage(1, DIRECTION.DOWN, 0);
				g.drawImage(im, getViewX() + 8, getViewY() + getContentHeight()
						* i - 11, null);
			}
		};
	}

	@Override
	protected int getFirstIndex() {
		int i = Message.getRecordSize() - COL - 1;
		if (i < 0)
			i = 0;
		return i;
	}

}