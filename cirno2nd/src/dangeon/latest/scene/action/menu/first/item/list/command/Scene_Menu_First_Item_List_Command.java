package dangeon.latest.scene.action.menu.first.item.list.command;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.BGM;
import main.res.SE;
import main.util.半角全角コンバーター;
import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List;
import dangeon.latest.scene.action.menu.first.item.list.command.enchant.Scene_Menu_First_Item_List_Command_Enchant;
import dangeon.latest.scene.action.menu.first.item.list.command.name.Scene_Menu_First_Item_List_Command_Name;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.Base_Artifact.STATUS;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.disc.Base_Disc;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印邪;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.EnchantArrow;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;
import dangeon.view.anime.DecurseEffect;
import dangeon.view.util.StringFilter;

public class Scene_Menu_First_Item_List_Command extends Base_Scene_Menu {
	private class CurseBook extends Book {
		private final String MSG;
		private final Base_Artifact A1, A2;

		private CurseBook() {
			this(SELECTED_ARTIFACT, null);
		}

		private CurseBook(Base_Artifact a1, Base_Artifact a2) {
			this("は呪われていて外せない！", a1, a2);
		}

		private CurseBook(String s) {
			this(s, SELECTED_ARTIFACT, null);
		}

		private CurseBook(String s, Base_Artifact a1, Base_Artifact a2) {
			MSG = s;
			if (a1 == null && a2 != null) {
				A1 = a2;
				A2 = null;
			} else {
				A1 = a1;
				A2 = a2;
			}
		}

		@Override
		protected final void work() {
			SE.SYSTEM_CURSE.play();
			String s = A1.getColoredName();
			if (A2 != null)
				s = s.concat("と").concat(A2.getColoredName());
			Message.set(s, MSG);
			setNextScene(Scene_Action.getMe());
		}
	}

	private class MsgBook extends Book {
		private final String MSG;

		private MsgBook(String msg) {
			if (SELECTED_ARTIFACT != null) {
				String name = SELECTED_ARTIFACT.getColoredName();
				if (msg.matches("固定")) {
					MSG = name.concat("は固定されていて拾えない");
				} else
					MSG = msg;
			} else {
				MSG = msg;
			}
		}

		@Override
		protected final void work() {
			Message.set(MSG);
			setNextScene(Scene_Action.getMe());
		}
	}

	private abstract class MyBook extends Book {
		public final boolean FLAG_TURN_PASS;

		public MyBook() {
			this(true);
		}

		public MyBook(boolean turn_pass) {
			FLAG_TURN_PASS = turn_pass;
		}

		protected abstract void contents_work();

		@Override
		protected final void work() {
			contents_work();
			setNextScene(Scene_Action.getMe());
			if (FLAG_TURN_PASS)
				TurnSystemController.callMeToStartEnemyTurn();
		}

	}

	private final boolean FLAG_FOOT;

	public final Base_Artifact SELECTED_ARTIFACT;

	private final String CL_ATK = Enchant.ATK.getColor().toString(),
			CL_DEF = Enchant.DEF.getColor().toString(), CL_ANY1 = Enchant.ANY1
					.getColor().toString(), CL_ANY2 = Enchant.ANY2.getColor()
					.toString(), CL_ANY3 = Enchant.ANY3.getColor().toString(),
			CL_NUM = StringFilter.NUMBERS.toString(), CL_N = Color.WHITE
					.toString();

	public Scene_Menu_First_Item_List_Command(KeyHolder kh, Base_View bv,
			Base_Artifact a, boolean flag_foot) {
		super(3, 2, kh, new Scene_Menu_First_Item_List_Command_View(bv));
		SELECTED_ARTIFACT = a;
		FLAG_FOOT = flag_foot;
		initializeContents(new Base_Artifact[0]);
		initialX_Y();
	}

	public Scene_Menu_First_Item_List_Command(KeyHolder kh, Base_View bv,
			Base_Artifact[] as) {
		super(3, 1, kh, new Scene_Menu_First_Item_List_Command_View(bv));
		SELECTED_ARTIFACT = null;
		FLAG_FOOT = false;
		initializeContents(as);
		initialX_Y();
	}

	@Override
	protected void action_else() {
	}

