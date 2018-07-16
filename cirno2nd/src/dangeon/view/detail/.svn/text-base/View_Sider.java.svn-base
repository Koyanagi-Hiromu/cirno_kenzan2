package dangeon.view.detail;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import main.Listener;
import main.Listener.ACTION;
import main.Second_Firster;
import main.constant.FR;
import main.util.BeautifulView;
import main.util.半角全角コンバーター;
import dangeon.controller.listener.KeyAccepter;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.Composition;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.EnchantArrow;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.BGMFONT;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class View_Sider {
	private static int story = Config.getStory();
	private static int KW, KH;
	private static BufferedImage KEY_EXPLAIN;
	private static int TW, TH;
	private static BufferedImage TASK_EXPLAIN;
	private static String info;
	static {
		setImage();
	}

	private static int information_time = 0;

	public static void draw(Graphics2D g) {
		if (KeyAccepter.isDirecting() || Scene_Action.getMe().isMap()) {
			return;
		}
		if (!Message.isDemandToWaitAnySystem())
			drawFooter(g);
		View_Sider_Info.draw(g);
		// if (DangeonScene.isPresentSceneLikeMenu()) {
		// BeautifulView.setAlphaOnImg(g, 0.5f);
		// }
		// if (!Config.isStory(story)) {
		// setImage();
		// }
		// if (Config.isKey_ex()) {
		// g.drawImage(KEY_EXPLAIN, 0, SCREEN.Y, KW, KH, null);
		// }
		// if (info != null) {
		// g.drawImage(TASK_EXPLAIN, FR.SCREEN_WIDTH - TW, SCREEN.Y, TW, TH,
		// null);
		// if (information_time > 0) {
		// information_time--;
		// if (information_time == 0) {
		// info = null;
		// setTaskImage();
		// }
		// BeautifulView.setAlphaOnImg(g, 1f);
		// }
		// } else {
		// View_Sider_Info.draw(g);
		// }
		// if (DangeonScene.isPresentSceneLikeMenu()) {
		// BeautifulView.setAlphaOnImg(g, 1f);
		// }
	}

	private static void drawEnchant(Graphics2D g, int y, int w, int w2, int w3) {
		Enchant enc[] = { Enchant.ATK, Enchant.DEF };
		// int h = g.getFontMetrics().getHeight();
		for (int i = 0; i < enc.length; i++) {
			Enchant en = enc[i];
			StringBuilder sb = new StringBuilder();
			Base_Artifact sc = en.getEnchant();
			if (sc == null) {
				sb.append(en.getColor());
				sb.append("(");
				sb.append(en.toString());
				sb.append("なし)");
			} else {
				if (sc.isCurse()) {
					sb.append(Base_Item.CL_CURSED);
				} else {
					sb.append(en.getColor());
				}
				String str = sc.getName();
				if (str.startsWith("橙")) {
					str = "ちぇん";
				} else if (str.startsWith("古明地")) {
					str = str.substring(3);
				}
				sb.append(str.substring(0, 3));
				sb.append(StringFilter.NUMBERS);
				int forge = sc.getForgeValue();
				if (forge > 9) {
					sb.append("+");
					sb.append(sc.getForgeValue());
				} else if (forge > 0) {
					sb.append("+");
					sb.append(" ");
					sb.append(sc.getForgeValue());
				} else if (forge < -9) {
					sb.append(forge);
				} else if (forge < 0) {
					sb.append(forge);
				} else {
					sb.append(Color.GRAY);
					sb.append("±0");
				}
				sb.append(Color.WHITE);
				sb.append(Composition.compositionSimply(sc));
			}
			StringFilter.drawString_plain(g, sb.toString(), w * i + 2, y);
		}
		enc = Enchant.getANYS();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < enc.length; i++) {
			Enchant en = enc[i];
			Base_Artifact sc = en.getEnchant();
			if (sc == null) {
				sb.append(en.getColor());
				sb.append(半角全角コンバーター.半角To全角数字(i + 1));
			} else if (!sc.isStaticCheked()) {
				sb.append(en.getColor());
				sb.append("？");
			} else if (sc.sim == null) {
				sb.append("□");
			} else {
				sb.append(sc.sim.getName(true));
			}
		}
		StringFilter.drawString_plain(g, sb.toString(), FR.SCREEN_WIDTH - w2
				- w3, y);
		sb = new StringBuilder();
		sb.append(Color.WHITE);
		if (EnchantArrow.getArrow() == null) {
			sb.append("(射撃なし)");
		} else {
			sb.append(EnchantArrow.getArrow().name());
		}
		StringFilter.drawString_plain(g, sb.toString(), FR.SCREEN_WIDTH - w3
				+ 2, y);
	}

	private static void drawEnchants(Graphics2D g, int y, int h) {
		if (Config.isKey_ex() && Message.ME.isEmpty()) {
			g.setColor(new Color(0, 0, 0, 125));
			g.fillRect(0, y, FR.SCREEN_WIDTH, h * 2);
		}
		int w2 = 22;
		int w3 = 54;
		int w = (FR.SCREEN_WIDTH - w2 - w3) / 2;
		g.setColor(new Color(250, 0, 0, 200));
		g.fillRect(0, FR.SCREEN_HEIGHT - h, w, h);
		g.setColor(new Color(250, 150, 0, 200));
		g.fillRect(w, FR.SCREEN_HEIGHT - h, w, h);
		g.setColor(new Color(0, 250, 250, 200));
		g.fillRect(FR.SCREEN_WIDTH - w3 - w2, FR.SCREEN_HEIGHT - h, w2, h);
		g.setColor(new Color(0, 250, 0, 200));
		g.fillRect(FR.SCREEN_WIDTH - w3, FR.SCREEN_HEIGHT - h, w3, h);
		g.setColor(Color.WHITE);
		drawEnchant(g, FR.SCREEN_HEIGHT - 1, w, w2, w3);
	}

	private static void drawFooter(Graphics2D g) {
		if (!Scene_Action.getMe().isCurrentScene()) {
			return;
		}
		if (!Message.ME.isEmpty()) {
			// return;
		}
		if (Scene_Action.getMe().isDirecting())
			BeautifulView.setAlphaOnImg(g, 0.3f);
		Font font = g.getFont();
		// g.setFont(NormalFont.NORMALFONT);
		g.setFont(NormalFont.NORMALFONT.deriveFont(10f));
		int h = 11;
		int y = FR.SCREEN_HEIGHT - h * 3;
		drawEnchants(g, y, h);
		if (Config.isKey_ex() && Message.ME.isEmpty()) {
			drawKey(g, y - 1);
		}
		if (Config.isLapON() && PresentField.isRandomField()) {
			drawTimer(g, y, h);
		}
		BeautifulView.setAlphaOnImg(g, 1f);
		g.setFont(font);
	}

	private static void drawKey(Graphics2D g, final int y) {
		ACTION[] actions = { ACTION.ENTER, ACTION.MENU, ACTION.FIRE,
				ACTION.IDASH, ACTION.MAP, ACTION.TURN, ACTION.BIAS, ACTION.MOVE };
		ACTION[] actions2 = { ACTION.REST, ACTION.ITEM, ACTION.GATHER,
				ACTION.SPEC, ACTION.SYMBOL, ACTION.RECORD, ACTION.BIAS,
				ACTION.DASH };
		boolean[] color = new boolean[actions.length];
		boolean flag_x = Scene_Action.getMe().isDushKeyPushing();
		String ex[] = new String[actions.length];
		for (int i = 0; i < actions.length; i++) {
			ACTION a = actions[i];
			StringBuilder sb = new StringBuilder();
			if (a == ACTION.MOVE) {
				String s;
				switch (Scene_Action.getMe().getDirection()) {
				case DOWN:
					s = "↓";
					break;
				case DOWN_LEFT:
					s = "_ ";
					break;
				case DOWN_RIGHT:
					s = " _";
					break;
				case LEFT:
					s = "←";
					break;
				case RIGHT:
					s = "→";
					break;
				case UP:
					s = "↑";
					break;
				case UP_LEFT:
					s = "^ ";
					break;
				case UP_RIGHT:
					s = " ^";
					break;
				default:
					s = "・";
					break;
				}
				sb.append(s);
				sb.append(":");
				if (flag_x) {
					a = actions2[i];
				} else {
					a = actions[i];
				}
				sb.append(a.SHORT_EX);
				color[i] = Scene_Action.getMe().KH.isDirecting();
			} else {
				String key = null;
				for (int keys : Listener.getKey().keySet()) {
					if (a == Listener.getKey().get(keys)) {
						String text = KeyEvent.getKeyText(keys);
						if (keys == KeyEvent.VK_ENTER
								|| keys == KeyEvent.VK_ESCAPE)
							continue;
						if (text.length() > 2) {
							text = text.substring(0, 2);
						} else {
							text = 半角全角コンバーター.半角To全角英字(text);
						}
						key = text;
						break;
					}
				}
				if (key == null) {
					if (a == ACTION.ENTER) {
						key = "En";
					}
				}
				sb.append(key);
				if (flag_x) {
					a = actions2[i];
				} else {
					a = actions[i];
				}
				sb.append(":");
				if (a == ACTION.SPEC) {
					if (PresentField.get().isEnemyEnchantDungeon()) {
						sb.append("敵装備");
					} else {
						sb.append(a.SHORT_EX);
					}
				} else {
					sb.append(a.SHORT_EX);
				}
				color[i] = is(actions[i]);
			}
			ex[i] = sb.toString();
		}
		final int cols = 4;
		final int rows = 2;
		final int X_DELT = 85;
		final int X = FR.SCREEN_WIDTH - X_DELT * cols + 24;
		int h = g.getFontMetrics().getHeight();
		for (int index = 0; index < ex.length; index++) {
			int j = index % rows;
			String name = ex[index];
			int x = X + X_DELT * (index / rows);
			int _y_ = y + h * (j + 1);
			drawKeyContent(g, name, x, _y_, h, flag_x, color[index]);
		}
		String key = null;
		for (int keys : Listener.getKey().keySet()) {
			if (ACTION.CANCEL == Listener.getKey().get(keys)) {
				String text = KeyEvent.getKeyText(keys);
				if (keys == KeyEvent.VK_ESCAPE)
					continue;
				if (text.length() > 2) {
					text = text.substring(0, 2);
				} else {
					text = 半角全角コンバーター.半角To全角英字(text);
				}
				key = text;
				break;
			}
		}
		if (key == null)
			key = "Es";
		drawKeyContent(g, key.concat(":組み合わせ"), 2, y + h, h, flag_x,
				is(ACTION.CANCEL));
	}

	private static void drawKeyContent(Graphics2D g, String name, int x, int y,
			int h, boolean flag_x, boolean b) {
		// int w = g.getFontMetrics().stringWidth(
		// StringFilter.getPlainString(name));
		// if (!flag_x && !b)
		// g.setPaint(new GradientPaint(x, y - h, new Color(0, 0, 255, 100),
		// x, y, new Color(0, 0, 100, 100)));
		// else if (b)
		// g.setPaint(new GradientPaint(x, y - h, new Color(250, 250, 0, 150),
		// x, y, new Color(200, 200, 0, 150)));
		// else
		// g.setPaint(new GradientPaint(x, y - h, new Color(255, 0, 0, 150),
		// x, y, new Color(255, 50, 0, 150)));
		// g.fillRect(x, y - h + 2, w, h);
		// g.setColor(Color.WHITE);
		// g.drawRect(x, y - h + 1, w, h);
		Color c;
		if (!flag_x && !b)
			c = Color.LIGHT_GRAY;
		else if (b)
			c = Color.YELLOW;
		else
			c = Color.MAGENTA;
		// if (!MainSystem.isKeyAccept()) {
		// c = Color.RED;
		// }
		// if (TurnSystemController.isEnemyTurn()) {
		// c = Color.BLUE;
		// }
		StringFilter.drawString(g, c.toString().concat(name), x, y);
	}

	private static void drawTimer(Graphics2D g, int y, int height) {
		StringBuilder sb = new StringBuilder();
		long t = Player.me.getPlayingMilliTime();
		boolean h = false;
		t /= 1000;
		if (t > 3600) {
			sb.append(Color.ORANGE);
			sb.append(t / 3600);
			t -= t / 3600 * 3600;
			sb.append("h");
			h = true;
		}
		sb.append(Color.RED);
		if (h || t > 60) {
			if (h && t / 60 < 10) {
				sb.append("0");
			}
			sb.append(t / 60);
			t -= t / 60 * 60;
			sb.append("m");
		}
		sb.append(Color.YELLOW);
		if (t < 10) {
			sb.append("0");
		}
		sb.append(t);
		sb.append("s");
		sb.append(Color.lightGray);
		t = Player.me.getPlayingMilliTime();
		t = t - t / 1000 * 1000;
		if (t < 10) {
			sb.append("00");
		} else if (t < 100) {
			sb.append("0");
		}
		sb.append(t);
		drawKeyContent(g, sb.toString(), 2, y + height * 2, height, true,
				is(ACTION.CANCEL));
	}

	private static String[] getExplain() {
		StringBuilder sb = new StringBuilder();
		if (getStory() >= 10) {
			sb.append("～KeyTips～,");

			if (getStory() >= 70) {
				sb.append("Ｚ：決定（攻撃／話しかける）,");
			} else {
				sb.append("Ｚ：決定（話しかける）,");
			}
		} else {
			return null;
		}
		if (getStory() >= 20) {
			sb.append("Ｘ：押しっぱでダッシュ,");
		}
		if (getStory() >= 40) {
			sb.append("Ｖ：メニューを開く,");
		}
		if (getStory() >= 50) {
			sb.append("Ｃ：ミニマップ表示,");
		}
		if (getStory() >= 80) {
			sb.append("Shift／Space：方向指定,");
		}
		if (getStory() >= 100) {
			sb.append("Ctrl：斜め制限,");
		}
		if (getStory() >= 110) {
			sb.append("Ｆ：射撃,");
		}
		if (getStory() >= 150) {
			sb.append("※Ｘ中にＺでターン送り");
		}
		return sb.toString().split(",");
	}

	private static int getStory() {
		return Config.getStory();
	}

	private static String getTask() {
		// if (PresentField.get() instanceof 慧音の教室
		// || PresentField.get() instanceof 出張寺子屋) {
		// switch (Config.getStory()) {
		// case 10:
		// return "慧音に話しかけよう";
		// case 20:
		// return "７つある慧音の教室をまわろう";
		// case 25:
		// return "階段を探そう";
		// case 30:
		// return "次の教室に行こう";
		// case 40:
		// return "アイテムを拾って使おう@Ｖボタンでメニューを開こう";
		// case 50:
		// case 60:
		// return "Ｃでミニマップを見てみよう@メニューから情報を選んで@メッセージ履歴を見てみよう";
		// case 70:
		// return "エネミーをやっつけよう";
		// case 73:
		// return "アイテムを投げよう";
		// case 80:
		// return "Shiftで向きを整えて@杖を使おう";
		// case 85:
		// case 90:
		// return "杖を投げ当てよう";
		// case 100:
		// return "ナイフに当たらないように斜めに移動しよう@Ctrlキーを押すと便利だぞ";
		// case 105:
		// return "アイテムからナイフを選んで使用してみよう";
		// case 110:
		// case 120:
		// return "メニューから射撃を開いてナイフをセットしよう@その後Ｆを押して投げてみよう";
		// case 123:
		// return "魔理沙のカードを攻撃装備しよう@美鈴のカードを防御装備しよう";
		// case 125:
		// return "橙のカードを攻撃装備して@素振りしてみよう";
		// case 127:
		// return "射命丸文のカードを防御装備してみよう";
		// case 129:
		// case 130:
		// return "メニューから情報を選んで@識別済み印一覧を確認しよう";
		// case 133:
		// return "斜め移動を駆使してカードを拾おう@その後エネミーが倒れるまで使用しよう";
		// case 135:
		// return "特殊タイプの印を確認しよう@攻撃や防御に装備して印一覧を確認しよう@印一覧はメニューの情報から開こう";
		// case 137:
		// return "同異タイプの印を確認しよう@薬草を何でも装備して印一覧を確認しよう";
		// case 138:
		// return "アイテムをいろいろ装備してみよう@メニューの情報から印一覧を開いて@印を確認しよう";
		// }
		// }

		return null;
	}

	private static boolean is(ACTION a) {
		return Scene_Action.getMe().KH.isContraining(a);
	}

	private static void setImage() {
		setTaskImage();
		String[] ex = getExplain();
		if (ex == null) {
			KW = KH = 1;
			KEY_EXPLAIN = new BufferedImage(KW, KH,
					BufferedImage.TYPE_INT_ARGB_PRE);
			return;
		}
		int width = 0;
		Font font = NormalFont.NORMALFONT.deriveFont(NormalFont.SIZE - 5f);
		FontMetrics fo = Second_Firster.ME.getFontMetrics(font);
		for (String s : ex) {
			width = Math.max(width, fo.stringWidth(s));
		}
		int height = (BGMFONT.HEIGHT + BGMFONT.DECENT) * ex.length
				+ BGMFONT.DECENT;
		KEY_EXPLAIN = new BufferedImage(width * 2, height * 2,
				BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = KEY_EXPLAIN.createGraphics();
		g.setFont(font);
		BeautifulView.setAntiAliasing(g, true);
		g.scale(2, 2);
		for (int i = 0; i < ex.length; i++) {
			String str = ex[i];
			g.drawString(str, 0, (BGMFONT.HEIGHT) * (i + 1));
		}
		KW = width;
		KH = height;
		story = Config.getStory();
	}

	public static void setInformation(String... msg) {
		View_Sider_Info.setInformation(msg);
	}

	private static void setTaskImage() {
		boolean flag = information_time == 0;
		if (flag) {
			info = getTask();
		}
		if (info == null) {
			TW = TH = 1;
			TASK_EXPLAIN = new BufferedImage(TW, TH,
					BufferedImage.TYPE_INT_ARGB_PRE);
			return;
		}
		String head = (flag) ? "～NextTask～@" : "@";
		String[] ex = head.concat(info).split("@");
		int width = 0;
		Font font = NormalFont.NORMALFONT.deriveFont(NormalFont.SIZE - 5f);
		FontMetrics fo = Second_Firster.ME.getFontMetrics(font);
		for (String s : ex) {
			width = Math.max(width, fo.stringWidth(s));
		}
		int height = (BGMFONT.HEIGHT + BGMFONT.DECENT) * ex.length
				+ BGMFONT.DECENT;
		TASK_EXPLAIN = new BufferedImage(width * 2, height * 2,
				BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = TASK_EXPLAIN.createGraphics();
		g.setFont(font);
		BeautifulView.setAntiAliasing(g, true);
		g.scale(2, 2);
		fo = g.getFontMetrics();
		for (int i = 0; i < ex.length; i++) {
			String str = ex[i];
			StringFilter.drawString(g, str,
					width - fo.stringWidth(StringFilter.getPlainString(str)),
					(BGMFONT.HEIGHT) * (i + 1));
		}
		TW = width;
		TH = height;
	}
}
