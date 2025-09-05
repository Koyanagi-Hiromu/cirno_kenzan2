package dangeon.model.object.creature.enemy;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;
import main.res.SE;
import main.util.FrameShaker;

public class 鬼人正邪 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 鬼人正邪(Point p, int Lv) {
		super(p, Lv);
	}

	private Base_Enemy changePosition() {
		ArrayList<Base_Creature> list = MapInSelect
				.getListRoomInCreatureAtPoint(getMassPoint());
		for (Iterator<Base_Creature> iterator = list.iterator(); iterator
				.hasNext();) {
			Base_Creature c = iterator.next();
			if (c instanceof 鬼人正邪 || !(c instanceof Base_Enemy)) {
				iterator.remove();
			} else {
				Mass mass = MassCreater.getMass(c.getMassPoint());
				if (mass.WATER || !mass.WALKABLE) {
					iterator.remove();
				}
			}
		}
		list.remove(this);
		if (list.isEmpty()) {
			return this;
		} else {
			Base_Enemy c = (Base_Enemy) list.get(new R().nextInt(list.size()));
			Point p = this.getMassPoint();
			this.setMassPoint(c.getMassPoint().getLocation());
			c.setMassPoint(p.getLocation());
			SE.AMANOJACK.play();
			MainMap.addEffect(new DoronEffect(c.getMassPoint(), null, true,
					false), false);
			MainMap.addEffect(new DoronEffect(this.getMassPoint(), null, true,
					false), false);
			Message.set(getColoredName(), "は", c.getColoredName(), "と位置を逆転させた");
			return c;
		}
	}

	public boolean effect() {
		SE.AMANOJACK.play();
		Message.set("あまのじゃく攻撃だ！");
		FrameShaker.doneNormaly();
		final Base_Creature c = this;
		MainMap.addEffect(new DoronEffect(Player.me.getMassPoint(), null, true,
				false), false);
		startAttack(new Task() {
			/**
		 *
		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (LV <= 2) {
					Damage.normalAttack(c, Player.me);
					enchantChange();
				} else if (LV == 3) {
					hpChange();
				}
			}
		});

		return true;
	}

	private void enchantChange() {
		if (!EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.融合)) {

			Base_Artifact atk = Enchant.ATK.getEnchant();
			Base_Artifact def = Enchant.DEF.getEnchant();
			if (LV == 2) {
				ArrayList<Base_Artifact> list = Belongings
						.getListItemNoEnchant();
				for (Iterator<Base_Artifact> iterator = list.iterator(); iterator
						.hasNext();) {
					Base_Artifact a = iterator.next();
					if (!(a instanceof SpellCard)) {
						iterator.remove();
					}
				}
				if (!list.isEmpty()) {
					Base_Artifact a = list.get(new R().nextInt(list.size()));
					Enchant enchant = new R().is(50) ? Enchant.ATK
							: Enchant.DEF;
					enchant.forceToRemove();
					enchant.tryToSet(a);
					SE.SYSTEM_ENCHANT.play();
					return;
				}
			}
			{
				// LV==1｜｜装備候補がない時
				if (atk == null && def == null) {
					Message.set("何も装備してなくて大丈夫だった");
				} else {
					boolean medalFlag = EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.邪);
					Enchant.DEF.setEnchant(atk);
					Enchant.ATK.setEnchant(def);
					SE.SYSTEM_ENCHANT.play();
					SE.SYSTEM_ENCHANT_OFF.play();
					String atk_s = atk != null ? atk.getColoredName()
							: Enchant.ATK.getColor() + "攻撃装備"
									+ Color.WHITE.toString();
					String def_s = def != null ? def.getColoredName()
							: Enchant.DEF.getColor() + "防御装備"
									+ Color.WHITE.toString();

					if (def != null && def.isCurse() && medalFlag && !EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.邪)) {
						Medal.青娥のカード_呪_を防御から攻撃に変更された.addCount();
					}
					Message.set(atk_s.concat("と"), def_s.concat("がひっくり返った"));
				}
			}
		} else {
			Message.set("装備を入れ替えられそうになったが、装備がくっついていて大丈夫だった");
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		// FIXME
		Message.set("未実装");
		return true;
	}

	@Override
	public Image getImage() {
		// if (getAnimation() != null) {
		// return IM.getSPImage(LV, direction, getAnimation().getComa());
		// }
		return super.getImage();
	}

	private void hpChange() {
		Message.set("ＨＰを裏返された");
		int max = Player.me.getMAX_HP();
		int hp = Player.me.getHP();
		int damaged = max - hp;
		int damage = hp - damaged;
		if (damage >= hp) {
			damage = hp - 1;
		}
		Player.me.chengeHP(this, "体を裏返されて倒れた", -damage);
	}

	@Override
	protected void init4() {
		setSpecialHuge();
	}

	@Override
	public boolean isInValidOnAttack() {
		if (!isSkillActive()) {
			return false;
		}
		return changePosition() != this;
	}

	@Override
	public int isKamin() {
		return 0;
	}

	@Override
	public boolean isThroughItem(Base_Artifact a) {
		Base_Enemy e = changePosition();
		if (e.equals(this)) {
			return super.isThroughItem(a);
		} else {
			return e.isThroughItem(a);
		}
	}

//	@Override
//	protected boolean resistCondition(CONDITION c) {
//		if (isPassiveSkillActive() && c != CONDITION.天邪鬼
//				&& !c.equals(CONDITION.特殊仮眠)) {
//			Message.set(getColoredName(), "は状態異常を天邪鬼状態に変換した");
//			SE.AMANOJACK.play();
//			MainMap.addEffect(
//					new DoronEffect(getMassPoint(), null, true, false), false);
//			this.setCondition(CONDITION.天邪鬼, 3);
//			return true;
//		}
//		return false;
//	}

	@Override
	public boolean setCondition(CONDITION c, int timer) {
		return super.setCondition(c, timer);
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible()) {
			return true;
		}
		direction = converDirection(Player.me.getMassPoint());
		return effect();
	}

	@Override
	protected boolean specialCheck() {
		if (!attack_possible()) {
			return false;
		}
		if (!isSpecialParcent()) {
			return false;
		}
		return true;
	}

}