	private void contentsBreak() {
		if (!SELECTED_ARTIFACT.isMobile())
			setDeprecatedContents("破棄", getExp_Breaking(), new MsgBook(
					SELECTED_ARTIFACT.getColoredName() + "は固定されていて破棄できない"));
		else
			setContents("破棄", getExp_Breaking(), new MyBook(false) {
				@Override
				protected void contents_work() {
					Belongings.breaking(SELECTED_ARTIFACT);
				}
			});
	}

	private void contentsBreakInBulk(final Base_Artifact[] as) {
		setContents("すべて破棄", getExp_BreakingInBulk(), new MyBook(false) {
			@Override
			protected void contents_work() {
				boolean atk = false, def = false;
				for (Base_Artifact a : as) {
					if (Enchant.ATK.isEquals(a))
						atk = true;
					if (Enchant.DEF.isEquals(a))
						def = true;
				}
				if (atk && def) {
					Medal.攻撃カードと防御カードをまとめて破棄した.addCount();
				}
				Belongings.breaking(as);
			}
		});
	}

	private void contentsEnchant() {
		if (SELECTED_ARTIFACT instanceof Arrow) {
			setContents("装備", getExp_Enchant(), new MyBook(false) {
				@Override
				protected void contents_work() {
					EnchantArrow.setArrow((Arrow) SELECTED_ARTIFACT);
				}
			});
		} else if (SELECTED_ARTIFACT.isEnchantedAndCursed()) {
			setDeprecatedContents("装備", getExp_Enchant(), new CurseBook());
		} else {
			setContents("装備", getExp_Enchant(), new Book() {
				@Override
				protected void work() {
					setNextScene(new Scene_Menu_First_Item_List_Command_Enchant(
							KH, CURRENT_VIEW, SELECTED_ARTIFACT));
				}
			});
		}
	}

	private void contentsExchange() {
		Base_Artifact a = Belongings.get(-1);
		if (a == null) {
			setDeprecatedContents("交換", getExp_Exchange(), new MsgBook(
					"足元に拾えるものは何もない"));
		} else if (!a.isMobile()) {
			setDeprecatedContents("交換", getExp_Exchange(), new MsgBook("固定"));
		} else if (SELECTED_ARTIFACT.isEnchantedAndCursed()) {
			setDeprecatedContents("交換", getExp_Exchange(), new CurseBook());
		} else {
			setContents("交換", getExp_Exchange(), new MyBook() {
				@Override
				protected void contents_work() {
					Base_Artifact.exchange(Belongings.get(-1), Belongings
							.getListItems().indexOf(SELECTED_ARTIFACT));
					setNextScene(Scene_Action.getMe());
				}
			});
		}
	}

	private void contentsExplain() {

		if (SELECTED_ARTIFACT.isCurse_And_isItViewed() && 印邪.isDeCurse()) {
			setContents("解呪", new String[] { "このアイテムを解呪します", "ターンの経過はありません" },
					new MyBook(false) {
						@Override
						protected void contents_work() {
							Message.set(SELECTED_ARTIFACT.getColoredName(),
									"の呪いを解いた");
							Player.me.setAnimation(new DecurseEffect());
							SELECTED_ARTIFACT.setCurse(false);
						}
					});

		} else if (SELECTED_ARTIFACT instanceof SpellCard
				&& SELECTED_ARTIFACT.isStaticCheked()) {
			final SpellCard s = (SpellCard) SELECTED_ARTIFACT;
			setContents("音楽", new String[] { "このカードに込められたBGMを再生します" },
					new MyBook(false) {
						@Override
						protected void contents_work() {
							BGM.play(s);
						}
					});
		} else if (!SELECTED_ARTIFACT.isStaticCheked()) {
			setContents("名前", new String[] { "この未識別アイテムに名前をつけます" }, new Book() {
				@Override
				protected void work() {
					setNextScene(new Scene_Menu_First_Item_List_Command_Name(
							KH, CURRENT_VIEW, SELECTED_ARTIFACT));
				}
			});
		} else if (SELECTED_ARTIFACT instanceof Base_Pot) {
			String[] arr = ((Base_Pot) SELECTED_ARTIFACT).getSuperExplain();
			setDeprecatedContents("説明", arr, new Book("") {

				@Override
				protected void work() {

				}

			});
		} else
			setDeprecatedPerfetedlyContents();
	}

