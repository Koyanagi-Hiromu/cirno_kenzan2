package dangeon.latest.scene.action;

import java.awt.Color;
import java.awt.Graphics2D;

import main.constant.FR;
import main.res.Image_LargeCharacter;
import dangeon.controller.DangeonScene;
import dangeon.controller.MainSystem;
import dangeon.controller.listener.menu.Conducter;
import dangeon.controller.listener.menu.Menu_Result;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.PresentField;
import dangeon.view.constant.NormalFont;
import dangeon.view.detail.Footer;
import dangeon.view.detail.Header;
import dangeon.view.detail.Item;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.Menu_First;
import dangeon.view.detail.MessageBox;
import dangeon.view.detail.MiniMap;
import dangeon.view.detail.Records;
import dangeon.view.detail.SecondAnime;
import dangeon.view.detail.View_Fire;
import dangeon.view.detail.View_Pad;
import dangeon.view.detail.View_SelectStairs;
import dangeon.view.detail.View_Select_Item;
import dangeon.view.detail.View_Sider;
import dangeon.view.detail.View_Simbols;
import dangeon.view.detail.View_StairScene;
import dangeon.view.detail.View_Volume;
import dangeon.view.util.ValueFollower;

public class Scene_Action_View extends Base_View {
	private Message message = Message.ME;

	@Override
	public void draw(Graphics2D g, boolean current) {
		Scene_Action.getMe().flag_current = current;
		draw_old(g);
		View_Sider.draw(g);
		message.draw(g);
	}

	private void draw_old(Graphics2D g) {

		g.setFont(NormalFont.NORMALFONT);

		if (Scene_Action.getMe().isMap() && MainSystem.isKeyAccept()) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
			MiniMap.draw(g);
			return;
		}
		MainMap.draw(g);
		if (!DangeonScene.isPresentScene(DangeonScene.RESULT)) {
			Header.draw(g);
		}
		if (PresentField.get().isMiniMapAvaible()) {
			MiniMap.draw(g);
		}
		Image_LargeCharacter.drawSlide(g);
		if (Conducter.getPhase() == Conducter.PHASE.印) {
			View_Simbols.draw(g);
		} else if (Conducter.getPhase() == Conducter.PHASE.パッド) {
			View_Pad.draw(g);
		} else if (Conducter.getPhase() == Conducter.PHASE.VOL) {
			View_Volume.draw(g);
		} else if (Conducter.getPhase() == Conducter.PHASE.履歴) {
			Records.draw(g);
		} else if (DangeonScene.isPresentScene(DangeonScene.SELECT_ITEM)) {
			MessageBox.draw(g);
			View_Select_Item.draw(g);
		} else if (DangeonScene.isPresentScene(DangeonScene.SELECT)) {
			MessageBox.draw(g);
			View_SelectStairs.draw(g);
		} else if (DangeonScene.isPresentScene(DangeonScene.RESULT)) {
			Menu_Result.draw(g);
		} else {
			drawMenuConducter(g);
			MessageBox.draw(g);
		}
		SecondAnime.draw(g);
		Footer.draw(g);
		if (DangeonScene.STAIRS.isPresentScene()) {
			View_StairScene.draw(g);
		}
		drawTest(g);
	}

	private void drawMenuConducter(Graphics2D g) {
		if (DangeonScene.isPresentSceneLikeMenu()) {
			Menu_First.draw(g);
			switch (Conducter.getPhase()) {
			case FIRST:
				break;
			case 射撃:
				View_Fire.draw(g);
				break;
			case 道具:
				Item.draw(g);
				break;
			case 情報:
				break;
			}
		}
	}

	private void drawTest(Graphics2D g) {
		boolean test = true;
		if (test) {
			ValueFollower.drawFrameRate(g);
			// ValueFollower.drawEnemyTurnStep(g);
			ValueFollower.drawAnyoneYouWanaChase(g);
		}
	}

}
