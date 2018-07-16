package dangeon.model.object.creature.player.save;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.WriteAbortedException;
import java.util.List;

import main.res.BGM;
import main.util.BlackOut;
import main.util.Show;
import title.Title;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.check.Checker_ClassName;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.EnchantArrow;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class SaveLoad implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 2L;
	public final Player PLAYER;
	public final List<Base_Artifact> ITEMS;
	public final Base_Artifact[] ENCHANT_ARTIFACT;
	public final Integer ENCHANT_ARROW;
	public final Base_Map BASE_MAP;
	public final MapList MAP_LIST;
	public final MassCreater MASS_CREATER;
	public final String HACK_NAME;
	public final Checker_ClassName<Checker> CHECKER;
	public final boolean ITEM_TEMP;

	private static long time = -1;

	public static void delete() {
		delete(false);
	}

	public static void delete(boolean temp) {
		File file = new File(getSaveURL(temp));
		try {
			int max = 99;
			int min = 1;
			int i = max;
			if (temp) {
				file.delete();
				new File(getSaveURL(temp)).createNewFile();
			} else {
				for (; i >= min; i--) {
					File f = new File(getRenameString(temp, i));
					if (f.exists()) {
						if (i == max)
							f.delete();
						else
							f.renameTo(new File(getRenameString(temp, i + 1)));
					}
				}
				file.renameTo(new File(getRenameString(temp, min)));
				new File(getSaveURL(temp)).createNewFile();
			}
			Config.release();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getRenameString(boolean temp, int i) {
		return getSaveURL(temp).concat("__temp__" + (i) + "__");
	}

	public static SaveLoad getSaveLoad(boolean temp) {
		String url = getSaveURL(temp);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(url));
			SaveLoad s = ((SaveLoad) ois.readObject());
			ois.close();
			return s;
		} catch (EOFException e) {
			// 初めての冒険
		} catch (FileNotFoundException e) {
			// 初めての冒険？
			// Show.showInformationMessageDialog("セーブファイルが見つかりません。新しく冒険を開始します。");
		} catch (InvalidClassException e) {
			// serialVersionUID の不一致
			Message.set("セーブデータが現在の形式と一致しなかったので新しく冒険を開始しました@");
		} catch (WriteAbortedException e) {
			// serialVersionUID の書き損じ？
			Message.set("セーブデータが現在の形式と一致しなかったので新しく冒険を開始しました@");
		} catch (Exception e) {
			Show.writeErrorText(e);
			Message.set("セーブデータが何かしらの原因で読み込めなかったので新しく冒険を開始しました@");
			Message.set("お手数ですが公式掲示板に、現在のバージョンと書き出されたerror.txtの内容を送っていただけると助かります@");
		}
		return null;
	}

	private static String getSaveURL(boolean temp) {
		StringBuilder sb = new StringBuilder();
		sb.append("save/");
		sb.append(Config.getSaveIndex());
		sb.append("/");
		if (temp)
			sb.append("temp");
		else
			sb.append(Config.getSaveIndex());
		sb.append(".save");
		return sb.toString();
	}

	public static void initTime() {
		time = System.nanoTime();
	}

	public static boolean staticLoad() {
		return staticLoad(false);
	}

	private static boolean staticLoad(boolean temp) {
		SaveLoad s = getSaveLoad(temp);
		if (s == null) {
			return false;
		} else {
			s.load();
			return true;
		}
	}

	public static List<Base_Artifact> staticLoad_SaisenBox() {
		SaveLoad s = getSaveLoad(true);
		if (s == null)
			return null;
		delete(true);
		return s.ITEMS;
	}

	public static boolean staticTempLoad() {
		return staticLoad(true);
	}

	public static void time_save() {
		if (time != -1) {
			Config.save((System.nanoTime() - time) / 1000000000);
		}
		time = System.nanoTime();
	}

	/**
	 * インスタンスを生成するだけではセーブされない。 直後にsave()メソッドを忘れずに呼ぶこと
	 * 
	 */
	public SaveLoad() {
		ITEM_TEMP = true;
		PLAYER = null;
		HACK_NAME = null;
		ITEMS = Belongings.getListItems();
		ENCHANT_ARTIFACT = null;
		ENCHANT_ARROW = null;
		BASE_MAP = null;
		MAP_LIST = null;
		MASS_CREATER = null;
		CHECKER = null;
	}

	/**
	 * インスタンスを生成するだけではセーブされない。 直後にsave()メソッドを忘れずに呼ぶこと
	 * 
	 * @param MC
	 */
	public SaveLoad(MassCreater MC) {
		ITEM_TEMP = false;
		PLAYER = Player.me;
		HACK_NAME = MassCreater.getHackName();
		ITEMS = Belongings.getListItems();
		ENCHANT_ARTIFACT = Enchant.savePresentEnchant();
		ENCHANT_ARROW = EnchantArrow.saveArrowEnchant();
		BASE_MAP = PresentField.get();
		MAP_LIST = new MapList(MapList.ME);
		MASS_CREATER = MC;
		CHECKER = Checker.saveHash();
	}

	private void load() {
		if (ITEM_TEMP) {
			Belongings.allRemove();
			Belongings.setListItems(ITEMS);
		} else {
			loadRegularly();
		}
	}

	private void loadRegularly() {
		Player.load(PLAYER);
		PLAYER.endDamaging();
		MassCreater.setHackName(HACK_NAME);
		Checker.load(CHECKER);
		Belongings.setListItems(ITEMS);
		Enchant.loadPresentEnchant(ENCHANT_ARTIFACT);
		EnchantArrow.loadArrowEnchant(ENCHANT_ARROW);
		PresentField.setPresentField(BASE_MAP);
		if (BASE_MAP.isDungeon()) {
			// ItemTable.testCreate(PresentField.get().getClassName());
			MASS_CREATER.createItemTable();
		} else {
			MASS_CREATER.createItemTableNoDungeon();
		}
		MapList.load(MAP_LIST);
		if (MASS_CREATER.MOVE_BY_STEP) {
			if (BASE_MAP.isDungeon()) {
				MapList.nextFloor();
			}
		}
		new BlackOut(BASE_MAP, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				delete();
				Scene_Action.getMe().setNextScene(Scene_Action.getMe());
				R.setFix(Config.getRandomSeed());
				MassCreater.load(MASS_CREATER);
				// boolean flag = false;
				// while (flag =
				// MapList.checkSpecialRoom(Player.me.getMassPoint())
				// || flag)
				// ;
				if (!BGM.isSpecialBGMplaying()) {
					System.out.println(BGM.get());
					// 開幕モンハウの時
					BGM bgm = BASE_MAP.getBGM();
					if (bgm != null) {
						bgm.play();
					}
				} else {
					System.out.println(BGM.get());
				}
			}
		});

	}

	private void save(boolean temp) {
		try {
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(new FileOutputStream(getSaveURL(temp)));
			oos.writeObject(this);
			oos.close();
			time_save();
		} catch (Exception e) {
			Show.showCriticalErrorMessageDialog(e);
		}
	}

	public void saveContinue() {
		save(false);
	}

	public void saveEnd() {
		save(false);
		BGM.waitUntilFadeOut_Thread();
		if (BASE_MAP.isDungeon()) {
			Message.set("セーブに成功しました。$次の階層から始まります。@");
		} else {
			Message.set("セーブに成功しました。$アイテムを持ったまま始まります。@");
		}
		Message.setTask_AfterReleaseDemandToPushEnter(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				new BlackOut("", new Task() {
					/**
					 *
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						Scene_Action.getMe().setNextScene(
								new Title(Scene_Action.getMe().KH));
					}
				});
			}
		});
	}

	public void saveItemsFirst() {
		save(true);
	}
}
