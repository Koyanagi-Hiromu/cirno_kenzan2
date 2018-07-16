package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.TrapTable;
import dangeon.model.map.ItemFall;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;

/**
 * 
 * @author Administrator
 * 
 */
public class 罠師の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "罠師の書";
	public static final String item_name_sound = "わなしのしょ";
	private static final int item_use_level1 = 10;
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.罠;

	public 罠師の書(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "使っても無くならず足元に落ちる。読むと氷状態もリセットされる。";
	}

	@Override
	public boolean isParmitThisItemFreeze() {
		return false;
	}

	private void itemEffect(int i) {
		TrapTable.addMapList(i, null);
	}

	@Override
	public void scroolUse() {
		罠師の書 a = new 罠師の書(Player.me.getMassPoint());
		ItemFall.itemFall(a);
		if (PresentField.get().isHaraheru()) {
			SE.ZAKUZAKU.play();
			itemEffect(item_use_level1);
			Message.set("このフロアにたくさんワナが出現した");
		} else {
			Message.set("ダンジョンで読まないと効果が無いようだ");
		}
	}

}