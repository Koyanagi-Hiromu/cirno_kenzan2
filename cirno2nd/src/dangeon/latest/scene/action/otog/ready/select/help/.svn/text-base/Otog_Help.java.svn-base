package dangeon.latest.scene.action.otog.ready.select.help;

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

public class Otog_Help extends Base_Scene {
	public final StringOnlyWindow WINDOW;

	public Otog_Help(KeyHolder kh, Base_View bv) {
		super(kh, new Otog_Help_View(bv));
		ArrayList<String> list = new ArrayList<String>();
		add(list, Color.CYAN, "◆", "SELECT MUSIC", "◆");
		add(list, StringFilter.NUMBERS, "GAUGE ", Color.WHITE, ": 難易度です");
		add(list, "        ゲージの増減量が決定されます");
		add(list, "        クリアすると対応した色のランプが光ります");
		add(list, Color.ORANGE, "        新たにランプが光るとポイントがもらえます");
		add(list, StringFilter.NUMBERS, "RANDOM", Color.WHITE,
				": レーンや方向がシャッフルされます");
		add(list, Color.ORANGE, "        ON", Color.RED, "a", Color.WHITE,
				"はおにぎりのレーンのみが、");
		add(list, Color.ORANGE, "        ON", Color.GREEN, "b", Color.WHITE,
				"は", Color.ORANGE, "ON", Color.RED, "a", Color.WHITE,
				"に加えて方向がシャッフルされます");
		add(list, Message.HORIZON);
		add(list, Color.CYAN, "◆", "PLAY&RESULT", "◆");
		add(list, StringFilter.NUMBERS, "QUIT ", Color.WHITE, ": ",
				Color.ORANGE, "未使用のキーを長押し", Color.WHITE, "すると", Color.ORANGE,
				"強制終了", Color.WHITE, "します");
		add(list, StringFilter.NUMBERS, "RETRY", Color.WHITE, ": ",
				Color.ORANGE, "RESULT中に方向キー", Color.WHITE, "を押すと",
				Color.ORANGE, "リトライ", Color.WHITE, "します");
		add(list, Message.HORIZON);
		add(list, Color.CYAN, "◆", "CONFIG", "◆");
		add(list, StringFilter.NUMBERS, "CIRNO ", Color.WHITE,
				": 十キー入力の配置を変更します");
		add(list, StringFilter.NUMBERS, "ADJUST", Color.WHITE,
				": PERFECTのタイミングを変更します");
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