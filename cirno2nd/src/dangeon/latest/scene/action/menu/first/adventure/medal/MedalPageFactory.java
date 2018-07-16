package dangeon.latest.scene.action.menu.first.adventure.medal;

public class MedalPageFactory {
	public Base_MedalPage create(int index) {
		int page = index + 1;
		switch (page) {
		case 1:
			return new MedalPage_1();
		case 2:
			return new MedalPage_2();
		case 3:
			return new MedalPage_3();
		case 4:
			return new MedalPage_4();
		case 5:
			return new MedalPage_5();
		case 6:
			return new MedalPage_6();
		case 7:
			return new MedalPage_7();
		default:
			return new MedalPage_1();
		}
	}

	public int length() {
		return 7;
	}

}