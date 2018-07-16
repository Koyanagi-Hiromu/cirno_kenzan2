package dangeon.latest.scene.action.otog;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.BGM;
import main.res.SE;
import main.util.BlackOut;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.otog.main.panel.Base_Panel;
import dangeon.latest.scene.action.otog.main.panel.Panel_East;
import dangeon.latest.scene.action.otog.main.panel.Panel_Main;
import dangeon.latest.scene.action.otog.main.panel.Panel_Sub;
import dangeon.latest.scene.action.otog.main.panel.Panel_Sub2;
import dangeon.latest.scene.action.otog.main.panel.Panel_Sub3;
import dangeon.latest.scene.action.otog.main.panel.Panel_West;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;

public class Scene_Otog extends Base_Scene {
	public final BufferedImage IM;
	public final ArrayList<Base_Panel> LIST = new ArrayList<Base_Panel>();
	public Panel_Main main_panel;
	public final Otog_Select PRE_OTOG_Select;

	public BGM pre_bgm;

	public int frame = 0;

	public final double FRAME = 75.0;
	public int panel_frame;

	public Scene_Otog(Otog_Select otog_Select) {
		super(Scene_Action.getMe().KH, new Scene_Otog_View());
		pre_bgm = BGM.get();
		PRE_OTOG_Select = otog_Select;
		IM = FileReadSupporter.readPNGImage("otog/image/music_bg02.png");
	}

	@Override
	public boolean action(ACTION a) {
		if (main_panel.VH.HASH.getIndex(a) == -1) {
			if (++frame > 33) {
				SE.SYSTEM_MAGIC.play();
				frame = 0;
				interrput();
				return END;
			}
			return AUTO_REPEAT;
		} else {
			frame = 0;
		}
		main_panel.action(a);
		return AUTO_REPEAT;
	}

	@Override
	public boolean arrow(DIRECTION d) {
		main_panel.arrow(d);
		return AUTO_REPEAT;
	}

	public Otog_Select getNextSelectScene() {
		return new Otog_Select(KH, PRE_OTOG_Select.CURRENT_VIEW.PREVIOUSE_VIEW,
				main_panel.VH);
	}

	public void init(Otog_Select s, boolean is_demo) {
		LIST.clear();
		panel_frame = 0;
		int margine = 10;
		main_panel = new Panel_Main(margine, this, s.VH);
		LIST.add(main_panel);
		Panel_West west = new Panel_West(main_panel, margine);
		LIST.add(west);
		Panel_Sub sub = new Panel_Sub(main_panel, margine);
		LIST.add(sub);
		Panel_Sub2 sub2 = new Panel_Sub2(main_panel, sub, margine);
		LIST.add(sub2);
		LIST.add(new Panel_Sub3(main_panel, sub2, margine));
		LIST.add(new Panel_East(main_panel, sub, margine));
		main_panel.VH.setOffset(s.getContent(0).OFFSET);
		main_panel.gameStart(s.getContent(0).BGM, is_demo);
	}

	public void interrput() {
		if (!BlackOut.isEmpty())
			return;
		if (!main_panel.VH.demo)
			for (TEXT t : TEXT.values()) {
				if (t == TEXT.MISS)
					continue;
				if (main_panel.VH.hash_save_tex_count.get(t) > 0) {
					main_panel.interrupt();
					return;
				}
			}
		new Fader(this, getNextSelectScene()) {
			@Override
			protected void taskWork() {
				pre_bgm.play();
			}
		};
	}

	@Override
	public void upDate() {
		main_panel.upDate();
	}

}
