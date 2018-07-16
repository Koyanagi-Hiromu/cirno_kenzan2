package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.星熊勇儀;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.OuraEffect;

public class 星熊勇儀のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "星熊勇儀のカード";
	public static final String item_name_sound = "ほしくまゆうきのかーと";
	private static final int composition = 3;
	private static final int item_str = 12;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "全ての攻撃が会心の一撃になる程度の能力" };

	public 星熊勇儀のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.鬼;
	}

	@Override
	public String getCharacterShortName() {
		return "勇儀";
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
		return "会心の一撃が出るようになるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("星熊選手の入場です");
	}

	@Override
	public String getIllustlator() {
		return "芋鍋";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=418789";
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
		return 星熊勇儀.class;
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
				SE.DAMAGED_CRITICAL.play();
				Player.me.setCondition(CONDITION.超会心, 0);
				Message.set("「今宵は存分にちからを振るわせて貰おうかねぇ」");
			}
		}, true));
		return true;
	}

}
