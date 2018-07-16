package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.霧雨魔理沙;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.MarisaSpark;

public class 霧雨魔理沙のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "霧雨魔理沙のカード";
	public static final String item_name_sound = "きりさめまりさのかーと";
	private static final int composition = 7;
	private static final int item_str = 16;
	private static final int item_def = 7;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "マスタースパークを放つ程度の能力" };

	private final int spelldamage = 90;

	public 霧雨魔理沙のカード(Point p) {
		super(p, item_name, 1, composition);
	}

	@Override
	public String getCharacterShortName() {
		return "魔理沙";
	}

	@Override
	public String getDoter() {
		return "きいろからー";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=95848";
	}

	@Override
	String getExplanToEnchant() {
		return "";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
	}

	@Override
	protected String getFlavor() {
		return new String("トド……？");
	}

	@Override
	public String getIllustlator() {
		return "オアシス";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=658181";
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
		return 霧雨魔理沙.class;
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
		Player.me.setSecondAnimation(new MarisaSpark(Player.me, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Point p = Player.me.getMassPoint().getLocation();
				DIRECTION d = Player.me.getDirection();
				while (true) {
					p.x += d.X;
					p.y += d.Y;
					if (MassCreater.isPointOutOfDangeon(p)) {
						break;
					}
					if (MapList.getEnemy(p) != null) {
						Damage.damage(null, null, null, Player.me,
								MapList.getEnemy(p), spelldamage);
					}
				}
			}
		}));

		return true;
	}
}
