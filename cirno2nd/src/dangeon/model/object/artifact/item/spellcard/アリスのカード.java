package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.アリス;
import dangeon.model.object.creature.enemy.人形;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class アリスのカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "アリスのカード";
	public static final String item_name_sound = "ありすのかーと";
	private static final int composition = 5;
	private static final int item_str = 4;
	private static final int item_def = 9;
	private static final boolean passing_of_spell = false;
	private static final String[] EXPLAN = new String[] { "周囲８マスに動かない人形を作り出す程度の能力" };

	public アリスのカード(Point p) {
		super(p, item_name, 1, composition, アリス.class);
		sim = ENCHANT_SIMBOL.魔;
	}

	private void effect() {
		Point p = Player.me.getMassPoint().getLocation();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				final Point _p = new Point(p.x + i, p.y + j);
				if (!MassCreater.getMass(_p).WALKABLE) {
					continue;
				}
				if (MapList.getEnemy(_p) == null) {
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
								MapList.addEnemy(new 人形(_p, 1)
										.setConditionList(CONDITION.死, 99));
							}
						}
					}), true);

				}
			}
		}
	}

	@Override
	public String getCharacterShortName() {
		return "アリス";
	}

	@Override
	public String getDoter() {
		return "かみねんど";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=648642";
	}

	@Override
	String getExplanToEnchant() {
		return "魔法ダメージを減らすぞ";
	}

	@Override
	String[] getExplanToUse() {
		return EXPLAN;
		// return new String[] { "周囲８マスに人形を作り出す程度の能力" };
	}

	@Override
	protected String getFlavor() {
		return new String("私の名前はアリス・マーガトロイド");
	}

	@Override
	public String getIllustlator() {
		return "五目みつき";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=38755";
	}

	@Override
	boolean getPassingOfSpellCard() {
		return passing_of_spell;
	}

	@Override
	protected String getSecondExplain_ByEach_Annotation() {
		return "使用すると無害な人形を繰り出す@遠距離攻撃を持たない敵に対する壁として使うのが基本@９９ターン後に自壊する";
	}

	@Override
	protected String[] getSecondExplan() {
		return null;
	}

	@Override
	public Class<? extends Base_Enemy> getStand() {
		return アリス.class;
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
		if (MapList.isEnemyMax()) {
			Message.set("「敵がフロアにいすぎて作り出せないわ」");
			return false;
		}
		effect();
		Message.set("人形を作り出した");
		return true;
	}
}
