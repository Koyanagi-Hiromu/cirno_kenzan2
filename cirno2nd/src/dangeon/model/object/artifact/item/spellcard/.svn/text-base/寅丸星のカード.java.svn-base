package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.寅丸星;
import dangeon.util.Flag;

public class 寅丸星のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "寅丸星のカード";
	public static final String item_name_sound = "とらまるしょうのかーと";
	private static final int composition = 5;
	private static final int item_str = 7;
	private static final int item_def = 7;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "次のフロアのアイテムを増やす程度の能力" };

	public 寅丸星のカード(Point p) {
		super(p, item_name, 1, composition, 寅丸星.class);
		sim = ENCHANT_SIMBOL.宝;
	}

	@Override
	public String getCharacterShortName() {
		return "星";
	}

	@Override
	public String getDoter() {
		return "ＵＮＫ教祖";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=583270";
	}

	@Override
	String getExplanToEnchant() {
		return "敵がアイテムを落としやすくなるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("理解不能理解不能理解不能");
	}

	@Override
	public String getIllustlator() {
		return "風花風花";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.kazabanahuuka.info/";
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
		return 寅丸星.class;
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
		Flag.setFlagItemPlus(true);
		Message.set("次のフロアが楽しみだ");
		return true;
	}
}
