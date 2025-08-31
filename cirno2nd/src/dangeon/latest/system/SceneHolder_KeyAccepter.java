package dangeon.latest.system;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import dangeon.controller.DangeonScene;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.TurnSystemController;
import dangeon.controller.listener.KeyAccepter;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.model.map.MapList;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import main.Base_KeyAccepter;
import main.Listener;
import main.Listener.ACTION;
import main.constant.FR;
import main.util.BlackOut;
import main.util.DIRECTION;
import title.Title;

/**
 * 各Sceneにキー（direction/action）を渡すクラス スレッドの同期調整も（KeyHolderを通じて）行う
 * 
 * @author weray
 * 
 */
public class SceneHolder_KeyAccepter extends Base_KeyAccepter {
	public final Scene_Action SCENE_ACTION;
	private Base_Scene base_scene;
	public final KeyHolder kh;

	public SceneHolder_KeyAccepter() {
		kh = new KeyHolder(this);
		SCENE_ACTION = new Scene_Action(kh);
		base_scene = new Title(kh);
	}

	boolean action(ACTION a) {
		return base_scene.action_pre(a);
	}

	boolean arrow(DIRECTION direction) {
		return base_scene.arrow(direction);
	}

	public void clearTaskKeyActions() {
		kh.clearTaskKeyActions();
	}

	@Override
	public void draw(Graphics2D g) {
		Base_Scene bs;
		do {
			bs = base_scene;
			bs.draw(g);
		} while (bs != base_scene);
	}

	public boolean isCurrentScene(Base_Scene base_scene2) {
		return base_scene.equals(base_scene2);
	}

	public boolean isScene(Class<? extends Base_Scene> clazz) {
		return base_scene.getClass() == clazz;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!BlackOut.isEmpty())
			return;
		DIRECTION d = Listener.getAllow_map().get(e.getKeyCode());
		ACTION a = Listener.getKey().get(e.getKeyCode());
		if (d != null)
			kh.setTaskKeyArrow(d);
		if (a != null) {
			kh.setTaskKeyAction(a);
		}
		kh.set(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		DIRECTION d = Listener.getAllow_map().get(e.getKeyCode());
		ACTION a = Listener.getKey().get(e.getKeyCode());
		if (a != null || d != null) {
			base_scene.keyReleased(a, d);
		}
	}

	private void objectUpdate() {
		Player.me.addPlayingMilliTime(FR.THREAD_SLEEP);
		if (!(base_scene instanceof Scene_Action))
			return;
		if (TaskOnMapObject.isThrowTaskEmpty()) {
			// ValueFollower.setChase(Player.me.getConditionList());
			TaskOnMapObject.work();
			KeyAccepter.upDate();
			if (TaskOnMapObject.isThrowTaskEmpty()) {
				TurnSystemController.upDate();
			}
		} else {
			TaskOnMapObject.getThrow().upDate();
		}
		if (!DangeonScene.RESULT.isPresentScene()) {
			objectUpDate();
		}
	}

	private void objectUpDate() {
		int player = 1;
		List<Base_Enemy> enemies = MapList.getListEnemy();
		List<Base_Artifact> items = MapList.getListArtifact();
		ArrayList<Base_MapObject> list = new ArrayList<Base_MapObject>(player
				+ enemies.size());
		list.add(Player.me);
		for (Base_Enemy enemy : enemies) {
			list.add(enemy);
		}
		for (Base_Artifact a : items) {
			list.add(a);
		}
		for (Base_MapObject creature : list) {
			creature.upDate();
		}
	}

	public void set(KeyEvent ev) {
		base_scene.upDate(ev);
	}

	void setKeyAccepter(Base_Scene base_scene) {
		this.base_scene = base_scene;
		// for (DIRECTION d : DIRECTION.values()) {
		// this.base_scene.keyReleased(null, d);
		// }
	}

	@Override
	public final void up_date() {
		// 入力されたキーを処理してから
		kh.upDate();
		// シーン特有のアップデート
		base_scene.upDate();
		//
		objectUpdate();
	}

}
