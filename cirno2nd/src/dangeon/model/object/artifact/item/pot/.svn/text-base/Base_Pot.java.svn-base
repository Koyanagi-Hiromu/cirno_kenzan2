package dangeon.model.object.artifact.item.pot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.check.Checker.KeyWord;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Enchant;
import dangeon.util.R;
import dangeon.util.STAGE;
import dangeon.view.detail.View_Sider;

public abstract class Base_Pot extends Base_Item {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public ENCHANT_SIMBOL simbol = null;
	private final List<STAGE> stages;

	protected int MAX;

	protected final ArrayList<Base_Artifact> LIST;

	public Base_Pot(Point p, String item_name, int max, STAGE... stages) {
		super(p, item_name, 0, ITEM_CASE.ETC);
		IM = Image_Artifact.BIN;
		MAX = max;
		LIST = new ArrayList<Base_Artifact>(MAX);
		this.stages = new ArrayList<STAGE>();
		for (STAGE stage : stages) {
			this.stages.add(stage);
		}
	}

	public void addMax() {
		if (MAX < 4)
			MAX++;
	}

	public boolean broken() {
		SE.BROKEN.play();
		for (Iterator<Base_Artifact> iterator = LIST.iterator(); iterator
				.hasNext();) {
			ItemFall.itemFall(mass_point, iterator.next());
		}
		return true;
	}

	protected boolean emptyAction() {
		Message.set(getColoredName(), "はからっぽだ！");
		if (!isStaticCheked()) {
			Checker.write(this, KeyWord.使用);
		}
		return false;
	}

	@Override
	protected int enchantDefence(boolean b, Base_Creature creature, int damage) {
		return 0;
	}

	@Override
	public void firstMsgAtUsingThis() {
	}

	@Override
	protected final String[] getExplan() {
		if (LIST.isEmpty()) {
			return super.getExplan();
		} else {
			String[] arr = new String[LIST.size()];
			for (int i = 0; i < LIST.size(); i++) {
				StringBuilder sb = new StringBuilder();
				if (i == 0)
					sb.append("【中身】");
				else
					sb.append("　　　　");
				Base_Artifact a = LIST.get(i);
				sb.append(a.getColoredName());
				arr[i] = sb.toString();
			}
			return arr;
		}
	}

	@Override
	public String getLastPackage() {
		return "pot";
	}

	public int getListSize() {
		return LIST.size();
	}

	public List<STAGE> getListStage() {
		return stages;
	}

	public int getMaxSize() {
		return MAX;
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		if (isStaticCheked()) {
			sb.append(getName());
			sb.append("[");
			sb.append(MAX - LIST.size());
			sb.append("]");
		} else {
			String name = getName();
			Matcher m = Pattern.compile("《.*?》").matcher(name);
			if (m.find()) {
				String sub = m.group();
				sb.append(m.replaceAll(""));
				sb.append("[");
				sb.append(MAX - LIST.size());
				sb.append("]");
				sb.append(sub);
			} else {
				sb.append(getName());
				sb.append("[");
				sb.append(MAX - LIST.size());
				sb.append("]");
			}
		}
		return sb;
	}

	@Override
	public int getShadow() {
		return 3;
	}

	public String[] getSuperExplain() {
		return super.getExplan();
	}

	public boolean isEmpty() {
		return LIST.isEmpty();
	}

	public boolean isMax() {
		return LIST.size() == MAX;
	}

	protected boolean isUnableToPutIn(Base_Artifact a) {
		if (Enchant.isEnchanted(a) && a.isCurse()) {
			return true;
		} else if (!a.isMobile()) {
			return true;
		} else if (a instanceof Base_Pot) {
			return true;
		}
		return false;
	}

	@Override
	public int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public void setMassPoint_ParabolaJump_NoAttack_PotBreak(Point p) {
		super.setMassPoint_ParabolaJump_NoAttack_PotBreak(p);
		if (new R().is(12)) {
			final Base_Pot af = this;
			at_movement_end_task = new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					if (getListComposition().contains(ENCHANT_SIMBOL.金)) {
						getListComposition().remove(ENCHANT_SIMBOL.金);
						Message.set(getColoredName(), "の神の加護が剥がれた@");
					} else if (broken()) {
						Message.set("転んだ衝撃で瓶が割れてしまった");
						View_Sider.setInformation(getColoredName(), "は壊れてしまった");
						MapList.removeArtifact(af);
					}
				}
			};
		}
	}

	public void setMax(int i) {
		MAX = i;
	}

}
