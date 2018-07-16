package dangeon.latest.scene.action.message;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

import main.Listener.ACTION;
import main.res.Image_LargeCharacter;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.util.view_window.WindowFrame;
import dangeon.model.config.Config;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Message {
	private class Line {
		private final String MSG;
		private boolean rapid;
		private boolean push;
		private MessageLock task;
		private final boolean group;

		public Line(String str) {
			MSG = str;
			group = turn_switch;
		}

	}

	public static final Integer X = null, Y = 250, WINDOW_Y = Y - 10, W = 370,
			COLS = 3;
	private static final ArrayList<MsgBlock> message_task = new ArrayList<MsgBlock>();
	private static ArrayList<Task> task_list = new ArrayList<Task>();
	private static Image_LargeCharacter ilc = Image_LargeCharacter.ANY;

	public static void clearRecord() {
		ME.record.clear();
	}

	static void endTask() {
		MsgBlock mb = message_task.get(0);
		if (mb.isSlept) {
			Player.flag_clear = false;
		}
		setRecord(mb.getMsg().toArray(new String[0]));
		if (!mb.TASK_LIST.isEmpty()) {
			for (Task task : mb.TASK_LIST) {
				task.work();
			}
			mb.TASK_LIST.clear();
		}
		message_task.remove(0);
	}

	public static MsgBlock getNextTask() {
		return message_task.get(1);
	}

	public static String[] getRecord() {
		return message_record.toArray(new String[0]);
	}

	public static String getRecord(int i) {
		return message_record.get(i);
	}

	public static int getRecordSize() {
		return ME.record.size();
	}

	public static MsgBlock getTask() {
		return message_task.get(0);
	}

	public static boolean isDemandToWaitAnySystem() {
		return ME.isDemandToWaitAnySystem_();
	}

	public static boolean isDemandToWaitPushingAnyKey() {
		return ME.isDemandToWaitPushingAnyKey_();
	}

	public static boolean isNextTaskEmpty() {
		return message_task.size() == 1;
	}

	public static boolean isTaskEmpty() {
		return message_task.isEmpty();
	}

	@Deprecated
	public static void releaseLock() {
		ME.releaseLock_(null);
	}

	public static void releaseLock(LinkedHashSet<ACTION> tasklist_action) {
		ME.releaseLock_(tasklist_action);
	}

	public static void removeILC() {
		ilc = Image_LargeCharacter.ANY;
	}

	/**
	 * _@で停止
	 * 
	 * @param strings
	 */
	public static void set(Object... strings) {
		String[] arr = new String[strings.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = strings[i].toString();
		}
		set(arr);
	}

	/**
	 * _@で停止
	 * 
	 * @param strings
	 */
	public static void set(String... strings) {
		ME.set_non_statiic(strings);
	}

	public static void setConcatFlag(boolean b) {
		ME.flag_concat = b;
	}

	/**
	 * 
	 * @param im_lc
	 *            「@」で文章待機中のときに表示するイメージを指定<br/>
	 *            解除はnullを指定すること
	 */
	public static void setImageLargeCharacter(Image_LargeCharacter im_lc) {
		if (im_lc == null) {
			ilc = Image_LargeCharacter.ANY;
		} else {
			ilc = im_lc;
		}
	}

	public static void setMessageTask(Task task_work_at_complete_apperance,
			String... array) {
		set(array);
		if (task_work_at_complete_apperance != null) {
			message_task.get(message_task.size() - 1).setTask(
					task_work_at_complete_apperance);
		}
	}

	public static void setMessageTask_AfterSleepDeamandKeyPush(int sleep,
			String... array) {
		ArrayList<String> list = new ArrayList<String>(array.length);
		boolean flag = false;
		for (String str : array) {
			list.add(str);
		}
		if (!flag) {
			removeILC();
		}
		if (!list.isEmpty()) {
			message_task.add(new MsgBlock(ilc, list, sleep));
		}

	}

	public static void setRecord(String... arr) {
		for (String str : arr) {
			if (message_record.size() == MAX_RECORD) {
				message_record.remove(0);
			}
			message_record.add(str);
		}
		if (message_record.size() == MAX_RECORD) {
			message_record.remove(0);
		}
		message_record.add("　　　　　　　　▼");
	}

	public static void setTask_AfterReleaseDemandToPushEnter(Task task) {
		ME.setTask_AfterReleaseDemandToPushEnter_non_static(task);
	}

	public static void setTurnStartFlagOn() {
		ME.flag_turn_start = true;
	}

	private final int RECORD = 100;

	public final static Message ME = new Message();

	private static final int HOLDING_INTERVAL = 60, WAITING_INTERVAL = 1;

	private final ArrayList<Line> task;

	private final ArrayList<String> record;

	private final WindowFrame WINDOW_FRAME;

	private int holding_time, waiting_time;
	private int diff;
	private boolean turn_switch, record_switch;
	private ArrayList<Conversation> conv_list = new ArrayList<Conversation>();

	private Color previous_color;

	public static final String HORIZON = "&--";

	public static final int MAX_RECORD = 100;

	private static final ArrayList<String> message_record = new ArrayList<String>(
			MAX_RECORD);

	public static void end() {
		// View_Sider_Info.reset();
		ME.reset();
	}

	private boolean flag_concat = true, flag_turn_start = true;

	private Message() {
		task = new ArrayList<Line>();
		record = new ArrayList<String>(RECORD);
		WINDOW_FRAME = new WindowFrame(X, WINDOW_Y, W, COLS);
		reset();
	}

	boolean addConv(Conversation conversation) {
		conv_list.add(conversation);
		return conv_list.size() == 1;
	}

	private void concat() {
		if (flag_concat && !task.isEmpty()) {
		} else {
			turn_switch = !turn_switch;
			if (flag_turn_start && !task.isEmpty()) {
				for (Line line : task) {
					line.rapid = true;
				}
			} else {
				flag_turn_start = false;
			}
		}
		flag_concat = true;
	}

	public void draw(Graphics2D g) {
		if (!conv_list.isEmpty()) {
			WINDOW_FRAME.drawWindow(g);
			drawStringField(g, conv_list.get(0));
		} else if (!task.isEmpty()) {
			if (holding_time < HOLDING_INTERVAL) {
				WINDOW_FRAME.drawWindow(g);
				drawStringField(g);
			} else {
				reset();
			}
		}
	}

	private void draw4Strings(Graphics2D g, WindowFrame wf) {
		int h = wf.getContentHeight();
		if (diff < h) {
			if (previous_color != null)
				g.setColor(previous_color);
			for (int i = 0; i < COLS + 1 && i < task.size(); i++) {
				wf.drawString(g, task.get(i).MSG, i + 1, -diff);
			}
			previous_color = StringFilter.getColor(g, task.get(0).MSG);
			if (waiting_time > 0) {
				waiting_time--;
			} else {
				diff += speed();
			}
		} else {
			remove();
			if (task.size() < 6) {
				waiting_time = WAITING_INTERVAL + 1;
				diff = 0;
			} else {
				diff -= h;
			}
			drawString(g, wf);
		}
	}

	private void drawbelow4Strings(Graphics2D g, WindowFrame wf, boolean lock) {
		int max = lock ? getIndexOfLockInCOLS() + 1 : task.size();
		for (int i = 0; i < max; i++) {
			wf.drawString(g, task.get(i).MSG, i + 1, 0);
		}
	}

	private boolean drawString(Graphics2D g, WindowFrame wf) {
		if (isLockInCOLS()) {
			drawbelow4Strings(g, wf, true);
			return true;
		} else if (task.size() <= COLS) {
			boolean b = true;
			for (Line line : task) {
				b = line.rapid && b;
			}
			if (b) {
				draw4Strings(g, wf);
			} else {
				drawbelow4Strings(g, wf, false);
			}
			holding_time++;
		} else {
			draw4Strings(g, wf);
		}
		return false;
	}

	private void drawStringField(Graphics2D g) {
		WindowFrame wf = WINDOW_FRAME.createDoubleSizeClearCopy();
		if (drawString(wf.initGraphics2Scaled(), wf))
			WINDOW_FRAME.drawArrow(g, false);
		wf.drawWindow(g);
	}

	private void drawStringField(Graphics2D g, Conversation conv) {
		WindowFrame wf = WINDOW_FRAME.createDoubleSizeClearCopy();
		int i = 0;
		for (String s : conv.getTEXT()) {
			wf.drawString(wf.initGraphics2Scaled(), s, ++i, 0);
		}
		if (!conv.isSelection())
			WINDOW_FRAME.drawArrow(g, conv.isWriting());
		wf.drawWindow(g);
		conv.drawName(g);
	}

	private int getIndexOfLockInCOLS() {
		int i = 0;
		for (Line line : task) {
			if (line.push) {
				return i;
			}
			if (++i == COLS) {
				return -1;
			}
		}
		return -1;
	}

	public String[] getRecordToArray() {
		return record.toArray(new String[0]);
	}

	private boolean isDemandToWaitAnySystem_() {
		return task.size() > 8;
		// if (task.isEmpty() || holding_time > 0) {
		// return false;
		// }
		// return task.get(0).rapid;
	}

	private boolean isDemandToWaitPushingAnyKey_() {
		if (!conv_list.isEmpty())
			return true;
		for (Line line : task) {
			if (line.push) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		return task.isEmpty() && conv_list.isEmpty();
	}

	private boolean isLockInCOLS() {
		return getIndexOfLockInCOLS() != -1;
	}

	private void release() {
		if (getTask().isLast()) {
			SE.SYSTEM_ENTER.play();
			getTask().wait_for_pushing_any_key = false;
			endTask();
			if (message_task.isEmpty()
					|| !message_task.get(0).wait_for_pushing_any_key) {
				ArrayList<Task> list = new ArrayList<Task>(task_list.size());
				for (Task task : task_list) {
					list.add(task);
				}
				for (Iterator<Task> iterator = list.iterator(); iterator
						.hasNext();) {
					Task task = iterator.next();
					task.work();
					task_list.remove(task);
				}
			}
		}
	}

	private void releaseLock_(int index) {
		SE.SYSTEM_ENTER.play();
		Line line = task.get(index);
		for (int i = 0; i <= index; i++) {
			remove();
		}
		if (line.task != null)
			line.task.second();
	}

	private void releaseLock_(LinkedHashSet<ACTION> tasklist_action) {
		if (!conv_list.isEmpty()) {
			Conversation c = conv_list.get(0);
			if (c.isSelection()) {
				c.EVENT.passActionIntoSelectBox(tasklist_action);
				if (c.isSelection())
					return;
				else
					c.EVENT = null;
			}
			if (c.EVENT != null) {
				c.EVENT.workAfterPush();
				if (c.isSelection())
					return;
			}
			conv_list.remove(0);
			if (conv_list.isEmpty())
				Image_LargeCharacter.slideOUT_Conv_end();
			else
				conv_list.get(0).slideIN();
		} else {
			releaseLock__();
		}
		SE.SYSTEM_ENTER.play();
	}

	private void releaseLock__() {
		boolean flag = false;
		int i = 0;
		for (Line line : task) {
			if (line.push) {
				flag = true;
				break;
			}
			if (++i == COLS) {
				return;
			}
		}
		if (flag) {
			releaseLock_(i);
		}
	}

	private void remove() {
		Line line = (task.remove(0));
		if (line.group != record_switch) {
			record.add(HORIZON);
			record_switch = !record_switch;
		}
		if (record.size() >= RECORD) {
			record.remove(0);
		}
		record.add(record.lastIndexOf(HORIZON), line.MSG);
	}

	private void reset() {
		while (!task.isEmpty())
			remove();
		holding_time = 0;
		diff = 0;
		previous_color = null;
	}

	@Deprecated
	void set(MessageLock t) {
		set_non_statiic(t.FIRST_MSG);
		Line line = task.get(task.size() - 1);
		setPushTrue(line);
		line.task = t;
		for (Line l : task) {
			l.rapid = true;
		}
	};

	private void set_non_statiic(String[] strings) {
		concat();
		boolean push = false;
		StringBuilder sb = new StringBuilder();
		for (String s : strings) {
			if (s.endsWith("@")) {
				s = s.replaceAll("@", "");
				push = true;
			}
			sb.append(s);
		}
		Color prev_color = null;
		for (String s : StringFilter.getBreakSpacingStrings(
				NormalFont.NORMALFONT, sb.toString(), W)) {
			if (task.isEmpty() && push)
				SE.SYSTEM_ENTER.play();
			task.add(new Line((prev_color != null) ? prev_color.toString()
					.concat(s) : s));
			prev_color = StringFilter.getColor(null, s);
		}
		prev_color = null;
		if (push) {
			setPushTrue(task.get(task.size() - 1));
			for (Line line : task) {
				line.rapid = true;
			}
		}
		holding_time = 0;
	}

	@Deprecated
	private void set_non_statiic_OLD(String[] strings) {
		concat();
		StringBuilder sb = new StringBuilder();
		for (String s : strings)
			sb.append(s);
		for (String s : StringFilter.getBreakSpacingStrings(
				NormalFont.NORMALFONT, sb.toString(), W))
			task.add(new Line(s));
		holding_time = 0;
	}

	public void setConcatFlag() {
		for (Line line : task) {
			line.rapid = true;
		}
	}

	/**
	 * %で必要改行 $で絶対改行 「%」と「$」は表示不可能<br/>
	 * 行末「@」で入力待ち
	 * 
	 * @param array
	 */
	public void setMessage(String... array) {
		if (flag_concat && !isTaskEmpty()) {
			for (String str : array) {
				if (str.endsWith("@")) {
					message_task.get(message_task.size() - 1)
							.setFlagRapid(true);
					flag_concat = false;
					set(array);
					return;
				}
			}
			message_task.get(message_task.size() - 1).concat(array);
		} else {
			if (flag_turn_start && !isTaskEmpty()) {
				message_task.get(message_task.size() - 1).setFlagRapid(true);
			} else {
				flag_turn_start = false;
			}
			ArrayList<String> list = new ArrayList<String>(array.length);
			boolean flag = false;
			for (String str : array) {
				if (str.endsWith("@")) {
					// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					flag = true;
					list.add(str.substring(0, str.length() - 1));
					message_task.add(new MsgBlock(ilc, list));
					message_task.get(message_task.size() - 1).wait_for_pushing_any_key = true;
					list.clear();
					continue;
				}
				list.add(str);
			}
			if (!flag) {
				removeILC();
			}
			if (!list.isEmpty()) {
				message_task.add(new MsgBlock(ilc, list));
			}
		}
		flag_concat = true;
		if (message_task.get(message_task.size() - 1).wait_for_pushing_any_key) {
			flag_concat = false;
		}
	}

	private void setPushTrue(Line line) {
		line.push = true;
	}

	public void setTask_AfterReleaseDemandToPushEnter_non_static(
			final Task _task) {
		Line line = task.get(task.size() - 1);
		setPushTrue(line);
		line.task = new MessageLock() {
			@Override
			public void second() {
				_task.work();
			}
		};
		for (Line l : task) {
			l.rapid = true;
		}
	}

	private int speed() {
		int i = task.size() - COLS;
		if (i < 1) {
			i = 1;
		} else if (i < 5) {
			// if (task.get(0).rapid) {
			// i = 5;
			// }
		}
		double device;
		switch (i) {
		case 1:
		case 2:
		case 3:
			if (!Config.isHighSpeedMessage()) {
				device = 6;
				break;
			}
		case 4:
		case 5:
		case 6:
			device = 3;
			break;
		case 7:
		case 8:
			device = 2;
			break;
		default:
			device = 1.5;
			break;
		}
		return (int) (WINDOW_FRAME.getContentHeight() / device);
	}

}
