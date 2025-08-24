package dangeon.latest.scene.action.menu.first.adventure.records;

import java.awt.Color;
import java.util.ArrayList;

import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.model.config.Config;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.Base_Map_Random.Difficulty;
import main.util.DIRECTION;

public class AdvRecord_Command extends Base_Scene_Menu {
	private final Base_Map_Random[] ARR;

	public final AdvRecord LIST;
	public final int FATE;

	private boolean flag_current = true;

	public AdvRecord_Command(AdvRecord list) {
		this(list, Config.getFate());
	}

	public AdvRecord_Command(AdvRecord list, int fate) {
		super(4, null, list.KH, new AdvRecord_Command_View(list.CURRENT_VIEW));
		ARR = list.ARR;
		LIST = list;
		FATE = fate;
	}

	@Override
	protected void action_else() {

	}

	@Override
	public boolean arrow(DIRECTION d) {
		boolean b = super.arrow(d);
		setNextScene(new AdvRecord_Command(new AdvRecord(LIST.KH,
				LIST.CURRENT_VIEW.PREVIOUSE_VIEW, getIndex(), FATE, x, y,
				LIST.PREVIOUS_SCENE), FATE));
		return b;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return new AdvRecord_Command_1st(new AdvRecord_Command(new AdvRecord(
				KH, CURRENT_VIEW, 0, FATE, 0, 0, LIST.PREVIOUS_SCENE), FATE),
				FATE);
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		for (Base_Map_Random bmr : ARR) {
			String name = bmr.getClassName();
			if (name.matches("魔理沙のトラップタワー")) {
				name = "トラップタワー";
			}
			setContents(name, bmr.getDIFFICULTY(FATE, true), bmr
					.getStoryManager_FirstFlag().hasFinished());
		}
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return super.initializeSubWindow();
	}

	@Override
	protected void initialX_Y() {
		x = LIST.X;
		y = LIST.Y;
	}

	public boolean isCurrent() {
		return flag_current;
	}

	protected void setContents(String string, Difficulty diff, boolean flag) {
		if (flag) {
			StringBuilder sb = new StringBuilder(string);
			sb.append("[");
			sb.append(diff.COLOR);
			sb.append(diff.name().substring(0, 1));
			sb.append(Color.WHITE);
			sb.append("]");
			super.setContents(sb.toString(), new Book() {
				@Override
				protected void work() {
					setNextScene(LIST);
				}
			});
		} else {
			super.setContents(Color.GRAY.toString().concat("？？？？"));
		}
	}

	public AdvRecord_Command setFlagCurrent(boolean flag) {
		flag_current = flag;
		return this;
	}

	@Override
	protected boolean vertical_sort() {
		return false;
	}

}