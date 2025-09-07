package dangeon.model.object.artifact.item.spellcard;

import java.awt.Point;

import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.view.result.Scene_Result_Info;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.大妖精;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.view.detail.Header;
import main.res.Image_LargeCharacter;
import main.res.SE;

public class 大妖精のカード extends SpellCard {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "大妖精のカード";
	public static final String item_name_sound = "たいようせいのかーと";
	private static final int composition = 7;
	private static final int item_str = 0;
	private static final int item_def = 2;
	private static final boolean passing_of_spell = true;

	private static final String[] EXPLAN = new String[] { "次の階に進む程度の能力" };

	public 大妖精のカード(Point p) {
		super(p, item_name, 1, composition);
	}

	@Override
	public String getCharacterShortName() {
		return "大妖精";
	}

	@Override
	public String getDoter() {
		return "めそうさん";
	}

	@Override
	public String getDoterURL() {
		return "http://www.pixiv.net/member.php?id=293174";
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
		return new String("大妖精だよ");
	}

	@Override
	public String getIllustlator() {
		return "電気ウサギ";
	}

	@Override
	public String getIllustlatorURL() {
		return "http://www.pixiv.net/member.php?id=33409";
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
		return 大妖精.class;
	}

	@Override
	public boolean isUdongeSelective() {
		return false;
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
		ConvEvent cne = new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("アイテムを３つ得る") {

					@Override
					protected void work() {
						Message.set("願いが叶って、アイテムが降ってきた");
						SE.ZAKUZAKU.play();
						for(int i = 0; i < 3; i++)
						{
							ItemFall.itemFall(ItemTable.itemReturn(Player.me.getMassPoint(), false));
						}
					}
				};
			}
			@Override
			protected Book getContent2() {
				return new Book("かき氷を食べる") {

					@Override
					protected void work() {
						Message.set("願いが叶って、チルノはかき氷を食べた");
						Header.setEatIce();
						CONDITION.conditionAllClear(Player.me, true);
						Player.me.chengeHP(this, null, 999);
					}
				};
			}
			@Override
			protected Book getContent3() {
				return new Book("自爆する") {

					@Override
					protected void work() {
						Message.set("願いが叶って、チルノは爆発した");
						MapInSelect.explosion(Player.me.getMassPoint().getLocation());
					}
				};
			}
			@Override
			protected Book getContent4() {
				return new Book("ダンジョンから帰還する") {

					@Override
					protected void work() {
						boolean success = wishToEscape();
						if (!success)
						{
							大妖精のカード.this.addBomb(2);
						}
					}
				};
			}
			@Override
			protected Book getContent5() {
				return new Book("ちょっと待って！") {

					@Override
					protected void work() {
						大妖精のカード.this.addBomb(2);
					}
				};
			}
			
			protected int pushCancelAction() {
				return 5;
			}
		};
		new Conversation(Image_LargeCharacter.大妖精, cne, "チルノちゃんの願いを教えて？$（どれを選んでもターンは経過しないよ）");
		return true;
		
	}
	
	boolean wishToEscape()
	{
		final Base_Map_Random BMR = PresentField.get().getBaseMapRandom();
		if (BMR == null) {
			Message.set("「ダンジョン内じゃないと使えないよ」");
			return false;
		} else if (Player.me.isGray()) {
			Message.set("「泥棒になっちゃうよ？」");
			return false;
		} else if (MapList.getFlagSheef()) {
			Message.set("「い、今はワープ出来ないよ！」");
			return false;
		} else {
			new Conversation("本当にダンジョンから帰還するの？", new ConvEvent() {
				@Override
				protected Book getYes() {
					return new Book() {

						@Override
						protected void work() {
							Player.me.cause_of_death = "大妖精のカードを使用して妖精の踊り場に帰還した";
							Player.flag_daichan_return = true;
							new Scene_Result_Info(BMR);
						}
					};
				}
				
				protected int pushCancelAction() {
					return -1;
				}
			});
			
			return true;
		}
	}

}
