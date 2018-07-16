package dangeon.model.object.artifact.item.spellcard.double_card;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.MarisaSpark;

public class DoubleCard extends Base_Item {

	/**
	 *
	 */
	private static final long serialVersionUID = -97526840866347523L;

	private static String doubleSpell(SpellCard s1, SpellCard s2) {
		return s1.getCharacterShortName().concat("と")
				.concat(s2.getCharacterShortName()).concat("のカード");
	}

	private SpellCard first;
	private SpellCard second;

	public DoubleCard(Point p, SpellCard sc, SpellCard sc2) {
		super(p, doubleSpell(sc, sc2), 0, ITEM_CASE.SPELLCARD);
		first = sc;
		second = sc2;
		setForgeValue();
	}

	@Override
	protected int enchantDefence(boolean b, Base_Creature creature, int damage) {
		return 0;
	}

	@Override
	protected String[] getExplan() {
		String[] str = new String[2];
		str[0] = first.getDoubleExplainToUse();
		str[1] = second.getDoubleExplainToUse();
		return str;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "second";
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean itemUse() {
		if (set()) {
			return true;
		}
		first.itemUse();
		second.itemUse();
		setForgeValue(-1);
		if (this.getForgeValue() < 0) {
			spellUsed();
		}
		return true;
	}

	private boolean set() {
		if (first.getCharacterShortName().matches("魔理沙")
				|| second.getCharacterShortName().matches("魔理沙")) {
			if (first.getCharacterShortName().matches("幽香")
					|| second.getCharacterShortName().matches("幽香")) {
				Player.me.setSecondAnimation(new MarisaSpark(Player.me,
						new Task() {
							/**
					 *
					 */
							private static final long serialVersionUID = 1L;

							@Override
							public void work() {
								Point p = Player.me.getMassPoint()
										.getLocation();
								DIRECTION d = Player.me.getDirection();
								while (true) {
									p.x += d.X;
									p.y += d.Y;
									if (MassCreater.isPointOutOfDangeon(p)) {
										break;
									}
									if (!MassCreater.getMass(p).WALKABLE) {
										MassCreater.dig(p, true);
									}
									if (MapList.getEnemy(p) != null) {
										Damage.damage(null, null, null,
												Player.me, MapList.getEnemy(p),
												120);
									}
								}
								MassCreater.retakeMassSet();
							}
						}));
				return true;
			}
		}
		return false;
	}

	private void setForgeValue() {
		forge_value = first.getForgeValue() + second.getForgeValue();
	}

	private void spellUsed() {
		if (isEnchantedNow())
			Enchant.forceToRemove(this);
		Belongings.remove(this);
	}
}
