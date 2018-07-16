package dangeon.model.object.creature.npc.second;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.SE;
import main.util.BlackOut;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.town.map.EternalHouse;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.map.field.town.map.UnderGroundHouse;
import dangeon.model.object.creature.npc.Base_NPC;

public class NPCおりん extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public final ArrayList<Base_Map> list;

	public NPCおりん(int x, int y) {
		super(new Point(x, y), "火焔猫燐", CHARA_IMAGE.火焔猫燐, false);
		list = new ArrayList<Base_Map>();
		list.add(new EternalHouse());
		list.add(new UnderGroundHouse());
	}

	private void confirmA() {
		String msg = "どこに行きたいんだい？";
		new Conversation(Image_LargeCharacter.火焔猫燐, msg, new ConvEvent() {
			@Override
			protected Book getContent1() {
				return createBook(0);
			}

			@Override
			protected Book getContent2() {
				return createBook(1);
			}

		});
	}

	private void confirmB() {
		String msg = "妖精の踊り場に帰るかい？$" + Color.GRAY.toString()
				+ "（アイテムを持ったまま帰還します）";
		new Conversation(Image_LargeCharacter.火焔猫燐, msg, new ConvEvent() {
			@Override
			protected Book getYes() {
				return new Book() {

					@Override
					protected void work() {
						move(new FairyPlace());
					}
				};
			}
		});
	}

	private Book createBook(final int i) {
		if (list.size() <= i) {
			return null;
		} else {
			return new Book(list.get(i).getMapName()) {

				@Override
				protected void work() {
					move(list.get(i));
				}
			};
		}
	}

	@Override
	public void message() {
		if (PresentField.get().getClass().equals(FairyPlace.class))
			confirmA();
		else
			confirmB();
	}

	protected void move(final Base_Map bm) {
		SE.SYSTEM_STAIR_STEP.play();
		Message.set("そらっ　しっかりつかまってな！");
		new BlackOut(bm, new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				TaskOnMapObject.setNewMap(new MassCreater(bm, bm
						.getEntrancePoint(), false));
			}
		});
	}
}
