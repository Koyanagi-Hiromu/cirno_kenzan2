package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.フランドール;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.view.anime.ExplosionEffect;
import dangeon.view.detail.MainMap;

public class フランドールのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "フランドールのカード";
	public static final String item_name_sound = "ふらんとーるのかーと";
	private static final int composition = 5;
	private static final int item_str = 10;
	private static final int item_def = 0;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "フロア中の落ちているアイテムを爆破する程度の能力" };

	public フランドールのカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.つるはし;
	}

	@Override
	public String getCharacterShortName() {
		return "フランドール";
	}

	@Override
	public String getDoter() {
		return "鮫妻";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=360177";
	}

	@Override
	String getExplanToEnchant() {
		return "壁や罠を破壊するぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("壊すでござる");
	}

	@Override
	public String getIllustlator() {
		return "ぷりもあ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=362745";
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
		return フランドール.class;
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
		final ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>(
				MapList.getListArtifact().size());
		for (Base_Artifact a : MapInSelect.getListRoomOrRoadInArtifact()) {
			if (a instanceof Base_Device) {
				continue;
			}
			list.add(a);
		}
		if (list.isEmpty()) {
			Message.set("「なにも落ちてないよ」");
			return false;
		} else {
			Message.set("「キュッとしてドッカーン☆」");
			MainMap.addEffect(new ExplosionEffect(list.get(0).getMassPoint(),
					new Task() {
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							Point ps[] = new Point[list.size()];
							for (int i = 0; i < ps.length; i++) {
								ps[i] = list.get(i).getMassPoint();
							}
							MapInSelect.explosion_effect(ps);
							if (Player.me.saisen != null) {
								Player.me.saisen.punish();
							}
						}

						@Override
						protected void work(int frame) {
							int i = (frame + 1) / 2;
							if (i < list.size()) {
								MainMap.addEffect(
										new ExplosionEffect(list.get(i)
												.getMassPoint(), null), true);
							}
						}
					}), true);
			return true;
		}
	}

}
