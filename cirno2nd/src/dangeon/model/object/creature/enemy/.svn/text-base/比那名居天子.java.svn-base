package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import main.util.DIRECTION;
import main.util.FrameShaker;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.view.anime.Special_Wait;
import dangeon.view.anime.Special_Wait_Effect;

public class 比那名居天子 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag_sword;

	public 比那名居天子(Point p, int Lv) {
		super(p, Lv);
	}

	;

	private final int damage() {
		switch (LV) {
		case 1:
			return 40;
		case 2:
			return 60;
		case 3:
			return 80;
		default:
			return 80;
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		SE.ATTACK_SWORD.play();
		Player.me.normalAttack();
		return true;
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null && flag_sword) {
			return IM.getATKImage(LV, direction, getAnimation().getComa());
		}
		return super.getImage();
	}

	private boolean isSlash() {
		if (!player_is_in_sight) {
			return false;
		}
		if (attack_possible()) {
			return false;
		}
		ArrayList<Base_Creature> list = MapInSelect
				.getList8DirectionAttackHitCreature(getMassPoint(), 2);
		if (!list.contains(Player.me)) {
			return false;
		}
		return true;
	}

	private boolean push() {
		if (!attack_possible()) {
			return false;
		}
		flag_sword = false;
		final 比那名居天子 THIS = this;
		Point p = Player.me.getMassPoint().getLocation();
		final DIRECTION d = converDirection(Player.me.getMassPoint());
		p.translate(d.X, d.Y);
		if (!MassCreater.isStandMass(p) || MassCreater.getMass(p).WATER) {
			return false;
		}
		final Task slash;
		slash = new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				slash();
			}
		};
		Message.set(getColoredName(), "は", Player.me.getColoredName(),
				"突き飛ばした！");

		Task push = new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				MapInSelect.吹き飛ばし(THIS, "突き飛ばされて", Player.me, d, 1, damage(),
						slash);
			}
		};
		SE.ATTACK_HEAVY.play();
		// startAttack(push);
		setAnimation(new Special_Wait_Effect(4, push));
		return true;
	}

	private boolean slash() {
		if (!isSlash()) {
			System.out.println("slash");
			return false;
		}
		flag_sword = true;
		final 比那名居天子 THIS = this;
		Point p = THIS.getMassPoint().getLocation();
		final DIRECTION d = converDirection(Player.me.getMassPoint());
		p.x += d.X;
		p.y += d.Y;
		final Point _p = p;
		Task move = new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				THIS.direction = d;
				Message.set(getColoredName(), "は素早く近づいた！");
				direction = converDirection(Player.me.getMassPoint());
				FrameShaker.doneNormaly();
				SE.ATTACK_SWORD.play();
				Damage.normalAttack(THIS, Player.me);
				setAnimation(new Special_Wait(THIS, 3, 2));
			}
		};
		THIS.setMassPoint_WalkLike(_p, 2, move);
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			return push();
		}
		return slash();
	}

	@Override
	protected boolean specialCheck() {
		if (!isSpecialParcent()) {
			return false;
		}
		if (attack_possible()) {
			return true;
		}
		if (isSlash()) {
			return true;
		}
		return false;
	}
}
