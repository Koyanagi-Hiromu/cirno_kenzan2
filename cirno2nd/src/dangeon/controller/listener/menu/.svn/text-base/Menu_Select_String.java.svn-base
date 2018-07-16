package dangeon.controller.listener.menu;

import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.model.object.artifact.Base_Artifact;

public class Menu_Select_String {
	public static Menu_Select_String ME;
	private final Task END_TASK;
	private final String SOUND_ORIGNE, SOUND_SHIFT;
	private final String CLEAR_SOUND, DULL_SOUND;
	private final Base_Artifact A;

	public ArrayList<Character> LIST = new ArrayList<Character>();

	private StringBuilder title = new StringBuilder();
	private int x, y;
	public final int X_LENGTH = 5, Y_LENGTH = 14;
	private int select_count = 0;

	private String selection = null;

	/**
	 * 
	 * @param task
	 *            キャンセル時にwork()を<br/>
	 *            決定時にwork(int)を呼ぶ
	 */
	public Menu_Select_String(Base_Artifact a, Task task) {
		A = a;
		// String tit = Checker.getNamed(A);
		// if (tit != null) {
		// title.append(tit.substring(tit.indexOf("：") + 1));
		// }
		END_TASK = task;
		String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのぁぃぅぇぉ１２３４５はひふへほまみむめもやゆよっーらりるれろわ　を　んゃゅょ？　６７８９０";
		for (int i = 0; i < str.length(); i++) {
			LIST.add(str.charAt(i));
		}
		SOUND_ORIGNE = "かきくけこさしすせそたちつてとはひふへほがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ";
		SOUND_SHIFT = "がぎぐげござじずぜぞだぢづでどばびぶべぼかきくけこさしすせそたちつてとぱぴぷぺぽはひふへほ";
		CLEAR_SOUND = "かきくけこさしすせそたちつてとはひふへほはひふへほ";
		DULL_SOUND = "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ";
	}

	/**
	 * 
	 * @param action
	 * @return 生成した文字列を返す（決定時以外はnull）
	 */
	@Deprecated
	public String actionCommand(ACTION action) {

		switch (action) {
		case TURN:
			selection();
			return null;
		case FIRE:
			SE.SYSTEM_ENTER.play();
			x = 4;
			y = -1;
			return null;
		case BIAS:
			shiftSoundMark();
			return null;
		case ENTER:
			if (y == -1) {
				if (x == 0) {
					selection();
					return null;
				} else if (x == 2) {
					remove1();
					return null;
				} else {
					// END_TASK.setKey(1);

					// END_TASK.work_call();
					return A.getClassName().concat(getTitle());
				}
			} else {
				addChar();
			}
			break;
		case CANCEL:
			if (title.length() == 0) {
				// END_TASK.setKey(-1);
				// END_TASK.work_call();
			} else {
				remove1();
			}
			break;
		default:
			break;
		}
		return null;
	}

	public void addChar() {
		String c = getChar();
		title.append(c);
		selection_reset();
	}

	public void arrowKeyPressed(DIRECTION direction) {
		switch (direction) {
		case UP:
		case DOWN:
		case LEFT:
		case RIGHT:
			break;
		default:
			return;
		}
		x += direction.X;
		if (x == -1) {
			x = X_LENGTH - 1;
			if (y >= Y_LENGTH / 2) {
				y -= Y_LENGTH / 2;
			} else if (y != -1) {
				y += Y_LENGTH / 2;
			}
		} else if (x == X_LENGTH) {
			x = 0;
			if (y >= Y_LENGTH / 2) {
				y -= Y_LENGTH / 2;
			} else if (y != -1) {
				y += Y_LENGTH / 2;
			}
		}
		if (direction.Y < 0) {
			if (y == 0 || y == Y_LENGTH / 2) {
				if (y == 0) {
					if (x >= 4) {
						x = 2;
					} else {
						x = 0;
					}
				} else {
					x = 4;
				}
				y = -1;
			} else {
				if (y == -1) {
					if (x == 0) {
						x = 0;
						y = Y_LENGTH / 2 - 1;
					} else if (x == 2) {
						x = 4;
						y = Y_LENGTH / 2 - 1;
					} else {
						x = 2;
						y = Y_LENGTH - 1;
					}
				} else {
					y += direction.Y;
				}
			}
		} else if (direction.Y > 0) {
			if (y == Y_LENGTH / 2 - 1 || y == Y_LENGTH - 1) {
				if (y == Y_LENGTH / 2 - 1) {
					if (x >= 4) {
						x = 2;
					} else {
						x = 0;
					}
				} else {
					x = 4;
				}
				y = -1;
			} else {
				if (y == -1) {
					if (x == 0) {
						x = 0;
						y = 0;
					} else if (x == 2) {
						x = 4;
						y = 0;
					} else {
						x = 2;
						y = Y_LENGTH / 2;
					}
				} else {
					y += direction.Y;
				}
			}
		}
		if (y == -1) {
			x += direction.X;
			if (x == 0 || x == 1) {
				x = 0;
			} else if (x == 2) {
				x = 2;
			} else {
				x = 4;
			}
		}
	}

