package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.風見幽香;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.MasterSpark;

public class 風見幽香のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "風見幽香のカード";
	public static final String item_name_sound = "かさみゆうかのかーと";
	private static final int composition = 6;
	private static final int item_str = 12;
	private static final int item_def = 9;
	private static final boolean passing_of_spell = false;

	private static final String[] EXPLAN = new String[] { "壁をも壊すマスタースパークを放つ程度の能力" };

	private final int spelldamage = 50;

	public 風見幽香のカード(Point p) {
		super(p, item_name, 1, composition, 風見幽香.class);
		sim = ENCHANT_SIMBOL.華;
	}

	private void effect() {
		Player.me.setSecondAnimation(new MasterSpark(Player.me, new Task() {
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
					if (!MassCreater.getMass(p).WALKABLE) {
						MassCreater.dig(p, true);
					}
					if (MapList.getEnemy(p) != null) {
						Damage.damage(null, null, null, Player.me,
								MapList.getEnemy(p), spelldamage);
					}
				}
				MassCreater.retakeMassSet();
			}
		}));
	}

	@Override
	public String getCharacterShortName() {
		return "幽香";
	}

	@Override
	public String getDoter() {
		return "るー";
	}

	@Override
	public String getDoterURL() {
		return "http://ruemoni.kitunebi.com/";
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
		return new String("割と厄い");
	}

	@Override
	public String getIllustlator() {
		return "nabesin1";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=434087";
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
		return 風見幽香.class;
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
		effect();
		return true;
	}

}
