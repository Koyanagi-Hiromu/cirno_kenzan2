package dangeon.model.map.field.random;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.random.bossmap.BossMap_Hisoutensoku;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.map.field.special.map.EndingMap;
import dangeon.model.map.field.special.map.GouseiMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.scrool.幻想郷縁起;
import dangeon.model.object.creature.npc.Abstract_NPC;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.BaseClassJob;
import dangeon.model.object.creature.player.class_job.ClassDefault;
import dangeon.model.object.creature.player.class_job.Classひねくれ者;
import dangeon.model.object.creature.player.class_job.Classゆっくり;
import dangeon.model.object.creature.player.class_job.Classナイフマスター;
import dangeon.model.object.creature.player.class_job.Classフラワーマスター;
import dangeon.model.object.creature.player.class_job.Class人工太陽;
import dangeon.model.object.creature.player.class_job.Class人形使い;
import dangeon.model.object.creature.player.class_job.Class冒険家;
import dangeon.model.object.creature.player.class_job.Class半人半霊;
import dangeon.model.object.creature.player.class_job.Class守矢神;
import dangeon.model.object.creature.player.class_job.Class気分屋;
import dangeon.model.object.creature.player.class_job.Class蓬莱人形;
import dangeon.model.object.creature.player.class_job.Class風水師;
import dangeon.model.object.creature.player.class_job.クラス箱;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.R;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.View_Sider;
import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.res.Image_MapTip;
import main.res.SE;
import main.util.BeautifulView;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;

public class ミラクルクエスト extends Base_Map_Random {
	public class ClassJobLvUpNpc extends Abstract_NPC {

		private static final long serialVersionUID = 1L;
		private boolean flag_lvup = false;

		public ClassJobLvUpNpc(int x, int y) {
			super(Player.me.getClassJob().getCharaImage(), Player.me
					.getClassJob().className(), x, y, false);
			this.direction = DIRECTION.DOWN;
			// if(BonusConductor.神霊()){ }
		}

		protected void ask() {
			StringBuilder sb = new StringBuilder();
			sb.append("凍ったアイテムを消費してクラスレベルを上昇させますか？$" + Color.LIGHT_GRAY
					+ "（現在クラスレベル"
					+ 半角全角コンバーター.半角To全角数字(Player.me.getClassJob().getLv())
					+ "）");
			new ConvEvent(sb.toString()) {

				@Override
				protected String[] getExn() {
					return Player.me.getClassJob().getSelectingExn();
				}

				@Override
				protected Book getYes() {
					return new Book() {

						@Override
						protected void work() {
							createItemList();

						}
					};
				}

			};
		}

		private void createItemList() {
			ArrayList<Base_Artifact> escapse = Belongings
					.getDeepCopy(Belongings.getListItems());
			for (Iterator<Base_Artifact> iterator = escapse.iterator(); iterator
					.hasNext();) {
				Base_Artifact a = iterator.next();
				if (a.isCold() && !a.isEnchantedNow()) {
					iterator.remove();
				}
			}
			new Item_List("捧げる", new Book_Item() {
				@Override
				public void work(Base_Artifact a) {
					Belongings.remove(a);
					lvUpMessage();
				}
			}, escapse, Belongings.getListItems());
		}

		@Override
		protected void drawCreature(Graphics2D g, int x, int y) {
			if (flag_lvup) {
				super.drawCreature(g, x, y);
			} else {
				BeautifulView.setAlphaOnImg(g, 0.5f);
				super.drawCreature(g, x, y);
				BeautifulView.setAlphaOnImg(g, 1f);
			}
		}

		@Override
		public Image getImage() {
			if (flag_lvup) {
				return getATKImage(Player.me.getClassJob().getLv(),
						DIRECTION.DOWN, 2);
			} else {
				return IM.getWalkImage(Player.me.getClassJob().getLv(),
						DIRECTION.DOWN, 0);
			}
		}

		public void lvUp() {
			flag_lvup = true;
			SE.FANFARE2.play();
			ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
			Player.me.getClassJob().addLv(list);
			if (!list.isEmpty()) {
				Point p = getMassPoint().getLocation();
				p.translate(2, 0);
				MainMap.addEffect(new DoronEffect(p, null, true, false), false);
				MapList.addEnemy(new クラス箱(p, list));
			}
		}

