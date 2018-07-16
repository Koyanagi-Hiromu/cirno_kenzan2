package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.util.ThunderDamage;

public class 堀川雷鼓のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "堀川雷鼓のカード";
	public static final String item_name_sound = "ほりかわらいこのかーと";
	private static final int composition = 5;
	private static final int item_str = 0;
	private static final int item_def = 14;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "" };

	public 堀川雷鼓のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.鼓;
		list_stage.add(STAGE.輝針城);
	}

	@Override
	public String getCharacterShortName() {
		return "雷鼓";
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
		if (MapList.getListEnemy().isEmpty()) {
			Message.set("「敵がいないね」");
			return false;
		} else {
			Message.set("「１０本だけ落とさせてもらうわ」");
			int max = 10;
			for (int i = 0; i < max; i++) {
				ThunderDamage.thunderDamage(
						this,
						Player.me,
						MapList.getListEnemy().get(
								R.ran(MapList.getListEnemy().size())), 25);
			}
			return true;
		}
	}

}
