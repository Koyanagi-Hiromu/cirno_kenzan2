package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.犬走椛;
import dangeon.model.object.creature.player.Player;

public class 犬走椛のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "犬走椛のカード";
	public static final String item_name_sound = "いぬはしりもみしのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "透視状態になる程度の能力" };

	public 犬走椛のカード(Point p) {
		super(p, item_name, 1, composition, 犬走椛.class);
		sim = ENCHANT_SIMBOL.必中;
	}

	@Override
	public String getCharacterShortName() {
		return "椛";
	}

	@Override
	public String getDoter() {
		return "slnchyt";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1345491";
	}

	@Override
	String getExplanToEnchant() {
		return "攻撃が必ず当たるようになるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("見えます見えます");
	}

	@Override
	public String getIllustlator() {
		return "鬼頭りん";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2151896";
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
		return 犬走椛.class;
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
		SE.LIGHT_ON.play();
		Player.me.setCondition(CONDITION.透視, 0);
		return true;
	}

}
