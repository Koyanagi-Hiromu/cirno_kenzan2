package dangeon.model.object.creature.npc;

import java.awt.Point;

import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.staff.一時しのぎの杖;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;

public abstract class Base_NPC extends Base_Enemy {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final boolean MOVE;
	protected final Image_LargeCharacter IMLC;

	public Base_NPC(Image_LargeCharacter IMLC, Point p, String name,
			CHARA_IMAGE c, boolean move) {
		super(p, name, 0);
		MOVE = move;
		if (c != null)
			IM = c;
		LV = 0;
		this.IMLC = IMLC;
	}

	public Base_NPC(Point p, String name, boolean move) {
		this(p, name, null, move);
	}

	public Base_NPC(Point p, String name, CHARA_IMAGE c, boolean move) {
		this(Image_LargeCharacter.get(name), p, name, c, move);
	}

	@Override
	public void action() {
		if (!MOVE) {
			enemy_actioned = true;
		} else {
			enemyMovePossible();
		}
	}

	@Override
	public boolean hasMessage() {
		return true;
	}

	@Override
	public boolean isPlayerSide() {
		return true;
	}

	@Override
	protected int itemDropParcent() {
		return 0;
	}

	@Override
	public boolean itemHitEffect(Base_Artifact a, boolean ento) {
		if (a instanceof 一時しのぎの杖) {
			Message.set("不思議なちからがはたらいて無効化された");
			return false;
		}
		return true;
	}

	@Override
	public abstract void message();

	@Override
	public void moving() {
		if (moveConditionChecker()) {
			enemy_actioned = true;
			return;
		}
		testTroop();
	}

	protected void rep(String... strings) {
		new Conversation(strings);
	}

	@Override
	protected final void saveKO() {
	}

	protected void say(String... strings) {
		new Conversation(IMLC, strings);
	}

	protected void setRep(Image_LargeCharacter imlc) {
		Conversation.previous_player = imlc;
	}

	protected void setTalks(Image_LargeCharacter imlc) {
		Conversation.previous_opponent = imlc;
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

	@Override
	public boolean staffHitCheck(Base_Artifact a) {
		if (a instanceof 一時しのぎの杖) {
			Message.set("不思議なちからがはたらいて無効化された");
			return false;
		}
		return super.staffHitCheck(a);
	}

	protected void talks(boolean end, String... msg) {
		ConvEvent cne = new ConvEvent() {
			@Override
			public void workAfterPush() {
				Player.flag_clear = false;
			}
		};
		new Conversation(Conversation.previous_opponent, cne, msg);
	}

	protected void talks(String... strings) {
		new Conversation(Conversation.previous_opponent, strings);
	}

	@Override
	public void setSize() {}
}
