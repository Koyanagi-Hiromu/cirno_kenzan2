package dangeon.model.map.field.random;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.res.BGM;
import main.util.BlackOut;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.view_window.WindowFrame;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.StairScene;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.map.field.special.map.EndingMap;
import dangeon.model.map.field.special.map.GouseiMap;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.artifact.item.pot.かき氷器;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.spellcard.大妖精のカード;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.save.SaveLoad;
import dangeon.util.R;
import dangeon.view.util.StringFilter;

public abstract class Base_Map_Random extends Base_Map {
	public enum Difficulty {
		Phantasm(Color.GRAY, 4), Lunatic(Color.RED, 3), Hard(Color.ORANGE, 2), Normal(
				Color.PINK, 1), Easy(Color.CYAN, 0);
		public static Difficulty getFromIndex(int index) {
			for (Difficulty d : values()) {
				if (d.index == index) {
					return d;
				}
			}
			return null;
		}

		public final String COLOR;

		public final int index;

		private Difficulty(Color c, int index) {
			COLOR = c.toString();
			this.index = index;
		}

		public Difficulty get(int delt) {
			int i = index + delt;
			int max = StoryManager.あとがき.hasFinished() ? Phantasm.index
					: Lunatic.index;
			if (i < 0)
				i = 0;
			else if (i > max)
				i = max;
			for (Difficulty d : Difficulty.values()) {
				if (d.index == i) {
					return d;
				}
			}
			return null;
		}

		public boolean is(Base_Map_Random bmr) {
			return bmr.getDIFFICULTY().equals(this);
		}

		public boolean isMoreThan(Difficulty d) {
			return this.index > d.index;
		}

		@Override
		public String toString() {
			return COLOR.concat(super.toString())
					.concat(Color.WHITE.toString());
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected List<Integer> gold_saisen = new ArrayList<Integer>();
	protected List<Integer> gold_saisen_fix = new ArrayList<Integer>();

	private final Difficulty DIFFICULTY;

	/**
	 * 
	 * @param un_checked_level
	 * <br/>
	 *            0=>未識別なし（全識別）<br/>
	 *            1=>消費未識別（装備識別済み）<br/>
	 *            2=>装備も未識別（全未識別）<br/>
	 * 
	 */
	public final int UN_CHECKED_LEVEL;

	public final Integer ITEM_MAX;

	protected Base_Map_Random() {
		this(Difficulty.Normal, 2, null);
	}

	/**
	 * 
	 * @param un_checked_level
	 * <br/>
	 *            0=>未識別なし（全識別）<br/>
	 *            1=>消費未識別（装備識別済み）<br/>
	 *            2=>装備も未識別（全未識別）<br/>
	 * @param item_max
	 *            nullで無制限
	 * @see MassCreater
	 */
	protected Base_Map_Random(Difficulty difficulty, int un_checked_level,
			Integer item_max) {
		super("random", 650);
		DIFFICULTY = difficulty;
		UN_CHECKED_LEVEL = un_checked_level;
		ITEM_MAX = item_max;
	}

	@Override
	protected void add(Base_MapObject... os) {
		super.add(os);
	}

	/**
	 * to override
	 * 
	 * @param hash
	 */
	protected void addSpecialFloor(HashMap<Integer, Base_Map> hash) {
	}

	public void createFirstMap() {
		createFirstMap(false);
	}

	public void createFirstMap(final boolean flag_retry_no_change) {
		if (flag_retry_no_change) {
			R.setFix(Config.getRetrySeed());
			SaveLoad.staticTempLoad();
		} else {
			long seed = getFixedRandom();
			R.setFix(seed);
			Config.saveRetrySeed(seed);
			new SaveLoad().saveItemsFirst();
		}
		Player.me.resetStatus();
		Player.me.setRestrictionFlags(this);
		StairScene.addTask(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Enchant.allRemove();
				for (Base_Artifact a : initItems(Player.me.getMassPoint())) {
					if (a == null)
						continue;
					if (!Belongings.isMax()) {
						Belongings.setItems(a);
						if (Config.isEnchantedOnigiriFirst()) {
							if (a instanceof 大きなおにぎり) {
								Enchant.ANY2.setEnchant(a);
							} else if (a instanceof Ring) {
								Enchant.ANY1.setEnchant(a);
							}
						}
					}
					a.check();
				}
				Player.me.setDirection(DIRECTION.DOWN);
			}
		});
		new MassCreater(this, null, true).createFirstMap(UN_CHECKED_LEVEL);
	}

