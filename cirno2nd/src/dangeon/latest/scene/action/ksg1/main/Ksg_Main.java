package dangeon.latest.scene.action.ksg1.main;

import java.awt.image.BufferedImage;

import main.Listener.ACTION;
import main.constant.FR;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.ksg1.main.panel.Ksg_Panel_Main;
import dangeon.latest.scene.action.ksg1.main.select.Ksg_End;
import dangeon.latest.system.KeyHolder;

public class Ksg_Main extends Base_Scene {

	public BufferedImage IM;
	public final Base_Scene prev;
	public final Ksg_Panel_Main PANEL;

	public Ksg_Main(KeyHolder kh, Base_View bv) {
		super(kh, new Ksg_Main_View(bv));
		prev = bv.PARENT_SCENE;
		IM = FileReadSupporter.readPNGImage("otog/image/music_bg01.png");

		setNextScene(this);
		int margine = 10;
		int w = 200;
		int x = (FR.SCREEN_WIDTH - w) / 2;
		PANEL = new Ksg_Panel_Main(x, w, margine, KH);
	}

	@Override
	public boolean action(ACTION a) {
		if (!PANEL.action(a)) {
			setNextScene(new Ksg_End(KH, CURRENT_VIEW, PANEL.count));
		}
		return END;
	}

	@Override
	public boolean arrow(DIRECTION d) {
		if (!PANEL.arrow(d)) {
			setNextScene(new Ksg_End(KH, CURRENT_VIEW, PANEL.count));
		}
		return END;
	}

	public Base_Scene init() {
		PANEL.init();
		return this;
	}

	@Override
	public void upDate() {
		PANEL.upDate();
		if (!PANEL.upDate_limit()) {
			setNextScene(new Ksg_End(KH, CURRENT_VIEW, PANEL.count));
		}
	}

}
