package dangeon.latest.util.view_window;

import java.awt.Font;
import java.awt.Graphics2D;

import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.menu.Base_Scene_Menu.MenuContent;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.strage.CHEN_Strage;
import dangeon.view.util.StringFilter;

/**
 * 橙の倉庫用：各行の右側に在庫数（×個数）を描くアイテム選択ウィンドウ
 */
public class StackCountMenuWindow extends ItemSelectMenuWindow {

	public StackCountMenuWindow(Base_Scene_Menu_View view, int w, Font font) {
		super(view, w, font);
	}

	@Override
	protected void drawString(Graphics2D g, int x, int y) {
		// 倉庫リストではレア度ランクの代わりに在庫数を表示する
		StringFilter.setDrawSampleRank(false);
		try {
			super.drawString(g, x, y);
		} finally {
			StringFilter.setDrawSampleRank(true);
		}
		g.setFont(FONT);
		for (int i = 0; i < SCENE.getContentSize(); i++) {
			MenuContent c = SCENE.getContentSize(i);
			if (c.OBJECT instanceof Base_Artifact) {
				Base_Artifact a = (Base_Artifact) c.OBJECT;
				int count = CHEN_Strage.me.getCount(CHEN_Strage.keyOf(a));
				String s = "×".concat(半角全角コンバーター.半角To全角数字(count));
				int sw = g.getFontMetrics().stringWidth(s);
				StringFilter.drawEdgedString(g, s, getX(x, i) + W - 26 - sw,
						getY(g, y, i));
			}
		}
	}
}
