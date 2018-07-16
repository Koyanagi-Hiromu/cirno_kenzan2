package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

/**
 * 
 * @author Administrator
 * 
 */
public class 破裂の書 extends Scrool implements SelectItem {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "破裂の書";
	public static final String item_name_sound = "はれつのしょ";
	public ENCHANT_SIMBOL simbol = null;

	public 破裂の書(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		return null;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "ボーン！";
	}

	@Override
	public void scroolUse() {
		SE.DIGG.play();
		Belongings.remove(A);
		if (A instanceof Base_Grass || A instanceof Staff) {
			A.check();
			Message.set(A.getColoredName(), "の効果が部屋いっぱいに広がった");
			setTask(MapInSelect.getListRoomInEnemy().iterator());
		} else {
			Message.set(A.getColoredName(), "は粉々になった");
		}
	}

	private void setTask(final Iterator<Base_Enemy> i) {
		if (i.hasNext())
			new Task() {

				/**
			 *
			 */
				private static final long serialVersionUID = 1L;

				private int frame = 0, FRAME = 2;

				private boolean flag = true;

				@Override
				public boolean isDemandToContinue() {
					return flag;
				}

				@Override
				public void work() {
					if (frame++ < FRAME)
						return;
					if (flag = MapList.isCreatureAnimating())
						return;
					Base_Creature c = i.next();
					if (A instanceof Base_Grass)
						((Base_Grass) A).haretsu(c);
					else
						A.itemHit(c, Player.me);
					setTask(i);
				}
			}.work_appointment();
	}
}