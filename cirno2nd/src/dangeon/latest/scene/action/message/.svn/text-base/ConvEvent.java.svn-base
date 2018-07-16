package dangeon.latest.scene.action.message;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import main.Listener.ACTION;
import main.res.SE;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.selectbox.SelectBox;

/**
 * ConvEvent(String...) からは自動的に生成されますが<br />
 * ConvEvent() から質問する際はworkAtTheSameTimeを呼んでください<br />
 * 会話の補佐クラスだったのが質問用クラスになった名残です<br />
 * 
 * <br />
 * =>【yes/no】:getYes(+getNo)をオーバーライド<br />
 * ____return new Book(){}<br />
 * =>【それ以外】:getContentXをオーバーライド<br />
 * ____return new Book("title"){}<br />
 * <br />
 * ※キャンセル時の上書きはpushCancelをオーバーライドしてreturn true;
 * 
 * @author weray
 * 
 */
public class ConvEvent {
	protected abstract class MySelectBox extends SelectBox {
		public MySelectBox() {
			super(getExn());
			select_box = this;
		}

		@Override
		protected void action_cancel() {
			if (pusedCancel()) {
				SE.SYSTEM_CANCEL.play();
				return;
			} else
				super.action_cancel();
			String s = getCancel();
			if (s != null)
				new Conversation(Conversation.previous_opponent, s);
			end();
		}

		@Override
		protected void action_enter(int index) {
			super.action_enter(index);
			end();
		}

		private void end() {
			select_box = null;
		}

		protected boolean pusedCancel() {
			int i = pushCancelAction() - 1;
			if (i == -1)
				return false;
			if (i == -2)
				return true;
			work(getContent(i).BOOK);
			return false;
		}
	}

	private Book[] books = null;

	private boolean flag_no_character;

	private MySelectBox select_box = null;

	public ConvEvent() {
	}

	public ConvEvent(Book... books) {
		this.books = books;
	}

	public ConvEvent(String... strings) {
		flag_no_character = true;
		new Conversation(this, strings);
	}

	private void createSelectBox() {
		final ArrayList<Book> lis = new ArrayList<Book>(10) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean add(Book e) {
				if (e == null)
					return false;
				return super.add(e);
			}
		};
		if (books != null) {
			for (Book book : books) {
				lis.add(book);
			}
		} else {
			lis.add(getContent1());
			lis.add(getContent2());
			lis.add(getContent3());
			lis.add(getContent4());
			lis.add(getContent5());
			lis.add(getContent6());
			lis.add(getContent7());
			lis.add(getContent8());
			lis.add(getContent9());
			lis.add(getContent10());
		}
		if (!lis.isEmpty()) {
			new MySelectBox() {
				@Override
				protected void action_cancel() {
					SE.SYSTEM_CANCEL.play();
					super.action_cancel();
				}

				@Override
				protected void initializeContents(ArrayList<MenuContent> list) {
					for (Book book : lis) {
						setContents(book);
					}
				}
			};
		}
	}

	public boolean defaultYes() {
		return true;
	}

	protected String getCancel() {
		return null;
	}

	protected Book getContent1() {
		return null;
	}

	protected Book getContent10() {
		return null;
	}

	protected Book getContent2() {
		return null;
	}

	protected Book getContent3() {
		return null;
	}

	protected Book getContent4() {
		return null;
	}

	protected Book getContent5() {
		return null;
	}

	protected Book getContent6() {
		return null;
	}

	protected Book getContent7() {
		return null;
	}

	protected Book getContent8() {
		return null;
	}

	protected Book getContent9() {
		return null;
	}

	protected String[] getExn() {
		return null;
	}

	protected Book getNo() {
		return null;
	}

	protected Book getYes() {
		return null;
	}

	public final boolean isSelection() {
		return select_box != null;
	}

	public final void passActionIntoSelectBox(
			LinkedHashSet<ACTION> tasklist_action) {
		for (ACTION action : tasklist_action) {
			if (isSelection())
				select_box.action(action);
		}
	}

	/**
	 * return 0 でなにもなく終了<br />
	 * -1でキャンセル無効<br />
	 * 1～9でそのコンテンツを選んだことになります<br />
	 * Yes/Noなら1でYES、2でNO<br />
	 * SEは自動で鳴ります<br />
	 * 
	 * @return
	 */
	protected int pushCancelAction() {
		return 0;
	}

	/**
	 * 質問スタート
	 */
	public void workAfterPush() {
		SE.SYSTEM_MENU.play();
		Book yes = getYes();
		if (yes != null) {
			new MySelectBox() {
				@Override
				protected void initializeContents(ArrayList<MenuContent> list) {
					setContents("はい", getYes());
					Book b = getNo();
					if (b == null)
						b = new Book() {

							@Override
							protected void work() {
								action_cancel();
							}
						};
					setContents("いいえ", b);
				}

				@Override
				protected void initialX_Y() {
					x = 0;
					y = defaultYes() ? 0 : 1;
				}
			};
		} else {
			createSelectBox();
		}
	}

	public void workAtTheSameTime() {
		if (flag_no_character)
			workAfterPush();
	}
}