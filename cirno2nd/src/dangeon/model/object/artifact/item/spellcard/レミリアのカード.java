package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.レミリア;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class レミリアのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "レミリアのカード";
	public static final String item_name_sound = "れみりあのかーと";
	private static final int composition = 4;
	private static final int item_str = 10;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵を吸血する程度の能力" };

	int blood = 25;

	public レミリアのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.血液;
	}

	@Override
	public String getCharacterShortName() {
		return "レミリア";
	}

	@Override
	public String getDoter() {
		return "AJIA";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=709125";
	}

	@Override
	String getExplanToEnchant() {
		return "攻撃すると時々HPが回復するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("カリスマ！！");
	}

	@Override
	public String getIllustlator() {
		return "よー";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=630924";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "敵の体力を吸い取るぞ！";
	}

	@Override
	protected String[] getSecondExplan() {
		return new String[] { "", "" };
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return レミリア.class;
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
		int counter = 0;
		for (Base_Creature c : MapInSelect.getListRoomInEnemy()) {
			MapInSelect.damage(null, null, (Base_Enemy) c, blood);
			counter += 25;
		}
		Message.set(String.valueOf(counter), "のHPを吸血した");
		Player.me.chengeHP(null, null, counter);
		return true;
	}

}
