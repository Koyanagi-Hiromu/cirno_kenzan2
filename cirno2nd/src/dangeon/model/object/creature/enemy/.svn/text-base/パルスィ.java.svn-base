package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.Random;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印炎;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.ExplosionEffect;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.detail.MainMap;

public class パルスィ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public パルスィ(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean condition() {
		StringBuilder sb = new StringBuilder();
		if (isBadCondition() && !Player.me.isBadCondition()) {
			sb.append("「私だけバッドステータスだなんて！」@");
		}
		if (getConditionList().isEmpty() || isBadCondition()) {
			if (!Player.me.getConditionList().isEmpty()
					&& !Player.me.isBadCondition()) {
				sb.append("「あなただけグッドステータスだなんて！」@");
			}
		}
		if (Player.me.getHP() == Player.me.getMAX_HP()) {
			sb.append("「元気いっぱいだなんて妬ましい！」@");
		}
		if (getHP() < Player.me.getHP()) {
			sb.append("「私よりＨＰがあるなんて妬ましい！」@");
		}
		Random rnd = new R();
		int p = 32;
		if (conditionCheck(CONDITION.イカリ)) {
			sb.append("「私のイカリが有頂天！」@");
		}
		if (rnd.nextInt(p) == 0)
			sb.append("「リア充爆発しろ！」@");
		if (rnd.nextInt(p) == 0)
			sb.append("「シンジャエバインダー！」@");
		if (rnd.nextInt(p) == 0)
			sb.append("「あなたを妬む理由なんていくらでも作れるわ！」@");
		if (rnd.nextInt(p) == 0) {
			sb.append("「地下");
			sb.append(MapList.getFloor());
			sb.append("Ｆまで潜ってきたのが妬ましい！」@");
		}
		if (rnd.nextInt(p) == 0)
			sb.append("「ダンジョンが666階までないなんておかしいわ！」@");
		if (rnd.nextInt(p) == 0)
			sb.append("「ヒャッハー！消毒だー！！」@");
		if (rnd.nextInt(p) == 0)
			sb.append("「私にも帽子やリボンをください！」@");
		if (rnd.nextInt(p) == 0)
			sb.append("「私の名前はパルシーでもパルシィでもない！」@");
		if (rnd.nextInt(p) == 0)
			sb.append("「７日もかけて呪ったのに結局あいつは幸せそう！」@");
		if (rnd.nextInt(p) == 0)
			sb.append("「パルパルパル！」@");
		if (rnd.nextInt(p) == 0)
			sb.append("「ぱっる～♪ぱ～るぱ～るりらら～♪」@");

		Message.set("ジェラシーボンバーだ！");
		if (sb.length() > 0) {
			String[] arr = sb.toString().split("@");
			Message.set(arr[rnd.nextInt(arr.length)]);
		}
		return true;
	}

	private void effect() {
		condition();
		Player.me.startDamaging();
		setAnimation(new Special_Wait_FrameByFrame(this, null, 0, new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
			};

			@Override
			public void work(int frame) {
				if (frame == 2) {
					MainMap.addEffect(new ExplosionEffect(Player.me
							.getMassPoint(), null));
				} else if (frame == 6) {
					// Player.me.setHP(getHP());
					int dam = Player.me.getHP() / 2;
					dam = 印炎.expDamege(dam);
					if (dam >= Player.me.getHP()) {
						// HPが１は残る
						dam--;
					}
					Player.me.chengeHP(null, null, -dam);
				}
			}
		}, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 4, 4, 5, 5, 6, 6, 7,
				7, 8, 8, 9, 9));
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		for (Base_Creature c : MapInSelect.getListAroundInCreature(Player.me
				.getMassPoint())) {
			Damage.damageNoMessage(null, null, Player.me, c,
					c.getMAX_HP() / 2 + 1);
		}
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (range(LV, LV < 4)) {
			direction = converDirection(Player.me.getMassPoint());
			effect();
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		int length = LV == 4 ? 3 : LV;
		return isSpecialParcent() && range(length, LV < 4);
	}
}
