package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ルーミア;
import dangeon.model.object.creature.player.Player;

public class ルーミアのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "ルーミアのカード";
	public static final String item_name_sound = "るーみあのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 3;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "１５ターンの間暗闇に隠れる程度の能力" };

	public ルーミアのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.食;
	}

	@Override
	public String getCharacterShortName() {
		return "ルーミア";
	}

	@Override
	public String getDoter() {
		return "ひろひろ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1225179";
	}

	@Override
	String getExplanToEnchant() {
		return "時々投擲物を食べるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("くやらみDA");
	}

	@Override
	public String getIllustlator() {
		return "fe";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2443628";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "使用すると自分も動けないがエネミーが自分を見失う";
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return ルーミア.class;
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
		SE.STATUS_GOOD.play();
		Player.me.setCondition(CONDITION.やりすごし, 0);
		return true;
	}

}
