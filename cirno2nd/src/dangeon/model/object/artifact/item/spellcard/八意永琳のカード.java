package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.八意永琳;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.Poison;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

public class 八意永琳のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "八意永琳のカード";
	public static final String item_name_sound = "やこころえいりんのかーと";
	private static final int composition = 4;
	private static final int item_str = 7;
	private static final int item_def = 7;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "ＨＰが全回復する程度の能力" };

	public 八意永琳のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.医;
	}

	@Override
	public String getCharacterShortName() {
		return "永琳";
	}

	@Override
	public String getDoter() {
		return "まるせん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1733063";
	}

	@Override
	String getExplanToEnchant() {
		return "薬草や妹切草の効果が上がるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return "弓矢のせいで……";
	}

	@Override
	public String getIllustlator() {
		return "りず。";
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
		return "草の使用効果だけでなく草の印の効果も上昇する";
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		// TODO 自動生成されたメソッド・スタブ
		return 八意永琳.class;
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
		MainMap.addEffect(new GoodBadEffect(false));
		Poison.effect(Player.me, 2, false, false);
		SE.STATUS_PIYOPIYO.play();
		Player.me.setCondition(CONDITION.混乱, 2);
		SE.STATUS_SPEEDY.play();
		Player.me.setCondition(CONDITION.倍速, 2);
		SE.SYSTEM_CURSE.play();
		Player.me.setCondition(CONDITION.邪法, -2);
		Player.me.chengeHP(null, null, 150);
		Player.me.setCondition(CONDITION.復活, 0);
		return true;
	}

}
