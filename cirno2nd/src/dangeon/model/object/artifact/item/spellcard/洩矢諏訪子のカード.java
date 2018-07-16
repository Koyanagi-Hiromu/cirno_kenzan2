package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.洩矢諏訪子;
import dangeon.view.anime.RainEffect;
import dangeon.view.detail.MainMap;

public class 洩矢諏訪子のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "洩矢諏訪子のカード";
	public static final String item_name_sound = "もりやすわこのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 8;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵の防御力を無くすぞ" };

	public 洩矢諏訪子のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.バトル;
	}

	@Override
	public String getCharacterShortName() {
		return "諏訪子";
	}

	@Override
	public String getDoter() {
		return "ayge";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1296656";
	}

	@Override
	String getExplanToEnchant() {
		return "ダメージを反射するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("すわっち");
	}

	@Override
	public String getIllustlator() {
		return "グラゲイター";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=974457";
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
		return 洩矢諏訪子.class;
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
		boolean flag = false;
		for (Base_Creature c : MapList.getListEnemy()) {
			c.setDEF(0);
			if (flag)
				MainMap.addEffect(new RainEffect(c, true, null), true);
			else
				MainMap.addEffect(new RainEffect(c, true, new Task() {

					/**
					 *
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						Message.set("防御力が０になった");
					}
				}), true);
			flag = true;
		}
		if (flag)
			Message.set("「ケロケロ！」");
		else
			Message.set("「部屋に敵がいないよ」");
		return flag;
	}

}