	protected Base_Map createGouseiMap() {
		return new GouseiMap(this);
	}

	public int dangerLv(int floor) {
		int array[] = getGouseiFloor();
		if (array.length == 0) {
			return 0;
		}
		for (int i = 0; i < array.length; i++) {
			if (floor < array[i]) {
				return i + Config.getFate() - 1;
			}
		}
		return array.length + Config.getFate() - 1;
	}

	public int expRate_From1To100() {
		return 100;
	}

	@Deprecated
	public ArrayList<Base_Artifact> firstItems() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public BGM getBGM() {
		if (MapList.getFloor() < 80) {
			return BGM.kanpyo_ch_sprite;
		} else {
			return BGM.kanpyo_ch_bbcc;
		}
	};

	public BossMap getBossMap() {
		return null;
	}

	@Override
	public String getCSVName() {
		StringBuilder sb = new StringBuilder();
		sb.append("csv/");
		sb.append(getClassName());
		sb.append("_");
		sb.append(getDIFFICULTY().name());
		sb.append(".csv");
		return sb.toString();
	}

	public Difficulty getDIFFICULTY() {
		return getDIFFICULTY(Config.getFate());
	}

	public Difficulty getDIFFICULTY(int fate) {
		return DIFFICULTY.get(fate);
	}

	@Override
	public Point getEntrancePoint() {
		return null;
	}

	/**
	 * ダンジョン概要を記述
	 */
	public String[] getExn() {
		String w = Color.WHITE.toString();
		String g = StringFilter.NUMBERS.toString();
		ArrayList<String> list = new ArrayList<String>(10);
		list.add(w + "◆" + getMapName() + "◆");
		String a = "全" + 半角全角コンバーター.半角To全角数字(getMaxFloor()) + "階";
		String b = w + "難易度：" + g + getDIFFICULTY();
		String s = "";
		if (getGouseiFloor().length == 0)
			s = "なし";
		else {
			for (int i : getGouseiFloor())
				s = s + String.valueOf(i) + ".5　";
			s = s.substring(0, s.length() - 1) + "階";
		}
		String c = w + "スキマフロア：" + g + s;
		if (ITEM_MAX == null) {
			s = g + "可能";
		} else if (ITEM_MAX == 0) {
			s = Color.ORANGE + "不可";
		} else {
			s = g + ITEM_MAX + "コまで";
		}
		String d = w + "持ち込み：" + s;
		s = isBossMap() ? "いる" : "いない";
		String e = w + "BOSS：" + g + s;
		list.add(a + WindowFrame.CENTERIZE + e);
		list.add(b + WindowFrame.CENTERIZE + d);
		list.add(c);
		list.add(Message.HORIZON);
		getExn_Warning(list);
		list.add("");
		return list.toArray(new String[0]);
	}

	protected void getExn_Warning(ArrayList<String> list) {
	}

	@Override
	public String getFileName() {
		StringBuilder sb = new StringBuilder();
		if (Player.me.isFlag_next_monster_house()) {
			SPECIAL_NUMBER = 0;
			sb.append("res/map/special_floor/");
			sb.append(SPECIAL_NUMBER);
			sb.append(".map");
			return sb.toString();
		} else if (isFixFloor()) {
			SPECIAL_NUMBER = new R().nextInt(SPECIAL_FLOOR);
			sb.append("res/map/special_floor/");
			sb.append(SPECIAL_NUMBER);
			sb.append(".map");
			return sb.toString();
		} else {
			SPECIAL_NUMBER = -1;
			sb.append(FILE_NAME);
			sb.append(new R().nextInt(RANDOM_LENGTH));
			sb.append(".map");
			return sb.toString();
		}
	}

	protected final Base_Map getFinalFloor() {
		return new EndingMap(this);
	}

	/**
	 * 
	 * @return -1 で通常ランダム -1以外で固定
	 */
	protected long getFixedRandom() {
		return System.nanoTime() / 1000000l;
	}

	public int[] getGoldFloor() {
		return getGouseiFloor();
	}

