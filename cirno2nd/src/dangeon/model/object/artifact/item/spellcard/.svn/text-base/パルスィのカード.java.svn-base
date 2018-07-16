package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.パルスィ;
import dangeon.model.object.creature.player.Player;

public class パルスィのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "パルスィのカード";
	public static final String item_name_sound = "ハルスィのかーと";
	private static final int composition = 5;
	private static final int item_str = 7;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "フロア中の敵のＨＰを自分と同じＨＰにする程度の能力" };

	public パルスィのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.嫉妬;
	}

	private void effect() {
		for (Base_Enemy em : MapList.getListEnemy()) {
			em.chengeHP(null, null, -(em.getHP() - Player.me.getHP()));
		}
	}

	@Override
	public String getCharacterShortName() {
		return "パルスィ";
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
		return "何か悪い事が起こると、次の攻撃が必中会心になるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ﾊﾟﾙﾊﾟﾙﾊﾟﾙﾊﾟﾙ");
	}

	@Override
	public String getIllustlator() {
		return "紅葉おろし";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=951956";
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
		return パルスィ.class;
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
		Message.set("「パルパルパルパルパルパル」");
		effect();
		return true;
	}
}
