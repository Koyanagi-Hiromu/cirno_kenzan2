package dangeon.model.map.field.random.second;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.model.config.StoryManager;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.NextFloor;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.random.ミラクルクエスト;
import dangeon.model.map.field.random.bossmap.BossMap_Daisakusen;
import dangeon.model.map.field.random.bossmap.BossMap_Hisoutensoku;
import dangeon.model.map.field.random.bossmap.BossMap_Sakasa;
import dangeon.model.map.field.random.bossmap.BossMap_Tokiko;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.map.field.special.map.EndingMap;
import dangeon.model.map.field.town.map.KoumaKan;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.arrow.鉄の矢;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.scrool.幻想郷縁起;
import dangeon.model.object.artifact.item.scrool.慧音の歴史書;
import dangeon.model.object.artifact.item.scrool.自由人の狂想曲;
import dangeon.model.object.creature.npc.second.Takarabako;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.ClassDefault;
import dangeon.util.R;
import main.res.BGM;
import main.res.Image_MapTip;
import main.res.SE;
import main.util.DIRECTION;

public class 七曜クエスト extends ミラクルクエスト {
	private enum FloorConducter {
		// vs ヒソウテンソク hisoutensoku
		// vs 朱鷺子　izanagi
		ERROR(0, null, Image_MapTip.紅魔館, "七曜クエスト"), 運命のワルツ(7, BGM.frozen_night,
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

	public 七曜クエスト() {
		super(Difficulty.Hard, 2, 0);
	}

	private class MiddMap extends EndingMap {
		private static final long serialVersionUID = 1L;
		private int count;

		public MiddMap(int count) {
			super(七曜クエスト.this);
			this.count = count - 1;
		}

		@Override
		public String getMapName() {
			return "境界の踊り場";
		}

		@Override
		protected Base_MapObject getStair(boolean flag_no_item) {
			Point center = new Point(19, 12);
			int i = 0;
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				if (i++ == count)
					break;
				if (i == 1) {
					add(new 鉄の矢(center.getLocation(), false).setArrowRest(15));
				} else {
					add(ItemTable.itemReturn(
							d.getFrontPoint(center.getLocation()), null));
				}
			}
			add(new Takarabako(center));
			return new Stairs(getExitPoint(), 七曜クエスト.this) {
				private static final long serialVersionUID = 1L;

				private void boss() {
					new ConvEvent("ただならぬ気配がする…") {
						@Override
						protected Book getContent1() {
							return new Book("BOSS戦へ進む") {

								@Override
								protected void work() {
									move();
								}
							};
						}

						@Override
						protected Book getContent2() {
							return new Book("まだ") {
								@Override
								protected void work() {
								}
							};
						}
					};
				}

				protected void getNextStairFromMidMap() {
					if (isBoss(MapList.getFloor())) {
						boss();
					} else {
						TaskOnMapObject.setTaskStairs(this);
					}
				}

				@Override
				public String[] getSlection() {
					if (isBoss(MapList.getFloor())) {
						return new String[] { "BOSS戦へ進む", "まだ" };
					} else {
						return super.getSlection();
					}
				}

				@Override
				public void move() {
					if (isBoss(MapList.getFloor())) {
						SE.SYSTEM_STAIR_STEP.play();
						BMR.goToBossMap();
					} else {
						super.move();
					}
				}

				@Override
				public boolean walkOnAction() {
					getNextStairFromMidMap();
					return false;
				}
			};
		}
	}

	@Override
	protected void addSpecialFloor(HashMap<Integer, Base_Map> hash) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (FloorConducter fc : FloorConducter.values()) {
			list.add(fc.floor);
		}
		for (int i = 0; i < getBossLength(); i++) {
			list.add(getXndBossFloor(i + 1));
		}
		Collections.sort(list);
		int count = 0;
		for (Integer integer : list) {
			hash.put(integer, new MiddMap(++count));
		}
		// hash.put(13,
		// });
		// hash.put(59, );
	}

