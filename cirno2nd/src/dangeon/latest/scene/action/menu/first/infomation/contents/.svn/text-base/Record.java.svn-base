package dangeon.latest.scene.action.menu.first.infomation.contents;

import java.awt.Graphics2D;
import java.awt.Image;

import main.res.CHARA_IMAGE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.scrool.Scrool;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.ScroolWindow;

public class Record extends Scrool {
	public Record(KeyHolder kh, Base_View bv) {
		super(kh, bv, Message.ME.getRecordToArray());
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