package dangeon.model.object.creature.player.strage;

import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.creature.npc.守矢賽銭箱;
import dangeon.model.object.creature.npc.賽銭箱;
import dangeon.model.object.creature.player.Belongings;

public class SAISEN_Strage extends Base_Strage {
	private final 賽銭箱 SAISEN;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SAISEN_Strage(守矢賽銭箱 守矢賽銭箱) {
		super(10, 1);
		SAISEN = null;
	}

	public SAISEN_Strage(賽銭箱 saisen) {
		super(10, 1);
		SAISEN = saisen;
	}

	public SAISEN_Strage(賽銭箱 saisen, int cols) {
		super(10, cols);
		SAISEN = saisen;
	}

	private void get(Base_Artifact a) {
		if (Belongings.isMax()) {
			Message.set("持ち物がいっぱいで取り出せない");
		} else {
			if (a instanceof Arrow) {
				switch (((Arrow) a).takeIt()) {
				case -1:
					// 持ってたけど９９本あって取り出せなかった
					return;
				case 1:
					TurnSystemController.callMeToStartEnemyTurn();
					remove(a);
					return;
				case 0:
					// 持ってない
					break;
				}
			}
			Message.set(a.getColoredName(), "を取り出した");
			TurnSystemController.callMeToStartEnemyTurn();
			remove(a);
			Belongings.setItems(a);
		}
	}

	public void justWatch() {
		if (getList().isEmpty())
			Message.set("もう何も入っていない");
		else
			new Item_List("見る", new Book_Item() {
				@Override
				public void work(Base_Artifact a) {
				}
			}, getList());
	}

	public void takeIt() {
		if (getList().isEmpty())
			Message.set("もう何も入っていない");
		else if (Belongings.isMax()) {
			Message.set("持ち物がいっぱいで取り出せない");
		} else {
			new Item_List(new Book_Item() {
				@Override
				public void work(Base_Artifact a) {
					if (SAISEN == null) {
						get(a);
					} else if (!MassCreater.getMass(SAISEN.getMassPoint())
							.isHoly() && SAISEN.isGray()) {
						terrible(a);
					} else {
						get(a);
					}
				}
			}, getList());
		}
	}

	public void takeMany() {
		if (getList().isEmpty())
			Message.set("もう何も入っていない");
		else if (Belongings.isMax()) {
			Message.set("持ち物がいっぱいで取り出せない");
		} else {
			new Item_List(new Book_Item() {
				@Override
				public void work(Base_Artifact a) {
					if (SAISEN == null) {
						get(a);
					} else if (!MassCreater.getMass(SAISEN.getMassPoint())
							.isHoly() && SAISEN.isGray()) {
						terrible(a);
					} else {
						get(a);
					}
				}
			}, getList()) {
				@Override
				protected Book getMultiBook(
						final Base_Artifact... selected_artifacts) {
					return new Book() {

						@Override
						protected void work() {
							for (Base_Artifact a : selected_artifacts) {
								get(a);
							}
							setNextScene(PREVIOUS_SCENE);
						}
					};
				}
			};
		}
	}

	private void terrible(final Base_Artifact a) {
		Message.set("取り出すと怒られる予感がする…！@");
		new Base_SelectBox("やめておく", "それでも取り出す") {
			@Override
			public void pushEnter(int y) {
				end();
				if (y == 1) {
					get(a);
					new Task() {
						/**
						 *
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							SAISEN.punish();
						}
					}.work_appointment();
				}
			}
		};
	}
}
