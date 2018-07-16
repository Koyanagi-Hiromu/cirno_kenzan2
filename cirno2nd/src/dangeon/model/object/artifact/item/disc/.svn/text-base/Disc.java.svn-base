package dangeon.model.object.artifact.item.disc;

import java.awt.Point;

import main.res.Image_Artifact;

public class Disc extends Base_Disc {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected String item_name;

	public Disc(Point p) {
		super(p, "名無しのDISC");
		IM = Image_Artifact.DISC;
		setDetail();
	}

	public Disc(Point p, String s1, String s2) {
		super(p, "DISC");
		IM = Image_Artifact.DISC;
		selectSetDetail(s1, s2);
		System.out.println(s1);
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "対応するカードの修正値が上がるぞ" };
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		sb.append(item_name);
		sb.append("のDISC");
		return sb;
	}

	public void selectSetDetail(char c, char c2) {
		String stage1 = null, stage2 = null;
		for (Disc_Detail d : Disc_Detail.values()) {
			if (stage1 == null && d.name().charAt(0) == c) {
				stage1 = d.name();
			}
			if (stage2 == null && d.name().charAt(0) == c2) {
				stage2 = d.name();
			}
		}
		selectSetDetail(stage1, stage2);
	}

	public void selectSetDetail(String s, String s2) {
		list_stage.clear();
		Disc_Detail d1 = Disc_Detail.selectDisc(s);
		Disc_Detail d2 = Disc_Detail.selectDisc(s2);
		name = item_name = d1.toString().concat("＆").concat(d2.toString());
		list_stage.add(d1.getStage());
		list_stage.add(d2.getStage());
		sim = d1.getSimbol();
		name = item_name;
	}

	protected void setDetail() {
		list_stage.clear();
		Disc_Detail d1 = Disc_Detail.RandomDisc();
		Disc_Detail d2 = Disc_Detail.RandomDisc();
		item_name = d1.toString().concat("＆").concat(d2.toString());
		list_stage.add(d1.getStage());
		list_stage.add(d2.getStage());
		sim = d1.getSimbol();
	}

}
