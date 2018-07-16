package cirno_question;

import cirno_question.ObjectNames.Enemy;
import cirno_question.ObjectNames.Item;
import dangeon.util.StringIsChangedToObject;

public class StartingRoader {
	public static StartingRoader ME = new StartingRoader();

	public void dotRoader() {
		for (String str : Enemy.monster) {
			QS.ENEMY_LIST.add(StringIsChangedToObject.returnBaseEnemy(str, 1,
					true));
		}
		for (String str : Item.card) {
			QS.CARD_LIST.add(StringIsChangedToObject.returnBaseItem(str,
					"spellcard", true));
		}
	}
}
