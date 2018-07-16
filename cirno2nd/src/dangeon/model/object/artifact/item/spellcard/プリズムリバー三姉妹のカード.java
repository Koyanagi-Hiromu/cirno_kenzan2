package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.res.BGM;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.player.Player;
import dangeon.util.STAGE;
import dangeon.view.anime.OuraEffect;

public class プリズムリバー三姉妹のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "プリズムリバー三姉妹のカード";
	public static final String item_name_sound = "ふりすむりはーさんしまいのかーと";
	private static final int composition = 3;
	private static final int item_str = 3;
	private static final int item_def = 3;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "身体を強化する程度の能力" };

	public プリズムリバー三姉妹のカード(Point p) {
		super(p, item_name, 1, composition);
		sim = ENCHANT_SIMBOL.かまいたち;
		list_stage.add(STAGE.妖々夢);
		list_stage.add(STAGE.花映塚);
	}

	@Override
	public String getCharacterShortName() {
		return "プリズムリバー";
	}

	@Override
	public String getDoter() {
		return "かうざー";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=91937";
	}

	@Override
	String getExplanToEnchant() {
		return "三方向に攻撃出来るぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("合奏だよ！");
	}

	@Override
	public String getIllustlator() {
		return "ツユサトマザル";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=364373";
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
		return CardAttackEffect.class;
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
		Player.me.setAnimation(new OuraEffect(Player.me, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Message.set("ちからとHPの最大値が上がった");
				Player.me.changeMAX_STR(1);
				Player.me.addLV(1);
				Player.me.addMAX_HP(5);
				BGM.UTIL.play();
			}
		}));
		return true;
	}

}
