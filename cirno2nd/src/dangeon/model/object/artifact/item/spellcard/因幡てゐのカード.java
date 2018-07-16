package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.TrapTable;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.因幡てゐ;
import dangeon.model.object.creature.player.Player;

public class 因幡てゐのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "因幡てゐのカード";
	public static final String item_name_sound = "いなはていのかーと";
	private static final int composition = 7;
	private static final int item_str = 7;
	private static final int item_def = 7;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "目薬をさして、ワナを沢山増やす程度の能力" };

	public 因幡てゐのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.兎;
	}

	@Override
	public String getCharacterShortName() {
		return "てゐ";
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
		return "装備効果の発動確立が上がるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("またまたやらせていただきましたァン！");
	}

	@Override
	public String getIllustlator() {
		return "ダバデぃ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=600374";
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
		// TODO 自動生成されたメソッド・スタブ
		return 因幡てゐ.class;
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
			Player.me.setCondition(CONDITION.目薬, 0);
			TrapTable.addMapList(25, null);
			Message.set("「にひひ　これでいたずら成功間違いなし！」");
			return true;
		} else {
			Message.set("「ダンジョンじゃないと作れない…」");
			return false;
		}
	}

}
