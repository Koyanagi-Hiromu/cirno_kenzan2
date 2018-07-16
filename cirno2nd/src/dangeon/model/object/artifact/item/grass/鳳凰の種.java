package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.FireEffect;

/**
 * 
 * @author Administrator
 * 
 */
public class 鳳凰の種 extends Base_Grass {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "鳳凰の種";
	public static final String item_name_sound = "ほうおうのたね";
	public ENCHANT_SIMBOL simbol = null;

	public 鳳凰の種(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "安心";
	}

	@Override
	public boolean grassUse() {
		Player.me.setAnimation(new FireEffect(Player.me, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Message.set("鳳凰にとりつかれた");
				Player.me.setCondition(CONDITION.復活, 0);
			}
		}));
		return true;
	}

	@Override
	protected boolean isAbleToBeHittedChecked(Base_Creature c) {
		return false;
	}
}