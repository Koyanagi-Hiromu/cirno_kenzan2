package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.多々良小傘;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 多々良小傘のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "多々良小傘のカード";
	public static final String item_name_sound = "たたらこかさのかーと";
	private static final int composition = 6;
	private static final int item_str = 6;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "周囲８マスの敵を金縛り状態にしてワープする程度の能力" };

	public 多々良小傘のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.驚;
	}

	@Override
	public String getCharacterShortName() {
		return "小傘";
	}

	@Override
	public String getDoter() {
		return "aozane";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=86531";
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
		return "わんわん";
	}

	@Override
	public String getIllustlator() {
		return "竜一";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=991469";
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
		return 多々良小傘.class;
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
		SE.KOGASA_SPELL.play();
		SE.STATUS_SIBIBI.play();
		MapInSelect.aroundInEnemyChengeCondition(CONDITION.麻痺, 0);
		Player.me.setMassPoint_Jump(MassCreater.getWarpPoint(null));
		return true;
	}

}
