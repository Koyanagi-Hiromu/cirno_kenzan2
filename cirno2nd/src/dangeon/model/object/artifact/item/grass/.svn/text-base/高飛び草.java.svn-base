package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.action.SpecialAction;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

/**
 * 高飛び層
 * 
 * @author Administrator
 * 
 */
public class 高飛び草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "高飛び草";
	public static final String item_name_sound = "たかとひそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.飛;

	public 高飛び草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		if (Player.me.equals(c)) {
			grassUse();
		} else {
			SpecialAction.高飛び(c);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "囲まれた時は迷わず使いたい。多くの緊急状況を回避できる優れたアイテム。出来ればＨＰに余裕があるときに飲もう。ちなみにラムネの味がする。";
	}

	@Override
	public boolean grassUse() {
		MainMap.addEffect(new GoodBadEffect(true));
		SE.STATUS_MEGUSURI.play();
		Player.me.setCondition(CONDITION.イリュージョン, 0);
		Message.set("回避の式が入力された");
		return true;
	}

}