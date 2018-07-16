package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.藤原妹紅;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.FireEffect;

public class 藤原妹紅のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "藤原妹紅のカード";
	public static final String item_name_sound = "ふしわらもこうのかーと";
	private static final int composition = 6;
	private static final int item_str = 10;
	private static final int item_def = 0;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "倒れても復活する程度の能力" };

	public 藤原妹紅のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.炎;
	}

	@Override
	public String getCharacterShortName() {
		return "妹紅";
	}

	@Override
	public String getDoter() {
		return "ささ和え";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=568693";
	}

	@Override
	String getExplanToEnchant() {
		return "アイテムが凍りにくくなるぞ";
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
		return "青の３";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=60121";
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
		return 藤原妹紅.class;
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
		Message.set("不死の力を背負ってみるかい？");
		Player.me.setAnimation(new FireEffect(Player.me, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Player.me.setCondition(CONDITION.復活, 99);
			}
		}));
		return true;
	}
}
