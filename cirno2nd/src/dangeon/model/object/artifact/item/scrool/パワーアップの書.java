package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.OuraEffect;

/**
 * 
 * @author Administrator
 * 
 */
public class パワーアップの書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "超人の書";
	public static final String item_name_sound = "ちょうしんのしょ";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.力１;

	public パワーアップの書(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "与えるダメージが増える上に受けるダメージもカットされる。まさにパワーアップ。これを読めば君もスーパーマンだ！だからといって時間は戻すことはできません。";
	}

	@Override
	public void scroolUse() {
		Player.me.setAnimation(new OuraEffect(Player.me, new Task() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Player.me.setCondition(CONDITION.パワーアップ, 0);
				Player.me.setCondition(CONDITION.目薬, 0);
				Message.set("ちからがみなぎった");
			}
		}));
	}

}