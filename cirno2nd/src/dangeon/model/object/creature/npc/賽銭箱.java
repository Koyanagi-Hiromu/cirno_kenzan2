package dangeon.model.object.creature.npc;

import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import main.res.Image_LargeCharacter;
import main.res.Image_Object;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.staff.一時しのぎの杖;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.strage.SAISEN_Strage;
import dangeon.util.R;
import dangeon.view.detail.MiniMap;

public class 賽銭箱 extends Base_NPC {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public final SAISEN_Strage STRAGE;
	private final Base_Artifact[] PUNISHMENTS;
	private final Room ROOM;

	protected 賽銭箱(Point p, String name, List<Base_Artifact> list) {
		super(p, name, false);
		ROOM = null;
		int size = list.size();
		int row = 10;
		int cols = (size + row - 1) / row;
		STRAGE = new SAISEN_Strage(this, cols);
		for (Iterator<Base_Artifact> iterator = list.iterator(); iterator
				.hasNext();) {
			STRAGE.addLast(iterator.next());
		}
		PUNISHMENTS = new Base_Artifact[0];
		IM = null;
	}

	public 賽銭箱(Room r) {
		super(new Point(r.X + r.W / 2, r.Y + r.H / 2), "博霊賽銭箱", false);
		ROOM = r;
		STRAGE = new SAISEN_Strage(this);
		int ran = 6 + (int) Math.floor(new R().nextDouble() * 5);
		PUNISHMENTS = new Base_Artifact[ran];
		for (int i = 0; i < ran; i++) {
			PUNISHMENTS[i] = ItemTable.itemReturn(mass_point, null);
			PUNISHMENTS[i].setPunishment(true);
			STRAGE.addLast(PUNISHMENTS[i]);
		}
		IM = null;
	}

	public 賽銭箱(Room r, List<Base_Artifact> list) {
		super(new Point(r.X + r.W / 2, r.Y + r.H / 2), "賽銭箱", false);
		ROOM = r;
		STRAGE = new SAISEN_Strage(this);
		PUNISHMENTS = new Base_Artifact[list.size()];
		for (int i = 0; i < PUNISHMENTS.length; i++) {
			PUNISHMENTS[i] = list.get(i);
			PUNISHMENTS[i].setPunishment(true);
			STRAGE.addLast(PUNISHMENTS[i]);
		}
		IM = null;
	}

	@Override
	protected void enemyBreakAction() {
		for (int i = 0; i < STRAGE.SIZE; i++) {
			ItemFall.itemFall(getMassPoint(), STRAGE.get(i));
		}
		STRAGE.clear();
		if (!MassCreater.getMass(Player.me.getMassPoint()).isHoly()) {
			new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					punish();
				}
			}.work_appointment();
		}
	}

	private int getBlackCount() {
		if (Player.me.saisen == null) {
			return 0;
		}
		HashMap<Base_Artifact, Boolean> map = new HashMap<Base_Artifact, Boolean>(
				PUNISHMENTS.length);
		for (Base_Artifact a : PUNISHMENTS) {
			map.put(a, STRAGE.contrains(a));
		}
		for (Mass[] m2 : MassCreater.getMass()) {
			for (Mass m : m2) {
				if (m.isHoly()) {
					Base_Artifact a = MapList.getArtiface(m.X, m.Y);
					if (a != null) {
						for (Base_Artifact pu : PUNISHMENTS) {
							if (a.equals(pu)) {
								map.put(a, true);
								break;
							}
						}
					}
				}
			}
		}
		int count = 0;
		for (boolean b : map.values()) {
			if (!b) {
				count++;
			}
		}
		return count;
	}

	@Override
	public Image getImage() {
		return Image_Object.賽銭箱.getImage();
	}

	@Override
	public int getShadowSize100() {
		return 44;
	}

	@Override
	public int getShadowY() {
		return 8;
	}

	public void goOut() {
		if (isWhite()) {
			Message.set("敷地の外に出た");
		} else {
			punish();
		}
	}

	public boolean isGray() {
		return getBlackCount() == 1;
	}

	public boolean isWhite() {
		return getBlackCount() <= 1;
	}

	private void judgement() {
		MapList.judgement();
		release();
	}

	@Override
	public void message() {
		new ConvEvent("お賽銭箱だ$１つだけ敷地の外に持ち出せるぞ") {
			@Override
			protected Book getContent1() {
				return new Book("取り出す") {
					@Override
					protected void work() {
						STRAGE.takeIt();
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("見る") {
					@Override
					protected void work() {
						STRAGE.justWatch();
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("やめる") {
					@Override
					protected void work() {
					}
				};
			}

			@Override
			protected Book getContent4() {
				return new Book("説明") {
					@Override
					protected void work() {
						Image_LargeCharacter imlc = Image_LargeCharacter.博麗霊夢;
						new Conversation(imlc, "※たくさんアイテムを取り出しちゃったら床に置いてもいいわ");
						new Conversation(imlc, "※２つ以上のお供え物を敷地の外に出すと・・・　うふふ");
						new Conversation(imlc, "※壊さなければ賽銭箱自体を敷地の外に出してもいいわ");
					}
				};
			}
		};
	}

	/**
	 * 
	 * @return 無事ならtrue
	 */
	public boolean punish() {
		if (MassCreater.getMass(Player.me.getMassPoint()).isHoly() || isWhite()) {
			return true;
		} else {
			judgement();
			// new Task() {
			// /**
			// *
			// */
			// private static final long serialVersionUID = 1L;
			//
			// @Override
			// public void work() {
			// judgement();
			// }
			// }.work_appointment();
			return false;
		}
	}

	public void release() {
		for (Base_Artifact a : PUNISHMENTS) {
			a.setPunishment(false);
		}
		ROOM.setHoly(false);
		MiniMap.reset();
	}

	@Override
	protected boolean resistCondition(CONDITION c) {
		return true;
	}

	@Override
	public boolean staffHitEffect(Base_Artifact a) {
		if (a instanceof 一時しのぎの杖) {
			Message.set("不思議なちからがはたらいて無効化された");
			return false;
		}
		return true;
	}

}
