package dungeon_creater.second_ver.readGraphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import main.res.CHARA_IMAGE;
import main.util.DIRECTION;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.object.creature.Base_Creature;

public class ReadingImage {
	static ArrayList<BufferedImage> image_list = new ArrayList<BufferedImage>();
	static final ArrayList<BufferedImage> base_image_list = new ArrayList<BufferedImage>();
	static HashMap<CHARA_IMAGE, Base_Creature> image_map = new HashMap<CHARA_IMAGE, Base_Creature>();
	static HashMap<String, BufferedImage> string_image_map = new HashMap<String, BufferedImage>();

	public static void draw(Graphics2D g) {

	}

	public static Base_Creature getBaseCreatureFromCharaImage(CHARA_IMAGE img) {
		return image_map.get(img);
	}

	public static ArrayList<BufferedImage> getListImage() {
		if (image_list.isEmpty()) {
			setListImage();
		}
		return image_list;
	}

	public static void setImageListConvertEnemyList(
			ArrayList<Base_Creature> list) {
		image_list.clear();
		for (Base_Creature c : list) {
			image_list.add(c.getWalkDot());
		}
	}

	public static void setList() {
		setListImage();
	}

	private static void setListImage() {
		image_list.clear();
		BufferedImage img;
		for (CHARA_IMAGE _img : CHARA_IMAGE.values()) {
			if (_img.name().matches("キスメ") || _img.name().matches("arrow")
					|| _img.name().matches("船")) {
				continue;
			}
			_img.getWalkImage(1, DIRECTION.DOWN, 1);
			for (int i = 0; i <= 2; i++) {
				img = _img.getWalkDot(i);
				Base_Creature c = EnemyTable
						.returnBaseEnemy(_img.name(), i + 1);
				image_map.put(_img, c);
				image_list.add(img);
				base_image_list.add(img);
				string_image_map.put(c.getName(), img);
			}
		}
	}

	public static void setListImage(ArrayList<BufferedImage> _list) {
		if (_list == null) {
			_list = base_image_list;
		}
		image_list = _list;
	}
}
