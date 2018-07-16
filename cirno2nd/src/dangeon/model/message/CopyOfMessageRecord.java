package dangeon.model.message;

public class CopyOfMessageRecord {
	// public static final int MAX_RECORD = 100;
	// private static final ArrayList<String> message_record = new
	// ArrayList<String>(
	// MAX_RECORD);
	// private static final ArrayList<MsgBlock> message_task = new
	// ArrayList<MsgBlock>();
	// private static boolean flag_concat;
	// private static boolean flag_turn_start = false;
	// private static ArrayList<Task> task_list = new ArrayList<Task>();
	//
	// public static boolean isTaskEmpty() {
	// return message_task.isEmpty();
	// }
	//
	// public static MsgBlock getTask() {
	// return message_task.get(0);
	// }
	//
	// public static int getRecordSize() {
	// return message_record.size();
	// }
	//
	// public static String[] getRecord() {
	// return message_record.toArray(new String[0]);
	// }
	//
	// public static String getRecord(int i) {
	// return message_record.get(i);
	// }
	//
	// public static void setRecord(String... arr) {
	// for (String str : arr) {
	// if (message_record.size() == MAX_RECORD) {
	// message_record.remove(0);
	// }
	// message_record.add(str);
	// }
	// if (message_record.size() == MAX_RECORD) {
	// message_record.remove(0);
	// }
	// message_record.add("　　　　　　　　▼");
	// }
	//
	// public static void clearRecord() {
	// message_record.clear();
	// message_record.add("");
	// message_record.add("");
	// }
	//
	// static void endTask() {
	// MsgBlock mb = message_task.get(0);
	// if (mb.isSlept) {
	// Player.flag_clear = false;
	// }
	// setRecord(mb.getMsg().toArray(new String[0]));
	// if (!mb.TASK_LIST.isEmpty()) {
	// for (Task task : mb.TASK_LIST) {
	// task.work();
	// }
	// mb.TASK_LIST.clear();
	// }
	// message_task.remove(0);
	// }
	//
	// public static boolean isNextTaskEmpty() {
	// return message_task.size() == 1;
	// }
	//
	// public static void setConcatFlag(boolean b) {
	// flag_concat = b;
	// }
	//
	// public static boolean isDemandToWaitAnySystem() {
	// if (isTaskEmpty()) {
	// return false;
	// }
	// if (!isNextTaskEmpty()) {
	// return true;
	// }
	// return !getTask().isLast();
	// }
	//
	// public static boolean isDemandToWaitPushingAnyKey() {
	// if (isTaskEmpty()) {
	// return false;
	// }
	// return getTask().wait_for_pushing_any_key;
	// }
	//
	// public static void releaseRockWaitPushingAnyKey() {
	// if (getTask().isLast()) {
	// SE.SYSTEM_ENTER.play();
	// getTask().wait_for_pushing_any_key = false;
	// endTask();
	// if (message_task.isEmpty()
	// || !message_task.get(0).wait_for_pushing_any_key) {
	// ArrayList<Task> list = new ArrayList<Task>(task_list.size());
	// for (Task task : task_list) {
	// list.add(task);
	// }
	// for (Iterator<Task> iterator = list.iterator(); iterator
	// .hasNext();) {
	// Task task = iterator.next();
	// task.work();
	// task_list.remove(task);
	// }
	// }
	// }
	// }
	//
	// public static MsgBlock getNextTask() {
	// return message_task.get(1);
	// }
	//
	// public static void setTurnStartFlag(boolean b) {
	// flag_turn_start = b;
	// }
	//
	// public static void setMessageTask(Task task_work_at_complete_apperance,
	// String... array) {
	// setMessageTask(array);
	// if (task_work_at_complete_apperance != null) {
	// message_task.get(message_task.size() - 1).setTask(
	// task_work_at_complete_apperance);
	// }
	// }
	//
	// private static Image_LargeCharacter ilc = Image_LargeCharacter.ANY;;
	//
	// /**
	// *
	// * @param im_lc
	// * 「@」で文章待機中のときに表示するイメージを指定<br/>
	// * 解除はnullを指定すること
	// */
	// public static void setImageLargeCharacter(Image_LargeCharacter im_lc) {
	// if (im_lc == null) {
	// ilc = Image_LargeCharacter.ANY;
	// } else {
	// ilc = im_lc;
	// }
	// }
	//
	// public static void removeILC() {
	// ilc = Image_LargeCharacter.ANY;
	// }
	//
	// public static void setMessageTask_AfterSleepDeamandKeyPush(int sleep,
	// String... array) {
	// ArrayList<String> list = new ArrayList<String>(array.length);
	// boolean flag = false;
	// for (String str : array) {
	// list.add(str);
	// }
	// if (!flag) {
	// removeILC();
	// }
	// if (!list.isEmpty()) {
	// message_task.add(new MsgBlock(ilc, list, sleep));
	// }
	//
	// }
	//
	// /**
	// * %で必要改行 $で絶対改行 「%」と「$」は表示不可能<br/>
	// * 行末「@」で入力待ち
	// *
	// * @param array
	// */
	// public static void setMessageTask(String... array) {
	// if (flag_concat && !isTaskEmpty()) {
	// System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbb   " + array[0]);
	// for (String str : array) {
	// if (str.endsWith("@")) {
	// message_task.get(message_task.size() - 1)
	// .setFlagRapid(true);
	// flag_concat = false;
	// setMessageTask(array);
	// return;
	// }
	// }
	// message_task.get(message_task.size() - 1).concat(array);
	// } else {
	// if (flag_turn_start && !isTaskEmpty()) {
	// message_task.get(message_task.size() - 1).setFlagRapid(true);
	// } else {
	// flag_turn_start = false;
	// }
	// ArrayList<String> list = new ArrayList<String>(array.length);
	// boolean flag = false;
	// for (String str : array) {
	// if (str.endsWith("@")) {
	// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	// flag = true;
	// list.add(str.substring(0, str.length() - 1));
	// message_task.add(new MsgBlock(ilc, list));
	// message_task.get(message_task.size() - 1).wait_for_pushing_any_key =
	// true;
	// list.clear();
	// continue;
	// }
	// list.add(str);
	// }
	// if (!flag) {
	// removeILC();
	// }
	// if (!list.isEmpty()) {
	// message_task.add(new MsgBlock(ilc, list));
	// }
	// }
	// flag_concat = true;
	// if (message_task.get(message_task.size() - 1).wait_for_pushing_any_key) {
	// flag_concat = false;
	// }
	// }
	//
	// public static void setTask_AfterReleaseDemandToPushEnter(Task task) {
	// task_list.add(task);
	// }
	//
	// public static boolean isDemandToWaitPushingAnyKey_ForDrawer() {
	// return isDemandToWaitPushingAnyKey() && getTask().sleep == 0;
	// }

}
