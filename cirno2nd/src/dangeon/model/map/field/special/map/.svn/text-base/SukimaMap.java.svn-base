package dangeon.model.map.field.special.map;

import java.awt.Point;
import java.util.AbstractList;

import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.SE;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.device.HiddenDevice;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ヒソウテンソク;
import dangeon.model.object.creature.npc.Abstract_NPC;
import dangeon.model.object.creature.npc.藍NPC;
import dangeon.model.object.creature.player.save.SaveLoad;
import dangeon.util.Switch;

public class SukimaMap extends GouseiMap {
	private static final long serialVersionUID = 1L;

	public SukimaMap() {
		super(null);
	}

	@Override
	public BGM getBGM() {
		return BGM.recess;
	}

	@Override
	public String getClassName() {
		return "倉庫のスキマ";
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {

		if (Switch.test) {
			Base_Enemy hisou = new Abstract_NPC(CHARA_IMAGE.arrow,
					"Boss_Creater", getEntrancePoint().x + 2,
					getEntrancePoint().y - 3, false) {
				private static final long serialVersionUID = 1L;

				@Override
				public void message() {
					Point p = mass_point.getLocation();
					p.translate(-3, -0);
					MapList.addEnemy(new ヒソウテンソク(p, LV));
				}

			};
			add(hisou);
		}
		final 藍NPC RAN_STRAGE = (藍NPC) new 藍NPC(new Point(
				getEntrancePoint().x + 2, getEntrancePoint().y - 1))
				.setDirection(DIRECTION.LEFT);
		add(new HiddenDevice(new Point(getEntrancePoint().x + 2,
				getEntrancePoint().y)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean walkOnAction() {
				yukariMsg(RAN_STRAGE);
				return false;
			}
		});
		add(new Abstract_NPC(CHARA_IMAGE.八雲紫, "八雲紫", getEntrancePoint().x + 2,
				getEntrancePoint().y, false, true, "") {
			private static final long serialVersionUID = 1L;

			@Override
			public void message() {
				yukariMsg(RAN_STRAGE);
			}
		}.setDirection(DIRECTION.LEFT));
		add(RAN_STRAGE);
		return list_object;
	}

	private void yukariMsg(final 藍NPC RAN_STRAGE) {
		new Conversation(Image_LargeCharacter.八雲紫, "床に落ちてるアイテムは消えるけど大丈夫かしら？",
				new ConvEvent() {
					@Override
					protected Book getYes() {
						return new Book() {

							@Override
							protected void work() {
								SE.YUKARI_SPELL.play();
								new BlackOut("", new Task() {
									/**
							 *
							 */
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
										RAN_STRAGE.save();
										MassCreater mc = new MassCreater(
												new FairyPlace(), false);
										new SaveLoad(mc).saveContinue();
										TaskOnMapObject.setNewMap(mc);
									}
								});
							}
						};
					}
				});
	}
}
