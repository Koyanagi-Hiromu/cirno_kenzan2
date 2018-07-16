package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.少名針妙丸;

public class 少名針妙丸のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "少名針妙丸のカード";
	public static final String item_name_sound = "すくなしんみょうまるのかーと";
	private static final int composition = 7;
	private static final int item_str = 2;
	private static final int item_def = 1;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "" };

	public 少名針妙丸のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.小;
	}

	@Override
	public String getCharacterShortName() {
		return "小名";
	}

	@Override
	public String getDoter() {
		return "";
	}

	@Override
	public String getDoterURL() {
		return "";
	}

	@Override
	String getExplanToEnchant() {
		return "";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("");
	}

	@Override
	public String getIllustlator() {
		return "";
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
		return 少名針妙丸.class;
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
		SE.BIGGER.play();
		Message.set("「小さくな～れ～」");
		for (Base_Enemy c : MapList.getListEnemy()) {
			c.changeSize(-1);
		}
		return true;
	}

}
