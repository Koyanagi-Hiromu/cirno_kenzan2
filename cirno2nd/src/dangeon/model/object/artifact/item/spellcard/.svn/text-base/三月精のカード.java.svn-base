package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.TrapTable;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.util.STAGE;

public class 三月精のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "三月精のカード";
	public static final String item_name_sound = "さんけっせいのかーと";
	private static final int composition = 6;
	private static final int item_str = 3;
	private static final int item_def = 3;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "うんこー" };

	public 三月精のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.罠;
		list_stage.add(STAGE.外伝);
	}

	@Override
	public String getCharacterShortName() {
		return "三月精";
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
		return "罠にかかりにくく、罠が必ず壊れるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("あたいが最強ね");
	}

	@Override
	public String getIllustlator() {
		return "猫に地球";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member_illust.php?id=366396";
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
		if (PresentField.get().isHaraheru()) {
			SE.ZAKUZAKU.play();
			Message.set("「いたずらに命をかけて！」");
			TrapTable.addMapList(5, null);
			return true;
		} else {
			Message.set("「ダンジョンじゃないと作れない…」");
			return false;
		}
	}

}
