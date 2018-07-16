package title;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.thread.MainThread;
import title.objects.Base_Title_Object;
import title.objects.body.BackGround;
import title.objects.body.PushEnter;
import dangeon.latest.scene.Base_View;
import dangeon.view.util.StringFilter;

public class Title_View extends Base_View {
	private BackGround back;
	private final BufferedImage BI;

	public Title_View() {
		back = new BackGround();
		BI = new BufferedImage(FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = BI.createGraphics();
		g.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, FR.SCREEN_HEIGHT,
				new Color(100, 200, 255)));
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
		g.setPaint(new RadialGradientPaint(FR.SCREEN_WIDTH / 2,
				FR.SCREEN_HEIGHT / 2, FR.SCREEN_WIDTH / 2, new float[] { 0.3f,
						1f }, new Color[] { new Color(255, 255, 255, 200),
						new Color(255, 255, 255, 0) }, CycleMethod.NO_CYCLE));
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
		g.dispose();
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		back.upDate();
		g.drawImage(BI, 0, 0, null);
		back.draw(g);

		Title t = (Title) PARENT_SCENE;
		if (t.msg != null) {
			int size = 25;
			g.setFont(g.getFont().deriveFont((float) size));
			g.setColor(new Color(0, 50, 0, (int) Math.floor(150 + 100 * Math
					.sin(MainThread.getFrame() / 10.0))));
			StringFilter.drawString(g, t.msg, (FR.SCREEN_WIDTH - g
					.getFontMetrics().stringWidth(t.msg)) / 2, FR.SCREEN_HEIGHT
					/ 4 + size);
			String s = "しばらくお待ち下さい（" + t.percent + "％）";
			StringFilter.drawString(g, s, (FR.SCREEN_WIDTH - g.getFontMetrics()
					.stringWidth(s)) / 2, FR.SCREEN_HEIGHT / 4 + size * 2);
			return;
		}
		for (Base_Title_Object o : t.objects)
			o.draw(g);
		for (Base_Title_Object o : t.objects_first) {
			if (!current) {
				if (o instanceof PushEnter)
					continue;
			}
			o.draw(g);
		}

	}
}