	private void contentsFootExchange() {
		if (!SELECTED_ARTIFACT.isMobile()) {
			setDeprecatedContents("交換", getExp_FootExchange(),
					new MsgBook("固定"));
		} else if (Belongings.isEmpty()) {
			setDeprecatedContents("交換", getExp_FootExchange(), new MsgBook(
					"持ち物が１つもない"));
		} else {
			setContents("交換", getExp_FootExchange(), new Book() {
				@Override
				protected void work() {
					// TODO　ページ数の表示　印の表示

					ArrayList<Base_Artifact> escapse_list = Belongings
							.getDeepCopy(Belongings.getListItems());
					for (Iterator<Base_Artifact> iterator = escapse_list
							.iterator(); iterator.hasNext();) {
						Base_Artifact a = iterator.next();
						if (!a.isEnchantedAndCursed()) {
							iterator.remove();
						}
					}
					setNextScene(new Item_List(KH, CURRENT_VIEW, "どれと交換？",
							new Book_Item() {
								@Override
								public void work(Base_Artifact a) {
									if (a.isEnchantedAndCursed()) {
										SE.SYSTEM_CURSE.play();
										Message.set(a.getColoredName(),
												"は呪われていて外せない");
									} else {
										Base_Artifact.exchange(
												SELECTED_ARTIFACT, a);
										TurnSystemController
												.callMeToStartEnemyTurn();
									}
								}
							}));
				}
			});
		}
	}

	private void contentsPickUp() {
		Boolean arrow_pick_up = null;
		if (Belongings.isMax()) {
			if (SELECTED_ARTIFACT instanceof Arrow) {
				Arrow arr = Belongings.getArrow(SELECTED_ARTIFACT.getClass());
				if (arr != null) {
					if (arr.getArrowRest() >= 99)
						arrow_pick_up = false;
				} else {
					arrow_pick_up = true;
				}
			} else
				arrow_pick_up = true;
		}
		if (!SELECTED_ARTIFACT.isMobile()) {
			setDeprecatedContents("拾う", getExp_PickUp(), new MsgBook("固定"));
		} else if (arrow_pick_up != null) {
			String msg = arrow_pick_up ? "持ち物がいっぱいで拾えない" : "これ以上まとめられない";
			setDeprecatedContents("拾う", getExp_PickUp(), new MsgBook(msg));
		} else {
			setContents("拾う", getExp_PickUp(), new MyBook() {
				@Override
				protected void contents_work() {
					SELECTED_ARTIFACT.itemPickUp();
				}
			});
		}
	}

	private void contentsReplace() {
		setContents("入替", getExp_Replace(), new Book() {
			@Override
			protected void work() {
				// TODO　ページ数の表示　印の表示
				setNextScene(new Item_List(KH, CURRENT_VIEW, "どれと入替？",
						new Book_Item() {
							@Override
							public void work(Base_Artifact a) {
								Belongings.replace(a, SELECTED_ARTIFACT);
							}
						}));
			}
		});
	}

	private void contentsReplaceInBulk(final Base_Artifact[] as) {
		boolean flag_success = false;
		if (as.length == 2) {
			flag_success = true;
			for (Base_Artifact a : as) {
				if (a.equals(Belongings.get(-1))) {
					flag_success = false;
					break;
				}
			}
		}
		if (as.length != 2)
			setDeprecatedContents("入れ替える", getExp_ReplaceInBulk(), new MsgBook(
					"アイテムを２つだけ選択してください"));
		else if (!flag_success)
			setDeprecatedContents("入れ替える", getExp_ReplaceInBulk(), new MsgBook(
					"足元のアイテムは入れ替えられない"));
		else if (as[0].isEnchantedAndCursed() || as[1].isEnchantedAndCursed()) {
			Base_Artifact a1 = as[0].isEnchantedAndCursed() ? as[0] : null;
			Base_Artifact a2 = as[1].isEnchantedAndCursed() ? as[1] : null;
			setDeprecatedContents("入れ替える", getExp_ReplaceInBulk(),
					new CurseBook(a1, a2));
		} else
			setContents("入れ替える", getExp_ReplaceInBulk(), new Book() {
				@Override
				protected void work() {
					Belongings.replace(as[0], as[1]);
					Scene_Menu_First_Item_List u = ((Scene_Menu_First_Item_List) CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE);
					setNextScene(new Scene_Menu_First_Item_List(KH,
							Scene_Action.getMe().CURRENT_VIEW, u.getPage(), u
									.getY()));
				}
			});
	}

