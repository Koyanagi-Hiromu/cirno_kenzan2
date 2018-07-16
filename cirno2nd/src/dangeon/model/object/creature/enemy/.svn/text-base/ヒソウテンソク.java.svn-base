package dangeon.model.object.creature.enemy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.res.BulletImage;
import main.res.CHARA_IMAGE;
import main.res.SE;
import main.util.BeautifulView;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import main.util.FrameShaker;
import main.util.半角全角コンバーター;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.HiddenDevice;
import dangeon.model.object.artifact.item.arrow.大砲の弾;
import dangeon.model.object.artifact.item.bullet.ドラゴンブレス;
import dangeon.model.object.artifact.item.bullet.目からビーム;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.ObjectPoint;
import dangeon.util.R;
import dangeon.util.ThunderDamage;
import dangeon.view.anime.Special_Wait;
import dangeon.view.anime.ThunderEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.util.WithinOutofScreen;

/**
 * マムルの位置づけ
 * 
 * @author Administrator
 * 
 */
public class ヒソウテンソク extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private transient BufferedImage arm, atk, breaking, dam, walk;
	private transient HiddenDevice hidden;

	private int flag_dying = -1;

	private int max_coma, each_frame;

	private boolean flag_arm;
	private int frame_count = -1;

	private int count = 0;

	public ヒソウテンソク(Point p, int Lv) {
		super(p, Lv);
		load();
	}

	@Override
	public void action() {
		if (!isSkillActive()
				|| Player.me.getConditionList().contains(CONDITION.やりすごし)
				|| Player.me.getConditionList().contains(CONDITION.暗闇)
				|| Player.me.getConditionList().contains(CONDITION.透明))
			enemy_actioned = true;
		else
			super.action();
	}

	private Base_Artifact beam() {
		SE.ATTACK_SHOOT_ICY.play();
		Message.set(getColoredName(), "はビームを放った");
		return new 大砲の弾(this.getMassPoint().getLocation(), false) {
			private static final long serialVersionUID = 1L;

			@Override
			public Image getImage(DIRECTION direction) {
				return BulletImage.eye_beam.getImage(direction);
			}

			@Override
			public int getShadow() {
				return 0;
			}

			@Override
			public void itemHit(Base_Creature target, Base_Creature source) {
				explode(target.getMassPoint(), source);
			}
		};
	}

	@Override
	public boolean chengeHP(Object obj, String str, int delt) {
		if (delt <= -999) {
			resist(getColoredName(), "はフルメタルコーティングだから999以上のダメージを受け付けない！");
			return false;
		}
		return super.chengeHP(obj, str, delt);
	}

	@Override
	protected void drawCreature(Graphics2D g, int x, int y) {
		Image[] ims = MainMap.getPlainWall(PresentField.get().getMapTip());
		MainMap.drawWall(g, ims, x, y);
		flag_arm = false;
		if (flag_dying != -1) {
			flag_dying++;
			if (getAnimation() != null) {
				BeautifulView.setAlphaOnImg(g, 1
						- (float) getAnimation().getComa() / max_coma);
			} else {
				BeautifulView.setAlphaOnImg(g, 0f);
			}
			super.drawCreature(g, x, y);
			BeautifulView.setAlphaOnImg(g, 1f);
		} else {
			super.drawCreature(g, x, y);
			if (isAttacking()) {
				flag_arm = true;
				super.drawCreature(g, x, y);
			}
		}
	}

	@Override
	public boolean enemyLifeCheck() {
		if (getHP() <= 0) {
			if (this.getConditionList().contains(CONDITION.復活)) {
				CONDITION.conditionRecovery(this, CONDITION.復活);
				Message.set(getColoredName(), "は不死鳥のごとく復活した");
				chengeHP(null, null, getMAX_HP());
				return false;
			} else if (recoveryGhost()) {
				return false;
			}
			setBeatedAway();
			return false;
		}
		return false;
	}

	@Override
	protected Point getAttackPoint() {
		return screen_point;
	}

	@Override
	public int getConvertedLV() {
		if (LV <= 2) {
			return 1;
		} else if (LV <= 4) {
			return 2;
		} else if (LV < 10) {
			return 3;
		} else {
			return 4;
		}
	}

	@Override
	protected int getFootDeltY() {
		return -5;
	}

	@Override
	public Image getImage() {
		if (flag_dying != -1) {
			if (getAnimation() != null)
				return getSubImage(breaking, getAnimation().getComa() % 3);
			else
				return getSubImage(breaking, 0);
		} else if (isAttacking()) {
			if (flag_arm)
				return getSubImage(arm, attack_No);
			else
				return getSubImage(atk, attack_No);
		} else if (getAnimation() != null) {
			return getSubImage(breaking, getAnimation().getComa() % 2);
		} else if (flag_damaging) {
			return dam;
		} else {
			return getSubImage(walk,
					CHARA_IMAGE.getKoma_StaticMethod(getMoveAnimationSpeed()));
		}
	}

	@Override
	public int getMaxLV() {
		return 99;
	}

	@Override
	public String getOriginalName() {
		return name;
	}

	@Override
	public int getShadowSize() {
		return super.getShadowSize();
	}

	private BufferedImage getSubImage(BufferedImage im, int index) {
		int length;
		if (im == arm)
			length = 6;
		else if (im == atk)
			length = 6;
		else if (im == breaking)
			length = 3;
		else if (im == walk)
			length = 3;
		else
			length = 1;
		int w = im.getWidth();
		int h = im.getHeight() / length;
		int x = 0;
		int y = h * index;
		return im.getSubimage(x, y, w, h);
	}

	@Override
	protected boolean itemHitEffect(Base_Artifact a, boolean ento) {
		String s;
		if (a instanceof MagicBullet) {
			s = "魔法";
		} else if (a instanceof 目からビーム) {
			s = "ビーム";
		} else
			s = "アイテム";
		resist(getColoredName(), "はフルメタルコーティングだから", s, "を受け付けない！");
		return false;
	}

	@Override
	public void load() {
		arm = loadImage("arm");
		atk = loadImage("atk");
		breaking = loadImage("breaking");
		dam = loadImage("dam");
		walk = loadImage("walk");
		name = "ヒソウテンソク";
		hidden = new HiddenDevice(mass_point);
		MapList.addArtifact(hidden);
	}

	private BufferedImage loadImage(String filename) {
		return FileReadSupporter.readImage("res/image/character_dot/ヒソウテンソク/"
				.concat(filename).concat(".png"));
	}

	private Base_Artifact mera() {
		Message.set(getColoredName(), "は炎を放った");
		return new ドラゴンブレス(this, 20);
	}

	private void resist(String... msg) {
		SE.YOUMU_SP2.play();
		SE.REIMU_BARRIER.play();
		setAnimation(new Special_Wait(this, 3, 4));
		chengeHP(null, null, -1);
		Message.set(msg);

	}

	@Override
	protected boolean resistCondition(CONDITION c) {
		resist(getColoredName(), "はフルメタルコーティングだから状態異常にならない！");
		return true;
	}

	@Override
	protected void setBeatedAway() {
		MapList.removeArtifact(hidden);
		flag_dying = 0;
		SE.GOGOGO.play();
		max_coma = 25;
		each_frame = 4;
		FrameShaker.setShake_time(max_coma * each_frame * FR.THREAD_SLEEP / 2);
		setAnimation(new Special_Wait(this, max_coma, each_frame, SE.GOGOGO, 2,
				new Task() {

					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						Message.set(getColoredName(), "をやっつけた");
						Player.me.setExpCash(getENEMY_EXP());
						setSuperBeatedAway();
					}
				}));
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
		LV = lv;
		setFirstStatus(MAX_HP + LV * 2, MAX_STR + LV, MAX_DEF + LV);
		this.ENEMY_EXP = ENEMY_EXP + lv * 100;

		if (LV > 1) {
			this.name = "ヒソウテンソク".concat("ver").concat(String.valueOf(LV));
			new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					tellHp();
				}
			}.work_appointment();
		}

	}

	private void setSuperBeatedAway() {
		super.setBeatedAway();
	}

	@Override
	protected boolean specialAttack() {
		if (!player_is_in_sight)
			return true;
		if (++count > 50) {
			count = 0;
			tellHp();
			return true;
		} else if (count == 20) {
			String at = "";
			if (!WithinOutofScreen.isOutside(this)) {
				setAnimation(new Special_Wait(this, 9, 4));
			} else {
				at = "@";
			}
			Message.set("「放電準備開始」", at);
			return true;
		} else if (count > 20 && count < 25) {
			String at = "";
			if (!WithinOutofScreen.isOutside(this)) {
				setAnimation(new Special_Wait(this, 3, 4));
			} else {
				at = "@";
			}
			Message.set("「", 半角全角コンバーター.半角To全角数字(25 - count), "」", at);
			return true;
		} else if (count == 25) {
			setAnimation(new Special_Wait(this, 9, 4));
			Message.set(getColoredName(), "の体から雷がほとばしる！");
			Message.set("部屋全体に雷ダメージ！");
			SE.LIGHTNING.play();
			MainMap.addEffect(new ThunderEffect(true, ObjectPoint
					.getDifferenceBetweenPlayer(screen_point.x, screen_point.y)));
			int damage = 50;
			for (Base_Enemy c : MapInSelect.getListRoomOrRoadInEnemy()) {
				thunder(c, damage);
			}
			thunder(Player.me, damage);
			return true;
		} else if (attack_possible()) {
			if (new R().is(15)) {
				startAttack(new Task() {
					/**
					 *
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						if (Damage.normalAttack(ヒソウテンソク.this, Player.me) > 0) {
							FrameShaker.doneNormaly();
							MapInSelect.吹き飛ばし(ヒソウテンソク.this, null, Player.me,
									ヒソウテンソク.this.direction, 10, 0);
						}
					}
				});
				return true;
			} else
				return false;
		} else {
			if (!MapInSelect.isCreatureOnTheStraightAllDirection(this,
					Player.me, Math.max(MassCreater.HEIGHT, MassCreater.WIDTH))) {
				return true;
			}
			direction = converDirection(Player.me.getMassPoint());
			setAnimation(new Special_Wait(this, 3, 4));
			// SE.ATTACK_SHOOT_ICY.play();
			SE.PITFALL_OPEN.play();
			Base_Artifact a;
			int r = new R().nextInt(2);
			if (r == 0)
				a = beam();
			else
				a = mera();
			a.itemThrow(this, HowToThrow.MAGIC,
					Math.max(MassCreater.HEIGHT, MassCreater.WIDTH));
			return true;
		}
		// if (!isSpecialParcent() ) {
		// return true;
		// }

	}

	@Override
	protected boolean specialCheck() {
		return true;
	}

	private void tellHp() {
		if (!WithinOutofScreen.isOutside(this)) {
			setAnimation(new Special_Wait(this, 9, 4));
		}
		Message.set("「残り活動値", getHP(), "デス　戦闘を続行します」");
	}

	private void thunder(Base_Creature c, int dmg) {
		ThunderDamage.thunderDamage(this, this, c, dmg);
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		if (frame_count < 2 * 3) {
			frame_count++;
			attaking_frame--;
		} else if (frame_count == 2 * 3) {
			frame_count++;
			FrameShaker.doneSoftly();
			SE.DAMAGED_CRITICAL.play();
		}
		if (normal_attack_phase == AttackPhase.GO) {
			attack_No += frame_count / 2;
		} else if (attaking_frame == -1) {
			frame_count = -1;
		}
	}

}
