package dangeon.latest.scene.action.otog.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import main.constant.FR;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.Fader;
import dangeon.latest.scene.action.otog.object.Bar;
import dangeon.latest.scene.action.otog.object.TargetA;
import dangeon.latest.scene.action.otog.object.TargetA_Long;
import dangeon.latest.scene.action.otog.object.TargetD;
import dangeon.latest.scene.action.otog.value.ValueHolder;
import dangeon.model.config.Config;

public class Initializer {
	public int first_beats;
	public int end_mp3_frame;
	public final ValueHolder VH;
	public final int[] random_row = new int[4], random_dir = new int[8];

	public Initializer(ValueHolder vh) {
		VH = vh;
		ArrayList<Integer> list = new ArrayList<Integer>(4);
		ArrayList<Integer> list_dir = new ArrayList<Integer>(4);
		for (int i = 0; i < random_row.length; i++) {
			list.add(i);
			list_dir.add((i + 1) * 2);
		}
		if (VH.flag_random != 0)
			Collections.shuffle(list);
		if (VH.flag_random == 2)
			Collections.shuffle(list_dir);
		for (int i = 0; i < random_row.length; i++)
			random_row[i] = list.get(i) + 1;
		for (int i = 0; i < random_dir.length; i++) {
			if (i % 2 == 0) {
				random_dir[i] = i + 1;
			} else {
				random_dir[i] = list_dir.get(i / 2);
			}
		}
	}

	private void add(long mp3Frame, int row, int i, int bpm) {
		if (i == 0)
			return;
		if (row == 0)
			VH.list.add(new TargetD(DIRECTION.getFromNUM(random_dir[i - 1]),
					VH, mp3Frame));
		else {
			row = random_row[row - 1];
			if (Config.isOtogArrowRight())
				row--;
			if (i == 1)
				VH.list.add(new TargetA(row, VH, mp3Frame));
			else
				VH.list.add(new TargetA_Long(getMp3Frame(i, bpm), row, VH,
						mp3Frame));
		}
	}

	private void addBar(long mp3Frame) {
		VH.bars.add(new Bar(VH, mp3Frame));
	}

	public long game_start() {
		if (Config.getOtogFPS() == 60)
			FR.changeSleep(FR.THREAD_SLEEP_VERY_FAST);
		long sleep = vh();
		VH.end_mp3_frame = end_mp3_frame;
		VH.node_sum = VH.list.size();
		VH.HEAL_MILLI_RATE = 7605l * VH.node_sum / (VH.node_sum * 10 + 6500)
				* 1000;
		if (VH.HEAL_MILLI_RATE < 260000l) {
			VH.HEAL_MILLI_RATE = 260000l;
		}
		VH.HEAL_MILLI_RATE /= VH.node_sum;
		return sleep;
	}

	private long getMilliSecond(int beats, int bpm) {
		if (bpm == 0)
			return 0;
		return beats * 60 * 1000 / (bpm * 12);
	}

	private long getMp3Frame(int beats, int bpm) {
		long ms = getMilliSecond(beats, bpm);
		return VH.bgm.getMP3MilliFrame(ms);
	}

	/**
	 * 
	 * @param file
	 * @return first_sleep
	 */
	private long load(File file) {
		FileReader filereader;
		BufferedReader br = null;
		int bpm = 0;
		long first_milli_second = 0;
		final long first_mute = VH.bgm.getMP3MilliFrame(4000);
		// long first_mute = 4000 * 48000 / 1152;
		// 116666
		try {
			filereader = new FileReader(file);
			br = new BufferedReader(filereader);
			String line = br.readLine();
			long sum = 0l;
			int beats = 0;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",", false);
				if (st.hasMoreTokens()) {
					String s_bpm = st.nextToken();
					if (!s_bpm.matches("_")) {
						sum += getMp3Frame(beats, bpm);
						bpm = Integer.valueOf(s_bpm);
						if (first_milli_second == 0) {
							first_milli_second = getMilliSecond(beats, bpm);
							first_beats = beats;
						}
						beats = 0;
						addBar(first_mute + sum);
					} else {
						int row = 0;
						while (st.hasMoreTokens()) {
							int i = Integer.valueOf(st.nextToken());
							add(first_mute + sum + getMp3Frame(beats, bpm),
									row++, i, bpm);
						}
						beats++;
					}
				}
			}
			sum += getMp3Frame(beats, bpm);
			end_mp3_frame = (int) ((first_mute + sum + 500) / 1000);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ロードに失敗ました");
			new Fader(VH.MAIN_PANEL.PARENT,
					VH.MAIN_PANEL.PARENT.getNextSelectScene());
			return -1;
		}
		if (br != null)
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return first_milli_second;
	}

	private long vh() {
		VH.list.clear();
		VH.bars.clear();
		VH.objects.clear();
		return load(VH.CONTENT.FILE);
	}
}
