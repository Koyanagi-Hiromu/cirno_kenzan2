package main.res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import load.Loading;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import dangeon.controller.TurnSystemController;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.disc.Base_Disc;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;

public enum Image_Player {
	//@formatter:off
	// アイテム
	card0, card1, disc0, disc1, disc2, disc3, ring0, ring1, eat, food, grass, item,
	mogu0, mogu1, munya0, munya1, scroll, ribbon0, ribbon1,book0,book1
	// その他
	, rising_hand, down, sleep0, sleep1, taiki1, taiki2, taiki3, taiki4, taiki5, taiki6, taiki7, taiki8
	// その他２
	, otiru1, otiru2, otiru3, otiru4, otiru5, otiru6, otiru7;
	public enum AnimeAccordingToItemClass {
		// SCROLL(SE.SYSTEM_SCROLL, 0,item, scroll, munya0, munya1, munya0,
		// munya1, munya0, munya1),
		GLASS(SE.SYSTEM_EAT, 6, item, grass, grass, mogu0, mogu1, mogu0, mogu1,
				mogu0, mogu1), FOOD(SE.SYSTEM_EAT, 6, item, food, food, mogu0,
				mogu1, mogu0, mogu1, mogu0, mogu1), SCROLL(
				SE.SYSTEM_USING_RING, 0, item, book0, book1, book0, book1,
				book0, book1), CARD(SE.SYSTEM_USING_SPELLCARD, 0, item), CARD_NO_EFFECT(
				SE.SYSTEM_USING_SPELLCARD, 0, item, card0, card1, card0, card1,
				card0, card1), RING(SE.SYSTEM_USING_RING, 0, item, ribbon0,
				ribbon1, ribbon0, ribbon1, ribbon0, ribbon1), DISC(
				SE.SYSTEM_USING_DISC, 0, item, disc0, disc1, disc2, disc3), PITFALL(
				SE.FALL, 0, otiru1, otiru2, otiru3, otiru4, otiru5, otiru6,
				otiru7, otiru7, otiru6, otiru5, otiru5, otiru5);
		private final int SOUND_FRAME;

		private final SE SOUND;

		private final Image_Player[] koma;

		private AnimeAccordingToItemClass(Image_Player... image_Players) {
			koma = image_Players;
			SOUND = null;
			SOUND_FRAME = 0;
		}
		private AnimeAccordingToItemClass(SE se, int frame,
				Image_Player... image_Players) {
			koma = image_Players;
			SOUND = se;
			SOUND_FRAME = frame;
		}
	}
	//@formatter:on
	public static final Image_Player[] TAIKI = { taiki1, taiki2, taiki3,
			taiki4, taiki5, taiki6, taiki7, taiki8 };
	private static final BufferedImage WAND[][] = new BufferedImage[8][3];

	static {
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			int i = d.getIndexFrom0ExceptNeautral();
			for (int j = 0; j < 3; j++) {
				StringBuilder sb = new StringBuilder();
				sb.append("res/image/character_dot/チルノ特殊/wand");
				sb.append(d.NUM);
				sb.append("_");
				sb.append(j + 1);
				sb.append(".png");
				try {
					WAND[i][j] = ImageIO.read(FileReadSupporter.readURL(sb
							.toString()));
				} catch (IOException e) {
					WAND[i][j] = null;
				}
			}
		}
	}

	public final Image IM;

	private static int frame = 0;

	private static AnimeAccordingToItemClass aatic;

	private static Base_Item using_item;

	private static Image_Player im = null;
	private static boolean animating = false;
	private static int f;

	public static AnimeAccordingToItemClass getAATIC() {
		return aatic;
	}

	public static Image getImage() {
		if (aatic == null)
			return im.IM;
		if (f >= aatic.koma.length) {
			f = aatic.koma.length - 1;
		}
		return aatic.koma[f].IM;
	}

	public static Image getWandImage(DIRECTION attacking_direction,
			int attack_No) {
		return WAND[attacking_direction.getIndexFrom0ExceptNeautral()][attack_No];
	}

	public static boolean isAnimating() {
		return animating;
	}

	private static boolean itemUse() {
		if (using_item.isUsingMouse() && BonusConductor.守矢神_口封じ()) {
			SE.MIRACLE_ONIGIRI.play();
			Player.me.setCondition(CONDITION.おにぎり, 5);
		}
		return using_item.itemUse();
	}

	public static void set(AnimeAccordingToItemClass aa) {
		using_item = null;
		frame = -1;
		aatic = aa;
		animating = true;
		upDate();
	}

	public static void set(Base_Item bi) {
		using_item = bi;
		frame = -1;
		if (using_item instanceof Base_Grass) {
			aatic = AnimeAccordingToItemClass.GLASS;
		} else if (using_item instanceof Food) {
			aatic = AnimeAccordingToItemClass.FOOD;
		} else if (using_item instanceof Scrool) {
			aatic = AnimeAccordingToItemClass.SCROLL;
		} else if (using_item instanceof Base_Disc) {
			aatic = AnimeAccordingToItemClass.DISC;
		} else if (using_item instanceof SpellCard) {
			if (Config.isCutIN()) {
				aatic = AnimeAccordingToItemClass.CARD;
			} else {
				aatic = AnimeAccordingToItemClass.CARD_NO_EFFECT;
			}
		} else if (using_item instanceof Ring) {
			aatic = AnimeAccordingToItemClass.RING;
		} else {
			aatic = null;
			if (itemUse()) {
				TurnSystemController.callMeToStartEnemyTurn();
			}
			animating = false;
			return;
		}
		animating = true;
		upDate();
	}

	public static void upDate() {
		if (aatic == null)
			upDate_no_aatic();
		else
			upDate_aatic();
	}

	private static void upDate_aatic() {
		if (frame == aatic.SOUND_FRAME) {
			if (aatic.SOUND != null) {
				aatic.SOUND.play();
			}
		}
		frame++;
		f = frame / 3;
		if (f >= aatic.koma.length) {
			if (using_item != null) {
				if (using_item instanceof SpellCard && Config.isCutIN()) {
					Player.me.setAnimation((SpellCard) using_item);
				} else {
					itemUse();
				}
				if (using_item.isFlagPassTurn())
					TurnSystemController.callMeToStartEnemyTurn();
				if (!(using_item instanceof Staff)) {
					using_item.check();
				}
			}
			animating = false;
		}
	}

	private static void upDate_no_aatic() {
		if (++frame == f) {
			animating = false;
		}
	}

	private Image_Player() {
		Loading.setStr(this);
		IM = loadImage(getURL());
	}

	private String getURL() {
		StringBuilder sb = new StringBuilder();
		sb.append("res/image/character_dot/チルノ特殊/");
		sb.append(name());
		sb.append(".png");
		return sb.toString();
	}

	private BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(FileReadSupporter.readURL(filename));
		} catch (IOException e) {
			return null;
		}
	}

	public void set(int wait_frame) {
		frame = 0;
		f = wait_frame;
		animating = true;
		im = this;
		aatic = null;
	}
}
