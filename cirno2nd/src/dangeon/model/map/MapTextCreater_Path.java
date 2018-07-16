package dangeon.model.map;

import java.util.ArrayList;
import java.util.List;

import dangeon.util.R;

public class MapTextCreater_Path {

	private class Finish extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static boolean[][] checked_path;
	static char[][] mass;
	static int end_x, end_y;
	private boolean[][] path;
	private boolean canCheck = true;
	private static List<MapTextCreater_Path> task = new ArrayList<MapTextCreater_Path>();
	static int W_MAX, H_MAX;
	static int count;

	static boolean triggerCheck(int x, int y) {
		try {
			new MapTextCreater_Path(x, y, MapTextCreater_Path.checked_path);
		} catch (Finish e) {
			// 最短経路
			return true;
		}
		for (int i = 0; i < 3; i++)
			rnd[i] = new R().nextInt(4 - i);
		count = 0;
		try {
			while (true) {
				if (task.isEmpty())
					break;
				count++;
				MapTextCreater_Path[] _task = task
						.toArray(new MapTextCreater_Path[0]);
				for (MapTextCreater_Path m : _task)
					m.doCheck();
				MapTextCreater.jf.repaint();
			}
		} catch (Finish e) {
			task.clear();
			return true;
		}
		return false;
	}

	private int x, y;
	private static int rnd[] = new int[4];

	MapTextCreater_Path(int x, int y, boolean[][] p) throws Finish {
		if (x == end_x && y == end_y)
			end(p);
		if (!checkInnerMap(x, y)) {
			canCheck = false;
			return;
		}
		if (checked_path[x][y])
			canCheck = false;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				checkMass(x + i - 1, y + j - 1);
		if (!canCheck)
			return;
		setPath(p);
		checked_path[x][y] = true;
		path[x][y] = true;
		this.x = x;
		this.y = y;
		task.add(this);
	}

	private boolean checkInnerMap(int x, int y) {
		return 1 <= x && x < W_MAX && 1 <= y && y < H_MAX;
	}

	private void checkMass(int x, int y) {
		try {
			if (mass[x][y] == MapTextCreater.ROOM)
				canCheck = false;
			if (mass[x][y] == MapTextCreater.PATH)
				canCheck = false;
		} catch (ArrayIndexOutOfBoundsException e) {
			// むしするー
		}
	}

	private void doCheck() throws Finish {
		if (!canCheck)
			return;
		boolean flag[] = { false, false, false, false };
		/*
		 * int n=4; while(n>0){ int rnd,index; rnd = new A().nextInt(n); index =
		 * rnd; for(int i=0;i<rnd;i++){ if(flag[i]) index++; }
		 * while(flag[index]){ index++; } index%=4; flag[index]=true; n--;
		 * if(index==0) new MapCreater_Path(x+1, y, path); else if(index==1) new
		 * MapCreater_Path(x-1, y, path); else if(index==2) new
		 * MapCreater_Path(x, y+1, path); else if(index==3) new
		 * MapCreater_Path(x, y-1, path); }
		 */
		int index;
		for (int i = 0; i < 4; i++) {
			index = rnd[i];
			for (int j = 0; j < index; j++)
				if (flag[j])
					index++;
			while (flag[index])
				index++;
			index %= 4;
			flag[index] = true;
			if (index == 0)
				new MapTextCreater_Path(x + 1, y, path);
			else if (index == 1)
				new MapTextCreater_Path(x - 1, y, path);
			else if (index == 2)
				new MapTextCreater_Path(x, y + 1, path);
			else if (index == 3)
				new MapTextCreater_Path(x, y - 1, path);

		}
		task.remove(this);
	}

	private void end(boolean[][] p) throws Finish {
		for (int j = 0; j < p[0].length; j++) {
			for (int i = 0; i < p.length; i++) {
				// System.out.print((p[i][j])? "■": "□");
				if (p[i][j])
					mass[i][j] = MapTextCreater.PATH;
			}
		}
		MapTextCreater.jf.repaint();
		throw new Finish();
	}

	private void setPath(boolean[][] p) {
		path = new boolean[p.length][p[0].length];
		for (int i = 0; i < path.length; i++)
			for (int j = 0; j < path[i].length; j++)
				path[i][j] = p[i][j];
	}
}
