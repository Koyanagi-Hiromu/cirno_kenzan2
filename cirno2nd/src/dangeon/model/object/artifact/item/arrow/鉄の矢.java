package dangeon.model.object.artifact.item.arrow;

import java.awt.Image;
import java.awt.Point;

import main.res.BulletImage;
import main.res.Image_Artifact;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印時;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.十六夜咲夜;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.Damage;
import dangeon.util.ThunderDamage;

/**
 * 
 * 
 * @author Administrator
 * 
 */
public class 鉄の矢 extends Arrow {

	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.ARROW;
	public static final String item_name = "咲夜のナイフ";
	public static final int composition = 0;
	public static final int item_use_str = 5;

	/**
	 * 自然発生ならばtrue 矢を打つなどしてMapに置かれたものはfalse
	 * 
	 * @param p
	 */
	public 鉄の矢(Point p, boolean natural) {
		super(p, item_name, natural);
		IM = Image_Artifact.KNIFE;
	}

	public 鉄の矢(Point p, String item_name, boolean natural) {
		super(p, item_name, natural);
	}

	@Override
	protected int arrowStr() {
		return item_use_str;
	}

	@Override
	public String getExplainationInShortCutSelecting() {
		return "たくさん手に入るうえ、攻撃力も高めです";
	}

	@Override
	public Image getImage(DIRECTION direction) {
		return BulletImage.knife.getImage(direction);
	}

	@Override
	public Arrow getOne() {
		return new 鉄の矢(mass_point, false);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "束ねて９９本まで持つことができる。足元のナイフを交換して拾うことで束ねずに持つこともできる。大変便利だが消耗品なので必要な時に丁寧に使用したい。";
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature deffece, Base_Creature attack) {
		if (attack instanceof Player) {
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.鼓)) {
				ThunderDamage.thunderDamage(this, Player.me, deffece, 15);
				return;
			}
			else if (attack == deffece && BonusConductor.ナイフマスター_通常攻撃()) {
				Damage.PtoE_ArrowAttack(this, attack, deffece, -9999);
				return;
			}
		} else if (attack instanceof 十六夜咲夜 && attack.getLV() == 4) {
			if (deffece instanceof Player
					&& !deffece.conditionCheck(CONDITION.影縫い)) {
				SE.STATUS_SHADOW.play();
				deffece.setCondition(CONDITION.影縫い, 0);
				return;
			}
		}
		int exp_cash = Player.me.getExpCash();
		super.itemHit(deffece, attack);
		if (exp_cash < Player.me.getExpCash()) {
			if (getArrowRest() != 1) {
				Medal.ナイフをまとめて投げて敵を倒した.addCount();
			}
		}
	}

	// public void itemHit() {
	// MessageRecord.setMessageTask(new String[] {
	// MapList.getEnemy(getMassPoint()).getName().concat("に"),
	// getName().concat("を打ち込んだ") });
	// MapList.getEnemy(getMassPoint()).arrowDamage(item_use_str);
	// }

	@Override
	public boolean itemUse() {
		印時.throwKnife(this);
		return true;
	}

	@Override
	protected String scale() {
		return "本";
	}

}