	@Override
	public int defaultItemNumber() {
		int f = MapList.getFloor();
		if (f < FloorConducter.ゴーストナイトムーン.floor) {
			return new R().nextInt(3) + 2;
		} else if (f < FloorConducter.一条戻り橋.floor) {
			return new R().nextInt(3) + new R().nextInt(2) + 2;
		} else {
			return new R().nextInt(4) + 2;
		}
	}

	@Override
	public BGM getBGM() {
		return FloorConducter.get().bgm;
	}

	private int getBossLength() {
		return 4;
	}

	@Override
	public BossMap getBossMap() {
		int floor = MapList.getFloor();
		if (floor <= getXndBossFloor(1)) {
			return new BossMap_Daisakusen(this) {
				private static final long serialVersionUID = 1L;

				@Override
				protected Base_Artifact getStair() {
					return getNextStair();
				}
			};
		} else if (floor <= getXndBossFloor(2)) {
			return new BossMap_Sakasa(this) {
				private static final long serialVersionUID = 1L;

				@Override
				public void appearStair() {
					慧音の歴史書 a = new 慧音の歴史書(boss.getMassPoint());
					Checker.checkStatic(a);
					ItemFall.itemFall(a);
					super.appearStair();
				}

				@Override
				protected Base_Artifact getStair() {
					return getNextStair();
				}
			};
		} else if (floor <= getXndBossFloor(3)) {
			return new BossMap_Hisoutensoku(this) {
				private static final long serialVersionUID = 1L;

				@Override
				public void appearStair() {
					幻想郷縁起 a = new 幻想郷縁起(boss.getMassPoint());
					Checker.checkStatic(a);
					ItemFall.itemFall(a);
					super.appearStair();
				}

				@Override
				protected Base_Artifact getStair() {
					return getNextStair();
				}
			};
		} else if (floor <= getXndBossFloor(4)) {
			return new BossMap_Tokiko(this) {
				private static final long serialVersionUID = 1L;

				@Override
				public void appearStair() {
					自由人の狂想曲 a = new 自由人の狂想曲(boss.getMassPoint());
					Checker.checkStatic(a);
					ItemFall.itemFall(a);
					super.appearStair();
				}

			};
		}
		return super.getBossMap();
	}
	
	private Stairs getNextStair() {
		return getNextStair(MassCreater.getStairsIP());
	}

	private Stairs getNextStair(Point p) {
		return new Stairs(p, 七曜クエスト.this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void move() {
				PresentField.setPresentField(bm);
				NextFloor.next(bm);
			}
		};
	}

	private int getXndBossFloor(int index) {
		if (index == 1)
			return 13;
		else if (index == 2)
			return 59;
		else if (index == 3)
			return 73;
		else if (index == 4)
			return 98;
		else
			return -1;
	}

	@Override
	public String getClassName() {
		return "七曜クエスト";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		if (Player.me.getClassJob().getClass().equals(ClassDefault.class)) {
			list.add("クラスを選んで進むダンジョンよ");
			list.add("運命のワルツを利用して作ったわ");
			list.add("覚悟して挑むことね");
		} else {
			for (String string : Player.me.getClassJob().getSelectingExn()) {
				list.add(string);
			}
		}
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
		if (MapList.getFloor() <= FloorConducter.運命のワルツ.floor) {
			return 6;
		} else if (MapList.getFloor() > FloorConducter.星に願いを.floor) {
			return 2;
		} else {
			return 8;
		}
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
		return StoryManager.七曜クエストclear;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.七曜クエストok;
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
	protected boolean isBGMDemandedToPlay() {
		int f = MapList.getFloor();
		int floors[] = new int[FloorConducter.values().length];
		for (int i = 0; i < floors.length; i++) {
			if (f == FloorConducter.values()[i].floor + 1)
				return true;
		}
		return super.isBGMDemandedToPlay();
	}

	private boolean isBoss(int floor) {
		for (int i = 0; i < getBossLength(); i++) {
			if (getXndBossFloor(i + 1) == floor)
				return true;
		}
		return false;
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