	/**
	 * @return 黄金の賽銭箱の出現確率 デフォルトで０％
	 */
	public int getGoldSaisenParcent() {
		return 0;
	}

	/**
	 * to override
	 * 
	 * @param hash
	 */
	public int[] getGouseiFloor() {
		return new int[] {};
	}

	@Override
	public int getHealRate() {
		if (this.DIFFICULTY.isMoreThan(Difficulty.Normal)) {
			int rate = 6 - dangerLv(MapList.getFloor());
			if (rate > 0)
				return super.getHealRate() * rate / 6;
			else
				return 60;
		} else {
			return super.getHealRate();
		}
	}

	@Override
	public int getMonsterHouse() {
		return 5;
	}

	@Override
	public int getShopParcent() {
		// 1F当たり２ページ落ちてる　→　平均１０ページ
		return 5;
	}

	@Override
	public final HashMap<Integer, Base_Map> getSpecialFloor() {
		HashMap<Integer, Base_Map> hash = new HashMap<Integer, Base_Map>();
		addSpecialFloor(hash);
		for (int i : getGouseiFloor()) {
			hash.put(i, createGouseiMap());
		}
		hash.put(getMaxFloor(), getFinalFloor());
		return hash;
	}

	public StoryManager getStoryManager_ClearFlag() {
		return null;
	}

	public StoryManager getStoryManager_FirstFlag() {
		return null;
	}

	@Override
	public int getTrapDefaultValue() {
		return getTrapDefaultValue(MapList.getFloor());
	}

	protected final int getTrapDefaultValue(int f) {
		int range, min;
		if (f < 6) {
			range = 1;
			min = 2;
			// ave:2
		} else if (f < 13) {
			range = 3;
			min = 2;
			// ave:3.5
		} else if (f < 20) {
			range = 3;
			min = 3;
			// ave:4.5
		} else {
			range = 4;
			min = 3;
			// ave:5
		}
		return new R().nextInt(range) + min;
	}

	/**
	 * BossMapがあるひと専用
	 */
	public void goToBossMap() {
		final BossMap map = getBossMap();
		new BlackOut("", new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				TaskOnMapObject.setNewMap(new MassCreater(map, false));
			}
		});
	}

	/**
	 * 大きなおにぎり以外にアイテムを持たせる場合over-ride
	 * 
	 * @return ダンジョンに入る時もらえるアイテム群
	 */
	protected Base_Artifact[] initItems(Point p) {
		Base_Artifact c;
		if (isDaichanCard()) {
			c = new 大妖精のカード(p);
			c.createSpellCard(false, 0);
		} else {
			c = null;
		}
		int size = 4 - Config.getFate();
		if (size <= 0)
			size = 1;
		return new Base_Artifact[] { c, new 大きなおにぎり(p), new かき氷器(p, size) };
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		return BGM.isBGMStatusStopped() || BGM.isSpecialBGMplaying()
				|| MapList.getFloor() == 1;
	}

	public boolean isBossMap() {
		return getBossMap() != null;
	}

	protected boolean isDaichanCard() {
		return true;
	}

	@Override
	public boolean isDangerous() {
		return dangerLv(MapList.getFloor()) > 0;
	}

	@Override
	public final boolean isDungeon() {
		return true;
	}

	public boolean isFixedRandom() {
		return getFixedRandom() != -1;
	}

	protected boolean isFixFloor() {
		return new R().nextInt(100) < 8;
	}

	public boolean isFixGoldSaisen() {
		int level = MapList.getFloor();
		if (level >= gold_saisen_fix.get(0) && level <= gold_saisen_fix.get(1)) {
			return true;
		}
		return false;
	}

	public boolean isGoldSaisenFloor() {
		int level = MapList.getFloor();
		int i = 0;
		while (true) {
			if (level >= gold_saisen.get(i) && level <= gold_saisen.get(i + 1)) {
				return true;
			}
			if (gold_saisen.size() < i + 3) {
				break;
			} else {
				i += 2;
			}
		}
		return false;
	}

	@Override
	public boolean isHaraheru() {
		return true;
	}

	@Override
	public boolean isInsane() {
		return dangerLv(MapList.getFloor()) > 2;
	}

	@Override
	public boolean isLightful() {
		return false;
	}

	public Base_Map returnTown() {
		return new FairyPlace();
	}

}
