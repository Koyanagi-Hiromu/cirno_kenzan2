package dangeon.latest.scene.action.message;

import java.util.ArrayList;

import main.constant.FR;
import main.res.Image_LargeCharacter;
import dangeon.controller.task.Task;
import dangeon.view.constant.NormalFont;
import dangeon.view.detail.MessageBox;
import dangeon.view.util.StringFilter;

public class MsgBlock {
	// private Msg2LineBox[] MSG_BOX;
	public static final int BLOCK_DURATION = 500;
	public static final int LAST_MESSAGE_DURATION = 1000;
	public int last_message_time = 0;
	public static final int SPEED = 5;
	public static int BOX_LINE = 3;

	// public Msg2LineBox[] getMSGBOXes() {
	// return MSG_BOX;
	// }

	private final ArrayList<String> LINE_LIST = new ArrayList<String>();
	private int diff = -NormalFont.HEIGHT * (BOX_LINE);
	private String[] string_arr;
	private boolean first_space_exist = true;

	public final Image_LargeCharacter ILC;

	boolean isSlept = false;

	int sleep = 0;

	boolean wait_for_pushing_any_key;

	private int y = 0;

	private boolean flag_rapid = false;

	ArrayList<Task> TASK_LIST = new ArrayList<Task>();

	/**
	 * %で必要改行 $で絶対改行 「%」と「$」は表示不可能<br/>
	 * 
	 * @param array
	 */
	MsgBlock(Image_LargeCharacter ilc, ArrayList<String> arr) {
		if (ilc.equals(Image_LargeCharacter.ANY)) {
			ILC = null;
		} else {
			ILC = ilc;
		}
		this.string_arr = arr.toArray(new String[0]);
		init();
	}

	public MsgBlock(Image_LargeCharacter ilc, ArrayList<String> arr, int sleep) {
		this(ilc, arr);
		wait_for_pushing_any_key = true;
		this.sleep = sleep;
		isSlept = true;
	}

	public void concat(String[] array) {
		String arr[] = new String[string_arr.length + 1 + array.length];
		for (int i = 0; i < string_arr.length; i++) {
			arr[i] = string_arr[i];
		}
		arr[string_arr.length] = "$";
		for (int i = 0; i < array.length; i++) {
			arr[string_arr.length + 1 + i] = array[i];
		}
		string_arr = arr;
		init();
	}

	public int getDif() {
		return diff;
	}

	private int getLeftLinesNumbers() {
		return LINE_LIST.size() - (BOX_LINE + y + 4);
	}

	public String getLine(int i) {
		try {
			return LINE_LIST.get(i + y);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

	public ArrayList<String> getMsg() {
		return LINE_LIST;
	}

	private void init() {
		StringFilter.setList(LINE_LIST, MessageBox.W_VALID, string_arr);
		last_message_time = 0;
	}

	public boolean isLast() {
		return !first_space_exist && !isNextLineExist() && sleep == 0;
	}

	private boolean isNext3LinesExist() {
		return LINE_LIST.size() > BOX_LINE + y + 4;
	}

	private boolean isNextLineExist() {
		return LINE_LIST.size() > BOX_LINE + y;
	}

	public boolean isOverDuration() {
		if (wait_for_pushing_any_key) {
			return false;
		} else if (flag_rapid) {
			return true;
		} else {
			int duration = (Message.isNextTaskEmpty()) ? LAST_MESSAGE_DURATION
					: BLOCK_DURATION;
			return last_message_time >= duration;
		}
	}

	public void remove1stLine() {
		y++;
	}

	public void setFlagRapid(boolean b) {
		flag_rapid = b;
	}

	public void setTask(Task task) {
		TASK_LIST.add(task);
	}

	public void upDate() {
		if (first_space_exist) {
			diff += SPEED * Math.max(2, getLeftLinesNumbers());
			if (diff >= 0) {
				diff = 0;
				first_space_exist = false;
			}
		} else if (isNextLineExist()) {
			if (isNext3LinesExist()) {
				diff += SPEED * getLeftLinesNumbers();
			} else {
				diff += SPEED;
			}
			if (diff >= NormalFont.HEIGHT) {
				diff = 0;
				remove1stLine();
			}
		} else {
			if (sleep > 0) {
				sleep--;
				if (sleep < 0) {
					sleep = 0;
				}
				return;
			}
			if (!TASK_LIST.isEmpty()) {
				ArrayList<Task> _TASK_LIST = new ArrayList<Task>();
				for (Task task : TASK_LIST) {
					_TASK_LIST.add(task);
				}
				for (Task task : _TASK_LIST) {
					task.work();
				}
				TASK_LIST.clear();
			}
			if (!wait_for_pushing_any_key) {
				if (isOverDuration()) {
					diff += SPEED * 2;
					int DIFF = NormalFont.HEIGHT;
					DIFF *= LINE_LIST.size() - y + 1;
					if (diff >= DIFF) {
					}
				} else {
					last_message_time += FR.THREAD_SLEEP;
				}
			}
		}
	}

}
