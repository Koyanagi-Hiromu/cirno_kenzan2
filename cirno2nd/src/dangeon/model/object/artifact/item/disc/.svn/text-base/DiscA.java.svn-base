package dangeon.model.object.artifact.item.disc;

import java.awt.Point;

import dangeon.util.STAGE;

public class DiscA extends Disc {
	private static final long serialVersionUID = 1L;

	private String stage = null;

	public DiscA(Point p) {
		super(p);
	}

	public DiscA(Point p, String stage) {
		super(p);
		this.stage = stage;
		setDetail();
	}

	@Override
	protected void setDetail() {
		list_stage.clear();
		Disc_Detail d1;
		if (stage == null)
			d1 = Disc_Detail.RandomDisc();
		else
			d1 = Disc_Detail.selectDisc(stage);
		name = item_name = d1.toString();
		list_stage.add(d1.getStage());
		list_stage.add(STAGE.PLAYER);
		sim = d1.getSimbol();
	}

}
