package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.鍵山雛;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DecurseEffect;
import dangeon.view.anime.OuraEffect;

public class 鍵山雛のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "鍵山雛のカード";
	public static final String item_name_sound = "かきやまひなのかーと";
	private static final int composition = 4;
	private static final int item_str = 4;
	private static final int item_def = 6;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "装備中の厄を吸い取る程度の能力" };

	public 鍵山雛のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.厄;
	}

	private boolean effect() {
		boolean b = false;
		for (Base_Artifact a : Enchant.getEnchantList()) {
			if (a.isCurse()) {
				a.setCurse(false);
				b = true;
			}
		}
		return b;
	}

	@Override
	public String getCharacterShortName() {
		return "雛";
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
		return "呪い攻撃を防ぐぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("割と厄い");
	}

	@Override
	public String getIllustlator() {
		return "麦島";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=2499443";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return 鍵山雛.class;
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
		if (effect()) {
			final 鍵山雛のカード ME = this;
			Player.me.setAnimation(new DecurseEffect());
			Player.me.setAnimation(new OuraEffect(Player.me, new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					Message.set("「穢れをこの身に集めましょう」");
					ME.setCurse(true);
					Player.me.chengeHP(null, null, Player.me.getMAX_HP());
					Player.me.setCondition(CONDITION.パワーアップ, 0);
				}
			}));
		}
		return true;
	}

}
