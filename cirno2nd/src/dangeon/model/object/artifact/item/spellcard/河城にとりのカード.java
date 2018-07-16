package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.にとり;
import dangeon.util.MapInSelect;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 河城にとりのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "河城にとりのカード";
	public static final String item_name_sound = "かわしろにとりのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 8;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "未実装" };

	public 河城にとりのカード(Point p) {
		super(p, item_name, 1, composition, にとり.class);
		sim = ENCHANT_SIMBOL.友;
	}

	@Override
	public String getCharacterShortName() {
		return "にとり";
	}

	@Override
	public String getDoter() {
		return "かみねんど";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=648642";
	}

	@Override
	String getExplanToEnchant() {
		return "時々守ってくれてダメージが半分になるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("改造！改造！");
	}

	@Override
	public String getIllustlator() {
		return "pentagon";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=383833";
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
		return にとり.class;
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
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : MapInSelect.getListRoomOrRoadInArtifact()) {
			if (a instanceof Base_Device) {
				continue;
			}
			list.add(a);
		}
		for (Iterator<Base_Artifact> iterator = list.iterator(); iterator
				.hasNext();) {
			Base_Artifact a = iterator.next();
			if (a instanceof Base_Device) {
				iterator.remove();
			}
		}
		if (list.isEmpty()) {
			Message.set("「落ちているものがないねー」");
			return false;
		} else {
			Message.set("「通路とかにも作っちゃう気をつけてねー」");
			for (Base_Artifact base_Artifact : list) {
				Point p = base_Artifact.getMassPoint();
				MainMap.addEffect(new DoronEffect(p, null));
				MassCreater.getMass(p).setWater(true);
			}
			MassCreater.retakeMassSet();
		}
		return true;
	}

}
