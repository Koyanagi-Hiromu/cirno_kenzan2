package dangeon.model.object.artifact.item.arrow;

import java.awt.Image;
import java.awt.Point;

import main.res.BulletImage;
import main.res.Image_Artifact;
import main.util.DIRECTION;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印時;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.resistant.Poison;

/**
 * 
 * 
 * @author Administrator
 * 
 */
public class 毒ナイフ extends 鉄の矢 {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "毒ナイフ";
	public static final int item_use_str = 3;

	public 毒ナイフ(Base_MapObject bm) {
		this(bm.getMassPoint(), false);
	}

	/**
	 * 自然発生ならばtrue 矢を打つなどしてMapに置かれたものはfalse
	 * 
	 * @param p
	 */
	public 毒ナイフ(Point p, boolean natural) {
		super(p, item_name, natural);
		IM = Image_Artifact.P_KNIFE;
	}

	@Override
	protected int arrowStr() {
		return item_use_str;
	}

	@Override
	public String getExplainationInShortCutSelecting() {
		return "当たった敵はちからが下がり弱体化します";
	}

	@Override
	public Image getImage(DIRECTION direction) {
		return BulletImage.p_knife.getImage(direction);
	}

	@Override
	public Arrow getOne() {
		return new 毒ナイフ(mass_point, false);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "当たるとちからが下がる。効果は毒ナイフの方が強いが咲夜のナイフの方が集めやすい。どちらかを捨てる時はそのことを天秤にかけて決めてほしい。";
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature deffece, Base_Creature attack) {
		super.itemHit(deffece, attack);
		Poison.effect(deffece, 1, false, false);
	}

	@Override
	public boolean itemUse() {
		印時.throwKnife(this);
		return true;
	}

	// public void itemHit() {
	// MessageRecord.setMessageTask(new String[] {
	// MapList.getEnemy(getMassPoint()).getName().concat("に"),
	// getName().concat("を打ち込んだ") });
	// MapList.getEnemy(getMassPoint()).arrowDamage(item_use_str);
	// }

	@Override
	protected String scale() {
		return "本";
	}

}