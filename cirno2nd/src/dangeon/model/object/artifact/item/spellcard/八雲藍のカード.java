package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.八雲藍;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.OuraEffect;

public class 八雲藍のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "八雲藍のカード";
	public static final String item_name_sound = "やくもらんのかーと";
	private static final int composition = 2;
	private static final int item_str = 10;
	private static final int item_def = 8;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "通常攻撃を必ず一回避けてジャンプする程度の能力" };

	public 八雲藍のカード(Point p) {
		super(p, item_name, 1, composition, 八雲藍.class);
		sim = ENCHANT_SIMBOL.式;
	}

	@Override
	public String getCharacterShortName() {
		return "藍";
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
		return "２マス先まで攻撃できるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("改造！改造！");
	}

	@Override
	public String getIllustlator() {
		return "さえばし";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=986563";
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
		return 八雲藍.class;
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
				Player.me.setCondition(CONDITION.イリュージョン, 0);
			}
		}));
		// ArrayList<DIRECTION> list = new ArrayList<DIRECTION>(8);
		// DIRECTION p_d = Player.me.getDirection();
		// for (DIRECTION d : p_d.getNeiboringDirections8()) {
		// list.add(d);
		// }
		// Point p;
		// for (DIRECTION d : list) {
		// p = Player.me.getMassPoint().getLocation();
		// p.translate(d.X, d.Y);
		// Base_Enemy e = MapList.getEnemy(p);
		// if (e != null && !(e instanceof Base_NPC)) {
		// Damage.normalAttack(Player.me, e);
		// Player.me.setStand(d, false);
		// continue;
		// }
		// if (Enchant.ATK.isEnchant()) {
		// Player.me.setStand(d, false);
		// }
		// }
		return true;
	}

}
