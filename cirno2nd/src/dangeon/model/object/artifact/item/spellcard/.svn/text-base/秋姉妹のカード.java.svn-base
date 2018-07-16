package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.player.Player;
import dangeon.util.STAGE;

public class 秋姉妹のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "秋姉妹のカード";
	public static final String item_name_sound = "あきしまいのかーと";
	private static final int composition = 2;
	private static final int item_str = 2;
	private static final int item_def = 2;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "大きなおにぎりを作り出す程度の能力" };

	public 秋姉妹のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.皮;
		list_stage.add(STAGE.風神録);
	}

	private void effect() {
		大きなおにぎり a = new 大きなおにぎり(Player.me.getMassPoint());
		ItemFall.itemFall(a);
		// if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY()) {
		// Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + 3);
		// Player.me.chengeSatiety(50);
		// MessageRecord.setMessageTask(new String[] {
		// getName().concat("を食べた"), "最大満腹度が３上がった" });
		// } else if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY() + 50)
		// {
		// Player.me.chengeSatiety(100);
		// MessageRecord.setMessageTask(new String[] {
		// getName().concat("を食べた"), "お腹が一杯になった" });
		// } else {
		// Player.me.chengeSatiety(50);
		// MessageRecord.setMessageTask(new String[] {
		// getName().concat("を食べた"), "それなりにお腹が膨れた" });
		// }
	}

	@Override
	public String getCharacterShortName() {
		return "秋姉妹";
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
		return "お腹が減りにくくなるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("二人は……");
	}

	@Override
	public String getIllustlator() {
		return "五味苺";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=568386";
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
		SE.MIRACLE_ONIGIRI.play();
		effect();
		return true;
	}

}
