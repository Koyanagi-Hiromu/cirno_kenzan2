package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.蘇我屠自古;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.ThunderDamage;
import dangeon.view.anime.ThunderEffect;
import dangeon.view.detail.MainMap;

public class 蘇我屠自古のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "蘇我屠自古のカード";
	public static final String item_name_sound = "そがとじこのかーと";
	private static final int composition = 5;
	private static final int item_str = 12;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内に雷を落とす程度の能力" };

	int damage = 35;

	public 蘇我屠自古のカード(Point p) {
		super(p, item_name, 1, composition, 蘇我屠自古.class);
		sim = ENCHANT_SIMBOL.雷;
	}

	@Override
	public String getCharacterShortName() {
		return "屠自子";
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
		return "攻撃時、時々雷を発生させるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("お腹が減ったでござる");
	}

	@Override
	public String getIllustlator() {
		return "Curee(AQUASTYLE)";
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
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 蘇我屠自古.class;
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
		SE.GOGOGO.play();
		MainMap.addEffect(new ThunderEffect(MassCreater.isPlayerInRoom()));
		for (Base_Enemy em : MapInSelect.getListRoomOrRoadInEnemy()) {
			ThunderDamage.thunderDamage(Player.me, Player.me, em, 15);
		}
		return true;
	}
}
