package dangeon.model.object.creature.enemy;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.menu.first.adventure.wiki.Wiki_Enemy;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.config.table.ItemDetail;
import dangeon.model.config.table.ItemTable;
import dangeon.model.config.table.SpecialMonsterHouse;
import dangeon.model.config.table.TrapTable;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.scrool.いかづちの書;
import dangeon.model.object.artifact.item.scrool.おにぎりの書;
import dangeon.model.object.artifact.item.scrool.おはらいの書;
import dangeon.model.object.artifact.item.scrool.グリモワール;
import dangeon.model.object.artifact.item.scrool.ダンジョン攻略本;
import dangeon.model.object.artifact.item.scrool.バクスイの書;
import dangeon.model.object.artifact.item.scrool.パワーアップの書;
import dangeon.model.object.artifact.item.scrool.メッキの書;
import dangeon.model.object.artifact.item.scrool.ワイルドカード;
import dangeon.model.object.artifact.item.scrool.人を殺せる書;
import dangeon.model.object.artifact.item.scrool.八咫烏の書;
import dangeon.model.object.artifact.item.scrool.切れ端;
import dangeon.model.object.artifact.item.scrool.大部屋の書;
import dangeon.model.object.artifact.item.scrool.天邪鬼の書;
import dangeon.model.object.artifact.item.scrool.封書モンスターハウス;
import dangeon.model.object.artifact.item.scrool.幻想郷縁起;
import dangeon.model.object.artifact.item.scrool.慧音の歴史書;
import dangeon.model.object.artifact.item.scrool.撒き餌の書;
import dangeon.model.object.artifact.item.scrool.敵倍速の書;
import dangeon.model.object.artifact.item.scrool.明かりの書;
import dangeon.model.object.artifact.item.scrool.氷の書;
import dangeon.model.object.artifact.item.scrool.混乱の書;
import dangeon.model.object.artifact.item.scrool.破裂の書;
import dangeon.model.object.artifact.item.scrool.罠師の書;
import dangeon.model.object.artifact.item.scrool.自爆の書;
import dangeon.model.object.artifact.item.scrool.解凍の書;
import dangeon.model.object.artifact.item.scrool.識別の書;
import dangeon.model.object.artifact.item.scrool.電光石火の書;
import dangeon.model.object.artifact.item.spellcard.朱鷺子のカード;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.action.SpecialAction;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.shop.Shop;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.view.anime.CardAnime;
import dangeon.view.anime.CirnoIceEffect;
import dangeon.view.anime.DecurseEffect;
import dangeon.view.anime.DoronEffect;
import dangeon.view.anime.Special_Wait;
import dangeon.view.anime.SunEffect;
import dangeon.view.detail.MainMap;
import main.res.Image_LargeCharacter;
import main.res.SE;
import main.thread.MainThread;

public class 朱鷺子 extends __店主 {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Base_Artifact item = null;

	private boolean flag_decurse;
	private boolean counter;

	public 朱鷺子(Point p, int Lv) {
		super(p, Lv);
	}

	public 朱鷺子(Point p, int Lv, Shop shop) {
		super(p, Lv, shop, Image_LargeCharacter.朱鷺子);
	}

	@Override
	public void action() {
		if (conditionCheck(CONDITION.安心)) {
			enemy_actioned = true;
		} else
			super.action();
	}

	@Override
	public boolean chengeHP(Object obj, String str, int delt) {
		if (LV == 4) {
			if (delt < -50) {
				Message.set("しかし", getColoredName(), "は５０ダメージに軽減！");
				delt = -50;
			}
			boolean flag = super.chengeHP(obj, str, delt);
			if (!flag && delt < 0 && !counter) {
				counter = true;
				if (isBadCondition() && item == null) {
					item = new ダンジョン攻略本(mass_point);
					readBook();
				} else if (isSpecialParcent()) {
					Message.set(getColoredName(), "は高飛びした");
					SpecialAction.高飛び(this);
					if (isSpecialParcent() || isSpecialParcent()) {
						readBook();
					}
				} else {
					readBook();
				}
			}
			return flag;
		} else {
			return super.chengeHP(obj, str, delt);
		}

	}

	protected void effect(Scrool book) {

		ArrayList<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy e : MapList.getListEnemy()) {
			list.add(e);
		}
		Base_Creature c = list.get(new R().nextInt(list.size()));
		if (c == this) {
			c = Player.me;
		}
		ArrayList<Base_Creature> room = new ArrayList<Base_Creature>(
				list.size());
		list.remove(this);
		for (Base_Creature e : list) {
			room.add(e);
		}
		room.add(Player.me);

