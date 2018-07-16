package dangeon.latest.scene.event.staffroll;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import main.constant.FR;
import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.util.CSVLoadSupporter;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import dangeon.latest.scene.Base_View;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.チルノのカード;
import dangeon.model.object.artifact.item.spellcard.堀川雷鼓のカード;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.enemy.Exルーミア;
import dangeon.model.object.creature.enemy.きもけーね;
import dangeon.model.object.creature.enemy.キスメ;
import dangeon.model.object.creature.enemy.チルノ;
import dangeon.model.object.creature.enemy.魅魔;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class StaffRoll_View extends Base_View {
	private class DAI_CHIRU {
		private BufferedImage DAI_CHIRU;
		double dx = 0;
		int x = FR.SCREEN_WIDTH, y = 100;

		public DAI_CHIRU() {
			DAI_CHIRU = FileReadSupporter.readImage("bg/chiruno_daichan2",
					"png");
		}

		public void draw(Graphics2D g) {
			dx -= 0.05;
			int w = DAI_CHIRU.getWidth();
			int h = DAI_CHIRU.getHeight();
			g.drawImage(DAI_CHIRU, x + (int) (dx), y, w / 2, h / 2, null);
		}
	}

	private class EnemyStaff extends Staff {
		Object imgs[] = new Object[3];

		private String arr[] = { "イラスト", "ドット", "アレンジ" };

		public EnemyStaff(int child, String[] arr, Object[] objs) {
			super(child, arr);
			imgs = objs;
		}

		@Override
		public void draw(Graphics2D g, int i) {
			int dx = 100;
			if (i != 2) {
				dx += 180;
				if (i == 1) {
					int dy = 0;
					dx -= ((BufferedImage) imgs[i]).getWidth() / 2 - 10;
					dy -= ((BufferedImage) imgs[i]).getHeight() / 2 - 10;
					g.drawImage(((BufferedImage) imgs[i]), x + dx, y(i - 1)
							+ dy, null);
				} else {
					g.drawImage(((BufferedImage) imgs[i]), x + dx, y(i - 1),
							100, h * 4, null);
				}
			}
			g.setColor(Color.GRAY);
			StringFilter.drawString(g, arr[i], x, y(i));
			if (i == 2) {
				g.setFont(small);
				g.setColor(Color.LIGHT_GRAY);
				StringFilter.drawString(g, "♪" + imgs[i].toString(),
						x + dx - 2, y(3) - 1);
				g.setFont(normal);
			}
			dx = 100;
			g.setColor(Color.WHITE);
			StringFilter.drawString(g, texts[i], x + dx, y(i));

		}

	}

	private class Staff {
		private final int END_CHILD;
		protected final int x = 10, y = FR.SCREEN_HEIGHT;
		protected double dy = 0;
		protected String[] texts;

		public Staff(int child, String... text) {
			texts = text;
			END_CHILD = child;
		}

		public Staff(String... texts) {
			this(CARD_END, texts);
		}

		public final int draw(Graphics2D g) {
			dy -= 1;
			// if (y + dy > FR.SCREEN_HEIGHT + h) {
			// return false;
			// }
			g.setColor(Color.WHITE);
			for (int i = 0; i < texts.length; i++) {
				draw(g, i);
			}
			if (y + dy < -h * 3) {
				return END_CHILD;
			} else {
				return CONTINUE;
			}

		}

		public void draw(Graphics2D g, int i) {
			StringFilter.drawString(g, texts[i], x, y(i));
		}

		public Staff setY(int i) {
			dy = +h * 5 * i;
			return this;
		}

		protected int y(int i) {
			return y + (int) (dy) + h * i;
		}
	}

	private DAI_CHIRU fairies = new DAI_CHIRU();

	private BufferedImage BACK;

	protected final int h = 15;

	private Font small = NormalFont.NORMALFONT.deriveFont(11f);
	private Font normal = NormalFont.NORMALFONT.deriveFont(15f);

	private final int CONTINUE = 0, CARD_END = 1, ALL_OVER = 3;

	private boolean flag_staff_end = false, flag_over = false;

	public final StaffRollCard TEXTS;

	private int count = -2;

	ArrayList<Staff> list = new ArrayList<Staff>();

	public StaffRoll_View() {
		BACK = FileReadSupporter.readImage("bg/ending_haike2", "png");
		TEXTS = new StaffRollCard();
		int max = 6;
		for (int i = 0; i < max; i++) {
			int index = i - max + 2;
			if (index == 1) {
				list.add(create(CARD_END, 1, "不思議の大冒険　チルノ見参２", "スタッフロール"));
			} else {
				list.add(create(CARD_END, index, ""));
			}
		}
	}

	@SuppressWarnings("unused")
	@Deprecated
	private Staff create(Base_Enemy e, int i) {
		Image_LargeCharacter imlc = e.getTatiE();
		BGM bgm = BGM.get2(e.getOriginalName());
		HashMap<String, Object> map = CSVLoadSupporter.getWiki(e
				.getOriginalName());
		BufferedImage imgs[] = new BufferedImage[2];
		int w = 100 * 2, h = this.h * 4 * 2;
		imgs[0] = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		{
			Graphics2D g = imgs[0].createGraphics();
			imlc.init();
			imlc.draw(g, 0, 0, w, h, true);
			g.dispose();
		}
		Image[][][] atks = e.getCharacterImage().ATK;
		Image atk = atks[0][5][atks[0][0].length - 1];
		imgs[1] = (BufferedImage) atk;
		Object objs[] = { imgs[0], imgs[1], bgm.TITLE };
		String[] arr = { imlc.getAuthor(), map.get("dot").toString(),
				bgm.AUTHOR };
		for (String string : arr) {
			if (string == null) {
				System.out.println(e);
				System.out.println();
			}
		}
		return new EnemyStaff(CARD_END, arr, objs).setY(i);
	}

	private Staff create(int child, int i, String... strings) {
		return new Staff(child, strings).setY(i);
	}

	private Staff create(SpellCard s, int i) {
		Image_LargeCharacter imlc = s.IMLC;
		BGM bgm = BGM.get(s.getClass());
		Base_Enemy e;
		if (s instanceof チルノのカード) {
			e = new チルノ(new Point(), 1);
		} else {
			try {
				Constructor<? extends Base_Enemy> con = s.getStand()
						.getConstructor(Point.class, int.class);
				e = con.newInstance(new Point(), 1);

			} catch (Exception ex) {
				return null;
			}
		}
		String name = e.getOriginalName();
		String dotter;
		try {
			HashMap<String, Object> map = CSVLoadSupporter.getWiki(name);
			dotter = map.get("dot").toString();
		} catch (Exception e2) {
			if (e instanceof Exルーミア || e instanceof きもけーね) {
				dotter = "ひろひろ";
			} else if (e instanceof キスメ) {
				dotter = "まるせん";
			} else if (e instanceof 魅魔) {
				dotter = "ほにゃー";
			} else {
				dotter = "かうざー";
			}
		}

		BufferedImage imgs[] = new BufferedImage[2];
		int w = 100 * 2, h = this.h * 4 * 2;
		imgs[0] = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		{
			Graphics2D g = imgs[0].createGraphics();
			imlc.init();
			imlc.draw(g, 0, 0, w, h, true);
			g.dispose();
		}
		if (s instanceof 堀川雷鼓のカード) {
			imgs[1] = (BufferedImage) CHARA_IMAGE.堀川雷鼓.getSPImage(0, 4);
		} else if (e instanceof CardAttackEffect) {
			((CardAttackEffect) e).setA(s);
			imgs[1] = (BufferedImage) e.getATKImage(0, DIRECTION.DOWN, 2);
		} else {
			Image[][][] atks = e.getCharacterImage().ATK;
			Image atk = atks[0][5][atks[0][0].length - 1];
			imgs[1] = (BufferedImage) atk;
		}
		if (bgm == null) {
			bgm = BGM.UTIL;
		}
		Object objs[] = { imgs[0], imgs[1], bgm.TITLE };
		String[] arr = { imlc.getAuthor(), dotter, bgm.AUTHOR };
		// for (String string : arr) {
		// if (string == null) {
		// System.out.println(e);
		// System.out.println();
		// }
		// }
		return new EnemyStaff(CARD_END, arr, objs).setY(i);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		g.drawImage(BACK, 0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT, null);
		g.setFont(normal);
		fairies.draw(g);
		int end = CONTINUE;
		for (Iterator<Staff> iterator = list.iterator(); iterator.hasNext();) {
			Staff staff = iterator.next();
			int check = staff.draw(g);
			if (check != CONTINUE) {
				iterator.remove();
				end = check;
			}
		}
		if (end != CONTINUE) {
			if (end == ALL_OVER) {
				((StaffRoll) PARENT_SCENE).end();
			} else if (flag_over) {
				return;
			} else if (!TEXTS.isEmpty()) {
				list.add(create(TEXTS.get(), 1));
			} else if (!flag_staff_end) {
				list.add(getStaffs().setY(1));
			} else {
				flag_over = true;
				list.add(create(ALL_OVER, 1, "See you next dungeon... ?"));
			}
		}
	}

	private Staff getStaffs() {
		switch (count++) {
		case 0:
			return new Staff("★★タイトル画像★★", "nabe_sin1");
		case 2:
			return new Staff("★★ストーリー１枚絵★★", "ぬぐぬ");
		case 4:
			return new Staff("★★UI協力★★", "ささ和え", "たいがー");
		case 6:
			return new Staff("★★状態異常アイコン★★", "かみねんど");
		case 8:
			return new Staff("★★マップチップ作成★★", "Dione");
		case 10:
			return new Staff("★★ダンシング・チルノ譜面作成★★", "Azel");
		case 12:
			return new Staff("★★PV作成★★", "titans");
		case 14:
			return new Staff("★★ジャケット作成★★", "バロン", "Azel");
		case 16:
			return new Staff("★★公式大会入賞者（流れ星の再来50F）★★", "【３位】16分34秒　アシカ",
					"【２位】16分12秒　aryoense");
		case 17:
			return new Staff("【１位】16分01秒　雷神ほねきち", "", "おめでとうございます！");
		case 19:
			return new Staff("★★プログラム補佐★★", "ロッチチ");
		case 21:
			return new Staff("★★プログラム/ストーリー/企画運営★★", "ウェレイ");
		case 23:
			return new Staff("★★スペシャルサンクス★★", "JUNYA", "にしかわ", "ナカニール石野");
		case 24:
			return new Staff("ニギラソ", "ござる", "渡辺", "ゆずぽんず");
		case 25:
			return new Staff("ずんだ", "", "大会に参加くださった方々", "ご意見くださった方々");
		case 26:
			return new Staff("", "", "", "そしてエンディングを見ているあなた");
		case 27:
			return new Staff("", "", "", "遊んでくれてありがとう！");
		case 30:
			flag_staff_end = true;
		}
		return new Staff("");
	}
}
