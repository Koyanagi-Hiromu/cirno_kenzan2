package dangeon.model.object.artifact.item.scrool;

import java.awt.Color;
import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.HitEffect;
import dangeon.view.detail.MainMap;
import main.res.Image_Artifact;
import main.res.SE;
import main.util.半角全角コンバーター;

public class 幻想郷縁起 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "幻想郷縁起";
	public static final String item_name_sound = "けんそうきょうえんき";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.縁;
	private int pages;

	public 幻想郷縁起(Point p) {
		super(p, item_name);
		pages = 1000;
		IM = Image_Artifact.BOOK2;
		sim = simbol;
	}

	public 幻想郷縁起(Point p, int pages) {
		super(p, item_name);
		this.pages = pages;
		IM = Image_Artifact.BOOK2;
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		sb.append(getName());
		sb.append("[");
		sb.append(pages);
		sb.append("]");
		return sb;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "様々な事が書いてある";
	}

	@Override
	public boolean isHolyPoint(Base_Creature source, Integer damage) {
		if (pages == 0) return false;
		damage /= 10;
		SE.REIMU_BARRIER.play();
		
		if (damage <= 0) return true;
		
		MainMap.addEffect(new HitEffect(Player.me, damage));
		Message.set(source.getColoredName(), Damage.enemy_color, "の攻撃によってページが",
				半角全角コンバーター.半角To全角数字(damage), "枚減った", Color.WHITE);
		pages -= damage;
		if (pages <= 0) {
			IM = Image_Artifact.BOOK2;
			pages = 0;
			Message.set(Color.RED, "ページがなくなってしまった");
		} else {
			Message.set("残り", 半角全角コンバーター.半角To全角数字(pages), "ページ");
		}
		return true;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		staticCheck();
		int damage = 5000;
		Message.set(new String[] { c.getColoredName().concat("に"),
				getColoredName().concat("をぶつけた") });
		Damage.damage(this, null, null, c2, c, damage);
	}

	@Override
	public void scroolUse() {
		if (pages == 0) {
			Message.set("特に効果はなかった");
		} else {
			int delt = Math.max(999 - Player.me.getBooks(), pages);
			if (delt < 1)
				Message.set("特に効果はなかった");
			else {
				Message.set(delt + "枚のページになってポケットに入った");
				Player.me.addBooks(delt);
				
			}
		}
	}
}