	private void contentsSetOn() {
		if (SELECTED_ARTIFACT.isEnchantedAndCursed()) {
			setDeprecatedContents("置く", getExp_SetOn(), new CurseBook());
		} else if (!ItemFall.isAbleToFall_AroundPlayer()) {
			setDeprecatedContents("置く", getExp_SetOn(),
					new MsgBook("置けるところがない"));
		} else {
			setContents("置く", getExp_SetOn(), new MyBook() {
				@Override
				protected void contents_work() {
					SELECTED_ARTIFACT.itemSetOn();
				}
			});
		}
	}

	private void contentsSetOnInBulk(final Base_Artifact[] as) {
		if (!ItemFall.isAbleToFall_AroundPlayer()) {
			setDeprecatedContents("すべて置く", getExp_SetOnInBulk(), new MsgBook(
					"置けるところがない"));
		} else {
			setContents("すべて置く", getExp_SetOnInBulk(), new MyBook() {
				@Override
				protected void contents_work() {
					boolean flag_curse_play = false;
					int count = 0;
					Base_Artifact foot = Belongings.get(-1);
					int foot_index = -1;
					if (foot != null) {
						for (foot_index = 0; foot_index < as.length; foot_index++) {
							Base_Artifact a = as[foot_index];
							if (a.equals(foot)) {
								break;
							}
						}
					}
					for (int i = 0; i < as.length
							&& ItemFall.isAbleToFall_AroundPlayer(); i++) {
						Base_Artifact a = as[i];
						if (i == foot_index) {
							count++;
							Message.set(a.getColoredName(), "は既に置いてある");
						} else if (!a.isEnchantedAndCursed()) {
							count++;
							a.itemSetOn();
						} else {
							flag_curse_play = true;
							Message.set(a.getColoredName(), "は呪われていて外せなかった");
						}
					}
					if (count > 0) {
						if (count < as.length) {
							Message.set("（選択された",
									半角全角コンバーター.半角To全角数字(as.length), "コのうち）",
									半角全角コンバーター.半角To全角数字(count), "コのアイテムを置いた");
						} else {
							Message.set(半角全角コンバーター.半角To全角数字(count),
									"コのアイテムを置いた");
						}
						Medal.アイテムをまとめて置いた.addCount();
					} else if (flag_curse_play) {
						SE.SYSTEM_CURSE.play();
					}
				}
			});
		}
	}

	private void contentsThrow() {
		if (SELECTED_ARTIFACT.isEnchantedAndCursed()) {
			setDeprecatedContents("投げ", getExp_Throw(), new CurseBook());
		} else if (!SELECTED_ARTIFACT.isMobile()
				&& !((SELECTED_ARTIFACT instanceof Base_Trap) && EnchantSpecial
						.enchantSimbolAllCheck(ENCHANT_SIMBOL.戯))) {
			setDeprecatedContents("投げ", getExp_Throw(), new MsgBook("固定"));
		} else {
			setContents("投げ", getExp_Throw(), new MyBook() {
				@Override
				protected void contents_work() {
					Player.me.itemThrow(SELECTED_ARTIFACT,
							Player.me.getDirection());
				}
			});
		}
	}

