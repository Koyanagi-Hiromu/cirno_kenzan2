package dangeon.model.object.artifact.item.arrow;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印時;

/**
 * 
 * 
 * @author Administrator
 * 
 */
public class ミニ八卦炉 extends Arrow {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.ARROW;
	public static final String item_name = "プチ八卦炉";
	public static final int composition = 0;
	public static final int item_use_str = 0;

	/**
	 * 自然発生ならばtrue 矢を打つなどしてMapに置かれたものはfalse
	 * 
	 * @param p
	 */
	public ミニ八卦炉(Point p, boolean natural) {
		super(p, item_name, natural);
		IM = Image_Artifact.HAKKE;
	}

	public ミニ八卦炉(Point p, int num) {
		super(p, item_name, num);
		IM = Image_Artifact.HAKKE;
	}

	@Override
	protected int arrowStr() {
		return item_use_str;
	}

	@Override
	public String getExplainationInShortCutSelecting() {
		return "壁も敵もすり抜けてまっすぐに飛びます";
	}

	@Override
	public Arrow getOne() {
		return new ミニ八卦炉(mass_point, false);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "魔理沙の魔力が込められた小さな小さな八卦炉。チルノが使うと間違った力がいい感じに働きあらゆるものを貫通して飛んでいく。使い勝手が良い。";
	}

	// public void itemHit() {
	// MessageRecord.setMessageTask(new String[] {
	// MapList.getEnemy(getMassPoint()).getName().concat("に"),
	// getName().concat("を打ち込んだ") });
	// MapList.getEnemy(getMassPoint()).arrowDamage(item_use_str);
	// }
	@Override
	public int getShadow() {
		return 7;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean itemUse() {
		印時.throwKnife(this);
		return true;
	}

	@Override
	protected String scale() {
		return "コ";
	}

}