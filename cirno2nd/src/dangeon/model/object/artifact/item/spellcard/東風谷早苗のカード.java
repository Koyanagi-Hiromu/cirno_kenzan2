package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.food.ミシャグジおにぎり;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.東風谷早苗;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class 東風谷早苗のカード extends SpellCard {
	//
	// private void call_sper_itemUseThis() {
	// super.itemUseThis();
	// }

	// @Override
	// public void itemUseThis() {
	// if (!isItemUseThisAvailable()) {
	// return;
	// }
	// TaskOnMapObject.setTaskMenuItem(new Base_MenuItem("どれを？", Belongings
	// .getListItems_except(this)) {
	// @Override
	// protected void pressedEnter(Base_Artifact _a) {
	// A = _a;
	// call_sper_itemUseThis();
	// end();
	// }
	// });
	// }

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String item_name = "東風谷早苗のカード";

	public static final String item_name_sound = "こちやさなえのかーと";
	private static final int composition = 4;
	private static final int item_str = 6;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;
	private static final String[] EXPLAN = new String[] { "周囲の的をおにぎりにする程度の能力" };

	public 東風谷早苗のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.祝;
	}

	private void effect() {
		int value = Belongings.getListItems().size();
		for (Base_Artifact a : Belongings.getListItems()) {
			if (Enchant.isEnchanted(a)) {
				Enchant.forceToRemove(a);
			}
		}
		Belongings.getListItems().clear();
		for (int i = 0; i <= value; i++) {
			Belongings
					.setItems(new 大きなおにぎり(new Point(Player.me.getMassPoint())));
		}
	}

	@Override
	public String getCharacterShortName() {
		return "早苗";
	}

	@Override
	public String getDoter() {
		return "ひろひろ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1225179";
	}

	@Override
	String getExplanToEnchant() {
		return "時々敵をおにぎりにするぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("お腹が減ったでござる");
	}

	@Override
	public String getIllustlator() {
		return "粗茶";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=10210";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 東風谷早苗.class;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		if (status == STATUS.STR) {
			return item_str;
		} else {
			return item_def;
		}
	}

	@Override
	protected boolean spellUse() {
		Base_Creature c = null;
		Point p = Player.me.getDirection().getFrontPoint(
				Player.me.getMassPoint().getLocation());
		for (Base_Creature c2 : MapList.getListCreature()) {
			if (c2.getMassPoint().equals(p)) {
				c = c2;
				break;
			}
		}
		if (c == null) {
			Message.set("「目の前に敵がいませんね」");
			return false;
		} else {
			TaskOnMapObject.addEnemyRemoveTask(c);
			Message.set("奇跡が起こって",
					c.getColoredName().concat("は").concat("おにぎりになった"));
			ItemFall.itemFall(c.getMassPoint(),
					new R().nextInt(2) == 0 ? new ミシャグジおにぎり(c.getMassPoint())
							: new 奇跡のおにぎり(c.getMassPoint()));
			SE.MIRACLE_ONIGIRI.play();
			return true;
		}
	}
}
