package dangeon.model.object.creature.player;

import java.util.HashMap;

public class EXP {
	public static final EXP table = new EXP();
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	private int lv1 = 0, lv2 = 10, lv3 = 30, lv4 = 60, lv5 = 100, lv6 = 160,
			lv7 = 250, lv8 = 370, lv9 = 530, lv10 = 730, lv11 = 970,
			lv12 = 1300, lv13 = 1600, lv14 = 2000, lv15 = 2400, lv16 = 2900,
			lv17 = 3500, lv18 = 4200, lv19 = 5000, lv20 = 6000, lv21 = 7000,
			lv22 = 8000, lv23 = 10000, lv24 = 13000, lv25 = 17000,
			lv26 = 22000, lv27 = 28000, lv28 = 35000, lv29 = 43000,
			lv30 = 52000, lv31 = 62000, lv32 = 73000, lv33 = 85000,
			lv34 = 100000, lv35 = 120000, lv36 = 150000, lv37 = 180000,
			lv38 = 210000, lv39 = 240000, lv40 = 270000, lv41 = 300000,
			lv42 = 350000, lv43 = 400000, lv44 = 450000, lv45 = 500000,
			lv46 = 550000, lv47 = 600000, lv48 = 650000, lv49 = 700000,
			lv50 = 750000, lv51 = 800000, lv52 = 850000, lv53 = 900000,
			lv54 = 950000, lv55 = 1000000, lv56 = 1050000, lv57 = 1100000,
			lv58 = 1150000, lv59 = 1200000, lv60 = 1250000, lv61 = 1310000,
			lv62 = 1370000, lv63 = 1430000, lv64 = 1490000, lv65 = 1550000,
			lv66 = 1610000, lv67 = 1670000, lv68 = 1730000, lv69 = 1790000,
			lv70 = 1850000, lv71 = 1910000, lv72 = 1970000, lv73 = 2030000,
			lv74 = 2090000, lv75 = 2150000, lv76 = 2210000, lv77 = 2270000,
			lv78 = 2330000, lv79 = 2390000, lv80 = 2450000, lv81 = 2510000,
			lv82 = 2570000, lv83 = 2630000, lv84 = 2690000, lv85 = 2750000,
			lv86 = 2810000, lv87 = 2870000, lv88 = 2930000, lv89 = 2990000,
			lv90 = 3050000, lv91 = 3110000, lv92 = 3170000, lv93 = 3230000,
			lv94 = 3290000, lv95 = 3350000, lv96 = 3410000, lv97 = 3470000,
			lv98 = 3530000, lv99 = 4000000;

	public EXP() {
		map.put(1, lv1);
		map.put(2, lv2);
		map.put(3, lv3);
		map.put(4, lv4);
		map.put(5, lv5);
		map.put(6, lv6);
		map.put(7, lv7);
		map.put(8, lv8);
		map.put(9, lv9);
		map.put(10, lv10);
		map.put(11, lv11);
		map.put(12, lv12);
		map.put(13, lv13);
		map.put(14, lv14);
		map.put(15, lv15);
		map.put(16, lv16);
		map.put(17, lv17);
		map.put(18, lv18);
		map.put(19, lv19);
		map.put(20, lv20);
		map.put(21, lv21);
		map.put(22, lv22);
		map.put(23, lv23);
		map.put(24, lv24);
		map.put(25, lv25);
		map.put(26, lv26);
		map.put(27, lv27);
		map.put(28, lv28);
		map.put(29, lv29);
		map.put(30, lv30);
		map.put(31, lv31);
		map.put(32, lv32);
		map.put(33, lv33);
		map.put(34, lv34);
		map.put(35, lv35);
		map.put(36, lv36);
		map.put(37, lv37);
		map.put(38, lv38);
		map.put(39, lv39);
		map.put(40, lv40);
		map.put(41, lv41);
		map.put(42, lv42);
		map.put(43, lv43);
		map.put(44, lv44);
		map.put(45, lv45);
		map.put(46, lv46);
		map.put(47, lv47);
		map.put(48, lv48);
		map.put(49, lv49);
		map.put(50, lv50);
		map.put(51, lv51);
		map.put(52, lv52);
		map.put(53, lv53);
		map.put(54, lv54);
		map.put(55, lv55);
		map.put(56, lv56);
		map.put(57, lv57);
		map.put(58, lv58);
		map.put(59, lv59);
		map.put(60, lv60);
		map.put(61, lv61);
		map.put(62, lv62);
		map.put(63, lv63);
		map.put(63, lv63);
		map.put(64, lv64);
		map.put(65, lv65);
		map.put(66, lv66);
		map.put(67, lv67);
		map.put(68, lv68);
		map.put(69, lv69);
		map.put(70, lv70);
		map.put(71, lv71);
		map.put(72, lv72);
		map.put(73, lv73);
		map.put(74, lv74);
		map.put(75, lv75);
		map.put(76, lv76);
		map.put(77, lv77);
		map.put(78, lv78);
		map.put(79, lv79);
		map.put(80, lv80);
		map.put(81, lv81);
		map.put(82, lv82);
		map.put(83, lv83);
		map.put(84, lv84);
		map.put(85, lv85);
		map.put(86, lv86);
		map.put(87, lv87);
		map.put(88, lv88);
		map.put(89, lv89);
		map.put(90, lv90);
		map.put(91, lv91);
		map.put(92, lv92);
		map.put(93, lv93);
		map.put(94, lv94);
		map.put(95, lv95);
		map.put(96, lv96);
		map.put(97, lv97);
		map.put(98, lv98);
		map.put(99, lv99);
	}

	public int level(int exp) {
		int i = 1;
		for (i = Player.me.getLV() + 1; i <= 99; i++) {
			if (exp < map.get(i)) {
				return i - 1;
			}
		}
		return i - 1;
	}

	public int levelDownExp() {
		return map.get(Player.me.getLV() + 1) - 1;
	}

	public void setExp() {
		Player.me.setPlayerExp(map.get(Player.me.getLV()));
	}
}
