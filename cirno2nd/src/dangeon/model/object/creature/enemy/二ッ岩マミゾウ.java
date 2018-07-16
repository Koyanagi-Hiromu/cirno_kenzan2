package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印招;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.DoronEffect;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.detail.MainMap;

public class 二ッ岩マミゾウ extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private Base_Artifact item;
	private int changed_floor_item = 0, changed_belongings = 0;
	private ArrayList<二ッ岩マミゾウ> list = new ArrayList<二ッ岩マミゾウ>();

	public 二ッ岩マミゾウ(Point p, int Lv) {
		this(p, Lv, null);
	}

	public 二ッ岩マミゾウ(Point p, int Lv, Base_Artifact item) {
		super(p, Lv);
		this.item = item;
	}

	private void change(final Base_Artifact a) {
		二ッ岩マミゾウ e = new 二ッ岩マミゾウ(a.getMassPoint(), LV, a);
		this.list.add(e);
		MapList.addEnemy(e);
		setAnime(this);
		setAnime(e);
		// setAnimation(new Special_Wait(this, 4, 10));
		// e.setAnimation(new Special_Wait(e, 4, 10));
		MainMap.addEffect(new DoronEffect(a.getMassPoint(), new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				MassCreater.getMass(a.getMassPoint()).setUnWarning();
				MapList.removeArtifact(a);
			}
		}, true));
	}

	private boolean check() {
		if (item != null || MapList.isEnemyMax())
			return false;
		if (LV == 3 || LV == 4) {
			if (dorobo())
				return true;
		}
		return floor_item();
	}

	private boolean dorobo() {
		boolean flag = false;
		if (attack_possible()) {
			int max;
			if (LV == 3)
				max = 1;
			else if (LV == 4)
				max = 2;
			else
				max = 0;
			while (changed_belongings < max)
				if (!MapInSelect.aroundEmptyCheck(Player.me.getMassPoint(),
						true).isEmpty()) {
					ArrayList<Base_Artifact> list = Belongings
							.getListItemNoEnchant();
					for (Iterator<Base_Artifact> iterator = list.iterator(); iterator
							.hasNext();) {
						Base_Artifact a = iterator.next();
						if (a.getListComposition().contains(ENCHANT_SIMBOL.金))
							iterator.remove();
					}
					if (!list.isEmpty())
						return true;
				} else {
					break;
				}
		}
		return flag;
	}

	private boolean doroboCheck(Base_Artifact a) {
		return 印招.effect()
				|| EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.融合)
				|| EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.護) || MapList.isEnemyMax()
				|| a.getListComposition().contains(ENCHANT_SIMBOL.金);
	}

	private void doroboMiss(final Base_Artifact a, Point p) {
		MainMap.addEffect(new DoronEffect(p, new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.融合)) {
					Message.set("しかし", Player.me.getColoredName(),
							"と融合していて奪えなかった");
				} else if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.護)
						|| a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
					Message.set("しかし神の加護があって変化できなかった");
				} else if (印招.effect()) {
					Message.set(getColoredName(), "はうまく分身を作り出せなかった！");
				} else {
					Message.set("しかしフロアにエネミーが多すぎて失敗した");
				}
			}
		}, true));
	}

	private boolean effetctDorobo() {
		boolean flag = false;
		while (dorobo()) {
			List<Point> list_around = MapInSelect.aroundEmptyCheck(
					Player.me.getMassPoint(), true);
			int i;
			for (i = 0; i < list_around.size(); i++) {
				if (MapList.getArtifact(list_around.get(i)) == null)
					break;
			}
			if (i == list_around.size()) {
				return flag;
			}
			final Point p = MapInSelect.aroundEmptyCheck(
					Player.me.getMassPoint(), true).get(i);
			changed_belongings++;
			List<Base_Artifact> list = Belongings.getListItemNoEnchant();
			for (Iterator<Base_Artifact> iterator = list.iterator(); iterator
					.hasNext();) {
				Base_Artifact a = iterator.next();
				if (a.getListComposition().contains(ENCHANT_SIMBOL.金))
					iterator.remove();
			}
			final Base_Artifact a = list.get(new R().nextInt(list.size()));
			if (doroboCheck(a)) {
				if (!EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.融合)) {
					Belongings.remove(a);
					ItemFall.itemFall(Player.me.getMassPoint(), p, a);
				}
				if (印招.effect()) {
					SE.SUMMON.play();
				}
				Message.set(getColoredName(), "は", Player.me.getColoredName(),
						"が持っていた", a.getColoredName(), "をマミゾウ化しようとした");
				this.setAnimation(new Special_Wait_FrameByFrame(this, null, 1,
						new Task() {
							private static final long serialVersionUID = 1L;

							@Override
							public void work() {

							}

						}, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0));
				doroboMiss(a, p);
			} else {
				Belongings.remove(a);
				// a.setMassPoint(p);
				ItemFall.itemFall(Player.me.getMassPoint(), p, a);
				Message.set(getColoredName(), "はチルノが持っていた", a.getColoredName(),
						"をマミゾウ化した");
				change(a);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	protected void enemyBreakAction() {
		// for (二ッ岩マミゾウ e : list) {
		// if (e.item != null) {
		// if (e.getHP() > 0) {
		// }
		// }
		// }
	}

	private boolean floor_item() {
		int max;
		if (LV == 1)
			max = 1;
		else
			max = 3;
		if (changed_floor_item >= max) {
			return false;
		} else {
			return !getNeiborItem().isEmpty();
		}
	}

	@Override
	public String getColoredName() {
		if (item != null)
			return item.getColoredName();
		else
			return super.getColoredName();
	}

	@Override
	public Image getImage() {
		return super.getImage();
	}

	@Override
	public String getName() {
		if (item != null)
			return item.getName();
		else
			return super.getName();
	}

	private ArrayList<Base_Artifact> getNeiborItem() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		Point poi = mass_point.getLocation();
		for (DIRECTION d : DIRECTION.values()) {
			Point p = poi.getLocation();
			p.translate(d.X, d.Y);
			Base_Artifact a = MapList.getArtifact(p);
			if (a != null && !a.isPunishment() && !a.isMerchant()) {
				if (a instanceof Base_Device && !(a instanceof Stairs)) {
					continue;
				}
				Mass mass = MassCreater.getMass(a.getMassPoint());
				if (!MapList.isCreature(a.getMassPoint()) && !mass.WATER
						&& mass.WALKABLE)
					list.add(a);
			}
		}
		return list;
	}

	public boolean hasStair() {
		return item instanceof Stairs;
	}

	public void itemFall() {
		if (item != null && !MapList.getListArtifact().contains(item)) {
			Message.set(item.getColoredName(), "は元に戻った");
			if (item instanceof Base_Trap) {
				item.setVisible(true);
			}
			ItemFall.itemFall(getMassPoint(), item);
			if (item.getClass().equals(Stairs.class)) {
				Medal.変化した階段を敷地内で元に戻した.addCount();
			}
			item = null;
		}
	}

	private void setAnime(二ッ岩マミゾウ e) {
		e.setAnimation(new Special_Wait_FrameByFrame(e, null, 1, null, 1, 2, 3,
				3, 3, 3, 3, 3, 3, 3, 2, 1, 0));
	}

	@Override
	protected void setBeatedAway() {
		if (item != null) {
			itemFall();
		} else {
			for (二ッ岩マミゾウ e : list) {
				if (e.getHP() > 0) {
					e.setBeatedAway();
				}
			}
		}
		super.setBeatedAway();
	}

	@Override
	protected boolean specialAttack() {
		if (!check())
			return false;
		if (LV == 3 || LV == 4) {
			if (effetctDorobo())
				return true;
		}
		changed_floor_item++;
		ArrayList<Base_Artifact> list = getNeiborItem();
		if (list.isEmpty())
			return false;
		Base_Artifact a = list.get(new R().nextInt(list.size()));
		if (印招.effect()) {
			setAnime(this);
			SE.SUMMON.play();
			Message.set(getColoredName(), "はうまく分身を作り出せなかった！");
			MainMap.addEffect(new DoronEffect(a.getMassPoint(), null, true));
		} else {
			Message.set(getColoredName(), "は", a.getColoredName(), "をマミゾウ化した");
			change(a);
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		return isSpecialParcent() && check();
	}

}
