package dangeon.latest.scene.action.menu.first.adventure.records;

import java.util.ArrayList;

import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.model.config.Config;

public class AdvRecord_Command_1st extends Base_Scene_Menu {
	public final AdvRecord_Command command;

	public AdvRecord_Command_1st(AdvRecord_Command advRecord_Command) {
		this(advRecord_Command, Config.getFate());
	}

	public AdvRecord_Command_1st(AdvRecord_Command advRecord_Command, int y) {
		super(3, 1, advRecord_Command.KH, new AdvRecord_Command_1st_View(
				advRecord_Command.CURRENT_VIEW));
		this.y = y;
		command = advRecord_Command.setFlagCurrent(false);
	}

	@Override
	protected void action_else() {

	}

	@Override
	public boolean arrow(DIRECTION d) {
		boolean b = super.arrow(d);
		setNextScene(new AdvRecord_Command_1st(
				new AdvRecord_Command(new AdvRecord(KH, CURRENT_VIEW, 0, y, 0,
						0, getPreviousScene()), y), y));
		return b;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return command.LIST.PREVIOUS_SCENE;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		for (int i = 0; i < 3; i++) {
			setContents("Fate+" + i, new Book() {
				@Override
				protected void work() {
					setNextScene(command.setFlagCurrent(true));
				}
			});
		}
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return super.initializeSubWindow();
	}

	@Override
	protected void initialX_Y() {
		y = command.FATE;
	}

}