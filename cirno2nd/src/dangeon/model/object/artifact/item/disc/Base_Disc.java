package dangeon.model.object.artifact.item.disc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import main.res.SE;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.view.anime.DecurseEffect;

public abstract class Base_Disc extends Base_Item implements SelectItem {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<STAGE> list_stage = new ArrayList<STAGE>();

	private Base_Artifact ME = this;

	protected Base_Disc(Point p, String item_name) {
		super(p, item_name, 2, ITEM_CASE.DISC);
	}

	@Override
	protected void action() {
	}

	@Override
	public int compareTo(Base_Artifact o) {
		if (getClassName().equals(o.getClassName())) {
			return getName().compareTo(o.getName());
		}
		return 1;
	}

	private int doublePlus() {
		if (list_stage.get(0) == list_stage.get(1)) {
			return 1;
		}
		return 0;
	}

	protected int enchantAttack(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	@Override
	protected int enchantDefence(boolean normal, Base_Creature c, int damage) {
		return damage;
	}

	@Override
	public void firstMsgAtUsingThis() {
		Message.set(getColoredName(), "を差し込んだ");
	}

	public String getDiscNames() {
		return name;
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		for (Iterator<Base_Artifact> i = list.iterator(); i.hasNext();) {
			Base_Artifact a = i.next();
			if (a.isStaticCheked()) {
				if (a instanceof Base_Pot) {
					Base_Pot pot = (Base_Pot) a;
					if (pot.getMaxSize() < 4) {
						List<STAGE> ss = pot.getListStage();
						if (ss == null) {
							ss = new ArrayList<STAGE>();
							a = ItemTable
									.createObject(mass_point, a.getClass());
							if (a instanceof Base_Pot) {
								ss = ((Base_Pot) a).getListStage();
							}
						}
						if (stageCheck(ss)) {
							i.remove();
							continue;
						}
					}
				} else if ((a instanceof SpellCard) && a.getForgeValue() < 99) {
					if (stageCheck(((SpellCard) a).getListStage())) {
						i.remove();
						continue;
					}
				}
			}
		}
		return list;
	}

	@Override
	public String getLastPackage() {
		return "disc";
	}

	@Override
	protected String getSecondExplain_ByCategory() {
		return "対応するタイトルのゲーム中に敵として出てくるキャラクターのカードを強化する。強化されたカードの呪いは解ける。なお２つのタイトルが同じ場合特別な印を持ち使用すれば修正値が＋２される。";
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return null;
	}

	private boolean isStageMatch(STAGE s) {
		for (STAGE stage : list_stage) {
			if (stage == s) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		Random ran = new R();
		int damage = ran.nextInt(2) + 1;
		Message.set(new String[] { c.getColoredName().concat("に"),
				getColoredName().concat("をぶつけた") });
		Damage.damage(this, null, null, c2, c, damage);
	}

	@Override
	public boolean itemUse() {
		int plus_forge;
		A.check();
		if (A.isCurse()) {
			A.setCurse(false);
			Player.me.setAnimation(new DecurseEffect());
			Message.set(A.getColoredName(), "にかかっていた呪いが解除され");
		}
		SE.KYOUKA.play();
		if (A instanceof Base_Pot) {
			int plus = 1 + doublePlus();
			int j = 0;
			String name = A.getColoredName();
			for (; j < plus; j++) {
				Base_Pot pot = ((Base_Pot) A);
				if (pot.getMaxSize() < 4) {
					pot.addMax();
				} else {
					break;
				}
			}
			Message.set(name, "の容量が", 半角全角コンバーター.半角To全角数字(j), "増えた");
		} else {
			if (plus()) {
				SE.LUCKEY.play();
				plus_forge = 2;
			} else {
				plus_forge = 1;
			}
			int plus = plus_forge + doublePlus();
			if (plus == 1) {
				Message.set(A.getColoredName(), "は強化された");
			} else if (plus == 2) {
				Message.set(A.getColoredName(), "はすごく強化された");
			} else {
				Message.set(A.getColoredName(), "はものすごく強化された");
			}
			A.setForgeValue(plus);
		}
		Belongings.remove(this);
		if (isEnchantedNow()) {
			Enchant.forceToRemove(this);
		}
		// TaskOnMapObject.addItemRemoveTask(ME);
		return true;
	}

	private boolean plus() {
		int parcent = 5;
		if (parcent >= new R().nextInt(100) + 1) {
			return true;
		}
		return false;
	}

	private boolean stageCheck(List<STAGE> ss) {

		for (STAGE s : ss) {
			if (isStageMatch(s)) {
				return true;
			}
		}
		return false;
	}
}