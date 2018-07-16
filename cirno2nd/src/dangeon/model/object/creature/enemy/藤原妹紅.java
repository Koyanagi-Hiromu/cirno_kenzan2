package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.SE;
import main.util.FrameShaker;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.ResistWeakPoint;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.FireEffect;
import dangeon.view.detail.View_Sider;

public class 藤原妹紅 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 藤原妹紅(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public int damagedWithFire(int damage) {
		addLV(1);
		return 0;
	}

	@Override
	protected Point getAttackPoint() {
		if (getAnimation() == null) {
			return super.getAttackPoint();
		} else {
			return new Point(screen_point.x + attack_delt_point.x / 2,
					screen_point.y + attack_delt_point.y / 2);
		}
	}

	@Override
	public SE getAttackSE() {
		if (getAnimation() == null) {
			return SE.ATTACK_FIRE;
		} else {
			return null;
		}
	}

	@Override
	public int getConvertedLV() {
		if (LV <= 6) {
			return 1;
		} else if (LV <= 12) {
			return 2;
		} else if (LV < 99) {
			return 3;
		} else {
			return 4;
		}
	}

	@Override
	public int getDeConvertedLV(int lv) {
		if (lv == 1) {
			return 1;
		} else if (lv == 2) {
			return 7;
		} else if (lv == 3) {
			return 13;
		} else {
			return 99;
		}
	}

	@Override
	public Image getImage() {
		int LV = getConvertedLV();
		if (isAttacking()) {
			return getATKImage(LV, attacking_direction, attack_No);
		} else if (flag_damaging) {
			return IM.getDamImage(LV, direction);
		}
		return IM.getWalkImage(LV, direction, getMoveAnimationSpeed());
	}

	@Override
	protected CONDITION getInitCondition() {
		return CONDITION.炎上;
	}

	@Override
	public int getMaxLV() {
		return 99;
	}

	@Override
	public boolean resistOrWeakAction(ResistWeakPoint res, int damage) {
		addLV(1);
		return true;
	}

	@Override
	public void setNameAndStatus() {
		int lv = LV;
		LV = 1;
		super.setNameAndStatus();
		int MAX_HP = this.getMAX_HP();
		int MAX_STR = this.MAX_STR;
		int MAX_DEF = this.MAX_DEF;
		int ENEMY_EXP = this.ENEMY_EXP;
		LV = 2;
		super.setNameAndStatus();
		int mlp = lv - 1;
		setFirstStatus(MAX_HP + this.getMAX_HP() * mlp, MAX_STR + this.MAX_STR
				* mlp, MAX_DEF + this.MAX_DEF * mlp);
		this.ENEMY_EXP = ENEMY_EXP * lv;
		LV = lv;
		this.name = name.replaceAll("[0-9]", "").concat(String.valueOf(LV));
	}

	@Override
	protected void setStateChengeByLevelUp() {
		setConditionList(CONDITION.炎上, 0);
	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			direction = converDirection(Player.me.getMassPoint());
			final Base_Creature c = this;
			FrameShaker.doneSoftly();
			startAttack(new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					int str = getSTR();
					setSTR(str * 125 / 100);
					if (Damage.normalAttack(c, Player.me) > 0) {
						setAnimation(new FireEffect(Player.me, new Task() {
							/**
							 *
							 */
							private static final long serialVersionUID = 1L;

							@Override
							public void work() {
								ArrayList<Base_Artifact> list = Belongings
										.getListFreezeItems();
								if (list.isEmpty()) {
									list = Belongings.getListItemNoEnchant();
									for (Iterator<Base_Artifact> iterator = list
											.iterator(); iterator.hasNext();) {
										Base_Artifact a = iterator.next();
										if (a.getListComposition().contains(
												ENCHANT_SIMBOL.金))
											iterator.remove();
									}
									if (list.isEmpty()) {
										return;
									}
									int select = new R().nextInt(list.size());
									Base_Artifact a = list.get(select);
									if (EnchantSpecial.enchantSimbolAllCheck(
											CASE.RING, ENCHANT_SIMBOL.護)) {
										Message.set(a.getColoredName(),
												"を燃やされそうになったが神の加護があって大丈夫だった");
									} else {
										Belongings.remove(a);
										Message.set(a.getColoredName(),
												"を燃やされた");
										View_Sider.setInformation(
												a.getColoredName(), "が燃やされました");
										if (ItemTable.getRank(a) >= 4) {
											Medal.敵に珍しいアイテムを壊された.addCount();
										}
									}

									return;
								} else {
									int select = new R().nextInt(list.size());
									Base_Artifact a = list.get(select);
									Message.set("凍っていた", a.getColoredName(),
											"が熱で溶けた");
									View_Sider.setInformation("凍っていた",
											a.getColoredName(), "が溶けました");
									a.freezeCountReset();
								}
							}
						}));
					}
					setSTR(str);
				}
			});
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (conditionCheck(CONDITION.炎上)) {
			if (getConvertedLV() >= 3) {
				for (Base_Creature c : MapInSelect
						.getListAroundInCreature(getMassPoint())) {
					if (c instanceof Base_Enemy) {
						if (!c.conditionCheck(CONDITION.炎上)) {
							Message.set(c.getColoredName(), "は炎に包まれた");
							SE.ATTACK_FIRE.play();
							c.setCondition(CONDITION.炎上, LV);
						}
					}
				}
			}
			if (attack_possible() && isSpecialParcent()) {
				return true;
			}
		}
		return false;
	}
}
