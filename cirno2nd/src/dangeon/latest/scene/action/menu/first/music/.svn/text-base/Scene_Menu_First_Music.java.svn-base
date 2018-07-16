package dangeon.latest.scene.action.menu.first.music;

import java.awt.Color;
import java.util.ArrayList;

import main.res.BGM;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Scene_Menu_First_Music extends Scene_Menu_First___ {

	private abstract class MyBook extends Book {

		protected abstract void content_work();

		@Override
		protected final void work() {
			content_work();
			setNextScene(new Scene_Menu_First_Music(KH,
					CURRENT_VIEW.PREVIOUSE_VIEW, getY()));
		}

	}

	private final int INIT_Y;

	int X;

	public final String[] simbols = { "☆", "♪", "⑨" };

	public Scene_Menu_First_Music(KeyHolder kh, Base_View bv) {
		this(kh, bv, 0);
	}

	protected Scene_Menu_First_Music(KeyHolder kh, Base_View bv, final int ini_y) {
		super(kh, new Scene_Menu_First_Music_View(bv));
		INIT_Y = ini_y;
	}

	private MenuContent fav(final int i) {
		return new MenuContent(simbols[i], simbols[i] + "に登録します", new Book() {
			@Override
			protected void work() {
				String s = BGM.save(i);
				if (s != null)
					Message.set(simbols[i], "に", s, Color.WHITE.toString(),
							"を保存しました");
				else
					Message.set("保存に失敗してしまいました");
				setNextScene(Scene_Action.getMe());
			}
		});
	}

	private MenuContent fav_load(final int i) {
		Object o = BGM.staticLoad(i, false);
		if (!(o instanceof String)) {
			if (o instanceof SpellCard) {
				o = "Load from" + ((SpellCard) o).getTrueName();
			} else {
				o = ((BGM) o).getColoredTitle();
			}
		} else {
			o = simbols[i] + o.toString();
		}
		return new MenuContent(simbols[i], o.toString(), new Book() {
			@Override
			protected void work() {
				Object o = BGM.staticLoad(i);
				if (o instanceof String)
					Message.set(simbols[i], o.toString());
				else {
					if (o instanceof SpellCard) {
						o = ((SpellCard) o).getTrueName();
					} else {
						o = ((BGM) o).getColoredTitle();
					}
					Message.set(simbols[i], "から", o.toString(),
							Color.WHITE.toString(), "を読み込みます");
					BGM.loaded = i;
				}
				setNextScene(Scene_Action.getMe());
			}
		});
	}

	private String getColor(boolean on) {
		return on ? StringFilter.NUMBERS.toString() : Color.GRAY.toString();
	}

	private void initBGM(final int y) {
		StringBuilder sb = new StringBuilder("ＢＧＭ調整　");
		sb.append(Color.LIGHT_GRAY);
		sb.append("♪");
		int vol = Config.getBGMVol();
		if (vol < 100)
			sb.append(" ");
		if (vol < 10)
			sb.append(" ");
		sb.append(vol);
		setContents(sb.toString(), new String[] { "♪BGM と SE効果音 の音量を調整します" },
				new Book() {
					@Override
					protected void work() {
						setNextScene(new Music_Vol(KH, CURRENT_VIEW, X, y, 0));
					}
				});
	}

	private void initFav(final int y) {
		setContents("お気に入りに登録", "現在の曲を保存します", new Book() {
			@Override
			protected void work() {
				setNextScene(new Music_Selection(KH, CURRENT_VIEW, X, y,
						fav(0), fav(1), fav(2)));
			}
		});
	}

	private void initFavPlay(final int y) {
		int playing = BGM.loaded;
		setContents("お気に入り　" + getColor(playing == 0) + simbols[0] + "　"
				+ getColor(playing == 1) + simbols[1] + "　"
				+ getColor(playing == 2) + simbols[2], "保存した曲を流します",
				new Book() {
					@Override
					protected void work() {
						setNextScene(new Music_Selection(KH, CURRENT_VIEW, X,
								y, fav_load(0), fav_load(1), fav_load(2)));
					}
				});
	}

	private void initHowToPlay(final int y) {
		final boolean fix = Config.isFixBGM();
		final boolean ran = Config.isRandom_BGM();
		String s = StringFilter.NUMBERS.toString().concat("ON→")
				.concat(Color.WHITE.toString());
		final MenuContent m1 = new MenuContent("乱", s + "合成した曲をランダムで再生します",
				new MyBook() {
					@Override
					protected void content_work() {
						Config.switchNo_Random_BGM();
					}
				});
		final MenuContent m2 = new MenuContent("固", s + "カードを変更しても曲を流しません",
				new MyBook() {
					@Override
					protected void content_work() {
						Config.switchBgm();
					}
				});
		setContents("再生形式　　" + getColor(ran) + m1.NAME + "　" + getColor(fix)
				+ m2.NAME, "カード装備時の再生形式を変更します", new Book() {
			@Override
			protected void work() {
				setNextScene(new Music_Selection(KH, CURRENT_VIEW, X, y, m1, m2));
			}
		});
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		initBGM(y(0));
		initSE(y(1));
		setDeprecatedContentsHorizon();
		initHowToPlay(y(3));
		setDeprecatedContentsHorizon();
		initFav(y(5));
		initFavPlay(y(6));
	}

	@Override
	protected void initialX_Y() {
		super.initialX_Y();
		y = INIT_Y;
	}

	private void initSE(final int y) {

		StringBuilder sb = new StringBuilder("効果音調整　");
		sb.append(Color.LIGHT_GRAY);
		sb.append("SE");
		int vol = Config.getSEVol();
		if (vol < 100)
			sb.append(" ");
		if (vol < 10)
			sb.append(" ");
		sb.append(vol);
		setContents(sb.toString(), new String[] { "♪BGM と SE効果音 の音量を調整します" },
				new Book() {
					@Override
					protected void work() {
						setNextScene(new Music_Vol(KH, CURRENT_VIEW, X, y, 1));
					}
				});
	}

	private int y(int i) {
		Scene_Menu_First_Music_View v = (Scene_Menu_First_Music_View) CURRENT_VIEW;
		int y = v.Y;
		int h = (int) (NormalFont.SIZE + 5);
		if (i == 6)
			y += h * i;
		else
			y += h * i + h / 2;
		return y;
	}
}