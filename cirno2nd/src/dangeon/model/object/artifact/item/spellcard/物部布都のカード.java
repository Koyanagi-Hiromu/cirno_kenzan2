package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.物部布都;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 物部布都のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "物部布都のカード";
	public static final String item_name_sound = "もののへふとのかーと";
	private static final int composition = 5;
	private static final int item_str = 6;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "攻撃、投擲が必中になるぞ" };

	public 物部布都のカード(Point p) {
		super(p, item_name, 1, composition, 物部布都.class);
		sim = ENCHANT_SIMBOL.舟;
	}

	@Override
	public String getCharacterShortName() {
		return "布都";
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
		return "水路を歩けるようになるぞ";
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
		return 物部布都.class;
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
		Message.set("「我にお任せを！」");
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			Point p = Player.me.getMassPoint().getLocation();
			p.translate(d.X, d.Y);
			MainMap.addEffect(new DoronEffect(p, null));
			MassCreater.getMass(p).setWater(true);
		}
		MassCreater.retakeMassSet();
		return true;
	}
}
