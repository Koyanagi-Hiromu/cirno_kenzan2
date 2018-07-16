package dangeon.latest.scene.action.menu.first;

import java.awt.Color;
import java.util.ArrayList;

import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.adventure.Scene_Menu_First_Adventure;
import dangeon.latest.scene.action.menu.first.config.Scene_Menu_First_Config;
import dangeon.latest.scene.action.menu.first.infomation.Scene_Menu_First_Info;
import dangeon.latest.scene.action.menu.first.item.Scene_Menu_First_Item;
import dangeon.latest.scene.action.menu.first.music.Scene_Menu_First_Music;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.latest.util.view_window.WindowContent;
import dangeon.model.map.PresentField;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;

public class Scene_Menu_First extends Base_Scene_Menu {

	public Scene_Menu_First(KeyHolder kh, Base_View bv) {
		super(1, kh, new Scene_Menu_First_View(bv));
	}

	@Override
	protected void action_else() {
		action_enter();
	}

	@Override
	public void ascX() {
		super.ascX();
		action_else();
	}

	@Override
	public void decX() {
		super.decX();
		action_enter();
	}

	@Override
	public Base_Scene getPreviousScene() {
		return Scene_Action.getMe();
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		setContents("道具", "", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Item(KH, CURRENT_VIEW));
			}
		});
		setContents("情報", "", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Info(KH, CURRENT_VIEW));
			}
		});
		setContents("音楽", "", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Music(KH, CURRENT_VIEW));
			}
		});
		setContents("設定", "", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Config(KH, CURRENT_VIEW));
			}
		});
		setContents("冒険", "", new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Adventure(KH, CURRENT_VIEW));
			}
		});
		final Base_Scene bs = PresentField.get().createOwnMenuScene(KH,
				CURRENT_VIEW);
		if (bs != null) {
			setContents("特殊", "", new Book() {
				@Override
				protected void work() {
					setNextScene(bs);
				}
			});
		}
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		ArrayList<WindowContent> wcs = new ArrayList<WindowContent>();
		int x = 0;
		StringBuilder sb = new StringBuilder(PresentField.get().getMapName());
		wcs.add(new WindowContent(x, 0, sb.toString()));
		sb = new StringBuilder();
		sb.append(Enchant.CL_ATK);
		sb.append("攻 ");
		sb.append(Enchant.getSumSTR());
		sb.append(Enchant.CL_DEF);
		sb.append("  防 ");
		if (HoldEnemy.ME.isHold())
			sb.append(HoldEnemy.ME.get().getDEF());
		else
			sb.append(Enchant.getSumDEF());
		sb.append(Color.PINK);
		sb.append("  Ｐ");
		int now, max;
		if (HoldEnemy.ME.isHold()) {
			now = Player.me.getSTR() + HoldEnemy.ME.get().getSTR();
			max = HoldEnemy.ME.get().getMAX_STR() + Player.me.getMAX_STR();
		} else {
			now = Player.me.getSTR();
			max = Player.me.getMAX_STR();
		}
		if (now < 100)
			sb.append(" ");
		sb.append(now);
		sb.append(Color.WHITE);
		sb.append("/");
		sb.append(max);
		wcs.add(new WindowContent(x, 1, sb.toString()));
		x = 223;

		sb = new StringBuilder();
		sb.append("満腹度 ");
		sb.append(Player.me.getSATIETY());
		sb.append("/");
		sb.append(Player.me.getMAX_SATIETY());
		wcs.add(new WindowContent(x, 0, sb.toString()));
		sb = new StringBuilder();
		sb.append("経験値 ");
		sb.append(String.format("%,d", Player.me.getPlayerExp()));
		wcs.add(new WindowContent(x, 1, sb.toString()));
		return new UnderMenuWindow(3, wcs.toArray(new WindowContent[0]));
	}

}