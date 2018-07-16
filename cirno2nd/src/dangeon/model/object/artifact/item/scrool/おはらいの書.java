package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DecurseEffect;

public class おはらいの書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "おはらいの書";
	public static final String item_name_sound = "おはらいのしょ";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.祓;

	private boolean C_FLAG = false;

	public おはらいの書(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "持っているアイテムの呪いを全て解く。しかし足元のアイテムの呪いは解けない。霊験あらたかなこのアイテムは呪われることがない。";
	}

	@Override
	public boolean isAbleToCurse() {
		return false;
	}

	@Override
	protected boolean isParmitToOpen() {
		return false;
	}

	@Override
	public void scroolUse() {
		for (Base_Artifact a : Belongings.getListItems()) {
			if (!C_FLAG && a.isCurse()) {
				C_FLAG = true;
			}
			a.setCurse(false);
			a.check("forge");
		}
		if (C_FLAG) {
			Player.me.setAnimation(new DecurseEffect());
			Message.set("持ち物の呪いが解けた");
		} else {
			Message.set("しかし持ち物は呪われていなかった");
		}
	}

}