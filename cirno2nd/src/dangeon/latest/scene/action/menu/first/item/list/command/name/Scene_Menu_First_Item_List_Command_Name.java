package dangeon.latest.scene.action.menu.first.item.list.command.name;

import java.awt.Color;
import java.util.ArrayList;

import main.res.SE;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.item.list.command.Scene_Menu_First_Item_List_Command;
import dangeon.latest.system.KeyHolder;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.view.detail.View_Sider;

public class Scene_Menu_First_Item_List_Command_Name extends Base_Scene_Menu {
	public final Base_Artifact SELECTED_ARTIFACT;
	public final String kana_type;
	public StringBuilder sb;

	public Scene_Menu_First_Item_List_Command_Name(KeyHolder kh, Base_View bv,
			Base_Artifact a) {
		this(kh, bv, a, "か");
	}

	public Scene_Menu_First_Item_List_Command_Name(KeyHolder kh, Base_View bv,
			Base_Artifact a, String type) {
		super(9, 11, kh, new Scene_Menu_First_Item_List_Command_Name_View(bv));
		SELECTED_ARTIFACT = a;
		kana_type = type;
		sb = new StringBuilder();
		String s = Checker.getWritenName(SELECTED_ARTIFACT);
		if (s != null) {
			sb.append(s);
		}
	}

	public Scene_Menu_First_Item_List_Command_Name(
			Scene_Menu_First_Item_List_Command_Name scn, String type) {
		this(scn.KH, scn.CURRENT_VIEW.PREVIOUSE_VIEW, scn.SELECTED_ARTIFACT,
				type);
		sb = scn.sb;
	}

	@Override
	protected void action_cancel() {
		if (sb.length() == 0) {
			super.action_cancel();
		} else {
			SE.SYSTEM_CANCEL.play();
			sb.deleteCharAt(sb.length() - 1);
			((Scene_Menu_First_Item_List_Command_Name_View) CURRENT_VIEW).TOP_WINDOW
					.setString(sb.toString());
		}
	}

	@Override
	protected void action_else() {
		SE.SYSTEM_ENTER.play();
		x = 10;
		y = 0;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		final Scene_Menu_First_Item_List_Command_Name ME = this;
		if (kana_type.matches("か")) {
			setContents(Color.CYAN + "カ", new Book() {

				@Override
				protected void work() {
					setNextScene(new Scene_Menu_First_Item_List_Command_Name(
							ME, "カ"));
				}
			});
		} else if (kana_type.matches("カ")) {
			setContents(Color.CYAN + "か", new Book() {

				@Override
				protected void work() {
					setNextScene(new Scene_Menu_First_Item_List_Command_Name(
							ME, "か"));
				}
			});
		}
		setDeprecatedPerfetedlyContents(9);
		setContents(Color.CYAN + "OK", new Book() {

			@Override
			protected void work() {
				if (getPreviousScene() instanceof Scene_Menu_First_Item_List_Command) {
					setNextScene(((Scene_Menu_First_Item_List_Command) getPreviousScene())
							.getPreviousScene());
				} else {
					setNextScene(Scene_Action.getMe());
				}
				if (sb.length() == 0) {
					String pre = SELECTED_ARTIFACT.getColoredName();
					Checker.writeName(SELECTED_ARTIFACT, sb.toString());
					View_Sider.setInformation(pre, "の名前を",
							SELECTED_ARTIFACT.getColoredName(), "に戻した");
				} else {
					String pre = SELECTED_ARTIFACT.getColoredName();
					Checker.writeName(SELECTED_ARTIFACT, sb.toString());
					View_Sider.setInformation(pre, "に",
							SELECTED_ARTIFACT.getColoredName(), "と名前をつけた");
				}
			}
		});
		String arr;
		if (kana_type.matches("か")) {
			arr = "あいうえお&はひふへほかきくけこ&まみむめもさしすせそ&や　ゆ　よたちつてと&らりるれろなにぬねの&わ　を　んぁぃぅぇぉ&っゃゅょー１２３４５&６７８９０強弱怖屑神&／！？゛゜";
		} else if (kana_type.matches("カ")) {
			arr = "アイウエオ&ハイフヘホカキクケコ&マミムメモサシスセソ&ヤ　ユ　ヨタチツテト&ラリルレロナニヌネノ&ワ　ヲ　ンァィゥェォ&ッャュョヵ×○△□☆&÷●▲■★～＃♪＄￥&～Σ＊゛゜";
		} else {
			arr = "";
		}
		for (int i = 0; i < arr.length(); i++) {
			setContents(String.valueOf(arr.charAt(i)));
		}
	}

	@Override
	protected void initialX_Y() {
		x = 0;
		y = 1;
	}

	@Override
	protected void setContents(final String name) {
		if (name.matches("&")) {
			setDeprecatedPerfetedlyContents();
			return;
		}
		setContents(name, new Book() {
			private boolean convert(String prev, String converted) {
				Character c = sb.charAt(sb.length() - 1);
				for (int i = 0; i < prev.length(); i++) {
					char seek = prev.charAt(i);
					if (c.equals(seek)) {
						sb.deleteCharAt(sb.length() - 1);
						sb.append(converted.charAt(i));
						tellTopWindow();
						return true;
					}
				}
				return false;
			}

			@Override
			protected void work() {
				if (name.matches("゛")) {
					String prev = "かきくけこさしすせそたちつてとはひふへほがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽカキクケコサシスセソタチツテトハヒフヘホガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポウヴ";
					String converted = "がぎぐげござじずぜぞだぢづでどばびぶべぼかきくけこさしすせそたちつてとはひふへほばびぶべぼガギグゲゴザジズゼゾダヂヅデドバビブベボカキクケコサシスセソタチツテトハヒフヘホバビブベボヴウ";
					if (convert(prev, converted)) {
						return;
					}
				} else if (name.matches("゜")) {
					String prev = "はひふへほばびぶべぼぱぴぷぺぽハヒフヘホバビブベボパピプペポ";
					String converted = "ぱぴぷぺぽぱぴぷぺぽはひふへほパピプペポパピプペポハヒフヘホ";
					if (convert(prev, converted)) {
						return;
					}
				}
				if (sb.length() == 5) {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append(name);
				tellTopWindow();
			}
		});
	}

	@Override
	protected void setExplain() {
	}

	private void tellTopWindow() {
		((Scene_Menu_First_Item_List_Command_Name_View) CURRENT_VIEW).TOP_WINDOW
				.setString(sb.toString());

	}
}