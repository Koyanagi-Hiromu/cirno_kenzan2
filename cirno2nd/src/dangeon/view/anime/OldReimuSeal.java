package dangeon.view.anime;

import java.awt.Image;
import java.awt.image.BufferedImage;

import dangeon.model.object.creature.Base_Creature;

public class OldReimuSeal extends Base_Effect_Effect {

	private final BufferedImage[] BODY;

	public OldReimuSeal(Base_Creature c, Base_Creature target) {
		super(target.getMassPoint(), 10, 2, null, 0, null);
		int coma = 10;
		String base = "anime/";
		BufferedImage bi;
		bi = loadImage(base.concat("old_reimu_effect.png"));
		int index = c.getDirection().getIndexFrom0ExceptNeautral();
		int w = bi.getWidth() / 8;
		int x = index * w;
		int h = bi.getHeight() / coma;
		BODY = new BufferedImage[coma];
		for (int i = 0; i < coma; i++) {
			BODY[i] = bi.getSubimage(x, h * i, w, h);
		}
	}

	@Override
	protected int getDelX() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getDelX();
	}

	@Override
	protected int getDelY() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getDelY();
	}

	@Override
	protected Image getImage(int coma) {
		return BODY[coma];
	}

}
