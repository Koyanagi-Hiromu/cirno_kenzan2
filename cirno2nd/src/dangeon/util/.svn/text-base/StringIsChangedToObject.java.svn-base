package dangeon.util;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import main.util.DIRECTION;
import cirno_question.frame.MainFrame;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class StringIsChangedToObject {
	public static Base_Artifact returnBaseDisc(String str, String category_str,
			boolean flag_null_point) {
		Constructor<?> con;
		try {
			Class<?> clazz = Class
					.forName("dangeon.model.object.artifact.item.disc.Disc");
			con = clazz.getConstructor(Point.class, String.class, String.class);
			Object obj;
			System.out.println(MainFrame.ME.disc_one.getSelectedItem());
			obj = con.newInstance(new Point(0, 0),
					MainFrame.ME.disc_one.getSelectedItem(),
					MainFrame.ME.disc_two.getSelectedItem());
			if (obj instanceof Base_Artifact) {
				Base_Artifact ba = (Base_Artifact) obj;
				return ba;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Base_Enemy returnBaseEnemy(String str, int level,
			boolean flag_null_point) {
		Constructor<?> con;
		try {
			Class<?> clazz = Class
					.forName("dangeon.model.object.creature.enemy.".concat(str));
			con = clazz.getConstructor(Point.class, int.class);
			Object obj;
			obj = con.newInstance(
					!flag_null_point ? MassCreater.getCreatureIp() : new Point(
							0, 0), level);
			if (obj instanceof Base_Enemy) {
				Base_Enemy em = (Base_Enemy) obj;
				em.setDirection(DIRECTION.DOWN);
				return em;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Base_Artifact returnBaseItem(String str, String category_str,
			boolean flag_null_point) {
		Constructor<?> con;
		try {
			Class<?> clazz = Class
					.forName("dangeon.model.object.artifact.item."
							.concat(category_str).concat(".").concat(str));
			if (category_str.matches("arrow")) {
				con = clazz.getConstructor(Point.class, boolean.class);
				Object obj = con.newInstance(new Point(0, 0), true);
				return (Base_Artifact) obj;
			}
			con = clazz.getConstructor(Point.class);
			Object obj;
			obj = con.newInstance(!flag_null_point ? MassCreater
					.getCreatureIp() : new Point(0, 0));
			if (obj instanceof Base_Artifact) {
				Base_Artifact ba = (Base_Artifact) obj;
				return ba;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Base_Trap returnBaseTrap(String str, boolean flag_null_point) {
		Constructor<?> con;
		try {
			Class<?> clazz = Class
					.forName("dangeon.model.object.artifact.trap.".concat(str));
			con = clazz.getConstructor(Point.class);
			Object obj;
			obj = con.newInstance(!flag_null_point ? MassCreater
					.getCreatureIp() : new Point(0, 0));
			if (obj instanceof Base_Trap) {
				Base_Trap tr = (Base_Trap) obj;
				return tr;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
