package connection.sv_cl;

import java.io.Serializable;

final class Simbol implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int FISRT = 1, EXCHANGE_OK = 2, DUNGEON = 3;

	public static Simbol create(int i) {
		return new Simbol(i);
	}

	public final int identifiler;

	private Simbol(int i) {
		identifiler = i;
	}

	public boolean is(int i) {
		return identifiler == i;
	}
}