		MapInSelect.getListRoomOrRoadInEnemy();
		if (book.isCurse() && !flag_decurse) {
			SE.SYSTEM_CURSE.play();
			Message.set("「しかし", book.getColoredName(), "は呪われていて読めないや」");
		} else if (book.getClass() == おにぎりの書.class) {
			SE.MIRACLE_ONIGIRI.play();
			Message.set("「奇跡は", c.getColoredName(), "に舞い降りた！」");
			c.setConditionNeglectResist(CONDITION.おにぎり, 0);
		} else if (book.getClass() == グリモワール.class) {
			pop(SpecialMonsterHouse.マジックギルド);
		} else if (book.getClass() == ワイルドカード.class) {
			SE.MEKKI.play();
			Message.set("「私のカードが足元に出来たよ！」");
			ItemFall.itemFall(new 朱鷺子のカード(mass_point.getLocation()));
		} else if (book.getClass() == メッキの書.class) {
			pop(SpecialMonsterHouse.八百万の神々);
		} else if (book.getClass() == 解凍の書.class) {
			pop(SpecialMonsterHouse.爆炎地帯);
		} else if (book.getClass() == 自爆の書.class) {
			Message.set("「キャー！」");
			MapInSelect.explosion(mass_point.getLocation());
		} else if (book.getClass() == 識別の書.class) {
			Base_Enemy e = this;
			Config.save(e, e.getConvertedLV(), false);
			SE.CHECK.play();
			Scene_Action a = Scene_Action.getMe();
			StringBuilder sb = new StringBuilder();
			for (STAGE stage : e.getCategory()) {
				if (sb.length() > 0)
					sb.append("&");
				sb.append(stage);
			}
			Base_Enemy en = EnemyTable.returnBaseEnemy(e.getConvertedLV(),
					e.getMassPoint(), e.getClass());
			a.setNextScene(new Wiki_Enemy(a.KH, a.CURRENT_VIEW, en, -1, -1, sb
					.toString(), en.getConvertedLV()));
		} else if (book.getClass() == 電光石火の書.class) {
			pop(SpecialMonsterHouse.天狗の狩場);
		} else if (book.getClass() == 破裂の書.class) {
			Message.set("「", c.getColoredName(), "！ 君に決めた！」");
			MapInSelect.explosion(c.getMassPoint().getLocation());
		} else if (book.getClass() == 切れ端.class) {
			Message.set(getColoredName(), "は楽しそうだ！");
		} else if (book.getClass() == 幻想郷縁起.class) {
			pop(SpecialMonsterHouse.素敵な楽園);
		} else if (book.getClass() == おはらいの書.class) {
			flag_decurse = true;
			MainMap.addEffect(new DecurseEffect(mass_point, null));
			Message.set("「持ってる本の呪いが解けたよ！」");
			Message.set("「呪われていても読めるようになったよ！」");
		} else if (book.getClass() == ダンジョン攻略本.class) {
			if (isBadCondition()) {
				for (CONDITION condition : getBadConditionList()) {
					CONDITION.conditionRecovery(this, condition);
				}
				for (CONDITION con : getConditionRemoveTask()) {
					getConditionList().remove(con);
				}
				this.getConditionRemoveTask().clear();
			}
			this.heal(this, 10);
			boolean b = false;
			for (Base_Creature em : MapList
					.getListAroundCreature(getMassPoint())) {
				em.setCondition(CONDITION.麻痺, 0);
				b = true;
			}
			if (b) {
				Message.set("「ついでに周囲を金縛り！」");
				SE.STATUS_SIBIBI.play();
			}
		} else if (book.getClass() == バクスイの書.class) {
			Message.set("「９ターンの間、わたしの世界が加速！」");
			SE.STATUS_SPEEDY.play();
			this.setCondition(CONDITION.倍速, 9);
		} else if (book.getClass() == パワーアップの書.class) {
			Message.set("「私にとっての味方すべてに闘志がみなぎる！」");
			SE.STATUS_POWER_UP.play();
			for (final Base_Creature base_Creature : room) {
				if (!base_Creature.isPlayerSide()
						&& !base_Creature.equals(Player.me)) {
					SE.STATUS_DODODO.play();
					base_Creature.setCondition(CONDITION.イカリ, 15);
				}
			}

		} else if (book.getClass() == 慧音の歴史書.class) {
			pop(null);
		} else if (book.getClass() == 混乱の書.class) {
			Message.set("「私にとっての敵すべてに混乱を付与！」");
			SE.STATUS_PIYOPIYO.play();
			for (Base_Creature base_Creature : room) {
				if (base_Creature.isPlayerSide()) {
					base_Creature.setCondition(CONDITION.混乱, 0);
				}
			}
			Player.me.setCondition(CONDITION.混乱, 0);
		} else if (book.getClass() == 封書モンスターハウス.class) {
			pop(SpecialMonsterHouse.ちびでかハウス);
		} else if (book.getClass() == 撒き餌の書.class) {
			pop(SpecialMonsterHouse.ワイルドカーペット);
		} else if (book.getClass() == 人を殺せる書.class) {
			pop(SpecialMonsterHouse.殲滅部隊);
		} else if (book.getClass() == 大部屋の書.class) {
			pop(SpecialMonsterHouse.投げ物選手権);
		} else if (book.getClass() == 敵倍速の書.class) {
			pop(SpecialMonsterHouse.ゴーストスポット);
		} else if (book.getClass() == 天邪鬼の書.class) {
			pop(SpecialMonsterHouse.ふらふら祭り);
		} else if (book.getClass() == 八咫烏の書.class) {
			Message.set("「部屋全体に４０の太陽ダメージ！」");
			MainMap.addEffect(new SunEffect(MassCreater.isPlayerInRoom()), true);
			SE.BURN.play();
			for (Base_Creature creature : room) {
				int dam = 40;
				if (creature instanceof チルノ || creature instanceof レティ
						|| creature instanceof レミリア
						|| creature instanceof フランドール) {
					dam = 999;
				}
				dam = creature.damagedWithFire(dam);
				MapInSelect.damage(this, null, creature, dam);
			}
			for (Base_Artifact a : MapList.getListItem()) {
				if (MassCreater.isPlayerInTheSameRoom(a.getMassPoint())) {
					a.freezeCountReset();
				}
			}
			if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.厄)) {
				Message.set("厄神様のご加護のおかげでアイテムが溶かされずに済んだ");
			} else {
				for (Base_Artifact a : Belongings.getListItems()) {
					if (a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
						if (a.isCold()) {
							Message.set("厄神様のご加護で", a.getColoredName(),
									"は溶かされなかった");
						}
					} else {
						a.freezeCountReset();
					}
				}
			}
		} else if (book.getClass() == 氷の書.class) {
			Message.set("「部屋全体に５０の氷ダメージ！」");
			SE.ICE.play();
			MainMap.addEffect(new CirnoIceEffect(), true);
			MassCreater.frozenWater(MassCreater.getPlayerRoom());
			for (Base_Creature creature : room) {
				Damage.damage(this, null, null, this, creature,
						50 * (creature.isIce() ? -1 : 1));
			}
		} else if (book.getClass() == いかづちの書.class) {
			pop(SpecialMonsterHouse.ビリビリルーム);
		} else if (book.getClass() == 明かりの書.class) {
			SE.LIGHT_ON.play();
			Message.set(getColoredName(), "は嬉しそうだ！");
		} else if (book.getClass() == 罠師の書.class) {
			SE.ZAKUZAKU.play();
			Message.set("罠がたくさん生成された");
			TrapTable.createMonsterHouse(MassCreater.getRoom(mass_point), 10);
		}
	}

	@Override
	protected void enemyBreakAction() {
		if (item != null) {
			Base_Artifact a;
			a = ItemTable.itemReturn(getMassPoint(),
					ItemDetail.getCategory(item));
			ItemFall.itemFall(getMassPoint(), a);
		}
	}

	private Scrool getBook() {
		if (isBadCondition()) {
			return new ダンジョン攻略本(mass_point);
		}

		switch (new R().nextInt(36)) {
		case 0:
			return new いかづちの書(mass_point);
		case 1:
			return new おにぎりの書(mass_point);
		case 2:
			return new グリモワール(mass_point);
		case 3:
			return new おはらいの書(mass_point);
		case 4:
			return new ダンジョン攻略本(mass_point);
		case 5:
			return new バクスイの書(mass_point);
		case 6:
			return new パワーアップの書(mass_point);
		case 7:
			return new メッキの書(mass_point);
		case 8:
			return new ワイルドカード(mass_point);
		case 9:
			return new 解凍の書(mass_point);
		case 10:
			return new 慧音の歴史書(mass_point);
		case 11:
			return new 幻想郷縁起(mass_point);
		case 12:
			return new 混乱の書(mass_point);
		case 13:
			return new 撒き餌の書(mass_point);
		case 14:
			return new 自爆の書(mass_point);
		case 15:
			return new 識別の書(mass_point);
		case 16:
			return new 人を殺せる書(mass_point);
		case 17:
			return new 大部屋の書(mass_point);
		case 18:
			return new 敵倍速の書(mass_point);
		case 19:
			return new 天邪鬼の書(mass_point);
		case 20:
			return new 電光石火の書(mass_point);
		case 21:
			return new 破裂の書(mass_point);
		case 22:
			return new 八咫烏の書(mass_point);
		case 23:
			return new 氷の書(mass_point);
		case 24:
			return new 封書モンスターハウス(mass_point);
		case 25:
			return new 明かりの書(mass_point);
		case 26:
			// return new 罠師の書(mass_point);
		default:
			break;
		}
		return new 切れ端(mass_point);
	}

	@Override
	protected String getHello() {
		return "フリーマーケットへようこそ！";
	}

	@Override
	protected String getHelloRoughly() {
		return "いらっしゃい！";
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			int coma = getAnimation().getComa() % 2;
			return IM.getSPImage(LV, coma);
		} else if (conditionCheck(CONDITION.安心)
				|| (flag_buyed && isPlayerSide())) {
			int coma = MainThread.getFrame() / 4 % 2;
			return IM.getSPImage(LV, coma);
		} else {
			return super.getImage();
		}
	}

	@Override
	protected void init4() {
		flag_decurse = false;
	}

	@Override
	public boolean itemHitEffect(Base_Artifact a, boolean ento) {
		if (item != null || !(a instanceof Scrool)) {
			return true;
		}
		setAnimation(new Special_Wait(this, 6, 3));
		SE.CHANGE_ITEM.play();
		item = a;
		Message.set(getColoredName(), "は", a.getColoredName(), "を嬉しそうに受け取った");
		Medal.朱鷺子に書を投げつけた.addCount();
		return false;
	}

	private void pop(SpecialMonsterHouse smh) {
		if (smh != null) {
			Message.set(Color.RED.toString(), smh.toString(),
					Color.WHITE.toString(), "からエネミーが抽選！");
		} else {
			Message.set("消え去った歴史からエネミーが出現！");
		}
		int length = 4 - new R().nextInt(5) / 2;
		int max = 20;
		List<Base_Enemy> list = MapList.getListEnemy();
		if (list.size() >= max) {
			for (int i = 0; i < length; i++) {
				while (true) {
					int index = new R().nextInt(list.size());
					if (list.get(index) != this) {
						MainMap.addEffect(new DoronEffect(list.remove(index)
								.getMassPoint(), null));
						break;
					}
				}
			}
		}
		Room r = MassCreater.getRoom(this.getMassPoint());
		if (r == null)
		{
			Message.set("しかし召喚できるマスがなかった");
		}
		for (int i = 0; i < length; i++) {
			Point p = MassCreater.getMonsterPoint(r);
			if (p == Mass.nullpo.null_point) break;
			Base_Enemy e;
			if (smh == null) {
				if (Player.me.Nedayashi != null) {
					String clazz = Player.me.Nedayashi.getClass()
							.getSimpleName();
					e = EnemyTable.returnBaseEnemy(clazz, 4);
					e.setMassPoint(p);
					MapList.addEnemy(e);
				} else {
					Message.set("しかし歴史から消え去ったエネミーがいなかった");
					return;
				}
			} else {
				e = EnemyTable.setMonsterHouse(p, smh);
			}
			if (e != null) {
				CONDITION.quickRemoveCondition(CONDITION.仮眠, e);
				MainMap.addEffect(new DoronEffect(p, null));
				Message.set(e.getColoredName(), "が現れた！");
			}
		}

	}

	private boolean readBook() {
		final Scrool book;
		if (item != null) {
			if (item instanceof Scrool)
				book = (Scrool) item;
			else
				return false;
			item = null;
		} else {
			book = getBook();
			if (!flag_decurse)
				book.defaultCurse();
		}
		SE.SYSTEM_SCROLL.play();
		Message.set(getColoredName(), "は", book.getColoredName(), "を読んだ");
		if (book.isCurse() && !flag_decurse) {
		} else if (book.getClass() == おにぎりの書.class) {
			Message.set("「ランダムで１キャラに奇跡の効果！」");
		} else if (book.getClass() == グリモワール.class) {
			Message.set("「魔法使いさんいらっしゃい！」");
		} else if (book.getClass() == ワイルドカード.class) {
			Message.set("「私自身を選択！」");
		} else if (book.getClass() == メッキの書.class) {
			Message.set("「助けて神様！」");
		} else if (book.getClass() == 解凍の書.class) {
			Message.set("「体ごと溶かしてあげる！」");
		} else if (book.getClass() == 自爆の書.class) {
			Message.set("「あっ・・・」");
		} else if (book.getClass() == 識別の書.class) {
		} else if (book.getClass() == 電光石火の書.class) {
			Message.set("「風が泣くのは天狗のしわざです！」");
		} else if (book.getClass() == 破裂の書.class) {
			Message.set("「ランダムで１キャラの中心を爆破！」");
		} else if (book.getClass() == 切れ端.class) {
			Message.set(getColoredName(), "は楽しそうに眺めている！");
			setAnimation(new Special_Wait(this, 3, 3));
			return true;
		} else if (book.getClass() == 幻想郷縁起.class) {
			Message.set("「本が読めるって素敵よね」");
		} else if (book.getClass() == おはらいの書.class) {
		} else if (book.getClass() == ダンジョン攻略本.class) {
			if (isBadCondition())
				Message.set("「悪い状態を全回復！」");
			else
				Message.set("「困ったらこれ！」");
		} else if (book.getClass() == バクスイの書.class) {
			Message.set("「わたしのほうそくがみだれる！」");
		} else if (book.getClass() == パワーアップの書.class) {
			Message.set("「ちからこそぱわぁ！」");
		} else if (book.getClass() == 慧音の歴史書.class) {
			Message.set("「この子たちにも出番をあげようよ」");
		} else if (book.getClass() == 混乱の書.class) {
			Message.set("『聞いてパコユカリーナ♪　最近泥棒多すぎーなの♪』");
		} else if (book.getClass() == 封書モンスターハウス.class) {
			Message.set("「何が出るかな？」");
		} else if (book.getClass() == 撒き餌の書.class) {
			Message.set("「帰り道のお菓子は獣に食べられてしまいましたとさ」");
		} else if (book.getClass() == 人を殺せる書.class) {
			Message.set("「真実のみが不滅なのよ　あなたは生き残れるかしら」");
		} else if (book.getClass() == 大部屋の書.class) {
			Message.set("「咲夜さんからナイフを集めよう！」");
		} else if (book.getClass() == 敵倍速の書.class) {
			Message.set("「うらめしや～～」");
		} else if (book.getClass() == 天邪鬼の書.class) {
			Message.set("「計算狂っちゃう？」");
		} else if (book.getClass() == 八咫烏の書.class) {
			Message.set("「アイテム全部溶かしてあげる！」");
		} else if (book.getClass() == 氷の書.class) {
			Message.set("「ヒヤリハットにご用心！」");
		} else if (book.getClass() == いかづちの書.class) {
			Message.set("「シビビ！と行きます！」");
		} else if (book.getClass() == 明かりの書.class) {
			Message.set("「わぁい朱鷺子、明かりの書大好き」");
		} else if (book.getClass() == 罠師の書.class) {
			Message.set("「たくさん罠を作っちゃうよ！」");
		}
		if (!book.isStaticCheked()) {
			book.staticCheck();
		}
		SE.SYSTEM_USING_SPELLCARD.play();
		setAnimation(new CardAnime(new 朱鷺子のカード(mass_point) {
			private static final long serialVersionUID = 1L;

			@Override
			public String getFuName() {
				return book.getName();
			}

			@Override
			public boolean itemUse() {
				effect(book);
				return true;
			}
		}));
		return true;
	}

	@Override
	public void setBombedAway() {
		chengeHP(null, null, -50);
	}

	@Override
	protected boolean specialAttack() {
		if (LV == 4 && MassCreater.isPlayerInTheSameRoom(getMassPoint()))
			return readBook();
		else
			return true;
	}

	@Override
	protected boolean specialCheck() {
		if (LV == 4) {
			counter = false;
			return isSpecialParcent();
		}
		
		return false;
	}

	@Override
	protected void wasshoi() {
		switch (new R().nextInt(4)) {
		case 0:
			say("ゆっくりしていってね！");
			break;
		case 1:
			say("売り物は鑑定しているよ！");
			break;
		case 2:
			say("未鑑定のアイテムは鑑定の手間もあるから高く買い取れないよ");
			break;
		case 3:
			say("静かに本を読んで暮らしたいかな");
			break;
		}
	}
}
