package dangeon.model.map.field.random;

import java.util.ArrayList;

import dangeon.controller.task.Task;
import dangeon.latest.scene.event.story.Event_Scene2;
import dangeon.latest.scene.event.story.Event_Scene3;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.random.bossmap.BossMap_Daisakusen;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.map.field.town.map.EternalHouse;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.階段戻り;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.Switch;
import main.res.Image_MapTip;

public class 救出大作戦 extends Base_Map_Random {

	private static final long serialVersionUID = 1L;

	public 救出大作戦() {
		super(Difficulty.Easy, 0, null);
	}

	@Override
	public int defaultItemNumber() {
		if (Difficulty.Easy.is(this)) {
			return new R().nextInt(4) + 3;
		} else {
			return new R().nextInt(4) + 2;
		}
	}

	@Override
	public BossMap getBossMap() {
		return new BossMap_Daisakusen(this) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Base_Artifact getStair() {
				String str;
				if (BMR.getStoryManager_ClearFlag().hasFinished()) {
					str = Player.me.flag_no_item ? "持ち込みなしで最深層まで辿り着き帰還した"
							: "最深層から帰還した";
				} else {
					str = "流れ星のかけらは取られたが、無事大妖精を救出した";
				}
				階段戻り a = new 階段戻り(MassCreater.getStairsIP(), str, BMR) {
					private static final long serialVersionUID = 1L;

					@Override
					public String[] getSlection() {
						if (BMR.getStoryManager_ClearFlag().hasFinished()) {
							return new String[] { "ダンジョンから帰還する", "やめる" };
						} else {
							return new String[] { "大妖精を助ける", "やめる" };
						}
					}

					@Override
					public void move() {
						if (BMR.getStoryManager_ClearFlag().hasFinished()) {
							super.move();
						} else {
							final 階段戻り THIS = this;
							new Event_Scene3(BMR.returnTown(), new Task() {
								private static final long serialVersionUID = 1L;

								@Override
								public void work() {
									BMR.getStoryManager_ClearFlag()
											.saveThisFinished();
									THIS.move();
								}
							});
						}
					};
				};
				return a;
			}

		};
	}

	@Override
	public String getClassName() {
		return "救出大作戦";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("はじめてのダンジョンです");
		list.add("カードの「使用」を選ぶ練習をしておこう");
		list.add("８階にお店が開かれていることがあるよ");
		if (StoryManager.大妖精救出へ.hasFinished()) {
			list.add("大妖精を助けました");
		} else {
			list.add("大妖精を助けに行こう");
		}
	}

	@Override
	protected long getFixedRandom() {
		return System.nanoTime() / 1000000l;
	}

	@Override
	public int[] getGouseiFloor() {
		if (Switch.test) {
			return new int[] { 2 };
		}
		return super.getGouseiFloor();
	}

	@Override
	public Image_MapTip getMapTip() {
		return Image_MapTip.草原_秋;
	}

	@Override
	public int getMaxFloor() {
		if (Difficulty.Easy.is(this)) {
			return 12;
		} else {
			return 15;
		}
	}

	@Override
	public int getMonsterHouse() {
		if (MapList.getFloor() != 1)
			return super.getMonsterHouse();
		return 0;
	}

	@Override
	public int getSaisenParcent() {
		return 0;
	}

	@Override
	public int getShopParcent() {
//		if (Difficulty.Easy.is(this) || Difficulty.Normal.is(this)) {
		if (MapList.getFloor() == 8) {
			return 100;
		}
//		}
		return 0;
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.大妖精救出clear;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.救出大作戦挑戦ok;
	}

	@Override
	public void goToBossMap() {
		if (!getStoryManager_ClearFlag().hasFinished()) {
			new Event_Scene2(getBossMap());
		} else {
			super.goToBossMap();
		}
	}

	@Override
	public boolean isBossMap() {
		return super.isBossMap();
	}

	@Override
	protected boolean isDaichanCard() {
		return StoryManager.大妖精救出へ.hasFinished();
	}

	@Override
	public boolean isLightful() {
		int f = MapList.getFloor();
		if (Difficulty.Normal.is(this)) {
			return f < 9 || f > 12;
		} else if (Difficulty.Hard.is(this)) {
			return f < 9;
		}
		return true;
	}

	@Override
	public Base_Map returnTown() {
		return new EternalHouse();
	}
}
