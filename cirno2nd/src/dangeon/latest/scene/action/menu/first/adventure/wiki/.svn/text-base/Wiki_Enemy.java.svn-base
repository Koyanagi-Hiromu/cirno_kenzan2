package dangeon.latest.scene.action.menu.first.adventure.wiki;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import main.Listener.ACTION;
import main.res.SE;
import main.util.CSVLoadSupporter;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.latest.util.view_window.WindowFrame;
import dangeon.model.config.Config;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.util.STAGE;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Wiki_Enemy extends Base_Scene {
	private final Base_Scene PREV;
	public final StringOnlyWindow WINDOW;
	public final Base_Enemy ENEMY;
	public int frame;
	private final boolean flag_detail;

	public Wiki_Enemy(KeyHolder kh, Base_View bv, Base_Enemy e, int index,
			int max, String title, int lv) {
		super(kh, new Wiki_Enemy_View());
		flag_detail = Config.isAccessableToDetail(e, lv);
		PREV = bv.PARENT_SCENE;
		ENEMY = e;
		while (ENEMY.isAnimating())
			ENEMY.upDate();
		ENEMY.setDamaging(false);
		ENEMY.setDirection(DIRECTION.DOWN);
		ArrayList<String> list = new ArrayList<String>();
		if (index < 0 || max <= 0) {
			add(list, title);
		} else {
			String zero = index < 10 ? "0" : "";
			String zero_ = max < 10 ? "0" : "";
			StringBuilder sb = new StringBuilder(WindowFrame.TAIL);
			for (Iterator<STAGE> iterator = ENEMY.getCategory().iterator(); iterator
					.hasNext();) {
				STAGE stage = iterator.next();
				sb.append(stage.ONE_NAME);
				sb.append("＆");
			}
			if (!ENEMY.getCategory().isEmpty())
				sb.deleteCharAt(sb.length() - 1);
			sb.append(" ");
			add(list, title, "(", zero, index, "/", zero_, max, ")",
					sb.toString());
		}
		add(list, "");
		add(list, "");
		add(list, e.getColoredName(), WindowFrame.TAIL,
				半角全角コンバーター.半角To全角数字(Config.getKONumber(e, lv)), "倒した ");
		add(list, Message.HORIZON);
		{
			add(list, "ＨＰ：", getHPStars(ENEMY.getMAX_HP()), "　攻：",
					getATKStars(ENEMY.getMAX_STR()), "　防：",
					getDEFStars(ENEMY.getMAX_DEF()));
		}
		add(list, "特技使用率：", getSkillParcent(), "　　道具所持率：", getItemDrop(),
				"　経験値：", ENEMY.getENEMY_EXP());
		add(list, Message.HORIZON);
		loadCsv(lv, list);
		WINDOW = new StringOnlyWindow(16, 5, 7, Message.W,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE),
				list.toArray(new String[0]));
	}

	@Override
	public boolean action(ACTION a) {
		switch (a) {
		case ENTER:
			if (!ENEMY.isAnimating()) {
				frame = 0;
				ENEMY.setDamaging(false);
				ENEMY.startAttack(null);
			}
			return END;
		case CANCEL:
		case MENU:
			SE.SYSTEM_ENTER.play();
			setNextScene(getPreviousScene());
			return END;
		default:
			SE.SYSTEM_DAMEGED_ANIME.play();
			frame = 24;
			ENEMY.setDamaging(true);
			break;
		}
		return END;
	}

	private void add(ArrayList<String> list, Object... text) {
		StringBuilder sb = new StringBuilder();
		for (Object object : text) {
			sb.append(object);
		}
		list.add(sb.toString());
	}

	@Override
	public boolean arrow(DIRECTION d) {
		ENEMY.setDamaging(false);
		frame = -48;
		DIRECTION converted = DIRECTION.getSum(KH.getDirections());
		// DIRECTION converted = DIRECTION.getSum(KH.getDirections());
		if (converted != null) {
			ENEMY.setDirection(converted);
		} else {
			ENEMY.setDirection(d);
		}
		return END;
	}

	private String getATKStars(int max) {
		int i;
		if (max < 7) {
			i = 0;
		} else if (max < 30) {
			i = 1;
		} else if (max < 50) {
			i = 2;
		} else if (max < 66) {
			i = 3;
		} else if (max < 100) {
			i = 4;
		} else if (max < 131) {
			i = 5;
		} else if (max < 255) {
			i = 6;
		} else {
			i = 7;
		}
		return getStar(i);
	}

	private String getDEFStars(int max) {
		int i;
		if (max < 10) {
			i = 0;
		} else if (max < 25) {
			i = 1;
		} else if (max < 50) {
			i = 2;
		} else if (max < 70) {
			i = 3;
		} else if (max < 80) {
			i = 4;
		} else if (max < 200) {
			i = 5;
		} else if (max < 999) {
			i = 6;
		} else {
			i = 7;
		}
		return getStar(i);
	}

	private String getHPStars(int max) {
		int i;
		if (max < 6) {
			i = 0;
		} else if (max < 25) {
			i = 1;
		} else if (max < 70) {
			i = 2;
		} else if (max < 130) {
			i = 3;
		} else if (max < 180) {
			i = 4;
		} else if (max < 255) {
			i = 5;
		} else if (max < 999) {
			i = 6;
		} else {
			i = 7;
		}
		return getStar(i);
	}

	private String getItemDrop() {
		// Color[] cs = { Color.MAGENTA, Color.CYAN, Color.GREEN, Color.ORANGE,
		// Color.RED };
		int i = ENEMY.getItemDropParcent();
		int normal = 250;
		if (i == 0) {
			return Color.GRAY + "無" + Color.WHITE;
		} else if (i < normal) {
			return Color.CYAN + "低" + Color.WHITE;
		} else if (i == normal) {
			return StringFilter.NUMBERS + "並" + Color.WHITE;
		} else if (i < 10000) {
			return Color.ORANGE + "高" + Color.WHITE;
		} else {
			return Color.RED + "必" + Color.WHITE;
		}
	}

	@Override
	public Base_Scene getPreviousScene() {
		return PREV;
	}

	private String getSkillParcent() {
		int i = ENEMY.getSpecialParcent();
		if (i <= 0) {
			return Color.GRAY + "無" + Color.WHITE;
		} else if (i < 20) {
			return Color.LIGHT_GRAY + "稀" + Color.WHITE;
		} else if (i < 30) {
			return Color.CYAN + "低" + Color.WHITE;
		} else if (i < 50) {
			return StringFilter.NUMBERS + "並" + Color.WHITE;
		} else if (i < 100) {
			return Color.ORANGE + "高" + Color.WHITE;
		} else {
			return Color.RED + "必" + Color.WHITE;
		}
	}

	private String getStar(int i) {
		StringBuilder sb = new StringBuilder();
		if (i > 5) {
			if (i == 6) {
				sb.append("★＊＊＊★");
			} else {
				sb.append(Color.PINK);
				sb.append("★ＭＡＸ★");
			}
		} else {
			Color[] cs = { Color.MAGENTA, Color.CYAN, Color.GREEN,
					Color.ORANGE, Color.RED };
			if (i != 0)
				sb.append(cs[i - 1]);
			for (int j = 0; j < 5; j++) {
				if (j < i) {
					sb.append("★");
				} else {
					sb.append(Color.GRAY);
					sb.append("☆");
				}
			}
		}
		sb.append(Color.WHITE);
		return sb.toString();

	}

	private void loadCsv(int lv, ArrayList<String> list) {
		HashMap<String, Object> map = CSVLoadSupporter.getWiki(ENEMY
				.getOriginalName());
		if (!flag_detail) {
			add(list, Color.gray, "・？？？？");
			add(list, "");
			add(list, "");
			add(list, "");
			add(list, "");
			add(list, "");
			add(list, "");
			add(list, WindowFrame.TAIL,
					"Dotted By ".concat((String) map.get("dot")));
		} else if (map == null) {
			add(list, "<NO_DATA>");
		} else {
			String[] s = (String[]) map.get("LV" + lv);
			for (String string : s) {
				if (string != null && !(string.isEmpty())) {
					String c = Color.PINK.toString();
					if (string.startsWith("『"))
						add(list, "　", c, string);
					else if (string.endsWith("』") || string.startsWith("＋")) {
						if (string.startsWith("・") || string.startsWith("＋"))
							add(list, "　　", c, string.substring(1));
						else
							add(list, "　", c, string);
					} else if (string.startsWith("・"))
						add(list, "　".concat(string.substring(1)));
					else
						add(list, "・".concat(string));
				} else
					add(list, "");
			}
			add(list, WindowFrame.TAIL,
					"Dotted By ".concat((String) map.get("dot")));
		}
	}

	@Override
	public void upDate() {
		if (ENEMY.isAnimating()) {
			ENEMY.upDate();
		} else {
			frame++;
			if (frame > 48) {
				ENEMY.setDamaging(false);
				frame = 0;
				ENEMY.setDirection(ENEMY.getDirection().getNeiboringDirection());
			}
		}
		super.upDate();
	}

}