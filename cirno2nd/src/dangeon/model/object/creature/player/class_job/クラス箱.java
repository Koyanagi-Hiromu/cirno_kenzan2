package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.List;

import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.npc.賽銭箱;

public class クラス箱 extends 賽銭箱 {
	private static final long serialVersionUID = 1L;

	public クラス箱(Point p, List<Base_Artifact> list) {
		super(p, "クラス箱", list);
	}

	@Override
	public void message() {
		new ConvEvent("クラスボーナス賽銭箱だ$全てのアイテムを持ち出せるぞ") {
			@Override
			protected Book getContent1() {
				return new Book("取り出す（複数選択可）") {
					@Override
					protected void work() {
						STRAGE.takeMany();
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

			// @Override
			// protected Book getContent4() {
			// return new Book("説明") {
			// @Override
			// protected void work() {
			// Message.set("※予期しない終了の仕方をしてしまって$※持ち込みアイテムを失った時に$※ご利用下さい@");
			// Message.set("※悪用はご遠慮ください@");
			// }
			// };
			// }
		};
	}
}
