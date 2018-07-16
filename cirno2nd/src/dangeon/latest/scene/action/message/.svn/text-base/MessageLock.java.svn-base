package dangeon.latest.scene.action.message;

public abstract class MessageLock {

	public final String[] FIRST_MSG;

	public MessageLock() {
		FIRST_MSG = null;
	}

	public MessageLock(String... first_msg) {
		FIRST_MSG = first_msg;
		Message.ME.set(this);
	}

	public abstract void second();
}
