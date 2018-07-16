package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.STAGE;

public class 九十九姉妹のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "九十九姉妹のカード";
	public static final String item_name_sound = "つくもしまいのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 9;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "" };

	public 九十九姉妹のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.音;
		list_stage.add(STAGE.輝針城);
	}

	private void effect(Base_Creature c) {
		Base_Creature used_creature = Player.me;
		DIRECTION direction = DIRECTION.getDirection(
				used_creature.getMassPoint(), c.getMassPoint());
		if (direction == DIRECTION.NEUTRAL) {
			// 反射された
			direction = c.direction.getReverse();
		}
		MapInSelect.吹き飛ばし(used_creature, "吹き飛ばし", c, direction, 10, 5);
	}

	@Override
	public String getCharacterShortName() {
		return "九十九";
	}

	@Override
	public String getDoter() {
		return "";
	}

	@Override
	public String getDoterURL() {
		return "";
	}

	@Override
	String getExplanToEnchant() {
		return "";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("");
	}

	@Override
	public String getIllustlator() {
		return "";
	}

	@Override
	public String getIllustlatorURL() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "通路で使用しても効果はない";
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return CardAttackEffect.class;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		if (status == STATUS.STR) {
			return item_str;
		} else {
			return item_def;
		}
	}

	@Override
	protected boolean spellUse() {
		for (Base_Creature c : MapList.getListEnemy()) {
			effect(c);
		}
		return true;
	}

}
