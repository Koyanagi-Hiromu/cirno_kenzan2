package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.NextFloor;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.special.map.EndingMap;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.落とし穴Wait;

public class 落とし穴の罠 extends Base_Trap {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 罠が発動する確立
	 */
	protected static final int PARCENTAGE = 50;
	/**
	 * 罠の壊れ安さ
	 */
	protected static final int DURABILITY = 0;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "落とし穴の罠";

	private final Image_Artifact[] ims = { Image_Artifact.trap1_16,
			Image_Artifact.pitfall_002, Image_Artifact.pitfall_003,
			Image_Artifact.pitfall_004 };

	public 落とし穴の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_16;
		sim = ENCHANT_SIMBOL.落とし穴;
	}

	@Override
	protected Base_Artifact action(Base_Artifact a) {
		Message.set("しかし軽くて起動しなかった");
		return super.action(a);
	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		if (c instanceof Player) {
			if (PresentField.get() instanceof EndingMap) {
				Message.set("しかし起動しなかった");
				return;
			}
			if (!PresentField.isRandomField()) {
				Message.set("しかし起動しなかった");
				return;
			}
			TurnSystemController.setTurnFinish();
			Player.me.setAnimation(new 落とし穴Wait(this, Player.me, new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					SE.SYSTEM_DAMAGED_NORMAL.play();
					// EnchantSpecial.setFlagNeglectHeal(true);
					EnchantSpecial.setFlagNeglectHeal(true);
					Damage.damage(null, "落下の衝撃によって", "落下の衝撃によって倒れた", null,
							Player.me, 5);
					if (Player.me.getHP() > 0) {
						NextFloor.next(PresentField.get());
						TurnSystemController.setTurnFinish();
					}
				}
			}));
		} else {
			Message.set("しかし起動しなかった");
		}
	}

	private String damageMessage() {
		return "落下の衝撃で倒れた";
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むと１つ下のフロアに行く。ピンチのときは階段代わりに踏んでしまおう。頑張って敵を倒したモンスターハウスで踏むととても悔しい。素振りを忘れずに。";
	}

	public void setIM(int index) {
		if (index > ims.length - 1) {
			index = ims.length - 1;
		}
		IM = ims[index];
	}
}
