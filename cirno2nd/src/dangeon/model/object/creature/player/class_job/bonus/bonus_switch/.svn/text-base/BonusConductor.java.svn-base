package dangeon.model.object.creature.player.class_job.bonus.bonus_switch;

import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.BaseClassJob;
import dangeon.model.object.creature.player.class_job.Classひねくれ者;
import dangeon.model.object.creature.player.class_job.Classゆっくり;
import dangeon.model.object.creature.player.class_job.Classフラワーマスター;
import dangeon.model.object.creature.player.class_job.Class人工太陽;
import dangeon.model.object.creature.player.class_job.Class人形使い;
import dangeon.model.object.creature.player.class_job.Class冒険家;
import dangeon.model.object.creature.player.class_job.Class半人半霊;
import dangeon.model.object.creature.player.class_job.Class守矢神;
import dangeon.model.object.creature.player.class_job.Class気分屋;
import dangeon.model.object.creature.player.class_job.Class蓬莱人形;
import dangeon.model.object.creature.player.class_job.Class賢将;
import dangeon.model.object.creature.player.class_job.Class風水師;

public class BonusConductor {
	private static boolean check(Class<? extends BaseClassJob> clazz, int child) {
		if (Player.me.getClassJob().getClass().equals(clazz)) {
			return Player.me.getClassJob().checkSwicth(child);
		}
		return false;

	}

	public static boolean checkSpec() {
		return Player.me.getClassJob().checkSpec();
	}

	public static boolean カリスマ_minus20() {
		return check(Class賢将.class, 0);
	}

	public static boolean ひねくれ者_攻撃受け() {
		return check(Classひねくれ者.class, 3);
	}

	public static boolean ひねくれ者_受け() {
		return check(Classひねくれ者.class, 0);
	}

	public static boolean ひねくれ者_受け半減() {
		return check(Classひねくれ者.class, 4);
	}

	public static boolean ひねくれ者_特技拡張() {
		return check(Classひねくれ者.class, 5);
	}

	public static boolean ひねくれ者_特技強化() {
		return check(Classひねくれ者.class, 2);
	}

	public static boolean ひねくれ者_氷装備禁止() {
		return check(Classひねくれ者.class, 6);
	}

	public static boolean フラワーマスター() {
		return check(Classフラワーマスター.class, 0);
	}

	public static boolean ゆっくり_最大HPボーナス２() {
		return check(Classゆっくり.class, 0);
	}

	public static boolean ゆっくり_最大HP常時１０() {
		return check(Classゆっくり.class, 2);
	}

	public static boolean ゆっくり＿被ダメージ量１() {
		return check(Classゆっくり.class, 3);
	}

	public static boolean ゆっくり＿被ダメージ量半減() {
		return check(Classゆっくり.class, 1);
	}

	public static boolean ゆっくり_魔投結界() {
		return check(Classゆっくり.class, 4);
	}

	public static boolean 気分屋_氷禁止() {
		return check(Class気分屋.class, 0);
	}

	public static boolean 守矢神() {
		return false;
	}

	public static boolean 守矢神_口封じ() {
		return check(Class守矢神.class, 0);
	}

	public static boolean 人形使い_HP上昇制限() {
		return check(Class人形使い.class, 1);
	}

	public static boolean 人形使い_呼び寄せ() {
		return check(Class人形使い.class, 3);
	}

	public static boolean 人形使い_寿命低下() {
		return check(Class人形使い.class, 5);
	}

	public static boolean 人形使い_前進() {
		return check(Class人形使い.class, 4);
	}

	public static boolean 人形使い_任意爆破() {
		return check(Class人形使い.class, 1);
	}

	public static boolean 人形使い_爆発強化() {
		return check(Class人形使い.class, 2);
	}

	public static boolean 人工太陽_敵爆発() {
		return check(Class人工太陽.class, 1);
	}

	public static boolean 人工太陽_爆発２倍() {
		return check(Class人工太陽.class, 0);
	}

	public static boolean 半人半霊() {
		return check(Class半人半霊.class, 0);
	}

	public static boolean 風水師_炎水() {
		return check(Class風水師.class, 0);
	}

	public static boolean 風水師_攻防０() {
		return check(Class風水師.class, 0);
	}

	public static boolean 風水師_水場３倍() {
		return check(Class風水師.class, 3);
	}

	public static boolean 風水師_浮島４倍() {
		return check(Class風水師.class, 4);
	}

	public static boolean 風水師_雷() {
		return check(Class風水師.class, 1);
	}

	public static boolean 蓬莱人形_自動復活() {
		return check(Class蓬莱人形.class, 4);
	}

	public static boolean 蓬莱人形_被ダメージ量２倍() {
		return check(Class蓬莱人形.class, 0);
	}

	public static boolean 蓬莱人形_氷カウント０() {
		return check(Class蓬莱人形.class, 1);
	}

	public static boolean 蓬莱人形_復活時炎上() {
		return check(Class蓬莱人形.class, 2);
	}

	public static boolean 蓬莱人形_復活時自爆() {
		return check(Class蓬莱人形.class, 3);
	}

	public static boolean 冒険家() {
		return check(Class冒険家.class, 0);
	}

}
