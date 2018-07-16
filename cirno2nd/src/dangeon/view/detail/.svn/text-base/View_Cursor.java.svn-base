package dangeon.view.detail;

import java.awt.Graphics2D;
import java.awt.Image;

import main.res.Image_Object;
import main.util.BeautifulView;

public class View_Cursor {

	public static void draw(Graphics2D g, int x, int y, boolean last) {
		if (!last) {
			BeautifulView.setAlphaOnImg(g, 0.6f);
		}
		Image im = Image_Object.suika.getImage();
		g.drawImage(im, x - im.getWidth(null), y - im.getHeight(null), null);
		if (!last) {
			BeautifulView.setAlphaOnImg(g, 1f);
		}
	}
}