	private void contentsUse() {
		if (SELECTED_ARTIFACT.isFrozen() && BonusConductor.気分屋_氷禁止()) {
			setDeprecatedContents("使用", getExp_Use(), new CurseBook(
					"は見飽きてしまって使う気が起きない！"));
		} else if (SELECTED_ARTIFACT.isCurse_And_isItViewed()) {
			setDeprecatedContents("使用", getExp_Use(), new CurseBook(
					"は呪われていて使用できない！"));
		} else if (isAbleToUse()) {
			setContents("使用", getExp_Use(), new Book() {
				@Override
				protected void work() {
					SELECTED_ARTIFACT.itemUseThis_fromMenu(KH, CURRENT_VIEW);
				}
			});
		} else if (SELECTED_ARTIFACT.isMerchant()) {
			setDeprecatedContents("使用", getExp_Use(), new Book() {
				@Override
				protected void work() {
					setNextScene(Scene_Action.getMe());
					Message.set("商品は使用できない");
				}
			});
		} else if (SELECTED_ARTIFACT instanceof SpellCard) {
			setDeprecatedContents("使用", getExp_Use(), new Book() {
				@Override
				protected void work() {
					setNextScene(Scene_Action.getMe());
					Message.set("修正値が足りなくて使用できない");
				}
			});
		} else if (SELECTED_ARTIFACT instanceof Scrool) {
			setDeprecatedContents("使用", getExp_Use(), new Book() {
				@Override
				protected void work() {
					setNextScene(Scene_Action.getMe());
					Message.set("満腹度が足りなくて使用できない");
				}
			});
		}
	}

	private String[] getExp_Breaking() {
		return new String[] { "このアイテムを消します", "ターンの経過はありません" };
	}

	private String[] getExp_BreakingInBulk() {
		return new String[] { "選択したアイテムをすべて消します", "ターンの経過はありません" };
	}

	private String[] getExp_Enchant() {
		ArrayList<String> list = new ArrayList<String>(4);
		if (SELECTED_ARTIFACT instanceof SpellCard) {
			SpellCard c = (SpellCard) SELECTED_ARTIFACT;
			ENCHANT_SIMBOL sim = SELECTED_ARTIFACT.sim;
			String simb, plus;
			boolean flag_p_check = c.isPerfectCheked(), flag_h_check = c
					.isStaticCheked();
			int pow;
			simb = "ます";
			if (flag_h_check && sim != null && sim.getShow() == 1)
				simb = sim.getName() + CL_N + "印が付与されます";
			pow = c.itemEnchantPower(STATUS.STR)
					+ (flag_p_check ? c.getForgeValue() : 0);
			plus = (pow < 10 ? " " : "") + pow;
			list.add(CL_ATK + "攻撃装備" + CL_N + "すると" + CL_ATK + "攻撃力" + CL_N
					+ "が" + CL_N + "+" + plus + CL_N + "され" + simb);
			simb = "ます";
			if (flag_h_check && sim != null && sim.getShow() == 2)
				simb = sim.getName() + CL_N + "印が付与されます";
			pow = c.itemEnchantPower(STATUS.DEF)
					+ (flag_p_check ? c.getForgeValue() : 0);
			plus = (pow < 10 ? " " : "") + pow;
			list.add(CL_DEF + "防御装備" + CL_N + "すると" + CL_DEF + "防御力" + CL_N
					+ "が" + CL_N + "+" + plus + CL_N + "され" + simb);
			if (flag_h_check && sim != null) {
				list.add(CL_ANY1 + "何でも装備" + CL_N + "すると" + sim.getName()
						+ CL_N + "印が付与されます");
			}
			if (SELECTED_ARTIFACT.isCurse_And_isItViewed()) {
				list.add("呪われているので自力で外すことができません");
			} else if (!flag_h_check) {
				list.add("装備すると識別されます");
			}
		} else if (SELECTED_ARTIFACT instanceof Arrow) {
			if (EnchantArrow.isEnchant(SELECTED_ARTIFACT))
				list.add("射撃キーのセットを解除します");
			else
				list.add("射撃キーにセットします");
		} else {
			list.add(CL_ANY1 + "なんでも装備" + CL_N + "します");
			ENCHANT_SIMBOL sim = SELECTED_ARTIFACT.sim;
			boolean flag = SELECTED_ARTIFACT instanceof Staff;
			if (!flag && !SELECTED_ARTIFACT.isStaticCheked()) {
				list.add("未識別アイテムなので印の効果が分かりません");
				list.add("効果が発揮された場合は自動的に識別されます");
			} else if (!flag && sim == null) {
				list.add("このアイテムには印がないので");
				list.add("装備するメリットはあまりありません");
			} else {
				switch (sim.getShow()) {
				case 1:
					list.add(CL_ATK + "攻撃印" + CL_N + "に" + sim.getName() + CL_N
							+ "を付与します");
					break;
				case 2:
					list.add(CL_DEF + "防御印" + CL_N + "に" + sim.getName() + CL_N
							+ "を付与します");
					break;
				case 3:
					list.add(CL_ANY1 + "特殊印" + CL_N + "に" + sim.getName()
							+ CL_N + "を付与します");
					break;
				case 4:
					list.add(CL_ATK + "攻撃印" + CL_N + "と" + CL_DEF + "防御印"
							+ CL_N + "に" + sim.getName() + CL_N + "を付与します");
					break;
				}
			}
			if (SELECTED_ARTIFACT.isCurse())
				list.add("呪われているので自力で外すことができません");
		}
		return list.toArray(new String[0]);
	}

