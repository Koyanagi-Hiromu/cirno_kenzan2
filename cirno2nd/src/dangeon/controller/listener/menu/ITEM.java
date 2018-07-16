package dangeon.controller.listener.menu;

import java.awt.Color;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.TurnSystemController;
import dangeon.controller.listener.menu.Conducter.PHASE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class ITEM {
	public enum ITEM_COMMAND {
		ENCHANT(0), Use(1), Throw(2), Put(3), Explain(4);
		private int y;
		public static final int LENGTH = ITEM_COMMAND.values().length;

		private static final Color BLACK = new Color(0x33, 0x00, 0x00);

		public static ITEM_COMMAND get(int y) {
			for (ITEM_COMMAND c : values())
				if (c.y == y)
					return c;
			return null;
		}

		private ITEM_COMMAND(int y) {
			this.y = y;
		}

		public Color getColor() {
			Base_Artifact a = Belongings.get(getIndex());
			switch (this) {
			case ENCHANT:
			case Throw:
				if (!a.isMobile()) {
					return BLACK;
				}
				if (a.isCurse() && Enchant.isEnchanted(a)) {
					return BLACK;
				}
				break;
			case Put:
				if (!a.isMobile()) {
					return BLACK;
				}
				if (a.isCurse() && Enchant.isEnchanted(a)) {
					return BLACK;
				}
				if (foot) {
					if (Belongings.getSize() == 0) {
						return BLACK;
					}
				} else if (Belongings.get(-1) != null) {
					if (!isFootMobile()) {
						return BLACK;
					}
				}
				break;
			case Use:
				if (a.isCurse()) {
					if (a instanceof SpellCard) {
						if (a.isPerfectCheked()) {
							return BLACK;
						}
					} else {
						return BLACK;
					}
				}
			case Explain:
			}
			return Color.WHITE;
		}

		@Override
		public String toString() {
			switch (this) {
			case ENCHANT:
				if (foot)
					return "拾う";
				// if (Belongings.get(getIndex()).isEnchantedNow())
				// return "外す";
				return "装備";
			case Use:
				if (Belongings.get(getIndex()).getColoredName()
						.matches(Stairs.name))
					return "進む";
				return "使用";
			case Throw:
				return "投げ";
			case Put:
				if (foot || Belongings.get(-1) != null) {
					return "交換";
				}
				return "置く";
			case Explain:
				if (Belongings.get(getIndex()).isStaticCheked()) {
					return "説明";
				} else {
					return "名前";
				}
			}
			return "エラー";
		}
	}

	public static boolean exchange = false;
	public static boolean flag_command_phase = false;
	public static int x = 0, y = 0;
	public static int command_y;
	public static boolean flag_explanation_phase = false;
	private static boolean foot = false;
	public static boolean flag_naming_phase = false;
	public static final int Y_LENGTH = 10, X_LENGTH = Belongings.getMax()
			/ Y_LENGTH;

	private static void actionCommand(ACTION action) {
		if (flag_naming_phase) {
			String name = Menu_Select_String.ME.actionCommand(action);
			if (name != null) {
				// Checker.setTempName(Belongings.get(getIndex()), name);
			}
			return;
		}
		Base_Artifact a = Belongings.get(getIndex());
		switch (action) {
		case ENTER:
			switch (ITEM_COMMAND.get(command_y)) {
			case ENCHANT:
				if (!isMobile()) {
					return;
				}
				if (isFoot()) {
					boolean flag = (Belongings.getSize() != Belongings.getMax());
					Belongings.get(-1).itemPickUp();
					if (flag) {
						TurnSystemController.callMeToStartEnemyTurn();
					}
					// } else if (Belongings.get(getIndex()).isEnchantedNow()) {
					// if (Enchant.tryToRemove(Belongings.get(getIndex()))) {
					// SE.ENCHANT_OFF.play();
					// TurnSystemController.callMeToStartEnemyTurn();
					// }
				} else {
					if (a.isCurse() && Enchant.isEnchanted(a)) {
						setMenuEnd();
						Message.set(a.getColoredName(), "は呪われていて外せない！");
						return;
					}
					ENCHANT.initENCHANT(a);
					return;
				}
				break;
			case Use:
				Belongings.get(getIndex()).itemUseThis();
				setInit();
				break;
			case Throw:
				if (!isMobile()) {
					return;
				}
				if (isThrow(a)) {
					Player.me.itemThrow(a, Player.me.getDirection());
					TurnSystemController.callMeToStartEnemyTurn();
				}
				setInit();
				break;
			case Put:
				if (!isMobile()) {
					return;
				}
				if (foot) {
					if (Belongings.getSize() == 0) {
						return;
					}
					exchange = true;
					return;
				} else {
					if (a.isCurse() && Enchant.isEnchanted(a)) {
						setMenuEnd();
						Message.set(a.getColoredName(), "は呪われていて外せない！");
						return;
					}
					if (Belongings.get(-1) != null) {
						if (!isFootMobile()) {
							return;
						}
						actionExchange(action);
					} else {
						if (Belongings.get(getIndex()).itemSetOn()) {
							TurnSystemController.callMeToStartEnemyTurn();
						}
						setInit();
					}
				}
				break;
			case Explain:
				if (Belongings.get(getIndex()).isStaticCheked()) {
					flag_explanation_phase = true;
				} else {
					Menu_Select_String.ME = new Menu_Select_String(
							Belongings.get(getIndex()), new Task() {
								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								@Override
								public void work() {
									flag_naming_phase = false;
								}

								@Override
								protected void work(int frame) {
									work();
									flag_command_phase = false;
								}
							});
					flag_naming_phase = true;
				}
				return;
			}
			setMenuEnd();
			break;
		case CANCEL:
			if (flag_explanation_phase) {
				flag_explanation_phase = false;
				break;
			}
			flag_command_phase = false;
			break;
		}
	}

	private static void actionExchange(ACTION action) {
		switch (action) {
		case ENTER:
			int i = getIndex();
			Base_Artifact a = Belongings.get(i);
			if (a.isCurse() && Enchant.isEnchanted(a)) {
				Message.set(a.getColoredName(), "は呪われていて外せない！");
				exchange = false;
				flag_command_phase = false;
				Conducter.phase = PHASE.FIRST;
				DangeonScene.setScene(DangeonScene.DANGEON);
				return;
			}
			Base_Artifact.exchange(Belongings.get(-1), i);
			TurnSystemController.callMeToStartEnemyTurn();
		case CANCEL:
			exchange = false;
			flag_command_phase = false;
			Conducter.phase = PHASE.FIRST;
			DangeonScene.setScene(DangeonScene.DANGEON);
			break;
		}
	}

	private static void actionFirst(ACTION action) {
		switch (action) {
		case ENTER:
			if (!foot && Belongings.getSize() == 0)
				return;
			if (foot && Belongings.get(-1) == null)
				return;
			command_y = 0;
			flag_command_phase = true;
			break;
		case ITEM:
			SE.SYSTEM_CANCEL.play();
			DangeonScene.setScene(DangeonScene.DANGEON);
			// break;
		case CANCEL:
			Conducter.phase = PHASE.FIRST;
			break;
		default:
			SE.SYSTEM_ARRANGEMENT.play();
			Belongings.sort();
			break;
		}
	}

	static void actionKeyPressed(ACTION action) {
		if (exchange) {
			actionExchange(action);
		} else if (flag_command_phase) {
			actionCommand(action);
		} else {
			actionFirst(action);
		}
	}

	static void arrowKeyPressed(DIRECTION direction) {
		if (flag_naming_phase) {
			Menu_Select_String.ME.arrowKeyPressed(direction);
			return;
		}
		if (flag_explanation_phase) {
			return;
		}
		if (flag_command_phase && !exchange) {
			// コマンド
			int _command_y = command_y;
			switch (direction) {
			case UP:
				_command_y--;
				break;
			case DOWN:
				_command_y++;
				break;
			case LEFT:
			case RIGHT:
				break;
			}
			if (_command_y < 0) {
				_command_y = ITEM_COMMAND.LENGTH - 1;
			} else if (_command_y > ITEM_COMMAND.LENGTH - 1) {
				_command_y = 0;
			}
			command_y = _command_y;
			return;
		}
		if (isFoot()) {
			// 足元
			if (Belongings.get(-1) == null || Belongings.getSize() == 0) {
				return;
			}
			foot = false;
			y = 0;
			switch (direction) {
			case LEFT:
				x = getMaxPage() - 1;
				break;
			case RIGHT:
				x = 0;
				break;
			case UP:
			case DOWN:
			}
			return;
		}
		// 通常・交換
		if (Belongings.getSize() == 0)
			return;
		int _x = x, _y = y;
		switch (direction) {
		case UP:
			_y--;
			break;
		case DOWN:
			_y++;
			break;
		case LEFT:
			_x--;
			break;
		case RIGHT:
			_x++;
			break;
		}
		while (true) {
			if (_x < 0 || _x > getMaxPage() - 1) {
				if (!exchange && Belongings.get(-1) != null
						&& !(Belongings.get(-1) instanceof Stairs)) {
					foot = true;
					return;
				} else if (_x < 0) {
					_x = getMaxPage() - 1;
				} else {
					_x = 0;
				}
			}
			int MAX_Y = Belongings.getSize() - _x * Y_LENGTH;
			if (MAX_Y < 1) {
				_x--;
				continue;
			}
			if (MAX_Y > Y_LENGTH)
				MAX_Y = Y_LENGTH;
			if (_y < 0)
				_y = MAX_Y - 1;
			if (_y > MAX_Y - 1) {
				if (_x == x)
					_y = 0;
				else
					_y = MAX_Y - 1;
			}
			break;
		}
		x = _x;
		y = _y;
	}

	/**
	 * @return 足元選択で-1
	 */
	public static int getIndex() {
		if (isFoot()) {
			return -1;
		}
		return x * Y_LENGTH + y;
	}

	public static int getMaxPage() {
		return (Belongings.getSize() - 1) / Y_LENGTH + 1;
	}

	public static int getPage() {
		return x + 1;
	}

	public static boolean isFoot() {
		return foot && !exchange;
	}

	private static boolean isFootMobile() {
		return Belongings.get(-1).isMobile();
	}

	public static boolean isLeftPageExist() {
		return x > 0;
	}

	private static boolean isMobile() {
		Base_Artifact a;
		if (isFoot()) {
			a = Belongings.get(-1);
		} else {
			a = Belongings.get(getIndex());
		}
		return a.isMobile();
	}

	public static boolean isRightPageExist() {
		return x < X_LENGTH - 1;
	}

	private static boolean isSelectedArtifactMobile() {
		return Belongings.get(getIndex()).isMobile();
	}

	private static boolean isThrow(Base_Artifact a) {
		if (Enchant.isEnchanted(a)) {
			if (a.isCurse()) {
				Message.set(a.getColoredName(), "は呪われていて投げられない！");
				return false;
			}
		}
		return true;
	}

	public static void setFoot() {
		foot = true;
		command_y = 0;
		x = 0;
		y = 0;
		flag_command_phase = Belongings.get(-1) != null;
	}

	public static void setInit() {
		command_y = 0;
		x = 0;
		y = 0;
		foot = false;
	}

	static void setMenuEnd() {
		flag_command_phase = false;
		Conducter.phase = PHASE.FIRST;
		DangeonScene.setScene(DangeonScene.DANGEON);
	}

}
