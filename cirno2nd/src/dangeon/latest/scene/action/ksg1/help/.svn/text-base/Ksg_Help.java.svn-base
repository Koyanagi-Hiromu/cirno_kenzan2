package dangeon.latest.scene.action.ksg1.help;

import java.awt.Color;
import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Ksg_Help extends Base_Scene {
	public final StringOnlyWindow WINDOW;

	public Ksg_Help(KeyHolder kh, Base_View bv) {
		super(kh, new Ksg_Help_View(bv));
		ArrayList<String> list = new ArrayList<String>();
		add(list, Color.ORANGE, "◆", "概要", "◆", Color.WHITE);
		add(list, "幽々子(様)のために食べ物を仕分けよう");
		add(list, "おにぎりと草が食べ物です");
		add(list, "ただしチルノじゃないので", Color.CYAN, "凍ったものはNG", Color.WHITE, "です");
		add(list, Color.PINK, "（なんて物を食べさせる気だと妖夢に怒られます）");
		add(list, Message.HORIZON);
		add(list, Color.ORANGE, "◆", "操作説明", "◆", Color.WHITE);
		add(list, "●左右に食べ物があるなら");
		add(list, StringFilter.NUMBERS, "　　　　　その方向のキーを押そう", Color.WHITE);
		add(list, "●どっちも食べられないなら");
		add(list, StringFilter.NUMBERS, "　　　　　上下キーかアクションキーを押して流そう", Color.WHITE);
		add(list, Color.PINK, "（食べ物を流すとなんて勿体無いと妖夢に怒られます）");
		add(list, Message.HORIZON);
		add(list, Color.ORANGE, "◆", "注意", "◆", Color.WHITE);
		// add(list, "仕分けが遅いと");
		// add(list, Color.WHITE, "　　　　　　　　　　　　　　　　　　　　みょ～ん");
		add(list, "仕分けが遅いと幽々子(様)が待ちきれなくなるので");
		add(list, "やっぱり妖夢に怒られます");
		// add(list, Message.HORIZON);
		// add(list, "◆その他◆");
		WINDOW = new StringOnlyWindow(16, 5, 8, Message.W,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE),
				list.toArray(new String[0]));
	}

	@Override
	public boolean action(ACTION a) {
		SE.SYSTEM_ENTER.play();
		setNextScene(getPreviousScene());
		return END;
	}

	private void add(ArrayList<String> list, Object... text) {
		StringBuilder sb = new StringBuilder();
		for (Object object : text) {
			sb.append(object);
		}
		list.add(sb.toString());
	}

	@Override
	public boolean arrow(DIRECTION d) {
		return END;
	}

}