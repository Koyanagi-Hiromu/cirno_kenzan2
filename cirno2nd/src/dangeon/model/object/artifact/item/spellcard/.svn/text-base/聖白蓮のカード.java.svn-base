package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.聖白蓮;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.OuraEffect;

public class 聖白蓮のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "聖白蓮のカード";
	public static final String item_name_sound = "ひしりひゃくれんのかーと";
	private static final int composition = 4;
	private static final int item_str = 6;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "パワーアップする程度の能力" };

	public 聖白蓮のカード(Point p) {
		super(p, item_name, 1, composition, 聖白蓮.class);
		sim = ENCHANT_SIMBOL.超人;
	}

	@Override
	public String getCharacterShortName() {
		return "白蓮";
	}

	@Override
	public String getDoter() {
		return "まるせん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1733063";
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
		return new String("聖");
	}

	@Override
	public String getIllustlator() {
		return "まるかた";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=6359";
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
		// TODO 自動生成されたメソッド・スタブ
		return 聖白蓮.class;
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
		Player.me.setAnimation(new OuraEffect(Player.me, new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Message.set("「いざ、南無三ーー！」");
				Player.me.setCondition(CONDITION.パワーアップ, 0);
			}
		}));
		return true;
	}

}
