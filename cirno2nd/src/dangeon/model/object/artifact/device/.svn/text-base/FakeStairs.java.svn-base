package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;

public class FakeStairs extends Stairs {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final int composition = 0;
	public boolean sight = true;

	public FakeStairs(Point p) {
		super(p, "ワープ装置", 1, 0, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.trap1_8;
		sim = ENCHANT_SIMBOL.バネ;
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "踏んだり使用したりするとどこか別の部屋へワープする",
				"どんな手段でも破壊することも移動させることもできない" };
	}

	@Override
	public void move() {
		walkOnAction();
	}

	@Override
	public boolean walkOnAction() {
		SE.SYSTEM_TRAP_ON.play();
		Message.set("ジャンプ台だ！");
		Point p = Player.me.getMassPoint().getLocation();
		DIRECTION d = Player.me.getDirection();
		p.translate(d.X, d.Y);
		p.translate(d.X, d.Y);
		for (int i = 0; i < 2 && !MassCreater.isWalkableFor(Player.me, p); i++) {
			p.translate(-d.X, -d.Y);
		}
		if (p.equals(Player.me.getMassPoint())) {
			Message.set("遠くへ飛ばされた");
			Player.me.setMassPoint_Jump(MassCreater.getWarpPoint(null));
		} else {
			Player.me.setMassPoint_ParabolaJumpAttack(p);
		}
		return false;
	}

	@Override
	public boolean walkOnAction(boolean b) {
		return walkOnAction();
	}

}
