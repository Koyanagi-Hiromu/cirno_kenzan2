package dangeon.latest.scene.action.menu.view.result;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dangeon.controller.DangeonScene;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.adventure.records.AdvRecord;
import dangeon.latest.scene.action.menu.view.Plain;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.WindowFrame;
import dangeon.model.config.Config;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.Base_Map_Random.Difficulty;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.Composition;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.BaseClassJob;
import dangeon.model.object.creature.player.class_job.ClassDefault;
import dangeon.model.object.creature.player.save.ResultSaveLoad;
import dangeon.model.object.creature.player.save.SaveLoad;
import dangeon.view.util.StringFilter;
import main.Listener.ACTION;
import main.Second_Firster;
import main.res.SE;
import main.util.BlackOut;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;

public class Scene_Result_Info extends Plain {
	private boolean flag_item_conservation;

	private boolean flag_jast_watch_record = false;

	private Base_Artifact[] present_enchants = new Base_Artifact[Enchant
			.values().length];
	static ResultSaveLoad result = null;

	private boolean flag_end;

	private Base_Map_Random BMR;

	private AdvRecord prev = null;

	public Scene_Result_Info() {
		this(new ResultSaveLoad(PresentField.get().getRandomMap()));
	}

	public Scene_Result_Info(Base_Map_Random bmr) {
		this(Scene_Action.getMe().KH, Scene_Action.getMe().CURRENT_VIEW,
				result = new ResultSaveLoad(bmr));
		BMR = bmr;
		flag_item_conservation = true;
		flag_jast_watch_record = false;
		TaskOnMapObject.clearCheckArtifact();
		enchant_on();
		setNextScene(this);
	}

	public Scene_Result_Info(KeyHolder kh, Base_View bv,
			ResultSaveLoad resultSaveLoad) {
		super(kh, bv, 0);
	}

	private Scene_Result_Info(ResultSaveLoad rsl) {
		this(Scene_Action.getMe().KH, Scene_Action.getMe().CURRENT_VIEW,
				result = rsl);
		flag_item_conservation = false;
		flag_jast_watch_record = false;
		TaskOnMapObject.clearCheckArtifact();
		enchant_on();
		setNextScene(this);
	}

	public Scene_Result_Info(String field, int i, int difficulty, AdvRecord prev) {
		this(ResultSaveLoad.staticLoad(field, i, difficulty));
		flag_jast_watch_record = true;
		this.prev = prev;
	}

	@Override
	protected void action_cancel() {
		if (flag_jast_watch_record) {
			action_enter();
		}
	}

	@Override
	protected void action_else(ACTION a) {
		if (a == ACTION.TURN) {
			SE.SYSTEM_ENTER.play();
			setNextScene(new ResultRecord(KH, CURRENT_VIEW, this));
		} else {
			super.action_else(a);
		}
	}

	@Override
	protected void action_enter() {
		if (flag_end)
			return;
		if (flag_jast_watch_record) {
			enchant_off();
			setNextScene(getPreviousScene());
		} else {
			end();
		}
	}

	@Override
	public boolean arrow(DIRECTION d) {
		if (!flag_end) {
			SE.SYSTEM_CURSOR.play();
			if (d.X > 0) {
				setNextScene(new Scene_Result_Item(KH, CURRENT_VIEW, this, true));
			} else if (d.X < 0) {
				setNextScene(new Scene_Result_Item(KH, CURRENT_VIEW, this,
						false));
			}
		}
		return END;
	}

	private void blackout() {
		blackout(false);
	}

