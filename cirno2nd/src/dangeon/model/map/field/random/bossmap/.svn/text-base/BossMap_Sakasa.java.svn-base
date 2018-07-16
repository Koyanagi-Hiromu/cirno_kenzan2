package dangeon.model.map.field.random.bossmap;

import java.awt.Point;

import main.res.BGM;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.scene.event.story.Event_Scene7;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.階段戻り;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.鬼人正邪;
import dangeon.model.object.creature.player.Player;

public class BossMap_Sakasa extends BossMap {
	private static final long serialVersionUID = 1L;

	public BossMap_Sakasa(Base_Map_Random bmr) {
		super(bmr, 1, new Point(19, 21));
	}

	@Override
	public BGM getBGM() {
		return BGM.kanpyo_ch2_fairy_starsapphire;
	}

	@Override
	protected Base_Enemy getBoss() {
		return new 鬼人正邪(new Point(19, 9), 4);
	}

	@Override
	protected Base_Artifact getStair() {
		String str;
		if (BMR.getStoryManager_ClearFlag().hasFinished()) {
			str = "最深層から帰還した";
		} else {
			str = "無事、宝物を取り返した";
		}
		if (Player.me.flag_no_item) {
			str = "持ち込みなしで" + str;
		} else if (Player.me.flag_no_item_daichan) {
			str = "持ち込み無しで救出大作戦に挑み、そのまま" + str;
		}
		階段戻り a = new 階段戻り(MassCreater.getStairsIP(), str, BMR) {
			private static final long serialVersionUID = 1L;

			@Override
			public String[] getSlection() {
				if (BMR.getStoryManager_ClearFlag().hasFinished()) {
					return new String[] { "ダンジョンから帰還する", "やめる" };
				} else {
					return new String[] { "宝物を取り返す", "やめる" };
				}
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
	protected void message() {
		Message.set("ちくしょー！@");
	}
}
