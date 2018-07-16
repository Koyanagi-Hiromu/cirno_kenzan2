package dangeon.model.object.creature.npc;

import java.awt.Image;
import java.awt.Point;
import java.util.List;

import main.res.Image_LargeCharacter;
import main.res.Image_Object;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.strage.SAISEN_Strage;

public class 守矢賽銭箱 extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final SAISEN_Strage STRAGE;

	public 守矢賽銭箱(Point p, List<Base_Artifact> list) {
		super(p, "守矢賽銭箱", null, false);
		STRAGE = new SAISEN_Strage(this);
		if (list != null) {
			for (Base_Artifact a : list) {
				STRAGE.addLast(a);
			}
		}
		IM = null;
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

	@Override
	public void message() {
		new ConvEvent("忘れられた賽銭箱だ") {
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
						Image_LargeCharacter imlc = Image_LargeCharacter.東風谷早苗;
						new Conversation(imlc, "※好きなだけ持って行きなさい");
						new Conversation(imlc, "※神の思し召しですよ");
					}
				};
			}
		};
	}
}
