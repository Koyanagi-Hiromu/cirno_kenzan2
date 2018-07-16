package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.config.Config;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.慧音の最終問題;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class 慧音NPC extends Base_Dungeon_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 慧音NPC(Point p) {
		super(p, "慧音", CHARA_IMAGE.上白沢慧音, false);
	}

	private Task getTask() {
		return new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				new Task() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						move();
					}
				}.work_appointment();
			}
		};
	}

	private void hack() {

		final String[] hack = getSelects("慧音の最終問題.txt", "慧");
		if (hack.length == 0) {
			Message.set("最終問題のハックが正しく登録されていないようだな@");
			Message.set("アイテム・エネミー・トラップの$３つのフォルダにファイルが存在しているか確認してくれ@");
			Message.removeILC();
			return;
		}
		Message.set("アレンジ問題に向かうのか？$（全９９Ｆ／持ち込み不可）@");
		new Base_SelectBox(hack) {
			@Override
			public void pushEnter(int y) {
				end();
				if (!Belongings.isEmpty()) {
					Message.set("このダンジョンにはアイテムを持ち込めないぞ");
				} else {
					MassCreater.setHackName("慧".concat(hack[y]));
					Message.set("チルノは勇気があるな$頑張るといい");
					move();
				}
			}
		};
		Message.removeILC();
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.上白沢慧音);
		if (Config.isHack_playing()) {
			hack();
			return;
		} else {
			String str = (Config.getStory() < 10000) ? "私の最終問題に向かうか？$（全９９Ｆ／持ち込み不可）@"
					: "私の最終問題をまた解いてくれるのか？$（全９９Ｆ／持ち込み不可）@";
			Message.set(str);
			new Base_SelectBox("はい", "いいえ") {
				@Override
				public void pushEnter(int y) {
					end();
					if (y == 0) {
						if (!Belongings.isEmpty()) {
							Message.set("このダンジョンにはアイテムを持ち込めないぞ");
						} else {
							if (Config.isStory(1200)) {
								Config.setStory(1300);
							}
							Message.set("チルノは勇気があるな$頑張るといい");
							move();
						}
					}
				}
			};
			Message.removeILC();
		}
	}

	protected void move() {
		// 慧音→19,19
		Point p = Player.me.getMassPoint();
		if (p.x == 18) {
			if (p.y == 19) {
				Player.me.direction = DIRECTION.DOWN_RIGHT;
			} else {
				Player.me.direction = DIRECTION.RIGHT;
			}
			Player.me.setMassPoint_WalkLike(new Point(19, 20), 1, getTask());
		} else if (p.x == 19) {
			Player.me.direction = DIRECTION.RIGHT;
			Player.me.setMassPoint_WalkLike(new Point(20, 20), 1, getTask());
		} else if (p.x == 20) {
			if (p.y == 19) {
				Player.me.direction = DIRECTION.RIGHT;
			} else {
				Player.me.direction = DIRECTION.UP_RIGHT;
			}
			Player.me.setMassPoint_WalkLike(new Point(21, 19), 1, getTask());
		} else {
			Player.me.direction = DIRECTION.UP;
			Player.me.setMassPoint_WalkLike(new Point(21, 18), 1, new Task() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					Player.me.resetStatus();
					for (Enchant e : Enchant.values()) {
						e.forceToRemove();
					}
					Belongings.getListItems().clear();
					Player.me.resetAll();
					Enchant.allRemove();
					Belongings.setItems(new 大きなおにぎり(Player.me.getMassPoint())
							.createSpellCard(false, 0));
					new MassCreater(慧音の最終問題.ME, null, true).createFirstMap(2);
				}
			});
		}

	}
}
