package dangeon.model.object.creature.enemy;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;

import main.res.Image_Object;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印炎;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.view.anime.ExplosionEffect;
import dangeon.view.detail.MainMap;

public class 人形 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag_doll_master;

	private DIRECTION doll_direction = DIRECTION.NEUTRAL;

	public 人形(Point p, int Lv) {
		this(p, Lv, false);
	}

	public 人形(Point p, int Lv, boolean flag_doll_master) {
		super(p, Lv);
		IM = null;
		this.setPlayerSide(true);
		this.flag_doll_master = flag_doll_master;
	}

	@Override
	public void action() {
		if (flag_doll_master && BonusConductor.人形使い_前進()) {
			TaskOnMapObject.addEnemyMoveTask(this);
		} else {
			enemy_actioned = true;
		}
		enemy_actioned = true;
	}

	@Override
	protected void enemyBreakAction() {
		if (flag_doll_master) {
			if (!isSkillActive(true)) {
				return;
			}
			Message.set(getColoredName(), "は爆発した", Color.WHITE.toString());
			explode();
		}
	}

	private void explode() {
		flag_doll_master = false;
		if (BonusConductor.人形使い_爆発強化()) {
			MapInSelect.explosion(getMassPoint());
		} else {
			MainMap.addEffect(new ExplosionEffect(mass_point, null));
			Point p = mass_point;
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				Base_Creature passive = MapList.getCreature(d.getFrontPoint(p
						.getLocation()));
				int damage = 40;
				int DAMAGE;
				if (passive != null) {
					DAMAGE = passive.damagedWithFire(damage);
					if (passive instanceof Player) {
						DAMAGE = 印炎.expDamege(DAMAGE);
					}
					Damage.damage(this, null, this.getColoredName()
							+ "の爆発に巻き込まれて倒れた", null, passive, DAMAGE);
				}
			}
		}
	}

	@Override
	public Image getImage() {
		return Image_Object.身代わり人形.getImage();
	}

	public boolean isMadeByDollMaster() {
		return flag_doll_master;
	}

	@Override
	protected int itemDropParcent() {
		return 0;
	}

	@Override
	public void moving() {
		if (flag_doll_master && BonusConductor.人形使い_前進()) {
			if (moveCheck(doll_direction, false)) {
				enemy_move(doll_direction.X, doll_direction.Y, doll_direction);
				enemy_actioned = true;
			}
		}
	}

	public 人形 setDollDirection(DIRECTION d) {
		doll_direction = d;
		return this;
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
