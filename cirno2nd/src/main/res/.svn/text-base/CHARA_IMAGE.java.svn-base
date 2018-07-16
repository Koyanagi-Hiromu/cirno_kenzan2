package main.res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;

import main.thread.MainThread;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import dangeon.model.object.creature.Base_Creature;

public enum CHARA_IMAGE {
	arrow, 聖白蓮, 風見幽香, にとり, 犬走椛, 寅丸星, チルノ, 上白沢慧音, ゆっくり霊夢(0), 黄金ゆっくり(0), 博麗霊夢
	//
	, 四季映姫ヤマザナドゥ, アリス, 西行寺幽々子, 八坂神奈子, 小野塚小町, 八雲藍, 十六夜咲夜, 霊烏路空
	//
	, 霧雨魔理沙, 藤原妹紅, リグルナイトバグ, 封獣ぬえ, 星熊勇儀, ナズーリン, 魂魄妖夢, ルーミア, メディスン
	//
	, 八雲紫, 橙, 村紗水蜜, 射命丸文, 因幡てゐ, 紅美鈴, 洩矢諏訪子, 八意永琳, ミスティア, パチュリー, レティ
	//
	, 黒谷ヤマメ, 鍵山雛, 火焔猫燐, 鈴仙・優曇華院・イナバ, パルスィ, レミリア, キスメ, 古明地こいし, 古明地さとり

	, 東風谷早苗, 多々良小傘, 蓬莱山輝夜, フランドール, 永江衣玖, 物部布都, 幽谷響子, 霍青娥, 伊吹萃香, 豊聡耳神子

	, 小悪魔, 宮古芳香, リリーホワイト, 比那名居天子, 蘇我屠自古, 大妖精

	, 船, Exルーミア, 秦こころ, 赤蛮奇_体, 赤蛮奇_頭, 赤蛮奇, 今泉影狼, わかさぎ姫, 二ッ岩マミゾウ, 二ツ岩マミゾウ, 朱鷺子,

	きもけーね, 少名針妙丸, 姫海棠はたて, 堀川雷鼓, 鬼人正邪, 魅魔, 着物勇儀, 剣, 先代巫女;
	private static int MAX = 20;

	public static CHARA_IMAGE get(Class<? extends Base_Creature> c) {
		String[] pack = { "dangeon.model.object.creature.enemy.",
				"dangeon.model.object.creature.npc.",
				"dangeon.model.object.creature.player." };
		label1: for (CHARA_IMAGE ci : values()) {
			label2: for (String str : pack) {
				try {
					Class<?> clazz = Class.forName(str.concat(ci.name()));
					if (clazz.equals(c)) {
						ci.load();
						return ci;
					} else {
						continue label1;
					}
				} catch (ClassNotFoundException e) {
					continue label2;
				}
			}
		}
		return arrow;
	}

	public static int getKoma_StaticMethod(float speed) {
		int koma;
		if (speed == 0) {
			return 1;
		} else {
			koma = (int) (6 / speed);
		}
		koma = MainThread.getFrame() / koma % 4;
		if (koma == 3) {
			return 1;
		}
		return koma;
	}

	public static void setMaxCharaLoadind() {
		MAX = 70;
	}

	private final int static_koma;

	private static ArrayList<CHARA_IMAGE> list = new ArrayList<CHARA_IMAGE>(MAX);

	/*
	 * テスト用　byロッチチ
	 */
	private BufferedImage[] image_walk = new BufferedImage[3];

	public Image[][][] WALK, ATK, DAM, SP;

	private int[] COMA = new int[4], DIR = new int[4];

	private CHARA_IMAGE() {
		this(1);
	}

	private CHARA_IMAGE(int koma) {
		static_koma = koma;
	}

	private BufferedImage get(BufferedImage[] BI, int i, int lv, int dir,
			int coma) {
		if (BI[i] == null) {
			return null;
		}
		if (lv == 4)
			lv = 0;
		int w = BI[i].getWidth() / DIR[i];
		int h = BI[i].getHeight() / 4 / COMA[i];
		int x = w * dir;
		int y = h * (lv * COMA[i] + coma);
		return BI[i].getSubimage(x, y, w, h);
	}

	public Image getATKImage(int lv, DIRECTION direction, int k) {
		load();
		if (lv == 4)
			lv = 0;
		return ATK[lv][direction.getRotate()][k];
	}

