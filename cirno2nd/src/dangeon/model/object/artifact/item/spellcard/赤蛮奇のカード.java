package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.赤蛮奇_体;
import dangeon.model.object.creature.enemy.赤蛮奇_頭;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 赤蛮奇のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "赤蛮奇のカード";
	public static final String item_name_sound = "せきはんきのかーと";
	private static final int composition = 5;
	private static final int item_str = 8;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "頭が取れる程度の能力" };

	public 赤蛮奇のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.頭;
	}

	@Override
	public String getCharacterShortName() {
		return "赤蛮奇";
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
		return "攻撃を受けると頭が取れるぞ！";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ゆっくり！");
	}

	@Override
	public String getIllustlator() {
		return "";
	}

	@Override
	public String getIllustlatorURL() {
		return "";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "頭が取れるぞ！";
	}

	@Override
	protected String[] getSecondExplan() {
		return new String[] { "", "" };
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 赤蛮奇_体.class;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		if (status == STATUS.STR) {
			return item_str;
		} else {
			return item_def;
		}
	}

	private boolean setHead() {
		Point set = MassCreater.getMonsterPoint(MassCreater.getRoom(Player.me
				.getMassPoint()));
		if (set == Mass.nullpo.null_point) {
			return false;
		}
		final 赤蛮奇_頭 hEAD = new 赤蛮奇_頭(set, 1, false);
		MapList.addEnemy(hEAD);
		MainMap.addEffect(new DoronEffect(set, null, true));
		return true;
	}

	@Override
	protected boolean spellUse() {
		if (MassCreater.getRoom(Player.me.getMassPoint()) == null) {
			Message.set("「……部屋内で使用してよね」");
			return false;
		}
		if (MapList.isEnemyMax()) {
			Message.set("「……不思議なちからで１つも頭が飛ばせないわ」");
			return false;
		}
		int count = 9;
		for (int i = 0; i < count; i++) {
			if (!setHead()) {
				break;
			}
		}
		Message.set("「怖がってくれるかしら？」");
		return true;
	}

}
