package dangeon.model.map.field.town.map;

import java.awt.Point;
import java.util.AbstractList;
import java.util.List;

import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.special.map.SukimaMap;
import dangeon.model.map.field.town.Base_TownMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.HiddenDevice;
import dangeon.model.object.artifact.device.HiddenMovePoint;
import dangeon.model.object.artifact.device.MeteorIncidentNPC;
import dangeon.model.object.artifact.item.spellcard.Exルーミアのカード;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Abstract_NPC;
import dangeon.model.object.creature.npc.ItemNpc;
import dangeon.model.object.creature.npc.敵確認NPC;
import dangeon.model.object.creature.npc.dungeonNpc.NPC二撃;
import dangeon.model.object.creature.npc.dungeonNpc.NPC五色の神霊廟;
import dangeon.model.object.creature.npc.dungeonNpc.NPC救出大作戦;
import dangeon.model.object.creature.npc.dungeonNpc.NPC賢将裏の洞窟;
import dangeon.model.object.creature.npc.dungeonNpc.NPC逆ヶ島;
import dangeon.model.object.creature.npc.dungeonNpc.NPC風穴旅行;
import dangeon.model.object.creature.npc.dungeonNpc.チルノの旅NPC;
import dangeon.model.object.creature.npc.second.MUSIC_NPC;
import dangeon.model.object.creature.npc.second.NPCおりん;
import dangeon.model.object.creature.npc.second.NPCパチェ;
import dangeon.model.object.creature.npc.second.SETSUNA_NPC;
import dangeon.model.object.creature.npc.second.救済箱;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.save.SaveLoad;
import dangeon.util.R;
import dangeon.util.Switch;
import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.Image_MapTip;
import main.res.SE;
import main.util.BlackOut;
import main.util.DIRECTION;

public class FairyPlace extends Base_TownMap {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private boolean flag_lumia = false;

	public FairyPlace() {
		super("firstMap");
	}

	private void add_紫(int x, int y) {
		final Abstract_NPC c = new Abstract_NPC(CHARA_IMAGE.八雲紫, "八雲紫", x, y,
				false, false, "あらあら@", "よく私に話しかけられたわね@",
				"それなら特殊合成のヒントを教えてあげるわ@", "「ひよこになるには変化する赤い謎と橙色の狸が１つずつ必要」よ@");
		add(c);
		add(new HiddenDevice(new Point(x, y + 1)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean walkOnAction() {
				if (!MapList.getListCreature().contains(c)) {
					return false;
				}
				Player.me.setDirection(DIRECTION.UP);
				new Conversation(Image_LargeCharacter.八雲紫, "あら？　倉庫へ行きたいの？",
						new ConvEvent() {
							@Override
							protected Book getNo() {
								return new Book() {

									@Override
									protected void work() {
										Message.set("そう、いつでもいらっしゃい");
									}
								};
							}

							@Override
							protected Book getYes() {
								return new Book() {

									@Override
									protected void work() {
										SE.YUKARI_SPELL.play();
										new BlackOut("", new Task() {
											/**
									 *
									 */
											private static final long serialVersionUID = 1L;

											@Override
											public void work() {
												TaskOnMapObject
														.setNewMap(new MassCreater(
																new SukimaMap(),
																false));
											}
										});
									}
								};
							}
						});
				return false;
			}
		});
	}

	@Override
	public BGM getBGM() {
		if (flag_lumia)
			return null;
		return BGM.cilno_kenzan;
	}

	@Override
	public String getClassName() {
		return "妖精の踊り場";
	}

	@Override
	public Point getEntrancePoint() {
		return new Point(24, 9);
	}

	@Override
	public Image_MapTip getMapTip() {
		return Image_MapTip.草原_秋;
	}

