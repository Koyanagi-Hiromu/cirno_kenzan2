package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import main.res.CHARA_IMAGE;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.魂魄妖夢のカード;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusSimbol;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;

public class Class半人半霊 extends BaseClassJob {

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.薬));
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.妹));
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSimbol(ENCHANT_SIMBOL.殺));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, "半死人属性を得る"));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new 魂魄妖夢のカード(p), 6));
	}

	@Override
	public CHARA_IMAGE getCharaImage() {
		return CHARA_IMAGE.魂魄妖夢;
	}

	@Override
	public SpellCard getRep() {
		return new 魂魄妖夢のカード(Player.me.getMassPoint());
	}

	@Override
	public String getSwitchExp() {
		return "仕分けで５０点以上を出した";
	}
}
