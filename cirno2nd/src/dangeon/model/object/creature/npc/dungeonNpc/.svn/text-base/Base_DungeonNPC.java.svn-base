package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

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
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

public abstract class Base_DungeonNPC extends Base_NPC {

	private static final long serialVersionUID = 1L;
	protected final String ASK;
	protected final Base_Map_Random MAP;

	/**
	 * 
	 * @param ask
	 *            質問文
	 */
	public Base_DungeonNPC(Point p, String name, CHARA_IMAGE c, String ask,
			Base_Map_Random map) {
		this(p, name, c, ask, ask, map);
	}

	/**
	 * 
	 * @param ask
	 *            質問文
	 */
	public Base_DungeonNPC(Point p, String name, CHARA_IMAGE c, String ask,
			String ask2, Base_Map_Random map) {
		super(p, name, c, false);
		ASK = map.getStoryManager_ClearFlag().hasFinished() ? ask2 : ask;
		MAP = map;
	}

	protected void ask() {
		new Conversation(IMLC, ASK, new ConvEvent() {
			@Override
			protected String getCancel() {
				return getMsg_cancel();
			}

			@Override
			protected String[] getExn() {
				return MAP.getExn();
			}

			@Override
			protected Book getYes() {
				return new Book() {

					@Override
					protected void work() {
						if (MAP.ITEM_MAX != null
								&& Belongings.getSize() > MAP.ITEM_MAX) {
							Message.set(getMsg_overItems());
						} else {
							intoDungeon();
						}
					}
				};
			}
		});
	}

	private boolean firstCheck() {
		return !MAP.getStoryManager_FirstFlag().hasFinished();
	}

	private void firstMessage() {
		sayFirst();
	}

	/**
	 * キャンセル時のメッセージを流す場合over-ride
	 * 
	 * @return　キャンセル時のメッセージ(nullでなし)
	 */
	protected String getMsg_cancel() {
		return null;
	}

	/**
	 * 「アイテムを持ちすぎだよ」を変更する場合over-ride
	 * 
	 * @return アイテムを持ちすぎている時に表示されるメッセージ
	 */
	protected String[] getMsg_overItems() {
		return new String[] { "アイテムを持ちすぎだよ" };
	}

	/**
	 * YUKARI_SPELLから変える場合over-ride
	 * 
	 * @return ダンジョンに入る時の効果音 nullで無音
	 */
	protected SE getSE() {
		return SE.YUKARI_SPELL;
	}

	/**
	 * 「ダンジョンに入りますか」「はい」のあとの処理
	 * 
	 */
	protected void intoDungeon() {
		if (getSE() != null)
			getSE().play();
		preCreaterFirstMap();
		MAP.createFirstMap();
	}

	@Override
	public void message() {
		if (firstCheck()) {
			firstMessage();
		} else {
			ask();
		}
	}

	protected void preCreaterFirstMap() {
	}

	protected void say(boolean end, String... msg) {
		ConvEvent cne = new ConvEvent() {
			@Override
			public void workAfterPush() {
				new BlackOut(new Task() {
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						Player.flag_clear = true;
						SE.FANFARE2.play();
						setTalks(Image_LargeCharacter.ANY);
						talks(MAP.getClassName(), "に挑戦できるようになった！");
						sayBrief();
					}
				}, new Task() {
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						MAP.getStoryManager_FirstFlag().saveThisFinished();
						TaskOnMapObject.reCreateNewMap();
					}
				});
			}
		};
		new Conversation(IMLC, cne, msg);
	}

	/**
	 * 最後にtalks(true,"~~~")を呼ぶこと<br />
	 */
	protected abstract void sayBrief();

	/**
	 * 最後にsay(true,"~~~")を呼ぶこと<br />
	 * ※sayBriefの条件です※
	 */
	protected abstract void sayFirst();

}
