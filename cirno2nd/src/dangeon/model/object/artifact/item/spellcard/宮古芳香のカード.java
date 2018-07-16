package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.宮古芳香;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 宮古芳香のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "宮古芳香のカード";
	public static final String item_name_sound = "みやこよしかのかーと";
	private static final int composition = 4;
	private static final int item_str = 9;
	private static final int item_def = 9;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵のHPを減らし満腹度をバクバクするぞ" };

	public 宮古芳香のカード(Point p) {
		super(p, item_name, 1, composition, 宮古芳香.class);
		sim = ENCHANT_SIMBOL.死;
	}

	@Override
	public String getCharacterShortName() {
		return "芳香";
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
		return "自然回復量がアップして、腐った物も平気になるぞ";
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
		return 宮古芳香.class;
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
			MapInSelect.damage(null, null, (Base_Enemy) c, 25);
			counter += 20;
		}
		Message.set(String.valueOf(counter), "バクバクした");
		Player.me.chengeSatiety(counter);
		// SE.CHECK.play();
		// SE.LUCKEY.play();
		// Message.set("「どれどれ先生に任せなさい」");
		// for (Base_Artifact a : Belongings.getListItems()) {
		// a.check();
		// }
		return true;
	}
}