	private String[] getExp_Exchange() {
		return new String[] { "足元のアイテムと交換します", "射撃物を拾った場合自動的にまとめます" };
	}

	private String[] getExp_FootExchange() {
		return new String[] { "手持ちのアイテムを１つ選んで交換します", "射撃物を拾った場合自動的にまとめます" };
	}

	private String[] getExp_PickUp() {
		return new String[] { "落ちているアイテムを拾います", "射撃物を拾った場合自動的にまとめます" };
	}

	private String[] getExp_Replace() {
		return new String[] { "選択したアイテムと位置を入れ替えます", "ターンの経過はありません" };
	}

	private String[] getExp_ReplaceInBulk() {
		return new String[] { "選択した２つのアイテムの位置を入れ替えます", "ターンの経過はありません" };
	}

	private String[] getExp_SetOn() {
		String near = Belongings.get(-1) != null ? "近く" : "足元";
		return new String[] { "アイテムを" + near + "に１つだけ置きます",
				CL_DEF + "射撃キー" + CL_N + "を押すと複数同時に置くこともできます" };
	}

	private String[] getExp_SetOnInBulk() {
		return new String[] { "複数のアイテムを同時に置きます", "選択が多すぎる場合は出来るだけ置きます" };
	}

	private String[] getExp_Throw() {
		if (SELECTED_ARTIFACT instanceof Arrow) {
			return new String[] { CL_ATK + "まとめてすべて" + CL_N + "投げます",
					"当たれば大ダメージを与えます",
					"１つずつ投げたい場合は" + CL_ATK + "使用を選択" + CL_N + "してください" };
		} else if (SELECTED_ARTIFACT instanceof Staff) {
			if (SELECTED_ARTIFACT.isCurse()) {
				return new String[] { "呪われています", "敵に当てると雀の涙ほどのダメージを与えます" };
			} else {
				return new String[] { CL_DEF + "杖" + CL_N
						+ "を敵に当てると魔法弾と同じ効果が発生します" };
			}
		} else if (SELECTED_ARTIFACT instanceof Base_Pot) {
			return new String[] { CL_NUM + "瓶" + CL_N + "を壁や敵に当てると中身がばらまかれます" };
		} else if (SELECTED_ARTIFACT instanceof Base_Grass) {
			if (SELECTED_ARTIFACT.isCurse()) {
				return new String[] { "呪われています", "敵に当てると雀の涙ほどのダメージを与えます" };
			} else {
				return new String[] {
						Color.GREEN + "草" + CL_N + "を敵に当てると" + CL_ATK
								+ "その敵と周囲８マス" + CL_N + "に", "特殊効果を発生させます",
						CL_DEF + "巻き込まれないよう注意して下さい" };
			}
		} else {
			return new String[] { "敵に当てると雀の涙ほどのダメージを与えます",
					CL_DEF + "ミニマップキー" + CL_N + "を押すとどこに落ちるか確認できます", };
		}

	}

