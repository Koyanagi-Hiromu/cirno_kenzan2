package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.scrool.いかづちの書;
import dangeon.model.object.artifact.item.scrool.バクスイの書;
import dangeon.model.object.artifact.item.scrool.パワーアップの書;
import dangeon.model.object.artifact.item.scrool.八咫烏の書;
import dangeon.model.object.artifact.item.scrool.天邪鬼の書;
import dangeon.model.object.artifact.item.scrool.氷の書;
import dangeon.model.object.artifact.item.scrool.混乱の書;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.小悪魔;
import dangeon.util.R;

public class 小悪魔のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "小悪魔のカード";
	public static final String item_name_sound = "こあくまのかーと";
	private static final int composition = 4;
	private static final int item_str = 3;
	private static final int item_def = 3;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の書を作り変える程度の能力" };

	public 小悪魔のカード(Point p) {
		super(p, item_name, 1, composition, 小悪魔.class);
	}

	@Override
	public String getCharacterShortName() {
		return "小悪魔";
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
		return new String("書ならお任せ");
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
		return 小悪魔.class;
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
		Point p = mass_point.getLocation();
		Scrool[] scs = { new 混乱の書(p), new バクスイの書(p), new 八咫烏の書(p),
				new いかづちの書(p), new 天邪鬼の書(p), new 氷の書(p), new パワーアップの書(p) };
		Scrool s = scs[new R().nextInt(scs.length)];
		s.setSampleItem(false);
		Message.set(s.getColoredName(), "をすばやく使用した");
		s.itemUse();
		return true;
	}

}
