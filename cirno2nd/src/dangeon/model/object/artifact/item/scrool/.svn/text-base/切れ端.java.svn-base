package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import main.util.半角全角コンバーター;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.R;

public class 切れ端 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "幻想郷縁起";
	public static final String item_name_sound = "けんそうきょうえんき";
	private int pages;

	public 切れ端(Point p) {
		this(p, null);
		IM = Image_Artifact.BOOK2;
	}

	public 切れ端(Point p, Integer pages) {
		super(p, item_name);
		if (pages != null)
			this.pages = pages;
		else
			this.pages = new R().is(10) ? 3 : new R().is(50) ? 2 : 1;
		// page 期待値　＝ 3*0.1 + 2*0.9*0.5 + 1*0.9*0.5=1.65
	}

	@Override
	public Boolean exchange() {
		切れ端 kirehashi = null;
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof 切れ端) {
				kirehashi = (切れ端) a;
				break;
			}
		}
		if (kirehashi == null) {
			return false;
		} else {
			matomeru(kirehashi, getName().concat("を１つにまとめた"));
			return null;
		}
	}

	@Override
	public int getMerchantSoldValue() {
		return pages;
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		sb.append(半角全角コンバーター.半角To全角数字(pages));
		sb.append("ページの");
		sb.append(getName());
		return sb;
	}

	private int getPages() {
		return pages;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "様々な事が書いてある";
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		int damage = pages * 30;
		Message.set(new String[] { c.getColoredName().concat("に"),
				getColoredName().concat("をぶつけた") });
		Damage.damage(this, null, null, c2, c, damage);
	}

	@Override
	public boolean itemPickUp() {
		切れ端 kirehashi = null;
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof 切れ端) {
				kirehashi = (切れ端) a;
				break;
			}
		}
		if (kirehashi != null) {
			matomeru(kirehashi, getColoredName().concat("を手持ちにまとめた"));
		} else {
			Message.set(getColoredName(), "をポケットに入れた");
			TaskOnMapObject.addTrapRemoveTask(this);
			SE.SYSTEM_PICKUP.play();
			Player.me.addBooks(pages);
		}
		return false;
	}

	private void matomeru(切れ端 arr, String str) {
		arr.setPages(arr.getPages() + this.getPages());
		TaskOnMapObject.addTrapRemoveTask(this);
		Message.set(str);
		SE.SYSTEM_PICKUP.play();
	}

	@Override
	public void scroolUse() {
		Message.set("ポケットに小さく入った");
		Player.me.addBooks(pages);
	}

	private void setPages(int i) {
		pages = i;
		if (pages > 99)
			pages = 99;
	}
}
