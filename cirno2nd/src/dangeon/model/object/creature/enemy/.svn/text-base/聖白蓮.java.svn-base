package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.artifact.item.staff.イカリの杖;
import dangeon.model.object.artifact.item.staff.オオイカリの杖;
import dangeon.model.object.artifact.item.staff.倍速の杖;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait;
import dangeon.view.util.WithinOutofScreen;

public class 聖白蓮 extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private Staff STAFF;

	public 聖白蓮(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean checkEnemyCondition(Base_Creature c) {
		if (c.getConditionList().contains(CONDITION.反射)) {
			return false;
		}
		if (c.getConditionList().contains(CONDITION.透明)) {
			return false;
		}
		switch (LV) {
		case 1:
			if (c.getConditionList().contains(CONDITION.イカリ)) {
				return false;
			}
			break;
		case 2:
			if (c.getConditionList().contains(CONDITION.三倍速)) {
				return false;
			}
			break;
		case 3:
		case 4:
			if (c.getConditionList().contains(CONDITION.三倍速)) {
				if (c.getConditionList().contains(CONDITION.イカリ)) {
					return false;
				}
			}
			break;
		}
		return true;
	}

	private void creatureEffect(Base_Creature c) {
		STAFF.enemyStaffUse(this);
	}

	private boolean effect() {
		List<Base_Creature> list = root();
		if (list.isEmpty()) {
			return false;
		}
		int select = new R().nextInt(list.size());
		Base_Creature c = hitCheck(list.get(select));
		creatureEffect(c);
		Message.set(getColoredName(), "はエア巻物を開いた");
		return !WithinOutofScreen.isOutside(this);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		STAFF.staffUse();
		return true;
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, getAnimation().getComa());
		}
		return super.getImage();
	}

	private Base_Creature hitCheck(Base_Creature c) {
		direction = converDirection(c.getMassPoint());
		return MapInSelect.getStraightHitCreature(getDirection(),
				getMassPoint(), 0);
	}

	@Override
	protected void init1() {
		STAFF = new イカリの杖(getMassPoint());
	}

	@Override
	protected void init2() {
		STAFF = new 倍速の杖(getMassPoint());
	}

	@Override
	protected void init3() {
		STAFF = new オオイカリの杖(getMassPoint());
	}

	@Override
	protected void init4() {
		STAFF = new オオイカリの杖(getMassPoint(), true);
	}

	@Override
	public int isKamin() {
		return 0;
	}

	private boolean monsterCheck(Base_Creature c) {
		if (LV == 4 && c instanceof Player) {
			return true;
		} else if (c instanceof Base_NPC || c instanceof Player
				|| !checkEnemyCondition(c) || c instanceof 黄金ゆっくり
				|| c instanceof 博麗霊夢 || c instanceof Exルーミア
				|| c instanceof 鬼人正邪) {
			return false;
		}
		return true;
	}

	private List<Base_Creature> root() {
		List<Base_Creature> list = new ArrayList<Base_Creature>();
		if (MassCreater.getMass(getMassPoint()).ROAD) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i == 0 && j == 0) {
						continue;
					}
					Base_Creature c = MapList.getCreature(getMassPoint().x + i,
							getMassPoint().y + j);
					if (c != null) {
						if (!monsterCheck(c)) {
							continue;
						}
						list.add(c);
					}
				}
			}
		} else {
			for (DIRECTION dir : DIRECTION.values()) {
				if (dir.NUM == 5) {
					continue;
				}
				List<Base_Creature> l = MapInSelect.getListStraightHitCreature(
						dir, getMassPoint(), 0);
				for (Base_Creature creature : l) {
					if (creature == null) {
						continue;
					}

					if (!monsterCheck(creature)) {
						continue;
					}
					if (!MassCreater.is1InTheSightFrom2(
							creature.getMassPoint().x,
							creature.getMassPoint().y, getMassPoint().x,
							getMassPoint().y, 1)) {
						continue;
					}
					list.add(creature);
				}
			}
		}
		return list;
	}

	@Override
	protected boolean specialAttack() {
		if (effect()) {
			SE.SYSTEM_SCROLL.play();
			setAnimation(new Special_Wait(this, 3, 4));
			return true;
		}
		return false;
	}

	@Override
	protected boolean specialCheck() {
		if (!isSpecialParcent()) {
			return false;
		}
		if (root().isEmpty()) {
			return false;
		}
		return true;
	}

}
