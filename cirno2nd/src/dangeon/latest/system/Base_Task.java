package dangeon.latest.system;

public abstract class Base_Task {
	protected enum STATUS {
		CONTINUE, END;
	}

	protected STATUS status = STATUS.END;

	public final boolean isEnd() {
		return status.equals(STATUS.END);
	}

	public abstract void work();
}
