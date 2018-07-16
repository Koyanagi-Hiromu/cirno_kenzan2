package dangeon.view.anime;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.res.Image_Anime;
import main.res.SE;
import main.util.半角全角コンバーター;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class RainEffect extends Effect_Image_Anime {
	public static RainEffect work(Base_Artifact a) {
		boolean dam = false;
		String invalid_msg = null;
		Base_Artifact selected_item = null;
		if (a instanceof SpellCard) {
			selected_item = a;
			if (EnchantSpecial.plating(selected_item)) {
				SE.MEKKI.play();
				invalid_msg = "しかし" + selected_item.getColoredName()
						+ "は雛の加護があって大丈夫だった";
			}
		} else {
			// NoMessage
		}
		return new RainEffect(a, invalid_msg, dam, selected_item, -1);
	}

	public static RainEffect work(int delt) {
		boolean dam = false;
		String invalid_msg = null;
		Base_Artifact selected_item = null;
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.衰)) {
			invalid_msg = "リボンの効果で錆を防いだ";
		} else {
			for (Base_Artifact a : Enchant.getEnchantList()) {
				if (a instanceof SpellCard) {
					list.add(a);
				}
			}
			if (list.size() == 0) {
				invalid_msg = "劣化するものを装備していなかったので大丈夫だった";
			} else {
				Random ran = new R();
				int selection = ran.nextInt(list.size());
				selected_item = list.get(selection);
				if (EnchantSpecial.plating(selected_item)) {
					SE.MEKKI.play();
					invalid_msg = "しかし" + selected_item.getColoredName()
							+ "は雛の加護があって大丈夫だった";
				} else {
					dam = true;
				}
			}
		}
		return new RainEffect(invalid_msg, dam, selected_item, delt);
	}

	/**
	 * LV３のとき
	 */
	public static RainEffect work_fuck() {
		String invalid_msg = null;
		Base_Artifact selected_item = null;
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		boolean composition_death = false, dam = false;
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.衰)) {
			invalid_msg = "リボンの効果で劣化を防いだ";
		} else {
			for (Base_Artifact a : Enchant.getEnchantList()) {
				if (a instanceof SpellCard) {
					list.add(a);
				}
			}
			if (list.size() == 0) {
				invalid_msg = "劣化するものを装備していなかったので大丈夫だった";
			} else {
				Random ran = new R();
				int selection = ran.nextInt(list.size());
				selected_item = list.get(selection);
				if (EnchantSpecial.plating(selected_item)) {
					selected_item.getListComposition().remove(ENCHANT_SIMBOL.金);
					invalid_msg = selected_item.getColoredName()
							+ "の雛の加護を剥がされた";
					composition_death = true;
				} else {
					List<ENCHANT_SIMBOL> _list = selected_item
							.getListComposition();
					if (_list.isEmpty() || new R().nextInt(2) != 0) {
						dam = true;
					} else {
						ENCHANT_SIMBOL ES = _list.get(new R().nextInt(_list
								.size()));
						_list.remove(ES);
						invalid_msg = selected_item.getColoredName() + "の"
								+ ES.getName() + "印を剥がされた";
						composition_death = true;
					}
				}
			}
		}
		if (composition_death) {
			return new RainEffect(invalid_msg, true, selected_item, -3);
		} else {
			return new RainEffect(invalid_msg, dam, selected_item, -3);
		}

	}

	private final boolean flag_valid;

	public final Base_MapObject O;

	public RainEffect(Base_MapObject o, boolean flag_valid, Task task) {
		super(o.getMassPoint(), 2, Image_Anime.rain, SE.RAIN, 1, task);
		O = o;
		this.flag_valid = flag_valid;
	}

	public RainEffect(final Base_MapObject o, final String msg,
			final boolean dam, final Base_Artifact selected_item, final int delt) {
		this(o, dam, new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (msg == null) {
					if (selected_item != null) {
						Message.set(selected_item.getColoredName(), "の修正値が",
								半角全角コンバーター.半角To全角数字(-delt), "下がった");
						selected_item.setForgeValue(delt);
					}
				} else {
					Message.set(msg);
				}
				if (o instanceof Base_Artifact) {
					Base_Artifact a = (Base_Artifact) o;
					MapList.removeArtifact(a);
					ItemFall.itemFall(a);
				}
			}
		});
	}

	public RainEffect(final String msg, final boolean dam,
			final Base_Artifact selected_item, final int delt) {
		this(Player.me, msg, dam, selected_item, delt);
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (flag_valid) {
			if (O instanceof Base_Creature) {
				((Base_Creature) O).startDamaging();
			}
		}
		return super.draw(g);
	}

	@Override
	protected int getDelY() {
		return -10;
	}

}
