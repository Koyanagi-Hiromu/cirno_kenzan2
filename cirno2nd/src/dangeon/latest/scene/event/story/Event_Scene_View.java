package dangeon.latest.scene.event.story;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.res.Image_LargeCharacter;
import main.util.FileReadSupporter;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.view.constant.NormalFont;

public class Event_Scene_View extends Base_View {

	private BufferedImage BI;

	public Event_Scene_View(int i) {
		BI = FileReadSupporter.readImage("bg/story_" + i, "png");
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g, 255);
		int x = 10, y = 10;
		int w = FR.SCREEN_WIDTH - x * 2;
		int h = Message.WINDOW_Y - y * 2;
		g.drawImage(BI, x, y, w, h, null);
		g.setFont(NormalFont.NORMALFONT);
		Image_LargeCharacter.drawSlide(g);
		Message.ME.draw(g);
	}

}
