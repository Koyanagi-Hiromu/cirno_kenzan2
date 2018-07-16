package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.creature.Base_Creature;

public class Board extends Base_Device {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int composition = 0;
	public boolean sight = true;
	public static String name = "説明書";
	private final String MSG;

	public Board(Point p, String msg) {
		super(p, name, 1, composition, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.NULL;
		MSG = msg;
	}

	protected int enchantAttack(boolean normal, Base_Creature c, int damage) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "装備:", "使用:", "" };
	}

	@Override
	public boolean itemUse() {
		walkOnAction();
		return false;
	}

	@Override
	public void itemUseThis() {
		walkOnAction();
	}

	@Override
	public boolean walkOnAction() {
		Message.set(MSG);
		return false;
	}

}
