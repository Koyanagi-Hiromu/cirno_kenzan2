package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.EyeEffect;

public class 鈴仙・優曇華院・イナバ extends Base_Enemy {

	private enum part {
		ATK, ENC, ENC_REM, USE
	}

	private static final long serialVersionUID = 1L;

	public static boolean effect() {
		Message.setConcatFlag(false);
		Message.set(Player.me.getColoredName() + "は発狂している！");
		ArrayList<part> list = new ArrayList<part>();
		setSpecial(list);
		while (true) {
			if (list.isEmpty()) {
				Message.set(Player.me.getColoredName() + "はぼぉっとした");
				return false;
			}
			int i = new R().nextInt(list.size());
			System.out.println(i);
			if (select(list.remove(i))) {
				return true;
			}
		}
	}

	private static boolean enchant() {
		if (!Enchant.isEnchantSpace()) {
			return false;
		}
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : Belongings.getListItems_exceptPocket()) {
			if (!Enchant.isEnchanted(a) && !(a instanceof Arrow))
				list.add(a);
		}
		if (list.isEmpty()) {
			return false;
		}
		Base_Artifact a = list.get(new R().nextInt(list.size()));
		for (Enchant e : Enchant.values()) {
			if (!(a instanceof SpellCard)) {
				if (e == Enchant.ATK || e == Enchant.DEF) {
					continue;
				}
			}
			if (e.isEnchantEmpty()) {
				if (e == Enchant.ANY3) {
					return false;
				}
				e.tryToSet(a);
				break;
			}
		}
		return true;
	}

	private static boolean enchantRemove() {
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.融合)) {
			Message.set("装備を外そうとしたが、体と融合していて外せなかった");
			return true;
		}
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		list = Enchant.getEnchantList();
		if (list.isEmpty()) {
			Message.set("装備を外そうとしたが、何も装備していなかったので平気だった");
			return true;
		}
		int select = new R().nextInt(list.size());
		Enchant.tryToRemove(list.get(select));
		return true;
	}

	private static boolean itemCheck(Base_Artifact a) {
		if (a instanceof SpellCard) {
			SpellCard s = (SpellCard) a;
			if (!s.isUdongeSelective())
				return true;
			if (!s.isAbleToUse())
				return true;
		}
		if (a instanceof SelectItem) {
			return true;
		}
		if (a.isMerchant())
			return true;
		if (a.isPunishment())
			return true;
		return false;
	}

	private static boolean randomAttack() {
		Message.set("デタラメな方向に攻撃した！");
		Player.me.setDirection(DIRECTION.getRandom8Direction());
		Player.me.normalAttack();
		return true;
	}

	private static boolean randomUse() {
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : Belongings.getListItems()) {
			if (Enchant.isEnchanted(a)) {
				continue;
			}
			if (!itemCheck(a)) {
				list.add(a);
			}
		}
		if (list.isEmpty()) {
			return false;
		}
		int select = new R().nextInt(list.size());
		list.get(select).itemUseThis();
		return true;
	}

	private static boolean select(part p) {
		switch (p) {
		case ATK:
			if (randomAttack()) {
				return true;
			}
			break;
		case ENC:
			if (Player.me.conditionCheck(CONDITION.おにぎり)) {
				Message.set(Player.me.getColoredName() + "はぼぉっとした");
				return true;
			} else if (enchant()) {
				return true;
			}
			break;
		case ENC_REM:
			if (Player.me.conditionCheck(CONDITION.おにぎり)) {
				Message.set(Player.me.getColoredName() + "はぼぉっとした");
				return true;
			} else if (enchantRemove()) {
				return true;
			}
			break;
		case USE:
			if (Player.me.conditionCheck(CONDITION.おにぎり)) {
				Message.set(Player.me.getColoredName() + "はぼぉっとした");
				return true;
			} else if (randomUse()) {
				return true;
			}
			break;
		}
		return false;
	}

	private static void setSpecial(ArrayList<part> list) {
		if (Enchant.isEnchantSpace()) {
			for (Base_Artifact a : Belongings.getListItems()) {
				if (!a.isEnchantedNow()) {
					list.add(part.ENC);
					break;
				}
			}
		}
		for (Base_Artifact a : Belongings.getListItems()) {
			if (!a.isEnchantedNow()) {
				if (itemCheck(a)) {
					list.add(part.USE);
					break;
				}
			}
		}
		Enchant.getEnchantList();
		for (Enchant e : Enchant.values()) {
			if (e.isEnchant()) {
				list.add(part.ENC_REM);
				break;
			}
		}
		list.add(part.ATK);
	}

	public 鈴仙・優曇華院・イナバ(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean effectCheck() {
		ArrayList<part> list = new ArrayList<part>();
		setSpecial(list);

		return list.isEmpty();
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		SE.CREASY_WAVE.play();
		for (Base_Creature c : MapInSelect.getListStraightHitCreature(
				Player.me.getDirection(), Player.me.getMassPoint(), 0)) {
			SE.STATUS_PIYOPIYO.play();
			c.setCondition(CONDITION.混乱, 0);
		}
		return true;
	}

	private boolean eyeToEye() {
		if (LV == 4) {
			return false;
		} else if (attack_possible()) {
			return false;
		} else {
			return direction != Player.me.direction.getReverse();
		}
	}

	@Override
	protected Point getAttackPoint() {
		return new Point(screen_point.x + attack_delt_point.x / 2,
				screen_point.y + attack_delt_point.y / 2);
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, 0);
		} else {
			return super.getImage();
		}
	}

	@Override
	protected boolean specialAttack() {
		direction = converDirection(Player.me.getMassPoint());
		Message.set(getName(), "の瞳が光る");
		if (!eyeToEye()) {
			Message.set("目を合わせられた！");
			Player.me.setDirection(DIRECTION.getDirection(Player.me, this));
		}
		setAnimation(new EyeEffect(Player.me, new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (eyeToEye()) {
					Message.set("しかし目が合っていなくて大丈夫だった");
				} else if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.瞳)) {
					Message.set("しかし波長は乱されなかった");
				} else if (effectCheck()) {
					Message.set("しかし波長は乱されなかった");
				} else {
					TaskOnMapObject.setFlagSkipPlayerTurn(true);
				}
			}
		}, LV));
		return true;
	}

	@Override
	protected boolean specialCheck() {
		int length = LV == 4 ? 10 : LV;
		if (range(length, true)) {
			if (!TaskOnMapObject.isFlagSkipPlayerTurn() && isSpecialParcent()) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		if (attaking_frame < 3) {
			attack_No = 0;
		} else {
			attack_No = attaking_frame - 3;
		}
		if (attack_No >= 7) {
			attack_No = 1;
		}
	}

}
