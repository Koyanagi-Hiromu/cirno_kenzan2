package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.player.Player;
import dangeon.util.STAGE;

public class 一輪雲山のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "一輪雲山のカード";
	public static final String item_name_sound = "いちりんうんさんのかーと";
	private static final int composition = 2;
	private static final int item_str = 2;
	private static final int item_def = 2;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "四回攻撃する程度の能力" };

	public 一輪雲山のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.追;
		list_stage.add(STAGE.星蓮船);
	}

	@Override
	public String getCharacterShortName() {
		return "一輪雲山のカード";
	}

	@Override
	public String getDoter() {
		return "かうざー";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=91937";
	}

	@Override
	String getExplanToEnchant() {
		return "時々追加で攻撃するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ブラボー！");
	}

	@Override
	public String getIllustlator() {
		return "ゴム";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=136544";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
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
		Player.me.additionalAttack();
		Player.me.additionalAttack();
		Player.me.additionalAttack();
		Player.me.normalAttack();
		return true;
	}

}