	private void blackout(boolean fairy) {
		BlackOut.setNoMoveSE();
		final Base_Map bm;
		if (!fairy && BMR != null) {
			bm = BMR.returnTown();
		} else {
			bm = new FairyPlace();
		}
		new BlackOut(bm, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				DangeonScene.DANGEON.setPresentScene();
				MassCreater mc = new MassCreater(bm, false);
				TaskOnMapObject.setNewMap(mc);
				initializePlayer();
				new SaveLoad(mc).saveContinue();
				SaveLoad.delete(true);
				setNextScene(getPreviousScene());
			}
		});
	}

	private void confirm() {
		new ConvEvent("アイテムを持ったまま帰還しますか？") {
			@Override
			protected Book getContent1() {
				return new Book("すぐに再挑戦") {

					@Override
					protected void work() {
						confirm_2();
					}
				};
			}
			
			@Override
			protected Book getContent2() {
				return new Book("いったん帰る") {

					@Override
					protected void work() {
						flag_item_conservation = true;
						blackout();
					}
				};
			}
			
//			@Override
//			protected Book getContent3() {
//				if (!PresentField.get().getRandomMap().isFixedRandom())
//					return null;
//				else
//					return new Book("同じシード値でやりなおす") {
//
//						@Override
//						protected void work() {
//							initializePlayer_Task(true);
//						}
//					};
//			}

			@Override
			protected int pushCancelAction() {
				return -1;
			}

		};
	}
	
	private void confirm_2() {

		new ConvEvent("アイテムが１つも残らないけど大丈夫？") {
			@Override
			protected Book getContent1() {
				return new Book("問題ない！（すぐに再挑戦）") {

					@Override
					protected void work() {
						flag_item_conservation = false;
						initializePlayer_Task(false);
					}
				};
			}
			
			@Override
			protected Book getContent2() {
				return new Book("やっぱり帰る") {

					@Override
					protected void work() {
						flag_item_conservation = true;
						blackout();
					}
				};
			}
			
			@Override
			protected int pushCancelAction() {
				return -1;
			}
		};
	}

	private void confirm_2_old() {
		if (!PresentField.get().getRandomMap().isFixedRandom()) {
			initializePlayer_Task(false);
		} else {
			
			new ConvEvent("アイテムと敵の配置を再現しますか？$") {
				@Override
				protected Book getContent1() {
					return new Book("いいえ（初期配置変更）") {
						@Override
						protected void work() {
							initializePlayer_Task(false);
						}
					};
				}

				@Override
				protected Book getContent2() {
					return new Book("はい（完全再現）") {
						@Override
						protected void work() {
							initializePlayer_Task(true);
						}
					};
				}

				@Override
				protected int pushCancelAction() {
					return -1;
				}
			};
			
//			final int numbers = Config.decRetryNumbers();
//			if (numbers == 0) {
//				confirm_2_0(numbers);
//			} else {
//				confirm_2_1(numbers);
//			}
		}
	}

	private void confirm_2_0(int numbers) {
		new ConvEvent("アイテムと敵の配置を再現しますか？$", getZanki(numbers)) {
			@Override
			protected Book getContent1() {
				return new Book("いいえ（初期配置変更）") {
					@Override
					protected void work() {
						initializePlayer_Task(false);
					}
				};
			}

			@Override
			protected int pushCancelAction() {
				return -1;
			}
		};
	}

	private void confirm_2_1(int numbers) {
		new ConvEvent("アイテムと敵の配置を再現しますか？$", getZanki(numbers)) {
			@Override
			protected Book getContent1() {
				return new Book("はい（完全再現）") {
					@Override
					protected void work() {
						initializePlayer_Task(true);
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("いいえ（初期配置変更）") {
					@Override
					protected void work() {
						initializePlayer_Task(false);
					}
				};
			}

			@Override
			protected int pushCancelAction() {
				return -1;
			}
		};
	}

	/**
	 * ２桁を想定
	 * 
	 * @param i
	 * @return
	 */
	private String convert(int i) {
		String s = 半角全角コンバーター.半角To全角数字(i);
		if (i < 10)
			s = s.concat("　");
		return s;
	}

	private String convert(long l) {
		String s = 半角全角コンバーター.半角To全角数字(l);
		return s;
	}

	private void enchant_off() {
		for (int i = 0; i < Enchant.values().length; i++) {
			Enchant.values()[i].setEnchant(present_enchants[i]);
		}
	}

	private void enchant_on() {
		for (int i = 0; i < Enchant.values().length; i++) {
			present_enchants[i] = Enchant.values()[i].getEnchant();
			Enchant.values()[i].setEnchant(result.ENCHANT_ARTIFACT[i]);
		}
	}

	private void end() {
		flag_end = true;
		if (Player.flag_daichan_return) {
			Config.addTimes();
			Player.flag_daichan_return = false;
			blackout(true);
		} else if (!Player.flag_clear) {
			Config.addTimes();
			Base_Map map = PresentField.get();
			if (map.getRandomMap() != null) {
				confirm();
			} else {
				blackout();
			}
		} else {
			blackout();
			return;
		}
	}

	@Override
	protected void getContents(ArrayList<String> list) {
		getLine1(list, new StringBuilder());
		getLine2(list, new StringBuilder());
		getLine3_4(list, new StringBuilder());
		getLine5(list, new StringBuilder());
		getLine6(list, new StringBuilder());
		getLine7(list, new StringBuilder());
		getLine8_13(list, new StringBuilder());
		getLine14_15(list, new StringBuilder());
	}

	private void getLine1(ArrayList<String> list, StringBuilder sb) {
		sb.append(Color.WHITE);
		sb.append("Lv");
		sb.append(convert(result.PLAYER.getLV()));
//		sb.append("　残機");
		sb.append(StringFilter.NUMBERS);
		for (int i = 0; i < result.LEFT_LIFE; i++) {
			sb.append("★");
		}
		sb.append(Color.WHITE);
		sb.append(WindowFrame.CENTERIZE);
		long t = result.PLAYER.getPlayingMilliTime();
		t /= 1000;
		if (t > 3600) {
			sb.append(convert(t / 3600));
			sb.append("時間");
			t -= t / 3600 * 3600;
		}
		if (t > 60) {
			sb.append(convert(t / 60));
			sb.append("分");
			t -= t / 60 * 60;
		}
		sb.append(convert(t));
		sb.append("秒");
		list.add(sb.toString());
	}

	private void getLine12_13(ArrayList<String> list, StringBuilder sb,
			Base_Artifact a, int i) {
		if (a != null) {
			list.add(a.getColoredName());
		} else {
			sb.append(Enchant.getANYS()[i].getColor());
			sb.append("何でも");
			sb.append(半角全角コンバーター.半角To全角数字(i + 1));
			sb.append("：");
			if (Boolean.TRUE.equals(result.PLAYER.hash_restriction.get(Enchant
					.get(i)))) {
				sb.append("未使用");
			} else {
				sb.append("なし");
			}
			list.add(sb.toString());
		}
	}

	private void getLine14_15(ArrayList<String> list,
			StringBuilder stringBuilder) {
		list.add("");
		String key = KeyEvent.getKeyText(ACTION.TURN.KEY_NUM);
		list.add(WindowFrame.TAIL + "→：所持アイテム　　" + key + "：メッセージログ");
	}

	private void getLine2(ArrayList<String> list, StringBuilder sb) {
		if (result.FIELD_NAME != null) {
			sb.append(result.FIELD_NAME);
			sb.append("-");
			sb.append(Difficulty.getFromIndex(result.DIFFICULTY));
			sb.append("-");
			sb.append("　");
			sb.append(半角全角コンバーター.半角To全角数字(result.FLOOR));
			sb.append("Ｆ");
			// sb.append(result.FIELD_NAME.concat("　")
			// .concat(半角全角コンバーター.半角To全角数字(result.FLOOR)).concat("Ｆ"));
		}
		BaseClassJob job = result.PLAYER.getClassJob();
		if (job != null && !job.getClass().equals(ClassDefault.class)) {
			sb.append(WindowFrame.TAIL);
			sb.append("【");
			sb.append(result.PLAYER.getClassJob().className());
			sb.append("】");
		}
		list.add(sb.toString());
	}

	private void getLine3_4(ArrayList<String> list, StringBuilder sb) {
		String s = StringFilter.getPlainString(result.PLAYER.cause_of_death);
		if (s == null || s.isEmpty())
			s = "原因不明の理由で倒れた";
		Graphics2D g = (Graphics2D) Second_Firster.ME.getGraphics();
		FontMetrics fm = g.getFontMetrics();
		int w = 325;
		if (fm.stringWidth(s) < w) {
			list.add(s);
			list.add("");
		} else {
			String s1, s2;
			for (int j = 0; true; j++) {
				if (fm.stringWidth((String) s.subSequence(0, s.length() - j)) < w) {
					j++;
					s1 = (String) s.subSequence(0, s.length() - j);
					s2 = (String) s.subSequence(s.length() - j, s.length());
					break;
				}
			}
			list.add(s1);
			list.add(s2);
		}
	}

	private void getLine5(ArrayList<String> list, StringBuilder sb) {
		sb.append("こうげき");
		sb.append(convert(result.ATK));
		sb.append(WindowFrame.CENTERIZE);
		sb.append("最大ＨＰ");
		sb.append(convert(result.PLAYER.getMAX_HP()));
		list.add(sb.toString());
	}

	private void getLine6(ArrayList<String> list, StringBuilder sb) {
		sb.append("ぼうぎょ");
		sb.append(convert(result.DEF));
		sb.append(WindowFrame.CENTERIZE);
		sb.append("最大満腹度");
		sb.append(convert(result.PLAYER.getMAX_SATIETY()));
		list.add(sb.toString());
	}

	private void getLine7(ArrayList<String> list, StringBuilder sb) {
		sb.append("ちから");
		int str = result.PLAYER.getSTR();
		sb.append(半角全角コンバーター.半角To全角数字(str));
		sb.append("/");
		sb.append(convert(result.PLAYER.getMAX_STR()));
		if (str < 10)
			sb.append("　");
		sb.append(WindowFrame.CENTERIZE);
		sb.append("経験値");
		sb.append(convert(result.PLAYER.getPlayerExp()));
		list.add(sb.toString());
	}

	private void getLine8_11(ArrayList<String> list, StringBuilder sb,
			SpellCard sc, int i) {
		sb.append(Enchant.get(i).getColor());
		if (sc != null) {
			sb.append(sc.getColoredName());
			list.add(sb.toString());
			list.add(Composition.composition(sc));
		} else {
			if (i == 0)
				sb.append("攻撃装備：");
			else
				sb.append("防御装備：");
			if (Boolean.TRUE.equals(result.PLAYER.hash_restriction.get(Enchant
					.get(i)))) {
				sb.append("未使用");
			} else {
				sb.append("なし");
			}
			list.add(sb.toString());
			list.add("");
		}
	}

	private void getLine8_13(ArrayList<String> list, StringBuilder sb) {
		Base_Artifact[] as = result.ENCHANT_ARTIFACT;
		getLine8_11(list, new StringBuilder(), (SpellCard) as[0], 0);
		getLine8_11(list, new StringBuilder(), (SpellCard) as[1], 1);
		getLine12_13(list, new StringBuilder(), as[2], 0);
		getLine12_13(list, new StringBuilder(), as[3], 1);
	}

	@Override
	public Base_Scene getPreviousScene() {
		if (prev == null)
			return super.getPreviousScene();
		else {
			SE.SYSTEM_ENTER.play();
			return prev;
		}
	}

	public static String getZanki(int numbers) {
		StringBuilder sb = new StringBuilder();
		sb.append(Color.CYAN.toString());
		sb.append("残機：");
		sb.append(StringFilter.NUMBERS);
		for (int i = 0; i < numbers; i++) {
			sb.append("★");
		}
		return sb.toString();
	}

	protected void initializePlayer() {
		if (flag_item_conservation) {
			Player.me.resetAll_exceptItems();
			Player.flag_clear = false;
		} else {
			Player.me.resetAll();
		}
		result = null;
	}

	protected void initializePlayer_Task(final boolean b) {
		final boolean flag = flag_item_conservation;
		if (flag) {
			Player.flag_clear = false;
		} else {
			Belongings.getListItems().clear();
		}
		Player.me.resetAll_exceptItems();
		// StairScene.addTask(new Task() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void work() {
		// Player.me.resetAll_exceptItems();
		// }
		// });
		result = null;
		PresentField.get().getRandomMap().createFirstMap(b);
	}
}