package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.res.SE;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.shop.Shop;

public abstract class CopyOf__店主 extends Base_Enemy {

	public final Shop shop;
	private boolean flag_first_in = true;
	protected boolean flag_buyed;

	protected Image_LargeCharacter IMLC;

	private static final long serialVersionUID = 1L;

	private BGM pre_bgm;

	protected Point pre_warning_point = new Point(-1, -1);

	public CopyOf__店主(Point p, int lv) {
		super(p, lv);
		this.shop = null;
	}

	public CopyOf__店主(Point p, int lv, Shop shop, Image_LargeCharacter imlc) {
		super(p, lv);
		this.shop = shop;
		IMLC = imlc;
	}

	@Override
	public void action() {
		if (isPlayerSide()) {
			this.setDirection(DIRECTION.getDirection(this, Player.me));
			enemy_actioned = true;
		} else {
			super.action();
		}
	}

	private void bgmPlay() {
		BGM bg = BGM.get();
		if (pre_bgm == null && bg != BGM.yukkurishiteittene) {
			pre_bgm = bg;
			BGM.yukkurishiteittene.play();
		}
	}

	private boolean checkSell() {
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (checkSell(a))
				return true;
		}
		return false;
	}

	private boolean checkSell(Base_Artifact a) {
		if (MassCreater.getMass(a.getMassPoint()).isShop() && !a.isMerchant()
				&& a instanceof Base_Item) {
			return true;
		}
		return false;
	}

	private boolean checkWarning(Base_Item merchants) {
		if (MapList.getListArtifact().contains(merchants)) {
			if (MassCreater.getMass(merchants.getMassPoint()).isShop()) {
				return false;
			}
		}
		return true;
	}

	public void gather() {
		if (warning()) {
			judgement();
		}
	}

	protected abstract String getHello();

	protected abstract String getHelloRoughly();

	public void goOutMessage() {
		if (warning()) {
			new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					judgement();
				}
			}.work_appointment();
		} else {
			if (pre_bgm != null)
				pre_bgm.play();
			pre_bgm = null;
			if (isPlayerSide()) {
				Message.set("「またきてね！」");
			} else {
				Message.set("お店をあとにした");
			}
		}
	}

	@Override
	public boolean hasMessage() {
		return isPlayerSide();
	}

	@Override
	public boolean isPlayerSide() {
		return shop != null && HP == getMAX_HP()
				&& MapList.getListEnemy().contains(this)
				&& Player.me.shop != null;
	}

	protected void judgement() {
		MapList.judgement();
		shop.release();
	}

	@Override
	public void message() {
		if (warning()) {
			pleasePages();
		} else if (checkSell()) {
			playerSell(null);
		} else {
			wasshoi();
		}
	}

	private void playerSell(String s) {
		final ArrayList<Base_Item> list = new ArrayList<Base_Item>();
		ConvEvent CnE = new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("いいよ") {
					@Override
					protected void work() {
						say("まいどー！");
						for (Base_Item base_Item : list) {
							Player.me
									.addBooks(base_Item.getMerchantSoldValue());
							base_Item.setMerchant(true);
							shop.getItemList().add(base_Item);
						}
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("だめ") {
					@Override
					protected void work() {
						say("そっかー");
					}
				};
			}

			@Override
			protected int pushCancelAction() {
				return 2;
			}

		};
		int sum = 0;
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (checkSell(a)) {
				list.add((Base_Item) a);
				sum += a.getMerchantSoldValue();
			}
		}
		StringBuilder sb = new StringBuilder();
		if (s != null)
			sb.append(s);
		sb.append("床に置いたアイテムを$　");
		sb.append(半角全角コンバーター.半角To全角数字(sum));
		sb.append("ページ　$と交換したいんだけどいいかな？");
		new Conversation(IMLC, sb.toString(), CnE);
	}

	private void pleasePages() {
		final ArrayList<Base_Item> list = new ArrayList<Base_Item>();
		int sum = 0;
		for (Base_Item a : shop.getItemList()) {
			if (checkWarning(a)) {
				list.add(a);
				sum += a.getMerchantBuyValue();
			}
		}
		final int SUM = sum;
		ConvEvent CnE = new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("いいよ") {
					@Override
					protected void work() {
						if (Player.me.getBooks() >= SUM) {
							SE.SYSTEM_ENCHANT.play();
							say("やったー！　ありがとね！");
							Player.me.addBooks(-SUM);
							for (Base_Item base_Item : list) {
								base_Item.setMerchant(false);
								shop.getItemList().remove(base_Item);
							}
							flag_buyed = true;
						} else {
							say("ページが足りないよ");
						}
						thenNext();
					}

				};
			}

			@Override
			protected Book getContent2() {
				return new Book("だめ") {
					@Override
					protected void work() {
						say("ちぇー");
						thenNext();
					}
				};
			}

			@Override
			protected int pushCancelAction() {
				return 2;
			}

			private void thenNext() {
				if (checkSell()) {
					playerSell("それはそうと");
				}
			}
		};
		StringBuilder sb = new StringBuilder();
		sb.append("持っていったアイテムの代わりに$　");
		sb.append(半角全角コンバーター.半角To全角数字(sum));
		sb.append("ページ　$欲しいんだけどいいかな？");
		new Conversation(IMLC, sb.toString(), CnE);
	}

	public void preGoOut() {
		if (warning()) {
			if (isPlayerSide()) {
				Point p = Player.me.getMassPoint();
				if (!pre_warning_point.equals(p)) {
					pre_warning_point = p;
					this.setDirection(DIRECTION.getDirection(this, Player.me));
					if (isSkillActive()) {
						Player.me.setDirection(DIRECTION.getDirection(
								Player.me, this));
						say("交換が済んでないよ？");
					} else {
						Message.set(getColoredName(), "は何か言いたげな目でこちらを見ている");
					}
				}
			}
		}
	}

	protected void say(String... strings) {
		new Conversation(IMLC, strings);
	}

	public final void shopInMessage() {
		bgmPlay();
		if (isPlayerSide() && !Player.me.conditionCheck(CONDITION.透明)) {
			if (!flag_first_in || Belongings.get(-1) != null) {
				Message.set(getHelloRoughly());
			} else {
				say(getHello());
			}
			flag_first_in = false;
		} else {
			Message.set("お店に足を踏み入れた");
		}
	}

	public boolean warning() {
		if (Player.me.shop == null) {
			return false;
		}
		for (Base_Item merchants : shop.getItemList()) {
			if (checkWarning(merchants)) {
				return true;
			}
		}
		return false;
	}

	protected abstract void wasshoi();

}
