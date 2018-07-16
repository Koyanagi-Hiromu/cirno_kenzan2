package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.enemy.チルノ;
import dangeon.util.MapInSelect;
import dangeon.util.STAGE;
import dangeon.view.anime.CirnoIceEffect;
import dangeon.view.detail.MainMap;

public class チルノのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "チルノのカード";
	public static final String item_name_sound = "ちるののかーと";
	private static final int composition = 9;
	private static final int item_str = 9;
	private static final int item_def = 9;
	private static final int use_damage = 40;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "フロア中の敵にダメージを与える程度の能力" };

	public チルノのカード(Point p) {
		super(p, item_name, 1, composition, チルノ.class);
		sim = ENCHANT_SIMBOL.チルノ;
		list_stage.add(STAGE.紅魔郷);
		list_stage.add(STAGE.妖々夢);
		list_stage.add(STAGE.花映塚);
		list_stage.add(STAGE.輝針城);
	}

	@Override
	public String getCharacterShortName() {
		return "チルノ";
	}

	@Override
	public String getDoter() {
		return "鮫妻";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=360177";
	}

	@Override
	String getExplanToEnchant() {
		return "特に効果がないぞ！";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("あたいが最強ね");
	}

	@Override
	public String getIllustlator() {
		return "てとら";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://tetlapot.net/";
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
		return CardAttackEffect.class;
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
		SE.ICE.play();
		MassCreater.frozenWater();
		MainMap.addEffect(new CirnoIceEffect(), true);
		MapInSelect.damageIcy_toAllEnemies(null, null, use_damage);
		return true;
	}

}
