package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.util.ArrayList;

import dangeon.model.config.StoryManager;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import main.constant.PropertySupporter;

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


	話しかける前にNPCにとりを倒してしまった("話す前にスキマの合成NPCを倒してしまった"),
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
	青娥のカード_呪_を防御から攻撃に変更された("装備がひっくり返って"+ENCHANT_SIMBOL.邪.getName()+"が発揮しなくなった"),

	Class冒険家("冒険家"),
	Class守矢神("守矢神"),
	Classナイフマスター("ナイフマスター"),
	Class人工太陽("人工太陽"),
	Class風水師("風水師"),
	Class人形使い("人形使い"),
	Classゆっくり("ゆっくり"),
	Classフラワーマスター("フラワーマスター"),
	Classパチュリー("パチュリー"),
	Classひねくれ者("ひねくれ者"),
	Class半人半霊("半人半霊"),
	Class気分屋("気分屋"),
	Class蓬莱人形("蓬莱人形"),
	
	Class冒険家_七曜("冒険家"),
	Class守矢神_七曜("守矢神"),
	Classナイフマスター_七曜("ナイフマスター"),
	Class人工太陽_七曜("人工太陽"),
	Class風水師_七曜("風水師"),
	Class人形使い_七曜("人形使い"),
	Classゆっくり_七曜("ゆっくり"),
	Classフラワーマスター_七曜("フラワーマスター"),
	Classパチュリー_七曜("パチュリー"),
	Classひねくれ者_七曜("ひねくれ者"),
	Class半人半霊_七曜("半人半霊"),
	Class気分屋_七曜("気分屋"),
	Class蓬莱人形_七曜("蓬莱人形"),

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
	
	public void saveLevel(int lv)
	{
		if (!isLevel(lv))
			save(getCount() + pow10(lv));
	}
	
	int pow10(int dight)
	{
		switch(dight)
		{
			case 1:
				return 1;
			case 2:
				return 10;
			case 3:
				return 100;
			case 4:
				return 1000;
			case 5:
				return 10000;
			default:
		        return (int)Math.pow(10, dight - 1);
		}
	}
	
	public boolean isLevel(int lv)
	{
		long x = getCount();
		
		switch(lv)
		{
			case 1:
				return x % 10 > 0;
			case 2:
				return (x / 10) % 10 > 0;
			case 3:
				return (x / 100) % 10 > 0;
			case 4:
				return (x / 1000) % 10 > 0;
			case 5:
				return (x / 10000) % 10 > 0;
			default:
		        return (x / (int)Math.pow(10, lv - 1)) % 10 > 0;
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
