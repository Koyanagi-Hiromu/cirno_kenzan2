package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.魂魄妖夢;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;

public class 魂魄妖夢のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "魂魄妖夢のカード";
	public static final String item_name_sound = "こんはくようむのかーと";
	private static final int composition = 5;
	private static final int item_str = 10;
	private static final int item_def = 4;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "一瞬で並んだ敵をすり抜けながら辻斬る程度の能力" };

	public 魂魄妖夢のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.連;
	}

	private void effect(List<Base_Creature> list) {
		SE.FIRST_OURA.play();
		SE.YOUMU_SP2.play();
		if (list.isEmpty()) {
			return;
		}
		for (Base_Creature c : list) {
			Damage.normalAttack(Player.me, c);
		}
	}

	@Override
	public String getCharacterShortName() {
		return "妖夢";
	}

	@Override
	public String getDoter() {
		return "シュウ";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1724270";
	}

	@Override
	String getExplanToEnchant() {
		return "連続で攻撃するぞ。ただしダメージは低いぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("辻斬り抜刀歳");
	}

	@Override
	public String getIllustlator() {
		return "ジョンディー";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=1686747";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "使用してもターンが経過しない@目の前に敵がいなくても１歩進む";
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 魂魄妖夢.class;
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
		List<Base_Creature> list = new ArrayList<Base_Creature>();
		Point p = Player.me.getMassPoint().getLocation();
		DIRECTION d = Player.me.getDirection();
		p.x += d.X;
		p.y += d.Y;
		if (MapList.getEnemy(p) != null) {
			while (true) {
				if (MapList.getCreature(p) != null) {
					list.add(MapList.getCreature(p));
					p.x += d.X;
					p.y += d.Y;
					continue;
				}
				if (MassCreater.getMass(p).WALKABLE) {
					break;
				}
				if (!MassCreater.getMass(p).WALKABLE) {
					Message.set("「壁が有るから能力が発動できませんね」");
					return false;
				}
			}
		} else if (!MassCreater.getMass(p).WALKABLE) {
			Message.set("「壁が有るから能力が発動できませんね」");
			return false;
		}
		Player.me.setMassPoint_WalkLike(p, 3);
		effect(list);
		return true;
	}
}
