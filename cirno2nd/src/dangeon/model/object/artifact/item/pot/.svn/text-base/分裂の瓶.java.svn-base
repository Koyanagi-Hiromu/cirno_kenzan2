package dangeon.model.object.artifact.item.pot;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import dangeon.model.object.artifact.Base_Artifact;

public class 分裂の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 分裂の瓶(Point p) {
		super(p, "神綺の魔法瓶", 2);
	}

	private Object deepCopy(Serializable obj) {
		if (obj == null) {
			return null;
		}
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(obj);
			ByteArrayInputStream byteIn = new ByteArrayInputStream(
					byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			return in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】@入れたアイテムが増えるわよー@増えたらその分容量を使っちゃうから１つの瓶につき１つしか入らないわ～";
	}

	@Override
	protected void potUse() {
		Base_Artifact a = (Base_Artifact) deepCopy(A);
		LIST.add(a);
		staticCheck();

	}
}
