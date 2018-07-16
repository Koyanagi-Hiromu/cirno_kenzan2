package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;
import java.util.List;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.八雲紫;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 八雲紫のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "八雲紫のカード";
	public static final String item_name_sound = "やくもゆかりのかーと";
	private static final int composition = 4;
	private static final int item_str = 6;
	private static final int item_def = 3;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "周囲８マスの敵をスキマ送りにする程度の能力" };

	public 八雲紫のカード(Point p) {
		super(p, item_name, 1, composition, 八雲紫.class);
		sim = ENCHANT_SIMBOL.潜;
	}

	@Override
	public String getCharacterShortName() {
		return "紫";
	}

	@Override
	public String getDoter() {
		return "まるせん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=1733063";
	}

	@Override
	String getExplanToEnchant() {
		return "壁を通して攻撃できるぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("改造！改造！");
	}

	@Override
	public String getIllustlator() {
		return "ふゆき";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=286217";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "使用するとフロア内のどこかにワープさせる";
	}

	@Override
	protected String[] getSecondExplan() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 八雲紫.class;
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
		List<Base_Creature> list = MapInSelect
				.getListAroundInCreature(Player.me.getMassPoint());
		if (!PresentField.get().isHaraheru()) {
			Message.set("「ダンジョン内で使用してほしいわ」");
			return false;
		}
		if (list.isEmpty()) {
			Message.set("「あら？　誰もいないわよ？」");
			return false;
		}
		SE.YUKARI_SPELL.play();
		Message.set("「どこに行くかはスキマ次第よ。」");
		for (final Base_Creature c : list) {
			c.setTelepoteAnimation(false, new Task() {

				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					c.setMassPoint(MassCreater.getWarpPoint((Base_Enemy) c));
				}
			});
			c.setCondition(CONDITION.混乱, 0);
		}
		return true;
	}

}