	public String getChar() {
		return getChar(x, y);
	}

	public String getChar(int x, int y) {
		int index = 5 * y + x;
		if (index < 0) {
			return "";
		}
		return String.valueOf((LIST.get(index)));
	}

	public int getPointX() {
		return getPointX(x, y);
	}

	public int getPointX(int x, int y) {
		if (y == -1) {
			if (x == 0) {
				return 0;
			} else if (x == 2) {
				return 4;
			} else {
				return 8;
			}
		}
		if (y > Y_LENGTH / 2 - 1) {
			return x + X_LENGTH + 1;
		} else {
			return x;
		}
	}

	public int getPointY() {
		return getPointY(y);
	}

	public int getPointY(int y) {
		if (y > Y_LENGTH / 2 - 1) {
			return y - (Y_LENGTH / 2 - 1);
		} else {
			return y + 1;
		}
	}

	public String getTitle() {
		if (selection == null) {
			return title.toString();
		} else {
			return selection;
		}
	}

	private void remove1() {
		if (title.length() > 0) {
			title.deleteCharAt(title.length() - 1);
		}
		selection_reset();
	}

	private void selection() {
		SE.SYSTEM_ARRANGEMENT.play();
		try {
			selection_throwExp();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	private void selection_reset() {
		select_count = 0;
		selection = null;
	}

	@Deprecated
	private void selection_throwExp() throws IllegalArgumentException,
			SecurityException, IllegalAccessException, NoSuchFieldException {
		// ArrayList<Class<?>> all_list =
		// Checker_ClassName.getList(A.getClass());
		// ArrayList<Class<?>> matched_list = new ArrayList<Class<?>>();
		// StringBuilder prefix = new StringBuilder(title);
		// for (int i = 0; i < title.length(); i++) {
		// int index = DULL_SOUND.indexOf(String.valueOf(prefix.charAt(i)));
		// if (index > -1) {
		// prefix.replace(i, i + 1,
		// CLEAR_SOUND.substring(index, index + 1));
		// }
		// }
		// for (Class<?> clazz : all_list) {
		// if (title.length() > 0) {
		// String name_sound = clazz.getDeclaredField("item_name_sound")
		// .get(null).toString();
		// if (!name_sound.startsWith(prefix.toString())) {
		// continue;
		// }
		// }
		// matched_list.add(clazz);
		// }
		// if (matched_list.size() == 0) {
		// return;
		// }
		// HashMap<String, String> hash = new HashMap<String, String>(
		// matched_list.size());
		// ArrayList<String> matched_list_sound = new ArrayList<String>(
		// matched_list.size());
		// for (Class<?> clazz : matched_list) {
		// String name = clazz.getDeclaredField("item_name").get(null)
		// .toString();
		// String name_sound = clazz.getDeclaredField("item_name_sound")
		// .get(null).toString();
		// hash.put(name_sound, name);
		// matched_list_sound.add(name_sound);
		// }
		// Collections.sort(matched_list_sound);
		// if (select_count == matched_list.size()) {
		// select_count -= matched_list.size();
		// }
		// selection = hash.get(matched_list_sound.get(select_count));
		// select_count++;
	}

	private void shiftSoundMark() {
		if (title.length() == 0) {
			return;
		}
		selection_reset();
		String c = title.substring(title.length() - 1);
		int i = SOUND_ORIGNE.indexOf(c);
		if (i != -1) {
			title.deleteCharAt(title.length() - 1);
			title.append(SOUND_SHIFT.charAt(i));
			SE.SYSTEM_ENTER.play();
		}
	}

}