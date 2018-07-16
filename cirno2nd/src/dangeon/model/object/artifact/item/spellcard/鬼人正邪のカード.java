package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.鬼人正邪;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 鬼人正邪のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "鬼人正邪のカード";
	public static final String item_name_sound = "きしんせいしゃのかーと";
	private static final int composition = 5;
	private static final int item_str = 10;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "" };

	public 鬼人正邪のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.天;
	}

	private void effect(Base_Creature em, CONDITION c, int time) {
		MainMap.addEffect(
				new DoronEffect(em.getMassPoint(), null, true, false), false);
		em.setCondition(c, time);
		hpChange(em);
	}

	@Override
	public String getCharacterShortName() {
		return "雷鼓";
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
		return 鬼人正邪.class;
	}

	private void hpChange(Base_Creature em) {
		int max = em.getMAX_HP();
		int hp = em.getHP();
		int damaged = max - hp;
		int damage = hp - damaged;
		if (damage >= hp) {
			damage = hp - 1;
		}
		em.chengeHP(Player.me, "体を裏返されて倒れた", -damage);
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
		int time = 20;
		CONDITION c = CONDITION.天邪鬼;
		Point p = Player.me.getMassPoint().getLocation();
		boolean flag = false;
		if (MassCreater.getMass(p).ROOM) {
			for (Base_Enemy em : MapList.getListEnemy()) {
				if (MassCreater.isPointInTheSameRoomInEntrance(p,
						em.getMassPoint())) {
					flag = true;
					effect(em, c, time);
				}
			}
		} else {
			for (Base_Creature em : MapList.getListAroundCreature(p)) {
				flag = true;
				effect(em, c, time);
			}
		}
		if (flag) {
			SE.AMANOJACK.play();
			return true;
		} else {
			return false;
		}
	}

}
