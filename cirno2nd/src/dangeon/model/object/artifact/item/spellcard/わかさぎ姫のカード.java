package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.SpecialRoom;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.わかさぎ姫;

public class わかさぎ姫のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "わかさぎ姫のカード";
	public static final String item_name_sound = "わかさぎひめのかーと";
	private static final int composition = 5;
	private static final int item_str = 7;
	private static final int item_def = 0;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "部屋内に水路を作る程度の能力" };

	public わかさぎ姫のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.水;
	}

	@Override
	public String getCharacterShortName() {
		return "橙";
	}

	@Override
	public String getDoter() {
		return "めそうさん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=293174";
	}

	@Override
	String getExplanToEnchant() {
		return "前後に攻撃するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ちぇんちぇん");
	}

	@Override
	public String getIllustlator() {
		return "ryosios";
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
		return "通路で使用しても効果はない";
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return わかさぎ姫.class;
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
		if (!PresentField.get().isHaraheru()) {
			Message.set("ごめんなさい、お腹がすくようなところじゃないと使えないのよ");
			return false;
		}
		Room r = MassCreater.getPlayerRoom();
		if (r != null) {
			SpecialRoom.waterHazard(r, null);
			MassCreater.retakeMassSet();
			return true;
		} else {
			Message.set("部屋内で使わないと意味が無いわ");
			return false;
		}
	}

}
