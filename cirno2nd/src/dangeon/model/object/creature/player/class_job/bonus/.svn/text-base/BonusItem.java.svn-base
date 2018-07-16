package dangeon.model.object.creature.player.class_job.bonus;

import java.util.ArrayList;

import main.util.半角全角コンバーター;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;

public class BonusItem extends BaseBonus {
	private static final long serialVersionUID = 1L;
	public int how_many = 1;

	public final Base_Artifact bonus_item;

	public BonusItem(Base_Artifact a) {
		this(a, 1);
	}

	public BonusItem(Base_Artifact s, int utsuwa) {
		if (s instanceof SpellCard) {
			s.composition_number = utsuwa;
			if (s.composition_number > 9) {
				s.composition_number = 9;
			}
		}
		bonus_item = s.createSpellCard(false, 0);
	}

	public BonusItem(int how_many, Base_Artifact a) {
		this(a, 1);
		this.how_many = how_many;
	}

	public BonusItem(SpellCard s) {
		this(s, s.composition_number);
	}

	@Override
	public void excute(ArrayList<Base_Artifact> list1,
			ArrayList<ENCHANT_SIMBOL> list2) {
		for (int i = 0; i < how_many; i++) {
			list1.add(bonus_item);
		}
	}

	@Override
	public String getEffectExn() {
		String s = bonus_item.getColoredName();
		if (how_many > 1) {
			s = s.concat("×").concat(半角全角コンバーター.半角To全角数字(how_many));
		}
		return s;
	}
}
