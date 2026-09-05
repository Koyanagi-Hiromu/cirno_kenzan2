package dangeon.latest.scene.action.strage;

import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.strage.CHEN_Strage;

/**
 * 橙の倉庫の「引き出す」リスト。アイテム図鑑と同じ構成で、
 * カテゴリごとの在庫を「名前 ×個数」で表示する。決定で1個ずつ引き出す。
 */
public class ChenStorage_List extends Item_List {

	/** カテゴリ番号（CHEN_Strageのカテゴリ並び 0〜6） */
	public final int INDEX;

	/** 開き直し時に復元するカーソル位置（リスト全体でのインデックス） */
	private final int RESTORE;

	public ChenStorage_List(int index) {
		this(index, 0);
	}

	public ChenStorage_List(int index, int cursor) {
		super(10, Scene_Action.getMe().KH, new ChenStorage_List_View(
				Scene_Action.getMe().CURRENT_VIEW),
				Scene_Action.getMe().CURRENT_VIEW, "引き出す", null, null,
				CHEN_Strage.me.createSampleList(index), false);
		INDEX = index;
		RESTORE = cursor;
		if (!LIST.isEmpty()) {
			int idx = Math.min(cursor, LIST.size() - 1);
			page = idx / COL;
		}
		Scene_Action.getMe().setNextScene(this);
	}

	@Override
	protected void action_enter(int index) {
		if (LIST.isEmpty()) {
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
