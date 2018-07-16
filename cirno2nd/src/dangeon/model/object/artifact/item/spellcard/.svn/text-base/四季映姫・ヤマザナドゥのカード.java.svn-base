package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.四季映姫ヤマザナドゥ;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.R;

public class 四季映姫・ヤマザナドゥのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "四季映姫のカード";
	public static final String item_name_sound = "しきえいきのかーと";
	private static final int composition = 6;
	private static final int item_str = 12;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵に白黒付ける程度の能力" };

	public 四季映姫・ヤマザナドゥのカード(Point p) {
		super(p, item_name, 1, composition, 四季映姫ヤマザナドゥ.class);
		sim = ENCHANT_SIMBOL.裁;
	}

	@Override
	public String getCharacterShortName() {
		return "映姫";
	}

	@Override
	public String getDoter() {
		return "大津絵伊一";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=33474";
	}

	@Override
	String getExplanToEnchant() {
		return "敵を起し易いぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ごほっごほっ");
	}

	@Override
	public String getIllustlator() {
		return "abua";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=352681";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "使用すると２分の１の確率で敵を倒す";
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 四季映姫ヤマザナドゥ.class;
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
		List<Base_Enemy> list = MapInSelect.getListRoomInEnemy();
		if (list.isEmpty()) {
			Message.set("「誰もいませんね」");
			return false;
		}
		Message.set("「判決を下す！」");
		Random ran = new R();
		int select = 0;
		for (Base_Enemy em : list) {
			select = ran.nextInt(100) + 1;
			if (select <= 50) {
				Message.set("「有罪！」");
				Damage.damage(null, null, null, Player.me, em, 999);
			} else {
				Message.set("「無罪！」");
			}
		}
		return true;
	}

}
