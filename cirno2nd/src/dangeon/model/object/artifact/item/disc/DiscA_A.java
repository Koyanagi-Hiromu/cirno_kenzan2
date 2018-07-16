package dangeon.model.object.artifact.item.disc;

import java.awt.Point;

public class DiscA_A extends Disc {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DiscA_A(Point p) {
		super(p);
	}

	@Override
	protected void setDetail() {
		list_stage.clear();
		Disc_Detail d1 = Disc_Detail.RandomDisc();
		Disc_Detail d2 = d1;
		name = item_name = d1.toString().concat("＆").concat(d2.toString());
		list_stage.add(d1.getStage());
		list_stage.add(d2.getStage());
		sim = d1.getSimbol();
	}

}
