package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import main.util.FrameShaker;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.比那名居天子;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 比那名居天子のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "比那名居天子のカード";
	public static final String item_name_sound = "ひなないてんしのかーと";
	private static final int composition = 5;
	private static final int item_str = 12;
	private static final int item_def = 10;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "部屋内の敵を転ばす程度の能力" };

	public 比那名居天子のカード(Point p) {
		super(p, item_name, 1, composition, 比那名居天子.class);
		sim = ENCHANT_SIMBOL.緋;
	}

	@Override
	public String getCharacterShortName() {
		return "天子";
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
		return "攻撃時、敵の防御力を無視するぞ";
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
		return 比那名居天子.class;
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
		FrameShaker.doneSoftly();
		for (Base_Creature c : MapInSelect.getListRoomInEnemy()) {
			if (!c.isFlying())
				MapInSelect.転ばし(Player.me, "転び", c, c.getDirection(), 1, 20);
		}
		return true;
	}
}
