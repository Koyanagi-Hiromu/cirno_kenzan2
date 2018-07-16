package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 封獣ぬえ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private static boolean flag = false;

	public static boolean isNue() {
		return isNue(1);
	}

	public static boolean isNue(int lv) {
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (em instanceof 封獣ぬえ && em.isSkillActive()) {
				if (lv <= ((封獣ぬえ) em).LV) {
					em.whereIsPlayer(false);
					if (em.player_is_in_sight) {
						if (!flag) {
							Message.set("HPが不明になった");
							SE.AMANOJACK.play();
							MainMap.addEffect(new DoronEffect(Player.me
									.getMassPoint().getLocation(), null, true,
									false), false);
						}
						flag = true;
						return true;
					}
				}
			}
		}

		if (lv <= 1) {
			if (flag) {
				Message.set("HPがよく分かるようになった");
			}
			flag = false;
		}
		return false;
	}

	public 封獣ぬえ(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	protected int getFootDeltY() {
		return super.getFootDeltY() + 1;
	}

	@Override
	protected boolean specialAttack() {
		return true;
	}

	//
	// @Override
	// protected int getSpecialParcent() {
	// return special_attack_parcent;
	// }
	//
	// private boolean isChengeEnemy() {
	// Point p = getMassPoint();
	// List<Base_Creature> list = new ArrayList<Base_Creature>();
	// Base_Creature c;
	// for (int i = -1; i <= 1; i++) {
	// for (int j = -1; j <= 1; j++) {
	// if (i == 0 && j == 0) {
	// continue;
	// }
	// c = MapList.getCreature(p.x + i, p.y + j);
	// if (c != null) {
	// if (!(c instanceof Base_NPC) && !(c instanceof 封獣ぬえ)) {
	// list.add(c);
	// c = null;
	// }
	// }
	// }
	// }
	// if (list.isEmpty()) {
	// System.out.println("ぬえ無い");
	// return false;
	// }
	// int select = new A().nextInt(list.size());
	// Base_Creature bc = list.get(select);
	// final Base_Enemy em;
	// if (bc instanceof Player) {
	// em = new チルノ(p, getLV());
	// } else {
	// em = EnemyTable.returnBaseEnemySetPoint(bc.getClass()
	// .getSimpleName(), bc.getLV(), getMassPoint());
	// }
	// final Base_Creature C = this;
	// if (em != null) {
	// em.massCheck();
	// }
	// System.out.println("ぬえ" + em);
	// if (WithinOutofScreen.isOutside(this)) {
	// TaskOnMapObject.addEnemyRemoveTask(C);
	// TaskOnMapObject.addEnemyPlusTask(em);
	// em.enemy_actioned = true;
	// } else {
	// Message.set("ぬえは", em.getColoredName(), "に変身した");
	// MainMap.addEffect(new DoronEffect(mass_point, new Task() {
	// @Override
	// public void work() {
	// }
	//
	// @Override
	// protected void work(int frame) {
	// if (frame == 4) {
	// TaskOnMapObject.addEnemyRemoveTask(C);
	// TaskOnMapObject.addEnemyPlusTask(em);
	// em.enemy_actioned = true;
	// }
	// }
	// }, true), true);
	// }
	//
	// return true;
	// }
	//
	// @Override
	// public Image getImage() {
	// if (getAnimation() != null) {
	// return IM.getATKImage(LV, direction, 0);
	// } else {
	// return super.getImage();
	// }
	// }

	//
	// @Override
	// protected boolean specialCheck() {
	// if (isSpecialParcent()) {
	// if (isChengeEnemy()) {
	// return true;
	// }
	// }
	// return false;
	// }
	@Override
	protected boolean specialCheck() {
		return false;
	}

}
// package dangeon.model.object.creature.enemy;
//
// import java.awt.Image;
// import java.awt.Point;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;
//
// import main.res.CHARA_IMAGE;
// import main.res.SE;
// import dangeon.model.condition.CONDITION;
// import dangeon.model.config.table.EnemyTable;
//
// public class 封獣ぬえ extends Base_Enemy {

// super(p, Lv);
// CHANGED_ENEMY = chengeIm();
// try {
// if (CHANGED_ENEMY != null) {
// CHANGED_IM = CHARA_IMAGE.get(CHANGED_ENEMY.getClass());
// } else {
// CHANGED_IM = IM;
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
//
// @Override
// public SE getAttackSE() {
// if (CHANGED_ENEMY == null) {
// return super.getAttackSE();
// } else {
// return CHANGED_ENEMY.getAttackSE();
// }
// }
//
// @Override
// public int getFootX() {
// if (CHANGED_ENEMY == null) {
// return super.getFootX();
// } else {
// return CHANGED_ENEMY.getFootX();
// }
// }
//
// @Override
// public int getFootY() {
// if (CHANGED_ENEMY == null) {
// return super.getFootY();
// } else {
// return CHANGED_ENEMY.getFootY();
// }
// }
//
// @Override
// public int getHeadX() {
// if (CHANGED_ENEMY == null) {
// return super.getHeadX();
// } else {
// return CHANGED_ENEMY.getHeadX();
// }
// }
//
// @Override
// public int getHeadY() {
// if (CHANGED_ENEMY == null) {
// return super.getHeadY();
// } else {
// return CHANGED_ENEMY.getHeadY();
// }
// }
//
// private Base_Enemy chengeIm() {
// List<Base_Enemy> list = EnemyTable.nue();
// if (list.isEmpty()) {
// return null;
// }
// int select = new A().nextInt(list.size());
// Base_Enemy em = list.get(select);
// em.setLV(this.LV);
// name = em.getColoredName();
// return em;
// }
//
// @Override
// public Image getImage() {
// if (conditionCheck(CONDITION.封印)) {
// return super.getImage();
// } else {
// CHARA_IMAGE origine = IM;
// IM = CHANGED_IM;
// Image i = super.getImage();
// IM = origine;
// return i;
// }
// }
//
// @Override
// protected void enemyBreakAction() {
// name = originalName();
// CHANGED_IM = IM;
// }
//
// private String originalName() {
// switch (LV) {
// case 1:
// return enemy_name;
// case 2:
// return enemy_name2;
// case 3:
// return enemy_name3;
// default:
// return null;
// }
// }
//
// @Override
// public String getColoredName() {
// if (conditionCheck(CONDITION.封印)) {
// name = originalName();
// }
// return super.getColoredName();
// }
//
// @Override
// protected String nameLevel() {
// if (conditionCheck(CONDITION.封印)) {
// return originalName();
// } else {
// return name;
// }
// }
//
// @Override
// protected List<Integer> statusLevel() {
// List<Integer> list = new ArrayList<Integer>();
// switch (LV) {
// case 1:
// list.add(HP);
// list.add(STR);
// list.add(DEF);
// list.add(EXP);
// break;
// case 2:
// list.add(HP2);
// list.add(STR2);
// list.add(DEF2);
// list.add(EXP2);
// break;
// case 3:
// list.add(HP3);
// list.add(STR3);
// list.add(DEF3);
// list.add(EXP3);
// break;
// }
// return list;
// }
//
// @Override
// protected boolean specialCheck() {
// return false;
// }
//
// @Override
// protected void addListCategory() {
//
// }
// }
