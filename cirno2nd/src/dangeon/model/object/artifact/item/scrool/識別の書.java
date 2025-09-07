package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.first.adventure.wiki.Wiki_Enemy;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Belongings;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.view.detail.View_Sider;
import main.res.SE;

public class 識別の書 extends Scrool implements SelectItem {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "識別の書";
	public static final String item_name_sound = "しきべつのしょ";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.識;
	private int pages;
	private final int first_pages;

	public 識別の書(Point p) {
		super(p, item_name);
		sim = simbol;
		first_pages = pages = new R().nextInt(4) + 3;
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		return null;
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		if (pages < first_pages) {
			sb.append(getName());
			sb.append("[");
			sb.append(pages);
			sb.append("]");
			return sb;
		} else {
			return super.getName(sb);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "アイテムを１つ完全識別する。杖に使えば残り回数も分かるので優先して使うといい。たまに持っているアイテムを全て識別する。足元のアイテムにも使える。";
	}

	@Override
	protected boolean isParmitToOpen() {
		return false;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		if (c instanceof Base_Enemy && !(c instanceof Base_NPC)) {
			staticCheck();
			Base_Enemy e = (Base_Enemy) c;
			Config.save(e, e.getConvertedLV(), false);
			SE.CHECK.play();
			Scene_Action a = Scene_Action.getMe();
			StringBuilder sb = new StringBuilder();
			for (STAGE stage : e.getCategory()) {
				if (sb.length() > 0)
					sb.append("&");
				sb.append(stage);
			}
			Base_Enemy en = EnemyTable.returnBaseEnemy(e.getConvertedLV(),
					e.getMassPoint(), e.getClass());
			a.setNextScene(new Wiki_Enemy(a.KH, a.CURRENT_VIEW, en, -1, -1, sb
					.toString(), en.getConvertedLV()));
			if (--pages > 0)
				ItemFall.itemFall_TrapCheck(getMassPoint(), this);
		} else {
			super.itemHit(c, c2);
		}
	}

	private boolean parfect() {
		SE.CHECK.play();
		int parfect = 10;
		int select = new R().nextInt(100) + 1;
		if (parfect >= select) {
			for (Base_Artifact a : Belongings.getListItems_except(this)) {
				a.check();
			}
			SE.LUCKEY.play();
			Message.set("ラッキー！持ち物全てが識別された");
			return true;
		}
		return false;
	}

	@Override
	public void scroolUse() {
		if (!parfect()) {
			Message.set(A.getColoredName(), "は");
			A.check();
			Message.set(A.getColoredName(), "だった");
			if (ItemTable.getRank(this) >= 3)
				View_Sider.setInformation("出現度：", ItemTable.getRank_String(this));
		}
	}

}