package dangeon.model.object.creature.player.class_job.bonus;

import java.awt.Point;
import java.util.ArrayList;

import main.util.半角全角コンバーター;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;

public class BonusItems extends BaseBonus {
	private static final long serialVersionUID = 1L;
	private Base_Artifact[] bonus_items;

	/**
	 * 
	 * @param clazz
	 *            コンストラクタがPoint.classのみ
	 * @param how_many
	 */
	public BonusItems(Class<? extends Base_Artifact> clazz, int how_many) {
		try {
			bonus_items = new Base_Artifact[how_many];
			for (int i = 0; i < bonus_items.length; i++) {
				bonus_items[i] = clazz.getConstructor(Point.class)
						.newInstance(Player.me.getMassPoint())
						.createSpellCard(false, 0);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void excute(ArrayList<Base_Artifact> list1,
			ArrayList<ENCHANT_SIMBOL> list2) {
		for (int i = 0; i < bonus_items.length; i++) {
			list1.add(bonus_items[i]);
		}
	}

	@Override
	public String getEffectExn() {
		String s = bonus_items[0].getColoredName();
		s = s.concat("×").concat(半角全角コンバーター.半角To全角数字(bonus_items.length));
		return s;
	}
}
