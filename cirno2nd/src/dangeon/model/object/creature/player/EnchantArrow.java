package dangeon.model.object.creature.player;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.arrow.ミニ八卦炉;
import dangeon.model.object.artifact.item.arrow.大砲の弾;
import dangeon.model.object.artifact.item.arrow.毒ナイフ;
import dangeon.model.object.artifact.item.arrow.鉄の矢;
import dangeon.view.util.StringFilter;

public enum EnchantArrow {
	咲夜ナイフ(0, new 鉄の矢(new Point(), false)), 毒ナイフ(1, new 毒ナイフ(new Point(), false)), プチ八卦炉(
			2, new ミニ八卦炉(new Point(), false)), 核弾(3, new 大砲の弾(new Point(),
			false));
	public static EnchantArrow get(int y) {
		for (EnchantArrow a : values()) {
			if (a.Y == y) {
				return a;
			}
		}
		return null;
	}

	public static EnchantArrow getArrow() {
		return arrow;
	}

	public static boolean isEnchant(Base_Artifact a) {
		if (EnchantArrow.getArrow() != null
				&& a.getClass().equals(EnchantArrow.getArrow().CLAZZ)) {
			for (Base_Artifact ba : Belongings.getListItems()) {
				if (a == ba)
					return true;
			}
			for (EnchantArrow ea : values()) {
				if (a == ea.SAMPLE)
					return true;
			}
		}
		return false;
	}

	public static void loadArrowEnchant(Integer index) {
		if (index == null) {
			arrow = null;
		} else {
			arrow = get(index);
		}
	}

	public static Integer saveArrowEnchant() {
		if (arrow == null) {
			return null;
		} else {
			return arrow.Y;
		}
	}

	public static void setArrow(Arrow a) {
		if (arrow != null && arrow.CLAZZ == a.getClass()) {
			arrow = null;
			Message.set("射撃キーのセットを解除した");
		} else {
			for (EnchantArrow arr : values()) {
				if (arr.CLAZZ == a.getClass()) {
					arrow = arr;
					break;
				}
			}
			Message.set(StringFilter.NUMBERS.toString(), EnchantArrow
					.getArrow().name(), Color.WHITE.toString(), "を射撃キーにセットした");
		}
	}

	public static void setArrow(int y) {
		if (arrow == get(y)) {
			arrow = null;
			Message.set("射撃のセットを解除した");
		} else {
			arrow = get(y);
			Message.set(EnchantArrow.getArrow().name(), "をセットした");
		}
	}

	public final Arrow SAMPLE;

	public final Class<? extends Arrow> CLAZZ;

	private static EnchantArrow arrow = null;

	public static int length() {
		return values().length;
	}

	public final Image IM;

	public final int Y;

	private EnchantArrow(int y, Arrow sample) {
		Y = y;
		SAMPLE = sample;
		CLAZZ = SAMPLE.getClass();
		Object obj = null;
		try {
			Constructor<?> con = CLAZZ.getConstructor(Point.class,
					boolean.class);
			obj = con.newInstance(new Point(), false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		IM = ((Base_Artifact) obj).getImage();
	}

	public EnchantArrow getEnchanted() {
		return arrow;
	}

}
