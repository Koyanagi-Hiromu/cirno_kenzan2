package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

import main.constant.PropertySupporter;
import dangeon.model.config.StoryManager;

/**
 * 
 * @author weray enumの表記名はもう変えちゃダメ
 */
public enum Medal {
	//@formatter:off
	経験値,
	最高HP,
	最高ちから,
	攻撃力,
	防御力,
	今までで合計XX体の敵をやっつけた,
	一度に与えた最高ダメージXX,
	一度に受けた最高ダメージXX,
	一本の雷で一度にXX体感電させた,
	一度にXXコの状態異常アイコンを出した,



	壁の中から賽銭箱を発見した ,
	博麗霊夢をエネミー図鑑に完全に登録した ,
	絶対許ザナデゥをやっつけた ,
	特殊合成のカードを作った ,
	魅魔のカードを作った ,
	幻想郷緑起を手に入れた ,
	にとりの魔法瓶で同じアイテムが抽選された ,
	宝箱から出現度Ｓのカードが出現した,


	クリア間近で倒れた ,
	ゆっくりに倒された ,
	ナイフを反射されて倒れた ,
	復活系のアイテムを残して倒れた ,
	おにぎりを食べて倒れた ,
	アチチ草によって燃え尽きた ,
	眠っている間に倒れた ,
	咳をして倒れた ,
	空中から敵にぶつかって倒れた ,
	奇跡が起こって倒れた ,
	泥棒していたら冒険の目的を忘れた ,
	鳳凰の種が呪われていて復活できなかった,


	ルーミアにおにぎりを投げつけた ,
	太陽の弱点を突いた("太陽に弱い敵の弱点を突いた"),
	ザ・咲夜に通常攻撃を当てた ,
	壁の中の敵を封印した ,
	倍速の敵に回復の杖を振った ,
	雛に呪いを吸い取ってもらった ,
	にとりにアイテムを変換させた ,
	敵から透視状態を奪った ,
	早苗におにぎりを投げつけた ,
	アイテムヤッホーにワナの毒ナイフを当てた ,
	雷鼓のレベルを上げた ,
	朱鷺子に書を投げつけた,



	アイテムをまとめて置いた ,
	水路の上に乗ってワープした ,
	アイテムを投げて毒ナイフを起動させた ,
	壁の中に入った ,
	ナイフをまとめて投げて敵を倒した,
	百鬼夜行抄を使用してボスを消した,



	泥棒成功回数 ,
	落としてゐを敷地近くで倒した ,
	変化した階段を敷地内で元に戻した ,
	階段を杖で移動させて敷地内に入れた ,
	階段の上に場所替えして泥棒した ,
	ニ部屋しかないマップで高飛びして泥棒した ,
	泥棒した時エネミー数が上限に達していた,



	話しかける前にNPCにとりを倒してしまった ,
	中身が詰まった瓶を遠投した ,
	賽銭箱を寅丸に投げられて泥棒させられた ,
	忘れられた賽銭箱がさわる前に壊れた ,
	にとりの魔法瓶に攻撃カードを入れた ,
	敵に珍しいアイテムを壊された ,
	弾かれたカードが敵に当たってなくなった ,
	攻撃カードと防御カードをまとめて破棄した ,
	未識別リボンを使用したらレアものだった ,
	未識別の草を飲んだら超不幸の種だった ,
	復活状態が吸われた,
	青娥のカード_呪_を防御から攻撃に変更された("青娥のカード(呪)を防御から攻撃に変更された"),

	//@formatter:on
	special, 気分屋OK, ひねくれOK, 復活回数, 命の草から蓮の花を咲かせた, 人形使いOK;
	public static ArrayList<String> getDeathList() {
		ArrayList<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			Object e = property.getProperty_Nature("death".concat(String
					.valueOf(i)));
			if (e == null)
				return list;
			list.add(e.toString());
			i++;
		}
	}

	private String toString;

	public static PropertySupporter property = StoryManager.property;

	public static void saveDeath(String death_message) {
		property.saveProperty(
				"death".concat(String.valueOf(getDeathList().size())),
				death_message);
	}

	private Medal() {
		toString = name();
	}

	private Medal(String name) {
		toString = name;
	}

	public void addCount() {
		save(getCount() + 1);
	}

	public long getCount() {
		Object obj = property.getProperty_Nature(key());
		try {
			return Long.valueOf(obj.toString());
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean hasFinished() {
		return getCount() > 0;
	}

	private String key() {
		return this.name();
	}

	public void overWrite(long l) {
		save(l);
	}

	private void save(Object o) {
		property.saveProperty(key(), o.toString());
	}

	public void save_the_more(long l) {
		if (l > getCount()) {
			save(l);
		}
	}

	@Override
	public String toString() {
		return toString;
	}

}