		private void lvUpMessage() {
			Player.me.setDirection(DIRECTION.DOWN);
			Player.flag_clear = true;
			setTalks(Image_LargeCharacter.ANY);
			lvUp();
			ConvEvent cne = new ConvEvent() {
				@Override
				public void workAfterPush() {
					Player.flag_clear = false;
					Player.me.getClassJob().getBGM().play();
				}
			};
			StringBuilder sb = new StringBuilder();
			sb.append("クラスレベルが上がった！");
			new Conversation(Conversation.previous_opponent, cne, sb.toString());
		}

		@Override
		public void message() {
			if (!flag_lvup) {
				ask();
			}
		}

	}

	public class ClassJobNpc extends Abstract_NPC {
		private static final long serialVersionUID = 1L;

		public final BaseClassJob job;

		public final DIRECTION direction;

		private boolean selecting;

		public ClassJobNpc(BaseClassJob job, int x, int y, DIRECTION d) {
			super(job.getCharaImage(), job.className(), x, y, false);
			this.job = job;
			this.direction = d;
		}

		protected void ask() {
			selecting = true;
			StringBuilder sb = new StringBuilder();
			sb.append("クラス【");
			sb.append(job.className());
			sb.append("】を選択しますか？$");
			sb.append(job.getExn());
			new ConvEvent(sb.toString()) {

				@Override
				protected String[] getExn() {
					return job.getSelectingExn();
				}

				@Override
				protected Book getNo() {
					return new Book() {
						@Override
						protected void work() {
							selecting = false;
						}
					};
				}

				@Override
				protected Book getYes() {
					return new Book() {

						@Override
						protected void work() {
							Player.me.setDirection(DIRECTION.DOWN);
							setJob(job);

							selecting = false;
							Player.flag_clear = true;
							SE.FANFARE2.play();
							setTalks(Image_LargeCharacter.ANY);
							ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
							job.setSaisenListLv(0, list);
							if (!list.isEmpty()) {
								Point p = new Point(19, 15);
								MainMap.addEffect(new DoronEffect(p, null,
										true, false), false);
								MapList.addEnemy(new クラス箱(p, list));
							}
							ConvEvent cne = new ConvEvent() {
								@Override
								public void workAfterPush() {
									Player.flag_clear = false;
									job.getBGM().play();
								}
							};
							StringBuilder sb = new StringBuilder();
							sb.append("クラス【");
							sb.append(job.className());
							sb.append("】になった！");
							new Conversation(Conversation.previous_opponent,
									cne, sb.toString());
							if (!getStoryManager_ClearFlag().hasFinished()) {
								Message.set("クラスの説明は【情報】の【何階に何がある？】から確認できます@");
							}
						}

					};
				}

				@Override
				protected int pushCancelAction() {
					return 2;
				}

			};
		}

		private boolean check(Class<? extends BaseClassJob> clazz) {
			return Player.me.getClassJob().getClass().equals(clazz);
		}

		private boolean checkDefault() {
			return check(ClassDefault.class);
		}

		@Override
		protected void drawCreature(Graphics2D g, int x, int y) {
			if (check(job.getClass()) || selecting) {
				super.drawCreature(g, x, y);
			} else {
				BeautifulView.setAlphaOnImg(g, 0.5f);
				super.drawCreature(g, x, y);
				BeautifulView.setAlphaOnImg(g, 1f);
			}
		}

		@Override
		public Image getImage() {
			if (check(job.getClass())) {
				return getATKImage(LV, direction, 2);
			} else if (checkDefault()) {
				return IM.getWalkImage(LV, direction, 0);
			} else {
				return IM.getDamImage(LV, direction);
			}
		}

		@Override
		public void message() {
			if (checkDefault()) {
				ask();
			} else if (check(job.getClass())) {
				Message.set("【出現条件】", job.getSwitchExp());
			}
		}
	}

	private static final long serialVersionUID = 1L;

	private boolean flag_to_1f;

	public ミラクルクエスト() {
		this(Difficulty.Easy, 2, 0);
	}
	
	public ミラクルクエスト(Difficulty difficulty, int un_checked_level,
			Integer item_max) {
		super(difficulty, un_checked_level, item_max);
	}

	@Override
	protected void add(Base_MapObject... os) {
		for (Base_MapObject o : os) {
			list_object.add(o);
		}
	}

