package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;

public class 明かりの書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "明かりの書";
	public static final String item_name_sound = "あかりのしょ";
	public static final int composition = 0;
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.冴;

	public 明かりの書(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "透視状態になりマップを把握できる。その上暗いダンジョンでは視界が広くなる。事前にピンチを避けられるため出来れば深層に持っていきたい";
	}

	@Override
	public void scroolUse() {
		SE.LIGHT_ON.play();
		Player.me.setCondition(CONDITION.透視, 0);
		MassCreater.setLight();
		MassCreater.setAllMassOnMiniMap();
	}

}