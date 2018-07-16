package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.UtilMessage;

public class ワイルドカード extends Scrool implements SelectItem {
	private static final long serialVersionUID = 1L;
	private boolean flg_bombCopy;

	public ワイルドカード(Point p) {
		super(p, "ワイルドカード");
		sim = null;
		IM = Image_Artifact.BOOK2;
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		for (Iterator<Base_Artifact> i = list.iterator(); i.hasNext();) {
			Base_Artifact a = i.next();
			if ((a instanceof SpellCard))
				i.remove();
		}
		return list;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "白紙だけども白紙じゃないぞ";
	}

	@Override
	protected void init() {
		super.init();
		if (Config.getFate() == 2) {
			exn[2] = "修正値もボムもないまっさらなカードだよ";
			flg_bombCopy = false;
		} else {
			exn[2] = "修正値はコピーできないよ";
			flg_bombCopy = true;
		}
	}

	@Override
	public void scroolUse() {
		SE.MEKKI.play();
		if (!(A instanceof SpellCard)) {
			UtilMessage.effectDefMsg();
			return;
		} else {
			if (A.isMerchant()) {
				Message.set("不思議なちからがはたらいてうまく写しとれなかった");
			} else {
				Message.set("カードを写しとった");
				int i = Belongings.getListItems().indexOf(this);
				SpellCard a = (SpellCard) ItemTable.createObject(
						Player.me.getMassPoint(), A.getClass());
				a.setForgeValue(-a.getForgeValue());
				if (flg_bombCopy == false) {
					a.addBomb(-99);
				}
				a.setCurse(false);
				a.check("forge");
				if (i >= 0 && i <= Belongings.getMax()) {
					Belongings.setItems(a, i);
				} else {
					ItemFall.itemFall(Player.me.getMassPoint(), a);
				}
			}
		}
	}
}
