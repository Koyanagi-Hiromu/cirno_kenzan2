package dangeon.model.object.artifact.item.food;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class 腐りきったおにぎり extends Base_Item {

	private static final long serialVersionUID = 1L;

	public 腐りきったおにぎり(Point p, String name) {
		super(p, name, 1, ITEM_CASE.FOOD);
		IM = Image_Artifact.FOOD;
		sim = ENCHANT_SIMBOL.腐;
	}

	@Override
	protected int enchantDefence(boolean b, Base_Creature creature, int damage) {
		return 0;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "正しくは腐ったかのように見えるおにぎり。食べると悪いことが起こる。どうしてもこれを食べなくてはいけない時は周囲に敵がいないときに食べよう。";
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public boolean itemUse() {
		Message.set(name, "の代わりに出現しました");
		return false;
	}
}
