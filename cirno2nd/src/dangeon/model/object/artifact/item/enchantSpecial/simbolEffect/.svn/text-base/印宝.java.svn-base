package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.detail.View_Sider;

public class 印宝 extends EnchantSpecial {
	/**
	 * new
	 * 
	 * @return
	 */
	public static Base_Artifact effect() {
		Base_Artifact a = MapList.getArtifact(Player.me.getMassPoint());
		if (a != null)
			return a;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.宝)) {
			// Fate+2だと満腹度は８歩に１減る
			// つまり800ターンで100減る
			// Fate+2だと楽しくなるまで700ターン
			if (PresentField.get().isHaraheru()) {
				if (parcentCheck(2, ENCHANT_SIMBOL.宝)) {
					if (new R().is(15)) {
						SE.ZAKUZAKU.play();
						a = ItemTable.itemReturn(Player.me.getMassPoint(),
								false);
						ItemFall.itemFall(a);
						View_Sider
								.setInformation(a.getColoredName(), "を落としました");
						return a;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static int effect(int i) {
		// if (!EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
		// ENCHANT_SIMBOL.宝)) {
		return i;
		// }
		// return SetEnchantCard.isSetCard(寅丸星のカード.class) ? i * 8 : i * 4;
	}
}