	@Override
	protected AbstractList<Base_MapObject> getObjectList() {
		Player.me.direction = DIRECTION.DOWN;
		Player.me.flag_no_item_daichan = false;
		kanako(new Point(20, 7));
		if (Switch.test) {
			add(new NPC二撃(new Point(20, 6)));
			add(new NPCパチェ(new Point(19, 7)));
			add(new チルノの旅NPC(new Point(21, 6)));
			add(new NPC逆ヶ島(new Point(22, 6)));
			// add(new 熱解凍NPC(new Point(17, 9)));
			add(new 敵確認NPC(new Point(25, 9)));
			// add(new デバッグ専用NPC(new Point(22, 9)));
			add(new ItemNpc(new Point(23, 9), 1));
			add(new HiddenMovePoint(23, 9, new UnderGroundHouse(),
					DIRECTION.RIGHT));
			add(new MUSIC_NPC(new Point(22, 9)));
			// Base_Enemy lumia = new Abstract_NPC(CHARA_IMAGE.ルーミア,
			// "Event_Checker", 16, 4, false) {
			// private static final long serialVersionUID = 1L;
			//
			// @Override
			// public void message() {
			// // new Event_Scene1();
			// new Event_Scene7(new 救出大作戦(), null);
			// }

		}
		;
		// Base_Enemy hisou = new Abstract_NPC(CHARA_IMAGE.arrow,
		// "Boss_Creater", 18, 4, false) {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void message() {
		// Point p = mass_point.getLocation();
		// p.translate(1, 1);
		// MapList.addEnemy(new ヒソウテンソク(p, LV));
		// }
		//
		// };
		// add(hisou);
		// } else if (Config.isPassWardTrue("miracle")) {
		// add(new NPC神奈子(new Point(20, 7)));
		// }
		// add(new レミリアNPC(new Point(24, 6)));
		// return test();
		if (!StoryManager.運命のワルツEvent_1.hasFinished()) {
			final Base_Enemy lumia;
			boolean flag = false;
			for (Base_Artifact a : Belongings.getListItems()) {
				if (a instanceof Exルーミアのカード) {
					flag = true;
					break;
				}
			}
			if (flag) {
				lumia = new Abstract_NPC(CHARA_IMAGE.ルーミア, "ルーミア", 16, 4, false) {
					private static final long serialVersionUID = 1L;

					@Override
					public void message() {
						BGM.waitUntilFadeOut_Thread();
						setTalks(Image_LargeCharacter.ANY);
						talks("・・・");
						talks("・・・");
						talks("剣で・・・");
						ConvEvent cne = new ConvEvent() {
							@Override
							public void workAfterPush() {
								new BlackOut(new Task() {
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
										setTalks(Image_LargeCharacter.Exルーミア);
										talks("・・・封印された運命を切り裂け");
										ConvEvent cne = new ConvEvent() {
											@Override
											public void workAfterPush() {
												BGM.Dingy_rip.play();
											}
										};
										talks("・・・闇の先で");
										new Conversation(
												Conversation.previous_opponent,
												cne, "・・・待っている");
									}
								}, new Task() {
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
										StoryManager.運命のワルツEvent_1
												.saveThisFinished();
										flag_lumia = true;
										TaskOnMapObject.reCreateNewMap();
									}
								});
							}
						};
						new Conversation(Image_LargeCharacter.ANY, cne,
								"封印された・・・");
					}
				};
			} else if (StoryManager.ミラクルクエスト挑戦ok.hasFinished()) {
				lumia = new Abstract_NPC(CHARA_IMAGE.ルーミア, "ルーミア", 16, 4,
						false, "もしエクストラなカードが手に入ったら見せてくれないかー？@", "⑨おー？@", "⑨エネミー図鑑にヒントが書いてあるらしいぞー");
			} else {
				lumia = new Abstract_NPC(CHARA_IMAGE.ルーミア, "ルーミア", 16, 4,
						false, "おーチルノかー？@", "封印状態になるとどんな状態異常も解除されるって知ってたかー？@",
						"⑨そーなのかー");

			}
			lumia.setCondition(CONDITION.やりすごし, 0);
			lumia.setDirection(DIRECTION.DOWN);
			add(lumia);

		}
		add(new HiddenMovePoint(17, 3, new KoumaKan(), DIRECTION.UP)
				.setMovePoint(new Point(17, 1)));
		if (StoryManager.逆ヶ島挑戦ok.hasFinished()) {
			add(new NPC五色の神霊廟(new Point(10, 6)).setDirection(DIRECTION.RIGHT));
		} else {
			add(new Abstract_NPC(CHARA_IMAGE.豊聡耳神子, "豊聡耳神子", 10, 6, false,
					"あなたは大変自分に素直ですね@", "⑨最強だから当たり前じゃない！")
					.setDirection(DIRECTION.RIGHT));
		}
		add(new Abstract_NPC(CHARA_IMAGE.物部布都, "物部布都", 10, 5, false, false,
				"お主が長居するとフロアが冷えて水路が凍ってしまうぞ@", "水路にアイテムを落としたら試してみるのもよいかもな？@",
				"⑨はーい").setDirection(DIRECTION.DOWN));
		add(new Abstract_NPC(CHARA_IMAGE.蘇我屠自古, "蘇我屠自古", 11, 5, false, true,
				"電気はキャラや水場に伝播するしダメージが大きいが…@", "アンタが感電してもHPが１は残るみたいだね@",
				"⑨氷は電気に強いってことね？", "⑨案外そうかもしれないな")
				.setDirection(DIRECTION.DOWN_LEFT));
		add(new NPC救出大作戦(new Point(29, 4)));
		if (StoryManager.逆ヶ島挑戦ok.hasFinished()) {
			add(new NPC逆ヶ島(new Point(28, 4)));
		}
		add_紫(21, 3);
		if (StoryManager.逆ヶ島clear.hasFinished()) {
			add(new チルノの旅NPC(new Point(16, 11)).setDirection(DIRECTION.LEFT));
			add(new MUSIC_NPC(new Point(16, 12)).setDirection(DIRECTION.LEFT));
		} else {
			add(new Abstract_NPC(CHARA_IMAGE.比那名居天子, "比那名居天子", 16, 11, false,
					"今日も絶好調だわ！@").setDirection(DIRECTION.LEFT));
			add(new Abstract_NPC(CHARA_IMAGE.永江衣玖, "永江衣玖", 16, 12, false,
					"しばらく大きな地震の予定はないですよ@").setDirection(DIRECTION.LEFT));
		}
		add(new Abstract_NPC(CHARA_IMAGE.二ッ岩マミゾウ, "二ッ岩マミゾウ", 18, 14, false,
				"回収機能は使っておるか？@", "ダッシュで乗ったりしたアイテムをフロア中から集めることができる@",
				"⑨使い道も教えてよ", "⑨それは自分で探すことじゃな", "⑨ちぇー")
				.setDirection(DIRECTION.DOWN));
		if (StoryManager.おりん車.hasFinished())
			add(new NPCおりん(24, 14));
		add(new Abstract_NPC(CHARA_IMAGE.封獣ぬえ, "封獣ぬえ", 19, 14, false,
				"カードは使用してるかい？@", "装備カードも使用できるのを忘れないようにね@") {
			private static final long serialVersionUID = 1L;

			@Override
			public int getFootDeltY() {
				return +1;
			}
		}.setDirection(DIRECTION.DOWN));
		if (StoryManager.二撃必殺挑戦ok.hasFinished())
			add(new NPC風穴旅行(new Point(22, 9)));
		add(new MeteorIncidentNPC(new Point(22, 9)));
		add(new SETSUNA_NPC(new Point(28, 11), DIRECTION.LEFT));
		add(new Abstract_NPC(CHARA_IMAGE.西行寺幽々子, "西行寺幽々子", 27, 11, false,
				"幽霊には回復がダメージになるわ～@", "理不尽よね～@").setDirection(DIRECTION.RIGHT));

		if (StoryManager.大妖精救出clear.hasFinished()) {
			add(new Abstract_NPC(CHARA_IMAGE.霍青娥, "霍青娥", 30, 8, false, true,
					"書は壁の中にいる敵にも効果があるけど@", "アイテムを投げつけても当たるから覚えておくといいわ@")
					.setDirection(DIRECTION.LEFT));
			add(new Abstract_NPC(CHARA_IMAGE.宮古芳香, "宮古芳香", 30, 9, false,
					"おまえ知ってるか～？@", "私のカードは隠し能力として～@", "おにぎりをおいしく食べる能力があるんだぞ～@")
					.setDirection(DIRECTION.LEFT));
		}
		if (StoryManager.おりん車.hasFinished()) {
			add(new NPC賢将裏の洞窟(new Point(30, 12)).setDirection(DIRECTION.LEFT));
		} else {
			add(new Abstract_NPC(CHARA_IMAGE.ナズーリン, "ナズーリン", 30, 12, false,
					"やあ妖精、君はいつも楽しそうだね@", "⑨まあね！").setDirection(DIRECTION.LEFT));
		}

		String[] arr = { "ゆっ？", "ゆっ！", "ゆ？", "ゆっゆっ", "ゆ～？", "ゆっゆっ！", "ゆ！",
				"ゆ！？", "ゆ～！", "ゆ？", "ゆっくりしていってねっ" };
		add(new Abstract_NPC(CHARA_IMAGE.ゆっくり霊夢, "ゆっくり", 30, 14, true,
				arr[new R().nextInt(arr.length)].concat(""))
				.setDirection(DIRECTION.LEFT));
		setSaisenBox(22, 4);
		if (StoryManager.逆ヶ島clear.hasFinished()) {
			add(new Abstract_NPC(CHARA_IMAGE.博麗霊夢, "博麗霊夢", 23, 4, false,
					"平和でいいわね"));
			add(new NPC二撃(new Point(24, 4)));
			add(new Abstract_NPC(CHARA_IMAGE.伊吹萃香, "伊吹萃香", 25, 4, false,
					"ちから試しするかい？@", "⑨また今度ね！").setDirection(DIRECTION.DOWN));
		} else if (StoryManager.大妖精救出clear.hasFinished()) {
			add(new Abstract_NPC(CHARA_IMAGE.博麗霊夢, "博麗霊夢", 23, 4, false,
					"平和でいいわね"));
			add(new Abstract_NPC(CHARA_IMAGE.星熊勇儀, "星熊勇儀", 24, 4, false,
					"なんとかなりそうかい？").setDirection(DIRECTION.DOWN));
			add(new Abstract_NPC(CHARA_IMAGE.伊吹萃香, "伊吹萃香", 25, 4, false,
					"お酒でも飲まないかい？@", "⑨また今度ね！").setDirection(DIRECTION.DOWN));
		} else {
			add(new Abstract_NPC(CHARA_IMAGE.博麗霊夢, "博麗霊夢", 23, 4, false,
					"大妖精がさらわれたって？@", "まぁもし異変まで発展したら解決しに向かうわ@", "⑨お茶でも飲んで待っててよ"));
			add(new Abstract_NPC(CHARA_IMAGE.星熊勇儀, "星熊勇儀", 24, 4, false,
					"鬼の端くれがまた何かやらかしたようだね@", "なんなら私も何か手伝おうかい？@",
					"⑨あたいひとりでなんとかするわ！@", "さらわれたのはあたいの友達だからね", "⑨あっはっは　良い答えだ")
					.setDirection(DIRECTION.DOWN));
			add(new Abstract_NPC(CHARA_IMAGE.伊吹萃香, "伊吹萃香", 25, 4, false,
					"お酒でも飲まないかい？@", "⑨また今度ね！").setDirection(DIRECTION.DOWN));
		}
		return super.getObjectList();
	}

	@Override
	public boolean isLightful() {
		return !flag_lumia;
	}

	private void kanako(Point point) {
		if (true) {

			add(new Abstract_NPC(CHARA_IMAGE.八坂神奈子, "八坂神奈子", point.x, point.y,
					false, "最近いささか信仰の衰えを感じるなぁ"));
			add(new Abstract_NPC(CHARA_IMAGE.洩矢諏訪子, "洩矢諏訪子", point.x + 1,
					point.y, false, "妖精はおにぎりを食べるのみにあらず・・・", "とりあえず元気が一番だね！"));
		}
		// else
		// if (Config.isExperienceMode() || StoryManager.シェアok.hasFinished()) {
		// add(new NPC神奈子(point));
		// add(new Abstract_NPC(CHARA_IMAGE.洩矢諏訪子, "洩矢諏訪子", point.x + 1,
		// point.y, false, "妖精はおにぎりを食べるのみにあらず・・・", "とりあえず元気が一番だね！"));
		// } else if (StoryManager.逆ヶ島clear.hasFinished()) {
		// add(new Abstract_NPC(CHARA_IMAGE.八坂神奈子, "八坂神奈子", point.x, point.y,
		// false) {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void message() {
		// say("おお、お前はチルノじゃないか");
		// say("活躍ぶりが実に素晴らしいじゃないか！");
		// say("ところでちょっと聞いてくれないか？");
		// say("実は最近早苗とうまく交信できなくてだな・・・");
		// say("原因を探っていくうちに、パラレル？わーるど？とやらに繋がってしまったんだ");
		// say("八雲が出てくるぐらい大事になっているんだが");
		// ConvEvent cne = new ConvEvent() {
		// @Override
		// public void workAfterPush() {
		// new BlackOut(new Task() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void work() {
		// Player.flag_clear = true;
		// SE.FANFARE2.play();
		// setTalks(Image_LargeCharacter.ANY);
		// talks("シェアアイテムができるようになった！");
		// talks("友達と一緒にダンジョンを潜れるぞ！");
		// talks(true, "動作は不安定だから注意！バックアップおすすめ");
		// }
		// }, new Task() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void work() {
		// StoryManager.シェアok.saveThisFinished();
		// TaskOnMapObject.reCreateNewMap();
		// }
		// });
		// }
		// };
		// new Conversation(IMLC, cne, "ちょっと調査に協力してくれやしないかい？");
		// }
		// });
		// add(new Abstract_NPC(CHARA_IMAGE.洩矢諏訪子, "洩矢諏訪子", point.x + 1,
		// point.y, false, "信仰は神のためにあらずってね", "妖精には難しいかな？"));
		// }
	}

	private void setSaisenBox(int x, int y) {
		List<Base_Artifact> list = SaveLoad.staticLoad_SaisenBox();
		if (list == null || list.isEmpty()) {
			return;
		} else {
			add(new 救済箱(new Point(x, y), list));
		}
	}

}
