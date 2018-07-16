package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.spellcard.パルスィのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;

public class 印妬 {
	public static int effect() {
		int val = 0;
		int count = 2;
		int x = Player.me.getMassPoint().x;
		int y = Player.me.getMassPoint().y;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if (MapList.getEnemy(x + i, y + j) != null) {
					val += count;
				}
			}
		}
		return SetEnchantCard.isSetCard(パルスィのカード.class) ? val * 2 : val;
	}
}
