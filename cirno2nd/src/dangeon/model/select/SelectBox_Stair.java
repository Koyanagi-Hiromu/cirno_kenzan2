package dangeon.model.select;

import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.model.object.artifact.device.Stairs;

public class SelectBox_Stair extends ConvEvent {
	private final Stairs S;
	private final String[] SELECTION;

	public SelectBox_Stair(Stairs stairs) {
		super();
		S = stairs;
		SELECTION = stairs.getSlection();
		workAfterPush();
	}

	protected void createConfirm() {
		new ConvEvent("本当に終了しますか？") {
			@Override
			protected Book getNo() {
				return new Book() {

					@Override
					protected void work() {
						new SelectBox_Stair(S);
					}
				};
			}

			@Override
			protected Book getYes() {
				return new Book() {

					@Override
					protected void work() {
						S.saveEnd();
					}
				};
			}

			@Override
			protected int pushCancelAction() {
				return -1;
			}
		}.workAfterPush();
	}

	@Override
	protected Book getContent1() {
		if (SELECTION.length < 1)
			return null;
		return new Book(SELECTION[0]) {
			@Override
			protected void work() {
				S.move();
			}
		};
	}

	@Override
	protected Book getContent2() {
		if (SELECTION.length < 2)
			return null;
		return new Book(SELECTION[1]) {
			@Override
			protected void work() {
			}
		};
	}

	@Override
	protected Book getContent3() {
		if (SELECTION.length < 3)
			return null;
		return new Book(SELECTION[2]) {
			@Override
			protected void work() {
				createConfirm();
			}
		};
	}

}
