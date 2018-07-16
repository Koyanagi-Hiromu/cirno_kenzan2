package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.Iterator;

import main.res.SE;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.チルノ;
import dangeon.model.object.creature.enemy.フランドール;
import dangeon.model.object.creature.enemy.レティ;
import dangeon.model.object.creature.enemy.レミリア;
import dangeon.model.object.creature.player.Belongings;
import dangeon.util.MapInSelect;
import dangeon.view.anime.SunEffect;
import dangeon.view.detail.MainMap;

/**
 * 
 * @author Administrator
 * 
 */
public class 八咫烏の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "八咫烏の書";
	public static final String item_name_sound = "やたがらすのしょ";
	public ENCHANT_SIMBOL simbol = null;

	int use_damage = 40;

	public 八咫烏の書(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.炎;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "アイテムを溶かすぞ";
	}

	@Override
	protected void scroolUse() {
		MainMap.addEffect(new SunEffect(MassCreater.isPlayerInRoom()), true);
		SE.BURN.play();
		for (Iterator<Base_Enemy> iterator = MapInSelect
				.getRoomOrRoadInEnemyList(); iterator.hasNext();) {
			Base_Creature c = iterator.next();
			int dam = use_damage;
			if (c instanceof チルノ || c instanceof レティ || c instanceof レミリア
					|| c instanceof フランドール) {
				Medal.太陽の弱点を突いた.addCount();
				dam = 999;
			}
			dam = c.damagedWithFire(dam);
			MapInSelect.damage(null, null, (Base_Enemy) c, dam);
		}
		for (Base_Artifact a : MapList.getListItem()) {
			if (MassCreater.isPlayerInTheSameRoom(a.getMassPoint())) {
				a.freezeCountReset();
			}
		}
		for (Base_Artifact a : Belongings.getListItems()) {
			a.freezeCountReset();
		}

	}

}