	@Override
	protected Base_Map createGouseiMap() {
		return new GouseiMap(this) {
			private static final long serialVersionUID = 1L;

			@Override
			protected AbstractList<Base_MapObject> getObjectList() {
				add(new ClassJobLvUpNpc(20, 12));
				return super.getObjectList();
			}
		};
	}

	@Override
	public int defaultItemNumber() {
		int f = MapList.getFloor();
		if (f > 99 || f == 1) {
			return 0;
		} else {
			if (Difficulty.Easy.is(this)) {
				return new R().nextInt(3) + new R().nextInt(3) + 3;
			} else if (Difficulty.Normal.is(this)) {
				return new R().nextInt(4) + 3;
			} else {
				return new R().nextInt(3) + new R().nextInt(2) + 2;
			}
		}
	}

	@Override
	public int getBelongingsMax() {
		int max = super.getBelongingsMax();
		if (BonusConductor.カリスマ_minus20()) {
			max -= 20;
		}
		return max;
	}

	@Override
	public BGM getBGM() {
		int f = MapList.getFloor();
		if (f == 1) {
			return BGM.The_boundary_of_the_world_wob;
		} else if (f >= 82) {
			if (f == 82)
				BGM.kanpyo_ch_bbcc.play();
			return BGM.kanpyo_ch_bbcc;
		} else if (f >= 75) {
			if (f == 75)
				BGM.The_boundary_of_the_world_wob.play();
			return BGM.The_boundary_of_the_world_wob;
		}
		return Player.me.getClassJob().getBGM();
	}

	@Override
	public String getClassName() {
		return "ミラクルクエスト";
	}

	@Override
	public Point getEntrancePoint() {
		return new Point(19, 13);
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		if (Player.me.getClassJob().getClass().equals(ClassDefault.class)) {
			list.add("クラスを選んで進むダンジョンだよ");
			list.add("好きなクラスを選んでね");
			list.add("スキマに着くと氷と引き換えに");
			list.add("クラスレベルを上げられるよ");
		} else {
			for (String string : Player.me.getClassJob().getSelectingExn()) {
				list.add(string);
			}
		}
	}

