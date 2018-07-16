package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.上白沢慧音;
import dangeon.model.object.creature.enemy.二ッ岩マミゾウ;
import dangeon.model.object.creature.enemy.人形;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 二ッ岩マミゾウのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "二ッ岩マミゾウのカード";
	public static final String item_name_sound = "ふたついわまみそうのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 10;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "部屋内の敵に状態異常を与えるぞ" };

	public 二ッ岩マミゾウのカード(Point p) {
		super(p, item_name, 1, composition, 上白沢慧音.class);
		sim = ENCHANT_SIMBOL.狸;
	}

	@Override
	public String getCharacterShortName() {
		return "マミゾウ";
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
		return "攻撃を受けると、時々敵に状態異常を与えるぞ";
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
		return "Curee(AQUASTYLE)";
	}

	@Override
	public String getIllustlatorURL() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
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
		return 二ッ岩マミゾウ.class;
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
		Message.set("「思い通りうまくいくかのぉ？」");
		ArrayList<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy e : MapList.getListEnemy()) {
			list.add(e);
		}
		for (final Base_Enemy e : list) {
			if (e instanceof Base_NPC)
				continue;
			Base_Artifact a = MapList.getArtifact(e.getMassPoint());
			boolean flag = a != null && a instanceof Base_Grass;
			flag = flag || MassCreater.getMass(e.getMassPoint()).WATER_LEEF;
			if (flag) {
				final Point _p = e.getMassPoint();
				MapList.removeEnemy(e);
				MainMap.addEffect(new DoronEffect(_p, new Task() {
					/**
					 *
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					}

					@Override
					protected void work(int frame) {
						if (frame == 4) {
							MapList.addEnemy(new 人形(_p, e.getConvertedLV()));
						}
					}
				}), true);
			}
		}
		return true;
	}
}
