package dangeon.view.anime;

import java.awt.Image;
import java.awt.Point;

import main.res.Image_Anime;
import main.res.SE;
import dangeon.controller.task.Task;

public abstract class Effect_Image_Anime extends Base_Effect_Effect {
	protected final Image_Anime IM_ANIME;
	protected final int ANIME_INDEXS[];

	public Effect_Image_Anime(Point p, int frame_by_coma, Image_Anime im_a,
			SE se, int se_frame, Task task, int... anime_index) {
		super(p, (anime_index == null || anime_index.length == 0) ? im_a
				.getLength() : anime_index.length, frame_by_coma, se, se_frame,
				task);
		IM_ANIME = im_a;
		if (anime_index == null || anime_index.length == 0) {
			ANIME_INDEXS = null;
		} else {
			ANIME_INDEXS = anime_index;
		}
	}

	@Override
	public int getComa() {
		if (ANIME_INDEXS == null) {
			return super.getComa();
		} else {
			return ANIME_INDEXS[getFrame() / getMaxComa()];
		}
	}

	@Override
	protected Image getImage(int coma) {
		return IM_ANIME.getImage(coma);
	}
}
