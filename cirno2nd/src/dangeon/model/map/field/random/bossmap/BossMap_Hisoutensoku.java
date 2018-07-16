package dangeon.model.map.field.random.bossmap;

import java.awt.Point;

import main.res.BGM;
import main.res.Image_MapTip;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.event.story.Event_Scene7;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.階段戻り;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ヒソウテンソク;
import dangeon.model.object.creature.player.Player;

public class BossMap_Hisoutensoku extends BossMap {
	private static final long serialVersionUID = 1L;

	public BossMap_Hisoutensoku(Base_Map_Random bmr) {
		super(bmr, 2, new Point(19, 26));
	}

	@Override
	public BGM getBGM() {
		return BGM.hisoutensoku;
	}

	@Override
	protected Base_Enemy getBoss() {
		return new ヒソウテンソク(new Point(19, 9), 1);
	}

	@Override
	public Image_MapTip getMapTip() {
		return Image_MapTip.スキマ;
	}

	@Override
	protected Base_Artifact getStair() {
		String str;
		str = "最深層から帰還した";
		if (Player.me.flag_no_item) {
			str = "持ち込みなしで" + str;
		}
		階段戻り a = new 階段戻り(MassCreater.getStairsIP(), str, BMR) {
			private static final long serialVersionUID = 1L;

			@Override
			public String[] getSlection() {
				return new String[] { "ダンジョンから帰還する", "やめる" };
			}

			@Override
			public void move() {
				if (BMR.getStoryManager_ClearFlag().hasFinished()) {
					super.move();
				} else {
					final 階段戻り THIS = this;
					new Event_Scene7(BMR.returnTown(), new Task() {
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							BMR.getStoryManager_ClearFlag().saveThisFinished();
							THIS.move();
						}
					});
				}
			};
		};
		return a;
	}

	@Override
	public boolean isForcedToMakeEnemy() {
		return TurnSystemController.isBoss(boss);
	}

	@Override
	protected void message() {
	}

}
