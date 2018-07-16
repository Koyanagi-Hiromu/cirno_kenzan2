package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.火焔猫燐;
import dangeon.model.object.creature.player.Player;

public class 火焔猫燐のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "火焔猫燐のカード";
	public static final String item_name_sound = "かえんひょうりんのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "周囲に金縛りと呪い状態の敵を召喚する程度の能力" };

	public 火焔猫燐のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.招;
	}

	@Override
	public String getCharacterShortName() {
		return "おりん";
	}

	@Override
	public String getDoter() {
		return "かうざー";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=91937";
	}

	@Override
	String getExplanToEnchant() {
		return "爆発のダメージを減らすぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("割と厄い");
	}

	@Override
	public String getIllustlator() {
		return "ゐぬ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=3222134";
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
		return 火焔猫燐.class;
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
		if (PresentField.get().isHaraheru()
				&& PresentField.get().isRandomField()) {
			SE.SUMMON.play();
			MapList.summonEnemy(Player.me.getMassPoint(), 8, null,
					CONDITION.麻痺, CONDITION.死);
			return true;
		} else {
			Message.set("「ちょいと！ランダムマップで使っておくれよ！」");
			return false;
		}
	}

}
