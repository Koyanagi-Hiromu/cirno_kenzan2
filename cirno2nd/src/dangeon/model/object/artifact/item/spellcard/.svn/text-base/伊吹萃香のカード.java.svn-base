package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.List;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.伊吹萃香;
import dangeon.model.object.creature.player.Player;

public class 伊吹萃香のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "伊吹萃香のカード";
	public static final String item_name_sound = "いふきすいかのかーと";
	private static final int composition = 4;
	private static final int item_str = 8;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "落ちているアイテムを萃める程度の能力" };

	public 伊吹萃香のカード(Point p) {
		super(p, item_name, 1, composition, 伊吹萃香.class);
		sim = ENCHANT_SIMBOL.酔;
	}

	@Override
	public String getCharacterShortName() {
		return "萃香";
	}

	@Override
	public String getDoter() {
		return "AJIA";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=709125";
	}

	@Override
	String getExplanToEnchant() {
		return "会心が出ると、追加効果が必ず出るぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("カリスマ！！");
	}

	@Override
	public String getIllustlator() {
		return "よー";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=630924";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "落ちているアイテムを萃めてくる程度の能力";
	}

	@Override
	protected String[] getSecondExplan() {
		return new String[] { "", "" };
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 伊吹萃香.class;
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
		List<Base_Artifact> list = MapList.getListItem();
		list.remove(this);
		if (list.isEmpty()) {
			Message.set("「アイテムが落ちてないねー」");
			return false;
		}
		SE.WARP_INSTANT.play();
		for (Base_Artifact a : list) {
			ItemFall.setItemFallPoint_NoMessage(Player.me.getMassPoint()
					.getLocation(), a);
		}
		Message.set("「はいはい集めてきましたよー」");
		if (Player.me.saisen != null) {
			Player.me.saisen.punish();
		}
		if (Player.me.shop != null) {
			Player.me.shop.getTenshu().gather();
		}
		return true;
	}

}