	@Override
	public String getFileName() {
		if (!isRandomField()) {
			return "res/map/specialMap/miracle.map";
		} else {
			return super.getFileName();
		}
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] { 14, 44, 74 };
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f == 1) {
			return Image_MapTip.stars;
		} else if (f < 9) {
			return Image_MapTip.stars;
		} else if (f < 19) {
			return Image_MapTip.命蓮寺;
		} else if (f < 28) {
			return Image_MapTip.マヨヒガ;
		} else if (f < 30) {
			return Image_MapTip.草原_秋;
		} else if (f < 34) {
			return Image_MapTip.地霊殿_水;
		} else if (f < 40) {
			return Image_MapTip.紅魔館;
		} else if (f < 44) {
			return Image_MapTip.地霊殿_水;
		} else if (f < 50) {
			return Image_MapTip.stars;
		} else if (f < 54) {
			return Image_MapTip.永遠亭;
		} else if (f < 59) {
			return Image_MapTip.命蓮寺;
		} else if (f < 65) {
			return Image_MapTip.木造校舎;
		} else if (f < 75) {
			return Image_MapTip.命蓮寺;
		} else if (f < 82) {
			return Image_MapTip.スキマ;
		} else if (f < 85) {
			return Image_MapTip.地霊殿_水;
		} else if (f < 90) {
			return Image_MapTip.紅魔館;
		} else if (f < 95) {
			return Image_MapTip.地霊殿_水;
		} else {
			return Image_MapTip.地霊殿;
		}
	}

	@Override
	public int getMaxFloor() {
		return 99;
	}

	@Override
	public int getMonsterHouse() {
		// if (Switch.test) {
		// return 1000;
		// }
		return super.getMonsterHouse();
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {
		add(new Stairs(new Point(19, 13)) {
			private static final long serialVersionUID = 1L;

			private void checkPlain() {
				if (Player.me.getClassJob().getClass()
						.equals(ClassDefault.class)) {
					View_Sider.setInformation("クラス【冒険家】になりました");
					Medal.ひねくれOK.save_the_more(1);
					setJob(new Class冒険家());
					Player.me.getClassJob().setSaisenListLv(0,
							new ArrayList<Base_Artifact>());
					Player.me.getClassJob().getBGM().play();
				}
				flag_to_1f = false;
			}

			@Override
			public void move() {
				checkPlain();
				super.move();
			}

			@Override
			public void saveEnd() {
				checkPlain();
				super.saveEnd();
			}
		});
		/**
		 * 　　早苗　　　　お空　　<br />
		 * 妹紅　　　　　　　　布都<br />
		 * 小石　　　　　　　　アリ<br />
		 * 妖夢　　　　　　　　ゆっ<br />
		 * 　　正邪　　　　優香<br />
		 */
		if (Medal.水路の上に乗ってワープした.hasFinished())
			add(new ClassJobNpc(new Class風水師(), 22, 13, DIRECTION.LEFT));
		if (Medal.人形使いOK.hasFinished())
			add(new ClassJobNpc(new Class人形使い(), 22, 14, DIRECTION.LEFT));
		if (Medal.ゆっくりに倒された.hasFinished())
			add(new ClassJobNpc(new Classゆっくり(), 22, 15, DIRECTION.LEFT));
		if (Medal.太陽の弱点を突いた.hasFinished())
			add(new ClassJobNpc(new Class人工太陽(), 20, 11, DIRECTION.DOWN));
		if (true)
			add(new ClassJobNpc(new Class守矢神(), 18, 11, DIRECTION.DOWN));
		if (Medal.ひねくれOK.hasFinished())
			add(new ClassJobNpc(new Classひねくれ者(), 18, 17, DIRECTION.UP));
		if (Medal.命の草から蓮の花を咲かせた.hasFinished())
			add(new ClassJobNpc(new Classフラワーマスター(), 20, 17, DIRECTION.UP));
		if (Medal.復活回数.getCount() > 9)
			add(new ClassJobNpc(new Class蓬莱人形(), 16, 13, DIRECTION.RIGHT));
		if (Medal.気分屋OK.hasFinished()) {
			add(new ClassJobNpc(new Class気分屋(), 16, 14, DIRECTION.RIGHT));
		} else if (new R().is(25)) {
			ClassJobNpc npc = new ClassJobNpc(new Class気分屋(), 16, 14,
					DIRECTION.RIGHT);
			npc.setCondition(CONDITION.透明, 0);
			add(npc);
		}
		if (Config.getKsg1Score() > 50)
			add(new ClassJobNpc(new Class半人半霊(), 16, 15, DIRECTION.RIGHT));
		if (Medal.ナイフをまとめて投げて敵を倒した.hasFinished())
			add(new ClassJobNpc(new Classナイフマスター(), 19, 17, DIRECTION.UP));
		flag_to_1f = true;
		return super.getObjectList();
	}

	@Override
	public int getSaisenParcent() {
		return 15;
	}

	@Override
	public int getShopParcent() {
		if (Difficulty.Hard.is(this)) {
			return super.getShopParcent() * 2 / 3;
		} else {
			return super.getShopParcent();
		}
	}

	@Override
	public int getSMH_Percent() {
		// if (Switch.test) {
		// return 1000;
		// }
		if (MapList.getFloor() < 80) {
			return SMH_NORMAL;
		} else {
			return 0;
		}
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.ミラクルクエストclear;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.ミラクルクエスト挑戦ok;
	}

	@Override
	public int getTrapDefaultValue() {
		return super.getTrapDefaultValue();
	}

	@Override
	public boolean isHaraheru() {
		return true;
	}

	@Override
	public boolean isLightful() {
		return false;
	}

	@Override
	public boolean isRandomField() {
		int f = MapList.getFloor();
		if (f == 1) {
			return false;
		} else {
			return super.isRandomField();
		}
	}

	@Override
	public boolean isWarningEnemy() {
		return false;
	}

	private void setJob(BaseClassJob job) {
		Checker.init(UN_CHECKED_LEVEL);
		Player.me.setClassJob(job);
		if (Class気分屋.class.isInstance(job)) {
			Medal.気分屋OK.save_the_more(1);
		}
	}
	
	@Override
	protected void addSpecialFloor(HashMap<Integer, Base_Map> hash) {
		hash.put(98, new EndingMap(this));
	}

	@Override
	public BossMap getBossMap() {
		return new BossMap_Hisoutensoku(this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void appearStair() {
				幻想郷縁起 a = new 幻想郷縁起(boss.getMassPoint());
				Checker.checkStatic(a);
				ItemFall.itemFall(a);
				super.appearStair();
			}
		};
	}
	
	@Override
	public boolean isHaraheranai()
	{
		return MapList.getFloor() == 1;
	}
}
