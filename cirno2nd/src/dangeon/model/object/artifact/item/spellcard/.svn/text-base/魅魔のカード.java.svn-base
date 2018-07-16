package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.魅魔;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.view.anime.ThunderEffect;
import dangeon.view.detail.MainMap;

public class 魅魔のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "魅魔のカード";
	public static final String item_name_sound = "みまのかーと";
	private static final int composition = 7;
	private static final int item_str = 13;
	private static final int item_def = 13;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "マスタースパークを放つ程度の能力" };

	public 魅魔のカード(Point p) {
		super(p, item_name, 1, composition);
	}

	@Override
	public String getCharacterShortName() {
		return "魔理沙";
	}

	@Override
	public String getDoter() {
		return "きいろからー";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=95848";
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
		return new String("トド……？");
	}

	@Override
	public String getIllustlator() {
		return "オアシス";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=658181";
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
		return 魅魔.class;
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
		SE.LIGHTNING.play();
		MainMap.addEffect(new ThunderEffect(MassCreater.isPlayerInRoom()));
		int damage = 50;
		for (Base_Enemy c : MapInSelect.getListRoomOrRoadInEnemy()) {
			int dmg = damage;
			// if (c instanceof Player) {
			// if (c.getHP() < damage) {
			// dmg = c.getHP() - 1;
			// }
			// }
			if (c.isThunder() || dmg <= 0) {
				Message.set(c.getColoredName(), "は平気だった");
				continue;
			}
			Damage.damage(Player.me, null, null, Player.me, c, dmg);
		}
		return true;
	}
}
