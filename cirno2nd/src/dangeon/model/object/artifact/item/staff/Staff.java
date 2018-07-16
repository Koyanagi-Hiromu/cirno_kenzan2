package dangeon.model.object.artifact.item.staff;

import java.awt.Point;
import java.util.Random;

import main.res.Image_Artifact;
import main.util.DIRECTION;
import dangeon.controller.ThrowingItem;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;

public abstract class Staff extends Base_Item {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 「完全識別」されたアイテムかどうか<br/>
	 * アイテム名は分かるが詳細（呪いと強さ）が分からない状態は「static識別」
	 */
	private boolean checked_perfect = false;

	/**
	 * effectの直前に判定<br />
	 * ダメージ系の杖で誰が振ったか調べてます
	 */
	protected Base_Creature used_creature;

	protected Staff(Point p, String item_name, int level) {
		super(p, item_name, 0, ITEM_CASE.STAFF);
		Random ran = new R();
		addStaffRest((ran.nextInt(5) + 2));
		IM = Image_Artifact.STAFF;
		sim = ENCHANT_SIMBOL.杖;
	}

	@Override
	protected void action() {
	}

	@Override
	public void check() {
		checked_perfect = true;
		super.check();
	}

	protected abstract void effect(Base_Creature c);

	protected boolean effect_checker() {
		return true;
	}

	protected void effectHitCheck(Base_Creature c, Base_Creature sorce) {
		if (c.staffHitCheck(this)) {
			pre_effect(c, sorce);
		}
	}

	protected int enchantAttack(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	@Override
	protected int enchantDefence(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	public void enemyStaffUse(Base_Creature c) {
		this.direction = c.getDirection();
		A = new MagicBullet(this, c);
		A.itemThrow(c);
	}

	@Override
	public void firstMsgAtUsingThis() {
	}

	@Override
	public String getClassName() {
		return "杖：";
	}

	@Override
	public String getLastPackage() {
		return "staff";
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		sb.append(getName());
		if (isPerfectCheked() || flag_open_numbers) {
			sb.append("[");
			sb.append(staff_rest);
			sb.append("]");
		} else if (staff_use_count != 0) {
			sb.append("[");
			sb.append(staff_use_count);
			sb.append("]");
		} else {
			sb.append("[");
			sb.append("?");
			sb.append("]");
		}
		return sb;
	}

	@Override
	protected String getSecondExplain_ByCategory() {
		return "使用すると飛距離無限の必中の魔法弾を飛ばし当たったものに効果を与える。投げて当てても同じ効果が発生する。残り回数がゼロになるまで複数回使うことができる。";
	}

	@Override
	public int getShadow() {
		return 5;
	}

	protected boolean hitWall(Base_Creature c) {
		return false;
	}

	public boolean hitWall(ThrowingItem throwingItem) {
		if (hitWall(throwingItem.C)) {
			if (!(throwingItem.C instanceof Base_Enemy) && effect_checker()) {
				staticCheck();
				Config.saveItemData(this);
			}
		}
		return false;
	}

	public boolean isMagicHitToItem() {
		return false;
	}

	public boolean isNeglectiveForWall_Magic() {
		return false;
	}

	@Override
	public boolean isPerfectCheked() {
		return checked_perfect;
	}

	public void itemEffect(Base_Creature c) {
		effect(c);
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		pre_effect(c, c2);
	}

	// public boolean itemUse() {
	// if (getStaffRest() <= 0) {
	// MessageRecord.setMessageTask(new String[] {
	// getName().concat("の回数が０の為"), "使えなかった" });
	// return true;
	// }
	//
	// staffUse();
	// staff_use_count--;
	// setStaffRest(getStaffRest() - 1);
	// return true;
	// }
	@Override
	public boolean itemUse() {
		if (getStaffRest() <= 0) {
			Player.me.setNoMagic();
			Message.set(new String[] { getColoredName().concat("の回数が０の為"),
					"使えなかった" });
			if (isStaticCheked()) {
				check();
			} else {
				flag_open_numbers = true;
			}
			return true;
		}
		Message.set(getColoredName(), "を振った");
		staff_use_count--;
		addStaffRest(-1);
		this.direction = Player.me.getDirection();
		A = new MagicBullet(this);
		A.itemThrow(Player.me, HowToThrow.MAGIC);
		// staffUse();
		return true;
	}

	// protected boolean staffUse(Base_Creature c) {
	// Base_Enemy em = way();
	// if (em == null)
	// return true;
	// effectHitCheck((Base_Creature) em);
	// return true;
	// }

	private void pre_effect(Base_Creature c, Base_Creature sorce) {
		if (!(sorce instanceof Base_Enemy) && effect_checker()) {
			staticCheck();
			Config.saveItemData(this);
		}
		this.used_creature = sorce;
		effect(c);
	}

	public void staffUse() {
		this.direction = Player.me.getDirection();
		new MagicBullet(this).itemThrow(Player.me, HowToThrow.MAGIC);
	}

	protected Base_Creature way(Base_Creature c) {
		DIRECTION d = c.getDirection();
		return wayCheck(c.getMassPoint().getLocation(), d.X, d.Y);
	}

	private Base_Enemy wayCheck(Point p, int i, int j) {
		while (true) {
			p.x += i;
			p.y += j;
			if (MapInSelect.MapMaxPoint(p)) {
				return null;
			}
			if (MapList.getEnemy(p) != null) {
				return MapList.getEnemy(p);
			} else if (!MassCreater.getMass(p).WALKABLE) {
				return null;
			} else {
				continue;
			}
		}
	}
}
