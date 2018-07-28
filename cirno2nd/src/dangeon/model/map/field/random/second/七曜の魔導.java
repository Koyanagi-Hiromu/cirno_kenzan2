package dangeon.model.map.field.random.second;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_MapTip;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.town.map.KoumaKan;
import dangeon.util.R;

public class 七曜の魔導 extends Base_Map_Random {
	private enum FloorConducter {
		// vs ヒソウテンソク hisoutensoku
		// vs 朱鷺子　izanagi
		ERROR(0, null, Image_MapTip.紅魔館, "七曜の魔導"), 運命のワルツ(7, BGM.frozen_night,
				Image_MapTip.紅魔館, "始まりの日曜日"), ゴーストナイトムーン(14, BGM.hyougenyakou,
				Image_MapTip.stars, "夜道の月曜日"), オールドヒストリー(24,
				BGM.kanpyo_ch_sprite, Image_MapTip.草原, "挑戦の火曜日"), 一条戻り橋(29,
				BGM.kanpyo_ch2_fairy, Image_MapTip.命蓮寺, "懐かしき水曜日"), ネクストヒストリー(
				44, BGM.kanpyo_ch2_hexa, Image_MapTip.草原_秋, "来世の木曜日"), レジスタンスベース(
				60, BGM.to_kou_chiruno, Image_MapTip.スキマ, "氾濫の金曜日"), 星に願いを(74,
				BGM.kanpyo_ch2_fff, Image_MapTip.stars, "祈りの土曜日"), 誰もいなくなるのか(
				86, BGM.kanpyo_ch_bbcc, Image_MapTip.地霊殿_水, "賢者の冥日"),

		;
		private static FloorConducter get() {
			if (PresentField.get() instanceof KoumaKan)
				return ERROR;
			int floor = MapList.getFloor();
			for (FloorConducter fc : values()) {
				if (floor <= fc.floor)
					return fc;
			}
			return ERROR;
		}

		private final int floor;
		private final BGM bgm;
		private final Image_MapTip map_tip;

		private final String title;

		private FloorConducter(int to_floor, BGM bgm, Image_MapTip map_tip) {
			this(to_floor, bgm, map_tip, null);
		}

		private FloorConducter(int to_floor, BGM bgm, Image_MapTip map_tip,
				String title) {
			this.floor = to_floor;
			this.bgm = bgm;
			this.map_tip = map_tip;
			this.title = title;
		}

		public Image_MapTip getMaptip() {
			return map_tip;
		}

		@Override
		public String toString() {
			if (title != null)
				return title;
			return super.toString();
		}
	}

	private static final long serialVersionUID = 1L;

	public 七曜の魔導() {
		super(Difficulty.Hard, 0, 10);
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 2;
	}

	@Override
	public int expRate_From1To100() {
		int rate = 100 - MapList.getFloor() + 1;
		return Math.max(rate, 25);
	}

	@Override
	public int getBelongingsMax() {
		return 20;
	}

	@Override
	public BGM getBGM() {
		return FloorConducter.get().bgm;
	}

	@Override
	public String getClassName() {
		return "七曜の魔導";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("アイテムが２０コまでしか持てませんが");
		list.add("持っているアイテムの印が全て適用されます");
		list.add("チルノのレベルが上がりにくいです");
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] {};
		// return new int[] { 14, 44, 74 };
	}

	@Override
	public Image_MapTip getMapTip() {
		return FloorConducter.get().getMaptip();
	}

	@Override
	public int getMaxFloor() {
		return 99;
	}

	@Override
	public int getSaisenParcent() {
		return 4;
	}

	@Override
	public int getShopParcent() {
		if (MapList.getFloor() <= FloorConducter.運命のワルツ.floor) {
			return 5;
		} else if (MapList.getFloor() > FloorConducter.星に願いを.floor) {
			return 0;
		} else {
			return 2;
		}
	}

	@Override
	public int getSMH_EnemyLV() {
		if (MapList.getFloor() <= FloorConducter.レジスタンスベース.floor) {
			return super.getSMH_EnemyLV();
		} else {
			return 4;
		}
	}

	@Override
	public int getSMH_Percent() {
		if (MapList.getFloor() <= FloorConducter.ネクストヒストリー.floor) {
			return 16;
		} else {
			if (MapList.getFloor() <= FloorConducter.誰もいなくなるのか.floor) {
				return SMH_NORMAL;
			} else {
				return 0;
			}
		}
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.七曜の魔導ok;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.七曜の魔導clear;
	}

	@Override
	public String getTitle() {
		return FloorConducter.get().toString();
	}

	@Override
	public int getTrapDefaultValue() {
		return super.getTrapDefaultValue();
	}

	@Override
	public boolean isAllEnchantDungeon() {
		return true;
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		int f = MapList.getFloor();
		int floors[] = new int[FloorConducter.values().length];
		for (int i = 0; i < floors.length; i++) {
			if (f == FloorConducter.values()[i].floor + 1)
				return true;
		}
		return super.isBGMDemandedToPlay();
	}

	@Override
	public boolean isEnemyEnchantDungeon() {
		return false;
	}

	@Override
	protected boolean isFixFloor() {
		if (MapList.getFloor() > FloorConducter.星に願いを.floor) {
			return new R().nextInt(100) < 20;
		} else {
			return new R().nextInt(100) < 10;
		}
	}

	@Override
	public boolean isLightful() {
		int f = MapList.getFloor();
		return f < 4;
	}

	@Override
	public boolean isLvUpedEnemy() {
		return true;
	}

	@Override
	public boolean isWarningEnemy() {
		return true;
	}
}
