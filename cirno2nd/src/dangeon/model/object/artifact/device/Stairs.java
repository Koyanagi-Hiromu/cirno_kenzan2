package dangeon.model.object.artifact.device;

import java.awt.Color;
import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.NextFloor;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.view.detail.View_Sider;
import dangeon.view.detail.View_Sider_Info;

public class Stairs extends Base_Device {
	private class MyConvEvent extends ConvEvent {
		private final boolean END;

		public MyConvEvent(boolean end) {
			super(Enchant.CL_DEF.toString(), "ポケット", Color.WHITE.toString(),
					"のアイテムは失われます$", "それでも進みますか？");
			END = end;
		}

		@Override
		protected Book getYes() {
			return new Book() {
				@Override
				protected void work() {
					View_Sider_Info.reset();
					Belongings.removePockets();
					if (END)
						NextFloor.saveEnd(bm);
					else
						NextFloor.next(bm);
				}
			};
		}

		@Override
		protected int pushCancelAction() {
			View_Sider_Info.reset();
			return 0;
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final int composition = 0;
	public boolean sight = true;

	public static String name = "階段";

	protected Base_Map bm = null;

	public Stairs(Point p) {
		super(p, name, 1, composition, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.STAIRS;
	}

	public Stairs(Point p, Base_Map bm) {
		this(p);
		this.bm = bm;
	}

	protected Stairs(Point p, String name, int level, int composition_number,
			ITEM_CASE item_case, GROW_RATE grow_rate, boolean mobile) {
		super(p, name, 1, composition, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.STAIRS;
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "*テスト用アイテム*", "これはデバグ用アイテムです",
				"何かが原因で拾っても悪用はほどほどに！" };
	}

	public String[] getSlection() {
		return (PresentField.get().isNextForbbitenSave()) ? new String[] {
				"下りる", "そのまま" } : (isVisible()) ? new String[] { "下りる", "そのまま",
				"セーブして中断" } : new String[] { "進む", "そのまま", "セーブして中断" };
	}

	@Override
	public boolean itemUse() {
		move();
		return false;
	}

	@Override
	public void itemUseThis() {
		move();
	}

	public void move() {
		if (Belongings.isOver30()) {
			warningInfo();
			new MyConvEvent(false);
		} else {
			NextFloor.next(bm);
		}
	}

	public void saveEnd() {
		if (Belongings.isOver30()) {
			warningInfo();
			new MyConvEvent(true);
		} else {
			NextFloor.saveEnd(bm);
		}
	}

	public Stairs setInVisible() {
		visible = false;
		return this;
	}

	@Override
	public boolean walkOnAction() {
		TaskOnMapObject.setTaskStairs(this);
		return false;
	}

	/**
	 * ダッシュして乗ったとき
	 * 
	 * @param b
	 *            　意味なし
	 * @return
	 */
	@Override
	public boolean walkOnAction(boolean b) {
		if (visible) {
			Message.set(getColoredName(), "の上に乗った");
		} else {
			walkOnAction();
		}
		return false;
	}

	private void warningInfo() {
		View_Sider.setInformation(Color.RED.toString(), "以下のアイテムがなくなります");
		for (Base_Artifact a : Belongings.getPocket())
			View_Sider.setInformation(a.getColoredName());
		View_Sider.setInformation("メニュー中に射撃キ―を押すと複数選択できます");
	}

	@Override
	public boolean waterAction() {
		setMassPoint(MassCreater.getStairsIP());
		MapList.addArtifact(this);
		Message.set("階段はどこかへワープした");
		return false;
	}

}
