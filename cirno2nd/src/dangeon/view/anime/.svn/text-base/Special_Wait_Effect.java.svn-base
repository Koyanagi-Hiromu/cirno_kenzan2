package dangeon.view.anime;

import java.awt.Graphics2D;

import main.res.SE;
import dangeon.controller.task.Task;

public class Special_Wait_Effect extends Base_Effect {

	private final int SE_FRAME;
	private final SE SOUND;
	public final int MAX;

	private int max_coma = 1;

	/**
	 * 
	 * @param c
	 * @param anime
	 *            アニメの配列の長さ
	 * @param max_coma
	 *            １つのコマのフレーム数
	 */
	public Special_Wait_Effect(int anime, int max_coma) {
		this(anime, max_coma, null, -1);
	}

	/**
	 * 
	 * @param c
	 * @param anime
	 *            アニメの配列の長さ
	 * @param max_coma
	 *            １つのアニメのコマ数
	 * @param se
	 * @param se_frame
	 */
	public Special_Wait_Effect(int anime, int max_coma, SE se, int se_frame) {
		this(anime * max_coma, se, se_frame);
		this.max_coma = max_coma;
	}

	/**
	 * 
	 * @param c
	 * @param anime
	 *            アニメの配列の長さ
	 * @param max_coma
	 *            １つのアニメのコマ数
	 * @param se
	 * @param se_frame
	 */
	public Special_Wait_Effect(int anime, int max_coma, SE se, int se_frame,
			Task task) {
		this(anime * max_coma, se, se_frame, task);
		this.max_coma = max_coma;
	}

	/**
	 * 
	 * @param c
	 * @param max_frame
	 * @param se
	 * @param se_frame
	 *            何フレーム目に鳴らすか。ウェイトなしで鳴らしたい場合は１を入力
	 */
	public Special_Wait_Effect(int max_frame, SE se, int se_frame) {
		super();
		MAX = max_frame;
		SE_FRAME = se_frame;
		SOUND = se;
	}

	/**
	 * 
	 * @param c
	 * @param max_frame
	 * @param se
	 * @param se_frame
	 *            何フレーム目に鳴らすか。ウェイトなしで鳴らしたい場合は１を入力
	 */
	public Special_Wait_Effect(int max_frame, SE se, int se_frame, Task task) {
		super(task);
		MAX = max_frame;
		SE_FRAME = se_frame;
		SOUND = se;
	}

	public Special_Wait_Effect(int max_frame, Task task) {
		this(max_frame, null, 0, task);
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (isFrameEqual(SE_FRAME)) {
			if (SOUND != null) {
				SOUND.play();
			}
		}
		return isFrameLessThan(MAX);
	}

	@Override
	public int getComa() {
		return getFrame() / max_coma;
	}

	protected int getMaxComa() {
		return max_coma;
	}
}
