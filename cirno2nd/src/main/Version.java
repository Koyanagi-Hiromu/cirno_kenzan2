package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import main.constant.PropertySupporter;
import main.util.Show;
import title.Title;
import dangeon.model.config.Config;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.random.ミラクルクエスト;
import dangeon.model.map.field.random.二撃必殺;
import dangeon.model.map.field.random.救出大作戦;
import dangeon.model.map.field.random.緋想の地下;
import dangeon.model.map.field.random.逆ヶ島;
import dangeon.model.map.field.random.風穴旅行;
import dangeon.model.map.field.random.second.五色の神霊廟;
import dangeon.model.object.creature.player.save.ResultSaveLoad;
import dangeon.util.Switch;

public class Version {
	private class Rank implements Comparable<Rank> {
		final int prev_rank, floor, life, flag_clear, flag_item,
				flag_item_daichan;
		final long time;

		public Rank(int prev_rank, int index, int difficulty) {
			this.prev_rank = prev_rank;
			ResultSaveLoad rsl = ResultSaveLoad.staticLoad(
					ARR[index].getClassName(), prev_rank, difficulty);
			floor = rsl.FLOOR;
			String result = rsl.PLAYER.cause_of_death;
			String[] arr = { "大妖精を救出した", "無事、宝物を取り返した", "最深層" };
			boolean flag = false;
			for (String string : arr) {
				if (result.contains(string)) {
					flag = true;
				}
			}
			flag_clear = flag ? 1 : -1;
			//
			flag_item = rsl.PLAYER.flag_no_item ? 1 : -1;
			flag_item_daichan = rsl.PLAYER.flag_no_item_daichan ? 1 : -1;
			time = rsl.PLAYER.getPlayingMilliTime();
			life = rsl.LEFT_LIFE;
		}

		@Override
		public int compareTo(Rank o) {
			long dt = this.time - o.time;
			int t;
			if (dt > 0)
				t = -1;
			else if (dt < 0)
				t = 1;
			else
				t = 0;
			int c[] = { this.floor - o.floor, this.flag_clear - o.flag_clear,
					this.flag_item - o.flag_item,
					this.flag_item_daichan - o.flag_item_daichan,
					this.life - o.life, t, this.prev_rank - o.prev_rank };
			for (int i = 0; i < c.length; i++) {
				if (c[i] != 0) {
					return c[i] * -1;
				}
			}
			return 0;
		}
	}

	public final Title title;

	private final Base_Map_Random[] ARR = { new 救出大作戦(), new 逆ヶ島(),
			new 緋想の地下(), new 二撃必殺(), new 五色の神霊廟(), new ミラクルクエスト(), new 風穴旅行() };

	public Version(Title title) {
		System.out.println(Switch.test);
		this.title = title;
	}

	private ArrayList<Rank> createRankingList(PropertySupporter ps, int i,
			int difficulty) {

		ArrayList<Rank> list = new ArrayList<Rank>();
		for (int count = 1; count <= 30; count++) {
			if (ps.getProperty_Nature("r" + count).toString().matches("-1"))
				break;
			list.add(new Rank(count, i, difficulty));
		}

		Collections.sort(list);
		return list;
	}

	public boolean latestCheck() {
		final String key = "ver";
		Object o = Config.getValue(key);
		int ver;
		if (o == null)
			ver = -1;
		else
			ver = Integer.valueOf(o.toString());
		System.out.println(ver);
		if (ver < 121) {
			title.setMsg("リザルトのソートをしています");
			new Thread() {
				@Override
				public void run() {
					to121();
					save(121, key);
					latestCheck();
				};
			}.start();
			return true;
			// Show.showInformationMessageDialog("完了しました");
		} else if (ver < 200) {
			title.setMsg("リザルトのソートをしています");
			new Thread() {
				@Override
				public void run() {
					to200();
					save(200, key);
					title.startGame(true);
				}

			}.start();
			return true;
			// Show.showInformationMessageDialog("完了しました");
		}
		return false;
	}

	private void reconvert(PropertySupporter ps, int i, int difficulty) {

		int max = 1;
		for (; max <= 30; max++) {
			if (ps.getProperty_Nature("r" + max).toString().matches("-1"))
				break;
		}
		max--;

		for (int count = 0; count < max; count++) {
			String s = ResultSaveLoad.getURL(ARR[i].getClassName(), count + 1,
					difficulty);
			File f = new File(s + "_");
			if (f.exists())
				f.renameTo(new File(s));
			// else
			// Show.showNotOpenErrorMessageDialog(s);
		}
		/*
		 *
		 */
	}

	private void save(int i, String key) {
		Config.setValue(key, i);
	}

	private void savePr(PropertySupporter ps, int i, int difficulty,
			ArrayList<Rank> list) {
		for (int count = 1; count <= list.size(); count++) {
			Rank rank = list.get(count - 1);
			StringBuilder sb = new StringBuilder();
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
			ps.saveProperty("r" + count, sb.toString());
		}
	}

	private void sortFiles(PropertySupporter ps, int i, int difficulty,
			ArrayList<Rank> list) {
		for (int count = 0; count < list.size(); count++) {
			String s = ResultSaveLoad.getURL(ARR[i].getClassName(), count + 1,
					difficulty);
			File f = new File(s);
			if (f.exists())
				f.renameTo(new File(s + "_"));
			else
				Show.showNotOpenErrorMessageDialog(s);
		}
		for (int count = 0; count < list.size(); count++) {
			String s = ResultSaveLoad.getURL(ARR[i].getClassName(),
					list.get(count).prev_rank, difficulty);
			String new_file_name = ResultSaveLoad.getURL(ARR[i].getClassName(),
					count + 1, difficulty);
			File f = new File(s + "_");
			if (f.exists())
				f.renameTo(new File(new_file_name));
			else
				Show.showNotOpenErrorMessageDialog(s + "_");
		}
	}

	private void sortRanking(int i, int fate) {
		PropertySupporter ps;
		final int difficulty = ARR[i].getDIFFICULTY(fate).index;
		ps = ResultSaveLoad.getPropertySupporter(ARR[i].getClassName(),
				difficulty);
		if (ps.load_failed)
			return;
		// reconvert(ps, i, difficulty);
		ArrayList<Rank> list = createRankingList(ps, i, difficulty);
		sortFiles(ps, i, difficulty, list);
		savePr(ps, i, difficulty, list);
	}

	private void to121() {
		// ランキング調整
		double percent = 0;
		for (int i = 0; i < ARR.length; i++) {
			for (int fate = 0; fate < 3; fate++) {
				percent = (i * 3 + fate) / (ARR.length * 3.0) * 100;
				System.out.println(percent);
				title.setPercent((int) Math.round(percent));
				sortRanking(i, fate);
			}
		}
		title.setPercent(100);
	}

	private void to200() {
		// ミラクエ調整
		double percent = 0;
		Base_Map_Random bmr = new ミラクルクエスト();
		for (int fate = 0; fate < 3; fate++) {
			percent = (fate) / (3.0) * 100;
			title.setPercent((int) Math.round(percent));
			File f = new File(ResultSaveLoad.getRoot(bmr.getClassName(), fate));
			if (f.exists()) {
				File[] files = f.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
				f.delete();
			}
		}
		title.setPercent(100);
	}
}
