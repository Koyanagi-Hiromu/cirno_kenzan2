package dangeon.view.anime;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.util.FileReadSupporter;
import dangeon.controller.task.Task;
import dangeon.model.object.creature.Base_Creature;

public abstract class Base_Anime {
	protected ArrayList<Image> frame_image;

	protected final Base_Creature C;

	private final Task TASK;

	protected int coma = 0;

	private int frame = 0;

	public Base_Anime(Base_Creature c) {
		C = c;
		TASK = null;
	}

	public Base_Anime(Base_Creature c, Task task) {
		C = c;
		TASK = task;
	}

	/**
	 * 
	 * @param g
	 * @return アニメ中ならtrue　終了したならfalse
	 */
	abstract protected boolean draw(Graphics2D g);

	public final boolean drawAnime(Graphics2D g) {
		frame++;
		if (draw(g)) {
			if (TASK != null) {
				TASK.work_appointment(frame);
			}
			return true;
		} else {
			if (TASK != null) {
				TASK.work_appointment(-1);
			}
			return false;
		}
	}

	protected String getBase() {
		return "res/image/";
	}

	public int getComa() {
		return coma;
	}

	protected int getFrame() {
		return frame;
	}

	protected boolean isFrameEqual(int value) {
		return frame == value;
	}

	protected boolean isFrameLessThan(int max) {
		if (frame < max) {
			return true;
		} else {
			frame = max;
			return false;
		}
	}

	protected BufferedImage loadImage(String filename) {
		return FileReadSupporter.readImage("res/image/".concat(filename));
	}
}
