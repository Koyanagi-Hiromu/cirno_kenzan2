package dungeon_creater.second_ver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import main.res.CHARA_IMAGE;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.object.creature.Base_Creature;
import dungeon_creater.second_ver.readGraphics.SortImage;

public class EnemyTableGraphicsPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static EnemyTableGraphicsPanel ME = new EnemyTableGraphicsPanel();
	static TableMainFrame FRAME = TableMainFrame.MAIN;

	/**
	 * ソートや初期時はfalse
	 */
	public boolean repainter = false;

	/**
	 * キャラクターを横に並べた時のX座標限界値
	 */
	private int chara_max_x = 12;

	/**
	 * 縮小前の規定サイズ。これより大きいサイズはこのサイズに切り出される
	 */
	int main_chara_size = 50;

	/**
	 * 縮小サイズ
	 */
	int reduction_chara = 42;

	/**
	 * キャラクターとキャラクターの間の距離
	 */
	int set_chara_space = 2;

	/**
	 * １キャラ分の枠サイズ
	 */
	int one_character_space = reduction_chara + set_chara_space;

	/**
	 * エネミーテーブルの開始X座標位置
	 */
	int table_x = chara_max_x * (reduction_chara + set_chara_space) + 30;
	/**
	 * エネミーテーブルの開始Y座標位置
	 */
	int table_start_y = one_character_space * 4;
	int explain_start_y = 10;
	int explain_height = one_character_space * 3;
	int dungeon_level_control_y = explain_height + explain_start_y + 15;
	int dungeon_level_control_y_line = dungeon_level_control_y + 5;
	int table_y = 30;
	/**
	 * エネミーテーブル枠のwidth
	 */
	int table_width = 430;
	/**
	 * エネミーテーブルの限度数
	 */
	int enemy_max = 10;
	int draw_dungeon_level_start_x = TableMainFrame.MAIN.main_panel_x - 150;
	/**
	 * 座標からキャラクターを返す
	 */
	public HashMap<Point, SubBaseCreature> image_map = new HashMap<Point, SubBaseCreature>();
	int control_tab_width_and_height = 10;
	boolean flag_sort = false;

	public EnemyTableGraphicsPanel() {
		this.setBackground(Color.WHITE);
	}

	/**
	 * main_chara_sizeまでカットする
	 * 
	 * @param _img
	 * @return
	 */
	public BufferedImage cutImageMainCharaSize(BufferedImage _img) {
		int current_x = _img.getWidth();
		int current_y = _img.getHeight();
		int cut_source_x1 = (current_x - main_chara_size) / 2;
		int cut_source_y1 = (current_y - main_chara_size) / 2;
		if (current_x > main_chara_size || current_y > main_chara_size) {
			_img = _img.getSubimage(cut_source_x1, cut_source_y1,
					main_chara_size, main_chara_size);
			current_x = _img.getWidth();
			current_y = _img.getHeight();
		}
		return _img;
	}

	public void drawDungeonLevel(Graphics2D g) {
		g.drawString(String.valueOf(DungeonLevelControl.getDungeonLevel())
				.concat("F"), draw_dungeon_level_start_x + 10,
				20 + table_start_y);
		g.drawLine(draw_dungeon_level_start_x, table_y + table_start_y,
				draw_dungeon_level_start_x + 110, table_y + table_start_y);
		g.drawLine(draw_dungeon_level_start_x, +table_start_y,
				draw_dungeon_level_start_x, table_y + table_start_y);
	}

	public void drawDungeonLevelControl(Graphics2D g) {
		// 操作タブ
		g.drawLine(table_x, dungeon_level_control_y_line,
				table_x + table_width, dungeon_level_control_y_line);
	}

	/**
	 * クリーチャー等の詳細説明
	 */
	public void drawObjectExplain(Graphics2D g) {
		g.drawRect(table_x, explain_start_y, table_width, explain_height);
	}

	/**
	 * テーブルキャラ枠の始点はtable_x,one_charactar。 テーブルを整形
	 * 
	 * @param g
	 */
	public void drawTableCreater(Graphics2D g) {
		g.drawString(FileControl.getFileName(), table_x + 10,
				20 + table_start_y);
		g.drawLine(table_x, table_y + table_start_y, table_x + 150, table_y
				+ table_start_y);
		g.drawLine(table_x, +table_start_y, table_x, table_y + table_start_y);
		for (int i = 1; i <= enemy_max; i++) {
			g.drawRect(table_x, one_character_space * i + table_start_y,
					one_character_space, one_character_space);
			g.drawRect(table_x + one_character_space, one_character_space * i
					+ table_start_y, table_width - one_character_space,
					one_character_space);
		}
	}

	public SubBaseCreature getImageOfPoint(Point p) {
		int x = p.x / (one_character_space);
		int y = p.y / (one_character_space);
		SubBaseCreature sb = image_map.get(new Point(x, y));
		if (sb == null) {
			return null;
		} else {
			return new SubBaseCreature(sb);
		}
	}

	public Point getPointEnemyTable(int y) {
		return new Point(table_x, one_character_space * y + table_start_y);
	}

	public Point getPointObjectExplainBox() {
		return new Point(table_x, explain_start_y);
	}

	public int getYEnemyTableFromPoint(Point p) {
		int x = p.x;
		int y = p.y - one_character_space - table_start_y;
		if (y < 0) {
			return -1;
		}
		y = y / one_character_space;
		if (!(x >= table_x && x <= table_x + one_character_space)) {
			return -1;
		}
		if (y >= enemy_max) {
			return -1;
		}
		return y;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setImages(g2);
		SetObject.draw(g2);
		drawTableCreater(g2);
		drawDungeonLevel(g2);
		drawObjectExplain(g2);
		DungeonLevelControl.drawControler(g2);
		drawDungeonLevelControl(g2);
	}

	/**
	 * 縮小メソッド
	 * 
	 * @param inputImage
	 * @return
	 */
	public BufferedImage reduction(BufferedImage inputImage) {
		// 現在のイメージのサイズ
		int currentWidth = inputImage.getWidth();
		int currentHeight = inputImage.getHeight();

		// 最終的なイメージのサイズ
		int endWidth = reduction_chara;
		int endHeight = reduction_chara;

		// 現在のイメージ
		BufferedImage currentImage = inputImage;
		// 最終的なサイズと現在のイメージの差
		int delta = currentWidth - endWidth;
		// 次に縮小するサイズ
		int nextPow2 = currentWidth >> 1;

		while (currentWidth > 1) {
			// 最終的なイメージとサイズの差が、次に縮小するサイズよりも
			// 小さいかどうか調べる
			if (delta <= nextPow2) {
				// イメージのサイズの差が小さい場合
				if (currentWidth != endWidth) {
					// 最終的な縮小率が 1/2n にならない場合
					BufferedImage tmpImage = new BufferedImage(endWidth,
							endHeight, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g = (Graphics2D) tmpImage.getGraphics();
					g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
							RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g.drawImage(currentImage, 0, 0, tmpImage.getWidth(),
							tmpImage.getHeight(), null);
					g.dispose();

					currentImage = tmpImage;
				}

				return currentImage;
			} else {
				// イメージのサイズの差が大きい場合
				// 更に半分に縮小する
				BufferedImage tmpImage = new BufferedImage(currentWidth >> 1,
						currentHeight >> 1, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = (Graphics2D) tmpImage.getGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.drawImage(currentImage, 0, 0, tmpImage.getWidth(),
						tmpImage.getHeight(), null);
				g.dispose();

				// 変数の更新
				currentImage = tmpImage;
				currentWidth = currentImage.getWidth();
				currentHeight = currentImage.getHeight();
				delta = currentWidth - endWidth;
				nextPow2 = currentWidth >> 1;
			}
		}

		return currentImage;
	}

	public void set() {
		this.setBackground(Color.WHITE);
	}

	/**
	 * エネミーテーブルにBaseCreatureをセットする
	 * 
	 * @param p
	 */
	public void setBaseCreature(Point p) {
		int i = getYEnemyTableFromPoint(p);
		if (i == -1) {
			return;
		}
		SetObject.setEnemyList(i);
	}

	public void setImages(Graphics2D g) {
		int put_x = 0;
		int put_y = 0;
		int count = 0;
		g.setColor(Color.GRAY);
		BufferedImage img;
		if (repainter) {
			SortImage.sort();
			image_map.clear();
			for (SubBaseCreature c : SetObject.enemy_list) {
				image_map.put(new Point(put_x, put_y), c);
				BufferedImage _img = c.C.getWalkDot();
				count++;
				// 読み込みの為
				_img = cutImageMainCharaSize(_img);
				_img = reduction(_img);
				g.drawImage(_img, put_x * (reduction_chara + set_chara_space),
						put_y * (reduction_chara + set_chara_space), this);
				// 線を書く
				int draw_line = (reduction_chara + set_chara_space);

				g.drawLine((put_x + 1) * draw_line, (put_y + 1) * draw_line,
						draw_line * put_x, (put_y + 1) * draw_line);
				g.drawLine((put_x + 1) * draw_line, (put_y + 1) * draw_line,
						draw_line * (put_x + 1), draw_line * put_y);
				put_x++;
				if (put_x >= 12) {
					put_y++;
					put_x = 0;
				}
			}
			return;
		}
		for (CHARA_IMAGE _img : CHARA_IMAGE.values()) {
			if (_img.name().matches("キスメ") || _img.name().matches("arrow")
					|| _img.name().matches("船")
					|| _img.name().matches("Exルーミア")) {
				continue;
			}
			_img.load();
			for (int i = 0; i <= 2; i++) {
				img = _img.getWalkDot(i);
				count++;
				// 読み込みの為
				img = cutImageMainCharaSize(img);
				img = reduction(img);
				g.drawImage(img, put_x * (reduction_chara + set_chara_space),
						put_y * (reduction_chara + set_chara_space), this);
				Base_Creature _c = EnemyTable.returnBaseEnemy(_img.name(),
						i + 1);
				SubBaseCreature c = new SubBaseCreature(_c, 1);
				SetObject.enemy_list.add(c);
				SetObject.base_enemy_list.add(c);
				image_map.put(new Point(put_x, put_y), c);
				// 線を書く
				int draw_line = (reduction_chara + set_chara_space);

				g.drawLine((put_x + 1) * draw_line, (put_y + 1) * draw_line,
						draw_line * put_x, (put_y + 1) * draw_line);
				g.drawLine((put_x + 1) * draw_line, (put_y + 1) * draw_line,
						draw_line * (put_x + 1), draw_line * put_y);
				put_x++;
				if (put_x >= chara_max_x) {
					put_y++;
					put_x = 0;
				}
			}
		}
		repainter = true;
	}
}
