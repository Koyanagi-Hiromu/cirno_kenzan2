package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.Iterator;

import main.res.SE;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.パチュリー;
import dangeon.model.object.creature.enemy.フランドール;
import dangeon.model.object.creature.enemy.レミリア;
import dangeon.util.MapInSelect;
import dangeon.view.anime.SunEffect;
import dangeon.view.detail.MainMap;

public class パチュリーのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "パチュリーのカード";
	public static final String item_name_sound = "はちゅりーのかーと";
	private static final int composition = 7;
	private static final int item_str = 7;
	private static final int item_def = 0;
	private static final int use_damage = 50;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内にダメージを与える程度の能力" };

	public パチュリーのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.喘息;
	}

	@Override
	public String getCharacterShortName() {
		return "パチュリー";
	}

	@Override
	public String getDoter() {
		return "てぃと";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=32251";
	}

	@Override
	String getExplanToEnchant() {
		return "時々喘息でダメージを受けるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("ごほっごほっ");
	}

	@Override
	public String getIllustlator() {
		return "砂月ゆうら";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2614394";
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
		return パチュリー.class;
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
		MainMap.addEffect(new SunEffect(MassCreater.isPlayerInRoom()), true);
		SE.BURN.play();
		for (Iterator<Base_Enemy> iterator = MapInSelect
				.getRoomOrRoadInEnemyList(); iterator.hasNext();) {
			Base_Creature c = iterator.next();
			int dam = use_damage;
			if (c.isIce() || c instanceof レミリア || c instanceof フランドール) {
				dam = 999;
				Medal.太陽の弱点を突いた.addCount();
			}
			dam = c.damagedWithFire(dam);
			MapInSelect.damage(null, null, (Base_Enemy) c, dam);
		}
		return true;
	}

}