	private String[] getExp_Use() {
		ArrayList<String> list = new ArrayList<String>(4);
		if (SELECTED_ARTIFACT instanceof Base_Trap) {
			list.add("起動させます");
			list.add("使用後は一定確率で壊れてしまいます");
		} else if (SELECTED_ARTIFACT instanceof SpellCard) {
			SpellCard c = (SpellCard) SELECTED_ARTIFACT;
			c.getBombs();
			int i = c.getBombs() - c.BOMB_USE;
			list.add("ボムを" + (c.isStaticCheked() ? c.BOMB_USE + "つ" : "")
					+ "消費してスペルを宣言します");
			if (i < 0) {
				if (c.getForgeValue() >= -i)
					list.add("ボムが不足しているので" + CL_DEF + "代わりに修正値を" + i + "します");
				else {
					list.add("ボムも修正値も不足しているため" + CL_ATK + "使用できません");
				}
			} else {
				list.add("不足分は修正値を減らします");
			}
		} else if (SELECTED_ARTIFACT instanceof Base_Grass) {
			list.add("飲んで特殊効果を得ます");
			list.add(CL_DEF + "少しだけおなかの足しになります");
		} else if (SELECTED_ARTIFACT instanceof Base_Disc) {
			list.add("知識を差し込んでカードの理解を深めます");
			list.add("選択したカードの" + CL_NUM + "修正値" + CL_N + "が上がります");
			list.add(CL_NUM + "修正値" + CL_N + "が上がると" + CL_ATK + "攻撃力" + CL_N
					+ "と" + CL_DEF + "防御力" + CL_N + "が上がります");
		} else if (SELECTED_ARTIFACT instanceof Scrool) {
			list.add("高く掲げて書に込められた呪文を解放します");
		} else if (SELECTED_ARTIFACT instanceof Arrow) {
			list.add("１つだけ手にとって投げます");
			list.add("まとめて投げたい場合は" + CL_DEF + "投げ" + CL_N + "を選択してください");
		} else if (SELECTED_ARTIFACT instanceof Food) {
			list.add("おいしく食べます");
			list.add(CL_DEF + "満腹の時に食べると最大満腹度が上昇します");
		} else if (SELECTED_ARTIFACT instanceof Base_Pot) {
			list.add("アイテムを１つ選んで中に入れます");
			list.add("もしくは中身のアレを上から順に使用します");
		} else if (SELECTED_ARTIFACT instanceof Ring) {
			list.add("高く掲げてリボンに込められた能力を解放します");
			list.add(CL_DEF + "これによって得た印の効果は次の階まで有効です");
		} else if (SELECTED_ARTIFACT instanceof Staff) {
			list.add("強く振って" + CL_DEF + "必中の魔法弾" + CL_N + "を飛ばします");
			list.add("振る度に残りの回数が減ります");
			list.add("拾ったときの残り回数は２～６です");
		} else {
			list.add("このアイテムを使用します");
			list.add("使用すると回数が減ったり無くなったりします");
		}
		return list.toArray(new String[0]);
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
	}

	protected void initializeContents(Base_Artifact... as) {
		if (as.length != 0) {
			contentsSetOnInBulk(as);
			contentsBreakInBulk(as);
			contentsReplaceInBulk(as);
		} else {
			if (FLAG_FOOT) {
				contentsPickUp();
				contentsThrow();
				contentsUse();
				contentsFootExchange();
				contentsBreak();
				contentsExplain();
			} else {
				// if (Belongings.getItemIndex(SELECTED_ARTIFACT) >=
				// Belongings.MAX)
				// contentsReplace();
				// else
				contentsEnchant();
				contentsThrow();
				contentsUse();
				// if (((Item_List) CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE)
				// .getPage() >= 3) {
				// contentsFootExchange();
				// } else {
				contentsExchange();
				// }
				contentsSetOn();
				contentsExplain();
			}
		}
	}

	private boolean isAbleToUse() {
		if (SELECTED_ARTIFACT.isMerchant()) {
			return false;
		} else if (SELECTED_ARTIFACT instanceof SpellCard) {
			SpellCard c = (SpellCard) SELECTED_ARTIFACT;
			return c.isAbleToUse();
		}
		return true;
	}

	protected void setContents() {
		super.setContents("");
	}

	@Override
	protected void setContents(String name, String[] explain, Book book) {
		if (Player.me.conditionCheck(CONDITION.おにぎり)) {
			setDeprecatedContents(name, explain, new MsgBook(
					"おにぎりの姿ではアイテムに触われません"));
		} else if (HoldEnemy.ME.isHold())
			setDeprecatedContents(name, explain, new MsgBook(
					"敵を装備している間はアイテムに触われません"));
		else
			super.setContents(name, explain, book);
	}

}