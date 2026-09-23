package dangeon.latest.scene.action.strage;

import java.awt.Color;

import main.util.半角全角コンバーター;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.first.adventure.wiki_item.ItemWiki_AllItem;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.disc.Disc_Detail;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.strage.CHEN_Strage;

/**
 * 橙の倉庫の「引き出す」リスト。アイテム図鑑と同じ構成で、
 * カテゴリごとの在庫を「名前 ×個数」で表示する。決定で1個ずつ引き出す。
 */
public class ChenStorage_List extends Item_List {

	/** カテゴリ番号（CHEN_Strageのカテゴリ並び） */
	public final int INDEX;

	/** trueなら「見る」モード（全種類を表示・引き出し不可） */
	public final boolean VIEW;

	/** 開き直し時に復元するカーソル位置（リスト全体でのインデックス） */
	private final int RESTORE;

	public ChenStorage_List(int index) {
		this(index, 0);
	}

	public ChenStorage_List(int index, int cursor) {
		this(index, cursor, false);
	}

	public ChenStorage_List(int index, int cursor, boolean view) {
		super(10, Scene_Action.getMe().KH, new ChenStorage_List_View(
				Scene_Action.getMe().CURRENT_VIEW),
				Scene_Action.getMe().CURRENT_VIEW, view ? "見る" : "引き出す", null,
				null, view ? CHEN_Strage.me.createViewSampleList(index)
						: CHEN_Strage.me.createSampleList(index), false);
		INDEX = index;
		VIEW = view;
		RESTORE = cursor;
		if (!LIST.isEmpty()) {
			int idx = Math.min(cursor, LIST.size() - 1);
			page = idx / COL;
		}
		Scene_Action.getMe().setNextScene(this);
	}

	@Override
	protected void action_enter(int index) {
		if (VIEW || LIST.isEmpty()) {
			// 「見る」は閲覧専用（図鑑と同じくカテゴリメニューに戻る）
			action_cancel();
			return;
		}
		Base_Artifact sample = LIST.get(getItemIndex());
		if (Belongings.isMax()) {
			Message.set("持ち物がいっぱいで受け取れない");
			return;
		}
		String key = CHEN_Strage.keyOf(sample);
		Base_Artifact a = CHEN_Strage.me.withdraw(key);
		if (a == null) {
			return;
		}
		Belongings.setItems(a);
		Message.set(a.getColoredName().concat("を引き出した"));
		// 個数表示を更新するためリストを開き直す（カーソル位置は維持）
		new ChenStorage_List(INDEX, getItemIndex());
	}

	@Override
	public Base_Scene getPreviousScene() {
		return new ChenStorage_Command(this);
	}

	/**
	 * アイテム図鑑と同じく、カテゴリの収集率（預けている種類数／全種類数）を％表示する<br>
	 * DISCの全種類数はタイトル数Nから計算する（1曲DISCがN種＋2曲DISCがN×N種）
	 */
	@Override
	public String getTop() {
		int max;
		int wiki = CHEN_Strage.wikiIndex(INDEX);
		if (wiki >= 0) {
			max = 0;
			for (String s : new ItemWiki_AllItem().get(wiki)) {
				if (!s.isEmpty()) {
					max++;
				}
			}
		} else {
			int n = Disc_Detail.values().length;
			max = n * n + n;
		}
		if (max == 0) {
			return super.getTop();
		}
		int stocked = CHEN_Strage.me.getStockedKinds(INDEX);
		int now = stocked < max ? stocked : max;
		StringBuilder sb = new StringBuilder();
		percent(sb, now, max);
		if (getMaxPage() > 1) {
			setPages(sb);
		}
		return sb.toString();
	}

	private void percent(StringBuilder sb, int now, int max) {
		int perc;
		if (max == 0) {
			perc = 0;
		} else {
			perc = now * 100 / max;
		}
		if (perc < 10) {
			sb.append(Color.PINK);
		} else if (perc < 40) {
			sb.append(Color.ORANGE);
		} else if (perc < 70) {
			sb.append(Color.GREEN);
		} else if (perc < 80) {
			sb.append(Color.BLUE);
		} else if (perc < 90) {
			sb.append(Color.CYAN);
		} else if (perc < 100) {
			sb.append(Color.YELLOW);
		} else {
			sb.append(Color.RED);
		}
		sb.append("【");
		if (perc < 100) {
			sb.append("　");
		}
		if (perc < 10) {
			sb.append("　");
		}
		sb.append(半角全角コンバーター.半角To全角数字(perc));
		sb.append("％】");
		sb.append(Color.WHITE);
	}

	@Override
	protected void initialX_Y() {
		super.initialX_Y();
		if (!LIST.isEmpty()) {
			int idx = Math.min(RESTORE, LIST.size() - 1);
			int cols = getNumberOfCols();
			y = idx % COL;
			if (y >= cols) {
				y = cols - 1;
			}
			if (y < 0) {
				y = 0;
			}
		}
	}
}
