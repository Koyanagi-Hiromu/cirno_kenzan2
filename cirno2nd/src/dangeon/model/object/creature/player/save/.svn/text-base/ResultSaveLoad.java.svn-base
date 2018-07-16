package dangeon.model.object.creature.player.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import main.constant.PropertySupporter;
import main.util.Show;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.Base_Map_Random.Difficulty;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class ResultSaveLoad implements Serializable {
	public class Rank {
		final int floor, life, flag_clear, flag_item, flag_item_daichan;
		final long time;

		public Rank() {
			floor = FLOOR;
			String result = PLAYER.cause_of_death;
			flag_clear = Player.flag_clear ? 1 : -1;
			flag_item = PLAYER.flag_no_item ? 1 : -1;
			flag_item_daichan = PLAYER.flag_no_item_daichan ? 1 : -1;
			time = PLAYER.getPlayingMilliTime();
			life = LEFT_LIFE;
		}

		public int compareTo(int floor, int flag_clear, int flag_item,
				int flag_item_daichan, int life, long time) {
			long dt = this.time - time;
			int t;
			if (dt > 0)
				t = -1;
			else if (dt < 0)
				t = 1;
			else
				t = 0;
			int c[] = { this.floor - floor, this.flag_clear - flag_clear,
					this.flag_item - flag_item,
					this.flag_item_daichan - flag_item_daichan,
					this.life - life, t };
			for (int i = 0; i < c.length; i++) {
				if (c[i] != 0) {
					return c[i] * -1;
				}
			}
			return 0;
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static String getNameDifficulty(Difficulty difficulty) {
		return "_".concat(difficulty.name());
	}

	public static String getNameDifficulty(int difficulty) {
		return getNameDifficulty(Difficulty.getFromIndex(difficulty));
	}

	public static String getRoot(String fileld_name, int difficulty) {
		StringBuilder sb = new StringBuilder();
		sb.append("save/");
		sb.append(fileld_name);
		if (difficulty >= 0) {
			sb.append(getNameDifficulty(difficulty));
		}
		return sb.toString();
	}

	public static String getURL(String fileld_name, int index, int difficulty) {
		StringBuilder sb = new StringBuilder();
		sb.append(getRoot(fileld_name, difficulty));
		sb.append("/");
		sb.append(index);
		sb.append(".save");
		return sb.toString();
	}

	public static ResultSaveLoad staticLoad(String fileld_name, int index,
			int difficulty) {
		String url = getURL(fileld_name, index, difficulty);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(url));
			ResultSaveLoad me = ((ResultSaveLoad) ois.readObject());
			ois.close();
			if (me.PLAYER.hash_restriction == null) {
				me.PLAYER.hash_restriction = new HashMap<Enchant, Boolean>();
			}
			return me;
		} catch (Exception e) {
			System.out.println(fileld_name);
			System.out.println(index);
			System.out.println(difficulty);
			System.out.println(url);
			e.printStackTrace();
			Show.showInformationMessageDialog("セーブファイルのデータが壊れています。");
		}
		return null;
	}

	public final int ATK, DEF;

	public final int DIFFICULTY;
	public final Player PLAYER;
	public final ArrayList<Base_Artifact> ITEMS;
	public final Base_Artifact[] ENCHANT_ARTIFACT;
	public final String FIELD_NAME;

	public final int FLOOR;

	public final String[] RECORD;

	public final int SCORE, RANK, LEFT_LIFE;
	public final int SAVE_INDEX;
	public final static int MAX_RECORD = 30;

	private static PropertySupporter ps;

	public static PropertySupporter getPropertySupporter(String name,
			int difficulty) {
		return new PropertySupporter(new File(getRoot(name, difficulty)));
	}

	/**
	 * インスタンスを生成するだけでセーブされる。
	 */
	public ResultSaveLoad(Base_Map_Random bmr) {
		this(bmr, true);
	}

	public ResultSaveLoad(Base_Map_Random bmr, boolean save) {
		Player.me.finshSecondAnimation();
		PLAYER = Player.me;
		LEFT_LIFE = Config.getRetryNumber() - 1;
		if (bmr == null) {
			FIELD_NAME = null;
			DIFFICULTY = -1;
		} else if (Config.isHack_playing()) {
			String name = MassCreater.getHackName();
			FIELD_NAME = "[".concat(name.substring(0, 1)).concat("] ")
					.concat(name.substring(1));
			DIFFICULTY = bmr.getDIFFICULTY().index;
		} else {
			FIELD_NAME = bmr.getClassName();
			DIFFICULTY = bmr.getDIFFICULTY().index;
		}
		FLOOR = MapList.getFloor();
		ITEMS = Belongings.getListItems();
		for (Base_Artifact a : ITEMS) {
			a.check();
		}
		ENCHANT_ARTIFACT = Enchant.savePresentEnchant();
		RECORD = Message.ME.getRecordToArray();
		if (RECORD.length > 0) {
			RECORD[RECORD.length - 1] = "";
		}
		ATK = Enchant.getSumSTR();
		DEF = Enchant.getSumDEF();
		int i = 1;
		if (!Config.isHack_playing()) {
			try {
				ps = new PropertySupporter(new File(getRoot(FIELD_NAME,
						DIFFICULTY)));
				Rank rank = new Rank();
				for (; i < MAX_RECORD + 1;) {
					if (compare(ps.getProperty_Nature(getKey(i)), rank))
						break;
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RANK = i;
		SAVE_INDEX = Config.getSaveIndex();
		SCORE = RANK * 1000;
		if (save) {
			save();
		}
	}

	private boolean compare(Object object, Rank rank) {
		if (object == null)
			return true;
		String[] arr = object.toString().split(",");
		if (arr.length == 1)
			return true;
		int[] arr_i = new int[arr.length - 1];
		for (int i = 0; i < arr_i.length; i++) {
			arr_i[i] = Integer.valueOf(arr[i]);
		}
		int i = rank.compareTo(arr_i[0], arr_i[1], arr_i[2], arr_i[3],
				arr_i[4], Long.valueOf(arr[arr.length - 1]));
		return i <= 0;
	}

	private void createConfig(File root) throws IOException {
		File newfile = new File(root + "/config.properties");
		newfile.createNewFile();
		ps = new PropertySupporter(root);
		for (int i = 1; i < MAX_RECORD + 1; i++) {
			ps.saveProperty(getKey(i), -1);
		}
	}

	private File getFile(int i) {
		return new File(getURL(FIELD_NAME, i, DIFFICULTY));
	}

	private String getKey(int i) {
		return "r".concat(String.valueOf(i));
	}

	private File getTemporaryFile(int i) {
		return new File(getURL(".", i, -1));
	}

	private void save() {
		SaveLoad.delete();
		if (FIELD_NAME == null) {
			// ダンジョン外で倒れた
			return;
		}
		try {
			if (Config.isHack_playing()) {
				int max = 10;
				ObjectOutputStream oos;
				File file = getTemporaryFile(max);
				file.createNewFile();
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(this);
				oos.close();
				File f;
				f = getTemporaryFile(max - 1);
				if (f.exists()) {
					f.delete();
				}
				for (int i = max - 2; i >= 0; i--) {
					f = getTemporaryFile(i);
					if (f.exists()) {
						f.renameTo(getTemporaryFile(i + 1));
					}
				}
				file.renameTo(getTemporaryFile(0));
			} else {
				ObjectOutputStream oos;
				File root = new File(getRoot(FIELD_NAME, DIFFICULTY));
				if (!root.exists() && !root.mkdir()) {
					Show.showErrorMessageDialog("ディレクトリの作成に失敗しました\nリザルトデータは破棄されます");
					return;
				}
				if (!new File(root + "/config.properties").exists()) {
					createConfig(root);
				}
				if (RANK <= MAX_RECORD) {
					File file = getFile(0);
					file.createNewFile();
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(this);
					oos.close();
					File f;
					f = getFile(MAX_RECORD);
					if (f.exists()) {
						f.delete();
					}
					for (int i = MAX_RECORD; i > RANK; i--) {
						f = getFile(i - 1);
						if (f.exists()) {
							f.renameTo(getFile(i));
						}
						System.out.println(i);
						ps.saveProperty(getKey(i),
								ps.getProperty_Nature(getKey(i - 1)));
					}
					file.renameTo(getFile(RANK));
					// ps.saveProperty(getKey(RANK), FLOOR);
					StringBuilder sb = new StringBuilder();
					Rank rank = new Rank();
					sb.append(rank.floor);
					sb.append(",");
					sb.append(rank.flag_clear);
					sb.append(",");
					sb.append(rank.flag_item);
					sb.append(",");
					sb.append(rank.flag_item_daichan);
					sb.append(",");
					sb.append(rank.life);
					sb.append(",");
					sb.append(rank.time);
					ps.saveProperty(getKey(RANK), sb.toString());
				}
			}

		} catch (Exception e) {
			Show.showErrorMessageDialog(e);
		}
	}
}
