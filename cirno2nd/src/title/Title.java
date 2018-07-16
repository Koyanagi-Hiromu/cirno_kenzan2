package title;

import java.util.ArrayList;

import main.Listener;
import main.Listener.ACTION;
import main.Version;
import main.res.BGM;
import main.res.SE;
import main.util.BlackOut;
import main.util.DIRECTION;
import title.command.Title_Comannd;
import title.objects.Base_Title_Object;
import title.objects.body.Body;
import title.objects.body.Onigiri;
import title.objects.body.PushEnter;
import title.objects.body.Rogo;
import title.objects.left.Left;
import title.objects.left.Left1;
import title.objects.left.Left2;
import title.objects.right.Right;
import title.objects.right.Right1;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.event.story.Event_Scene1;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.save.SaveLoad;

public class Title extends Base_Scene {
	public final ArrayList<Base_Title_Object> objects = new ArrayList<Base_Title_Object>(),
			objects_first = new ArrayList<Base_Title_Object>();
	private int first_wait_frame = 7;

	public String msg;

	public int percent;

	public Title(KeyHolder kh) {
		super(kh, new Title_View());
		objects_first.add(new Rogo());
		objects_first.add(new PushEnter());
		objects.add(new Body());
		addSecond();
		BGM.kanpyo_ch2_fairy.play();
	}

	@Override
	public boolean action(ACTION a) {
		if (a != null) {
			if (a == ACTION.ENTER) {
				SE.SYSTEM_ENTER.play();
				setNextScene(new Title_Comannd(KH, CURRENT_VIEW, this));
			}
		}
		return END;
	}

	public void addSecond() {
		objects.add(new Left2());
		objects.add(new Left1());
		objects.add(new Left());
		objects.add(new Right1());
		objects.add(new Right());
		objects.add(new Onigiri());
	}

	@Override
	public boolean arrow(DIRECTION d) {
		return false;
	}

	public void setMsg(String string) {
		msg = string;
		percent = 0;
	}

	public void setPercent(int p) {
		percent = p;
	}

	public void startGame() {
		if (new Version(this).latestCheck()) {
			Listener.setValid(false);
			return;
		}
		startGame(true);
	}

	public void startGame(boolean b) {
		Listener.setValid(true);
		boolean flag_load_suceess = SaveLoad.staticLoad();
		if (flag_load_suceess) {
			return;
		} else {
			final Base_Map bm;
			Player.me.setDirection(DIRECTION.DOWN);
			bm = new FairyPlace();
			Player.me.resetAll();
			if (Config.getTimes(Config.getSaveIndex() - 1) == -1) {
				Config.addTimes();
				Config.addTimes();
				new Event_Scene1();
				return;
			} else {
				new BlackOut(bm, new Task() {
					/**
							 *
							 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						if (Config.getTimes(Config.getSaveIndex() - 1) == 0) {
							Config.addTimes();
						}
						setNextScene(Scene_Action.getMe());
						MassCreater mc = new MassCreater(bm, false);
						mc.createMap();
					}
				});

			}
		}
	}

	@Override
	public void upDate() {
		if (first_wait_frame == 0)
			for (Base_Title_Object o : objects)
				o.upDate();
		else
			first_wait_frame--;
		for (Base_Title_Object o : objects_first)
			o.upDate();
	}

}
