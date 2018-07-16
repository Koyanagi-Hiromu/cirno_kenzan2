package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印招;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.util.WithinOutofScreen;

public class 豊聡耳神子 extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private boolean flag_skill_used;

	private ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();

	public 豊聡耳神子(Point p, int Lv) {
		super(p, Lv);
	}

	private void enemyWarp() {
		int max;
		ArrayList<Base_Enemy> list;
		{
			list = new ArrayList<Base_Enemy>();
			List<Base_Enemy> _list = MapList.getListEnemy();
			for (int i = 0; i < _list.size(); i++) {
				list.add(_list.get(i));
			}
		}
		list.remove(this);
		if (LV == 1) {
			max = 1;
			removeOutsideRoom(list);
		} else if (LV == 2) {
			max = 1;
		} else if (LV == 3) {
			max = 1;
		} else {
			max = 2;
		}
		for (Iterator<Base_Enemy> iterator = list.iterator(); iterator
				.hasNext();) {
			if (iterator.next() instanceof Base_NPC) {
				iterator.remove();
			}
		}
		for (int i = 0; i < max; i++) {
			if (!list.isEmpty()) {
				warp(list.remove(new R().nextInt(list.size())));
			}
		}
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return getATKImage(LV, direction, getAnimation().getComa());
		} else {
			return super.getImage();
		}
	}

	private void perform() {
		if (list.isEmpty()) {
			SE.STATUS_SEAL.play();
			setCondition(CONDITION.封印, 0);
			return;
		}
		final Base_Creature c = list.remove(0);
		final Point p = perform(c);
		if (p == null)
			return;
		this.direction = DIRECTION.getDirection(mass_point, p);
		if (!(c.equals(Player.me)) && WithinOutofScreen.isOutside(this)) {
			c.setMassPoint(p);
			perform();
		} else {
			int[] arr = new int[] { 0, 0, 0, 1, 1, 1 };
			if (c.equals(Player.me)) {
				for (int i = 0; i < arr.length; i++) {
					arr[i] += 3;
				}
			}
			setAnimation(new Special_Wait_FrameByFrame(c,
					SE.ATTACK_SMALL_OBJECT, 3, new Task() {
						/**
				 *
				 */
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							c.setMassPoint_ParabolaJumpAttack(p, new Task() {
								/**
						 *
						 */
								private static final long serialVersionUID = 1L;

								@Override
								public void work() {
									readyToPerform();
								}
							});
						}
					}, arr));
		}
	}

	private Point perform(Base_Creature c) {
		Point p = null;
		boolean flag_success = true;
		if (c.equals(Player.me)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.イカリ)) {
			p = Player.me.getMassPoint().getLocation();
			DIRECTION d = DIRECTION.getDirection(Player.me, this);
			p.translate(d.X, d.Y);
			if (MassCreater.isWalkableFor(c, p)) {
				flag_success = false;
				Message.set("しかし", Player.me.getColoredName(), "は重くてあまり動かなかった");
			} else {
				p = null;
			}
		} else {
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				p = mass_point.getLocation();
				p.translate(d.X, d.Y);
				if (MassCreater.isWalkableFor(c, p))
					break;
				p = null;
			}
		}
		if (p == null) {
			if (c.equals(Player.me)) {
				Message.set("しかし召喚できる場所がなかった");
			}
			return null;
		} else {
			if (c instanceof Base_Enemy) {
				Base_Enemy e = (Base_Enemy) c;
				e.enemy_actioned = true;
				e.flag_2nd_move = e.flag_3rd_move = true;
				CONDITION.wakeUp(e);
				// else{
				// Message.set("「戯れはおわりじゃ！」");
				// }
			}
			if (c instanceof 物部布都) {
				Message.set("「我にお任せを！」");
			} else if (c instanceof 蘇我屠自古) {
				Message.set("「やってやんよ！」");
			} else if (flag_success) {
				Message.set(c.getColoredName(), "が召喚された");
			}

		}
		return p;
	}

	private boolean range() {
		if (MassCreater.getRoom(mass_point) == null)
			return false;
		if (LV == 1 || LV == 2)
			return MassCreater.isPlayerInTheSameRoom(getMassPoint());
		else
			return true;
	}

	private void readyToPerform() {
		if (list.isEmpty()) {
			SE.STATUS_SEAL.play();
			setCondition(CONDITION.封印, 0);
			return;
		}
		setAnimation(new Special_Wait_FrameByFrame(this, null, 4, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				perform();
			}
		}, 0, 0, 0));
	}

	private void removeOutsideRoom(ArrayList<Base_Enemy> list) {
		for (Iterator<Base_Enemy> iterator = list.iterator(); iterator
				.hasNext();) {
			Base_Enemy c = iterator.next();
			if (!MassCreater.isPointInTheSameRoom(getMassPoint(),
					c.getMassPoint())) {
				iterator.remove();
			}
		}
	}

	@Override
	protected boolean specialAttack() {
		if (!range())
			return false;
		list.clear();
		Message.set(getColoredName(), "は召集の詔を発した");
		if (印招.effect()) {
			SE.SUMMON.play();
			Message.set("しかし", Player.me.getColoredName(), "は召喚を拒否した！");
		} else {
			warp(Player.me);
		}
		enemyWarp();
		perform();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible())
			return false;
		if (!isSpecialParcent()) {
			return false;
		}
		if (!range()) {
			return false;
		}
		return true;
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		attack_No = (attaking_frame - 1) / 2;
		if (attaking_frame == 5 && !flag_mute) {
			SE.ATTACK_SWORD.play();
		}
		if (attack_No > 5) {
			attack_No = 5;
		}
	}

	private void warp(Base_Creature c) {
		list.add(c);
	}
}