	public Image getDamImage(int lv, DIRECTION direction) {
		load();
		if (lv == 4)
			lv = 0;
		return DAM[lv][direction.getRotate()][0];
	}

	public int getKoma(float speed) {
		if (speed == 0) {
			return static_koma;
		} else {
			return getKoma_StaticMethod(speed);
		}
	}

	public Image getSPImage(int lv, DIRECTION direction, int coma) {
		load();
		if (lv == 4)
			lv = 0;
		if (SP[lv].length == 1) {
			return SP[lv][0][coma];
		} else if (SP[lv][direction.getRotate()][coma] != null) {
			return SP[lv][direction.getRotate()][coma];
		} else {
			return getATKImage(lv, direction, coma);
		}
	}

	public Image getSPImage(int lv, int coma) {
		load();
		if (lv == 4)
			lv = 0;
		return SP[lv][0][coma];
	}

	/*
	 * テスト用　byロッチチ
	 */
	public BufferedImage getWalkDot(int i) {
		if (image_walk[i] == null) {
			return 聖白蓮.image_walk[i];
		}
		return image_walk[i];
	}

	/**
	 * 
	 * @param lv
	 * @param direction
	 * @param speed
	 *            0:停止　0.5:鈍足　1:等速　2:倍速　3:３倍速
	 * @return
	 */
	public Image getWalkImage(int lv, DIRECTION direction, float speed) {
		if (lv == 4)
			lv = 0;
		load();
		return WALK[lv][direction.getRotate()][getKoma(speed)];
	}

	private void init() {
		if (this == 剣) {
			System.out.print(false);
		}
		if (this == 二ツ岩マミゾウ) {
			return;
		}
		String[] filename = { "walk", "atk", "dam", "spec" };
		Properties PROP = new Properties();
		try {
			// InputStream inputStream = FileReadSupporter.readURL(
			// pass("dot.properties")).openStream();
			PROP.load(FileReadSupporter.readUTF8(pass("dot.properties")));
			// inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		final BufferedImage[] BI = new BufferedImage[4];
		for (int i = 0; i < filename.length; i++) {
			String str;
			str = PROP.getProperty(filename[i]);
			if (str == null) {
				COMA[i] = 1;
			} else {
				COMA[i] = Integer.valueOf(str);
			}
			str = PROP.getProperty(filename[i].concat("_dir"));
			if (str == null) {
				DIR[i] = 1;
			} else {
				DIR[i] = Integer.valueOf(str);
			}
			if (str != null) {
				BI[i] = loadImage(filename[i]);
			} else {
				BI[i] = null;
			}
		}
		PROP.clear();
		BufferedImage im[][][][] = new BufferedImage[filename.length][][][];
		int LV = 3 + 1;
		for (int i = 0; i < filename.length; i++) {
			im[i] = new BufferedImage[LV][DIR[i]][COMA[i]];
			boolean null_flag = true;
			for (int lv = 0; lv < LV; lv++) {
				for (int dir = 0; dir < DIR[i]; dir++) {
					for (int coma = 0; coma < COMA[i]; coma++) {
						im[i][lv][dir][coma] = get(BI, i, lv, dir, coma);
						null_flag = null_flag && im[i][lv][dir][coma] == null;
					}
				}
			}
			if (null_flag) {
				im[i] = null;
			}
		}
		WALK = im[0];
		ATK = im[1];
		DAM = im[2];
		SP = im[3];
		im = null;
		// テスト用
		// for (int i = 0; i <= 2; i++) {
		// image_walk[i] = get(BI, 0, i + 1, 5, 0);
		// }
	}

	public void load() {
		if (list.contains(this)) {
			list.remove(this);
			list.add(this);
			return;
		} else {
			list.add(this);
			// FIXME 本当にコメントアウトでいいのか？
			// if (list.size() > MAX) {
			// System.out.println("CHARA_IMAGE : MAX");
			// list.get(0).reset();
			// list.remove(0);
			// }
			init();
		}
	}

	private BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(FileReadSupporter.readURL(pass(filename
					.concat(".png"))));
		} catch (IOException e) {
			return null;
		}
	}

	private String pass(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/image/character_dot/");
		sb.append(name());
		sb.append("/");
		sb.append(name);
		return sb.toString();
	}

	private void reset() {
		WALK = null;
		ATK = null;
		DAM = null;
		SP = null;
	}

}
