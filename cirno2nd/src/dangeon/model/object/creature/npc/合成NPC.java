package dangeon.model.object.creature.npc;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.CHARA_IMAGE;
import connection.sv_cl.SocketHolder;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.Composition;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.player.Belongings;

public class 合成NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 合成した「よ」
	 */
	private final String tail;

	private Base_Artifact BASE_OBJECT;

	private Base_Artifact MATERIAL;
	private String Base_Name;
	private String Material;
	private boolean flag_talked = false;

	public 合成NPC(Point p, CHARA_IMAGE im, String name, String tail) {
		super(p, name, im, false);
		this.tail = tail;
	}

	private void chooseMaterial(Base_Artifact a) {
		ArrayList<Base_Artifact> list_escape;
		list_escape = Belongings.getDeepCopy(Belongings.getListItems());
		if (a instanceof Staff) {
			for (Iterator<Base_Artifact> iterator = list_escape.iterator(); iterator
					.hasNext();) {
				Base_Artifact artifact = iterator.next();
				if (artifact.getClass().equals(a.getClass())) {
					iterator.remove();
				} else {
					continue;
				}
			}
		} else {
			for (Iterator<Base_Artifact> iterator = list_escape.iterator(); iterator
					.hasNext();) {
				Base_Artifact artifact = iterator.next();
				if (artifact.sim != null || artifact instanceof SpellCard) {
					iterator.remove();
				} else {
					continue;
				}
			}
		}
		for (Base_Artifact ar : Belongings.getListItems()) {
			if (ar.isEnchantedNow() && ar.isCurse()) {
				list_escape.add(ar);
			}
		}
		if (!list_escape.contains(a))
			list_escape.add(a);
		Scene_Action.getMe().setNextScene(
				new Item_List(a.getName().concat("と合成する"), new Book_Item() {

					@Override
					public void work(Base_Artifact a) {
						MATERIAL = a;
						if (Composition.setComposition(BASE_OBJECT, MATERIAL)) {
							Material = a.getColoredName();
							Composition.isSpecialCard(BASE_OBJECT, Belongings
									.getListItems().indexOf(BASE_OBJECT));
							Message.set(Base_Name, "に", Material, "を合成した", tail);
						} else {
							Material = a.getColoredName();
							Message.set(Base_Name, "に", Material, "は合成出来ない",
									tail);
						}
						Scene_Action.getMe().setNextScene(Scene_Action.getMe());
					}

				}, list_escape, Belongings.getListItems()));
	}

	private void composite() {
		ArrayList<Base_Artifact> list_escape = Belongings
				.getDeepCopy(Belongings.getListItems());
		for (Iterator<Base_Artifact> iterator = list_escape.iterator(); iterator
				.hasNext();) {
			Base_Artifact artifact = iterator.next();
			if (artifact instanceof SpellCard || artifact instanceof Staff) {
				if (artifact.isEnchantedNow() && artifact.isCurse()) {
					continue;
				} else {
					iterator.remove();
				}
			} else {
				continue;
			}
		}
		Scene_Action.getMe().setNextScene(
				new Item_List("合成ベース", new Book_Item() {

					@Override
					public void work(Base_Artifact a) {
						BASE_OBJECT = a;
						Base_Name = a.getColoredName();
						chooseMaterial(a);
					}

				}, list_escape, Belongings.getListItems()) {

					@Override
					protected void setContents(final Base_Artifact a) {
						if (isException(a)) {
							setContents(
									Color.BLACK.toString().concat(a.getName()),
									null, new Book() {
										@Override
										protected void work() {
											Message.set("それは選択できない");
											action_cancel();
										}
									}, a);
						} else {
							setContents(a.getColoredName(), null, new Book() {
								@Override
								protected void work() {
									if (BOOK != null) {
										BOOK.work(a);
									}
								}
							}, a);
						}
					}
				});
	}

	@Override
	public void message() {
		flag_talked = true;
		if (SocketHolder.isConnected()) {
			no_good();
		} else {
			composite();
		}
	}

	private void no_good() {
		say("電波障害が出てうまく合成できないよ");
	}

	@Override
	protected void setBeatedAway() {
		if (!SocketHolder.isConnected()) {
			if (!flag_talked) {
				Medal.話しかける前にNPCにとりを倒してしまった.addCount();
			}
		}
		super.setBeatedAway();
	}

}
