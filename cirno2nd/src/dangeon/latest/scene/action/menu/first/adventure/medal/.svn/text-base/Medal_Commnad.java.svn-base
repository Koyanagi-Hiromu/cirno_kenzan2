package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.view.constant.NormalFont;

public class Medal_Commnad extends Base_Scene_Menu {
	private Base_Scene BACK;
	private String[] array;

	protected StringOnlyWindow sub_left_window;

	public Medal_Commnad(KeyHolder kh, Base_Scene prev_secene) {
		super(null, 1, kh, new Medal_Commnad_View(
				Scene_Action.getMe().CURRENT_VIEW));
		BACK = prev_secene;
	}

	@Override
	protected void action_else() {

	}

	@Override
	public boolean arrow(DIRECTION d) {
		boolean b = super.arrow(d);
		setArray();
		return b;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return BACK;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		MedalPageFactory factory = new MedalPageFactory();
		int max = factory.length();
		for (int i = 0; i < max; i++) {
			int j = i + 1;
			Base_MedalPage page = factory.create(i);
			ArrayList<String> list2 = new ArrayList<String>();
			page.getExp(list2);
			setContents(半角全角コンバーター.toMaruMoji(j) + page.getShortTitle(),
					list2.toArray(new String[0]), new Book() {
						@Override
						protected void work() {
						}
					});
		}
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		setArray();
		sub_left_window = new StringOnlyWindow(6, 24, 289,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE), array);
		return new UnderMenuWindow(2, Message.Y + 23) {
			@Override
			protected void drawString(Graphics2D g, int X, int Y) {
				for (int i = 0; i < explain.length; i++) {
					drawString(g, explain[i], i + 1);
				}
			}
		};
	}

	@Override
	protected void initialX_Y() {
		super.initialX_Y();
		setArray();
	}

	private void setArray() {
		array = new String[12];
		for (int i = 0; i < array.length; i++) {
			array[i] = "";
		}
		ArrayList<MedalWrapper> list = new ArrayList<MedalWrapper>();
		new MedalPageFactory().create(y).addMedals(list);
		String color[] = { new Color(255, 200, 200).toString(),
				new Color(240, 240, 255).toString(), };
		for (int i = 0; i < list.size(); i++) {
			Medal m = list.get(i).medal;
			if (m.hasFinished())
				array[i] = color[i % 2].concat(list.get(i).toString());
			else
				array[i] = Color.GRAY.toString().concat("？？？？");
		}
		if (sub_left_window != null)
			sub_left_window.setString(array);
	}

}