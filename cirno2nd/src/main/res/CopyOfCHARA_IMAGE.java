package main.res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import load.Loading;
import main.thread.MainThread;
import main.util.DIRECTION;
import dangeon.model.object.creature.Base_Creature;

public enum CopyOfCHARA_IMAGE {
	arrow("Arrow1", "Arrow2", "Arrow3", "Arrow", new Special("atk", 3)), 聖白蓮(
			"chara_hijiri_l1_", "chara_hijiri_l2_", "chara_hijiri_l3_",
			"chara_hijiri_npc_", new Special("burst", 3)), 風見幽香("enm1", "enm2",
			"enm3", "幽香", new Special("burst", 1)), にとり("にとり_enm1_",
			"にとり_enm2_", "にとり_enm3_", "にとり", new Special("特殊", 2, true)), 犬走椛(
			"椛1", "椛2", "椛3", "椛"), 寅丸星("星_npc_", "", new Special("sp", 3)), チルノ(
			"chil_enm1_", "chil_enm2_", "chil_enm3_", "chil"), 上白沢慧音("慧音enm1_",
			"慧音enm2_", "慧音enm3_", "慧音npc_", new Special("sp", 9, true)), ゆっくり霊夢(
			"chara_yukkuri_lv1_", "chara_yukkuri_lv2_", "chara_yukkuri_lv3_",
			"chara_yukkuri_npc_"), 黄金ゆっくり("chara_yukkuri2_lv1_",
			"chara_yukkuri2_lv2_", "chara_yukkuri2_lv3_", "chara_yukkuri_npc_"), 博麗霊夢(
			"", "１"), 四季映姫ヤマザナドゥ("eiki_enm3_", "eiki_enm2_", "eiki_enm1_",
			"eiki_npc_"), アリス("アリス", "１"), 西行寺幽々子("幽々子1", "幽々子2", "幽々子3",
			"幽々子", new Special("sp", 3)), 八坂神奈子("chara_kanako_lv1_",
			"chara_kanako_lv2_", "chara_kanako_lv3_", "chara_kanako_npc_",
			new Special("burst_", 3)), 小野塚小町("komachi_enm1_", "komachi_enm2_",
			"komachi_enm3_", "komachi_npc_"), 八雲藍("藍", "藍", "藍", "藍",
			new Special("atk", 4)), 十六夜咲夜("sakuya_enm1_", "sakuya_enm2_",
			"sakuya_enm3_", "sakuya_"), 霊烏路空("oku_enm1_", "oku_enm2_",
			"oku_enm3_", "oku_npc_", new Special("special", 5)), 霧雨魔理沙(
			"魔理沙_enm1_", "魔理沙_enm2_", "魔理沙_enm3_", "魔理沙_npc_", new Special(
					"特殊", 4)), 藤原妹紅("moko", "１"), リグルナイトバグ("riguru1",
			"riguru2", "riguru3", "riguru"), 封獣ぬえ("ぬえ_enm1_", "ぬえ_enm2_",
			"ぬえ_enm3_", "ぬえ_npc_"), 星熊勇儀("勇儀_enm1_", "勇儀_enm2_", "勇儀_enm3_",
			"勇儀_npc_"), ナズーリン("Nazrin_enm1_", "Nazrin_enm2_", "Nazrin_enm3_",
			"Nazrin_npc_"), 魂魄妖夢("youmu_enm1_", "youmu_enm2_", "youmu_enm3_",
			"youmu_npc_", new Special("sp", 3)), ルーミア("ルーミア_enm1_",
			"ルーミア_enm2_", "ルーミア_enm3_", "ルーミア_npc_"), メディスン("medicine", "攻"), 八雲紫(
			"chara_yukari_lv1_", "chara_yukari_lv2_", "chara_yukari_lv3_",
			"chara_yukari_npc_"), 橙("橙", "橙", "橙", "橙"), 村紗水蜜("村沙_enm1_",
			"村沙_enm2_", "村沙_enm3_", "村沙_npc_", new Special("sp", 3)), 射命丸文(
			"ayayaya_enm1_", "ayayaya_enm2_", "ayayaya_enm3_", "ayayaya_npc_"), 因幡てゐ(
			"tei_npc_", ""), 紅美鈴("China_matome+_enm1_", "China_matome+_enm2_",
			"China_matome+_enm3_", "China_matome+_npc_"), 洩矢諏訪子("諏訪子_npc_", "歩"), 八意永琳(
			"chara_eirin_npc_", "歩")
	// , ミスティア("Mistia", "１")
	, パチュリー("", "", "", "")
	// , レティ("レティ", "歩"), 黒谷ヤマメ("chara_yamame_npc_", "歩")
	, 鍵山雛("雛_npc_", "")
	// , パルスィ("Parsee_Matome_npc_", "")
	;
	public static CopyOfCHARA_IMAGE get(Class<? extends Base_Creature> c) {
		String[] pack = { "dangeon.model.object.creature.enemy.",
				"dangeon.model.object.creature.npc.",
				"dangeon.model.object.creature.player." };
		label1: for (CopyOfCHARA_IMAGE ci : values()) {
			label2: for (String str : pack) {
				try {
					Class<?> clazz = Class.forName(str.concat(ci.name()));
					if (clazz.equals(c)) {
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

	private final Image[][][] WALK, ATK, DAM;
	public final boolean LV;
	private Image[][][] SP;

	public static ArrayList<CopyOfCHARA_IMAGE> load_task = new ArrayList<CopyOfCHARA_IMAGE>();

	public static int getKoma(float speed) {
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

	private Special SPECIAL = null;

	/**
	 * 
	 * @param enm1
	 * @param enm2
	 * @param enm3
	 * @param npc
	 * @param level
	 *            歩　１　攻
	 */
	private CopyOfCHARA_IMAGE(String npc, String level) {
		LV = true;
		Loading.setStr(this);
		long time = System.nanoTime();
		int walk_anime = 3;
		int atk_anime = 3;
		int dam_anime = 1;
		String name[] = { npc };
		WALK = new Image[4][8][walk_anime];
		SP = WALK;
		if (level.matches("歩")) {
			ATK = null;
			DAM = WALK;
			for (int c = 0; c < name.length; c++) {
				for (DIRECTION d : DIRECTION.values()) {
					if (d == DIRECTION.NEUTRAL) {
						continue;
					}
					for (int i = 0; i < walk_anime; i++) {
						WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c], d.NUM, i));
						if (WALK[c][d.getIndexFrom0ExceptNeautral()][i] == null) {
							WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getArrowURL(
									c, name[c], d.NUM, i));
						}
					}
				}
			}
		} else if (level.matches("")) {
			ATK = new Image[4][8][atk_anime];
			for (int c = 0; c < name.length; c++) {
				for (DIRECTION d : DIRECTION.values()) {
					if (d == DIRECTION.NEUTRAL) {
						continue;
					}
					for (int i = 0; i < atk_anime; i++) {
						ATK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c].concat("atk"), d.NUM, i));
					}
				}
			}
			DAM = new Image[4][8][dam_anime];
			for (int c = 0; c < name.length; c++) {
				for (DIRECTION d : DIRECTION.values()) {
					if (d == DIRECTION.NEUTRAL) {
						continue;
					}
					for (int i = 0; i < walk_anime; i++) {
						WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c], d.NUM, i));
						if (WALK[c][d.getIndexFrom0ExceptNeautral()][i] == null) {
							WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getArrowURL(
									c, name[c], d.NUM, i));
						}
					}
					for (int i = 0; i < dam_anime; i++) {
						DAM[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c].concat("dam"), d.NUM));
					}
				}
			}
		} else if (level.matches("被")) {
			ATK = null;
			DAM = new Image[4][8][dam_anime];
			for (int c = 0; c < name.length; c++) {
				for (DIRECTION d : DIRECTION.values()) {
					if (d == DIRECTION.NEUTRAL) {
						continue;
					}
					for (int i = 0; i < walk_anime; i++) {
						WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c], d.NUM, i));
						if (WALK[c][d.getIndexFrom0ExceptNeautral()][i] == null) {
							WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getArrowURL(
									c, name[c], d.NUM, i));
						}
					}
					for (int i = 0; i < dam_anime; i++) {
						DAM[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c].concat("dam"), d.NUM));
					}
				}
			}
		} else {
			if (level.matches("１")) {
				ATK = null;
			} else if (level.matches("攻")) {
				ATK = new Image[4][8][atk_anime];
				for (int c = 0; c < name.length; c++) {
					for (DIRECTION d : DIRECTION.values()) {
						if (d == DIRECTION.NEUTRAL) {
							continue;
						}
						for (int i = 0; i < atk_anime; i++) {
							ATK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
									c, name[c].concat("atk"), d.NUM, i));
						}
					}
				}
			} else {
				ATK = null;
			}
			DAM = new Image[4][8][dam_anime];
			for (int c = 0; c < name.length; c++) {
				for (DIRECTION d : DIRECTION.values()) {
					if (d == DIRECTION.NEUTRAL) {
						continue;
					}
					for (int i = 0; i < walk_anime; i++) {
						WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c], d.NUM, i));
						if (WALK[c][d.getIndexFrom0ExceptNeautral()][i] == null) {
							WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getArrowURL(
									c, name[c], d.NUM, i));
						}
					}
					for (int i = 0; i < dam_anime; i++) {
						DAM[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c].concat("dam"), 2));
					}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			WALK[i] = WALK[0];
			if (ATK != null) {
				ATK[i] = ATK[0];
			}
			if (DAM != null) {
				DAM[i] = DAM[0];
			}
		}
		// System.out.print(name());
		// System.out.print("loaded : ");
		// System.out.print((System.nanoTime() - time) / 1000000);
		// System.out.println("[ms]");
	}

	private CopyOfCHARA_IMAGE(String npc, String level, Special sp) {
		this(npc, level);
		initSP(npc, npc, npc, npc, sp);

	}

	private CopyOfCHARA_IMAGE(String enm1, String enm2, String enm3, String npc) {
		LV = false;
		Loading.setStr(this);
		long time = System.nanoTime();
		int walk_anime = 3;
		int atk_anime = 3;
		int dam_anime = 1;
		WALK = new Image[4][8][walk_anime];
		ATK = new Image[4][8][atk_anime];
		DAM = new Image[4][8][dam_anime];
		SP = WALK;
		String name[] = { npc, enm1, enm2, enm3 };
		for (int c = 0; c < name.length; c++) {
			for (DIRECTION d : DIRECTION.values()) {
				if (d == DIRECTION.NEUTRAL) {
					continue;
				}
				for (int i = 0; i < walk_anime; i++) {
					WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
							c, name[c], d.NUM, i));
					if (WALK[c][d.getIndexFrom0ExceptNeautral()][i] == null) {
						WALK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getArrowURL(
								c, name[c], d.NUM, i));
					}
				}
				for (int i = 0; i < atk_anime; i++) {
					ATK[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
							c, name[c].concat("atk"), d.NUM, i));
					if (ATK[c][d.getIndexFrom0ExceptNeautral()][i] == null) {
						ATK[c][d.getIndexFrom0ExceptNeautral()][i] = WALK[c][d
								.getIndexFrom0ExceptNeautral()][i];
					}
				}
				DAM[c][d.getIndexFrom0ExceptNeautral()][0] = loadImage(getURL(
						c, name[c].concat("dam"), d.NUM));
			}
			if (DAM[c][DIRECTION.DOWN.getIndexFrom0ExceptNeautral()][0] == null) {
				for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
					DAM[c][DIRECTION.DOWN.getIndexFrom0ExceptNeautral()][0] = WALK[c][d
							.getIndexFrom0ExceptNeautral()][0];
				}
			} else {
				for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
					if (DAM[c][d.getIndexFrom0ExceptNeautral()][0] == null) {
						DAM[c][d.getIndexFrom0ExceptNeautral()][0] = DAM[c][DIRECTION.DOWN
								.getIndexFrom0ExceptNeautral()][0];
					}
				}
			}
		}
		// System.out.print(name());
		// System.out.print("loaded : ");
		// System.out.print((System.nanoTime() - time) / 1000000);
		// System.out.println("[ms]");
	}

	private CopyOfCHARA_IMAGE(String enm1, String enm2, String enm3,
			String npc, Special sp) {
		this(enm1, enm2, enm3, npc);
		initSP(enm1, enm2, enm3, npc, sp);
	}

	private String getArrowURL(int c, String name, int i, int j) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/image/SmallCharacter/arrow/");
		if (c == 0) {
			sb.append("npc/");
		} else {
			sb.append("enm");
			sb.append(c);
			sb.append("/Arrow");
			sb.append(c);
		}
		sb.append(i);
		sb.append("_");
		sb.append(j + 1);
		sb.append(".png");
		return sb.toString();
	}

	// private Image[] WALK_IMAGE, ATK_IMAGE, DAM_IMAGE;
	//
	// private CHARA_IMAGE(String url) {
	// // FIX ME ８方向だから８＊３にするべき
	// int l = 9 * 3;
	// WALK_IMAGE = new Image[l];
	// Image[] atk = new Image[l];
	// Image[] dam = new Image[9];
	//
	// for (int i = 1; i <= 9; i++) {
	// for (int j = 0; j < 3; j++) {
	// WALK_IMAGE[3 * (i - 1) + j] = loadImage(getURL(url, i, j));
	// atk[3 * (i - 1) + j] = loadImage(getURL(url.concat("atk"), i, j));
	// }
	// }
	// for (int i = 0; i < dam.length; i++) {
	// dam[i] = loadImage(getURL(url.concat("dam"), i));
	// }
	// for (int i = 0; i < l; i++) {
	// if (WALK_IMAGE[i] == null) {
	// WALK_IMAGE[i] = getSubsutitute();
	// }
	// }
	// boolean flag;
	// flag = false;
	// for (int i = 0; i < l; i++) {
	// if (flag = atk[i] != null) {
	// break;
	// }
	// }
	// if (flag) {
	// for (int i = 0; i < l; i++) {
	// if (atk[i] == null) {
	// atk[i] = getSubsutitute();
	// }
	// }
	// ATK_IMAGE = atk;
	// } else {
	// ATK_IMAGE = null;
	// }
	// int count = 0;
	// for (int i = 0; i < dam.length; i++) {
	// if (dam[i] != null) {
	// count++;
	// }
	// }
	// if (count == 1) {
	// Image[] d = new Image[1];
	// for (int i = 0; i < dam.length; i++) {
	// if (dam[i] != null) {
	// d[0] = dam[i];
	// break;
	// }
	// }
	// DAM_IMAGE = d;
	// } else if (count > 1) {
	// for (int i = 0; i < dam.length; i++) {
	// if (dam[i] == null) {
	// dam[i] = getSubsutitute();
	// }
	// }
	// DAM_IMAGE = dam;
	// } else {
	// DAM_IMAGE = null;
	// }
	// System.out.println("load : " + name());
	// }
	//
	// private CHARA_IMAGE() {
	// this("");
	// }
	//
	// private CHARA_IMAGE(String url, String sp, String... strings) {
	// this(url);
	// Image[] spc = new Image[9];
	// for (int i = 0; i < spc.length; i++) {
	// spc[i] = loadImage(getURL(url.concat(sp), i));
	// }
	// SPECIAL_IMAGE = spc;
	// }

	public Image getATKImage(int lv, DIRECTION direction, int k) {
		if (ATK == null) {
			return WALK[lv][direction.getIndexFrom0ExceptNeautral()][k];
		}
		return ATK[lv][direction.getIndexFrom0ExceptNeautral()][k];
	}

	public Image getDamImage(int lv, DIRECTION direction) {
		return DAM[lv][direction.getIndexFrom0ExceptNeautral()][0];
	}

	public Special getSPImage() {
		return SPECIAL;
	}

	public Image getSPImage(DIRECTION direction) {
		return SP[1][direction.getIndexFrom0ExceptNeautral()][0];
	}

	public Image getSPImage(int lv, DIRECTION direction) {
		return SP[lv][direction.getIndexFrom0ExceptNeautral()][0];
	}

	public Image getSPImage(int lv, DIRECTION direction, int koma) {
		if (SP[lv][direction.getIndexFrom0ExceptNeautral()][koma] != null) {
			return SP[lv][direction.getIndexFrom0ExceptNeautral()][koma];
		} else {
			return getATKImage(lv, direction, koma);
		}
	}

	public Image getSPImage(int lv, int coma) {
		return SP[lv][0][coma];
	}

	private String getURL(int c, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/image/SmallCharacter/");
		sb.append(name());
		sb.append("/");
		if (c == 0) {
			sb.append("npc/");
		} else {
			sb.append("enm");
			sb.append(c);
			sb.append("/");
		}
		sb.append(name);
		sb.append(".png");
		return sb.toString();
	}

	private String getURL(int c, String name, int i) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/image/SmallCharacter/");
		sb.append(name());
		sb.append("/");
		if (c == 0) {
			sb.append("npc/");
		} else {
			sb.append("enm");
			sb.append(c);
			sb.append("/");
		}
		sb.append(name);
		sb.append(i);
		sb.append(".png");
		return sb.toString();
	}

	private String getURL(int c, String name, int i, int j) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/image/SmallCharacter/");
		sb.append(name());
		sb.append("/");
		if (c == 0) {
			sb.append("npc/");
		} else {
			sb.append("enm");
			sb.append(c);
			sb.append("/");
		}
		sb.append(name);
		sb.append(i);
		sb.append("_");
		sb.append(j + 1);
		sb.append(".png");
		return sb.toString();
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
		return WALK[lv][direction.getIndexFrom0ExceptNeautral()][getKoma(speed)];
	}

	public Image getWalkImage(int lv, DIRECTION direction, int i) {
		return WALK[lv][direction.getIndexFrom0ExceptNeautral()][i];
	}

	private void initSP(String enm1, String enm2, String enm3, String npc,
			Special sp) {
		SPECIAL = sp;
		if (sp.DIRECTION_ONLY_2) {
			initSP_Only1(enm1, enm2, enm3, npc, sp);
			return;
		}
		SP = new Image[4][8][sp.LENGTH];
		String name[] = { npc, enm1, enm2, enm3 };
		for (int c = 0; c < name.length; c++) {
			if (sp.LENGTH == 1) {
				for (DIRECTION d : DIRECTION.values()) {
					if (d == DIRECTION.NEUTRAL) {
						continue;
					}
					SP[c][d.getIndexFrom0ExceptNeautral()][0] = loadImage(getURL(
							c, name[c].concat(sp.NAME), d.NUM));
				}
			} else {
				for (int i = 0; i < sp.LENGTH; i++) {
					for (DIRECTION d : DIRECTION.values()) {
						if (d == DIRECTION.NEUTRAL) {
							continue;
						}
						SP[c][d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
								c, name[c].concat(sp.NAME), d.NUM, i));
					}
				}
			}
		}
	}

	private void initSP_Only1(String enm1, String enm2, String enm3,
			String npc, Special sp) {
		SP = new Image[4][1][sp.LENGTH];
		String name[] = { npc, enm1, enm2, enm3 };
		for (int c = 0; c < name.length; c++) {
			if (sp.LENGTH == 1) {
				SP[c][0][0] = loadImage(getURL(c, name[c].concat(sp.NAME)));
			} else {
				for (int i = 0; i < sp.LENGTH; i++) {
					SP[c][0][i] = loadImage(getURL(c, name[c].concat(sp.NAME),
							i + 1));
				}
			}
		}
	}

	public boolean isSPImage() {
		return SPECIAL != null;
	}

	private BufferedImage loadImage(String filename) {

		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			return null;
		}
	}

}
